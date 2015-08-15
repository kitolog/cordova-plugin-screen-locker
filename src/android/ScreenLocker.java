package com.applurk.plugin;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.os.PowerManager;

import android.util.Log;
import android.provider.Settings;
import android.widget.Toast;

import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.app.KeyguardManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ScreenLocker extends CordovaPlugin {
    public static final String TAG = "ScreenLocker";
    public static final String ACTION_UNLOCK = "unlock";
    public static final String ACTION_UNLOCK_WINDOW = "unlock_window";
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
    public boolean execute(final String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        boolean result = false;
// Shows a toast
        Log.v(TAG, "ScreenLocker received:" + action);

        try {
            JSONObject arg_object = args.getJSONObject(0);
            if (ACTION_LOCK.equals(action)) {
//                Lock device
                Log.v(TAG, "Lock action not implemented, please wait for a new plugin version");
//                WindowManager wm = (WindowManager) this.cordova.getActivity().getSystemService(this.cordova.getActivity().WINDOW_SERVICE);
//                DevicePolicyManager mDPM;
//                mDPM = (DevicePolicyManager) this.cordova.getActivity().getSystemService(Context.DEVICE_POLICY_SERVICE);
//                mDPM.lockNow();
//                Log.v(TAG, "ScreenLocker received SUCCESS:" + action);
//                callbackContext.success();
//                result = true;
                callbackContext.error("Not implemented");
                result = false;
            } else if (ACTION_UNLOCK_WINDOW.equals(action)) {
//                Unlock
//                http://developer.android.com/reference/android/app/Activity.html#getWindow()
                Log.v(TAG, "ScreenLocker received ACTION_UNLOCK_WINDOW");
                cordova.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Window window = cordova.getActivity().getWindow();
                        window.addFlags(LayoutParams.FLAG_DISMISS_KEYGUARD);
                        window.addFlags(LayoutParams.FLAG_SHOW_WHEN_LOCKED);
                        window.addFlags(LayoutParams.FLAG_TURN_SCREEN_ON);
                        Log.v(TAG, "ScreenLocker received SUCCESS:" + action);
                        callbackContext.success();
                    }
                });

                result = true;
            } else if (ACTION_UNLOCK.equals(action)) {
//                Unlock
//                http://developer.android.com/reference/android/app/Activity.html#getWindow()
                Log.v(TAG, "ScreenLocker received ACTION_UNLOCK");
                cordova.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        PowerManager powerManager = (PowerManager) cordova.getActivity().getSystemService(Context.POWER_SERVICE);
                        PowerManager.WakeLock wakeLock = powerManager.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK
                                | PowerManager.FULL_WAKE_LOCK
                                | PowerManager.ACQUIRE_CAUSES_WAKEUP), "TAG");
                        if(wakeLock.isHeld()) {
                            wakeLock.release();
                        }
                        wakeLock.acquire();

                        KeyguardManager keyguardManager = (KeyguardManager) cordova.getActivity().getSystemService(Context.KEYGUARD_SERVICE);
                        KeyguardManager.KeyguardLock keyguardLock =  keyguardManager.newKeyguardLock("Tag");
                        keyguardLock.disableKeyguard();

                        Log.v(TAG, "ScreenLocker received SUCCESS:" + action);
                        callbackContext.success();
                    }
                });

                result = true;
            } else {
                callbackContext.error("Invalid action");
                result = false;
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            callbackContext.error(e.getMessage());
            result = false;
        }

        return result;
    }
}