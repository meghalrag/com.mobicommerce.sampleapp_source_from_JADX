package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.o */
public final class C0280o implements C0167k {
    private static Map f1173a;
    private static Map f1174b;

    static {
        f1173a = new HashMap();
        f1174b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0280o() {
        f1173a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "This application is not authorized for card scanning.");
        f1173a.put(ap.CANCEL, "Cancel");
        f1173a.put(ap.CARDTYPE_AMERICANEXPRESS, "American Express");
        f1173a.put(ap.CARDTYPE_DISCOVER, "Discover");
        f1173a.put(ap.CARDTYPE_JCB, "JCB");
        f1173a.put(ap.CARDTYPE_MASTERCARD, "MasterCard");
        f1173a.put(ap.CARDTYPE_VISA, "Visa");
        f1173a.put(ap.DONE, "Done");
        f1173a.put(ap.ENTRY_CVV, "CVV");
        f1173a.put(ap.ENTRY_POSTAL_CODE, "Postal Code");
        f1173a.put(ap.ENTRY_EXPIRES, "Expires");
        f1173a.put(ap.ENTRY_NUMBER, "Number");
        f1173a.put(ap.ENTRY_TITLE, "Card");
        f1173a.put(ap.EXPIRES_PLACEHOLDER, "MM/YY");
        f1173a.put(ap.OK, "OK");
        f1173a.put(ap.SCAN_GUIDE, "Hold card here.\nIt will scan automatically.");
        f1173a.put(ap.KEYBOARD, "Keyboard\u2026");
        f1173a.put(ap.ENTRY_CARD_NUMBER, "Card Number");
        f1173a.put(ap.MANUAL_ENTRY_TITLE, "Card Details");
        f1173a.put(ap.WHOOPS, "Whoops!");
        f1173a.put(ap.ERROR_NO_DEVICE_SUPPORT, "This device cannot use the camera to read card numbers.");
        f1173a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "Device camera is unavailable.");
        f1173a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "The device had an unexpected error opening the camera.");
    }

    public final String m1132a() {
        return "en";
    }

    public final /* synthetic */ String m1133a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1174b.containsKey(str2) ? (String) f1174b.get(str2) : (String) f1173a.get(apVar);
    }
}
