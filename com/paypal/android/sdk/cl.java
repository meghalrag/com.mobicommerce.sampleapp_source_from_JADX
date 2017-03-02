package com.paypal.android.sdk;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

final class cl implements Runnable {
    private final AbstractHttpClient f445a;
    private final HttpContext f446b;
    private final HttpUriRequest f447c;
    private final cm f448d;
    private boolean f449e;
    private int f450f;

    public cl(AbstractHttpClient abstractHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, cm cmVar) {
        this.f445a = abstractHttpClient;
        this.f446b = httpContext;
        this.f447c = httpUriRequest;
        this.f448d = cmVar;
        if (cmVar instanceof co) {
            this.f449e = true;
        }
    }

    private void m317a() {
        if (!Thread.currentThread().isInterrupted()) {
            try {
                HttpResponse execute = this.f445a.execute(this.f447c, this.f446b);
                if (!Thread.currentThread().isInterrupted() && this.f448d != null) {
                    this.f448d.m325a(execute);
                }
            } catch (IOException e) {
                if (!Thread.currentThread().isInterrupted()) {
                    throw e;
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r6 = this;
        r1 = 0;
        r0 = r6.f448d;	 Catch:{ IOException -> 0x0030 }
        if (r0 == 0) goto L_0x000a;
    L_0x0005:
        r0 = r6.f448d;	 Catch:{ IOException -> 0x0030 }
        r0.m319a();	 Catch:{ IOException -> 0x0030 }
    L_0x000a:
        r0 = 1;
        r2 = r6.f445a;	 Catch:{ IOException -> 0x0030 }
        r3 = r2.getHttpRequestRetryHandler();	 Catch:{ IOException -> 0x0030 }
        r2 = r0;
        r0 = r1;
    L_0x0013:
        if (r2 == 0) goto L_0x00a3;
    L_0x0015:
        r6.m317a();	 Catch:{ UnknownHostException -> 0x0022, SocketException -> 0x0044, SocketTimeoutException -> 0x0052, NoHttpResponseException -> 0x0060, IOException -> 0x006e, NullPointerException -> 0x007c }
    L_0x0018:
        r0 = r6.f448d;	 Catch:{ IOException -> 0x0030 }
        if (r0 == 0) goto L_0x0021;
    L_0x001c:
        r0 = r6.f448d;	 Catch:{ IOException -> 0x0030 }
        r0.m326b();	 Catch:{ IOException -> 0x0030 }
    L_0x0021:
        return;
    L_0x0022:
        r0 = move-exception;
        r2 = r6.f448d;	 Catch:{ IOException -> 0x0030 }
        if (r2 == 0) goto L_0x0018;
    L_0x0027:
        r2 = r6.f448d;	 Catch:{ IOException -> 0x0030 }
        r3 = "can't resolve host";
        r4 = 0;
        r2.m328b(r0, r3, r4);	 Catch:{ IOException -> 0x0030 }
        goto L_0x0018;
    L_0x0030:
        r0 = move-exception;
        r2 = r6.f448d;
        if (r2 == 0) goto L_0x0021;
    L_0x0035:
        r2 = r6.f448d;
        r2.m326b();
        r2 = r6.f449e;
        if (r2 == 0) goto L_0x00ac;
    L_0x003e:
        r2 = r6.f448d;
        r2.m324a(r0, r1, r1);
        goto L_0x0021;
    L_0x0044:
        r0 = move-exception;
        r2 = r6.f448d;	 Catch:{ IOException -> 0x0030 }
        if (r2 == 0) goto L_0x0018;
    L_0x0049:
        r2 = r6.f448d;	 Catch:{ IOException -> 0x0030 }
        r3 = "can't resolve host";
        r4 = 0;
        r2.m328b(r0, r3, r4);	 Catch:{ IOException -> 0x0030 }
        goto L_0x0018;
    L_0x0052:
        r0 = move-exception;
        r2 = r6.f448d;	 Catch:{ IOException -> 0x0030 }
        if (r2 == 0) goto L_0x0018;
    L_0x0057:
        r2 = r6.f448d;	 Catch:{ IOException -> 0x0030 }
        r3 = "socket time out";
        r4 = 0;
        r2.m328b(r0, r3, r4);	 Catch:{ IOException -> 0x0030 }
        goto L_0x0018;
    L_0x0060:
        r0 = move-exception;
        r2 = r6.f448d;	 Catch:{ IOException -> 0x0030 }
        if (r2 == 0) goto L_0x0018;
    L_0x0065:
        r2 = r6.f448d;	 Catch:{ IOException -> 0x0030 }
        r3 = "Android 2.x closed connection re-use bug";
        r4 = 0;
        r2.m328b(r0, r3, r4);	 Catch:{ IOException -> 0x0030 }
        goto L_0x0018;
    L_0x006e:
        r0 = move-exception;
        r2 = r6.f450f;	 Catch:{ IOException -> 0x0030 }
        r2 = r2 + 1;
        r6.f450f = r2;	 Catch:{ IOException -> 0x0030 }
        r4 = r6.f446b;	 Catch:{ IOException -> 0x0030 }
        r2 = r3.retryRequest(r0, r2, r4);	 Catch:{ IOException -> 0x0030 }
        goto L_0x0013;
    L_0x007c:
        r2 = move-exception;
        r0 = new java.io.IOException;	 Catch:{ IOException -> 0x0030 }
        r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0030 }
        r5 = "NPE in HttpClient";
        r4.<init>(r5);	 Catch:{ IOException -> 0x0030 }
        r2 = r2.getMessage();	 Catch:{ IOException -> 0x0030 }
        r2 = r4.append(r2);	 Catch:{ IOException -> 0x0030 }
        r2 = r2.toString();	 Catch:{ IOException -> 0x0030 }
        r0.<init>(r2);	 Catch:{ IOException -> 0x0030 }
        r2 = r6.f450f;	 Catch:{ IOException -> 0x0030 }
        r2 = r2 + 1;
        r6.f450f = r2;	 Catch:{ IOException -> 0x0030 }
        r4 = r6.f446b;	 Catch:{ IOException -> 0x0030 }
        r2 = r3.retryRequest(r0, r2, r4);	 Catch:{ IOException -> 0x0030 }
        goto L_0x0013;
    L_0x00a3:
        r2 = new java.net.ConnectException;	 Catch:{ IOException -> 0x0030 }
        r2.<init>();	 Catch:{ IOException -> 0x0030 }
        r2.initCause(r0);	 Catch:{ IOException -> 0x0030 }
        throw r2;	 Catch:{ IOException -> 0x0030 }
    L_0x00ac:
        r2 = r6.f448d;
        r2.m328b(r0, r1, r1);
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.paypal.android.sdk.cl.run():void");
    }
}
