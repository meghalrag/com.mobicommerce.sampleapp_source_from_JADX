package com.paypal.android.sdk.payments;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.paypal.android.sdk.payments.r */
final class C0118r implements OnClickListener {
    private /* synthetic */ FuturePaymentConsentActivity f754a;

    C0118r(FuturePaymentConsentActivity futurePaymentConsentActivity) {
        this.f754a = futurePaymentConsentActivity;
    }

    public final void onClick(View view) {
        view.setEnabled(false);
        FuturePaymentConsentActivity.m381d(this.f754a);
    }
}
