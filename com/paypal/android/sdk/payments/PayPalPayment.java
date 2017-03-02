package com.paypal.android.sdk.payments;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Log;
import com.paypal.android.sdk.C0069T;
import com.paypal.android.sdk.C0072c;
import com.paypal.android.sdk.C0082m;
import com.paypal.android.sdk.bz;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import org.json.JSONObject;

public final class PayPalPayment implements Parcelable {
    public static final Creator CREATOR;
    public static final String PAYMENT_INTENT_AUTHORIZE = "authorize";
    public static final String PAYMENT_INTENT_ORDER = "order";
    public static final String PAYMENT_INTENT_SALE = "sale";
    private static final String f558a;
    private BigDecimal f559b;
    private String f560c;
    private String f561d;
    private String f562e;
    private PayPalPaymentDetails f563f;
    private String f564g;
    private PayPalItem[] f565h;
    private boolean f566i;
    private ShippingAddress f567j;
    private String f568k;
    private String f569l;
    private String f570m;

    static {
        f558a = PayPalPayment.class.getSimpleName();
        CREATOR = new C0102U();
    }

    private PayPalPayment(Parcel parcel) {
        this.f560c = parcel.readString();
        try {
            this.f559b = new BigDecimal(parcel.readString());
        } catch (NumberFormatException e) {
        }
        this.f561d = parcel.readString();
        this.f564g = parcel.readString();
        this.f562e = parcel.readString();
        this.f563f = (PayPalPaymentDetails) parcel.readParcelable(C0072c.m291a().m293b().getClassLoader());
        int readInt = parcel.readInt();
        if (readInt > 0) {
            this.f565h = new PayPalItem[readInt];
            parcel.readTypedArray(this.f565h, PayPalItem.CREATOR);
        }
        this.f567j = (ShippingAddress) parcel.readParcelable(C0072c.m291a().m293b().getClassLoader());
        this.f566i = parcel.readInt() == 1;
        this.f568k = parcel.readString();
        this.f569l = parcel.readString();
        this.f570m = parcel.readString();
    }

    public PayPalPayment(BigDecimal bigDecimal, String str, String str2, String str3) {
        this.f559b = bigDecimal;
        this.f560c = str;
        this.f561d = str2;
        this.f564g = str3;
        this.f563f = null;
        this.f562e = null;
        String str4 = f558a;
        toString();
    }

    private static void m432a(boolean z, String str) {
        if (!z) {
            Log.e(f558a, str + " is invalid.  Please see the docs.");
        }
    }

    private static boolean m433a(String str, String str2, int i) {
        if (!C0069T.m52d(str) || str.length() <= i) {
            return true;
        }
        Log.e(f558a, str2 + " is too long (max " + i + ")");
        return false;
    }

    private boolean m434k() {
        boolean z = false;
        if (this.f560c != null) {
            try {
                z = C0082m.m363a().contains(Currency.getInstance(this.f560c));
            } catch (IllegalArgumentException e) {
            }
        }
        return z;
    }

    protected final BigDecimal m435a() {
        return this.f559b;
    }

    protected final String m436b() {
        return this.f561d;
    }

    public final PayPalPayment bnCode(String str) {
        this.f562e = str;
        return this;
    }

    protected final String m437c() {
        return this.f564g;
    }

    public final PayPalPayment custom(String str) {
        this.f569l = str;
        return this;
    }

    protected final String m438d() {
        return this.f560c;
    }

    public final int describeContents() {
        return 0;
    }

    protected final String m439e() {
        return this.f562e;
    }

    public final PayPalPayment enablePayPalShippingAddressesRetrieval(boolean z) {
        this.f566i = z;
        return this;
    }

    protected final PayPalPaymentDetails m440f() {
        return this.f563f;
    }

    protected final PayPalItem[] m441g() {
        return this.f565h;
    }

    public final String getAmountAsLocalizedString() {
        if (this.f559b == null) {
            return null;
        }
        return C0082m.m362a(Locale.getDefault(), bz.m975d().m978b().m354a(), this.f559b.doubleValue(), this.f560c, true);
    }

    public final ShippingAddress getProvidedShippingAddress() {
        return this.f567j;
    }

    protected final String m442h() {
        return this.f568k;
    }

