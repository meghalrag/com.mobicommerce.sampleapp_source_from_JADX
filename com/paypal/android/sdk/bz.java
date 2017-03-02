package com.paypal.android.sdk;

import java.util.Locale;

public final class bz implements C0078i {
    private static volatile bz f1071a;

    private bz() {
    }

    public static bz m975d() {
        if (f1071a == null) {
            synchronized (bz.class) {
                if (f1071a == null) {
                    f1071a = new bz();
                }
            }
        }
        return f1071a;
    }

    public final C0080k m976a() {
        return m978b();
    }

    public final String m977a(String str) {
        return str;
    }

    public final C0080k m978b() {
        return new C0080k(Locale.getDefault().getCountry());
    }

    public final Locale m979c() {
        return Locale.getDefault();
    }
}
