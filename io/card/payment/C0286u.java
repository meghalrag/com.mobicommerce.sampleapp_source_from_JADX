package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.u */
public final class C0286u implements C0167k {
    private static Map f1185a;
    private static Map f1186b;

    static {
        f1185a = new HashMap();
        f1186b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0286u() {
        f1185a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "Cette application n\u2019est pas autoris\u00e9e \u00e0 scanner la carte.");
        f1185a.put(ap.CANCEL, "Annuler");
        f1185a.put(ap.CARDTYPE_AMERICANEXPRESS, "American Express");
        f1185a.put(ap.CARDTYPE_DISCOVER, "Discover");
        f1185a.put(ap.CARDTYPE_JCB, "JCB");
        f1185a.put(ap.CARDTYPE_MASTERCARD, "MasterCard");
        f1185a.put(ap.CARDTYPE_VISA, "Visa");
        f1185a.put(ap.DONE, "OK");
        f1185a.put(ap.ENTRY_CVV, "Crypto.");
        f1185a.put(ap.ENTRY_POSTAL_CODE, "Code postal");
        f1185a.put(ap.ENTRY_EXPIRES, "Date d\u2019expiration");
        f1185a.put(ap.ENTRY_NUMBER, "Num\u00e9ro");
        f1185a.put(ap.ENTRY_TITLE, "Carte");
        f1185a.put(ap.EXPIRES_PLACEHOLDER, "MM/AA");
        f1185a.put(ap.OK, "OK");
        f1185a.put(ap.SCAN_GUIDE, "Maintenez la carte \u00e0 cet endroit.\nElle va \u00eatre automatiquement scann\u00e9e.");
        f1185a.put(ap.KEYBOARD, "Clavier\u2026");
        f1185a.put(ap.ENTRY_CARD_NUMBER, "N\u00ba de carte");
        f1185a.put(ap.MANUAL_ENTRY_TITLE, "Carte");
        f1185a.put(ap.WHOOPS, "D\u00e9sol\u00e9");
        f1185a.put(ap.ERROR_NO_DEVICE_SUPPORT, "Cet appareil ne peut pas utiliser l\u2019appareil photo pour lire les num\u00e9ros de carte.");
        f1185a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "L\u2019appareil photo n\u2019est pas disponible.");
        f1185a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "Une erreur s\u2019est produite en ouvrant l\u2019appareil photo.");
    }

    public final String m1144a() {
        return "fr";
    }

    public final /* synthetic */ String m1145a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1186b.containsKey(str2) ? (String) f1186b.get(str2) : (String) f1185a.get(apVar);
    }
}
