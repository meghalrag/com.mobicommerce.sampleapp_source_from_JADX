package io.card.payment;

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

/* renamed from: io.card.payment.N */
public final class C0149N {
    public static final int f821a;
    public static final Drawable f822b;
    public static final int f823c;
    public static final int f824d;
    public static final int f825e;
    public static final Typeface f826f;
    private static int[] f827g;
    private static int[] f828h;
    private static int[] f829i;
    private static int[] f830j;
    private static int f831k;
    private static int f832l;
    private static int f833m;
    private static int f834n;
    private static int f835o;
    private static int f836p;
    private static int f837q;
    private static int f838r;
    private static int f839s;
    private static int f840t;
    private static int f841u;
    private static int f842v;

    static {
        f827g = new int[]{16842919, 16842910};
        f828h = new int[]{16842910};
        f829i = new int[]{-16842910};
        f830j = new int[]{16842908};
        f821a = Color.parseColor("#003087");
        f831k = Color.parseColor("#aa003087");
        f832l = Color.parseColor("#009CDE");
        f822b = new ColorDrawable(Color.parseColor("#55a0cc"));
        f823c = Color.parseColor("#f5f5f5");
        Color.parseColor("#c4dceb");
        f833m = f821a;
        f834n = f831k;
        f835o = f832l;
        f836p = Color.parseColor("#c5ddeb");
        f837q = Color.parseColor("#717074");
        f838r = Color.parseColor("#aa717074");
        f839s = Color.parseColor("#5a5a5d");
        f840t = Color.parseColor("#f5f5f5");
        Color.parseColor("#e5e5e5");
        Color.parseColor("#333333");
        f841u = Color.parseColor("#515151");
        f842v = Color.parseColor("#797979");
        f824d = Color.parseColor("#b32317");
        int i = f841u;
        f825e = f841u;
        i = f841u;
        i = f821a;
        i = f841u;
        i = f842v;
        Typeface.create("sans-serif-light", 0);
        f826f = Typeface.create("sans-serif-light", 0);
        Typeface.create("sans-serif-light", 0);
        Typeface.create("sans-serif-bold", 0);
        Typeface.create("sans-serif-light", 0);
        Typeface.create("sans-serif", 0);
        Typeface.create("sans-serif-light", 0);
        ColorStateList colorStateList = new ColorStateList(new int[][]{f827g, f828h}, new int[]{f835o, f833m});
    }

    private static Drawable m733a(int i, float f) {
        Drawable[] drawableArr = new Drawable[2];
        drawableArr[0] = new ColorDrawable(i);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RectShape());
        shapeDrawable.getPaint().setStrokeWidth(2.0f * f);
        shapeDrawable.getPaint().setStyle(Style.STROKE);
        shapeDrawable.getPaint().setColor(f823c);
        drawableArr[1] = shapeDrawable;
        return new LayerDrawable(drawableArr);
    }

    private static Drawable m734a(int i, int i2, float f) {
        Drawable[] drawableArr = new Drawable[3];
        drawableArr[0] = new ColorDrawable(i);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RectShape());
        shapeDrawable.getPaint().setStrokeWidth(2.0f * f);
        shapeDrawable.getPaint().setStyle(Style.STROKE);
        shapeDrawable.getPaint().setColor(f823c);
        drawableArr[1] = shapeDrawable;
        shapeDrawable = new ShapeDrawable(new RectShape());
        shapeDrawable.getPaint().setStrokeWidth(f);
        shapeDrawable.getPaint().setStyle(Style.STROKE);
        shapeDrawable.getPaint().setColor(i2);
        drawableArr[2] = shapeDrawable;
        return new LayerDrawable(drawableArr);
    }

    public static Drawable m735a(Context context) {
        Drawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(f827g, new ColorDrawable(f835o));
        stateListDrawable.addState(f829i, new ColorDrawable(f836p));
        stateListDrawable.addState(f830j, C0149N.m734a(f833m, f834n, C0149N.m737c(context)));
        stateListDrawable.addState(f828h, C0149N.m733a(f833m, C0149N.m737c(context)));
        return stateListDrawable;
    }

    public static Drawable m736b(Context context) {
        Drawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(f827g, new ColorDrawable(f839s));
        stateListDrawable.addState(f829i, new ColorDrawable(f840t));
        stateListDrawable.addState(f830j, C0149N.m734a(f837q, f838r, C0149N.m737c(context)));
        stateListDrawable.addState(f828h, C0149N.m733a(f837q, C0149N.m737c(context)));
        return stateListDrawable;
    }

    private static float m737c(Context context) {
        return context.getResources().getDisplayMetrics().density * (C0150O.m743b("4dip", context) / 2.0f);
    }
}
