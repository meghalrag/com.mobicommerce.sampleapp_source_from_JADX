package com.paypal.android.sdk.payments;

import com.paypal.android.sdk.bN;

/* renamed from: com.paypal.android.sdk.payments.E */
final class C0234E implements ae {
    private /* synthetic */ LoginActivity f1082a;

    C0234E(LoginActivity loginActivity) {
        this.f1082a = loginActivity;
    }

    public final void m997a() {
        this.f1082a.m395d();
    }

    public final void m998a(ag agVar) {
        LoginActivity.m390b(this.f1082a);
        if (agVar.m633a() && agVar.f703b.equals("invalid_request")) {
            this.f1082a.f510a;
            this.f1082a.f523n.m494a(this.f1082a.m403i(), true);
        } else if (agVar.m633a() && agVar.f703b.equals("2fa_required")) {
            C0108b.m653a(this.f1082a, bN.m132a(agVar.f703b), 3);
        } else {
            LoginActivity.m389a(this.f1082a, agVar.f703b);
        }
    }
}
