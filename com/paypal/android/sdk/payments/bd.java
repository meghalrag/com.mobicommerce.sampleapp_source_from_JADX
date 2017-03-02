package com.paypal.android.sdk.payments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class bd implements OnClickListener {
    private /* synthetic */ ProfileSharingConsentActivity f726a;

    bd(ProfileSharingConsentActivity profileSharingConsentActivity) {
        this.f726a = profileSharingConsentActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f726a.onBackPressed();
    }
}
