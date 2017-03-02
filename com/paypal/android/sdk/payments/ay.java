package com.paypal.android.sdk.payments;

import android.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import com.paypal.android.sdk.bN;
import com.paypal.android.sdk.bO;
import com.paypal.android.sdk.cd;
import java.util.ArrayList;

final class ay implements OnClickListener {
    final /* synthetic */ cd f719a;
    final /* synthetic */ ArrayList f720b;
    final /* synthetic */ PaymentConfirmActivity f721c;

    ay(PaymentConfirmActivity paymentConfirmActivity, cd cdVar, ArrayList arrayList) {
        this.f721c = paymentConfirmActivity;
        this.f719a = cdVar;
        this.f720b = arrayList;
    }

    public final void onClick(View view) {
        Builder builder = new Builder(view.getContext());
        builder.setTitle(bN.m131a(bO.SHIPPING_ADDRESS)).setAdapter(this.f719a, new az(this));
        builder.create().show();
    }
}
