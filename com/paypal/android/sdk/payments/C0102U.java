package com.paypal.android.sdk.payments;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.paypal.android.sdk.payments.U */
final class C0102U implements Creator {
    C0102U() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new PayPalPayment((byte) 0);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new PayPalPayment[i];
    }
}
