package com.paypal.android.sdk;

import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public abstract class ap {
    private static long f57i;
    private String f58a;
    private String f59b;
    private Map f60c;
    private final C0065N f61d;
    private final C0074e f62e;
    private C0071b f63f;
    private String f64g;
    private aq f65h;
    private long f66j;
    private Integer f67k;
    private String f68l;

    static {
        f57i = 1;
    }

    public ap(C0071b c0071b, C0065N c0065n, C0074e c0074e, C0078i c0078i) {
        this(c0071b, c0065n, c0074e, c0078i, null);
    }

    public ap(C0071b c0071b, C0065N c0065n, C0074e c0074e, C0078i c0078i, String str) {
        long j = f57i;
        f57i = 1 + j;
        this.f66j = j;
        this.f63f = c0071b;
        this.f64g = str;
        this.f61d = c0065n;
        this.f62e = c0074e;
        this.f60c = new LinkedHashMap();
    }

    protected final JSONObject m71A() {
        Object nextValue = new JSONTokener(this.f59b).nextValue();
        if (nextValue instanceof JSONObject) {
            return (JSONObject) nextValue;
        }
        throw new JSONException("could not parse:" + this.f59b + "\nnextValue:" + nextValue);
    }

    public final String m72B() {
        return getClass().getSimpleName() + " SN:" + this.f66j;
    }

    public final long m73C() {
        return this.f66j;
    }

    public final aq m74D() {
        return this.f65h;
    }

    public final boolean m75E() {
        return this.f65h == null;
    }

    public final Integer m76F() {
        return this.f67k;
    }

    public abstract String m77a();

    public String m78a(C0071b c0071b) {
        String a = this.f61d.m29a(c0071b);
        if (a != null) {
            return this.f64g != null ? a + this.f64g : a;
        } else {
            throw new RuntimeException("API " + c0071b.toString() + " has no record for server " + this.f61d.m31b());
        }
    }

    public final void m79a(aq aqVar) {
        if (this.f65h != null) {
            throw new IllegalStateException("Can't add another error to this event.  Stop double parsing!");
        }
        this.f65h = aqVar;
    }

    public final void m80a(Integer num) {
        this.f67k = num;
    }

    public final void m81a(String str, String str2, String str3) {
        m79a(new ar(str, str2, str3));
    }

    public abstract void m82b();

    protected final void m83b(String str, String str2) {
        this.f60c.put(str, str2);
    }

    public abstract void m84c();

    public abstract String m85d();

    public final void m86d(String str) {
        this.f58a = str;
    }

    public final void m87e(String str) {
        this.f59b = str;
    }

    public boolean m88e() {
        return false;
    }

    public void m89f() {
    }

    public final void m90f(String str) {
        this.f68l = str;
    }

    public final C0074e m91u() {
        return this.f62e;
    }

    public final String m92v() {
        return this.f58a;
    }

    public final String m93w() {
        return this.f59b;
    }

    public final C0071b m94x() {
        return this.f63f;
    }

    public final Map m95y() {
        return this.f60c;
    }

    public final String m96z() {
        return this.f68l;
    }
}
