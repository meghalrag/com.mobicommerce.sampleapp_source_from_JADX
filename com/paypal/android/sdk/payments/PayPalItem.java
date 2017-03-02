package com.paypal.android.sdk.payments;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.paypal.android.sdk.C0069T;
import java.math.BigDecimal;
import org.json.JSONArray;
import org.json.JSONObject;

public final class PayPalItem implements Parcelable {
    public static final Creator CREATOR;
    private String f552a;
    private Integer f553b;
    private BigDecimal f554c;
    private String f555d;
    private String f556e;

    static {
        PayPalItem.class.getSimpleName();
        CREATOR = new C0100S();
    }

    private PayPalItem(Parcel parcel) {
        this.f552a = parcel.readString();
        this.f553b = Integer.valueOf(parcel.readInt());
        try {
            this.f554c = new BigDecimal(parcel.readString());
        } catch (NumberFormatException e) {
        }
        this.f555d = parcel.readString();
        this.f556e = parcel.readString();
    }

    public PayPalItem(String str, Integer num, BigDecimal bigDecimal, String str2, String str3) {
        this.f552a = str;
        this.f553b = num;
        this.f554c = bigDecimal;
        this.f555d = str2;
        this.f556e = str3;
    }

    public static BigDecimal getItemTotal(PayPalItem[] payPalItemArr) {
        BigDecimal bigDecimal = new BigDecimal("0.00");
        for (PayPalItem payPalItem : payPalItemArr) {
            bigDecimal = bigDecimal.add(payPalItem.f554c.multiply(BigDecimal.valueOf((long) payPalItem.f553b.intValue())));
        }
        return bigDecimal;
    }

    public static JSONArray toJSONArray(PayPalItem[] payPalItemArr) {
        JSONArray jSONArray = new JSONArray();
        for (PayPalItem payPalItem : payPalItemArr) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.accumulate("quantity", Integer.toString(payPalItem.f553b.intValue()));
            jSONObject.accumulate("name", payPalItem.f552a);
            jSONObject.accumulate("price", payPalItem.f554c.toString());
            jSONObject.accumulate("currency", payPalItem.f555d);
            jSONObject.accumulate("sku", payPalItem.f556e);
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    public final int describeContents() {
        return 0;
    }

    public final String getCurrency() {
        return this.f555d;
    }

    public final String getName() {
        return this.f552a;
    }

    public final BigDecimal getPrice() {
        return this.f554c;
    }

    public final Integer getQuantity() {
        return this.f553b;
    }

    public final String getSku() {
        return this.f556e;
    }

    public final boolean isValid() {
        if (this.f553b.intValue() <= 0) {
            Log.e("paypal.sdk", "item.quantity must be a positive integer.");
            return false;
        } else if (C0069T.m51c(this.f555d)) {
            Log.e("paypal.sdk", "item.currency field is required.");
            return false;
        } else if (C0069T.m51c(this.f552a)) {
            Log.e("paypal.sdk", "item.name field is required.");
            return false;
        } else if (C0069T.m51c(this.f556e)) {
            Log.e("paypal.sdk", "item.sku field is required.");
            return false;
        } else if (this.f554c != null) {
            return true;
        } else {
            Log.e("paypal.sdk", "item.price field is required.");
            return false;
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f552a);
        parcel.writeInt(this.f553b.intValue());
        parcel.writeString(this.f554c.toString());
        parcel.writeString(this.f555d);
        parcel.writeString(this.f556e);
    }
}
