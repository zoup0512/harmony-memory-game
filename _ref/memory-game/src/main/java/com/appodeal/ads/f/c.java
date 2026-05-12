package com.appodeal.ads.f;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;
import com.appodeal.ads.e;
import com.facebook.internal.AnalyticsEvents;
import com.mopub.common.AdType;
import java.util.Date;
import java.util.Map.Entry;
import org.json.JSONObject;

public class c {
    private final int a;
    private final String b;
    private final JSONObject c;
    private final String d = "Appodeal_placement_capping";
    private long e;
    private long f;
    private long g;
    private long h;

    @VisibleForTesting
    public c(int i, @NonNull String str, @NonNull JSONObject jSONObject) {
        this.a = i;
        this.b = str;
        this.c = jSONObject;
    }

    private boolean y() {
        return this.c.optBoolean("disable", false);
    }

    public int a() {
        return this.a;
    }

    public boolean b() {
        return y() || this.c.optBoolean("banners_disabled", false);
    }

    public int c() {
        return this.c.optInt("banners_impression_period", -1) * 1000;
    }

    public double d() {
        return this.c.optDouble("banners_price_floor", -1.0d);
    }

    public boolean e() {
        return y() || this.c.optBoolean("interstitials_disabled", false);
    }

    public int f() {
        return this.c.optInt("interstitials_impression_period", -1) * 1000;
    }

    public int g() {
        return this.c.optInt("interstitials_impression_cap", 0);
    }

    public double h() {
        return this.c.optDouble("interstitials_price_floor", -1.0d);
    }

    public boolean i() {
        return y() || this.c.optBoolean("rewarded_video_disabled", false);
    }

    public int j() {
        return this.c.optInt("rewarded_video_impression_period", -1) * 1000;
    }

    public int k() {
        return this.c.optInt("rewarded_video_impression_cap", 0);
    }

    @Nullable
    public String l() {
        return this.c.optString("reward_currency", null);
    }

    public int m() {
        return this.c.optInt("reward_amount", 0);
    }

    public double n() {
        return this.c.optDouble("rewarded_video_price_floor", -1.0d);
    }

    public boolean o() {
        return y() || this.c.optBoolean("video_disabled", false);
    }

    public int p() {
        return this.c.optInt("video_impression_period", -1) * 1000;
    }

    public int q() {
        return this.c.optInt("video_impression_cap", 0);
    }

    public double r() {
        return this.c.optDouble("video_price_floor", -1.0d);
    }

    public boolean s() {
        return y() || this.c.optBoolean("mrec_disabled", false);
    }

    public int t() {
        return this.c.optInt("mrec_impression_period", -1) * 1000;
    }

    public double u() {
        return this.c.optDouble("mrec_price_floor", -1.0d);
    }

    public int v() {
        return this.c.optInt("ad_impression_cap", 0);
    }

    public int w() {
        return this.c.optInt("ad_impression_period", -1) * 1000;
    }

    public String x() {
        return this.b;
    }

    public String toString() {
        return this.c.toString();
    }

    public boolean a(int i, e eVar) {
        if (!a(i)) {
            Appodeal.a(String.format("Placement '%s': %s disabled", new Object[]{x(), an.a(i)}));
            return false;
        } else if (!c(i)) {
            Appodeal.a(String.format("Placement '%s': %s impression cap reached", new Object[]{x(), an.a(i)}));
            return false;
        } else if (!e(i)) {
            Appodeal.a(String.format("Placement '%s': %s impression period hasn't passed yet", new Object[]{x(), an.a(i)}));
            return false;
        } else if (eVar == null || a(i, eVar.b)) {
            return true;
        } else {
            Appodeal.a(String.format("Placement '%s': %s impression eCPM $%s lower than price floor", new Object[]{x(), an.a(i), Double.valueOf(eVar.b)}));
            return false;
        }
    }

    public boolean a(int i) {
        switch (i) {
            case 1:
                if (e()) {
                    return false;
                }
                return true;
            case 2:
                if (o()) {
                    return false;
                }
                return true;
            case 4:
                if (b()) {
                    return false;
                }
                return true;
            case 128:
                if (i()) {
                    return false;
                }
                return true;
            case 256:
                return !s();
            default:
                return true;
        }
    }

    public void b(int i) {
        f(i);
        d(i);
    }

