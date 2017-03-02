package com.paypal.android.sdk.payments;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.paypal.android.sdk.C0071b;
import com.paypal.android.sdk.C0072c;
import com.paypal.android.sdk.C0074e;
import com.paypal.android.sdk.C0077h;
import com.paypal.android.sdk.C0123q;
import com.paypal.android.sdk.C0125s;
import com.paypal.android.sdk.C0232L;
import com.paypal.android.sdk.ao;
import com.paypal.android.sdk.ap;
import com.paypal.android.sdk.bA;
import com.paypal.android.sdk.bB;
import com.paypal.android.sdk.bC;
import com.paypal.android.sdk.bD;
import com.paypal.android.sdk.bF;
import com.paypal.android.sdk.bH;
import com.paypal.android.sdk.bM;
import com.paypal.android.sdk.bN;
import com.paypal.android.sdk.bu;
import com.paypal.android.sdk.bz;
import io.card.payment.CreditCard;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

public final class PayPalService extends Service {
    public static final String EXTRA_PAYPAL_CONFIGURATION = "com.paypal.android.sdk.paypalConfiguration";
    private static final String f585b;
    private static boolean f586c;
    private static boolean f587d;
    private static boolean f588e;
    private static Intent f589u;
    bH f590a;
    private C0074e f591f;
    private bA f592g;
    private PayPalConfiguration f593h;
    private boolean f594i;
    private boolean f595j;
    private ag f596k;
    private C0107a f597l;
    private C0114l f598m;
    private String f599n;
    private C0232L f600o;
    private af f601p;
    private ae f602q;
    private List f603r;
    private boolean f604s;
    private boolean f605t;
    private ah f606v;
    private final IBinder f607w;

    static {
        f585b = PayPalService.class.getSimpleName();
        f586c = true;
        f587d = true;
        f588e = true;
    }

    public PayPalService() {
        this.f597l = new C0107a();
        this.f598m = new C0240i(this);
        this.f603r = new ArrayList();
        this.f604s = f586c;
        this.f605t = f587d;
        this.f607w = new ad(this);
    }

    private void m459A() {
        m465a(new bD());
    }

    private ag m462a(ap apVar) {
        return new ag(this, apVar.m74D().m98b(), apVar.m76F(), apVar.m74D().m97a());
    }

