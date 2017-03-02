package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.F */
public final class C0264F implements C0167k {
    private static Map f1139a;
    private static Map f1140b;

    static {
        f1139a = new HashMap();
        f1140b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0264F() {
        f1139a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "Este aplicativo n\u00e3o est\u00e1 autorizado a fazer leitura de cart\u00f5es.");
        f1139a.put(ap.CANCEL, "Cancelar");
        f1139a.put(ap.CARDTYPE_AMERICANEXPRESS, "American Express");
        f1139a.put(ap.CARDTYPE_DISCOVER, "Discover");
        f1139a.put(ap.CARDTYPE_JCB, "JCB");
        f1139a.put(ap.CARDTYPE_MASTERCARD, "MasterCard");
        f1139a.put(ap.CARDTYPE_VISA, "Visa");
        f1139a.put(ap.DONE, "Conclu\u00eddo");
        f1139a.put(ap.ENTRY_CVV, "CVV");
        f1139a.put(ap.ENTRY_POSTAL_CODE, "CEP");
        f1139a.put(ap.ENTRY_EXPIRES, "Vencimento");
        f1139a.put(ap.ENTRY_NUMBER, "N\u00famero");
        f1139a.put(ap.ENTRY_TITLE, "Cart\u00e3o");
        f1139a.put(ap.EXPIRES_PLACEHOLDER, "MM/AA");
        f1139a.put(ap.OK, "OK");
        f1139a.put(ap.SCAN_GUIDE, "Posicionar cart\u00e3o aqui.\nEle ser\u00e1 digitalizado automaticamente.");
        f1139a.put(ap.KEYBOARD, "Teclado\u2026");
        f1139a.put(ap.ENTRY_CARD_NUMBER, "N\u00famero do Cart\u00e3o");
        f1139a.put(ap.MANUAL_ENTRY_TITLE, "Dados do cart\u00e3o");
        f1139a.put(ap.WHOOPS, "Oh!");
        f1139a.put(ap.ERROR_NO_DEVICE_SUPPORT, "Este dispositivo n\u00e3o pode usar a c\u00e2mera para ler n\u00fameros de cart\u00e3o.");
        f1139a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "A c\u00e2mera do dispositivo n\u00e3o est\u00e1 dispon\u00edvel.");
        f1139a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "O dispositivo sofreu um erro inesperado ao abrir a c\u00e2mera.");
    }

    public final String m1091a() {
        return "pt_BR";
    }

    public final /* synthetic */ String m1092a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1140b.containsKey(str2) ? (String) f1140b.get(str2) : (String) f1139a.get(apVar);
    }
}
