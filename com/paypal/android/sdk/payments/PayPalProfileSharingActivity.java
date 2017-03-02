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

public final class PayPalProfileSharingActivity extends Activity {
    public static final String EXTRA_REQUESTED_SCOPES = "com.paypal.android.sdk.requested_scopes";
    public static final String EXTRA_RESULT_AUTHORIZATION = "com.paypal.android.sdk.authorization";
    public static final int RESULT_EXTRAS_INVALID = 2;
    private static final String f575a;
    private Date f576b;
    private Timer f577c;
    private PayPalService f578d;
    private final ServiceConnection f579e;
    private boolean f580f;

    static {
        f575a = PayPalProfileSharingActivity.class.getSimpleName();
    }

    public PayPalProfileSharingActivity() {
        this.f579e = new C0105X(this);
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
                        Log.e(f575a, "result was OK, no intent data, oops");
                        break;
                    }
                    PayPalAuthorization payPalAuthorization = (PayPalAuthorization) intent.getParcelableExtra(EXTRA_RESULT_AUTHORIZATION);
                    if (payPalAuthorization == null) {
                        Log.e(f575a, "result was OK, have data, but no authorization state in bundle, oops");
                        break;
                    }
                    Intent intent2 = new Intent();
                    intent2.putExtra(EXTRA_RESULT_AUTHORIZATION, payPalAuthorization);
                    setResult(-1, intent2);
                    break;
                case DialogFragment.STYLE_NORMAL /*0*/:
                    break;
                default:
                    Log.wtf(f575a, "unexpected request code " + i + " call it a cancel");
                    break;
            }
        }
        finish();
    }

    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        new StringBuilder().append(getClass().getSimpleName()).append(".onCreate");
        new bi(this).m661a();
        new C0108b(this).m658a(Arrays.asList(new String[]{PayPalProfileSharingActivity.class.getName(), LoginActivity.class.getName(), ProfileSharingConsentActivity.class.getName()}));
        this.f580f = bindService(C0108b.m657b(this), this.f579e, 1);
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
                return C0108b.m645a((Activity) this, new C0104W(this));
            case FragmentManagerImpl.ANIM_STYLE_CLOSE_ENTER /*3*/:
                return C0108b.m647a((Activity) this, bO.UNAUTHORIZED_MERCHANT_TITLE, bundle, i);
            default:
                return C0108b.m647a((Activity) this, bO.UNAUTHORIZED_DEVICE_TITLE, bundle, i);
        }
    }

    protected final void onDestroy() {
        new StringBuilder().append(getClass().getSimpleName()).append(".onDestroy");
        if (this.f578d != null) {
            this.f578d.m513m();
            this.f578d.m519s();
        }
        if (this.f580f) {
            unbindService(this.f579e);
            this.f580f = false;
        }
        super.onDestroy();
    }
}
