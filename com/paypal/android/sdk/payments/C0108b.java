package com.paypal.android.sdk.payments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.util.Log;
import android.widget.TextView;
import com.paypal.android.sdk.C0069T;
import com.paypal.android.sdk.C0076g;
import com.paypal.android.sdk.bN;
import com.paypal.android.sdk.bO;
import com.paypal.android.sdk.bt;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/* renamed from: com.paypal.android.sdk.payments.b */
class C0108b {
    private Context f723a;

    C0108b(Context context) {
        this.f723a = context;
    }

    static AlertDialog m643a(Activity activity, bO bOVar, bO bOVar2, OnClickListener onClickListener) {
        return new Builder(activity).setIcon(17301543).setTitle(bN.m131a(bOVar)).setMessage(bN.m131a(bOVar2)).setPositiveButton(bN.m131a(bO.OK), onClickListener).setNegativeButton(bN.m131a(bO.CANCEL), null).create();
    }

    public static AlertDialog m644a(Context context, bO bOVar, String str, OnClickListener onClickListener) {
        CharSequence a = bN.m131a(bOVar);
        return new Builder(context).setMessage(str).setCancelable(false).setTitle(a).setPositiveButton(bN.m131a(bO.OK), onClickListener).create();
    }

    static Dialog m645a(Activity activity, OnClickListener onClickListener) {
        return new Builder(activity).setIcon(17301543).setCancelable(false).setTitle(bN.m131a(bO.TRY_AGAIN)).setMessage(bN.m131a(bO.SERVER_PROBLEM)).setPositiveButton(bN.m131a(bO.TRY_AGAIN), onClickListener).setNegativeButton(bN.m131a(bO.CANCEL), new C0113g(activity)).create();
    }

    static Dialog m646a(Activity activity, bO bOVar, Bundle bundle) {
        return C0108b.m644a((Context) activity, bOVar, bundle.getString("alert_errors"), new C0111e());
    }

    static Dialog m647a(Activity activity, bO bOVar, Bundle bundle, int i) {
        return C0108b.m644a((Context) activity, bOVar, bundle.getString("alert_errors"), new C0112f(activity, i));
    }

    static Dialog m648a(Activity activity, bO bOVar, Bundle bundle, OnClickListener onClickListener) {
        return C0108b.m644a((Context) activity, bOVar, bundle.getString("alert_errors"), onClickListener);
    }

