package com.paypal.android.sdk;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.paypal.android.sdk.payments.BuildConfig;
import java.util.ArrayList;
import java.util.Locale;

public final class cd extends ArrayAdapter {
    private int f421a;

    public cd(Context context, ArrayList arrayList, int i) {
        super(context, 0, arrayList);
        this.f421a = i;
    }

    private static int m296a(Context context, RelativeLayout relativeLayout, String str, int i) {
        if (C0069T.m51c(str)) {
            return i;
        }
        TextView textView = new TextView(context);
        textView.setId(i + 1);
        LayoutParams a = bt.m265a(-2, -2, 1, 2301);
        a.addRule(0, 2302);
        a.addRule(3, i);
        textView.setText(str);
        bt.m287c(textView, 83);
        bt.m283b(textView, "6dip", null, "6dip", null);
        relativeLayout.addView(textView, a);
        textView.setEllipsize(TruncateAt.END);
        return i + 1;
    }

    public final void m297a(int i) {
        this.f421a = i;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        cc ccVar = (cc) getItem(i);
        Context context = viewGroup.getContext();
        View linearLayout = new LinearLayout(viewGroup.getContext());
        View relativeLayout = new RelativeLayout(context);
        linearLayout.addView(relativeLayout);
        bt.m274a(relativeLayout, null, "6dip", null, "6dip");
        View a = bt.m262a(viewGroup.getContext(), ccVar.m982a(), BuildConfig.VERSION_NAME);
        a.setId(2301);
        LayoutParams a2 = bt.m266a(context, "40dip", "40dip", 9);
        a2.addRule(10);
        relativeLayout.addView(a, a2);
        bt.m274a(a, "6dip", null, null, null);
        a = bt.m262a(viewGroup.getContext(), "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", "checked");
        a.setId(2302);
        a2 = bt.m266a(context, "20dip", "20dip", 11);
        a2.addRule(10);
        relativeLayout.addView(a, a2);
        a.setColorFilter(bs.f378b);
        bt.m274a(a, null, null, "8dip", null);
        if (i == this.f421a) {
            a.setVisibility(0);
        } else {
            a.setVisibility(4);
        }
        TextView textView = new TextView(context);
        textView.setId(2303);
        textView.setText(ccVar.m987f());
        bt.m279a(textView, 83);
        bt.m283b(textView, "6dip", null, "6dip", null);
        relativeLayout.addView(textView, bt.m265a(-2, -2, 1, 2301));
        int a3 = m296a(context, relativeLayout, ccVar.m989h(), m296a(context, relativeLayout, ccVar.m988g(), 2303));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ccVar.m990i());
        stringBuilder.append(" ");
        stringBuilder.append(ccVar.m991j());
        if (C0069T.m52d(ccVar.m992k())) {
            stringBuilder.append("  ");
            stringBuilder.append(ccVar.m992k());
        }
        if (C0069T.m52d(ccVar.m993l())) {
            StringBuilder append = stringBuilder.append("  ");
            String l = ccVar.m993l();
            if (!C0069T.m51c(l)) {
                Object toLowerCase = Locale.getDefault().getCountry().toLowerCase(Locale.US);
                if (C0069T.m51c(toLowerCase) || !toLowerCase.equals(l.toLowerCase(Locale.US))) {
                    l = "[" + l + "]";
                    append.append(l);
                }
            }
            l = BuildConfig.VERSION_NAME;
            append.append(l);
        }
        m296a(context, relativeLayout, stringBuilder.toString(), a3);
        return linearLayout;
    }
}
