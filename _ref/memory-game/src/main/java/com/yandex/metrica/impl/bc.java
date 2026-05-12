package com.yandex.metrica.impl;

import android.text.TextUtils;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.yandex.metrica.impl.ob.cm;
import com.yandex.metrica.impl.utils.h;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class bc {

    static class a {
        private a a;
        private boolean b;
        private String c;
        private String d;
        private String e;
        private String f;
        private String g;
        private String h;
        private String i;
        private String j;
        private cm k = new cm();

        public enum a {
            BAD,
            OK
        }

        a() {
        }

        void a(boolean z) {
            this.b = z;
        }

        public boolean a() {
            return this.b;
        }

        void a(String str) {
            this.c = str;
        }

        public String b() {
            return this.c;
        }

        void b(String str) {
            this.d = str;
        }

        public String c() {
            return this.d;
        }

        void c(String str) {
            this.e = str;
        }

        public String d() {
            return this.e;
        }

        void d(String str) {
            this.g = str;
        }

        public String e() {
            return this.g;
        }

        void e(String str) {
            this.h = str;
        }

        public String f() {
            return this.h;
        }

        void f(String str) {
            this.i = str;
        }

        public String g() {
            return this.i;
        }

        void g(String str) {
            this.j = str;
        }

        public String h() {
            return this.j;
        }

        void a(a aVar) {
            this.a = aVar;
        }

        public a i() {
            return this.a;
        }

        public void a(cm cmVar) {
            this.k = cmVar;
        }

        public cm j() {
            return this.k;
        }

        public String k() {
            return this.f;
        }

        public void h(String str) {
            this.f = str;
        }
    }

    private static String a(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getJSONObject(str).getString(Param.VALUE);
        } catch (Exception e) {
            return "";
        }
    }

    private static String b(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getJSONObject(str).getString("url");
        } catch (Exception e) {
            return "";
        }
    }

    public static a a(byte[] bArr) {
        a aVar = new a();
        try {
            a aVar2 = new a(new String(bArr, "UTF-8"));
            aVar.e(a((JSONObject) aVar2, "device_id"));
            aVar.f(a((JSONObject) aVar2, "uuid"));
            JSONObject jSONObject = (JSONObject) aVar2.a("query_hosts", new JSONObject());
            if (jSONObject.has("list")) {
                jSONObject = jSONObject.getJSONObject("list");
                String b = b(jSONObject, "get_ad");
                if (a(b)) {
                    aVar.a(b);
                }
                b = b(jSONObject, "report");
                if (a(b)) {
                    aVar.b(b);
                }
                b = b(jSONObject, "report_ad");
                if (a(b)) {
                    aVar.c(b);
                }
                b = b(jSONObject, "ssl_pinning");
                if (a(b)) {
                    aVar.d(b);
                }
                String b2 = b(jSONObject, "bind_id");
                if (a(b2)) {
                    aVar.h(b2);
                }
            }
            jSONObject = ((JSONObject) aVar2.a("distribution_customization", new JSONObject())).optJSONObject("clids");
            if (jSONObject != null) {
                a(aVar, jSONObject);
            }
            jSONObject = (JSONObject) aVar2.a(SettingsJsonConstants.FEATURES_KEY, new JSONObject());
            aVar.a(false);
            if (jSONObject.has("list")) {
                jSONObject = jSONObject.getJSONObject("list");
                if (jSONObject.has("easy_collecting")) {
                    aVar.a(jSONObject.getJSONObject("easy_collecting").optBoolean("enabled", false));
                }
            }
            a(aVar, aVar2);
            aVar.a(a.OK);
            return aVar;
        } catch (Exception e) {
            a aVar3 = new a();
            aVar3.a(a.BAD);
            return aVar3;
        }
    }

    private static void a(a aVar, a aVar2) throws JSONException {
        JSONObject optJSONObject = aVar2.optJSONObject("browsers");
        if (optJSONObject != null) {
            JSONArray optJSONArray = optJSONObject.optJSONArray("list");
            if (optJSONArray != null) {
                cm cmVar = new cm();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject = optJSONArray.getJSONObject(i);
                    Object optString = jSONObject.optString("package_id");
                    if (!TextUtils.isEmpty(optString)) {
                        cmVar.a(optString, jSONObject.optInt("min_interval_seconds"));
                    }
                }
                aVar.a(cmVar);
            }
        }
    }

    private static boolean a(String str) {
        return !be.a(str);
    }

    private static void a(a aVar, JSONObject jSONObject) throws JSONException {
        Map hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            JSONObject optJSONObject = jSONObject.optJSONObject(str);
            if (optJSONObject != null && optJSONObject.has(Param.VALUE)) {
                hashMap.put(str, optJSONObject.getString(Param.VALUE));
            }
        }
        aVar.g(h.a(hashMap));
    }

    public static Long a(Map<String, List<String>> map) {
        if (!bg.a((Map) map)) {
            Collection collection = (List) map.get("Date");
            if (!bg.a(collection)) {
                try {
                    return Long.valueOf(new SimpleDateFormat("E, d MMM yyyy HH:mm:ss z", Locale.US).parse((String) collection.get(0)).getTime());
                } catch (Exception e) {
                }
            }
        }
        return null;
    }
}
