package com.yandex.metrica.impl;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Build.VERSION;
import com.facebook.appevents.AppEventsConstants;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.a;
import com.yandex.metrica.impl.interact.DeviceInfo;
import com.yandex.metrica.impl.ob.bm;
import com.yandex.metrica.impl.ob.br;
import com.yandex.metrica.impl.ob.cf;
import com.yandex.metrica.impl.ob.j;
import com.yandex.metrica.impl.utils.e;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.util.List;
import java.util.Locale;

public class av {
    private String A;
    private String B;
    private String C;
    private String D = AppEventsConstants.EVENT_PARAM_VALUE_NO;
    private boolean E;
    private String F;
    private String G;
    private String a = Build.MANUFACTURER;
    private String b = Build.MODEL;
    private String c = VERSION.RELEASE;
    private int d = VERSION.SDK_INT;
    private String e = "251";
    private String f = ax.a();
    private String g = "5816";
    private String h = "public";
    private String i = AbstractSpiCall.ANDROID_CLIENT_TYPE;
    private String j = "2";
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private int q;
    private int r;
    private int s;
    private float t;
    private String u = a.PHONE.name().toLowerCase(Locale.US);
    private String v;
    private String w;
    private String x;
    private String y;
    private String z = "https://startup.mobile.yandex.net/";

    public String a() {
        return this.o;
    }

    public synchronized String b() {
        return a(this.k, "");
    }

    public synchronized void a(String str) {
        if (!be.a(str)) {
            this.k = str;
        }
    }

    public synchronized void b(String str) {
        if (!be.a(str)) {
            this.l = str;
        }
    }

    public synchronized String c() {
        return a(this.l, "");
    }

    public String d() {
        return a(this.p, "");
    }

    public void c(String str) {
        this.p = str;
    }

    public synchronized void d(String str) {
        this.m = str;
    }

    public synchronized String e() {
        return this.m;
    }

    public String f() {
        return this.j;
    }

    public String g() {
        return this.f;
    }

    public void e(String str) {
        this.e = str;
    }

    public String h() {
        return this.e;
    }

    public void f(String str) {
        this.w = str;
    }

    public String i() {
        return this.w;
    }

    public int j() {
        return e.a(this.w, 0);
    }

    public String k() {
        return this.g;
    }

    public void g(String str) {
        this.g = str;
    }

    public String l() {
        return this.h;
    }

    public void h(String str) {
        this.h = str;
    }

    public String m() {
        return this.i;
    }

    public String n() {
        return a(this.n, "");
    }

    public String o() {
        return a(this.a, "");
    }

    public String p() {
        return a(this.b, "");
    }

    public String q() {
        return a(this.c, "");
    }

    public int r() {
        return this.d;
    }

    public void i(String str) {
        this.c = str;
    }

    public void a(int i) {
        this.d = i;
    }

    public int s() {
        return this.q;
    }

    public int t() {
        return this.r;
    }

    public int u() {
        return this.s;
    }

    public float v() {
        return this.t;
    }

    public String w() {
        return a(this.v, "");
    }

    public void j(String str) {
        this.v = str;
    }

    public String x() {
        return a(this.x, "");
    }

    public void l(String str) {
        this.F = str;
    }

    public String y() {
        return a(this.F, "");
    }

    public String z() {
        return a(this.y, "");
    }

    public void n(String str) {
        this.A = str;
    }

    public void o(String str) {
        this.B = str;
    }

    public String A() {
        return a(this.B, "");
    }

    public String B() {
        return a(this.C, "");
    }

    public void p(String str) {
        this.C = str;
    }

    public String C() {
        return a(this.A, "");
    }

    public String D() {
        return a(this.z, "https://startup.mobile.yandex.net/");
    }

    public void q(String str) {
        this.D = str;
    }

    public String E() {
        return a(this.D, AppEventsConstants.EVENT_PARAM_VALUE_NO);
    }

    public String F() {
        return a(this.u, a.PHONE.name().toLowerCase(Locale.US));
    }

    public boolean G() {
        return this.E;
    }

    public void a(boolean z) {
        this.E = z;
    }

    public String H() {
        return a(this.G, "https://certificate.mobile.yandex.net/api/v1/pins");
    }

    public void r(String str) {
        this.G = str;
    }

