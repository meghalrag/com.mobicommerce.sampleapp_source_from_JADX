package com.paypal.android.sdk;

import java.util.ArrayList;
import java.util.List;

public final class bp {
    private static bp f363c;
    private List f364a;
    private List f365b;

    private bp() {
        this.f364a = new ArrayList();
        this.f365b = new ArrayList();
    }

    public static bp m235a() {
        if (f363c == null) {
            f363c = new bp();
        }
        return f363c;
    }

    private void m236b() {
        if (!this.f365b.isEmpty()) {
            bo boVar = (bo) this.f365b.get(0);
            this.f365b.remove(0);
            this.f364a.add(boVar);
            new Thread(boVar).start();
        }
    }

    public final void m237a(bo boVar) {
        this.f365b.add(boVar);
        if (this.f364a.size() < 3) {
            m236b();
        }
    }

    public final void m238b(bo boVar) {
        this.f364a.remove(boVar);
        m236b();
    }
}
