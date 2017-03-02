package nl.xservices.plugins;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.text.Html;
import android.util.Base64;
import android.widget.Toast;
import com.paypal.android.sdk.payments.BuildConfig;
import com.paypal.android.sdk.payments.PayPalOAuthScopes;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONException;

public class SocialSharing extends CordovaPlugin {
    private static final String ACTION_AVAILABLE_EVENT = "available";
    private static final String ACTION_CAN_SHARE_VIA = "canShareVia";
    private static final String ACTION_CAN_SHARE_VIA_EMAIL = "canShareViaEmail";
    private static final String ACTION_SHARE_EVENT = "share";
    private static final String ACTION_SHARE_VIA = "shareVia";
    private static final String ACTION_SHARE_VIA_EMAIL_EVENT = "shareViaEmail";
    private static final String ACTION_SHARE_VIA_FACEBOOK_EVENT = "shareViaFacebook";
    private static final String ACTION_SHARE_VIA_FACEBOOK_WITH_PASTEMESSAGEHINT = "shareViaFacebookWithPasteMessageHint";
    private static final String ACTION_SHARE_VIA_SMS_EVENT = "shareViaSMS";
    private static final String ACTION_SHARE_VIA_TWITTER_EVENT = "shareViaTwitter";
    private static final String ACTION_SHARE_VIA_WHATSAPP_EVENT = "shareViaWhatsApp";
    private static final int ACTIVITY_CODE_SENDVIAEMAIL = 2;
    private CallbackContext _callbackContext;
    private String pasteMessage;

    private abstract class SocialSharingRunnable implements Runnable {
        public CallbackContext callbackContext;

        SocialSharingRunnable(CallbackContext cb) {
            this.callbackContext = cb;
        }
    }

    /* renamed from: nl.xservices.plugins.SocialSharing.1 */
    class C02911 extends SocialSharingRunnable {
        private final /* synthetic */ JSONArray val$bcc;
        private final /* synthetic */ JSONArray val$cc;
        private final /* synthetic */ JSONArray val$files;
        private final /* synthetic */ String val$message;
        private final /* synthetic */ SocialSharing val$plugin;
        private final /* synthetic */ String val$subject;
        private final /* synthetic */ JSONArray val$to;

        C02911(CallbackContext $anonymous0, String str, String str2, JSONArray jSONArray, JSONArray jSONArray2, JSONArray jSONArray3, JSONArray jSONArray4, SocialSharing socialSharing) {
            this.val$message = str;
            this.val$subject = str2;
            this.val$to = jSONArray;
            this.val$cc = jSONArray2;
            this.val$bcc = jSONArray3;
            this.val$files = jSONArray4;
            this.val$plugin = socialSharing;
            super($anonymous0);
        }

        public void run() {
            Intent draft = new Intent("android.intent.action.SEND_MULTIPLE");
            if (SocialSharing.notEmpty(this.val$message)) {
                if (this.val$message.matches(".*<[^>]+>.*")) {
                    draft.putExtra("android.intent.extra.TEXT", Html.fromHtml(this.val$message));
                    draft.setType("text/html");
                } else {
                    draft.putExtra("android.intent.extra.TEXT", this.val$message);
                    draft.setType("text/plain");
                }
            }
            if (SocialSharing.notEmpty(this.val$subject)) {
                draft.putExtra("android.intent.extra.SUBJECT", this.val$subject);
            }
            try {
                if (this.val$to != null && this.val$to.length() > 0) {
                    draft.putExtra("android.intent.extra.EMAIL", SocialSharing.toStringArray(this.val$to));
                }
                if (this.val$cc != null && this.val$cc.length() > 0) {
                    draft.putExtra("android.intent.extra.CC", SocialSharing.toStringArray(this.val$cc));
                }
                if (this.val$bcc != null && this.val$bcc.length() > 0) {
                    draft.putExtra("android.intent.extra.BCC", SocialSharing.toStringArray(this.val$bcc));
                }
                if (this.val$files.length() > 0) {
                    ArrayList<Uri> fileUris = new ArrayList();
                    String dir = SocialSharing.this.getDownloadDir();
                    for (int i = 0; i < this.val$files.length(); i++) {
                        Uri fileUri = SocialSharing.this.getFileUriAndSetType(draft, dir, this.val$files.getString(i), this.val$subject, i);
                        if (fileUri != null) {
                            fileUris.add(fileUri);
                        }
                    }
                    if (!fileUris.isEmpty()) {
                        draft.putExtra("android.intent.extra.STREAM", fileUris);
                    }
                }
            } catch (Exception e) {
                this.callbackContext.error(e.getMessage());
            }
            draft.setType("application/octet-stream");
            SocialSharing.this.cordova.startActivityForResult(this.val$plugin, Intent.createChooser(draft, "Choose Email App"), SocialSharing.ACTIVITY_CODE_SENDVIAEMAIL);
        }
    }

