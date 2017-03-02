package com.paypal.android.sdk.payments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* renamed from: com.paypal.android.sdk.payments.x */
final class C0120x implements OnClickListener {
    private /* synthetic */ FuturePaymentConsentActivity f756a;

    C0120x(FuturePaymentConsentActivity futurePaymentConsentActivity) {
        this.f756a = futurePaymentConsentActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f756a.m368a();
    }
}
