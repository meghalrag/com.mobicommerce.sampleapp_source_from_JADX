package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.L */
public final class C0270L implements C0167k {
    private static Map f1151a;
    private static Map f1152b;

    static {
        f1151a = new HashMap();
        f1152b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0270L() {
        f1151a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "\u6b64\u61c9\u7528\u7a0b\u5f0f\u6c92\u6709\u6383\u63cf\u4fe1\u7528\u5361\u7684\u6388\u6b0a\u3002");
        f1151a.put(ap.CANCEL, "\u53d6\u6d88");
        f1151a.put(ap.CARDTYPE_AMERICANEXPRESS, "\u7f8e\u570b\u904b\u901a");
        f1151a.put(ap.CARDTYPE_DISCOVER, "Discover");
        f1151a.put(ap.CARDTYPE_JCB, "JCB");
        f1151a.put(ap.CARDTYPE_MASTERCARD, "MasterCard");
        f1151a.put(ap.CARDTYPE_VISA, "Visa");
        f1151a.put(ap.DONE, "\u5b8c\u6210");
        f1151a.put(ap.ENTRY_CVV, "CVV");
        f1151a.put(ap.ENTRY_POSTAL_CODE, "\u90f5\u905e\u5340\u865f");
        f1151a.put(ap.ENTRY_EXPIRES, "\u5230\u671f");
        f1151a.put(ap.ENTRY_NUMBER, "\u865f\u78bc");
        f1151a.put(ap.ENTRY_TITLE, "\u4fe1\u7528\u5361");
        f1151a.put(ap.EXPIRES_PLACEHOLDER, "\u6708\uff0f\u5e74");
        f1151a.put(ap.OK, "\u78ba\u5b9a");
        f1151a.put(ap.SCAN_GUIDE, "\u5c07\u4fe1\u7528\u5361\u7f6e\u65bc\u6b64\u8655\u3002\n\u88dd\u7f6e\u6703\u81ea\u52d5\u6383\u63cf\u3002");
        f1151a.put(ap.KEYBOARD, "\u9375\u76e4\u2026");
        f1151a.put(ap.ENTRY_CARD_NUMBER, "\u5361\u865f");
        f1151a.put(ap.MANUAL_ENTRY_TITLE, "\u4fe1\u7528\u5361\u8a73\u7d30\u8cc7\u6599");
        f1151a.put(ap.WHOOPS, "\u62b1\u6b49\uff01");
        f1151a.put(ap.ERROR_NO_DEVICE_SUPPORT, "\u6b64\u88dd\u7f6e\u7121\u6cd5\u4f7f\u7528\u76f8\u6a5f\u8b80\u53d6\u4fe1\u7528\u5361\u5361\u865f\u3002");
        f1151a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "\u7121\u6cd5\u4f7f\u7528\u88dd\u7f6e\u7684\u76f8\u6a5f\u3002");
        f1151a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "\u6b64\u88dd\u7f6e\u555f\u52d5\u76f8\u6a5f\u6642\u767c\u751f\u610f\u5916\u932f\u8aa4\u3002");
    }

    public final String m1103a() {
        return "zh-Hant";
    }

    public final /* synthetic */ String m1104a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1152b.containsKey(str2) ? (String) f1152b.get(str2) : (String) f1151a.get(apVar);
    }
}
