package io.card.payment;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

/* renamed from: io.card.payment.h */
public final class C0164h {
    private static String f942a;

    static {
        f942a = "UTF-8";
    }

    protected final String m816a() {
        List linkedList = new LinkedList();
        ConcurrentHashMap concurrentHashMap = null;
        for (Entry entry : concurrentHashMap.entrySet()) {
            linkedList.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
        }
        return URLEncodedUtils.format(linkedList, f942a);
    }

    public final String toString() {
        ConcurrentHashMap concurrentHashMap = null;
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : concurrentHashMap.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append((String) entry.getKey());
            stringBuilder.append("=");
            stringBuilder.append((String) entry.getValue());
        }
        for (Entry entry2 : concurrentHashMap.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append((String) entry2.getKey());
            stringBuilder.append("=");
            stringBuilder.append("FILE");
        }
        return stringBuilder.toString();
    }
}
