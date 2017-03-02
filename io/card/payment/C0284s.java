package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.s */
public final class C0284s implements C0167k {
    private static Map f1181a;
    private static Map f1182b;

    static {
        f1181a = new HashMap();
        f1182b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0284s() {
        f1181a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "Esta aplicaci\u00f3n no est\u00e1 autorizada para escanear tarjetas.");
        f1181a.put(ap.CANCEL, "Cancelar");
        f1181a.put(ap.CARDTYPE_AMERICANEXPRESS, "American Express");
        f1181a.put(ap.CARDTYPE_DISCOVER, "Discover");
        f1181a.put(ap.CARDTYPE_JCB, "JCB");
        f1181a.put(ap.CARDTYPE_MASTERCARD, "MasterCard");
        f1181a.put(ap.CARDTYPE_VISA, "Visa");
        f1181a.put(ap.DONE, "Hecho");
        f1181a.put(ap.ENTRY_CVV, "CVV");
        f1181a.put(ap.ENTRY_POSTAL_CODE, "C\u00f3digo postal");
        f1181a.put(ap.ENTRY_EXPIRES, "Caduca");
        f1181a.put(ap.ENTRY_NUMBER, "N\u00famero");
        f1181a.put(ap.ENTRY_TITLE, "Tarjeta");
        f1181a.put(ap.EXPIRES_PLACEHOLDER, "MM/AA");
        f1181a.put(ap.OK, "Aceptar");
        f1181a.put(ap.SCAN_GUIDE, "Mantenga la tarjeta aqu\u00ed.\nSe escanear\u00e1 autom\u00e1ticamente.");
        f1181a.put(ap.KEYBOARD, "Teclado\u2026");
        f1181a.put(ap.ENTRY_CARD_NUMBER, "N\u00famero de tarjeta");
        f1181a.put(ap.MANUAL_ENTRY_TITLE, "Detalles de la tarjeta");
        f1181a.put(ap.WHOOPS, "Lo sentimos.");
        f1181a.put(ap.ERROR_NO_DEVICE_SUPPORT, "Este dispositivo no puede usar la c\u00e1mara para leer n\u00fameros de tarjeta.");
        f1181a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "La c\u00e1mara del dispositivo no est\u00e1 disponible.");
        f1181a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "Al abrir la c\u00e1mara, el dispositivo ha experimentado un error inesperado.");
    }

    public final String m1140a() {
        return "es";
    }

    public final /* synthetic */ String m1141a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1182b.containsKey(str2) ? (String) f1182b.get(str2) : (String) f1181a.get(apVar);
    }
}
