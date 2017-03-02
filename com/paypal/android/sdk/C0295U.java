package com.paypal.android.sdk;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.paypal.android.sdk.U */
public final class C0295U extends af {
    private String f1204a;
    private String f1205b;
    private boolean f1206c;
    private JSONObject f1207d;
    private JSONObject f1208e;
    private JSONObject f1209f;
    private String f1210g;
    private String f1211h;
    private String f1212i;
    private String f1213j;

    public C0295U(C0065N c0065n, C0074e c0074e, String str, C0078i c0078i, String str2, String str3, boolean z, String str4, String str5, String str6, String str7, JSONObject jSONObject, JSONObject jSONObject2) {
        C0071b c0071b = C0071b.ApproveAndExecuteSfoPaymentRequest;
        StringBuilder stringBuilder = new StringBuilder("Bearer ");
        if (str.equals(PayPalConfiguration.ENVIRONMENT_NO_NETWORK) && str2 == null) {
            str2 = "mock_euat";
        }
        super(c0071b, c0065n, c0074e, c0078i, stringBuilder.append(str2).toString());
        this.f1206c = z;
        this.f1204a = str6;
        this.f1205b = str7;
        this.f1207d = jSONObject;
        this.f1208e = jSONObject2;
        m83b("PayPal-Request-Id", str3);
        if (C0069T.m52d(str4)) {
            m83b("PayPal-Partner-Attribution-Id", str4);
        }
        if (C0069T.m52d(str5)) {
            m83b("Paypal-Application-Correlation-Id", str5);
        }
    }

    private static String m1172a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        try {
            JSONObject jSONObject = jSONArray.getJSONObject(0);
            if (jSONObject == null) {
                return null;
            }
            JSONArray jSONArray2 = jSONObject.getJSONArray("related_resources");
            if (jSONArray2 == null) {
                return null;
            }
            jSONObject = jSONArray2.getJSONObject(0);
            if (jSONObject == null) {
                return null;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("authorization");
            if (optJSONObject != null) {
                return optJSONObject.optString("id");
            }
            jSONObject = jSONObject.optJSONObject(PayPalPayment.PAYMENT_INTENT_ORDER);
            return jSONObject != null ? jSONObject.optString("id") : null;
        } catch (JSONException e) {
            return null;
        }
    }

    public final String m1173a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.accumulate("payment_id", this.f1204a);
        jSONObject.accumulate("session_id", this.f1205b);
        if (this.f1208e != null) {
            jSONObject.accumulate("funding_option", this.f1208e);
        }
        if (this.f1207d != null) {
            jSONObject.accumulate("shipping_address", this.f1207d);
        }
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.accumulate("device_info", C0069T.m48b(C0084o.m364a().toString()));
        jSONObject2.accumulate("app_info", C0069T.m48b(C0079j.m351a().toString()));
        jSONObject2.accumulate("risk_data", C0069T.m48b(be.m200a().m224b().toString()));
        jSONObject.accumulate("client_info", jSONObject2);
        return jSONObject.toString();
    }

    public final void m1174b() {
        try {
            this.f1209f = m71A().getJSONObject("payment");
            this.f1210g = this.f1209f.optString("state");
            this.f1204a = this.f1209f.optString("id");
            this.f1211h = this.f1209f.optString("create_time");
            this.f1212i = this.f1209f.optString("intent");
            this.f1213j = C0295U.m1172a(this.f1209f.getJSONArray("transactions"));
        } catch (JSONException e) {
            m1175c();
        }
    }

    public final void m1175c() {
        m935b(m71A());
    }

    public final String m1176d() {
        return "{     \"payment\": {         \"id\": \"PAY-6PU626847B294842SKPEWXHY\",         \"transactions\": [             {                 \"amount\": {                     \"total\": \"2.85\",                     \"details\": {                         \"subtotal\": \"2.85\"                     },                     \"currency\": \"USD\"                 },                 \"description\": \"Awesome Sauce\",                 \"related_resources\": [                     {                         \"sale\": {                             \"amount\": {                                 \"total\": \"2.85\",                                 \"currency\": \"USD\"                             },                             \"id\": \"5LR21373K59921925\",                             \"parent_payment\": \"PAY-6PU626847B294842SKPEWXHY\",                             \"update_time\": \"2014-07-18T18:47:06Z\",                             \"state\": \"completed\",                             \"create_time\": \"2014-07-18T18:46:55Z\",                             \"links\": [                                 {                                     \"method\": \"GET\",                                     \"rel\": \"self\",                                     \"href\": \"https://www.stage2std019.stage.\"                                 },                                 {                                     \"method\": \"POST\",                                     \"rel\": \"refund\",                                     \"href\": \"https://www.stage2std019.stage. \"                                 },                                 {                                     \"method\": \"GET\",                                     \"rel\": \"parent_payment\",                                     \"href\": \"https://www.stage2std019.stage.PEWXHY \"                                 }                             ]                         }                     }                 ]             }         ],         \"update_time\": \"2014-07-18T18:47:06Z\",         \"payer\": {             \"payer_info\": {                 \"shipping_address\": {                                      }             },             \"payment_method\": \"paypal\"         },         \"state\": \"approved\",         \"create_time\": \"2014-07-18T18:46:55Z\",         \"links\": [             {                 \"method\": \"GET\",                 \"rel\": \"self\",                 \"href\": \"https://www.stage2std019.stage.paypal.\"             }         ],         \"intent\": \"sale\"     } } ";
    }

    public final void m1177f() {
        be.m200a().m227f();
    }

    public final String m1178g() {
        return this.f1204a;
    }

    public final boolean m1179h() {
        return this.f1206c;
    }

    public final String m1180i() {
        return this.f1210g;
    }

    public final String m1181j() {
        return this.f1211h;
    }

    public final String m1182k() {
        return this.f1212i;
    }

    public final String m1183l() {
        return this.f1213j;
    }
}
