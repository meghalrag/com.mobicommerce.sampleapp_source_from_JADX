package com.paypal.android.sdk;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class aZ {
    private static final String f38a;

    static {
        f38a = aZ.class.getSimpleName();
    }

    public static boolean m58a(Context context, boolean z) {
        return m59a(context, z, "com.paypal.android.p2pmobile.Sdk", "com.paypal.android.lib.authenticator.activity.SdkActivity");
    }

    private static boolean m59a(Context context, boolean z, String str, String str2) {
        String str3;
        boolean z2 = false;
        PackageManager packageManager = context.getPackageManager();
        try {
            packageManager.getApplicationInfo("com.paypal.android.p2pmobile", 4224);
            if (packageManager.checkPermission("android.permission.AUTHENTICATE_ACCOUNTS", "com.paypal.android.p2pmobile") == 0) {
                if (packageManager.queryIntentActivities(aW.m56a(str, str2), 0).size() > 0) {
                    if (z) {
                        if (!m60a(packageManager)) {
                            str3 = f38a;
                        }
                    }
                    z2 = true;
                } else {
                    str3 = f38a;
                    new StringBuilder("PayPal wallet app will not accept intent to: [action:").append(str).append(", class:").append(str2).append("]");
                }
            } else {
                str3 = f38a;
            }
        } catch (NameNotFoundException e) {
            Log.e(f38a, "com.paypal.android.p2pmobile not found.");
        }
        str3 = f38a;
        new StringBuilder("returning isValid:").append(z2);
        return z2;
    }

    private static boolean m60a(PackageManager packageManager) {
        Signature[] signatureArr = packageManager.getPackageInfo("com.paypal.android.p2pmobile", 64).signatures;
        int length = signatureArr.length;
        int i = 0;
        while (i < length) {
            try {
                X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(signatureArr[i].toByteArray()));
                String name = x509Certificate.getSubjectX500Principal().getName();
                String name2 = x509Certificate.getIssuerX500Principal().getName();
                int hashCode = x509Certificate.getPublicKey().hashCode();
                String str = f38a;
                new StringBuilder("Certificate subject: ").append(name);
                str = f38a;
                new StringBuilder("Certificate issuer: ").append(name2);
                str = f38a;
                new StringBuilder("Certificate public key hash code: ").append(hashCode);
                boolean z = "O=Paypal".equals(name) && "O=Paypal".equals(name2) && 34172764 == hashCode;
                if (z) {
                    return z;
                }
                Log.e(f38a, "Authenticator cert is NOT valid.");
                return z;
            } catch (Throwable e) {
                Log.e(f38a, "exception parsing cert", e);
                i++;
            }
        }
        return false;
    }
}
