package io.card.payment;

/* renamed from: io.card.payment.S */
final /* synthetic */ class C0152S {
    static final /* synthetic */ int[] f852a;

    static {
        f852a = new int[CardType.values().length];
        try {
            f852a[CardType.AMEX.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            f852a[CardType.DISCOVER.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            f852a[CardType.JCB.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            f852a[CardType.MASTERCARD.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            f852a[CardType.VISA.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            f852a[CardType.INSUFFICIENT_DIGITS.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            f852a[CardType.UNKNOWN.ordinal()] = 7;
        } catch (NoSuchFieldError e7) {
        }
    }
}
