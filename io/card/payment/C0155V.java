package io.card.payment;

import android.content.Context;
import android.hardware.Camera.Size;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.util.Log;
import java.util.HashMap;
import java.util.List;

/* renamed from: io.card.payment.V */
final class C0155V {
    private static String f855a;
    private static String f856b;
    private static String f857c;

    static {
        f855a = null;
        f856b = null;
    }

    static HashMap m759a() {
        HashMap hashMap = new HashMap();
        hashMap.put("android_model", Build.MODEL);
        hashMap.put("android_build_id", Build.ID);
        hashMap.put("android_board", Build.BOARD);
        hashMap.put("android_bootloader", Build.BOOTLOADER);
        hashMap.put("android_brand", Build.BRAND);
        hashMap.put("android_cpu_abi", Build.CPU_ABI);
        hashMap.put("android_cpu_abi2", Build.CPU_ABI2);
        hashMap.put("android_device", Build.DEVICE);
        hashMap.put("android_display", Build.DISPLAY);
        hashMap.put("android_hardware", Build.HARDWARE);
        hashMap.put("android_manufacturer", Build.MANUFACTURER);
        hashMap.put("android_product", Build.PRODUCT);
        hashMap.put("android_tags", Build.TAGS);
        hashMap.put("android_time", String.valueOf(Build.TIME));
        hashMap.put("android_user", Build.USER);
        hashMap.put("android_sdk", String.valueOf(VERSION.SDK_INT));
        hashMap.put("android_version", VERSION.RELEASE);
        if (f856b != null) {
            hashMap.put("supported_capture_resolutions", f856b);
        }
        return hashMap;
    }

    static HashMap m760a(Context context) {
        HashMap hashMap = new HashMap();
        hashMap.put("uid", C0155V.m763b(context));
        hashMap.put("manufacturer", Build.MANUFACTURER);
        hashMap.put("model", Build.MODEL + ',' + Build.PRODUCT);
        hashMap.put("os", "android");
        hashMap.put("os_version", VERSION.RELEASE);
        hashMap.put("library_version", "sdk-3.1.5-10-gba7f52e");
        return hashMap;
    }

    static void m761a(List list) {
        if (list != null) {
            f856b = "[";
            for (Size size : list) {
                f856b += "[" + size.width + ", " + size.height + "], ";
            }
            f856b += "]";
        }
        new StringBuilder("- supported preview sizes: ").append(f856b);
    }

    public static String m762b() {
        if (f857c == null) {
            int i = VERSION.SDK_INT;
            String str = VERSION.INCREMENTAL;
            String str2 = "unknown[" + i + "]";
            if (i == 10000) {
                str2 = "Current Development: " + VERSION.CODENAME;
            } else if (i >= 14) {
                str2 = "Ice Cream Sandwich";
            } else if (i >= 13) {
                str2 = "Honeycomb MR2";
            } else if (i >= 12) {
                str2 = "Honeycomb MR1";
            } else if (i >= 11) {
                str2 = "Honeycomb";
            } else if (i >= 10) {
                str2 = "Gingerbread MR1";
            } else if (i >= 9) {
                str2 = "Gingerbread";
            } else if (i >= 8) {
                str2 = "Froyo";
            } else if (i >= 7) {
                str2 = "Eclair MR1";
            } else if (i >= 6) {
                str2 = "Eclair 0.1";
            } else if (i >= 5) {
                str2 = "Eclair";
            } else if (i >= 4) {
                str2 = "Donut";
            } else if (i >= 3) {
                str2 = "Cupcake";
            }
            f857c = VERSION.RELEASE + " (" + str2 + ")/" + str;
        }
        return f857c;
    }

    private static String m763b(Context context) {
        if (f855a != null) {
            return f855a;
        }
        String string = Secure.getString(context.getContentResolver(), "android_id");
        String str = string.contentEquals("9774d56d682e549c") ? "n/a" : string;
        String str2 = "n/a";
        if (VERSION.SDK_INT >= 9) {
            try {
                string = (String) Build.class.getField("SERIAL").get(null);
            } catch (Throwable e) {
                Log.w("DeviceInfo", "error getting serial number", e);
            }
            f855a = str + ' ' + string;
            new StringBuilder("device UUID: ").append(f855a);
            return f855a;
        }
        string = str2;
        f855a = str + ' ' + string;
        new StringBuilder("device UUID: ").append(f855a);
        return f855a;
    }
}