    /* renamed from: nl.xservices.plugins.SocialSharing.2 */
    class C02922 extends SocialSharingRunnable {
        private final /* synthetic */ String val$appPackageName;
        private final /* synthetic */ JSONArray val$files;
        private final /* synthetic */ String val$msg;
        private final /* synthetic */ CordovaInterface val$mycordova;
        private final /* synthetic */ boolean val$peek;
        private final /* synthetic */ CordovaPlugin val$plugin;
        private final /* synthetic */ String val$subject;
        private final /* synthetic */ String val$url;

        /* renamed from: nl.xservices.plugins.SocialSharing.2.1 */
        class C01701 extends TimerTask {
            private final /* synthetic */ String val$msg;

            /* renamed from: nl.xservices.plugins.SocialSharing.2.1.1 */
            class C01691 implements Runnable {
                private final /* synthetic */ String val$msg;

                C01691(String str) {
                    this.val$msg = str;
                }

                public void run() {
                    SocialSharing.this.showPasteMessage(this.val$msg, SocialSharing.this.pasteMessage);
                }
            }

            C01701(String str) {
                this.val$msg = str;
            }

            public void run() {
                SocialSharing.this.cordova.getActivity().runOnUiThread(new C01691(this.val$msg));
            }
        }

        C02922(CallbackContext $anonymous0, String str, JSONArray jSONArray, String str2, String str3, String str4, boolean z, CordovaInterface cordovaInterface, CordovaPlugin cordovaPlugin) {
            this.val$msg = str;
            this.val$files = jSONArray;
            this.val$subject = str2;
            this.val$url = str3;
            this.val$appPackageName = str4;
            this.val$peek = z;
            this.val$mycordova = cordovaInterface;
            this.val$plugin = cordovaPlugin;
            super($anonymous0);
        }

