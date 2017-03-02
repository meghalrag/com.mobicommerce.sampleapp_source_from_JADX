package com.paypal.android.sdk.payments;

import com.paypal.android.sdk.bN;
import com.paypal.android.sdk.bO;

/* renamed from: com.paypal.android.sdk.payments.p */
final class C0242p implements ac {
    private /* synthetic */ FuturePaymentConsentActivity f1102a;

    C0242p(FuturePaymentConsentActivity futurePaymentConsentActivity) {
        this.f1102a = futurePaymentConsentActivity;
    }

    public final void m1055a(ag agVar) {
        this.f1102a.f499f.f208h.setEnabled(true);
        this.f1102a.dismissDialog(2);
        if (agVar.f703b.equals("invalid_request")) {
            C0108b.m653a(this.f1102a, bN.m131a(bO.SESSION_EXPIRED_MESSAGE), 4);
        } else {
            C0108b.m653a(this.f1102a, bN.m132a(agVar.f703b), 1);
        }
    }

    public final void m1056a(Object obj) {
        this.f1102a.m369a(-1, new PayPalAuthorization(this.f1102a.f500g.m504d(), (String) obj, this.f1102a.f500g.m502b().f95d));
        this.f1102a.finish();
    }
}
