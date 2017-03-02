package io.card.payment;

import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import com.paypal.android.sdk.payments.BuildConfig;
import java.util.Date;

/* renamed from: io.card.payment.W */
final class C0274W implements ah {
    public int f1160a;
    public int f1161b;
    private boolean f1162c;

    public C0274W() {
        getClass().getName();
    }

    public C0274W(int i, int i2) {
        getClass().getName();
        this.f1160a = i;
        this.f1161b = i2;
        boolean z = this.f1160a > 0 && this.f1161b > 0;
        this.f1162c = z;
    }

    public final boolean m1112a() {
        if (this.f1160a <= 0 || 12 < this.f1160a) {
            return false;
        }
        Date date = new Date();
        return this.f1161b <= (date.getYear() + 1900) + 15 ? this.f1161b > date.getYear() + 1900 || (this.f1161b == date.getYear() + 1900 && this.f1160a >= date.getMonth() + 1) : false;
    }

    public final void afterTextChanged(Editable editable) {
        this.f1162c = editable.length() >= 5;
        String obj = editable.toString();
        if (obj != null) {
            Date b = C0151Q.m749b(obj);
            if (b != null) {
                this.f1160a = b.getMonth() + 1;
                this.f1161b = b.getYear();
                if (this.f1161b < 1900) {
                    this.f1161b += 1900;
                }
            }
        }
    }

    public final String m1113b() {
        return String.format("%02d/%02d", new Object[]{Integer.valueOf(this.f1160a), Integer.valueOf(this.f1161b % 100)});
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.f1160a = 0;
        this.f1161b = 0;
        this.f1162c = false;
    }

    public final boolean m1114c() {
        return this.f1162c;
    }

    public final CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        int i5;
        CharSequence spannableStringBuilder = new SpannableStringBuilder(charSequence);
        if (i3 != 0 || spannableStringBuilder.length() <= 0 || '1' >= spannableStringBuilder.charAt(0) || spannableStringBuilder.charAt(0) > '9') {
            i5 = i2;
        } else {
            spannableStringBuilder.insert(0, "0");
            i5 = i2 + 1;
        }
        int i6 = i4 - i3;
        if (i3 - i6 <= 2 && (i3 + i5) - i6 >= 2) {
            i6 = 2 - i3;
            if (i6 == i5 || (i6 >= 0 && i6 < i5 && spannableStringBuilder.charAt(i6) != '/')) {
                spannableStringBuilder.insert(i6, "/");
                i5++;
            }
        }
        String spannableStringBuilder2 = new SpannableStringBuilder(spanned).replace(i3, i4, spannableStringBuilder, i, i5).toString();
        if (spannableStringBuilder2.length() > 0 && (spannableStringBuilder2.charAt(0) < '0' || '1' < spannableStringBuilder2.charAt(0))) {
            return BuildConfig.VERSION_NAME;
        }
        if (spannableStringBuilder2.length() >= 2) {
            if (spannableStringBuilder2.charAt(0) != '0' && spannableStringBuilder2.charAt(1) > '2') {
                return BuildConfig.VERSION_NAME;
            }
            if (spannableStringBuilder2.charAt(0) == '0' && spannableStringBuilder2.charAt(1) == '0') {
                return BuildConfig.VERSION_NAME;
            }
        }
        return spannableStringBuilder2.length() > 5 ? BuildConfig.VERSION_NAME : spannableStringBuilder;
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
