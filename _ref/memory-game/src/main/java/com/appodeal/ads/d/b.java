package com.appodeal.ads.d;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Pair;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.Native;
import com.appodeal.ads.UserSettings;
import com.appodeal.ads.UserSettings.Alcohol;
import com.appodeal.ads.UserSettings.Gender;
import com.appodeal.ads.UserSettings.Occupation;
import com.appodeal.ads.UserSettings.Relation;
import com.appodeal.ads.UserSettings.Smoking;
import com.appodeal.ads.an;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.places.model.PlaceFields;
import com.facebook.share.internal.ShareConstants;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.mopub.volley.DefaultRetryPolicy;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    private static final Boolean a = null;
    private final Double b;
    private final JSONObject c;
    private final JSONObject d;
    private final JSONObject e;
    private final boolean f;
    private int g = 0;
    private int h = 0;
    private Context i;
    private final boolean j;
    private JSONObject k;
    private String[] l = new String[]{"gender", "yob", "lat", "lon", "city", "zip"};

    public b(Context context, Double d, String str, String str2, String str3, String str4, Integer num, String str5, JSONObject jSONObject, boolean z, JSONObject jSONObject2, boolean z2, boolean z3) {
        this.i = context;
        this.b = d;
        this.c = jSONObject;
        this.d = this.c.optJSONObject("user_settings");
        this.e = jSONObject2;
        this.f = z;
        this.j = z2;
        this.k = new JSONObject();
        this.k.put("id", str);
        this.k.put("at", num);
        this.k.put("test", z3 ? 1 : 0);
        if (jSONObject2.has("bcat")) {
            JSONArray optJSONArray = jSONObject2.optJSONArray("bcat");
            if (optJSONArray != null) {
                this.k.put("bcat", optJSONArray);
            }
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(PlaceFields.PHONE);
        String str6 = null;
        try {
            str6 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
        }
        if (z2) {
            String optString = jSONObject2.optString("name");
            if (optString.isEmpty() || optString.equals("")) {
                try {
                    optString = (String) context.getPackageManager().getApplicationLabel(context.getApplicationInfo());
                } catch (Throwable e2) {
                    Appodeal.a(e2);
                }
            }
            a(str3, optString, null, null, null, null, str6, context.getPackageName(), a, null, str4, null);
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            this.g = displayMetrics.widthPixels;
            this.h = displayMetrics.heightPixels;
            a(false, a(), System.getProperty("http.agent"), jSONObject.optString("ip"), null, null, null, null, null, null, null, telephonyManager.getNetworkOperatorName(), Locale.getDefault().getLanguage(), Build.MANUFACTURER, Build.MODEL, Build.HARDWARE, Integer.valueOf(displayMetrics.heightPixels), Integer.valueOf(displayMetrics.widthPixels), Integer.valueOf(displayMetrics.densityDpi), Float.valueOf(displayMetrics.density), "Android", VERSION.RELEASE, true, Integer.valueOf(c()), Integer.valueOf(Double.valueOf(Math.sqrt((double) (((((float) displayMetrics.widthPixels) / displayMetrics.xdpi) * (((float) displayMetrics.widthPixels) / displayMetrics.xdpi)) + ((((float) displayMetrics.heightPixels) / displayMetrics.ydpi) * (((float) displayMetrics.heightPixels) / displayMetrics.ydpi))))).doubleValue() >= 7.0d ? 5 : 4), str2, null);
            int offset = TimeZone.getDefault().getOffset(new Date().getTime()) / 60000;
            Integer num2 = null;
            Float f = null;
            Float f2 = null;
            Location e3 = an.e(context);
            if (e3 != null) {
                num2 = Integer.valueOf(1);
                f = Float.valueOf((float) e3.getLatitude());
                f2 = Float.valueOf((float) e3.getLongitude());
            } else if (this.d != null) {
                num2 = Integer.valueOf(3);
                f2 = this.d.has("lon") ? Float.valueOf((float) this.d.getDouble("lon")) : null;
                f = this.d.has("lat") ? Float.valueOf((float) this.d.getDouble("lat")) : null;
            }
            str6 = null;
            String str7 = null;
            if (this.d != null) {
                str6 = this.d.optString("city");
                str7 = this.d.optString("zip");
            }
            a(f, f2, str5, null, null, null, str6, str7, num2, Integer.valueOf(offset), null);
            b();
            d();
        }
    }

    private void b() {
        String str;
        Integer num;
        String string;
        UserSettings userSettings = Appodeal.getUserSettings(this.i);
        Gender gender = userSettings.getGender();
        if (gender != null) {
            switch (gender) {
                case MALE:
                    str = "M";
                    break;
                case FEMALE:
                    str = "F";
                    break;
                case OTHER:
                    str = "O";
                    break;
            }
        }
        str = null;
        String birthday = userSettings.getBirthday();
        if (birthday == null || birthday.length() != 10) {
            num = null;
        } else {
            try {
                num = Integer.valueOf(Integer.parseInt(birthday.substring(6, 10)));
            } catch (NumberFormatException e) {
                num = null;
            }
        }
        if (num == null && this.d != null && this.d.has("yob")) {
            num = Integer.valueOf(this.d.getInt("yob"));
        }
        if (str == null && this.d != null && this.d.has("gender")) {
            string = this.d.getString("gender");
        } else {
            string = str;
        }
        String interests = userSettings.getInterests();
        if (interests == null && this.d != null && this.d.has("interests")) {
            interests = this.d.optString("interests");
        }
        a(null, null, num, string, interests, null, null);
        JSONArray jSONArray = new JSONArray();
        JSONArray jSONArray2 = new JSONArray();
        List arrayList = new ArrayList();
        Alcohol alcohol = userSettings.getAlcohol();
        if (alcohol != null) {
            jSONArray2.put(a("alcohol", "alcohol", String.valueOf(alcohol.getValue()), null));
            arrayList.add("alcohol");
        }
        str = userSettings.getBirthday();
        if (str != null) {
            jSONArray2.put(a("birthday", "birthday", str, null));
            arrayList.add("birthday");
        }
        Smoking smoking = userSettings.getSmoking();
        if (smoking != null) {
            jSONArray2.put(a("smoking", "smoking", String.valueOf(smoking.getValue()), null));
            arrayList.add("smoking");
        }
        Relation relation = userSettings.getRelation();
        if (relation != null) {
            jSONArray2.put(a("relation", "relation", String.valueOf(relation.getValue()), null));
            arrayList.add("relation");
        }
        Occupation occupation = userSettings.getOccupation();
        if (occupation != null) {
            jSONArray2.put(a("occupation", "occupation", String.valueOf(occupation.getValue()), null));
            arrayList.add("occupation");
        }
        Integer age = userSettings.getAge();
        if (age != null) {
            jSONArray2.put(a("age", "age", String.valueOf(age), null));
            arrayList.add("age");
        }
        str = this.c.optString("address");
        if (str != null) {
            jSONArray.put(a("address", "address", str, null));
        }
        JSONObject optJSONObject = this.c.optJSONObject("segments");
        if (optJSONObject != null) {
            Iterator keys = optJSONObject.keys();
            while (keys.hasNext()) {
                str = (String) keys.next();
                if (!arrayList.contains(str)) {
                    jSONArray.put(a(str.toLowerCase(), str.toLowerCase(), optJSONObject.getString(str), null));
                }
            }
        }
        if (this.d != null) {
            arrayList = Arrays.asList(this.l);
            Iterator keys2 = this.d.keys();
            while (keys2.hasNext()) {
                str = (String) keys2.next();
                if (!arrayList.contains(str)) {
                    jSONArray.put(a(str.toLowerCase(), str.toLowerCase(), this.d.getString(str), null));
                }
            }
        }
        a(AppEventsConstants.EVENT_PARAM_VALUE_YES, "appodeal", jSONArray, null);
        a("2", "user", jSONArray2, null);
    }

    public JSONObject a(boolean z, int i, e eVar) {
        JSONObject jSONObject = new JSONObject(this.k.toString());
        if (this.g == 0 || this.h == 0) {
            DisplayMetrics displayMetrics = this.i.getResources().getDisplayMetrics();
            this.g = displayMetrics.widthPixels;
            this.h = displayMetrics.heightPixels;
        }
        JSONObject a = a(a(eVar), Integer.valueOf(1), Integer.valueOf(i), Integer.valueOf(5), new Integer[]{Integer.valueOf(2), Integer.valueOf(5)}, Integer.valueOf(this.g), Integer.valueOf(this.h), null, Integer.valueOf(1), null, null, null, null, null, Boolean.valueOf(true), new Integer[]{Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3)}, new Integer[]{Integer.valueOf(1), Integer.valueOf(2)}, Integer.valueOf(0), null, new Integer[]{Integer.valueOf(5)}, null, null);
        if (!z) {
            a(a);
        }
        jSONObject.put("imp", new JSONArray().put(a(AppEventsConstants.EVENT_PARAM_VALUE_YES, null, a, null, null, null, Boolean.valueOf(!z), "appodeal", this.b, "USD", null, null, null, null)));
        return jSONObject;
    }

    public JSONObject a(List<Pair<Integer, Integer>> list, int i, int i2, boolean z) {
        JSONObject jSONObject = new JSONObject(this.k.toString());
        if (this.g == 0 || this.h == 0) {
            DisplayMetrics displayMetrics = this.i.getResources().getDisplayMetrics();
            this.g = displayMetrics.widthPixels;
            this.h = displayMetrics.heightPixels;
        }
        JSONArray jSONArray = new JSONArray();
        if (list.isEmpty()) {
            list.add(new Pair(Integer.valueOf(i), Integer.valueOf(i2)));
        }
        for (Pair pair : list) {
            jSONArray.put(a(String.valueOf(list.indexOf(pair) + 1), a((Integer) pair.first, (Integer) pair.second, z ? Integer.valueOf(this.g) : null, z ? Integer.valueOf(this.h) : null, null, null, null, Integer.valueOf(z ? 7 : 0), null, null, new String[]{"image/jpg", "image/gif", "image/png"}, Boolean.valueOf(false), new Integer[]{Integer.valueOf(5), Integer.valueOf(3)}, new Integer[]{Integer.valueOf(5)}, null), null, null, null, null, Boolean.valueOf(z), "appodeal", this.b, "USD", null, null, null, null));
        }
        jSONObject.put("imp", jSONArray);
        return jSONObject;
    }

    public JSONObject a(int i, List<Pair<Integer, Integer>> list, int i2) {
        int i3;
        JSONObject jSONObject = new JSONObject(this.k.toString());
        JSONArray jSONArray = new JSONArray();
        if (list.isEmpty()) {
            list.add(new Pair(Integer.valueOf(1200), Integer.valueOf(627)));
        }
        if (i2 == 0) {
            i3 = 50;
        } else {
            i3 = i2;
        }
        for (Pair pair : list) {
            JSONArray jSONArray2 = jSONArray;
            jSONArray2.put(a(String.valueOf(list.indexOf(pair) + 1), null, null, a(i, 40, new String[]{"image/jpg", "image/gif", "image/png"}, ((Integer) pair.first).intValue(), ((Integer) pair.second).intValue(), i3, new String[]{"video/mp4"}, Integer.valueOf(1), Integer.valueOf(Native.w / 1000), new Integer[]{Integer.valueOf(2), Integer.valueOf(5)}, true, true, false, true, true, false, true, true, false, false, false, false, false, false, true, false, true, null), null, null, Boolean.valueOf(false), "appodeal", this.b, "USD", null, null, null, null));
        }
        jSONObject.put("imp", jSONArray);
        return jSONObject;
    }

    public JSONObject a(@Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3, @Nullable Integer num4, @Nullable Integer num5, @Nullable Integer num6, @Nullable String str, @Nullable Integer num7, @Nullable Integer[] numArr, @Nullable Integer[] numArr2, @Nullable String[] strArr, @Nullable Boolean bool, @Nullable Integer[] numArr3, @Nullable Integer[] numArr4, @Nullable JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("w", num);
        jSONObject2.put("h", num2);
        jSONObject2.put("wmax", num3);
        jSONObject2.put("hmax", num4);
        jSONObject2.put("wmin", num5);
        jSONObject2.put("hmin", num6);
        jSONObject2.put("id", str);
        jSONObject2.put("pos", num7);
        if (numArr != null && numArr.length > 0) {
            jSONObject2.put("btype", a((Object[]) numArr));
        }
        if (numArr2 != null && numArr2.length > 0) {
            jSONObject2.put("battr", a((Object[]) numArr2));
        }
        if (strArr != null && strArr.length > 0) {
            jSONObject2.put("mimes", a((Object[]) strArr));
        }
        if (bool != null) {
            jSONObject2.put("topframe", bool.booleanValue() ? 1 : 0);
        }
        if (numArr3 != null && numArr3.length > 0) {
            jSONObject2.put("expdir", a((Object[]) numArr3));
        }
        if (numArr4 != null && numArr4.length > 0) {
            jSONObject2.put("api", a((Object[]) numArr4));
        }
        jSONObject2.put("ext", jSONObject);
        return jSONObject2;
    }

    public JSONObject a(@NonNull String[] strArr, @NonNull Integer num, @NonNull Integer num2, @Nullable Integer num3, @Nullable Integer[] numArr, @Nullable Integer num4, @Nullable Integer num5, @Nullable Integer num6, @Nullable Integer num7, @Nullable Integer num8, @Nullable Integer[] numArr2, @Nullable Integer num9, @Nullable Integer num10, @Nullable Integer num11, @Nullable Boolean bool, @Nullable Integer[] numArr3, @Nullable Integer[] numArr4, @Nullable Integer num12, @Nullable JSONArray jSONArray, @Nullable Integer[] numArr5, @Nullable Integer[] numArr6, @Nullable JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("mimes", a((Object[]) strArr));
        jSONObject2.put("minduration", num);
        if (num2.intValue() > 0) {
            jSONObject2.put("maxduration", num2);
        }
        jSONObject2.put("protocol", num3);
        if (numArr != null && numArr.length > 0) {
            jSONObject2.put("protocols", a((Object[]) numArr));
        }
        jSONObject2.put("w", num4);
        jSONObject2.put("h", num5);
        jSONObject2.put("startdelay", num6);
        jSONObject2.put("linearity", num7);
        jSONObject2.put("sequence", num8);
        if (numArr2 != null && numArr2.length > 0) {
            jSONObject2.put("battr", a((Object[]) numArr2));
        }
        jSONObject2.put("maxextended", num9);
        jSONObject2.put("minbitrate", num10);
        jSONObject2.put("maxbitrate", num11);
        if (bool != null) {
            jSONObject2.put("boxingallowed", bool.booleanValue() ? 1 : 0);
        }
        if (numArr3 != null && numArr3.length > 0) {
            jSONObject2.put("playbackmethod", a((Object[]) numArr3));
        }
        if (numArr4 != null && numArr4.length > 0) {
            jSONObject2.put("delivery", a((Object[]) numArr4));
        }
        jSONObject2.put("pos", num12);
        jSONObject2.put("companionad", jSONArray);
        if (numArr5 != null && numArr5.length > 0) {
            jSONObject2.put("api", a((Object[]) numArr5));
        }
        if (numArr6 != null && numArr6.length > 0) {
            jSONObject2.put("companiontype", a((Object[]) numArr6));
        }
        jSONObject2.put("ext", jSONObject);
        return jSONObject2;
    }

    public JSONObject a(int i, int i2, @NonNull String[] strArr, int i3, int i4, int i5, @NonNull String[] strArr2, @NonNull Integer num, @NonNull Integer num2, @NonNull Integer[] numArr, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, boolean z16, boolean z17, @Nullable JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("ver", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("ver", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        jSONObject3.put("plcmtcnt", i);
        jSONObject3.put("layout", 6);
        JSONArray jSONArray = new JSONArray();
        if (z) {
            jSONArray.put(a(0, 1, i2, null));
        }
        if (z2) {
            jSONArray.put(a(1, 1, 1, strArr, 0, 0, i5, i5, jSONObject));
        }
        if (z3) {
            jSONArray.put(a(2, 0, 2, strArr, 0, 0, 0, 0, jSONObject));
        }
        if (z4) {
            jSONArray.put(a(3, 0, 3, strArr, 0, 0, i3, i4, jSONObject));
        }
        if (z5) {
            jSONArray.put(a(4, 0, strArr2, num.intValue(), num2.intValue(), numArr, jSONObject));
        }
        if (z6) {
            jSONArray.put(a(5, 0, 1, 0, jSONObject));
        }
        if (z7) {
            jSONArray.put(a(6, 1, 2, 0, jSONObject));
        }
        if (z8) {
            jSONArray.put(a(7, 0, 3, 0, jSONObject));
        }
        if (z9) {
            jSONArray.put(a(8, 0, 4, 0, jSONObject));
        }
        if (z10) {
            jSONArray.put(a(9, 0, 5, 0, jSONObject));
        }
        if (z11) {
            jSONArray.put(a(10, 0, 6, 0, jSONObject));
        }
        if (z12) {
            jSONArray.put(a(11, 0, 7, 0, jSONObject));
        }
        if (z13) {
            jSONArray.put(a(12, 0, 8, 0, jSONObject));
        }
        if (z14) {
            jSONArray.put(a(13, 0, 9, 0, jSONObject));
        }
        if (z15) {
            jSONArray.put(a(14, 0, 10, 0, jSONObject));
        }
        if (z16) {
            jSONArray.put(a(15, 0, 11, 0, jSONObject));
        }
        if (z17) {
            jSONArray.put(a(16, 0, 12, 0, jSONObject));
        }
        jSONObject3.put("assets", jSONArray);
        jSONObject2.put(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, jSONObject3.toString());
        return jSONObject2;
    }

    private JSONObject a(int i, int i2, int i3, @Nullable JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("id", i);
        jSONObject2.put("required", i2);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("len", i3);
        jSONObject3.put("ext", jSONObject);
        jSONObject2.put("title", jSONObject3);
        return jSONObject2;
    }

    private JSONObject a(int i, int i2, int i3, @Nullable String[] strArr, int i4, int i5, int i6, int i7, @Nullable JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("id", i);
        jSONObject2.put("required", i2);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("type", i3);
        if (i4 != 0) {
            jSONObject3.put("w", i4);
        }
        if (i5 != 0) {
            jSONObject3.put("h", i5);
        }
        if (i6 != 0) {
            jSONObject3.put("wmin", i6);
        }
        if (i7 != 0) {
            jSONObject3.put("hmin", i7);
        }
        jSONObject3.put("mimes", a((Object[]) strArr));
        jSONObject3.put("ext", jSONObject);
        jSONObject2.put("img", jSONObject3);
        return jSONObject2;
    }

    private JSONObject a(int i, int i2, String[] strArr, int i3, int i4, @NonNull Integer[] numArr, @Nullable JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("id", i);
        jSONObject2.put("required", i2);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("mimes", a((Object[]) strArr));
        jSONObject3.put("minduration", i3);
        jSONObject3.put("maxduration", i4);
        jSONObject3.put("protocols", a((Object[]) numArr));
        jSONObject3.put("ext", jSONObject);
        jSONObject2.put(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO, jSONObject3);
        return jSONObject2;
    }

    private JSONObject a(int i, int i2, int i3, int i4, @Nullable JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("id", i);
        jSONObject2.put("required", i2);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("type", i3);
        if (i4 != 0) {
            jSONObject3.put("len", i4);
        }
        jSONObject3.put("ext", jSONObject);
        jSONObject2.put(ShareConstants.WEB_DIALOG_PARAM_DATA, jSONObject3);
        return jSONObject2;
    }

    public JSONObject a(@NonNull String str, @Nullable JSONObject jSONObject, @Nullable JSONObject jSONObject2, @Nullable JSONObject jSONObject3, @Nullable String str2, @Nullable String str3, @Nullable Boolean bool, @Nullable String str4, @Nullable Double d, @Nullable String str5, @Nullable Boolean bool2, @Nullable String[] strArr, @Nullable JSONObject jSONObject4, @Nullable JSONObject jSONObject5) {
        JSONObject jSONObject6 = new JSONObject();
        jSONObject6.put("id", str);
        jSONObject6.put("banner", jSONObject);
        jSONObject6.put(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO, jSONObject2);
        jSONObject6.put(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, jSONObject3);
        jSONObject6.put("displaymanager", str2);
        jSONObject6.put("displaymanagerver", str3);
        if (bool != null) {
            jSONObject6.put("instl", bool.booleanValue() ? 1 : 0);
        }
        jSONObject6.put("tagid", str4);
        jSONObject6.put("bidfloor", d);
        jSONObject6.put("bidfloorcur", str5);
        if (bool2 != null) {
            jSONObject6.put("secure", bool2.booleanValue() ? 1 : 0);
        }
        if (strArr != null && strArr.length > 0) {
            jSONObject6.put("iframebuster", a((Object[]) strArr));
        }
        jSONObject6.put("pmp", jSONObject4);
        jSONObject6.put("ext", jSONObject5);
        return jSONObject6;
    }

    public b a(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String[] strArr, @Nullable String[] strArr2, @Nullable String[] strArr3, @Nullable String str4, @Nullable String str5, @Nullable Boolean bool, @Nullable String str6, @Nullable String str7, @Nullable JSONObject jSONObject) {
        JSONObject jSONObject2;
        JSONObject optJSONObject = this.k.optJSONObject(SettingsJsonConstants.APP_KEY);
        if (optJSONObject == null) {
            jSONObject2 = new JSONObject();
        } else {
            jSONObject2 = optJSONObject;
        }
        if (str != null) {
            jSONObject2.put("id", str);
        }
        if (!(this.j && str2 == null)) {
            jSONObject2.put("name", str2);
        }
        if (!(this.j && str3 == null)) {
            jSONObject2.put("domain", str3);
        }
        if (!this.j || (strArr != null && strArr.length > 0)) {
            jSONObject2.put("cat", a((Object[]) strArr));
        }
        if (!this.j || (strArr2 != null && strArr2.length > 0)) {
            jSONObject2.put("sectioncat", a((Object[]) strArr2));
        }
        if (!this.j || (strArr3 != null && strArr3.length > 0)) {
            jSONObject2.put("pagecat", a((Object[]) strArr3));
        }
        if (!(this.j && str4 == null)) {
            jSONObject2.put("ver", str4);
        }
        if (!(this.j && str5 == null)) {
            jSONObject2.put("bundle", str5);
        }
        if (bool != null) {
            jSONObject2.put("privacypolicy", bool.booleanValue() ? 1 : 0);
        } else if (!this.j) {
            jSONObject2.put("privacypolicy", null);
        }
        if (!(this.j && str6 == null)) {
            jSONObject2.put("keywords", str6);
        }
        if (!(this.j && str7 == null)) {
            jSONObject2.put("storeUrl", str7);
        }
        if (!(this.j && jSONObject == null)) {
            jSONObject2.put("ext", jSONObject);
        }
        if (this.e != null) {
            if (this.e.has("paid")) {
                jSONObject2.put("paid", this.e.optInt("paid"));
            }
            if (this.e.has("publisher")) {
                jSONObject2.put("publisher", a(this.e.optString("publisher"), this.e.optString("publisher_id", null)));
            }
            if (this.e.has("categories")) {
                jSONObject2.put("cat", this.e.getJSONArray("categories"));
            }
        }
        this.k.put(SettingsJsonConstants.APP_KEY, jSONObject2);
        return this;
    }

    public b a(boolean z, String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable String str11, @Nullable String str12, @Nullable String str13, @Nullable String str14, @Nullable String str15, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3, @Nullable Float f, @Nullable String str16, @Nullable String str17, boolean z2, @Nullable Integer num4, @Nullable Integer num5, @Nullable String str18, @Nullable JSONObject jSONObject) {
        JSONObject optJSONObject = this.k.optJSONObject("device");
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
        }
        optJSONObject.put("dnt", z ? 1 : 0);
        if (!(this.j && str == null)) {
            optJSONObject.put("lmt", Integer.valueOf(str).intValue() == 1 ? 0 : 1);
        }
        if (str2 != null) {
            optJSONObject.put("ua", str2);
        }
        if (!(this.j && str3 == null)) {
            optJSONObject.put("ip", str3);
        }
        if (!(this.j && str4 == null)) {
            optJSONObject.put("didsha1", str4);
        }
        if (!(this.j && str5 == null)) {
            optJSONObject.put("didmp5", str5);
        }
        if (!(this.j && str6 == null)) {
            optJSONObject.put("dpidsha1", str6);
        }
        if (!(this.j && str7 == null)) {
            optJSONObject.put("dpidmd5", str7);
        }
        if (!(this.j && str8 == null)) {
            optJSONObject.put("macsha1", str8);
        }
        if (!(this.j && str9 == null)) {
            optJSONObject.put("macmd5", str9);
        }
        if (!(this.j && str10 == null)) {
            optJSONObject.put("ipv6", str10);
        }
        if (!(this.j && str11 == null)) {
            optJSONObject.put("carrier", str11);
        }
        if (!(this.j && str12 == null)) {
            optJSONObject.put("language", str12);
        }
        if (!(this.j && str13 == null)) {
            optJSONObject.put("make", str13);
        }
        if (!(this.j && str14 == null)) {
            optJSONObject.put("model", str14);
        }
        if (!(this.j && str15 == null)) {
            optJSONObject.put("hwv", str15);
        }
        if (!(this.j && num == null)) {
            optJSONObject.put("h", num);
        }
        if (!(this.j && num2 == null)) {
            optJSONObject.put("w", num2);
        }
        if (!(this.j && num3 == null)) {
            optJSONObject.put("ppi", num3);
        }
        if (!(this.j && f == null)) {
            optJSONObject.put("pxratio", f);
        }
        if (!(this.j && str16 == null)) {
            optJSONObject.put("os", str16);
        }
        if (!(this.j && str17 == null)) {
            optJSONObject.put("osv", str17);
        }
        optJSONObject.put("js", z2 ? 1 : 0);
        if (!(this.j && num4 == null)) {
            optJSONObject.put("connectiontype", num4);
        }
        if (!(this.j && num5 == null)) {
            optJSONObject.put("devicetype", num5);
        }
        if (!(this.j && str18 == null)) {
            optJSONObject.put("ifa", str18);
        }
        if (!(this.j && jSONObject == null)) {
            optJSONObject.put("ext", jSONObject);
        }
        optJSONObject.put("flashver", AppEventsConstants.EVENT_PARAM_VALUE_NO);
        this.k.put("device", optJSONObject);
        return this;
    }

    public b a(@Nullable Float f, @Nullable Float f2, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable Integer num, @Nullable Integer num2, @Nullable JSONObject jSONObject) {
        JSONObject optJSONObject = this.k.optJSONObject("device");
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
        }
        JSONObject optJSONObject2 = optJSONObject.optJSONObject(AdWebViewClient.GEO);
        if (optJSONObject2 == null) {
            optJSONObject2 = new JSONObject();
        }
        if (!(this.j && f == null)) {
            optJSONObject2.put("lat", f);
        }
        if (!(this.j && f2 == null)) {
            optJSONObject2.put("lon", f2);
        }
        if (!(this.j && str == null)) {
            optJSONObject2.put("country", str);
        }
        if (!(this.j && str2 == null)) {
            optJSONObject2.put("region", str2);
        }
        if (!(this.j && str3 == null)) {
            optJSONObject2.put("regionfips104", str3);
        }
        if (!(this.j && str4 == null)) {
            optJSONObject2.put("metro", str4);
        }
        if (!(this.j && str5 == null)) {
            optJSONObject2.put("city", str5);
        }
        if (!(this.j && str6 == null)) {
            optJSONObject2.put("zip", str6);
        }
        if (!(this.j && num == null)) {
            optJSONObject2.put("type", num);
        }
        if (!(this.j && num2 == null)) {
            optJSONObject2.put("utcoffset", num2);
        }
        if (!(this.j && jSONObject == null)) {
            optJSONObject2.put("ext", jSONObject);
        }
        optJSONObject.put(AdWebViewClient.GEO, optJSONObject2);
        this.k.put("device", optJSONObject);
        return this;
    }

    public b a(@Nullable String str, @Nullable String str2, @Nullable Integer num, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable JSONObject jSONObject) {
        JSONObject optJSONObject = this.k.optJSONObject("user");
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
        }
        if (!(this.j && str == null)) {
            optJSONObject.put("id", str);
        }
        if (!(this.j && str2 == null)) {
            optJSONObject.put("lon", str2);
        }
        if (!(this.j && num == null)) {
            optJSONObject.put("yob", num);
        }
        if (!(this.j && str3 == null)) {
            optJSONObject.put("gender", str3);
        }
        if (!(this.j && str4 == null)) {
            optJSONObject.put("keywords", str4);
        }
        if (!(this.j && str5 == null)) {
            optJSONObject.put("customdata", str5);
        }
        if (!(this.j && jSONObject == null)) {
            optJSONObject.put("ext", jSONObject);
        }
        this.k.put("user", optJSONObject);
        return this;
    }

    public b a(@Nullable String str, @Nullable String str2, @Nullable JSONArray jSONArray, @Nullable JSONObject jSONObject) {
        JSONObject optJSONObject = this.k.optJSONObject("user");
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
        }
        JSONArray optJSONArray = optJSONObject.optJSONArray(ShareConstants.WEB_DIALOG_PARAM_DATA);
        if (optJSONArray == null) {
            optJSONArray = new JSONArray();
        }
        JSONObject jSONObject2 = new JSONObject();
        if (!(this.j && str == null)) {
            jSONObject2.put("id", str);
        }
        if (!(this.j && str2 == null)) {
            jSONObject2.put("name", str2);
        }
        if (!(this.j && jSONArray == null)) {
            jSONObject2.put("segment", jSONArray);
        }
        if (!(this.j && jSONObject == null)) {
            jSONObject2.put("ext", jSONObject);
        }
        optJSONArray.put(jSONObject2);
        optJSONObject.put(ShareConstants.WEB_DIALOG_PARAM_DATA, optJSONArray);
        this.k.put("user", optJSONObject);
        return this;
    }

    public static JSONObject a(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable JSONObject jSONObject) {
        return new JSONObject().put("id", str).put("name", str2).put(Param.VALUE, str3).put("ext", jSONObject);
    }

    private int c() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.i.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return 0;
        }
        switch (activeNetworkInfo.getType()) {
            case 0:
                return 3;
            case 1:
                return 2;
            case 9:
                return 1;
            default:
                return 0;
        }
    }

    public String a() {
        return this.i.getSharedPreferences("appodeal", 0).getString("advertisingTracking", null);
    }

    static JSONArray a(Object[] objArr) {
        if (VERSION.SDK_INT >= 19) {
            return new JSONArray(objArr);
        }
        JSONArray jSONArray = new JSONArray();
        for (Object put : objArr) {
            jSONArray.put(put);
        }
        return jSONArray;
    }

    public void a(Float f, JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("imp");
        if (optJSONArray != null && f != null && f.floatValue() >= DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                try {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    jSONObject2.put("bidfloor", jSONObject2.getDouble("bidfloor") * ((double) f.floatValue()));
                    JSONArray jSONArray = jSONObject2.getJSONObject("pmp").getJSONArray("deals");
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        JSONObject jSONObject3 = jSONArray.getJSONObject(i2);
                        if (jSONObject3.has("bidfloor")) {
                            jSONObject3.put("bidfloor", jSONObject3.getDouble("bidfloor") * ((double) f.floatValue()));
                        }
                    }
                } catch (JSONException e) {
                }
            }
        }
    }

    private void d() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("coppa", this.f ? 1 : 0);
        this.k.put("regs", jSONObject);
    }

    private JSONObject a(String str, String str2) {
        if (str == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", str);
        jSONObject.put("id", str2);
        return jSONObject;
    }

    private void a(JSONObject jSONObject) {
        if (this.g == 0 || this.h == 0) {
            DisplayMetrics displayMetrics = this.i.getResources().getDisplayMetrics();
            this.g = displayMetrics.widthPixels;
            this.h = displayMetrics.heightPixels;
        }
        JSONObject a = a(Integer.valueOf(this.g), Integer.valueOf(this.h), Integer.valueOf(this.g), Integer.valueOf(this.h), null, null, null, Integer.valueOf(7), null, null, new String[]{"image/jpg", "image/gif", "image/png"}, Boolean.valueOf(false), new Integer[]{Integer.valueOf(5), Integer.valueOf(3)}, new Integer[]{Integer.valueOf(5)}, null);
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(a);
        jSONObject.put("companionad", jSONArray);
    }

    private String[] a(e eVar) {
        String[] strArr;
        List arrayList = new ArrayList();
        if (eVar.h()) {
            if (eVar.f()) {
                arrayList.add("video/mp4");
            }
            if (VERSION.SDK_INT >= 17 && eVar.g()) {
                arrayList.add(WebRequest.CONTENT_TYPE_JAVASCRIPT);
            }
            strArr = new String[arrayList.size()];
        } else {
            arrayList.add("video/mp4");
            if (VERSION.SDK_INT >= 17) {
                arrayList.add(WebRequest.CONTENT_TYPE_JAVASCRIPT);
            }
            strArr = new String[arrayList.size()];
        }
        arrayList.toArray(strArr);
        return strArr;
    }
}
