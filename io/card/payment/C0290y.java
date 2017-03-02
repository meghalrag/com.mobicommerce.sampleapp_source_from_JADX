package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.y */
public final class C0290y implements C0167k {
    private static Map f1193a;
    private static Map f1194b;

    static {
        f1193a = new HashMap();
        f1194b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0290y() {
        f1193a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "\uce74\ub4dc\ub97c \uc2a4\uce94\ud560 \uc218 \uc788\ub294 \uc560\ud50c\ub9ac\ucf00\uc774\uc158\uc774 \uc544\ub2d9\ub2c8\ub2e4.");
        f1193a.put(ap.CANCEL, "\ucde8\uc18c");
        f1193a.put(ap.CARDTYPE_AMERICANEXPRESS, "American Express");
        f1193a.put(ap.CARDTYPE_DISCOVER, "Discover");
        f1193a.put(ap.CARDTYPE_JCB, "JCB");
        f1193a.put(ap.CARDTYPE_MASTERCARD, "MasterCard");
        f1193a.put(ap.CARDTYPE_VISA, "Visa");
        f1193a.put(ap.DONE, "\uc644\ub8cc");
        f1193a.put(ap.ENTRY_CVV, "CVV");
        f1193a.put(ap.ENTRY_POSTAL_CODE, "\uc6b0\ud3b8\ubc88\ud638");
        f1193a.put(ap.ENTRY_EXPIRES, "\uc720\ud6a8\uae30\uac04");
        f1193a.put(ap.ENTRY_NUMBER, "\ubc88\ud638");
        f1193a.put(ap.ENTRY_TITLE, "\uce74\ub4dc");
        f1193a.put(ap.EXPIRES_PLACEHOLDER, "MM / YY");
        f1193a.put(ap.OK, "\ud655\uc778");
        f1193a.put(ap.SCAN_GUIDE, "\uce74\ub4dc\ub97c \uc5ec\uae30\uc5d0 \uac16\ub2e4 \ub300\uc138\uc694.\n\uc790\ub3d9\uc73c\ub85c \uc2a4\uce94\ub429\ub2c8\ub2e4.");
        f1193a.put(ap.KEYBOARD, "\ud0a4\ubcf4\ub4dc\u2026");
        f1193a.put(ap.ENTRY_CARD_NUMBER, "\uce74\ub4dc \ubc88\ud638");
        f1193a.put(ap.MANUAL_ENTRY_TITLE, "\uce74\ub4dc \uc138\ubd80\uc815\ubcf4");
        f1193a.put(ap.WHOOPS, "\uc774\ub7f0!");
        f1193a.put(ap.ERROR_NO_DEVICE_SUPPORT, "\uae30\uae30\uac00 \uce74\uba54\ub77c\ub97c \uc774\uc6a9\ud55c \uce74\ub4dc \uc22b\uc790 \ud310\ub3c5\uc744 \uc9c0\uc6d0\ud558\uc9c0 \uc54a\uc2b5\ub2c8\ub2e4.");
        f1193a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "\uae30\uae30\uc5d0\uc11c \uce74\uba54\ub77c\ub97c \uc0ac\uc6a9\ud560 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4.");
        f1193a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "\uae30\uae30\uc5d0\uc11c \uce74\uba54\ub77c\ub97c \uc5ec\ub294 \ub3d9\uc548 \uc608\uc0c1\uce58 \ubabb\ud55c \uc624\ub958\uac00 \ubc1c\uc0dd\ud588\uc2b5\ub2c8\ub2e4.");
    }

    public final String m1152a() {
        return "ko";
    }

    public final /* synthetic */ String m1153a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1194b.containsKey(str2) ? (String) f1194b.get(str2) : (String) f1193a.get(apVar);
    }
}
