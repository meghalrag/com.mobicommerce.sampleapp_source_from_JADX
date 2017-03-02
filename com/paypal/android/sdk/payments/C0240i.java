package com.paypal.android.sdk.payments;

import com.paypal.android.sdk.bM;

/* renamed from: com.paypal.android.sdk.payments.i */
final class C0240i implements C0114l {
    private C0299k f1100a;
    private C0298j f1101b;

    public C0240i(PayPalService payPalService) {
        this.f1100a = new C0299k(payPalService);
        this.f1101b = new C0298j(payPalService);
    }

    public final void m1050a(bM bMVar, boolean z, String str, String str2, String str3) {
        this.f1100a.m1046a(bMVar, z, str, str2, null);
        this.f1101b.m1046a(bMVar, z, str, str2, str3);
    }
}
