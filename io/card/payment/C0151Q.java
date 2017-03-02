package io.card.payment;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.google.android.gcm.GCMConstants;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.text.CharacterIterator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: io.card.payment.Q */
final class C0151Q {
    private String f846a;
    private String f847b;
    private JSONObject f848c;
    private C0158a f849d;
    private WeakReference f850e;
    private boolean f851f;

    C0151Q(Context context, String str, boolean z) {
        this.f847b = UUID.randomUUID().toString();
        this.f849d = C0156Y.m764a();
        this.f846a = str;
        this.f851f = z;
        this.f850e = new WeakReference(context);
    }

    public static String m746a(String str, boolean z, CardType cardType) {
        String c = z ? C0151Q.m751c(str) : str;
        if (cardType == null) {
            cardType = CardType.fromCardNumber(c);
        }
        int numberLength = cardType.numberLength();
        if (c.length() != numberLength) {
            return str;
        }
        if (numberLength != 16) {
            return numberLength == 15 ? C0151Q.m752d(c) : str;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            numberLength = 0;
            while (numberLength < 16) {
                if (numberLength != 0 && numberLength % 4 == 0) {
                    stringBuilder.append(' ');
                }
                stringBuilder.append(c.charAt(numberLength));
                numberLength++;
            }
            return stringBuilder.toString();
        }
    }

    private void m747a(String str, Map map) {
        JSONObject jSONObject;
        if (map != null) {
            try {
                jSONObject = new JSONObject(map);
            } catch (JSONException e) {
                Log.e("CardScanAnalyticsReporter", "error creating event" + e.getMessage());
                return;
            } catch (UnsupportedEncodingException e2) {
                Log.e("CardScanAnalyticsReporter", "error encoding event" + e2.getMessage());
                return;
            }
        }
        jSONObject = new JSONObject();
        jSONObject.put("app_token", this.f846a);
        jSONObject.put("detect_card_only", this.f851f);
        jSONObject.put("event_name", str);
        jSONObject.put("session_id", this.f847b);
        jSONObject.put("timestamp", System.currentTimeMillis() / 1000);
        jSONObject.put("device", new JSONObject(C0155V.m760a((Context) this.f850e.get())));
        jSONObject.put("android", new JSONObject(C0155V.m759a()));
        jSONObject.put("application_identifiers", m750b());
        this.f849d.m770a((Context) this.f850e.get(), "https://api.card.io/0/sdk/analytics.json", new StringEntity(jSONObject.toString()), "application/json", new C0273R(str));
    }

    public static boolean m748a(String str) {
        int[][] iArr = new int[][]{new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[]{0, 2, 4, 6, 8, 1, 3, 5, 7, 9}};
        CharacterIterator stringCharacterIterator = new StringCharacterIterator(str);
        char last = stringCharacterIterator.last();
        int i = 0;
        int i2 = 0;
        while (last != '\uffff') {
            if (!Character.isDigit(last)) {
                return false;
            }
            int i3 = i2 + 1;
            i += iArr[i2 & 1][last - 48];
            last = stringCharacterIterator.previous();
            i2 = i3;
        }
        return i % 10 == 0;
    }

    public static Date m749b(String str) {
        SimpleDateFormat simpleDateFormat;
        Date date = null;
        String c = C0151Q.m751c(str);
        int length = c.length();
        if (length == 4) {
            simpleDateFormat = new SimpleDateFormat("MMyy");
        } else if (length == 6) {
            simpleDateFormat = new SimpleDateFormat("MMyyyy");
        } else {
            Object obj = date;
        }
        if (simpleDateFormat != null) {
            try {
                simpleDateFormat.setLenient(false);
                date = simpleDateFormat.parse(c);
            } catch (ParseException e) {
            }
        }
        return date;
    }

    private JSONObject m750b() {
        if (this.f848c == null) {
            this.f848c = new JSONObject();
            Context context = (Context) this.f850e.get();
            String packageName = context.getPackageName();
            this.f848c.put("package_name", context.getPackageName());
            try {
                PackageManager packageManager = context.getPackageManager();
                PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
                this.f848c.put("package_display_name", packageInfo.applicationInfo.loadLabel(packageManager).toString());
                this.f848c.put("package_version", packageInfo.versionName);
            } catch (NameNotFoundException e) {
            }
        }
        return this.f848c;
    }

    public static String m751c(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    private static String m752d(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while (i < 15) {
            if (i == 4 || i == 10) {
                stringBuilder.append(' ');
            }
            stringBuilder.append(str.charAt(i));
            i++;
        }
        return stringBuilder.toString();
    }

    final void m753a() {
        m747a("scan_start", null);
    }

    final void m754a(ap apVar, Throwable th, Map map) {
        if (map == null) {
            map = new HashMap();
        }
        map.put(GCMConstants.EXTRA_ERROR, apVar.toString());
        if (th != null) {
            map.put("error_detail", th.toString());
        }
        m747a("device_error", map);
    }

    final void m755a(ap apVar, Map map) {
        m754a(apVar, null, map);
    }

    final void m756a(Map map) {
        m747a("scan_cancel", map);
    }

    final void m757b(Map map) {
        m747a("scan_manual_entry", map);
    }

    final void m758c(Map map) {
        m747a("scan_success", map);
    }
}
