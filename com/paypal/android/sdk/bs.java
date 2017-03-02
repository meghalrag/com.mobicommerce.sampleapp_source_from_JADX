package com.paypal.android.sdk;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RectShape;

public final class bs {
    private static int f370A;
    private static int f371B;
    private static int f372C;
    private static int f373D;
    private static int f374E;
    private static int f375F;
    private static int f376G;
    public static final int f377a;
    public static final int f378b;
    public static final Drawable f379c;
    public static final int f380d;
    public static final int f381e;
    public static final int f382f;
    public static final int f383g;
    public static final int f384h;
    public static final int f385i;
    public static final int f386j;
    public static final int f387k;
    public static final int f388l;
    public static final Typeface f389m;
    public static final Typeface f390n;
    public static final Typeface f391o;
    public static final Typeface f392p;
    public static final Typeface f393q;
    public static final Typeface f394r;
    public static final Typeface f395s;
    public static final ColorStateList f396t;
    private static int[] f397u;
    private static int[] f398v;
    private static int[] f399w;
    private static int[] f400x;
    private static int f401y;
    private static int f402z;

    static {
        f397u = new int[]{16842919, 16842910};
        f398v = new int[]{16842910};
        f399w = new int[]{-16842910};
        f400x = new int[]{16842908};
        f377a = Color.parseColor("#003087");
        Color.parseColor("#aa003087");
        f378b = Color.parseColor("#009CDE");
        f401y = Color.parseColor("#aa009CDE");
        f379c = new ColorDrawable(Color.parseColor("#717074"));
        f380d = Color.parseColor("#f5f5f5");
        f381e = Color.parseColor("#c4dceb");
        f402z = f378b;
        f370A = f401y;
        f371B = f377a;
        f372C = Color.parseColor("#c5ddeb");
        f373D = Color.parseColor("#717074");
        f374E = Color.parseColor("#aa717074");
        f375F = Color.parseColor("#5a5a5d");
        f376G = Color.parseColor("#f5f5f5");
        f382f = Color.parseColor("#e5e5e5");
        Color.parseColor("#333333");
        f383g = Color.parseColor("#515151");
        f384h = Color.parseColor("#797979");
        Color.parseColor("#b32317");
        int i = f383g;
        f385i = f383g;
        f386j = f383g;
        i = f377a;
        f387k = f383g;
        f388l = f384h;
        Typeface.create("sans-serif-light", 0);
        f389m = Typeface.create("sans-serif-light", 0);
        f390n = Typeface.create("sans-serif-light", 0);
        f391o = Typeface.create("sans-serif-bold", 0);
        f392p = Typeface.create("sans-serif", 2);
        f393q = Typeface.create("sans-serif-light", 0);
        f394r = Typeface.create("sans-serif", 0);
        f395s = Typeface.create("sans-serif-light", 0);
        f396t = new ColorStateList(new int[][]{f397u, f398v}, new int[]{f371B, f402z});
    }

    private static Drawable m250a(int i, float f) {
        Drawable[] drawableArr = new Drawable[2];
        drawableArr[0] = new ColorDrawable(i);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RectShape());
        shapeDrawable.getPaint().setStrokeWidth(2.0f * f);
        shapeDrawable.getPaint().setStyle(Style.STROKE);
        shapeDrawable.getPaint().setColor(f380d);
        drawableArr[1] = shapeDrawable;
        return new LayerDrawable(drawableArr);
    }

    private static Drawable m251a(int i, int i2, float f) {
        Drawable[] drawableArr = new Drawable[3];
        drawableArr[0] = new ColorDrawable(i);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RectShape());
        shapeDrawable.getPaint().setStrokeWidth(2.0f * f);
        shapeDrawable.getPaint().setStyle(Style.STROKE);
        shapeDrawable.getPaint().setColor(f380d);
        drawableArr[1] = shapeDrawable;
        shapeDrawable = new ShapeDrawable(new RectShape());
        shapeDrawable.getPaint().setStrokeWidth(f);
        shapeDrawable.getPaint().setStyle(Style.STROKE);
        shapeDrawable.getPaint().setColor(i2);
        drawableArr[2] = shapeDrawable;
        return new LayerDrawable(drawableArr);
    }

    public static Drawable m252a(Context context) {
        Drawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(f397u, new ColorDrawable(f371B));
        stateListDrawable.addState(f399w, new ColorDrawable(f372C));
        stateListDrawable.addState(f400x, m251a(f402z, f370A, m255d(context)));
        stateListDrawable.addState(f398v, m250a(f402z, m255d(context)));
        return stateListDrawable;
    }

    public static Drawable m253b(Context context) {
        Drawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(f397u, new ColorDrawable(f375F));
        stateListDrawable.addState(f399w, new ColorDrawable(f376G));
        stateListDrawable.addState(f400x, m251a(f373D, f374E, m255d(context)));
        stateListDrawable.addState(f398v, m250a(f373D, m255d(context)));
        return stateListDrawable;
    }

    protected static Drawable m254c(Context context) {
        Drawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(f400x, m251a(0, f370A, m255d(context)));
        stateListDrawable.addState(f398v, new ColorDrawable(0));
        return stateListDrawable;
    }

    private static float m255d(Context context) {
        return context.getResources().getDisplayMetrics().density * (bt.m280b("4dip", context) / 2.0f);
    }
}
