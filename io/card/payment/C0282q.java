package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.q */
public final class C0282q implements C0167k {
    private static Map f1177a;
    private static Map f1178b;

    static {
        f1177a = new HashMap();
        f1178b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0282q() {
        f1177a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "This application is not authorised for card scanning.");
        f1177a.put(ap.CANCEL, "Cancel");
        f1177a.put(ap.CARDTYPE_AMERICANEXPRESS, "American Express");
        f1177a.put(ap.CARDTYPE_DISCOVER, "Discover");
        f1177a.put(ap.CARDTYPE_JCB, "JCB");
        f1177a.put(ap.CARDTYPE_MASTERCARD, "MasterCard");
        f1177a.put(ap.CARDTYPE_VISA, "Visa");
        f1177a.put(ap.DONE, "Done");
        f1177a.put(ap.ENTRY_CVV, "CVV");
        f1177a.put(ap.ENTRY_POSTAL_CODE, "Postcode");
        f1177a.put(ap.ENTRY_EXPIRES, "Expires");
        f1177a.put(ap.ENTRY_NUMBER, "Number");
        f1177a.put(ap.ENTRY_TITLE, "Card");
        f1177a.put(ap.EXPIRES_PLACEHOLDER, "MM/YY");
        f1177a.put(ap.OK, "OK");
        f1177a.put(ap.SCAN_GUIDE, "Hold card here.\nIt will scan automatically.");
        f1177a.put(ap.KEYBOARD, "Keyboard\u2026");
        f1177a.put(ap.ENTRY_CARD_NUMBER, "Card Number");
        f1177a.put(ap.MANUAL_ENTRY_TITLE, "Card Details");
        f1177a.put(ap.WHOOPS, "Whoops!");
        f1177a.put(ap.ERROR_NO_DEVICE_SUPPORT, "This device cannot use the camera to read card numbers.");
        f1177a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "Device camera is unavailable.");
        f1177a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "The device had an unexpected error opening the camera.");
    }

    public final String m1136a() {
        return "en_GB";
    }

    public final /* synthetic */ String m1137a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1178b.containsKey(str2) ? (String) f1178b.get(str2) : (String) f1177a.get(apVar);
    }
}
