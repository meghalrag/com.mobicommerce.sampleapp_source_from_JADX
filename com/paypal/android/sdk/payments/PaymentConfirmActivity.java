package com.paypal.android.sdk.payments;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.v4.media.TransportMediator;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.widget.ListView;
import com.paypal.android.sdk.C0069T;
import com.paypal.android.sdk.C0072c;
import com.paypal.android.sdk.C0082m;
import com.paypal.android.sdk.C0125s;
import com.paypal.android.sdk.C0297X;
import com.paypal.android.sdk.aW;
import com.paypal.android.sdk.aX;
import com.paypal.android.sdk.aY;
import com.paypal.android.sdk.bH;
import com.paypal.android.sdk.bM;
import com.paypal.android.sdk.bN;
import com.paypal.android.sdk.bO;
import com.paypal.android.sdk.bP;
import com.paypal.android.sdk.bS;
import com.paypal.android.sdk.bT;
import com.paypal.android.sdk.bX;
import com.paypal.android.sdk.bu;
import com.paypal.android.sdk.bz;
import com.paypal.android.sdk.cc;
import com.paypal.android.sdk.cd;
import io.card.payment.CardType;
import io.card.payment.CreditCard;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

public final class PaymentConfirmActivity extends Activity {
    private static final String f614a;
    private aE f615b;
    private bj f616c;
    private boolean f617d;
    private bX f618e;
    private ap f619f;
    private aF f620g;
    private CreditCard f621h;
    private PayPalService f622i;
    private final ServiceConnection f623j;
    private boolean f624k;

    static {
        f614a = PaymentConfirmActivity.class.getSimpleName();
    }

    public PaymentConfirmActivity() {
        this.f623j = new au(this);
    }

    private static C0125s m533a(PayPalPayment payPalPayment) {
        return new C0125s(new BigDecimal(C0082m.m356a(payPalPayment.m435a().doubleValue(), payPalPayment.m438d()).trim()), payPalPayment.m438d());
    }

    private void m535a(int i) {
        setResult(i, new Intent());
    }

    static void m536a(Activity activity, int i, aF aFVar, CreditCard creditCard) {
        String str = f614a;
        Intent intent = new Intent(activity, PaymentConfirmActivity.class);
        intent.putExtras(activity.getIntent());
        intent.putExtra("com.paypal.android.sdk.payments.PaymentConfirmActivity.EXTRA_PAYMENT_KIND", aFVar);
        intent.putExtra("com.paypal.android.sdk.payments.PaymentConfirmActivity.EXTRA_CREDIT_CARD", creditCard);
        activity.startActivityForResult(intent, 2);
    }

    private void m537a(Bundle bundle) {
        String string = bundle.getString("authAccount");
        String string2 = bundle.getString("authtoken");
        String string3 = bundle.getString("scope");
        long j = bundle.getLong("valid_until");
        for (String str : bundle.keySet()) {
            if (bundle.get(str) == null) {
                String.format("%s:null", new Object[]{(String) r7.next()});
            } else {
                String.format("%s:%s (%s)", new Object[]{(String) r7.next(), bundle.get(str).toString(), bundle.get(str).getClass().getName()});
            }
            String str2 = f614a;
        }
        bu buVar = new bu(string2, string3, j, false);
        if (this.f622i == null) {
            this.f615b = new aE(this, string, buVar);
        } else {
            m543a(string, buVar);
        }
    }

    static /* synthetic */ void m539a(PaymentConfirmActivity paymentConfirmActivity, C0297X c0297x) {
        paymentConfirmActivity.f616c = new bj(c0297x, paymentConfirmActivity.f619f.m641a().getProvidedShippingAddress());
        paymentConfirmActivity.getIntent().putExtra("com.paypal.android.sdk.payments.PaymentConfirmActivity.EXTRA_PAYMENT_INFO", paymentConfirmActivity.f616c);
        paymentConfirmActivity.m546b();
        paymentConfirmActivity.m564j();
    }

    static /* synthetic */ void m540a(PaymentConfirmActivity paymentConfirmActivity, List list, int i) {
        paymentConfirmActivity.f619f.m642b().m665a(i);
        paymentConfirmActivity.f618e.m155a((Context) paymentConfirmActivity, (bS) list.get(i));
    }

