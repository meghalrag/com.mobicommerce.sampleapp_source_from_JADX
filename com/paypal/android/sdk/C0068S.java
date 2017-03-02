package com.paypal.android.sdk;

import android.os.Build;
import android.os.Build.VERSION;

/* renamed from: com.paypal.android.sdk.S */
public class C0068S {
    private static final String f16a;
    private static String f17b;

    static {
        f16a = C0068S.class.getSimpleName();
        f17b = null;
    }

    public static String m32a(C0078i c0078i, C0074e c0074e) {
        if (f17b == null) {
            f17b = "Mozilla/5.0 (Linux; U; Android " + VERSION.RELEASE + "; " + c0078i.m349c().toString().replace("_", "-") + "; " + Build.MODEL + " " + Build.DISPLAY + ") mpl" + "/" + c0074e.m339b();
            String str = f16a;
            new StringBuilder("UserAgent: ").append(f17b);
        }
        return f17b;
    }
}
