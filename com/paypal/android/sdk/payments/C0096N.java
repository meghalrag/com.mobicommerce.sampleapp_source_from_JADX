package com.paypal.android.sdk.payments;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.paypal.android.sdk.payments.N */
final class C0096N implements Creator {
    C0096N() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new PayPalConfiguration((byte) 0);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new PayPalConfiguration[i];
    }
}
