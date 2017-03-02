package com.paypal.android.sdk;

import com.paypal.android.sdk.payments.BuildConfig;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

/* renamed from: com.paypal.android.sdk.m */
public final class C0082m {
    private static List f475a;
    private static String f476b;
    private static final Locale f477c;
    private static final Locale f478d;
    private static List f479e;
    private static NumberFormat f480f;

    static {
        f475a = Arrays.asList(new String[]{"AUD", "BRL", "CAD", "CHF", "CZK", "DKK", "EUR", "GBP", "HKD", "HUF", "ILS", "JPY", "MXN", "MYR", "NOK", "NZD", "PHP", "PLN", "SEK", "SGD", "THB", "TWD", "TRY", "USD"});
        f476b = "JPY, HUF, TWD";
        f477c = Locale.US;
        f478d = Locale.GERMANY;
        f479e = null;
        f480f = null;
    }

    private static int m355a(Locale locale, Currency currency) {
        if (f480f == null) {
            f480f = NumberFormat.getCurrencyInstance(locale);
        }
        f480f.setCurrency(currency);
        return f480f.format(1234.56d).indexOf("1") != 0 ? 0 : 1;
    }

    public static String m356a(double d, String str) {
        return C0082m.m357a(d, str, (DecimalFormat) NumberFormat.getInstance(f477c));
    }

    private static String m357a(double d, String str, DecimalFormat decimalFormat) {
        String str2 = "#######0";
        if ((f476b.indexOf(str.toUpperCase(Locale.US)) == -1 ? 1 : null) != null) {
            str2 = "#####0.00";
        }
        decimalFormat.applyPattern(str2);
        return decimalFormat.format(d);
    }

    public static String m358a(double d, Currency currency) {
        DecimalFormat decimalFormat = C0082m.m359a(currency).equals(",") ? (DecimalFormat) NumberFormat.getInstance(f478d) : (DecimalFormat) NumberFormat.getInstance(f477c);
        String str = "#######0";
        if ((f476b.indexOf(currency.getCurrencyCode().toUpperCase(Locale.US)) == -1 ? 1 : null) != null) {
            str = "#####0.00";
        }
        decimalFormat.applyPattern(str);
        return decimalFormat.format(d);
    }

    private static String m359a(Currency currency) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setCurrency(currency);
        return decimalFormat.format(1.56d).indexOf(".") > 0 ? "." : ",";
    }

    public static String m360a(Locale locale, double d, Currency currency) {
        return C0082m.m361a(locale, d, currency, true);
    }

    private static String m361a(Locale locale, double d, Currency currency, boolean z) {
        Object obj = C0082m.m355a(locale, currency) == 0 ? 1 : null;
        String symbol = currency.getSymbol();
        String currencyCode = currency.getCurrencyCode();
        try {
            return (obj == null ? currencyCode + " " : BuildConfig.VERSION_NAME) + (obj != null ? symbol : BuildConfig.VERSION_NAME) + C0082m.m358a(d, currency) + (obj == null ? " " + symbol : BuildConfig.VERSION_NAME) + (obj != null ? " " + currencyCode : BuildConfig.VERSION_NAME);
        } catch (NumberFormatException e) {
            return BuildConfig.VERSION_NAME;
        }
    }

    public static String m362a(Locale locale, String str, double d, String str2, boolean z) {
        String str3;
        String symbol = Currency.getInstance(str2).getSymbol();
        String str4 = " ";
        Object obj = C0082m.m355a(locale, Currency.getInstance(str2)) == 0 ? 1 : null;
        StringBuilder append = new StringBuilder().append(obj != null ? symbol + str4 : BuildConfig.VERSION_NAME);
        if (str.equalsIgnoreCase("AU")) {
            str3 = "AUD";
        } else if (str.equalsIgnoreCase("GB")) {
            str3 = "GBP";
        } else if (str.equalsIgnoreCase("UK")) {
            str3 = "GBP";
        } else if (str.equalsIgnoreCase("CA")) {
            str3 = "CAD";
        } else if (str.equalsIgnoreCase("AT")) {
            str3 = "EUR";
        } else if (str.equalsIgnoreCase("CZ")) {
            str3 = "CZK";
        } else if (str.equalsIgnoreCase("DK")) {
            str3 = "DKK";
        } else if (str.equalsIgnoreCase("FR")) {
            str3 = "EUR";
        } else if (str.equalsIgnoreCase("DE")) {
            str3 = "EUR";
        } else if (str.equalsIgnoreCase("HU")) {
            str3 = "HUF";
        } else if (str.equalsIgnoreCase("IE")) {
            str3 = "EUR";
        } else if (str.equalsIgnoreCase("IT")) {
            str3 = "EUR";
        } else if (str.equalsIgnoreCase("NL")) {
            str3 = "EUR";
        } else if (str.equalsIgnoreCase("PL")) {
            str3 = "PLN";
        } else if (str.equalsIgnoreCase("PT")) {
            str3 = "EUR";
        } else if (str.equalsIgnoreCase("ES")) {
            str3 = "EUR";
        } else if (str.equalsIgnoreCase("SE")) {
            str3 = "SEK";
        } else {
            if (!str.equalsIgnoreCase("ZA")) {
                if (str.equalsIgnoreCase("NZ")) {
                    str3 = "NZD";
                } else if (str.equalsIgnoreCase("LT")) {
                    str3 = "EUR";
                } else if (str.equalsIgnoreCase("JP")) {
                    str3 = "JPY";
                } else if (str.equalsIgnoreCase("BR")) {
                    str3 = "BRL";
                } else if (str.equalsIgnoreCase("MY")) {
                    str3 = "MYR";
                } else if (str.equalsIgnoreCase("MX")) {
                    str3 = "MXN";
                }
            }
            str3 = "USD";
        }
        return append.append(C0082m.m357a(d, str2, C0082m.m359a(Currency.getInstance(str3)).equals(",") ? (DecimalFormat) NumberFormat.getInstance(f478d) : (DecimalFormat) NumberFormat.getInstance(f477c))).append(obj == null ? str4 + symbol : BuildConfig.VERSION_NAME).toString();
    }

    public static List m363a() {
        if (f479e == null) {
            f479e = new ArrayList();
            for (String instance : f475a) {
                f479e.add(Currency.getInstance(instance));
            }
            Collections.sort(f479e, new C0083n());
        }
        return f479e;
    }
}
