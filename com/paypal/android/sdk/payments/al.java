package com.paypal.android.sdk.payments;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import java.util.Calendar;

final class al implements ServiceConnection {
    private /* synthetic */ PaymentActivity f708a;

    al(PaymentActivity paymentActivity) {
        this.f708a = paymentActivity;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        new StringBuilder().append(PaymentActivity.class.getSimpleName()).append(".onServiceConnected");
        if (this.f708a.isFinishing()) {
            new StringBuilder().append(PaymentActivity.class.getSimpleName()).append(".onServiceConnected exit - isFinishing");
            return;
        }
        this.f708a.f611d = ((ad) iBinder).f701a;
        if (this.f708a.f611d.m503c() == null) {
            Log.e(PaymentActivity.f608a, "Service state invalid.  Did you start the PayPalService?");
            this.f708a.setResult(2);
            this.f708a.finish();
            return;
        }
        ao aoVar = new ao(this.f708a.getIntent(), this.f708a.f611d.m503c());
        if (!aoVar.m687d()) {
            Log.e(PaymentActivity.f608a, "Service extras invalid.  Please see the docs.");
            this.f708a.setResult(2);
            this.f708a.finish();
        } else if (aoVar.m1038e()) {
            this.f708a.f611d.m510j();
            if (this.f708a.f611d.m507g()) {
                PaymentMethodActivity.m571a(this.f708a, 1, this.f709a.f1095a.f611d.m503c().m407a(), this.f709a.f1095a.f611d.m503c());
                return;
            }
            Calendar instance = Calendar.getInstance();
            instance.add(13, 1);
            this.f708a.f610c = instance.getTime();
            this.f708a.f611d.m494a(new am(this.f708a), false);
        } else {
            Log.e(PaymentActivity.f608a, "Extras invalid.  Please see the docs.");
            this.f708a.setResult(2);
            this.f708a.finish();
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.f708a.f611d = null;
        PaymentActivity.f608a;
    }
}
