package com.paypal.android.sdk.payments;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import org.json.JSONObject;

public final class PaymentConfirmation implements Parcelable {
    public static final Creator CREATOR;
    private static final String f625a;
    private String f626b;
    private PayPalPayment f627c;
    private ProofOfPayment f628d;

    static {
        f625a = PaymentConfirmation.class.getSimpleName();
        CREATOR = new aG();
    }

    private PaymentConfirmation(Parcel parcel) {
        this.f626b = parcel.readString();
        this.f627c = (PayPalPayment) parcel.readParcelable(PayPalPayment.class.getClassLoader());
        this.f628d = (ProofOfPayment) parcel.readParcelable(ProofOfPayment.class.getClassLoader());
    }

    PaymentConfirmation(String str, PayPalPayment payPalPayment, ProofOfPayment proofOfPayment) {
        this.f626b = str;
        this.f627c = payPalPayment;
        this.f628d = proofOfPayment;
    }

    public final int describeContents() {
        return 0;
    }

    public final String getEnvironment() {
        return this.f626b;
    }

    public final PayPalPayment getPayment() {
        return this.f627c;
    }

    public final ProofOfPayment getProofOfPayment() {
        return this.f628d;
    }

    public final JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("environment", this.f626b);
            jSONObject2.put("paypal_sdk_version", Version.PRODUCT_VERSION);
            jSONObject2.put("platform", "Android");
            jSONObject2.put("product_name", Version.PRODUCT_NAME);
            jSONObject.put("client", jSONObject2);
            jSONObject.put("response", this.f628d.toJSONObject());
            jSONObject.put("response_type", "payment");
            return jSONObject;
        } catch (Throwable e) {
            Log.e(f625a, "Error encoding JSON", e);
            return null;
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f626b);
        parcel.writeParcelable(this.f627c, 0);
        parcel.writeParcelable(this.f628d, 0);
    }
}
