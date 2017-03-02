package com.paypal.android.sdk.payments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* renamed from: com.paypal.android.sdk.payments.g */
final class C0113g implements OnClickListener {
    private /* synthetic */ Activity f749a;

    C0113g(Activity activity) {
        this.f749a = activity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f749a.finish();
    }
}
