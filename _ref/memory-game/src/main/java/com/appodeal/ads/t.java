package com.appodeal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Base64;
import android.util.Pair;
import com.appodeal.ads.d.h;
import com.appodeal.ads.f.f;
import com.appodeal.ads.utils.Log.LogLevel;
import com.appodeal.ads.utils.d;
import com.appodeal.ads.utils.e;
import com.appodeal.ads.utils.g;
import com.appodeal.ads.utils.s;
import com.appodeal.ads.utils.v;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.places.model.PlaceFields;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.mopub.common.AdType;
import com.mopub.common.Constants;
import com.mopub.common.GpsHelper;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.json.JSONArray;
import org.json.JSONObject;

public class t {
    @VisibleForTesting
    static JSONObject o;
    static SSLSocketFactory p;
    @VisibleForTesting
    final a a;
    @VisibleForTesting
    final Context b;
    @VisibleForTesting
    final int c;
    @VisibleForTesting
    final String d;
    @VisibleForTesting
    final String e;
    @VisibleForTesting
    final String f;
    @VisibleForTesting
    final String g;
    @VisibleForTesting
    final h h;
    @VisibleForTesting
    final com.appodeal.ads.f.c i;
    @VisibleForTesting
    final long j;
    @VisibleForTesting
    final String k;
    @VisibleForTesting
    final Long l;
    @VisibleForTesting
    final int m;
    @VisibleForTesting
    final double n;
    @VisibleForTesting
    boolean q;
    @VisibleForTesting
    boolean r;
    @VisibleForTesting
    boolean s;
    @VisibleForTesting
    boolean t;
    @VisibleForTesting
    boolean u;
    @VisibleForTesting
    boolean v;
    @VisibleForTesting
    boolean w;
    @VisibleForTesting
    boolean x;
    @VisibleForTesting
    boolean y;

    public interface a {
        void a(int i);

        void a(JSONObject jSONObject, int i, String str);
    }

    private class b extends AsyncTask<Void, Void, JSONObject> {
        final /* synthetic */ t a;

        private b(t tVar) {
            this.a = tVar;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((JSONObject) obj);
        }

