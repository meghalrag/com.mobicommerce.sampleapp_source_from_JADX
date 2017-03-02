package com.paypal.android.sdk.payments;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.paypal.android.sdk.payments.z */
final class C0122z implements OnClickListener {
    private /* synthetic */ FuturePaymentInfoActivity f760a;

    C0122z(FuturePaymentInfoActivity futurePaymentInfoActivity) {
        this.f760a = futurePaymentInfoActivity;
    }

    public final void onClick(View view) {
        this.f760a.finish();
    }
}
