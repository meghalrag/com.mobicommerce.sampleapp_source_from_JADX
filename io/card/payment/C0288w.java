package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.w */
public final class C0288w implements C0167k {
    private static Map f1189a;
    private static Map f1190b;

    static {
        f1189a = new HashMap();
        f1190b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0288w() {
        f1189a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "Scansione carta non disponibile.");
        f1189a.put(ap.CANCEL, "Annulla");
        f1189a.put(ap.CARDTYPE_AMERICANEXPRESS, "American Express");
        f1189a.put(ap.CARDTYPE_DISCOVER, "Discover");
        f1189a.put(ap.CARDTYPE_JCB, "JCB");
        f1189a.put(ap.CARDTYPE_MASTERCARD, "MasterCard");
        f1189a.put(ap.CARDTYPE_VISA, "Visa");
        f1189a.put(ap.DONE, "Fine");
        f1189a.put(ap.ENTRY_CVV, "CVV");
        f1189a.put(ap.ENTRY_POSTAL_CODE, "Codice postale");
        f1189a.put(ap.ENTRY_EXPIRES, "Scadenza");
        f1189a.put(ap.ENTRY_NUMBER, "Numero");
        f1189a.put(ap.ENTRY_TITLE, "Carta");
        f1189a.put(ap.EXPIRES_PLACEHOLDER, "MM/AA");
        f1189a.put(ap.OK, "OK");
        f1189a.put(ap.SCAN_GUIDE, "Inquadra la carta.\nLa scansione \u00e8 automatica.");
        f1189a.put(ap.KEYBOARD, "Tastiera\u2026");
        f1189a.put(ap.ENTRY_CARD_NUMBER, "Numero di carta");
        f1189a.put(ap.MANUAL_ENTRY_TITLE, "Dati carta");
        f1189a.put(ap.WHOOPS, "Oops!");
        f1189a.put(ap.ERROR_NO_DEVICE_SUPPORT, "La fotocamera non legge il numero di carta.");
        f1189a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "Fotocamera non disponibile.");
        f1189a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "Errore inatteso nell\u2019apertura della fotocamera.");
    }

    public final String m1148a() {
        return "it";
    }

    public final /* synthetic */ String m1149a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1190b.containsKey(str2) ? (String) f1190b.get(str2) : (String) f1189a.get(apVar);
    }
}
