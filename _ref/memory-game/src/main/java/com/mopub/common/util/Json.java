package com.mopub.common.util;

import android.text.TextUtils;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferTable;
import com.mopub.common.logging.MoPubLog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Json {
    public static Map<String, String> jsonStringToMap(String str) {
        Map<String, String> hashMap = new HashMap();
        if (TextUtils.isEmpty(str)) {
            return hashMap;
        }
        JSONObject jSONObject = (JSONObject) new JSONTokener(str).nextValue();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str2 = (String) keys.next();
            hashMap.put(str2, jSONObject.getString(str2));
        }
        return hashMap;
    }

    public static String mapToJsonString(Map<String, String> map) {
        if (map == null) {
            return "{}";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        Object obj = 1;
        for (Entry entry : map.entrySet()) {
            if (obj == null) {
                stringBuilder.append(",");
            }
            stringBuilder.append("\"");
            stringBuilder.append((String) entry.getKey());
            stringBuilder.append("\":\"");
            stringBuilder.append((String) entry.getValue());
            stringBuilder.append("\"");
            obj = null;
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public static String[] jsonArrayToStringArray(String str) {
        try {
            JSONArray jSONArray = ((JSONObject) new JSONTokener("{key:" + str + "}").nextValue()).getJSONArray(TransferTable.COLUMN_KEY);
            String[] strArr = new String[jSONArray.length()];
            for (int i = 0; i < strArr.length; i++) {
                strArr[i] = jSONArray.getString(i);
            }
            return strArr;
        } catch (JSONException e) {
            return new String[0];
        }
    }

    public static <T> T getJsonValue(JSONObject jSONObject, String str, Class<T> cls) {
        if (jSONObject == null || str == null || cls == null) {
            throw new IllegalArgumentException("Cannot pass any null argument to getJsonValue");
        }
        Object opt = jSONObject.opt(str);
        if (opt == null) {
            MoPubLog.w("Tried to get Json value with key: " + str + ", but it was null");
            return null;
        } else if (cls.isInstance(opt)) {
            return cls.cast(opt);
        } else {
            MoPubLog.w("Tried to get Json value with key: " + str + ", of type: " + cls.toString() + ", its type did not match");
            return null;
        }
    }
}
