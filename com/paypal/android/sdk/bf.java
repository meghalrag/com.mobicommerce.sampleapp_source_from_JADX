package com.paypal.android.sdk;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.CursorAdapter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

final class bf extends Handler {
    private /* synthetic */ be f355a;

    bf(be beVar) {
        this.f355a = beVar;
    }

    public final void handleMessage(Message message) {
        try {
            switch (message.what) {
                case CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER /*2*/:
                    Object queryParameter;
                    try {
                        queryParameter = Uri.parse("?" + ((String) message.obj)).getQueryParameter("responseEnvelope.ack");
                    } catch (UnsupportedOperationException e) {
                        queryParameter = null;
                    }
                    if ("Success".equals(queryParameter)) {
                        Map hashMap = new HashMap(this.f355a.f345n);
                        for (Entry entry : this.f355a.f345n.entrySet()) {
                            Object obj = null;
                            for (Entry entry2 : this.f355a.f346o.entrySet()) {
                                Object obj2 = (!((String) entry2.getKey()).equals(entry.getKey()) || (entry2.getValue() != null ? entry2.getValue().equals(entry.getValue()) : entry.getValue() == null)) ? obj : 1;
                                obj = obj2;
                            }
                            if (obj != null) {
                                hashMap.remove(entry.getKey());
                            }
                        }
                        this.f355a.f345n = hashMap;
                        this.f355a.f342k = this.f355a.f343l;
                        this.f355a.f343l = null;
                        return;
                    }
                    return;
                case 12:
                    bb bbVar = (bb) message.obj;
                    if (bbVar != null) {
                        be.m205a(this.f355a, bbVar);
                        return;
                    }
                    return;
                default:
                    return;
            }
        } catch (Throwable e2) {
            bq.m244a("RiskComponent", null, e2);
        }
        bq.m244a("RiskComponent", null, e2);
    }
}
