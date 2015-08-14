package com.applurk.plugin.ScreenLocker;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;

import android.app.admin.DevicePolicyManager;
import android.content.Context;

import android.util.Log;
import android.provider.Settings;
import android.widget.Toast;

import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ScreenLocker extends CordovaPlugin {
    public static final String TAG = "ScreenLocker";
    public static final String ACTION_UNLOCK = "unlock";
    public static final String ACTION_LOCK = "lock";

    /**
     * Constructor.
     */
    public ScreenLocker() {
    }

    /**
     * Sets the context of the Command. This can then be used to do things like
     * get file paths associated with the Activity.
     *
     * @param cordova The context of the main Activity.
     * @param webView The CordovaWebView Cordova is running in.
     */
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        Log.v(TAG, "Init ScreenLocker");
    }

    @Override
    public boolean execute(final String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        boolean result = false;
// Shows a toast
        Log.v(TAG, "ScreenLocker received:" + action);

        try {
            JSONObject arg_object = args.getJSONObject(0);
            if (ACTION_LOCK.equals(action)) {
//                Lock device
                Log.v(TAG, "ScreenLocker received SUCCESS:" + action);
                WindowManager wm = (WindowManager) this.cordova.getActivity().getSystemService(this.cordova.getActivity().WINDOW_SERVICE);
                DevicePolicyManager mDPM;
                mDPM = (DevicePolicyManager) this.cordova.getActivity().getSystemService(Context.DEVICE_POLICY_SERVICE);
                mDPM.lockNow();
                Log.v(TAG, "ScreenLocker received SUCCESS:" + action);
                callbackContext.success();
                result = true;
            } else if (ACTION_UNLOCK.equals(action)) {
//                Unlock
//                http://developer.android.com/reference/android/app/Activity.html#getWindow()
                WindowManager wm = (WindowManager) this.cordova.getActivity().getSystemService(this.cordova.getActivity().WINDOW_SERVICE);
                Window window = this.cordova.getActivity().getWindow();
                window.addFlags(LayoutParams.FLAG_DISMISS_KEYGUARD);
                window.addFlags(LayoutParams.FLAG_SHOW_WHEN_LOCKED);
                window.addFlags(LayoutParams.FLAG_TURN_SCREEN_ON);
                Log.v(TAG, "ScreenLocker received SUCCESS:" + action);
                callbackContext.success();
                result = true;
            }
            callbackContext.error("Invalid action");
            result = false;
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            callbackContext.error(e.getMessage());
            result = false;
        }

        return result;
    }
}