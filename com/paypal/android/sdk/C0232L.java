package com.paypal.android.sdk;

import android.os.Message;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.paypal.android.sdk.L */
public final class C0232L implements C0065N {
    private C0074e f958a;
    private C0078i f959b;
    private C0064M f960c;
    private C0077h f961d;
    private final C0058C f962e;
    private C0062J f963f;
    private long f964g;

    public C0232L(C0077h c0077h, C0074e c0074e, C0078i c0078i, boolean z, int i) {
        this.f962e = new C0058C();
        this.f964g = 0;
        this.f961d = c0077h;
        this.f958a = c0074e;
        this.f959b = c0078i;
        this.f960c = new C0064M(this);
        String a = c0077h.m342a();
        CharSequence c = this.f958a.m340c();
        if (C0069T.m46a(c)) {
            throw new RuntimeException("User agent not set correctly.  It must be set to a non-null, non-empty string in the coreEnvironment.");
        }
        this.f963f = new C0062J(a, c0074e, this, 90000, c, C0068S.m32a(this.f959b, c0074e), z, i);
    }

    private void m847b(ap apVar) {
        this.f963f.m27a(apVar);
    }

    public final String m848a(C0071b c0071b) {
        return (this.f961d == null || this.f961d.m345b() == null) ? null : (String) this.f961d.m345b().get(c0071b);
    }

    public final void m849a() {
        this.f963f.m26a();
    }

    public final void m850a(C0060E c0060e) {
        this.f962e.m2a(c0060e);
    }

    public final void m851a(C0069T c0069t, String str) {
        if (c0069t.f22e) {
            m847b(new C0233R(this, this.f958a, this.f959b, c0069t));
        } else {
            m847b(new C0294Q(this, this.f958a, this.f959b, c0069t, str));
        }
    }

    public final void m852a(ap apVar) {
        apVar.m89f();
        be.m200a().m226e();
        if (!apVar.m88e()) {
            Message message = new Message();
            message.what = 2;
            message.obj = apVar;
            this.f960c.sendMessage(message);
        }
    }

    public final void m853a(C0123q c0123q, String str, boolean z, String str2, boolean z2, String str3) {
        m847b(new ab(this, this.f958a, this.f961d.m342a(), str, this.f959b, c0123q, z, str2, z2, str3));
    }

    public final void m854a(String str) {
        m847b(new ae(this.f961d.m342a(), this, this.f958a, this.f959b, str));
    }

    public final void m855a(String str, String str2) {
        m847b(new aa(this, this.f958a, this.f959b, this.f961d.m342a(), str, str2));
    }

    public final void m856a(String str, String str2, String str3, C0125s c0125s, Map map, ao[] aoVarArr, String str4, boolean z, String str5, String str6, String str7, JSONObject jSONObject, boolean z2, String str8, String str9, String str10) {
        m847b(new C0297X(this, this.f958a, this.f961d.m342a(), this.f959b, str, str2, null, c0125s, map, aoVarArr, str4, z, str5, str6, str7, z2).m1184a(str8).m1186b(str9).m1188c(str10));
    }

    public final void m857a(String str, String str2, String str3, String str4, C0125s c0125s, Map map, ao[] aoVarArr, String str5, boolean z, String str6, String str7, String str8, String str9, String str10, String str11) {
        m847b(new C0302Y(this, this.f958a, this.f959b, str, str2, str3, str4, c0125s, map, aoVarArr, str5, z, str6, str7, str8).m1256a(str9).m1258b(str10).m1259c(str11));
    }

    public final void m858a(String str, String str2, String str3, String str4, String str5, int i, int i2) {
        m847b(new ag(this, this.f958a, this.f959b, str, str2, str3, str4, str5, i, i2));
    }

    public final void m859a(String str, String str2, String str3, String str4, String str5, int i, int i2, String str6, C0125s c0125s, Map map, ao[] aoVarArr, String str7, boolean z, String str8, String str9, String str10, String str11, String str12, String str13) {
        m847b(new C0302Y(this, this.f958a, this.f959b, str, str2, str3, str4, str5, i, i2, null, c0125s, map, aoVarArr, str7, z, str8, str9, str10).m1256a(str11).m1258b(str12).m1259c(str13));
    }

    public final void m860a(String str, String str2, String str3, String str4, List list) {
        m847b(new C0300W(this, this.f958a, this.f961d.m342a(), this.f959b, str, str2, str3, list));
    }

    public final void m861a(String str, String str2, String str3, String str4, boolean z, String str5, String str6, JSONObject jSONObject, JSONObject jSONObject2) {
        m847b(new C0295U(this, this.f958a, this.f961d.m342a(), this.f959b, str, str2, z, str3, str4, str5, str6, jSONObject, jSONObject2));
    }

    public final String m862b() {
        return this.f961d.m342a();
    }

    public final void m863b(C0060E c0060e) {
        this.f962e.m4b(c0060e);
    }

    public final void m864b(String str, String str2) {
        m847b(new C0301Z(this, this.f958a, this.f959b, str, str2));
    }
}
