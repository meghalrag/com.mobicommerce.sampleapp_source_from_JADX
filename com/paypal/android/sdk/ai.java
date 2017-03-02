package com.paypal.android.sdk;

final class ai implements Runnable {
    private /* synthetic */ ap f39a;
    private /* synthetic */ ah f40b;

    ai(ah ahVar, ap apVar) {
        this.f40b = ahVar;
        this.f39a = apVar;
    }

    public final void run() {
        ah.f1233a;
        new StringBuilder("Mock executing ").append(this.f39a.m72B()).append(" request: ").append(this.f39a.m92v());
        String d = this.f39a.m85d();
        if (C0069T.m51c(d)) {
            throw new RuntimeException("Blank mock value for " + this.f39a.m94x());
        }
        this.f39a.m94x().toString();
        new StringBuilder("mock response:").append(d);
        this.f39a.m87e(d);
        try {
            Thread.sleep((long) this.f40b.f1236d);
        } catch (InterruptedException e) {
            this.f39a.m94x().toString();
        }
        C0229A.m840a(this.f39a);
        if (!(this.f39a instanceof C0233R) && !(this.f39a instanceof C0294Q)) {
            this.f40b.f1234b.m30a(this.f39a);
        }
    }
}
