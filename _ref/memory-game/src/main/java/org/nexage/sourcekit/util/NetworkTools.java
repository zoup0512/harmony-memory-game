package org.nexage.sourcekit.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkTools {
    private static final String TAG = HttpTools.class.getName();

    public static boolean connectedToInternet(Context context) {
        VASTLog.d(TAG, "Testing connectivity:");
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
        if (networkInfo == null || !networkInfo.isConnected()) {
            networkInfo = connectivityManager.getNetworkInfo(0);
            if (networkInfo == null || !networkInfo.isConnected()) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                    VASTLog.d(TAG, "No Internet connection");
                    return false;
                }
                VASTLog.d(TAG, "Connected to Internet");
                return true;
            }
            VASTLog.d(TAG, "Connected to Internet");
            return true;
        }
        VASTLog.d(TAG, "Connected to Internet");
        return true;
    }
}
