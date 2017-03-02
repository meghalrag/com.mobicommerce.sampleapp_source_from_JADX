package io.card.payment;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import com.paypal.android.sdk.payments.BuildConfig;
import java.lang.ref.WeakReference;

final class ac extends View {
    private static final Orientation[] f871a;
    private final WeakReference f872b;
    private DetectionInfo f873c;
    private Bitmap f874d;
    private Rect f875e;
    private CreditCard f876f;
    private int f877g;
    private int f878h;
    private boolean f879i;
    private String f880j;
    private GradientDrawable f881k;
    private final Paint f882l;
    private final Paint f883m;
    private Path f884n;
    private Rect f885o;
    private final af f886p;
    private final C0157Z f887q;
    private Rect f888r;
    private Rect f889s;
    private final boolean f890t;
    private int f891u;
    private float f892v;

    static {
        f871a = new Orientation[]{Orientation.TOP_BOTTOM, Orientation.LEFT_RIGHT, Orientation.BOTTOM_TOP, Orientation.RIGHT_LEFT};
    }

    public ac(CardIOActivity cardIOActivity, boolean z) {
        super(cardIOActivity, null);
        this.f879i = false;
        this.f892v = 1.0f;
        this.f890t = z;
        this.f872b = new WeakReference(cardIOActivity);
        this.f891u = 1;
        this.f892v = getResources().getDisplayMetrics().density / 1.5f;
        this.f886p = new af(70.0f * this.f892v, 50.0f * this.f892v);
        this.f887q = new C0157Z(cardIOActivity);
        this.f882l = new Paint(1);
        this.f883m = new Paint(1);
        this.f883m.clearShadowLayer();
        this.f883m.setStyle(Style.FILL);
        this.f883m.setColor(-1157627904);
        this.f880j = ao.m801a(ap.SCAN_GUIDE);
    }

    private Rect m774a(int i, int i2, int i3, int i4) {
        int i5 = (int) (8.0f * this.f892v);
        Rect rect = new Rect();
        rect.left = Math.min(i, i3) - i5;
        rect.right = Math.max(i, i3) + i5;
        rect.top = Math.min(i2, i4) - i5;
        rect.bottom = i5 + Math.max(i2, i4);
        return rect;
    }

    public static boolean m775c() {
        return false;
    }

    public final Bitmap m776a() {
        return (this.f874d == null || this.f874d.isRecycled()) ? null : Bitmap.createBitmap(this.f874d, 0, 0, this.f874d.getWidth(), this.f874d.getHeight());
    }

    public final void m777a(int i) {
        this.f878h = i;
    }

