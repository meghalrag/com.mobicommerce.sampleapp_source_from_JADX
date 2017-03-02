package com.paypal.android.sdk;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.paypal.android.sdk.X */
public final class C0297X extends af {
    private String f1214a;
    private String f1215b;
    private JSONArray f1216c;
    private JSONObject f1217d;
    private JSONArray f1218e;
    private C0125s f1219f;
    private Map f1220g;
    private ao[] f1221h;
    private String f1222i;
    private String f1223j;
    private boolean f1224k;
    private String f1225l;
    private String f1226m;
    private String f1227n;

    public C0297X(C0065N c0065n, C0074e c0074e, String str, C0078i c0078i, String str2, String str3, String str4, C0125s c0125s, Map map, ao[] aoVarArr, String str5, boolean z, String str6, String str7, String str8, boolean z2) {
        C0071b c0071b = C0071b.CreateSfoPaymentRequest;
        StringBuilder stringBuilder = new StringBuilder("Bearer ");
        if (str.equals(PayPalConfiguration.ENVIRONMENT_NO_NETWORK) && str2 == null) {
            str2 = "mock_euat";
        }
        super(c0071b, c0065n, c0074e, c0078i, stringBuilder.append(str2).toString());
        this.f1219f = c0125s;
        this.f1220g = map;
        this.f1221h = aoVarArr;
        this.f1222i = str5;
        this.f1224k = z2;
        this.f1223j = str8;
        if (C0069T.m51c(this.f1223j)) {
            this.f1223j = PayPalPayment.PAYMENT_INTENT_SALE;
        }
        this.f1223j = this.f1223j.toLowerCase(Locale.US);
        m83b("PayPal-Request-Id", str3);
        if (C0069T.m52d(str6)) {
            m83b("PayPal-Partner-Attribution-Id", str6);
        }
        if (C0069T.m52d(str7)) {
            m83b("Paypal-Application-Correlation-Id", str7);
        }
    }

    public final C0297X m1184a(String str) {
        this.f1225l = str;
        return this;
    }

