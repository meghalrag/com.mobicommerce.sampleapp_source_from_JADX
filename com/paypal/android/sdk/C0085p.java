package com.paypal.android.sdk;

import android.os.Build.VERSION;
import java.util.HashMap;

/* renamed from: com.paypal.android.sdk.p */
final class C0085p extends HashMap {
    C0085p() {
        put(Integer.valueOf(2), "ANDROIDCDMA_PHONE");
        put(Integer.valueOf(1), "ANDROIDGSM_PHONE");
        if (VERSION.SDK_INT >= 11) {
            put(Integer.valueOf(3), "ANDROIDGSM_UNDEFINED");
        }
        put(Integer.valueOf(0), "ANDROIDGSM_UNDEFINED");
    }
}
