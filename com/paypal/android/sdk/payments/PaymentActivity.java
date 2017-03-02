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

public final class PaymentActivity extends Activity {
    public static final String EXTRA_PAYMENT = "com.paypal.android.sdk.payment";
    public static final String EXTRA_RESULT_CONFIRMATION = "com.paypal.android.sdk.paymentConfirmation";
    public static final int RESULT_EXTRAS_INVALID = 2;
    private static final String f608a;
    private Timer f609b;
    private Date f610c;
    private PayPalService f611d;
    private final ServiceConnection f612e;
    private boolean f613f;

    static {
        f608a = PaymentActivity.class.getSimpleName();
    }

    public PaymentActivity() {
        this.f612e = new al(this);
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
                        Log.e(f608a, "result was OK, no intent data, oops");
                        break;
                    }
                    PaymentConfirmation paymentConfirmation = (PaymentConfirmation) intent.getParcelableExtra(EXTRA_RESULT_CONFIRMATION);
                    if (paymentConfirmation == null) {
                        Log.e(f608a, "result was OK, have data, but no payment state in bundle, oops");
                        break;
                    }
                    Intent intent2 = new Intent();
                    intent2.putExtra(EXTRA_RESULT_CONFIRMATION, paymentConfirmation);
                    setResult(-1, intent2);
                    break;
                case DialogFragment.STYLE_NORMAL /*0*/:
                    break;
                default:
                    Log.wtf("paypal.sdk", "unexpected request code " + i + " call it a cancel");
                    break;
            }
        }
        finish();
    }

    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        new StringBuilder().append(getClass().getSimpleName()).append(".onCreate");
        new bi(this).m661a();
        new C0108b(this).m658a(Arrays.asList(new String[]{PaymentActivity.class.getName(), LoginActivity.class.getName(), PaymentMethodActivity.class.getName(), PaymentConfirmActivity.class.getName()}));
        this.f613f = bindService(C0108b.m657b(this), this.f612e, 1);
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
                return C0108b.m645a((Activity) this, new ak(this));
            case FragmentManagerImpl.ANIM_STYLE_CLOSE_ENTER /*3*/:
                return C0108b.m647a((Activity) this, bO.UNAUTHORIZED_MERCHANT_TITLE, bundle, i);
            default:
                return C0108b.m647a((Activity) this, bO.UNAUTHORIZED_DEVICE_TITLE, bundle, i);
        }
    }

    protected final void onDestroy() {
        new StringBuilder().append(getClass().getSimpleName()).append(".onDestroy");
        if (this.f611d != null) {
            this.f611d.m513m();
            this.f611d.m519s();
        }
        if (this.f613f) {
            unbindService(this.f612e);
            this.f613f = false;
        }
        super.onDestroy();
    }
}
