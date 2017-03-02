package com.paypal.android.sdk.payments;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* renamed from: com.paypal.android.sdk.payments.v */
final class C0119v implements ServiceConnection {
    final /* synthetic */ FuturePaymentConsentActivity f755a;

    C0119v(FuturePaymentConsentActivity futurePaymentConsentActivity) {
        this.f755a = futurePaymentConsentActivity;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        new StringBuilder().append(FuturePaymentConsentActivity.class.getSimpleName()).append(".onServiceConnected");
        this.f755a.f500g = ((ad) iBinder).f701a;
        if (this.f755a.f500g.m501a(new C0246w(this))) {
            FuturePaymentConsentActivity.m382e(this.f755a);
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.f755a.f500g = null;
    }
}
