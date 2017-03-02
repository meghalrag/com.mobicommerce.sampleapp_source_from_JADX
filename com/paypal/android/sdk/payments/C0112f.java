package com.paypal.android.sdk.payments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* renamed from: com.paypal.android.sdk.payments.f */
final class C0112f implements OnClickListener {
    private /* synthetic */ Activity f747a;
    private /* synthetic */ int f748b;

    C0112f(Activity activity, int i) {
        this.f747a = activity;
        this.f748b = i;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f747a.removeDialog(this.f748b);
        this.f747a.finish();
    }
}
