package com.paypal.android.sdk.payments;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* renamed from: com.paypal.android.sdk.payments.F */
final class C0090F implements ServiceConnection {
    final /* synthetic */ LoginActivity f493a;

    C0090F(LoginActivity loginActivity) {
        this.f493a = loginActivity;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        new StringBuilder().append(LoginActivity.class.getSimpleName()).append(".onServiceConnected");
        this.f493a.f523n = ((ad) iBinder).f701a;
        LoginActivity.m390b(this.f493a);
        if (this.f493a.f523n.m501a(new C0235G(this))) {
            this.f493a.m404a();
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.f493a.f523n = null;
    }
}
