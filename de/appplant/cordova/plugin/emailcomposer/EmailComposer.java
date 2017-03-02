package de.appplant.cordova.plugin.emailcomposer;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import android.text.Html;
import android.util.Base64;
import com.paypal.android.sdk.payments.BuildConfig;
import com.squareup.okhttp.internal.http.HttpTransport;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EmailComposer extends CordovaPlugin {
    protected static final String STORAGE_FOLDER;
    private CallbackContext command;

    /* renamed from: de.appplant.cordova.plugin.emailcomposer.EmailComposer.1 */
    class C01471 implements Runnable {
        private final /* synthetic */ Intent val$draft;
        private final /* synthetic */ EmailComposer val$plugin;

        C01471(EmailComposer emailComposer, Intent intent) {
            this.val$plugin = emailComposer;
            this.val$draft = intent;
        }

        public void run() {
            EmailComposer.this.cordova.startActivityForResult(this.val$plugin, Intent.createChooser(this.val$draft, "Select Email App"), 0);
        }
    }

    static {
        STORAGE_FOLDER = File.separator + "email_composer";
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.command = callbackContext;
        if ("open".equals(action)) {
            open(args);
            return true;
        } else if (!"isServiceAvailable".equals(action)) {
            return false;
        } else {
            isServiceAvailable();
            return true;
        }
    }

    private void isServiceAvailable() {
        this.command.sendPluginResult(new PluginResult(Status.OK, isEmailAccountConfigured().booleanValue()));
    }

    private void open(JSONArray args) throws JSONException {
        openDraft(getDraftWithProperties(args.getJSONObject(0)));
    }

    private Intent getDraftWithProperties(JSONObject params) throws JSONException {
        Intent mail = new Intent("android.intent.action.SEND_MULTIPLE");
        if (params.has("subject")) {
            setSubject(params.getString("subject"), mail);
        }
        if (params.has("body")) {
            setBody(params.getString("body"), Boolean.valueOf(params.optBoolean("isHtml")), mail);
        }
        if (params.has("to")) {
            setRecipients(params.getJSONArray("to"), mail);
        }
        if (params.has("cc")) {
            setCcRecipients(params.getJSONArray("cc"), mail);
        }
        if (params.has("bcc")) {
            setBccRecipients(params.getJSONArray("bcc"), mail);
        }
        if (params.has("attachments")) {
            setAttachments(params.getJSONArray("attachments"), mail);
        }
        mail.setType("application/octet-stream");
        return mail;
    }

    private void openDraft(Intent draft) {
        this.cordova.getThreadPool().execute(new C01471(this, draft));
    }

    private void setSubject(String subject, Intent draft) {
        draft.putExtra("android.intent.extra.SUBJECT", subject);
    }

    private void setBody(String body, Boolean isHTML, Intent draft) {
        if (isHTML.booleanValue()) {
            draft.putExtra("android.intent.extra.TEXT", Html.fromHtml(body));
            draft.setType("text/html");
            return;
        }
        draft.putExtra("android.intent.extra.TEXT", body);
        draft.setType("text/plain");
    }

    private void setRecipients(JSONArray recipients, Intent draft) throws JSONException {
        String[] receivers = new String[recipients.length()];
        for (int i = 0; i < recipients.length(); i++) {
            receivers[i] = recipients.getString(i);
        }
        draft.putExtra("android.intent.extra.EMAIL", receivers);
    }

    private void setCcRecipients(JSONArray ccRecipients, Intent draft) throws JSONException {
        String[] receivers = new String[ccRecipients.length()];
        for (int i = 0; i < ccRecipients.length(); i++) {
            receivers[i] = ccRecipients.getString(i);
        }
        draft.putExtra("android.intent.extra.CC", receivers);
    }

    private void setBccRecipients(JSONArray bccRecipients, Intent draft) throws JSONException {
        String[] receivers = new String[bccRecipients.length()];
        for (int i = 0; i < bccRecipients.length(); i++) {
            receivers[i] = bccRecipients.getString(i);
        }
        draft.putExtra("android.intent.extra.BCC", receivers);
    }

    private void setAttachments(JSONArray attachments, Intent draft) throws JSONException {
        ArrayList<Uri> attachmentUris = new ArrayList();
        for (int i = 0; i < attachments.length(); i++) {
            attachmentUris.add(getUriForPath(attachments.getString(i)));
        }
        draft.putParcelableArrayListExtra("android.intent.extra.STREAM", attachmentUris);
    }

    private Boolean isEmailAccountConfigured() {
        boolean z = true;
        if (this.cordova.getActivity().getPackageManager().queryIntentActivities(new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", "max@mustermann.com", null)), 0).size() <= 1) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    private Uri getUriForPath(String path) {
        if (path.startsWith("res:")) {
            return getUriForResourcePath(path);
        }
        if (path.startsWith("file:")) {
            return getUriForAbsolutePath(path);
        }
        if (path.startsWith("www:")) {
            return getUriForAssetPath(path);
        }
        if (path.startsWith("base64:")) {
            return getUriForBase64Content(path);
        }
        return Uri.parse(path);
    }

    private Uri getUriForAbsolutePath(String path) {
        File file = new File(path.replaceFirst("file://", BuildConfig.VERSION_NAME));
        if (!file.exists()) {
            System.err.println("Attachment path not found: " + file.getAbsolutePath());
        }
        return Uri.fromFile(file);
    }

    private Uri getUriForAssetPath(String path) {
        String resPath = path.replaceFirst("www:/", "www");
        String fileName = resPath.substring(resPath.lastIndexOf(47) + 1);
        String storage = new StringBuilder(String.valueOf(this.cordova.getActivity().getExternalCacheDir().toString())).append(STORAGE_FOLDER).toString();
        File file = new File(storage, fileName);
        new File(storage).mkdir();
        try {
            AssetManager assets = this.cordova.getActivity().getAssets();
            FileOutputStream outStream = new FileOutputStream(file);
            copyFile(assets.open(resPath), outStream);
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            System.err.println("Attachment asset not found: assets/" + resPath);
            e.printStackTrace();
        }
        return Uri.fromFile(file);
    }

    private Uri getUriForResourcePath(String path) {
        String resPath = path.replaceFirst("res://", BuildConfig.VERSION_NAME);
        String fileName = resPath.substring(resPath.lastIndexOf(47) + 1);
        String resName = fileName.substring(0, fileName.lastIndexOf(46));
        String extension = resPath.substring(resPath.lastIndexOf(46));
        String storage = new StringBuilder(String.valueOf(this.cordova.getActivity().getExternalCacheDir().toString())).append(STORAGE_FOLDER).toString();
        int resId = getResId(resPath);
        File file = new File(storage, new StringBuilder(String.valueOf(resName)).append(extension).toString());
        if (resId == 0) {
            System.err.println("Attachment resource not found: " + resPath);
        }
        new File(storage).mkdir();
        try {
            Resources res = this.cordova.getActivity().getResources();
            FileOutputStream outStream = new FileOutputStream(file);
            copyFile(res.openRawResource(resId), outStream);
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Uri.fromFile(file);
    }

    private Uri getUriForBase64Content(String content) {
        String resName = content.substring(content.indexOf(":") + 1, content.indexOf("//"));
        byte[] bytes = Base64.decode(content.substring(content.indexOf("//") + 2), 0);
        String storage = this.cordova.getActivity().getCacheDir() + STORAGE_FOLDER;
        File file = new File(storage, resName);
        new File(storage).mkdir();
        try {
            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write(bytes);
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Uri.parse("content://" + (getPackageName() + AttachmentProvider.AUTHORITY + "/" + resName));
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[HttpTransport.DEFAULT_CHUNK_LENGTH];
        while (true) {
            int read = in.read(buffer);
            if (read != -1) {
                out.write(buffer, 0, read);
            } else {
                return;
            }
        }
    }

    private int getResId(String resPath) {
        Resources res = this.cordova.getActivity().getResources();
        String pkgName = getPackageName();
        String dirName = resPath.substring(0, resPath.lastIndexOf(47));
        String fileName = resPath.substring(resPath.lastIndexOf(47) + 1);
        return res.getIdentifier(fileName.substring(0, fileName.lastIndexOf(46)), dirName, pkgName);
    }

    private String getPackageName() {
        return this.cordova.getActivity().getPackageName();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        this.command.success();
    }
}
