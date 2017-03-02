package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.H */
public final class C0266H implements C0167k {
    private static Map f1143a;
    private static Map f1144b;

    static {
        f1143a = new HashMap();
        f1144b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0266H() {
        f1143a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "Appen \u00e4r inte godk\u00e4nd f\u00f6r skanning av kort.");
        f1143a.put(ap.CANCEL, "Avbryt");
        f1143a.put(ap.CARDTYPE_AMERICANEXPRESS, "American Express");
        f1143a.put(ap.CARDTYPE_DISCOVER, "Discover");
        f1143a.put(ap.CARDTYPE_JCB, "JCB");
        f1143a.put(ap.CARDTYPE_MASTERCARD, "MasterCard");
        f1143a.put(ap.CARDTYPE_VISA, "Visa");
        f1143a.put(ap.DONE, "Klart");
        f1143a.put(ap.ENTRY_CVV, "CVV");
        f1143a.put(ap.ENTRY_POSTAL_CODE, "Postnummer");
        f1143a.put(ap.ENTRY_EXPIRES, "G\u00e5r ut");
        f1143a.put(ap.ENTRY_NUMBER, "Nummer");
        f1143a.put(ap.ENTRY_TITLE, "Kort");
        f1143a.put(ap.EXPIRES_PLACEHOLDER, "MM/\u00c5\u00c5");
        f1143a.put(ap.OK, "OK");
        f1143a.put(ap.SCAN_GUIDE, "H\u00e5ll kortet h\u00e4r.\nDet skannas automatiskt.");
        f1143a.put(ap.KEYBOARD, "Tangentbord \u2026");
        f1143a.put(ap.ENTRY_CARD_NUMBER, "Kortnummer");
        f1143a.put(ap.MANUAL_ENTRY_TITLE, "Kortinformation");
        f1143a.put(ap.WHOOPS, "Hoppsan!");
        f1143a.put(ap.ERROR_NO_DEVICE_SUPPORT, "Den h\u00e4r enheten kan inte anv\u00e4nda kameran till att l\u00e4sa kortnummer.");
        f1143a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "Enhetens kamera \u00e4r inte tillg\u00e4nglig.");
        f1143a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "Ett ov\u00e4ntat fel uppstod n\u00e4r enheten skulle \u00f6ppna kameran.");
    }

    public final String m1095a() {
        return "sv";
    }

    public final /* synthetic */ String m1096a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1144b.containsKey(str2) ? (String) f1144b.get(str2) : (String) f1143a.get(apVar);
    }
}
