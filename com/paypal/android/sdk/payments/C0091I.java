package com.paypal.android.sdk.payments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* renamed from: com.paypal.android.sdk.payments.I */
final class C0091I implements OnClickListener {
    private /* synthetic */ LoginActivity f505a;

    C0091I(LoginActivity loginActivity) {
        this.f505a = loginActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f505a.onBackPressed();
    }
}
