package com.paypal.android.sdk.payments;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import java.util.Calendar;

/* renamed from: com.paypal.android.sdk.payments.P */
final class C0098P implements ServiceConnection {
    private /* synthetic */ PayPalFuturePaymentActivity f527a;

    C0098P(PayPalFuturePaymentActivity payPalFuturePaymentActivity) {
        this.f527a = payPalFuturePaymentActivity;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        new StringBuilder().append(PayPalFuturePaymentActivity.class.getSimpleName()).append(".onServiceConnected");
        if (this.f527a.isFinishing()) {
            new StringBuilder().append(PayPalFuturePaymentActivity.class.getSimpleName()).append(".onServiceConnected exit - isFinishing");
            return;
        }
        this.f527a.f549d = ((ad) iBinder).f701a;
        if (this.f527a.f549d.m503c() == null) {
            Log.e(PayPalFuturePaymentActivity.f546a, "Service state invalid.  Did you start the PayPalService?");
            this.f527a.setResult(2);
            this.f527a.finish();
            return;
        }
        aa aaVar = new aa(this.f527a.getIntent(), this.f527a.f549d.m503c(), false);
        if (!aaVar.m687d()) {
            Log.e(PayPalFuturePaymentActivity.f546a, "Service extras invalid.  Please see the docs.");
            this.f527a.setResult(2);
            this.f527a.finish();
        } else if (!aaVar.m1013e()) {
            Log.e(PayPalFuturePaymentActivity.f546a, "Extras invalid.  Please see the docs.");
            this.f527a.setResult(2);
            this.f527a.finish();
        } else if (this.f527a.f549d.m507g()) {
            FuturePaymentConsentActivity.m370a((Activity) this.f527a, 1, this.f527a.f549d.m503c());
        } else {
            Calendar instance = Calendar.getInstance();
            instance.add(13, 1);
            this.f527a.f547b = instance.getTime();
            this.f527a.f549d.m494a(new C0237Q(this.f527a), false);
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.f527a.f549d = null;
        PayPalFuturePaymentActivity.f546a;
    }
}
