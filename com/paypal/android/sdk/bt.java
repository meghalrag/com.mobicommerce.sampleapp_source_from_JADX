package com.paypal.android.sdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.UnderlineSpan;
import android.util.Base64;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class bt {
    private static final Map f403a;
    private static Pattern f404b;
    private static HashMap f405c;

    static {
        Map hashMap = new HashMap();
        hashMap.put("px", Integer.valueOf(0));
        hashMap.put("dip", Integer.valueOf(1));
        hashMap.put("dp", Integer.valueOf(1));
        hashMap.put("sp", Integer.valueOf(2));
        hashMap.put("pt", Integer.valueOf(3));
        hashMap.put("in", Integer.valueOf(4));
        hashMap.put("mm", Integer.valueOf(5));
        f403a = Collections.unmodifiableMap(hashMap);
        f404b = Pattern.compile("^\\s*(\\d+(\\.\\d+)*)\\s*([a-zA-Z]+)\\s*$");
        f405c = new HashMap();
    }

    public static int m256a(String str, Context context) {
        return str == null ? 0 : (int) m280b(str, context);
    }

    public static Bitmap m257a(String str, Context context, int i) {
        Options options = new Options();
        if (context != null) {
            options.inTargetDensity = context.getResources().getDisplayMetrics().densityDpi;
        } else {
            options.inTargetDensity = 160;
        }
        options.inDensity = 240;
        options.inScaled = false;
        byte[] decode = Base64.decode(str, 0);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length, options);
    }

    public static SpannableString m258a(String str) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new UnderlineSpan(), 0, str.length(), 0);
        return spannableString;
    }

    public static View m259a(LinearLayout linearLayout) {
        View view = new View(linearLayout.getContext());
        linearLayout.addView(view);
        m271a(view, new ColorDrawable(bs.f382f));
        m270a(view, -1, "1dip");
        m283b(view, null, "12dip", null, "12dip");
        return view;
    }

    public static LayoutParams m260a() {
        return new LayoutParams(-1, -2);
    }

    public static ViewGroup m261a(Context context) {
        ViewGroup scrollView = new ScrollView(context);
        scrollView.setBackgroundColor(bs.f380d);
        return scrollView;
    }

    public static ImageView m262a(Context context, String str, String str2) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ScaleType.CENTER_INSIDE);
        imageView.setImageBitmap(m286c(str, context));
        imageView.setAdjustViewBounds(true);
        imageView.setContentDescription(str2);
        return imageView;
    }

    public static LinearLayout m263a(Context context, boolean z, int i, LinearLayout linearLayout) {
        View linearLayout2 = new LinearLayout(context);
        if (i != 0) {
            linearLayout2.setId(i);
        }
        linearLayout.addView(linearLayout2);
        linearLayout2.setGravity(17);
        linearLayout2.setOrientation(0);
        m275a(linearLayout2, z, context);
        m270a(linearLayout2, -1, "58dip");
        m283b(linearLayout2, null, null, null, "4dip");
        return linearLayout2;
    }

    public static LinearLayout m264a(ViewGroup viewGroup) {
        View linearLayout = new LinearLayout(viewGroup.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(bs.f380d);
        viewGroup.addView(linearLayout);
        m269a(linearLayout, -1, -2);
        return linearLayout;
    }

    public static RelativeLayout.LayoutParams m265a(int i, int i2, int i3, int i4) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(i3, i4);
        return layoutParams;
    }

    public static RelativeLayout.LayoutParams m266a(Context context, String str, String str2, int i) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(m256a(str, context), m256a(str2, context));
        layoutParams.addRule(i);
        return layoutParams;
    }

    public static void m267a(View view) {
        m283b(view, "4dip", null, "4dip", null);
    }

    public static void m268a(View view, int i, float f) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.gravity = i;
            layoutParams2.weight = f;
        }
    }

    public static void m269a(View view, int i, int i2) {
        LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i2;
    }

    public static void m270a(View view, int i, String str) {
        m269a(view, i, m256a(str, view.getContext()));
    }

    private static void m271a(View view, Drawable drawable) {
        if (VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void m272a(View view, String str, int i) {
        m269a(view, m256a(str, view.getContext()), -2);
    }

    public static void m273a(View view, String str, String str2) {
        m283b(view, "4dip", str, "4dip", str2);
    }

    public static void m274a(View view, String str, String str2, String str3, String str4) {
        Context context = view.getContext();
        view.setPadding(m256a(str, context), m256a(str2, context), m256a(str3, context), m256a(str4, context));
    }

    public static void m275a(View view, boolean z, Context context) {
        m269a(view, -1, -2);
        m274a(view, "10dip", "0dip", "10dip", "0dip");
        m271a(view, z ? bs.m252a(context) : bs.m253b(context));
        view.setFocusable(true);
        view.setMinimumHeight(m256a("54dip", context));
        if (view instanceof TextView) {
            m278a((TextView) view);
        }
        if (!(view instanceof Button)) {
            view.setClickable(true);
        }
    }

    public static void m276a(Button button) {
        m277a(button, 17);
    }

    public static void m277a(Button button, int i) {
        m274a(button, "2dip", "2dip", "2dip", "2dip");
        button.setTypeface(bs.f390n);
        button.setTextColor(bs.f396t);
        if (VERSION.SDK_INT < 16) {
            button.setBackgroundDrawable(bs.m254c(button.getContext()));
        } else {
            button.setBackground(bs.m254c(button.getContext()));
        }
        button.setAutoLinkMask(15);
        button.setTextSize(14.0f);
        button.setTextColor(bs.f396t);
        button.setGravity(i);
    }

    public static void m278a(TextView textView) {
        textView.setGravity(17);
        textView.setTextColor(-1);
        textView.setTextSize(20.0f);
        textView.setTypeface(bs.f389m);
    }

    public static void m279a(TextView textView, int i) {
        textView.setTextSize(18.0f);
        textView.setTypeface(bs.f391o);
        textView.setSingleLine(true);
        textView.setGravity(83);
        textView.setTextColor(bs.f383g);
    }

    public static float m280b(String str, Context context) {
        if (str == null) {
            return 0.0f;
        }
        CharSequence toLowerCase = str.toLowerCase();
        if (f405c.containsKey(toLowerCase)) {
            return ((Float) f405c.get(toLowerCase)).floatValue();
        }
        Matcher matcher = f404b.matcher(toLowerCase);
        if (matcher.matches()) {
            float parseFloat = Float.parseFloat(matcher.group(1));
            Integer num = (Integer) f403a.get(matcher.group(3).toLowerCase());
            if (num == null) {
                num = Integer.valueOf(1);
            }
            float applyDimension = TypedValue.applyDimension(num.intValue(), parseFloat, context.getResources().getDisplayMetrics());
            f405c.put(toLowerCase, Float.valueOf(applyDimension));
            return applyDimension;
        }
        throw new NumberFormatException();
    }

    public static LinearLayout m281b(Context context) {
        View linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        m274a(linearLayout, "10dip", "14dip", "10dip", "14dip");
        return linearLayout;
    }

    public static void m282b(View view, String str, String str2) {
        Context context = view.getContext();
        m269a(view, m256a(str, context), m256a(str2, context));
    }

    public static void m283b(View view, String str, String str2, String str3, String str4) {
        Context context = view.getContext();
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof MarginLayoutParams) {
            ((MarginLayoutParams) layoutParams).setMargins(m256a(str, context), m256a(str2, context), m256a(str3, context), m256a(str4, context));
        }
    }

    public static void m284b(TextView textView) {
        textView.setTextColor(bs.f387k);
        textView.setLinkTextColor(bs.f396t);
        textView.setTypeface(bs.f395s);
        textView.setTextSize(13.0f);
        textView.setSingleLine(false);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static void m285b(TextView textView, int i) {
        textView.setTextSize(16.0f);
        textView.setTypeface(bs.f392p);
        textView.setSingleLine(true);
        textView.setGravity(83);
        textView.setTextColor(bs.f383g);
    }

    public static Bitmap m286c(String str, Context context) {
        return m257a(str, context, 240);
    }

    public static void m287c(TextView textView, int i) {
        textView.setTextSize(14.0f);
        textView.setTypeface(bs.f393q);
        textView.setSingleLine(true);
        textView.setGravity(i);
        textView.setTextColor(bs.f383g);
    }

    public static void m288d(TextView textView, int i) {
        textView.setTextSize(13.0f);
        textView.setTypeface(bs.f393q);
        textView.setSingleLine(true);
        textView.setGravity(83);
        textView.setTextColor(bs.f383g);
    }
}
