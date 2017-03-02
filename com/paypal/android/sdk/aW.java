package com.paypal.android.sdk;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

public class aW {
    private static final String f30a;

    static {
        f30a = aW.class.getSimpleName();
    }

    public static Intent m55a(String str, aY aYVar, aX aXVar, String str2) {
        Intent a = m56a("com.paypal.android.p2pmobile.Sdk", "com.paypal.android.lib.authenticator.activity.SdkActivity");
        Bundle bundle = new Bundle();
        bundle.putString("target_client_id", str);
        bundle.putString("token_request_type", aYVar.toString());
        bundle.putString("response_type", aXVar.toString());
        bundle.putString("app_guid", str2);
        String str3 = f30a;
        new StringBuilder("launching authenticator with bundle:").append(bundle);
        a.putExtras(bundle);
        return a;
    }

    static Intent m56a(String str, String str2) {
        Intent intent = new Intent(str);
        intent.setComponent(ComponentName.unflattenFromString(str2));
        intent.setPackage("com.paypal.android.p2pmobile");
        return intent;
    }

    public static Intent m57b(String str, aY aYVar, aX aXVar, String str2) {
        Intent a = m55a(str, aYVar, aXVar, str2);
        a.putExtra("scope", "https://api.paypal.com/v1/payments/.*");
        return a;
    }
}
