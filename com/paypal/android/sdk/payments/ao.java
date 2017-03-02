package com.paypal.android.sdk.payments;

import android.content.Intent;

final class ao extends C0116o {
    ao(Intent intent, PayPalConfiguration payPalConfiguration) {
        super(intent, payPalConfiguration);
    }

    protected final String m1037a() {
        return PaymentActivity.class.getSimpleName();
    }

    final boolean m1038e() {
        ap apVar = new ap(m685b());
        boolean z = apVar.m641a() != null && apVar.m641a().isProcessable();
        m684a(z, "PaymentActivity.EXTRA_PAYMENT");
        return z;
    }
}
