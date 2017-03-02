package com.paypal.android.sdk;

import android.os.Handler;
import android.os.Message;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;

public final class bj extends bo {
    private Handler f1055a;
    private String f1056b;
    private String f1057c;
    private String f1058d;
    private ba f1059e;

    public bj(String str, String str2, String str3, ba baVar, Handler handler) {
        this.f1055a = handler;
        this.f1056b = str;
        this.f1057c = str2;
        this.f1058d = str3;
        this.f1059e = baVar;
    }

    public final void run() {
        Reader bufferedReader;
        Object e;
        Throwable th;
        this.f1055a.sendMessage(Message.obtain(this.f1055a, 20, this.f1056b));
        Reader reader = null;
        try {
            HttpClient defaultHttpClient = new DefaultHttpClient();
            HttpConnectionParams.setSoTimeout(defaultHttpClient.getParams(), 10000);
            HttpConnectionParams.setConnectionTimeout(defaultHttpClient.getParams(), 10000);
            HttpUriRequest httpGet = new HttpGet(this.f1056b);
            httpGet.setHeader("User-Agent", String.format("%s/%s/%s/%s/Android", new Object[]{this.f1059e.m176a(), this.f1059e.m178b(), this.f1058d, this.f1057c}));
            httpGet.setHeader("Accept-Language", "en-us");
            bufferedReader = new BufferedReader(new InputStreamReader(defaultHttpClient.execute(httpGet).getEntity().getContent(), "UTF-8"));
            try {
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        stringBuffer.append(readLine);
                    } else {
                        this.f1055a.sendMessage(Message.obtain(this.f1055a, 22, stringBuffer.toString()));
                        C0069T.m43a(bufferedReader);
                        bp.m235a().m238b(this);
                        return;
                    }
                }
            } catch (RuntimeException e2) {
                e = e2;
                try {
                    this.f1055a.sendMessage(Message.obtain(this.f1055a, 21, e));
                    C0069T.m43a(bufferedReader);
                    bp.m235a().m238b(this);
                } catch (Throwable th2) {
                    th = th2;
                    reader = bufferedReader;
                    C0069T.m43a(reader);
                    bp.m235a().m238b(this);
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                reader = bufferedReader;
                try {
                    this.f1055a.sendMessage(Message.obtain(this.f1055a, 21, e));
                    C0069T.m43a(reader);
                    bp.m235a().m238b(this);
                } catch (Throwable th3) {
                    th = th3;
                    C0069T.m43a(reader);
                    bp.m235a().m238b(this);
                    throw th;
                }
            }
        } catch (RuntimeException e4) {
            e = e4;
            bufferedReader = null;
            this.f1055a.sendMessage(Message.obtain(this.f1055a, 21, e));
            C0069T.m43a(bufferedReader);
            bp.m235a().m238b(this);
        } catch (Exception e5) {
            e = e5;
            this.f1055a.sendMessage(Message.obtain(this.f1055a, 21, e));
            C0069T.m43a(reader);
            bp.m235a().m238b(this);
        }
    }
}
