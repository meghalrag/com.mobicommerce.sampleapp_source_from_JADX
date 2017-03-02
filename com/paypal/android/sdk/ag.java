package com.paypal.android.sdk;

import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public final class ag extends C0296V {
    public String f1267a;
    private String f1268b;
    private String f1269c;
    private String f1270d;
    private int f1271e;
    private int f1272f;
    private String f1273g;
    private String f1274h;
    private Date f1275i;

    public ag(C0065N c0065n, C0074e c0074e, C0078i c0078i, String str, String str2, String str3, String str4, String str5, int i, int i2) {
        super(C0071b.TokenizeCreditCardRequest, c0065n, c0074e, c0078i, str);
        this.f1267a = str2;
        this.f1268b = str3;
        if (str4 == null) {
            throw new RuntimeException("cardNumber should not be null.  If it is, then you're probably trying to tokenize a card that's already been tokenized.");
        }
        this.f1269c = str4;
        this.f1270d = str5;
        this.f1271e = i;
        this.f1272f = i2;
    }

    public final String m1246a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.accumulate("payer_id", this.f1267a);
        jSONObject.accumulate("cvv2", this.f1270d);
        jSONObject.accumulate("expire_month", Integer.valueOf(this.f1271e));
        jSONObject.accumulate("expire_year", Integer.valueOf(this.f1272f));
        jSONObject.accumulate("number", this.f1269c);
        jSONObject.accumulate("type", this.f1268b);
        return jSONObject.toString();
    }

    public final void m1247b() {
        JSONObject A = m71A();
        try {
            this.f1273g = A.getString("id");
            String string = A.getString("number");
            if (this.f1274h == null || !this.f1274h.endsWith(string.substring(string.length() - 4))) {
                this.f1274h = string;
            }
            this.f1275i = C0130x.m708a(A.getString("valid_until"));
        } catch (JSONException e) {
            m1248c();
        }
    }

    public final void m1248c() {
        m935b(m71A());
    }

    public final String m1249d() {
        return "{\"id\":\"CARD-50Y58962PH1899901KFFBSDA\",\"valid_until\":\"2016-03-19T00:00:00.000Z\",\"state\":\"ok\",\"type\":\"visa\",\"number\":\"xxxxxxxxxxxx" + this.f1269c.substring(this.f1269c.length() - 4) + "\",\"expire_month\":\"" + this.f1271e + "\",\"expire_year\":\"" + this.f1272f + "\",\"links\":[" + "{\"href\":\"https://api.sandbox.paypal.com/v1/vault/credit-card/CARD-50Y58962PH1899901KFFBSDA\"," + "\"rel\":\"self\",\"method\":\"GET\"" + "}]" + "}";
    }

    public final String m1250g() {
        return this.f1273g;
    }

    public final String m1251h() {
        return this.f1274h;
    }

    public final Date m1252i() {
        return this.f1275i;
    }

    public final String m1253j() {
        return this.f1268b;
    }

    public final int m1254k() {
        return this.f1271e;
    }

    public final int m1255l() {
        return this.f1272f;
    }
}
