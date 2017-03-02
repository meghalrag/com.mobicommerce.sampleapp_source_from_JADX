package io.card.payment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.Base64;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.TextView;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: io.card.payment.O */
public final class C0150O {
    private static final Map f843a;
    private static Pattern f844b;
    private static HashMap f845c;

    static {
        Map hashMap = new HashMap();
        hashMap.put("px", Integer.valueOf(0));
        hashMap.put("dip", Integer.valueOf(1));
        hashMap.put("dp", Integer.valueOf(1));
        hashMap.put("sp", Integer.valueOf(2));
        hashMap.put("pt", Integer.valueOf(3));
        hashMap.put("in", Integer.valueOf(4));
        hashMap.put("mm", Integer.valueOf(5));
        f843a = Collections.unmodifiableMap(hashMap);
        f844b = Pattern.compile("^\\s*(\\d+(\\.\\d+)*)\\s*([a-zA-Z]+)\\s*$");
        f845c = new HashMap();
    }

    public static int m738a(String str, Context context) {
        return str == null ? 0 : (int) C0150O.m743b(str, context);
    }

    public static Bitmap m739a(String str, Context context, int i) {
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

    public static void m740a(View view, int i, int i2) {
        LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = -2;
    }

    public static void m741a(View view, String str, String str2, String str3, String str4) {
        Context context = view.getContext();
        view.setPadding(C0150O.m738a(str, context), C0150O.m738a(str2, context), C0150O.m738a(str3, context), C0150O.m738a(str4, context));
    }

    public static void m742a(View view, boolean z, Context context) {
        C0150O.m740a(view, -1, -2);
        C0150O.m741a(view, "10dip", "0dip", "10dip", "0dip");
        Drawable a = z ? C0149N.m735a(context) : C0149N.m736b(context);
        if (VERSION.SDK_INT >= 16) {
            view.setBackground(a);
        } else {
            view.setBackgroundDrawable(a);
        }
        view.setFocusable(true);
        view.setMinimumHeight(C0150O.m738a("54dip", context));
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            textView.setGravity(17);
            textView.setTextColor(-1);
            textView.setTextSize(20.0f);
            textView.setTypeface(C0149N.f826f);
        }
        if (!(view instanceof Button)) {
            view.setClickable(true);
        }
    }

    public static float m743b(String str, Context context) {
        if (str == null) {
            return 0.0f;
        }
        CharSequence toLowerCase = str.toLowerCase();
        if (f845c.containsKey(toLowerCase)) {
            return ((Float) f845c.get(toLowerCase)).floatValue();
        }
        Matcher matcher = f844b.matcher(toLowerCase);
        if (matcher.matches()) {
            float parseFloat = Float.parseFloat(matcher.group(1));
            Integer num = (Integer) f843a.get(matcher.group(3).toLowerCase());
            if (num == null) {
                num = Integer.valueOf(1);
            }
            float applyDimension = TypedValue.applyDimension(num.intValue(), parseFloat, context.getResources().getDisplayMetrics());
            f845c.put(toLowerCase, Float.valueOf(applyDimension));
            return applyDimension;
        }
        throw new NumberFormatException();
    }

    public static void m744b(View view, String str, String str2, String str3, String str4) {
        Context context = view.getContext();
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof MarginLayoutParams) {
            ((MarginLayoutParams) layoutParams).setMargins(C0150O.m738a(str, context), C0150O.m738a(str2, context), C0150O.m738a(str3, context), C0150O.m738a(str4, context));
        }
    }

    public static Bitmap m745c(String str, Context context) {
        return C0150O.m739a(str, context, 240);
    }
}
