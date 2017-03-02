package com.paypal.android.sdk.payments;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import java.math.BigDecimal;
import org.json.JSONObject;

public final class PayPalPaymentDetails implements Parcelable {
    public static final Creator CREATOR;
    private static final String f571a;
    private BigDecimal f572b;
    private BigDecimal f573c;
    private BigDecimal f574d;

    static {
        f571a = PayPalPaymentDetails.class.getSimpleName();
        CREATOR = new C0103V();
    }

    private PayPalPaymentDetails(Parcel parcel) {
        BigDecimal bigDecimal = null;
        try {
            String readString = parcel.readString();
            this.f573c = readString == null ? null : new BigDecimal(readString);
            readString = parcel.readString();
            this.f572b = readString == null ? null : new BigDecimal(readString);
            String readString2 = parcel.readString();
            if (readString2 != null) {
                bigDecimal = new BigDecimal(readString2);
            }
            this.f574d = bigDecimal;
        } catch (NumberFormatException e) {
            throw new RuntimeException("error unparceling PayPalPaymentDetails");
        }
    }

    public PayPalPaymentDetails(BigDecimal bigDecimal, BigDecimal bigDecimal2, BigDecimal bigDecimal3) {
        this.f573c = bigDecimal;
        this.f572b = bigDecimal2;
        this.f574d = bigDecimal3;
    }

    protected final BigDecimal m445a() {
        return this.f572b;
    }

    protected final BigDecimal m446b() {
        return this.f573c;
    }

    protected final BigDecimal m447c() {
        return this.f574d;
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean isProcessable() {
        return this.f572b != null;
    }

    public final JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.f573c != null) {
                jSONObject.put("shipping", this.f573c.toPlainString());
            }
            if (this.f572b != null) {
                jSONObject.put("subtotal", this.f572b.toPlainString());
            }
            if (this.f574d == null) {
                return jSONObject;
            }
            jSONObject.put("tax", this.f574d.toPlainString());
            return jSONObject;
        } catch (Throwable e) {
            Log.e(f571a, "error encoding JSON", e);
            return null;
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        String str = null;
        parcel.writeString(this.f573c == null ? null : this.f573c.toString());
        parcel.writeString(this.f572b == null ? null : this.f572b.toString());
        if (this.f574d != null) {
            str = this.f574d.toString();
        }
        parcel.writeString(str);
    }
}
