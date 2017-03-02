package com.paypal.android.sdk;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.telephony.TelephonyManager;
import com.paypal.android.sdk.payments.PayPalOAuthScopes;
import java.util.UUID;

/* renamed from: com.paypal.android.sdk.d */
public class C0073d {
    private static final String f457a;
    private Context f458b;
    private String f459c;

    static {
        f457a = C0073d.class.getSimpleName();
    }

    public C0073d(Context context, String str) {
        this.f458b = context;
        this.f459c = str;
    }

    public final String m331a(String str, String str2) {
        return this.f458b.getSharedPreferences(this.f459c, 0).getString(str, str2);
    }

    public final boolean m332a() {
        int i = 0;
        for (NetworkInfo state : ((ConnectivityManager) this.f458b.getSystemService("connectivity")).getAllNetworkInfo()) {
            State state2 = state.getState();
            if (state2 == State.CONNECTED || state2 == State.CONNECTING) {
                i++;
            }
        }
        if (i == 0) {
            String str = f457a;
        }
        return i > 0;
    }

    public final int m333b() {
        return ((TelephonyManager) this.f458b.getSystemService(PayPalOAuthScopes.PAYPAL_SCOPE_PHONE)).getPhoneType();
    }

    public final void m334b(String str, String str2) {
        Editor edit = this.f458b.getSharedPreferences(this.f459c, 0).edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public final String m335c() {
        try {
            PackageManager packageManager = this.f458b.getPackageManager();
            return packageManager.getPackageInfo(this.f458b.getPackageName(), 0).applicationInfo.loadLabel(packageManager).toString();
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public final String m336d() {
        try {
            return ((TelephonyManager) this.f458b.getSystemService(PayPalOAuthScopes.PAYPAL_SCOPE_PHONE)).getSimOperatorName();
        } catch (SecurityException e) {
            String str = f457a;
            e.toString();
            return null;
        }
    }

    public final String m337e() {
        String a = m331a("InstallationGUID", null);
        if (a != null) {
            return a;
        }
        m334b("InstallationGUID", UUID.randomUUID().toString());
        return m331a("InstallationGUID", null);
    }
}