    private void m464a(Intent intent) {
        int i = 500;
        if (intent == null || intent.getExtras() == null) {
            throw new RuntimeException("Service extras required. Please see the docs.");
        }
        f589u = intent;
        String str = f585b;
        new StringBuilder("init:").append(intent.toString()).append(" with extras:").append(intent.getExtras());
        for (String str2 : intent.getExtras().keySet()) {
            String str3 = f585b;
            new StringBuilder("==SERVICE EXTRA:(").append(str2).append(",").append(intent.getExtras().get(str2)).append(")");
        }
        if (this.f593h == null) {
            this.f593h = (PayPalConfiguration) intent.getParcelableExtra(EXTRA_PAYPAL_CONFIGURATION);
            if (this.f593h == null) {
                throw new RuntimeException("Missing EXTRA_PAYPAL_CONFIGURATION in service intent. Please see the docs.");
            }
        }
        if (this.f593h.m421o()) {
            str3 = this.f593h.m408b();
            if (str3.equals(PayPalConfiguration.ENVIRONMENT_PRODUCTION)) {
                str2 = "https://api.paypal.com/v1/";
            } else if (str3.startsWith(PayPalConfiguration.ENVIRONMENT_SANDBOX)) {
                str2 = "https://api.sandbox.paypal.com/v1/";
            } else if (str3.equals(PayPalConfiguration.ENVIRONMENT_NO_NETWORK)) {
                str2 = null;
            } else if ("partner".equals(BuildConfig.FLAVOR) && intent.hasExtra("com.paypal.android.sdk.baseEnvironmentUrl")) {
                str2 = intent.getStringExtra("com.paypal.android.sdk.baseEnvironmentUrl");
            } else {
                throw new RuntimeException("Invalid environment selected:" + str3);
            }
            C0077h c0077h = new C0077h();
            c0077h.m343a(str3);
            c0077h.m344a(new HashMap());
            if (str2 != null) {
                if (str2.startsWith("https://")) {
                    if (!str2.endsWith("/")) {
                        Log.w(f585b, str2 + " does not end with a slash, adding one.");
                        str2 = str2 + "/";
                    }
                    c0077h.m345b().put(C0071b.PreAuthRequest, str2 + "oauth2/token");
                    c0077h.m345b().put(C0071b.LoginRequest, str2 + "oauth2/login");
                    c0077h.m345b().put(C0071b.ConsentRequest, str2 + "oauth2/consent");
                    c0077h.m345b().put(C0071b.CreditCardPaymentRequest, str2 + "payments/payment");
                    c0077h.m345b().put(C0071b.PayPalPaymentRequest, str2 + "payments/payment");
                    c0077h.m345b().put(C0071b.CreateSfoPaymentRequest, str2 + "orchestration/msdk-create-sfo-payment");
                    c0077h.m345b().put(C0071b.ApproveAndExecuteSfoPaymentRequest, str2 + "orchestration/msdk-approve-and-execute-sfo-payment");
                    c0077h.m345b().put(C0071b.TokenizeCreditCardRequest, str2 + "vault/credit-card");
                    c0077h.m345b().put(C0071b.DeleteCreditCardRequest, str2 + "vault/credit-card");
                    c0077h.m345b().put(C0071b.GetAppInfoRequest, str2 + "apis/applications");
                } else {
                    throw new RuntimeException(str2 + " does not start with 'https://', ignoring " + str3);
                }
            }
            if (this.f600o == null) {
                if ("partner".equals(BuildConfig.FLAVOR) && intent.hasExtra("com.paypal.android.sdk.mockNetworkDelay")) {
                    i = intent.getIntExtra("com.paypal.android.sdk.mockNetworkDelay", 500);
                }
                this.f604s = f586c;
                if ("partner".equals(BuildConfig.FLAVOR) && intent.hasExtra("com.paypal.android.sdk.enableAuthenticator")) {
                    this.f604s = intent.getBooleanExtra("com.paypal.android.sdk.enableAuthenticator", f586c);
                }
                if ("partner".equals(BuildConfig.FLAVOR) && intent.hasExtra("com.paypal.android.sdk.enableAuthenticatorSecurity")) {
                    this.f605t = intent.getBooleanExtra("com.paypal.android.sdk.enableAuthenticatorSecurity", f587d);
                }
                boolean z = f588e;
                if ("partner".equals(BuildConfig.FLAVOR) && intent.hasExtra("com.paypal.android.sdk.enableStageSsl")) {
                    z = intent.getBooleanExtra("com.paypal.android.sdk.enableStageSsl", f588e);
                }
                if (this.f591f == null) {
                    this.f591f = new C0241m();
                }
                this.f600o = new C0232L(c0077h, this.f591f, bz.m975d(), z, i);
            }
            bN.m133b(this.f593h.m407a());
            if (this.f606v == null) {
                this.f606v = new ah(this, this.f600o);
            }
            if (this.f592g == null) {
                this.f592g = m484y();
            }
            if (!this.f593h.m416j()) {
                m518r();
                m506f();
                m459A();
            }
            m485z();
            return;
        }
        throw new RuntimeException("Service extras invalid.  Please check the docs.");
    }

    private void m465a(bD bDVar) {
        new bC(C0072c.m291a().m294c(), this.f593h.m408b()).m116a(bDVar);
    }

    private void m466a(bM bMVar, boolean z, String str, String str2, String str3) {
        this.f598m.m680a(bMVar, z, str, str2, str3);
    }

    static /* synthetic */ void m467a(PayPalService payPalService, ap apVar) {
        payPalService.f592g.f94c = null;
        String str = f585b;
        new StringBuilder().append(apVar.m72B()).append(" request error");
        str = apVar.m74D().m98b();
        Log.e("paypal.sdk", str);
        payPalService.m475b(bM.DeviceCheck, str, apVar.m96z());
        if (payPalService.f601p != null) {
            str = f585b;
            payPalService.f601p.m632a(payPalService.m462a(apVar));
            payPalService.f601p = null;
        }
        payPalService.f594i = false;
    }

    private static boolean m470a(bu buVar) {
        return buVar != null && buVar.m290c();
    }

