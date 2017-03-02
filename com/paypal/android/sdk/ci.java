package com.paypal.android.sdk;

import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

final class ci implements HttpRequestInterceptor {
    private /* synthetic */ ch f444a;

    ci(ch chVar) {
        this.f444a = chVar;
    }

    public final void process(HttpRequest httpRequest, HttpContext httpContext) {
        if (!httpRequest.containsHeader("Accept-Encoding")) {
            httpRequest.addHeader("Accept-Encoding", "gzip");
        }
        for (String str : this.f444a.f443g.keySet()) {
            httpRequest.addHeader(str, (String) this.f444a.f443g.get(str));
        }
    }
}
