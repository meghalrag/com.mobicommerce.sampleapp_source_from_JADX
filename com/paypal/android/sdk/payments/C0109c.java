package com.paypal.android.sdk.payments;

import android.app.Activity;
import android.content.Intent;
import android.text.style.URLSpan;
import android.view.View;

/* renamed from: com.paypal.android.sdk.payments.c */
final class C0109c extends URLSpan {
    private Activity f744a;
    private Class f745b;
    private C0110d f746c;

    C0109c(URLSpan uRLSpan, Activity activity, Class cls, C0110d c0110d) {
        super(uRLSpan.getURL());
        this.f744a = activity;
        this.f745b = cls;
        this.f746c = c0110d;
    }

    public final void onClick(View view) {
        Intent intent = new Intent(this.f744a, this.f745b);
        this.f746c.m679a();
        this.f744a.startActivity(intent);
    }
}
