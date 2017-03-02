package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.G */
public final class C0265G implements C0167k {
    private static Map f1141a;
    private static Map f1142b;

    static {
        f1141a = new HashMap();
        f1142b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0265G() {
        f1141a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "\u042d\u0442\u043e \u043f\u0440\u0438\u043b\u043e\u0436\u0435\u043d\u0438\u0435 \u043d\u0435 \u0430\u0432\u0442\u043e\u0440\u0438\u0437\u043e\u0432\u0430\u043d\u043e \u0434\u043b\u044f \u0441\u043a\u0430\u043d\u0438\u0440\u043e\u0432\u0430\u043d\u0438\u044f \u043a\u0430\u0440\u0442.");
        f1141a.put(ap.CANCEL, "\u041e\u0442\u043c\u0435\u043d\u0430");
        f1141a.put(ap.CARDTYPE_AMERICANEXPRESS, "American Express");
        f1141a.put(ap.CARDTYPE_DISCOVER, "Discover");
        f1141a.put(ap.CARDTYPE_JCB, "JCB");
        f1141a.put(ap.CARDTYPE_MASTERCARD, "MasterCard");
        f1141a.put(ap.CARDTYPE_VISA, "Visa");
        f1141a.put(ap.DONE, "\u0413\u043e\u0442\u043e\u0432\u043e");
        f1141a.put(ap.ENTRY_CVV, "\u041a\u043e\u0434 \u0431\u0435\u0437\u043e\u043f\u0430\u0441\u043d\u043e\u0441\u0442\u0438");
        f1141a.put(ap.ENTRY_POSTAL_CODE, "\u0418\u043d\u0434\u0435\u043a\u0441");
        f1141a.put(ap.ENTRY_EXPIRES, "\u0414\u0435\u0439\u0441\u0442\u0432\u0438\u0442\u0435\u043b\u044c\u043d\u0430 \u0434\u043e");
        f1141a.put(ap.ENTRY_NUMBER, "\u041d\u043e\u043c\u0435\u0440");
        f1141a.put(ap.ENTRY_TITLE, "\u041a\u0430\u0440\u0442\u0430");
        f1141a.put(ap.EXPIRES_PLACEHOLDER, "\u041c\u041c/\u0413\u0413");
        f1141a.put(ap.OK, "\u041e\u041a");
        f1141a.put(ap.SCAN_GUIDE, "\u0414\u0435\u0440\u0436\u0438\u0442\u0435 \u043a\u0430\u0440\u0442\u0443 \u0432\u043d\u0443\u0442\u0440\u0438 \u0440\u0430\u043c\u043a\u0438.\n\u041e\u043d\u0430 \u0431\u0443\u0434\u0435\u0442 \u0441\u0447\u0438\u0442\u0430\u043d\u0430\n\u0430\u0432\u0442\u043e\u043c\u0430\u0442\u0438\u0447\u0435\u0441\u043a\u0438.");
        f1141a.put(ap.KEYBOARD, "\u041a\u043b\u0430\u0432\u0438\u0430\u0442\u0443\u0440\u0430\u2026");
        f1141a.put(ap.ENTRY_CARD_NUMBER, "\u041d\u043e\u043c\u0435\u0440 \u043a\u0430\u0440\u0442\u044b");
        f1141a.put(ap.MANUAL_ENTRY_TITLE, "\u0418\u043d\u0444\u043e\u0440\u043c\u0430\u0446\u0438\u044f \u043e \u043a\u0430\u0440\u0442\u0435");
        f1141a.put(ap.WHOOPS, "\u041e\u0439\u2026");
        f1141a.put(ap.ERROR_NO_DEVICE_SUPPORT, "\u0412 \u0434\u0430\u043d\u043d\u043e\u043c \u0443\u0441\u0442\u0440\u043e\u0439\u0441\u0442\u0432\u0435 \u043d\u0435\u0442 \u043e\u043f\u0446\u0438\u0438 \u0441\u0447\u0438\u0442\u044b\u0432\u0430\u043d\u0438\u044f \u043d\u043e\u043c\u0435\u0440\u0430 \u043a\u0430\u0440\u0442\u044b \u0441 \u043f\u043e\u043c\u043e\u0449\u044c\u044e \u0444\u043e\u0442\u043e\u043a\u0430\u043c\u0435\u0440\u044b.");
        f1141a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "\u0424\u043e\u0442\u043e\u043a\u0430\u043c\u0435\u0440\u0430 \u0443\u0441\u0442\u0440\u043e\u0439\u0441\u0442\u0432\u0430 \u043d\u0435\u0434\u043e\u0441\u0442\u0443\u043f\u043d\u0430.");
        f1141a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "\u0412\u043e\u0437\u043d\u0438\u043a\u043b\u0430 \u043d\u0435\u0437\u0430\u043f\u043b\u0430\u043d\u0438\u0440\u043e\u0432\u0430\u043d\u043d\u0430\u044f \u043e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u043e\u0442\u043a\u0440\u044b\u0442\u0438\u0438 \u0444\u043e\u0442\u043e\u043a\u0430\u043c\u0435\u0440\u044b \u0443\u0441\u0442\u0440\u043e\u0439\u0441\u0442\u0432\u0430.");
    }

    public final String m1093a() {
        return "ru";
    }

    public final /* synthetic */ String m1094a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1142b.containsKey(str2) ? (String) f1142b.get(str2) : (String) f1141a.get(apVar);
    }
}
