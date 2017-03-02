package com.paypal.android.sdk;

import java.util.Comparator;
import java.util.Currency;

/* renamed from: com.paypal.android.sdk.n */
final class C0083n implements Comparator {
    C0083n() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        return ((Currency) obj).getCurrencyCode().compareTo(((Currency) obj2).getCurrencyCode());
    }
}