    private void m542a(String str) {
        this.f618e.m159a(str);
    }

    private void m543a(String str, bu buVar) {
        this.f622i.m502b().f95d = str;
        m542a(str);
        this.f622i.m502b().f99h = buVar;
        if (this.f620g != aF.PayPal) {
            this.f618e.m165b(true);
        }
    }

    private void m544a(boolean z) {
        if (C0115n.m681a((Context) this, this.f622i)) {
            startActivityForResult(aW.m57b(this.f622i.m503c().m417k(), z ? aY.PROMPT_LOGIN : aY.USER_REQUIRED, aX.token, C0072c.m291a().m294c().m337e()), 2);
            return;
        }
        String str = f614a;
        LoginActivity.m386a(this, 1, this.f622i.m515o(), false, z, "https://api.paypal.com/v1/payments/.*", this.f622i.m503c());
    }

    private static Map m545b(PayPalPayment payPalPayment) {
        if (payPalPayment != null) {
            Map hashMap = new HashMap();
            PayPalPaymentDetails f = payPalPayment.m440f();
            if (f != null) {
                if (f.m446b() != null) {
                    hashMap.put("shipping", C0082m.m356a(f.m446b().doubleValue(), payPalPayment.m438d()));
                }
                if (f.m445a() != null) {
                    hashMap.put("subtotal", C0082m.m356a(f.m445a().doubleValue(), payPalPayment.m438d()));
                }
                if (f.m447c() != null) {
                    hashMap.put("tax", C0082m.m356a(f.m447c().doubleValue(), payPalPayment.m438d()));
                }
            }
            if (!hashMap.isEmpty()) {
                return hashMap;
            }
        }
        return null;
    }

    private void m546b() {
        if (this.f616c != null) {
            Object cdVar;
            JSONObject jSONObject = null;
            if (this.f616c.m666b() != null) {
                jSONObject = this.f616c.m666b().toJSONObject();
            }
            int h = this.f616c.m673h();
            ArrayList a = cc.m980a(jSONObject, this.f616c.m664a(), this.f616c.m674i());
            if (a == null || a.size() <= 0) {
                this.f618e.m171f().setClickable(false);
                this.f618e.m171f().setVisibility(8);
            } else {
                this.f618e.m171f().setVisibility(0);
                this.f618e.m171f().setClickable(true);
                this.f618e.m156a(getApplicationContext(), (cc) a.get(h));
                cdVar = new cd(this, a, h);
                new ListView(this).setAdapter(cdVar);
                this.f618e.m169d(new ay(this, cdVar, a));
            }
            h = this.f616c.m672g();
            a = bS.m965a(this.f616c.m668c(), this.f616c.m669d());
            if (a == null || a.size() <= 0) {
                this.f618e.m170e().setClickable(false);
                this.f618e.m170e().setVisibility(8);
            } else {
                this.f618e.m170e().setVisibility(0);
                this.f618e.m170e().setClickable(true);
                this.f618e.m155a(getApplicationContext(), (bS) a.get(h));
                cdVar = new bT(this, a, h);
                new ListView(this).setAdapter(cdVar);
                this.f618e.m167c(new aw(this, cdVar, a));
            }
            this.f618e.m165b(true);
        }
    }

    static /* synthetic */ void m547b(PaymentConfirmActivity paymentConfirmActivity) {
        if (paymentConfirmActivity.f620g.equals(aF.PayPal)) {
            paymentConfirmActivity.f618e.m157a(C0108b.m650a(paymentConfirmActivity.f622i.m503c().m407a()));
        } else {
            paymentConfirmActivity.f618e.m157a(null);
        }
        if (paymentConfirmActivity.f615b != null) {
            paymentConfirmActivity.m543a(paymentConfirmActivity.f615b.f678a, paymentConfirmActivity.f615b.f679b);
            paymentConfirmActivity.f615b = null;
        }
    }

    static /* synthetic */ void m548b(PaymentConfirmActivity paymentConfirmActivity, List list, int i) {
        paymentConfirmActivity.f619f.m642b().m667b(i);
        paymentConfirmActivity.f618e.m156a((Context) paymentConfirmActivity, (cc) list.get(i));
    }

    private void m549c() {
        if (this.f622i.m502b().f99h != null && !this.f622i.m502b().f99h.m974a()) {
            this.f622i.m502b().f99h = null;
            this.f622i.m502b().f95d = null;
        }
    }

