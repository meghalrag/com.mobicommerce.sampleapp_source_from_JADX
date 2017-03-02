package org.apache.cordova;

import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebStorage.QuotaUpdater;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout.LayoutParams;

public class CordovaChromeClient extends WebChromeClient {
    public static final int FILECHOOSER_RESULTCODE = 5173;
    private long MAX_QUOTA;
    private String TAG;
    protected CordovaWebView appView;
    protected CordovaInterface cordova;
    public ValueCallback<Uri> mUploadMessage;
    private View mVideoProgressView;

    /* renamed from: org.apache.cordova.CordovaChromeClient.1 */
    class C01841 implements OnClickListener {
        private final /* synthetic */ JsResult val$result;

        C01841(JsResult jsResult) {
            this.val$result = jsResult;
        }

        public void onClick(DialogInterface dialog, int which) {
            this.val$result.confirm();
        }
    }

    /* renamed from: org.apache.cordova.CordovaChromeClient.2 */
    class C01852 implements OnCancelListener {
        private final /* synthetic */ JsResult val$result;

        C01852(JsResult jsResult) {
            this.val$result = jsResult;
        }

        public void onCancel(DialogInterface dialog) {
            this.val$result.cancel();
        }
    }

    /* renamed from: org.apache.cordova.CordovaChromeClient.3 */
    class C01863 implements OnKeyListener {
        private final /* synthetic */ JsResult val$result;

