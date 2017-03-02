package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.m */
public final class C0278m implements C0167k {
    private static Map f1169a;
    private static Map f1170b;

    static {
        f1169a = new HashMap();
        f1170b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0278m() {
        f1169a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "Denne app er ikke godkendt til scanning af kort.");
        f1169a.put(ap.CANCEL, "Annuller");
        f1169a.put(ap.CARDTYPE_AMERICANEXPRESS, "American Express");
        f1169a.put(ap.CARDTYPE_DISCOVER, "Discover");
        f1169a.put(ap.CARDTYPE_JCB, "JCB");
        f1169a.put(ap.CARDTYPE_MASTERCARD, "MasterCard");
        f1169a.put(ap.CARDTYPE_VISA, "Visa");
        f1169a.put(ap.DONE, "Udf\u00f8rt");
        f1169a.put(ap.ENTRY_CVV, "CVV");
        f1169a.put(ap.ENTRY_POSTAL_CODE, "Postnummer");
        f1169a.put(ap.ENTRY_EXPIRES, "Udl\u00f8ber");
        f1169a.put(ap.ENTRY_NUMBER, "Nummer");
        f1169a.put(ap.ENTRY_TITLE, "Kort");
        f1169a.put(ap.EXPIRES_PLACEHOLDER, "MM/\u00c5\u00c5");
        f1169a.put(ap.OK, "OK");
        f1169a.put(ap.SCAN_GUIDE, "Hold kortet her.\nDet scannes automatisk.");
        f1169a.put(ap.KEYBOARD, "Tastatur\u2026");
        f1169a.put(ap.ENTRY_CARD_NUMBER, "Kortnummer");
        f1169a.put(ap.MANUAL_ENTRY_TITLE, "Kortoplysninger");
        f1169a.put(ap.WHOOPS, "Whoops!");
        f1169a.put(ap.ERROR_NO_DEVICE_SUPPORT, "Denne enhed kan ikke anvende kameraet til at l\u00e6se kortnumre.");
        f1169a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "Enhed kamera ikke er tilg\u00e6ngelig.");
        f1169a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "Enheden havde en uventet fejl under \u00e5bning af kamera.");
    }

    public final String m1128a() {
        return "da";
    }

    public final /* synthetic */ String m1129a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1170b.containsKey(str2) ? (String) f1170b.get(str2) : (String) f1169a.get(apVar);
    }
}
