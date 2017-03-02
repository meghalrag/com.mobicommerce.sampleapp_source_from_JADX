package io.card.payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* renamed from: io.card.payment.j */
public class C0166j {
    private static final String f944a;
    private static final Map f945b;
    private static final Set f946c;
    private static /* synthetic */ boolean f947g;
    private Map f948d;
    private C0167k f949e;
    private Class f950f;

    static {
        f947g = !C0166j.class.desiredAssertionStatus();
        f944a = C0166j.class.getSimpleName();
        f945b = new HashMap();
        f946c = new HashSet();
        f945b.put("zh_CN", "zh-Hans");
        f945b.put("zh_TW", "zh-Hant_TW");
        f945b.put("zh_HK", "zh-Hant");
        f945b.put("en_UK", "en_GB");
        f945b.put("en_IE", "en_GB");
        f945b.put("iw_IL", "he");
        f945b.put("no", "nb");
        f946c.add("he");
        f946c.add("ar");
    }

    public C0166j(Class cls, List list) {
        this.f948d = new LinkedHashMap();
        this.f950f = cls;
        for (C0167k c0167k : list) {
            String a = c0167k.m823a();
            if (a == null) {
                throw new RuntimeException("Null localeName");
            } else if (this.f948d.containsKey(a)) {
                throw new RuntimeException("Locale " + a + " already added");
            } else {
                this.f948d.put(a, c0167k);
                m817c(a);
            }
        }
        m821a(null);
    }

    private void m817c(String str) {
        C0167k c0167k = (C0167k) this.f948d.get(str);
        List arrayList = new ArrayList();
        String str2 = f944a;
        new StringBuilder("Checking locale ").append(str);
        for (Enum enumR : (Enum[]) this.f950f.getEnumConstants()) {
            String str3 = "[" + str + "," + enumR + "]";
            if (c0167k.m824a(enumR, null) == null) {
                arrayList.add("Missing " + str3);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            it.next();
            str2 = f944a;
        }
    }

    private C0167k m818d(String str) {
        C0167k c0167k = null;
        if (str == null || str.length() < 2) {
            return null;
        }
        if (f945b.containsKey(str)) {
            String str2 = (String) f945b.get(str);
            C0167k c0167k2 = (C0167k) this.f948d.get(str2);
            String str3 = f944a;
            new StringBuilder("Overriding locale specifier ").append(str).append(" with ").append(str2);
            c0167k = c0167k2;
        }
        if (c0167k == null) {
            c0167k = (C0167k) this.f948d.get(str.contains("_") ? str : str + "_" + Locale.getDefault().getCountry());
        }
        if (c0167k == null) {
            c0167k = (C0167k) this.f948d.get(str);
        }
        if (c0167k != null) {
            return c0167k;
        }
        return (C0167k) this.f948d.get(str.substring(0, 2));
    }

    public final String m819a(Enum enumR) {
        return m820a(enumR, this.f949e);
    }

    public final String m820a(Enum enumR, C0167k c0167k) {
        String toUpperCase = Locale.getDefault().getCountry().toUpperCase(Locale.US);
        String a = c0167k.m824a(enumR, toUpperCase);
        if (a == null) {
            new StringBuilder("Missing localized string for [").append(this.f949e.m823a()).append(",Key.").append(enumR.toString()).append("]");
            a = f944a;
            a = ((C0167k) this.f948d.get("en")).m824a(enumR, toUpperCase);
        }
        if (a != null) {
            return a;
        }
        a = f944a;
        new StringBuilder("Missing localized string for [en,Key.").append(enumR.toString()).append("], so defaulting to keyname");
        return enumR.toString();
    }

    public final void m821a(String str) {
        String str2 = f944a;
        new StringBuilder("setLanguage(").append(str).append(")");
        this.f949e = null;
        this.f949e = m822b(str);
        if (f947g || this.f949e != null) {
            str2 = f944a;
            new StringBuilder("setting locale to:").append(this.f949e.m823a());
            return;
        }
        throw new AssertionError();
    }

    public final C0167k m822b(String str) {
        C0167k c0167k = null;
        if (str != null) {
            c0167k = m818d(str);
        }
        if (c0167k == null) {
            String locale = Locale.getDefault().toString();
            String str2 = f944a;
            new StringBuilder().append(str).append(" not found.  Attempting to look for ").append(locale);
            c0167k = m818d(locale);
        }
        if (c0167k == null) {
            locale = f944a;
            c0167k = (C0167k) this.f948d.get("en");
        }
        if (f947g || c0167k != null) {
            return c0167k;
        }
        throw new AssertionError();
    }
}
