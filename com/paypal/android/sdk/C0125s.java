package com.paypal.android.sdk;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import java.math.BigDecimal;
import java.util.Currency;

/* renamed from: com.paypal.android.sdk.s */
public class C0125s implements Parcelable {
    public static final Creator CREATOR;
    private static /* synthetic */ boolean f765d;
    private BigDecimal f766a;
    private Currency f767b;
    private long f768c;

    static {
        f765d = !C0125s.class.desiredAssertionStatus();
        CREATOR = new C0126t();
    }

    public C0125s(Parcel parcel) {
        this.f768c = parcel.readLong();
        this.f766a = new BigDecimal(parcel.readString());
        try {
            this.f767b = Currency.getInstance(parcel.readString());
        } catch (IllegalArgumentException e) {
            Log.e("MoneySpec", "Exception reading currency code from parcel, reset to default");
            C0078i c0078i = null;
            this.f767b = Currency.getInstance(c0078i.m349c());
        }
    }

    public C0125s(BigDecimal bigDecimal, String str) {
        Currency instance = Currency.getInstance(str);
        this.f768c = System.currentTimeMillis();
        this.f766a = bigDecimal;
        this.f767b = instance;
    }

    public final BigDecimal m694a() {
        return this.f766a;
    }

    public final Currency m695b() {
        return this.f767b;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (f765d || (obj instanceof C0125s)) {
            C0125s c0125s = (C0125s) obj;
            return c0125s.f766a == this.f766a && c0125s.f767b.equals(this.f767b);
        } else {
            throw new AssertionError();
        }
    }

    public String toString() {
        C0078i c0078i = null;
        return C0082m.m360a(c0078i.m349c(), this.f766a.doubleValue(), this.f767b);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f768c);
        parcel.writeString(this.f766a.toString());
        parcel.writeString(this.f767b.getCurrencyCode());
    }
}
