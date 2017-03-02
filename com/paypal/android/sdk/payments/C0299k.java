package com.paypal.android.sdk.payments;

import com.paypal.android.sdk.C0069T;
import com.paypal.android.sdk.C0072c;
import com.paypal.android.sdk.bL;
import com.paypal.android.sdk.bx;
import java.util.Map;

/* renamed from: com.paypal.android.sdk.payments.k */
final class C0299k extends C0239h {
    public C0299k(PayPalService payPalService) {
        super(new aj(payPalService));
    }

    protected final void m1213a(String str, Map map) {
        if (!m1045a().m635a().f92a.m290c()) {
            m1045a().m635a().f92a = new bx();
            map.put("v49", str);
            map.put("v51", C0072c.m291a().m294c().m336d());
            map.put("v52", bL.f116a + " " + bL.f119d);
            map.put("v53", bL.f120e);
        }
        m1045a().m637c().m851a(new C0069T(m1045a().m635a().f92a.m289b(), map, bL.f118c, bL.f117b, true), null);
    }

    protected final String m1214b() {
        return "mpl";
    }
}