        C01863(JsResult jsResult) {
            this.val$result = jsResult;
        }

        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode != 4) {
                return true;
            }
            this.val$result.confirm();
            return false;
        }
    }

    /* renamed from: org.apache.cordova.CordovaChromeClient.4 */
    class C01874 implements OnClickListener {
        private final /* synthetic */ JsResult val$result;

        C01874(JsResult jsResult) {
            this.val$result = jsResult;
        }

        public void onClick(DialogInterface dialog, int which) {
            this.val$result.confirm();
        }
    }

    /* renamed from: org.apache.cordova.CordovaChromeClient.5 */
    class C01885 implements OnClickListener {
        private final /* synthetic */ JsResult val$result;

        C01885(JsResult jsResult) {
            this.val$result = jsResult;
        }

        public void onClick(DialogInterface dialog, int which) {
            this.val$result.cancel();
        }
    }

    /* renamed from: org.apache.cordova.CordovaChromeClient.6 */
    class C01896 implements OnCancelListener {
        private final /* synthetic */ JsResult val$result;

        C01896(JsResult jsResult) {
            this.val$result = jsResult;
        }

        public void onCancel(DialogInterface dialog) {
            this.val$result.cancel();
        }
    }

    /* renamed from: org.apache.cordova.CordovaChromeClient.7 */
    class C01907 implements OnKeyListener {
        private final /* synthetic */ JsResult val$result;

        C01907(JsResult jsResult) {
            this.val$result = jsResult;
        }

        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode != 4) {
                return true;
            }
            this.val$result.cancel();
            return false;
        }
    }

    /* renamed from: org.apache.cordova.CordovaChromeClient.8 */
    class C01918 implements OnClickListener {
        private final /* synthetic */ EditText val$input;
        private final /* synthetic */ JsPromptResult val$res;

        C01918(EditText editText, JsPromptResult jsPromptResult) {
            this.val$input = editText;
            this.val$res = jsPromptResult;
        }

        public void onClick(DialogInterface dialog, int which) {
            this.val$res.confirm(this.val$input.getText().toString());
        }
    }

    /* renamed from: org.apache.cordova.CordovaChromeClient.9 */
    class C01929 implements OnClickListener {
        private final /* synthetic */ JsPromptResult val$res;

        C01929(JsPromptResult jsPromptResult) {
            this.val$res = jsPromptResult;
        }

        public void onClick(DialogInterface dialog, int which) {
            this.val$res.cancel();
        }
    }

    @Deprecated
    public CordovaChromeClient(CordovaInterface cordova) {
        this.TAG = "CordovaLog";
        this.MAX_QUOTA = 104857600;
        this.cordova = cordova;
    }

    public CordovaChromeClient(CordovaInterface ctx, CordovaWebView app) {
        this.TAG = "CordovaLog";
        this.MAX_QUOTA = 104857600;
        this.cordova = ctx;
        this.appView = app;
    }

    @Deprecated
    public void setWebView(CordovaWebView view) {
        this.appView = view;
    }

    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        Builder dlg = new Builder(this.cordova.getActivity());
        dlg.setMessage(message);
        dlg.setTitle("Alert");
        dlg.setCancelable(true);
        dlg.setPositiveButton(17039370, new C01841(result));
        dlg.setOnCancelListener(new C01852(result));
        dlg.setOnKeyListener(new C01863(result));
        dlg.show();
        return true;
    }

    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        Builder dlg = new Builder(this.cordova.getActivity());
        dlg.setMessage(message);
        dlg.setTitle("Confirm");
        dlg.setCancelable(true);
        dlg.setPositiveButton(17039370, new C01874(result));
        dlg.setNegativeButton(17039360, new C01885(result));
        dlg.setOnCancelListener(new C01896(result));
        dlg.setOnKeyListener(new C01907(result));
        dlg.show();
        return true;
    }

    public boolean onJsPrompt(WebView view, String origin, String message, String defaultValue, JsPromptResult result) {
        String handledRet = this.appView.bridge.promptOnJsPrompt(origin, message, defaultValue);
        if (handledRet != null) {
            result.confirm(handledRet);
        } else {
            JsPromptResult res = result;
            Builder dlg = new Builder(this.cordova.getActivity());
            dlg.setMessage(message);
            EditText input = new EditText(this.cordova.getActivity());
            if (defaultValue != null) {
                input.setText(defaultValue);
            }
            dlg.setView(input);
            dlg.setCancelable(false);
            dlg.setPositiveButton(17039370, new C01918(input, res));
            dlg.setNegativeButton(17039360, new C01929(res));
            dlg.show();
        }
        return true;
    }

    public void onExceededDatabaseQuota(String url, String databaseIdentifier, long currentQuota, long estimatedSize, long totalUsedQuota, QuotaUpdater quotaUpdater) {
        LOG.m827d(this.TAG, "onExceededDatabaseQuota estimatedSize: %d  currentQuota: %d  totalUsedQuota: %d", Long.valueOf(estimatedSize), Long.valueOf(currentQuota), Long.valueOf(totalUsedQuota));
        quotaUpdater.updateQuota(this.MAX_QUOTA);
    }

    public void onConsoleMessage(String message, int lineNumber, String sourceID) {
        if (VERSION.SDK_INT == 7) {
            LOG.m827d(this.TAG, "%s: Line %d : %s", sourceID, Integer.valueOf(lineNumber), message);
            super.onConsoleMessage(message, lineNumber, sourceID);
        }
    }

    @TargetApi(8)
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        if (consoleMessage.message() != null) {
            LOG.m827d(this.TAG, "%s: Line %d : %s", consoleMessage.sourceId(), Integer.valueOf(consoleMessage.lineNumber()), consoleMessage.message());
        }
        return super.onConsoleMessage(consoleMessage);
    }

    public void onGeolocationPermissionsShowPrompt(String origin, Callback callback) {
        super.onGeolocationPermissionsShowPrompt(origin, callback);
        callback.invoke(origin, true, false);
    }

    public void onShowCustomView(View view, CustomViewCallback callback) {
        this.appView.showCustomView(view, callback);
    }

    public void onHideCustomView() {
        this.appView.hideCustomView();
    }

    public View getVideoLoadingProgressView() {
        if (this.mVideoProgressView == null) {
            LinearLayout layout = new LinearLayout(this.appView.getContext());
            layout.setOrientation(1);
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.addRule(13);
            layout.setLayoutParams(layoutParams);
            ProgressBar bar = new ProgressBar(this.appView.getContext());
            LinearLayout.LayoutParams barLayoutParams = new LinearLayout.LayoutParams(-2, -2);
            barLayoutParams.gravity = 17;
            bar.setLayoutParams(barLayoutParams);
            layout.addView(bar);
            this.mVideoProgressView = layout;
        }
        return this.mVideoProgressView;
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
        openFileChooser(uploadMsg, "*/*");
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
        openFileChooser(uploadMsg, acceptType, null);
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        this.mUploadMessage = uploadMsg;
        Intent i = new Intent("android.intent.action.GET_CONTENT");
        i.addCategory("android.intent.category.OPENABLE");
        i.setType("*/*");
        this.cordova.getActivity().startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
    }

    public ValueCallback<Uri> getValueCallback() {
        return this.mUploadMessage;
    }
}
