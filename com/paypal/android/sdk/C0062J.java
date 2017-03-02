package com.paypal.android.sdk;

import android.util.Log;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* renamed from: com.paypal.android.sdk.J */
class C0062J extends Thread {
    private static final String f7a;
    private C0065N f8b;
    private String f9c;
    private List f10d;
    private boolean f11e;
    private ah f12f;
    private C0293F f13g;

    static {
        f7a = C0062J.class.getSimpleName();
    }

    C0062J(String str, C0074e c0074e, C0065N c0065n, int i, String str2, String str3, boolean z, int i2) {
        this.f10d = Collections.synchronizedList(new LinkedList());
        this.f8b = c0065n;
        this.f9c = str;
        this.f12f = new ah(this.f8b, i2);
        this.f13g = new C0293F(str, c0074e, c0065n, 90000, str2, str3, z);
        start();
    }

    public final void m26a() {
        if (!this.f11e) {
            this.f13g.m1163a();
            this.f11e = true;
            synchronized (this.f10d) {
                this.f10d.notifyAll();
            }
            interrupt();
            while (isAlive()) {
                try {
                    Thread.sleep(10);
                    String str = f7a;
                    new StringBuilder("Waiting for ").append(getClass().getSimpleName()).append(" to die");
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public final void m27a(ap apVar) {
        synchronized (this.f10d) {
            this.f10d.add(apVar);
            String str = f7a;
            new StringBuilder("Queued ").append(apVar.m72B());
            this.f10d.notifyAll();
        }
    }

    public void run() {
        ap apVar;
        String str = f7a;
        new StringBuilder("Starting ").append(getClass().getSimpleName());
        while (!this.f11e) {
            synchronized (this.f10d) {
                if (this.f10d.isEmpty()) {
                    try {
                        this.f10d.wait();
                        apVar = null;
                    } catch (InterruptedException e) {
                        apVar = null;
                    }
                } else {
                    apVar = (ap) this.f10d.remove(0);
                }
            }
            if (apVar != null) {
                try {
                    apVar.m86d(apVar.m77a());
                } catch (Throwable e2) {
                    Log.e("paypal.sdk", "Exception computing request", e2);
                    apVar.m79a(new ar(C0076g.PARSE_RESPONSE_ERROR.toString(), "JSON Exception in computeRequest", e2.getMessage()));
                } catch (Throwable e22) {
                    Log.e("paypal.sdk", "Exception computing request", e22);
                    apVar.m79a(new ar(C0076g.PARSE_RESPONSE_ERROR.toString(), "Unsupported encoding", e22.getMessage()));
                }
                if (!(this.f9c.equals(PayPalConfiguration.ENVIRONMENT_NO_NETWORK) ? this.f12f : this.f13g).m28b(apVar)) {
                    this.f8b.m30a(apVar);
                }
            }
        }
        str = f7a;
        new StringBuilder().append(getClass().getSimpleName()).append(" exiting");
        this.f13g.m1164b();
    }
}
