package com.paypal.android.sdk;

import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public final class bl implements X509TrustManager {
    private X509TrustManager f362a;

    public bl(KeyStore keyStore) {
        this.f362a = null;
        TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        instance.init(null);
        TrustManager[] trustManagers = instance.getTrustManagers();
        if (trustManagers.length == 0) {
            throw new NoSuchAlgorithmException("no trust manager found");
        }
        this.f362a = (X509TrustManager) trustManagers[0];
    }

    public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        this.f362a.checkClientTrusted(x509CertificateArr, str);
    }

    public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        if (x509CertificateArr == null || x509CertificateArr.length != 1) {
            this.f362a.checkServerTrusted(x509CertificateArr, str);
        } else {
            x509CertificateArr[0].checkValidity();
        }
    }

    public final X509Certificate[] getAcceptedIssuers() {
        return this.f362a.getAcceptedIssuers();
    }
}
