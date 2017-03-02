package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.B */
public final class C0260B implements C0167k {
    private static Map f1109a;
    private static Map f1110b;

    static {
        f1109a = new HashMap();
        f1110b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0260B() {
        f1109a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "Denne applikasjonen er ikke godkjent for kortskanning.");
        f1109a.put(ap.CANCEL, "Avbryt");
        f1109a.put(ap.CARDTYPE_AMERICANEXPRESS, "American Express");
        f1109a.put(ap.CARDTYPE_DISCOVER, "Discover");
        f1109a.put(ap.CARDTYPE_JCB, "JCB");
        f1109a.put(ap.CARDTYPE_MASTERCARD, "MasterCard");
        f1109a.put(ap.CARDTYPE_VISA, "Visa");
        f1109a.put(ap.DONE, "Fullf\u00f8rt");
        f1109a.put(ap.ENTRY_CVV, "CVV");
        f1109a.put(ap.ENTRY_POSTAL_CODE, "Postnummer");
        f1109a.put(ap.ENTRY_EXPIRES, "Utl\u00f8per");
        f1109a.put(ap.ENTRY_NUMBER, "Nummer");
        f1109a.put(ap.ENTRY_TITLE, "Kort");
        f1109a.put(ap.EXPIRES_PLACEHOLDER, "MM/\u00c5\u00c5");
        f1109a.put(ap.OK, "OK");
        f1109a.put(ap.SCAN_GUIDE, "Hold kortet her.\nDet skannes automatisk.");
        f1109a.put(ap.KEYBOARD, "Tastatur \u2026");
        f1109a.put(ap.ENTRY_CARD_NUMBER, "Kortnummer");
        f1109a.put(ap.MANUAL_ENTRY_TITLE, "Kortdetaljer");
        f1109a.put(ap.WHOOPS, "Ups!");
        f1109a.put(ap.ERROR_NO_DEVICE_SUPPORT, "Denne enheten kan ikke bruke kameraet til \u00e5 lese kortnumre.");
        f1109a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "Kameraet er utilgjengelig.");
        f1109a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "Det oppstod en uventet feil ved kameraoppstart.");
    }

    public final String m1063a() {
        return "nb";
    }

    public final /* synthetic */ String m1064a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1110b.containsKey(str2) ? (String) f1110b.get(str2) : (String) f1109a.get(apVar);
    }
}
