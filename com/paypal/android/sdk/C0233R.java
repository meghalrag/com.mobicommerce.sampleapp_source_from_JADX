package com.paypal.android.sdk;

import com.paypal.android.sdk.payments.BuildConfig;
import java.util.Calendar;

/* renamed from: com.paypal.android.sdk.R */
public final class C0233R extends ap {
    private final C0069T f965a;

    public C0233R(C0065N c0065n, C0074e c0074e, C0078i c0078i, C0069T c0069t) {
        super(C0071b.SiteCatalystRequest, c0065n, c0074e, c0078i);
        this.f965a = c0069t;
    }

    public final String m865a() {
        StringBuilder stringBuilder = new StringBuilder();
        Calendar instance = Calendar.getInstance();
        stringBuilder.append(Integer.toString(instance.get(5))).append("/").append(Integer.toString(instance.get(2))).append("/").append(Integer.toString(instance.get(1))).append(" ").append(Integer.toString(instance.get(11))).append(":").append(Integer.toString(instance.get(12))).append(":").append(Integer.toString(instance.get(13))).append(" ").append(Integer.toString(instance.get(4))).append(" ").append(Long.toString(-((long) ((instance.get(15) + instance.get(16)) / 60000))));
        String stringBuilder2 = stringBuilder.toString();
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("s").append(this.f965a.f18a).append("?AQB=1").append("&ndh=1").append("&t" + C0069T.m48b(stringBuilder2));
        stringBuilder3.append("&ch=" + C0069T.m48b(this.f965a.f20c)).append("&sv=" + this.f965a.f21d).append("&vid=" + C0069T.m48b(C0072c.m291a().m294c().m337e().replace("-", BuildConfig.VERSION_NAME)));
        C0069T.m44a(this.f965a.f19b, stringBuilder3);
        stringBuilder3.append("&AQE=1");
        return stringBuilder3.toString();
    }

    public final String m866a(C0071b c0071b) {
        return "https://paypal.112.2o7.net/b/ss/paypalglobal/0/OIP-2.1.6/";
    }

    public final void m867b() {
    }

    public final void m868c() {
    }

    public final String m869d() {
        return "mockSiteCatalystResponse";
    }

    public final boolean m870e() {
        return true;
    }
}