        protected JSONObject a(Void... voidArr) {
            Throwable th;
            URLConnection uRLConnection;
            Throwable th2;
            Editor edit;
            String str;
            JSONObject jSONObject;
            try {
                JSONObject a = this.a.a(this.a.b.getSharedPreferences("appodeal", 0));
                if (a == null) {
                    return null;
                }
                String a2;
                URL b = this.a.b();
                SharedPreferences sharedPreferences = this.a.b.getSharedPreferences("Appodeal", 0);
                try {
                    URLConnection openConnection = b.openConnection();
                    GZIPOutputStream gZIPOutputStream;
                    try {
                        if (b.getProtocol().equals(Constants.HTTPS)) {
                            ((HttpsURLConnection) openConnection).setSSLSocketFactory(this.a.d());
                        }
                        if (this.a.y && sharedPreferences.contains(this.a.d)) {
                            openConnection.setConnectTimeout(10000);
                            openConnection.setReadTimeout(10000);
                        } else {
                            openConnection.setConnectTimeout(20000);
                            openConnection.setReadTimeout(20000);
                        }
                        openConnection.setConnectTimeout(20000);
                        openConnection.setReadTimeout(20000);
                        openConnection.setRequestProperty("Content-Type", "text/plain; charset=UTF-8");
                        openConnection.setDoOutput(true);
                        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                        gZIPOutputStream.write(a.toString().getBytes("UTF-8"));
                        try {
                            gZIPOutputStream.close();
                        } catch (Throwable e) {
                            Appodeal.a(e);
                        }
                        an.a(openConnection.getOutputStream(), Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0));
                        a2 = an.a(openConnection.getInputStream());
                        if (a2 == null || a2.isEmpty() || a2.equals(" ") || this.a.d.equals("stats")) {
                            a2 = null;
                        }
                        if (openConnection != null) {
                            if (openConnection instanceof HttpsURLConnection) {
                                ((HttpsURLConnection) openConnection).disconnect();
                            } else if (openConnection instanceof HttpURLConnection) {
                                ((HttpURLConnection) openConnection).disconnect();
                            }
                        }
                    } catch (Throwable e2) {
                        th = e2;
                        uRLConnection = openConnection;
                        th2 = th;
                        try {
                            Appodeal.a(th2);
                            if (!th2.getMessage().equals("No valid pins found in chain!") || th2.getMessage().equals("java.security.cert.CertPathValidatorException: Trust anchor for certification path not found.")) {
                                if (uRLConnection != null) {
                                    if (uRLConnection instanceof HttpsURLConnection) {
                                        ((HttpsURLConnection) uRLConnection).disconnect();
                                    } else if (uRLConnection instanceof HttpURLConnection) {
                                        ((HttpURLConnection) uRLConnection).disconnect();
                                    }
                                }
                                return null;
                            }
                            if (uRLConnection != null) {
                                if (uRLConnection instanceof HttpsURLConnection) {
                                    ((HttpsURLConnection) uRLConnection).disconnect();
                                    a2 = null;
                                } else if (uRLConnection instanceof HttpURLConnection) {
                                    ((HttpURLConnection) uRLConnection).disconnect();
                                    a2 = null;
                                }
                                if (a2 != null) {
                                    if (this.a.y) {
                                        edit = sharedPreferences.edit();
                                        edit.putString(this.a.d, a2);
                                        edit.apply();
                                    }
                                    str = a2;
                                } else {
                                    if (this.a.y) {
                                    }
                                    return null;
                                }
                                jSONObject = new JSONObject(str);
                                if (this.a.y) {
                                    Appodeal.a(str);
                                } else {
                                    Appodeal.a(str, LogLevel.verbose);
                                }
                                UserSettings.userData = jSONObject.optJSONObject("user_data");
                                this.a.a(jSONObject);
                                return jSONObject;
                            }
                            a2 = null;
                            if (a2 != null) {
                                if (this.a.y) {
                                }
                                return null;
                            }
                            if (this.a.y) {
                                edit = sharedPreferences.edit();
                                edit.putString(this.a.d, a2);
                                edit.apply();
                            }
                            str = a2;
                            jSONObject = new JSONObject(str);
                            if (this.a.y) {
                                Appodeal.a(str, LogLevel.verbose);
                            } else {
                                Appodeal.a(str);
                            }
                            UserSettings.userData = jSONObject.optJSONObject("user_data");
                            this.a.a(jSONObject);
                            return jSONObject;
                        } catch (Throwable th3) {
                            th2 = th3;
                            if (uRLConnection != null) {
                                if (!(uRLConnection instanceof HttpsURLConnection)) {
                                    ((HttpsURLConnection) uRLConnection).disconnect();
                                } else if (uRLConnection instanceof HttpURLConnection) {
                                    ((HttpURLConnection) uRLConnection).disconnect();
                                }
                            }
                            throw th2;
                        }
                    } catch (Throwable e22) {
                        th = e22;
                        uRLConnection = openConnection;
                        th2 = th;
                        if (uRLConnection != null) {
                            if (!(uRLConnection instanceof HttpsURLConnection)) {
                                ((HttpsURLConnection) uRLConnection).disconnect();
                            } else if (uRLConnection instanceof HttpURLConnection) {
                                ((HttpURLConnection) uRLConnection).disconnect();
                            }
                        }
                        throw th2;
                    }
                } catch (Throwable e222) {
                    th2 = e222;
                    uRLConnection = null;
                    Appodeal.a(th2);
                    if (th2.getMessage().equals("No valid pins found in chain!")) {
                    }
                    if (uRLConnection != null) {
                        if (uRLConnection instanceof HttpsURLConnection) {
                            ((HttpsURLConnection) uRLConnection).disconnect();
                        } else if (uRLConnection instanceof HttpURLConnection) {
                            ((HttpURLConnection) uRLConnection).disconnect();
                        }
                    }
                    return null;
                } catch (Throwable e2222) {
                    th2 = e2222;
                    uRLConnection = null;
                    if (uRLConnection != null) {
                        if (!(uRLConnection instanceof HttpsURLConnection)) {
                            ((HttpsURLConnection) uRLConnection).disconnect();
                        } else if (uRLConnection instanceof HttpURLConnection) {
                            ((HttpURLConnection) uRLConnection).disconnect();
                        }
                    }
                    throw th2;
                }
                if (a2 != null) {
                    if (this.a.y) {
                        edit = sharedPreferences.edit();
                        edit.putString(this.a.d, a2);
                        edit.apply();
                    }
                    str = a2;
                } else if (this.a.y || !sharedPreferences.contains(this.a.d)) {
                    return null;
                } else {
                    Appodeal.a(new com.appodeal.ads.utils.b.a("/get error, using saved waterfall"));
                    str = sharedPreferences.getString(this.a.d, "");
                }
                try {
                    jSONObject = new JSONObject(str);
                    if (this.a.y) {
                        Appodeal.a(str, LogLevel.verbose);
                    } else {
                        Appodeal.a(str);
                    }
                    try {
                        UserSettings.userData = jSONObject.optJSONObject("user_data");
                        this.a.a(jSONObject);
                        return jSONObject;
                    } catch (Throwable th22) {
                        Appodeal.a(th22);
                        return jSONObject;
                    }
                } catch (Throwable e22222) {
                    Appodeal.a(e22222);
                    return null;
                }
            } catch (Throwable e222222) {
                Appodeal.a(e222222);
                return null;
            }
        }

