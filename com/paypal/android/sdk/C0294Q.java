package com.paypal.android.sdk;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.cordova.networkinformation.NetworkManager;
import org.json.JSONObject;

/* renamed from: com.paypal.android.sdk.Q */
public final class C0294Q extends af {
    private static Map f1201b;
    private static Set f1202c;
    private final C0069T f1203a;

    static {
        Map hashMap = new HashMap();
        f1201b = hashMap;
        hashMap.put("c14", "erpg");
        f1201b.put("c25", "page");
        f1201b.put("c26", "link");
        f1201b.put("c27", "pgln");
        f1201b.put("c29", "eccd");
        f1201b.put("c35", "lgin");
        f1201b.put("vers", "vers");
        f1201b.put("c50", "rsta");
        f1201b.put("gn", "pgrp");
        f1201b.put("v49", "mapv");
        f1201b.put("v51", "mcar");
        f1201b.put("v52", "mosv");
        f1201b.put("v53", "mdvs");
        f1201b.put("clid", "clid");
        f1201b.put("apid", "apid");
        f1201b.put("calc", "calc");
        f1201b.put("e", "e");
        f1201b.put("t", "t");
        f1201b.put("g", "g");
        f1201b.put("srce", "srce");
        f1201b.put("vid", "vid");
        f1201b.put("bchn", "bchn");
        f1201b.put("adte", "adte");
        f1201b.put("sv", "sv");
        f1201b.put("dsid", "dsid");
        f1201b.put("bzsr", "bzsr");
        f1201b.put("prid", "prid");
        Set hashSet = new HashSet();
        f1202c = hashSet;
        hashSet.add("v25");
        f1202c.add("v31");
        f1202c.add("c37");
    }

    public C0294Q(C0065N c0065n, C0074e c0074e, C0078i c0078i, C0069T c0069t, String str) {
        super(C0071b.FptiRequest, c0065n, c0074e, c0078i, str);
        this.f1203a = c0069t;
        m83b("Accept", "application/json; charset=utf-8");
        m83b("Accept-Language", "en_US");
        m83b("Content-Type", "application/json");
    }

    private JSONObject m1166a(Map map) {
        JSONObject jSONObject = new JSONObject();
        for (String str : map.keySet()) {
            String str2;
            if (!C0069T.m51c(str)) {
                if (f1202c.contains(str)) {
                    new StringBuilder("SC key ").append(str).append(" not used in FPTI, skipping");
                } else if (f1201b.containsKey(str)) {
                    str2 = (String) f1201b.get(str);
                    if (str2 != null) {
                        jSONObject.accumulate(str2, map.get(str));
                    }
                } else {
                    new StringBuilder("No mapping for SC key ").append(str).append(", skipping");
                }
            }
            str2 = null;
            if (str2 != null) {
                jSONObject.accumulate(str2, map.get(str));
            }
        }
        return jSONObject;
    }

    public final String m1167a() {
        String b = C0069T.m48b(C0072c.m291a().m294c().m337e());
        String str = this.f1203a.f18a;
        JSONObject jSONObject = new JSONObject();
        jSONObject.accumulate("tracking_visitor_id", b);
        jSONObject.accumulate("tracking_visit_id", str);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.accumulate("actor", jSONObject);
        jSONObject2.accumulate("channel", NetworkManager.MOBILE);
        String l = Long.toString(System.currentTimeMillis());
        jSONObject2.accumulate("tracking_event", l);
        this.f1203a.f19b.put("t", l);
        this.f1203a.f19b.put("dsid", b);
        this.f1203a.f19b.put("vid", str);
        jSONObject2.accumulate("event_params", m1166a(this.f1203a.f19b));
        return jSONObject2.toString();
    }

    public final String m1168a(C0071b c0071b) {
        return "https://api.paypal.com/v1/tracking/events";
    }

    public final void m1169b() {
    }

    public final void m1170c() {
    }

    public final String m1171d() {
        return "mockFptiResponse";
    }
}
