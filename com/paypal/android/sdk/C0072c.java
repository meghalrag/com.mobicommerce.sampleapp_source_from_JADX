package com.paypal.android.sdk;

import android.content.Context;

/* renamed from: com.paypal.android.sdk.c */
public class C0072c {
    private static volatile C0072c f408a;
    private Context f409b;
    private C0073d f410c;

    private C0072c() {
    }

    public static C0072c m291a() {
        if (f408a == null) {
            synchronized (C0072c.class) {
                if (f408a == null) {
                    f408a = new C0072c();
                }
            }
        }
        return f408a;
    }

    public final void m292a(Context context, String str) {
        this.f409b = context;
        this.f410c = new C0073d(context, str);
    }

    public final Context m293b() {
        return this.f409b;
    }

    public final C0073d m294c() {
        return this.f410c;
    }
}
