package com.paypal.android.sdk.payments;

import android.view.View;
import android.view.View.OnClickListener;

final class aK implements OnClickListener {
    private /* synthetic */ PaymentMethodActivity f687a;

    aK(PaymentMethodActivity paymentMethodActivity) {
        this.f687a = paymentMethodActivity;
    }

    public final void onClick(View view) {
        this.f687a.showDialog(2);
    }
}
