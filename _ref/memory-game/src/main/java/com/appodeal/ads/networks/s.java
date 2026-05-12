package com.appodeal.ads.networks;

import android.app.Activity;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.AppodealSettings;
import com.appodeal.ads.UserSettings;
import com.appodeal.ads.UserSettings.Gender;
import com.revmob.RevMob;
import com.revmob.RevMobUserGender;

public class s {
    public static void a(Activity activity, RevMob revMob) {
        try {
            UserSettings userSettings = Appodeal.getUserSettings(activity);
            Gender gender = userSettings.getGender();
            if (gender != null) {
                switch (gender.getValue()) {
                    case 0:
                        revMob.setUserGender(RevMobUserGender.UNDEFINED);
                        break;
                    case 1:
                        revMob.setUserGender(RevMobUserGender.FEMALE);
                        break;
                    case 2:
                        revMob.setUserGender(RevMobUserGender.MALE);
                        break;
                }
            }
            if (userSettings.getEmail() != null) {
                revMob.setUserEmail(userSettings.getEmail());
            }
            if (AppodealSettings.d) {
                revMob.setUserAgeRangeMin(13);
            } else if (userSettings.getAge() != null) {
                revMob.setUserAgeRangeMax(userSettings.getAge().intValue());
                revMob.setUserAgeRangeMin(userSettings.getAge().intValue());
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }
}
