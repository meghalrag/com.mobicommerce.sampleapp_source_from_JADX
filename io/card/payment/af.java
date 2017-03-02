package io.card.payment;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.v4.view.ViewCompat;
import java.util.Arrays;

final class af {
    private boolean f897a;
    private float f898b;
    private float f899c;

    public af(float f, float f2) {
        this.f897a = false;
        this.f898b = f;
        this.f899c = f2;
    }

    public final void m789a(Canvas canvas) {
        canvas.save();
        canvas.translate((-this.f898b) / 2.0f, (-this.f899c) / 2.0f);
        Paint paint = new Paint();
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint.setStyle(Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(1.5f);
        Paint paint2 = new Paint();
        paint2.setStyle(Style.FILL);
        paint2.setColor(-1);
        if (this.f897a) {
            paint2.setAlpha(192);
        } else {
            paint2.setAlpha(96);
        }
        float[] fArr = new float[8];
        Arrays.fill(fArr, 5.0f);
        RoundRectShape roundRectShape = new RoundRectShape(fArr, null, null);
        roundRectShape.resize(this.f898b, this.f899c);
        roundRectShape.draw(canvas, paint2);
        roundRectShape.draw(canvas, paint);
        paint = new Paint();
        paint.setStyle(Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        if (this.f897a) {
            paint.setColor(-1);
        } else {
            paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        }
        Path path = new Path();
        path.moveTo(10.0f, 0.0f);
        path.lineTo(0.0f, 11.0f);
        path.lineTo(6.0f, 11.0f);
        path.lineTo(2.0f, 20.0f);
        path.lineTo(13.0f, 8.0f);
        path.lineTo(7.0f, 8.0f);
        path.lineTo(10.0f, 0.0f);
        path.setLastPoint(10.0f, 0.0f);
        Matrix matrix = new Matrix();
        matrix.postTranslate(-6.5f, -10.0f);
        matrix.postScale(0.05f, 0.05f);
        path.transform(matrix);
        matrix = new Matrix();
        float f = 0.8f * this.f899c;
        matrix.postScale(f, f);
        path.transform(matrix);
        canvas.translate(this.f898b / 2.0f, this.f899c / 2.0f);
        canvas.drawPath(path, paint);
        canvas.restore();
    }

    public final void m790a(boolean z) {
        new StringBuilder("Torch ").append(z ? "ON" : "OFF");
        this.f897a = z;
    }
}
