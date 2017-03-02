package com.paypal.android.sdk;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import io.card.payment.CardType;
import java.util.Date;

public final class bH extends bG implements Parcelable {
    public static final Creator CREATOR;
    private String f1047b;
    private Date f1048c;
    private String f1049d;
    private CardType f1050e;
    private int f1051f;
    private int f1052g;

    static {
        CREATOR = new bI();
    }

    private bH(Parcel parcel) {
        this.a = parcel.readString();
        this.f1047b = parcel.readString();
        this.f1049d = parcel.readString();
        this.f1048c = (Date) parcel.readSerializable();
        this.f1050e = (CardType) parcel.readSerializable();
        this.f1051f = parcel.readInt();
        this.f1052g = parcel.readInt();
    }

    public bH(String str, String str2, String str3, String str4, String str5, int i, int i2) {
        this.a = str2;
        this.f1047b = str;
        this.f1048c = C0130x.m708a(str3);
        m956c(str4);
        m957d(str5);
        this.f1051f = i;
        this.f1052g = i2;
    }

    public bH(String str, String str2, Date date, String str3, String str4, int i, int i2) {
        this.a = m126a(str2);
        this.f1047b = str;
        this.f1048c = date;
        m956c(str3);
        m957d(str4);
        this.f1051f = i;
        this.f1052g = i2;
    }

    public static String m955b(String str) {
        return str == null ? null : "x-" + str.substring(str.length() - 4);
    }

    private void m956c(String str) {
        if (str != null) {
            this.f1049d = str.substring(str.length() - 4);
        } else {
            this.f1049d = null;
        }
    }

    private void m957d(String str) {
        this.f1050e = CardType.fromString(str);
    }

    public final boolean m958c() {
        return (C0069T.m46a(this.f1047b) || C0069T.m46a(this.f1049d) || C0069T.m46a(this.a) || this.f1048c == null || this.f1048c.before(new Date()) || this.f1050e == null || this.f1050e == CardType.UNKNOWN || this.f1051f <= 0 || this.f1051f > 12 || this.f1052g < 0 || this.f1052g > 9999) ? false : true;
    }

    public final Date m959d() {
        return this.f1048c;
    }

    public final int describeContents() {
        return 0;
    }

    public final String m960e() {
        return m955b(this.f1049d);
    }

    public final String m961f() {
        return this.f1047b;
    }

    public final CardType m962g() {
        return this.f1050e;
    }

    public final int m963h() {
        return this.f1051f;
    }

    public final int m964i() {
        return this.f1052g;
    }

    public final String toString() {
        return "TokenizedCreditCard(token=" + this.f1047b + ",lastFourDigits=" + this.f1049d + ",payerId=" + this.a + ",tokenValidUntil=" + this.f1048c + ",cardType=" + this.f1050e + ",expiryMonth/year=" + this.f1051f + "/" + this.f1052g + ")";
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.f1047b);
        parcel.writeString(this.f1049d);
        parcel.writeSerializable(this.f1048c);
        parcel.writeSerializable(this.f1050e);
        parcel.writeInt(this.f1051f);
        parcel.writeInt(this.f1052g);
    }
}
