package com.paypal.android.sdk.payments;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.paypal.android.sdk.payments.S */
final class C0100S implements Creator {
    C0100S() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new PayPalItem((byte) 0);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new PayPalItem[i];
    }
}
