package com.chartboost.sdk.Model;

import android.text.TextUtils;
import com.chartboost.sdk.CBLocation;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.h;
import com.chartboost.sdk.impl.ad;
import com.chartboost.sdk.impl.af;
import com.chartboost.sdk.impl.be;
import com.chartboost.sdk.impl.bi;
import com.chartboost.sdk.impl.f;
import com.chartboost.sdk.impl.t;
import com.facebook.internal.AnalyticsEvents;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.Date;

public class a {
    private boolean A;
    private Boolean B = null;
    private h C;
    private d D;
    private Runnable E;
    public b a = b.NATIVE;
    public final Date b;
    public e c;
    public final a d;
    public String e;
    public c f;
    public int g = 0;
    public String h = "";
    public String i;
    public long j = 0;
    public boolean k = false;
    public boolean l;
    public boolean m;
    public be n;
    public boolean o;
    public boolean p = false;
    public boolean q = false;
    public boolean r = false;
    public boolean s = false;
    public ad t;
    public boolean u;
    public boolean v = false;
    public boolean w = false;
    public boolean x = false;
    private final com.chartboost.sdk.e y;
    private com.chartboost.sdk.Libraries.e.a z;

    public enum a {
        INTERSTITIAL,
        MORE_APPS,
        REWARDED_VIDEO,
        NONE
    }

    public enum b {
        NATIVE,
        WEB
    }

    public enum c {
        INTERSTITIAL,
        INTERSTITIAL_VIDEO,
        INTERSTITIAL_REWARD_VIDEO,
        NONE
    }

    public interface d {
        void a(a aVar);

        void a(a aVar, CBImpressionError cBImpressionError);

        void a(a aVar, String str, com.chartboost.sdk.Libraries.e.a aVar2);

        void b(a aVar);

        void c(a aVar);

        void d(a aVar);
    }

    public enum e {
        LOADING,
        LOADED,
        DISPLAYED,
        CACHED,
        DISMISSING,
        NONE
    }

    public a(com.chartboost.sdk.e eVar, a aVar, boolean z, String str, boolean z2, b bVar) {
        this.y = eVar;
        this.c = e.LOADING;
        this.l = z;
        this.b = new Date();
        this.m = false;
        this.u = false;
        this.w = true;
        this.d = aVar;
        this.o = z2;
        this.z = com.chartboost.sdk.Libraries.e.a.a;
        this.f = c.NONE;
        this.e = str;
        this.A = true;
        this.a = bVar;
        if (this.e == null) {
            this.e = CBLocation.LOCATION_DEFAULT;
        }
    }

