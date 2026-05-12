package com.appodeal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;
import com.appodeal.ads.utils.Log.LogLevel;
import com.appodeal.ads.utils.b.a;
import com.facebook.internal.FacebookRequestErrorClassification;
import org.json.JSONObject;

public class UserSettings {
    public static boolean sendingInProgress;
    public static JSONObject userData;
    private final SharedPreferences a;

    public enum Alcohol {
        NEGATIVE(1),
        NEUTRAL(2),
        POSITIVE(3);
        
        private final int a;

        private Alcohol(int i) {
            this.a = i;
        }

        public int getValue() {
            return this.a;
        }

        private static Alcohol b(Integer num) {
            if (num == null) {
                return null;
            }
            switch (num.intValue()) {
                case 1:
                    return NEGATIVE;
                case 2:
                    return NEUTRAL;
                case 3:
                    return POSITIVE;
                default:
                    return null;
            }
        }
    }

    public enum Gender {
        OTHER(0, "o", 1),
        FEMALE(1, "f", 2),
        MALE(2, "m", 1);
        
        private final int a;
        private final String b;
        private final int c;

        private Gender(int i, String str, int i2) {
            this.a = i;
            this.b = str;
            this.c = i2;
        }

        public int getValue() {
            return this.a;
        }

        public String getMopubValue() {
            return this.b;
        }

        public int getMailruValue() {
            return this.c;
        }

        private static Gender b(Integer num) {
            if (num == null) {
                return null;
            }
            switch (num.intValue()) {
                case 0:
                    return OTHER;
                case 1:
                    return FEMALE;
                case 2:
                    return MALE;
                default:
                    return null;
            }
        }
    }

    public enum Occupation {
        OTHER(0),
        WORK(1),
        SCHOOL(2),
        UNIVERSITY(3);
        
        private final int a;

        private Occupation(int i) {
            this.a = i;
        }

        public int getValue() {
            return this.a;
        }

        private static Occupation b(Integer num) {
            if (num == null) {
                return null;
            }
            switch (num.intValue()) {
                case 0:
                    return OTHER;
                case 1:
                    return WORK;
                case 2:
                    return SCHOOL;
                case 3:
                    return UNIVERSITY;
                default:
                    return null;
            }
        }
    }

    public enum Relation {
        OTHER(0, FacebookRequestErrorClassification.KEY_OTHER),
        SINGLE(1, "single"),
        DATING(2, "single"),
        ENGAGED(3, "married"),
        MARRIED(4, "married"),
        SEARCHING(5, "single");
        
        private final int a;
        private final String b;

        private Relation(int i, String str) {
            this.a = i;
            this.b = str;
        }

        public int getValue() {
            return this.a;
        }

        public String getMopubValue() {
            return this.b;
        }

        private static Relation b(Integer num) {
            if (num == null) {
                return null;
            }
            switch (num.intValue()) {
                case 0:
                    return OTHER;
                case 1:
                    return SINGLE;
                case 2:
                    return DATING;
                case 3:
                    return ENGAGED;
                case 4:
                    return MARRIED;
                case 5:
                    return SEARCHING;
                default:
                    return null;
            }
        }
    }

    public enum Smoking {
        NEGATIVE(1),
        NEUTRAL(2),
        POSITIVE(3);
        
        private final int a;

        private Smoking(int i) {
            this.a = i;
        }

        public int getValue() {
            return this.a;
        }

        private static Smoking b(Integer num) {
            if (num == null) {
                return null;
            }
            switch (num.intValue()) {
                case 1:
                    return NEGATIVE;
                case 2:
                    return NEUTRAL;
                case 3:
                    return POSITIVE;
                default:
                    return null;
            }
        }
    }

    UserSettings(Context context) {
        this.a = context.getSharedPreferences("appodeal", 0);
    }

