package com.paypal.android.sdk.payments;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.widget.CursorAdapter;
import com.paypal.android.sdk.C0297X;
import com.paypal.android.sdk.bN;
import com.paypal.android.sdk.bO;

final class av implements ac {
    private /* synthetic */ au f1097a;

    av(au auVar) {
        this.f1097a = auVar;
    }

    public final void m1041a(ag agVar) {
        this.f1097a.f714a.f622i.m502b().m108a();
        this.f1097a.f714a.m564j();
        if (!agVar.m633a() || "UNAUTHORIZED_PAYMENT".equals(agVar.f703b)) {
            switch (as.f712a[this.f1097a.f714a.f620g.ordinal()]) {
                case CursorAdapter.FLAG_AUTO_REQUERY /*1*/:
                    if ("DUPLICATE_TRANSACTION".equals(agVar.f703b)) {
                        this.f1097a.f714a.showDialog(6);
                        return;
                    } else {
                        this.f1097a.f714a.showDialog(5);
                        return;
                    }
                case CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER /*2*/:
                case FragmentManagerImpl.ANIM_STYLE_CLOSE_ENTER /*3*/:
                    this.f1097a.f714a.f618e.m165b(true);
                    C0108b.m653a(this.f1097a.f714a, bN.m132a(agVar.f703b), 1);
                    return;
                default:
                    return;
            }
        }
        switch (as.f712a[this.f1097a.f714a.f620g.ordinal()]) {
            case CursorAdapter.FLAG_AUTO_REQUERY /*1*/:
                C0108b.m653a(this.f1097a.f714a, bN.m131a(bO.SESSION_EXPIRED_MESSAGE), 4);
            case CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER /*2*/:
            case FragmentManagerImpl.ANIM_STYLE_CLOSE_ENTER /*3*/:
                this.f1097a.f714a.showDialog(2);
                PaymentConfirmActivity.f614a;
                new StringBuilder("server thinks token is expired, get new one. AccessToken: ").append(this.f1097a.f714a.f622i.m502b().f94c);
                this.f1097a.f714a.f622i.m494a(this.f1097a.f714a.m559h(), true);
            default:
        }
    }

    public final void m1042a(Object obj) {
        if (obj instanceof ProofOfPayment) {
            Parcelable paymentConfirmation = new PaymentConfirmation(this.f1097a.f714a.f622i.m504d(), this.f1097a.f714a.f619f.m641a(), (ProofOfPayment) obj);
            Intent intent = this.f1097a.f714a.getIntent();
            intent.putExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION, paymentConfirmation);
            this.f1097a.f714a.m549c();
            this.f1097a.f714a.setResult(-1, intent);
            this.f1097a.f714a.finish();
        } else if (obj instanceof C0297X) {
            PaymentConfirmActivity.m539a(this.f1097a.f714a, (C0297X) obj);
        }
    }
}
