package com.appodeal.ads.utils.a;

import android.content.Context;
import android.util.Pair;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.AppodealSettings;
import com.appodeal.ads.utils.c;
import com.appodeal.ads.utils.g;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONObject;

public class b {
    public static final HashMap<String, HashMap<String, Integer>> a = new HashMap();
    public String b;
    public String c;
    public a d;
    public int e;
    public int f;
    public int g;
    public int h;
    public boolean i;
    private c j;

    public enum a {
        CAMPAIGN,
        IMAGE
    }

    public b(Context context, JSONObject jSONObject) {
        try {
            this.b = String.valueOf(jSONObject.getInt("campaign_id"));
            this.c = String.valueOf(jSONObject.getInt("image_id"));
            if (jSONObject.getString("cap_type").equals("image")) {
                this.d = a.IMAGE;
            } else {
                this.d = a.CAMPAIGN;
            }
            this.e = jSONObject.getInt("impressions");
            this.f = jSONObject.getInt("period");
            this.g = jSONObject.optInt(SettingsJsonConstants.SESSION_KEY, -1);
            this.h = jSONObject.optInt("interval", 0);
            this.i = jSONObject.optBoolean("per_app", false);
            if (c.a(context) && g.d() && !this.i) {
                this.j = new d(this.b);
            } else {
                this.j = new a(this.b);
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public boolean a(Context context) {
        try {
            int i;
            int i2;
            int i3;
            ArrayList arrayList = new ArrayList();
            JSONObject a = this.j.a(context);
            int i4;
            switch (this.d) {
                case CAMPAIGN:
                    if (a != null) {
                        Iterator keys = a.keys();
                        i = 0;
                        while (keys.hasNext()) {
                            JSONArray jSONArray = a.getJSONArray((String) keys.next());
                            i2 = i;
                            i = 0;
                            while (i < jSONArray.length()) {
                                i3 = jSONArray.getInt(i);
                                arrayList.add(Integer.valueOf(i3));
                                if (i3 <= i2) {
                                    i3 = i2;
                                }
                                i++;
                                i2 = i3;
                            }
                            i = i2;
                        }
                    } else {
                        i = 0;
                    }
                    if (!a.containsKey(this.b)) {
                        i4 = i;
                        i = 0;
                        break;
                    }
                    i2 = 0;
                    for (Integer intValue : ((HashMap) a.get(this.b)).values()) {
                        i2 += intValue.intValue();
                    }
                    i4 = i;
                    i = i2;
                    break;
                case IMAGE:
                    if (a == null || !a.has(this.c)) {
                        i2 = 0;
                    } else {
                        JSONArray jSONArray2 = a.getJSONArray(this.c);
                        i = 0;
                        i2 = 0;
                        while (i < jSONArray2.length()) {
                            i3 = jSONArray2.getInt(i);
                            arrayList.add(Integer.valueOf(i3));
                            if (i3 <= i2) {
                                i3 = i2;
                            }
                            i++;
                            i2 = i3;
                        }
                    }
                    if (a.containsKey(this.b)) {
                        HashMap hashMap = (HashMap) a.get(this.b);
                        if (hashMap.containsKey(this.c)) {
                            i = ((Integer) hashMap.get(this.c)).intValue();
                            i4 = i2;
                            break;
                        }
                    }
                    i = 0;
                    i4 = i2;
                    break;
                default:
                    i = 0;
                    i4 = 0;
                    break;
            }
            long currentTimeMillis = ((System.currentTimeMillis() / 1000) / 60) - ((long) this.f);
            Iterator it = arrayList.iterator();
            i2 = 0;
            while (it.hasNext()) {
                if (((long) ((Integer) it.next()).intValue()) >= currentTimeMillis) {
                    i3 = i2 + 1;
                } else {
                    i3 = i2;
                }
                i2 = i3;
            }
            boolean z = i2 < this.e;
            if (this.g > 0) {
                z = z && i < this.g;
            }
            long currentTimeMillis2 = ((System.currentTimeMillis() / 1000) / 60) - ((long) this.h);
            if (this.h <= 0) {
                return z;
            }
            if (!z || ((long) r5) >= currentTimeMillis2) {
                return false;
            }
            return true;
        } catch (Throwable e) {
            Appodeal.a(e);
            return true;
        }
    }

    public void b(Context context) {
        try {
            JSONObject jSONObject;
            HashMap hashMap;
            int intValue;
            JSONObject a = this.j.a(context);
            if (a == null) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = a;
            }
            try {
                JSONArray jSONArray;
                if (jSONObject.has(this.c)) {
                    jSONArray = jSONObject.getJSONArray(this.c);
                } else {
                    jSONArray = new JSONArray();
                }
                jSONArray.put((System.currentTimeMillis() / 1000) / 60);
                jSONObject.put(this.c, jSONArray);
            } catch (Throwable e) {
                Appodeal.a(e);
            }
            this.j.a(context, jSONObject);
            if (a.containsKey(this.b)) {
                hashMap = (HashMap) a.get(this.b);
            } else {
                HashMap hashMap2 = new HashMap();
                a.put(this.b, hashMap2);
                hashMap = hashMap2;
            }
            if (hashMap.containsKey(this.c)) {
                intValue = ((Integer) hashMap.get(this.c)).intValue();
            } else {
                intValue = 0;
            }
            hashMap.put(this.c, Integer.valueOf(intValue + 1));
        } catch (Throwable e2) {
            Appodeal.a(e2);
        }
    }

    public static JSONObject a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return new JSONObject();
        }
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            try {
                String str = (String) keys.next();
                JSONObject jSONObject2 = new JSONObject(jSONObject.getString(str));
                Iterator keys2 = jSONObject2.keys();
                while (keys2.hasNext()) {
                    String str2 = (String) keys2.next();
                    JSONArray jSONArray = jSONObject2.getJSONArray(str2);
                    List arrayList = new ArrayList();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.add(Integer.valueOf(jSONArray.getInt(i)));
                    }
                    Collections.sort(arrayList);
                    Collection subList = arrayList.subList(Math.max(arrayList.size() - 20, 0), arrayList.size());
                    long currentTimeMillis = ((System.currentTimeMillis() / 1000) / 60) - 43200;
                    Iterator it = subList.iterator();
                    while (it.hasNext()) {
                        if (((long) ((Integer) it.next()).intValue()) < currentTimeMillis) {
                            it.remove();
                        }
                    }
                    if (subList.size() > 0) {
                        jSONObject2.put(str2, new JSONArray(subList));
                    } else {
                        keys2.remove();
                    }
                }
                if (jSONObject2.length() > 0) {
                    jSONObject.put(str, jSONObject2);
                } else {
                    keys.remove();
                }
            } catch (Throwable e) {
                Appodeal.a(e);
            }
        }
        return jSONObject;
    }

    public static void c(Context context) {
        try {
            a.b(context, a(a.b(context)));
        } catch (Throwable e) {
            Appodeal.a(e);
        }
        try {
            if (c.a(context) && g.d()) {
                d.a(a(d.a()));
            }
        } catch (Throwable e2) {
            Appodeal.a(e2);
        }
    }

    public static void a(ArrayList<JSONObject> arrayList) {
        try {
            if (AppodealSettings.l) {
                int i;
                HashMap hashMap = new HashMap();
                for (i = 0; i < arrayList.size(); i++) {
                    JSONObject jSONObject = (JSONObject) arrayList.get(i);
                    if (jSONObject.has("freq")) {
                        ArrayList arrayList2;
                        double d = jSONObject.getDouble("ecpm");
                        if (hashMap.containsKey(Double.valueOf(d))) {
                            arrayList2 = (ArrayList) hashMap.get(Double.valueOf(d));
                        } else {
                            arrayList2 = new ArrayList();
                        }
                        arrayList2.add(new Pair(Integer.valueOf(i), Double.valueOf(jSONObject.getJSONObject("freq").optDouble("weight", 1.0d))));
                        hashMap.put(Double.valueOf(d), arrayList2);
                    }
                }
                for (ArrayList arrayList3 : hashMap.values()) {
                    if (arrayList3.size() != 1) {
                        double d2 = 0.0d;
                        Iterator it = arrayList3.iterator();
                        while (it.hasNext()) {
                            d2 += ((Double) ((Pair) it.next()).second).doubleValue();
                        }
                        Collection arrayList4 = new ArrayList();
                        Iterator it2 = arrayList3.iterator();
                        while (it2.hasNext()) {
                            Pair pair = (Pair) it2.next();
                            arrayList4.addAll(Collections.nCopies((int) Math.round((((Double) pair.second).doubleValue() / d2) * 100.0d), pair.first));
                        }
                        Collections.shuffle(arrayList4);
                        ArrayList arrayList5 = new ArrayList(new LinkedHashSet(arrayList4));
                        HashMap hashMap2 = new HashMap();
                        for (i = 0; i < arrayList3.size(); i++) {
                            hashMap2.put(arrayList5.get(i), arrayList.get(((Integer) ((Pair) arrayList3.get(i)).first).intValue()));
                        }
                        for (Entry entry : hashMap2.entrySet()) {
                            arrayList.set(((Integer) entry.getKey()).intValue(), entry.getValue());
                        }
                    }
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }
}
