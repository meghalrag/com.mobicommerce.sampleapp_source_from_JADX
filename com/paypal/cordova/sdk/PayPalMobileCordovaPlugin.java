package com.paypal.cordova.sdk;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import com.paypal.android.sdk.payments.PayPalAuthorization;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalFuturePaymentActivity;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalPaymentDetails;
import com.paypal.android.sdk.payments.PayPalProfileSharingActivity;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.paypal.android.sdk.payments.Version;
import java.math.BigDecimal;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PayPalMobileCordovaPlugin extends CordovaPlugin {
    private static final int REQUEST_CODE_FUTURE_PAYMENT = 2;
    private static final int REQUEST_SINGLE_PAYMENT = 1;
    private Activity activity;
    private CallbackContext callbackContext;
    private PayPalConfiguration configuration;
    private String environment;
    private String productionClientId;
    private String sandboxClientId;
    private boolean serverStarted;

    public PayPalMobileCordovaPlugin() {
        this.environment = PayPalConfiguration.ENVIRONMENT_PRODUCTION;
        this.productionClientId = null;
        this.sandboxClientId = null;
        this.configuration = new PayPalConfiguration();
        this.activity = null;
        this.serverStarted = false;
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        this.activity = this.cordova.getActivity();
        if (action.equals("version")) {
            version();
            return true;
        } else if (action.equals("init")) {
            init(args);
            return true;
        } else if (action.equals("prepareToRender")) {
            prepareToRender(args);
            return true;
        } else if (action.equals("applicationCorrelationIDForEnvironment")) {
            applicationCorrelationIDForEnvironment(args);
            return true;
        } else if (action.equals("renderSinglePaymentUI")) {
            renderSinglePaymentUI(args);
            return true;
        } else if (!action.equals("renderFuturePaymentUI")) {
            return false;
        } else {
            renderFuturePaymentUI(args);
            return true;
        }
    }

    public void onDestroy() {
        if (this.activity != null && this.serverStarted) {
            this.activity.stopService(new Intent(this.activity, PayPalService.class));
        }
        super.onDestroy();
    }

    private void version() {
        this.callbackContext.success(Version.PRODUCT_VERSION);
    }

    private void init(JSONArray args) throws JSONException {
        JSONObject jObject = args.getJSONObject(0);
        this.productionClientId = jObject.getString("PayPalEnvironmentProduction");
        this.sandboxClientId = jObject.getString("PayPalEnvironmentSandbox");
        this.callbackContext.success();
    }

    private void prepareToRender(JSONArray args) throws JSONException {
        String env = args.getString(0);
        if (env.equalsIgnoreCase("PayPalEnvironmentNoNetwork")) {
            this.environment = PayPalConfiguration.ENVIRONMENT_NO_NETWORK;
        } else if (env.equalsIgnoreCase("PayPalEnvironmentProduction")) {
            this.environment = PayPalConfiguration.ENVIRONMENT_PRODUCTION;
            this.configuration.clientId(this.productionClientId);
        } else if (env.equalsIgnoreCase("PayPalEnvironmentSandbox")) {
            this.environment = PayPalConfiguration.ENVIRONMENT_SANDBOX;
            this.configuration.clientId(this.sandboxClientId);
        } else {
            this.callbackContext.error("The provided environment is not supported");
            return;
        }
        this.configuration.environment(this.environment);
        if (args.length() > REQUEST_SINGLE_PAYMENT) {
            updatePayPalConfiguration(args.getJSONObject(REQUEST_SINGLE_PAYMENT));
        }
        startService();
        this.callbackContext.success();
    }

    private void applicationCorrelationIDForEnvironment(JSONArray args) throws JSONException {
        this.callbackContext.success(PayPalConfiguration.getApplicationCorrelationId(this.cordova.getActivity()));
    }

    private void startService() {
        if (this.serverStarted) {
            this.serverStarted = this.activity.stopService(new Intent(this.activity, PayPalService.class));
        }
        Intent intent = new Intent(this.activity, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, this.configuration);
        this.activity.startService(intent);
        this.serverStarted = true;
    }

    private void renderSinglePaymentUI(JSONArray args) throws JSONException {
        if (args.length() != REQUEST_SINGLE_PAYMENT) {
            this.callbackContext.error("renderPaymentUI payment object must be provided");
            return;
        }
        JSONObject paymentObject = args.getJSONObject(0);
        String amount = paymentObject.getString("amount");
        String currency = paymentObject.getString("currency");
        String shortDescription = paymentObject.getString("shortDescription");
        String paymentIntent = PayPalPayment.PAYMENT_INTENT_SALE.equalsIgnoreCase(paymentObject.getString("intent")) ? PayPalPayment.PAYMENT_INTENT_SALE : PayPalPayment.PAYMENT_INTENT_AUTHORIZE;
        JSONObject paymentDetails = paymentObject.has("details") ? paymentObject.getJSONObject("details") : null;
        PayPalPayment payment = new PayPalPayment(new BigDecimal(amount), currency, shortDescription, paymentIntent);
        payment.paymentDetails(parsePaymentDetails(paymentDetails));
        Intent intent = new Intent(this.activity, PaymentActivity.class);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        this.cordova.startActivityForResult(this, intent, REQUEST_SINGLE_PAYMENT);
    }

    private void renderFuturePaymentUI(JSONArray args) throws JSONException {
        this.cordova.startActivityForResult(this, new Intent(this.activity, PayPalFuturePaymentActivity.class), REQUEST_CODE_FUTURE_PAYMENT);
    }

    private void updatePayPalConfiguration(JSONObject object) throws JSONException {
        if (object != null && object.length() != 0) {
            if (object.has("defaultUserEmail") && !object.isNull("defaultUserEmail")) {
                this.configuration.defaultUserEmail(object.getString("defaultUserEmail"));
            }
            if (object.has("defaultUserPhoneCountryCode") && !object.isNull("defaultUserPhoneCountryCode")) {
                this.configuration.defaultUserPhoneCountryCode(object.getString("defaultUserPhoneCountryCode"));
            }
            if (object.has("defaultUserPhoneNumber") && !object.isNull("defaultUserPhoneNumber")) {
                this.configuration.defaultUserPhone(object.getString("defaultUserPhoneNumber"));
            }
            if (object.has("merchantName") && !object.isNull("merchantName")) {
                this.configuration.merchantName(object.getString("merchantName"));
            }
            if (object.has("merchantPrivacyPolicyURL") && !object.isNull("merchantPrivacyPolicyURL")) {
                this.configuration.merchantPrivacyPolicyUri(Uri.parse(object.getString("merchantPrivacyPolicyURL")));
            }
            if (object.has("merchantUserAgreementURL") && !object.isNull("merchantUserAgreementURL")) {
                this.configuration.merchantUserAgreementUri(Uri.parse(object.getString("merchantUserAgreementURL")));
            }
            if (object.has("acceptCreditCards")) {
                this.configuration.acceptCreditCards(object.getBoolean("acceptCreditCards"));
            }
            if (object.has("rememberUser")) {
                this.configuration.rememberUser(object.getBoolean("rememberUser"));
            }
            if (object.has("forceDefaultsInSandbox")) {
                this.configuration.forceDefaultsOnSandbox(object.getBoolean("forceDefaultsInSandbox"));
            }
            if (object.has("languageOrLocale") && !object.isNull("languageOrLocale")) {
                this.configuration.languageOrLocale(object.getString("languageOrLocale"));
            }
            if (object.has("sandboxUserPassword") && !object.isNull("sandboxUserPassword")) {
                this.configuration.sandboxUserPassword(object.getString("sandboxUserPassword"));
            }
            if (object.has("sandboxUserPin") && !object.isNull("sandboxUserPin")) {
                this.configuration.sandboxUserPin(object.getString("sandboxUserPin"));
            }
        }
    }

    private PayPalPaymentDetails parsePaymentDetails(JSONObject object) throws JSONException {
        if (object == null || object.length() == 0) {
            return null;
        }
        BigDecimal subtotal;
        BigDecimal shipping;
        BigDecimal tax;
        if (object.has("subtotal")) {
            subtotal = new BigDecimal(object.getString("subtotal"));
        } else {
            subtotal = null;
        }
        if (object.has("shipping")) {
            shipping = new BigDecimal(object.getString("shipping"));
        } else {
            shipping = null;
        }
        if (object.has("tax")) {
            tax = new BigDecimal(object.getString("tax"));
        } else {
            tax = null;
        }
        return new PayPalPaymentDetails(shipping, subtotal, tax);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (REQUEST_SINGLE_PAYMENT == requestCode) {
            if (resultCode == -1) {
                if (intent.hasExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION)) {
                    this.callbackContext.success(((PaymentConfirmation) intent.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION)).toJSONObject());
                    return;
                }
                this.callbackContext.error("payment was ok but no confirmation");
            } else if (resultCode == 0) {
                this.callbackContext.error("payment cancelled");
            } else if (resultCode == REQUEST_CODE_FUTURE_PAYMENT) {
                this.callbackContext.error("An invalid Payment was submitted. Please see the docs.");
            } else {
                this.callbackContext.error(resultCode);
            }
        } else if (requestCode != REQUEST_CODE_FUTURE_PAYMENT) {
        } else {
            if (resultCode == -1) {
                if (intent.hasExtra(PayPalProfileSharingActivity.EXTRA_RESULT_AUTHORIZATION)) {
                    this.callbackContext.success(((PayPalAuthorization) intent.getParcelableExtra(PayPalProfileSharingActivity.EXTRA_RESULT_AUTHORIZATION)).toJSONObject());
                    return;
                }
                this.callbackContext.error("Authorization was ok but no code");
            } else if (resultCode == 0) {
                this.callbackContext.error("Future Payment user canceled.");
            } else if (resultCode == REQUEST_CODE_FUTURE_PAYMENT) {
                this.callbackContext.error("Possibly configuration submitted is invalid");
            }
        }
    }
}
