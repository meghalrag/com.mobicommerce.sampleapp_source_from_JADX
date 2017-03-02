package io.card.payment;

import android.content.Context;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Base64;
import com.paypal.android.sdk.payments.BuildConfig;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;

/* renamed from: io.card.payment.a */
public final class C0158a {
    private static int f864a;
    private static int f865b;
    private final DefaultHttpClient f866c;
    private final HttpContext f867d;
    private ThreadPoolExecutor f868e;
    private final Map f869f;
    private final Map f870g;

    static {
        f864a = 10;
        f865b = 10000;
    }

    public C0158a() {
        HttpParams basicHttpParams = new BasicHttpParams();
        ConnManagerParams.setTimeout(basicHttpParams, (long) f865b);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(f864a));
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 10);
        HttpConnectionParams.setSoTimeout(basicHttpParams, f865b);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, f865b);
        HttpConnectionParams.setTcpNoDelay(basicHttpParams, true);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD);
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUserAgent(basicHttpParams, String.format("android-async-http/%s (http://loopj.com/android-async-http)", new Object[]{"1.3.2"}));
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        ClientConnectionManager threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
        this.f867d = new SyncBasicHttpContext(new BasicHttpContext());
        this.f866c = new DefaultHttpClient(threadSafeClientConnManager, basicHttpParams);
        this.f866c.addRequestInterceptor(new ai(this));
        this.f866c.addResponseInterceptor(new aj());
        this.f866c.setHttpRequestRetryHandler(new an());
        this.f868e = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        this.f869f = new WeakHashMap();
        this.f870g = new HashMap();
    }

    private void m768a(DefaultHttpClient defaultHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, String str, C0162f c0162f, Context context) {
        if (str != null) {
            httpUriRequest.addHeader("Content-Type", str);
        }
        Future submit = this.f868e.submit(new al(defaultHttpClient, httpContext, httpUriRequest, c0162f));
        if (context != null) {
            List list = (List) this.f869f.get(context);
            if (list == null) {
                list = new LinkedList();
                this.f869f.put(context, list);
            }
            list.add(new WeakReference(submit));
        }
    }

    public final void m769a(Context context, String str, C0162f c0162f) {
        m768a(this.f866c, this.f867d, new HttpGet(str), null, c0162f, context);
    }

    public final void m770a(Context context, String str, HttpEntity httpEntity, String str2, C0162f c0162f) {
        DefaultHttpClient defaultHttpClient = this.f866c;
        HttpContext httpContext = this.f867d;
        HttpUriRequest httpPost = new HttpPost(str);
        if (httpEntity != null) {
            httpPost.setEntity(httpEntity);
        }
        m768a(defaultHttpClient, httpContext, httpPost, str2, c0162f, context);
    }

    public final void m771a(String str) {
        HttpProtocolParams.setUserAgent(this.f866c.getParams(), str);
    }

    public final void m772a(String str, String str2) {
        this.f870g.remove("Authorization");
        if (str != null) {
            if (str == null) {
                str = BuildConfig.VERSION_NAME;
            }
            this.f870g.put("Authorization", String.format("Basic %s", new Object[]{Base64.encodeToString(str.getBytes(), 2)}));
        }
    }

    public final void m773a(SSLSocketFactory sSLSocketFactory) {
        this.f866c.getConnectionManager().getSchemeRegistry().register(new Scheme("https", sSLSocketFactory, 443));
    }
}
