package com.paypal.android.sdk;

import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class bV implements Iterable {
    private List f223a;
    private int f224b;

    static {
        bV.class.getSimpleName();
    }

    public bV(JSONArray jSONArray, JSONObject jSONObject) {
        this.f223a = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object a;
            try {
                a = bU.m138a(jSONArray.getJSONObject(i));
            } catch (JSONException e) {
                Log.w("paypal.sdk", "Error extracting funding source: " + e.getMessage());
                a = null;
            }
            if (a != null) {
                this.f223a.add(a);
            }
        }
        if (jSONObject != null) {
            Object a2;
            try {
                a2 = bU.m138a(jSONObject);
            } catch (JSONException e2) {
                Log.w("paypal.sdk", "Error parsing backup funding instrument: " + e2.getMessage());
                a2 = null;
            }
            if (a2 != null) {
                this.f223a.add(a2);
            }
        }
        this.f224b = m146f();
    }

    private int m146f() {
        Double valueOf = Double.valueOf(0.0d);
        int i = 0;
        for (int i2 = 0; i2 < this.f223a.size(); i2++) {
            if (((bU) this.f223a.get(i2)).m143d().doubleValue() > valueOf.doubleValue()) {
                valueOf = ((bU) this.f223a.get(i2)).m143d();
                i = i2;
            }
        }
        return i;
    }

    public final bU m147a(int i) {
        this.f223a.size();
        return (bU) this.f223a.get(0);
    }

    public final String m148a() {
        return ((bU) this.f223a.get(this.f224b)).m140a();
    }

    public final boolean m149b() {
        Object f = ((bU) this.f223a.get(this.f224b)).m145f();
        return C0069T.m52d(f) ? f.toUpperCase().equals("DELAYED_TRANSFER") : false;
    }

    public final String m150c() {
        return this.f223a.size() == 1 ? ((bU) this.f223a.get(0)).m141b() : bN.m131a(bO.AND_OTHER_FUNDING_SOURCES);
    }

    public final String m151d() {
        return ((bU) this.f223a.get(this.f224b)).m144e();
    }

    public final int m152e() {
        return this.f223a.size();
    }

    public final Iterator iterator() {
        return this.f223a.iterator();
    }
}
