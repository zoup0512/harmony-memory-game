package com.appodeal.ads;

import com.appodeal.ads.utils.Log.LogLevel;
import org.json.JSONObject;

public class AppodealSettings {
    public static boolean a = false;
    public static boolean b = true;
    public static LogLevel c = LogLevel.none;
    public static boolean d = false;
    public static boolean e = false;
    public static int f = -1;
    public static boolean g = false;
    public static boolean h = false;
    public static boolean i = false;
    public static boolean j = true;
    public static boolean k = true;
    public static boolean l = true;
    public static boolean m = false;
    public static boolean n = false;

    public static int a(Integer num) {
        if (num == null) {
            return 600000;
        }
        return num.intValue();
    }

    public static void muteVideosIfCallsMuted(boolean z) {
        e = z;
    }

    public static void disableWebViewCacheClear() {
        k = false;
    }

    static void a(JSONObject jSONObject) {
        try {
            if (jSONObject.has("ach") && jSONObject.getString("ach") != null) {
                if (jSONObject.getString("ach").equals(m.c)) {
                    m.a().a(m.c);
                } else if (jSONObject.getString("ach").equals(m.d)) {
                    m.a().a(m.d);
                } else {
                    m.a().a(m.b);
                }
            }
            if (jSONObject.has("for_kids")) {
                d = jSONObject.getBoolean("for_kids");
            }
            if (j) {
                i = jSONObject.optBoolean("disable_rtb");
            }
            if (jSONObject.has("randomize_offers")) {
                l = jSONObject.getBoolean("randomize_offers");
            }
            if (jSONObject.has("send_apps")) {
                m = jSONObject.getBoolean("send_apps");
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }
}
