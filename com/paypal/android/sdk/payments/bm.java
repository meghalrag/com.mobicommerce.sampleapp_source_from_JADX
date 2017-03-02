package com.paypal.android.sdk.payments;

import android.text.style.URLSpan;
import android.view.View;

final class bm extends URLSpan {
    private C0110d f743a;

    bm(URLSpan uRLSpan, C0110d c0110d) {
        super(uRLSpan.getURL());
        this.f743a = c0110d;
    }

    public final void onClick(View view) {
        this.f743a.m679a();
        super.onClick(view);
    }
}
