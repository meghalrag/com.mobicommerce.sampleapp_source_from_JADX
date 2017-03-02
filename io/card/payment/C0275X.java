package io.card.payment;

import android.text.Editable;
import android.text.Spanned;
import com.paypal.android.sdk.payments.BuildConfig;

/* renamed from: io.card.payment.X */
final class C0275X implements ah {
    public int f1163a;
    private String f1164b;

    public C0275X(int i) {
        this.f1163a = i;
    }

    public final boolean m1115a() {
        return this.f1164b != null && this.f1164b.length() == this.f1163a;
    }

    public final void afterTextChanged(Editable editable) {
        this.f1164b = editable.toString();
    }

    public final String m1116b() {
        return this.f1164b;
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final boolean m1117c() {
        return m1115a();
    }

    public final CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        return (i2 <= 0 || ((spanned.length() + i4) - i3) + i2 <= this.f1163a) ? null : BuildConfig.VERSION_NAME;
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
