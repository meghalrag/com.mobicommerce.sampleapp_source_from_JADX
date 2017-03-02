package com.paypal.android.sdk.payments;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class bf implements Creator {
    bf() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new ProofOfPayment((byte) 0);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new ProofOfPayment[i];
    }
}
