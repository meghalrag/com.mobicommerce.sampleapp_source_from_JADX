package io.card.payment;

import org.apache.http.client.HttpResponseException;

final class ae extends C0162f {
    private /* synthetic */ C0159c f1166a;

    ae(C0159c c0159c) {
        this.f1166a = c0159c;
    }

    public final void m1121a(String str) {
        this.f1166a.authorizeScanSuccessful();
    }

    public final void m1122a(Throwable th) {
        if (!(th instanceof HttpResponseException)) {
            this.f1166a.authorizeScanFailed(th);
        } else if (((HttpResponseException) th).getStatusCode() == 401) {
            this.f1166a.authorizeScanUnsuccessful();
        } else {
            this.f1166a.authorizeScanFailed(th);
        }
    }
}
