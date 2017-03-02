package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.I */
public final class C0267I implements C0167k {
    private static Map f1145a;
    private static Map f1146b;

    static {
        f1145a = new HashMap();
        f1146b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0267I() {
        f1145a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "\u0e41\u0e2d\u0e1b\u0e1e\u0e25\u0e34\u0e40\u0e04\u0e0a\u0e31\u0e19\u0e19\u0e35\u0e49\u0e44\u0e21\u0e48\u0e44\u0e14\u0e49\u0e23\u0e31\u0e1a\u0e2d\u0e19\u0e38\u0e0d\u0e32\u0e15\u0e43\u0e2b\u0e49\u0e2a\u0e41\u0e01\u0e19\u0e1a\u0e31\u0e15\u0e23");
        f1145a.put(ap.CANCEL, "\u0e22\u0e01\u0e40\u0e25\u0e34\u0e01");
        f1145a.put(ap.CARDTYPE_AMERICANEXPRESS, "American Express");
        f1145a.put(ap.CARDTYPE_DISCOVER, "Discover");
        f1145a.put(ap.CARDTYPE_JCB, "JCB");
        f1145a.put(ap.CARDTYPE_MASTERCARD, "MasterCard");
        f1145a.put(ap.CARDTYPE_VISA, "Visa");
        f1145a.put(ap.DONE, "\u0e40\u0e2a\u0e23\u0e47\u0e08\u0e41\u0e25\u0e49\u0e27");
        f1145a.put(ap.ENTRY_CVV, "CVV");
        f1145a.put(ap.ENTRY_POSTAL_CODE, "\u0e23\u0e2b\u0e31\u0e2a\u0e44\u0e1b\u0e23\u0e29\u0e13\u0e35\u0e22\u0e4c");
        f1145a.put(ap.ENTRY_EXPIRES, "\u0e2b\u0e21\u0e14\u0e2d\u0e32\u0e22\u0e38");
        f1145a.put(ap.ENTRY_NUMBER, "\u0e2b\u0e21\u0e32\u0e22\u0e40\u0e25\u0e02");
        f1145a.put(ap.ENTRY_TITLE, "\u0e1a\u0e31\u0e15\u0e23");
        f1145a.put(ap.EXPIRES_PLACEHOLDER, "\u0e14\u0e14/\u0e1b\u0e1b");
        f1145a.put(ap.OK, "\u0e15\u0e01\u0e25\u0e07");
        f1145a.put(ap.SCAN_GUIDE, "\u0e16\u0e37\u0e2d\u0e1a\u0e31\u0e15\u0e23\u0e44\u0e27\u0e49\u0e15\u0e23\u0e07\u0e19\u0e35\u0e49\n\u0e40\u0e04\u0e23\u0e37\u0e48\u0e2d\u0e07\u0e08\u0e30\u0e2a\u0e41\u0e01\u0e19\u0e42\u0e14\u0e22\u0e2d\u0e31\u0e15\u0e42\u0e19\u0e21\u0e31\u0e15\u0e34");
        f1145a.put(ap.KEYBOARD, "\u0e04\u0e35\u0e22\u0e4c\u0e1a\u0e2d\u0e23\u0e4c\u0e14\u2026");
        f1145a.put(ap.ENTRY_CARD_NUMBER, "\u0e2b\u0e21\u0e32\u0e22\u0e40\u0e25\u0e02\u0e1a\u0e31\u0e15\u0e23");
        f1145a.put(ap.MANUAL_ENTRY_TITLE, "\u0e23\u0e32\u0e22\u0e25\u0e30\u0e40\u0e2d\u0e35\u0e22\u0e14\u0e1a\u0e31\u0e15\u0e23");
        f1145a.put(ap.WHOOPS, "\u0e02\u0e2d\u0e2d\u0e20\u0e31\u0e22\u0e04\u0e48\u0e30");
        f1145a.put(ap.ERROR_NO_DEVICE_SUPPORT, "\u0e2d\u0e38\u0e1b\u0e01\u0e23\u0e13\u0e4c\u0e44\u0e21\u0e48\u0e2a\u0e32\u0e21\u0e32\u0e23\u0e16\u0e43\u0e0a\u0e49\u0e01\u0e25\u0e49\u0e2d\u0e07\u0e40\u0e1e\u0e37\u0e48\u0e2d\u0e2d\u0e48\u0e32\u0e19\u0e2b\u0e21\u0e32\u0e22\u0e40\u0e25\u0e02\u0e1a\u0e31\u0e15\u0e23\u0e44\u0e14\u0e49");
        f1145a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "\u0e01\u0e25\u0e49\u0e2d\u0e07\u0e02\u0e2d\u0e07\u0e2d\u0e38\u0e1b\u0e01\u0e23\u0e13\u0e4c\u0e44\u0e21\u0e48\u0e1e\u0e23\u0e49\u0e2d\u0e21\u0e43\u0e0a\u0e49\u0e07\u0e32\u0e19");
        f1145a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "\u0e2d\u0e38\u0e1b\u0e01\u0e23\u0e13\u0e4c\u0e1e\u0e1a\u0e02\u0e49\u0e2d\u0e1c\u0e34\u0e14\u0e1e\u0e25\u0e32\u0e14\u0e02\u0e13\u0e30\u0e40\u0e1b\u0e34\u0e14\u0e01\u0e25\u0e49\u0e2d\u0e07");
    }

    public final String m1097a() {
        return "th";
    }

    public final /* synthetic */ String m1098a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1146b.containsKey(str2) ? (String) f1146b.get(str2) : (String) f1145a.get(apVar);
    }
}
