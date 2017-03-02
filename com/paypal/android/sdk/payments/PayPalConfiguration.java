package com.paypal.android.sdk.payments;

import android.app.Activity;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.paypal.android.sdk.C0069T;

public final class PayPalConfiguration implements Parcelable {
    public static final Creator CREATOR;
    public static final String ENVIRONMENT_NO_NETWORK = "mock";
    public static final String ENVIRONMENT_PRODUCTION = "live";
    public static final String ENVIRONMENT_SANDBOX = "sandbox";
    private static final String f531a;
    private String f532b;
    private String f533c;
    private String f534d;
    private String f535e;
    private String f536f;
    private boolean f537g;
    private String f538h;
    private String f539i;
    private boolean f540j;
    private String f541k;
    private String f542l;
    private Uri f543m;
    private Uri f544n;
    private boolean f545o;

    static {
        f531a = PayPalConfiguration.class.getSimpleName();
        CREATOR = new C0096N();
    }

    public PayPalConfiguration() {
        this.f540j = true;
        this.f545o = true;
    }

    private PayPalConfiguration(Parcel parcel) {
        boolean z = true;
        this.f540j = true;
        this.f545o = true;
        this.f533c = parcel.readString();
        this.f532b = parcel.readString();
        this.f534d = parcel.readString();
        this.f535e = parcel.readString();
        this.f536f = parcel.readString();
        this.f537g = parcel.readByte() == (byte) 1;
        this.f538h = parcel.readString();
        this.f539i = parcel.readString();
        this.f540j = parcel.readByte() == (byte) 1;
        this.f541k = parcel.readString();
        this.f542l = parcel.readString();
        this.f543m = (Uri) parcel.readParcelable(null);
        this.f544n = (Uri) parcel.readParcelable(null);
        if (parcel.readByte() != (byte) 1) {
            z = false;
        }
        this.f545o = z;
    }

    private static void m406a(boolean z, String str) {
        if (!z) {
            Log.e(f531a, str + " is invalid.  Please see the docs.");
        }
    }

    public static final String getApplicationCorrelationId(Activity activity) {
        return bg.m659a(activity);
    }

    public static final String getLibraryVersion() {
        return Version.PRODUCT_VERSION;
    }

    final String m407a() {
        return this.f532b;
    }

    public final PayPalConfiguration acceptCreditCards(boolean z) {
        this.f540j = z;
        return this;
    }

    final String m408b() {
        if (C0069T.m46a(this.f533c)) {
            this.f533c = ENVIRONMENT_PRODUCTION;
            Log.w(f531a, "defaulting to production environment");
        }
        return this.f533c;
    }

    final String m409c() {
        return this.f534d;
    }

    public final PayPalConfiguration clientId(String str) {
        this.f541k = str;
        return this;
    }

    final String m410d() {
        return this.f535e;
    }

    public final PayPalConfiguration defaultUserEmail(String str) {
        this.f534d = str;
        return this;
    }

    public final PayPalConfiguration defaultUserPhone(String str) {
        this.f535e = str;
        return this;
    }

    public final PayPalConfiguration defaultUserPhoneCountryCode(String str) {
        this.f536f = str;
        return this;
    }

    public final int describeContents() {
        return 0;
    }

    final String m411e() {
        return this.f536f;
    }

    public final PayPalConfiguration environment(String str) {
        this.f533c = str;
        return this;
    }

    final boolean m412f() {
        return this.f537g;
    }

    public final PayPalConfiguration forceDefaultsOnSandbox(boolean z) {
        this.f537g = z;
        return this;
    }

    final String m413g() {
        return this.f538h;
    }

    final String m414h() {
        return this.f539i;
    }

    final boolean m415i() {
        return this.f540j;
    }

    final boolean m416j() {
        return this.f545o;
    }

    final String m417k() {
        return this.f541k;
    }

    final String m418l() {
        return this.f542l;
    }

    public final PayPalConfiguration languageOrLocale(String str) {
        this.f532b = str;
        return this;
    }

    final Uri m419m() {
        return this.f543m;
    }

    public final PayPalConfiguration merchantName(String str) {
        this.f542l = str;
        return this;
    }

    public final PayPalConfiguration merchantPrivacyPolicyUri(Uri uri) {
        this.f543m = uri;
        return this;
    }

    public final PayPalConfiguration merchantUserAgreementUri(Uri uri) {
        this.f544n = uri;
        return this;
    }

    final Uri m420n() {
        return this.f544n;
    }

    final boolean m421o() {
        boolean z;
        boolean a = C0108b.m656a(f531a, m408b(), "environment");
        m406a(a, "environment");
        if (!a) {
            z = false;
        } else if (m408b().equals(ENVIRONMENT_NO_NETWORK)) {
            z = true;
        } else {
            z = C0108b.m656a(f531a, this.f541k, "clientId");
            m406a(z, "clientId");
        }
        return a && z;
    }

    public final PayPalConfiguration rememberUser(boolean z) {
        this.f545o = z;
        return this;
    }

    public final PayPalConfiguration sandboxUserPassword(String str) {
        this.f538h = str;
        return this;
    }

    public final PayPalConfiguration sandboxUserPin(String str) {
        this.f539i = str;
        return this;
    }

    public final String toString() {
        return String.format(PayPalConfiguration.class.getSimpleName() + ": {environment:%s: client_id:%s: languageOrLocale:%s}", new Object[]{this.f533c, this.f541k, this.f532b});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = 1;
        parcel.writeString(this.f533c);
        parcel.writeString(this.f532b);
        parcel.writeString(this.f534d);
        parcel.writeString(this.f535e);
        parcel.writeString(this.f536f);
        parcel.writeByte((byte) (this.f537g ? 1 : 0));
        parcel.writeString(this.f538h);
        parcel.writeString(this.f539i);
        parcel.writeByte((byte) (this.f540j ? 1 : 0));
        parcel.writeString(this.f541k);
        parcel.writeString(this.f542l);
        parcel.writeParcelable(this.f543m, 0);
        parcel.writeParcelable(this.f544n, 0);
        if (!this.f545o) {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
    }
}
