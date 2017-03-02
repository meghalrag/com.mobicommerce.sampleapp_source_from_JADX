package com.paypal.android.sdk;

/* renamed from: com.paypal.android.sdk.b */
public enum C0071b {
    SiteCatalystRequest(C0075f.GET),
    FptiRequest(C0075f.POST),
    PreAuthRequest(C0075f.POST),
    LoginRequest(C0075f.POST),
    ConsentRequest(C0075f.POST),
    CreditCardPaymentRequest(C0075f.POST),
    PayPalPaymentRequest(C0075f.POST),
    CreateSfoPaymentRequest(C0075f.POST),
    ApproveAndExecuteSfoPaymentRequest(C0075f.POST),
    TokenizeCreditCardRequest(C0075f.POST),
    DeleteCreditCardRequest(C0075f.DELETE),
    GetAppInfoRequest(C0075f.GET);
    
    private C0075f f91m;

    private C0071b(C0075f c0075f) {
        this.f91m = c0075f;
    }

    public final C0075f m107a() {
        return this.f91m;
    }
}
