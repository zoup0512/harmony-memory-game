package com.appodeal.ads.networks;

import android.app.Activity;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;
import com.facebook.internal.AnalyticsEvents;
import com.jirbo.adcolony.AdColony;
import com.jirbo.adcolony.AdColonyAd;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public class a {
    public static boolean a = false;
    private static boolean b = false;
    private static boolean c = false;

    public static void a(Activity activity, String str, String str2, JSONObject jSONObject, String str3) {
        if (!c) {
            String[] strArr;
            String str4 = "";
            try {
                str4 = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0).versionName;
            } catch (Throwable e) {
                Appodeal.a(e);
            }
            String format = String.format("version:%s,store:%s", new Object[]{str4, str});
            if (jSONObject != null && jSONObject.length() > 0) {
                try {
                    ArrayList arrayList = new ArrayList();
                    Iterator keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        str4 = (String) keys.next();
                        JSONArray jSONArray = jSONObject.getJSONArray(str4);
                        for (int i = 0; i < jSONArray.length(); i++) {
                            if (str4.equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE)) {
                                com.appodeal.ads.native_ad.a.c.add(jSONArray.getString(i));
                            }
                            if (str4.equals("skippable")) {
                                com.appodeal.ads.g.a.c.add(jSONArray.getString(i));
                            }
                            if (str4.equals("rewarded")) {
                                com.appodeal.ads.e.a.c.add(jSONArray.getString(i));
                            }
                            arrayList.add(jSONArray.getString(i));
                        }
                    }
                    strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
                } catch (Throwable e2) {
                    Appodeal.a(e2);
                }
                if (strArr == null) {
                    strArr = new String[]{str3};
                }
                if (!b) {
                    b = true;
                    if (Appodeal.c != null || Appodeal.c.isFinishing()) {
                        AdColony.configure(activity, format, str2, strArr);
                    } else {
                        AdColony.configure(Appodeal.c, format, str2, strArr);
                    }
                    AdColony.addAdAvailabilityListener(new b());
                    c = true;
                    b = false;
                }
            }
            strArr = null;
            if (strArr == null) {
                strArr = new String[]{str3};
            }
            if (!b) {
                b = true;
                if (Appodeal.c != null) {
                }
                AdColony.configure(activity, format, str2, strArr);
                AdColony.addAdAvailabilityListener(new b());
                c = true;
                b = false;
            }
        }
    }

    public static String a(AdColonyAd adColonyAd) {
        if (adColonyAd != null) {
            try {
                Object a;
                if (adColonyAd.getClass().getName().contains("AdColonyV4VCAd")) {
                    a = an.a(adColonyAd, "j", true, 1);
                } else {
                    a = an.a(adColonyAd, "j", true, 2);
                }
                if (a == null) {
                    return null;
                }
                a = an.a(a, "z", false, 0);
                if (a == null) {
                    return null;
                }
                a = an.a(a, "d", false, 0);
                if (a == null) {
                    return null;
                }
                return an.d((String) a);
            } catch (Throwable e) {
                Appodeal.a(e);
            }
        }
        return null;
    }
}
