package com.paypal.android.sdk;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ae extends ac {
    public String f1263a;
    public String f1264b;
    public long f1265c;
    public boolean f1266d;

    public ae(String str, C0065N c0065n, C0074e c0074e, C0078i c0078i, String str2) {
        super(C0071b.PreAuthRequest, c0065n, c0074e, c0078i, af.m934a(str, str2));
    }

    public final String m1242a() {
        Map hashMap = new HashMap();
        hashMap.put("response_type", "token");
        hashMap.put("grant_type", "client_credentials");
        hashMap.put("return_authn_schemes", "true");
        hashMap.put("device_info", C0069T.m48b(C0084o.m364a().toString()));
        hashMap.put("app_info", C0069T.m48b(C0079j.m351a().toString()));
        hashMap.put("risk_data", C0069T.m48b(be.m200a().m224b().toString()));
        return C0069T.m36a(hashMap);
    }

    public final void m1243b() {
        JSONObject A = m71A();
        try {
            this.f1263a = A.getString("access_token");
            A.getString("app_id");
            this.f1264b = A.getString("scope");
            this.f1265c = A.getLong("expires_in");
            JSONArray jSONArray = A.getJSONArray("supported_authn_schemes");
            for (int i = 0; i < jSONArray.length(); i++) {
                if (jSONArray.get(i).equals("phone_pin")) {
                    this.f1266d = true;
                }
            }
        } catch (JSONException e) {
            m936c(A);
        }
    }

    public final void m1244c() {
        m936c(m71A());
    }

    public final String m1245d() {
        return "{\"scope\":\"https://api.paypal.com/v1/payments/.* https://api.paypal.com/v1/vault/credit-card https://api.paypal.com/v1/vault/credit-card/.* openid\",\"access_token\":\"iPVzWUwTo1fEG6n.2sTZCahv8wa2b8uGpBs1KZ-6XxA\",\"token_type\":\"Bearer\",\"app_id\":\"APP-10NHR005R4826426K\",\"expires_in\":900,\"supported_authn_schemes\": [ \"email_password\", \"phone_pin\" ]}";
    }
}
