package io.card.payment;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.view.MotionEventCompat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

class ad extends ViewGroup {
    private static /* synthetic */ boolean f893d;
    private int f894a;
    private int f895b;
    private SurfaceView f896c;

    static {
        f893d = !ad.class.desiredAssertionStatus();
    }

    public ad(Context context) {
        super(context, null);
        this.f894a = 480;
        this.f895b = 640;
        this.f896c = new SurfaceView(context);
        addView(this.f896c);
    }

    public final SurfaceView m787a() {
        if (f893d || this.f896c != null) {
            return this.f896c;
        }
        throw new AssertionError();
    }

    final SurfaceHolder m788b() {
        SurfaceHolder holder = m787a().getHolder();
        if (f893d || holder != null) {
            return holder;
        }
        throw new AssertionError();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawARGB(MotionEventCompat.ACTION_MASK, MotionEventCompat.ACTION_MASK, 0, 0);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        StringBuilder stringBuilder = new StringBuilder("- isSurfaceValid: false");
        if (z && getChildCount() > 0) {
            if (f893d || this.f896c != null) {
                int i5 = i3 - i;
                int i6 = i4 - i2;
                int i7;
                if (this.f895b * i5 > this.f894a * i6) {
                    i7 = (this.f894a * i6) / this.f895b;
                    this.f896c.layout((i5 - i7) / 2, 0, (i5 + i7) / 2, i6);
                    return;
                }
                i7 = (this.f895b * i5) / this.f894a;
                this.f896c.layout(0, (i6 - i7) / 2, i5, (i6 + i7) / 2);
                return;
            }
            throw new AssertionError();
        }
    }

    protected void onMeasure(int i, int i2) {
        String.format("Preview.onMeasure(w:%d, h:%d) setMeasuredDimension(w:%d, h:%d)", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(resolveSize(getSuggestedMinimumWidth(), i)), Integer.valueOf(resolveSize(getSuggestedMinimumHeight(), i2))});
        setMeasuredDimension(r0, r1);
    }
}
