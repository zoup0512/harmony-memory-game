package com.appodeal.ads.utils;

import android.content.Context;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.UserSettings;
import com.appodeal.ads.UserSettings.Gender;

public class r {
    public static Gender a(Context context) {
        String a = a("gender");
        if (a == null) {
            return Appodeal.getUserSettings(context).getGender();
        }
        if (a.equalsIgnoreCase("o")) {
            return Gender.OTHER;
        }
        if (a.equalsIgnoreCase("f")) {
            return Gender.FEMALE;
        }
        if (a.equalsIgnoreCase("m")) {
            return Gender.MALE;
        }
        return null;
    }

    public static Integer b(Context context) {
        int b = b("age");
        if (b != -1) {
            return Integer.valueOf(b);
        }
        return Appodeal.getUserSettings(context).getAge();
    }

    public static Integer c(Context context) {
        String birthday = Appodeal.getUserSettings(context).getBirthday();
        Integer num = null;
        if (birthday != null && birthday.length() == 10) {
            try {
                num = Integer.valueOf(Integer.parseInt(birthday.substring(6, 10)));
            } catch (NumberFormatException e) {
            }
        }
        return num;
    }

    private static String a(String str) {
        if (UserSettings.userData == null) {
            return null;
        }
        return UserSettings.userData.optJSONObject("user_settings").optString(str);
    }

    private static int b(String str) {
        if (UserSettings.userData == null) {
            return -1;
        }
        return UserSettings.userData.optJSONObject("user_settings").optInt(str, -1);
    }
}
