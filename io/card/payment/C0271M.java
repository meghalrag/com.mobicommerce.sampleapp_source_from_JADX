package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.M */
public final class C0271M implements C0167k {
    private static Map f1153a;
    private static Map f1154b;

    static {
        f1153a = new HashMap();
        f1154b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0271M() {
        f1153a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "\u6b64\u61c9\u7528\u7a0b\u5f0f\u672a\u7d93\u6388\u6b0a\uff0c\u7121\u6cd5\u6383\u63cf\u4fe1\u7528\u5361\u3002");
        f1153a.put(ap.CANCEL, "\u53d6\u6d88");
        f1153a.put(ap.CARDTYPE_AMERICANEXPRESS, "\u7f8e\u570b\u904b\u901a");
        f1153a.put(ap.CARDTYPE_DISCOVER, "Discover");
        f1153a.put(ap.CARDTYPE_JCB, "JCB");
        f1153a.put(ap.CARDTYPE_MASTERCARD, "MasterCard");
        f1153a.put(ap.CARDTYPE_VISA, "Visa");
        f1153a.put(ap.DONE, "\u5b8c\u6210");
        f1153a.put(ap.ENTRY_CVV, "\u4fe1\u7528\u5361\u9a57\u8b49\u78bc");
        f1153a.put(ap.ENTRY_POSTAL_CODE, "\u90f5\u905e\u5340\u865f");
        f1153a.put(ap.ENTRY_EXPIRES, "\u5230\u671f\u65e5");
        f1153a.put(ap.ENTRY_NUMBER, "\u865f\u78bc");
        f1153a.put(ap.ENTRY_TITLE, "\u4fe1\u7528\u5361");
        f1153a.put(ap.EXPIRES_PLACEHOLDER, "\u6708 / \u5e74");
        f1153a.put(ap.OK, "\u78ba\u5b9a");
        f1153a.put(ap.SCAN_GUIDE, "\u5c07\u4fe1\u7528\u5361\u653e\u5728\u6b64\u8655\u3002\n\u7cfb\u7d71\u5c07\u81ea\u52d5\u6383\u63cf\u3002");
        f1153a.put(ap.KEYBOARD, "\u9375\u76e4\u2026");
        f1153a.put(ap.ENTRY_CARD_NUMBER, "\u5361\u865f");
        f1153a.put(ap.MANUAL_ENTRY_TITLE, "\u4fe1\u7528\u5361\u8a73\u7d30\u8cc7\u6599");
        f1153a.put(ap.WHOOPS, "\u7cdf\u7cd5\uff01");
        f1153a.put(ap.ERROR_NO_DEVICE_SUPPORT, "\u6b64\u88dd\u7f6e\u7121\u6cd5\u4f7f\u7528\u76f8\u6a5f\u8b80\u53d6\u5361\u865f\u3002");
        f1153a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "\u7121\u6cd5\u4f7f\u7528\u76f8\u6a5f\u3002");
        f1153a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "\u555f\u52d5\u76f8\u6a5f\u6642\u767c\u751f\u610f\u5916\u7684\u932f\u8aa4\u3002");
    }

    public final String m1105a() {
        return "zh-Hant_TW";
    }

    public final /* synthetic */ String m1106a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1154b.containsKey(str2) ? (String) f1154b.get(str2) : (String) f1153a.get(apVar);
    }
}
