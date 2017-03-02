package com.paypal.android.sdk;

import com.paypal.android.sdk.payments.BuildConfig;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class aa extends af {
    private static final String f1228a;
    private Map f1229b;
    private String f1230c;
    private String f1231d;
    private String f1232e;

    static {
        f1228a = aa.class.getSimpleName();
    }

    public aa(C0065N c0065n, C0074e c0074e, C0078i c0078i, String str, String str2, String str3) {
        C0071b c0071b = C0071b.GetAppInfoRequest;
        StringBuilder stringBuilder = new StringBuilder("Bearer ");
        if (str.equals(PayPalConfiguration.ENVIRONMENT_NO_NETWORK) && str2 == null) {
            str2 = "mock_euat";
        }
        super(c0071b, c0065n, c0074e, c0078i, stringBuilder.append(str2).toString(), "/" + str3);
        this.f1229b = new HashMap();
        m83b("Content-Type", "application/x-www-form-urlencoded");
    }

    private static void m1196a(JSONArray jSONArray, Map map) {
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject != null) {
                    CharSequence optString = jSONObject.optString("name");
                    CharSequence optString2 = jSONObject.optString("value");
                    if (C0069T.m52d(optString) && C0069T.m52d(optString2)) {
                        map.put(optString, optString2);
                    }
                }
            }
        }
    }

    public final String m1197a() {
        return BuildConfig.VERSION_NAME;
    }

    public final void m1198b() {
        String str = f1228a;
        JSONObject A = m71A();
        JSONArray optJSONArray = A.optJSONArray("capabilities");
        if (optJSONArray != null) {
            JSONObject jSONObject;
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                if (jSONObject2 != null && "PAYPAL_ACCESS".equals(jSONObject2.optString("name"))) {
                    jSONObject = jSONObject2;
                    break;
                }
            }
            jSONObject = null;
            if (jSONObject != null) {
                m1196a(jSONObject.optJSONArray("attributes"), this.f1229b);
                str = f1228a;
                new StringBuilder("Attributes: ").append(this.f1229b.toString());
            }
        }
        JSONArray optJSONArray2 = A.optJSONArray("attributes");
        if (optJSONArray2 != null) {
            Map hashMap = new HashMap();
            m1196a(optJSONArray2, hashMap);
            this.f1230c = (String) hashMap.get("privacy_policy_url");
            this.f1231d = (String) hashMap.get("user_agreement_url");
            this.f1232e = (String) hashMap.get("display_name");
        }
    }

    public final void m1199c() {
    }

    public final String m1200d() {
        return " {         \"attributes\": [             {                 \"name\": \"display_name\",                  \"value\": \"Jacunski Software\"             },              {                 \"name\": \"privacy_policy_url\",                  \"value\": \"http://www.cnn.com\"             },              {                 \"name\": \"user_agreement_url\",                  \"value\": \"http://www.uber.com\"             }         ],          \"name\": \"LiveTestApp\",          \"capabilities\": [             {                 \"scopes\": [],                  \"name\": \"PAYPAL_ACCESS\",                  \"attributes\": [                     {                         \"name\": \"openid_connect\",                          \"value\": \"Y\"                     },                      {                         \"name\": \"oauth_date_of_birth\",                          \"value\": \"Y\"                     },                      {                         \"name\": \"oauth_fullname\",                          \"value\": \"Y\"                     },                      {                         \"name\": \"oauth_zip\",                          \"value\": \"Y\"                     },                      {                         \"name\": \"oauth_language\",                          \"value\": \"Y\"                     },                      {                         \"name\": \"oauth_city\",                          \"value\": \"Y\"                     },                      {                         \"name\": \"oauth_country\",                          \"value\": \"Y\"                     },                      {                         \"name\": \"oauth_timezone\",                         \"value\": \"Y\"                     },                      {                         \"name\": \"oauth_email\",                          \"value\": \"Y\"                     },                      {                         \"name\": \"oauth_street_address1\",                          \"value\": \"Y\"                     },                      {                         \"name\": \"oauth_street_address2\",                          \"value\": \"Y\"                     },                      {                         \"name\": \"oauth_phone_number\",                          \"value\": \"Y\"                     },                      {                         \"name\": \"oauth_locale\",                          \"value\": \"Y\"                     },                      {                         \"name\": \"oauth_state\",                          \"value\": \"Y\"                     },                      {                         \"name\": \"oauth_age_range\",                          \"value\": \"Y\"                     },                      {                         \"name\": \"oauth_account_verified\",                          \"value\": \"Y\"                     },                      {                         \"name\": \"oauth_account_creation_date\",                          \"value\": \"Y\"                     },                      {                         \"name\": \"oauth_account_type\",                          \"value\": \"Y\"                     }                 ]             },              {                 \"scopes\": [                     \"https://api.paypal.com/v1/payments/.*\",                      \"https://api.paypal.com/v1/vault/credit-card\",                      \"https://api.paypal.com/v1/vault/credit-card/.*\"                 ],                  \"name\": \"PAYMENT\",                  \"features\": [                     {                         \"status\": \"ACTIVE\",                          \"name\": \"ACCEPT_CARD\"                     },                      {                         \"status\": \"ACTIVE\",                          \"name\": \"ACCEPT_PAYPAL\"                     }                 ]             }         ]     }    ";
    }

    public final Map m1201g() {
        return this.f1229b;
    }

    public final String m1202h() {
        return this.f1230c;
    }

    public final String m1203i() {
        return this.f1231d;
    }

    public final String m1204j() {
        return this.f1232e;
    }
}
