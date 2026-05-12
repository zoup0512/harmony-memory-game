package com.appodeal.ads.utils;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Base64;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;
import com.cmcm.adsdk.Const;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.places.model.PlaceFields;
import com.mopub.common.AdType;
import com.mopub.common.GpsHelper;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import org.json.JSONObject;

public class b {
    public static JSONObject a(Context context, String str, int i, String str2, String str3, String str4) {
        JSONObject jSONObject = new JSONObject();
        try {
            String str5 = "";
            String str6 = "";
            switch (i) {
                case 1:
                    str5 = AdType.INTERSTITIAL;
                    str6 = "text/html";
                    break;
                case 2:
                    str5 = AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO;
                    str6 = "text/xml";
                    break;
                case 4:
                    str5 = "banner";
                    str6 = "text/html";
                    break;
                case 128:
                    str5 = "rewardedVideo";
                    str6 = "text/xml";
                    break;
                case 256:
                    str5 = "mrec";
                    str6 = "text/html";
                    break;
                case 512:
                    str5 = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE;
                    str6 = "application/json";
                    break;
            }
            jSONObject.put(SettingsJsonConstants.APP_KEY, a(context));
            jSONObject.put(Const.KEY_JUHE, a(str, str5, str6, str2, str3));
            jSONObject.put("timestamp", a());
            jSONObject.put("device", a(context, str4));
        } catch (Throwable e) {
            Appodeal.a(e);
        }
        return jSONObject;
    }

    private static String a() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZ", Locale.ENGLISH).format(Calendar.getInstance().getTime());
    }

    private static JSONObject a(Context context, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            Object string = context.getSharedPreferences("appodeal", 0).getString(GpsHelper.ADVERTISING_ID_KEY, null);
            if (string == null) {
                string = an.l(context);
            }
            jSONObject.put(SettingsJsonConstants.APP_IDENTIFIER_KEY, string);
            jSONObject.put("deviceType", an.n(context) ? "tablet" : PlaceFields.PHONE);
            jSONObject.put("deviceModel", String.format("%s %s", new Object[]{Build.MANUFACTURER, Build.MODEL}));
            jSONObject.put("os", "Android");
            jSONObject.put("osVersion", VERSION.RELEASE);
            jSONObject.put("sdkVersion", "1.15.7");
            jSONObject.put("encryptedSession", str);
        } catch (Throwable e) {
            Appodeal.a(e);
        }
        return jSONObject;
    }

    private static JSONObject a(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appId", context.getPackageName());
            try {
                jSONObject.put("version", context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
            } catch (NameNotFoundException e) {
            }
        } catch (Throwable e2) {
            Appodeal.a(e2);
        }
        return jSONObject;
    }

    private static JSONObject a(String str, String str2, String str3, String str4, String str5) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("adNetwork", str);
            jSONObject.put("adType", str2);
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("contentType", str3);
            jSONObject3.put("body", Base64.encodeToString(str4.getBytes("UTF-8"), 2));
            jSONObject2.put("payload", jSONObject3);
            jSONObject.put("creative", jSONObject2);
            jSONObject.put("encryptedAdUnit", str5);
        } catch (Throwable e) {
            Appodeal.a(e);
        }
        return jSONObject;
    }
}
