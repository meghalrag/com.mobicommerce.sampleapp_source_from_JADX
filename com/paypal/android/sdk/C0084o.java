package com.paypal.android.sdk;

import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.paypal.android.sdk.o */
public class C0084o {
    private static final HashMap f481a;
    private static Map f482b;

    static {
        C0084o.class.getSimpleName();
        f481a = new C0085p();
        Map hashMap = new HashMap();
        f482b = hashMap;
        hashMap.put("device_identifier", C0069T.m48b(C0072c.m291a().m294c().m337e()));
        f482b.put("device_type", "Android");
        f482b.put("device_name", C0069T.m48b(Build.DEVICE));
        f482b.put("device_model", C0069T.m48b(Build.MODEL));
        Map map = f482b;
        String str = "device_key_type";
        Object obj = (String) f481a.get(Integer.valueOf(C0072c.m291a().m294c().m333b()));
        if (TextUtils.isEmpty(obj)) {
            obj = "ANDROIDGSM_UNDEFINED";
        }
        map.put(str, obj);
        f482b.put("device_os", "Android");
        f482b.put("device_os_version", C0069T.m48b(VERSION.RELEASE));
        map = f482b;
        str = "is_device_simulator";
        boolean z = Build.PRODUCT.equals("sdk") || Build.PRODUCT.equals("google_sdk") || Build.FINGERPRINT.contains("generic");
        map.put(str, Boolean.toString(z));
    }

    public static JSONObject m364a() {
        JSONObject jSONObject = new JSONObject();
        try {
            for (String str : f482b.keySet()) {
                jSONObject.put(str, f482b.get(str));
            }
            return jSONObject;
        } catch (Throwable e) {
            Log.e("paypal.sdk", "Error encoding JSON", e);
            return null;
        }
    }

    public static String m365b() {
        return C0069T.m48b(C0072c.m291a().m294c().m337e());
    }
}