    public void a(com.chartboost.sdk.Libraries.e.a aVar, d dVar) {
        int i;
        int i2 = 0;
        if (aVar == null) {
            aVar = com.chartboost.sdk.Libraries.e.a.a();
        }
        this.z = aVar;
        this.c = e.LOADING;
        this.D = dVar;
        CharSequence e = this.z.e("type");
        if (com.chartboost.sdk.impl.a.a().a(e) || !e.equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE)) {
            this.a = b.WEB;
        } else {
            this.a = b.NATIVE;
        }
        if (this.a == b.NATIVE) {
            i = 1;
        } else {
            i = 0;
        }
        if (i != 0) {
            switch (this.d) {
                case INTERSTITIAL:
                    if (!aVar.a("media-type").equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO)) {
                        this.f = c.INTERSTITIAL;
                        this.C = new com.chartboost.sdk.impl.e(this);
                        break;
                    }
                    this.f = c.INTERSTITIAL_VIDEO;
                    this.C = new f(this);
                    this.A = false;
                    break;
                case REWARDED_VIDEO:
                    this.f = c.INTERSTITIAL_REWARD_VIDEO;
                    this.C = new f(this);
                    this.A = false;
                    if (aVar.c()) {
                        this.g = aVar.f("reward");
                        this.h = aVar.e("currency-name");
                        break;
                    }
                    break;
                case MORE_APPS:
                    this.C = new t(this);
                    this.A = false;
                    break;
            }
        }
        switch (this.d) {
            case INTERSTITIAL:
                if (!aVar.a("media-type").c() || !aVar.a("media-type").equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO)) {
                    if (!aVar.a("media-type").c() || !aVar.a("media-type").equals("image")) {
                        CBLogging.b("CBImpression", "Unknown media type in the response, so have issues determining which ad type to create the view for.");
                        a(CBImpressionError.ERROR_CREATING_VIEW);
                        break;
                    }
                    this.f = c.INTERSTITIAL;
                    break;
                }
                this.f = c.INTERSTITIAL_VIDEO;
                this.A = false;
                break;
                break;
            case REWARDED_VIDEO:
                this.f = c.INTERSTITIAL_REWARD_VIDEO;
                this.A = false;
                if (aVar.c()) {
                    this.g = aVar.f("reward");
                }
                if (this.g <= 0) {
                    try {
                        com.chartboost.sdk.Libraries.e.a a = aVar.a("webview");
                        if (a.c() && a.a("elements").c()) {
                            a = a.a("elements");
                            if (a.p() > 0) {
                                while (i2 < a.p()) {
                                    com.chartboost.sdk.Libraries.e.a c = a.c(i2);
                                    CharSequence e2 = c.e("param");
                                    if (!com.chartboost.sdk.impl.a.a().a(e2) && e2.contains("reward_amount")) {
                                        this.g = Integer.valueOf(c.f(Param.VALUE)).intValue();
                                    }
                                    if (!com.chartboost.sdk.impl.a.a().a(e2) && e2.contains("reward_currency")) {
                                        this.h = c.e(Param.VALUE);
                                    }
                                    i2++;
                                }
                                break;
                            }
                        }
                    } catch (Exception e3) {
                        com.chartboost.sdk.Tracking.a.a(getClass(), "prepare webview rewarded video", e3);
                        break;
                    }
                }
                break;
            case MORE_APPS:
                this.A = false;
                break;
        }
        this.C = new bi(this);
        this.C.a(aVar);
    }

    public boolean a() {
        return this.A;
    }

    public void b() {
        if (this.D != null) {
            this.w = true;
            this.D.b(this);
        }
    }

    public void c() {
        if (this.D != null) {
            this.D.a(this);
        }
    }

    public boolean a(String str, com.chartboost.sdk.Libraries.e.a aVar) {
        Exception exception;
        if (this.c != e.DISPLAYED || this.q) {
            return false;
        }
        if (str == null) {
            str = this.z.e("link");
        }
        String e = this.z.e("deep-link");
        if (!TextUtils.isEmpty(e)) {
            try {
                if (af.a(e)) {
                    try {
                        this.B = Boolean.TRUE;
                        str = e;
                    } catch (Exception e2) {
                        str = e;
                        exception = e2;
                        com.chartboost.sdk.Tracking.a.a(getClass(), "onClick", exception);
                        if (!this.u) {
                            return false;
                        }
                        this.u = true;
                        this.w = false;
                        this.D.a(this, str, aVar);
                        return true;
                    }
                }
                this.B = Boolean.FALSE;
            } catch (Exception e3) {
                exception = e3;
                com.chartboost.sdk.Tracking.a.a(getClass(), "onClick", exception);
                if (!this.u) {
                    return false;
                }
                this.u = true;
                this.w = false;
                this.D.a(this, str, aVar);
                return true;
            }
        }
        if (!this.u) {
            return false;
        }
        this.u = true;
        this.w = false;
        this.D.a(this, str, aVar);
        return true;
    }

    public boolean d() {
        return this.B != null;
    }

    public boolean e() {
        return this.B.booleanValue();
    }

    public void a(CBImpressionError cBImpressionError) {
        if (this.D != null) {
            this.D.a(this, cBImpressionError);
        }
    }

    public void f() {
        if (this.D != null) {
            this.D.c(this);
        }
    }

    public void g() {
        if (this.D != null) {
            this.D.d(this);
        }
    }

    public boolean h() {
        if (this.C != null) {
            this.C.b();
            if (this.C.e() != null) {
                return true;
            }
        }
        CBLogging.b("CBImpression", "reinitializing -- no view protocol exists!!");
        CBLogging.e("CBImpression", "reinitializing -- view not yet created");
        return false;
    }

    public void i() {
        j();
        if (this.m) {
            if (this.C != null) {
                this.C.d();
            }
            this.C = null;
            CBLogging.e("CBImpression", "Destroying the view and view data");
        }
    }

    public void j() {
        if (this.n != null) {
            this.n.d();
            try {
                if (!(this.C == null || this.C.e() == null || this.C.e().getParent() == null)) {
                    this.n.removeView(this.C.e());
                }
            } catch (Exception e) {
                CBLogging.b("CBImpression", "Exception raised while cleaning up views", e);
                com.chartboost.sdk.Tracking.a.a(getClass(), "cleanUpViews", e);
            }
            this.n = null;
        }
        if (this.C != null) {
            this.C.f();
        }
        CBLogging.e("CBImpression", "Destroying the view");
    }

    public CBImpressionError k() {
        if (this.C != null) {
            return this.C.c();
        }
        return CBImpressionError.ERROR_CREATING_VIEW;
    }

    public com.chartboost.sdk.h.a l() {
        if (this.C != null) {
            return this.C.e();
        }
        return null;
    }

    public void m() {
        if (this.C != null && this.C.e() != null) {
            this.C.e().setVisibility(8);
        }
    }

    public void a(Runnable runnable) {
        this.E = runnable;
    }

    public void n() {
        this.q = true;
    }

    public void o() {
        if (this.E != null) {
            this.E.run();
            this.E = null;
        }
        this.q = false;
        this.p = false;
    }

    public String p() {
        return this.z.e("ad_id");
    }

    public com.chartboost.sdk.e q() {
        return this.y;
    }

    public void r() {
        q().j(this);
    }

    public boolean s() {
        if (this.C != null) {
            return this.C.l();
        }
        return false;
    }

    public void t() {
        this.u = false;
        if (this.C != null && this.v) {
            this.v = false;
            this.C.m();
        }
    }

    public void u() {
        this.u = false;
    }

    public void v() {
        if (this.C != null && !this.v) {
            this.v = true;
            this.C.n();
        }
    }

    public com.chartboost.sdk.Libraries.e.a w() {
        return this.z == null ? com.chartboost.sdk.Libraries.e.a.a : this.z;
    }

    public void a(com.chartboost.sdk.Libraries.e.a aVar) {
        if (aVar == null) {
            aVar = com.chartboost.sdk.Libraries.e.a.a;
        }
        this.z = aVar;
    }

    public h x() {
        return this.C;
    }

    public boolean y() {
        return this.w;
    }
}
