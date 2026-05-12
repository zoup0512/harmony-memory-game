package com.chartboost.sdk;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.amazonaws.services.s3.internal.Constants;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Model.a.b;
import com.chartboost.sdk.impl.ad;
import com.chartboost.sdk.impl.ad.c;
import com.chartboost.sdk.impl.ad.d;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.AnalyticsEvents;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public abstract class e {
    protected final Handler a = CBUtility.c();
    public ConcurrentHashMap<String, com.chartboost.sdk.Model.a> b = new ConcurrentHashMap();
    public b c = b.NATIVE;
    private final Map<String, com.chartboost.sdk.Model.a> d = new HashMap();
    private final ConcurrentHashMap<String, com.chartboost.sdk.Model.a> e = new ConcurrentHashMap();
    private final ConcurrentHashMap<String, com.chartboost.sdk.Model.a> f = new ConcurrentHashMap();
    private a g = null;

    protected interface a {
        void a(com.chartboost.sdk.Model.a aVar);

        void a(com.chartboost.sdk.Model.a aVar, CBImpressionError cBImpressionError);

        void b(com.chartboost.sdk.Model.a aVar);

        void c(com.chartboost.sdk.Model.a aVar);

        void d(com.chartboost.sdk.Model.a aVar);

        void e(com.chartboost.sdk.Model.a aVar);

        boolean f(com.chartboost.sdk.Model.a aVar);

        boolean g(com.chartboost.sdk.Model.a aVar);

        boolean h(com.chartboost.sdk.Model.a aVar);
    }

    protected abstract com.chartboost.sdk.Model.a a(String str, boolean z);

    protected abstract a b();

    protected abstract ad e(com.chartboost.sdk.Model.a aVar);

    public abstract String e();

    protected abstract ad l(com.chartboost.sdk.Model.a aVar);

    public void a(String str) {
        com.chartboost.sdk.Model.a aVar;
        final com.chartboost.sdk.Model.a aVar2 = (com.chartboost.sdk.Model.a) this.e.get(str);
        final com.chartboost.sdk.Model.a aVar3 = (com.chartboost.sdk.Model.a) this.b.get(str);
        if (aVar3 != null) {
            aVar = aVar3;
        } else if (aVar2 != null) {
            aVar = aVar2;
        } else {
            aVar = c(str);
            if (aVar != null) {
                aVar.k = true;
                CBLogging.b(getClass().getSimpleName(), String.format("%s %s", new Object[]{"Request already in process for impression with location", str}));
                return;
            }
            aVar = a(str, false);
        }
        g h = f.q().h();
        if (h == null || !h.c()) {
            if (!b(aVar)) {
                this.a.post(new Runnable(this) {
                    final /* synthetic */ e d;

                    public void run() {
                        if (aVar2 != null) {
                            if (aVar2.c == com.chartboost.sdk.Model.a.e.NONE) {
                                aVar2.c = com.chartboost.sdk.Model.a.e.CACHED;
                            }
                            this.d.g(aVar2);
                        } else if (aVar3 == null || !aVar3.w().c()) {
                            this.d.c(aVar);
                        } else {
                            aVar3.a(aVar3.w(), f.c().a);
                        }
                    }
                });
            }
        } else if (a() != null) {
            a().a(aVar, CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
        }
    }

    public void b(String str) {
        com.chartboost.sdk.Model.a aVar = (com.chartboost.sdk.Model.a) this.e.get(str);
        com.chartboost.sdk.Model.a aVar2 = (com.chartboost.sdk.Model.a) this.b.get(str);
        if (aVar2 != null) {
            a().d(aVar2);
        } else if (aVar != null) {
            a().d(aVar);
        } else if (c(str) != null) {
            CBLogging.b(getClass().getSimpleName(), String.format("%s %s", new Object[]{"Request already in process for impression with location", str}));
        } else {
            aVar = a(str, true);
            if (!b(aVar)) {
                c(aVar);
            }
        }
    }

    protected void a(com.chartboost.sdk.Model.a aVar) {
        p(aVar);
        a().d(aVar);
        aVar.c = com.chartboost.sdk.Model.a.e.CACHED;
    }

    protected final boolean b(com.chartboost.sdk.Model.a aVar) {
        if (a().h(aVar) || f.p().getInt("cbPrefSessionCount", 0) != 1) {
            return false;
        }
        a(aVar, CBImpressionError.FIRST_SESSION_INTERSTITIALS_DISABLED);
        return true;
    }

    protected void c(com.chartboost.sdk.Model.a aVar) {
        if (f(aVar) && a().g(aVar)) {
            if (!aVar.l && aVar.d == com.chartboost.sdk.Model.a.a.MORE_APPS && c.v()) {
                aVar.o = true;
                f.q().a(aVar);
            }
            if (d(aVar)) {
                ad e = e(aVar);
                if (e != null) {
                    a(e, aVar);
                    o(aVar);
                    com.chartboost.sdk.Tracking.a.a(e(), aVar.e, aVar.p(), aVar.l);
                }
            }
        }
    }

    protected boolean d(com.chartboost.sdk.Model.a aVar) {
        return true;
    }

    protected void a(final com.chartboost.sdk.Model.a aVar, final CBImpressionError cBImpressionError) {
        Chartboost.a(new Runnable(this) {
            final /* synthetic */ e c;

            public void run() {
                this.c.n(aVar);
                g h = f.q().h();
                if (h != null && h.b()) {
                    h.a(aVar, true);
                } else if (aVar.c == com.chartboost.sdk.Model.a.e.DISPLAYED && h != null) {
                    h.b(aVar);
                }
                this.c.a().a(aVar, cBImpressionError);
                String p = aVar.p();
                com.chartboost.sdk.Tracking.a k = f.k();
                String e = aVar.q().e();
                String str = aVar.e;
                if (TextUtils.isEmpty(p)) {
                    p = "";
                }
                k.a(e, str, p, cBImpressionError);
            }
        });
    }

    protected final boolean f(com.chartboost.sdk.Model.a aVar) {
        if (c.q()) {
            g h = f.q().h();
            if (aVar.l || h == null || !h.c()) {
                if (f.h().b()) {
                    return true;
                }
                a(aVar, CBImpressionError.INTERNET_UNAVAILABLE);
                return false;
            } else if (a() == null) {
                return false;
            } else {
                a().a(aVar, CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
                return false;
            }
        }
        a(aVar, CBImpressionError.SESSION_NOT_STARTED);
        return false;
    }

    protected void g(com.chartboost.sdk.Model.a aVar) {
        boolean z = aVar.c != com.chartboost.sdk.Model.a.e.DISPLAYED;
        if (z) {
            if (c.b() != null && c.b().doesWrapperUseCustomShouldDisplayBehavior()) {
                this.f.put(aVar.e == null ? "" : aVar.e, aVar);
            }
            if (!a().f(aVar)) {
                return;
            }
        }
        a(aVar, z);
    }

    protected void b(String str, boolean z) {
        if (str == null) {
            str = "";
        }
        com.chartboost.sdk.Model.a aVar = (com.chartboost.sdk.Model.a) this.f.get(str);
        if (aVar != null) {
            this.f.remove(str);
            if (z) {
                a(aVar, true);
            }
        }
    }

    private void a(com.chartboost.sdk.Model.a aVar, boolean z) {
        boolean z2 = aVar.c == com.chartboost.sdk.Model.a.e.CACHED;
        i(aVar);
        g h = f.q().h();
        if (h != null) {
            if (h.b()) {
                h.a(aVar, false);
            } else if (!(!aVar.o || z2 || aVar.c == com.chartboost.sdk.Model.a.e.DISPLAYED)) {
                return;
            }
        }
        if (z) {
            h(aVar);
        } else {
            f.q().a(aVar);
        }
    }

    protected void h(com.chartboost.sdk.Model.a aVar) {
        f.q().a(aVar);
    }

    protected void i(com.chartboost.sdk.Model.a aVar) {
        j(aVar);
    }

    public void j(com.chartboost.sdk.Model.a aVar) {
        if (!aVar.m) {
            aVar.m = true;
            aVar.l = false;
            k(aVar);
            this.b.remove(aVar.e);
            if (e(aVar.e) == aVar) {
                f(aVar.e);
            }
        }
    }

    protected void k(final com.chartboost.sdk.Model.a aVar) {
        ad l = l(aVar);
        l.a(true);
        if (aVar.l) {
            l.a("cached", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        } else {
            l.a("cached", AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        Object e = aVar.w().e("ad_id");
        if (e != null) {
            l.a("ad_id", e);
        }
        l.a("location", aVar.e);
        l.a(new d(this) {
            final /* synthetic */ e b;

            public void a(com.chartboost.sdk.Libraries.e.a aVar, ad adVar) {
                if (c.k() && !this.b.d(aVar.e)) {
                    this.b.b(aVar.e);
                }
            }
        });
        com.chartboost.sdk.Tracking.a.a(e(), aVar.e, aVar.p());
    }

    protected final boolean m(com.chartboost.sdk.Model.a aVar) {
        return TimeUnit.MILLISECONDS.toSeconds(new Date().getTime() - aVar.b.getTime()) >= 86400;
    }

    protected void a(com.chartboost.sdk.Model.a aVar, com.chartboost.sdk.Libraries.e.a aVar2) {
        if (aVar2.f("status") == Constants.NO_SUCH_BUCKET_STATUS_CODE) {
            CBLogging.b(aVar.d, "Invalid status code" + aVar2.a("status"));
            a(aVar, CBImpressionError.NO_AD_FOUND);
        } else if (aVar2.f("status") != 200) {
            CBLogging.b(aVar.d, "Invalid status code" + aVar2.a("status"));
            a(aVar, CBImpressionError.INVALID_RESPONSE);
        } else {
            aVar.a(aVar2, f.c().a);
        }
    }

    protected final void a(ad adVar, final com.chartboost.sdk.Model.a aVar) {
        aVar.x = true;
        adVar.a(new c(this) {
            final /* synthetic */ e b;

            public void a(final com.chartboost.sdk.Libraries.e.a aVar, ad adVar) {
                Chartboost.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass4 b;

                    public void run() {
                        try {
                            if (aVar.c() && aVar.f("status") != 200) {
                                CBLogging.b(aVar.d, "Invalid status code" + aVar.a("status"));
                                this.b.b.a(aVar, CBImpressionError.NO_AD_FOUND);
                                f.k().a(this.b.b.e(), aVar.e, "Status Code: " + aVar.f("status"), CBImpressionError.NO_AD_FOUND);
                            } else if (aVar.c()) {
                                aVar.x = false;
                                CharSequence e = aVar.e("type");
                                if (com.chartboost.sdk.impl.a.a().a(e) || !e.equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE)) {
                                    this.b.b.a(aVar, b.WEB);
                                } else {
                                    this.b.b.a(aVar, b.NATIVE);
                                }
                                this.b.b.a(aVar, aVar);
                            } else {
                                f.k().a(this.b.b.e(), aVar.e, "Status Code: " + aVar.f("status"), CBImpressionError.INVALID_RESPONSE);
                                this.b.b.a(aVar, CBImpressionError.INVALID_RESPONSE);
                            }
                        } catch (Exception e2) {
                            com.chartboost.sdk.Tracking.a.a(e.class, "sendRequest onSuccess", e2);
                            this.b.b.a(aVar, CBImpressionError.INVALID_RESPONSE);
                        }
                    }
                });
            }

            public void a(com.chartboost.sdk.Libraries.e.a aVar, final ad adVar, final CBError cBError) {
                Chartboost.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass4 c;

                    public void run() {
                        aVar.x = false;
                        String str = "network failure";
                        String str2 = "request %s failed with error %s: %s";
                        Object[] objArr = new Object[3];
                        objArr[0] = adVar.h();
                        objArr[1] = cBError.a().name();
                        objArr[2] = cBError.b() != null ? cBError.b() : "";
                        CBLogging.d(str, String.format(str2, objArr));
                        this.c.b.a(aVar, cBError.c());
                    }
                });
            }
        });
    }

    protected com.chartboost.sdk.Model.a c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return (com.chartboost.sdk.Model.a) this.d.get(str);
    }

    protected void n(com.chartboost.sdk.Model.a aVar) {
        if (aVar != null) {
            this.d.remove(aVar.e);
        }
    }

    protected void o(com.chartboost.sdk.Model.a aVar) {
        if (aVar != null) {
            aVar.j = System.currentTimeMillis();
            this.d.put(aVar.e, aVar);
        }
    }

    public boolean d(String str) {
        return e(str) != null;
    }

    protected com.chartboost.sdk.Model.a e(String str) {
        com.chartboost.sdk.Model.a aVar = (com.chartboost.sdk.Model.a) this.e.get(str);
        if (aVar != null && !m(aVar)) {
            return aVar;
        }
        if (!this.b.isEmpty() && this.b.containsKey(str)) {
            aVar = (com.chartboost.sdk.Model.a) this.b.get(str);
            if (!(aVar == null || m(aVar))) {
                return aVar;
            }
        }
        return null;
    }

    protected void f(String str) {
        CBLogging.a(getClass().getSimpleName(), "##### Removing impression-> " + e() + " at location" + str);
        this.e.remove(str);
    }

    protected void p(com.chartboost.sdk.Model.a aVar) {
        CBLogging.a(getClass().getSimpleName(), "##### Adding aimpression-> " + e() + " at location" + aVar.e);
        CBLogging.a(getClass().getSimpleName(), "##### Impression should cache:" + aVar.l);
        this.e.put(aVar.e, aVar);
    }

    protected final a a() {
        if (this.g == null) {
            this.g = b();
        }
        return this.g;
    }

    public void c() {
        if (!(this.b == null || this.b.isEmpty())) {
            CBLogging.a(getClass().getSimpleName(), "###### Invalidate Cached Impression for webview");
            for (com.chartboost.sdk.Model.a aVar : this.b.values()) {
                o(aVar);
                this.b.remove(aVar.e);
                a(aVar, aVar.w());
            }
        }
        if (!(this.e == null || this.e.isEmpty())) {
            for (com.chartboost.sdk.Model.a aVar2 : this.e.values()) {
                o(aVar2);
                a(aVar2, aVar2.w());
                this.e.remove(aVar2.e);
            }
        }
        if (this.f != null && !this.f.isEmpty()) {
            for (com.chartboost.sdk.Model.a aVar22 : this.f.values()) {
                o(aVar22);
                a(aVar22, aVar22.w());
                this.f.remove(aVar22.e);
            }
        }
    }

    protected Context d() {
        Chartboost q = f.q();
        if (q != null) {
            return q.getValidContext();
        }
        return c.x();
    }

    public void q(com.chartboost.sdk.Model.a aVar) {
    }

    public b f() {
        return this.c;
    }

    public void a(com.chartboost.sdk.Model.a aVar, b bVar) {
        if (aVar != null) {
            aVar.a = bVar;
        }
        this.c = bVar;
    }

    public void g() {
        CBLogging.a(getClass(), "Invalidating any pending impressions for " + getClass());
        long toMillis = TimeUnit.MINUTES.toMillis((long) c.E());
        long currentTimeMillis = System.currentTimeMillis();
        if (this.d != null && !this.d.isEmpty()) {
            Iterator it = this.d.entrySet().iterator();
            while (it.hasNext()) {
                com.chartboost.sdk.Model.a aVar = (com.chartboost.sdk.Model.a) ((Entry) it.next()).getValue();
                if (currentTimeMillis - aVar.j > toMillis) {
                    aVar.a(CBImpressionError.PENDING_IMPRESSION_ERROR);
                    CBLogging.a(this, "Expired pending impression in cache, removing the impression for " + getClass());
                    f.k().a(e(), aVar.e, aVar.p(), "Pending impression still in cache, so removing them", false);
                    it.remove();
                }
            }
        }
    }
}
