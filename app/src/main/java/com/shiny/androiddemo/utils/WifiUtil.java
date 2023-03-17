package com.shiny.androiddemo.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class WifiUtil {
    private static final String TAG = "WifiUtil";
    public static boolean isConnectedTo(String ssid, Context context) {
        boolean retVal = false;
        WifiManager wifi = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifi.getConnectionInfo();
        if (wifiInfo != null) {
            String currentConnectedSSID = wifiInfo.getSSID().substring(1, wifiInfo.getSSID().length() -1);
            Log.i(TAG, "isConnectedTo: " + wifiInfo.getSSID());
            if (ssid.equals(currentConnectedSSID)) {
                retVal = true;
            }
        }
        return retVal;
    }
}
