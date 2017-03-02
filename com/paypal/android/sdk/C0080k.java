package com.paypal.android.sdk;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/* renamed from: com.paypal.android.sdk.k */
public class C0080k implements Parcelable {
    public static final Creator CREATOR;
    private String f474a;

    static {
        CREATOR = new C0081l();
    }

    public C0080k(Parcel parcel) {
        this.f474a = parcel.readString();
    }

    public C0080k(String str) {
        if (str.equals("OTHER") || str.length() == 2) {
            this.f474a = str;
        } else {
            this.f474a = "US";
        }
    }

    public final String m354a() {
        return this.f474a;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f474a.equals(((C0080k) obj).f474a);
    }

    public int hashCode() {
        String str = this.f474a;
        return str == null ? C0069T.m33a(47, 0) : str.hashCode();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f474a);
    }
}
