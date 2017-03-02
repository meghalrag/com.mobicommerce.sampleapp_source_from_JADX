package com.paypal.android.sdk;

import android.location.Location;
import com.paypal.android.sdk.payments.BuildConfig;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class bd {
    public String f278A;
    public boolean f279B;
    public String f280C;
    public String f281D;
    public boolean f282E;
    public String f283F;
    public String f284G;
    public long f285H;
    public long f286I;
    public String f287J;
    public int f288K;
    public int f289L;
    public String f290M;
    public int f291N;
    public String f292O;
    public boolean f293P;
    public boolean f294Q;
    public String f295R;
    public long f296S;
    public long f297T;
    public String f298U;
    public String f299V;
    public String f300W;
    public String f301X;
    public String f302Y;
    public String f303Z;
    public String f304a;
    public Map aa;
    private String ab;
    private String ac;
    public String f305b;
    public String f306c;
    public int f307d;
    public String f308e;
    public int f309f;
    public String f310g;
    public String f311h;
    public String f312i;
    public String f313j;
    public String f314k;
    public String f315l;
    public String f316m;
    public long f317n;
    public String f318o;
    public List f319p;
    public List f320q;
    public String f321r;
    public String f322s;
    public String f323t;
    public String f324u;
    public Location f325v;
    public int f326w;
    public String f327x;
    public String f328y;
    public String f329z;

    public bd() {
        this.f288K = -1;
        this.f289L = -1;
        this.ab = "Android";
        this.ac = "full";
    }

    private static JSONObject m194a(Location location) {
        if (location == null) {
            return null;
        }
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("{");
            stringBuilder.append("\"lat\":");
            stringBuilder.append(location.getLatitude());
            stringBuilder.append(",\"lng\":");
            stringBuilder.append(location.getLongitude());
            stringBuilder.append(",\"acc\":");
            stringBuilder.append(location.getAccuracy());
            stringBuilder.append(",\"timestamp\":");
            stringBuilder.append(location.getTime());
            stringBuilder.append("}");
            return new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            return null;
        }
    }

    private void m195a(JSONObject jSONObject) {
        if (this.aa != null) {
            for (Entry entry : this.aa.entrySet()) {
                try {
                    jSONObject.put((String) entry.getKey(), entry.getValue());
                } catch (Throwable e) {
                    bq.m244a(null, null, e);
                }
            }
        }
    }

    public final JSONObject m196a() {
        Object obj = null;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("app_guid", this.f304a);
            jSONObject.put("app_id", this.f305b);
            jSONObject.put("app_version", this.f306c);
            jSONObject.put("base_station_id", this.f307d == -1 ? null : Integer.valueOf(this.f307d));
            jSONObject.put("bssid", this.f308e);
            jSONObject.put("cell_id", this.f309f == -1 ? null : Integer.valueOf(this.f309f));
            jSONObject.put("comp_version", this.f310g);
            jSONObject.put("conf_url", this.f311h);
            jSONObject.put("conf_version", this.f312i);
            jSONObject.put("conn_type", this.f313j);
            jSONObject.put("device_id", this.f314k);
            jSONObject.put("device_model", this.f315l);
            jSONObject.put("device_name", this.f316m);
            jSONObject.put("device_uptime", this.f317n);
            jSONObject.put("ip_addrs", this.f318o);
            jSONObject.put("ip_addresses", this.f319p == null ? null : new JSONArray(this.f319p));
            jSONObject.put("known_apps", this.f320q == null ? null : new JSONArray(this.f320q));
            jSONObject.put("line_1_number", BuildConfig.VERSION_NAME.equals(this.f321r) ? null : this.f321r);
            jSONObject.put("linker_id", this.f322s);
            jSONObject.put("locale_country", this.f323t);
            jSONObject.put("locale_lang", this.f324u);
            jSONObject.put("location", m194a(this.f325v));
            jSONObject.put("location_area_code", this.f326w == -1 ? null : Integer.valueOf(this.f326w));
            jSONObject.put("mac_addrs", this.f327x);
            jSONObject.put("os_type", this.ab);
            jSONObject.put("os_version", this.f328y);
            jSONObject.put("payload_type", this.ac);
            jSONObject.put("phone_type", this.f329z);
            jSONObject.put("risk_comp_session_id", this.f278A);
            jSONObject.put("roaming", this.f279B);
            jSONObject.put("sim_operator_name", BuildConfig.VERSION_NAME.equals(this.f280C) ? null : this.f280C);
            jSONObject.put("sim_serial_number", this.f281D);
            jSONObject.put("sms_enabled", this.f282E);
            jSONObject.put("ssid", this.f283F);
            jSONObject.put("cdma_network_id", this.f289L == -1 ? null : Integer.valueOf(this.f289L));
            String str = "cdma_system_id";
            if (this.f288K != -1) {
                obj = Integer.valueOf(this.f288K);
            }
            jSONObject.put(str, obj);
            jSONObject.put("subscriber_id", this.f284G);
            jSONObject.put("timestamp", this.f285H);
            jSONObject.put("total_storage_space", this.f286I);
            jSONObject.put("tz_name", this.f287J);
            jSONObject.put("network_operator", this.f290M);
            jSONObject.put("source_app", this.f291N);
            jSONObject.put("source_app_version", this.f292O);
            jSONObject.put("is_emulator", this.f293P);
            jSONObject.put("is_rooted", this.f294Q);
            jSONObject.put("pairing_id", this.f295R);
            jSONObject.put("app_first_install_time", this.f296S);
            jSONObject.put("app_last_update_time", this.f297T);
            jSONObject.put("android_id", this.f298U);
            jSONObject.put("serial_number", this.f301X);
            jSONObject.put("advertising_identifier", this.f299V);
            jSONObject.put("notif_token", this.f300W);
            jSONObject.put("bluetooth_mac_addrs", this.f302Y);
            jSONObject.put("gsf_id", this.f303Z);
            m195a(jSONObject);
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public final JSONObject m197a(bd bdVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("is_emulator", bdVar.f293P);
            jSONObject.put("is_rooted", bdVar.f294Q);
            jSONObject.put("app_guid", bdVar.f304a);
            jSONObject.put("risk_comp_session_id", bdVar.f278A);
            jSONObject.put("timestamp", bdVar.f285H);
            jSONObject.put("payload_type", "incremental");
            jSONObject.put("source_app", bdVar.f291N);
            jSONObject.put("pairing_id", bdVar.f295R);
            m195a(jSONObject);
            if (!(this.f305b == null || this.f305b.equals(bdVar.f305b))) {
                jSONObject.put("app_id", bdVar.f305b);
            }
            if (!(this.f306c == null || this.f306c.equals(bdVar.f306c))) {
                jSONObject.put("app_version", bdVar.f306c);
            }
            if (this.f307d != bdVar.f307d) {
                jSONObject.put("base_station_id", bdVar.f307d);
            }
            if (!(this.f308e == null || this.f308e.equals(bdVar.f308e))) {
                jSONObject.put("bssid", bdVar.f308e);
            }
            if (this.f309f != bdVar.f309f) {
                jSONObject.put("cell_id", bdVar.f309f);
            }
            if (!(this.f310g == null || this.f310g.equals(bdVar.f310g))) {
                jSONObject.put("comp_version", bdVar.f310g);
            }
            if (!(this.f312i == null || this.f312i.equals(bdVar.f312i))) {
                jSONObject.put("conf_version", bdVar.f312i);
            }
            if (!(this.f311h == null || this.f311h.equals(bdVar.f311h))) {
                jSONObject.put("conf_url", bdVar.f311h);
            }
            if (!(this.f313j == null || this.f313j.equals(bdVar.f313j))) {
                jSONObject.put("conn_type", bdVar.f313j);
            }
            if (!(this.f314k == null || this.f314k.equals(bdVar.f314k))) {
                jSONObject.put("device_id", bdVar.f314k);
            }
            if (!(this.f315l == null || this.f315l.equals(bdVar.f315l))) {
                jSONObject.put("device_model", bdVar.f315l);
            }
            if (!(this.f316m == null || this.f316m.equals(bdVar.f316m))) {
                jSONObject.put("device_name", bdVar.f316m);
            }
            if (this.f317n != bdVar.f317n) {
                jSONObject.put("device_uptime", bdVar.f317n);
            }
            if (!(this.f318o == null || this.f318o.equals(bdVar.f318o))) {
                jSONObject.put("ip_addrs", bdVar.f318o);
            }
            if (!(this.f319p == null || bdVar.f319p == null || this.f319p.toString().equals(bdVar.f319p.toString()))) {
                jSONObject.put("ip_addresses", new JSONArray(bdVar.f319p));
            }
            if (!(this.f320q == null || bdVar.f320q == null || this.f320q.toString().equals(bdVar.f320q.toString()))) {
                jSONObject.put("known_apps", new JSONArray(bdVar.f320q));
            }
            if (!(this.f321r == null || this.f321r.equals(bdVar.f321r))) {
                jSONObject.put("line_1_number", bdVar.f321r);
            }
            if (!(this.f322s == null || this.f322s.equals(bdVar.f322s))) {
                jSONObject.put("linker_id", bdVar.f322s);
            }
            if (!(this.f323t == null || this.f323t.equals(bdVar.f323t))) {
                jSONObject.put("locale_country", bdVar.f323t);
            }
            if (!(this.f324u == null || this.f324u.equals(bdVar.f324u))) {
                jSONObject.put("locale_lang", bdVar.f324u);
            }
            if (!(this.f325v == null || bdVar.f325v == null || this.f325v.toString().equals(bdVar.f325v.toString()))) {
                jSONObject.put("location", m194a(bdVar.f325v));
            }
            if (this.f326w != bdVar.f326w) {
                jSONObject.put("location_area_code", bdVar.f326w);
            }
            if (!(this.f327x == null || this.f327x.equals(bdVar.f327x))) {
                jSONObject.put("mac_addrs", bdVar.f327x);
            }
            if (!(this.f302Y == null || this.f302Y.equals(bdVar.f302Y))) {
                jSONObject.put("bluetooth_mac_addrs", bdVar.f302Y);
            }
            if (!(this.ab == null || this.ab.equals(bdVar.ab))) {
                jSONObject.put("os_type", bdVar.ab);
            }
            if (!(this.f328y == null || this.f328y.equals(bdVar.f328y))) {
                jSONObject.put("os_version", bdVar.f328y);
            }
            if (!(this.f329z == null || this.f329z.equals(bdVar.f329z))) {
                jSONObject.put("phone_type", bdVar.f329z);
            }
            if (this.f279B != bdVar.f279B) {
                jSONObject.put("roaming", bdVar.f279B);
            }
            if (!(this.f280C == null || this.f280C.equals(bdVar.f280C))) {
                jSONObject.put("sim_operator_name", bdVar.f280C);
            }
            if (!(this.f281D == null || this.f281D.equals(bdVar.f281D))) {
                jSONObject.put("sim_serial_number", bdVar.f281D);
            }
            if (this.f282E != bdVar.f282E) {
                jSONObject.put("sms_enabled", bdVar.f282E);
            }
            if (!(this.f283F == null || this.f283F.equals(bdVar.f283F))) {
                jSONObject.put("ssid", bdVar.f283F);
            }
            if (this.f289L != bdVar.f289L) {
                jSONObject.put("cdma_network_id", bdVar.f289L);
            }
            if (this.f288K != bdVar.f288K) {
                jSONObject.put("cdma_system_id", bdVar.f288K);
            }
            if (!(this.f284G == null || this.f284G.equals(bdVar.f284G))) {
                jSONObject.put("subscriber_id", bdVar.f284G);
            }
            if (this.f286I != bdVar.f286I) {
                jSONObject.put("total_storage_space", bdVar.f286I);
            }
            if (!(this.f287J == null || this.f287J.equals(bdVar.f287J))) {
                jSONObject.put("tz_name", bdVar.f287J);
            }
            if (!(this.f290M == null || this.f290M.equals(bdVar.f290M))) {
                jSONObject.put("network_operator", bdVar.f290M);
            }
            if (!(this.f292O == null || this.f292O.equals(bdVar.f292O))) {
                jSONObject.put("source_app_version", bdVar.f292O);
            }
            if (this.f296S != bdVar.f296S) {
                jSONObject.put("app_first_install_time", bdVar.f296S);
            }
            if (this.f297T != bdVar.f297T) {
                jSONObject.put("app_last_update_time", bdVar.f297T);
            }
            if (!(this.f298U == null || this.f298U.equals(bdVar.f298U))) {
                jSONObject.put("android_id", bdVar.f298U);
            }
            if (!(this.f301X == null || this.f301X.equals(bdVar.f301X))) {
                jSONObject.put("serial_number", bdVar.f301X);
            }
            if (!(this.f299V == null || this.f299V.equals(bdVar.f299V))) {
                jSONObject.put("advertising_identifier", bdVar.f299V);
            }
            if (!(this.f300W == null || this.f300W.equals(bdVar.f300W))) {
                jSONObject.put("notif_token", bdVar.f300W);
            }
            if (!(this.f303Z == null || this.f303Z.equals(bdVar.f303Z))) {
                jSONObject.put("gsf_id", bdVar.f303Z);
            }
        } catch (JSONException e) {
        }
        return jSONObject;
    }
}
