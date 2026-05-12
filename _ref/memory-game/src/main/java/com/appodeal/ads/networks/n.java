package com.appodeal.ads.networks;

import android.content.Context;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.UserSettings.Gender;
import com.appodeal.ads.an;
import com.cmcm.adsdk.Const;
import com.my.target.ads.CustomParams;
import com.my.target.ads.InterstitialAd;
import com.my.target.nativeads.models.VideoData;
import java.util.ArrayList;

public class n {
    public static CustomParams a(Context context) {
        CustomParams customParams = new CustomParams();
        Integer age = Appodeal.getUserSettings(context).getAge();
        Gender gender = Appodeal.getUserSettings(context).getGender();
        if (age != null) {
            customParams.setAge(age.intValue());
        }
        if (gender != null) {
            customParams.setGender(gender.getMailruValue());
        }
        return customParams;
    }

    public static String a(InterstitialAd interstitialAd) {
        try {
            Object a = an.a(interstitialAd, Const.KEY_JUHE, false, 0);
            if (a == null) {
                return null;
            }
            a = an.a(a, "a", false, 0);
            if (a == null) {
                return null;
            }
            a = an.a(a, "B", false, 0);
            if (a == null) {
                return null;
            }
            ArrayList arrayList = (ArrayList) an.a(a, "u", false, 0);
            if (arrayList == null) {
                return null;
            }
            if (arrayList.size() > 0) {
                VideoData videoData = (VideoData) arrayList.get(0);
                if (videoData == null) {
                    return null;
                }
                if (videoData.getUrl() != null) {
                    return an.d(videoData.getUrl());
                }
            }
            return null;
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }
}
