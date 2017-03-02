package io.card.payment;

import android.content.Intent;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.squareup.okhttp.internal.http.HttpTransport;

/* renamed from: io.card.payment.e */
final class C0161e implements Runnable {
    private /* synthetic */ CardIOActivity f939a;

    C0161e(CardIOActivity cardIOActivity) {
        this.f939a = cardIOActivity;
    }

    public final void run() {
        this.f939a.getWindow().clearFlags(HttpTransport.DEFAULT_CHUNK_LENGTH);
        this.f939a.getWindow().addFlags(AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY);
        Intent intent = new Intent(this.f939a, DataEntryActivity.class);
        if (this.f939a.f1121f != null) {
            this.f939a.f1121f.m784b();
            if (!(CardIOActivity.f1113a == null || CardIOActivity.f1113a.isRecycled())) {
                CardIOActivity.f1113a.recycle();
            }
            CardIOActivity.f1113a = this.f939a.f1121f.m776a();
        }
        if (this.f939a.f1124i != null) {
            intent.putExtra(CardIOActivity.EXTRA_SCAN_RESULT, this.f939a.f1124i);
            this.f939a.f1124i = null;
        }
        intent.putExtras(this.f939a.getIntent());
        intent.addFlags(1082195968);
        this.f939a.startActivityForResult(intent, 10);
    }
}
