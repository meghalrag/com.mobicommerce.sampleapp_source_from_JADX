package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.C */
public final class C0261C implements C0167k {
    private static Map f1111a;
    private static Map f1112b;

    static {
        f1111a = new HashMap();
        f1112b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0261C() {
        f1111a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "Deze toepassing is niet goedgekeurd voor het scannen van creditcards.");
        f1111a.put(ap.CANCEL, "Annuleren");
        f1111a.put(ap.CARDTYPE_AMERICANEXPRESS, "American Express");
        f1111a.put(ap.CARDTYPE_DISCOVER, "Discover");
        f1111a.put(ap.CARDTYPE_JCB, "JCB");
        f1111a.put(ap.CARDTYPE_MASTERCARD, "MasterCard");
        f1111a.put(ap.CARDTYPE_VISA, "Visa");
        f1111a.put(ap.DONE, "Gereed");
        f1111a.put(ap.ENTRY_CVV, "CVV");
        f1111a.put(ap.ENTRY_POSTAL_CODE, "Postcode");
        f1111a.put(ap.ENTRY_EXPIRES, "Vervaldatum");
        f1111a.put(ap.ENTRY_NUMBER, "Nummer");
        f1111a.put(ap.ENTRY_TITLE, "Creditcard");
        f1111a.put(ap.EXPIRES_PLACEHOLDER, "MM/JJ");
        f1111a.put(ap.OK, "OK");
        f1111a.put(ap.SCAN_GUIDE, "Houd kaart hier.\nScannen gaat automatisch.");
        f1111a.put(ap.KEYBOARD, "Toetsenbord\u2026");
        f1111a.put(ap.ENTRY_CARD_NUMBER, "Creditcardnummer");
        f1111a.put(ap.MANUAL_ENTRY_TITLE, "Kaartgegevens");
        f1111a.put(ap.WHOOPS, "Oeps!");
        f1111a.put(ap.ERROR_NO_DEVICE_SUPPORT, "Met de camera van dit apparaat kunnen geen kaartnummers worden gelezen.");
        f1111a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "Camera apparaat niet beschikbaar.");
        f1111a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "Er is een onverwachte fout opgetreden bij het starten van de camera.");
    }

    public final String m1065a() {
        return "nl";
    }

    public final /* synthetic */ String m1066a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1112b.containsKey(str2) ? (String) f1112b.get(str2) : (String) f1111a.get(apVar);
    }
}
