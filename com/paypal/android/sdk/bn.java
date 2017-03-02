package com.paypal.android.sdk;

import android.os.Handler;
import android.os.Message;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public final class bn extends bo {
    private String f1063a;
    private List f1064b;
    private List f1065c;
    private Handler f1066d;
    private boolean f1067e;
    private SSLSocketFactory f1068f;

    public bn(String str, List list, Handler handler, boolean z, SSLSocketFactory sSLSocketFactory) {
        this.f1063a = str;
        this.f1064b = list;
        this.f1065c = new ArrayList();
        this.f1066d = handler;
        this.f1067e = z;
        this.f1068f = sSLSocketFactory;
    }

    public final void run() {
        Object e;
        Throwable th;
        this.f1066d.sendMessage(Message.obtain(this.f1066d, 0, this.f1063a));
        Reader reader = null;
        try {
            HttpClient defaultHttpClient;
            if (!this.f1067e) {
                this.f1065c.add(new BasicNameValuePair("CLIENT-AUTH", "No cert"));
            }
            this.f1065c.add(new BasicNameValuePair("X-PAYPAL-RESPONSE-DATA-FORMAT", "NV"));
            this.f1065c.add(new BasicNameValuePair("X-PAYPAL-REQUEST-DATA-FORMAT", "NV"));
            this.f1065c.add(new BasicNameValuePair("X-PAYPAL-SERVICE-VERSION", "1.0.0"));
            if (this.f1067e) {
                defaultHttpClient = new DefaultHttpClient();
            } else {
                SchemeRegistry schemeRegistry = new SchemeRegistry();
                schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
                if (this.f1068f == null) {
                    schemeRegistry.register(new Scheme("https", new bk(), 443));
                } else {
                    schemeRegistry.register(new Scheme("https", this.f1068f, 443));
                }
                HttpParams basicHttpParams = new BasicHttpParams();
                basicHttpParams.setParameter("http.conn-manager.max-total", Integer.valueOf(30));
                basicHttpParams.setParameter("http.conn-manager.max-per-route", new ConnPerRouteBean(30));
                basicHttpParams.setParameter("http.protocol.expect-continue", Boolean.valueOf(false));
                HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
                Object defaultHttpClient2 = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
            }
            HttpConnectionParams.setSoTimeout(defaultHttpClient.getParams(), 10000);
            HttpConnectionParams.setConnectionTimeout(defaultHttpClient.getParams(), 10000);
            HttpUriRequest httpPost = new HttpPost(this.f1063a);
            for (NameValuePair nameValuePair : this.f1065c) {
                httpPost.addHeader(nameValuePair.getName(), nameValuePair.getValue());
            }
            httpPost.setEntity(new UrlEncodedFormEntity(this.f1064b, "UTF-8"));
            Reader bufferedReader = new BufferedReader(new InputStreamReader(defaultHttpClient.execute(httpPost).getEntity().getContent(), "UTF-8"));
            try {
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        stringBuffer.append(readLine);
                    } else {
                        this.f1066d.sendMessage(Message.obtain(this.f1066d, 2, stringBuffer.toString()));
                        C0069T.m43a(bufferedReader);
                        bp.m235a().m238b(this);
                        return;
                    }
                }
            } catch (RuntimeException e2) {
                e = e2;
                reader = bufferedReader;
                try {
                    this.f1066d.sendMessage(Message.obtain(this.f1066d, 1, e));
                    C0069T.m43a(reader);
                    bp.m235a().m238b(this);
                } catch (Throwable th2) {
                    th = th2;
                    C0069T.m43a(reader);
                    bp.m235a().m238b(this);
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                reader = bufferedReader;
                this.f1066d.sendMessage(Message.obtain(this.f1066d, 1, e));
                C0069T.m43a(reader);
                bp.m235a().m238b(this);
            } catch (Throwable th3) {
                th = th3;
                reader = bufferedReader;
                C0069T.m43a(reader);
                bp.m235a().m238b(this);
                throw th;
            }
        } catch (RuntimeException e4) {
            e = e4;
            this.f1066d.sendMessage(Message.obtain(this.f1066d, 1, e));
            C0069T.m43a(reader);
            bp.m235a().m238b(this);
        } catch (Exception e5) {
            e = e5;
            this.f1066d.sendMessage(Message.obtain(this.f1066d, 1, e));
            C0069T.m43a(reader);
            bp.m235a().m238b(this);
        }
    }
}