    public final String m1185a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.accumulate("intent", this.f1223j);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.accumulate("payment_method", "paypal");
        jSONObject.accumulate("payer", jSONObject2);
        jSONObject2 = new JSONObject();
        jSONObject2.accumulate("cancel_url", "http://cancelurl");
        jSONObject2.accumulate("return_url", "http://returnurl");
        jSONObject.accumulate("redirect_urls", jSONObject2);
        C0125s c0125s = this.f1219f;
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.accumulate("currency", c0125s.m695b().getCurrencyCode());
        jSONObject3.accumulate("total", c0125s.m694a().toPlainString());
        if (!(this.f1220g == null || this.f1220g.isEmpty())) {
            Object obj;
            String str = "details";
            if (this.f1220g == null || this.f1220g.isEmpty()) {
                obj = null;
            } else {
                obj = new JSONObject();
                if (this.f1220g.containsKey("shipping")) {
                    obj.accumulate("shipping", this.f1220g.get("shipping"));
                }
                if (this.f1220g.containsKey("subtotal")) {
                    obj.accumulate("subtotal", this.f1220g.get("subtotal"));
                }
                if (this.f1220g.containsKey("tax")) {
                    obj.accumulate("tax", this.f1220g.get("tax"));
                }
            }
            jSONObject3.accumulate(str, obj);
        }
        jSONObject2 = new JSONObject();
        jSONObject2.accumulate("amount", jSONObject3);
        jSONObject2.accumulate("description", this.f1222i);
        if (this.f1221h != null && this.f1221h.length > 0) {
            jSONObject3 = new JSONObject();
            jSONObject3.accumulate("items", ao.m70a(this.f1221h));
            jSONObject2.accumulate("item_list", jSONObject3);
        }
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(jSONObject2);
        jSONObject.accumulate("transactions", jSONArray);
        if (C0069T.m52d(this.f1225l)) {
            jSONObject2.accumulate("invoice_number", this.f1225l);
        }
        if (C0069T.m52d(this.f1226m)) {
            jSONObject2.accumulate("custom", this.f1226m);
        }
        if (C0069T.m52d(this.f1227n)) {
            jSONObject2.accumulate("soft_descriptor", this.f1227n);
        }
        jSONObject2 = new JSONObject();
        jSONObject2.accumulate("device_info", C0069T.m48b(C0084o.m364a().toString()));
        jSONObject2.accumulate("app_info", C0069T.m48b(C0079j.m351a().toString()));
        jSONObject2.accumulate("risk_data", C0069T.m48b(be.m200a().m224b().toString()));
        jSONObject3 = new JSONObject();
        jSONObject3.accumulate("payment", jSONObject);
        jSONObject3.accumulate("client_info", jSONObject2);
        if (this.f1224k) {
            jSONObject3.accumulate("retrieve_shipping_addresses", Boolean.valueOf(true));
        }
        return jSONObject3.toString();
    }

    public final C0297X m1186b(String str) {
        this.f1226m = str;
        return this;
    }

    public final void m1187b() {
        JSONObject A = m71A();
        this.f1214a = A.optString("payment_id");
        this.f1215b = A.getString("session_id");
        this.f1216c = A.optJSONArray("addresses");
        A = A.optJSONObject("funding_options");
        if (A != null) {
            this.f1217d = A.optJSONObject("default_option");
            this.f1218e = A.optJSONArray("alternate_options");
        }
    }

    public final C0297X m1188c(String str) {
        this.f1227n = str;
        return this;
    }

    public final void m1189c() {
        m935b(m71A());
    }

    public final String m1190d() {
        return "{    \"session_id\":\"7N0112287V303050T\",    \"payment_id\":\"PAY-18X32451H0459092JKO7KFUI\",    \"addresses\": [          {             \"city\": \"Columbia\",              \"line2\": \"6073 2nd Street\",              \"line1\": \"Suite 222\",              \"recipient_name\": \"Beverly Jello\",             \"state\": \"MD\",              \"postal_code\": \"21045\",             \"default_address\": false,              \"country_code\": \"US\",              \"type\": \"HOME_OR_WORK\",              \"id\": \"366853\"          },          {             \"city\": \"Austin\",              \"line2\": \"Apt. 222\",              \"line1\": \"52 North Main St. \",              \"recipient_name\": \"Michael Chassen\",             \"state\": \"TX\",              \"postal_code\": \"78729\",             \"default_address\": true,              \"country_code\": \"US\",              \"type\": \"HOME_OR_WORK\",              \"id\": \"366852\"          },          {             \"city\": \"Austin\",              \"line1\": \"202 South State St. \",              \"recipient_name\": \"Sam Stone\",             \"state\": \"TX\",              \"postal_code\": \"78729\",             \"default_address\": true,              \"country_code\": \"US\",              \"type\": \"HOME_OR_WORK\",              \"id\": \"366852\"          }     ],     \"funding_options\":{       \"default_option\":{          \"id\":\"1\",          \"backup_funding_instrument\":{             \"payment_card\":{                \"number\":\"8029\",                \"type\":\"VISA\"             }          },          \"funding_sources\":[             {                \"amount\":{                   \"value\":\"216.85\",                   \"currency\":\"USD\"                },                \"funding_instrument_type\":\"BANK_ACCOUNT\",                \"funding_mode\":\"INSTANT_TRANSFER\",                \"bank_account\":{                   \"bank_name\":\"SunTrust\",                   \"account_number\":\"7416\",                   \"account_number_type\":\"BBAN\",                   \"country_code\":\"US\",                   \"account_type\":\"CHECKING\"                }             },             {                \"amount\":{                   \"value\":\"6.00\",                   \"currency\":\"USD\"                },                \"funding_instrument_type\":\"BALANCE\",                \"funding_mode\":\"INSTANT_TRANSFER\"             }          ]       },       \"alternate_options\":[          {             \"id\":\"2\",             \"funding_sources\":[                {                   \"amount\":{                      \"value\":\"216.85\",                      \"currency\":\"USD\"                   },                   \"funding_instrument_type\":\"PAYMENT_CARD\",                   \"payment_card\":{                      \"number\":\"8029\",                      \"type\":\"VISA\"                   },                   \"funding_mode\":\"INSTANT_TRANSFER\"                },                {                   \"amount\":{                      \"value\":\"6.00\",                      \"currency\":\"USD\"                   },                   \"funding_instrument_type\":\"BALANCE\",                   \"funding_mode\":\"INSTANT_TRANSFER\"                }             ]          },          {             \"id\":\"3\",             \"funding_sources\":[                {                   \"amount\":{                      \"value\":\"216.85\",                      \"currency\":\"USD\"                   },                   \"funding_instrument_type\":\"PAYMENT_CARD\",                   \"payment_card\":{                      \"number\":\"8011\",                      \"type\":\"VISA\"                   },                   \"funding_mode\":\"INSTANT_TRANSFER\"                },                {                   \"amount\":{                      \"value\":\"6.00\",                      \"currency\":\"USD\"                   },                   \"funding_instrument_type\":\"BALANCE\",                   \"funding_mode\":\"INSTANT_TRANSFER\"                }             ]          }       ]    } }";
    }

    public final String m1191g() {
        return this.f1214a;
    }

    public final String m1192h() {
        return this.f1215b;
    }

    public final JSONArray m1193i() {
        return this.f1216c;
    }

    public final JSONObject m1194j() {
        return this.f1217d;
    }

    public final JSONArray m1195k() {
        return this.f1218e;
    }
}
