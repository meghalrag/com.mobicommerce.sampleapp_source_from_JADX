package com.paypal.android.sdk.payments;

import android.content.Intent;
import com.paypal.android.sdk.C0069T;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

final class aa extends C0116o {
    private boolean f1091a;
    private Set f1092b;

    aa(Intent intent, PayPalConfiguration payPalConfiguration, boolean z) {
        super(intent, payPalConfiguration);
        this.f1092b = new HashSet(Arrays.asList(new String[]{PayPalOAuthScopes.PAYPAL_SCOPE_FUTURE_PAYMENTS, PayPalOAuthScopes.PAYPAL_SCOPE_ADDRESS, PayPalOAuthScopes.PAYPAL_SCOPE_EMAIL, PayPalOAuthScopes.PAYPAL_SCOPE_PAYPAL_ATTRIBUTES, PayPalOAuthScopes.PAYPAL_SCOPE_PHONE, PayPalOAuthScopes.PAYPAL_SCOPE_PROFILE}));
        this.f1091a = z;
    }

    private static boolean m1011a(String str) {
        try {
            URL url = new URL(str);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    protected final String m1012a() {
        return PayPalFuturePaymentActivity.class.getSimpleName();
    }

    final boolean m1013e() {
        boolean d = C0069T.m52d(m686c().m418l());
        m684a(d, "merchantName");
        boolean z = m686c().m419m() != null && C0108b.m656a(PayPalFuturePaymentActivity.class.getSimpleName(), m686c().m419m().toString(), "merchantPrivacyPolicyUrl") && m1011a(m686c().m419m().toString());
        m684a(z, "merchantPrivacyPolicyUrl");
        boolean z2 = m686c().m420n() != null && C0108b.m656a(PayPalFuturePaymentActivity.class.getSimpleName(), m686c().m420n().toString(), "merchantUserAgreementUrl") && m1011a(m686c().m420n().toString());
        m684a(z2, "merchantUserAgreementUrl");
        boolean z3 = !this.f1091a;
        if (this.f1091a) {
            PayPalOAuthScopes payPalOAuthScopes = (PayPalOAuthScopes) m685b().getParcelableExtra(PayPalProfileSharingActivity.EXTRA_REQUESTED_SCOPES);
            if (payPalOAuthScopes == null) {
                z3 = false;
            } else if (payPalOAuthScopes.m431a() == null || payPalOAuthScopes.m431a().size() <= 0) {
                z3 = false;
            } else {
                for (String contains : payPalOAuthScopes.m431a()) {
                    if (!this.f1092b.contains(contains)) {
                        z3 = false;
                        break;
                    }
                }
                z3 = true;
            }
        }
        m684a(z3, "paypalScopes");
        return d && z && z2 && z3;
    }
}
