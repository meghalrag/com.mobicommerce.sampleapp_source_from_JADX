package com.paypal.android.sdk;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;

final class br implements Runnable {
    private /* synthetic */ Context f368a;
    private /* synthetic */ bd f369b;

    br(Context context, bd bdVar) {
        this.f368a = context;
        this.f369b = bdVar;
    }

    public final void run() {
        try {
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.f368a);
            this.f369b.f299V = advertisingIdInfo.getId();
        } catch (Throwable e) {
            bq.m244a("RiskComponent.Util", e.getLocalizedMessage(), e);
        }
    }
}
