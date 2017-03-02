package com.paypal.android.sdk.payments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import com.paypal.android.sdk.C0069T;
import com.paypal.android.sdk.C0082m;
import com.paypal.android.sdk.bH;
import com.paypal.android.sdk.bM;
import com.paypal.android.sdk.bN;
import com.paypal.android.sdk.bO;
import com.paypal.android.sdk.bP;
import com.paypal.android.sdk.bY;
import com.paypal.android.sdk.bt;
import com.paypal.android.sdk.bz;
import io.card.payment.CardIOActivity;
import io.card.payment.CardType;
import io.card.payment.CreditCard;
import java.util.Locale;
import java.util.Timer;

public final class PaymentMethodActivity extends Activity {
    private static final String f629a;
    private static boolean f630f;
    private Timer f631b;
    private boolean f632c;
    private boolean f633d;
    private boolean f634e;
    private boolean f635g;
    private bY f636h;
    private ap f637i;
    private PayPalService f638j;
    private final ServiceConnection f639k;
    private boolean f640l;

    static {
        f629a = PaymentMethodActivity.class.getSimpleName();
        f630f = false;
    }

    public PaymentMethodActivity() {
        this.f639k = new aO(this);
    }

    static void m571a(Activity activity, int i, String str, PayPalConfiguration payPalConfiguration) {
        String str2 = f629a;
        f630f = bN.m134c(str);
        Intent intent = new Intent(activity, PaymentMethodActivity.class);
        intent.putExtras(activity.getIntent());
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, payPalConfiguration);
        activity.startActivityForResult(intent, 1);
    }

    private void m572a(Bundle bundle) {
        for (String str : bundle.keySet()) {
            if (bundle.get(str) == null) {
                String.format("%s:null", new Object[]{(String) r1.next()});
            } else {
                String.format("%s:%s (%s)", new Object[]{(String) r1.next(), bundle.get(str).toString(), bundle.get(str).getClass().getName()});
            }
            String str2 = f629a;
        }
        if (C0115n.m682a(bundle.getString("scope"), bundle.getString("grant_type"))) {
            str2 = f629a;
            m578c();
            return;
        }
        str2 = f629a;
    }

    static /* synthetic */ void m573a(PaymentMethodActivity paymentMethodActivity) {
        paymentMethodActivity.f638j.m487a(bM.SelectPayPalPayment);
        PaymentConfirmActivity.m536a(paymentMethodActivity, 2, aF.PayPal, null);
    }

    private void m575b() {
        if (this.f634e && !this.f633d) {
            this.f636h.f262m.setImageBitmap(bt.m286c("iVBORw0KGgoAAAANSUhEUgAAADcAAAAsCAYAAADByiAeAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAohJREFUeNrcWYGRgjAQJMwXQAl0IFbwWMFrBS8VvHYAFagVoBVIB2IFaAV8B08H/GUm/GA+CUouBL2ZTByEwCZ3m70LcRCsrusQutDBswranhBSOTYNgC1rM1ZA82yD+6nN2Urn21xNYAF0Jmf33Ro45DhDH9+1ObN3mMe84yVXTusd7ojjTds7iIzeoZtAU7mE1/E/5p536fj/yvbFb+VmDK2sn9c27b2RcKuVOs9vdJVnVN0QBsyHrjAQQ9RNMmhn5j5tC1g8zQ0A3AK4dbNqKbJ7nJjevIeYKN3HBpSOjy2h6DjznuxLQR4xpZvbYj4MX5+CO2S9aJvGCCEL+LnGEgBv2EEsWRXqIj4HJpeA3ML9FRq5IbhioEiHCsVz6V9s/H9WlwdiDHArSfycHpicpWSM0ia4UvJRRY+xlsiJcKwrnBPBtbSnLEt594b427O9cvisgL2cr6XobMobwbWdDXAitvvUTW8E5JTbAHcWXMOQUiHnHRcb4CrBXoYhBiYSjTooOH5GfSRB4I8BnI/xAV0eoTNxaOCUWfBjdh0DOFFsZAjgMkGtZnBCCTH3pIb2BewY2gDn8bkbU/q5xpgJ5t6pK7++BNcWHdUqmUV8GsQUj72irGD1KNvNHgQY8VJOIccGz+c8ybhxR9pyVOSCsW4+11S/asOZeMCIwWvdnyvuxygzJhgrh3pYiHiYqZ3P8XXI4t6SniTJ3WAWhjHBNUripKqNKNyQFoVXmB+DFXOqWDyw/tLEGItBCv6DpUkmTouSBlyJqOrHYlHjlocXA0Y9JGvAbSWpxrPajoaAy6mKVwBIDyHjG7ZkanyqKXxtu+IacEQ3bCmgZt8gixlhZdEBzK8AAwBIvuGtI5K/kgAAAABJRU5ErkJggg==", (Context) this));
            this.f636h.f262m.setVisibility(0);
            this.f636h.f262m.setContentDescription(bN.m131a(bO.SCAN_CARD_ICON_DESCRIPTION));
        }
    }

    private void m578c() {
        showDialog(3);
        this.f632c = true;
        this.f631b = new Timer();
        this.f631b.schedule(new aL(this), 1000);
        this.f632c = true;
    }

    static /* synthetic */ void m579c(PaymentMethodActivity paymentMethodActivity) {
        paymentMethodActivity.f638j.m487a(bM.SelectCreditCardPayment);
        bH q = paymentMethodActivity.f638j.m517q();
        if (q == null || !q.m958c()) {
            Intent intent = new Intent(paymentMethodActivity, CardIOActivity.class);
            intent.putExtra(CardIOActivity.EXTRA_LANGUAGE_OR_LOCALE, paymentMethodActivity.f638j.m503c().m407a());
            intent.putExtra(CardIOActivity.EXTRA_APP_TOKEN, "f7d2752ebd6842438a05fb6481b8332a");
            intent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true);
            intent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, true);
            intent.putExtra(CardIOActivity.EXTRA_REQUIRE_ZIP, false);
            paymentMethodActivity.startActivityForResult(intent, 1);
            return;
        }
        PaymentConfirmActivity.m536a(paymentMethodActivity, 2, aF.CreditCardToken, null);
    }

    private void m580d() {
        new StringBuilder().append(getClass().getSimpleName()).append(".refreshPayment");
        if (C0115n.m681a((Context) this, this.f638j)) {
            this.f638j.m506f();
        }
        PayPalPayment a = this.f637i.m641a();
        CharSequence a2 = C0082m.m362a(Locale.getDefault(), bz.m975d().m978b().m354a(), a.m435a().doubleValue(), a.m438d(), true);
        this.f636h.f252c.f418d.setText(a.m436b());
        this.f636h.f252c.f417c.setText(a2);
        if (this.f638j.m508h() && this.f638j.m502b().f99h.m974a()) {
            a2 = this.f638j.m516p();
            if (C0069T.m50b(a2)) {
                this.f636h.f253d.setText(a2);
                this.f636h.f253d.setVisibility(0);
                this.f636h.f251b.setVisibility(0);
            } else {
                this.f636h.f253d.setVisibility(8);
                this.f636h.f251b.setVisibility(8);
            }
        } else {
            this.f636h.f253d.setVisibility(8);
            this.f636h.f251b.setVisibility(8);
        }
        if (this.f638j.m503c().m415i()) {
            bH q = this.f638j.m517q();
            if (q == null || !q.m958c()) {
                this.f636h.f256g.setVisibility(8);
                this.f636h.f254e.setText(bN.m131a(bO.PAY_WITH_CARD));
                this.f636h.f260k.setVisibility(8);
            } else {
                this.f633d = true;
                this.f636h.f262m.setVisibility(8);
                this.f636h.f254e.setText(q.m960e());
                CardType g = q.m962g();
                this.f636h.f256g.setImageBitmap(bP.m135a(this, g));
                this.f636h.f256g.setContentDescription(g.toString());
                this.f636h.f256g.setVisibility(0);
                this.f636h.f260k.setText(bt.m258a(bN.m131a(bO.CLEAR_CREDIT_CARD_INFO)));
                this.f636h.f260k.setVisibility(0);
                this.f636h.f257h.setVisibility(0);
                this.f636h.f260k.setVisibility(0);
            }
            m575b();
        } else {
            this.f636h.f257h.setVisibility(8);
            this.f636h.f260k.setVisibility(8);
        }
        C0108b.m654a(this.f636h.f258i.f214b, this.f638j.m503c().m408b());
    }

    private void m582e() {
        this.f640l = bindService(C0108b.m657b(this), this.f639k, 1);
    }

    static /* synthetic */ void m588j(PaymentMethodActivity paymentMethodActivity) {
        boolean z = !paymentMethodActivity.f632c && (!paymentMethodActivity.f638j.m503c().m415i() || paymentMethodActivity.f638j.m517q() == null);
        String str = f629a;
        new StringBuilder("autoAdvanceToPayPalConfirmIfLoggedIn: ").append(z);
        if (!C0115n.m681a((Context) paymentMethodActivity, paymentMethodActivity.f638j)) {
            if ((!paymentMethodActivity.f638j.m503c().m415i() && !paymentMethodActivity.f632c) || (z && paymentMethodActivity.f638j.m508h() && paymentMethodActivity.f638j.m502b().f99h.m974a())) {
                paymentMethodActivity.m578c();
            }
        }
    }

    protected final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        new StringBuilder().append(getClass().getSimpleName()).append(".onActivityResult (requestCode: ").append(i).append(", resultCode: ").append(i2).append(")");
        switch (i) {
            case CursorAdapter.FLAG_AUTO_REQUERY /*1*/:
                if (intent != null && intent.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                    PaymentConfirmActivity.m536a(this, 2, aF.CreditCard, (CreditCard) intent.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT));
                }
            case CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER /*2*/:
                if (i2 == -1) {
                    PaymentConfirmation paymentConfirmation = (PaymentConfirmation) intent.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                    Intent intent2 = new Intent();
                    intent2.putExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION, paymentConfirmation);
                    setResult(i2, intent2);
                    finish();
                } else if (i2 == 0) {
                    this.f632c = true;
                }
            case FragmentManagerImpl.ANIM_STYLE_CLOSE_ENTER /*3*/:
                if (i2 == -1) {
                    String str = f629a;
                    m572a(intent.getExtras());
                } else if (i2 == 0) {
                    this.f632c = true;
                }
            default:
        }
    }

    public final void onBackPressed() {
        new StringBuilder().append(getClass().getSimpleName()).append(".onBackPressed");
        if (this.f638j != null) {
            this.f638j.m487a(bM.PaymentMethodCancel);
        }
        if (this.f631b != null) {
            this.f631b.cancel();
        }
        super.onBackPressed();
    }

    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        new StringBuilder().append(getClass().getSimpleName()).append(".onCreate");
        C0069T.m49b((Activity) this);
        C0069T.m38a((Activity) this);
        m582e();
        this.f636h = new bY(this, f630f);
        this.f637i = new ap(getIntent());
        setContentView(this.f636h.f250a);
        C0108b.m651a((Activity) this, this.f636h.f261l, bO.YOUR_ORDER);
        this.f636h.f255f.setText(bN.m131a(bO.PAY_WITH));
        this.f636h.f251b.setText(bt.m258a(bN.m131a(bO.LOG_OUT_BUTTON)));
        this.f636h.f259j.setOnClickListener(new aH(this));
        this.f636h.f251b.setOnClickListener(new aI(this));
        this.f636h.f257h.setOnClickListener(new aJ(this));
        this.f636h.f260k.setOnClickListener(new aK(this));
        if (bundle == null) {
            if (!C0108b.m655a((Activity) this)) {
                finish();
            }
            this.f635g = false;
        } else {
            this.f632c = bundle.getBoolean("PP_PreventAutoLogin");
            this.f635g = bundle.getBoolean("PP_PageTrackingSent");
        }
        new aP().execute(new Void[0]);
        this.f631b = null;
    }

    protected final Dialog onCreateDialog(int i, Bundle bundle) {
        switch (i) {
            case CursorAdapter.FLAG_AUTO_REQUERY /*1*/:
                return C0108b.m643a((Activity) this, bO.LOG_OUT, bO.CONFIRM_LOG_OUT, new aM(this));
            case CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER /*2*/:
                return C0108b.m643a((Activity) this, bO.CLEAR_CC_ALERT_TITLE, bO.CONFIRM_CLEAR_CREDIT_CARD_INFO, new aN(this));
            case FragmentManagerImpl.ANIM_STYLE_CLOSE_ENTER /*3*/:
                return C0108b.m649a((Context) this, bO.AUTHENTICATING, bO.ONE_MOMENT);
            default:
                return null;
        }
    }

    protected final void onDestroy() {
        new StringBuilder().append(getClass().getSimpleName()).append(".onDestroy");
        if (this.f640l) {
            unbindService(this.f639k);
            this.f640l = false;
        }
        super.onDestroy();
    }

    protected final void onRestart() {
        super.onRestart();
        m582e();
    }

    protected final void onResume() {
        super.onResume();
        new StringBuilder().append(getClass().getSimpleName()).append(".onResume");
        if (this.f638j != null) {
            m580d();
        }
    }

    protected final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        new StringBuilder().append(getClass().getSimpleName()).append(".onSaveInstanceState");
        bundle.putBoolean("PP_PreventAutoLogin", this.f632c);
        bundle.putBoolean("PP_PageTrackingSent", this.f635g);
    }

    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.f636h.f252c.m295a();
    }
}
