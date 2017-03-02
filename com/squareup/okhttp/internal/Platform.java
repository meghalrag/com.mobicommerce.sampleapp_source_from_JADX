package com.squareup.okhttp.internal;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import javax.net.ssl.SSLSocket;

public class Platform {
    private static final Platform PLATFORM;
    private Constructor<DeflaterOutputStream> deflaterConstructor;

    private static class JettyNpnProvider implements InvocationHandler {
        private final List<String> protocols;
        private String selected;
        private boolean unsupported;

        public JettyNpnProvider(List<String> protocols) {
            this.protocols = protocols;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String methodName = method.getName();
            Class<?> returnType = method.getReturnType();
            if (args == null) {
                args = Util.EMPTY_STRING_ARRAY;
            }
            if (methodName.equals("supports") && Boolean.TYPE == returnType) {
                return Boolean.valueOf(true);
            }
            if (methodName.equals("unsupported") && Void.TYPE == returnType) {
                this.unsupported = true;
                return null;
            } else if (methodName.equals("protocols") && args.length == 0) {
                return this.protocols;
            } else {
                if (methodName.equals("selectProtocol") && String.class == returnType && args.length == 1 && (args[0] == null || (args[0] instanceof List))) {
                    List<?> serverProtocols = args[0];
                    this.selected = (String) this.protocols.get(0);
                    return this.selected;
                } else if (!methodName.equals("protocolSelected") || args.length != 1) {
                    return method.invoke(this, args);
                } else {
                    this.selected = (String) args[0];
                    return null;
                }
            }
        }
    }

    private static class Android23 extends Platform {
        protected final Class<?> openSslSocketClass;
        private final Method setHostname;
        private final Method setUseSessionTickets;

        private Android23(Class<?> openSslSocketClass, Method setUseSessionTickets, Method setHostname) {
            this.openSslSocketClass = openSslSocketClass;
            this.setUseSessionTickets = setUseSessionTickets;
            this.setHostname = setHostname;
        }

        public void connectSocket(Socket socket, InetSocketAddress address, int connectTimeout) throws IOException {
            try {
                socket.connect(address, connectTimeout);
            } catch (SecurityException se) {
                IOException ioException = new IOException("Exception in connect");
                ioException.initCause(se);
                throw ioException;
            }
        }

        public void enableTlsExtensions(SSLSocket socket, String uriHost) {
            super.enableTlsExtensions(socket, uriHost);
            if (this.openSslSocketClass.isInstance(socket)) {
                try {
                    this.setUseSessionTickets.invoke(socket, new Object[]{Boolean.valueOf(true)});
                    this.setHostname.invoke(socket, new Object[]{uriHost});
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e2) {
                    throw new AssertionError(e2);
                }
            }
        }
    }

    private static class JdkWithJettyNpnPlatform extends Platform {
        private final Class<?> clientProviderClass;
        private final Method getMethod;
        private final Method putMethod;
        private final Class<?> serverProviderClass;

        public JdkWithJettyNpnPlatform(Method putMethod, Method getMethod, Class<?> clientProviderClass, Class<?> serverProviderClass) {
            this.putMethod = putMethod;
            this.getMethod = getMethod;
            this.clientProviderClass = clientProviderClass;
            this.serverProviderClass = serverProviderClass;
        }

        public void setNpnProtocols(SSLSocket socket, byte[] npnProtocols) {
            try {
                List<String> strings = new ArrayList();
                int i = 0;
                while (i < npnProtocols.length) {
                    int i2 = i + 1;
                    int length = npnProtocols[i];
                    strings.add(new String(npnProtocols, i2, length, "US-ASCII"));
                    i = i2 + length;
                }
                Object provider = Proxy.newProxyInstance(Platform.class.getClassLoader(), new Class[]{this.clientProviderClass, this.serverProviderClass}, new JettyNpnProvider(strings));
                this.putMethod.invoke(null, new Object[]{socket, provider});
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            } catch (InvocationTargetException e2) {
                throw new AssertionError(e2);
            } catch (IllegalAccessException e3) {
                throw new AssertionError(e3);
            }
        }

        public byte[] getNpnSelectedProtocol(SSLSocket socket) {
            byte[] bArr = null;
            try {
                JettyNpnProvider provider = (JettyNpnProvider) Proxy.getInvocationHandler(this.getMethod.invoke(null, new Object[]{socket}));
                if (!provider.unsupported && provider.selected == null) {
                    Logger.getLogger("com.squareup.okhttp.OkHttpClient").log(Level.INFO, "NPN callback dropped so SPDY is disabled. Is npn-boot on the boot class path?");
                } else if (!provider.unsupported) {
                    bArr = provider.selected.getBytes("US-ASCII");
                }
                return bArr;
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError();
            } catch (InvocationTargetException e2) {
                throw new AssertionError();
            } catch (IllegalAccessException e3) {
                throw new AssertionError();
            }
        }
    }

