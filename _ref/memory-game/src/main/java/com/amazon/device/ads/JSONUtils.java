package com.amazon.device.ads;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class JSONUtils {

    public static class JSONUtilities {
        public boolean getBooleanFromJSON(JSONObject jSONObject, String str, boolean z) {
            return JSONUtils.getBooleanFromJSON(jSONObject, str, z);
        }

        public String getStringFromJSON(JSONObject jSONObject, String str, String str2) {
            return JSONUtils.getStringFromJSON(jSONObject, str, str2);
        }

        public int getIntegerFromJSON(JSONObject jSONObject, String str, int i) {
            return JSONUtils.getIntegerFromJSON(jSONObject, str, i);
        }

        public long getLongFromJSON(JSONObject jSONObject, String str, long j) {
            return JSONUtils.getLongFromJSON(jSONObject, str, j);
        }

        public JSONArray getJSONArrayFromJSON(JSONObject jSONObject, String str) {
            return JSONUtils.getJSONArrayFromJSON(jSONObject, str);
        }

        public int getIntegerFromJSONArray(JSONArray jSONArray, int i, int i2) {
            return JSONUtils.getIntegerFromJSONArray(jSONArray, i, i2);
        }

        public JSONObject getJSONObjectFromJSONArray(JSONArray jSONArray, int i) {
            return JSONUtils.getJSONObjectFromJSONArray(jSONArray, i);
        }

        public Map<String, String> createMapFromJSON(JSONObject jSONObject) {
            return JSONUtils.createMapFromJSON(jSONObject);
        }

        public void put(JSONObject jSONObject, String str, String str2) {
            JSONUtils.put(jSONObject, str, str2);
        }

        public void put(JSONObject jSONObject, String str, int i) {
            JSONUtils.put(jSONObject, str, i);
        }

        public void put(JSONObject jSONObject, String str, long j) {
            JSONUtils.put(jSONObject, str, j);
        }

        public void put(JSONObject jSONObject, String str, boolean z) {
            JSONUtils.put(jSONObject, str, z);
        }

        public JSONObject getJSONObjectFromString(String str) {
            return JSONUtils.getJSONObjectFromString(str);
        }
    }

    JSONUtils() {
    }

    public static boolean getBooleanFromJSON(JSONObject jSONObject, String str, boolean z) {
        if (jSONObject.isNull(str)) {
            return z;
        }
        return jSONObject.optBoolean(str, z);
    }

    public static String getStringFromJSON(JSONObject jSONObject, String str, String str2) {
        if (jSONObject.isNull(str)) {
            return str2;
        }
        return jSONObject.optString(str, str2);
    }

    public static int getIntegerFromJSON(JSONObject jSONObject, String str, int i) {
        if (jSONObject.isNull(str)) {
            return i;
        }
        return jSONObject.optInt(str, i);
    }

    public static long getLongFromJSON(JSONObject jSONObject, String str, long j) {
        if (jSONObject.isNull(str)) {
            return j;
        }
        return jSONObject.optLong(str, j);
    }

    public static JSONArray getJSONArrayFromJSON(JSONObject jSONObject, String str) {
        if (jSONObject.isNull(str)) {
            return null;
        }
        return jSONObject.optJSONArray(str);
    }

    public static int getIntegerFromJSONArray(JSONArray jSONArray, int i, int i2) {
        if (jSONArray.isNull(i)) {
            return i2;
        }
        return jSONArray.optInt(i, i2);
    }

    public static JSONObject getJSONObjectFromJSONArray(JSONArray jSONArray, int i) {
        JSONObject jSONObject = null;
        if (!jSONArray.isNull(i)) {
            try {
                jSONObject = jSONArray.getJSONObject(i);
            } catch (JSONException e) {
            }
        }
        return jSONObject;
    }

    public static void put(JSONObject jSONObject, String str, String str2) {
        if (str2 != null && !str2.equals("")) {
            try {
                jSONObject.put(str, str2);
            } catch (JSONException e) {
            }
        }
    }

    public static void put(JSONObject jSONObject, String str, int i) {
        try {
            jSONObject.put(str, i);
        } catch (JSONException e) {
        }
    }

    public static void put(JSONObject jSONObject, String str, long j) {
        try {
            jSONObject.put(str, j);
        } catch (JSONException e) {
        }
    }

    public static void put(JSONObject jSONObject, String str, boolean z) {
        try {
            jSONObject.put(str, z);
        } catch (JSONException e) {
        }
    }

    public static JSONObject getJSONObjectFromString(String str) {
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            return null;
        }
    }

    public static Map<String, String> createMapFromJSON(JSONObject jSONObject) {
        Map<String, String> hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            String stringFromJSON = getStringFromJSON(jSONObject, str, null);
            if (stringFromJSON != null) {
                hashMap.put(str, stringFromJSON);
            }
        }
        return hashMap;
    }
}
