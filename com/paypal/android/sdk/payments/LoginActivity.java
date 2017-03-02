package com.paypal.android.sdk.payments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import com.paypal.android.sdk.C0069T;
import com.paypal.android.sdk.C0078i;
import com.paypal.android.sdk.C0080k;
import com.paypal.android.sdk.C0123q;
import com.paypal.android.sdk.C0127u;
import com.paypal.android.sdk.C0128v;
import com.paypal.android.sdk.bD;
import com.paypal.android.sdk.bM;
import com.paypal.android.sdk.bN;
import com.paypal.android.sdk.bO;
import com.paypal.android.sdk.bW;
import com.paypal.android.sdk.bt;
import com.paypal.android.sdk.bz;
import java.util.Locale;

public final class LoginActivity extends Activity implements TextWatcher {
    private final String f510a;
    private C0093K f511b;
    private String f512c;
    private String f513d;
    private String f514e;
    private String f515f;
    private String f516g;
    private String f517h;
    private boolean f518i;
    private boolean f519j;
    private boolean f520k;
    private boolean f521l;
    private bW f522m;
    private PayPalService f523n;
    private final ServiceConnection f524o;
    private boolean f525p;

    public LoginActivity() {
        this.f510a = LoginActivity.class.getSimpleName();
        this.f524o = new C0090F(this);
    }

    static void m385a(Activity activity, int i, bD bDVar, boolean z, PayPalConfiguration payPalConfiguration) {
        m386a(activity, 1, null, true, false, null, payPalConfiguration);
    }

