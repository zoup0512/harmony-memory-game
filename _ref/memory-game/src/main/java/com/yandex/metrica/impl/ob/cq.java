package com.yandex.metrica.impl.ob;

import android.os.Bundle;
import android.text.TextUtils;
import com.yandex.metrica.IIdentifierCallback;
import com.yandex.metrica.impl.be;
import java.util.Map;

public class cq {
    private String a = this.j.a(null);
    private String b = this.j.b(null);
    private String c = this.j.c(null);
    private String d = this.j.d(null);
    private String e = this.j.p(null);
    private String f = this.j.e(null);
    private long g = this.j.a(0);
    private String h = this.j.f(null);
    private String i = this.j.n(null);
    private final bi j;

    public enum a {
        IDENTIFIERS,
        URLS,
        ALL
    }

    public cq(bi biVar, String str) {
        this.j = biVar;
        c(str);
        e();
    }

    private void c(String str) {
        if (be.a(this.a) && !be.a(str)) {
            this.a = str;
        }
    }

    synchronized void a(Map<String, String> map) {
        b((Map) map);
        c((Map) map);
    }

    synchronized boolean a(a aVar) {
        boolean h;
        if (a.ALL == aVar) {
            h = h();
        } else if (a.IDENTIFIERS == aVar) {
            h = f();
        } else if (a.URLS == aVar) {
            h = g();
        } else {
            h = false;
        }
        return h;
    }

    synchronized void b(Map<String, String> map) {
        if (!be.a(this.a)) {
            map.put(IIdentifierCallback.YANDEX_MOBILE_METRICA_UUID, this.a);
        }
        if (!be.a(this.b)) {
            map.put(IIdentifierCallback.YANDEX_MOBILE_METRICA_DEVICE_ID, this.b);
        }
    }

    synchronized void c(Map<String, String> map) {
        if (!be.a(this.c)) {
            map.put(IIdentifierCallback.YANDEX_MOBILE_METRICA_GET_AD_URL, this.c);
        }
        if (!be.a(this.d)) {
            map.put(IIdentifierCallback.YANDEX_MOBILE_METRICA_REPORT_AD_URL, this.d);
        }
    }

    synchronized void a(Bundle bundle) {
        b(bundle);
        c(bundle);
        b(bundle.getLong("ServerTimeOffset"));
        String string = bundle.getString("Clids");
        if (!be.a(string)) {
            this.h = string;
        }
        Object string2 = bundle.getString("CookieBrowsers");
        if (!TextUtils.isEmpty(string2)) {
            this.i = string2;
        }
        e();
    }

    private void e() {
        this.j.g(this.a).h(this.b).i(this.c).k(this.d).d(this.g).l(this.h).o(this.i).q(this.e).h();
    }

    void a(long j) {
        this.j.e(j).h();
    }

    String b() {
        return this.f;
    }

    void a(String str) {
        this.f = str;
        this.j.j(str).h();
    }

    private synchronized boolean f() {
        boolean z = true;
        synchronized (this) {
            if (be.a(this.a, this.b)) {
                z = false;
            }
        }
        return z;
    }

    private synchronized boolean g() {
        boolean z = true;
        synchronized (this) {
            if (be.a(this.c)) {
                z = false;
            }
        }
        return z;
    }

    private synchronized boolean h() {
        boolean z;
        z = f() && g();
        return z;
    }

    private synchronized void b(Bundle bundle) {
        c(bundle.getString("UuId"));
        String string = bundle.getString("DeviceId");
        if (!be.a(string)) {
            b(string);
        }
    }

    private synchronized void c(Bundle bundle) {
        Object string = bundle.getString("AdUrlGet");
        if (!TextUtils.isEmpty(string)) {
            d(string);
        }
        string = bundle.getString("AdUrlReport");
        if (!TextUtils.isEmpty(string)) {
            e(string);
        }
        string = bundle.getString("BindIdUrl");
        if (!TextUtils.isEmpty(string)) {
            f(string);
        }
    }

    synchronized void b(String str) {
        this.b = str;
    }

    private synchronized void d(String str) {
        this.c = str;
    }

    private synchronized void e(String str) {
        this.d = str;
    }

    private synchronized void f(String str) {
        this.e = str;
    }

    private synchronized void b(long j) {
        this.g = j;
    }

    String c() {
        return this.a;
    }

    String d() {
        return this.b;
    }

    boolean a() {
        long currentTimeMillis = (System.currentTimeMillis() / 1000) - this.j.b(0);
        return currentTimeMillis > 86400 || currentTimeMillis < 0;
    }
}
