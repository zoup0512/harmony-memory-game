package com.yandex.metrica.impl;

import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.AnalyticsEvents;

public class ax {

    private static class a {
        static final String a;

        static {
            String str = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE;
            if (ax.b("com.unity3d.player.UnityPlayer")) {
                str = "unity";
            } else if (ax.b("mono.MonoPackageManager")) {
                str = "xamarin";
            }
            a = str;
        }
    }

    public static String a() {
        String str = "2.51";
        if (str.length() - str.indexOf(46) < 3) {
            return str + AppEventsConstants.EVENT_PARAM_VALUE_NO;
        }
        return str;
    }

    public static boolean b() {
        return b("com.yandex.metrica.YandexMetricaInternal");
    }

    public static String c() {
        return a.a;
    }

    private static boolean b(String str) {
        try {
            if (Class.forName(str) != null) {
                return true;
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
