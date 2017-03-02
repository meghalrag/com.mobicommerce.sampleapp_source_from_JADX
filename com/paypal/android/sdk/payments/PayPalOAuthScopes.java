package com.paypal.android.sdk.payments;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class PayPalOAuthScopes implements Parcelable {
    public static final Creator CREATOR;
    public static final String PAYPAL_SCOPE_ADDRESS = "address";
    public static final String PAYPAL_SCOPE_EMAIL = "email";
    public static final String PAYPAL_SCOPE_FUTURE_PAYMENTS = "https://uri.paypal.com/services/payments/futurepayments";
    public static final String PAYPAL_SCOPE_PAYPAL_ATTRIBUTES = "https://uri.paypal.com/services/paypalattributes";
    public static final String PAYPAL_SCOPE_PHONE = "phone";
    public static final String PAYPAL_SCOPE_PROFILE = "profile";
    private List f557a;

    static {
        CREATOR = new C0101T();
    }

    public PayPalOAuthScopes() {
        this.f557a = new ArrayList();
    }

    private PayPalOAuthScopes(Parcel parcel) {
        this.f557a = new ArrayList();
        int readInt = parcel.readInt();
        for (int i = 0; i < readInt; i++) {
            this.f557a.add(parcel.readString());
        }
    }

    public PayPalOAuthScopes(Set set) {
        this();
        for (String add : set) {
            this.f557a.add(add);
        }
    }

    final List m431a() {
        return this.f557a;
    }

    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return String.format(PayPalOAuthScopes.class.getSimpleName() + ": {%s}", new Object[]{this.f557a});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f557a.size());
        for (int i2 = 0; i2 < this.f557a.size(); i2++) {
            parcel.writeString((String) this.f557a.get(i2));
        }
    }
}
