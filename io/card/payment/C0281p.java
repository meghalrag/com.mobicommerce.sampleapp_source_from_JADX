package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.p */
public final class C0281p implements C0167k {
    private static Map f1175a;
    private static Map f1176b;

    static {
        f1175a = new HashMap();
        f1176b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0281p() {
        f1175a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "This application is not authorised for card scanning.");
        f1175a.put(ap.CANCEL, "Cancel");
        f1175a.put(ap.CARDTYPE_AMERICANEXPRESS, "American Express");
        f1175a.put(ap.CARDTYPE_DISCOVER, "Discover");
        f1175a.put(ap.CARDTYPE_JCB, "JCB");
        f1175a.put(ap.CARDTYPE_MASTERCARD, "MasterCard");
        f1175a.put(ap.CARDTYPE_VISA, "Visa");
        f1175a.put(ap.DONE, "Done");
        f1175a.put(ap.ENTRY_CVV, "CVV");
        f1175a.put(ap.ENTRY_POSTAL_CODE, "Postcode");
        f1175a.put(ap.ENTRY_EXPIRES, "Expires");
        f1175a.put(ap.ENTRY_NUMBER, "Number");
        f1175a.put(ap.ENTRY_TITLE, "Card");
        f1175a.put(ap.EXPIRES_PLACEHOLDER, "MM/YY");
        f1175a.put(ap.OK, "OK");
        f1175a.put(ap.SCAN_GUIDE, "Hold card here.\nIt will scan automatically.");
        f1175a.put(ap.KEYBOARD, "Keyboard\u2026");
        f1175a.put(ap.ENTRY_CARD_NUMBER, "Card Number");
        f1175a.put(ap.MANUAL_ENTRY_TITLE, "Card Details");
        f1175a.put(ap.WHOOPS, "Whoops!");
        f1175a.put(ap.ERROR_NO_DEVICE_SUPPORT, "This device cannot use the camera to read card numbers.");
        f1175a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "Device camera is unavailable.");
        f1175a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "The device had an unexpected error opening the camera.");
    }

    public final String m1134a() {
        return "en_AU";
    }

    public final /* synthetic */ String m1135a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1176b.containsKey(str2) ? (String) f1176b.get(str2) : (String) f1175a.get(apVar);
    }
}
