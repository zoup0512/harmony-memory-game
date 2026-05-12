package com.flurry.sdk;

import android.telephony.TelephonyManager;
import com.facebook.places.model.PlaceFields;

public class js {
    private static final String a = js.class.getSimpleName();
    private static js b;

    private js() {
    }

    public static synchronized js a() {
        js jsVar;
        synchronized (js.class) {
            if (b == null) {
                b = new js();
            }
            jsVar = b;
        }
        return jsVar;
    }

    public static String b() {
        TelephonyManager telephonyManager = (TelephonyManager) jy.a().a.getSystemService(PlaceFields.PHONE);
        if (telephonyManager == null) {
            return null;
        }
        return telephonyManager.getNetworkOperatorName();
    }

    public static String c() {
        TelephonyManager telephonyManager = (TelephonyManager) jy.a().a.getSystemService(PlaceFields.PHONE);
        if (telephonyManager == null) {
            return null;
        }
        return telephonyManager.getNetworkOperator();
    }
}
