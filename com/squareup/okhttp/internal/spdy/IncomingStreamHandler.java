package com.squareup.okhttp.internal.spdy;

import java.io.IOException;

public interface IncomingStreamHandler {
    public static final IncomingStreamHandler REFUSE_INCOMING_STREAMS;

    /* renamed from: com.squareup.okhttp.internal.spdy.IncomingStreamHandler.1 */
    class C02541 implements IncomingStreamHandler {
        C02541() {
        }

        public void receive(SpdyStream stream) throws IOException {
            stream.close(ErrorCode.REFUSED_STREAM);
        }
    }

    void receive(SpdyStream spdyStream) throws IOException;

    static {
        REFUSE_INCOMING_STREAMS = new C02541();
    }
}