    protected final String m443i() {
        return this.f569l;
    }

    public final PayPalPayment invoiceNumber(String str) {
        this.f568k = str;
        return this;
    }

    public final boolean isEnablePayPalShippingAddressesRetrieval() {
        return this.f566i;
    }

    public final boolean isProcessable() {
        boolean z;
        boolean k = m434k();
        boolean z2 = this.f559b != null && this.f559b.compareTo(BigDecimal.ZERO) == 1;
        boolean b = C0069T.m50b(this.f561d);
        boolean z3 = C0069T.m52d(this.f564g) && (this.f564g.equals(PAYMENT_INTENT_SALE) || this.f564g.equals(PAYMENT_INTENT_AUTHORIZE) || this.f564g.equals(PAYMENT_INTENT_ORDER));
        boolean isProcessable = this.f563f == null ? true : this.f563f.isProcessable();
        boolean a = C0069T.m51c(this.f562e) ? true : C0069T.m47a(this.f562e);
        if (this.f565h == null || this.f565h.length == 0) {
            z = true;
        } else {
            for (PayPalItem isValid : this.f565h) {
                if (!isValid.isValid()) {
                    z = false;
                    break;
                }
            }
            z = true;
        }
        boolean z4 = m433a(this.f568k, "invoiceNumber", AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY);
        if (!m433a(this.f569l, "custom", AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY)) {
            z4 = false;
        }
        if (!m433a(this.f570m, "softDescriptor", 22)) {
            z4 = false;
        }
        m432a(k, "currencyCode");
        m432a(z2, "amount");
        m432a(b, "shortDescription");
        m432a(z3, "paymentIntent");
        m432a(isProcessable, "details");
        m432a(a, "bnCode");
        m432a(z, "items");
        return k && z2 && b && isProcessable && z3 && a && z && z4;
    }

    public final PayPalPayment items(PayPalItem[] payPalItemArr) {
        this.f565h = payPalItemArr;
        return this;
    }

    protected final String m444j() {
        return this.f570m;
    }

    public final PayPalPayment paymentDetails(PayPalPaymentDetails payPalPaymentDetails) {
        this.f563f = payPalPaymentDetails;
        return this;
    }

    public final PayPalPayment providedShippingAddress(ShippingAddress shippingAddress) {
        this.f567j = shippingAddress;
        return this;
    }

    public final PayPalPayment softDescriptor(String str) {
        this.f570m = str;
        return this;
    }

    public final JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("amount", this.f559b.toPlainString());
            jSONObject.put("currency_code", this.f560c);
            if (this.f563f != null) {
                jSONObject.put("details", this.f563f.toJSONObject());
            }
            jSONObject.put("short_description", this.f561d);
            jSONObject.put("intent", this.f564g.toString());
            if (C0069T.m52d(this.f562e)) {
                jSONObject.put("bn_code", this.f562e);
            }
            if (this.f565h == null || this.f565h.length <= 0) {
                return jSONObject;
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.accumulate("items", PayPalItem.toJSONArray(this.f565h));
            jSONObject.put("item_list", jSONObject2);
            return jSONObject;
        } catch (Throwable e) {
            Log.e(f558a, "error encoding JSON", e);
            return null;
        }
    }

    public final String toString() {
        String str = "PayPalPayment: {%s: $%s %s, %s}";
        Object[] objArr = new Object[4];
        objArr[0] = this.f561d;
        objArr[1] = this.f559b != null ? this.f559b.toString() : null;
        objArr[2] = this.f560c;
        objArr[3] = this.f564g;
        return String.format(str, objArr);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = 0;
        parcel.writeString(this.f560c);
        parcel.writeString(this.f559b.toString());
        parcel.writeString(this.f561d);
        parcel.writeString(this.f564g);
        parcel.writeString(this.f562e);
        parcel.writeParcelable(this.f563f, 0);
        if (this.f565h != null) {
            parcel.writeInt(this.f565h.length);
            parcel.writeTypedArray(this.f565h, 0);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeParcelable(this.f567j, 0);
        if (this.f566i) {
            i2 = 1;
        }
        parcel.writeInt(i2);
        parcel.writeString(this.f568k);
        parcel.writeString(this.f569l);
        parcel.writeString(this.f570m);
    }
}
