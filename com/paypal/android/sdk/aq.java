package com.paypal.android.sdk;

public abstract class aq {
    private String f69a;
    private String f70b;

    private aq(String str) {
    }

    protected aq(String str, String str2) {
        this(str);
        this.f69a = str2;
        this.f70b = null;
    }

    protected aq(String str, String str2, String str3, String str4) {
        this(str);
        this.f69a = str2;
        this.f70b = str3;
    }

    public final String m97a() {
        return this.f70b;
    }

    public final String m98b() {
        return this.f69a;
    }
}
