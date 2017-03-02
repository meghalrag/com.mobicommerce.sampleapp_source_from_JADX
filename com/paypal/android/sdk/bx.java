package com.paypal.android.sdk;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.Random;

public final class bx extends bw implements Parcelable {
    public static final Creator CREATOR;

    static {
        CREATOR = new by();
    }

    public bx() {
        this.b = System.currentTimeMillis() + 1800000;
        Random random = new Random(this.b);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            stringBuilder.append((char) ((Math.abs(random.nextInt()) % 10) + 48));
        }
        this.a = stringBuilder.toString();
    }

    public bx(Parcel parcel) {
        super(parcel);
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeLong(this.b);
    }
}
