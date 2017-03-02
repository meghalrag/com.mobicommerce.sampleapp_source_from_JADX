package com.appheros.appbuilder;

import android.os.Bundle;
import org.apache.cordova.CordovaActivity;

public class RWS extends CordovaActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadUrl(this.launchUrl);
    }
}
