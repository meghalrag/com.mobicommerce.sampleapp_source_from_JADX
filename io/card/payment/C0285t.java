package io.card.payment;

import java.util.HashMap;
import java.util.Map;

/* renamed from: io.card.payment.t */
public final class C0285t implements C0167k {
    private static Map f1183a;
    private static Map f1184b;

    static {
        f1183a = new HashMap();
        f1184b = new HashMap();
        HashMap hashMap = new HashMap();
    }

    public C0285t() {
        f1183a.put(ap.APP_NOT_AUTHORIZED_MESSAGE, "Esta aplicaci\u00f3n no est\u00e1 autorizada para escanear tarjetas.");
        f1183a.put(ap.CANCEL, "Cancelar");
        f1183a.put(ap.CARDTYPE_AMERICANEXPRESS, "American Express");
        f1183a.put(ap.CARDTYPE_DISCOVER, "Discover");
        f1183a.put(ap.CARDTYPE_JCB, "JCB");
        f1183a.put(ap.CARDTYPE_MASTERCARD, "MasterCard");
        f1183a.put(ap.CARDTYPE_VISA, "Visa");
        f1183a.put(ap.DONE, "Listo");
        f1183a.put(ap.ENTRY_CVV, "CVV");
        f1183a.put(ap.ENTRY_POSTAL_CODE, "C\u00f3digo postal");
        f1183a.put(ap.ENTRY_EXPIRES, "Caduca");
        f1183a.put(ap.ENTRY_NUMBER, "N\u00famero");
        f1183a.put(ap.ENTRY_TITLE, "Tarjeta");
        f1183a.put(ap.EXPIRES_PLACEHOLDER, "MM/AA");
        f1183a.put(ap.OK, "Aceptar");
        f1183a.put(ap.SCAN_GUIDE, "Sostenga la tarjeta aqu\u00ed.\nSe escanear\u00e1 autom\u00e1ticamente.");
        f1183a.put(ap.KEYBOARD, "Teclado\u2026");
        f1183a.put(ap.ENTRY_CARD_NUMBER, "N\u00famero de tarjeta");
        f1183a.put(ap.MANUAL_ENTRY_TITLE, "Detalles de la tarjeta");
        f1183a.put(ap.WHOOPS, "\u00a1Huy!");
        f1183a.put(ap.ERROR_NO_DEVICE_SUPPORT, "Este dispositivo no puede usar la c\u00e1mara para leer n\u00fameros de tarjeta.");
        f1183a.put(ap.ERROR_CAMERA_CONNECT_FAIL, "La c\u00e1mara del dispositivo no est\u00e1 disponible.");
        f1183a.put(ap.ERROR_CAMERA_UNEXPECTED_FAIL, "El dispositivo tuvo un error inesperado al abrir la c\u00e1mara.");
    }

    public final String m1142a() {
        return "es_MX";
    }

    public final /* synthetic */ String m1143a(Enum enumR, String str) {
        ap apVar = (ap) enumR;
        String str2 = apVar.toString() + "|" + str;
        return f1184b.containsKey(str2) ? (String) f1184b.get(str2) : (String) f1183a.get(apVar);
    }
}
