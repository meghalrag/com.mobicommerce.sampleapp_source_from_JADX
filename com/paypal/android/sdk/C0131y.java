package com.paypal.android.sdk;

import android.util.Base64;
import android.util.Log;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/* renamed from: com.paypal.android.sdk.y */
public final class C0131y {
    private static String f780a;
    private String f781b;

    static {
        f780a = C0131y.class.getSimpleName();
    }

    public C0131y(String str) {
        this.f781b = str;
    }

    private static String m709a(Exception exception) {
        Log.e(f780a, exception.getMessage());
        return null;
    }

    public final String m710a(String str) {
        if (str == null) {
            return null;
        }
        try {
            Key generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(this.f781b.getBytes("UTF8")));
            byte[] bytes = str.getBytes("UTF8");
            Cipher instance = Cipher.getInstance("DES");
            instance.init(1, generateSecret);
            return Base64.encodeToString(instance.doFinal(bytes), 0);
        } catch (Exception e) {
            return C0131y.m709a(e);
        } catch (Exception e2) {
            return C0131y.m709a(e2);
        } catch (Exception e22) {
            return C0131y.m709a(e22);
        } catch (Exception e222) {
            return C0131y.m709a(e222);
        } catch (Exception e2222) {
            return C0131y.m709a(e2222);
        } catch (Exception e22222) {
            return C0131y.m709a(e22222);
        } catch (Exception e222222) {
            return C0131y.m709a(e222222);
        }
    }

    public final String m711b(String str) {
        if (str == null) {
            return null;
        }
        try {
            Key generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(this.f781b.getBytes("UTF8")));
            byte[] decode = Base64.decode(str, 0);
            Cipher instance = Cipher.getInstance("DES");
            instance.init(2, generateSecret);
            return new String(instance.doFinal(decode));
        } catch (Exception e) {
            return C0131y.m709a(e);
        } catch (Exception e2) {
            return C0131y.m709a(e2);
        } catch (Exception e22) {
            return C0131y.m709a(e22);
        } catch (Exception e222) {
            return C0131y.m709a(e222);
        } catch (Exception e2222) {
            return C0131y.m709a(e2222);
        } catch (Exception e22222) {
            return C0131y.m709a(e22222);
        } catch (Exception e222222) {
            return C0131y.m709a(e222222);
        }
    }
}
