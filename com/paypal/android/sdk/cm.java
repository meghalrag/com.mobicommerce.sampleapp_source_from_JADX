package com.paypal.android.sdk;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.CursorAdapter;
import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.util.EntityUtils;

public class cm {
    private Handler f451a;

    public cm() {
        if (Looper.myLooper() != null) {
            this.f451a = new cn(this);
        }
    }

    protected final Message m318a(int i, Object obj) {
        if (this.f451a != null) {
            return this.f451a.obtainMessage(i, obj);
        }
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = obj;
        return obtain;
    }

    protected final void m319a() {
        m327b(m318a(2, null));
    }

    protected void m320a(Message message) {
        Object[] objArr;
        switch (message.what) {
            case DialogFragment.STYLE_NORMAL /*0*/:
                objArr = (Object[]) message.obj;
                ((Integer) objArr[0]).intValue();
                m321a((String) objArr[1], (String) objArr[2]);
            case CursorAdapter.FLAG_AUTO_REQUERY /*1*/:
                objArr = (Object[]) message.obj;
                m323a((Throwable) objArr[0], (String) objArr[1], (String) objArr[2]);
            default:
        }
    }

    public void m321a(String str, String str2) {
    }

    public void m322a(Throwable th, String str) {
    }

    public void m323a(Throwable th, String str, String str2) {
        m322a(th, str2);
    }

    protected void m324a(Throwable th, byte[] bArr, String str) {
        m327b(m318a(1, new Object[]{th, bArr, str}));
    }

    void m325a(HttpResponse httpResponse) {
        String entityUtils;
        Throwable e;
        int statusCode;
        String str = null;
        StatusLine statusLine = httpResponse.getStatusLine();
        try {
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                entityUtils = EntityUtils.toString(new BufferedHttpEntity(entity), "UTF-8");
                try {
                    Header[] headers = httpResponse.getHeaders("Paypal-Debug-Id");
                    if (headers != null && headers.length > 0) {
                        str = headers[0].getValue();
                    }
                } catch (IOException e2) {
                    e = e2;
                    m328b(e, null, null);
                    if (statusLine.getStatusCode() >= 300) {
                        statusCode = statusLine.getStatusCode();
                        m327b(m318a(0, new Object[]{Integer.valueOf(statusCode), entityUtils, str}));
                    }
                    m328b(new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase()), entityUtils, str);
                    return;
                }
            }
            entityUtils = null;
        } catch (Throwable e3) {
            e = e3;
            entityUtils = null;
            m328b(e, null, null);
            if (statusLine.getStatusCode() >= 300) {
                m328b(new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase()), entityUtils, str);
                return;
            }
            statusCode = statusLine.getStatusCode();
            m327b(m318a(0, new Object[]{Integer.valueOf(statusCode), entityUtils, str}));
        }
        if (statusLine.getStatusCode() >= 300) {
            m328b(new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase()), entityUtils, str);
            return;
        }
        statusCode = statusLine.getStatusCode();
        m327b(m318a(0, new Object[]{Integer.valueOf(statusCode), entityUtils, str}));
    }

    protected final void m326b() {
        m327b(m318a(3, null));
    }

    protected final void m327b(Message message) {
        if (this.f451a != null) {
            this.f451a.sendMessage(message);
        } else {
            m320a(message);
        }
    }

    protected final void m328b(Throwable th, String str, String str2) {
        m327b(m318a(1, new Object[]{th, str, str2}));
    }
}
