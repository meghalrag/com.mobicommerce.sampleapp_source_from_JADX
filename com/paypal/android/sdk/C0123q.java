package com.paypal.android.sdk;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/* renamed from: com.paypal.android.sdk.q */
public final class C0123q implements Parcelable {
    public static final Creator CREATOR;
    private String f761a;
    private String f762b;
    private C0128v f763c;
    private String f764d;

    static {
        CREATOR = new C0124r();
    }

    public C0123q(Parcel parcel) {
        this.f761a = parcel.readString();
        this.f762b = parcel.readString();
        this.f763c = (C0128v) parcel.readParcelable(C0128v.class.getClassLoader());
        this.f764d = parcel.readString();
    }

    public C0123q(C0128v c0128v, String str) {
        this.f763c = c0128v;
        this.f764d = str;
    }

    public C0123q(String str, String str2) {
        this.f761a = str;
        this.f762b = str2;
    }

    public final boolean m689a() {
        return this.f761a != null;
    }

    public final String m690b() {
        return this.f761a;
    }

    public final String m691c() {
        return this.f762b;
    }

    public final C0128v m692d() {
        return this.f763c;
    }

    public final int describeContents() {
        return 0;
    }

    public final String m693e() {
        return this.f764d;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f761a);
        parcel.writeString(this.f762b);
        parcel.writeParcelable(this.f763c, 0);
        parcel.writeString(this.f764d);
    }
}
