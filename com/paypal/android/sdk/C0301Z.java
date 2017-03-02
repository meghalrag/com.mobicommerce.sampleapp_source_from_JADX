package com.paypal.android.sdk;

/* renamed from: com.paypal.android.sdk.Z */
public final class C0301Z extends C0296V {
    private String f1241a;

    public C0301Z(C0065N c0065n, C0074e c0074e, C0078i c0078i, String str, String str2) {
        super(C0071b.DeleteCreditCardRequest, c0065n, c0074e, c0078i, str);
        this.f1241a = str2;
    }

    public final String m1220a() {
        return this.f1241a;
    }

    public final void m1221b() {
    }

    public final void m1222c() {
        m935b(m71A());
    }

    public final String m1223d() {
        return "mockDeleteCreditCardResponse";
    }
}
