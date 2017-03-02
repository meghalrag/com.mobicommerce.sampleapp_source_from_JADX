package com.paypal.android.sdk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public final class cp {
    private static String f453a;

    static {
        f453a = "UTF-8";
    }

    protected final String m329a() {
        ConcurrentHashMap concurrentHashMap = null;
        List linkedList = new LinkedList();
        for (Entry entry : concurrentHashMap.entrySet()) {
            linkedList.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
        }
        for (Entry entry2 : concurrentHashMap.entrySet()) {
            Iterator it = ((ArrayList) entry2.getValue()).iterator();
            while (it.hasNext()) {
                linkedList.add(new BasicNameValuePair((String) entry2.getKey(), (String) it.next()));
            }
        }
        return URLEncodedUtils.format(linkedList, f453a);
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
        for (Entry entry22 : concurrentHashMap.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            ArrayList arrayList = (ArrayList) entry22.getValue();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (arrayList.indexOf(str) != 0) {
                    stringBuilder.append("&");
                }
                stringBuilder.append((String) entry22.getKey());
                stringBuilder.append("=");
                stringBuilder.append(str);
            }
        }
        return stringBuilder.toString();
    }
}
