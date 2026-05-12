package com.yandex.metrica.impl;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import android.util.SparseArray;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferTable;
import com.yandex.metrica.c.a.h;
import com.yandex.metrica.c.a.i;
import com.yandex.metrica.impl.ob.ay;
import com.yandex.metrica.impl.ob.cx;
import com.yandex.metrica.impl.utils.j;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class ak {
    private static Map<ay, Integer> a;
    private static SparseArray<ay> b;

    static class a {
        private static final Map<com.yandex.metrica.impl.p.a, Class<?>> o;
        private static final Map<com.yandex.metrica.impl.p.a, Integer> p;
        protected String a;
        protected String b;
        protected int c;
        protected long d;
        protected String e;
        protected String f;
        protected String g;
        protected Integer h;
        protected Integer i;
        protected String j;
        protected String k;
        protected int l;
        protected int m;
        protected String n;

        static {
            Map hashMap = new HashMap();
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_REGULAR, d.class);
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_REFERRER_DEPRECATED, e.class);
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_ACTIVITY_START_DEPRECATED, a.class);
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_ALIVE, a.class);
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_EXCEPTION_UNHANDLED_DEPRECATED, e.class);
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_NATIVE_CRASH, g.class);
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_EXCEPTION_USER, d.class);
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_IDENTITY, f.class);
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_STATBOX, d.class);
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_SET_USER_INFO, d.class);
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_REPORT_USER_INFO, d.class);
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_EXCEPTION_UNHANDLED, d.class);
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_START, a.class);
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_CUSTOM_EVENT, b.class);
            o = Collections.unmodifiableMap(hashMap);
            hashMap = new HashMap();
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_INIT, Integer.valueOf(1));
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_REGULAR, Integer.valueOf(4));
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_REFERRER_DEPRECATED, Integer.valueOf(5));
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_ACTIVITY_START_DEPRECATED, Integer.valueOf(2));
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_ALIVE, Integer.valueOf(7));
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_EXCEPTION_UNHANDLED_DEPRECATED, Integer.valueOf(3));
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_EXCEPTION_UNHANDLED, Integer.valueOf(3));
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_NATIVE_CRASH, Integer.valueOf(3));
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_EXCEPTION_USER, Integer.valueOf(6));
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_IDENTITY, Integer.valueOf(8));
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_STATBOX, Integer.valueOf(11));
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_SET_USER_INFO, Integer.valueOf(12));
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_REPORT_USER_INFO, Integer.valueOf(12));
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_FIRST_ACTIVATION, Integer.valueOf(13));
            hashMap.put(com.yandex.metrica.impl.p.a.EVENT_TYPE_START, Integer.valueOf(2));
            p = Collections.unmodifiableMap(hashMap);
        }

        static a a(int i, boolean z) {
            Class cls;
            a aVar;
            com.yandex.metrica.impl.p.a a = com.yandex.metrica.impl.p.a.a(i);
            switch (a) {
                case EVENT_TYPE_INIT:
                case EVENT_TYPE_FIRST_ACTIVATION:
                    if (!z) {
                        cls = c.class;
                        break;
                    }
                    cls = d.class;
                    break;
                default:
                    cls = (Class) o.get(a);
                    break;
            }
            Integer num = (Integer) p.get(a);
            try {
                aVar = (a) cls.newInstance();
            } catch (Exception e) {
                aVar = new a();
            }
            return aVar.a(num);
        }

        a a(String str) {
            this.a = str;
            return this;
        }

        a b(String str) {
            this.b = str;
            return this;
        }

        a a(int i) {
            this.c = i;
            return this;
        }

        a a(long j) {
            this.d = j;
            return this;
        }

        a c(String str) {
            this.e = str;
            return this;
        }

        a d(String str) {
            this.g = str;
            return this;
        }

        a e(String str) {
            this.f = str;
            return this;
        }

        a a(Integer num) {
            this.h = num;
            return this;
        }

        a b(Integer num) {
            this.i = num;
            return this;
        }

        a f(String str) {
            this.j = str;
            return this;
        }

        a g(String str) {
            this.k = str;
            return this;
        }

        a b(int i) {
            this.l = i;
            return this;
        }

        a c(int i) {
            this.m = i;
            return this;
        }

        a h(String str) {
            this.n = str;
            return this;
        }

        protected String a() {
            return "";
        }

        protected byte[] b() {
            return new byte[0];
        }

        protected Integer c() {
            return this.h;
        }

        protected String d() {
            return this.j;
        }

        com.yandex.metrica.c.a.g.a e() {
            com.yandex.metrica.c.a.g.a aVar = new com.yandex.metrica.c.a.g.a();
            com.yandex.metrica.c.a.e a = ak.a(this.m, this.n, this.g, this.f);
            com.yandex.metrica.c.a.d c = ak.c(this.e);
            com.yandex.metrica.c.a.a d = ak.d(this.k);
            if (a != null) {
                aVar.h = a;
            }
            if (c != null) {
                aVar.g = c;
            }
            if (a() != null) {
                aVar.e = a();
            }
            if (b() != null) {
                aVar.f = b();
            }
            if (d() != null) {
                aVar.i = d();
            }
            if (d != null) {
                aVar.j = d;
            }
            aVar.d = c().intValue();
            aVar.b = (long) this.c;
            aVar.c = this.d;
            aVar.k = this.l;
            aVar.l = f();
            return aVar;
        }

        protected int f() {
            return 0;
        }
    }

    static class d extends a {
        d() {
        }

        protected String a() {
            return this.a;
        }

        protected byte[] b() {
            if (this.b != null) {
                return be.c(this.b);
            }
            return super.b();
        }
    }

    static class b extends d {
        b() {
        }

        protected Integer c() {
            return this.i;
        }
    }

    static class c extends a {
        c() {
        }

        protected String a() {
            return this.a;
        }
    }

    static class e extends a {
        e() {
        }

        protected byte[] b() {
            return be.c(this.a);
        }
    }

    static class f extends a {
        f() {
        }

        protected byte[] b() {
            return Base64.decode(this.b, 0);
        }

        public int f() {
            return 1;
        }
    }

    static class g extends a {
        g() {
        }

        protected byte[] b() {
            return be.c(r.c(this.b));
        }
    }

    static {
        Map hashMap = new HashMap();
        hashMap.put(ay.FOREGROUND, Integer.valueOf(0));
        hashMap.put(ay.BACKGROUND, Integer.valueOf(1));
        a = Collections.unmodifiableMap(hashMap);
        SparseArray sparseArray = new SparseArray();
        sparseArray.put(0, ay.FOREGROUND);
        sparseArray.put(1, ay.BACKGROUND);
        b = sparseArray;
    }

    public static com.yandex.metrica.c.b a(ContentValues contentValues) {
        return a(contentValues.getAsLong("start_time"), contentValues.getAsLong("server_time_offset"));
    }

    public static h a(cx cxVar) {
        h hVar = new h();
        if (cxVar.a() != null) {
            hVar.b = cxVar.a().intValue();
        }
        if (cxVar.b() != null) {
            hVar.c = cxVar.b().intValue();
        }
        if (!TextUtils.isEmpty(cxVar.d())) {
            hVar.d = cxVar.d();
        }
        hVar.e = cxVar.c();
        if (!TextUtils.isEmpty(cxVar.e())) {
            hVar.f = cxVar.e();
        }
        return hVar;
    }

    public static ay a(int i) {
        return (ay) b.get(i);
    }

    public static List<i> a(String str) {
        try {
            List<i> arrayList = new ArrayList();
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    arrayList.add(a(jSONArray.getJSONObject(i)));
                } catch (Exception e) {
                }
            }
            return arrayList;
        } catch (Exception e2) {
            return new ArrayList();
        }
    }

    public static i a(JSONObject jSONObject) throws JSONException {
        i iVar;
        try {
            iVar = new i();
            iVar.b = jSONObject.getString("mac");
            iVar.c = jSONObject.getInt("signal_strength");
            iVar.d = jSONObject.getString("ssid");
            iVar.e = jSONObject.optBoolean("is_connected");
            return iVar;
        } catch (Exception e) {
            iVar = new i();
            iVar.b = jSONObject.getString("mac");
            return iVar;
        }
    }

    public static com.yandex.metrica.c.b a(Long l, Long l2) {
        long longValue = l.longValue();
        com.yandex.metrica.c.b bVar = new com.yandex.metrica.c.b();
        bVar.b = longValue;
        bVar.c = ((GregorianCalendar) GregorianCalendar.getInstance()).getTimeZone().getOffset(longValue * 1000) / 1000;
        if (l2 != null) {
            bVar.d = l2.longValue();
        }
        return bVar;
    }

    public static com.yandex.metrica.c.a.g.b a(String str, int i, com.yandex.metrica.c.b bVar) {
        com.yandex.metrica.c.a.g.b bVar2 = new com.yandex.metrica.c.a.g.b();
        bVar2.b = bVar;
        bVar2.c = str;
        bVar2.d = i;
        return bVar2;
    }

    static int a(ay ayVar) {
        return ((Integer) a.get(ayVar)).intValue();
    }

    public static com.yandex.metrica.c.a.b[] b(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONArray jSONArray = new JSONArray(str);
                com.yandex.metrica.c.a.b[] bVarArr = new com.yandex.metrica.c.a.b[jSONArray.length()];
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (jSONObject != null) {
                        bVarArr[i] = b(jSONObject);
                    }
                }
                return bVarArr;
            }
            return null;
        } catch (JSONException e) {
            try {
                return new com.yandex.metrica.c.a.b[]{b(new JSONObject(str))};
            } catch (Exception e2) {
            }
        }
    }

    static com.yandex.metrica.c.a.b b(JSONObject jSONObject) {
        com.yandex.metrica.c.a.b bVar = new com.yandex.metrica.c.a.b();
        if (jSONObject.has("signal_strength")) {
            int optInt = jSONObject.optInt("signal_strength");
            if (optInt != -1) {
                bVar.c = optInt;
            }
        }
        if (jSONObject.has("cell_id")) {
            bVar.b = jSONObject.optInt("cell_id");
        }
        if (jSONObject.has("lac")) {
            bVar.d = jSONObject.optInt("lac");
        }
        if (jSONObject.has("country_code")) {
            bVar.e = jSONObject.optInt("country_code");
        }
        if (jSONObject.has("operator_id")) {
            bVar.f = jSONObject.optInt("operator_id");
        }
        if (jSONObject.has("operator_name")) {
            bVar.g = jSONObject.optString("operator_name");
        }
        if (jSONObject.has("is_connected")) {
            bVar.h = jSONObject.optBoolean("is_connected");
        }
        bVar.i = jSONObject.optInt("cell_type", 0);
        if (jSONObject.has("pci")) {
            bVar.j = jSONObject.optInt("pci");
        }
        return bVar;
    }

    public static com.yandex.metrica.c.a.d c(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                a aVar = new a(str);
                com.yandex.metrica.c.a.d dVar = new com.yandex.metrica.c.a.d();
                dVar.c = aVar.getDouble("lon");
                dVar.b = aVar.getDouble("lat");
                if (aVar.b("altitude")) {
                    dVar.h = aVar.getInt("altitude");
                }
                if (aVar.b("direction")) {
                    dVar.f = aVar.getInt("direction");
                }
                if (aVar.b("precision")) {
                    dVar.e = aVar.getInt("precision");
                }
                if (aVar.b(TransferTable.COLUMN_SPEED)) {
                    dVar.g = aVar.getInt(TransferTable.COLUMN_SPEED);
                }
                if (aVar.b("timestamp")) {
                    dVar.d = aVar.getLong("timestamp") / 1000;
                }
                if (!aVar.b("provider")) {
                    return dVar;
                }
                String a = aVar.a("provider");
                if ("gps".equals(a)) {
                    dVar.i = 1;
                    return dVar;
                } else if (!"network".equals(a)) {
                    return dVar;
                } else {
                    dVar.i = 2;
                    return dVar;
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static com.yandex.metrica.c.a.e a(int i, String str, String str2, String str3) {
        com.yandex.metrica.c.a.e eVar = new com.yandex.metrica.c.a.e();
        eVar.d = i;
        if (str != null) {
            eVar.e = str;
        }
        com.yandex.metrica.c.a.b[] b = b(str3);
        List a = a(str2);
        if (b != null) {
            eVar.b = b;
        }
        if (a != null) {
            eVar.c = (i[]) a.toArray(new i[a.size()]);
        }
        return eVar;
    }

    public static com.yandex.metrica.c.a.a d(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                com.yandex.metrica.d a = j.a(str);
                com.yandex.metrica.c.a.a aVar = new com.yandex.metrica.c.a.a();
                aVar.b = a.a();
                if (!TextUtils.isEmpty(a.b())) {
                    aVar.c = a.b();
                }
                if (bg.a(a.c())) {
                    return aVar;
                }
                aVar.d = bg.b(a.c());
                return aVar;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void a(com.yandex.metrica.c.a.g gVar) {
        com.yandex.metrica.c.a.g.a[] aVarArr = gVar.d;
        StringBuilder stringBuilder = new StringBuilder("Session events' Ids: ");
        if (aVarArr != null) {
            for (com.yandex.metrica.c.a.g.a aVar : aVarArr) {
                stringBuilder.append(aVar.b + " ");
            }
        }
    }

    public static com.yandex.metrica.c.a.f[] a(Context context) {
        Collection b = bi.a(context).b();
        if (bg.a(b)) {
            return null;
        }
        com.yandex.metrica.c.a.f[] fVarArr = new com.yandex.metrica.c.a.f[b.size()];
        for (int i = 0; i < b.size(); i++) {
            com.yandex.metrica.c.a.f fVar = new com.yandex.metrica.c.a.f();
            com.yandex.metrica.impl.bi.a aVar = (com.yandex.metrica.impl.bi.a) b.get(i);
            fVar.b = aVar.a;
            fVar.c = aVar.b;
            fVarArr[i] = fVar;
        }
        return fVarArr;
    }
}
