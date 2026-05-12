package com.chartboost.sdk.InPlay;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Model.CBError.CBClickError;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.c;
import com.chartboost.sdk.d;
import com.chartboost.sdk.f;
import com.chartboost.sdk.impl.aa;
import com.chartboost.sdk.impl.ad;
import com.chartboost.sdk.impl.af;
import com.chartboost.sdk.impl.v;
import com.chartboost.sdk.impl.x;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class a {
    private static final String a = a.class.getSimpleName();
    private final ArrayList<CBInPlay> b = new ArrayList();
    private final LinkedHashMap<String, Bitmap> c = new LinkedHashMap(4);
    private volatile boolean d = false;

    private class a implements v {
        protected String a;
        final /* synthetic */ a b;

        private a(a aVar) {
            this.b = aVar;
        }

        public void a(x xVar) {
            CBLogging.b(a.a, "Bitmap download failed " + xVar.a());
            if (c.h() != null) {
                c.h().didFailToLoadInPlay(this.a, CBImpressionError.NETWORK_FAILURE);
            }
        }
    }

    private class b implements aa<Bitmap> {
        protected boolean a;
        protected String b;
        protected CBInPlay c;
        final /* synthetic */ a d;

        private b(a aVar) {
            this.d = aVar;
        }

        public void a(Bitmap bitmap) {
            synchronized (this.d) {
                this.d.c.put(this.b, bitmap);
                this.d.a(this.c, this.b, this.a);
            }
        }
    }

    public synchronized void a(String str) {
        if (!(b() || this.d)) {
            a(str, true);
        }
    }

    private synchronized boolean b() {
        return this.b.size() == 4;
    }

    public synchronized boolean b(String str) {
        boolean z;
        if (this.b.size() > 0) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public synchronized CBInPlay c(String str) {
        CBInPlay cBInPlay;
        cBInPlay = null;
        if (this.b.size() > 0) {
            cBInPlay = (CBInPlay) this.b.get(0);
            this.b.remove(0);
        }
        if (!(b() || this.d)) {
            a(str, true);
        }
        if (cBInPlay == null && c.h() != null) {
            c.h().didFailToLoadInPlay(str, CBImpressionError.NO_AD_FOUND);
        }
        return cBInPlay;
    }

    private void a(final String str, final boolean z) {
        this.d = true;
        ad adVar = new ad("/inplay/get");
        adVar.a("raw", Boolean.valueOf(true));
        adVar.a("cache", Boolean.valueOf(true));
        adVar.a(com.chartboost.sdk.impl.w.b.HIGH);
        adVar.b(true);
        adVar.a("location", (Object) str);
        adVar.a(com.chartboost.sdk.Model.b.e);
        adVar.a(new ad.c(this) {
            final /* synthetic */ a c;

            public void a(com.chartboost.sdk.Libraries.e.a aVar, ad adVar) {
                this.c.d = false;
                if (aVar.c()) {
                    CBInPlay cBInPlay = new CBInPlay();
                    cBInPlay.a(aVar);
                    cBInPlay.b(aVar.e("name"));
                    if (!TextUtils.isEmpty(str)) {
                        cBInPlay.a(str);
                    }
                    com.chartboost.sdk.Libraries.e.a a = aVar.a("icons");
                    if (a.c() && !TextUtils.isEmpty(a.e("lg"))) {
                        String e = a.e("lg");
                        if (this.c.c.get(e) == null) {
                            aa bVar = new b();
                            v aVar2 = new a();
                            bVar.c = cBInPlay;
                            bVar.b = e;
                            bVar.a = z;
                            aVar2.a = str;
                            f.g().a(e, bVar, aVar2);
                            return;
                        }
                        this.c.a(cBInPlay, e, true);
                    }
                }
            }

            public void a(com.chartboost.sdk.Libraries.e.a aVar, ad adVar, CBError cBError) {
                CBLogging.b(a.a, "InPlay cache call failed" + cBError);
                this.c.d = false;
                if (c.h() != null) {
                    c.h().didFailToLoadInPlay(str, cBError != null ? cBError.c() : null);
                }
            }
        });
    }

    private synchronized void a(CBInPlay cBInPlay, String str, boolean z) {
        cBInPlay.a((Bitmap) this.c.get(str));
        this.b.add(cBInPlay);
        com.chartboost.sdk.a h = c.h();
        if (h != null && z) {
            h.didCacheInPlay(cBInPlay.getLocation());
        }
        if (!(b() || this.d)) {
            a(cBInPlay.getLocation(), false);
        }
    }

    protected static void a(CBInPlay cBInPlay) {
        Object a = cBInPlay.a();
        ad adVar = new ad("/inplay/show");
        adVar.a("inplay-dictionary", a);
        adVar.a("location", cBInPlay.getLocation());
        adVar.a(true);
        adVar.t();
    }

    protected static void b(final CBInPlay cBInPlay) {
        String str;
        final com.chartboost.sdk.Libraries.e.a a = cBInPlay.a();
        if (a != null) {
            String e = a.e("link");
            String e2 = a.e("deep-link");
            if (!TextUtils.isEmpty(e2)) {
                try {
                    if (!af.a(e2)) {
                        e2 = e;
                    }
                    str = e2;
                } catch (Exception e3) {
                    CBLogging.b(a, "Cannot open a url");
                }
            }
            str = e;
        } else {
            str = null;
        }
        final d c = f.c();
        com.chartboost.sdk.d.a anonymousClass2 = new com.chartboost.sdk.d.a() {
            public void a() {
                ad d = c.d();
                d.a("location", cBInPlay.getLocation());
                d.a("to", a);
                d.a("cgn", a);
                d.a("creative", a);
                d.a("ad_id", a);
                d.a("type", a);
                d.a("more_type", a);
                d.a(true);
                d.t();
            }
        };
        if (TextUtils.isEmpty(str)) {
            c.a().a(null, false, str, CBClickError.URI_INVALID, anonymousClass2);
        } else {
            c.a(null, str, anonymousClass2);
        }
    }
}
