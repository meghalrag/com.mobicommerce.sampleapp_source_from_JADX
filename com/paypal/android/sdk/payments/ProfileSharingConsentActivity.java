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
import com.paypal.android.sdk.aa;
import com.paypal.android.sdk.bM;
import com.paypal.android.sdk.bN;
import com.paypal.android.sdk.bO;
import com.paypal.android.sdk.bQ;
import com.paypal.android.sdk.bt;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public final class ProfileSharingConsentActivity extends Activity {
    private static final String f641a;
    private static String f642c;
    private PayPalOAuthScopes f643b;
    private aR f644d;
    private be f645e;
    private boolean f646f;
    private bQ f647g;
    private PayPalService f648h;
    private final ServiceConnection f649i;
    private boolean f650j;
    private boolean f651k;

    static {
        f641a = ProfileSharingConsentActivity.class.getSimpleName();
    }

    public ProfileSharingConsentActivity() {
        this.f649i = new ba(this);
    }

    private static String m591a(String str) {
        return C0069T.m51c(str) ? null : str.toLowerCase().equals("openid_connect") ? null : str.toLowerCase().equals("oauth_account_creation_date") ? bN.m131a(bO.CONSENT_AGREEMENT_ATTRIBUTE_ACCOUNT_CREATION_DATE) : str.toLowerCase().equals("oauth_account_verified") ? bN.m131a(bO.CONSENT_AGREEMENT_ATTRIBUTE_ACCOUNT_STATUS) : str.toLowerCase().equals("oauth_account_type") ? bN.m131a(bO.CONSENT_AGREEMENT_ATTRIBUTE_ACCOUNT_TYPE) : (str.toLowerCase().equals("oauth_street_address1") || str.toLowerCase().equals("oauth_street_address2") || str.toLowerCase().equals("oauth_city") || str.toLowerCase().equals("oauth_state") || str.toLowerCase().equals("oauth_country") || str.toLowerCase().equals("oauth_zip")) ? bN.m131a(bO.CONSENT_AGREEMENT_ATTRIBUTE_ADDRESS) : str.toLowerCase().equals("oauth_age_range") ? bN.m131a(bO.CONSENT_AGREEMENT_ATTRIBUTE_AGE_RANGE) : str.toLowerCase().equals("oauth_date_of_birth") ? bN.m131a(bO.CONSENT_AGREEMENT_ATTRIBUTE_DATE_OF_BIRTH) : str.toLowerCase().equals("oauth_email") ? bN.m131a(bO.CONSENT_AGREEMENT_ATTRIBUTE_EMAIL_ADDRESS) : str.toLowerCase().equals("oauth_fullname") ? bN.m131a(bO.CONSENT_AGREEMENT_ATTRIBUTE_FULL_NAME) : str.toLowerCase().equals("oauth_gender") ? bN.m131a(bO.CONSENT_AGREEMENT_ATTRIBUTE_GENDER) : str.toLowerCase().equals("oauth_language") ? bN.m131a(bO.CONSENT_AGREEMENT_ATTRIBUTE_LANGUAGE) : str.toLowerCase().equals("oauth_locale") ? bN.m131a(bO.CONSENT_AGREEMENT_ATTRIBUTE_LOCALE) : str.toLowerCase().equals("oauth_phone_number") ? bN.m131a(bO.CONSENT_AGREEMENT_ATTRIBUTE_PHONE) : str.toLowerCase().equals("oauth_timezone") ? bN.m131a(bO.CONSENT_AGREEMENT_ATTRIBUTE_TIME_ZONE) : str;
    }

    private void m592a() {
        new StringBuilder().append(getClass().getSimpleName()).append(".doLogin");
        if (C0115n.m681a((Context) this, this.f648h)) {
            startActivityForResult(aW.m55a(this.f648h.m503c().m417k(), aY.PROMPT_LOGIN, aX.code, C0072c.m291a().m294c().m337e()), 2);
            return;
        }
        String str = f641a;
        LoginActivity.m385a(this, 1, null, true, this.f648h.m503c());
    }

    private void m593a(int i, PayPalAuthorization payPalAuthorization) {
        Intent intent = new Intent();
        intent.putExtra(PayPalProfileSharingActivity.EXTRA_RESULT_AUTHORIZATION, payPalAuthorization);
        setResult(i, intent);
    }

    static void m594a(Activity activity, int i, PayPalConfiguration payPalConfiguration) {
        String str = f641a;
        Intent intent = new Intent(activity, ProfileSharingConsentActivity.class);
        intent.putExtras(activity.getIntent());
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, payPalConfiguration);
        activity.startActivityForResult(intent, 1);
    }

    private void m595a(Bundle bundle) {
        String string = bundle.getString("authAccount");
        String string2 = bundle.getString("code");
        String string3 = bundle.getString("nonce");
        for (String str : bundle.keySet()) {
            if (bundle.get(str) == null) {
                String.format("%s:null", new Object[]{(String) r4.next()});
            } else {
                String.format("%s:%s (%s)", new Object[]{(String) r4.next(), bundle.get(str).toString(), bundle.get(str).getClass().getName()});
            }
            String str2 = f641a;
        }
        be beVar = new be(this, string2, string3, string);
        if (this.f648h == null) {
            this.f645e = beVar;
        } else {
            m600a(beVar);
        }
    }

    private void m596a(SpannableString spannableString) {
        int i = 0;
        URLSpan[] uRLSpanArr = (URLSpan[]) spannableString.getSpans(0, spannableString.length(), URLSpan.class);
        int length = uRLSpanArr.length;
        while (i < length) {
            URLSpan uRLSpan = uRLSpanArr[i];
            spannableString.setSpan(new C0109c(uRLSpan, this, FuturePaymentInfoActivity.class, new aX(this)), spannableString.getSpanStart(uRLSpan), spannableString.getSpanEnd(uRLSpan), 33);
            spannableString.removeSpan(uRLSpan);
            i++;
        }
    }

    static /* synthetic */ void m598a(ProfileSharingConsentActivity profileSharingConsentActivity, aa aaVar) {
        profileSharingConsentActivity.f644d = new aR(aaVar);
        profileSharingConsentActivity.getIntent().putExtra("com.paypal.android.sdk.payments.ppAppInfo", profileSharingConsentActivity.f644d);
        profileSharingConsentActivity.m604c();
        try {
            profileSharingConsentActivity.dismissDialog(2);
        } catch (IllegalArgumentException e) {
        }
    }

    private void m600a(be beVar) {
        this.f648h.m502b().f98g = beVar.f728b;
        this.f648h.m502b().f97f = beVar.f729c;
        this.f648h.m502b().f95d = beVar.f727a;
        showDialog(2);
        m602b();
    }

    private void m602b() {
        if (this.f648h != null) {
            this.f648h.m514n();
        }
    }

    private void m603b(SpannableString spannableString) {
        int i = 0;
        URLSpan[] uRLSpanArr = (URLSpan[]) spannableString.getSpans(0, spannableString.length(), URLSpan.class);
        int length = uRLSpanArr.length;
        while (i < length) {
            URLSpan uRLSpan = uRLSpanArr[i];
            spannableString.setSpan(new bm(uRLSpan, new aY(this)), spannableString.getSpanStart(uRLSpan), spannableString.getSpanEnd(uRLSpan), 33);
            spannableString.removeSpan(uRLSpan);
            i++;
        }
    }

    private void m604c() {
        if (this.f643b != null && this.f644d != null && this.f648h != null) {
            String l = this.f648h.m503c().m418l();
            if (this.f644d.m626d() != null) {
                l = this.f644d.m626d();
            }
            String uri = this.f648h.m503c().m419m().toString();
            if (this.f644d.m624b() != null) {
                uri = this.f644d.m624b();
            }
            String uri2 = this.f648h.m503c().m420n().toString();
            if (this.f644d.m625c() != null) {
                uri2 = this.f644d.m625c();
            }
            f642c = this.f648h.m503c().m407a();
            String format = String.format(bN.m131a(bO.CONSENT_AGREEMENT_INTRO), new Object[]{"<b>" + l + "</b>"});
            String str = bN.m134c(f642c) ? "\u200f" : BuildConfig.VERSION_NAME;
            this.f647g.f203c[0].setText(Html.fromHtml(str + format));
            if (bN.m134c(f642c)) {
                this.f647g.f203c[0].setGravity(5);
            }
            int i = 1;
            this.f647g.f203c[0].setVisibility(0);
            List a = this.f643b.m431a();
            PayPalScope[] values = PayPalScope.values();
            int length = values.length;
            int i2 = 0;
            Object obj = null;
            while (i2 < length) {
                int i3;
                PayPalScope payPalScope = values[i2];
                if (a.contains(payPalScope.m457a())) {
                    if (payPalScope.equals(PayPalScope.FUTURE_PAYMENTS)) {
                        SpannableString spannableString = new SpannableString(Html.fromHtml(str + String.format(bN.m131a(payPalScope.m458b()), new Object[]{"future-payment-consent", "<b>" + l + "</b>", "<b>" + l + "</b>"})));
                        m596a(spannableString);
                        spannableString.setSpan(new BulletSpan(15), 0, spannableString.length(), 0);
                        this.f647g.f203c[i].setVisibility(0);
                        this.f647g.f203c[i].setFocusable(true);
                        this.f647g.f203c[i].setNextFocusLeftId((47010 + i) - 1);
                        this.f647g.f203c[i].setNextFocusRightId((47010 + i) + 1);
                        i3 = i + 1;
                        this.f647g.f203c[i].setText(spannableString);
                    } else if (payPalScope.f582a && m610e() && r5 == null) {
                        CharSequence spannableString2 = new SpannableString(str + String.format(bN.m131a(payPalScope.m458b()), new Object[]{m611f()}));
                        spannableString2.setSpan(new BulletSpan(15), 0, spannableString2.length(), 0);
                        this.f647g.f203c[i].setVisibility(0);
                        this.f647g.f203c[i].setFocusable(true);
                        this.f647g.f203c[i].setNextFocusLeftId((47010 + i) - 1);
                        this.f647g.f203c[i].setNextFocusRightId((47010 + i) + 1);
                        i3 = i + 1;
                        this.f647g.f203c[i].setText(spannableString2);
                        obj = 1;
                    }
                    i2++;
                    i = i3;
                }
                i3 = i;
                i2++;
                i = i3;
            }
            l = String.format(bN.m131a(bO.CONSENT_AGREEMENT_MERCHANT_PRIVACY_POLICY), new Object[]{"<b>" + l + "</b>", uri, uri2});
            uri = f641a;
            SpannableString spannableString3 = new SpannableString(Html.fromHtml(str + l));
            spannableString3.setSpan(new BulletSpan(15), 0, spannableString3.length(), 0);
            m603b(spannableString3);
            this.f647g.f203c[i].setVisibility(0);
            this.f647g.f203c[i].setFocusable(true);
            this.f647g.f203c[i].setNextFocusLeftId((47010 + i) - 1);
            this.f647g.f203c[i].setNextFocusRightId(47002);
            int i4 = i + 1;
            this.f647g.f203c[i].setText(spannableString3);
            uri = bN.m131a(bO.PRIVACY);
            Object[] objArr = new Object[1];
            CharSequence toLowerCase = Locale.getDefault().getCountry().toLowerCase(Locale.US);
            if (C0069T.m51c(toLowerCase)) {
                toLowerCase = "us";
            }
            objArr[0] = String.format("https://www.paypal.com/%s/cgi-bin/webscr?cmd=p/gen/ua/policy_privacy-outside", new Object[]{toLowerCase});
            spannableString3 = new SpannableString(Html.fromHtml(str + String.format(uri, objArr)));
            m605c(spannableString3);
            this.f647g.f204d.setText(spannableString3);
            this.f647g.f204d.setMovementMethod(LinkMovementMethod.getInstance());
            this.f647g.f204d.setNextFocusLeftId((47010 + i4) - 1);
            this.f647g.f204d.setNextFocusRightId(47001);
            toLowerCase = C0108b.m650a(this.f648h.m503c().m407a());
            if (toLowerCase != null) {
                this.f647g.f205e.setText(toLowerCase);
                this.f647g.f205e.setVisibility(0);
            }
            this.f647g.f209i.setText(bN.m131a(bO.CONSENT_AGREEMENT_AGREE));
            this.f647g.f207g.setOnClickListener(new aV(this));
            this.f647g.f208h.setOnClickListener(new aW(this));
            this.f647g.f208h.setEnabled(true);
            if (this.f645e != null) {
                m600a(this.f645e);
                this.f645e = null;
            }
        }
    }

    private void m605c(SpannableString spannableString) {
        int i = 0;
        URLSpan[] uRLSpanArr = (URLSpan[]) spannableString.getSpans(0, spannableString.length(), URLSpan.class);
        int length = uRLSpanArr.length;
        while (i < length) {
            URLSpan uRLSpan = uRLSpanArr[i];
            spannableString.setSpan(new bm(uRLSpan, new aZ(this)), spannableString.getSpanStart(uRLSpan), spannableString.getSpanEnd(uRLSpan), 33);
            spannableString.removeSpan(uRLSpan);
            i++;
        }
    }

    static /* synthetic */ void m606c(ProfileSharingConsentActivity profileSharingConsentActivity) {
        profileSharingConsentActivity.f648h.m487a(bM.ConsentCancel);
        profileSharingConsentActivity.finish();
    }

    private void m607d() {
        this.f650j = bindService(C0108b.m657b(this), this.f649i, 1);
    }

    static /* synthetic */ void m608d(ProfileSharingConsentActivity profileSharingConsentActivity) {
        profileSharingConsentActivity.f648h.m487a(bM.ConsentAgree);
        if (profileSharingConsentActivity.f648h.m509i() && profileSharingConsentActivity.f648h.m507g()) {
            profileSharingConsentActivity.showDialog(2);
            profileSharingConsentActivity.f648h.m499a(profileSharingConsentActivity.f643b.m431a());
            return;
        }
        C0108b.m653a((Activity) profileSharingConsentActivity, bN.m131a(bO.SESSION_EXPIRED_MESSAGE), 4);
    }

    static /* synthetic */ void m609e(ProfileSharingConsentActivity profileSharingConsentActivity) {
        boolean z;
        new StringBuilder().append(profileSharingConsentActivity.getClass().getSimpleName()).append(".postBindSetup()");
        new StringBuilder().append(profileSharingConsentActivity.getClass().getSimpleName()).append(".startLoginIfNeeded (access token: ").append(profileSharingConsentActivity.f648h.m502b().f99h);
        if (profileSharingConsentActivity.f648h.m508h() || profileSharingConsentActivity.f651k) {
            z = false;
        } else {
            new StringBuilder().append(profileSharingConsentActivity.getClass().getSimpleName()).append(" -- doing the login...");
            profileSharingConsentActivity.f651k = true;
            profileSharingConsentActivity.m592a();
            z = true;
        }
        if (!profileSharingConsentActivity.f646f) {
            profileSharingConsentActivity.f646f = true;
            profileSharingConsentActivity.f648h.m487a(bM.ConsentWindow);
        }
        C0108b.m654a(profileSharingConsentActivity.f647g.f206f.f214b, profileSharingConsentActivity.f648h.m504d());
        profileSharingConsentActivity.f648h.m492a(new aT(profileSharingConsentActivity));
        profileSharingConsentActivity.m604c();
        if (!z && profileSharingConsentActivity.f644d == null) {
            profileSharingConsentActivity.m602b();
        }
    }

    private boolean m610e() {
        Set hashSet = new HashSet();
        for (String a : this.f644d.m623a()) {
            String a2 = m591a(a2);
            if (a2 != null) {
                hashSet.add(a2);
            }
        }
        return hashSet.size() > 0;
    }

    private String m611f() {
        String a;
        Set<String> hashSet = new HashSet();
        for (String a2 : this.f644d.m623a()) {
            a2 = m591a(a2);
            if (a2 != null) {
                hashSet.add(a2);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        a2 = BuildConfig.VERSION_NAME;
        String str = a2;
        for (String a22 : hashSet) {
            stringBuilder.append(str);
            stringBuilder.append(a22);
            str = ", ";
        }
        return stringBuilder.toString();
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
                if (i2 == -1) {
                    showDialog(2);
                    m602b();
                    return;
                }
                m593a(i2, null);
                finish();
            case CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER /*2*/:
                if (i2 == -1) {
                    m595a(intent.getExtras());
                    return;
                }
                m593a(i2, null);
                finish();
            default:
                Log.e(f641a, "unhandled requestCode " + i);
        }
    }

    public final void onBackPressed() {
        this.f648h.m487a(bM.ConsentCancel);
        super.onBackPressed();
    }

    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        new StringBuilder().append(getClass().getSimpleName()).append(".onCreate");
        if (bundle == null) {
            if (!C0108b.m655a((Activity) this)) {
                finish();
            }
            this.f646f = false;
        } else {
            this.f646f = bundle.getBoolean("pageTrackingSent");
            this.f651k = bundle.getBoolean("isLoginActivityStarted");
        }
        this.f643b = (PayPalOAuthScopes) getIntent().getParcelableExtra(PayPalProfileSharingActivity.EXTRA_REQUESTED_SCOPES);
        this.f644d = (aR) getIntent().getParcelableExtra("com.paypal.android.sdk.payments.ppAppInfo");
        m607d();
        C0069T.m49b((Activity) this);
        C0069T.m38a((Activity) this);
        this.f647g = new bQ(this);
        setContentView(this.f647g.f201a);
        C0108b.m651a((Activity) this, this.f647g.f202b, null);
        this.f647g.f207g.setText(bt.m258a(bN.m131a(bO.CANCEL)));
        this.f647g.f207g.setVisibility(0);
        m604c();
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
                return C0108b.m648a((Activity) this, bO.SESSION_EXPIRED_TITLE, bundle, new bc(this));
            case FragmentManagerImpl.ANIM_STYLE_FADE_ENTER /*5*/:
                return C0108b.m644a((Context) this, bO.CONSENT_FAILED_ALERT_TITLE, bN.m132a("invalid_scope"), new bd(this));
            case FragmentManagerImpl.ANIM_STYLE_FADE_EXIT /*6*/:
                return C0108b.m644a((Context) this, bO.CONSENT_FAILED_ALERT_TITLE, bN.m132a("server_error"), new aU(this));
            default:
                return null;
        }
    }

    protected final void onDestroy() {
        new StringBuilder().append(getClass().getSimpleName()).append(".onDestroy");
        if (this.f648h != null) {
            this.f648h.m511k();
        }
        if (this.f650j) {
            unbindService(this.f649i);
            this.f650j = false;
        }
        super.onDestroy();
    }

    protected final void onRestart() {
        super.onRestart();
        m607d();
    }

    protected final void onResume() {
        super.onResume();
        new StringBuilder().append(getClass().getSimpleName()).append(".onResume");
    }

    protected final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("pageTrackingSent", this.f646f);
        bundle.putBoolean("isLoginActivityStarted", this.f651k);
    }
}
