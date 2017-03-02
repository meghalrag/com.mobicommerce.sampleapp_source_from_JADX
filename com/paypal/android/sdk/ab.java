package com.paypal.android.sdk;

import android.os.Build;
import com.paypal.android.sdk.payments.PayPalOAuthScopes;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class ab extends ac {
    public final C0123q f1242a;
    public final boolean f1243b;
    public String f1244c;
    public String f1245d;
    public String f1246e;
    public String f1247f;
    public long f1248g;
    private String f1249h;
    private final boolean f1250i;
    private final String f1251j;

    public ab(C0065N c0065n, C0074e c0074e, String str, String str2, C0078i c0078i, C0123q c0123q, boolean z, String str3, boolean z2, String str4) {
        super(C0071b.LoginRequest, c0065n, c0074e, c0078i, af.m934a(str, str2));
        this.f1242a = c0123q;
        this.f1243b = z;
        this.f1249h = str3;
        this.f1250i = z2;
        this.f1251j = str4;
    }

    public final String m1224a() {
        Map hashMap = new HashMap();
        hashMap.put("grant_type", "password");
        hashMap.put("response_type", this.f1249h);
        if (this.f1249h != null && this.f1249h.equals("token")) {
            hashMap.put("scope_consent_context", "access_token");
        }
        if (!C0069T.m51c(this.f1251j)) {
            hashMap.put("scope", this.f1251j);
        }
        hashMap.put("risk_data", C0069T.m48b(be.m200a().m224b().toString()));
        if (this.f1242a.m689a()) {
            hashMap.put(PayPalOAuthScopes.PAYPAL_SCOPE_EMAIL, C0069T.m48b(this.f1242a.m690b()));
            hashMap.put("password", C0069T.m48b(this.f1242a.m691c()));
        } else {
            this.f1242a.m692d().m707c();
            hashMap.put(PayPalOAuthScopes.PAYPAL_SCOPE_PHONE, C0069T.m48b("+" + this.f1242a.m692d().m707c() + " " + this.f1242a.m692d().m704a()));
            hashMap.put("pin", this.f1242a.m693e());
        }
        hashMap.put("remember_me", "true");
        hashMap.put("device_name", C0069T.m48b(Build.DEVICE));
        hashMap.put("redirect_uri", C0069T.m48b("urn:ietf:wg:oauth:2.0:oob"));
        return C0069T.m36a(hashMap);
    }

    public final void m1225b() {
        JSONObject A = m71A();
        try {
            this.f1245d = A.getString("nonce");
            this.f1247f = A.getString("scope");
            if (this.f1250i) {
                this.f1244c = A.getString("code");
                return;
            }
            this.f1246e = A.getString("access_token");
            this.f1248g = A.getLong("expires_in");
        } catch (JSONException e) {
            m936c(A);
        }
    }

    public final void m1226c() {
        m936c(m71A());
    }

    public final String m1227d() {
        return "{ \"access_token\": \"mock_access_token\", \"code\": \"EJhi9jOPswug9TDOv93qg4Y28xIlqPDpAoqd7biDLpeGCPvORHjP1Fh4CbFPgKMGCHejdDwe9w1uDWnjPCp1lkaFBjVmjvjpFtnr6z1YeBbmfZYqa9faQT_71dmgZhMIFVkbi4yO7hk0LBHXt_wtdsw\", \"nonce\": \"2013-09-17T21:52:45ZLGVU-xDKZfHnlNZVtyUE2w\", \"scope\": \"https://api.paypal.com/v1/payments/.* https://uri.paypal.com/services/payments/futurepayments\", \"token_type\": \"Bearer\",\"expires_in\":28800,\"visitor_id\":\"zVxjDBTRRNfYXdOb19-lcTblxm-6bzXGvSlP76ZiHOudKaAvoxrW8Cg5pA6EjIPpiz4zlw\" }";
    }
}
