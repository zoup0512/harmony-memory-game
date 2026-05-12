package com.chartboost.sdk.Libraries;

import android.os.Build.VERSION;
import android.text.TextUtils;
import butterknife.internal.ButterKnifeProcessor;
import com.amazonaws.services.s3.internal.Constants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class e {

    public static class a {
        public static final a a = new a(null);
        private final Object b;

        private a(Object obj) {
            this.b = obj;
        }

        public static a a() {
            return a(com.chartboost.sdk.impl.a.a().d());
        }

        public a a(String str) {
            if (this.b instanceof JSONObject) {
                return a(((JSONObject) this.b).opt(str));
            }
            if (this.b instanceof Map) {
                return a(((Map) this.b).get(str));
            }
            return a;
        }

        public boolean b() {
            return this.b == null || this.b == JSONObject.NULL;
        }

        public boolean c() {
            return !b();
        }

        public boolean d() {
            return !TextUtils.isEmpty(i());
        }

        public boolean b(String str) {
            return a(str).b();
        }

        public boolean c(String str) {
            return a(str).c();
        }

        public JSONObject e() {
            if (this.b instanceof JSONObject) {
                return (JSONObject) this.b;
            }
            if (this.b instanceof Map) {
                return e.a((Map) this.b);
            }
            return null;
        }

        public Map<String, Object> f() {
            if (this.b instanceof JSONObject) {
                return e.a((JSONObject) this.b);
            }
            if (this.b instanceof Map) {
                return (Map) this.b;
            }
            return null;
        }

        public JSONArray g() {
            if (this.b instanceof JSONArray) {
                return (JSONArray) this.b;
            }
            if (this.b instanceof List) {
                return e.a((List) this.b);
            }
            return null;
        }

        public List<?> h() {
            if (this.b instanceof JSONArray) {
                return e.a((JSONArray) this.b);
            }
            if (this.b instanceof List) {
                return (List) this.b;
            }
            return null;
        }

        public String i() {
            if (b()) {
                return null;
            }
            return this.b instanceof String ? (String) this.b : this.b.toString();
        }

        public String d(String str) {
            return this.b instanceof String ? (String) this.b : str;
        }

        public double j() {
            return a(0.0d);
        }

        public double a(double d) {
            if (!(this.b instanceof String)) {
                return this.b instanceof Number ? ((Number) this.b).doubleValue() : d;
            } else {
                try {
                    return Double.parseDouble((String) this.b);
                } catch (NumberFormatException e) {
                    return d;
                }
            }
        }

        public float k() {
            return a(0.0f);
        }

        public float a(float f) {
            if (!(this.b instanceof String)) {
                return this.b instanceof Number ? ((Number) this.b).floatValue() : f;
            } else {
                try {
                    return Float.parseFloat((String) this.b);
                } catch (NumberFormatException e) {
                    return f;
                }
            }
        }

        public int l() {
            return a(0);
        }

        public int a(int i) {
            if (!(this.b instanceof String)) {
                return this.b instanceof Number ? ((Number) this.b).intValue() : i;
            } else {
                try {
                    return Integer.parseInt((String) this.b);
                } catch (NumberFormatException e) {
                    return i;
                }
            }
        }

        public long m() {
            return b(0);
        }

        public long b(int i) {
            if (!(this.b instanceof String)) {
                return this.b instanceof Number ? ((Number) this.b).longValue() : (long) i;
            } else {
                try {
                    return Long.parseLong((String) this.b);
                } catch (NumberFormatException e) {
                    return (long) i;
                }
            }
        }

        public boolean n() {
            return a(false);
        }

        public boolean a(boolean z) {
            return this.b instanceof Boolean ? ((Boolean) this.b).booleanValue() : z;
        }

        public boolean equals(Object other) {
            a a = a(other);
            if (b()) {
                return a.b();
            }
            JSONObject e = e();
            if (e != null) {
                JSONObject e2 = a.e();
                if (e2 != null) {
                    return j.a(e, e2);
                }
            }
            JSONArray g = g();
            if (g != null) {
                JSONArray g2 = a.g();
                if (g2 != null) {
                    return j.a(g, g2);
                }
            }
            if (this.b instanceof String) {
                return this.b.equals(a.i());
            }
            if (a.b instanceof String) {
                return a.b.equals(i());
            }
            return o().equals(a.o());
        }

        public Object o() {
            return this.b;
        }

        public static a a(Object obj) {
            if (obj instanceof a) {
                return (a) obj;
            }
            if (obj == null || obj == JSONObject.NULL) {
                return a;
            }
            return new a(obj);
        }

        public int p() {
            if (this.b instanceof JSONArray) {
                return ((JSONArray) this.b).length();
            }
            if (this.b instanceof List) {
                return ((List) this.b).size();
            }
            return 1;
        }

        public a c(int i) {
            if (this.b instanceof JSONArray) {
                return a(((JSONArray) this.b).opt(i));
            }
            if (!(this.b instanceof List)) {
                return i != 0 ? a : this;
            } else {
                try {
                    return a(((List) this.b).get(i));
                } catch (Exception e) {
                    com.chartboost.sdk.Tracking.a.a(getClass(), "get(int)", e);
                    return a;
                }
            }
        }

        public String e(String str) {
            return a(str).i();
        }

        public int f(String str) {
            return a(str).l();
        }

        public float g(String str) {
            return a(str).k();
        }

        public double h(String str) {
            return a(str).j();
        }

        public long i(String str) {
            return a(str).m();
        }

        public boolean j(String str) {
            return a(str).n();
        }

        public List<String> q() {
            if (this.b instanceof JSONObject) {
                return e.a(((JSONObject) this.b).names());
            }
            if (!(this.b instanceof Map)) {
                return null;
            }
            List<String> arrayList = new ArrayList();
            arrayList.addAll(((Map) this.b).keySet());
            return arrayList;
        }

        public void a(String str, Object obj) {
            if (obj instanceof a) {
                obj = ((a) obj).o();
            }
            if (this.b instanceof JSONObject) {
                try {
                    ((JSONObject) this.b).put(str, obj);
                } catch (Exception e) {
                    CBLogging.b(this, "Error updating balances dictionary.", e);
                    com.chartboost.sdk.Tracking.a.a(getClass(), "put(String, JSONObject)", e);
                }
            } else if (this.b instanceof Map) {
                try {
                    ((Map) this.b).put(str, obj);
                } catch (Exception e2) {
                    CBLogging.b(this, "Error updating balances dictionary.", e2);
                    com.chartboost.sdk.Tracking.a.a(getClass(), "put(String, Map)", e2);
                }
            }
        }

        public static a k(String str) {
            if (str == null) {
                CBLogging.d("CBJSON", "null passed when creating new JSON object");
                return a;
            }
            try {
                if (str.trim().startsWith("[")) {
                    return a(com.chartboost.sdk.impl.a.a().b(str));
                }
                return a(com.chartboost.sdk.impl.a.a().a(str));
            } catch (Exception e) {
                CBLogging.b("CBJSON", "error creating new json object", e);
                com.chartboost.sdk.Tracking.a.a(a.class, "fromJSON", e);
                return a;
            }
        }

        private static boolean r() {
            return VERSION.SDK_INT < 19;
        }

        public static JSONObject a(Map map) throws JSONException {
            if (r()) {
                return b(map);
            }
            return new JSONObject(map);
        }

        private static JSONObject b(Map map) throws JSONException {
            JSONObject d = com.chartboost.sdk.impl.a.a().d();
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null) {
                    throw new NullPointerException("key == null");
                }
                d.put(str, b(entry.getValue()));
            }
            return d;
        }

        private static Object b(Object obj) {
            if (obj == null) {
                return JSONObject.NULL;
            }
            if ((obj instanceof JSONArray) || (obj instanceof JSONObject) || obj.equals(JSONObject.NULL)) {
                return obj;
            }
            try {
                if (obj instanceof Collection) {
                    return com.chartboost.sdk.impl.a.a().a((Collection) obj);
                }
                if (obj.getClass().isArray()) {
                    return new JSONArray(obj);
                }
                if (obj instanceof Map) {
                    return new JSONObject((Map) obj);
                }
                if ((obj instanceof Boolean) || (obj instanceof Byte) || (obj instanceof Character) || (obj instanceof Double) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Short) || (obj instanceof String)) {
                    return obj;
                }
                if (obj.getClass().getPackage().getName().startsWith(ButterKnifeProcessor.JAVA_PREFIX)) {
                    return obj.toString();
                }
                return null;
            } catch (Exception e) {
                com.chartboost.sdk.Tracking.a.a(a.class, "wrapJSON", e);
            }
        }

        public String toString() {
            JSONObject e = e();
            if (e != null) {
                return e.toString();
            }
            JSONArray g = g();
            if (g != null) {
                return g.toString();
            }
            if (this.b != null) {
                return this.b.toString();
            }
            return Constants.NULL_VERSION_ID;
        }
    }

    public static class b {
        private final String a;
        private final Object b;

        public b(String str, Object obj) {
            this.a = str;
            if (obj instanceof a) {
                this.b = ((a) obj).o();
            } else {
                this.b = obj;
            }
        }
    }

    public static a a(b... bVarArr) {
        a a = a.a();
        for (b bVar : bVarArr) {
            a.a(bVar.a, bVar.b);
        }
        return a;
    }

    public static b a(String str, Object obj) {
        return new b(str, obj);
    }

    public static List<?> a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        List<?> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                Object obj = jSONArray.get(i);
                if (obj instanceof JSONObject) {
                    obj = a((JSONObject) obj);
                } else if (obj instanceof JSONArray) {
                    obj = a((JSONArray) obj);
                } else if (obj.equals(JSONObject.NULL)) {
                    obj = null;
                }
                arrayList.add(obj);
            } catch (Exception e) {
                CBLogging.b("CBJSON", "error converting json", e);
                com.chartboost.sdk.Tracking.a.a(e.class, "JSONArrayToList", e);
            }
        }
        return arrayList;
    }

    public static Map<String, Object> a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        Map<String, Object> hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            try {
                String str = (String) keys.next();
                Object obj = jSONObject.get(str);
                if (obj instanceof JSONObject) {
                    obj = a((JSONObject) obj);
                } else if (obj instanceof JSONArray) {
                    obj = a((JSONArray) obj);
                } else if (obj.equals(JSONObject.NULL)) {
                    obj = null;
                }
                hashMap.put(str, obj);
            } catch (Exception e) {
                CBLogging.b("CBJSON", "error converting json", e);
                com.chartboost.sdk.Tracking.a.a(e.class, "JSONObjectToMap", e);
            }
        }
        return hashMap;
    }

    public static JSONArray a(List<?> list) {
        if (list == null) {
            return null;
        }
        JSONArray e = com.chartboost.sdk.impl.a.a().e();
        for (int i = 0; i < list.size(); i++) {
            try {
                Object obj = list.get(i);
                if (obj instanceof Map) {
                    obj = a((Map) obj);
                } else if (obj instanceof List) {
                    obj = a((List) obj);
                } else if (obj == null) {
                    obj = JSONObject.NULL;
                }
                e.put(obj);
            } catch (Exception e2) {
                CBLogging.b("CBJSON", "error converting json", e2);
                com.chartboost.sdk.Tracking.a.a(e.class, "listToJSONArray", e2);
            }
        }
        return e;
    }

    public static JSONObject a(Map<?, ?> map) {
        if (map == null) {
            return null;
        }
        JSONObject d = com.chartboost.sdk.impl.a.a().d();
        for (Entry entry : map.entrySet()) {
            String obj = entry.getKey().toString();
            Object value = entry.getValue();
            try {
                if (value instanceof Map) {
                    value = a((Map) value);
                } else if (value instanceof List) {
                    value = a((List) value);
                } else if (value == null) {
                    value = JSONObject.NULL;
                }
                d.put(obj, value);
            } catch (Exception e) {
                CBLogging.b("CBJSON", "error converting json", e);
                com.chartboost.sdk.Tracking.a.a(e.class, "mapToJSONObject", e);
            }
        }
        return d;
    }
}
