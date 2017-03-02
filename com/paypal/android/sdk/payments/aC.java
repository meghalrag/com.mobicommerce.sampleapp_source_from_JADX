package com.paypal.android.sdk.payments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class aC implements OnClickListener {
    private /* synthetic */ PaymentConfirmActivity f676a;

    aC(PaymentConfirmActivity paymentConfirmActivity) {
        this.f676a = paymentConfirmActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f676a.onBackPressed();
    }
}
