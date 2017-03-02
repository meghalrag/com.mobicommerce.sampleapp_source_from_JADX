package com.paypal.android.sdk.payments;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class bk implements Creator {
    bk() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new bj(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new bj[i];
    }
}
