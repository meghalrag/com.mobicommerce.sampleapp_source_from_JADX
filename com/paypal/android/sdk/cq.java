package com.paypal.android.sdk;

import android.os.SystemClock;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Iterator;
import javax.net.ssl.SSLException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

final class cq implements HttpRequestRetryHandler {
    private static HashSet f454a;
    private static HashSet f455b;
    private final int f456c;

    static {
        f454a = new HashSet();
        f455b = new HashSet();
        f454a.add(NoHttpResponseException.class);
        f454a.add(UnknownHostException.class);
        f454a.add(SocketException.class);
        f455b.add(InterruptedIOException.class);
        f455b.add(SSLException.class);
    }

    public cq(int i) {
        this.f456c = 5;
    }

    private static boolean m330a(HashSet hashSet, Throwable th) {
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            if (((Class) it.next()).isInstance(th)) {
                return true;
            }
        }
        return false;
    }

    public final boolean retryRequest(IOException iOException, int i, HttpContext httpContext) {
        boolean z = true;
        Boolean bool = (Boolean) httpContext.getAttribute("http.request_sent");
        boolean z2 = bool != null && bool.booleanValue();
        z2 = i > this.f456c ? false : m330a(f455b, iOException) ? false : m330a(f454a, iOException) ? true : !z2 ? true : true;
        if (!z2) {
            z = z2;
        } else if (((HttpUriRequest) httpContext.getAttribute("http.request")).getMethod().equals("POST")) {
            z = false;
        }
        if (z) {
            SystemClock.sleep(1500);
        } else {
            iOException.printStackTrace();
        }
        return z;
    }
}
