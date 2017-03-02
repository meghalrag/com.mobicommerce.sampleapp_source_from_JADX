package com.paypal.android.sdk.payments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.v4.media.TransportMediator;
import android.support.v4.widget.CursorAdapter;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.BulletSpan;
import android.text.style.URLSpan;
import android.util.Log;
import com.paypal.android.sdk.C0069T;
import com.paypal.android.sdk.C0072c;
import com.paypal.android.sdk.aW;
import com.paypal.android.sdk.aX;
import com.paypal.android.sdk.aY;
import com.paypal.android.sdk.bM;
import com.paypal.android.sdk.bN;
import com.paypal.android.sdk.bO;
import com.paypal.android.sdk.bQ;
import com.paypal.android.sdk.bt;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public final class FuturePaymentConsentActivity extends Activity {
    protected static String f494a;
    private static final String f495b;
    private static PayPalOAuthScopes f496c;
    private C0121y f497d;
    private boolean f498e;
    private bQ f499f;
    private PayPalService f500g;
    private final ServiceConnection f501h;
    private boolean f502i;
    private boolean f503j;

    static {
        f495b = FuturePaymentConsentActivity.class.getSimpleName();
        f496c = new PayPalOAuthScopes(new HashSet(Arrays.asList(new String[]{PayPalOAuthScopes.PAYPAL_SCOPE_FUTURE_PAYMENTS})));
    }

    public FuturePaymentConsentActivity() {
        this.f501h = new C0119v(this);
    }

    private void m368a() {
        new StringBuilder().append(getClass().getSimpleName()).append(".doLogin");
        if (C0115n.m681a((Context) this, this.f500g)) {
            startActivityForResult(aW.m55a(this.f500g.m503c().m417k(), aY.PROMPT_LOGIN, aX.code, C0072c.m291a().m294c().m337e()), 2);
            return;
        }
        String str = f495b;
        LoginActivity.m385a(this, 1, null, true, this.f500g.m503c());
    }

    private void m369a(int i, PayPalAuthorization payPalAuthorization) {
        Intent intent = new Intent();
        intent.putExtra(PayPalProfileSharingActivity.EXTRA_RESULT_AUTHORIZATION, payPalAuthorization);
        setResult(i, intent);
    }

    static void m370a(Activity activity, int i, PayPalConfiguration payPalConfiguration) {
        String str = f495b;
        Intent intent = new Intent(activity, FuturePaymentConsentActivity.class);
        intent.putExtras(activity.getIntent());
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, payPalConfiguration);
        activity.startActivityForResult(intent, 1);
    }

    private void m371a(Bundle bundle) {
        String string = bundle.getString("authAccount");
        String string2 = bundle.getString("code");
        String string3 = bundle.getString("nonce");
        for (String str : bundle.keySet()) {
            if (bundle.get(str) == null) {
                String.format("%s:null", new Object[]{(String) r4.next()});
            } else {
                String.format("%s:%s (%s)", new Object[]{(String) r4.next(), bundle.get(str).toString(), bundle.get(str).getClass().getName()});
            }
            String str2 = f495b;
        }
        C0121y c0121y = new C0121y(this, string2, string3, string);
        if (this.f500g == null) {
            this.f497d = c0121y;
        } else {
            m375a(c0121y);
        }
    }

    private void m372a(SpannableString spannableString) {
        int i = 0;
        URLSpan[] uRLSpanArr = (URLSpan[]) spannableString.getSpans(0, spannableString.length(), URLSpan.class);
        int length = uRLSpanArr.length;
        while (i < length) {
            URLSpan uRLSpan = uRLSpanArr[i];
            spannableString.setSpan(new C0109c(uRLSpan, this, FuturePaymentInfoActivity.class, new C0243s(this)), spannableString.getSpanStart(uRLSpan), spannableString.getSpanEnd(uRLSpan), 33);
            spannableString.removeSpan(uRLSpan);
            i++;
        }
    }

    private void m375a(C0121y c0121y) {
        this.f500g.m502b().f98g = c0121y.f758b;
        this.f500g.m502b().f97f = c0121y.f759c;
        this.f500g.m502b().f95d = c0121y.f757a;
        this.f499f.f208h.setEnabled(true);
    }

    private void m377b() {
        this.f502i = bindService(C0108b.m657b(this), this.f501h, 1);
    }

    private void m378b(SpannableString spannableString) {
        int i = 0;
        URLSpan[] uRLSpanArr = (URLSpan[]) spannableString.getSpans(0, spannableString.length(), URLSpan.class);
        int length = uRLSpanArr.length;
        while (i < length) {
            URLSpan uRLSpan = uRLSpanArr[i];
            spannableString.setSpan(new bm(uRLSpan, new C0244t(this)), spannableString.getSpanStart(uRLSpan), spannableString.getSpanEnd(uRLSpan), 33);
            spannableString.removeSpan(uRLSpan);
            i++;
        }
    }

    private void m379c(SpannableString spannableString) {
        int i = 0;
        URLSpan[] uRLSpanArr = (URLSpan[]) spannableString.getSpans(0, spannableString.length(), URLSpan.class);
        int length = uRLSpanArr.length;
        while (i < length) {
            URLSpan uRLSpan = uRLSpanArr[i];
            spannableString.setSpan(new bm(uRLSpan, new C0245u(this)), spannableString.getSpanStart(uRLSpan), spannableString.getSpanEnd(uRLSpan), 33);
            spannableString.removeSpan(uRLSpan);
            i++;
        }
    }

    static /* synthetic */ void m380c(FuturePaymentConsentActivity futurePaymentConsentActivity) {
        futurePaymentConsentActivity.f500g.m487a(bM.ConsentCancel);
        futurePaymentConsentActivity.finish();
    }

    static /* synthetic */ void m381d(FuturePaymentConsentActivity futurePaymentConsentActivity) {
        futurePaymentConsentActivity.f500g.m487a(bM.ConsentAgree);
        if (futurePaymentConsentActivity.f500g.m509i() && futurePaymentConsentActivity.f500g.m507g()) {
            futurePaymentConsentActivity.showDialog(2);
            futurePaymentConsentActivity.f500g.m499a(f496c.m431a());
            return;
        }
        C0108b.m653a((Activity) futurePaymentConsentActivity, bN.m131a(bO.SESSION_EXPIRED_MESSAGE), 4);
    }

    static /* synthetic */ void m382e(FuturePaymentConsentActivity futurePaymentConsentActivity) {
        if (!(futurePaymentConsentActivity.f500g.m508h() || futurePaymentConsentActivity.f503j)) {
            futurePaymentConsentActivity.f503j = true;
            futurePaymentConsentActivity.m368a();
        }
        if (!futurePaymentConsentActivity.f498e) {
            futurePaymentConsentActivity.f498e = true;
            futurePaymentConsentActivity.f500g.m487a(bM.ConsentWindow);
        }
        C0108b.m654a(futurePaymentConsentActivity.f499f.f206f.f214b, futurePaymentConsentActivity.f500g.m504d());
        futurePaymentConsentActivity.f500g.m492a(new C0242p(futurePaymentConsentActivity));
        String l = futurePaymentConsentActivity.f500g.m503c().m418l();
        f494a = futurePaymentConsentActivity.f500g.m503c().m407a();
        String format = String.format(bN.m131a(bO.CONSENT_AGREEMENT_INTRO), new Object[]{"<b>" + l + "</b>"});
        String str = bN.m134c(f494a) ? "\u200f" : BuildConfig.VERSION_NAME;
        futurePaymentConsentActivity.f499f.f203c[0].setText(Html.fromHtml(str + format));
        if (bN.m134c(f494a)) {
            futurePaymentConsentActivity.f499f.f203c[0].setGravity(5);
        }
        int i = 1;
        futurePaymentConsentActivity.f499f.f203c[0].setVisibility(0);
        List a = f496c.m431a();
        PayPalScope[] values = PayPalScope.values();
        int length = values.length;
        int i2 = 0;
        while (i2 < length) {
            int i3;
            PayPalScope payPalScope = values[i2];
            if (!a.contains(payPalScope.m457a())) {
                i3 = i;
            } else if (payPalScope.equals(PayPalScope.FUTURE_PAYMENTS)) {
                SpannableString spannableString = new SpannableString(Html.fromHtml(str + String.format(bN.m131a(payPalScope.m458b()), new Object[]{"future-payment-consent", "<b>" + l + "</b>", "<b>" + l + "</b>"})));
                futurePaymentConsentActivity.m372a(spannableString);
                spannableString.setSpan(new BulletSpan(15), 0, spannableString.length(), 0);
                futurePaymentConsentActivity.f499f.f203c[i].setVisibility(0);
                futurePaymentConsentActivity.f499f.f203c[i].setFocusable(true);
                futurePaymentConsentActivity.f499f.f203c[i].setNextFocusLeftId((47010 + i) - 1);
                futurePaymentConsentActivity.f499f.f203c[i].setNextFocusRightId((47010 + i) + 1);
                i3 = i + 1;
                futurePaymentConsentActivity.f499f.f203c[i].setText(spannableString);
            } else {
                CharSequence spannableString2 = new SpannableString(str + Html.fromHtml(bN.m131a(payPalScope.m458b())));
                spannableString2.setSpan(new BulletSpan(15), 0, spannableString2.length(), 0);
                futurePaymentConsentActivity.f499f.f203c[i].setVisibility(0);
                futurePaymentConsentActivity.f499f.f203c[i].setFocusable(true);
                futurePaymentConsentActivity.f499f.f203c[i].setNextFocusLeftId((47010 + i) - 1);
                futurePaymentConsentActivity.f499f.f203c[i].setNextFocusRightId((47010 + i) + 1);
                i3 = i + 1;
                futurePaymentConsentActivity.f499f.f203c[i].setText(spannableString2);
            }
            i2++;
            i = i3;
        }
        format = String.format(bN.m131a(bO.CONSENT_AGREEMENT_MERCHANT_PRIVACY_POLICY), new Object[]{"<b>" + l + "</b>", futurePaymentConsentActivity.f500g.m503c().m419m().toString(), futurePaymentConsentActivity.f500g.m503c().m420n()});
        String str2 = f495b;
        SpannableString spannableString3 = new SpannableString(Html.fromHtml(str + format));
        spannableString3.setSpan(new BulletSpan(15), 0, spannableString3.length(), 0);
        futurePaymentConsentActivity.m378b(spannableString3);
        futurePaymentConsentActivity.f499f.f203c[i].setVisibility(0);
        futurePaymentConsentActivity.f499f.f203c[i].setFocusable(true);
        futurePaymentConsentActivity.f499f.f203c[i].setNextFocusLeftId((47010 + i) - 1);
        futurePaymentConsentActivity.f499f.f203c[i].setNextFocusRightId(47002);
        int i4 = i + 1;
        futurePaymentConsentActivity.f499f.f203c[i].setText(spannableString3);
        String a2 = bN.m131a(bO.PRIVACY);
        Object[] objArr = new Object[1];
        CharSequence toLowerCase = Locale.getDefault().getCountry().toLowerCase(Locale.US);
        if (C0069T.m51c(toLowerCase)) {
            toLowerCase = "us";
        }
        objArr[0] = String.format("https://www.paypal.com/%s/cgi-bin/webscr?cmd=p/gen/ua/policy_privacy-outside", new Object[]{toLowerCase});
        SpannableString spannableString4 = new SpannableString(Html.fromHtml(str + String.format(a2, objArr)));
        futurePaymentConsentActivity.m379c(spannableString4);
        futurePaymentConsentActivity.f499f.f204d.setText(spannableString4);
        futurePaymentConsentActivity.f499f.f204d.setMovementMethod(LinkMovementMethod.getInstance());
        futurePaymentConsentActivity.f499f.f204d.setNextFocusLeftId((47010 + i4) - 1);
        futurePaymentConsentActivity.f499f.f204d.setNextFocusRightId(47001);
        CharSequence a3 = C0108b.m650a(futurePaymentConsentActivity.f500g.m503c().m407a());
        if (a3 != null) {
            futurePaymentConsentActivity.f499f.f205e.setText(a3);
            futurePaymentConsentActivity.f499f.f205e.setVisibility(0);
        }
        futurePaymentConsentActivity.f499f.f209i.setText(bN.m131a(bO.CONSENT_AGREEMENT_AGREE));
        futurePaymentConsentActivity.f499f.f207g.setOnClickListener(new C0117q(futurePaymentConsentActivity));
        futurePaymentConsentActivity.f499f.f208h.setOnClickListener(new C0118r(futurePaymentConsentActivity));
        if (futurePaymentConsentActivity.f497d != null) {
            futurePaymentConsentActivity.m375a(futurePaymentConsentActivity.f497d);
            futurePaymentConsentActivity.f497d = null;
        }
    }

    public final void finish() {
        super.finish();
        new StringBuilder().append(getClass().getSimpleName()).append(".finish");
    }

    protected final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        new StringBuilder().append(getClass().getSimpleName()).append(".onActivityResult(").append(i).append(",").append(i2).append(",").append(intent).append(")");
        switch (i) {
            case CursorAdapter.FLAG_AUTO_REQUERY /*1*/:
                if (i2 != -1) {
                    m369a(i2, null);
                    finish();
                } else if (this.f499f != null && this.f499f.f208h != null) {
                    this.f499f.f208h.setEnabled(true);
                }
            case CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER /*2*/:
                if (i2 != -1) {
                    m369a(i2, null);
                    finish();
                } else if (this.f499f != null && this.f499f.f208h != null) {
                    this.f499f.f208h.setEnabled(true);
                    m371a(intent.getExtras());
                }
            default:
                Log.e(f495b, "unhandled requestCode " + i);
        }
    }

    public final void onBackPressed() {
        this.f500g.m487a(bM.ConsentCancel);
        super.onBackPressed();
    }

    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        new StringBuilder().append(getClass().getSimpleName()).append(".onCreate");
        if (bundle == null) {
            if (!C0108b.m655a((Activity) this)) {
                finish();
            }
            this.f498e = false;
        } else {
            this.f498e = bundle.getBoolean("pageTrackingSent");
            this.f503j = bundle.getBoolean("isLoginActivityStarted");
        }
        m377b();
        C0069T.m49b((Activity) this);
        C0069T.m38a((Activity) this);
        this.f499f = new bQ(this);
        setContentView(this.f499f.f201a);
        C0108b.m651a((Activity) this, this.f499f.f202b, null);
        this.f499f.f207g.setText(bt.m258a(bN.m131a(bO.CANCEL)));
        this.f499f.f207g.setVisibility(0);
    }

    protected final Dialog onCreateDialog(int i, Bundle bundle) {
        switch (i) {
            case CursorAdapter.FLAG_AUTO_REQUERY /*1*/:
                return C0108b.m646a((Activity) this, bO.CONSENT_FAILED_ALERT_TITLE, bundle);
            case CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER /*2*/:
                return C0108b.m649a((Context) this, bO.PROCESSING, bO.ONE_MOMENT);
            case FragmentManagerImpl.ANIM_STYLE_CLOSE_ENTER /*3*/:
                return C0108b.m647a((Activity) this, bO.INTERNAL_ERROR, bundle, i);
            case TransportMediator.FLAG_KEY_MEDIA_PLAY /*4*/:
                return C0108b.m648a((Activity) this, bO.SESSION_EXPIRED_TITLE, bundle, new C0120x(this));
            default:
                return null;
        }
    }

    protected final void onDestroy() {
        new StringBuilder().append(getClass().getSimpleName()).append(".onDestroy");
        if (this.f500g != null) {
            this.f500g.m511k();
        }
        if (this.f502i) {
            unbindService(this.f501h);
            this.f502i = false;
        }
        super.onDestroy();
    }

    protected final void onRestart() {
        super.onRestart();
        m377b();
    }

    protected final void onResume() {
        super.onResume();
        new StringBuilder().append(getClass().getSimpleName()).append(".onResume");
    }

    protected final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("pageTrackingSent", this.f498e);
        bundle.putBoolean("isLoginActivityStarted", this.f503j);
    }
}
