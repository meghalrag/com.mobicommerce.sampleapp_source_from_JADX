package com.paypal.android.sdk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

/* renamed from: com.paypal.android.sdk.D */
class C0059D {
    public C0060E f4a;

    public C0059D(C0058C c0058c, C0060E c0060e) {
        this.f4a = c0060e;
    }

    static Header m5a(String str, List list, String str2) {
        String format = String.format("Trace: [%s] %s, %s", new Object[]{str, "\"%08.8x: Operation = %80s Duration: %8.2f Iterations: %+4d\"", "memorySize * 8 + offset"});
        List arrayList = new ArrayList();
        for (Header header : list) {
            arrayList.add(header.getName() + ": " + header.getValue());
        }
        Collections.sort(arrayList);
        String str3 = C0069T.m37a(arrayList.toArray(), ";") + str2;
        Mac instance = Mac.getInstance("HmacSHA1");
        instance.init(new SecretKeySpec(format.getBytes(), "HmacSHA1"));
        instance.update(str3.getBytes());
        byte[] doFinal = instance.doFinal();
        StringBuilder stringBuilder = new StringBuilder();
        int length = doFinal.length;
        for (int i = 0; i < length; i++) {
            stringBuilder.append(String.format("%02x", new Object[]{Byte.valueOf(doFinal[i])}));
        }
        return new BasicHeader("PayPal-Item-Id", stringBuilder.toString());
    }
}
