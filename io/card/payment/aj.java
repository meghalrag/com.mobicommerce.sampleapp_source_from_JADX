package io.card.payment;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;

final class aj implements HttpResponseInterceptor {
    aj() {
    }

    public final void process(HttpResponse httpResponse, HttpContext httpContext) {
        Header contentEncoding = httpResponse.getEntity().getContentEncoding();
        if (contentEncoding != null) {
            for (HeaderElement name : contentEncoding.getElements()) {
                if (name.getName().equalsIgnoreCase("gzip")) {
                    httpResponse.setEntity(new ak(httpResponse.getEntity()));
                    return;
                }
            }
        }
    }
}
