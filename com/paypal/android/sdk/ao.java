package com.paypal.android.sdk;

import java.math.BigDecimal;
import org.json.JSONArray;
import org.json.JSONObject;

public class ao {
    private String f52a;
    private Integer f53b;
    private BigDecimal f54c;
    private String f55d;
    private String f56e;

    static {
        ao.class.getSimpleName();
    }

    public ao(String str, Integer num, BigDecimal bigDecimal, String str2, String str3) {
        this.f52a = str;
        this.f53b = num;
        this.f54c = bigDecimal;
        this.f55d = str2;
        this.f56e = str3;
    }

    public static JSONArray m70a(ao[] aoVarArr) {
        if (aoVarArr == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (ao aoVar : aoVarArr) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.accumulate("quantity", Integer.toString(aoVar.f53b.intValue()));
            jSONObject.accumulate("name", aoVar.f52a);
            jSONObject.accumulate("price", aoVar.f54c.toString());
            jSONObject.accumulate("currency", aoVar.f55d);
            jSONObject.accumulate("sku", aoVar.f56e);
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }
}
