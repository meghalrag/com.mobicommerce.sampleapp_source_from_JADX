package com.paypal.android.sdk.payments;

import android.content.Context;
import android.util.Log;
import com.paypal.android.sdk.C0070a;
import com.paypal.android.sdk.C0072c;
import com.paypal.android.sdk.C0079j;
import com.paypal.android.sdk.be;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

final class bg {
    private static final String f730a;
    private static ExecutorService f731b;

    static {
        f730a = bg.class.getSimpleName();
        f731b = Executors.newCachedThreadPool();
    }

    bg() {
    }

    public static final String m659a(Context context) {
        try {
            if (C0072c.m291a().m293b() == null) {
                C0241m c0241m = new C0241m();
                C0072c.m291a().m292a(context, "AndroidBasePrefs");
            }
            String a = be.m200a().m223a(context, C0072c.m291a().m294c().m337e(), C0070a.MSDK, C0079j.m353c(), null, false);
            f731b.submit(new bh());
            String str = f730a;
            StringBuilder stringBuilder = new StringBuilder("Init risk component: ");
            be.m200a();
            stringBuilder.append(be.m210c());
            return a;
        } catch (Throwable th) {
            Log.e("paypal.sdk", "An internal component failed to initialize: " + th.getMessage());
            return null;
        }
    }
}
