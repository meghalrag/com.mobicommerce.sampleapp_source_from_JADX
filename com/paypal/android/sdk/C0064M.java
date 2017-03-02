package com.paypal.android.sdk;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.CursorAdapter;
import java.lang.ref.WeakReference;

/* renamed from: com.paypal.android.sdk.M */
final class C0064M extends Handler {
    private WeakReference f14a;

    public C0064M(C0232L c0232l) {
        this.f14a = new WeakReference(c0232l);
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER /*2*/:
                C0232L c0232l = (C0232L) this.f14a.get();
                if (c0232l != null) {
                    c0232l.f962e.m3a((ap) message.obj, 0);
                }
            default:
        }
    }
}
