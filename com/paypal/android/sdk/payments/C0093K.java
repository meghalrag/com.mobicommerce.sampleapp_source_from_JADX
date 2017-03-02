package com.paypal.android.sdk.payments;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/* renamed from: com.paypal.android.sdk.payments.K */
enum C0093K implements Parcelable {
    EMAIL,
    PIN;
    
    public static final Creator CREATOR;

    static {
        CREATOR = new C0094L();
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this);
    }
}
