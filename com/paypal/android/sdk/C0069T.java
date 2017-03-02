package com.paypal.android.sdk;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.widget.TextView;
import com.paypal.android.sdk.payments.BuildConfig;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.paypal.android.sdk.T */
public class C0069T {
    public final String f18a;
    public final Map f19b;
    public final String f20c;
    public final String f21d;
    public final boolean f22e;

    public C0069T(String str, Map map, String str2, String str3, boolean z) {
        this.f19b = map;
        this.f18a = str;
        this.f21d = str3;
        this.f20c = str2;
        this.f22e = z;
    }

    public static int m33a(int i, int i2) {
        return i * 37;
    }

    public static String m34a(File file) {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        byte[] bArr = new byte[((int) randomAccessFile.length())];
        randomAccessFile.readFully(bArr);
        randomAccessFile.close();
        return new String(bArr, "UTF-8");
    }

    public static String m35a(Iterable iterable, String str) {
        if (iterable == null) {
            return null;
        }
        Iterator it = iterable.iterator();
        if (it == null) {
            return null;
        }
        if (!it.hasNext()) {
            return BuildConfig.VERSION_NAME;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return next == null ? BuildConfig.VERSION_NAME : next.toString();
        } else {
            StringBuilder stringBuilder = new StringBuilder(AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY);
            if (next != null) {
                stringBuilder.append(next);
            }
            while (it.hasNext()) {
                if (str != null) {
                    stringBuilder.append(str);
                }
                next = it.next();
                if (next != null) {
                    stringBuilder.append(next);
                }
            }
            return stringBuilder.toString();
        }
    }

    public static String m36a(Map map) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (Entry entry : map.entrySet()) {
            Object obj2;
            if (obj == null) {
                stringBuilder.append("&");
                obj2 = obj;
            } else {
                obj2 = null;
            }
            stringBuilder.append(((String) entry.getKey()) + "=" + ((String) entry.getValue()));
            obj = obj2;
        }
        return stringBuilder.toString();
    }

    public static String m37a(Object[] objArr, String str) {
        if (objArr == null) {
            return null;
        }
        int length = objArr.length;
        if (objArr == null) {
            return null;
        }
        if (str == null) {
            str = BuildConfig.VERSION_NAME;
        }
        if (length <= 0) {
            return BuildConfig.VERSION_NAME;
        }
        StringBuilder stringBuilder = new StringBuilder(length << 4);
        for (int i = 0; i < length; i++) {
            if (i > 0) {
                stringBuilder.append(str);
            }
            if (objArr[i] != null) {
                stringBuilder.append(objArr[i]);
            }
        }
        return stringBuilder.toString();
    }

    public static void m38a(Activity activity) {
        if (C0069T.m45a()) {
            activity.requestWindowFeature(8);
        }
    }

    public static void m39a(Activity activity, TextView textView, String str, String str2, Drawable drawable) {
        activity.setTitle(str2 + str);
        if (C0069T.m45a()) {
            ActionBar actionBar = activity.getActionBar();
            actionBar.setBackgroundDrawable(bs.f379c);
            actionBar.setTitle(str);
            TextView textView2 = (TextView) activity.findViewById(Resources.getSystem().getIdentifier("action_bar_title", "id", "android"));
            if (textView2 != null) {
                textView2.setTextColor(-1);
            }
            actionBar.setDisplayHomeAsUpEnabled(false);
            if (drawable == null || VERSION.SDK_INT < 14) {
                actionBar.setDisplayShowHomeEnabled(false);
            } else {
                actionBar.setIcon(drawable);
            }
            if (textView != null) {
                textView.setVisibility(8);
            }
        } else if (textView != null) {
            textView.setText(str);
        }
    }

    public static void m40a(File file, String str) {
        Throwable th;
        OutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(str.getBytes("UTF-8"));
                C0069T.m42a(fileOutputStream);
            } catch (Throwable th2) {
                th = th2;
                C0069T.m42a(fileOutputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            C0069T.m42a(fileOutputStream);
            throw th;
        }
    }

    public static void m41a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
            }
        }
    }

    public static void m42a(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
            }
        }
    }

    public static void m43a(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
            }
        }
    }

    protected static void m44a(Map map, StringBuilder stringBuilder) {
        if (!map.isEmpty()) {
            for (String str : map.keySet()) {
                if (map.get(str) == null) {
                    new StringBuilder("No value for ").append(str).append(", skipping");
                    return;
                }
                String b = C0069T.m48b((String) map.get(str));
                stringBuilder.append("&").append(str);
                stringBuilder.append("=").append(b);
            }
        }
    }

    public static boolean m45a() {
        return VERSION.SDK_INT >= 11;
    }

    public static boolean m46a(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean m47a(String str) {
        if (C0069T.m51c(str)) {
            return false;
        }
        return (str.matches("^([a-zA-Z0-9]|_|-)*$")) && str.length() <= 32;
    }

    public static String m48b(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "unable_to_encode:" + str;
        }
    }

    public static void m49b(Activity activity) {
        if ((VERSION.SDK_INT >= 11 ? 1 : null) != null) {
            activity.setTheme(16973934);
        } else {
            activity.setTheme(16973836);
        }
    }

    public static boolean m50b(CharSequence charSequence) {
        return !C0069T.m46a(charSequence);
    }

    public static boolean m51c(CharSequence charSequence) {
        if (charSequence != null) {
            int length = charSequence.length();
            if (length != 0) {
                for (int i = 0; i < length; i++) {
                    if (!Character.isWhitespace(charSequence.charAt(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return true;
    }

    public static boolean m52d(CharSequence charSequence) {
        return !C0069T.m51c(charSequence);
    }

    public static boolean m53e(CharSequence charSequence) {
        if (C0069T.m46a(charSequence)) {
            return false;
        }
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (Character.isWhitespace(charSequence.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
