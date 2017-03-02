package com.paypal.android.sdk;

import org.apache.http.conn.ssl.SSLSocketFactory;

/* renamed from: com.paypal.android.sdk.B */
public class C0057B {
    private static final String f1a;

    static {
        f1a = C0057B.class.getSimpleName();
    }

    public static ch m0a(int i, boolean z, boolean z2, String str) {
        String str2 = f1a;
        ch chVar = new ch();
        chVar.m309a(i);
        chVar.m314a(str);
        chVar.m308a().getParams().setParameter("http.protocol.expect-continue", Boolean.valueOf(!z));
        if (z) {
            try {
                chVar.m316a(new C0067P());
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        } else if (z2) {
            chVar.m316a(new al());
        } else {
            chVar.m316a(SSLSocketFactory.getSocketFactory());
        }
        return chVar;
    }
}
