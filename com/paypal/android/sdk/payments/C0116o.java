package com.paypal.android.sdk.payments;

import android.content.Intent;
import android.util.Log;

/* renamed from: com.paypal.android.sdk.payments.o */
abstract class C0116o {
    private Intent f751a;
    private PayPalConfiguration f752b;

    C0116o(Intent intent, PayPalConfiguration payPalConfiguration) {
        this.f751a = intent;
        this.f752b = payPalConfiguration;
    }

    abstract String m683a();

    protected final void m684a(boolean z, String str) {
        if (!z) {
            Log.e(m683a(), str + " is invalid.  Please see the docs.");
        }
    }

    final Intent m685b() {
        return this.f751a;
    }

    final PayPalConfiguration m686c() {
        return this.f752b;
    }

    protected final boolean m687d() {
        if (this.f752b.m421o()) {
            return true;
        }
        Log.e(m683a(), "Service extra invalid.");
        return false;
    }

    abstract boolean m688e();
}
