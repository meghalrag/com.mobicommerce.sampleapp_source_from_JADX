package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.r */
public final class C0283r implements C0167k {
    private static Map f1179a;
    private static Map f1180b;

    static {
        f1179a = new HashMap();
        f1180b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0283r() {
        f1179a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "Thees eppleeceshun is nut oothureezed fur cerd scunneeng.");
        f1179a.put(ap.CANCEL, "Cuncel");
        f1179a.put(ap.CARDTYPE_AMERICANEXPRESS, "Emereecun Ixpress");
        f1179a.put(ap.CARDTYPE_DISCOVER, "Deescufer");
        f1179a.put(ap.CARDTYPE_JCB, "JCB");
        f1179a.put(ap.CARDTYPE_MASTERCARD, "MesterCerd");
        f1179a.put(ap.CARDTYPE_VISA, "Feesa");
        f1179a.put(ap.DONE, "B\u00f6rk B\u00f6rk B\u00f6rk!");
        f1179a.put(ap.ENTRY_CVV, "CFF");
        f1179a.put(ap.ENTRY_POSTAL_CODE, "Pustel Cude");
        f1179a.put(ap.ENTRY_EXPIRES, "Expures");
        f1179a.put(ap.ENTRY_NUMBER, "Noomber");
        f1179a.put(ap.ENTRY_TITLE, "Cerd");
        f1179a.put(ap.EXPIRES_PLACEHOLDER, "MM/YY");
        f1179a.put(ap.OK, "OK");
        f1179a.put(ap.SCAN_GUIDE, "Huld cerd here-a.\nIt veell scun ootumeteecelly.");
        f1179a.put(ap.KEYBOARD, "Keybuerd\u2026");
        f1179a.put(ap.ENTRY_CARD_NUMBER, "Cerd Noomber");
        f1179a.put(ap.MANUAL_ENTRY_TITLE, "Cerd Deteeels");
        f1179a.put(ap.WHOOPS, "Vhuups!");
        f1179a.put(ap.ERROR_NO_DEVICE_SUPPORT, "Thees defeece-a cunnut use-a zee cemera tu reed cerd noombers.");
        f1179a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "Device camera is unavailable.");
        f1179a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "Zee defeece-a hed un unexpected irrur oopeneeng zee cemera.");
    }

    public final String m1138a() {
        return "en_SE";
    }

    public final /* synthetic */ String m1139a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1180b.containsKey(str2) ? (String) f1180b.get(str2) : (String) f1179a.get(apVar);
    }
}
