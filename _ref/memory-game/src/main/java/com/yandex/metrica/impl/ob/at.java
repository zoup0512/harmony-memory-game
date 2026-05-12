package com.yandex.metrica.impl.ob;

import android.os.SystemClock;
import android.text.TextUtils;
import com.yandex.metrica.impl.av;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONObject;

public abstract class at {
    protected j a;
    protected ax b;
    protected long c = this.b.a(-1);
    protected long d = this.b.c(SystemClock.elapsedRealtime());
    protected AtomicLong e = new AtomicLong(this.b.e(0));
    private boolean f = this.b.b(true);
    private volatile a g;

    static class a {
        private final String a;
        private final String b;
        private final String c;
        private final String d;
        private final String e;
        private final String f;
        private final int g;

        a(JSONObject jSONObject) {
            this.a = jSONObject.optString("kitVer");
            this.b = jSONObject.optString("clientKitVer");
            this.c = jSONObject.optString("kitBuildNumber");
            this.d = jSONObject.optString("appVer");
            this.e = jSONObject.optString("appBuild");
            this.f = jSONObject.optString("osVer");
            this.g = jSONObject.optInt("osApiLev", -1);
        }

        boolean a(av avVar) {
            return TextUtils.equals(avVar.h(), this.a) && TextUtils.equals(avVar.i(), this.b) && TextUtils.equals(avVar.k(), this.c) && TextUtils.equals(avVar.x(), this.d) && TextUtils.equals(avVar.z(), this.e) && TextUtils.equals(avVar.q(), this.f) && this.g == avVar.r();
        }
    }

    protected abstract ay a();

    protected abstract int b();

    at(j jVar, ax axVar) {
        this.a = jVar;
        this.b = axVar;
        this.b.d(this.d).a();
    }

    long c() {
        return this.c;
    }

    synchronized void d() {
        this.c = System.currentTimeMillis() / 1000;
        this.e.set(0);
        this.d = SystemClock.elapsedRealtime();
        this.g = null;
        this.b.i(this.c).h(SystemClock.elapsedRealtime() / 1000).d(this.d).f(this.e.get()).a();
        this.a.i().a(this.c, a());
        a(true);
    }

    long e() {
        return this.b.g(0) - TimeUnit.MILLISECONDS.toSeconds(this.d);
    }

    boolean f() {
        boolean z;
        if (this.c >= 0) {
            long elapsedRealtime = (SystemClock.elapsedRealtime() / 1000) - this.b.g(0);
            long g = g();
            if (elapsedRealtime < 0 || elapsedRealtime >= ((long) b()) || g >= au.a) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                a l = l();
                z = l != null ? !l.a(this.a.h()) : false;
                if (!z) {
                    z = false;
                    return z;
                }
            }
        }
        z = true;
        if (z) {
        }
    }

    long g() {
        return TimeUnit.MILLISECONDS.toSeconds(SystemClock.elapsedRealtime() - this.d);
    }

    synchronized void h() {
        this.b.h(-2147483648L).a();
        this.g = null;
    }

    void i() {
        this.b.h(SystemClock.elapsedRealtime() / 1000).a();
    }

    long j() {
        long andIncrement = this.e.getAndIncrement();
        this.b.f(this.e.get()).a();
        return andIncrement;
    }

    boolean k() {
        return this.f && c() > 0;
    }

    void a(boolean z) {
        if (this.f != z) {
            this.f = z;
            this.b.a(this.f).a();
        }
    }

    private a l() {
        if (this.g == null) {
            synchronized (this) {
                if (this.g == null) {
                    try {
                        Object asString = this.a.i().c(c(), a()).getAsString("report_request_parameters");
                        if (!TextUtils.isEmpty(asString)) {
                            this.g = new a(new JSONObject(asString));
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        return this.g;
    }
}
