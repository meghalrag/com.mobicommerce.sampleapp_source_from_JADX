package com.paypal.android.sdk.payments;

import com.paypal.android.sdk.bO;

public enum PayPalScope {
    FUTURE_PAYMENTS(PayPalOAuthScopes.PAYPAL_SCOPE_FUTURE_PAYMENTS, bO.CONSENT_AGREEMENT_FUTURE_PAYMENTS, false),
    ACCOUNT_PROFILE(PayPalOAuthScopes.PAYPAL_SCOPE_PROFILE, bO.CONSENT_AGREEMENT_ATTRIBUTES, true),
    PAYPAL_ATTRIBUTES(PayPalOAuthScopes.PAYPAL_SCOPE_PAYPAL_ATTRIBUTES, bO.CONSENT_AGREEMENT_ATTRIBUTES, true),
    EMAIL(PayPalOAuthScopes.PAYPAL_SCOPE_EMAIL, bO.CONSENT_AGREEMENT_ATTRIBUTES, true),
    ADDRESS(PayPalOAuthScopes.PAYPAL_SCOPE_ADDRESS, bO.CONSENT_AGREEMENT_ATTRIBUTES, true),
    PHONE(PayPalOAuthScopes.PAYPAL_SCOPE_PHONE, bO.CONSENT_AGREEMENT_ATTRIBUTES, true);
    
    boolean f582a;
    private String f583b;
    private bO f584c;

    private PayPalScope(String str, bO bOVar, boolean z) {
        this.f583b = str;
        this.f584c = bOVar;
        this.f582a = z;
    }

    final String m457a() {
        return this.f583b;
    }

    final bO m458b() {
        return this.f584c;
    }
}
