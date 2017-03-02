package com.paypal.android.sdk;

import android.util.Log;

/* renamed from: com.paypal.android.sdk.A */
public abstract class C0229A implements C0063K {
    protected static void m840a(ap apVar) {
        try {
            apVar.m82b();
        } catch (Exception e) {
            Log.e("paypal.sdk", "Exception parsing server response", e);
            apVar.m79a(new ar(C0076g.PARSE_RESPONSE_ERROR, e));
        }
    }
}
