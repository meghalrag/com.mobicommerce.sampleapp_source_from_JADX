package com.paypal.android.sdk.payments;

import android.os.Build;
import android.os.Build.VERSION;
import com.paypal.android.sdk.C0074e;

/* renamed from: com.paypal.android.sdk.payments.m */
final class C0241m implements C0074e {
    C0241m() {
    }

    public final String m1051a() {
        return Version.PRODUCT_VERSION;
    }

    public final String m1052b() {
        return Version.PRODUCT_VERSION;
    }

    public final String m1053c() {
        return "PayPalSDK/PayPal-Android-SDK 2.5.2 (Android " + VERSION.RELEASE + "; " + Build.MANUFACTURER + " " + Build.MODEL + "; " + ")";
    }

    public final String m1054d() {
        return BuildConfig.LATEST_SHA1;
    }
}
