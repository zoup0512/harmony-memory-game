package com.cmcm.picks.vastvideo;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.cmcm.adsdk.Const;
import com.cmcm.adsdk.utils.BackgroundHandler;
import com.cmcm.picks.loader.Ad;
import com.cmcm.picks.market.MarketUtils;
import com.cmcm.utils.Commons;
import com.cmcm.utils.ReportFactory;
import com.cmcm.utils.g;
import com.cmcm.utils.h;
import com.mopub.mobileads.VastIconXmlManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: VastAgent */
public class a {
    private int a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private VastModel j;
    private Ad k;
    private String l = "";
    private boolean m = true;
    private boolean n;
    private boolean o = true;
    private boolean p;
    private boolean q = false;
    private VastVideoProgressListener r;
    private boolean s;

    public a(VastModel vastModel) {
        this.j = vastModel;
        if (vastModel == null) {
            throw new IllegalStateException("the vast model is null, please check");
        }
        this.k = vastModel.getAd();
        if (this.k != null) {
            this.l = this.k.getPosid();
        }
    }

    public void a(boolean z) {
        if (!z) {
            this.j = null;
            this.s = false;
            this.m = true;
        }
        this.a = 0;
        this.b = false;
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = false;
        this.p = false;
        this.n = false;
        this.q = false;
    }

    public VastModel a() {
        return this.j;
    }

    public int b() {
        return this.a;
    }

    public void a(int i) {
        this.a = i;
    }

    public boolean c() {
        return this.s;
    }

    public void b(boolean z) {
        this.s = z;
    }

    public boolean d() {
        return this.b;
    }

    public void a(boolean z, int i, boolean z2) {
        if (!this.b) {
            this.b = z;
            if (this.b && z2) {
                b(i);
            }
        }
    }

    public boolean e() {
        return this.c;
    }

    public void c(boolean z) {
        this.c = z;
    }

    public boolean f() {
        return this.m;
    }

    public void a(boolean z, boolean z2, int i, int i2) {
        this.m = z;
        if (!z2) {
            return;
        }
        if (this.m) {
            i(i, i2);
        } else {
            j(i, i2);
        }
    }

    public boolean g() {
        return this.o;
    }

    public void d(boolean z) {
        this.o = z;
    }

    public boolean h() {
        return this.n;
    }

    public void e(boolean z) {
        this.n = z;
    }

    public void a(VastVideoProgressListener vastVideoProgressListener) {
        this.r = vastVideoProgressListener;
    }

    public void i() {
        this.r = null;
    }

    public boolean j() {
        return this.p;
    }

    public void f(boolean z) {
        this.p = z;
    }

    public boolean k() {
        return this.q;
    }

    public void g(boolean z) {
        this.q = z;
    }

    public void a(int i, int i2) {
        List l = this.j.l();
        if (l != null && l.size() > 0) {
            BackgroundHandler.executeAsyncTask(new d(l), new Void[0]);
        }
        a(4, i, i2);
    }

    public void b(int i, int i2) {
        List o = this.j.o();
        if (o != null && o.size() > 0) {
            BackgroundHandler.executeAsyncTask(new d(o), new Void[0]);
        }
        a(11, i, i2);
    }

    public void c(int i, int i2) {
        if (!this.d) {
            List e = this.j.e();
            if (e != null && e.size() > 0) {
                BackgroundHandler.executeAsyncTask(new d(e), new Void[0]);
            }
            a(0, i2, i);
            this.d = true;
        }
    }

    public void d(int i, int i2) {
        if (!this.i) {
            if (i2 == 0) {
                List f = this.j.f();
                if (f != null && f.size() > 0) {
                    if (!this.d) {
                        c(i, 0);
                    }
                    BackgroundHandler.executeAsyncTask(new d(f), new Void[0]);
                }
                f = this.j.d();
                if (f != null && f.size() > 0) {
                    BackgroundHandler.executeAsyncTask(new d(f), new Void[0]);
                }
            }
            ReportFactory.report("view", this.k, this.l, "");
            a(1, i2, i);
            this.i = true;
            if (this.r != null) {
                this.r.onVastVideoShow();
            }
        }
    }

