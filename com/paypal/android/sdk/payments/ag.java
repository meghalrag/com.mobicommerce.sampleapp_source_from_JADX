package com.paypal.android.sdk.payments;

final class ag {
    Integer f702a;
    String f703b;
    String f704c;

    ag(PayPalService payPalService, String str, Integer num, String str2) {
        this.f703b = str;
        this.f702a = num;
        this.f704c = str2;
    }

    final boolean m633a() {
        return this.f702a != null && this.f702a.equals(Integer.valueOf(401));
    }
}
