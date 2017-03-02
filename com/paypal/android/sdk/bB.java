package com.paypal.android.sdk;

import com.paypal.android.sdk.payments.PayPalService;
import java.util.HashMap;
import java.util.Map;

public final class bB {
    private static final String f102a;
    private static Map f103b;

    static {
        f102a = PayPalService.class.getSimpleName();
        f103b = new HashMap();
    }

    public static bu m111a(String str) {
        bu buVar = (bu) f103b.get(str);
        String str2 = f102a;
        new StringBuilder("getLoginAccessToken(").append(str).append(") returns String:").append(buVar);
        return buVar;
    }

    public static void m112a(bu buVar, String str) {
        f103b.put(str, buVar);
        String str2 = f102a;
        new StringBuilder("setLoginAccessToken(").append(buVar).append(",").append(str).append(")");
    }

    public static void m113b(String str) {
        f103b.remove(str);
    }
}
