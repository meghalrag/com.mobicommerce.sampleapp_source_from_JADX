package com.paypal.android.sdk;

import java.util.Map;

/* renamed from: com.paypal.android.sdk.h */
public final class C0077h {
    private String f471a;
    private Map f472b;

    public final String m342a() {
        return this.f471a;
    }

    public final void m343a(String str) {
        this.f471a = str;
    }

    public final void m344a(Map map) {
        this.f472b = map;
    }

    public final Map m345b() {
        return this.f472b;
    }

    public final String toString() {
        return getClass().getSimpleName() + "(" + this.f471a + ",endpoints=" + this.f472b + ")";
    }
}
