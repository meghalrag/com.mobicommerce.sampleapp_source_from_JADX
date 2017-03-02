package com.paypal.android.sdk;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class bu extends bw implements Parcelable {
    public static final Creator CREATOR;
    private boolean f1069c;
    private String f1070d;

    static {
        bu.class.getSimpleName();
        CREATOR = new bv();
    }

    public bu(Parcel parcel) {
        super(parcel);
        this.f1070d = parcel.readString();
        this.f1069c = parcel.readByte() != null;
    }

    public bu(String str, String str2, long j, boolean z) {
        this.f406a = str;
        this.f407b = j;
        this.f1070d = str2;
        this.f1069c = z;
    }

    public final boolean m974a() {
        return this.f1069c;
    }

    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return bu.class.getSimpleName() + "(mToken:" + this.a + ", mGoodUntil:" + this.b + ", isCreatedInternally:" + this.f1069c + ")";
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeLong(this.b);
        parcel.writeString(this.f1070d);
        parcel.writeByte((byte) (this.f1069c ? 1 : 0));
    }
}
