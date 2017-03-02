package io.card.payment;

import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

final class ai implements HttpRequestInterceptor {
    private /* synthetic */ C0158a f902a;

    ai(C0158a c0158a) {
        this.f902a = c0158a;
    }

    public final void process(HttpRequest httpRequest, HttpContext httpContext) {
        for (String str : this.f902a.f870g.keySet()) {
            httpRequest.addHeader(str, (String) this.f902a.f870g.get(str));
        }
    }
}
