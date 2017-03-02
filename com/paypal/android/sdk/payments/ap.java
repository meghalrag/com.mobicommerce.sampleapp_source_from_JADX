package com.paypal.android.sdk.payments;

import android.content.Intent;

final class ap {
    private Intent f710a;

    ap(Intent intent) {
        this.f710a = intent;
    }

    final PayPalPayment m641a() {
        return (PayPalPayment) this.f710a.getParcelableExtra(PaymentActivity.EXTRA_PAYMENT);
    }

    public final bj m642b() {
        return (bj) this.f710a.getParcelableExtra("com.paypal.android.sdk.payments.PaymentConfirmActivity.EXTRA_PAYMENT_INFO");
    }
}
