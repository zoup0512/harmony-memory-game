package com.yandex.metrica.impl.ob;

import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import com.yandex.metrica.impl.ob.en.a;
import com.yandex.metrica.impl.ob.en.b;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class ea {
    private static final String a = ea.class.getSimpleName();
    private du b;
    private du c;
    private el d;
    private Map<String, String> e = new HashMap();
    private eb f;
    private String g;
    private ec h;
    private long i;
    private final ReentrantLock j = new ReentrantLock();

    ea(dx dxVar, dr drVar, el elVar, dw dwVar) {
        this.b = drVar.c();
        this.c = drVar.a();
        this.d = elVar;
        this.g = dwVar.b();
        this.e.put("app_id", dxVar.c());
        this.e.put("app_platform", "android_" + VERSION.RELEASE);
        this.e.put("manufacturer", Build.MANUFACTURER);
        this.e.put("model", Build.MODEL);
        this.e.put("app_version", dxVar.a());
        this.i = dwVar.a();
    }

    ReentrantLock a() {
        return this.j;
    }

    synchronized boolean b() {
        boolean a;
        if (j()) {
            Log.i(a, "starting pins update on error");
            JSONObject g = g();
            if (g != null) {
                a = a(g);
            } else {
                h();
            }
        }
        a = false;
        return a;
    }

    synchronized void c() {
        if (d() && j()) {
            Log.i(a, "starting pins update on schedule");
            this.f = i();
            this.d.a(this.f, new b<JSONObject>(this) {
                final /* synthetic */ ea a;

                {
                    this.a = r1;
                }

                public void a(JSONObject jSONObject) {
                    this.a.a(jSONObject);
                    this.a.f = null;
                }
            }, new a(this) {
                final /* synthetic */ ea a;

                {
                    this.a = r1;
                }

                public void a(ek ekVar) {
                    Log.i(ea.a, "can't update pins on schedule: " + ekVar.getMessage());
                    this.a.h();
                    this.a.f = null;
                }
            });
        }
    }

    synchronized void a(ec ecVar) {
        this.h = ecVar;
    }

    boolean d() {
        return !e() && (a(this.b, this.i) || a(this.c, this.i));
    }

    boolean e() {
        return this.f != null;
    }

    private JSONObject g() {
        Exception e;
        try {
            eo a = eo.a();
            this.d.a(i(), a, a);
            return (JSONObject) a.get(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e2) {
            e = e2;
        } catch (ExecutionException e3) {
            e = e3;
        } catch (TimeoutException e4) {
            e = e4;
        }
        Log.i(a, "can't update pins on error: " + e.getMessage());
        return null;
    }

    private boolean a(JSONObject jSONObject) {
        try {
            a(jSONObject.getJSONArray("pins-sha256"), this.b);
            a(jSONObject.getJSONArray("blacklist"), this.c);
            Log.i(a, "pins have been updated");
            return true;
        } catch (JSONException e) {
            Log.i(a, "can't update pins: " + e.getMessage());
            return false;
        }
    }

    private static void a(JSONArray jSONArray, du duVar) throws JSONException {
        duVar.a();
        for (int i = 0; i < jSONArray.length(); i++) {
            duVar.a(jSONArray.getString(i));
        }
    }

    private void h() {
        this.b.d();
        this.c.d();
    }

    private static boolean a(du duVar, long j) {
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis - duVar.c() >= j || currentTimeMillis < duVar.c();
    }

    private eb i() {
        CharSequence a = this.h.a();
        if (TextUtils.isEmpty(a)) {
            this.e.remove("uuid");
        } else {
            this.e.put("uuid", a);
        }
        return new eb(this.g, this.e);
    }

    private boolean j() {
        return this.d != null;
    }
}
