package com.paypal.android.sdk.payments;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.paypal.android.sdk.payments.L */
final class C0094L implements Creator {
    C0094L() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return (C0093K) parcel.readSerializable();
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new C0093K[i];
    }
}
