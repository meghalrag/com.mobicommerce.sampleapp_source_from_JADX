package com.paypal.android.sdk;

import com.paypal.android.sdk.payments.BuildConfig;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/* renamed from: com.paypal.android.sdk.u */
public class C0127u {
    private static List f769a;
    private static Pattern f770b;
    private static Pattern f771c;
    private static Pattern f772d;
    private static Pattern f773e;
    private static Pattern f774f;
    private static /* synthetic */ boolean f775g;

    static {
        f775g = !C0127u.class.desiredAssertionStatus();
        f769a = Arrays.asList(new String[]{"AU", "BR", "CA", "ES", "FR", "GB", "IT", "MY", "SG", "US"});
        f770b = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,} *$");
        f771c = Pattern.compile("^[0-9]{4,8}$");
        f772d = Pattern.compile("^\\+?[0-9]{7,14}$");
        f773e = Pattern.compile("[ .\\-\\(\\)]*");
        f774f = Pattern.compile("^\\+?0+$");
    }

    public static boolean m696a(String str) {
        if (f775g || str != null) {
            return f770b.matcher(str).matches();
        }
        throw new AssertionError();
    }

    public static boolean m697b(String str) {
        if (f775g || str != null) {
            return f771c.matcher(str).matches();
        }
        throw new AssertionError();
    }

    public static boolean m698c(String str) {
        return str.length() >= 8;
    }

    public static boolean m699d(String str) {
        if (f775g || str != null) {
            CharSequence replaceAll = f773e.matcher(str).replaceAll(BuildConfig.VERSION_NAME);
            return f772d.matcher(replaceAll).matches() && !f774f.matcher(replaceAll).matches();
        } else {
            throw new AssertionError();
        }
    }

    public static String m700e(String str) {
        return f773e.matcher(str).replaceAll(BuildConfig.VERSION_NAME);
    }

    public static boolean m701f(String str) {
        return C0069T.m51c(str) ? false : f769a.contains(str.toUpperCase());
    }
}
