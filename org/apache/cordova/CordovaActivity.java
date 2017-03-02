package org.apache.cordova;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.ValueCallback;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.paypal.android.sdk.payments.BuildConfig;
import com.plugin.gcm.PushPlugin;
import com.squareup.okhttp.internal.http.HttpTransport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

public class CordovaActivity extends Activity implements CordovaInterface {
    private static int ACTIVITY_EXITING;
    private static int ACTIVITY_RUNNING;
    private static int ACTIVITY_STARTING;
    public static String TAG;
    protected CordovaPlugin activityResultCallback;
    protected boolean activityResultKeepRunning;
    private int activityState;
    protected CordovaWebView appView;
    protected Whitelist externalWhitelist;
    private String initCallbackClass;
    protected Whitelist internalWhitelist;
    protected boolean keepRunning;
    protected String launchUrl;
    protected int loadUrlTimeoutValue;
    protected ArrayList<PluginEntry> pluginEntries;
    protected CordovaPreferences preferences;
    @Deprecated
    protected LinearLayout root;
    protected ProgressDialog spinnerDialog;
    protected Dialog splashDialog;
    protected int splashscreen;
    protected int splashscreenTime;
    private final ExecutorService threadPool;
    @Deprecated
    protected CordovaWebViewClient webViewClient;

    /* renamed from: org.apache.cordova.CordovaActivity.1 */
    class C01771 implements OnCancelListener {
        private final /* synthetic */ CordovaActivity val$me;

        C01771(CordovaActivity cordovaActivity) {
            this.val$me = cordovaActivity;
        }

        public void onCancel(DialogInterface dialog) {
            this.val$me.spinnerDialog = null;
        }
    }

    /* renamed from: org.apache.cordova.CordovaActivity.2 */
    class C01782 implements Runnable {
        private final /* synthetic */ String val$errorUrl;
        private final /* synthetic */ CordovaActivity val$me;

        C01782(CordovaActivity cordovaActivity, String str) {
            this.val$me = cordovaActivity;
            this.val$errorUrl = str;
        }

        public void run() {
            this.val$me.spinnerStop();
            this.val$me.appView.showWebPage(this.val$errorUrl, false, true, null);
        }
    }

    /* renamed from: org.apache.cordova.CordovaActivity.3 */
    class C01793 implements Runnable {
        private final /* synthetic */ String val$description;
        private final /* synthetic */ boolean val$exit;
        private final /* synthetic */ String val$failingUrl;
        private final /* synthetic */ CordovaActivity val$me;

        C01793(boolean z, CordovaActivity cordovaActivity, String str, String str2) {
            this.val$exit = z;
            this.val$me = cordovaActivity;
            this.val$description = str;
            this.val$failingUrl = str2;
        }

        public void run() {
            if (this.val$exit) {
                this.val$me.appView.setVisibility(8);
                this.val$me.displayError("Application Error", this.val$description + " (" + this.val$failingUrl + ")", "OK", this.val$exit);
            }
        }
    }

    /* renamed from: org.apache.cordova.CordovaActivity.4 */
    class C01814 implements Runnable {
        private final /* synthetic */ String val$button;
        private final /* synthetic */ boolean val$exit;
        private final /* synthetic */ CordovaActivity val$me;
        private final /* synthetic */ String val$message;
        private final /* synthetic */ String val$title;

        /* renamed from: org.apache.cordova.CordovaActivity.4.1 */
        class C01801 implements OnClickListener {
            private final /* synthetic */ boolean val$exit;
            private final /* synthetic */ CordovaActivity val$me;

            C01801(boolean z, CordovaActivity cordovaActivity) {
                this.val$exit = z;
                this.val$me = cordovaActivity;
            }

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (this.val$exit) {
                    this.val$me.endActivity();
                }
            }
        }

        C01814(CordovaActivity cordovaActivity, String str, String str2, String str3, boolean z) {
            this.val$me = cordovaActivity;
            this.val$message = str;
            this.val$title = str2;
            this.val$button = str3;
            this.val$exit = z;
        }