    public boolean c(int i) {
        if (g(i) && v() > 0 && !a("all", v())) {
            return false;
        }
        switch (i) {
            case 1:
                if (g() == 0 || a("banner", g())) {
                    return true;
                }
                return false;
            case 2:
                if (q() == 0 || a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO, q())) {
                    return true;
                }
                return false;
            case 128:
                if (k() == 0 || a(AdType.REWARDED_VIDEO, k())) {
                    return true;
                }
                return false;
            default:
                return true;
        }
    }

    public void d(int i) {
        if (g(i) && v() > 0) {
            b("all", v());
        }
        switch (i) {
            case 1:
                if (g() > 0) {
                    b("banner", g());
                    return;
                }
                return;
            case 2:
                if (q() > 0) {
                    b(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO, q());
                    return;
                }
                return;
            case 128:
                if (k() > 0) {
                    b(AdType.REWARDED_VIDEO, k());
                    return;
                }
                return;
            default:
                return;
        }
    }

    public boolean a(String str, int i) {
        SharedPreferences sharedPreferences = Appodeal.b.getSharedPreferences("Appodeal_placement_capping", 0);
        String f = an.f();
        if (!sharedPreferences.contains(f)) {
            return true;
        }
        try {
            JSONObject jSONObject = new JSONObject(sharedPreferences.getString(f, null));
            if (!jSONObject.has(x())) {
                return true;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject(x());
            if (!jSONObject2.has(str)) {
                return true;
            }
            JSONObject jSONObject3 = jSONObject2.getJSONObject(str);
            if (i != jSONObject3.getInt("initial_value")) {
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("initial_value", i);
                jSONObject2.put(str, jSONObject4);
                sharedPreferences.edit().putString(f, jSONObject.toString()).apply();
                return true;
            } else if (jSONObject3.optInt("current_value", i) <= 0) {
                return false;
            } else {
                return true;
            }
        } catch (Throwable e) {
            Appodeal.a(e);
            return true;
        }
    }

    public void b(String str, int i) {
        SharedPreferences sharedPreferences = Appodeal.b.getSharedPreferences("Appodeal_placement_capping", 0);
        String f = an.f();
        try {
            String string = sharedPreferences.getString(f, null);
            JSONObject jSONObject;
            JSONObject jSONObject2;
            JSONObject jSONObject3;
            if (string != null) {
                jSONObject = new JSONObject(string);
                if (jSONObject.has(x())) {
                    jSONObject2 = jSONObject.getJSONObject(x());
                    if (jSONObject2.has(str)) {
                        jSONObject2 = jSONObject2.getJSONObject(str);
                        jSONObject2.put("current_value", jSONObject2.optInt("current_value", jSONObject2.getInt("initial_value")) - 1);
                    } else {
                        jSONObject3 = new JSONObject();
                        jSONObject3.put("initial_value", i);
                        jSONObject3.put("current_value", i - 1);
                        jSONObject2.put(str, jSONObject3);
                    }
                } else {
                    jSONObject2 = new JSONObject();
                    jSONObject3 = new JSONObject();
                    jSONObject3.put("initial_value", i);
                    jSONObject3.put("current_value", i - 1);
                    jSONObject2.put(str, jSONObject3);
                    jSONObject.put(x(), jSONObject2);
                }
                sharedPreferences.edit().putString(f, jSONObject.toString()).apply();
            } else {
                jSONObject2 = new JSONObject();
                jSONObject = new JSONObject();
                jSONObject3 = new JSONObject();
                jSONObject3.put("initial_value", i);
                jSONObject3.put("current_value", i - 1);
                jSONObject.put(str, jSONObject3);
                jSONObject2.put(x(), jSONObject);
                sharedPreferences.edit().putString(f, jSONObject2.toString()).apply();
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
        z();
    }

    private void z() {
        SharedPreferences sharedPreferences = Appodeal.b.getSharedPreferences("Appodeal_placement_capping", 0);
        Date c = an.c(an.f());
        for (Entry entry : sharedPreferences.getAll().entrySet()) {
            Date c2 = an.c((String) entry.getKey());
            if (!(c == null || c2 == null || !c2.before(c))) {
                sharedPreferences.edit().remove((String) entry.getKey()).apply();
            }
        }
    }

    public boolean e(int i) {
        long currentTimeMillis = System.currentTimeMillis();
        if (g(i) && w() > 0 && this.h > 0 && currentTimeMillis - this.h < ((long) w())) {
            return false;
        }
        switch (i) {
            case 1:
                if (f() < 0 || this.e == 0 || currentTimeMillis - this.e >= ((long) f())) {
                    return true;
                }
                return false;
            case 2:
                if (p() < 0 || this.g == 0 || currentTimeMillis - this.g >= ((long) p())) {
                    return true;
                }
                return false;
            case 128:
                if (j() < 0 || this.f == 0 || currentTimeMillis - this.f >= ((long) j())) {
                    return true;
                }
                return false;
            default:
                return true;
        }
    }

    public void f(int i) {
        long currentTimeMillis = System.currentTimeMillis();
        if (g(i) && w() > 0) {
            this.h = currentTimeMillis;
        }
        switch (i) {
            case 1:
                if (f() > 0 || w() > 0) {
                    this.e = currentTimeMillis;
                    return;
                }
                return;
            case 2:
                if (p() > 0 || w() > 0) {
                    this.g = currentTimeMillis;
                    return;
                }
                return;
            case 128:
                if (j() > 0 || w() > 0) {
                    this.f = currentTimeMillis;
                    return;
                }
                return;
            default:
                return;
        }
    }

    public boolean a(int i, double d) {
        switch (i) {
            case 1:
                if (d < h()) {
                    return false;
                }
                return true;
            case 2:
                if (d < r()) {
                    return false;
                }
                return true;
            case 4:
                if (d < d()) {
                    return false;
                }
                return true;
            case 128:
                if (d < n()) {
                    return false;
                }
                return true;
            case 256:
                return d >= u();
            default:
                return true;
        }
    }

    private boolean g(int i) {
        return i == 1 || i == 2 || i == 128;
    }
}
