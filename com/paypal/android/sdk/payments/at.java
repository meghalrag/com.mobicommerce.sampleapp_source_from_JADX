package com.paypal.android.sdk.payments;

import android.view.View;
import android.view.View.OnClickListener;

final class at implements OnClickListener {
    private /* synthetic */ PaymentConfirmActivity f713a;

    at(PaymentConfirmActivity paymentConfirmActivity) {
        this.f713a = paymentConfirmActivity;
    }

    public final void onClick(View view) {
        this.f713a.onBackPressed();
    }
}