    private ao[] m472a(PayPalItem[] payPalItemArr) {
        if (payPalItemArr == null) {
            return null;
        }
        ao[] aoVarArr = new ao[payPalItemArr.length];
        int length = payPalItemArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            PayPalItem payPalItem = payPalItemArr[i];
            int i3 = i2 + 1;
            aoVarArr[i2] = new ao(payPalItem.getName(), payPalItem.getQuantity(), payPalItem.getPrice(), payPalItem.getCurrency(), payPalItem.getSku());
            i++;
            i2 = i3;
        }
        return aoVarArr;
    }

    private void m475b(bM bMVar, String str, String str2) {
        m466a(bMVar, false, str, str2, null);
    }

    static /* synthetic */ void m480d(PayPalService payPalService, ap apVar) {
        String b = apVar.m74D().m98b();
        Log.e("paypal.sdk", b);
        payPalService.m475b(bM.ConfirmPayment, b, apVar.m96z());
        payPalService.f597l.m620a(payPalService.m462a(apVar));
    }

    private boolean m483x() {
        return (this.f593h == null || this.f592g == null) ? false : true;
    }

    private static bA m484y() {
        return new bA();
    }

    private void m485z() {
        m494a(new ab(this), false);
    }

    protected final C0232L m486a() {
        return this.f600o;
    }

    final void m487a(bM bMVar) {
        m466a(bMVar, false, null, null, null);
    }

    final void m488a(bM bMVar, Boolean bool) {
        m466a(bMVar, bool.booleanValue(), null, null, null);
    }

    final void m489a(bM bMVar, Boolean bool, String str) {
        m466a(bMVar, bool.booleanValue(), null, str, null);
    }

    final void m490a(bM bMVar, String str) {
        m466a(bMVar, false, null, str, null);
    }

    final void m491a(bM bMVar, String str, String str2) {
        m466a(bMVar, false, null, str, str2);
    }

    final void m492a(ac acVar) {
        this.f597l.m619a(acVar);
    }

    final void m493a(ae aeVar) {
        if (this.f595j) {
            this.f595j = false;
            if (this.f596k != null) {
                aeVar.m630a(this.f596k);
                this.f596k = null;
            } else {
                aeVar.m629a();
            }
            m512l();
            return;
        }
        this.f602q = aeVar;
    }

    final void m494a(af afVar, boolean z) {
        if (z) {
            this.f592g.f94c = null;
        }
        this.f601p = afVar;
        if (!this.f594i && !this.f592g.m110c()) {
            this.f594i = true;
            m487a(bM.DeviceCheck);
            this.f600o.m854a(this.f593h.m417k());
        }
    }

    final void m495a(C0123q c0123q, boolean z, String str, boolean z2, String str2) {
        if (this.f592g.f94c != null) {
            this.f592g.f94c.m289b();
        }
        this.f600o.m853a(c0123q, this.f593h.m417k(), z, str, z2, str2);
    }

    final void m496a(C0125s c0125s, Map map, PayPalItem[] payPalItemArr, String str, boolean z, String str2, String str3, JSONObject jSONObject, boolean z2, String str4, String str5, String str6) {
        String str7 = f585b;
        this.f600o.m856a(this.f592g.f99h.m289b(), this.f592g.m109b(), null, c0125s, map, m472a(payPalItemArr), str, z, str2, null, str3, jSONObject, z2, str4, str5, str6);
    }

    final void m497a(String str, CreditCard creditCard, C0125s c0125s, Map map, PayPalItem[] payPalItemArr, String str2, boolean z, String str3, String str4, String str5, String str6, String str7) {
        String str8 = f585b;
        if (creditCard.cardNumber.equalsIgnoreCase("4111111111111111") && this.f593h.m408b().startsWith(PayPalConfiguration.ENVIRONMENT_SANDBOX)) {
            creditCard.cardNumber = "4444333322221111";
        }
        this.f600o.m859a(this.f592g.f94c.m289b(), str, creditCard.getCardType().toString().toLowerCase(Locale.US), creditCard.cardNumber, creditCard.cvv, creditCard.expiryMonth, creditCard.expiryYear, null, c0125s, map, m472a(payPalItemArr), str2, z, str3, null, str4, str5, str6, str7);
    }

    final void m498a(String str, String str2, C0125s c0125s, Map map, PayPalItem[] payPalItemArr, String str3, boolean z, String str4, String str5, String str6, String str7, String str8, String str9) {
        String str10 = f585b;
        this.f600o.m857a(this.f592g.f94c.m289b(), str, str2, str4, c0125s, map, m472a(payPalItemArr), str3, z, str5, null, str6, str7, str8, str9);
    }

    final void m499a(List list) {
        String str = f585b;
        String str2 = null;
        if (this.f592g.f94c != null) {
            str2 = this.f592g.f94c.m289b();
        }
        this.f600o.m860a(this.f593h.m417k(), this.f592g.f98g, this.f592g.f97f, str2, list);
    }

    final void m500a(boolean z, String str, String str2, JSONObject jSONObject, JSONObject jSONObject2, String str3) {
        String str4 = f585b;
        this.f600o.m861a(this.f592g.f99h.m289b(), this.f592g.m109b(), str3, null, z, str, str2, jSONObject, jSONObject2);
    }

    protected final boolean m501a(ai aiVar) {
        if (m483x()) {
            return true;
        }
        String str = f585b;
        this.f603r.add(aiVar);
        return false;
    }

    protected final bA m502b() {
        return this.f592g;
    }

    final PayPalConfiguration m503c() {
        return this.f593h;
    }

    protected final String m504d() {
        return this.f593h.m408b();
    }

    protected final String m505e() {
        return this.f593h.m417k();
    }

    final void m506f() {
        this.f592g.f99h = null;
        bB.m113b(this.f593h.m408b());
        this.f592g.f96e = null;
        this.f592g.f95d = null;
    }

    final boolean m507g() {
        return this.f592g.m110c();
    }

    final boolean m508h() {
        bA bAVar = this.f592g;
        return bAVar.f99h != null && bAVar.f99h.m290c();
    }

    final boolean m509i() {
        return this.f592g.f98g != null;
    }

    final void m510j() {
        bD o = m515o();
        if (o == null) {
            m506f();
            return;
        }
        bu buVar = this.f592g.f99h;
        bu a = bB.m111a(this.f593h.m408b());
        String str;
        if (m470a(buVar) || !m470a(a)) {
            str = f585b;
        } else {
            this.f592g.f99h = a;
            str = f585b;
        }
        bA bAVar = this.f592g;
        String b = o.m124d() ? o.m123c().equals(bF.EMAIL) ? o.m122b() : o.m118a().m705a(bz.m975d()) : null;
        bAVar.f95d = b;
    }

    final void m511k() {
        this.f597l.m622b();
    }

    final void m512l() {
        String str = f585b;
        this.f602q = null;
    }

    final void m513m() {
        String str = f585b;
        this.f601p = null;
    }

    final void m514n() {
        this.f600o.m855a(this.f592g.f94c.m289b(), this.f593h.m417k());
    }

    final bD m515o() {
        return new bC(C0072c.m291a().m294c(), this.f593h.m408b()).m114a();
    }

    public final IBinder onBind(Intent intent) {
        String str = f585b;
        new bi(this).m661a();
        if (!m483x()) {
            m464a(f589u);
        }
        return this.f607w;
    }

    public final void onCreate() {
        String str = f585b;
        new StringBuilder("service created: ").append(this);
        bg.m659a(this);
    }

    public final void onDestroy() {
        if (this.f606v != null) {
            this.f606v.m16b();
            this.f606v = null;
        }
        if (this.f600o != null) {
            this.f600o.m849a();
            this.f600o = null;
        }
        String str = f585b;
        new StringBuilder("service destroyed: ").append(this);
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        String str = f585b;
        new StringBuilder("onStartCommand: Received start id: ").append(i2);
        m464a(intent);
        this.f599n = intent.getComponent().getPackageName();
        m487a(bM.PreConnect);
        if (this.f603r.size() > 0) {
            for (ai a : this.f603r) {
                a.m634a();
            }
            this.f603r.clear();
        }
        return 3;
    }

    final String m516p() {
        return this.f592g.f95d;
    }

    final bH m517q() {
        return new bC(C0072c.m291a().m294c(), this.f593h.m408b()).m115a(this.f593h.m417k());
    }

    final void m518r() {
        this.f590a = m517q();
        new bC(C0072c.m291a().m294c(), this.f593h.m408b()).m117a(new bH(), null);
        if (this.f590a != null && this.f592g.f94c != null) {
            this.f600o.m864b(this.f592g.f94c.m289b(), this.f590a.m961f());
            this.f590a = null;
        }
    }

    final void m519s() {
        if (this.f593h == null || !this.f593h.m421o()) {
            String str = f585b;
            return;
        }
        this.f592g = m484y();
        m485z();
    }

    protected final String m520t() {
        return this.f599n;
    }

    final boolean m521u() {
        return this.f604s;
    }

    final boolean m522v() {
        return this.f605t;
    }
}
