package com.paypal.android.sdk;

import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.http.Header;
import org.apache.http.client.HttpResponseException;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;

/* renamed from: com.paypal.android.sdk.F */
class C0293F extends C0229A {
    private static final String f1195a;
    private String f1196b;
    private C0065N f1197c;
    private ch f1198d;
    private ch f1199e;
    private C0074e f1200f;

    static {
        f1195a = C0293F.class.getSimpleName();
    }

    public C0293F(String str, C0074e c0074e, C0065N c0065n, int i, String str2, String str3, boolean z) {
        this.f1196b = str;
        this.f1200f = c0074e;
        this.f1197c = c0065n;
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        boolean z2 = (str.equals(PayPalConfiguration.ENVIRONMENT_PRODUCTION) || str.startsWith(PayPalConfiguration.ENVIRONMENT_SANDBOX) || str.equals(PayPalConfiguration.ENVIRONMENT_NO_NETWORK)) ? false : true;
        boolean z3 = z2 && !z;
        this.f1198d = C0057B.m0a(i, z3, !z2, str2);
        this.f1198d.m315a(threadPoolExecutor);
        this.f1199e = C0057B.m0a(i, true, false, str3);
        this.f1199e.m315a(threadPoolExecutor);
    }

    private static String m1155a(String str, String str2) {
        if (!str.endsWith("/")) {
            str = str + "/";
        }
        return str + str2;
    }

    static /* synthetic */ void m1156a(C0293F c0293f, ap apVar, IOException iOException) {
        String str = f1195a;
        new StringBuilder().append(apVar.m72B()).append(" failure.");
        if (iOException instanceof HttpResponseException) {
            int statusCode = ((HttpResponseException) iOException).getStatusCode();
            Log.e("paypal.sdk", "request failure with http statusCode:" + statusCode + ",exception:" + iOException.toString());
            apVar.m80a(Integer.valueOf(statusCode));
            try {
                apVar.m84c();
            } catch (JSONException e) {
                apVar.m81a(C0076g.INTERNAL_SERVER_ERROR.toString(), statusCode + " http response received.  Response not parsable.", null);
            }
            if (apVar.m75E()) {
                apVar.m81a(C0076g.INTERNAL_SERVER_ERROR.toString(), statusCode + " http response received.  Response not parsable.", null);
            }
        } else {
            apVar.m79a(new ar(C0076g.SERVER_COMMUNICATION_ERROR, (Exception) iOException));
        }
        Log.e("paypal.sdk", "request failed with server response:" + apVar.m93w());
        c0293f.f1197c.m30a(apVar);
    }

    private Header[] m1157a(ap apVar, String str) {
        String str2;
        List arrayList = new ArrayList();
        for (Entry entry : apVar.m95y().entrySet()) {
            arrayList.add(new BasicHeader((String) entry.getKey(), (String) entry.getValue()));
        }
        if (str != null) {
            try {
                arrayList.add(C0059D.m5a(this.f1200f.m341d(), arrayList, str));
            } catch (InvalidKeyException e) {
                str2 = f1195a;
            } catch (NoSuchAlgorithmException e2) {
                str2 = f1195a;
            } catch (UnsupportedEncodingException e3) {
                str2 = f1195a;
            }
        }
        Header[] headerArr = (Header[]) arrayList.toArray(new Header[0]);
        for (Header header : headerArr) {
            String str3;
            if (apVar.m88e()) {
                str3 = f1195a;
                new StringBuilder().append(apVar.m72B()).append(" header: ").append(header.getName()).append("=").append(header.getValue());
            } else {
                str3 = f1195a;
                new StringBuilder().append(apVar.m72B()).append(" header: ").append(header.getName()).append("=").append(header.getValue());
            }
        }
        return headerArr;
    }

    final void m1163a() {
        this.f1198d.m313a(C0072c.m291a().m293b(), true);
        this.f1199e.m313a(C0072c.m291a().m293b(), true);
    }

    final void m1164b() {
        this.f1198d.m308a().getConnectionManager().closeIdleConnections(1, TimeUnit.MILLISECONDS);
        this.f1199e.m308a().getConnectionManager().closeIdleConnections(1, TimeUnit.MILLISECONDS);
    }

    public final boolean m1165b(ap apVar) {
        if (C0072c.m291a().m294c().m332a()) {
            String a = apVar.m78a(apVar.m94x());
            String str;
            if (apVar.m88e()) {
                str = f1195a;
                new StringBuilder().append(apVar.m72B()).append(" endpoint: ").append(a);
                str = f1195a;
                new StringBuilder().append(apVar.m72B()).append(" request: ").append(apVar.m92v());
            } else {
                str = f1195a;
                new StringBuilder().append(apVar.m72B()).append(" endpoint: ").append(a);
                str = f1195a;
                new StringBuilder().append(apVar.m72B()).append(" request: ").append(apVar.m92v());
            }
            try {
                ch chVar = apVar.m88e() ? this.f1199e : this.f1198d;
                cm c0231i = (apVar.m88e() || (apVar instanceof C0294Q)) ? new C0231I(apVar, (byte) 0) : new C0230H(apVar, (byte) 0);
                switch (C0061G.f6a[apVar.m94x().m107a().ordinal()]) {
                    case CursorAdapter.FLAG_AUTO_REQUERY /*1*/:
                        chVar.m311a(C0072c.m291a().m293b(), C0293F.m1155a(a, apVar.m92v()), m1157a(apVar, null), null, c0231i);
                        break;
                    case CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER /*2*/:
                        String v = apVar.m92v();
                        chVar.m312a(C0072c.m291a().m293b(), a, m1157a(apVar, v), new StringEntity(v, Charset.forName("UTF-8").name()), null, c0231i);
                        break;
                    case FragmentManagerImpl.ANIM_STYLE_CLOSE_ENTER /*3*/:
                        this.f1198d.m310a(C0072c.m291a().m293b(), C0293F.m1155a(a, apVar.m92v()), m1157a(apVar, null), new C0230H(apVar, (byte) 0));
                        break;
                    default:
                        throw new RuntimeException(apVar.m94x().m107a() + " not supported.");
                }
                return true;
            } catch (Exception e) {
                apVar.m79a(new ar(C0076g.INTERNAL_ERROR, e));
                return false;
            }
        }
        apVar.m79a(new ar(C0076g.SERVER_COMMUNICATION_ERROR.toString()));
        return false;
    }
}
