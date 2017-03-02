package io.card.payment;

import android.content.Context;
import android.view.OrientationEventListener;

/* renamed from: io.card.payment.d */
final class C0160d extends OrientationEventListener {
    private /* synthetic */ CardIOActivity f938a;

    C0160d(CardIOActivity cardIOActivity, Context context) {
        this.f938a = cardIOActivity;
        super(context, 2);
    }

    public final void onOrientationChanged(int i) {
        this.f938a.m1068a(i);
    }
}
