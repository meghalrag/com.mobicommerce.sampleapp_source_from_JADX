package com.paypal.android.sdk.payments;

import android.app.Activity;
import java.util.TimerTask;

/* renamed from: com.paypal.android.sdk.payments.R */
final class C0099R extends TimerTask {
    private /* synthetic */ C0237Q f658a;

    C0099R(C0237Q c0237q) {
        this.f658a = c0237q;
    }

    public final void run() {
        FuturePaymentConsentActivity.m370a((Activity) this.f658a.f1085a, 1, this.f527a.f549d.m503c());
    }
}
