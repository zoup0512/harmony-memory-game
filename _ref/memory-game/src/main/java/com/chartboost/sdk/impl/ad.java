package com.chartboost.sdk.impl;

import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.Libraries.g;
import com.chartboost.sdk.Libraries.g.k;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.f;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ad {
    private static Map<String, Object> f;
    protected com.chartboost.sdk.Libraries.e.a a;
    private String b;
    private String c;
    private Map<String, Object> d;
    private Map<String, Object> e;
    private String g;
    private c h = null;
    private boolean i = false;
    private boolean j = false;
    private com.chartboost.sdk.Libraries.g.a k = null;
    private final ae l;
    private int m;
    private boolean n = false;
    private boolean o = true;
    private com.chartboost.sdk.impl.w.b p = com.chartboost.sdk.impl.w.b.NORMAL;

    public interface c {
        void a(com.chartboost.sdk.Libraries.e.a aVar, ad adVar);

        void a(com.chartboost.sdk.Libraries.e.a aVar, ad adVar, CBError cBError);
    }

    public static abstract class d implements c {
        public void a(com.chartboost.sdk.Libraries.e.a aVar, ad adVar, CBError cBError) {
        }
    }

    private static class a implements c {
        private final d a;
        private final b b;

        public a(d dVar, b bVar) {
            this.a = dVar;
            this.b = bVar;
        }

        public void a(com.chartboost.sdk.Libraries.e.a aVar, ad adVar) {
            if (this.a != null) {
                this.a.a(aVar, adVar);
            }
        }

        public void a(com.chartboost.sdk.Libraries.e.a aVar, ad adVar, CBError cBError) {
            if (this.b != null) {
                this.b.a(aVar, adVar, cBError);
            }
        }
    }

    public static abstract class b implements c {
    }

    public ad(String str) {
        this.b = str;
        this.g = HttpRequest.METHOD_POST;
        this.l = f.b();
        a(0);
    }

    protected void a() {
        if (this.e == null) {
            this.e = new HashMap();
        }
        this.e.put("Accept", "application/json");
        this.e.put("X-Chartboost-Client", CBUtility.b());
        this.e.put("X-Chartboost-API", "6.5.1");
        this.e.put("X-Chartboost-Client", CBUtility.b());
    }

    public static Map<String, Object> b() {
        if (f == null) {
            f = new HashMap();
            f.put("X-Chartboost-Client", CBUtility.b());
            f.put("X-Chartboost-App", com.chartboost.sdk.c.f());
        }
        f.put("X-Chartboost-Reachability", Integer.valueOf(f.h().a()));
        return f;
    }

    protected String c() {
        return "application/json";
    }

    public void a(String str, Object obj) {
        if (this.a == null) {
            this.a = com.chartboost.sdk.Libraries.e.a.a();
        }
        this.a.a(str, obj);
    }

    public void a(String str, String str2) {
        if (this.e == null) {
            this.e = new HashMap();
        }
        this.e.put(str, str2);
    }

    public void a(String str, com.chartboost.sdk.Libraries.e.a aVar) {
        if (aVar != null && aVar.c(str)) {
            a(str, aVar.e(str));
        }
    }

    protected void d() {
        int i = 0;
        as i2 = f.i();
        a(SettingsJsonConstants.APP_KEY, i2.o);
        a("model", i2.a);
        a("device_type", i2.p);
        a("os", i2.b);
        a("country", i2.c);
        a("language", i2.d);
        a("sdk", i2.g);
        a("timestamp", i2.m);
        a(SettingsJsonConstants.SESSION_KEY, Integer.valueOf(f.p().getInt("cbPrefSessionCount", 0)));
        a("reachability", Integer.valueOf(f.h().a()));
        a("scale", i2.n);
        String str = "is_portrait";
        if (CBUtility.a().a()) {
            i = 1;
        }
        a(str, Integer.valueOf(i));
        a("bundle", i2.e);
        a("bundle_id", i2.f);
        a("carrier", i2.q);
        a("custom_id", com.chartboost.sdk.c.p());
        a("mediation", com.chartboost.sdk.c.e());
        if (com.chartboost.sdk.c.b() != null) {
            a("framework_version", com.chartboost.sdk.c.c());
            a("wrapper_version", com.chartboost.sdk.c.d());
        }
        a("rooted_device", Boolean.valueOf(i2.r));
        a("timezone", i2.s);
        a("mobile_network", i2.t);
        a("dw", i2.j);
        a("dh", i2.k);
        a("dpi", i2.l);
        a("w", i2.h);
        a("h", i2.i);
        a("identity", com.chartboost.sdk.Libraries.c.b());
        a("commit_hash", (Object) "2c21bbaaeeb65c0ecc688dee8b3bfeb4fbf1916b");
        com.chartboost.sdk.Libraries.c.a c = com.chartboost.sdk.Libraries.c.c();
        if (c.b()) {
            a("tracking", Integer.valueOf(c.a()));
        }
        Object T = com.chartboost.sdk.c.T();
        if (!a.a().a((CharSequence) T)) {
            a("config_variant", T);
        }
    }

    public void e() {
        String f = com.chartboost.sdk.c.f();
        String g = com.chartboost.sdk.c.g();
        g = com.chartboost.sdk.Libraries.b.b(com.chartboost.sdk.Libraries.b.a(String.format(Locale.US, "%s %s\n%s\n%s", new Object[]{this.g, f(), g, g()}).getBytes()));
        a("X-Chartboost-App", f);
        a("X-Chartboost-Signature", g);
    }

    public String f() {
        return h() + CBUtility.a(this.d);
    }

    public String g() {
        return this.a.toString();
    }

    public String h() {
        if (this.b == null) {
            return "/";
        }
        return (this.b.startsWith("/") ? "" : "/") + this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public boolean i() {
        return h().equals("/api/track");
    }

    public com.chartboost.sdk.Libraries.e.a j() {
        return this.a;
    }

    public Map<String, Object> k() {
        return this.e;
    }

    public boolean l() {
        return this.j;
    }

    public void a(boolean z) {
        this.j = z;
    }

    public com.chartboost.sdk.Libraries.g.a m() {
        return this.k;
    }

    public boolean n() {
        return this.n;
    }

    public void b(boolean z) {
        this.n = z;
    }

    public void a(com.chartboost.sdk.Libraries.g.a aVar) {
        if (!g.c(aVar)) {
            CBLogging.b("CBRequest", "Validation predicate must be a dictionary style -- either VDictionary, VDictionaryExact, VDictionaryWithValues, or just a list of KV pairs.");
        }
        this.k = aVar;
    }

    public void a(k... kVarArr) {
        this.k = g.a(kVarArr);
    }

    public void b(String str) {
        this.c = str;
    }

    public void a(com.chartboost.sdk.impl.w.b bVar) {
        this.p = bVar;
    }

    public com.chartboost.sdk.impl.w.b o() {
        return this.p;
    }

    public int p() {
        return this.m;
    }

    public void a(int i) {
        this.m = i;
    }

    public boolean q() {
        return this.o;
    }

    public void c(boolean z) {
        this.o = z;
    }

    public boolean r() {
        return this.i;
    }

    public void d(boolean z) {
        this.i = z;
    }

    public c s() {
        return this.h;
    }

    public void t() {
        a(null, null);
    }

    public void a(c cVar) {
        if (!com.chartboost.sdk.c.n()) {
            this.j = false;
            this.n = false;
        }
        this.h = cVar;
        d(true);
        this.l.a(this, cVar);
    }

    public void a(d dVar, b bVar) {
        if (!com.chartboost.sdk.c.n()) {
            this.j = false;
            this.n = false;
        }
        d(true);
        this.h = new a(dVar, bVar);
        this.l.a(this, this.h);
    }

    public static ad a(com.chartboost.sdk.Libraries.e.a aVar) {
        try {
            ad adVar = new ad(aVar.e("path"));
            adVar.g = aVar.e("method");
            adVar.d = aVar.a("query").f();
            adVar.a = aVar.a("body");
            adVar.e = aVar.a("headers").f();
            adVar.j = aVar.j("ensureDelivery");
            adVar.c = aVar.e("eventType");
            adVar.b = aVar.e("path");
            adVar.m = aVar.f("retryCount");
            if (aVar.a("callback") instanceof c) {
                adVar.h = (c) aVar.a("callback");
            }
            return adVar;
        } catch (Exception e) {
            CBLogging.d("CBRequest", "Unable to deserialize failed request", e);
            com.chartboost.sdk.Tracking.a.a(ad.class, "deserialize", e);
            return null;
        }
    }

    public com.chartboost.sdk.Libraries.e.a u() {
        return e.a(e.a("path", this.b), e.a("method", this.g), e.a("query", e.a(this.d)), e.a("body", this.a), e.a("eventType", this.c), e.a("headers", e.a(this.e)), e.a("ensureDelivery", Boolean.valueOf(this.j)), e.a("retryCount", Integer.valueOf(this.m)), e.a("callback", this.h));
    }
}
