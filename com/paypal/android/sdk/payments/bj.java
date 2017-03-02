package com.paypal.android.sdk.payments;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.paypal.android.sdk.C0297X;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class bj implements Parcelable {
    public static final Creator CREATOR;
    private JSONArray f734a;
    private ShippingAddress f735b;
    private int f736c;
    private int f737d;
    private JSONObject f738e;
    private JSONArray f739f;
    private int f740g;
    private String f741h;
    private String f742i;

    static {
        CREATOR = new bk();
    }

    public bj(Parcel parcel) {
        this.f736c = -1;
        this.f740g = -1;
        if (parcel != null) {
            String readString;
            try {
                readString = parcel.readString();
                if (readString != null) {
                    this.f734a = new JSONArray(readString);
                } else {
                    this.f734a = null;
                }
            } catch (JSONException e) {
                this.f734a = null;
            }
            this.f735b = (ShippingAddress) parcel.readParcelable(bj.class.getClassLoader());
            try {
                readString = parcel.readString();
                if (readString != null) {
                    this.f738e = new JSONObject(readString);
                } else {
                    this.f738e = null;
                }
            } catch (JSONException e2) {
                this.f738e = null;
            }
            try {
                readString = parcel.readString();
                if (readString != null) {
                    this.f739f = new JSONArray(readString);
                } else {
                    this.f739f = null;
                }
            } catch (JSONException e3) {
                this.f739f = null;
            }
            this.f741h = parcel.readString();
            this.f742i = parcel.readString();
            this.f740g = parcel.readInt();
            this.f736c = parcel.readInt();
            this.f737d = parcel.readInt();
        }
    }

    public bj(C0297X c0297x, ShippingAddress shippingAddress) {
        this.f736c = -1;
        this.f740g = -1;
        this.f734a = c0297x.m1193i();
        this.f738e = c0297x.m1194j();
        this.f739f = c0297x.m1195k();
        this.f741h = c0297x.m1191g();
        this.f742i = c0297x.m1192h();
        this.f735b = shippingAddress;
        if (this.f735b != null) {
            this.f736c = 0;
            this.f737d = m662a(this.f735b, this.f734a);
            return;
        }
        this.f736c = m663a(this.f734a);
        this.f737d = -1;
    }

    private static int m662a(ShippingAddress shippingAddress, JSONArray jSONArray) {
        if (!(shippingAddress == null || jSONArray == null)) {
            for (int i = 0; i < jSONArray.length(); i++) {
                if (shippingAddress.m616a(jSONArray.optJSONObject(i))) {
                    return i;
                }
            }
        }
        return -1;
    }

    private static int m663a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return -1;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            if (jSONArray.optJSONObject(i).optBoolean("default_address", false)) {
                return i;
            }
        }
        return 0;
    }

    public final JSONArray m664a() {
        return this.f734a;
    }

    public final void m665a(int i) {
        this.f740g = i;
    }

    public final ShippingAddress m666b() {
        return this.f735b;
    }

    public final void m667b(int i) {
        this.f736c = i;
    }

    public final JSONObject m668c() {
        return this.f738e;
    }

    public final JSONArray m669d() {
        return this.f739f;
    }

    public int describeContents() {
        return 0;
    }

    public final String m670e() {
        return this.f741h;
    }

    public final String m671f() {
        return this.f742i;
    }

    public final int m672g() {
        return this.f740g < 0 ? 0 : this.f740g;
    }

    public final int m673h() {
        return this.f736c < 0 ? 0 : this.f736c;
    }

    public final int m674i() {
        return this.f737d;
    }

    public final boolean m675j() {
        return this.f740g != -1;
    }

    public final boolean m676k() {
        return this.f736c != -1;
    }

    public final JSONObject m677l() {
        return this.f740g <= 0 ? null : this.f739f.optJSONObject(this.f740g - 1);
    }

    public final JSONObject m678m() {
        if (this.f736c < 0) {
            return null;
        }
        if (this.f735b == null) {
            return this.f734a.optJSONObject(this.f736c);
        }
        if (this.f736c == 0) {
            return this.f737d < 0 ? this.f735b.toJSONObject() : this.f734a.optJSONObject(this.f737d);
        } else {
            int i = this.f736c - 1;
            if (this.f737d >= 0 && i >= this.f737d) {
                i++;
            }
            return this.f734a.optJSONObject(i);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        String str = null;
        parcel.writeString(this.f734a != null ? this.f734a.toString() : null);
        parcel.writeParcelable(this.f735b, 0);
        parcel.writeString(this.f738e != null ? this.f738e.toString() : null);
        if (this.f739f != null) {
            str = this.f739f.toString();
        }
        parcel.writeString(str);
        parcel.writeString(this.f741h);
        parcel.writeString(this.f742i);
        parcel.writeInt(this.f740g);
        parcel.writeInt(this.f736c);
        parcel.writeInt(this.f737d);
    }
}
