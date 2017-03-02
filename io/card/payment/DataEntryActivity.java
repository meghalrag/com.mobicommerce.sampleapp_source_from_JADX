package io.card.payment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.DateKeyListener;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.squareup.okhttp.internal.http.HttpTransport;

public final class DataEntryActivity extends Activity implements TextWatcher {
    private int f802a;
    private int f803b;
    private TextView f804c;
    private EditText f805d;
    private ah f806e;
    private EditText f807f;
    private ah f808g;
    private EditText f809h;
    private ah f810i;
    private EditText f811j;
    private ah f812k;
    private ImageView f813l;
    private Button f814m;
    private Button f815n;
    private CreditCard f816o;
    private boolean f817p;
    private String f818q;
    private final String f819r;
    private boolean f820s;

    public DataEntryActivity() {
        this.f802a = 1;
        this.f803b = 100;
        this.f819r = getClass().getName();
    }

    private void m726a() {
        if (this.f816o == null) {
            this.f816o = new CreditCard();
        }
        if (this.f807f != null) {
            this.f816o.expiryMonth = ((C0274W) this.f808g).f1160a;
            this.f816o.expiryYear = ((C0274W) this.f808g).f1161b;
        }
        Parcelable creditCard = new CreditCard(this.f806e.m799b(), this.f816o.expiryMonth, this.f816o.expiryYear, this.f810i.m799b(), this.f812k.m799b());
        Intent intent = new Intent();
        intent.putExtra(CardIOActivity.EXTRA_SCAN_RESULT, creditCard);
        setResult(CardIOActivity.RESULT_CARD_INFO, intent);
        finish();
    }

    private EditText m728b() {
        int i = 100;
        while (true) {
            int i2 = i + 1;
            EditText editText = (EditText) findViewById(i);
            if (editText == null) {
                return null;
            }
            if (editText.getText().length() == 0 && editText.requestFocus()) {
                return editText;
            }
            i = i2;
        }
    }

    private void m729c() {
        Button button = this.f814m;
        boolean z = this.f806e.m798a() && this.f808g.m798a() && this.f810i.m798a() && this.f812k.m798a();
        button.setEnabled(z);
        if (this.f817p && this.f806e.m798a() && this.f808g.m798a() && this.f810i.m798a() && this.f812k.m798a()) {
            m726a();
        }
    }