    private String a(String str) {
        String str2 = null;
        try {
            if (this.a.contains(str)) {
                str2 = this.a.getString(str, null);
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
        return str2;
    }

    private UserSettings a(String str, String str2) {
        Editor edit = this.a.edit();
        edit.putString(str, str2);
        a(edit, str, str2);
        edit.apply();
        return this;
    }

    private Integer b(String str) {
        Integer num = null;
        try {
            if (this.a.contains(str)) {
                num = Integer.valueOf(this.a.getInt(str, 0));
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
        return num;
    }

    private UserSettings a(String str, Integer num) {
        Editor edit = this.a.edit();
        edit.putInt(str, num.intValue());
        a(edit, str, num.intValue());
        edit.apply();
        return this;
    }

    public String getUserId() {
        return a("userId");
    }

    public UserSettings setUserId(@NonNull String str) {
        if (str == null) {
            Appodeal.a(new a("Unable to set user id to null"));
            return this;
        }
        Appodeal.a(String.format("Set userId: %s", new Object[]{str}), LogLevel.verbose);
        return a("userId", str);
    }

    public String getEmail() {
        return a("email");
    }

    public UserSettings setEmail(@NonNull String str) {
        if (str == null) {
            Appodeal.a(new a("Unable to set email to null"));
            return this;
        }
        Appodeal.a(String.format("Set email: %s", new Object[]{str}), LogLevel.verbose);
        return a("email", str);
    }

    public Gender getGender() {
        return Gender.b(b("gender"));
    }

    public UserSettings setGender(@NonNull Gender gender) {
        if (gender == null) {
            Appodeal.a(new a("Unable to set gender to null"));
            return this;
        }
        Appodeal.a(String.format("Set gender: %s", new Object[]{gender.toString()}), LogLevel.verbose);
        return a("gender", Integer.valueOf(gender.getValue()));
    }

    public String getBirthday() {
        return a("birthday");
    }

    public UserSettings setBirthday(@NonNull String str) {
        if (str == null) {
            Appodeal.a(new a("Unable to set birthday to null"));
            return this;
        }
        Appodeal.a(String.format("Set birthday: %s", new Object[]{str}), LogLevel.verbose);
        return a("birthday", str);
    }

    public Integer getAge() {
        return b("age");
    }

    public UserSettings setAge(int i) {
        Appodeal.a(String.format("Set age: %s", new Object[]{Integer.valueOf(i)}), LogLevel.verbose);
        return a("age", Integer.valueOf(i));
    }

    public Occupation getOccupation() {
        return Occupation.b(b("occupation"));
    }

    public UserSettings setOccupation(@NonNull Occupation occupation) {
        if (occupation == null) {
            Appodeal.a(new a("Unable to set occupation to null"));
            return this;
        }
        Appodeal.a(String.format("Set occupation: %s", new Object[]{occupation.toString()}), LogLevel.verbose);
        return a("occupation", Integer.valueOf(occupation.getValue()));
    }

    public Relation getRelation() {
        return Relation.b(b("relation"));
    }

    public UserSettings setRelation(@NonNull Relation relation) {
        if (relation == null) {
            Appodeal.a(new a("Unable to set relation to null"));
            return this;
        }
        Appodeal.a(String.format("Set relation: %s", new Object[]{relation.toString()}), LogLevel.verbose);
        return a("relation", Integer.valueOf(relation.getValue()));
    }

    public Smoking getSmoking() {
        return Smoking.b(b("smoking"));
    }

    public UserSettings setSmoking(@NonNull Smoking smoking) {
        if (smoking == null) {
            Appodeal.a(new a("Unable to set smoking to null"));
            return this;
        }
        Appodeal.a(String.format("Set smoking: %s", new Object[]{smoking.toString()}), LogLevel.verbose);
        return a("smoking", Integer.valueOf(smoking.getValue()));
    }

    public Alcohol getAlcohol() {
        return Alcohol.b(b("alcohol"));
    }

    public UserSettings setAlcohol(@NonNull Alcohol alcohol) {
        if (alcohol == null) {
            Appodeal.a(new a("Unable to set alcohol to null"));
            return this;
        }
        Appodeal.a(String.format("Set alcohol: %s", new Object[]{alcohol.toString()}), LogLevel.verbose);
        return a("alcohol", Integer.valueOf(alcohol.getValue()));
    }

    public String getInterests() {
        return a("interests");
    }

    public UserSettings setInterests(@NonNull String str) {
        if (str == null) {
            Appodeal.a(new a("Unable to set interests to null"));
            return this;
        }
        Appodeal.a(String.format("Set interests: %s", new Object[]{str}), LogLevel.verbose);
        return a("interests", str);
    }

    JSONObject a() {
        Object obj = null;
        JSONObject jSONObject = new JSONObject();
        try {
            Object valueOf;
            jSONObject.put("userId", getUserId());
            jSONObject.put("email", getEmail());
            jSONObject.put("gender", getGender() != null ? Integer.valueOf(getGender().getValue()) : null);
            jSONObject.put("birthday", getBirthday());
            jSONObject.put("age", getAge());
            String str = "occupation";
            if (getOccupation() != null) {
                valueOf = Integer.valueOf(getOccupation().getValue());
            } else {
                valueOf = null;
            }
            jSONObject.put(str, valueOf);
            str = "relation";
            if (getRelation() != null) {
                valueOf = Integer.valueOf(getRelation().getValue());
            } else {
                valueOf = null;
            }
            jSONObject.put(str, valueOf);
            str = "smoking";
            if (getSmoking() != null) {
                valueOf = Integer.valueOf(getSmoking().getValue());
            } else {
                valueOf = null;
            }
            jSONObject.put(str, valueOf);
            String str2 = "alcohol";
            if (getAlcohol() != null) {
                obj = Integer.valueOf(getAlcohol().getValue());
            }
            jSONObject.put(str2, obj);
            jSONObject.put("interests", getInterests());
        } catch (Throwable e) {
            Appodeal.a(e);
        }
        return jSONObject;
    }

    public String toMopubString() {
        String str = "";
        if (getGender() != null) {
            str = str + "m_gender:" + getGender().getMopubValue() + ',';
        }
        if (getAge() != null) {
            str = str + "m_age:" + getAge() + ',';
        }
        if (getRelation() != null) {
            str = str + "m_marital:" + getRelation().getMopubValue() + ',';
        }
        return str.isEmpty() ? null : str.substring(0, str.length() - 1);
    }

    private void a(Editor editor, String str, int i) {
        if (i != this.a.getInt(str, -1)) {
            editor.putBoolean("should_update_user_settings", true);
            editor.apply();
        }
    }

    private void a(Editor editor, String str, String str2) {
        if (!this.a.getString(str, " ").equals(str2)) {
            editor.putBoolean("should_update_user_settings", true);
            editor.apply();
        }
    }
}
