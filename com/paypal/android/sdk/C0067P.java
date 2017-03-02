package com.paypal.android.sdk;

import android.util.Log;
import java.net.Socket;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;

/* renamed from: com.paypal.android.sdk.P */
public final class C0067P extends SSLSocketFactory {
    private javax.net.ssl.SSLSocketFactory f15a;

    public C0067P() {
        super(null);
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, new TrustManager[]{new C0066O()}, null);
            this.f15a = instance.getSocketFactory();
            setHostnameVerifier(new AllowAllHostnameVerifier());
        } catch (Exception e) {
            Log.e("paypal.sdk", "TrustAllSSLSocketFactory caught exception " + e.getMessage());
        }
    }

    public final Socket createSocket() {
        return this.f15a.createSocket();
    }

    public final Socket createSocket(Socket socket, String str, int i, boolean z) {
        return this.f15a.createSocket(socket, str, i, z);
    }
}
