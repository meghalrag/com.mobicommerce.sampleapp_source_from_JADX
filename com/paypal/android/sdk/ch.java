package com.paypal.android.sdk;

import android.content.Context;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
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

public final class ch {
    private static int f437a;
    private static int f438b;
    private final DefaultHttpClient f439c;
    private final HttpContext f440d;
    private ThreadPoolExecutor f441e;
    private final Map f442f;
    private final Map f443g;

    static {
        f437a = 10;
        f438b = 10000;
    }

    public ch() {
        HttpParams basicHttpParams = new BasicHttpParams();
        ConnManagerParams.setTimeout(basicHttpParams, (long) f438b);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(f437a));
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 10);
        HttpConnectionParams.setSoTimeout(basicHttpParams, f438b);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, f438b);
        HttpConnectionParams.setTcpNoDelay(basicHttpParams, true);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD);
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUserAgent(basicHttpParams, String.format("android-async-http/%s (http://loopj.com/android-async-http)", new Object[]{"1.4.3"}));
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        ClientConnectionManager threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
        this.f440d = new SyncBasicHttpContext(new BasicHttpContext());
        this.f439c = new DefaultHttpClient(threadSafeClientConnManager, basicHttpParams);
        this.f439c.addRequestInterceptor(new ci(this));
        this.f439c.addResponseInterceptor(new cj(this));
        this.f439c.setHttpRequestRetryHandler(new cq(5));
        this.f441e = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        this.f442f = new WeakHashMap();
        this.f443g = new HashMap();
    }

    private void m307a(DefaultHttpClient defaultHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, String str, cm cmVar, Context context) {
        if (str != null) {
            httpUriRequest.addHeader("Content-Type", str);
        }
        Future submit = this.f441e.submit(new cl(defaultHttpClient, httpContext, httpUriRequest, cmVar));
        if (context != null) {
            List list = (List) this.f442f.get(context);
            if (list == null) {
                list = new LinkedList();
                this.f442f.put(context, list);
            }
            list.add(new WeakReference(submit));
        }
    }

    public final HttpClient m308a() {
        return this.f439c;
    }

    public final void m309a(int i) {
        HttpParams params = this.f439c.getParams();
        ConnManagerParams.setTimeout(params, (long) i);
        HttpConnectionParams.setSoTimeout(params, i);
        HttpConnectionParams.setConnectionTimeout(params, i);
    }

    public final void m310a(Context context, String str, Header[] headerArr, cm cmVar) {
        HttpUriRequest httpDelete = new HttpDelete(str);
        if (headerArr != null) {
            httpDelete.setHeaders(headerArr);
        }
        m307a(this.f439c, this.f440d, httpDelete, null, cmVar, context);
    }

    public final void m311a(Context context, String str, Header[] headerArr, cp cpVar, cm cmVar) {
        String str2 = null;
        if (str2 != null) {
            String a = str2.m329a();
            str = str.indexOf("?") == -1 ? str + "?" + a : str + "&" + a;
        }
        HttpUriRequest httpGet = new HttpGet(str);
        if (headerArr != null) {
            httpGet.setHeaders(headerArr);
        }
        m307a(this.f439c, this.f440d, httpGet, str2, cmVar, context);
    }

    public final void m312a(Context context, String str, Header[] headerArr, HttpEntity httpEntity, String str2, cm cmVar) {
        HttpUriRequest httpPost = new HttpPost(str);
        if (httpEntity != null) {
            httpPost.setEntity(httpEntity);
        }
        if (headerArr != null) {
            httpPost.setHeaders(headerArr);
        }
        m307a(this.f439c, this.f440d, httpPost, null, cmVar, context);
    }

    public final void m313a(Context context, boolean z) {
        List<WeakReference> list = (List) this.f442f.get(context);
        if (list != null) {
            for (WeakReference weakReference : list) {
                Future future = (Future) weakReference.get();
                if (future != null) {
                    future.cancel(true);
                }
            }
        }
        this.f442f.remove(context);
    }

    public final void m314a(String str) {
        HttpProtocolParams.setUserAgent(this.f439c.getParams(), str);
    }

    public final void m315a(ThreadPoolExecutor threadPoolExecutor) {
        this.f441e = threadPoolExecutor;
    }

    public final void m316a(SSLSocketFactory sSLSocketFactory) {
        this.f439c.getConnectionManager().getSchemeRegistry().register(new Scheme("https", sSLSocketFactory, 443));
    }
}
