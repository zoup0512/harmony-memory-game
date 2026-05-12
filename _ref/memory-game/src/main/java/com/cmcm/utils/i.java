package com.cmcm.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/* compiled from: NetworkUtil */
public class i {
    public static boolean a(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        try {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            if (networkInfo == null) {
                return false;
            }
            State state = networkInfo.getState();
            if (state == State.CONNECTED || state == State.CONNECTING) {
                return true;
            }
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public static int b(Context context) {
        if (context == null) {
            return 0;
        }
        int i;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                i = 4;
                return i;
            }
            if (activeNetworkInfo.getType() == 1) {
                i = c(context) ? 3 : 4;
            } else {
                i = activeNetworkInfo.getSubtype();
                i = a(i) ? 1 : c(i) ? 2 : b(i) ? 5 : 4;
            }
            return i;
        } catch (Exception e) {
            i = 0;
        }
    }

    private static boolean b(int i) {
        if (i == 13) {
            return true;
        }
        return false;
    }

    public static boolean c(Context context) {
        if (context == null) {
            return false;
        }
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        int ipAddress = connectionInfo == null ? 0 : connectionInfo.getIpAddress();
        if (!wifiManager.isWifiEnabled() || ipAddress == 0) {
            return false;
        }
        return true;
    }

    public static boolean a(int i) {
        switch (i) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return true;
            default:
                return false;
        }
    }

    private static boolean c(int i) {
        switch (i) {
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return true;
            default:
                return false;
        }
    }

    public static boolean d(Context context) {
        int b = b(context);
        if (b == 1 || b == 2 || b == 5) {
            return true;
        }
        return false;
    }

    public static boolean e(Context context) {
        if (context != null) {
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected()) {
                        return true;
                    }
                    return false;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }
}
