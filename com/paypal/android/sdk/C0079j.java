package com.paypal.android.sdk;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.v4.media.TransportMediator;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.paypal.android.sdk.j */
public class C0079j {
    private static Map f473a;

    static {
        C0079j.class.getSimpleName();
        Map hashMap = new HashMap();
        f473a = hashMap;
        hashMap.put("app_version", C0079j.m350a(C0072c.m291a().m293b()));
        f473a.put("app_category", "1");
        C0073d c = C0072c.m291a().m294c();
        if (c.m333b() == 1) {
            f473a.put("client_platform", "AndroidGSM");
        } else if (c.m333b() == 2) {
            f473a.put("client_platform", "AndroidCDMA");
        } else {
            f473a.put("client_platform", "AndroidOther");
        }
        f473a.put("device_app_id", c.m335c());
    }

    private static String m350a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), TransportMediator.FLAG_KEY_MEDIA_NEXT).versionName;
        } catch (NameNotFoundException e) {
            return "unknown versionName";
        }
    }

    public static JSONObject m351a() {
        JSONObject jSONObject = new JSONObject();
        try {
            for (String str : f473a.keySet()) {
                jSONObject.put(str, f473a.get(str));
            }
            return jSONObject;
        } catch (Throwable e) {
            Log.e("paypal.sdk", "Error encoding JSON", e);
            return null;
        }
    }

    public static String m352b() {
        return (String) f473a.get("device_app_id");
    }

    public static String m353c() {
        return (String) f473a.get("app_version");
    }
}
