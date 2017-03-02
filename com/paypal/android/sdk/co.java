package com.paypal.android.sdk;

import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.CursorAdapter;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.util.EntityUtils;

public final class co extends cm {
    private static String[] f1081a;

    static {
        f1081a = new String[]{"image/jpeg", "image/png"};
    }

    protected final void m994a(Message message) {
        switch (message.what) {
            case DialogFragment.STYLE_NORMAL /*0*/:
                ((Integer) ((Object[]) message.obj)[0]).intValue();
            case CursorAdapter.FLAG_AUTO_REQUERY /*1*/:
                Object[] objArr = (Object[]) message.obj;
                byte[] bArr = (byte[]) objArr[1];
                m322a((Throwable) objArr[0], (String) objArr[3]);
            default:
                super.m320a(message);
        }
    }

    protected final void m995a(Throwable th, byte[] bArr, String str) {
        m327b(m318a(1, new Object[]{th, bArr, str}));
    }

    final void m996a(HttpResponse httpResponse) {
        byte[] bArr = null;
        StatusLine statusLine = httpResponse.getStatusLine();
        Header[] headers = httpResponse.getHeaders("PayPal-Debug-Id");
        String value = (headers == null || headers.length <= 0) ? null : headers[1].getValue();
        Header[] headers2 = httpResponse.getHeaders("Content-Type");
        if (headers2.length != 1) {
            m995a(new HttpResponseException(statusLine.getStatusCode(), "None, or more than one, Content-Type Header found!"), null, value);
            return;
        }
        Header header = headers2[0];
        int i = 0;
        for (String equals : f1081a) {
            if (equals.equals(header.getValue())) {
                i = 1;
            }
        }
        if (i == 0) {
            m995a(new HttpResponseException(statusLine.getStatusCode(), "Content-Type not allowed!"), null, value);
            return;
        }
        try {
            HttpEntity entity = httpResponse.getEntity();
            bArr = EntityUtils.toByteArray(entity != null ? new BufferedHttpEntity(entity) : null);
        } catch (Throwable e) {
            m995a(e, null, value);
        }
        if (statusLine.getStatusCode() >= 300) {
            m995a(new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase()), bArr, value);
        } else {
            m327b(m318a(0, new Object[]{Integer.valueOf(statusLine.getStatusCode()), bArr, value}));
        }
    }
}
