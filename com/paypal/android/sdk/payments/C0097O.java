package com.paypal.android.sdk.payments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* renamed from: com.paypal.android.sdk.payments.O */
final class C0097O implements OnClickListener {
    private /* synthetic */ PayPalFuturePaymentActivity f526a;

    C0097O(PayPalFuturePaymentActivity payPalFuturePaymentActivity) {
        this.f526a = payPalFuturePaymentActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f526a.f549d.m494a(new C0237Q(this.f526a), true);
    }
}
