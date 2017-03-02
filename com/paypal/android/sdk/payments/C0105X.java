package com.paypal.android.sdk.payments;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import java.util.Calendar;

/* renamed from: com.paypal.android.sdk.payments.X */
final class C0105X implements ServiceConnection {
    private /* synthetic */ PayPalProfileSharingActivity f668a;

    C0105X(PayPalProfileSharingActivity payPalProfileSharingActivity) {
        this.f668a = payPalProfileSharingActivity;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        new StringBuilder().append(PayPalProfileSharingActivity.class.getSimpleName()).append(".onServiceConnected");
        if (this.f668a.isFinishing()) {
            new StringBuilder().append(PayPalProfileSharingActivity.class.getSimpleName()).append(".onServiceConnected exit - isFinishing");
            return;
        }
        this.f668a.f578d = ((ad) iBinder).f701a;
        if (this.f668a.f578d.m503c() == null) {
            Log.e(PayPalProfileSharingActivity.f575a, "Service state invalid.  Did you start the PayPalService?");
            this.f668a.setResult(2);
            this.f668a.finish();
            return;
        }
        aa aaVar = new aa(this.f668a.getIntent(), this.f668a.f578d.m503c(), true);
        if (!aaVar.m687d()) {
            Log.e(PayPalProfileSharingActivity.f575a, "Service extras invalid.  Please see the docs.");
            this.f668a.setResult(2);
            this.f668a.finish();
        } else if (!aaVar.m1013e()) {
            Log.e(PayPalProfileSharingActivity.f575a, "Extras invalid.  Please see the docs.");
            this.f668a.setResult(2);
            this.f668a.finish();
        } else if (this.f668a.f578d.m507g()) {
            ProfileSharingConsentActivity.m594a((Activity) this.f668a, 1, this.f669a.f1086a.f578d.m503c());
        } else {
            Calendar instance = Calendar.getInstance();
            instance.add(13, 1);
            this.f668a.f576b = instance.getTime();
            this.f668a.f578d.m494a(new C0238Y(this.f668a), false);
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.f668a.f578d = null;
        PayPalProfileSharingActivity.f575a;
    }
}
