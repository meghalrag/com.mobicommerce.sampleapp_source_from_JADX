package io.card.payment;

import android.util.Log;
import org.apache.http.conn.ssl.SSLSocketFactory;

/* renamed from: io.card.payment.Y */
final class C0156Y {
    private static String f858a;
    private static C0158a f859b;

    static {
        f858a = String.format("card.io/%s (Android %s)", new Object[]{"sdk-3.1.5-10-gba7f52e", C0155V.m762b()});
        f859b = null;
    }

    static synchronized C0158a m764a() {
        C0158a c0158a = null;
        synchronized (C0156Y.class) {
            if (f859b == null) {
                C0158a c0158a2 = new C0158a();
                f859b = c0158a2;
                c0158a2.m771a(f858a);
                try {
                    f859b.m773a(SSLSocketFactory.getSocketFactory());
                } catch (Throwable e) {
                    Log.e("HttpClientFactory", "Exception creating https client", e);
                    f859b = null;
                }
            }
            c0158a = f859b;
        }
        return c0158a;
    }
}
