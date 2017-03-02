package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.x */
public final class C0289x implements C0167k {
    private static Map f1191a;
    private static Map f1192b;

    static {
        f1191a = new HashMap();
        f1192b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0289x() {
        f1191a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "\u3053\u306e\u30a2\u30d7\u30ea\u3067\u306f\u30ab\u30fc\u30c9\u30b9\u30ad\u30e3\u30f3\u306e\u4f7f\u7528\u304c\u8a31\u53ef\u3055\u308c\u3066\u3044\u307e\u305b\u3093\u3002");
        f1191a.put(ap.CANCEL, "\u30ad\u30e3\u30f3\u30bb\u30eb");
        f1191a.put(ap.CARDTYPE_AMERICANEXPRESS, "American Express");
        f1191a.put(ap.CARDTYPE_DISCOVER, "Discover");
        f1191a.put(ap.CARDTYPE_JCB, "JCB");
        f1191a.put(ap.CARDTYPE_MASTERCARD, "MasterCard");
        f1191a.put(ap.CARDTYPE_VISA, "Visa");
        f1191a.put(ap.DONE, "\u5b8c\u4e86");
        f1191a.put(ap.ENTRY_CVV, "CVV");
        f1191a.put(ap.ENTRY_POSTAL_CODE, "\u90f5\u4fbf\u756a\u53f7");
        f1191a.put(ap.ENTRY_EXPIRES, "\u6709\u52b9\u671f\u9650");
        f1191a.put(ap.ENTRY_NUMBER, "\u756a\u53f7");
        f1191a.put(ap.ENTRY_TITLE, "\u30ab\u30fc\u30c9");
        f1191a.put(ap.EXPIRES_PLACEHOLDER, "MM/YY");
        f1191a.put(ap.OK, "OK");
        f1191a.put(ap.SCAN_GUIDE, "\u3053\u3053\u3067\u30ab\u30fc\u30c9\u3092\u304a\u6301\u3061\u304f\u3060\u3055\u3044\u3002\n\u81ea\u52d5\u7684\u306b\u30b9\u30ad\u30e3\u30f3\u3055\u308c\u307e\u3059\u3002");
        f1191a.put(ap.KEYBOARD, "\u30ad\u30fc\u30dc\u30fc\u30c9\u2026");
        f1191a.put(ap.ENTRY_CARD_NUMBER, "\u30ab\u30fc\u30c9\u756a\u53f7");
        f1191a.put(ap.MANUAL_ENTRY_TITLE, "\u30ab\u30fc\u30c9\u306e\u8a73\u7d30");
        f1191a.put(ap.WHOOPS, "\u7533\u3057\u8a33\u3042\u308a\u307e\u305b\u3093\u3002");
        f1191a.put(ap.ERROR_NO_DEVICE_SUPPORT, "\u3053\u306e\u7aef\u672b\u3067\u306f\u30ab\u30fc\u30c9\u756a\u53f7\u306e\u8aad\u8fbc\u306b\u30ab\u30e1\u30e9\u3092\u4f7f\u3048\u307e\u305b\u3093\u3002");
        f1191a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "\u7aef\u672b\u306e\u30ab\u30e1\u30e9\u3092\u4f7f\u7528\u3067\u304d\u307e\u305b\u3093\u3002");
        f1191a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "\u30ab\u30e1\u30e9\u3092\u8d77\u52d5\u4e2d\u306b\u4e88\u671f\u3057\u306a\u3044\u30a8\u30e9\u30fc\u304c\u767a\u751f\u3057\u307e\u3057\u305f\u3002");
    }

    public final String m1150a() {
        return "ja";
    }

    public final /* synthetic */ String m1151a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1192b.containsKey(str2) ? (String) f1192b.get(str2) : (String) f1191a.get(apVar);
    }
}
