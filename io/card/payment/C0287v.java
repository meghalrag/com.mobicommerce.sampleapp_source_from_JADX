package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.v */
public final class C0287v implements C0167k {
    private static Map f1187a;
    private static Map f1188b;

    static {
        f1187a = new HashMap();
        f1188b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0287v() {
        f1187a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "\u05dc\u05d0 \u05d4\u05d5\u05e2\u05e0\u05e7 \u05dc\u05d9\u05d9\u05e9\u05d5\u05dd \u05d6\u05d4 \u05d0\u05d9\u05e9\u05d5\u05e8 \u05dc\u05e1\u05e8\u05d9\u05e7\u05ea \u05db\u05e8\u05d8\u05d9\u05e1\u05d9\u05dd.");
        f1187a.put(ap.CANCEL, "\u05d1\u05d9\u05d8\u05d5\u05dc");
        f1187a.put(ap.CARDTYPE_AMERICANEXPRESS, "\u05d0\u05de\u05e8\u05d9\u05e7\u05df \u05d0\u05e7\u05e1\u05e4\u05e8\u05e1");
        f1187a.put(ap.CARDTYPE_DISCOVER, "Discover\u200f");
        f1187a.put(ap.CARDTYPE_JCB, "JCB\u200f");
        f1187a.put(ap.CARDTYPE_MASTERCARD, "\u05de\u05d0\u05e1\u05d8\u05e8\u05e7\u05d0\u05e8\u05d3");
        f1187a.put(ap.CARDTYPE_VISA, "\u05d5\u05d9\u05d6\u05d4");
        f1187a.put(ap.DONE, "\u05d1\u05d5\u05e6\u05e2");
        f1187a.put(ap.ENTRY_CVV, "\u05e7\u05d5\u05d3 \u05d0\u05d9\u05de\u05d5\u05ea \u05db\u05e8\u05d8\u05d9\u05e1");
        f1187a.put(ap.ENTRY_POSTAL_CODE, "\u05de\u05d9\u05e7\u05d5\u05d3");
        f1187a.put(ap.ENTRY_EXPIRES, "\u05ea\u05d0\u05e8\u05d9\u05da \u05ea\u05e4\u05d5\u05d2\u05d4");
        f1187a.put(ap.ENTRY_NUMBER, "\u05de\u05e1\u05e4\u05e8");
        f1187a.put(ap.ENTRY_TITLE, "\u05db\u05e8\u05d8\u05d9\u05e1");
        f1187a.put(ap.EXPIRES_PLACEHOLDER, "MM/YY\u200f");
        f1187a.put(ap.OK, "\u05d0\u05d9\u05e9\u05d5\u05e8");
        f1187a.put(ap.SCAN_GUIDE, "\u05d4\u05d7\u05d6\u05e7 \u05d0\u05ea \u05d4\u05db\u05e8\u05d8\u05d9\u05e1 \u05db\u05d0\u05df.\n\u05d4\u05e1\u05e8\u05d9\u05e7\u05d4 \u05ea\u05ea\u05d1\u05e6\u05e2 \u05d1\u05d0\u05d5\u05e4\u05df \u05d0\u05d5\u05d8\u05d5\u05de\u05d8\u05d9.");
        f1187a.put(ap.KEYBOARD, "\u05de\u05e7\u05dc\u05d3\u05ea\u2026");
        f1187a.put(ap.ENTRY_CARD_NUMBER, "\u05de\u05e1\u05e4\u05e8 \u05db\u05e8\u05d8\u05d9\u05e1");
        f1187a.put(ap.MANUAL_ENTRY_TITLE, "\u05e4\u05e8\u05d8\u05d9 \u05db\u05e8\u05d8\u05d9\u05e1");
        f1187a.put(ap.WHOOPS, "\u05d0\u05d5\u05e4\u05e1!");
        f1187a.put(ap.ERROR_NO_DEVICE_SUPPORT, "\u05d4\u05de\u05db\u05e9\u05d9\u05e8 \u05d0\u05d9\u05e0\u05d5 \u05de\u05e1\u05d5\u05d2\u05dc \u05dc\u05d4\u05e9\u05ea\u05de\u05e9 \u05d1\u05de\u05e6\u05dc\u05de\u05d4 \u05dc\u05e7\u05e8\u05d9\u05d0\u05ea \u05de\u05e1\u05e4\u05e8\u05d9 \u05db\u05e8\u05d8\u05d9\u05e1.");
        f1187a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "\u05de\u05e6\u05dc\u05de\u05ea \u05d4\u05de\u05db\u05e9\u05d9\u05e8 \u05d0\u05d9\u05e0\u05d4 \u05d6\u05de\u05d9\u05e0\u05d4.");
        f1187a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "\u05d4\u05de\u05db\u05e9\u05d9\u05e8 \u05e0\u05ea\u05e7\u05dc \u05d1\u05e9\u05d2\u05d9\u05d0\u05d4 \u05d1\u05dc\u05ea\u05d9 \u05e6\u05e4\u05d5\u05d9\u05d4 \u05d1\u05d6\u05de\u05df \u05d4\u05e4\u05e2\u05dc\u05ea \u05d4\u05de\u05e6\u05dc\u05de\u05d4.");
    }

    public final String m1146a() {
        return "he";
    }

    public final /* synthetic */ String m1147a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1188b.containsKey(str2) ? (String) f1188b.get(str2) : (String) f1187a.get(apVar);
    }
}
