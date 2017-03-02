package io.card.payment;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: io.card.payment.U */
final class C0154U implements OnClickListener {
    private /* synthetic */ DataEntryActivity f854a;

    C0154U(DataEntryActivity dataEntryActivity) {
        this.f854a = dataEntryActivity;
    }

    public final void onClick(View view) {
        this.f854a.setResult(CardIOActivity.RESULT_ENTRY_CANCELED);
        this.f854a.finish();
    }
}
