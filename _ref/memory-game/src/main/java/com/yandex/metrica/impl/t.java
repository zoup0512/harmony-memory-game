package com.yandex.metrica.impl;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.os.SystemClock;
import android.util.Base64;
import com.yandex.metrica.impl.utils.d;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class t {
    JSONObject a;

    public t(String str) {
        try {
            this.a = new JSONObject(str);
        } catch (Exception e) {
            this.a = new JSONObject();
        }
    }

    public t a() {
        try {
            c();
            b();
        } catch (Exception e) {
        }
        return this;
    }

    t b() throws JSONException {
        ((JSONObject) a(this.a, "dfid", new JSONObject())).put("boot_time", (System.currentTimeMillis() - SystemClock.elapsedRealtime()) / 1000);
        return this;
    }

    t a(Context context) throws JSONException, UnsupportedEncodingException {
        JSONObject jSONObject = (JSONObject) a((JSONObject) a(this.a, "dfid", new JSONObject()), "au", new JSONObject());
        JSONArray jSONArray = (JSONArray) a(jSONObject, "aun", new JSONArray());
        JSONArray jSONArray2 = (JSONArray) a(jSONObject, "ausf", new JSONArray());
        JSONArray jSONArray3 = (JSONArray) a(jSONObject, "audf", new JSONArray());
        JSONArray jSONArray4 = (JSONArray) a(jSONObject, "aulu", new JSONArray());
        jSONObject.put("auv", 0);
        HashSet hashSet = new HashSet();
        for (ResolveInfo resolveInfo : bg.a(context, new String(Base64.decode("YW5kcm9pZC5pbnRlbnQuYWN0aW9uLk1BSU4=", 0), "UTF-8"), new String(Base64.decode("YW5kcm9pZC5pbnRlbnQuY2F0ZWdvcnkuTEFVTkNIRVI=", 0), "UTF-8"))) {
            ApplicationInfo applicationInfo = resolveInfo.activityInfo.applicationInfo;
            if (hashSet.add(applicationInfo.packageName)) {
                boolean z;
                jSONArray.put(applicationInfo.packageName);
                if ((applicationInfo.flags & 1) == 1) {
                    z = true;
                } else {
                    z = false;
                }
                jSONArray2.put(z);
                jSONArray4.put(new File(applicationInfo.sourceDir).lastModified());
                if (applicationInfo.enabled) {
                    z = false;
                } else {
                    z = true;
                }
                jSONArray3.put(z);
            }
        }
        return this;
    }

    t b(Context context) throws JSONException {
        JSONObject jSONObject = (JSONObject) a((JSONObject) a(this.a, "dfid", new JSONObject()), "apps", new JSONObject());
        JSONArray jSONArray = (JSONArray) a(jSONObject, "names", new JSONArray());
        JSONArray jSONArray2 = (JSONArray) a(jSONObject, "system_flags", new JSONArray());
        JSONArray jSONArray3 = (JSONArray) a(jSONObject, "disabled_flags", new JSONArray());
        JSONArray jSONArray4 = (JSONArray) a(jSONObject, "first_install_time", new JSONArray());
        JSONArray jSONArray5 = (JSONArray) a(jSONObject, "last_update_time", new JSONArray());
        jSONObject.put("version", 0);
        for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(0)) {
            boolean z;
            jSONArray.put(packageInfo.packageName);
            jSONArray2.put((packageInfo.applicationInfo.flags & 1) == 1);
            if (packageInfo.applicationInfo.enabled) {
                z = false;
            } else {
                z = true;
            }
            jSONArray3.put(z);
            jSONArray4.put(packageInfo.firstInstallTime / 1000);
            jSONArray5.put(packageInfo.lastUpdateTime / 1000);
        }
        return this;
    }

    t c() throws JSONException {
        JSONObject jSONObject = (JSONObject) a(this.a, "dfid", new JSONObject());
        long a = bg.a(true);
        long a2 = bg.a(false);
        long b = bg.b(true);
        long b2 = bg.b(false);
        jSONObject.put("tds", a + a2);
        jSONObject.put("fds", b + b2);
        return this;
    }

    static <T> T a(JSONObject jSONObject, String str, T t) throws JSONException {
        if (!jSONObject.has(str)) {
            jSONObject.put(str, t);
        }
        return jSONObject.get(str);
    }

    public String toString() {
        return this.a.toString();
    }

    public String d() {
        return Base64.encodeToString(new d().a(be.c(this.a.toString())), 0);
    }
}