    private static class Android41 extends Android23 {
        private final Method getNpnSelectedProtocol;
        private final Method setNpnProtocols;

        private Android41(Class<?> openSslSocketClass, Method setUseSessionTickets, Method setHostname, Method setNpnProtocols, Method getNpnSelectedProtocol) {
            super(setUseSessionTickets, setHostname, null);
            this.setNpnProtocols = setNpnProtocols;
            this.getNpnSelectedProtocol = getNpnSelectedProtocol;
        }

        public void setNpnProtocols(SSLSocket socket, byte[] npnProtocols) {
            if (this.openSslSocketClass.isInstance(socket)) {
                try {
                    this.setNpnProtocols.invoke(socket, new Object[]{npnProtocols});
                } catch (IllegalAccessException e) {
                    throw new AssertionError(e);
                } catch (InvocationTargetException e2) {
                    throw new RuntimeException(e2);
                }
            }
        }

        public byte[] getNpnSelectedProtocol(SSLSocket socket) {
            if (!this.openSslSocketClass.isInstance(socket)) {
                return null;
            }
            try {
                return (byte[]) this.getNpnSelectedProtocol.invoke(socket, new Object[0]);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }
    }

    static {
        PLATFORM = findPlatform();
    }

    public static Platform get() {
        return PLATFORM;
    }

    public String getPrefix() {
        return "OkHttp";
    }

    public void logW(String warning) {
        System.out.println(warning);
    }

    public void tagSocket(Socket socket) throws SocketException {
    }

    public void untagSocket(Socket socket) throws SocketException {
    }

    public URI toUriLenient(URL url) throws URISyntaxException {
        return url.toURI();
    }

    public void enableTlsExtensions(SSLSocket socket, String uriHost) {
    }

    public void supportTlsIntolerantServer(SSLSocket socket) {
        socket.setEnabledProtocols(new String[]{"SSLv3"});
    }

    public byte[] getNpnSelectedProtocol(SSLSocket socket) {
        return null;
    }

    public void setNpnProtocols(SSLSocket socket, byte[] npnProtocols) {
    }

    public void connectSocket(Socket socket, InetSocketAddress address, int connectTimeout) throws IOException {
        socket.connect(address, connectTimeout);
    }

