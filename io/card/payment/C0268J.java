package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.J */
public final class C0268J implements C0167k {
    private static Map f1147a;
    private static Map f1148b;

    static {
        f1147a = new HashMap();
        f1148b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0268J() {
        f1147a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "Bu uygulama, kart tarama i\u00e7in yetkilendirilmedi.");
        f1147a.put(ap.CANCEL, "\u0130ptal");
        f1147a.put(ap.CARDTYPE_AMERICANEXPRESS, "American Express");
        f1147a.put(ap.CARDTYPE_DISCOVER, "Discover");
        f1147a.put(ap.CARDTYPE_JCB, "JCB");
        f1147a.put(ap.CARDTYPE_MASTERCARD, "MasterCard");
        f1147a.put(ap.CARDTYPE_VISA, "Visa");
        f1147a.put(ap.DONE, "Bitti");
        f1147a.put(ap.ENTRY_CVV, "CVV");
        f1147a.put(ap.ENTRY_POSTAL_CODE, "Posta Kodu");
        f1147a.put(ap.ENTRY_EXPIRES, "Son kullanma tarihi");
        f1147a.put(ap.ENTRY_NUMBER, "Numara");
        f1147a.put(ap.ENTRY_TITLE, "Kart");
        f1147a.put(ap.EXPIRES_PLACEHOLDER, "AA/YY");
        f1147a.put(ap.OK, "Tamam");
        f1147a.put(ap.SCAN_GUIDE, "Kart\u0131n\u0131z\u0131 buraya tutun.\nOtomatik olarak taranacakt\u0131r.");
        f1147a.put(ap.KEYBOARD, "Klavye\u2026");
        f1147a.put(ap.ENTRY_CARD_NUMBER, "Kart Numaras\u0131");
        f1147a.put(ap.MANUAL_ENTRY_TITLE, "Kart Ayr\u0131nt\u0131lar\u0131");
        f1147a.put(ap.WHOOPS, "Pardon!");
        f1147a.put(ap.ERROR_NO_DEVICE_SUPPORT, "Bu cihaz\u0131n kameras\u0131 kart rakamlar\u0131n\u0131 okuyamaz.");
        f1147a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "Cihaz kameras\u0131 kullan\u0131lam\u0131yor.");
        f1147a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "Cihaz kameray\u0131 a\u00e7arken beklenmedik bir hata verdi.");
    }

    public final String m1099a() {
        return "tr";
    }

    public final /* synthetic */ String m1100a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1148b.containsKey(str2) ? (String) f1148b.get(str2) : (String) f1147a.get(apVar);
    }
}
