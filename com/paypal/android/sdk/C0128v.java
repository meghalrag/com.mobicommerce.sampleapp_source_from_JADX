package com.paypal.android.sdk;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.telephony.PhoneNumberUtils;
import com.paypal.android.sdk.payments.BuildConfig;
import java.util.HashMap;
import java.util.Locale;

/* renamed from: com.paypal.android.sdk.v */
public class C0128v implements Parcelable {
    public static final Creator CREATOR;
    private static HashMap f776c;
    private C0080k f777a;
    private String f778b;

    static {
        CREATOR = new C0129w();
        HashMap hashMap = new HashMap();
        f776c = hashMap;
        hashMap.put("US", "1");
        f776c.put("CA", "1");
        f776c.put("GB", "44");
        f776c.put("FR", "33");
        f776c.put("IT", "39");
        f776c.put("ES", "34");
        f776c.put("AU", "61");
        f776c.put("MY", "60");
        f776c.put("SG", "65");
        f776c.put("AR", "54");
        f776c.put("UK", "44");
        f776c.put("ZA", "27");
        f776c.put("GR", "30");
        f776c.put("NL", "31");
        f776c.put("BE", "32");
        f776c.put("SG", "65");
        f776c.put("PT", "351");
        f776c.put("LU", "352");
        f776c.put("IE", "353");
        f776c.put("IS", "354");
        f776c.put("MT", "356");
        f776c.put("CY", "357");
        f776c.put("FI", "358");
        f776c.put("HU", "36");
        f776c.put("LT", "370");
        f776c.put("LV", "371");
        f776c.put("EE", "372");
        f776c.put("SI", "386");
        f776c.put("CH", "41");
        f776c.put("CZ", "420");
        f776c.put("SK", "421");
        f776c.put("AT", "43");
        f776c.put("DK", "45");
        f776c.put("SE", "46");
        f776c.put("NO", "47");
        f776c.put("PL", "48");
        f776c.put("DE", "49");
        f776c.put("MX", "52");
        f776c.put("BR", "55");
        f776c.put("NZ", "64");
        f776c.put("TH", "66");
        f776c.put("JP", "81");
        f776c.put("KR", "82");
        f776c.put("HK", "852");
        f776c.put("CN", "86");
        f776c.put("TW", "886");
        f776c.put("TR", "90");
        f776c.put("IN", "91");
        f776c.put("IL", "972");
        f776c.put("MC", "377");
        f776c.put("CR", "506");
        f776c.put("CL", "56");
        f776c.put("VE", "58");
        f776c.put("EC", "593");
        f776c.put("UY", "598");
    }

    public C0128v(Parcel parcel) {
        this.f777a = (C0080k) parcel.readParcelable(C0080k.class.getClassLoader());
        this.f778b = parcel.readString();
    }

    public C0128v(C0078i c0078i, C0080k c0080k, String str) {
        m703a(c0080k, c0078i.m347a(C0127u.m700e(str)));
    }

    public C0128v(C0078i c0078i, String str) {
        m703a(c0078i.m346a(), c0078i.m347a(C0127u.m700e(str)));
    }

    public static C0128v m702a(C0078i c0078i, String str) {
        String[] split = str.split("[|]");
        if (split.length == 2) {
            return new C0128v(c0078i, new C0080k(split[0]), split[1]);
        }
        throw new C0132z(BuildConfig.VERSION_NAME);
    }

    private void m703a(C0080k c0080k, String str) {
        this.f777a = c0080k;
        this.f778b = str;
    }

    public final String m704a() {
        return this.f778b;
    }

    public final String m705a(C0078i c0078i) {
        return c0078i.m349c().equals(Locale.US) ? PhoneNumberUtils.formatNumber(this.f778b) : this.f778b;
    }

    public final String m706b() {
        return this.f777a.m354a() + "|" + this.f778b;
    }

    public final String m707c() {
        return (String) f776c.get(this.f777a.m354a());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f777a, 0);
        parcel.writeString(this.f778b);
    }
}
