package io.card.payment;

import android.text.Editable;
import android.text.Spanned;

class ab implements ah {
    private String f1165a;

    ab() {
    }

    public boolean m1118a() {
        return this.f1165a != null && this.f1165a.length() > 0;
    }

    public void afterTextChanged(Editable editable) {
        this.f1165a = editable.toString().trim();
    }

    public final String m1119b() {
        return this.f1165a;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final boolean m1120c() {
        return m1118a();
    }

    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        return null;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
