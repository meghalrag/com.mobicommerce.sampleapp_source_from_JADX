package com.paypal.android.sdk.payments;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

final class ba implements ServiceConnection {
    final /* synthetic */ ProfileSharingConsentActivity f724a;

    ba(ProfileSharingConsentActivity profileSharingConsentActivity) {
        this.f724a = profileSharingConsentActivity;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        new StringBuilder().append(ProfileSharingConsentActivity.class.getSimpleName()).append(".onServiceConnected");
        this.f724a.f648h = ((ad) iBinder).f701a;
        if (this.f724a.f648h.m501a(new bb(this))) {
            ProfileSharingConsentActivity.m609e(this.f724a);
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.f724a.f648h = null;
    }
}