        public void run() {
            try {
                Builder dlg = new Builder(this.val$me);
                dlg.setMessage(this.val$message);
                dlg.setTitle(this.val$title);
                dlg.setCancelable(false);
                dlg.setPositiveButton(this.val$button, new C01801(this.val$exit, this.val$me));
                dlg.create();
                dlg.show();
            } catch (Exception e) {
                CordovaActivity.this.finish();
            }
        }
    }

    /* renamed from: org.apache.cordova.CordovaActivity.5 */
    class C01835 implements Runnable {
        private final /* synthetic */ CordovaActivity val$that;
        private final /* synthetic */ int val$time;

        /* renamed from: org.apache.cordova.CordovaActivity.5.1 */
        class C01821 implements Runnable {
            C01821() {
            }

            public void run() {
                CordovaActivity.this.removeSplashScreen();
            }
        }

        C01835(CordovaActivity cordovaActivity, int i) {
            this.val$that = cordovaActivity;
            this.val$time = i;
        }

        public void run() {
            Display display = CordovaActivity.this.getWindowManager().getDefaultDisplay();
            LinearLayout root = new LinearLayout(this.val$that.getActivity());
            root.setMinimumHeight(display.getHeight());
            root.setMinimumWidth(display.getWidth());
            root.setOrientation(1);
            root.setBackgroundColor(CordovaActivity.this.preferences.getInteger("backgroundColor", ViewCompat.MEASURED_STATE_MASK));
            root.setLayoutParams(new LayoutParams(-1, -1, 0.0f));
            root.setBackgroundResource(this.val$that.splashscreen);
            CordovaActivity.this.splashDialog = new Dialog(this.val$that, 16973840);
            if ((CordovaActivity.this.getWindow().getAttributes().flags & HttpTransport.DEFAULT_CHUNK_LENGTH) == HttpTransport.DEFAULT_CHUNK_LENGTH) {
                CordovaActivity.this.splashDialog.getWindow().setFlags(HttpTransport.DEFAULT_CHUNK_LENGTH, HttpTransport.DEFAULT_CHUNK_LENGTH);
            }
            CordovaActivity.this.splashDialog.setContentView(root);
            CordovaActivity.this.splashDialog.setCancelable(false);
            CordovaActivity.this.splashDialog.show();
            new Handler().postDelayed(new C01821(), (long) this.val$time);
        }
    }

    public CordovaActivity() {
        this.spinnerDialog = null;
        this.threadPool = Executors.newCachedThreadPool();
        this.activityState = 0;
        this.activityResultCallback = null;
        this.splashscreen = 0;
        this.splashscreenTime = 3000;
        this.loadUrlTimeoutValue = 20000;
        this.keepRunning = true;
    }

    static {
        TAG = "CordovaActivity";
        ACTIVITY_STARTING = 0;
        ACTIVITY_RUNNING = 1;
        ACTIVITY_EXITING = 2;
    }

    public void setAuthenticationToken(AuthenticationToken authenticationToken, String host, String realm) {
        if (this.appView != null && this.appView.viewClient != null) {
            this.appView.viewClient.setAuthenticationToken(authenticationToken, host, realm);
        }
    }

    public AuthenticationToken removeAuthenticationToken(String host, String realm) {
        if (this.appView == null || this.appView.viewClient == null) {
            return null;
        }
        return this.appView.viewClient.removeAuthenticationToken(host, realm);
    }

    public AuthenticationToken getAuthenticationToken(String host, String realm) {
        if (this.appView == null || this.appView.viewClient == null) {
            return null;
        }
        return this.appView.viewClient.getAuthenticationToken(host, realm);
    }

    public void clearAuthenticationTokens() {
        if (this.appView != null && this.appView.viewClient != null) {
            this.appView.viewClient.clearAuthenticationTokens();
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        LOG.m831i(TAG, "Apache Cordova native platform version 3.6.3 is starting");
        LOG.m825d(TAG, "CordovaActivity.onCreate()");
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.initCallbackClass = savedInstanceState.getString("callbackClass");
        }
        loadConfig();
    }

    protected void loadConfig() {
        ConfigXmlParser parser = new ConfigXmlParser();
        parser.parse((Activity) this);
        this.preferences = parser.getPreferences();
        this.preferences.setPreferencesBundle(getIntent().getExtras());
        this.preferences.copyIntoIntentExtras(this);
        this.internalWhitelist = parser.getInternalWhitelist();
        this.externalWhitelist = parser.getExternalWhitelist();
        this.launchUrl = parser.getLaunchUrl();
        this.pluginEntries = parser.getPluginEntries();
        Config.parser = parser;
    }

    protected void createViews() {
        Display display = getWindowManager().getDefaultDisplay();
        this.root = new LinearLayoutSoftKeyboardDetect(this, display.getWidth(), display.getHeight());
        this.root.setOrientation(1);
        this.root.setLayoutParams(new LayoutParams(-1, -1, 0.0f));
        this.appView.setId(100);
        this.appView.setLayoutParams(new LayoutParams(-1, -1, 1.0f));
        this.appView.setVisibility(4);
        this.root.addView(this.appView);
        setContentView(this.root);
        this.root.setBackgroundColor(this.preferences.getInteger("BackgroundColor", ViewCompat.MEASURED_STATE_MASK));
    }

    public Activity getActivity() {
        return this;
    }

    protected CordovaWebView makeWebView() {
        return new CordovaWebView(this);
    }

    protected CordovaWebViewClient makeWebViewClient(CordovaWebView webView) {
        return webView.makeWebViewClient(this);
    }

    protected CordovaChromeClient makeChromeClient(CordovaWebView webView) {
        return webView.makeWebChromeClient(this);
    }

    public void init() {
        init(this.appView, null, null);
    }

    @SuppressLint({"NewApi"})
    @Deprecated
    public void init(CordovaWebView webView, CordovaWebViewClient webViewClient, CordovaChromeClient webChromeClient) {
        LOG.m825d(TAG, "CordovaActivity.init()");
        if (!this.preferences.getBoolean("ShowTitle", false)) {
            getWindow().requestFeature(1);
        }
        if (this.preferences.getBoolean("SetFullscreen", false)) {
            Log.d(TAG, "The SetFullscreen configuration is deprecated in favor of Fullscreen, and will be removed in a future version.");
            getWindow().setFlags(HttpTransport.DEFAULT_CHUNK_LENGTH, HttpTransport.DEFAULT_CHUNK_LENGTH);
        } else if (this.preferences.getBoolean("Fullscreen", false)) {
            getWindow().setFlags(HttpTransport.DEFAULT_CHUNK_LENGTH, HttpTransport.DEFAULT_CHUNK_LENGTH);
        } else {
            getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT);
        }
        if (webView == null) {
            webView = makeWebView();
        }
        this.appView = webView;
        if (this.appView.pluginManager == null) {
            this.appView.init(this, webViewClient != null ? webViewClient : makeWebViewClient(this.appView), webChromeClient != null ? webChromeClient : makeChromeClient(this.appView), this.pluginEntries, this.internalWhitelist, this.externalWhitelist, this.preferences);
        }
        if (this.preferences.getBoolean("DisallowOverscroll", false)) {
            this.appView.setOverScrollMode(2);
        }
        createViews();
        setVolumeControlStream(3);
    }

    public void loadUrl(String url) {
        if (this.appView == null) {
            init();
        }
        this.splashscreenTime = this.preferences.getInteger("SplashScreenDelay", this.splashscreenTime);
        String splash = this.preferences.getString("SplashScreen", null);
        if (this.splashscreenTime > 0 && splash != null) {
            this.splashscreen = getResources().getIdentifier(splash, "drawable", getClass().getPackage().getName());
            if (this.splashscreen != 0) {
                showSplashScreen(this.splashscreenTime);
            }
        }
        this.keepRunning = this.preferences.getBoolean("KeepRunning", true);
        if (this.appView.getParent() != null) {
            loadSpinner();
        }
        if (this.splashscreen != 0) {
            this.appView.loadUrl(url, this.splashscreenTime);
        } else {
            this.appView.loadUrl(url);
        }
    }

    public void loadUrl(String url, int time) {
        this.splashscreenTime = time;
        loadUrl(url);
    }

    void loadSpinner() {
        String loading;
        if (this.appView == null || !this.appView.canGoBack()) {
            loading = this.preferences.getString("LoadingDialog", null);
        } else {
            loading = this.preferences.getString("LoadingPageDialog", null);
        }
        if (loading != null) {
            String title = BuildConfig.VERSION_NAME;
            String message = "Loading Application...";
            if (loading.length() > 0) {
                int comma = loading.indexOf(44);
                if (comma > 0) {
                    title = loading.substring(0, comma);
                    message = loading.substring(comma + 1);
                } else {
                    title = BuildConfig.VERSION_NAME;
                    message = loading;
                }
            }
            spinnerStart(title, message);
        }
    }

    @Deprecated
    public void cancelLoadUrl() {
    }

    @Deprecated
    public void clearCache() {
        if (this.appView == null) {
            init();
        }
        this.appView.clearCache(true);
    }

    @Deprecated
    public void clearHistory() {
        this.appView.clearHistory();
    }

    @Deprecated
    public boolean backHistory() {
        if (this.appView != null) {
            return this.appView.backHistory();
        }
        return false;
    }

    @Deprecated
    public boolean getBooleanProperty(String name, boolean defaultValue) {
        return this.preferences.getBoolean(name, defaultValue);
    }

    @Deprecated
    public int getIntegerProperty(String name, int defaultValue) {
        return this.preferences.getInteger(name, defaultValue);
    }

    @Deprecated
    public String getStringProperty(String name, String defaultValue) {
        return this.preferences.getString(name, defaultValue);
    }

    @Deprecated
    public double getDoubleProperty(String name, double defaultValue) {
        return this.preferences.getDouble(name, defaultValue);
    }

    @Deprecated
    public void setBooleanProperty(String name, boolean value) {
        Log.d(TAG, "Setting boolean properties in CordovaActivity will be deprecated in 3.0 on July 2013, please use config.xml");
        getIntent().putExtra(name.toLowerCase(), value);
    }

    @Deprecated
    public void setIntegerProperty(String name, int value) {
        Log.d(TAG, "Setting integer properties in CordovaActivity will be deprecated in 3.0 on July 2013, please use config.xml");
        getIntent().putExtra(name.toLowerCase(), value);
    }

    @Deprecated
    public void setStringProperty(String name, String value) {
        Log.d(TAG, "Setting string properties in CordovaActivity will be deprecated in 3.0 on July 2013, please use config.xml");
        getIntent().putExtra(name.toLowerCase(), value);
    }

    @Deprecated
    public void setDoubleProperty(String name, double value) {
        Log.d(TAG, "Setting double properties in CordovaActivity will be deprecated in 3.0 on July 2013, please use config.xml");
        getIntent().putExtra(name.toLowerCase(), value);
    }

    protected void onPause() {
        super.onPause();
        LOG.m825d(TAG, "Paused the application!");
        if (this.activityState != ACTIVITY_EXITING && this.appView != null) {
            this.appView.handlePause(this.keepRunning);
            removeSplashScreen();
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (this.appView != null) {
            this.appView.onNewIntent(intent);
        }
    }

    protected void onResume() {
        super.onResume();
        LOG.m825d(TAG, "Resuming the App");
        if (this.activityState == ACTIVITY_STARTING) {
            this.activityState = ACTIVITY_RUNNING;
        } else if (this.appView != null) {
            getWindow().getDecorView().requestFocus();
            this.appView.handleResume(this.keepRunning, this.activityResultKeepRunning);
            if ((!this.keepRunning || this.activityResultKeepRunning) && this.activityResultKeepRunning) {
                this.keepRunning = this.activityResultKeepRunning;
                this.activityResultKeepRunning = false;
            }
        }
    }

    public void onDestroy() {
        LOG.m825d(TAG, "CordovaActivity.onDestroy()");
        super.onDestroy();
        removeSplashScreen();
        if (this.appView != null) {
            this.appView.handleDestroy();
        } else {
            this.activityState = ACTIVITY_EXITING;
        }
    }

    public void postMessage(String id, Object data) {
        if (this.appView != null) {
            this.appView.postMessage(id, data);
        }
    }

    @Deprecated
    public void addService(String serviceType, String className) {
        if (this.appView != null && this.appView.pluginManager != null) {
            this.appView.pluginManager.addService(serviceType, className);
        }
    }

    @Deprecated
    public void sendJavascript(String statement) {
        if (this.appView != null) {
            this.appView.bridge.getMessageQueue().addJavaScript(statement);
        }
    }

    public void spinnerStart(String title, String message) {
        if (this.spinnerDialog != null) {
            this.spinnerDialog.dismiss();
            this.spinnerDialog = null;
        }
        this.spinnerDialog = ProgressDialog.show(this, title, message, true, true, new C01771(this));
    }

    public void spinnerStop() {
        if (this.spinnerDialog != null && this.spinnerDialog.isShowing()) {
            this.spinnerDialog.dismiss();
            this.spinnerDialog = null;
        }
    }

    public void endActivity() {
        this.activityState = ACTIVITY_EXITING;
        super.finish();
    }

    public void startActivityForResult(CordovaPlugin command, Intent intent, int requestCode) {
        this.activityResultCallback = command;
        this.activityResultKeepRunning = this.keepRunning;
        if (command != null) {
            this.keepRunning = false;
        }
        super.startActivityForResult(intent, requestCode);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        LOG.m825d(TAG, "Incoming Result");
        super.onActivityResult(requestCode, resultCode, intent);
        Log.d(TAG, "Request code = " + requestCode);
        if (this.appView != null && requestCode == CordovaChromeClient.FILECHOOSER_RESULTCODE) {
            ValueCallback<Uri> mUploadMessage = this.appView.getWebChromeClient().getValueCallback();
            Log.d(TAG, "did we get here?");
            if (mUploadMessage != null) {
                Uri result = (intent == null || resultCode != -1) ? null : intent.getData();
                Log.d(TAG, "result = " + result);
                mUploadMessage.onReceiveValue(result);
            } else {
                return;
            }
        }
        CordovaPlugin callback = this.activityResultCallback;
        if (callback == null && this.initCallbackClass != null) {
            this.activityResultCallback = this.appView.pluginManager.getPlugin(this.initCallbackClass);
            callback = this.activityResultCallback;
        }
        if (callback != null) {
            LOG.m825d(TAG, "We have a callback to send this result to");
            callback.onActivityResult(requestCode, resultCode, intent);
        }
    }

    public void setActivityResultCallback(CordovaPlugin plugin) {
        this.activityResultCallback = plugin;
    }

    public void onReceivedError(int errorCode, String description, String failingUrl) {
        CordovaActivity me = this;
        String errorUrl = this.preferences.getString("errorUrl", null);
        if (errorUrl == null || (!(errorUrl.startsWith("file://") || this.internalWhitelist.isUrlWhiteListed(errorUrl)) || failingUrl.equals(errorUrl))) {
            runOnUiThread(new C01793(errorCode != -2, me, description, failingUrl));
        } else {
            runOnUiThread(new C01782(me, errorUrl));
        }
    }

    public void displayError(String title, String message, String button, boolean exit) {
        runOnUiThread(new C01814(this, message, title, button, exit));
    }

    @Deprecated
    public boolean isUrlWhiteListed(String url) {
        return this.internalWhitelist.isUrlWhiteListed(url);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        postMessage("onCreateOptionsMenu", menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        postMessage("onPrepareOptionsMenu", menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        postMessage("onOptionsItemSelected", item);
        return true;
    }

    @Deprecated
    public Context getContext() {
        LOG.m825d(TAG, "This will be deprecated December 2012");
        return this;
    }

    @Deprecated
    public void showWebPage(String url, boolean openExternal, boolean clearHistory, HashMap<String, Object> params) {
        if (this.appView != null) {
            this.appView.showWebPage(url, openExternal, clearHistory, params);
        }
    }

    public void removeSplashScreen() {
        if (this.splashDialog != null && this.splashDialog.isShowing()) {
            this.splashDialog.dismiss();
            this.splashDialog = null;
        }
    }

    protected void showSplashScreen(int time) {
        runOnUiThread(new C01835(this, time));
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (this.appView == null || ((!this.appView.isCustomViewShowing() && this.appView.getFocusedChild() == null) || (keyCode != 4 && keyCode != 82))) {
            return super.onKeyUp(keyCode, event);
        }
        return this.appView.onKeyUp(keyCode, event);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (this.appView == null || this.appView.getFocusedChild() == null || (keyCode != 4 && keyCode != 82)) {
            return super.onKeyDown(keyCode, event);
        }
        return this.appView.onKeyDown(keyCode, event);
    }

    public Object onMessage(String id, Object data) {
        if (!"onScrollChanged".equals(id)) {
            LOG.m825d(TAG, "onMessage(" + id + "," + data + ")");
        }
        if ("splashscreen".equals(id)) {
            if ("hide".equals(data.toString())) {
                removeSplashScreen();
            } else if (this.splashDialog == null || !this.splashDialog.isShowing()) {
                String splashResource = this.preferences.getString("SplashScreen", null);
                if (splashResource != null) {
                    this.splashscreen = getResources().getIdentifier(splashResource, "drawable", getClass().getPackage().getName());
                }
                showSplashScreen(this.splashscreenTime);
            }
        } else if ("spinner".equals(id)) {
            if ("stop".equals(data.toString())) {
                spinnerStop();
                this.appView.setVisibility(0);
            }
        } else if ("onReceivedError".equals(id)) {
            JSONObject d = (JSONObject) data;
            try {
                onReceivedError(d.getInt("errorCode"), d.getString("description"), d.getString("url"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (PushPlugin.EXIT.equals(id)) {
            endActivity();
        }
        return null;
    }

    public ExecutorService getThreadPool() {
        return this.threadPool;
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.activityResultCallback != null) {
            outState.putString("callbackClass", this.activityResultCallback.getClass().getName());
        }
    }
}
