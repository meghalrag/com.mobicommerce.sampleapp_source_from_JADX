package nl.xservices.plugins;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class Toast extends CordovaPlugin {
    private static final String ACTION_SHOW_EVENT = "show";

    /* renamed from: nl.xservices.plugins.Toast.1 */
    class C01711 implements Runnable {
        private final /* synthetic */ CallbackContext val$callbackContext;
        private final /* synthetic */ String val$duration;
        private final /* synthetic */ String val$message;
        private final /* synthetic */ String val$position;

        C01711(String str, String str2, CallbackContext callbackContext, String str3) {
            this.val$message = str;
            this.val$position = str2;
            this.val$callbackContext = callbackContext;
            this.val$duration = str3;
        }

        public void run() {
            android.widget.Toast toast = android.widget.Toast.makeText(Toast.this.webView.getContext(), this.val$message, 0);
            if ("top".equals(this.val$position)) {
                toast.setGravity(49, 0, 20);
            } else if ("bottom".equals(this.val$position)) {
                toast.setGravity(81, 0, 20);
            } else if ("center".equals(this.val$position)) {
                toast.setGravity(17, 0, 0);
            } else {
                this.val$callbackContext.error("invalid position. valid options are 'top', 'center' and 'bottom'");
                return;
            }
            if ("short".equals(this.val$duration)) {
                toast.setDuration(0);
            } else if ("long".equals(this.val$duration)) {
                toast.setDuration(1);
            } else {
                this.val$callbackContext.error("invalid duration. valid options are 'short' and 'long'");
                return;
            }
            toast.show();
            this.val$callbackContext.success();
        }
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (ACTION_SHOW_EVENT.equals(action)) {
            String message = args.getString(0);
            String duration = args.getString(1);
            this.cordova.getActivity().runOnUiThread(new C01711(message, args.getString(2), callbackContext, duration));
            return true;
        }
        callbackContext.error("toast." + action + " is not a supported function. Did you mean '" + ACTION_SHOW_EVENT + "'?");
        return false;
    }
}
