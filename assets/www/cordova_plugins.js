cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "file": "plugins/com.paypal.cordova.mobilesdk/www/cdv-plugin-paypal-mobile-sdk.js",
        "id": "com.paypal.cordova.mobilesdk.PayPalMobile",
        "clobbers": [
            "PayPalMobile"
        ]
    },
    {
        "file": "plugins/com.phonegap.plugins.PushPlugin/www/PushNotification.js",
        "id": "com.phonegap.plugins.PushPlugin.PushNotification",
        "clobbers": [
            "PushNotification"
        ]
    },
    {
        "file": "plugins/de.appplant.cordova.plugin.email-composer/www/email_composer.js",
        "id": "de.appplant.cordova.plugin.email-composer.EmailComposer",
        "clobbers": [
            "plugin.email"
        ]
    },
    {
        "file": "plugins/nl.x-services.plugins.toast/www/Toast.js",
        "id": "nl.x-services.plugins.toast.Toast",
        "clobbers": [
            "window.plugins.toast"
        ]
    },
    {
        "file": "plugins/nl.x-services.plugins.toast/test/tests.js",
        "id": "nl.x-services.plugins.toast.tests"
    },
    {
        "file": "plugins/org.apache.cordova.device/www/device.js",
        "id": "org.apache.cordova.device.device",
        "clobbers": [
            "device"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.inappbrowser/www/inappbrowser.js",
        "id": "org.apache.cordova.inappbrowser.inappbrowser",
        "clobbers": [
            "window.open"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.network-information/www/network.js",
        "id": "org.apache.cordova.network-information.network",
        "clobbers": [
            "navigator.connection",
            "navigator.network.connection"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.network-information/www/Connection.js",
        "id": "org.apache.cordova.network-information.Connection",
        "clobbers": [
            "Connection"
        ]
    },
    {
        "file": "plugins/nl.x-services.plugins.socialsharing/www/SocialSharing.js",
        "id": "nl.x-services.plugins.socialsharing.SocialSharing",
        "clobbers": [
            "window.plugins.socialsharing"
        ]
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "com.paypal.cordova.mobilesdk": "2.0.2",
    "com.phonegap.plugins.PushPlugin": "2.3.1",
    "de.appplant.cordova.plugin.email-composer": "0.8.2dev",
    "nl.x-services.plugins.toast": "2.0.2",
    "org.apache.cordova.device": "0.2.13-dev",
    "org.apache.cordova.inappbrowser": "0.5.3-dev",
    "org.apache.cordova.network-information": "0.2.14-dev",
    "nl.x-services.plugins.socialsharing": "4.3.8"
}
// BOTTOM OF METADATA
});