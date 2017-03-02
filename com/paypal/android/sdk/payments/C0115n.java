package com.paypal.android.sdk.payments;

import android.content.Context;
import com.paypal.android.sdk.C0069T;
import com.paypal.android.sdk.aZ;

/* renamed from: com.paypal.android.sdk.payments.n */
class C0115n {
    private static final String f750a;

    static {
        f750a = C0115n.class.getSimpleName();
    }

    C0115n() {
    }

    static boolean m681a(Context context, PayPalService payPalService) {
        boolean z = false;
        String d = payPalService.m504d();
        boolean z2 = d.startsWith(PayPalConfiguration.ENVIRONMENT_SANDBOX) || d.equals(PayPalConfiguration.ENVIRONMENT_NO_NETWORK);
        if (z2) {
            d = f750a;
            new StringBuilder("Is mock or sandbox:").append(payPalService.m504d());
        } else if (payPalService.m521u()) {
            z = aZ.m58a(context, payPalService.m522v());
        } else {
            d = f750a;
        }
        d = f750a;
        new StringBuilder("returning isValid:").append(z);
        return z;
    }

    public static boolean m682a(String str, String str2) {
        return (C0069T.m51c(str) || C0069T.m51c(str2) || !str.contains("payments/.*")) ? false : str2.equals("ID_TOKEN") || str2.equals("CREDENTIALS");
    }
}
