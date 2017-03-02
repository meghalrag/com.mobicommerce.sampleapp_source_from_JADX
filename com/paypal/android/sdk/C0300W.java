package com.paypal.android.sdk;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.paypal.android.sdk.W */
public final class C0300W extends ac {
    public String f1238a;
    private List f1239b;
    private String f1240c;

    public C0300W(C0065N c0065n, C0074e c0074e, String str, C0078i c0078i, String str2, String str3, String str4, List list) {
        super(C0071b.ConsentRequest, c0065n, c0074e, c0078i, af.m934a(str, str2));
        this.f1238a = str3;
        this.f1240c = str4;
        this.f1239b = list;
    }

    public final String m1216a() {
        Map hashMap = new HashMap();
        hashMap.put("code", this.f1238a);
        hashMap.put("nonce", this.f1240c);
        hashMap.put("scope", C0069T.m35a(this.f1239b, " "));
        return C0069T.m36a(hashMap);
    }

    public final void m1217b() {
    }

    public final void m1218c() {
        m936c(m71A());
    }

    public final String m1219d() {
        return "{\"code\":\"EOTHbvqh0vwM2ldM2QIXbjVw0hZNuZEJLqdWmfTBLLSvGfqgyy9GKvjGybIxyGMd7gHXCXVtymqFQHS-J-4-Ir6u2LUVVdyLKonwTtdFw9qhBaMb4NZuZHKS0bGxdZlRAB3_Fk8HX2r3z8j03xScx4M\",\"scope\":\"https://uri.paypal.com/services/payments/futurepayments\"}";
    }
}
