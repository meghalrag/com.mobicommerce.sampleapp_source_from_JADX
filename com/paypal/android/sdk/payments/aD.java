package com.paypal.android.sdk.payments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class aD implements OnClickListener {
    private /* synthetic */ PaymentConfirmActivity f677a;

    aD(PaymentConfirmActivity paymentConfirmActivity) {
        this.f677a = paymentConfirmActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f677a.onBackPressed();
    }
}
