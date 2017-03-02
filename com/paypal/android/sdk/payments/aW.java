package com.paypal.android.sdk.payments;

import android.view.View;
import android.view.View.OnClickListener;

final class aW implements OnClickListener {
    private /* synthetic */ ProfileSharingConsentActivity f700a;

    aW(ProfileSharingConsentActivity profileSharingConsentActivity) {
        this.f700a = profileSharingConsentActivity;
    }

    public final void onClick(View view) {
        view.setEnabled(false);
        ProfileSharingConsentActivity.m608d(this.f700a);
    }
}
