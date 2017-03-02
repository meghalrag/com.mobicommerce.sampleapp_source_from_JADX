package com.squareup.okhttp.internal;

import java.net.InetAddress;
import java.net.UnknownHostException;

public interface Dns {
    public static final Dns DEFAULT;

    /* renamed from: com.squareup.okhttp.internal.Dns.1 */
    class C02501 implements Dns {
        C02501() {
        }

        public InetAddress[] getAllByName(String host) throws UnknownHostException {
            return InetAddress.getAllByName(host);
        }
    }

    InetAddress[] getAllByName(String str) throws UnknownHostException;

    static {
        DEFAULT = new C02501();
    }
}
