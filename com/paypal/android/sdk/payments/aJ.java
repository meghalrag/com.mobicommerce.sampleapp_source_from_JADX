package com.paypal.android.sdk.payments;

import android.view.View;
import android.view.View.OnClickListener;

final class aJ implements OnClickListener {
    private /* synthetic */ PaymentMethodActivity f686a;

    aJ(PaymentMethodActivity paymentMethodActivity) {
        this.f686a = paymentMethodActivity;
    }

    public final void onClick(View view) {
        PaymentMethodActivity.m579c(this.f686a);
    }
}
