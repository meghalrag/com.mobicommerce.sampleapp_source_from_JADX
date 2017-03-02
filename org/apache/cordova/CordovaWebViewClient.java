package org.apache.cordova;

import android.annotation.TargetApi;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.support.v4.media.TransportMediator;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.paypal.android.sdk.payments.BuildConfig;
import com.plugin.gcm.PushPlugin;
import java.util.Hashtable;
import org.json.JSONException;
import org.json.JSONObject;

public class CordovaWebViewClient extends WebViewClient {
    private static final String TAG = "CordovaWebViewClient";
    CordovaWebView appView;
    private Hashtable<String, AuthenticationToken> authenticationTokens;
    CordovaInterface cordova;
    private boolean doClearHistory;
    CordovaUriHelper helper;
    boolean isCurrentlyLoading;

    /* renamed from: org.apache.cordova.CordovaWebViewClient.1 */
    class C01981 implements Runnable {

        /* renamed from: org.apache.cordova.CordovaWebViewClient.1.1 */
        class C01971 implements Runnable {
            C01971() {
            }

            public void run() {
                CordovaWebViewClient.this.appView.postMessage("spinner", "stop");
            }
        }

        C01981() {
        }

        public void run() {
            try {
                Thread.sleep(2000);
                CordovaWebViewClient.this.cordova.getActivity().runOnUiThread(new C01971());
            } catch (InterruptedException e) {
            }
        }
    }

    @Deprecated
    public CordovaWebViewClient(CordovaInterface cordova) {
        this.doClearHistory = false;
        this.authenticationTokens = new Hashtable();
        this.cordova = cordova;
    }

    public CordovaWebViewClient(CordovaInterface cordova, CordovaWebView view) {
        this.doClearHistory = false;
        this.authenticationTokens = new Hashtable();
        this.cordova = cordova;
        this.appView = view;
        this.helper = new CordovaUriHelper(cordova, view);
    }

    @Deprecated
    public void setWebView(CordovaWebView view) {
        this.appView = view;
        this.helper = new CordovaUriHelper(this.cordova, view);
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return this.helper.shouldOverrideUrlLoading(view, url);
    }

    public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
        AuthenticationToken token = getAuthenticationToken(host, realm);
        if (token != null) {
            handler.proceed(token.getUserName(), token.getPassword());
        } else {
            super.onReceivedHttpAuthRequest(view, handler, host, realm);
        }
    }

    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        this.isCurrentlyLoading = true;
        LOG.m825d(TAG, "onPageStarted(" + url + ")");
        this.appView.bridge.reset(url);
        this.appView.postMessage("onPageStarted", url);
        if (this.appView.pluginManager != null) {
            this.appView.pluginManager.onReset();
        }
    }

    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (this.isCurrentlyLoading) {
            this.isCurrentlyLoading = false;
            LOG.m825d(TAG, "onPageFinished(" + url + ")");
            if (this.doClearHistory) {
                view.clearHistory();
                this.doClearHistory = false;
            }
            CordovaWebView cordovaWebView = this.appView;
            cordovaWebView.loadUrlTimeout++;
            this.appView.postMessage("onPageFinished", url);
            if (this.appView.getVisibility() == 4) {
                new Thread(new C01981()).start();
            }
            if (url.equals("about:blank")) {
                this.appView.postMessage(PushPlugin.EXIT, null);
            }
        }
    }

    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        if (this.isCurrentlyLoading) {
            LOG.m827d(TAG, "CordovaWebViewClient.onReceivedError: Error code=%s Description=%s URL=%s", Integer.valueOf(errorCode), description, failingUrl);
            CordovaWebView cordovaWebView = this.appView;
            cordovaWebView.loadUrlTimeout++;
            if (errorCode == -10) {
                if (view.canGoBack()) {
                    view.goBack();
                    return;
                }
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
            JSONObject data = new JSONObject();
            try {
                data.put("errorCode", errorCode);
                data.put("description", description);
                data.put("url", failingUrl);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.appView.postMessage("onReceivedError", data);
        }
    }

    @TargetApi(8)
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        try {
            if ((this.cordova.getActivity().getPackageManager().getApplicationInfo(this.cordova.getActivity().getPackageName(), TransportMediator.FLAG_KEY_MEDIA_NEXT).flags & 2) != 0) {
                handler.proceed();
            } else {
                super.onReceivedSslError(view, handler, error);
            }
        } catch (NameNotFoundException e) {
            super.onReceivedSslError(view, handler, error);
        }
    }

    public void setAuthenticationToken(AuthenticationToken authenticationToken, String host, String realm) {
        if (host == null) {
            host = BuildConfig.VERSION_NAME;
        }
        if (realm == null) {
            realm = BuildConfig.VERSION_NAME;
        }
        this.authenticationTokens.put(host.concat(realm), authenticationToken);
    }

    public AuthenticationToken removeAuthenticationToken(String host, String realm) {
        return (AuthenticationToken) this.authenticationTokens.remove(host.concat(realm));
    }

    public AuthenticationToken getAuthenticationToken(String host, String realm) {
        AuthenticationToken token = (AuthenticationToken) this.authenticationTokens.get(host.concat(realm));
        if (token != null) {
            return token;
        }
        token = (AuthenticationToken) this.authenticationTokens.get(host);
        if (token == null) {
            token = (AuthenticationToken) this.authenticationTokens.get(realm);
        }
        if (token == null) {
            return (AuthenticationToken) this.authenticationTokens.get(BuildConfig.VERSION_NAME);
        }
        return token;
    }

    public void clearAuthenticationTokens() {
        this.authenticationTokens.clear();
    }
}
