package com.paypal.android.sdk.payments;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;

class bi {
    private static final String f732a;
    private Context f733b;

    static {
        f732a = bi.class.getSimpleName();
    }

    bi(Context context) {
        this.f733b = context;
    }

    private boolean m660b() {
        try {
            PackageInfo packageInfo = this.f733b.getPackageManager().getPackageInfo(this.f733b.getPackageName(), 4);
            if (packageInfo.services == null) {
                return false;
            }
            for (ServiceInfo serviceInfo : packageInfo.services) {
                if (serviceInfo.name.equals(PayPalService.class.getName())) {
                    String str = f732a;
                    new StringBuilder("Service exported=").append(serviceInfo.exported);
                    str = f732a;
                    new StringBuilder("Service processName=").append(serviceInfo.processName);
                    str = f732a;
                    new StringBuilder("context.getPackageName()=").append(this.f733b.getPackageName());
                    if (!serviceInfo.exported && this.f733b.getPackageName().equals(serviceInfo.processName)) {
                        String str2 = f732a;
                        new StringBuilder("Found ").append(PayPalService.class.getName()).append(" in manifest.");
                        return true;
                    }
                }
            }
            return false;
        } catch (NameNotFoundException e) {
            throw new RuntimeException("Exception loading manifest" + e.getMessage());
        }
    }

    final void m661a() {
        if (!m660b()) {
            throw new RuntimeException("Please declare the following in your manifest:<service android:name=\"com.paypal.android.sdk.payments.PayPalService\" android:exported=\"false\" />");
        }
    }
}
