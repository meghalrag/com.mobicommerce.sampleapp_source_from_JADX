package com.paypal.android.sdk;

/* renamed from: com.paypal.android.sdk.V */
public abstract class C0296V extends af {
    public C0296V(C0071b c0071b, C0065N c0065n, C0074e c0074e, C0078i c0078i, String str) {
        super(c0071b, c0065n, c0074e, c0078i, "Bearer " + str);
        m83b("Content-Type", "application/json");
        m83b("Accept", "application/json");
    }
}
