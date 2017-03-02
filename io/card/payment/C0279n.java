package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.n */
public final class C0279n implements C0167k {
    private static Map f1171a;
    private static Map f1172b;

    static {
        f1171a = new HashMap();
        f1172b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0279n() {
        f1171a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "Diese Anwendung ist nicht f\u00fcr das Einlesen von Kreditkarten zugelassen.");
        f1171a.put(ap.CANCEL, "Abbrechen");
        f1171a.put(ap.CARDTYPE_AMERICANEXPRESS, "American Express");
        f1171a.put(ap.CARDTYPE_DISCOVER, "Discover");
        f1171a.put(ap.CARDTYPE_JCB, "JCB");
        f1171a.put(ap.CARDTYPE_MASTERCARD, "MasterCard");
        f1171a.put(ap.CARDTYPE_VISA, "Visa");
        f1171a.put(ap.DONE, "Fertig");
        f1171a.put(ap.ENTRY_CVV, "Kartenpr\u00fcfnr.");
        f1171a.put(ap.ENTRY_POSTAL_CODE, "PLZ");
        f1171a.put(ap.ENTRY_EXPIRES, "G\u00fcltig bis");
        f1171a.put(ap.ENTRY_NUMBER, "Nummer");
        f1171a.put(ap.ENTRY_TITLE, "Kreditkarte");
        f1171a.put(ap.EXPIRES_PLACEHOLDER, "MM/JJ");
        f1171a.put(ap.OK, "OK");
        f1171a.put(ap.SCAN_GUIDE, "Kreditkarte hierhin halten.\nSie wird automatisch gelesen.");
        f1171a.put(ap.KEYBOARD, "Tastatur\u2026");
        f1171a.put(ap.ENTRY_CARD_NUMBER, "Kartennummer");
        f1171a.put(ap.MANUAL_ENTRY_TITLE, "Kreditkartendetails");
        f1171a.put(ap.WHOOPS, "Leider ist ein Fehler aufgetreten.");
        f1171a.put(ap.ERROR_NO_DEVICE_SUPPORT, "Dieses Ger\u00e4t kann mit der Kamera keine Kreditkartennummern lesen.");
        f1171a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "Die Kamera ist nicht verf\u00fcgbar.");
        f1171a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "Beim \u00d6ffnen der Kamera ist ein unerwarteter Fehler aufgetreten.");
    }

    public final String m1130a() {
        return "de";
    }

    public final /* synthetic */ String m1131a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1172b.containsKey(str2) ? (String) f1172b.get(str2) : (String) f1171a.get(apVar);
    }
}
