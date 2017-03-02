package com.paypal.android.sdk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* renamed from: com.paypal.android.sdk.x */
public class C0130x extends SimpleDateFormat {
    private static final String f779a;
    private static final long serialVersionUID = 5709634976027470847L;

    static {
        f779a = C0130x.class.getSimpleName();
    }

    public C0130x() {
        this(TimeZone.getTimeZone("UTC"));
    }

    private C0130x(TimeZone timeZone) {
        super("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US);
        setTimeZone(timeZone);
    }

    public static Date m708a(String str) {
        if (str == null) {
            return null;
        }
        String str2;
        String[] strArr = new String[]{"yyyy-MM-dd'T'HH:mm:ssZ", "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "yyyy-MM-dd'T'HH:mm:ss'Z'"};
        int i = 0;
        while (i < 4) {
            str2 = strArr[i];
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2, Locale.US);
            simpleDateFormat.setLenient(true);
            try {
                Date parse = simpleDateFormat.parse(str);
                if (parse != null) {
                    return parse;
                }
                i++;
            } catch (ParseException e) {
                String str3 = f779a;
                new StringBuilder("unsuccessful attempt to parse date '").append(str).append("': ").append(e.getMessage()).append(" while using format:'").append(str2).append("'");
            }
        }
        str2 = f779a;
        new StringBuilder("couldn't parse '").append(str).append("'");
        return null;
    }
}
