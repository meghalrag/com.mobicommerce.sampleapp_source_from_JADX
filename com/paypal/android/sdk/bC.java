package com.paypal.android.sdk;

public final class bC {
    private static /* synthetic */ boolean f104d;
    private C0073d f105a;
    private String f106b;
    private C0131y f107c;

    static {
        f104d = !bC.class.desiredAssertionStatus();
    }

    public bC(C0073d c0073d, String str) {
        if (f104d || c0073d != null) {
            this.f105a = c0073d;
            this.f106b = "com.paypal.android.sdk." + str + ".";
            this.f107c = new C0131y(C0084o.m365b() + C0079j.m352b());
            return;
        }
        throw new AssertionError();
    }

    public final bD m114a() {
        C0128v a;
        String a2;
        CharSequence a3;
        bD bDVar;
        String a4 = this.f105a.m331a(this.f106b + "loginPhoneNumber", null);
        if (a4 != null) {
            try {
                a = C0128v.m702a(bz.m975d(), a4);
            } catch (C0132z e) {
            }
            a2 = this.f105a.m331a(this.f106b + "loginEmail", null);
            a3 = this.f105a.m331a(this.f106b + "loginTypePrevious", null);
            bDVar = new bD(a2, a, C0069T.m46a(a3) ? null : bF.valueOf(a3));
            return bDVar.m124d() ? bDVar : null;
        }
        a = null;
        a2 = this.f105a.m331a(this.f106b + "loginEmail", null);
        a3 = this.f105a.m331a(this.f106b + "loginTypePrevious", null);
        if (C0069T.m46a(a3)) {
        }
        bDVar = new bD(a2, a, C0069T.m46a(a3) ? null : bF.valueOf(a3));
        if (bDVar.m124d()) {
        }
    }

    public final bH m115a(String str) {
        String a = this.f105a.m331a(this.f106b + "tokenizedRedactedCardNumber", null);
        String a2 = this.f105a.m331a(this.f106b + "token", null);
        String a3 = this.f105a.m331a(this.f106b + "tokenPayerID", null);
        String a4 = this.f105a.m331a(this.f106b + "tokenValidUntil", null);
        String a5 = this.f105a.m331a(this.f106b + "tokenizedCardType", null);
        int parseInt = Integer.parseInt(this.f105a.m331a(this.f106b + "tokenizedCardExpiryMonth", "1"));
        int parseInt2 = Integer.parseInt(this.f105a.m331a(this.f106b + "tokenizedCardExpiryYear", "0"));
        Object b = this.f107c.m711b(this.f105a.m331a(this.f106b + "tokenClientId", null));
        if (C0069T.m51c(b) || !b.equals(str)) {
            return null;
        }
        bH bHVar = new bH(a2, a3, a4, a, a5, parseInt, parseInt2);
        return !bHVar.m958c() ? null : bHVar;
    }

    public final void m116a(bD bDVar) {
        String str = null;
        this.f105a.m334b(this.f106b + "loginPhoneNumber", bDVar.m118a() != null ? bDVar.m118a().m706b() : null);
        this.f105a.m334b(this.f106b + "loginEmail", bDVar.m122b());
        if (bDVar.m123c() != null) {
            str = bDVar.m123c().toString();
        }
        this.f105a.m334b(this.f106b + "loginTypePrevious", str);
    }

    public final void m117a(bH bHVar, String str) {
        String str2 = null;
        this.f105a.m334b(this.f106b + "token", bHVar.m961f());
        this.f105a.m334b(this.f106b + "tokenPayerID", bHVar.m125a());
        this.f105a.m334b(this.f106b + "tokenValidUntil", bHVar.m959d() != null ? new C0130x().format(bHVar.m959d()) : null);
        this.f105a.m334b(this.f106b + "tokenizedRedactedCardNumber", bHVar.m960e());
        if (bHVar.m962g() != null) {
            str2 = bHVar.m962g().toString();
        }
        this.f105a.m334b(this.f106b + "tokenizedCardType", str2);
        this.f105a.m334b(this.f106b + "tokenizedCardExpiryMonth", String.valueOf(bHVar.m963h()));
        this.f105a.m334b(this.f106b + "tokenizedCardExpiryYear", String.valueOf(bHVar.m964i()));
        this.f105a.m334b(this.f106b + "tokenClientId", this.f107c.m710a(str));
    }
}
