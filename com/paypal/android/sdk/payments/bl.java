package com.paypal.android.sdk.payments;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class bl implements Creator {
    bl() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new ShippingAddress((byte) 0);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new ShippingAddress[i];
    }
}
