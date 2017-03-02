package com.squareup.okhttp.internal.spdy;

import android.support.v4.media.TransportMediator;
import com.paypal.android.sdk.payments.BuildConfig;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

final class Hpack {
    static final List<HeaderEntry> INITIAL_CLIENT_TO_SERVER_HEADER_TABLE;
    static final int INITIAL_CLIENT_TO_SERVER_HEADER_TABLE_LENGTH = 1262;
    static final List<HeaderEntry> INITIAL_SERVER_TO_CLIENT_HEADER_TABLE;
    static final int INITIAL_SERVER_TO_CLIENT_HEADER_TABLE_LENGTH = 1304;
    static final int PREFIX_5_BITS = 31;
    static final int PREFIX_6_BITS = 63;
    static final int PREFIX_7_BITS = 127;
    static final int PREFIX_8_BITS = 255;

    static class HeaderEntry {
        private final String name;
        private final String value;

        HeaderEntry(String name, String value) {
            this.name = name;
            this.value = value;
        }

        int length() {
            return (this.name.length() + 32) + this.value.length();
        }
    }

    static class Reader {
        private long bufferSize;
        private long bytesLeft;
        private final List<String> emittedHeaders;
        private final List<HeaderEntry> headerTable;
        private final DataInputStream in;
        private final long maxBufferSize;
        private final BitSet referenceSet;

        Reader(DataInputStream in, boolean client) {
            this.maxBufferSize = 4096;
            this.referenceSet = new BitSet();
            this.emittedHeaders = new ArrayList();
            this.bufferSize = 0;
            this.bytesLeft = 0;
            this.in = in;
            if (client) {
                this.headerTable = new ArrayList(Hpack.INITIAL_SERVER_TO_CLIENT_HEADER_TABLE);
                this.bufferSize = 1304;
                return;
            }
            this.headerTable = new ArrayList(Hpack.INITIAL_CLIENT_TO_SERVER_HEADER_TABLE);
            this.bufferSize = 1262;
        }

        public void readHeaders(int byteCount) throws IOException {
            this.bytesLeft += (long) byteCount;
            while (this.bytesLeft > 0) {
                int b = readByte();
                if ((b & TransportMediator.FLAG_KEY_MEDIA_NEXT) != 0) {
                    readIndexedHeader(readInt(b, Hpack.PREFIX_7_BITS));
                } else if (b == 96) {
                    readLiteralHeaderWithoutIndexingNewName();
                } else if ((b & 224) == 96) {
                    readLiteralHeaderWithoutIndexingIndexedName(readInt(b, Hpack.PREFIX_5_BITS) - 1);
                } else if (b == 64) {
                    readLiteralHeaderWithIncrementalIndexingNewName();
                } else if ((b & 224) == 64) {
                    readLiteralHeaderWithIncrementalIndexingIndexedName(readInt(b, Hpack.PREFIX_5_BITS) - 1);
                } else if (b == 0) {
                    readLiteralHeaderWithSubstitutionIndexingNewName();
                } else if ((b & 192) == 0) {
                    readLiteralHeaderWithSubstitutionIndexingIndexedName(readInt(b, Hpack.PREFIX_6_BITS) - 1);
                } else {
                    throw new AssertionError();
                }
            }
        }

        public void emitReferenceSet() {
            int i = this.referenceSet.nextSetBit(0);
            while (i != -1) {
                this.emittedHeaders.add(getName(i));
                this.emittedHeaders.add(getValue(i));
                i = this.referenceSet.nextSetBit(i + 1);
            }
        }

        public List<String> getAndReset() {
            List<String> result = new ArrayList(this.emittedHeaders);
            this.emittedHeaders.clear();
            return result;
        }

        private void readIndexedHeader(int index) {
            if (this.referenceSet.get(index)) {
                this.referenceSet.clear(index);
            } else {
                this.referenceSet.set(index);
            }
        }

        private void readLiteralHeaderWithoutIndexingIndexedName(int index) throws IOException {
            String name = getName(index);
            String value = readString();
            this.emittedHeaders.add(name);
            this.emittedHeaders.add(value);
        }

        private void readLiteralHeaderWithoutIndexingNewName() throws IOException {
            String name = readString();
            String value = readString();
            this.emittedHeaders.add(name);
            this.emittedHeaders.add(value);
        }

