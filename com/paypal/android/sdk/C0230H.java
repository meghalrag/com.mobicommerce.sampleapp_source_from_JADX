package com.paypal.android.sdk;

import android.util.Log;
import java.io.IOException;
import java.util.Locale;
import org.apache.http.NoHttpResponseException;

/* renamed from: com.paypal.android.sdk.H */
final class C0230H extends cm {
    private final ap f954a;
    private /* synthetic */ C0293F f955b;

    private C0230H(C0293F c0293f, ap apVar) {
        this.f955b = c0293f;
        this.f954a = apVar;
    }

    private String m841a(String str) {
        return String.format(Locale.US, this.f954a.m72B() + " PayPal Debug-ID: %s [%s, %s]", new Object[]{str, this.f955b.f1196b, this.f955b.f1200f.m338a() + ";release"});
    }

    public final void m842a(String str, String str2) {
        this.f954a.m87e(str);
        this.f954a.m90f(str2);
        C0293F.f1195a;
        new StringBuilder().append(this.f954a.m72B()).append(" success. response: ").append(this.f954a.m93w());
        if (C0069T.m52d(str2)) {
            Log.w("paypal.sdk", m841a(str2));
        }
        if (this.f954a.m75E()) {
            C0293F c0293f = this.f955b;
            C0229A.m840a(this.f954a);
        }
        this.f955b.f1197c.m30a(this.f954a);
    }

    public final void m843a(Throwable th, String str, String str2) {
        this.f954a.m87e(str);
        if (C0069T.m52d(str2)) {
            Log.w("paypal.sdk", m841a(str2));
        }
        if (th instanceof NoHttpResponseException) {
            this.f955b.f1198d.m308a().getConnectionManager().closeExpiredConnections();
            this.f955b.m1165b(this.f954a);
            return;
        }
        C0293F.m1156a(this.f955b, this.f954a, (IOException) th);
    }
}
