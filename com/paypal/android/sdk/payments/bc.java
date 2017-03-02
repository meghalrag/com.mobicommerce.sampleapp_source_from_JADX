package com.paypal.android.sdk.payments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class bc implements OnClickListener {
    private /* synthetic */ ProfileSharingConsentActivity f725a;

    bc(ProfileSharingConsentActivity profileSharingConsentActivity) {
        this.f725a = profileSharingConsentActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f725a.m592a();
    }
}
