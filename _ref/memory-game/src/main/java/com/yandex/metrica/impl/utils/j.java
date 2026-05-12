package com.yandex.metrica.impl.utils;

import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.yandex.metrica.d;
import com.yandex.metrica.impl.bg;
import org.json.JSONException;
import org.json.JSONObject;

public class j {

    public enum a {
        LOGIN("login"),
        LOGOUT("logout"),
        SWITCH("switch"),
        UPDATE("update");
        
        private String e;

        private a(String str) {
            this.e = str;
        }

        public String toString() {
            return this.e;
        }
    }

    public static d a(String str) {
        d dVar = new d();
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                dVar.a(jSONObject.optString("UserInfo.UserId", null));
                dVar.b(jSONObject.optString("UserInfo.Type", null));
                dVar.a(bg.a(jSONObject.optJSONObject("UserInfo.Options")));
            } catch (JSONException e) {
            }
        }
        return dVar;
    }

    public static String a(a aVar) {
        String str = null;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt(NativeProtocol.WEB_DIALOG_ACTION, aVar.toString());
            str = jSONObject.toString();
        } catch (JSONException e) {
        }
        return str;
    }
}
