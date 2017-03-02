package com.paypal.android.sdk;

import org.apache.http.NoHttpResponseException;

/* renamed from: com.paypal.android.sdk.I */
final class C0231I extends cm {
    private final ap f956a;
    private /* synthetic */ C0293F f957b;

    private C0231I(C0293F c0293f, ap apVar) {
        this.f957b = c0293f;
        this.f956a = apVar;
    }

    public final void m844a(String str, String str2) {
        this.f956a.m87e(str);
        C0293F.f1195a;
        new StringBuilder().append(this.f956a.m72B()).append(" success");
    }

    public final void m845a(Throwable th, String str) {
        this.f956a.m87e(str);
        if (th instanceof NoHttpResponseException) {
            this.f957b.f1199e.m308a().getConnectionManager().closeExpiredConnections();
            this.f957b.m1165b(this.f956a);
            return;
        }
        C0293F.f1195a;
        new StringBuilder().append(this.f956a.m72B()).append(" failure");
    }
}
