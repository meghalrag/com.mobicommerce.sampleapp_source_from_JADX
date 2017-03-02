package com.paypal.android.sdk.payments;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.paypal.android.sdk.payments.T */
final class C0101T implements Creator {
    C0101T() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new PayPalOAuthScopes((byte) 0);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new PayPalOAuthScopes[i];
    }
}