        private void readLiteralHeaderWithIncrementalIndexingIndexedName(int nameIndex) throws IOException {
            insertIntoHeaderTable(this.headerTable.size(), new HeaderEntry(getName(nameIndex), readString()));
        }

        private void readLiteralHeaderWithIncrementalIndexingNewName() throws IOException {
            insertIntoHeaderTable(this.headerTable.size(), new HeaderEntry(readString(), readString()));
        }

        private void readLiteralHeaderWithSubstitutionIndexingIndexedName(int nameIndex) throws IOException {
            insertIntoHeaderTable(readInt(readByte(), Hpack.PREFIX_8_BITS), new HeaderEntry(getName(nameIndex), readString()));
        }

        private void readLiteralHeaderWithSubstitutionIndexingNewName() throws IOException {
            insertIntoHeaderTable(readInt(readByte(), Hpack.PREFIX_8_BITS), new HeaderEntry(readString(), readString()));
        }

        private String getName(int index) {
            return ((HeaderEntry) this.headerTable.get(index)).name;
        }

        private String getValue(int index) {
            return ((HeaderEntry) this.headerTable.get(index)).value;
        }

        private void insertIntoHeaderTable(int index, HeaderEntry entry) {
            int delta = entry.length();
            if (index != this.headerTable.size()) {
                delta -= ((HeaderEntry) this.headerTable.get(index)).length();
            }
            if (((long) delta) > 4096) {
                this.headerTable.clear();
                this.bufferSize = 0;
                this.emittedHeaders.add(entry.name);
                this.emittedHeaders.add(entry.value);
                return;
            }
            while (this.bufferSize + ((long) delta) > 4096) {
                remove(0);
                index--;
            }
            if (index < 0) {
                index = 0;
                this.headerTable.add(0, entry);
            } else if (index == this.headerTable.size()) {
                this.headerTable.add(index, entry);
            } else {
                this.headerTable.set(index, entry);
            }
            this.bufferSize += (long) delta;
            this.referenceSet.set(index);
        }

        private void remove(int index) {
            this.bufferSize -= (long) ((HeaderEntry) this.headerTable.remove(index)).length();
        }

        private int readByte() throws IOException {
            this.bytesLeft--;
            return this.in.readByte() & Hpack.PREFIX_8_BITS;
        }

        int readInt(int firstByte, int prefixMask) throws IOException {
            int prefix = firstByte & prefixMask;
            if (prefix < prefixMask) {
                return prefix;
            }
            int result = prefixMask;
            int shift = 0;
            while (true) {
                int b = readByte();
                if ((b & TransportMediator.FLAG_KEY_MEDIA_NEXT) == 0) {
                    return result + (b << shift);
                }
                result += (b & Hpack.PREFIX_7_BITS) << shift;
                shift += 7;
            }
        }

        public String readString() throws IOException {
            int length = readInt(readByte(), Hpack.PREFIX_8_BITS);
            byte[] encoded = new byte[length];
            this.bytesLeft -= (long) length;
            this.in.readFully(encoded);
            return new String(encoded, "UTF-8");
        }
    }

    static class Writer {
        private final OutputStream out;

        Writer(OutputStream out) {
            this.out = out;
        }

        public void writeHeaders(List<String> nameValueBlock) throws IOException {
            int size = nameValueBlock.size();
            for (int i = 0; i < size; i += 2) {
                this.out.write(96);
                writeString((String) nameValueBlock.get(i));
                writeString((String) nameValueBlock.get(i + 1));
            }
        }

        public void writeInt(int value, int prefixMask, int bits) throws IOException {
            if (value < prefixMask) {
                this.out.write(bits | value);
                return;
            }
            this.out.write(bits | prefixMask);
            value -= prefixMask;
            while (value >= TransportMediator.FLAG_KEY_MEDIA_NEXT) {
                this.out.write((value & Hpack.PREFIX_7_BITS) | TransportMediator.FLAG_KEY_MEDIA_NEXT);
                value >>>= 7;
            }
            this.out.write(value);
        }

        public void writeString(String headerName) throws IOException {
            byte[] bytes = headerName.getBytes("UTF-8");
            writeInt(bytes.length, Hpack.PREFIX_8_BITS, 0);
            this.out.write(bytes);
        }
    }

