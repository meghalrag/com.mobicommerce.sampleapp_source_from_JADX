package com.paypal.android.sdk;

import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.paypal.android.sdk.Y */
public final class C0302Y extends ad {
    private String f1276b;
    private String f1277c;
    private String f1278d;
    private int f1279e;
    private int f1280f;
    private String f1281g;
    private String f1282h;
    private String f1283i;
    private String f1284j;

    public C0302Y(C0065N c0065n, C0074e c0074e, C0078i c0078i, String str, String str2, String str3, String str4, C0125s c0125s, Map map, ao[] aoVarArr, String str5, boolean z, String str6, String str7, String str8) {
        super(C0071b.CreditCardPaymentRequest, c0065n, c0074e, c0078i, str, str2, str4, c0125s, map, aoVarArr, str5, z, str6, str7, str8);
        this.f1281g = str3;
    }

    public C0302Y(C0065N c0065n, C0074e c0074e, C0078i c0078i, String str, String str2, String str3, String str4, String str5, int i, int i2, String str6, C0125s c0125s, Map map, ao[] aoVarArr, String str7, boolean z, String str8, String str9, String str10) {
        super(C0071b.CreditCardPaymentRequest, c0065n, c0074e, c0078i, str, str2, str6, c0125s, map, aoVarArr, str7, z, str8, str9, str10);
        this.f1276b = str3;
        this.f1277c = str4;
        this.f1278d = str5;
        this.f1279e = i;
        this.f1280f = i2;
    }

    public final C0302Y m1256a(String str) {
        this.f1282h = str;
        return this;
    }

    final void m1257a(JSONObject jSONObject) {
        if (C0069T.m52d(this.f1282h)) {
            jSONObject.accumulate("invoice_number", this.f1282h);
        }
        if (C0069T.m52d(this.f1283i)) {
            jSONObject.accumulate("custom", this.f1283i);
        }
        if (C0069T.m52d(this.f1284j)) {
            jSONObject.accumulate("soft_descriptor", this.f1284j);
        }
    }

    public final C0302Y m1258b(String str) {
        this.f1283i = str;
        return this;
    }

    public final C0302Y m1259c(String str) {
        this.f1284j = str;
        return this;
    }

    public final String m1260d() {
        String a = C0082m.m358a(m1236o().m694a().doubleValue(), m1236o().m695b());
        return "{\"id\":\"PAY-6RV70583SB702805EKEYSZ6Y\",\"intent\":\"sale\",\"create_time\": \"2014-02-12T22:29:49Z\",\"update_time\": \"2014-02-12T22:29:50Z\",\"payer\":{\"funding_instruments\":[{\"credit_card\":{\"expire_month\":\"" + this.f1279e + "\",\"expire_year\":\"" + this.f1280f + "\",\"number\":\"" + (this.f1276b != null ? this.f1277c.substring(this.f1277c.length() - 4) : "xxxxxxxxxx1111") + "\",\"type\":\"VISA\"}" + "}]," + "\"payment_method\":\"credit_card\"}," + "\"state\":\"approved\",\"transactions\":" + "[{" + "\"amount\":{\"currency\":\"USD\"," + "\"total\":\"" + a + "\"},\"description\":\"I am a sanity check payment.\"," + "\"item_list\":{},\"payee\":" + "{\"merchant_id\":\"PKKPTJKL75YDS\"},\"related_resources\":" + "[{\"sale\":{\"amount\":{\"currency\":\"USD\",\"total\":\"" + a + "\"},\"id\":\"0EW02334X44816642\"," + "\"parent_payment\":\"PAY-123456789012345689\",\"state\":\"completed\"}}]}]}";
    }

    public final String m1261g() {
        return this.f1277c;
    }

    public final String m1262h() {
        return this.f1276b;
    }

    public final String m1263i() {
        return this.f1278d;
    }

    public final int m1264j() {
        return this.f1279e;
    }

    public final int m1265k() {
        return this.f1280f;
    }

    protected final JSONArray m1266l() {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject;
        JSONObject jSONObject2;
        if (this.f1276b != null) {
            jSONObject = new JSONObject();
            jSONObject.accumulate("cvv2", this.f1278d);
            jSONObject.accumulate("expire_month", Integer.valueOf(this.f1279e));
            jSONObject.accumulate("expire_year", Integer.valueOf(this.f1280f));
            jSONObject.accumulate("number", this.f1277c);
            jSONObject.accumulate("type", this.f1276b);
            jSONObject2 = new JSONObject();
            jSONObject2.accumulate("credit_card", jSONObject);
            jSONArray.put(jSONObject2);
        } else {
            jSONObject = new JSONObject();
            jSONObject.accumulate("payer_id", this.f1252a);
            jSONObject.accumulate("credit_card_id", this.f1281g);
            jSONObject2 = new JSONObject();
            jSONObject2.accumulate("credit_card_token", jSONObject);
            jSONArray.put(jSONObject2);
        }
        return jSONArray;
    }

    protected final String m1267m() {
        return "credit_card";
    }
}
