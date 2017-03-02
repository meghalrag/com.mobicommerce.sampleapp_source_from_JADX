package com.paypal.android.sdk.payments;

import android.view.View;
import android.view.View.OnClickListener;

final class aq implements OnClickListener {
    private /* synthetic */ PaymentConfirmActivity f711a;

    aq(PaymentConfirmActivity paymentConfirmActivity) {
        this.f711a = paymentConfirmActivity;
    }

    public final void onClick(View view) {
        view.setEnabled(false);
        this.f711a.m557g();
    }
}
