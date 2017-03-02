package com.paypal.android.sdk;

import java.util.ArrayList;
import java.util.List;

/* renamed from: com.paypal.android.sdk.C */
public class C0058C {
    private static final String f2a;
    private final List f3b;

    static {
        f2a = C0058C.class.getSimpleName();
    }

    public C0058C() {
        this.f3b = new ArrayList();
    }

    private void m1a(ap apVar, long j, List list) {
        if (apVar.m73C() < j) {
            String str = f2a;
            new StringBuilder("discarding ").append(apVar.m72B());
            return;
        }
        for (C0060E c0060e : list) {
            if (apVar instanceof ae) {
                if (apVar.m75E()) {
                    c0060e.m13a((ae) apVar);
                } else {
                    c0060e.m23b((ae) apVar);
                }
            } else if (apVar instanceof ab) {
                if (apVar.m75E()) {
                    c0060e.m12a((ab) apVar);
                } else {
                    c0060e.m22b((ab) apVar);
                }
            } else if (apVar instanceof C0302Y) {
                if (apVar.m75E()) {
                    c0060e.m10a((C0302Y) apVar);
                } else {
                    c0060e.m20b((C0302Y) apVar);
                }
            } else if (apVar instanceof ag) {
                if (apVar.m75E()) {
                    c0060e.m14a((ag) apVar);
                } else {
                    c0060e.m24b((ag) apVar);
                }
            } else if (apVar instanceof C0300W) {
                if (apVar.m75E()) {
                    c0060e.m8a((C0300W) apVar);
                } else {
                    c0060e.m18b((C0300W) apVar);
                }
            } else if (apVar instanceof C0297X) {
                if (apVar.m75E()) {
                    c0060e.m9a((C0297X) apVar);
                } else {
                    c0060e.m19b((C0297X) apVar);
                }
            } else if (apVar instanceof C0295U) {
                if (apVar.m75E()) {
                    c0060e.m7a((C0295U) apVar);
                } else {
                    c0060e.m17b((C0295U) apVar);
                }
            } else if (apVar instanceof aa) {
                if (apVar.m75E()) {
                    c0060e.m11a((aa) apVar);
                } else {
                    c0060e.m21b((aa) apVar);
                }
            } else if (apVar instanceof ap) {
                if (apVar.m75E()) {
                    c0060e.m15a(apVar);
                } else {
                    c0060e.m25b(apVar);
                }
            }
        }
    }

    public final void m2a(C0060E c0060e) {
        synchronized (this.f3b) {
            for (C0059D c0059d : this.f3b) {
                if (c0059d.f4a == c0060e) {
                    String str = f2a;
                    new StringBuilder("Ignoring attempt to re-register interface ").append(c0060e);
                    return;
                }
            }
            this.f3b.add(new C0059D(this, c0060e));
        }
    }

    public final void m3a(ap apVar, long j) {
        List arrayList = new ArrayList();
        synchronized (this.f3b) {
            for (C0059D add : this.f3b) {
                arrayList.add(0, add);
            }
        }
        arrayList = new ArrayList();
        for (C0059D add2 : this.f3b) {
            arrayList.add(0, add2.f4a);
        }
        String str = f2a;
        new StringBuilder("dispatching ").append(apVar.m72B());
        m1a(apVar, j, arrayList);
    }

    public final void m4b(C0060E c0060e) {
        synchronized (this.f3b) {
            for (C0059D c0059d : this.f3b) {
                if (c0059d.f4a == c0060e) {
                    this.f3b.remove(c0059d);
                    return;
                }
            }
            String str = f2a;
            new StringBuilder("Ignoring attempt to unregister an unregistered interface ").append(c0060e);
        }
    }
}
