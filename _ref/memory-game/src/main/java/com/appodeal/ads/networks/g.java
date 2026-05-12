package com.appodeal.ads.networks;

import android.app.Activity;
import com.chartboost.sdk.Chartboost;

public class g {
    public static boolean a = false;
    private static boolean b = false;

    public static void a(Activity activity, String str, String str2) {
        if (!b) {
            b = true;
            Chartboost.startWithAppId(activity, str, str2);
            Chartboost.onCreate(activity);
            Chartboost.onStart(activity);
            Chartboost.onResume(activity);
            Chartboost.setAutoCacheAds(false);
            Chartboost.setActivityCallbacks(true);
        }
    }
}
