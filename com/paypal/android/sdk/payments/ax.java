package com.paypal.android.sdk.payments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class ax implements OnClickListener {
    private /* synthetic */ aw f718a;

    ax(aw awVar) {
        this.f718a = awVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f718a.f715a.m137a(i);
        PaymentConfirmActivity.m540a(this.f718a.f717c, this.f718a.f716b, i);
    }
}
