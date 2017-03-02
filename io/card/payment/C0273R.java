package io.card.payment;

import android.util.Log;

/* renamed from: io.card.payment.R */
final class C0273R extends C0162f {
    private /* synthetic */ String f1159a;

    C0273R(String str) {
        this.f1159a = str;
    }

    public final void m1110a(String str) {
        new StringBuilder("successfully posted ").append(this.f1159a).append(":\n").append(str);
    }

    public final void m1111a(Throwable th) {
        Log.w("CardScanAnalyticsReporter", "error posting " + this.f1159a + ": " + th.getMessage());
    }
}
