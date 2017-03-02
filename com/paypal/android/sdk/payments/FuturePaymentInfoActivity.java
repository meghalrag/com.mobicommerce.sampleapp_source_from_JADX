package com.paypal.android.sdk.payments;

import android.app.Activity;
import android.os.Bundle;
import com.paypal.android.sdk.C0069T;
import com.paypal.android.sdk.bN;
import com.paypal.android.sdk.bO;

public final class FuturePaymentInfoActivity extends Activity {
    private C0086A f504a;

    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        C0069T.m49b((Activity) this);
        C0069T.m38a((Activity) this);
        this.f504a = new C0086A(this, bN.m134c(FuturePaymentConsentActivity.f494a));
        setContentView(this.f504a.f483a);
        C0108b.m651a((Activity) this, this.f504a.f484b, null);
        this.f504a.f485c.setText(bN.m131a(bO.BACK_BUTTON));
        this.f504a.f485c.setOnClickListener(new C0122z(this));
    }
}
