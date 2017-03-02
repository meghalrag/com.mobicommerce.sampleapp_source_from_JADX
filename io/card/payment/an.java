package io.card.payment;

import android.os.SystemClock;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import javax.net.ssl.SSLHandshakeException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

final class an implements HttpRequestRetryHandler {
    private static HashSet f910a;
    private static HashSet f911b;
    private final int f912c;

    static {
        f910a = new HashSet();
        f911b = new HashSet();
        f910a.add(NoHttpResponseException.class);
        f910a.add(UnknownHostException.class);
        f910a.add(SocketException.class);
        f911b.add(InterruptedIOException.class);
        f911b.add(SSLHandshakeException.class);
    }

    public an() {
        this.f912c = 2;
    }

    public final boolean retryRequest(IOException iOException, int i, HttpContext httpContext) {
        boolean z;
        Boolean bool = (Boolean) httpContext.getAttribute("http.request_sent");
        Object obj = (bool == null || !bool.booleanValue()) ? null : 1;
        if (i <= 2 && !f911b.contains(iOException.getClass())) {
            if (f910a.contains(iOException.getClass())) {
                z = true;
            } else if (obj == null) {
                z = true;
            } else if (!((HttpUriRequest) httpContext.getAttribute("http.request")).getMethod().equals("POST")) {
                z = true;
            }
            if (z) {
                iOException.printStackTrace();
            } else {
                SystemClock.sleep(1500);
            }
            return z;
        }
        z = false;
        if (z) {
            iOException.printStackTrace();
        } else {
            SystemClock.sleep(1500);
        }
        return z;
    }
}
