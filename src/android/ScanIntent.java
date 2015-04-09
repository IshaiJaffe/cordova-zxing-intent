package com.ishaijaffe.zxingintent;

import java.util.HashMap;
import java.util.Map;

import org.apache.cordova.CordovaActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.net.Uri;
import android.text.Html;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.PluginResult;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * ScanIntent is a PhoneGap plugin that bridges Zxing Android intents and web
 * applications:
 * 
 * 1. web apps can spawn zxing scans intents
 * 
 * @author ishai@bablic.com
 * 
 */
public class ScanIntent extends CordovaPlugin {

    private CallbackContext onNewIntentCallbackContext = null;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        try {
            // Parse the arguments
            final CordovaResourceApi resourceApi = webView.getResourceApi();
            JSONObject obj = args.getJSONObject(0);
            JSONObject extras = obj.has("extras") ? obj.getJSONObject("extras") : null;
            Map<String, String> extrasMap = new HashMap<String, String>();

            // Populate the extras if any exist
            if (extras != null) {
                JSONArray extraNames = extras.names();
                for (int i = 0; i < extraNames.length(); i++) {
                    String key = extraNames.getString(i);
                    String value = extras.getString(key);
                    extrasMap.put(key, value);
                }
            }

            scanActivity(extrasMap);
            this.onNewIntentCallbackContext = callbackContext;
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            String errorMessage=e.getMessage();
            //return new PluginResult(PluginResult.Status.JSON_EXCEPTION);
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.JSON_EXCEPTION,errorMessage));
            return false;
        }
    }


	public void onActivityResult(int requestCode, int resultCode, Intent intent){
		IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if (scanResult != null) {
			this.onNewIntentCallbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK,scanResult.getContents()));
		}
	}
	
    void scanActivity(Map<String, String> extras) {
        // get activity
		CordovaActivity myActivity = ((CordovaActivity)this.cordova.getActivity());

		// set this plugin as callback handler
		myActivity.setActivityResultCallback((CordovaPlugin)this);

		IntentIntegrator integrator = new IntentIntegrator(myActivity);
		// add extras
		for (Map.Entry<String, String> entry : extras.entrySet()){
            integrator.addExtra(entry.getKey(),entry.getValue());
        }
		integrator.initiateScan();
    }
}
