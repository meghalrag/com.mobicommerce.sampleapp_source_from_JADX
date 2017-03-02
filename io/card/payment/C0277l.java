package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.l */
public final class C0277l implements C0167k {
    private static Map f1167a;
    private static Map f1168b;

    static {
        f1167a = new HashMap();
        f1168b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0277l() {
        f1167a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "\u0647\u0630\u0627 \u0627\u0644\u062a\u0637\u0628\u064a\u0642 \u063a\u064a\u0631 \u0645\u0639\u062a\u0645\u062f \u0644\u0645\u0633\u062d \u0627\u0644\u0628\u0637\u0627\u0642\u0629.");
        f1167a.put(ap.CANCEL, "\u0625\u0644\u063a\u0627\u0621");
        f1167a.put(ap.CARDTYPE_AMERICANEXPRESS, "American Express\u200f");
        f1167a.put(ap.CARDTYPE_DISCOVER, "Discover\u200f");
        f1167a.put(ap.CARDTYPE_JCB, "JCB\u200f");
        f1167a.put(ap.CARDTYPE_MASTERCARD, "MasterCard\u200f");
        f1167a.put(ap.CARDTYPE_VISA, "Visa\u200f");
        f1167a.put(ap.DONE, "\u062a\u0645");
        f1167a.put(ap.ENTRY_CVV, "CVV\u200f");
        f1167a.put(ap.ENTRY_POSTAL_CODE, "\u0627\u0644\u0631\u0645\u0632 \u0627\u0644\u0628\u0631\u064a\u062f\u064a");
        f1167a.put(ap.ENTRY_EXPIRES, "\u062a\u0627\u0631\u064a\u062e \u0627\u0646\u062a\u0647\u0627\u0621 \u0627\u0644\u0635\u0644\u0627\u062d\u064a\u0629");
        f1167a.put(ap.ENTRY_NUMBER, "\u0627\u0644\u0631\u0642\u0645");
        f1167a.put(ap.ENTRY_TITLE, "\u0627\u0644\u0628\u0637\u0627\u0642\u0629");
        f1167a.put(ap.EXPIRES_PLACEHOLDER, "MM/YY\u200f");
        f1167a.put(ap.OK, "\u0645\u0648\u0627\u0641\u0642");
        f1167a.put(ap.SCAN_GUIDE, "\u0627\u0645\u0633\u0643 \u0627\u0644\u0628\u0637\u0627\u0642\u0629 \u0647\u0646\u0627.\n \u0633\u062a\u0645\u0633\u062d \u062a\u0644\u0642\u0627\u0626\u064a\u0627.");
        f1167a.put(ap.KEYBOARD, "\u0644\u0648\u062d\u0629 \u0627\u0644\u0645\u0641\u0627\u062a\u064a\u062d\u2026");
        f1167a.put(ap.ENTRY_CARD_NUMBER, "\u0631\u0642\u0645 \u0627\u0644\u0628\u0637\u0627\u0642\u0629");
        f1167a.put(ap.MANUAL_ENTRY_TITLE, "\u062a\u0641\u0627\u0635\u064a\u0644 \u0627\u0644\u0628\u0637\u0627\u0642\u0629");
        f1167a.put(ap.WHOOPS, "\u0639\u0630\u0631\u0627\u064b!");
        f1167a.put(ap.ERROR_NO_DEVICE_SUPPORT, "\u0647\u0630\u0627 \u0627\u0644\u062c\u0647\u0627\u0632 \u0644\u0627 \u064a\u0645\u0643\u0646\u0647 \u0627\u0633\u062a\u0639\u0645\u0627\u0644 \u0627\u0644\u0643\u0627\u0645\u064a\u0631\u0627 \u0644\u0642\u0631\u0627\u0621\u0629 \u0623\u0631\u0642\u0627\u0645 \u0627\u0644\u0628\u0637\u0627\u0642\u0629.");
        f1167a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "\u0643\u0627\u0645\u064a\u0631\u0627 \u0627\u0644\u062c\u0647\u0627\u0632 \u063a\u064a\u0631 \u0645\u062a\u0627\u062d\u0629.");
        f1167a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "\u0627\u0644\u062c\u0647\u0627\u0632 \u062d\u062f\u062b \u0628\u0647 \u062e\u0637\u0627 \u063a\u064a\u0631 \u0645\u062a\u0648\u0642\u0639 \u0639\u0646\u062f \u0641\u062a\u062d \u0627\u0644\u0643\u0627\u0645\u064a\u0631\u0627.");
    }

    public final String m1126a() {
        return "ar";
    }

    public final /* synthetic */ String m1127a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1168b.containsKey(str2) ? (String) f1168b.get(str2) : (String) f1167a.get(apVar);
    }
}