    public void e(int i, int i2) {
        if (!this.e) {
            List g = this.j.g();
            if (g != null && g.size() > 0) {
                if (!this.i) {
                    d(i, 0);
                }
                BackgroundHandler.executeAsyncTask(new d(g), new Void[0]);
            }
            a(5, i2, i);
            this.e = true;
        }
    }

    public void f(int i, int i2) {
        if (!this.f) {
            List h = this.j.h();
            if (h != null && h.size() > 0) {
                if (!this.e) {
                    e(i, (int) (((float) i) * 0.25f));
                }
                BackgroundHandler.executeAsyncTask(new d(h), new Void[0]);
            }
            a(6, i2, i);
            this.f = true;
        }
    }

    public void g(int i, int i2) {
        if (!this.g) {
            List i3 = this.j.i();
            if (i3 != null && i3.size() > 0) {
                if (!this.f) {
                    f(i, (int) (((float) i) * 0.5f));
                }
                BackgroundHandler.executeAsyncTask(new d(i3), new Void[0]);
            }
            a(7, i2, i);
            this.g = true;
        }
    }

    public void b(int i) {
        if (!this.h) {
            List j = this.j.j();
            if (j != null && j.size() > 0) {
                if (!this.g) {
                    g(i, (int) (((float) i) * 0.75f));
                }
                BackgroundHandler.executeAsyncTask(new d(j), new Void[0]);
            }
            a(2, i, i);
            this.h = true;
            if (this.r != null) {
                this.r.onVastVideoComplete();
            }
        }
    }

    public void l() {
        List c = this.j.c();
        if (c != null && c.size() > 0) {
            BackgroundHandler.executeAsyncTask(new d(c), new Void[0]);
        }
    }

    public static void a(VastModel vastModel) {
        if (vastModel != null) {
            List c = vastModel.c();
            if (c != null && c.size() > 0) {
                BackgroundHandler.executeAsyncTask(new d(c), new Void[0]);
            }
        }
    }

    public void a(int i, int i2, int i3) {
        if (i3 >= 0) {
            Map hashMap = new HashMap();
            hashMap.put(VastIconXmlManager.DURATION, String.valueOf(i3));
            hashMap.put("playtime", String.valueOf(i2));
            hashMap.put("event", String.valueOf(i));
            ReportFactory.report(ReportFactory.VAST_PLAY, this.k, this.l, null, hashMap);
        }
    }

    public void h(int i, int i2) {
        if (this.r != null) {
            this.r.onVastVideoClick();
        }
        List s = this.j.s();
        if (s != null && s.size() > 0) {
            BackgroundHandler.executeAsyncTask(new d(s), new Void[0]);
            Map hashMap = new HashMap();
            hashMap.put(VastIconXmlManager.DURATION, String.valueOf(i2));
            hashMap.put("playtime", String.valueOf(i));
            hashMap.put("event", String.valueOf(0));
            ReportFactory.report(ReportFactory.VAST_CLICK, this.j.getAd(), this.l, null, hashMap);
        }
    }

    public void i(int i, int i2) {
        List m = this.j.m();
        if (m != null && m.size() > 0) {
            BackgroundHandler.executeAsyncTask(new d(m), new Void[0]);
        }
        a(8, i2, i);
    }

    public void j(int i, int i2) {
        List n = this.j.n();
        if (n != null && n.size() > 0) {
            BackgroundHandler.executeAsyncTask(new d(n), new Void[0]);
        }
        a(9, i2, i);
    }

    public void k(int i, int i2) {
        List p = this.j.p();
        if (p != null && p.size() > 0) {
            BackgroundHandler.executeAsyncTask(new d(p), new Void[0]);
        }
        a(12, i2, i);
    }

    public void a(Context context) {
        String r = this.j.r();
        Context hVar = new h(context);
        if (TextUtils.isEmpty(r)) {
            g.a(Const.TAG, "vast:through url is empty");
            return;
        }
        g.a(Const.TAG, "vast:click through url =" + r);
        if (MarketUtils.isGooglePlayUrl(r)) {
            Commons.openGooglePlayByUrl(r, hVar);
        } else if (VERSION.SDK_INT >= 21) {
            MarketUtils.openUriByBrowser(hVar, r.trim());
        } else {
            f.a(hVar, r.trim());
        }
    }
}
