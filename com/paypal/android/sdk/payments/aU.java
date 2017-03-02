package com.paypal.android.sdk.payments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class aU implements OnClickListener {
    private /* synthetic */ ProfileSharingConsentActivity f698a;

    aU(ProfileSharingConsentActivity profileSharingConsentActivity) {
        this.f698a = profileSharingConsentActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f698a.onBackPressed();
    }
}
