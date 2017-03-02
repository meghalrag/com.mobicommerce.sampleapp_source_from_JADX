package com.paypal.android.sdk.payments;

import com.paypal.android.sdk.C0069T;
import com.paypal.android.sdk.C0072c;
import com.paypal.android.sdk.C0079j;
import com.paypal.android.sdk.bJ;
import com.paypal.android.sdk.bL;
import com.paypal.android.sdk.bM;
import java.util.Map;
import org.apache.cordova.networkinformation.NetworkManager;

/* renamed from: com.paypal.android.sdk.payments.j */
final class C0298j extends C0239h {
    public C0298j(PayPalService payPalService) {
        super(new aj(payPalService));
    }

    protected final void m1210a(String str, Map map) {
        if (!m1045a().m635a().f93b.m290c()) {
            m1045a().m635a().f93b = new bJ();
        }
        map.put("v49", str);
        map.put("v51", C0072c.m291a().m294c().m336d());
        map.put("v52", bL.f116a + " " + bL.f119d);
        map.put("v53", bL.f120e);
        map.put("clid", m1045a().m636b());
        map.put("apid", C0079j.m352b() + "|" + str + "|" + m1045a().m640f());
        C0069T c0069t = new C0069T(m1045a().m635a().f93b.m289b(), map, bL.f118c, bL.f117b, false);
        String str2 = null;
        if (!(m1045a().m635a() == null || m1045a().m635a().f94c == null)) {
            str2 = m1045a().m635a().f94c.m289b();
        }
        m1045a().m637c().m851a(c0069t, str2);
    }

    protected final void m1211a(Map map, bM bMVar, String str, String str2) {
        map.put("g", m1045a().m639e());
        map.put("vers", bL.f116a + ":" + m1045a().m638d() + ":");
        map.put("srce", "msdk");
        map.put("sv", NetworkManager.MOBILE);
        map.put("bchn", "msdk");
        map.put("adte", "FALSE");
        map.put("bzsr", NetworkManager.MOBILE);
        if (C0069T.m52d(str)) {
            map.put("calc", str);
        }
        if (C0069T.m52d(str2)) {
            map.put("prid", str2);
        }
        map.put("e", bMVar.m130b() ? "cl" : "im");
    }

    protected final String m1212b() {
        return "msdk";
    }
}
