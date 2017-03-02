package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.D */
public final class C0262D implements C0167k {
    private static Map f1135a;
    private static Map f1136b;

    static {
        f1135a = new HashMap();
        f1136b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0262D() {
        f1135a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "Ta aplikacja nie jest autoryzowana do skanowania karty.");
        f1135a.put(ap.CANCEL, "Anuluj");
        f1135a.put(ap.CARDTYPE_AMERICANEXPRESS, "American Express");
        f1135a.put(ap.CARDTYPE_DISCOVER, "Discover");
        f1135a.put(ap.CARDTYPE_JCB, "JCB");
        f1135a.put(ap.CARDTYPE_MASTERCARD, "MasterCard");
        f1135a.put(ap.CARDTYPE_VISA, "Visa");
        f1135a.put(ap.DONE, "Gotowe");
        f1135a.put(ap.ENTRY_CVV, "Kod CVV2/CVC2");
        f1135a.put(ap.ENTRY_POSTAL_CODE, "Kod pocztowy");
        f1135a.put(ap.ENTRY_EXPIRES, "Wygasa");
        f1135a.put(ap.ENTRY_NUMBER, "Numer");
        f1135a.put(ap.ENTRY_TITLE, "Karta");
        f1135a.put(ap.EXPIRES_PLACEHOLDER, "MM/RR");
        f1135a.put(ap.OK, "OK");
        f1135a.put(ap.SCAN_GUIDE, "Przytrzymaj kart\u0119 tutaj.\nZostanie ona zeskanowana automatycznie.");
        f1135a.put(ap.KEYBOARD, "Klawiatura\u2026");
        f1135a.put(ap.ENTRY_CARD_NUMBER, "Numer karty");
        f1135a.put(ap.MANUAL_ENTRY_TITLE, "Dane karty");
        f1135a.put(ap.WHOOPS, "Ups!");
        f1135a.put(ap.ERROR_NO_DEVICE_SUPPORT, "Na tym urz\u0105dzeniu nie mo\u017cna odczyta\u0107 numeru karty za pomoc\u0105 aparatu.");
        f1135a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "Aparat na tym urz\u0105dzeniu jest niedostepny.");
        f1135a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "Przy otwieraniu aparatu na tym urz\u0105dzeniu wyst\u0105pi\u0142 nieoczekiwany b\u0142\u0105d.");
    }

    public final String m1087a() {
        return "pl";
    }

    public final /* synthetic */ String m1088a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1136b.containsKey(str2) ? (String) f1136b.get(str2) : (String) f1135a.get(apVar);
    }
}
