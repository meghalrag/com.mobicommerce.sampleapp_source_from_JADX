package com.paypal.android.sdk;

import android.content.Context;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public final class cb {
    public TableLayout f415a;
    public TableLayout f416b;
    public TextView f417c;
    public TextView f418d;
    private int f419e;
    private boolean f420f;

    public cb(Context context, String str) {
        this.f420f = false;
        this.f416b = new TableLayout(context);
        this.f416b.setColumnShrinkable(0, false);
        this.f416b.setColumnStretchable(0, false);
        this.f416b.setColumnStretchable(1, false);
        this.f416b.setColumnShrinkable(1, false);
        View tableRow = new TableRow(context);
        this.f416b.addView(tableRow);
        this.f418d = new TextView(context);
        this.f418d.setTextColor(bs.f385i);
        this.f418d.setText("Item");
        this.f418d.setSingleLine(true);
        this.f418d.setGravity(83);
        this.f418d.setTextSize(18.0f);
        this.f418d.setTextColor(bs.f385i);
        this.f418d.setTypeface(bs.f393q);
        tableRow.addView(this.f418d);
        bt.m268a(this.f418d, 16, 1.0f);
        this.f419e = bt.m256a("10dip", context);
        bt.m283b(this.f418d, null, null, "10dip", null);
        this.f417c = new TextView(context);
        this.f417c.setTextSize(18.0f);
        this.f417c.setTypeface(bs.f394r);
        this.f417c.setText(str);
        this.f417c.setSingleLine(true);
        this.f417c.setGravity(85);
        this.f417c.setTextColor(bs.f386j);
        tableRow.addView(this.f417c);
        bt.m268a(this.f417c, 5, 1.0f);
        this.f415a = this.f416b;
    }

    public final void m295a() {
        TextView textView = this.f417c;
        TextView textView2 = this.f418d;
        int width = (this.f416b.getWidth() - ((int) textView.getPaint().measureText(textView.getText().toString()))) - this.f419e;
        CharSequence ellipsize = TextUtils.ellipsize(textView2.getText(), textView2.getPaint(), (float) width, TruncateAt.END);
        textView2.setWidth(width);
        textView2.setText(ellipsize);
    }
}
