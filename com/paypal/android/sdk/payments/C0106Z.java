package com.paypal.android.sdk.payments;

import android.app.Activity;
import java.util.TimerTask;

/* renamed from: com.paypal.android.sdk.payments.Z */
final class C0106Z extends TimerTask {
    private /* synthetic */ C0238Y f669a;

    C0106Z(C0238Y c0238y) {
        this.f669a = c0238y;
    }

    public final void run() {
        ProfileSharingConsentActivity.m594a((Activity) this.f669a.f1086a, 1, this.f669a.f1086a.f578d.m503c());
    }
}
