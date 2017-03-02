package com.paypal.android.sdk.payments;

import com.paypal.android.sdk.C0069T;
import com.paypal.android.sdk.bL;
import com.paypal.android.sdk.bM;
import com.paypal.android.sdk.bz;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* renamed from: com.paypal.android.sdk.payments.h */
abstract class C0239h implements C0114l {
    private aj f1099a;

    protected C0239h(aj ajVar) {
        this.f1099a = ajVar;
    }

    private String m1044a(bM bMVar, boolean z) {
        String str = bL.f117b + ":" + m1049b() + ":" + bMVar.m128a();
        return z ? str + "|error" : str;
    }

    protected final aj m1045a() {
        return this.f1099a;
    }

    public final void m1046a(bM bMVar, boolean z, String str, String str2, String str3) {
        Object obj;
        String str4 = Version.PRODUCT_VERSION;
        bz.m975d();
        String locale = Locale.getDefault().toString();
        Map hashMap = new HashMap();
        boolean z2 = !C0069T.m46a((CharSequence) str);
        hashMap.put("gn", m1044a(bMVar, z2));
        hashMap.put("v31", m1044a(bMVar, z2));
        String str5 = "c25";
        String str6 = m1044a(bMVar, z2) + ":" + bMVar.m129a(this.f1099a.m638d(), z);
        if (z2) {
            obj = str6 + "|error";
        } else {
            String str7 = str6;
        }
        hashMap.put(str5, obj);
        hashMap.put("v25", "D=c25");
        hashMap.put("c37", bL.f116a + "::");
        hashMap.put("c50", locale);
        hashMap.put("c35", "out");
        m1048a(hashMap, bMVar, str2, str3);
        if (str != null) {
            hashMap.put("c29", str);
        }
        m1047a(str4, hashMap);
    }

    abstract void m1047a(String str, Map map);

    protected void m1048a(Map map, bM bMVar, String str, String str2) {
    }

    protected abstract String m1049b();
}
