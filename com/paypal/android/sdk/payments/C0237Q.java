package com.paypal.android.sdk.payments;

import android.app.Activity;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/* renamed from: com.paypal.android.sdk.payments.Q */
final class C0237Q implements af {
    final /* synthetic */ PayPalFuturePaymentActivity f1085a;

    C0237Q(PayPalFuturePaymentActivity payPalFuturePaymentActivity) {
        this.f1085a = payPalFuturePaymentActivity;
    }

    public final void m1002a() {
        Date time = Calendar.getInstance().getTime();
        if (this.f1085a.f547b.compareTo(time) > 0) {
            long time2 = this.f1085a.f547b.getTime() - time.getTime();
            PayPalFuturePaymentActivity.f546a;
            new StringBuilder("Delaying ").append(time2).append(" milliseconds so user doesn't see flicker.");
            this.f1085a.f548c = new Timer();
            this.f1085a.f548c.schedule(new C0099R(this), time2);
            return;
        }
        FuturePaymentConsentActivity.m370a((Activity) this.f1085a, 1, this.f527a.f549d.m503c());
    }

    public final void m1003a(ag agVar) {
        C0108b.m652a(this.f1085a, agVar, 1, 2, 3);
    }
}
