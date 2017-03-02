package com.paypal.android.sdk.payments;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import org.json.JSONObject;

public final class PayPalAuthorization implements Parcelable {
    public static final Creator CREATOR;
    private String f528a;
    private String f529b;
    private String f530c;

    static {
        PayPalAuthorization.class.getSimpleName();
        CREATOR = new C0095M();
    }

    private PayPalAuthorization(Parcel parcel) {
        this.f528a = parcel.readString();
        this.f529b = parcel.readString();
        this.f530c = parcel.readString();
    }

    PayPalAuthorization(String str, String str2, String str3) {
        this.f528a = str;
        this.f529b = str2;
        if ("partner".equals(BuildConfig.FLAVOR)) {
            this.f530c = str3;
        }
    }

    public final int describeContents() {
        return 0;
    }

    public final String getAuthorizationCode() {
        return this.f529b;
    }

    public final String getEnvironment() {
        return this.f528a;
    }

    public final JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("environment", this.f528a);
            jSONObject2.put("paypal_sdk_version", Version.PRODUCT_VERSION);
            jSONObject2.put("platform", "Android");
            jSONObject2.put("product_name", Version.PRODUCT_NAME);
            jSONObject.put("client", jSONObject2);
            jSONObject2 = new JSONObject();
            jSONObject2.put("code", this.f529b);
            jSONObject.put("response", jSONObject2);
            if ("partner".equals(BuildConfig.FLAVOR)) {
                jSONObject2 = new JSONObject();
                jSONObject2.put("display_string", this.f530c);
                jSONObject.put("user", jSONObject2);
            }
            jSONObject.put("response_type", "authorization_code");
            return jSONObject;
        } catch (Throwable e) {
            Log.e("paypal.sdk", "Error encoding JSON", e);
            return null;
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f528a);
        parcel.writeString(this.f529b);
        parcel.writeString(this.f530c);
    }
}
