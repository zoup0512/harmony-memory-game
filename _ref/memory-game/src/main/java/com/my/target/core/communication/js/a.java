package com.my.target.core.communication.js;

import android.webkit.ConsoleMessage;
import com.facebook.share.internal.ShareConstants;
import com.my.target.core.communication.js.events.b;
import com.my.target.core.communication.js.events.c;
import com.my.target.core.communication.js.events.d;
import com.my.target.core.communication.js.events.e;
import com.my.target.core.communication.js.events.f;
import com.my.target.core.communication.js.events.g;
import com.my.target.core.communication.js.events.h;
import com.my.target.core.communication.js.events.i;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: JSEventDeserializer */
public final class a {
    public static f a(ConsoleMessage consoleMessage) {
        String message = consoleMessage.message();
        if (!(message == null || message.equals("") || !message.startsWith("adman://onEvent,"))) {
            try {
                return a(new JSONObject(message.substring(16)));
            } catch (JSONException e) {
            }
        }
        return null;
    }

    private static f a(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2;
        int i = 0;
        String str = null;
        String string = jSONObject.getString("event");
        if (jSONObject.has(ShareConstants.WEB_DIALOG_PARAM_DATA)) {
            jSONObject2 = jSONObject.getJSONObject(ShareConstants.WEB_DIALOG_PARAM_DATA);
        } else {
            jSONObject2 = null;
        }
        if (string.equals("onError")) {
            if (jSONObject2 == null) {
                return new e();
            }
            str = "jsError";
            if (jSONObject2.has("error")) {
                str = str + " error: " + jSONObject2.getString("error");
            }
            if (jSONObject2.has("message")) {
                str = str + " message: " + jSONObject2.getString("message");
            }
            return new e(str);
        } else if (string.equals("onExpand")) {
            if (jSONObject2 != null && jSONObject2.has("width") && jSONObject2.has("height")) {
                return new g(jSONObject2.getInt("width"), jSONObject2.getInt("height"));
            }
            return new g();
        } else if (string.equals("onAdStart")) {
            if (jSONObject2 == null || !jSONObject2.has("format") || !jSONObject2.has("banners")) {
                return null;
            }
            string = jSONObject2.getString("format");
            JSONArray jSONArray = jSONObject2.getJSONArray("banners");
            int length = jSONArray.length();
            String[] strArr = new String[length];
            while (i < length) {
                strArr[i] = jSONArray.getString(i);
                i++;
            }
            return new d(strArr, string);
        } else if (string.equals("onSizeChange")) {
            if (jSONObject2 != null && jSONObject2.has("width") && jSONObject2.has("height")) {
                return new h(jSONObject2.getInt("width"), jSONObject2.getInt("height"));
            }
            return null;
        } else if (string.equals("onStat")) {
            if (jSONObject2 == null || !jSONObject2.has("stats")) {
                return null;
            }
            JSONArray jSONArray2 = jSONObject2.getJSONArray("stats");
            int length2 = jSONArray2.length();
            List arrayList = new ArrayList();
            while (i < length2) {
                arrayList.add(jSONArray2.getString(i));
                i++;
            }
            if (jSONObject2.has("type")) {
                str = jSONObject2.getString("type");
            }
            return new i(arrayList, str);
        } else if (!string.equals("onAdClick")) {
            return new b(string);
        } else {
            if (jSONObject2 == null || !jSONObject2.has("format") || !jSONObject2.has("bannerId")) {
                return null;
            }
            return new c(jSONObject2.getString("bannerId"), jSONObject2.getString("format"));
        }
    }
}
