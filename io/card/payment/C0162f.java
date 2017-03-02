package io.card.payment;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.CursorAdapter;
import android.widget.TextView;
import com.paypal.android.sdk.payments.BuildConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.util.EntityUtils;

/* renamed from: io.card.payment.f */
public class C0162f {
    private Handler f940a;

    public C0162f() {
        if (Looper.myLooper() != null) {
            this.f940a = new am(this);
        }
    }

    private Message m804a(int i, Object obj) {
        if (this.f940a != null) {
            return this.f940a.obtainMessage(i, obj);
        }
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = obj;
        return obtain;
    }

    public static void m805a(Activity activity, TextView textView, String str, String str2, Drawable drawable) {
        if (str2 == null) {
            str2 = BuildConfig.VERSION_NAME;
        }
        activity.setTitle(str2 + str);
        if (C0162f.m807c()) {
            ActionBar actionBar = activity.getActionBar();
            actionBar.setBackgroundDrawable(C0149N.f822b);
            actionBar.setTitle(str);
            TextView textView2 = (TextView) activity.findViewById(Resources.getSystem().getIdentifier("action_bar_title", "id", "android"));
            if (textView2 != null) {
                textView2.setTextColor(-1);
            }
            actionBar.setDisplayHomeAsUpEnabled(false);
            if (drawable == null || VERSION.SDK_INT < 14) {
                actionBar.setDisplayShowHomeEnabled(false);
            } else {
                actionBar.setIcon(drawable);
            }
            if (textView != null) {
                textView.setVisibility(8);
            }
        } else if (textView != null) {
            textView.setText(str);
        }
    }

    private void m806b(Message message) {
        if (this.f940a != null) {
            this.f940a.sendMessage(message);
        } else {
            m810a(message);
        }
    }

    public static boolean m807c() {
        return VERSION.SDK_INT >= 11;
    }

    public static boolean m808d() {
        return VERSION.SDK_INT >= 11;
    }

    protected final void m809a() {
        m806b(m804a(2, null));
    }

    protected final void m810a(Message message) {
        switch (message.what) {
            case DialogFragment.STYLE_NORMAL /*0*/:
                m811a((String) message.obj);
            case CursorAdapter.FLAG_AUTO_REQUERY /*1*/:
                m812a((Throwable) ((Object[]) message.obj)[0]);
            default:
        }
    }

    public void m811a(String str) {
    }

    public void m812a(Throwable th) {
    }

    protected final void m813a(Throwable th, String str) {
        m806b(m804a(1, new Object[]{th, str}));
    }

    final void m814a(HttpResponse httpResponse) {
        Object obj = null;
        StatusLine statusLine = httpResponse.getStatusLine();
        try {
            HttpEntity entity = httpResponse.getEntity();
            obj = EntityUtils.toString(entity != null ? new BufferedHttpEntity(entity) : null);
        } catch (Throwable e) {
            m813a(e, null);
        }
        int statusCode = statusLine.getStatusCode();
        if (statusCode < 200 || 300 <= statusCode) {
            m813a(new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase()), (String) obj);
        } else {
            m806b(m804a(0, obj));
        }
    }

    protected final void m815b() {
        m806b(m804a(3, null));
    }
}