    private void m551d() {
        this.f624k = bindService(C0108b.m657b(this), this.f623j, 1);
    }

    static /* synthetic */ void m552d(PaymentConfirmActivity paymentConfirmActivity) {
        if (!paymentConfirmActivity.f617d) {
            paymentConfirmActivity.f617d = true;
            paymentConfirmActivity.f622i.m487a(bM.ConfirmPaymentWindow);
        }
        paymentConfirmActivity.m556f();
    }

    private boolean m554e() {
        if (!this.f620g.equals(aF.PayPal) || this.f622i.m508h()) {
            return false;
        }
        m544a(false);
        return true;
    }

    private void m556f() {
        PayPalPayment a = this.f619f.m641a();
        this.f618e.m161a(a.m436b(), C0082m.m362a(Locale.getDefault(), bz.m975d().m978b().m354a(), a.m435a().doubleValue(), a.m438d(), true));
        if (this.f620g == aF.PayPal) {
            this.f618e.m162a(true);
            m542a(this.f622i.m516p());
        } else if (this.f620g == aF.CreditCard || this.f620g == aF.CreditCardToken) {
            String b;
            int i;
            int i2;
            CardType cardType;
            this.f618e.m162a(false);
            if (this.f620g == aF.CreditCard) {
                b = bH.m955b(this.f621h.getRedactedCardNumber());
                i = this.f621h.expiryMonth;
                i2 = this.f621h.expiryYear;
                cardType = this.f621h.getCardType();
            } else {
                bH q = this.f622i.m517q();
                b = q.m960e();
                i = q.m963h();
                i2 = q.m964i();
                cardType = q.m962g();
            }
            this.f618e.m160a(b, bP.m135a(this, cardType), String.format(Locale.getDefault(), "%02d / %04d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        } else {
            Log.wtf(f614a, "Unknown payment type: " + this.f620g.toString());
            C0108b.m653a((Activity) this, "The payment is not a valid type. Please try again.", 3);
        }
        C0108b.m654a(this.f618e.m168d(), this.f622i.m504d());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m557g() {
        /*
        r17 = this;
        r1 = com.paypal.android.sdk.payments.as.f712a;
        r0 = r17;
        r2 = r0.f620g;
        r2 = r2.ordinal();
        r1 = r1[r2];
        switch(r1) {
            case 1: goto L_0x0013;
            case 2: goto L_0x001d;
            case 3: goto L_0x001d;
            default: goto L_0x000f;
        };
    L_0x000f:
        r1 = 1;
    L_0x0010:
        if (r1 != 0) goto L_0x0051;
    L_0x0012:
        return;
    L_0x0013:
        r1 = r17.m554e();
        if (r1 != 0) goto L_0x001b;
    L_0x0019:
        r1 = 1;
        goto L_0x0010;
    L_0x001b:
        r1 = 0;
        goto L_0x0010;
    L_0x001d:
        r0 = r17;
        r1 = r0.f622i;
        r1 = r1.m507g();
        if (r1 != 0) goto L_0x000f;
    L_0x0027:
        r1 = 2;
        r0 = r17;
        r0.showDialog(r1);
        r1 = f614a;
        r1 = new java.lang.StringBuilder;
        r2 = "token is expired, get new one. AccessToken: ";
        r1.<init>(r2);
        r0 = r17;
        r2 = r0.f622i;
        r2 = r2.m502b();
        r2 = r2.f94c;
        r1.append(r2);
        r0 = r17;
        r1 = r0.f622i;
        r2 = r17.m559h();
        r3 = 1;
        r1.m494a(r2, r3);
        r1 = 0;
        goto L_0x0010;
    L_0x0051:
        r1 = 2;
        r0 = r17;
        r0.showDialog(r1);
        r0 = r17;
        r1 = r0.f619f;
        r10 = r1.m641a();
        r6 = m533a(r10);
        r7 = m545b(r10);
        r9 = r10.m436b();
        r0 = r17;
        r1 = r0.f622i;
        r1 = r1.m503c();
        r2 = r1.m416j();
        r1 = com.paypal.android.sdk.payments.as.f712a;
        r0 = r17;
        r3 = r0.f620g;
        r3 = r3.ordinal();
        r1 = r1[r3];
        switch(r1) {
            case 1: goto L_0x0087;
            case 2: goto L_0x00bc;
            case 3: goto L_0x00fe;
            default: goto L_0x0086;
        };
    L_0x0086:
        goto L_0x0012;
    L_0x0087:
        r0 = r17;
        r1 = r0.f619f;
        r6 = r1.m642b();
        r0 = r17;
        r1 = r0.f622i;
        r3 = r6.m670e();
        r4 = r6.m671f();
        r5 = r6.m676k();
        if (r5 == 0) goto L_0x00b8;
    L_0x00a1:
        r5 = r6.m678m();
    L_0x00a5:
        r7 = r6.m675j();
        if (r7 == 0) goto L_0x00ba;
    L_0x00ab:
        r6 = r6.m677l();
    L_0x00af:
        r7 = r10.m439e();
        r1.m500a(r2, r3, r4, r5, r6, r7);
        goto L_0x0012;
    L_0x00b8:
        r5 = 0;
        goto L_0x00a5;
    L_0x00ba:
        r6 = 0;
        goto L_0x00af;
    L_0x00bc:
        r0 = r17;
        r1 = r0.f622i;
        r1 = r1.m517q();
        r0 = r17;
        r3 = r0.f622i;
        r0 = r17;
        r4 = r0.f622i;
        r4 = r4.m502b();
        r4 = r4.m109b();
        r5 = r1.m961f();
        r8 = r10.m441g();
        r11 = r1.m127b();
        r12 = r10.m439e();
        r1 = r10.m437c();
        r13 = r1.toString();
        r14 = r10.m442h();
        r15 = r10.m443i();
        r16 = r10.m444j();
        r10 = r2;
        r3.m498a(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16);
        goto L_0x0012;
    L_0x00fe:
        r0 = r17;
        r3 = r0.f622i;
        r0 = r17;
        r1 = r0.f622i;
        r1 = r1.m502b();
        r4 = r1.m109b();
        r0 = r17;
        r5 = r0.f621h;
        r8 = r10.m441g();
        r11 = r10.m439e();
        r1 = r10.m437c();
        r12 = r1.toString();
        r13 = r10.m442h();
        r14 = r10.m443i();
        r15 = r10.m444j();
        r10 = r2;
        r3.m497a(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15);
        goto L_0x0012;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.paypal.android.sdk.payments.PaymentConfirmActivity.g():void");
    }

    private af m559h() {
        return new ar(this);
    }

    private void m562i() {
        PayPalPayment a = this.f619f.m641a();
        C0125s a2 = m533a(a);
        Map b = m545b(a);
        String b2 = a.m436b();
        boolean j = this.f622i.m503c().m416j();
        ShippingAddress providedShippingAddress = a.getProvidedShippingAddress();
        JSONObject jSONObject = null;
        if (providedShippingAddress != null) {
            jSONObject = providedShippingAddress.toJSONObject();
        }
        this.f622i.m496a(a2, b, a.m441g(), b2, j, a.m439e(), a.m437c().toString(), jSONObject, a.isEnablePayPalShippingAddressesRetrieval(), a.m442h(), a.m443i(), a.m444j());
    }

    private void m564j() {
        try {
            dismissDialog(2);
        } catch (IllegalArgumentException e) {
        }
    }

    public final void finish() {
        super.finish();
        new StringBuilder().append(getClass().getSimpleName()).append(".finish");
    }

    protected final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        new StringBuilder().append(getClass().getSimpleName()).append(".onActivityResult(requestCode:").append(i).append(", resultCode:").append(i2).append(", data:").append(intent).append(")");
        switch (i) {
            case CursorAdapter.FLAG_AUTO_REQUERY /*1*/:
                if (i2 == -1) {
                    if (this.f618e != null) {
                        this.f618e.m165b(false);
                    }
                    if (this.f622i != null) {
                        showDialog(2);
                        m562i();
                        m542a(this.f622i.m516p());
                        return;
                    }
                    return;
                }
                m535a(i2);
                finish();
            case CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER /*2*/:
                if (i2 == -1) {
                    this.f618e.m165b(true);
                    m537a(intent.getExtras());
                    if (this.f622i != null) {
                        showDialog(2);
                        m562i();
                        m542a(this.f622i.m516p());
                        return;
                    }
                    return;
                }
                m535a(i2);
                finish();
            default:
                Log.e(f614a, "unhandled requestCode " + i);
        }
    }

    public final void onBackPressed() {
        this.f622i.m487a(bM.ConfirmPaymentCancel);
        m549c();
        super.onBackPressed();
    }

    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        new StringBuilder().append(getClass().getSimpleName()).append(".onCreate");
        m551d();
        if (bundle == null) {
            if (!C0108b.m655a((Activity) this)) {
                finish();
            }
            this.f617d = false;
        } else {
            this.f617d = bundle.getBoolean("pageTrackingSent");
        }
        this.f620g = (aF) getIntent().getSerializableExtra("com.paypal.android.sdk.payments.PaymentConfirmActivity.EXTRA_PAYMENT_KIND");
        this.f621h = (CreditCard) getIntent().getParcelableExtra("com.paypal.android.sdk.payments.PaymentConfirmActivity.EXTRA_CREDIT_CARD");
        this.f619f = new ap(getIntent());
        C0069T.m49b((Activity) this);
        C0069T.m38a((Activity) this);
        this.f618e = new bX(this, this.f620g == aF.PayPal);
        setContentView(this.f618e.m154a());
        C0108b.m651a((Activity) this, this.f618e.m163b(), bO.CONFIRM);
        this.f618e.m164b(new aq(this));
        this.f618e.m158a(new at(this));
        if (aF.PayPal == this.f620g) {
            this.f616c = (bj) getIntent().getParcelableExtra("com.paypal.android.sdk.payments.PaymentConfirmActivity.EXTRA_PAYMENT_INFO");
            m546b();
        }
    }

    protected final Dialog onCreateDialog(int i, Bundle bundle) {
        switch (i) {
            case CursorAdapter.FLAG_AUTO_REQUERY /*1*/:
                return C0108b.m646a((Activity) this, bO.PAY_FAILED_ALERT_TITLE, bundle);
            case CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER /*2*/:
                return C0108b.m649a((Context) this, bO.PROCESSING, bO.ONE_MOMENT);
            case FragmentManagerImpl.ANIM_STYLE_CLOSE_ENTER /*3*/:
                return C0108b.m647a((Activity) this, bO.INTERNAL_ERROR, bundle, i);
            case TransportMediator.FLAG_KEY_MEDIA_PLAY /*4*/:
                return C0108b.m648a((Activity) this, bO.SESSION_EXPIRED_TITLE, bundle, new aA(this));
            case FragmentManagerImpl.ANIM_STYLE_FADE_ENTER /*5*/:
                bO bOVar = bO.WE_ARE_SORRY;
                bO bOVar2 = bO.UNEXPECTED_PAYMENT_FLOW;
                bO bOVar3 = bO.TRY_AGAIN;
                bO bOVar4 = bO.CANCEL;
                OnClickListener aBVar = new aB(this);
                return new Builder(this).setIcon(17301543).setTitle(bN.m131a(bOVar)).setMessage(bN.m131a(bOVar2)).setPositiveButton(bN.m131a(bOVar3), aBVar).setNegativeButton(bN.m131a(bOVar4), new aC(this)).create();
            case FragmentManagerImpl.ANIM_STYLE_FADE_EXIT /*6*/:
                return C0108b.m644a((Context) this, bO.PAY_FAILED_ALERT_TITLE, bN.m132a("DUPLICATE_TRANSACTION"), new aD(this));
            default:
                return null;
        }
    }

    protected final void onDestroy() {
        new StringBuilder().append(getClass().getSimpleName()).append(".onDestroy");
        if (this.f622i != null) {
            this.f622i.m511k();
        }
        if (this.f624k) {
            unbindService(this.f623j);
            this.f624k = false;
        }
        super.onDestroy();
    }

    protected final void onRestart() {
        super.onRestart();
        m551d();
    }

    protected final void onResume() {
        super.onResume();
        new StringBuilder().append(getClass().getSimpleName()).append(".onResume");
        if (this.f622i != null) {
            m556f();
        }
    }

    protected final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("pageTrackingSent", this.f617d);
    }

    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        new StringBuilder().append(getClass().getSimpleName()).append(".onWindowFocusChanged");
        this.f618e.m166c();
    }
}
