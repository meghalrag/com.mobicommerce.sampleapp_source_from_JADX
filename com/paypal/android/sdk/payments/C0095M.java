package com.paypal.android.sdk.payments;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.paypal.android.sdk.payments.M */
final class C0095M implements Creator {
    C0095M() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new PayPalAuthorization((byte) 0);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new PayPalAuthorization[i];
    }
}
