package com.chartboost.sdk.Libraries;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.provider.Settings.Secure;
import android.util.Base64;
import com.chartboost.sdk.f;
import com.chartboost.sdk.impl.ae;
import java.util.UUID;
import org.json.JSONObject;

public final class c {
    private static String a = null;
    private static String b = null;
    private static a c = a.PRELOAD;
    private static String d = null;

    public enum a {
        PRELOAD(-1),
        LOADING(-1),
        UNKNOWN(-1),
        TRACKING_ENABLED(0),
        TRACKING_DISABLED(1);
        
        private final int f;

        private a(int i) {
            this.f = i;
        }

        public int a() {
            return this.f;
        }

        public boolean b() {
            return this.f != -1;
        }
    }

    private c() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a() {
        /*
        r1 = com.chartboost.sdk.Libraries.d.class;
        monitor-enter(r1);
        r0 = c();	 Catch:{ all -> 0x002c }
        r2 = com.chartboost.sdk.Libraries.c.a.PRELOAD;	 Catch:{ all -> 0x002c }
        if (r0 == r2) goto L_0x000d;
    L_0x000b:
        monitor-exit(r1);	 Catch:{ all -> 0x002c }
    L_0x000c:
        return;
    L_0x000d:
        r0 = com.chartboost.sdk.Libraries.c.a.LOADING;	 Catch:{ all -> 0x002c }
        a(r0);	 Catch:{ all -> 0x002c }
        monitor-exit(r1);	 Catch:{ all -> 0x002c }
        r0 = 0;
        r1 = "com.google.android.gms.ads.identifier.AdvertisingIdClient";
        r0 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x003c }
    L_0x001a:
        if (r0 != 0) goto L_0x002f;
    L_0x001c:
        r0 = new java.lang.ClassNotFoundException;	 Catch:{ ClassNotFoundException -> 0x0024 }
        r1 = "Google play services library is missing. Unable to find class com.google.android.gms.ads.identifier.AdvertisingIdClient";
        r0.<init>(r1);	 Catch:{ ClassNotFoundException -> 0x0024 }
        throw r0;	 Catch:{ ClassNotFoundException -> 0x0024 }
    L_0x0024:
        r0 = move-exception;
        r0.printStackTrace();
        g();
        goto L_0x000c;
    L_0x002c:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x002c }
        throw r0;
    L_0x002f:
        r0 = com.chartboost.sdk.impl.u.a();
        r1 = new com.chartboost.sdk.Libraries.c$1;
        r1.<init>();
        r0.execute(r1);
        goto L_0x000c;
    L_0x003c:
        r1 = move-exception;
        goto L_0x001a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.Libraries.c.a():void");
    }

    private static void g() {
        CBLogging.b("CBIdentity", "WARNING: It looks like you've forgotten to include the Google Play Services library in your project. Please review the SDK documentation for more details.");
        a(a.UNKNOWN);
        ae.a();
    }

    public static String b() {
        if (a == null) {
            a = h();
        }
        return a;
    }

    public static synchronized a c() {
        a aVar;
        synchronized (c.class) {
            aVar = c;
        }
        return aVar;
    }

    protected static synchronized void a(a aVar) {
        synchronized (c.class) {
            c = aVar;
        }
    }

    public static synchronized String d() {
        String str;
        synchronized (c.class) {
            str = b;
        }
        return str;
    }

    private static synchronized void b(String str) {
        synchronized (c.class) {
            b = str;
        }
    }

    private static String h() {
        Object e = e();
        if (e == null || "9774d56d682e549c".equals(e)) {
            e = i();
        }
        String d = d();
        com.chartboost.sdk.Libraries.e.a a = com.chartboost.sdk.Libraries.e.a.a();
        a.a("uuid", e);
        a.a("gaid", d);
        JSONObject e2 = a.e();
        if (e2 == null) {
            e2 = new JSONObject();
        }
        return Base64.encodeToString(e2.toString().getBytes(), 0);
    }

    public static String e() {
        return Secure.getString(com.chartboost.sdk.c.x().getContentResolver(), "android_id");
    }

    private static String i() {
        if (d == null) {
            SharedPreferences p = f.p();
            if (p != null) {
                d = p.getString("cbUUID", null);
                if (d == null) {
                    d = UUID.randomUUID().toString();
                    Editor edit = p.edit();
                    edit.putString("cbUUID", d);
                    edit.apply();
                }
            }
        }
        return d;
    }
}
