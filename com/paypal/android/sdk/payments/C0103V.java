package com.paypal.android.sdk.payments;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.paypal.android.sdk.payments.V */
final class C0103V implements Creator {
    C0103V() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new PayPalPaymentDetails((byte) 0);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new PayPalPaymentDetails[i];
    }
}
