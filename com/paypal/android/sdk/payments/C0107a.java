package com.paypal.android.sdk.payments;

import com.paypal.android.sdk.C0297X;
import com.paypal.android.sdk.aa;

/* renamed from: com.paypal.android.sdk.payments.a */
class C0107a {
    private static final String f670a;
    private ag f671b;
    private Object f672c;
    private ac f673d;

    static {
        f670a = C0107a.class.getSimpleName();
    }

    C0107a() {
    }

    private void m617b(ac acVar) {
        String str = f670a;
        acVar.m628a(this.f672c);
        Object obj = ((this.f672c instanceof C0297X) || (this.f672c instanceof aa)) ? null : 1;
        this.f672c = null;
        if (obj != null) {
            m622b();
        }
    }

    final void m618a() {
        if (this.f673d != null) {
            m617b(this.f673d);
        } else {
            String str = f670a;
        }
    }

    final void m619a(ac acVar) {
        String str = f670a;
        if (this.f672c != null) {
            m617b(acVar);
        } else if (this.f671b != null) {
            acVar.m627a(this.f671b);
            this.f671b = null;
            m622b();
        } else {
            this.f673d = acVar;
        }
    }

    final void m620a(ag agVar) {
        if (this.f673d != null) {
            this.f673d.m627a(agVar);
        } else {
            this.f671b = agVar;
        }
    }

    final void m621a(Object obj) {
        this.f672c = obj;
    }

    final void m622b() {
        String str = f670a;
        this.f673d = null;
    }
}
