package com.paypal.android.sdk;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

public final class bm extends bo {
    private Context f1060a;
    private String f1061b;
    private Handler f1062c;

    public bm(Context context, String str, Handler handler) {
        this.f1060a = context;
        this.f1061b = str;
        this.f1062c = handler;
    }

    public final void run() {
        this.f1062c.sendMessage(Message.obtain(this.f1062c, 10, this.f1061b));
        try {
            this.f1062c.sendMessage(Message.obtain(this.f1062c, 12, new bb(this.f1060a, this.f1061b)));
        } catch (Exception e) {
            this.f1062c.sendMessage(Message.obtain(this.f1062c, 11, e));
        } finally {
            bp.m235a().m238b(this);
        }
    }
}
