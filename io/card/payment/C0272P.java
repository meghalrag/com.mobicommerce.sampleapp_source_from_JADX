package io.card.payment;

import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import com.paypal.android.sdk.payments.BuildConfig;

/* renamed from: io.card.payment.P */
final class C0272P implements ah {
    private static int[] f1155b;
    private static int[] f1156c;
    private String f1157a;
    private int f1158d;

    static {
        f1155b = new int[]{4, 11};
        f1156c = new int[]{4, 9, 14};
    }

    public C0272P() {
        this.f1158d = 0;
    }

    public C0272P(String str) {
        this.f1158d = 0;
        this.f1157a = str;
    }

    public final boolean m1107a() {
        return m1109c() && C0151Q.m748a(this.f1157a);
    }

    public final void afterTextChanged(Editable editable) {
        int i = 0;
        this.f1157a = C0151Q.m751c(editable.toString());
        CardType fromCardNumber = CardType.fromCardNumber(this.f1157a);
        if (this.f1158d > 1) {
            int i2 = this.f1158d;
            int i3 = this.f1158d - 1;
            this.f1158d = 0;
            if (i2 > i3) {
                editable.delete(i3, i2);
            }
        }
        while (i < editable.length()) {
            char charAt = editable.charAt(i);
            if ((fromCardNumber.numberLength() == 15 && (i == 4 || i == 11)) || (fromCardNumber.numberLength() == 16 && (i == 4 || i == 9 || i == 14))) {
                if (charAt != ' ') {
                    editable.insert(i, " ");
                }
            } else if (charAt == ' ') {
                editable.delete(i, i + 1);
                i--;
            }
            i++;
        }
    }

    public final String m1108b() {
        return this.f1157a;
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final boolean m1109c() {
        if (TextUtils.isEmpty(this.f1157a)) {
            return false;
        }
        return this.f1157a.length() == CardType.fromCardNumber(this.f1157a).numberLength();
    }

    public final CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        String c = C0151Q.m751c(new SpannableStringBuilder(spanned).replace(i3, i4, charSequence, i, i2).toString());
        int numberLength = CardType.fromCardNumber(c).numberLength();
        if (c.length() > numberLength) {
            return BuildConfig.VERSION_NAME;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
        int[] iArr = numberLength == 15 ? f1155b : f1156c;
        int i5 = i4 - i3;
        numberLength = 0;
        while (numberLength < iArr.length) {
            if (charSequence.length() == 0 && i3 == iArr[numberLength] && spanned.charAt(i3) == ' ') {
                this.f1158d = iArr[numberLength];
            }
            if (i3 - i5 <= iArr[numberLength] && (i3 + i2) - i5 >= iArr[numberLength]) {
                int i6 = iArr[numberLength] - i3;
                if (i6 == i2 || (i6 >= 0 && i6 < i2 && spannableStringBuilder.charAt(i6) != ' ')) {
                    spannableStringBuilder.insert(i6, " ");
                    i2++;
                }
            }
            numberLength++;
        }
        return spannableStringBuilder;
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