        protected void a(JSONObject jSONObject) {
            try {
                if (this.a.a == null) {
                    return;
                }
                if (jSONObject == null) {
                    this.a.a.a(this.a.c);
                } else {
                    this.a.a.a(jSONObject, this.a.c, this.a.d);
                }
            } catch (Throwable e) {
                Appodeal.a(e);
            }
        }
    }

    public static class c {
        private final Context a;
        private final int b;
        private final String c;
        private a d;
        private String e;
        private String f;
        private String g;
        private h h;
        private com.appodeal.ads.f.c i;
        private long j;
        private String k;
        private Long l;
        private int m = 1;
        private double n = -1.0d;

        public c(Context context, int i, String str) {
            this.a = context;
            this.b = i;
            this.c = str;
        }

        public c a(a aVar) {
            this.d = aVar;
            return this;
        }

        public c a(String str) {
            this.e = str;
            return this;
        }

        public c b(String str) {
            this.f = str;
            return this;
        }

        public c c(String str) {
            this.g = str;
            return this;
        }

        public c a(h hVar) {
            this.h = hVar;
            return this;
        }

        public c a(com.appodeal.ads.f.c cVar) {
            this.i = cVar;
            return this;
        }

        public c a(long j) {
            this.j = j;
            return this;
        }

        public c d(String str) {
            this.k = str;
            return this;
        }

        public c a(Long l) {
            this.l = l;
            return this;
        }

        public c a(int i) {
            this.m = i;
            return this;
        }

        public c a(double d) {
            this.n = d;
            return this;
        }

        public t a() {
            return new t();
        }
    }

    private t(c cVar) {
        boolean z = true;
        this.a = cVar.d;
        this.b = cVar.a;
        this.c = cVar.b;
        this.d = cVar.c;
        this.e = cVar.e;
        this.f = cVar.f;
        this.g = cVar.g;
        this.h = cVar.h;
        this.j = cVar.j;
        this.i = cVar.i;
        this.k = cVar.k;
        this.l = cVar.l;
        this.m = cVar.m;
        this.n = cVar.n;
        if (this.d != null) {
            if (!AppodealSettings.a || ((this.a != null && !(this.a instanceof v)) || (!this.d.equals("stats") && !this.d.equals("show") && !this.d.equals("click") && !this.d.equals("finish") && !this.d.equals("install")))) {
                boolean z2 = this.d.equals("banner") || this.d.equals("debug");
                this.q = z2;
                if (this.d.equals("banner_320") || this.d.equals("debug_banner_320")) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                this.r = z2;
                if (this.d.equals("banner_mrec") || this.d.equals("debug_mrec")) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                this.s = z2;
                if (this.d.equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO) || this.d.equals("debug_video")) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                this.t = z2;
                if (this.d.equals(AdType.REWARDED_VIDEO) || this.d.equals("debug_rewarded_video")) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                this.u = z2;
                if (this.d.equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE) || this.d.equals("debug_native")) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                this.v = z2;
                if (this.d.equals("debug") || this.d.equals("debug_banner_320") || this.d.equals("debug_video") || this.d.equals("debug_rewarded_video") || this.d.equals("debug_mrec") || this.d.equals("debug_native")) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                this.w = z2;
                if (this.q || this.r || this.s || this.t || this.u || this.v) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                this.x = z2;
                if (this.d.equals("stats") || this.d.equals("show") || this.d.equals("click") || this.d.equals("finish") || this.d.equals("install")) {
                    z = false;
                }
                this.y = z;
            }
        }
    }

    public void a() {
        if (this.b instanceof Activity) {
            ((Activity) this.b).runOnUiThread(new Runnable(this) {
                final /* synthetic */ t a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (VERSION.SDK_INT >= 11) {
                        new b().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                    } else {
                        new b().execute(new Void[0]);
                    }
                }
            });
        } else if (VERSION.SDK_INT >= 11) {
            new b().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            new b().execute(new Void[0]);
        }
    }

    private SSLSocketFactory d() {
        SSLSocketFactory sSLSocketFactory;
        synchronized (t.class) {
            if (p == null) {
                List arrayList = new ArrayList();
                arrayList.add("A1ABC1296E644B3A25179FCD3E277C8D36039BEE94478E2F5104FA4244237F54");
                arrayList.add("E91093227F02CE854C3214749DC7FB3459E0E43E80CAE27F01AA0EA92894C9E1");
                TrustManager[] trustManagerArr = new TrustManager[]{new e(arrayList, 1494633600000L)};
                SSLContext instance = SSLContext.getInstance("TLSv1");
                instance.init(null, trustManagerArr, null);
                p = instance.getSocketFactory();
            }
            sSLSocketFactory = p;
        }
        return sSLSocketFactory;
    }

    URL b() {
        if (this.y) {
            return new URL(String.format("%s%s:%s/%s", new Object[]{g.a(g.c()), g.b(), Integer.valueOf(g.c()), "get"}));
        }
        return new URL(String.format("%s%s:%s/%s", new Object[]{g.a(g.c()), g.b(), Integer.valueOf(g.c()), this.d}));
    }

    private void a(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("segments");
        if (optJSONArray != null && optJSONArray.length() != 0) {
            com.appodeal.ads.f.g gVar = new com.appodeal.ads.f.g(this.b, jSONObject);
            if (gVar.b(optJSONArray)) {
                f a = gVar.a(optJSONArray);
                if (a != null) {
                    try {
                        a.a();
                    } catch (Throwable e) {
                        Appodeal.a(e);
                    }
                    com.appodeal.ads.f.g.a(a);
                }
                if (Appodeal.d != null) {
                    Appodeal.d.a();
                }
            }
        }
    }

    @Nullable
    @VisibleForTesting
    JSONObject a(SharedPreferences sharedPreferences) {
        JSONObject b = b(sharedPreferences);
        if (b == null) {
            return null;
        }
        JSONArray jSONArray;
        try {
            if (this.q) {
                b.put("type", "banner");
            }
            if (this.r) {
                b.put("type", "banner_320");
                float g = an.g(this.b);
                float h = an.h(this.b);
                if (g.t && g >= 728.0f && h > 720.0f) {
                    b.put("large_banners", true);
                }
            }
            if (this.s) {
                b.put("type", "banner_mrec");
            }
            if (this.t || this.u) {
                b.put("type", AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO);
            }
            if (this.u) {
                b.put(AdType.REWARDED_VIDEO, true);
            }
            if (this.v) {
                b.put("type", AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
            }
            if (this.w) {
                b.put("debug", true);
            }
            if (AppodealSettings.a) {
                b.put("test", true);
            }
            Pair d = an.d(this.b);
            b.put("lt", d.first);
            b.put("lat", ((Pair) d.second).first);
            b.put("lon", ((Pair) d.second).second);
            com.appodeal.ads.an.a b2 = an.b(this.b);
            b.put("connection", b2.a);
            b.put("battery", (double) an.k(this.b));
            b.put("connection_subtype", b2.b);
            b.put("connection_fast", b2.c);
            b.put("crr", an.c(this.b));
            b.put("locale", Locale.getDefault().toString());
            b.put("timezone", new SimpleDateFormat("Z", Locale.ENGLISH).format(Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.ENGLISH).getTime()));
            b.put("local_time", System.currentTimeMillis() / 1000);
            b.put("user_agent", System.getProperty("http.agent"));
            d.c(this.b);
            b.put("session_id", d.a(sharedPreferences));
            b.put("session_uptime", d.b());
            b.put("app_uptime", d.b(sharedPreferences));
            String string = sharedPreferences.getString("inapps", null);
            if (string != null) {
                b.put("inapps", new JSONObject(string));
            }
            if (this.x) {
                jSONArray = new JSONArray();
                if (this.q) {
                    for (o oVar : n.a(this.b)) {
                        if (oVar.g() != null) {
                            jSONArray.put(oVar.a());
                        }
                    }
                }
                if (this.t) {
                    for (ap apVar : ah.a(this.b)) {
                        if (apVar.g() != null) {
                            jSONArray.put(apVar.a());
                        }
                    }
                }
                if (this.u) {
                    for (ap apVar2 : ak.a(this.b)) {
                        if (apVar2.g() != null) {
                            jSONArray.put(apVar2.a());
                        }
                    }
                }
                if (this.r) {
                    for (h hVar : g.a(this.b)) {
                        if (hVar.f() != null) {
                            jSONArray.put(hVar.a());
                        }
                    }
                }
                if (this.s) {
                    for (w wVar : v.a(this.b)) {
                        if (wVar.f() != null) {
                            jSONArray.put(wVar.a());
                        }
                    }
                }
                if (this.v) {
                    for (ac acVar : Native.a(this.b)) {
                        if (acVar != null) {
                            jSONArray.put(acVar.a());
                        }
                    }
                }
                b.put("show_array", jSONArray);
            }
            if (this.g != null) {
                b.put("loaded_offer", this.g);
            }
            if (!(this.l == null || this.l.longValue() == -1)) {
                b.put("segment_id", this.l);
            }
            if (this.j != 0) {
                b.put("show_timestamp", this.j);
            }
            if (this.d.equals("click")) {
                b.put("click_timestamp", System.currentTimeMillis() / 1000);
            }
            if (this.d.equals("finish")) {
                b.put("finish_timestamp", System.currentTimeMillis() / 1000);
            }
            if (this.m > 1) {
                b.put("capacity", this.m);
            }
            if (this.n > 0.0d) {
                b.put("price_floor", this.n);
            }
            b.put("id", this.e);
            b.put("main_id", this.f);
            if (this.x || this.k != null) {
                b.put("ad_stats", c());
            }
            if (this.i != null) {
                b.put("placement_id", this.i.a());
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(sharedPreferences.getLong("lastSettingsTime", 0));
        instance.add(5, 1);
        if (!UserSettings.sendingInProgress && this.x && (instance.getTimeInMillis() < System.currentTimeMillis() || sharedPreferences.getBoolean("should_update_user_settings", true))) {
            UserSettings.sendingInProgress = true;
            try {
                b.put("sa", s.a(this.b));
            } catch (Throwable e2) {
                Appodeal.a(e2);
            }
            try {
                b.put("user_settings", Appodeal.getUserSettings(this.b).a());
            } catch (Throwable e22) {
                Appodeal.a(e22);
            }
            Editor edit = sharedPreferences.edit();
            edit.putLong("lastSettingsTime", System.currentTimeMillis());
            edit.putBoolean("should_update_user_settings", false);
            edit.apply();
            UserSettings.sendingInProgress = false;
        }
        instance = Calendar.getInstance();
        instance.setTimeInMillis(sharedPreferences.getLong("lastAppTime", 0));
        instance.add(5, 1);
        if (!AppodealSettings.n && AppodealSettings.m && this.x && instance.getTimeInMillis() < System.currentTimeMillis()) {
            AppodealSettings.n = true;
            try {
                jSONArray = new JSONArray();
                List<ApplicationInfo> installedApplications = this.b.getPackageManager().getInstalledApplications(0);
                Pattern compile = Pattern.compile("^?(?:com\\.android|com\\.google|com\\.sec|com\\.samsung|com\\.sonyericsson|com\\.sonymobile|com\\.motorola|com\\.htc).*$");
                if (installedApplications != null) {
                    for (ApplicationInfo applicationInfo : installedApplications) {
                        Object obj = applicationInfo.packageName;
                        if (!(compile.matcher(obj).matches() || obj.equals(AbstractSpiCall.ANDROID_CLIENT_TYPE))) {
                            jSONArray.put(obj);
                        }
                    }
                }
                b.put("apps", jSONArray);
            } catch (Throwable e222) {
                Appodeal.a(e222);
            }
            edit = sharedPreferences.edit();
            edit.putLong("lastAppTime", System.currentTimeMillis());
            edit.apply();
            AppodealSettings.n = false;
        }
        if (this.h != null) {
            if (this.d.equals("stats")) {
                b.put("id", this.h.a().toString());
            } else if (this.d.equals("show")) {
                if (this.h.f()) {
                    if (this.h.d()) {
                        b.put("rtb_check", this.h.c());
                        b.put("bidder_id", this.h.e());
                    } else {
                        Appodeal.a("/get error, rtb invalid check");
                        return null;
                    }
                }
            } else if ((this.d.equals("click") || this.d.equals("finish")) && this.h.f()) {
                b.put("id", this.h.e());
            }
        }
        return b;
    }

    @Nullable
    @VisibleForTesting
    JSONObject b(SharedPreferences sharedPreferences) {
        synchronized (t.class) {
            String str;
            if (o == null) {
                o = new JSONObject();
                PackageManager packageManager = this.b.getPackageManager();
                String string = sharedPreferences.getString("appKey", null);
                if (string == null) {
                    return null;
                }
                String str2;
                Object obj;
                PackageInfo packageInfo;
                Pair f;
                Object installerPackageName;
                String string2 = sharedPreferences.getString(GpsHelper.ADVERTISING_ID_KEY, null);
                Object string3 = sharedPreferences.getString("advertisingTracking", null);
                if (string2 == null && !this.d.equals("install")) {
                    try {
                        Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
                        AdvertisingIdClient.class.getDeclaredMethod("getAdvertisingIdInfo", new Class[]{Context.class});
                        Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.b);
                        string2 = advertisingIdInfo.getId();
                        str2 = advertisingIdInfo.isLimitAdTrackingEnabled() ? AppEventsConstants.EVENT_PARAM_VALUE_NO : AppEventsConstants.EVENT_PARAM_VALUE_YES;
                        Editor edit = sharedPreferences.edit();
                        edit.putString(GpsHelper.ADVERTISING_ID_KEY, string2);
                        edit.putString("advertisingTracking", str2);
                        edit.apply();
                        Appodeal.a(String.format("Advertising ID: %s", new Object[]{string2}));
                        str = string2;
                        obj = str2;
                    } catch (Throwable e) {
                        Appodeal.a(e);
                    }
                    if (str != null) {
                        string3 = an.l(this.b);
                    } else {
                        str2 = str;
                    }
                    o.put("app_key", string);
                    o.put(AbstractSpiCall.ANDROID_CLIENT_TYPE, VERSION.RELEASE);
                    o.put("android_level", VERSION.SDK_INT);
                    o.put("sdk", "1.15.7");
                    string = this.b.getPackageName();
                    o.put("package", string);
                    packageInfo = packageManager.getPackageInfo(string, 0);
                    o.put("package_version", packageInfo.versionName);
                    o.put("package_code", packageInfo.versionCode);
                    o.put("framework", packageManager.getApplicationInfo(string, 128).metaData.getString("com.appodeal.framework"));
                    o.put("android_id", string3);
                    o.put("advertising_tracking", obj);
                    f = an.f(this.b);
                    o.put("width", f.first);
                    o.put("height", f.second);
                    if (an.n(this.b)) {
                        o.put("device_type", PlaceFields.PHONE);
                    } else {
                        o.put("device_type", "tablet");
                    }
                    o.put("platform", Build.MANUFACTURER.equals("Amazon") ? "amazon" : "google");
                    installerPackageName = packageManager.getInstallerPackageName(string);
                    if (installerPackageName == null) {
                        installerPackageName = "unknown";
                    }
                    o.put("installer", installerPackageName);
                    o.put("manufacturer", Build.MANUFACTURER);
                    o.put("model", String.format("%s %s", new Object[]{Build.MANUFACTURER, Build.MODEL}));
                    o.put("rooted", an.a());
                }
                str = string2;
                obj = string3;
                if (str != null) {
                    str2 = str;
                } else {
                    string3 = an.l(this.b);
                }
                o.put("app_key", string);
                o.put(AbstractSpiCall.ANDROID_CLIENT_TYPE, VERSION.RELEASE);
                o.put("android_level", VERSION.SDK_INT);
                o.put("sdk", "1.15.7");
                string = this.b.getPackageName();
                o.put("package", string);
                try {
                    packageInfo = packageManager.getPackageInfo(string, 0);
                    o.put("package_version", packageInfo.versionName);
                    o.put("package_code", packageInfo.versionCode);
                } catch (Throwable e2) {
                    Appodeal.a(e2);
                }
                try {
                    o.put("framework", packageManager.getApplicationInfo(string, 128).metaData.getString("com.appodeal.framework"));
                } catch (Throwable e22) {
                    Appodeal.a(e22);
                }
                o.put("android_id", string3);
                o.put("advertising_tracking", obj);
                f = an.f(this.b);
                o.put("width", f.first);
                o.put("height", f.second);
                if (an.n(this.b)) {
                    o.put("device_type", PlaceFields.PHONE);
                } else {
                    o.put("device_type", "tablet");
                }
                if (Build.MANUFACTURER.equals("Amazon")) {
                }
                o.put("platform", Build.MANUFACTURER.equals("Amazon") ? "amazon" : "google");
                try {
                    installerPackageName = packageManager.getInstallerPackageName(string);
                    if (installerPackageName == null) {
                        installerPackageName = "unknown";
                    }
                    o.put("installer", installerPackageName);
                } catch (Throwable e222) {
                    Appodeal.a(e222);
                }
                o.put("manufacturer", Build.MANUFACTURER);
                o.put("model", String.format("%s %s", new Object[]{Build.MANUFACTURER, Build.MODEL}));
                o.put("rooted", an.a());
            }
            JSONObject jSONObject = new JSONObject();
            Iterator keys = o.keys();
            while (keys.hasNext()) {
                str = (String) keys.next();
                jSONObject.put(str, o.get(str));
            }
            return jSONObject;
        }
    }

    @VisibleForTesting
    JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        int i = ((((g.z + n.r) + ah.p) + ak.p) + Native.q) + v.v;
        int i2 = ak.o + ah.o;
        try {
            jSONObject.put("show", ((((g.y + n.q) + ah.n) + ak.n) + Native.p) + v.u);
            jSONObject.put("click", i);
            if (this.t || this.u || (this.k != null && (this.k.equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO) || this.k.equals(AdType.REWARDED_VIDEO)))) {
                jSONObject.put("finish", i2);
            }
            if (this.q || (this.k != null && this.k.equals("banner"))) {
                jSONObject.put(String.format("%s_%s", new Object[]{"banner", "show"}), n.q);
                jSONObject.put(String.format("%s_%s", new Object[]{"banner", "click"}), n.r);
            }
            if (this.t || (this.k != null && this.k.equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO))) {
                jSONObject.put(String.format("%s_%s", new Object[]{AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO, "show"}), ah.n);
                jSONObject.put(String.format("%s_%s", new Object[]{AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO, "click"}), ah.p);
                jSONObject.put(String.format("%s_%s", new Object[]{AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO, "finish"}), ah.o);
            }
            if (this.u || (this.k != null && this.k.equals(AdType.REWARDED_VIDEO))) {
                jSONObject.put(String.format("%s_%s", new Object[]{AdType.REWARDED_VIDEO, "show"}), ak.n);
                jSONObject.put(String.format("%s_%s", new Object[]{AdType.REWARDED_VIDEO, "click"}), ak.p);
                jSONObject.put(String.format("%s_%s", new Object[]{AdType.REWARDED_VIDEO, "finish"}), ak.o);
            }
            if (this.r || (this.k != null && this.k.equals("banner_320"))) {
                jSONObject.put(String.format("%s_%s", new Object[]{"banner_320", "show"}), g.y);
                jSONObject.put(String.format("%s_%s", new Object[]{"banner_320", "click"}), g.z);
            }
            if (this.s || (this.k != null && this.k.equals("banner_mrec"))) {
                jSONObject.put(String.format("%s_%s", new Object[]{"banner_mrec", "show"}), v.u);
                jSONObject.put(String.format("%s_%s", new Object[]{"banner_mrec", "click"}), v.v);
            }
            if (this.v || (this.k != null && this.k.equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE))) {
                jSONObject.put(String.format("%s_%s", new Object[]{AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, "show"}), Native.p);
                jSONObject.put(String.format("%s_%s", new Object[]{AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, "click"}), Native.q);
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
        return jSONObject;
    }
}