    public final void m778a(Bitmap bitmap) {
        if (this.f874d != null) {
            this.f874d.recycle();
        }
        this.f874d = bitmap;
        if (this.f874d != null) {
            RectF rectF = new RectF(2.0f, 2.0f, (float) (this.f874d.getWidth() - 2), (float) (this.f874d.getHeight() - 2));
            float height = ((float) this.f874d.getHeight()) * 0.06666667f;
            Bitmap createBitmap = Bitmap.createBitmap(this.f874d.getWidth(), this.f874d.getHeight(), Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawColor(0);
            Paint paint = new Paint(1);
            paint.setColor(ViewCompat.MEASURED_STATE_MASK);
            paint.setStyle(Style.FILL);
            canvas.drawRoundRect(rectF, height, height, paint);
            Paint paint2 = new Paint();
            paint2.setFilterBitmap(false);
            Canvas canvas2 = new Canvas(this.f874d);
            paint2.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
            canvas2.drawBitmap(createBitmap, 0.0f, 0.0f, paint2);
            paint2.setXfermode(null);
            createBitmap.recycle();
        }
    }

    public final void m779a(Rect rect) {
        this.f885o = rect;
    }

    public final void m780a(Rect rect, int i) {
        Point point;
        new StringBuilder("setGuideAndRotation: ").append(rect).append(", ").append(i);
        this.f877g = i;
        this.f875e = rect;
        invalidate();
        if (this.f877g % 180 != 0) {
            point = new Point((int) (this.f892v * 40.0f), (int) (this.f892v * 60.0f));
            this.f891u = -1;
        } else {
            point = new Point((int) (this.f892v * 60.0f), (int) (this.f892v * 40.0f));
            this.f891u = 1;
        }
        if (this.f885o != null) {
            new StringBuilder().append(this.f885o).append(", ").append(point).append(", ").append(this.f885o).append(", ").append(point);
            this.f888r = ag.m791a(new Point(this.f885o.left + point.x, this.f885o.top + point.y), (int) (70.0f * this.f892v), (int) (this.f892v * 50.0f));
            this.f889s = ag.m791a(new Point(this.f885o.right - point.x, point.y + this.f885o.top), (int) (100.0f * this.f892v), (int) (this.f892v * 50.0f));
            this.f881k = new GradientDrawable(f871a[(this.f877g / 90) % 4], new int[]{-1, ViewCompat.MEASURED_STATE_MASK});
            this.f881k.setGradientType(0);
            this.f881k.setBounds(this.f875e);
            this.f881k.setAlpha(50);
            this.f884n = new Path();
            this.f884n.addRect(new RectF(this.f885o), Direction.CW);
            this.f884n.addRect(new RectF(this.f875e), Direction.CCW);
        }
    }

    public final void m781a(CreditCard creditCard) {
        this.f876f = creditCard;
    }

    public final void m782a(DetectionInfo detectionInfo) {
        if (this.f873c != null) {
            DetectionInfo detectionInfo2 = this.f873c;
            Object obj = (detectionInfo.topEdge == detectionInfo2.topEdge && detectionInfo.bottomEdge == detectionInfo2.bottomEdge && detectionInfo.leftEdge == detectionInfo2.leftEdge && detectionInfo.rightEdge == detectionInfo2.rightEdge) ? 1 : null;
            if (obj == null) {
                invalidate();
            }
        }
        this.f873c = detectionInfo;
    }

    public final void m783a(boolean z) {
        this.f886p.m790a(z);
        invalidate();
    }

    public final void m784b() {
        int i = 0;
        if (this.f874d != null) {
            if (this.f876f.flipped) {
                Matrix matrix = new Matrix();
                matrix.setRotate(180.0f, (float) (this.f874d.getWidth() / 2), (float) (this.f874d.getHeight() / 2));
                this.f874d = Bitmap.createBitmap(this.f874d, 0, 0, this.f874d.getWidth(), this.f874d.getHeight(), matrix, false);
            }
            Canvas canvas = new Canvas(this.f874d);
            Paint paint = new Paint();
            ag.m793a(paint);
            paint.setTextSize(28.0f * this.f892v);
            int length = this.f876f.cardNumber.length();
            float width = ((float) this.f874d.getWidth()) / 428.0f;
            int i2 = (int) ((((float) this.f876f.yoff) * width) - 6.0f);
            while (i < length) {
                canvas.drawText(this.f876f.cardNumber.charAt(i), (float) ((int) (((float) this.f876f.xoff[i]) * width)), (float) i2, paint);
                i++;
            }
        }
    }

    public final void m785b(boolean z) {
        this.f887q.m766a(z);
    }

    public final Rect m786d() {
        return this.f888r;
    }

    public final void onDraw(Canvas canvas) {
        if (this.f875e != null && this.f885o != null) {
            canvas.save();
            this.f881k.draw(canvas);
            int i = (this.f877g == 0 || this.f877g == 180) ? (this.f875e.bottom - this.f875e.top) / 4 : (this.f875e.right - this.f875e.left) / 4;
            if (this.f873c != null && this.f873c.m732c() == 4) {
                canvas.drawPath(this.f884n, this.f883m);
            }
            this.f882l.clearShadowLayer();
            this.f882l.setStyle(Style.FILL);
            this.f882l.setColor(this.f878h);
            canvas.drawRect(m774a(this.f875e.left, this.f875e.top, this.f875e.left + i, this.f875e.top), this.f882l);
            canvas.drawRect(m774a(this.f875e.left, this.f875e.top, this.f875e.left, this.f875e.top + i), this.f882l);
            canvas.drawRect(m774a(this.f875e.right, this.f875e.top, this.f875e.right - i, this.f875e.top), this.f882l);
            canvas.drawRect(m774a(this.f875e.right, this.f875e.top, this.f875e.right, this.f875e.top + i), this.f882l);
            canvas.drawRect(m774a(this.f875e.left, this.f875e.bottom, this.f875e.left + i, this.f875e.bottom), this.f882l);
            canvas.drawRect(m774a(this.f875e.left, this.f875e.bottom, this.f875e.left, this.f875e.bottom - i), this.f882l);
            canvas.drawRect(m774a(this.f875e.right, this.f875e.bottom, this.f875e.right - i, this.f875e.bottom), this.f882l);
            canvas.drawRect(m774a(this.f875e.right, this.f875e.bottom, this.f875e.right, this.f875e.bottom - i), this.f882l);
            if (this.f873c != null) {
                if (this.f873c.topEdge) {
                    canvas.drawRect(m774a(this.f875e.left, this.f875e.top, this.f875e.right, this.f875e.top), this.f882l);
                }
                if (this.f873c.bottomEdge) {
                    canvas.drawRect(m774a(this.f875e.left, this.f875e.bottom, this.f875e.right, this.f875e.bottom), this.f882l);
                }
                if (this.f873c.leftEdge) {
                    canvas.drawRect(m774a(this.f875e.left, this.f875e.top, this.f875e.left, this.f875e.bottom), this.f882l);
                }
                if (this.f873c.rightEdge) {
                    canvas.drawRect(m774a(this.f875e.right, this.f875e.top, this.f875e.right, this.f875e.bottom), this.f882l);
                }
                if (this.f873c.m732c() < 3) {
                    float f = 34.0f * this.f892v;
                    float f2 = 26.0f * this.f892v;
                    ag.m793a(this.f882l);
                    this.f882l.setTextAlign(Align.CENTER);
                    this.f882l.setTextSize(f2);
                    canvas.translate((float) (this.f875e.left + (this.f875e.width() / 2)), (float) (this.f875e.top + (this.f875e.height() / 2)));
                    canvas.rotate((float) (this.f891u * this.f877g));
                    if (!(this.f880j == null || this.f880j == BuildConfig.VERSION_NAME)) {
                        String[] split = this.f880j.split("\n");
                        float f3 = (-(((((float) (split.length - 1)) * f) - f2) / 2.0f)) - 3.0f;
                        for (String drawText : split) {
                            canvas.drawText(drawText, 0.0f, f3, this.f882l);
                            f3 += f;
                        }
                    }
                }
            }
            canvas.restore();
            canvas.save();
            canvas.translate(this.f889s.exactCenterX(), this.f889s.exactCenterY());
            canvas.rotate((float) (this.f891u * this.f877g));
            this.f887q.m765a(canvas, 100.0f * this.f892v, 50.0f * this.f892v);
            canvas.restore();
            if (this.f890t) {
                canvas.save();
                canvas.translate(this.f888r.exactCenterX(), this.f888r.exactCenterY());
                canvas.rotate((float) (this.f891u * this.f877g));
                this.f886p.m789a(canvas);
                canvas.restore();
            }
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            if ((motionEvent.getAction() & MotionEventCompat.ACTION_MASK) == 0) {
                Point point = new Point((int) motionEvent.getX(), (int) motionEvent.getY());
                Rect a = ag.m791a(point, 20, 20);
                new StringBuilder("onTouchEvent: ").append(point);
                if (this.f890t && this.f888r != null && Rect.intersects(this.f888r, a)) {
                    ((CardIOActivity) this.f872b.get()).m1085b();
                } else if (this.f889s == null || !Rect.intersects(this.f889s, a)) {
                    ((CardIOActivity) this.f872b.get()).m1086c();
                }
            }
        } catch (NullPointerException e) {
        }
        return false;
    }
}
