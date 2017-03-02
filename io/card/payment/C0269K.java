package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.K */
public final class C0269K implements C0167k {
    private static Map f1149a;
    private static Map f1150b;

    static {
        f1149a = new HashMap();
        f1150b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0269K() {
        f1149a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "\u8be5\u5e94\u7528\u7a0b\u5e8f\u672a\u83b7\u5f97\u6388\u6743\uff0c\u4e0d\u80fd\u7528\u6765\u626b\u63cf\u5361\u3002");
        f1149a.put(ap.CANCEL, "\u53d6\u6d88");
        f1149a.put(ap.CARDTYPE_AMERICANEXPRESS, "American Express");
        f1149a.put(ap.CARDTYPE_DISCOVER, "Discover");
        f1149a.put(ap.CARDTYPE_JCB, "JCB");
        f1149a.put(ap.CARDTYPE_MASTERCARD, "MasterCard");
        f1149a.put(ap.CARDTYPE_VISA, "Visa");
        f1149a.put(ap.DONE, "\u5b8c\u6210");
        f1149a.put(ap.ENTRY_CVV, "CVV");
        f1149a.put(ap.ENTRY_POSTAL_CODE, "\u90ae\u653f\u7f16\u7801");
        f1149a.put(ap.ENTRY_EXPIRES, "\u5931\u6548\u65e5\u671f\uff1a");
        f1149a.put(ap.ENTRY_NUMBER, "\u53f7\u7801");
        f1149a.put(ap.ENTRY_TITLE, "\u5361");
        f1149a.put(ap.EXPIRES_PLACEHOLDER, "MM/YY");
        f1149a.put(ap.OK, "\u786e\u5b9a");
        f1149a.put(ap.SCAN_GUIDE, "\u6301\u5361\u7f6e\u4e8e\u6b64\u5904\u3002\n\u8bbe\u5907\u4f1a\u81ea\u52a8\u626b\u63cf\u5361\u3002");
        f1149a.put(ap.KEYBOARD, "\u952e\u76d8\u2026");
        f1149a.put(ap.ENTRY_CARD_NUMBER, "\u5361\u53f7");
        f1149a.put(ap.MANUAL_ENTRY_TITLE, "\u5361\u8be6\u7ec6\u4fe1\u606f");
        f1149a.put(ap.WHOOPS, "\u7cdf\u7cd5\uff01");
        f1149a.put(ap.ERROR_NO_DEVICE_SUPPORT, "\u6b64\u8bbe\u5907\u65e0\u6cd5\u4f7f\u7528\u6444\u50cf\u5934\u8bfb\u53d6\u5361\u53f7\u3002");
        f1149a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "\u8bbe\u5907\u6444\u50cf\u5934\u4e0d\u53ef\u7528\u3002");
        f1149a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "\u8bbe\u5907\u6253\u5f00\u6444\u50cf\u5934\u65f6\u51fa\u73b0\u610f\u5916\u9519\u8bef\u3002");
    }

    public final String m1101a() {
        return "zh-Hans";
    }

    public final /* synthetic */ String m1102a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1150b.containsKey(str2) ? (String) f1150b.get(str2) : (String) f1149a.get(apVar);
    }
}
