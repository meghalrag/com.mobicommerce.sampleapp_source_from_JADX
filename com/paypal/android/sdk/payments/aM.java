package com.paypal.android.sdk.payments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class aM implements OnClickListener {
    private /* synthetic */ PaymentMethodActivity f689a;

    aM(PaymentMethodActivity paymentMethodActivity) {
        this.f689a = paymentMethodActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f689a.f638j.m506f();
        this.f689a.m580d();
    }
}
