package com.appodeal.ads.networks;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.AppodealSettings;
import com.appodeal.ads.UserSettings.Gender;
import com.appodeal.ads.an;
import com.appodeal.ads.utils.r;
import com.flurry.android.FlurryAgent;
import com.flurry.android.ads.FlurryAdTargeting;
import com.flurry.android.ads.FlurryGender;
import java.util.HashMap;
import java.util.Map;

public class j {
    public static FlurryAdTargeting a(Activity activity) {
        FlurryAdTargeting flurryAdTargeting = new FlurryAdTargeting();
        a(activity, flurryAdTargeting);
        b(activity, flurryAdTargeting);
        c(activity, flurryAdTargeting);
        d(activity, flurryAdTargeting);
        if (AppodealSettings.a) {
            FlurryAgent.setLogEnabled(true);
            FlurryAgent.setLogEvents(true);
            FlurryAgent.setLogLevel(2);
            flurryAdTargeting.setEnableTestAds(true);
        }
        return flurryAdTargeting;
    }

    private static void a(Activity activity, FlurryAdTargeting flurryAdTargeting) {
        Location e = an.e(activity);
        if (e != null) {
            flurryAdTargeting.setLocation((float) e.getLatitude(), (float) e.getLatitude());
        }
    }

    private static void b(Activity activity, FlurryAdTargeting flurryAdTargeting) {
        String interests = Appodeal.getUserSettings(activity).getInterests();
        Map hashMap = new HashMap();
        hashMap.put("interests", interests);
        flurryAdTargeting.setKeywords(hashMap);
    }

    private static void c(Activity activity, FlurryAdTargeting flurryAdTargeting) {
        Gender a = r.a((Context) activity);
        if (a == Gender.FEMALE) {
            flurryAdTargeting.setGender(FlurryGender.FEMALE);
        } else if (a == Gender.MALE) {
            flurryAdTargeting.setGender(FlurryGender.MALE);
        } else if (a == Gender.OTHER) {
            flurryAdTargeting.setGender(FlurryGender.UNKNOWN);
        }
    }

    private static void d(Activity activity, FlurryAdTargeting flurryAdTargeting) {
        Integer b = r.b((Context) activity);
        if (b != null) {
            flurryAdTargeting.setAge(b.intValue());
        }
    }
}