    public OutputStream newDeflaterOutputStream(OutputStream out, Deflater deflater, boolean syncFlush) {
        try {
            Constructor<DeflaterOutputStream> constructor = this.deflaterConstructor;
            if (constructor == null) {
                constructor = DeflaterOutputStream.class.getConstructor(new Class[]{OutputStream.class, Deflater.class, Boolean.TYPE});
                this.deflaterConstructor = constructor;
            }
            return (OutputStream) constructor.newInstance(new Object[]{out, deflater, Boolean.valueOf(syncFlush)});
        } catch (NoSuchMethodException e) {
            throw new UnsupportedOperationException("Cannot SPDY; no SYNC_FLUSH available");
        } catch (InvocationTargetException e2) {
            RuntimeException runtimeException;
            if (e2.getCause() instanceof RuntimeException) {
                runtimeException = (RuntimeException) e2.getCause();
            } else {
                runtimeException = new RuntimeException(e2.getCause());
            }
            throw runtimeException;
        } catch (InstantiationException e3) {
            throw new RuntimeException(e3);
        } catch (IllegalAccessException e4) {
            throw new AssertionError();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.squareup.okhttp.internal.Platform findPlatform() {
        /*
        r2 = "com.android.org.conscrypt.OpenSSLSocketImpl";
        r3 = java.lang.Class.forName(r2);	 Catch:{ ClassNotFoundException -> 0x0040, NoSuchMethodException -> 0x0055 }
    L_0x0006:
        r2 = "setUseSessionTickets";
        r8 = 1;
        r8 = new java.lang.Class[r8];	 Catch:{ ClassNotFoundException -> 0x00d9, NoSuchMethodException -> 0x0055 }
        r9 = 0;
        r10 = java.lang.Boolean.TYPE;	 Catch:{ ClassNotFoundException -> 0x00d9, NoSuchMethodException -> 0x0055 }
        r8[r9] = r10;	 Catch:{ ClassNotFoundException -> 0x00d9, NoSuchMethodException -> 0x0055 }
        r4 = r3.getMethod(r2, r8);	 Catch:{ ClassNotFoundException -> 0x00d9, NoSuchMethodException -> 0x0055 }
        r2 = "setHostname";
        r8 = 1;
        r8 = new java.lang.Class[r8];	 Catch:{ ClassNotFoundException -> 0x00d9, NoSuchMethodException -> 0x0055 }
        r9 = 0;
        r10 = java.lang.String.class;
        r8[r9] = r10;	 Catch:{ ClassNotFoundException -> 0x00d9, NoSuchMethodException -> 0x0055 }
        r5 = r3.getMethod(r2, r8);	 Catch:{ ClassNotFoundException -> 0x00d9, NoSuchMethodException -> 0x0055 }
        r2 = "setNpnProtocols";
        r8 = 1;
        r8 = new java.lang.Class[r8];	 Catch:{ NoSuchMethodException -> 0x0048, ClassNotFoundException -> 0x00d9 }
        r9 = 0;
        r10 = byte[].class;
        r8[r9] = r10;	 Catch:{ NoSuchMethodException -> 0x0048, ClassNotFoundException -> 0x00d9 }
        r6 = r3.getMethod(r2, r8);	 Catch:{ NoSuchMethodException -> 0x0048, ClassNotFoundException -> 0x00d9 }
        r2 = "getNpnSelectedProtocol";
        r8 = 0;
        r8 = new java.lang.Class[r8];	 Catch:{ NoSuchMethodException -> 0x0048, ClassNotFoundException -> 0x00d9 }
        r7 = r3.getMethod(r2, r8);	 Catch:{ NoSuchMethodException -> 0x0048, ClassNotFoundException -> 0x00d9 }
        r2 = new com.squareup.okhttp.internal.Platform$Android41;	 Catch:{ NoSuchMethodException -> 0x0048, ClassNotFoundException -> 0x00d9 }
        r8 = 0;
        r2.<init>(r4, r5, r6, r7, r8);	 Catch:{ NoSuchMethodException -> 0x0048, ClassNotFoundException -> 0x00d9 }
    L_0x003f:
        return r2;
    L_0x0040:
        r16 = move-exception;
        r2 = "org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl";
        r3 = java.lang.Class.forName(r2);	 Catch:{ ClassNotFoundException -> 0x00d9, NoSuchMethodException -> 0x0055 }
        goto L_0x0006;
    L_0x0048:
        r16 = move-exception;
        r8 = new com.squareup.okhttp.internal.Platform$Android23;	 Catch:{ ClassNotFoundException -> 0x00d9, NoSuchMethodException -> 0x0055 }
        r12 = 0;
        r13 = 0;
        r9 = r3;
        r10 = r4;
        r11 = r5;
        r8.<init>(r10, r11, r12, r13);	 Catch:{ ClassNotFoundException -> 0x00d9, NoSuchMethodException -> 0x0055 }
        r2 = r8;
        goto L_0x003f;
    L_0x0055:
        r2 = move-exception;
    L_0x0056:
        r18 = "org.eclipse.jetty.npn.NextProtoNego";
        r17 = java.lang.Class.forName(r18);	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r2 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r8 = java.lang.String.valueOf(r18);	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r2.<init>(r8);	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r8 = "$Provider";
        r2 = r2.append(r8);	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r2 = r2.toString();	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r19 = java.lang.Class.forName(r2);	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r2 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r8 = java.lang.String.valueOf(r18);	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r2.<init>(r8);	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r8 = "$ClientProvider";
        r2 = r2.append(r8);	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r2 = r2.toString();	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r14 = java.lang.Class.forName(r2);	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r2 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r8 = java.lang.String.valueOf(r18);	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r2.<init>(r8);	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r8 = "$ServerProvider";
        r2 = r2.append(r8);	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r2 = r2.toString();	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r21 = java.lang.Class.forName(r2);	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r2 = "put";
        r8 = 2;
        r8 = new java.lang.Class[r8];	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r9 = 0;
        r10 = javax.net.ssl.SSLSocket.class;
        r8[r9] = r10;	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r9 = 1;
        r8[r9] = r19;	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r0 = r17;
        r20 = r0.getMethod(r2, r8);	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r2 = "get";
        r8 = 1;
        r8 = new java.lang.Class[r8];	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r9 = 0;
        r10 = javax.net.ssl.SSLSocket.class;
        r8[r9] = r10;	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r0 = r17;
        r15 = r0.getMethod(r2, r8);	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r2 = new com.squareup.okhttp.internal.Platform$JdkWithJettyNpnPlatform;	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        r0 = r20;
        r1 = r21;
        r2.<init>(r0, r15, r14, r1);	 Catch:{ ClassNotFoundException -> 0x00cf, NoSuchMethodException -> 0x00d7 }
        goto L_0x003f;
    L_0x00cf:
        r2 = move-exception;
    L_0x00d0:
        r2 = new com.squareup.okhttp.internal.Platform;
        r2.<init>();
        goto L_0x003f;
    L_0x00d7:
        r2 = move-exception;
        goto L_0x00d0;
    L_0x00d9:
        r2 = move-exception;
        goto L_0x0056;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.Platform.findPlatform():com.squareup.okhttp.internal.Platform");
    }
}
