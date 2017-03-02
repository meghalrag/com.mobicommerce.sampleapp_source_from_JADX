package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.E */
public final class C0263E implements C0167k {
    private static Map f1137a;
    private static Map f1138b;

    static {
        f1137a = new HashMap();
        f1138b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0263E() {
        f1137a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "Esta aplica\u00e7\u00e3o n\u00e3o est\u00e1 autorizada a fazer leitura de cart\u00f5es.");
        f1137a.put(ap.CANCEL, "Cancelar");
        f1137a.put(ap.CARDTYPE_AMERICANEXPRESS, "American Express");
        f1137a.put(ap.CARDTYPE_DISCOVER, "Discover");
        f1137a.put(ap.CARDTYPE_JCB, "JCB");
        f1137a.put(ap.CARDTYPE_MASTERCARD, "MasterCard");
        f1137a.put(ap.CARDTYPE_VISA, "Visa");
        f1137a.put(ap.DONE, "Conclu\u00eddo");
        f1137a.put(ap.ENTRY_CVV, "CVV");
        f1137a.put(ap.ENTRY_POSTAL_CODE, "C\u00f3digo Postal");
        f1137a.put(ap.ENTRY_EXPIRES, "Vencimento");
        f1137a.put(ap.ENTRY_NUMBER, "N\u00famero");
        f1137a.put(ap.ENTRY_TITLE, "Cart\u00e3o");
        f1137a.put(ap.EXPIRES_PLACEHOLDER, "MM/AA");
        f1137a.put(ap.OK, "OK");
        f1137a.put(ap.SCAN_GUIDE, "Segure o cart\u00e3o aqui.\nSer\u00e1 lido automaticamente.");
        f1137a.put(ap.KEYBOARD, "Teclado\u2026");
        f1137a.put(ap.ENTRY_CARD_NUMBER, "N\u00famero do cart\u00e3o");
        f1137a.put(ap.MANUAL_ENTRY_TITLE, "Detalhes do cart\u00e3o");
        f1137a.put(ap.WHOOPS, "Ups!");
        f1137a.put(ap.ERROR_NO_DEVICE_SUPPORT, "Este dispositivo n\u00e3o pode utilizar a c\u00e2mara para ler n\u00fameros de cart\u00f5es.");
        f1137a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "A c\u00e2mara do dispositivo n\u00e3o est\u00e1 dispon\u00edvel.");
        f1137a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "Ocorreu um erro inesperado no dispositivo ao abrir a c\u00e2mara.");
    }

    public final String m1089a() {
        return "pt";
    }

    public final /* synthetic */ String m1090a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1138b.containsKey(str2) ? (String) f1138b.get(str2) : (String) f1137a.get(apVar);
    }
}
