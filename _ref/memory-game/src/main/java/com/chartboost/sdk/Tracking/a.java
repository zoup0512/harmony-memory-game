package com.chartboost.sdk.Tracking;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Log;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.b;
import com.chartboost.sdk.Libraries.g;
import com.chartboost.sdk.Libraries.h;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.c;
import com.chartboost.sdk.f;
import com.chartboost.sdk.impl.ad;
import com.chartboost.sdk.impl.w;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.AnalyticsEvents;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

public class a implements com.chartboost.sdk.Libraries.a {
    private static final String b = a.class.getSimpleName();
    private static final Long j = Long.valueOf(TimeUnit.MINUTES.toMillis(5));
    private String c;
    private JSONArray d = new JSONArray();
    private long e;
    private long f;
    private final long g = System.currentTimeMillis();
    private final h h = new h(false);
    private boolean i = false;
    private long k = (System.currentTimeMillis() - j.longValue());

    public static void a() {
        a("start");
        a("did-become-active");
    }

    private static void a(String str) {
        f.k().a(SettingsJsonConstants.SESSION_KEY, str, null, null, null, null, SettingsJsonConstants.SESSION_KEY, false);
    }

    public void b() {
        a(false);
    }

    private void a(boolean z) {
        com.chartboost.sdk.Libraries.e.a a = com.chartboost.sdk.Libraries.e.a.a();
        a.a("complete", Boolean.valueOf(z));
        f.k().a(SettingsJsonConstants.SESSION_KEY, "end", null, null, null, null, a.e(), SettingsJsonConstants.SESSION_KEY, false);
        a("did-become-active");
    }

    public void a(String str, String str2, String str3, String str4) {
        a("webview-track", str, str2, str3, str4, null, null, "system", false);
    }

    public static void a(JSONObject jSONObject) {
        f.k().a("folder", c.G().booleanValue() ? "web" : AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, null, null, null, null, jSONObject, "system", false);
    }

    public static void a(String str, String str2, String str3, boolean z) {
        f.k().a("ad-get", str, str2, TextUtils.isEmpty(str3) ? "empty-adid" : str3, b(z), "single", null, "system", false);
    }

    public static void a(String str, String str2, String str3) {
        f.k().a("ad-show", str, str2, str3, null, null, "system", false);
    }

    public void b(String str, String str2, String str3) {
        a("ad-click", str, str2, str3, null, null, "system", false);
    }

    public void c(String str, String str2, String str3) {
        a("ad-close", str, str2, str3, null, null, "system", false);
    }

    public void d(String str, String str2, String str3) {
        a("ad-dismiss", str, str2, str3, null, null, "system", false);
    }

    public void a(String str, String str2, String str3, CBImpressionError cBImpressionError) {
        a("ad-error", str, str2, TextUtils.isEmpty(str3) ? "empty-adid" : str3, cBImpressionError != null ? cBImpressionError.toString() : "", null, "system", false);
    }

    public void a(String str, String str2, String str3, String str4, boolean z) {
        a("ad-error", str, str2, TextUtils.isEmpty(str3) ? "empty-adid" : str3, str4, null, "system", z);
    }

    public void b(String str, String str2, String str3, String str4) {
        a("ad-warning", str, str2, TextUtils.isEmpty(str3) ? "empty-adid" : str3, str4, null, "system", false);
    }

    public static void a(String str, String str2) {
        f.k().a("asset-prefetcher", "start", c.G().booleanValue() ? "web" : AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, str, str2, null, null, "system", false);
    }

    public static void c(String str, String str2, String str3, String str4) {
        f.k().a("asset-prefetcher", "failure", c.G().booleanValue() ? "web" : AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, str, str2, str4, null, "system", false);
    }

    public static void e(String str, String str2, String str3) {
        f.k().a("asset-prefetcher", GraphResponse.SUCCESS_KEY, c.G().booleanValue() ? "web" : AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, str, str2, str3, null, "system", false);
    }

    public void b(String str, String str2) {
        a("playback-complete", str, str2, null, null, null, "system", false);
    }

    public void c(String str, String str2) {
        a("replay", str, str2, null, null, null, "system", false);
    }

    public void d(String str, String str2) {
        a("playback-start", str, str2, null, null, null, "system", false);
    }

    public void e(String str, String str2) {
        a("playback-stop", str, str2, null, null, null, "system", false);
    }

    public static void a(Class cls, String str, Exception exception) {
        exception.printStackTrace();
        a k = f.k();
        if (k != null) {
            k.b(cls, str, exception);
        }
    }

