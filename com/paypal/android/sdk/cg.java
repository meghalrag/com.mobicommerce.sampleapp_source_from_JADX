package com.paypal.android.sdk;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public final class cg {
    private LinearLayout f429a;
    private RelativeLayout f430b;
    private ImageView f431c;
    private ImageView f432d;
    private TextView f433e;
    private TextView f434f;
    private TextView f435g;
    private TextView f436h;

    public cg(Context context) {
        this.f429a = new LinearLayout(context);
        this.f429a.setOrientation(1);
        this.f430b = new RelativeLayout(context);
        this.f429a.addView(this.f430b);
        this.f431c = new ImageView(context);
        this.f431c.setId(2301);
        this.f430b.addView(this.f431c);
        bt.m282b(this.f431c, "35dip", "35dip");
        bt.m283b(this.f431c, null, "4dip", null, null);
        this.f432d = bt.m262a(context, "iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAATZJREFUeNrsmMENgkAQRVnPUoAWQB0WoAVoAViA3tW7FEADUIDebUAaoAELwDv+TcaEkFUOsDhj5ieTTdgQ5jHLZ3aDQKVSqVSqAVTX9Q4RSUw8RKSIO+KGmI/5fNMzeZvsGdF88yVia4ypxgCY9Lx/ipi1rkUExb8CVIXFh4SvqMKRPQBBrDAcHFMJIHL2AG8XwrB2TJ0AcWEPQBB2ySwdUxtAlBIAQgxpy5WsKnKmkjVAAyJzuJMXezU+ykp/ZFuJsA0BgA17gAZE5ttevQF02GsOiIQ9AEHEGGJf9uodoMNe7UddsAcgiMyHvU4C4dIl9NcfsWgbFf0jE91KiG7mxLfTojc0oreUojf1oo9VvthlgeS3Y7QpfZu5J+LhsMu9mG7w14e7Q4LIPF5XqVQqlWi9BBgAacm2vqgEoPIAAAAASUVORK5CYII=", "go to selection");
        this.f432d.setId(2304);
        this.f432d.setColorFilter(bs.f383g);
        LayoutParams a = bt.m266a(context, "20dip", "20dip", 11);
        a.addRule(15);
        this.f430b.addView(this.f432d, a);
        this.f433e = new TextView(context);
        bt.m287c(this.f433e, 83);
        this.f433e.setId(2302);
        a = bt.m265a(-2, -2, 1, 2301);
        a.addRule(0, 2304);
        this.f430b.addView(this.f433e, a);
        bt.m283b(this.f433e, "6dip", null, null, null);
        View linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(0);
        linearLayout.setId(2306);
        LayoutParams a2 = bt.m265a(-2, -2, 1, 2301);
        a2.addRule(3, 2302);
        a2.addRule(0, 2304);
        this.f430b.addView(linearLayout, a2);
        this.f434f = new TextView(context);
        bt.m279a(this.f434f, 83);
        linearLayout.addView(this.f434f);
        bt.m283b(this.f434f, "6dip", null, null, null);
        this.f435g = new TextView(context);
        this.f435g.setId(2305);
        bt.m287c(this.f435g, 83);
        linearLayout.addView(this.f435g);
        bt.m283b(this.f435g, "6dip", null, null, null);
        this.f436h = new TextView(context);
        this.f436h.setId(2307);
        bt.m285b(this.f436h, 83);
        a = bt.m265a(-2, -2, 1, 2301);
        a.addRule(3, 2306);
        a.addRule(0, 2304);
        this.f430b.addView(this.f436h, a);
        this.f436h.setText(bN.m131a(bO.PAY_AFTER_DELIVERY));
        bt.m283b(this.f436h, "6dip", null, null, null);
        this.f436h.setVisibility(8);
        bt.m259a(this.f429a);
        this.f429a.setVisibility(8);
    }

    public final View m303a() {
        return this.f429a;
    }

    public final void m304a(Context context, cf cfVar) {
        this.f431c.setImageBitmap(bt.m286c(cfVar.m298a(), context));
        this.f433e.setText(cfVar.m299b());
        bt.m269a(this.f433e, -2, -1);
        this.f434f.setText(cfVar.m300c());
        bt.m269a(this.f434f, -2, -1);
        this.f434f.setEllipsize(TruncateAt.END);
        this.f435g.setText(cfVar.m301d());
        bt.m269a(this.f435g, -2, -1);
        this.f435g.setEllipsize(TruncateAt.END);
        if (cfVar.m302e()) {
            this.f436h.setVisibility(0);
        } else {
            this.f436h.setVisibility(8);
        }
    }

    public final void m305a(OnClickListener onClickListener) {
        this.f429a.setOnClickListener(onClickListener);
    }
}
