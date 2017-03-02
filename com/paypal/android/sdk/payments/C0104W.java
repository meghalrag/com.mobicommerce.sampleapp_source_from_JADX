package com.paypal.android.sdk.payments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/* renamed from: com.paypal.android.sdk.payments.W */
final class C0104W implements OnClickListener {
    private /* synthetic */ PayPalProfileSharingActivity f667a;

    C0104W(PayPalProfileSharingActivity payPalProfileSharingActivity) {
        this.f667a = payPalProfileSharingActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f667a.f578d.m494a(new C0238Y(this.f667a), true);
    }
}
