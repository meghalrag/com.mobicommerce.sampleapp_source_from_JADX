package com.paypal.android.sdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class as {
    private static final String f71a;
    private static final Map f72b;
    private static final Set f73c;
    private static /* synthetic */ boolean f74g;
    private Map f75d;
    private at f76e;
    private Class f77f;

    static {
        f74g = !as.class.desiredAssertionStatus();
        f71a = as.class.getSimpleName();
        f72b = new HashMap();
        f73c = new HashSet();
        f72b.put("zh_CN", "zh-Hans");
        f72b.put("zh_TW", "zh-Hant_TW");
        f72b.put("zh_HK", "zh-Hant");
        f72b.put("en_UK", "en_GB");
        f72b.put("en_IE", "en_GB");
        f72b.put("iw_IL", "he");
        f72b.put("no", "nb");
        f73c.add("he");
        f73c.add("ar");
    }

    public as(Class cls, List list) {
        this.f75d = new LinkedHashMap();
        this.f77f = cls;
        for (at atVar : list) {
            String a = atVar.m104a();
            if (a == null) {
                throw new RuntimeException("Null localeName");
            } else if (this.f75d.containsKey(a)) {
                throw new RuntimeException("Locale " + a + " already added");
            } else {
                this.f75d.put(a, atVar);
                m99b(a);
            }
        }
        m103a(null);
    }

    private void m99b(String str) {
        at atVar = (at) this.f75d.get(str);
        List arrayList = new ArrayList();
        String str2 = f71a;
        new StringBuilder("Checking locale ").append(str);
        for (Enum enumR : (Enum[]) this.f77f.getEnumConstants()) {
            String str3 = "[" + str + "," + enumR + "]";
            if (atVar.m105a(enumR, null) == null) {
                arrayList.add("Missing " + str3);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            it.next();
            str2 = f71a;
        }
    }

    private at m100c(String str) {
        at atVar = null;
        if (str == null || str.length() < 2) {
            return null;
        }
        if (f72b.containsKey(str)) {
            String str2 = (String) f72b.get(str);
            at atVar2 = (at) this.f75d.get(str2);
            String str3 = f71a;
            new StringBuilder("Overriding locale specifier ").append(str).append(" with ").append(str2);
            atVar = atVar2;
        }
        if (atVar == null) {
            atVar = (at) this.f75d.get(str.contains("_") ? str : str + "_" + Locale.getDefault().getCountry());
        }
        if (atVar == null) {
            atVar = (at) this.f75d.get(str);
        }
        if (atVar != null) {
            return atVar;
        }
        return (at) this.f75d.get(str.substring(0, 2));
    }

    public final String m101a(Enum enumR) {
        at atVar = this.f76e;
        String toUpperCase = Locale.getDefault().getCountry().toUpperCase(Locale.US);
        String a = atVar.m105a(enumR, toUpperCase);
        if (a == null) {
            new StringBuilder("Missing localized string for [").append(this.f76e.m104a()).append(",Key.").append(enumR.toString()).append("]");
            a = f71a;
            a = ((at) this.f75d.get("en")).m105a(enumR, toUpperCase);
        }
        if (a != null) {
            return a;
        }
        a = f71a;
        new StringBuilder("Missing localized string for [en,Key.").append(enumR.toString()).append("], so defaulting to keyname");
        return enumR.toString();
    }

    public final String m102a(String str, Enum enumR) {
        String a = this.f76e.m106a(str);
        if (a != null) {
            return a;
        }
        return String.format(m101a(enumR), new Object[]{str});
    }

    public final void m103a(String str) {
        at atVar = null;
        String str2 = f71a;
        new StringBuilder("setLanguage(").append(str).append(")");
        this.f76e = null;
        if (str != null) {
            atVar = m100c(str);
        }
        if (atVar == null) {
            String locale = Locale.getDefault().toString();
            str2 = f71a;
            new StringBuilder().append(str).append(" not found.  Attempting to look for ").append(locale);
            atVar = m100c(locale);
        }
        if (atVar == null) {
            locale = f71a;
            atVar = (at) this.f75d.get("en");
        }
        if (f74g || atVar != null) {
            this.f76e = atVar;
            if (f74g || this.f76e != null) {
                locale = f71a;
                new StringBuilder("setting locale to:").append(this.f76e.m104a());
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }
}