    static {
        INITIAL_CLIENT_TO_SERVER_HEADER_TABLE = Arrays.asList(new HeaderEntry[]{new HeaderEntry(":scheme", "http"), new HeaderEntry(":scheme", "https"), new HeaderEntry(":host", BuildConfig.VERSION_NAME), new HeaderEntry(":path", "/"), new HeaderEntry(":method", "GET"), new HeaderEntry("accept", BuildConfig.VERSION_NAME), new HeaderEntry("accept-charset", BuildConfig.VERSION_NAME), new HeaderEntry("accept-encoding", BuildConfig.VERSION_NAME), new HeaderEntry("accept-language", BuildConfig.VERSION_NAME), new HeaderEntry("cookie", BuildConfig.VERSION_NAME), new HeaderEntry("if-modified-since", BuildConfig.VERSION_NAME), new HeaderEntry("user-agent", BuildConfig.VERSION_NAME), new HeaderEntry("referer", BuildConfig.VERSION_NAME), new HeaderEntry("authorization", BuildConfig.VERSION_NAME), new HeaderEntry("allow", BuildConfig.VERSION_NAME), new HeaderEntry("cache-control", BuildConfig.VERSION_NAME), new HeaderEntry("connection", BuildConfig.VERSION_NAME), new HeaderEntry("content-length", BuildConfig.VERSION_NAME), new HeaderEntry("content-type", BuildConfig.VERSION_NAME), new HeaderEntry("date", BuildConfig.VERSION_NAME), new HeaderEntry("expect", BuildConfig.VERSION_NAME), new HeaderEntry("from", BuildConfig.VERSION_NAME), new HeaderEntry("if-match", BuildConfig.VERSION_NAME), new HeaderEntry("if-none-match", BuildConfig.VERSION_NAME), new HeaderEntry("if-range", BuildConfig.VERSION_NAME), new HeaderEntry("if-unmodified-since", BuildConfig.VERSION_NAME), new HeaderEntry("max-forwards", BuildConfig.VERSION_NAME), new HeaderEntry("proxy-authorization", BuildConfig.VERSION_NAME), new HeaderEntry("range", BuildConfig.VERSION_NAME), new HeaderEntry("via", BuildConfig.VERSION_NAME)});
        INITIAL_SERVER_TO_CLIENT_HEADER_TABLE = Arrays.asList(new HeaderEntry[]{new HeaderEntry(":status", "200"), new HeaderEntry("age", BuildConfig.VERSION_NAME), new HeaderEntry("cache-control", BuildConfig.VERSION_NAME), new HeaderEntry("content-length", BuildConfig.VERSION_NAME), new HeaderEntry("content-type", BuildConfig.VERSION_NAME), new HeaderEntry("date", BuildConfig.VERSION_NAME), new HeaderEntry("etag", BuildConfig.VERSION_NAME), new HeaderEntry("expires", BuildConfig.VERSION_NAME), new HeaderEntry("last-modified", BuildConfig.VERSION_NAME), new HeaderEntry("server", BuildConfig.VERSION_NAME), new HeaderEntry("set-cookie", BuildConfig.VERSION_NAME), new HeaderEntry("vary", BuildConfig.VERSION_NAME), new HeaderEntry("via", BuildConfig.VERSION_NAME), new HeaderEntry("access-control-allow-origin", BuildConfig.VERSION_NAME), new HeaderEntry("accept-ranges", BuildConfig.VERSION_NAME), new HeaderEntry("allow", BuildConfig.VERSION_NAME), new HeaderEntry("connection", BuildConfig.VERSION_NAME), new HeaderEntry("content-disposition", BuildConfig.VERSION_NAME), new HeaderEntry("content-encoding", BuildConfig.VERSION_NAME), new HeaderEntry("content-language", BuildConfig.VERSION_NAME), new HeaderEntry("content-location", BuildConfig.VERSION_NAME), new HeaderEntry("content-range", BuildConfig.VERSION_NAME), new HeaderEntry("link", BuildConfig.VERSION_NAME), new HeaderEntry("location", BuildConfig.VERSION_NAME), new HeaderEntry("proxy-authenticate", BuildConfig.VERSION_NAME), new HeaderEntry("refresh", BuildConfig.VERSION_NAME), new HeaderEntry("retry-after", BuildConfig.VERSION_NAME), new HeaderEntry("strict-transport-security", BuildConfig.VERSION_NAME), new HeaderEntry("transfer-encoding", BuildConfig.VERSION_NAME), new HeaderEntry("www-authenticate", BuildConfig.VERSION_NAME)});
    }

    private Hpack() {
    }
}
