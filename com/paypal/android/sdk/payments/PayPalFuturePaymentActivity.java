package com.paypal.android.sdk.payments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import com.paypal.android.sdk.C0069T;
import com.paypal.android.sdk.bN;
import com.paypal.android.sdk.bO;
import com.paypal.android.sdk.ce;
import java.util.Arrays;
import java.util.Date;
import java.util.Timer;
import org.apache.cordova.CordovaResourceApi;

public final class PayPalFuturePaymentActivity extends Activity {
    public static final String EXTRA_RESULT_AUTHORIZATION = "com.paypal.android.sdk.authorization";
    public static final int RESULT_EXTRAS_INVALID = 2;
    private static final String f546a;
    private Date f547b;
    private Timer f548c;
    private PayPalService f549d;
    private final ServiceConnection f550e;
    private boolean f551f;

    static {
        f546a = PayPalFuturePaymentActivity.class.getSimpleName();
    }

    public PayPalFuturePaymentActivity() {
        this.f550e = new C0098P(this);
    }

    public final void finish() {
        super.finish();
        new StringBuilder().append(getClass().getSimpleName()).append(".finish");
    }

    protected final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        new StringBuilder().append(getClass().getSimpleName()).append(".onActivityResult");
        if (i == 1) {
            switch (i2) {
                case CordovaResourceApi.URI_TYPE_UNKNOWN /*-1*/:
                    if (intent == null) {
                        Log.e(f546a, "result was OK, no intent data, oops");
                        break;
                    }
                    PayPalAuthorization payPalAuthorization = (PayPalAuthorization) intent.getParcelableExtra(EXTRA_RESULT_AUTHORIZATION);
                    if (payPalAuthorization == null) {
                        Log.e(f546a, "result was OK, have data, but no authorization state in bundle, oops");
                        break;
                    }
                    Intent intent2 = new Intent();
                    intent2.putExtra(EXTRA_RESULT_AUTHORIZATION, payPalAuthorization);
                    setResult(-1, intent2);
                    break;
                case DialogFragment.STYLE_NORMAL /*0*/:
                    break;
                default:
                    Log.wtf(f546a, "unexpected request code " + i + " call it a cancel");
                    break;
            }
        }
        finish();
    }

    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        new StringBuilder().append(getClass().getSimpleName()).append(".onCreate");
        new bi(this).m661a();
        new C0108b(this).m658a(Arrays.asList(new String[]{PayPalFuturePaymentActivity.class.getName(), LoginActivity.class.getName(), FuturePaymentInfoActivity.class.getName(), FuturePaymentConsentActivity.class.getName()}));
        this.f551f = bindService(C0108b.m657b(this), this.f550e, 1);
        C0069T.m49b((Activity) this);
        C0069T.m38a((Activity) this);
        ce ceVar = new ce(this);
        setContentView(ceVar.f422a);
        ceVar.f423b.setText(bN.m131a(bO.CHECKING_DEVICE));
        C0108b.m651a((Activity) this, null, bO.CHECKING_DEVICE);
    }

    protected final Dialog onCreateDialog(int i, Bundle bundle) {
        switch (i) {
            case RESULT_EXTRAS_INVALID /*2*/:
                return C0108b.m645a((Activity) this, new C0097O(this));
            case FragmentManagerImpl.ANIM_STYLE_CLOSE_ENTER /*3*/:
                return C0108b.m647a((Activity) this, bO.UNAUTHORIZED_MERCHANT_TITLE, bundle, i);
            default:
                return C0108b.m647a((Activity) this, bO.UNAUTHORIZED_DEVICE_TITLE, bundle, i);
        }
    }

    protected final void onDestroy() {
        new StringBuilder().append(getClass().getSimpleName()).append(".onDestroy");
        if (this.f549d != null) {
            this.f549d.m513m();
            this.f549d.m519s();
        }
        if (this.f551f) {
            unbindService(this.f550e);
            this.f551f = false;
        }
        super.onDestroy();
    }
}
