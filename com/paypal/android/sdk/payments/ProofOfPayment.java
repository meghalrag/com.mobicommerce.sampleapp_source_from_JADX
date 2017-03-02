package com.paypal.android.sdk.payments;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.paypal.android.sdk.C0069T;
import org.json.JSONObject;

public final class ProofOfPayment implements Parcelable {
    public static final Creator CREATOR;
    private static final String f652a;
    private String f653b;
    private String f654c;
    private String f655d;
    private String f656e;
    private String f657f;

    static {
        f652a = ProofOfPayment.class.getSimpleName();
        CREATOR = new bf();
    }

    private ProofOfPayment(Parcel parcel) {
        this(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
    }

    ProofOfPayment(String str, String str2, String str3, String str4, String str5) {
        this.f653b = str;
        this.f654c = str2;
        this.f655d = str3;
        this.f656e = str4;
        this.f657f = str5;
        String str6 = f652a;
        new StringBuilder("ProofOfPayment created: ").append(toString());
    }

    public final int describeContents() {
        return 0;
    }

    public final String getCreateTime() {
        return this.f655d;
    }

    public final String getIntent() {
        return this.f656e;
    }

    public final String getPaymentId() {
        return this.f654c;
    }

    public final String getState() {
        return this.f653b;
    }

    public final String getTransactionId() {
        return this.f657f;
    }

    public final JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("create_time", this.f655d);
            jSONObject.put("id", this.f654c);
            jSONObject.put("intent", this.f656e);
            jSONObject.put("state", this.f653b);
            if (!C0069T.m52d(this.f657f) || !C0069T.m52d(this.f656e)) {
                return jSONObject;
            }
            if (this.f656e.equals(PayPalPayment.PAYMENT_INTENT_AUTHORIZE)) {
                jSONObject.put("authorization_id", this.f657f);
                return jSONObject;
            } else if (!this.f656e.equals(PayPalPayment.PAYMENT_INTENT_ORDER)) {
                return jSONObject;
            } else {
                jSONObject.put("order_id", this.f657f);
                return jSONObject;
            }
        } catch (Throwable e) {
            Log.e(f652a, "error encoding JSON", e);
            return null;
        }
    }

    public final String toString() {
        return "{" + this.f656e + ": " + (C0069T.m52d(this.f657f) ? this.f657f : "no transactionId") + "}";
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f653b);
        parcel.writeString(this.f654c);
        parcel.writeString(this.f655d);
        parcel.writeString(this.f656e);
        parcel.writeString(this.f657f);
    }
}