    public final void afterTextChanged(Editable editable) {
        if (this.f805d != null && editable == this.f805d.getText()) {
            if (!this.f806e.m800c()) {
                this.f805d.setTextColor(-12303292);
            } else if (this.f806e.m798a()) {
                m728b();
            } else {
                this.f805d.setTextColor(C0149N.f824d);
            }
            if (this.f809h != null) {
                C0275X c0275x = (C0275X) this.f810i;
                int cvvLength = CardType.fromCardNumber(this.f806e.m799b().toString()).cvvLength();
                c0275x.f1163a = cvvLength;
                this.f809h.setHint(cvvLength == 4 ? "1234" : "123");
            }
        } else if (this.f807f == null || editable != this.f807f.getText()) {
            if (this.f809h == null || editable != this.f809h.getText()) {
                if (this.f811j != null && editable == this.f811j.getText()) {
                    if (!this.f812k.m800c()) {
                        this.f811j.setTextColor(-12303292);
                    } else if (this.f812k.m798a()) {
                        m728b();
                    } else {
                        this.f811j.setTextColor(C0149N.f824d);
                    }
                }
            } else if (!this.f810i.m800c()) {
                this.f809h.setTextColor(-12303292);
            } else if (this.f810i.m798a()) {
                m728b();
            } else {
                this.f809h.setTextColor(C0149N.f824d);
            }
        } else if (!this.f808g.m800c()) {
            this.f807f.setTextColor(-12303292);
        } else if (this.f808g.m798a()) {
            m728b();
        } else {
            this.f807f.setTextColor(C0149N.f824d);
        }
        m729c();
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    protected final void onCreate(Bundle bundle) {
        String str = this.f819r;
        if (C0162f.m808d()) {
            setTheme(16973934);
        } else {
            setTheme(16973836);
        }
        this.f818q = C0162f.m808d() ? "12dip" : "2dip";
        super.onCreate(bundle);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            throw new IllegalStateException("Didn't find any extras!");
        }
        LayoutParams layoutParams;
        ao.m803a(getIntent());
        this.f820s = extras.getBoolean("io.card.payment.intentSenderIsPayPal");
        int a = C0150O.m738a("4dip", this);
        View relativeLayout = new RelativeLayout(this);
        relativeLayout.setBackgroundColor(C0149N.f823c);
        View scrollView = new ScrollView(this);
        int i = this.f802a;
        this.f802a = i + 1;
        scrollView.setId(i);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(10);
        relativeLayout.addView(scrollView, layoutParams2);
        View linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        scrollView.addView(linearLayout, -1, -1);
        View linearLayout2 = new LinearLayout(this);
        linearLayout2.setOrientation(1);
        LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -1);
        this.f816o = (CreditCard) extras.getParcelable(CardIOActivity.EXTRA_SCAN_RESULT);
        this.f817p = extras.getBoolean("debug_autoAcceptResult");
        if (this.f816o != null) {
            this.f806e = new C0272P(this.f816o.cardNumber);
            this.f813l = new ImageView(this);
            LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
            this.f813l.setPadding(0, 0, 0, a);
            layoutParams4.weight = 1.0f;
            this.f813l.setImageBitmap(CardIOActivity.f1113a);
            linearLayout2.addView(this.f813l, layoutParams4);
            C0150O.m744b(this.f813l, null, null, null, "8dip");
        } else {
            this.f804c = new TextView(this);
            this.f804c.setTextSize(24.0f);
            this.f804c.setTextColor(C0149N.f821a);
            linearLayout2.addView(this.f804c);
            C0150O.m741a(this.f804c, null, null, null, "8dip");
            C0150O.m740a(this.f804c, -2, -2);
            scrollView = new LinearLayout(this);
            scrollView.setOrientation(1);
            C0150O.m741a(scrollView, null, "4dip", null, "4dip");
            View textView = new TextView(this);
            C0150O.m741a(textView, this.f818q, null, null, null);
            textView.setText(ao.m801a(ap.ENTRY_CARD_NUMBER));
            textView.setTextColor(C0149N.f825e);
            scrollView.addView(textView, -2, -2);
            this.f805d = new EditText(this);
            EditText editText = this.f805d;
            int i2 = this.f803b;
            this.f803b = i2 + 1;
            editText.setId(i2);
            this.f805d.setMaxLines(1);
            this.f805d.setImeOptions(6);
            this.f805d.setTextAppearance(getApplicationContext(), 16842816);
            this.f805d.setInputType(3);
            this.f805d.setHint("1234 5678 1234 5678");
            this.f806e = new C0272P();
            this.f805d.addTextChangedListener(this.f806e);
            this.f805d.addTextChangedListener(this);
            this.f805d.setFilters(new InputFilter[]{new DigitsKeyListener(), this.f806e});
            scrollView.addView(this.f805d, -1, -2);
            linearLayout2.addView(scrollView, -1, -1);
        }
        View linearLayout3 = new LinearLayout(this);
        LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, -2);
        C0150O.m741a(linearLayout3, null, "4dip", null, null);
        linearLayout3.setOrientation(0);
        boolean z = extras.getBoolean(CardIOActivity.EXTRA_REQUIRE_EXPIRY);
        boolean z2 = extras.getBoolean(CardIOActivity.EXTRA_REQUIRE_CVV);
        Object obj = (extras.getBoolean(CardIOActivity.EXTRA_REQUIRE_ZIP) || extras.getBoolean(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE)) ? 1 : null;
        if (z) {
            View linearLayout4 = new LinearLayout(this);
            layoutParams4 = new LinearLayout.LayoutParams(0, -1, 1.0f);
            linearLayout4.setOrientation(1);
            View textView2 = new TextView(this);
            textView2.setTextColor(C0149N.f825e);
            textView2.setText(ao.m801a(ap.ENTRY_EXPIRES));
            C0150O.m741a(textView2, this.f818q, null, null, null);
            linearLayout4.addView(textView2, -2, -2);
            this.f807f = new EditText(this);
            EditText editText2 = this.f807f;
            int i3 = this.f803b;
            this.f803b = i3 + 1;
            editText2.setId(i3);
            this.f807f.setMaxLines(1);
            this.f807f.setImeOptions(6);
            this.f807f.setTextAppearance(getApplicationContext(), 16842816);
            this.f807f.setInputType(3);
            this.f807f.setHint(ao.m801a(ap.EXPIRES_PLACEHOLDER));
            if (this.f816o != null) {
                this.f808g = new C0274W(this.f816o.expiryMonth, this.f816o.expiryYear);
            } else {
                this.f808g = new C0274W();
            }
            if (this.f808g.m800c()) {
                this.f807f.setText(this.f808g.m799b());
                if (!this.f808g.m798a()) {
                    this.f807f.setTextColor(C0149N.f824d);
                }
            }
            this.f807f.addTextChangedListener(this.f808g);
            this.f807f.addTextChangedListener(this);
            editText2 = this.f807f;
            InputFilter[] inputFilterArr = new InputFilter[2];
            inputFilterArr[0] = new DateKeyListener();
            inputFilterArr[1] = this.f808g;
            editText2.setFilters(inputFilterArr);
            linearLayout4.addView(this.f807f, -1, -2);
            linearLayout3.addView(linearLayout4, layoutParams4);
            str = (z2 || obj != null) ? "4dip" : null;
            C0150O.m744b(linearLayout4, null, null, str, null);
        } else {
            this.f808g = new C0276b();
        }
        if (z2) {
            textView2 = new LinearLayout(this);
            layoutParams = new LinearLayout.LayoutParams(0, -1, 1.0f);
            textView2.setOrientation(1);
            scrollView = new TextView(this);
            scrollView.setTextColor(C0149N.f825e);
            C0150O.m741a(scrollView, this.f818q, null, null, null);
            scrollView.setText(ao.m801a(ap.ENTRY_CVV));
            textView2.addView(scrollView, -2, -2);
            this.f809h = new EditText(this);
            EditText editText3 = this.f809h;
            i3 = this.f803b;
            this.f803b = i3 + 1;
            editText3.setId(i3);
            this.f809h.setMaxLines(1);
            this.f809h.setImeOptions(6);
            this.f809h.setTextAppearance(getApplicationContext(), 16842816);
            this.f809h.setInputType(3);
            this.f809h.setHint("123");
            int i4 = 4;
            if (this.f816o != null) {
                i4 = CardType.fromCardNumber(this.f806e.m799b()).cvvLength();
            }
            this.f810i = new C0275X(i4);
            editText3 = this.f809h;
            inputFilterArr = new InputFilter[2];
            inputFilterArr[0] = new DigitsKeyListener();
            inputFilterArr[1] = this.f810i;
            editText3.setFilters(inputFilterArr);
            this.f809h.addTextChangedListener(this.f810i);
            this.f809h.addTextChangedListener(this);
            textView2.addView(this.f809h, -1, -2);
            linearLayout3.addView(textView2, layoutParams);
            C0150O.m744b(textView2, z ? "4dip" : null, null, obj != null ? "4dip" : null, null);
        } else {
            this.f810i = new C0276b();
        }
        if (obj != null) {
            linearLayout4 = new LinearLayout(this);
            layoutParams4 = new LinearLayout.LayoutParams(0, -1, 1.0f);
            linearLayout4.setOrientation(1);
            textView = new TextView(this);
            textView.setTextColor(C0149N.f825e);
            C0150O.m741a(textView, this.f818q, null, null, null);
            textView.setText(ao.m801a(ap.ENTRY_POSTAL_CODE));
            linearLayout4.addView(textView, -2, -2);
            this.f811j = new EditText(this);
            editText = this.f811j;
            int i5 = this.f803b;
            this.f803b = i5 + 1;
            editText.setId(i5);
            this.f811j.setMaxLines(1);
            this.f811j.setImeOptions(6);
            this.f811j.setTextAppearance(getApplicationContext(), 16842816);
            this.f811j.setInputType(1);
            this.f812k = new aa();
            this.f811j.addTextChangedListener(this.f812k);
            this.f811j.addTextChangedListener(this);
            linearLayout4.addView(this.f811j, -1, -2);
            linearLayout3.addView(linearLayout4, layoutParams4);
            str = (z || z2) ? "4dip" : null;
            C0150O.m744b(linearLayout4, str, null, null, null);
        } else {
            this.f812k = new C0276b();
        }
        linearLayout2.addView(linearLayout3, layoutParams5);
        linearLayout.addView(linearLayout2, layoutParams3);
        C0150O.m744b(linearLayout2, "16dip", "20dip", "16dip", "20dip");
        scrollView = new LinearLayout(this);
        int i6 = this.f802a;
        this.f802a = i6 + 1;
        scrollView.setId(i6);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12);
        scrollView.setPadding(0, a, 0, 0);
        scrollView.setBackgroundColor(0);
        layoutParams2.addRule(2, scrollView.getId());
        this.f814m = new Button(this);
        LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-1, -2, 1.0f);
        this.f814m.setText(ao.m801a(ap.DONE));
        this.f814m.setOnClickListener(new C0153T(this));
        this.f814m.setEnabled(false);
        scrollView.addView(this.f814m, layoutParams6);
        C0150O.m742a(this.f814m, true, (Context) this);
        C0150O.m741a(this.f814m, "5dip", null, "5dip", null);
        C0150O.m744b(this.f814m, "8dip", "8dip", "4dip", "8dip");
        this.f814m.setTextSize(16.0f);
        this.f815n = new Button(this);
        layoutParams6 = new LinearLayout.LayoutParams(-1, -2, 1.0f);
        this.f815n.setText(ao.m801a(ap.CANCEL));
        this.f815n.setOnClickListener(new C0154U(this));
        scrollView.addView(this.f815n, layoutParams6);
        C0150O.m742a(this.f815n, false, (Context) this);
        C0150O.m741a(this.f815n, "5dip", null, "5dip", null);
        C0150O.m744b(this.f815n, "4dip", "8dip", "8dip", "8dip");
        this.f815n.setTextSize(16.0f);
        relativeLayout.addView(scrollView, layoutParams);
        if (C0162f.m807c()) {
            requestWindowFeature(8);
        }
        setContentView(relativeLayout);
        Drawable drawable = null;
        if (this.f820s) {
            drawable = new BitmapDrawable(getResources(), C0150O.m739a("iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyRpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoTWFjaW50b3NoKSIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDpCNDMzRTRFQ0M2MjQxMUUzOURBQ0E3QTY0NjU3OUI5QiIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDpCNDMzRTRFREM2MjQxMUUzOURBQ0E3QTY0NjU3OUI5QiI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOkI0MzNFNEVBQzYyNDExRTM5REFDQTdBNjQ2NTc5QjlCIiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOkI0MzNFNEVCQzYyNDExRTM5REFDQTdBNjQ2NTc5QjlCIi8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+Eyd0MQAABoFJREFUeNrMWl1M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", (Context) this, 240));
        }
        C0162f.m805a(this, this.f804c, ao.m801a(ap.MANUAL_ENTRY_TITLE), "card.io - ", drawable);
    }

    protected final void onResume() {
        super.onResume();
        String str = this.f819r;
        getWindow().setFlags(0, HttpTransport.DEFAULT_CHUNK_LENGTH);
        m729c();
        if (this.f805d != null || this.f807f == null || this.f808g.m798a()) {
            m728b();
        } else {
            this.f807f.requestFocus();
        }
        if (!(this.f805d == null && this.f807f == null && this.f809h == null && this.f811j == null)) {
            getWindow().setSoftInputMode(5);
        }
        str = this.f819r;
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