    public synchronized boolean I() {
        boolean z = true;
        synchronized (this) {
            if (be.a(b(), c(), C())) {
                z = false;
            }
        }
        return z;
    }

    public synchronized boolean a(long j) {
        boolean z = false;
        synchronized (this) {
            if (I()) {
                long currentTimeMillis = (System.currentTimeMillis() / 1000) - j;
                if (currentTimeMillis <= 86400 && currentTimeMillis >= 0) {
                    z = true;
                }
            }
        }
        return z;
    }

    public void a(j jVar) {
        Context m = jVar.m();
        String b = jVar.l().b();
        CounterConfiguration j = jVar.j();
        DeviceInfo instance = DeviceInfo.getInstance(m);
        bm x = jVar.x();
        this.o = bg.a(m, j, b);
        this.u = a(m, j);
        List a = ba.a(m, ba.a(m).setPackage(b));
        a aVar = aw.a;
        if (!a.isEmpty()) {
            aVar = aw.a(ba.a(((ResolveInfo) a.get(0)).serviceInfo));
        }
        this.w = aVar.a;
        a(instance);
        a(x, jVar);
        a(jVar, x);
        b(x);
        String o = j.o();
        if (be.a(o)) {
            o = x();
            if (be.a(o)) {
                o = bg.b(m, b);
            }
        }
        k(o);
        o = j.p();
        if (be.a(o)) {
            o = z();
            if (be.a(o)) {
                o = bg.a(m, b);
            }
        }
        m(o);
        a(x);
        d(jVar);
    }

    private void a(bm bmVar) {
        a(bmVar.c());
    }

    private void b(bm bmVar) {
        this.F = bmVar.d(null);
    }

    public void b(j jVar) {
        a(DeviceInfo.getInstance(jVar.m()));
        b(jVar.x());
    }

    String a(Context context, CounterConfiguration counterConfiguration) {
        a e = counterConfiguration.e();
        return e == null ? b(context) : e.a();
    }

    String b(Context context) {
        return DeviceInfo.getInstance(context).deviceType;
    }

    void c(j jVar) {
        bm x = jVar.x();
        a(jVar, x);
        a(x, jVar);
        a(x);
    }

    private synchronized void a(j jVar, bm bmVar) {
        String c = c();
        if (be.a(c)) {
            c = jVar.j().h();
            if (be.a(c)) {
                c = br.a().a(jVar.m());
            }
        }
        b(c);
        c = jVar.j().g();
        if (be.a(c)) {
            c = b();
            if (be.a(c)) {
                c = bmVar.b("");
            }
        }
        a(c);
    }

    private void a(DeviceInfo deviceInfo) {
        this.n = deviceInfo.platformDeviceId;
        this.s = deviceInfo.screenDpi;
        this.t = deviceInfo.scaleFactor;
        int i = deviceInfo.screenWidth;
        int i2 = deviceInfo.screenHeight;
        this.q = Math.max(i, i2);
        this.r = Math.min(i, i2);
        this.v = deviceInfo.getLocale();
        this.D = deviceInfo.deviceRootStatus;
    }

    private void a(bm bmVar, j jVar) {
        this.B = bmVar.f("");
        this.C = bmVar.g("");
        this.z = bmVar.c("https://startup.mobile.yandex.net/");
        this.A = bmVar.e("");
        e(jVar);
    }

    public synchronized void a(a aVar) {
        a(aVar.g());
        b(aVar.f());
        o(aVar.b());
        n(aVar.c());
        p(aVar.d());
        r(aVar.e());
        l(aVar.h());
        a(aVar.a());
    }

    public synchronized void d(j jVar) {
        CounterConfiguration j = jVar.j();
        a(j.g());
        d(j.i());
        k(j.o());
        m(j.p());
        c(j.C());
        e(jVar);
    }

    private void e(j jVar) {
        String n = jVar.j().n();
        if (!be.a(n)) {
            if (!n.equals(this.z)) {
                this.A = null;
                cf.a(jVar.m(), jVar.l().b());
            }
            this.z = n;
        }
    }

    public String a(Context context) {
        return a(b.a.b(context), "");
    }

    public void k(String str) {
        if (!be.a(str)) {
            this.x = str;
        }
    }

    public void m(String str) {
        if (!be.a(str)) {
            this.y = str;
        }
    }

    private static String a(String str, String str2) {
        return !be.a(str) ? str : str2;
    }
}