    static void m386a(Activity activity, int i, bD bDVar, boolean z, boolean z2, String str, PayPalConfiguration payPalConfiguration) {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.putExtras(activity.getIntent());
        intent.putExtra("com.paypal.android.sdk.payments.persistedLogin", bDVar);
        intent.putExtra("com.paypal.android.sdk.payments.useResponseTypeCode", z);
        intent.putExtra("com.paypal.android.sdk.payments.forceLogin", z2);
        intent.putExtra("com.paypal.android.sdk.payments.requestedScopes", str);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, payPalConfiguration);
        activity.startActivityForResult(intent, i);
    }

    static /* synthetic */ void m389a(LoginActivity loginActivity, String str) {
        loginActivity.f522m.f230f.setEnabled(true);
        loginActivity.f513d = null;
        loginActivity.f516g = null;
        loginActivity.m397e();
        C0108b.m653a((Activity) loginActivity, bN.m132a(str), 1);
    }

    static /* synthetic */ void m390b(LoginActivity loginActivity) {
        try {
            loginActivity.dismissDialog(2);
        } catch (IllegalArgumentException e) {
        }
    }

    static /* synthetic */ void m391b(LoginActivity loginActivity, View view) {
        loginActivity.m398f();
        if (loginActivity.f511b == C0093K.PIN) {
            loginActivity.f511b = C0093K.EMAIL;
        } else {
            loginActivity.f511b = C0093K.PIN;
        }
        loginActivity.m397e();
        loginActivity.f522m.m153a(loginActivity.f511b == C0093K.EMAIL);
    }

    private boolean m393c() {
        return getIntent().getBooleanExtra("com.paypal.android.sdk.payments.useResponseTypeCode", false);
    }

    private void m395d() {
        setResult(-1);
        finish();
    }

    private void m397e() {
        if (this.f511b == C0093K.PIN) {
            this.f522m.f229e.setVisibility(4);
            this.f522m.f232h.setText(bt.m258a(bN.m131a(bO.LOGIN_WITH_EMAIL)));
            this.f522m.f226b.setText(this.f514e);
            this.f522m.f226b.setHint(bN.m131a(bO.PHONE));
            this.f522m.f226b.setInputType(3);
            this.f522m.f228d.setText(this.f516g);
            this.f522m.f228d.setHint(bN.m131a(bO.PIN));
            this.f522m.f228d.setInputType(VERSION.SDK_INT < 11 ? 2 : 18);
            if (VERSION.SDK_INT < 11) {
                this.f522m.f228d.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        } else {
            this.f522m.f229e.setVisibility(0);
            this.f522m.f232h.setText(bt.m258a(bN.m131a(bO.LOGIN_WITH_PHONE)));
            this.f522m.f226b.setText(this.f512c);
            this.f522m.f226b.setHint(bN.m131a(bO.EMAIL));
            this.f522m.f226b.setInputType(33);
            this.f522m.f228d.setText(this.f513d);
            this.f522m.f228d.setHint(bN.m131a(bO.PASSWORD));
            this.f522m.f228d.setInputType(129);
        }
        m401g();
        if (this.f522m.f226b.getText().length() > 0 && this.f522m.f228d.getText().length() == 0) {
            this.f522m.f228d.requestFocus();
        }
        C0108b.m654a(this.f522m.f227c.f214b, this.f523n.m504d());
    }

    private void m398f() {
        if (this.f511b == C0093K.PIN) {
            this.f514e = this.f522m.f226b.getText().toString();
            this.f516g = this.f522m.f228d.getText().toString();
            return;
        }
        this.f512c = this.f522m.f226b.getText().toString();
        this.f513d = this.f522m.f228d.getText().toString();
    }

    private void m401g() {
        boolean z = true;
        String obj = this.f522m.f226b.getText().toString();
        String obj2 = this.f522m.f228d.getText().toString();
        if (this.f511b == C0093K.PIN) {
            if (!(C0127u.m699d(obj) && C0127u.m697b(obj2))) {
                z = false;
            }
        } else if (!(C0127u.m696a(obj) && C0127u.m698c(obj2))) {
            z = false;
        }
        this.f522m.f230f.setEnabled(z);
        this.f522m.f230f.setFocusable(z);
    }

    private void m402h() {
        this.f522m.f230f.setEnabled(false);
        if (this.f523n.m507g()) {
            C0123q c0123q;
            m398f();
            if (this.f511b == C0093K.PIN) {
                C0078i d = bz.m975d();
                c0123q = new C0123q(this.f515f == null ? new C0128v(d, this.f514e) : new C0128v(d, new C0080k(this.f515f), this.f514e), this.f516g);
            } else {
                c0123q = new C0123q(this.f512c, this.f513d);
            }
            showDialog(2);
            this.f523n.m495a(c0123q, this.f519j, m393c() ? "code" : "token", m393c(), this.f517h);
            return;
        }
        showDialog(2);
        String str = this.f510a;
        new StringBuilder("token is expired, get new one. AccessToken: ").append(this.f523n.m502b().f94c);
        this.f523n.m494a(m403i(), true);
    }

    private af m403i() {
        return new C0236H(this);
    }

    final void m404a() {
        PayPalConfiguration c = this.f523n.m503c();
        if (bN.m134c(this.f523n.m503c().m407a())) {
            this.f522m.f228d.setGravity(5);
            this.f522m.f226b.setGravity(5);
        }
        if (!(C0127u.m701f(Locale.getDefault().getCountry().toLowerCase(Locale.US)) && this.f523n.m502b().f100i)) {
            this.f522m.f232h.setVisibility(4);
        }
        if (this.f520k) {
            this.f512c = c.m409c();
            String d = c.m410d();
            if (d != null) {
                this.f514e = d;
            }
            d = c.m411e();
            if (d != null) {
                this.f515f = d;
            }
            if (c.m412f() && !c.m408b().equals(PayPalConfiguration.ENVIRONMENT_PRODUCTION)) {
                this.f513d = c.m413g();
                this.f516g = c.m414h();
            }
        }
        if (getIntent().getBooleanExtra("com.paypal.android.sdk.payments.forceLogin", false) && !this.f521l) {
            this.f521l = true;
            this.f523n.m506f();
        }
        if (this.f523n.m508h()) {
            m395d();
            return;
        }
        if (!this.f518i) {
            this.f518i = true;
            this.f523n.m488a(bM.LoginWindow, Boolean.valueOf(this.f519j));
        }
        this.f519j = false;
        bD bDVar = (bD) getIntent().getParcelableExtra("com.paypal.android.sdk.payments.persistedLogin");
        if (bDVar != null) {
            this.f519j = true;
            if (C0069T.m46a(this.f512c) && C0069T.m50b(bDVar.m122b())) {
                this.f512c = bDVar.m122b();
            }
            if (this.f514e == null && bDVar.m118a() != null) {
                this.f514e = bDVar.m118a().m705a(bz.m975d());
            }
            if (this.f511b == null) {
                if (bDVar.m123c() != null) {
                    switch (C0092J.f506a[bDVar.m123c().ordinal()]) {
                        case CursorAdapter.FLAG_AUTO_REQUERY /*1*/:
                            this.f511b = C0093K.EMAIL;
                            break;
                        case CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER /*2*/:
                            this.f511b = C0093K.PIN;
                            break;
                        default:
                            break;
                    }
                }
                this.f511b = C0093K.EMAIL;
            }
        }
        m397e();
        this.f523n.m493a(new C0234E(this));
    }

    public final void afterTextChanged(Editable editable) {
        m401g();
    }

    final void m405b() {
        String str;
        C0078i d = bz.m975d();
        Object a = d.m348b().m354a();
        if (C0069T.m52d(a) && a.equalsIgnoreCase("US")) {
            str = "https://www.paypal.com/webapps/accountrecovery/passwordrecovery";
        } else {
            str = d.m349c().getLanguage();
            str = String.format("https://www.paypal.com/%s/cgi-bin/webscr?cmd=_account-recovery&from=%s&locale.x=%s", new Object[]{a, "PayPalMPL", str});
        }
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        this.f523n.m488a(bM.LoginForgotPassword, Boolean.valueOf(this.f519j));
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void onBackPressed() {
        this.f523n.m488a(bM.LoginCancel, Boolean.valueOf(this.f519j));
        super.onBackPressed();
    }

    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        new StringBuilder().append(getClass().getSimpleName()).append(".onCreate");
        this.f517h = getIntent().getExtras().getString("com.paypal.android.sdk.payments.requestedScopes");
        this.f525p = bindService(C0108b.m657b(this), this.f524o, 1);
        C0069T.m49b((Activity) this);
        C0069T.m38a((Activity) this);
        this.f522m = new bW(this);
        setContentView(this.f522m.f225a);
        C0108b.m651a((Activity) this, null, bO.LOG_IN_TO_PAYPAL);
        this.f522m.f229e.setText(bt.m258a(bN.m131a(bO.FORGOT_PASSWORD)));
        this.f522m.f231g.setText(bN.m131a(bO.LOG_IN));
        this.f522m.f226b.addTextChangedListener(this);
        this.f522m.f228d.addTextChangedListener(this);
        this.f522m.f230f.setOnClickListener(new C0087B(this));
        this.f522m.f229e.setOnClickListener(new C0088C(this));
        this.f522m.f232h.setOnClickListener(new C0089D(this));
        if (bundle == null) {
            this.f518i = false;
            this.f520k = true;
            return;
        }
        this.f520k = false;
        this.f518i = bundle.getBoolean("PP_PageTrackingSent");
        this.f511b = (C0093K) bundle.getParcelable("PP_LoginType");
        this.f512c = bundle.getString("PP_SavedEmail");
        this.f514e = bundle.getString("PP_SavedPhone");
        this.f515f = bundle.getString("PP_savedPhoneCountryCode");
        this.f513d = bundle.getString("PP_SavedPassword");
        this.f516g = bundle.getString("PP_SavedPIN");
        this.f519j = bundle.getBoolean("PP_IsReturningUser");
        this.f521l = bundle.getBoolean("PP_IsClearedLogin");
        this.f517h = bundle.getString("PP_RequestedScopes");
    }

    protected final Dialog onCreateDialog(int i, Bundle bundle) {
        switch (i) {
            case CursorAdapter.FLAG_AUTO_REQUERY /*1*/:
                return C0108b.m646a((Activity) this, bO.LOGIN_FAILED_ALERT_TITLE, bundle);
            case CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER /*2*/:
                return C0108b.m649a((Context) this, bO.AUTHENTICATING, bO.ONE_MOMENT);
            case FragmentManagerImpl.ANIM_STYLE_CLOSE_ENTER /*3*/:
                return C0108b.m644a((Context) this, bO.WE_ARE_SORRY, bN.m131a(bO.TWO_FA_REQUIRED_ERROR), new C0091I(this));
            default:
                return null;
        }
    }

    protected final void onDestroy() {
        new StringBuilder().append(getClass().getSimpleName()).append(".onDestroy");
        if (this.f523n != null) {
            this.f523n.m512l();
        }
        if (this.f525p) {
            unbindService(this.f524o);
            this.f525p = false;
        }
        super.onDestroy();
    }

    protected final void onResume() {
        super.onResume();
        new StringBuilder().append(getClass().getSimpleName()).append(".onResume");
        if (this.f523n != null) {
            m397e();
        }
    }

    protected final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        m398f();
        bundle.putParcelable("PP_LoginType", this.f511b);
        bundle.putString("PP_SavedEmail", this.f512c);
        bundle.putString("PP_SavedPhone", this.f514e);
        bundle.putString("PP_SavedPhoneCountryCode", this.f515f);
        bundle.putString("PP_SavedPassword", this.f513d);
        bundle.putString("PP_SavedPIN", this.f516g);
        bundle.putBoolean("PP_IsReturningUser", this.f519j);
        bundle.putBoolean("PP_PageTrackingSent", this.f518i);
        bundle.putBoolean("PP_IsClearedLogin", this.f521l);
        bundle.putString("PP_RequestedScopes", this.f517h);
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