    private synchronized void b(Class cls, String str, Exception exception) {
        if (!this.i) {
            this.i = true;
            try {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - this.k >= j.longValue()) {
                    String str2 = str;
                    a("exception", cls.getName(), str2, exception.getClass().getName(), exception.getMessage(), Log.getStackTraceString(exception), null, "critical", true);
                    this.k = currentTimeMillis;
                }
                this.i = false;
            } catch (Exception e) {
                e.printStackTrace();
                this.i = false;
            } catch (Throwable th) {
                this.i = false;
            }
        }
    }

    public static void a(String str, String str2, String str3, String str4, String str5, String str6, JSONObject jSONObject) {
        f.k().a(str, str2, str3, str4, str5, str6, jSONObject, "system", false);
    }

    private void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z) {
        a(str, str2, str3, str4, str5, str6, new JSONObject(), str7, z);
    }

    private void a(String str, String str2, String str3, String str4, String str5, String str6, JSONObject jSONObject, String str7, boolean z) {
        JSONObject m = c.m();
        if (m != null && m.optBoolean(str7)) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = currentTimeMillis - this.e;
            currentTimeMillis -= this.g;
            com.chartboost.sdk.Libraries.e.a a = com.chartboost.sdk.Libraries.e.a.a();
            a.a("event", a((Object) str));
            a.a("kingdom", a((Object) str2));
            a.a("phylum", a((Object) str3));
            a.a("class", a((Object) str4));
            a.a("family", a((Object) str5));
            a.a("genus", a((Object) str6));
            String str8 = "meta";
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            }
            a.a(str8, jSONObject);
            a.a("clientTimestamp", Long.valueOf(System.currentTimeMillis() / 1000));
            a.a("session_id", h());
            a.a("totalSessionTime", Long.valueOf(j / 1000));
            a.a("currentSessionTime", Long.valueOf(currentTimeMillis / 1000));
            synchronized (this) {
                boolean z2 = this.i;
                this.i = true;
                try {
                    this.d.put(a.e());
                    Object a2 = com.chartboost.sdk.Libraries.e.a.a();
                    a2.a("events", this.d);
                    CBLogging.a(b, "###Writing" + a((Object) str) + "to tracking cache dir");
                    g();
                    if (z || d()) {
                        a(com.chartboost.sdk.Libraries.e.a.a(a2)).t();
                        k();
                    }
                    this.i = z2;
                } catch (Throwable th) {
                    this.i = z2;
                }
            }
        }
    }

    private boolean d() {
        if (this.d == null || this.d.length() < 50) {
            return false;
        }
        return true;
    }

    private String e() {
        com.chartboost.sdk.Libraries.e.a a = com.chartboost.sdk.Libraries.e.a.a();
        a.a("startTime", Long.valueOf(System.currentTimeMillis()));
        a.a("deviceID", com.chartboost.sdk.Libraries.c.e());
        this.c = b.b(a.toString().getBytes());
        return this.c;
    }

    public void c() {
        com.chartboost.sdk.Libraries.e.a a = this.h.a(this.h.g(), "cb_previous_session_info");
        if (a != null) {
            this.f = a.i("timestamp");
            this.e = a.i("start_timestamp");
            this.c = a.e("session_id");
            if (System.currentTimeMillis() - this.f > 180000) {
                a(true);
            } else if (!TextUtils.isEmpty(this.c)) {
                g();
                return;
            }
        }
        f();
    }

    private void f() {
        long currentTimeMillis = System.currentTimeMillis();
        this.e = currentTimeMillis;
        this.f = currentTimeMillis;
        this.c = e();
        a(currentTimeMillis, currentTimeMillis);
        SharedPreferences p = f.p();
        int i = p.getInt("cbPrefSessionCount", 0) + 1;
        Editor edit = p.edit();
        edit.putInt("cbPrefSessionCount", i);
        edit.apply();
    }

    private void g() {
        a(this.e, System.currentTimeMillis());
    }

    private void a(long j, long j2) {
        com.chartboost.sdk.Libraries.e.a a = com.chartboost.sdk.Libraries.e.a.a();
        a.a("start_timestamp", Long.valueOf(j));
        a.a("timestamp", Long.valueOf(j2));
        a.a("session_id", this.c);
        this.h.a(this.h.g(), "cb_previous_session_info", a);
    }

    private ad a(com.chartboost.sdk.Libraries.e.a aVar) {
        ad adVar = new ad("/api/track");
        adVar.a("track", (Object) aVar);
        adVar.a(g.a(g.a("status", com.chartboost.sdk.Libraries.a.a)));
        adVar.a(w.b.LOW);
        return adVar;
    }

    public String toString() {
        return "Session [ startTime: " + j() + " sessionEvents: " + i() + " ]";
    }

    private String h() {
        return this.c;
    }

    private JSONArray i() {
        return this.d;
    }

    private long j() {
        return this.e;
    }

    private static String b(boolean z) {
        return z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO;
    }

    private static Object a(Object obj) {
        return obj != null ? obj : "";
    }

    private void k() {
        this.d = new JSONArray();
    }
}
