package com.appodeal.ads;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.util.Pair;
import com.appodeal.ads.utils.b.b;
import com.appodeal.ads.utils.b.c;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.places.model.PlaceFields;
import com.mopub.common.GpsHelper;
import com.yalantis.ucrop.util.FileUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class m {
    public static String b = "off";
    public static String c = "all";
    public static String d = "fatal";
    @VisibleForTesting
    final c a;
    private final String e;
    private JSONObject f;
    private final long g;
    private Future<?> h;
    private ExecutorService i;
    private UncaughtExceptionHandler j;

    private static class a {
        static final m a = new m();
    }

    private m() {
        this.e = "ExceptionsHandler";
        this.g = System.currentTimeMillis();
        this.a = new c();
        try {
            if (this.a.c()) {
                c();
            }
        } catch (Exception e) {
            Log.e("ExceptionsHandler", e.toString());
        }
    }

    private void c() {
        this.j = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler(this) {
            final /* synthetic */ m a;

            {
                this.a = r1;
            }

            public void uncaughtException(Thread thread, Throwable th) {
                th.printStackTrace(new PrintWriter(new StringWriter()));
                this.a.a(th, true);
                if (this.a.j != null && !this.a.j.equals(this)) {
                    this.a.j.uncaughtException(thread, th);
                }
            }
        });
    }

    private void d() {
        if (this.j != null) {
            Thread.setDefaultUncaughtExceptionHandler(this.j);
        }
    }

    public static m a() {
        return a.a;
    }

    public synchronized void a(Throwable th) {
        try {
            if (this.a.c()) {
                th.printStackTrace(new PrintWriter(new StringWriter()));
                a(th, false);
            }
        } catch (Exception e) {
            Log.e("ExceptionsHandler", e.toString());
        }
    }

    private synchronized void a(Throwable th, boolean z) {
        try {
            if (this.a.c() && (!this.a.d() || z)) {
                JSONObject jSONObject = new JSONObject(f().toString());
                jSONObject.put("fatal", z);
                JSONArray jSONArray = new JSONArray();
                while (th != null) {
                    jSONArray.put(b(th));
                    th = th.getCause();
                }
                jSONObject.put("errors", jSONArray);
                a(jSONObject, Appodeal.b);
                this.a.a(jSONObject.toString());
                if (!z) {
                    b();
                }
            }
        } catch (JSONException e) {
            Log.e("ExceptionsHandler", e.toString());
        }
    }

    void b() {
        if (Appodeal.b != null && an.a(Appodeal.b) && this.a.f() && !this.a.b()) {
            if (this.h == null || this.h.isDone()) {
                e();
                this.h = this.i.submit(new b(this.a));
            }
        }
    }

    private void e() {
        if (this.i == null) {
            this.i = Executors.newSingleThreadExecutor();
        }
    }

    private JSONObject f() {
        if (this.f != null) {
            return this.f;
        }
        this.f = new JSONObject();
        try {
            this.f.put("sdk", "1.15.7");
            String packageName = Appodeal.b.getPackageName();
            this.f.put("package", packageName);
            PackageManager packageManager = Appodeal.b.getPackageManager();
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
                this.f.put("package_version", packageInfo.versionName);
                this.f.put("package_code", packageInfo.versionCode);
                this.f.put("framework", packageManager.getApplicationInfo(packageName, 128).metaData.getString("com.appodeal.framework"));
            } catch (Exception e) {
                Log.e("ExceptionsHandler", e.toString());
            }
            Object string = Appodeal.b.getSharedPreferences("appodeal", 0).getString(GpsHelper.ADVERTISING_ID_KEY, null);
            if (string == null) {
                string = an.l(Appodeal.b);
            }
            this.f.put("idfa", string);
            this.f.put("android_level", VERSION.SDK_INT);
            this.f.put("model", Build.MODEL);
            this.f.put("manufacturer", Build.MANUFACTURER);
            if (an.n(Appodeal.b)) {
                this.f.put("device_type", "tablet");
            } else {
                this.f.put("device_type", PlaceFields.PHONE);
            }
            this.f.put("platform", Build.MANUFACTURER.equals("Amazon") ? "amazon" : "google");
            this.f.put("os", "Android");
            this.f.put("os_version", VERSION.RELEASE);
            Pair f = an.f(Appodeal.b);
            this.f.put("width", f.first);
            this.f.put("height", f.second);
            this.f.put("cpu", an.b());
            this.f.put("opengl", an.o(Appodeal.b));
            this.f.put("ram_total", an.c());
            this.f.put("disk_total", an.e());
            this.f.put("root", an.a());
        } catch (JSONException e2) {
            Log.e("ExceptionsHandler", e2.toString());
        }
        return this.f;
    }

    private JSONObject a(JSONObject jSONObject, Context context) {
        try {
            jSONObject.put("connection", an.b(context).a);
            jSONObject.put("ram_current", an.p(context));
            jSONObject.put("disk_current", an.d());
            jSONObject.put("battery", (double) an.k(context));
            jSONObject.put("running_time", g());
            jSONObject.put("orientation", an.q(context));
            jSONObject.put("online", an.a(context));
            jSONObject.put("muted", an.r(context));
            jSONObject.put("background", Appodeal.a);
            jSONObject.put("timestamp", System.currentTimeMillis());
        } catch (JSONException e) {
            Log.e("ExceptionsHandler", e.toString());
        }
        return jSONObject;
    }

    private JSONObject b(Throwable th) {
        if (th == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE, th.getLocalizedMessage());
            jSONObject.put("error_name", th.getClass().getName());
            JSONArray jSONArray = new JSONArray();
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("method", stackTraceElement.getClassName() + FileUtils.HIDDEN_PREFIX + stackTraceElement.getMethodName());
                jSONObject2.put(TransferTable.COLUMN_FILE, stackTraceElement.getFileName() == null ? AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN : stackTraceElement.getFileName());
                jSONObject2.put("line_number", stackTraceElement.getLineNumber());
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("error_trace", jSONArray);
            return jSONObject;
        } catch (Exception e) {
            Log.e("ExceptionsHandler", e.toString());
            return jSONObject;
        }
    }

    private Long g() {
        return Long.valueOf(System.currentTimeMillis() - this.g);
    }

    void a(String str) {
        if (!this.a.g().equals(str)) {
            this.a.c(str);
            if (str.equals(b)) {
                d();
                this.a.e();
                return;
            }
            c();
        }
    }
}
