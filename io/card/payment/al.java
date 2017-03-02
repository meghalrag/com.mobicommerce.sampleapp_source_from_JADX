package io.card.payment;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

final class al implements Runnable {
    private final AbstractHttpClient f904a;
    private final HttpContext f905b;
    private final HttpUriRequest f906c;
    private final C0162f f907d;
    private int f908e;

    public al(AbstractHttpClient abstractHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, C0162f c0162f) {
        this.f904a = abstractHttpClient;
        this.f905b = httpContext;
        this.f906c = httpUriRequest;
        this.f907d = c0162f;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r7 = this;
        r1 = 0;
        r0 = r7.f907d;	 Catch:{ IOException -> 0x009f }
        if (r0 == 0) goto L_0x000a;
    L_0x0005:
        r0 = r7.f907d;	 Catch:{ IOException -> 0x009f }
        r0.m809a();	 Catch:{ IOException -> 0x009f }
    L_0x000a:
        r0 = 1;
        r2 = r7.f904a;	 Catch:{ IOException -> 0x009f }
        r3 = r2.getHttpRequestRetryHandler();	 Catch:{ IOException -> 0x009f }
        r2 = r0;
        r0 = r1;
    L_0x0013:
        if (r2 == 0) goto L_0x0096;
    L_0x0015:
        r0 = java.lang.Thread.currentThread();	 Catch:{ IOException -> 0x0046, NullPointerException -> 0x006c }
        r0 = r0.isInterrupted();	 Catch:{ IOException -> 0x0046, NullPointerException -> 0x006c }
        if (r0 != 0) goto L_0x003c;
    L_0x001f:
        r0 = r7.f904a;	 Catch:{ IOException -> 0x0046, NullPointerException -> 0x006c }
        r2 = r7.f906c;	 Catch:{ IOException -> 0x0046, NullPointerException -> 0x006c }
        r4 = r7.f905b;	 Catch:{ IOException -> 0x0046, NullPointerException -> 0x006c }
        r0 = r0.execute(r2, r4);	 Catch:{ IOException -> 0x0046, NullPointerException -> 0x006c }
        r2 = java.lang.Thread.currentThread();	 Catch:{ IOException -> 0x0046, NullPointerException -> 0x006c }
        r2 = r2.isInterrupted();	 Catch:{ IOException -> 0x0046, NullPointerException -> 0x006c }
        if (r2 != 0) goto L_0x003c;
    L_0x0033:
        r2 = r7.f907d;	 Catch:{ IOException -> 0x0046, NullPointerException -> 0x006c }
        if (r2 == 0) goto L_0x003c;
    L_0x0037:
        r2 = r7.f907d;	 Catch:{ IOException -> 0x0046, NullPointerException -> 0x006c }
        r2.m814a(r0);	 Catch:{ IOException -> 0x0046, NullPointerException -> 0x006c }
    L_0x003c:
        r0 = r7.f907d;	 Catch:{ IOException -> 0x009f }
        if (r0 == 0) goto L_0x0045;
    L_0x0040:
        r0 = r7.f907d;	 Catch:{ IOException -> 0x009f }
        r0.m815b();	 Catch:{ IOException -> 0x009f }
    L_0x0045:
        return;
    L_0x0046:
        r0 = move-exception;
        r2 = "AsyncHttpRequest";
        r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x009f }
        r5 = "problem making request... retrying: ";
        r4.<init>(r5);	 Catch:{ IOException -> 0x009f }
        r5 = r0.getMessage();	 Catch:{ IOException -> 0x009f }
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x009f }
        r4 = r4.toString();	 Catch:{ IOException -> 0x009f }
        android.util.Log.w(r2, r4);	 Catch:{ IOException -> 0x009f }
        r2 = r7.f908e;	 Catch:{ IOException -> 0x009f }
        r2 = r2 + 1;
        r7.f908e = r2;	 Catch:{ IOException -> 0x009f }
        r4 = r7.f905b;	 Catch:{ IOException -> 0x009f }
        r2 = r3.retryRequest(r0, r2, r4);	 Catch:{ IOException -> 0x009f }
        goto L_0x0013;
    L_0x006c:
        r0 = move-exception;
        r2 = new java.io.IOException;	 Catch:{ IOException -> 0x009f }
        r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x009f }
        r5 = "NPE in HttpClient ";
        r4.<init>(r5);	 Catch:{ IOException -> 0x009f }
        r0 = r0.getMessage();	 Catch:{ IOException -> 0x009f }
        r0 = r4.append(r0);	 Catch:{ IOException -> 0x009f }
        r0 = r0.toString();	 Catch:{ IOException -> 0x009f }
        r2.<init>(r0);	 Catch:{ IOException -> 0x009f }
        r0 = r7.f908e;	 Catch:{ IOException -> 0x009f }
        r0 = r0 + 1;
        r7.f908e = r0;	 Catch:{ IOException -> 0x009f }
        r4 = r7.f905b;	 Catch:{ IOException -> 0x009f }
        r0 = r3.retryRequest(r2, r0, r4);	 Catch:{ IOException -> 0x009f }
        r6 = r2;
        r2 = r0;
        r0 = r6;
        goto L_0x0013;
    L_0x0096:
        r2 = new java.net.ConnectException;	 Catch:{ IOException -> 0x009f }
        r2.<init>();	 Catch:{ IOException -> 0x009f }
        r2.initCause(r0);	 Catch:{ IOException -> 0x009f }
        throw r2;	 Catch:{ IOException -> 0x009f }
    L_0x009f:
        r0 = move-exception;
        r2 = r7.f907d;
        if (r2 == 0) goto L_0x0045;
    L_0x00a4:
        r2 = r7.f907d;
        r2.m815b();
        r2 = r7.f907d;
        r2.m813a(r0, r1);
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.card.payment.al.run():void");
    }
}