    static Dialog m649a(Context context, bO bOVar, bO bOVar2) {
        Dialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(bN.m131a(bOVar));
        progressDialog.setMessage(bN.m131a(bOVar2));
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    static SpannableString m650a(String str) {
        Object toLowerCase = Locale.getDefault().getCountry().toLowerCase(Locale.US);
        if (C0069T.m51c(toLowerCase) || !toLowerCase.equals("jp")) {
            return null;
        }
        String a = bN.m131a(bO.JAPANESE_COMPLIANCE_AGREEMENT);
        Object[] objArr = new Object[2];
        String str2 = (C0069T.m52d(str) && str.equals("ja")) ? "https://cms.paypal.com/jp/cgi-bin/marketingweb?cmd=_render-content&content_ID=ua/Legal_Hub_full&locale.x=ja_JP" : "https://cms.paypal.com/jp/cgi-bin/marketingweb?cmd=_render-content&content_ID=ua/Legal_Hub_full&locale.x=en_US";
        objArr[0] = str2;
        objArr[1] = "https://www.paypal.jp/jp/contents/regulation/info/overseas-remittance/";
        return new SpannableString(Html.fromHtml(String.format(a, objArr)));
    }

    static void m651a(Activity activity, TextView textView, bO bOVar) {
        C0069T.m39a(activity, textView, bOVar != null ? bN.m131a(bOVar) : BuildConfig.VERSION_NAME, "PayPal - ", new BitmapDrawable(activity.getResources(), bt.m257a("iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyRpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoTWFjaW50b3NoKSIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDpCNDMzRTRFQ0M2MjQxMUUzOURBQ0E3QTY0NjU3OUI5QiIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDpCNDMzRTRFREM2MjQxMUUzOURBQ0E3QTY0NjU3OUI5QiI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOkI0MzNFNEVBQzYyNDExRTM5REFDQTdBNjQ2NTc5QjlCIiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOkI0MzNFNEVCQzYyNDExRTM5REFDQTdBNjQ2NTc5QjlCIi8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+Eyd0MQAABoFJREFUeNrMWl1M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", (Context) activity, 240)));
    }

    static void m652a(Activity activity, ag agVar, int i, int i2, int i3) {
        int i4 = 0;
        int i5 = ((agVar.f702a == null || agVar.f702a.intValue() < 500) && !agVar.f703b.equals(C0076g.SERVER_COMMUNICATION_ERROR.toString())) ? 0 : 1;
        if (i5 != 0) {
            activity.showDialog(2);
            return;
        }
        if (agVar.m633a() && "invalid_client".equals(agVar.f703b)) {
            i4 = 1;
        }
        if (i4 != 0) {
            C0108b.m653a(activity, bN.m131a(bO.UNAUTHORIZED_MERCHANT_MESSAGE), 3);
        } else {
            C0108b.m653a(activity, bN.m131a(bO.UNAUTHORIZED_DEVICE_MESSAGE), 1);
        }
    }

    static void m653a(Activity activity, String str, int i) {
        Bundle bundle = new Bundle();
        bundle.putString("alert_errors", str);
        activity.removeDialog(i);
        activity.showDialog(i, bundle);
    }

    static void m654a(TextView textView, String str) {
        if (textView == null) {
            return;
        }
        if (str == null || str.equals(PayPalConfiguration.ENVIRONMENT_PRODUCTION)) {
            textView.setVisibility(8);
        } else if (str.startsWith(PayPalConfiguration.ENVIRONMENT_SANDBOX)) {
            textView.setText(bN.m131a(bO.ENVIRONMENT_SANDBOX));
        } else if (str.equals(PayPalConfiguration.ENVIRONMENT_NO_NETWORK)) {
            textView.setText(bN.m131a(bO.ENVIRONMENT_MOCK_DATA));
        } else {
            textView.setText(str);
        }
    }

    static boolean m655a(Activity activity) {
        String name = activity.getClass().getName();
        ComponentName callingActivity = activity.getCallingActivity();
        if (callingActivity == null) {
            Log.e("paypal.sdk", name + " called by a null activity, not allowed");
            return false;
        }
        String className = callingActivity.getClassName();
        if (className.startsWith("com.paypal.android.sdk.payments.")) {
            return true;
        }
        Log.e("paypal.sdk", name + " called by " + className + " which is not part of the SDK, not allowed");
        return false;
    }

    static boolean m656a(String str, String str2, String str3) {
        boolean a = C0069T.m46a((CharSequence) str2);
        if (a) {
            Log.e(str, str3 + " is empty.");
        }
        boolean e = C0069T.m53e(str2);
        if (e) {
            Log.e(str, str3 + " contains whitespace.");
        }
        return (a || e) ? false : true;
    }

    static Intent m657b(Activity activity) {
        Intent intent = new Intent(activity.getApplicationContext(), PayPalService.class);
        intent.putExtras(activity.getIntent());
        return intent;
    }

    final void m658a(Collection collection) {
        try {
            PackageInfo packageInfo = this.f723a.getPackageManager().getPackageInfo(this.f723a.getPackageName(), 1);
            Set hashSet = new HashSet(collection);
            if (packageInfo.activities != null) {
                for (ActivityInfo activityInfo : packageInfo.activities) {
                    hashSet.remove(activityInfo.name);
                }
            }
            if (!hashSet.isEmpty()) {
                throw new RuntimeException("Missing required activities in manifest:" + hashSet);
            }
        } catch (NameNotFoundException e) {
            throw new RuntimeException("Exception loading manifest" + e.getMessage());
        }
    }
}