        public void run() {
            String message = this.val$msg;
            boolean hasMultipleAttachments = this.val$files.length() > 1;
            Intent sendIntent = new Intent(hasMultipleAttachments ? "android.intent.action.SEND_MULTIPLE" : "android.intent.action.SEND");
            sendIntent.addFlags(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END);
            if (this.val$files.length() > 0) {
                ArrayList<Uri> fileUris = new ArrayList();
                try {
                    String dir = SocialSharing.this.getDownloadDir();
                    Uri fileUri = null;
                    for (int i = 0; i < this.val$files.length(); i++) {
                        fileUri = SocialSharing.this.getFileUriAndSetType(sendIntent, dir, this.val$files.getString(i), this.val$subject, i);
                        if (fileUri != null) {
                            fileUris.add(fileUri);
                        }
                    }
                    if (!fileUris.isEmpty()) {
                        if (hasMultipleAttachments) {
                            sendIntent.putExtra("android.intent.extra.STREAM", fileUris);
                        } else {
                            sendIntent.putExtra("android.intent.extra.STREAM", fileUri);
                        }
                    }
                } catch (Exception e) {
                    this.callbackContext.error(e.getMessage());
                }
            } else {
                sendIntent.setType("text/plain");
            }
            if (SocialSharing.notEmpty(this.val$subject)) {
                sendIntent.putExtra("android.intent.extra.SUBJECT", this.val$subject);
            }
            if (SocialSharing.notEmpty(this.val$url)) {
                if (SocialSharing.notEmpty(message)) {
                    message = new StringBuilder(String.valueOf(message)).append(" ").append(this.val$url).toString();
                } else {
                    message = this.val$url;
                }
            }
            if (SocialSharing.notEmpty(message)) {
                sendIntent.putExtra("android.intent.extra.TEXT", message);
                sendIntent.putExtra("sms_body", message);
            }
            if (this.val$appPackageName != null) {
                String packageName = this.val$appPackageName;
                String passedActivityName = null;
                if (packageName.contains("/")) {
                    String[] items = this.val$appPackageName.split("/");
                    packageName = items[0];
                    passedActivityName = items[1];
                }
                ActivityInfo activity = SocialSharing.this.getActivity(this.callbackContext, sendIntent, packageName);
                if (activity == null) {
                    return;
                }
                if (this.val$peek) {
                    this.callbackContext.sendPluginResult(new PluginResult(Status.OK));
                    return;
                }
                sendIntent.addCategory("android.intent.category.LAUNCHER");
                String str = activity.applicationInfo.packageName;
                if (passedActivityName == null) {
                    passedActivityName = activity.name;
                }
                sendIntent.setComponent(new ComponentName(str, passedActivityName));
                this.val$mycordova.startActivityForResult(this.val$plugin, sendIntent, 0);
                if (SocialSharing.this.pasteMessage != null) {
                    new Timer().schedule(new C01701(this.val$msg), 2000);
                }
            } else if (this.val$peek) {
                this.callbackContext.sendPluginResult(new PluginResult(Status.OK));
            } else {
                this.val$mycordova.startActivityForResult(this.val$plugin, Intent.createChooser(sendIntent, null), 1);
            }
        }
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this._callbackContext = callbackContext;
        this.pasteMessage = null;
        if (ACTION_AVAILABLE_EVENT.equals(action)) {
            callbackContext.sendPluginResult(new PluginResult(Status.OK));
            return true;
        } else if (ACTION_SHARE_EVENT.equals(action)) {
            return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(ACTIVITY_CODE_SENDVIAEMAIL), args.getString(3), null, false);
        } else if (ACTION_SHARE_VIA_TWITTER_EVENT.equals(action)) {
            return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(ACTIVITY_CODE_SENDVIAEMAIL), args.getString(3), "twitter", false);
        } else if (ACTION_SHARE_VIA_FACEBOOK_EVENT.equals(action)) {
            return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(ACTIVITY_CODE_SENDVIAEMAIL), args.getString(3), "com.facebook.katana", false);
        } else if (ACTION_SHARE_VIA_FACEBOOK_WITH_PASTEMESSAGEHINT.equals(action)) {
            this.pasteMessage = args.getString(4);
            return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(ACTIVITY_CODE_SENDVIAEMAIL), args.getString(3), "com.facebook.katana", false);
        } else if (ACTION_SHARE_VIA_WHATSAPP_EVENT.equals(action)) {
            return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(ACTIVITY_CODE_SENDVIAEMAIL), args.getString(3), "whatsapp", false);
        } else if (ACTION_CAN_SHARE_VIA.equals(action)) {
            return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(ACTIVITY_CODE_SENDVIAEMAIL), args.getString(3), args.getString(4), true);
        } else if (ACTION_CAN_SHARE_VIA_EMAIL.equals(action)) {
            if (isEmailAvailable()) {
                callbackContext.sendPluginResult(new PluginResult(Status.OK));
                return true;
            }
            callbackContext.sendPluginResult(new PluginResult(Status.ERROR, "not available"));
            return false;
        } else if (ACTION_SHARE_VIA.equals(action)) {
            return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(ACTIVITY_CODE_SENDVIAEMAIL), args.getString(3), args.getString(4), false);
        } else if (ACTION_SHARE_VIA_SMS_EVENT.equals(action)) {
            return invokeSMSIntent(args.getString(0), args.getString(1));
        } else {
            if (ACTION_SHARE_VIA_EMAIL_EVENT.equals(action)) {
                return invokeEmailIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(ACTIVITY_CODE_SENDVIAEMAIL), args.isNull(3) ? null : args.getJSONArray(3), args.isNull(4) ? null : args.getJSONArray(4), args.isNull(5) ? null : args.getJSONArray(5));
            }
            callbackContext.error("socialSharing." + action + " is not a supported function. Did you mean '" + ACTION_SHARE_EVENT + "'?");
            return false;
        }
    }

    private boolean isEmailAvailable() {
        if (this.cordova.getActivity().getPackageManager().queryIntentActivities(new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", "someone@domain.com", null)), 0).size() > 1) {
            return true;
        }
        return false;
    }

    private boolean invokeEmailIntent(CallbackContext callbackContext, String message, String subject, JSONArray to, JSONArray cc, JSONArray bcc, JSONArray files) throws JSONException {
        this.cordova.getThreadPool().execute(new C02911(callbackContext, message, subject, to, cc, bcc, files, this));
        return true;
    }

    private String getDownloadDir() throws IOException {
        String dir = this.webView.getContext().getExternalFilesDir(null) + "/socialsharing-downloads";
        createOrCleanDir(dir);
        return dir;
    }

    private boolean doSendIntent(CallbackContext callbackContext, String msg, String subject, JSONArray files, String url, String appPackageName, boolean peek) {
        this.cordova.getThreadPool().execute(new C02922(callbackContext, msg, files, subject, url, appPackageName, peek, this.cordova, this));
        return true;
    }

    @SuppressLint({"NewApi"})
    private void showPasteMessage(String msg, String label) {
        if (VERSION.SDK_INT >= 11) {
            ((ClipboardManager) this.cordova.getActivity().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(label, msg));
            Toast toast = Toast.makeText(this.webView.getContext(), label, 1);
            toast.setGravity(17, 0, 0);
            toast.show();
        }
    }

    private Uri getFileUriAndSetType(Intent sendIntent, String dir, String image, String subject, int nthFile) throws IOException {
        String localImage = image;
        sendIntent.setType("image/*");
        if (!image.startsWith("http")) {
            if (!image.startsWith("www/")) {
                if (image.startsWith("data:")) {
                    if (image.contains(";base64,")) {
                        String encodedImg = image.substring(image.indexOf(";base64,") + 8);
                        if (!image.contains("data:image/")) {
                            sendIntent.setType(image.substring(image.indexOf("data:") + 5, image.indexOf(";base64")));
                        }
                        String imgExtension = image.substring(image.indexOf("/") + 1, image.indexOf(";base64"));
                        String fileName = "file." + imgExtension;
                        if (notEmpty(subject)) {
                            fileName = sanitizeFilename(subject) + (nthFile == 0 ? BuildConfig.VERSION_NAME : "_" + nthFile) + "." + imgExtension;
                        }
                        saveFile(Base64.decode(encodedImg, 0), dir, fileName);
                        localImage = "file://" + dir + "/" + fileName;
                    } else {
                        sendIntent.setType("text/plain");
                        return null;
                    }
                }
                if (!image.startsWith("file://")) {
                    throw new IllegalArgumentException("URL_NOT_SUPPORTED");
                }
                return Uri.parse(localImage);
            }
        }
        String filename = getFileName(image);
        localImage = "file://" + dir + "/" + filename;
        if (image.startsWith("http")) {
            URLConnection connection = new URL(image).openConnection();
            String disposition = connection.getHeaderField("Content-Disposition");
            if (disposition != null) {
                Matcher matcher = Pattern.compile("filename=([^;]+)").matcher(disposition);
                if (matcher.find()) {
                    filename = matcher.group(1).replaceAll("[^a-zA-Z0-9._-]", BuildConfig.VERSION_NAME);
                    localImage = "file://" + dir + "/" + filename;
                }
            }
            saveFile(getBytes(connection.getInputStream()), dir, filename);
        } else {
            saveFile(getBytes(this.webView.getContext().getAssets().open(image)), dir, filename);
        }
        return Uri.parse(localImage);
    }

    private boolean invokeSMSIntent(String message, String p_phonenumbers) {
        Intent intent;
        String phonenumbers = getPhoneNumbersWithManufacturerSpecificSeparators(p_phonenumbers);
        if (VERSION.SDK_INT >= 19) {
            intent = new Intent("android.intent.action.SENDTO");
            StringBuilder stringBuilder = new StringBuilder("smsto:");
            if (!notEmpty(phonenumbers)) {
                phonenumbers = BuildConfig.VERSION_NAME;
            }
            intent.setData(Uri.parse(stringBuilder.append(phonenumbers).toString()));
        } else {
            intent = new Intent("android.intent.action.VIEW");
            intent.setType("vnd.android-dir/mms-sms");
            if (phonenumbers != null) {
                intent.putExtra(PayPalOAuthScopes.PAYPAL_SCOPE_ADDRESS, phonenumbers);
            }
        }
        intent.putExtra("sms_body", message);
        try {
            this.cordova.startActivityForResult(this, intent, 0);
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        }
    }

    private static String getPhoneNumbersWithManufacturerSpecificSeparators(String phonenumbers) {
        if (!notEmpty(phonenumbers)) {
            return null;
        }
        char separator;
        if (Build.MANUFACTURER.equalsIgnoreCase("samsung")) {
            separator = ',';
        } else {
            separator = ';';
        }
        return phonenumbers.replace(';', separator).replace(',', separator);
    }

    private ActivityInfo getActivity(CallbackContext callbackContext, Intent shareIntent, String appPackageName) {
        List<ResolveInfo> activityList = this.webView.getContext().getPackageManager().queryIntentActivities(shareIntent, 0);
        for (ResolveInfo app : activityList) {
            if (app.activityInfo.packageName.contains(appPackageName)) {
                return app.activityInfo;
            }
        }
        callbackContext.sendPluginResult(new PluginResult(Status.ERROR, getShareActivities(activityList)));
        return null;
    }

    private JSONArray getShareActivities(List<ResolveInfo> activityList) {
        List<String> packages = new ArrayList();
        for (ResolveInfo app : activityList) {
            packages.add(app.activityInfo.packageName);
        }
        return new JSONArray(packages);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (ACTIVITY_CODE_SENDVIAEMAIL == requestCode) {
            super.onActivityResult(requestCode, resultCode, intent);
            this._callbackContext.success();
            return;
        }
        this._callbackContext.sendPluginResult(new PluginResult(Status.OK, resultCode == -1));
    }

    private void createOrCleanDir(String downloadDir) throws IOException {
        File dir = new File(downloadDir);
        if (dir.exists()) {
            cleanupOldFiles(dir);
        } else if (!dir.mkdirs()) {
            throw new IOException("CREATE_DIRS_FAILED");
        }
    }

    private String getFileName(String url) {
        int lastIndexOfSlash = url.lastIndexOf(47);
        return lastIndexOfSlash == -1 ? url : url.substring(lastIndexOfSlash + 1);
    }

    private byte[] getBytes(InputStream is) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayBuffer baf = new ByteArrayBuffer(5000);
        while (true) {
            int current = bis.read();
            if (current == -1) {
                return baf.toByteArray();
            }
            baf.append((byte) current);
        }
    }

    private void saveFile(byte[] bytes, String dirName, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File(new File(dirName), fileName));
        fos.write(bytes);
        fos.flush();
        fos.close();
    }

    private void cleanupOldFiles(File dir) {
        for (File f : dir.listFiles()) {
            f.delete();
        }
    }

    private static boolean notEmpty(String what) {
        return (what == null || BuildConfig.VERSION_NAME.equals(what) || "null".equalsIgnoreCase(what)) ? false : true;
    }

    private static String[] toStringArray(JSONArray jsonArray) throws JSONException {
        String[] result = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            result[i] = jsonArray.getString(i);
        }
        return result;
    }

    public static String sanitizeFilename(String name) {
        return name.replaceAll("[:\\\\/*?|<> ]", "_");
    }
}
