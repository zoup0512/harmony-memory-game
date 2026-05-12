package com.appodeal.ads.f;

import android.support.annotation.NonNull;
import com.appodeal.ads.Appodeal;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class f {
    final a a;
    final e[] b;
    private final long c;
    private final JSONArray d;
    private final a e;

    public static class a {
        private final JSONObject a;

        public a(JSONObject jSONObject) {
            JSONObject optJSONObject = jSONObject.optJSONObject("settings");
            if (optJSONObject != null) {
                this.a = optJSONObject;
            } else {
                this.a = new JSONObject();
            }
        }

        public boolean a() {
            return this.a.optBoolean("disable", false);
        }

        public boolean b() {
            return a() || this.a.optBoolean("banners_disabled", false);
        }

        public boolean c() {
            return a() || this.a.optBoolean("interstitials_disabled", false);
        }

        public boolean d() {
            return a() || this.a.optBoolean("rewarded_video_disabled", false);
        }

        public boolean e() {
            return a() || this.a.optBoolean("video_disabled", false);
        }

        public boolean f() {
            return a() || this.a.optBoolean("native_disabled", false);
        }

        public boolean g() {
            return a() || this.a.optBoolean("mrec_disabled", false);
        }

        public double h() {
            return this.a.optDouble("banners_price_floor", -1.0d);
        }

        public double i() {
            return this.a.optDouble("interstitials_price_floor", -1.0d);
        }

        public double j() {
            return this.a.optDouble("rewarded_video_price_floor", -1.0d);
        }

        public double k() {
            return this.a.optDouble("mrec_price_floor", -1.0d);
        }

        public double l() {
            return this.a.optDouble("video_price_floor", -1.0d);
        }

        public double m() {
            return this.a.optDouble("native_price_floor", -1.0d);
        }

        public JSONArray a(int i) {
            JSONArray jSONArray = null;
            switch (i) {
                case 1:
                    jSONArray = this.a.optJSONArray("interstitials_disabled");
                    break;
                case 2:
                    jSONArray = this.a.optJSONArray("video_disabled");
                    break;
                case 4:
                    jSONArray = this.a.optJSONArray("banners_disabled");
                    break;
                case 128:
                    jSONArray = this.a.optJSONArray("rewarded_video_disabled");
                    break;
                case 256:
                    jSONArray = this.a.optJSONArray("mrec_disabled");
                    break;
                case 512:
                    jSONArray = this.a.optJSONArray("native_disabled");
                    break;
            }
            if (jSONArray != null) {
                return jSONArray;
            }
            return this.a.optJSONArray("disabled");
        }

        public boolean b(int i) {
            JSONArray a = a(i);
            if (a == null || a.length() == 0) {
                return false;
            }
            int i2 = 0;
            while (i2 < a.length()) {
                try {
                    if (a.getString(i2).equals("rtb")) {
                        return true;
                    }
                    i2++;
                } catch (Throwable e) {
                    Appodeal.a(e);
                }
            }
            return false;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.util.List<org.json.JSONObject> a(java.util.List<org.json.JSONObject> r9, int r10) {
            /*
            r8 = this;
            r2 = 0;
            r0 = -4616189618054758400; // 0xbff0000000000000 float:0.0 double:-1.0;
            switch(r10) {
                case 1: goto L_0x0037;
                case 2: goto L_0x0046;
                case 4: goto L_0x0065;
                case 128: goto L_0x0056;
                case 256: goto L_0x0074;
                case 512: goto L_0x0083;
                default: goto L_0x0006;
            };
        L_0x0006:
            r4 = r2;
            r2 = r0;
        L_0x0008:
            r5 = r8.a(r4);	 Catch:{ Exception -> 0x0032 }
            r1 = new java.util.ArrayList;	 Catch:{ Exception -> 0x0032 }
            r0 = r9.size();	 Catch:{ Exception -> 0x0032 }
            r1.<init>(r0);	 Catch:{ Exception -> 0x0032 }
            r6 = r9.iterator();	 Catch:{ Exception -> 0x0032 }
        L_0x0019:
            r0 = r6.hasNext();	 Catch:{ Exception -> 0x0032 }
            if (r0 == 0) goto L_0x0093;
        L_0x001f:
            r0 = r6.next();	 Catch:{ Exception -> 0x0032 }
            r0 = (org.json.JSONObject) r0;	 Catch:{ Exception -> 0x0032 }
            r7 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0032 }
            r0 = r0.toString();	 Catch:{ Exception -> 0x0032 }
            r7.<init>(r0);	 Catch:{ Exception -> 0x0032 }
            r1.add(r7);	 Catch:{ Exception -> 0x0032 }
            goto L_0x0019;
        L_0x0032:
            r0 = move-exception;
            com.appodeal.ads.Appodeal.a(r0);
        L_0x0036:
            return r9;
        L_0x0037:
            r0 = r8.a;	 Catch:{ Exception -> 0x0032 }
            r1 = "interstitial_overridden_ecpm";
            r2 = r0.optJSONObject(r1);	 Catch:{ Exception -> 0x0032 }
            r0 = r8.i();	 Catch:{ Exception -> 0x0032 }
            r4 = r2;
            r2 = r0;
            goto L_0x0008;
        L_0x0046:
            r0 = r8.a;	 Catch:{ Exception -> 0x0032 }
            r1 = "video_overridden_ecpm";
            r2 = r0.optJSONObject(r1);	 Catch:{ Exception -> 0x0032 }
            r0 = r8.l();	 Catch:{ Exception -> 0x0032 }
            r4 = r2;
            r2 = r0;
            goto L_0x0008;
        L_0x0056:
            r0 = r8.a;	 Catch:{ Exception -> 0x0032 }
            r1 = "rewarded_video_overridden_ecpm";
            r2 = r0.optJSONObject(r1);	 Catch:{ Exception -> 0x0032 }
            r0 = r8.j();	 Catch:{ Exception -> 0x0032 }
            r4 = r2;
            r2 = r0;
            goto L_0x0008;
        L_0x0065:
            r0 = r8.a;	 Catch:{ Exception -> 0x0032 }
            r1 = "banner_overridden_ecpm";
            r2 = r0.optJSONObject(r1);	 Catch:{ Exception -> 0x0032 }
            r0 = r8.h();	 Catch:{ Exception -> 0x0032 }
            r4 = r2;
            r2 = r0;
            goto L_0x0008;
        L_0x0074:
            r0 = r8.a;	 Catch:{ Exception -> 0x0032 }
            r1 = "mrec_overridden_ecpm";
            r2 = r0.optJSONObject(r1);	 Catch:{ Exception -> 0x0032 }
            r0 = r8.k();	 Catch:{ Exception -> 0x0032 }
            r4 = r2;
            r2 = r0;
            goto L_0x0008;
        L_0x0083:
            r0 = r8.a;	 Catch:{ Exception -> 0x0032 }
            r1 = "native_overridden_ecpm";
            r2 = r0.optJSONObject(r1);	 Catch:{ Exception -> 0x0032 }
            r0 = r8.m();	 Catch:{ Exception -> 0x0032 }
            r4 = r2;
            r2 = r0;
            goto L_0x0008;
        L_0x0093:
            r8.b(r1, r10);	 Catch:{ Exception -> 0x0032 }
            r8.a(r1, r5, r4);	 Catch:{ Exception -> 0x0032 }
            r8.a(r1, r2);	 Catch:{ Exception -> 0x0032 }
            r8.a(r1, r5);	 Catch:{ Exception -> 0x0032 }
            r9 = r1;
            goto L_0x0036;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appodeal.ads.f.f.a.a(java.util.List, int):java.util.List<org.json.JSONObject>");
        }

        private Set<String> a(JSONObject jSONObject) {
            Set<String> hashSet = new HashSet();
            if (jSONObject != null) {
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    hashSet.add(keys.next());
                }
            }
            return hashSet;
        }

        private void b(List<JSONObject> list, int i) {
            try {
                JSONArray a = a(i);
                if (a != null && a.length() != 0) {
                    HashSet hashSet = new HashSet(a.length());
                    for (int i2 = 0; i2 < a.length(); i2++) {
                        hashSet.add(a.getString(i2));
                    }
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        JSONObject jSONObject = (JSONObject) it.next();
                        String optString = jSONObject.optString("status", null);
                        String optString2 = jSONObject.optString("name", null);
                        if (!((optString == null || optString.isEmpty() || !hashSet.contains(optString)) && (optString2 == null || optString2.isEmpty() || !hashSet.contains(optString2)))) {
                            it.remove();
                        }
                    }
                }
            } catch (Throwable e) {
                Appodeal.a(e);
            }
        }

        private void a(List<JSONObject> list, Set<String> set, JSONObject jSONObject) {
            if (!set.isEmpty()) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    JSONObject jSONObject2 = (JSONObject) it.next();
                    String optString = jSONObject2.optString("status", null);
                    String optString2 = jSONObject2.optString("name", null);
                    if (optString2 == null || optString2.isEmpty()) {
                        optString2 = optString;
                    }
                    if (!(optString2 == null || optString2.isEmpty() || !set.contains(optString2))) {
                        if (jSONObject2.has("cap")) {
                            if (jSONObject2.getBoolean("cap")) {
                                it.remove();
                            } else {
                                jSONObject2.put("ecpm", jSONObject.getDouble(optString2));
                            }
                        } else if (!a((List) list, jSONObject2)) {
                            jSONObject2.put("ecpm", jSONObject.getDouble(optString2));
                        }
                    }
                }
            }
        }

        private boolean a(List<JSONObject> list, JSONObject jSONObject) {
            String optString = jSONObject.optString("id");
            Object optString2 = jSONObject.optString("status");
            String optString3 = jSONObject.optString("name", null);
            if (!(optString3 == null || optString3.isEmpty())) {
                optString2 = optString3;
            }
            for (JSONObject jSONObject2 : list) {
                String optString4 = jSONObject2.optString("id");
                if (optString4 == null || !optString4.equals(optString)) {
                    optString4 = jSONObject2.optString("status");
                    optString3 = jSONObject2.optString("name", null);
                    if (optString3 == null || optString3.isEmpty()) {
                        optString3 = optString4;
                    }
                    if (optString3 != null && optString3.equals(r1)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public void a(List<JSONObject> list, double d) {
            try {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (((JSONObject) it.next()).optDouble("ecpm", 0.0d) < d) {
                        it.remove();
                    }
                }
            } catch (Throwable e) {
                Appodeal.a(e);
            }
        }

        private void a(List<JSONObject> list, final Set<String> set) {
            if (!set.isEmpty()) {
                Collections.sort(list, new Comparator<JSONObject>(this) {
                    final /* synthetic */ a b;

                    public /* synthetic */ int compare(Object obj, Object obj2) {
                        return a((JSONObject) obj, (JSONObject) obj2);
                    }

                    public int a(JSONObject jSONObject, JSONObject jSONObject2) {
                        if (!set.contains(jSONObject.optString("status")) && !set.contains(jSONObject2.optString("status"))) {
                            return 0;
                        }
                        double optDouble = jSONObject2.optDouble("ecpm") - jSONObject.optDouble("ecpm");
                        if (optDouble == 0.0d) {
                            return 0;
                        }
                        if (optDouble < 0.0d) {
                            return -1;
                        }
                        return 1;
                    }
                });
            }
        }
    }

    f(@NonNull JSONObject jSONObject) {
        this.c = (long) jSONObject.optInt("id", -1);
        this.a = a.a(jSONObject.optString("match_rule", ""));
        JSONArray optJSONArray = jSONObject.optJSONArray("rules");
        if (optJSONArray != null) {
            this.b = new e[optJSONArray.length()];
            a(optJSONArray);
        } else {
            this.b = null;
        }
        this.d = jSONObject.optJSONArray("placements");
        this.e = new a(jSONObject);
    }

    private void a(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                this.b[i] = new e(jSONArray.optJSONObject(i));
            } catch (Throwable e) {
                Appodeal.a(e);
            }
        }
    }

    public void a() {
        if (this.d != null) {
            for (int i = 0; i < this.d.length(); i++) {
                JSONObject jSONObject = this.d.getJSONObject(i);
                int i2 = jSONObject.getInt("id");
                String string = jSONObject.getString("name");
                d.a.put(string, new c(i2, string, jSONObject.getJSONObject("settings")));
            }
        }
    }

    public a b() {
        return this.e;
    }

    public long c() {
        return this.c;
    }
}
