package com.appodeal.ads.f;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import org.json.JSONArray;
import org.json.JSONObject;

public class e {
    final String a;
    final b b;
    Object c;
    a d = a(this.a);

    enum a {
        Version,
        String,
        StringArray,
        Integer,
        IntegerArray,
        Double,
        Boolean,
        Unknown
    }

    e(JSONObject jSONObject) {
        this.a = jSONObject.getString("name");
        this.b = b.a(jSONObject.getString("op"));
        this.c = a(jSONObject);
    }

    private Object a(JSONObject jSONObject) {
        switch (this.d) {
            case Version:
                return new h(jSONObject.getString(Param.VALUE));
            case String:
                return jSONObject.getString(Param.VALUE);
            case StringArray:
                return c(jSONObject);
            case Integer:
                return Integer.valueOf(jSONObject.getInt(Param.VALUE));
            case IntegerArray:
                return b(jSONObject);
            case Double:
                return Double.valueOf(jSONObject.getString(Param.VALUE));
            case Boolean:
                return Boolean.valueOf(jSONObject.getString(Param.VALUE));
            case Unknown:
                return jSONObject.getString(Param.VALUE);
            default:
                return null;
        }
    }

    private Integer[] b(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray(Param.VALUE);
        Integer[] numArr = new Integer[optJSONArray.length()];
        for (int i = 0; i < optJSONArray.length(); i++) {
            numArr[i] = Integer.valueOf(optJSONArray.getString(i));
        }
        return numArr;
    }

    private String[] c(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray(Param.VALUE);
        String[] strArr = new String[optJSONArray.length()];
        for (int i = 0; i < optJSONArray.length(); i++) {
            strArr[i] = optJSONArray.getString(i);
        }
        return strArr;
    }

    private a a(String str) {
        if (str.equals(SettingsJsonConstants.APP_KEY)) {
            return a.StringArray;
        }
        if (str.equals("app_version")) {
            return a.Version;
        }
        if (str.equals("sdk_version")) {
            return a.Version;
        }
        if (str.equals("country")) {
            return a.StringArray;
        }
        if (str.equals("android_version")) {
            return a.Version;
        }
        if (str.equals("has_app_installed")) {
            return a.String;
        }
        if (str.equals("session_count")) {
            return a.Integer;
        }
        if (str.equals("average_session_length")) {
            return a.Double;
        }
        if (str.equals("device_model")) {
            return a.StringArray;
        }
        if (str.equals("connection_type")) {
            return a.IntegerArray;
        }
        if (str.equals("gender")) {
            return a.IntegerArray;
        }
        if (str.equals("age")) {
            return a.Integer;
        }
        if (str.equals("occupation")) {
            return a.IntegerArray;
        }
        if (str.equals("relation")) {
            return a.IntegerArray;
        }
        if (str.equals("interests")) {
            return a.StringArray;
        }
        if (str.equals("last_session_time")) {
            return a.Integer;
        }
        if (str.equals("bought_inapps")) {
            return a.Boolean;
        }
        if (str.equals("inapp_sum")) {
            return a.Double;
        }
        if (str.equals("inapp_sum_all_apps")) {
            return a.Double;
        }
        if (str.equals("device_type")) {
            return a.StringArray;
        }
        if (str.equals("platform")) {
            return a.StringArray;
        }
        if (str.equals("day")) {
            return a.Integer;
        }
        if (str.equals("hour")) {
            return a.Integer;
        }
        return a.Unknown;
    }

    public static a a(Object obj) {
        if (obj instanceof Integer) {
            return a.Integer;
        }
        if (obj instanceof Double) {
            return a.Double;
        }
        if (obj instanceof Boolean) {
            return a.Boolean;
        }
        if (obj instanceof String) {
            return a.String;
        }
        return a.Unknown;
    }

    public void a() {
        if (this.d == a.Integer) {
            this.c = c(this.c);
        } else if (this.d == a.Double) {
            this.c = b(this.c);
        } else if (this.d == a.Boolean) {
            this.c = d(this.c);
        }
    }

    private Double b(Object obj) {
        if (obj instanceof String) {
            return Double.valueOf((String) obj);
        }
        if (obj instanceof Double) {
            return (Double) obj;
        }
        return null;
    }

    private Integer c(Object obj) {
        if (obj instanceof String) {
            return Integer.valueOf((String) obj);
        }
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        return null;
    }

    private Boolean d(Object obj) {
        if (obj instanceof String) {
            return Boolean.valueOf((String) obj);
        }
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        return null;
    }
}
