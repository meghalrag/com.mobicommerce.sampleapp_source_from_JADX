package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.A */
public final class C0259A implements C0167k {
    private static Map f1107a;
    private static Map f1108b;

    static {
        f1107a = new HashMap();
        f1108b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0259A() {
        f1107a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "Aplikasi ini tidak dibenarkan untuk pengimbasan kad.");
        f1107a.put(ap.CANCEL, "Batal");
        f1107a.put(ap.CARDTYPE_AMERICANEXPRESS, "American Express");
        f1107a.put(ap.CARDTYPE_DISCOVER, "Discover");
        f1107a.put(ap.CARDTYPE_JCB, "JCB");
        f1107a.put(ap.CARDTYPE_MASTERCARD, "MasterCard");
        f1107a.put(ap.CARDTYPE_VISA, "Visa");
        f1107a.put(ap.DONE, "Selesai");
        f1107a.put(ap.ENTRY_CVV, "CVV");
        f1107a.put(ap.ENTRY_POSTAL_CODE, "Poskod");
        f1107a.put(ap.ENTRY_EXPIRES, "Luput");
        f1107a.put(ap.ENTRY_NUMBER, "Nombor");
        f1107a.put(ap.ENTRY_TITLE, "Kad");
        f1107a.put(ap.EXPIRES_PLACEHOLDER, "BB/TT");
        f1107a.put(ap.OK, "OK");
        f1107a.put(ap.SCAN_GUIDE, "Pegang kad di sini.\nIa akan mengimbas secara automatik.");
        f1107a.put(ap.KEYBOARD, "Papan Kekunci\u2026");
        f1107a.put(ap.ENTRY_CARD_NUMBER, "Nombor Kad");
        f1107a.put(ap.MANUAL_ENTRY_TITLE, "Butiran Kad");
        f1107a.put(ap.WHOOPS, "Oop!");
        f1107a.put(ap.ERROR_NO_DEVICE_SUPPORT, "Peranti ini tidak dapat menggunakan kamera untuk membaca nombor kad.");
        f1107a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "Kamera peranti tidak tersedia.");
        f1107a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "Peranti mengalami ralat tidak dijangka semasa membuka kamera.");
    }

    public final String m1061a() {
        return "ms";
    }

    public final /* synthetic */ String m1062a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1108b.containsKey(str2) ? (String) f1108b.get(str2) : (String) f1107a.get(apVar);
    }
}
