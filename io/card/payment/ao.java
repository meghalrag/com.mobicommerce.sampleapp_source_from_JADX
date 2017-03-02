package io.card.payment;

import android.content.Intent;

public final class ao {
    private static final C0166j f913a;

    static {
        f913a = new C0166j(ap.class, C0168z.f951a);
    }

    public static String m801a(ap apVar) {
        return f913a.m819a((Enum) apVar);
    }

    public static String m802a(ap apVar, String str) {
        return f913a.m820a(apVar, f913a.m822b(str));
    }

    public static void m803a(Intent intent) {
        f913a.m821a(intent.getStringExtra(CardIOActivity.EXTRA_LANGUAGE_OR_LOCALE));
    }
}
