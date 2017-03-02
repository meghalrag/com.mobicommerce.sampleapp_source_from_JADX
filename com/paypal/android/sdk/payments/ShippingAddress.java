package com.paypal.android.sdk.payments;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.paypal.android.sdk.C0069T;
import org.json.JSONException;
import org.json.JSONObject;

public final class ShippingAddress implements Parcelable {
    public static final Creator CREATOR;
    private static final String f659a;
    private String f660b;
    private String f661c;
    private String f662d;
    private String f663e;
    private String f664f;
    private String f665g;
    private String f666h;

    static {
        f659a = ShippingAddress.class.getSimpleName();
        CREATOR = new bl();
    }

    private ShippingAddress(Parcel parcel) {
        this.f660b = parcel.readString();
        this.f661c = parcel.readString();
        this.f662d = parcel.readString();
        this.f663e = parcel.readString();
        this.f664f = parcel.readString();
        this.f665g = parcel.readString();
        this.f666h = parcel.readString();
    }

    private static void m613a(boolean z, String str) {
        if (!z) {
            Log.e(f659a, str + " is invalid.  Please see the docs.");
        }
    }

    private static boolean m614a(String str) {
        return C0069T.m52d(str);
    }

    private static boolean m615a(String str, String str2) {
        return C0069T.m51c(str) ? C0069T.m51c(str2) : C0069T.m51c(str2) ? false : str.trim().equals(str2.trim());
    }

    final boolean m616a(JSONObject jSONObject) {
        return m615a(jSONObject.optString("recipient_name"), this.f660b) && m615a(jSONObject.optString("line1"), this.f661c) && m615a(jSONObject.optString("line2"), this.f662d) && m615a(jSONObject.optString("city"), this.f663e) && m615a(jSONObject.optString("state"), this.f664f) && m615a(jSONObject.optString("country_code"), this.f666h) && m615a(jSONObject.optString("postal_code"), this.f665g);
    }

    public final ShippingAddress city(String str) {
        this.f663e = str;
        return this;
    }

    public final ShippingAddress countryCode(String str) {
        this.f666h = str;
        return this;
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean isProcessable() {
        boolean d = C0069T.m52d(this.f660b);
        boolean d2 = C0069T.m52d(this.f661c);
        boolean d3 = C0069T.m52d(this.f663e);
        boolean z = C0069T.m52d(this.f666h) && this.f666h.length() == 2;
        m613a(d, "recipient_name");
        m613a(d2, "line1");
        m613a(d3, "city");
        m613a(z, "country_code");
        return d && d2 && d3 && z;
    }

    public final ShippingAddress line1(String str) {
        this.f661c = str;
        return this;
    }

    public final ShippingAddress line2(String str) {
        this.f662d = str;
        return this;
    }

    public final ShippingAddress postalCode(String str) {
        this.f665g = str;
        return this;
    }

    public final ShippingAddress recipientName(String str) {
        this.f660b = str;
        return this;
    }

    public final ShippingAddress state(String str) {
        this.f664f = str;
        return this;
    }

    public final JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.accumulate("recipient_name", this.f660b);
            jSONObject.accumulate("line1", this.f661c);
            jSONObject.accumulate("city", this.f663e);
            jSONObject.accumulate("country_code", this.f666h);
            if (m614a(this.f662d)) {
                jSONObject.accumulate("line2", this.f662d);
            }
            if (m614a(this.f664f)) {
                jSONObject.accumulate("state", this.f664f);
            }
            if (m614a(this.f665g)) {
                jSONObject.accumulate("postal_code", this.f665g);
            }
        } catch (JSONException e) {
            Log.e(f659a, e.getMessage());
        }
        return jSONObject;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f660b);
        parcel.writeString(this.f661c);
        parcel.writeString(this.f662d);
        parcel.writeString(this.f663e);
        parcel.writeString(this.f664f);
        parcel.writeString(this.f665g);
        parcel.writeString(this.f666h);
    }
}
