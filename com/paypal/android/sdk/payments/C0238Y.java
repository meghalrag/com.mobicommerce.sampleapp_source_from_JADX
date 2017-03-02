package com.paypal.android.sdk.payments;

import android.app.Activity;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/* renamed from: com.paypal.android.sdk.payments.Y */
final class C0238Y implements af {
    final /* synthetic */ PayPalProfileSharingActivity f1086a;

    C0238Y(PayPalProfileSharingActivity payPalProfileSharingActivity) {
        this.f1086a = payPalProfileSharingActivity;
    }

    public final void m1004a() {
        Date time = Calendar.getInstance().getTime();
        if (this.f1086a.f576b.compareTo(time) > 0) {
            long time2 = this.f1086a.f576b.getTime() - time.getTime();
            PayPalProfileSharingActivity.f575a;
            new StringBuilder("Delaying ").append(time2).append(" miliseconds so user doesn't see flicker.");
            this.f1086a.f577c = new Timer();
            this.f1086a.f577c.schedule(new C0106Z(this), time2);
            return;
        }
        ProfileSharingConsentActivity.m594a((Activity) this.f1086a, 1, this.f669a.f1086a.f578d.m503c());
    }

    public final void m1005a(ag agVar) {
        C0108b.m652a(this.f1086a, agVar, 1, 2, 3);
    }
}
