package com.chartboost.sdk;

import android.app.Activity;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.CBError.CBClickError;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Model.a.c;
import com.chartboost.sdk.Model.a.e;
import com.chartboost.sdk.impl.ad;
import com.chartboost.sdk.impl.af;
import com.chartboost.sdk.impl.be;
import com.chartboost.sdk.impl.bi;
import com.chartboost.sdk.impl.f;
import java.util.Locale;

public class d {
    private static final String b = d.class.getSimpleName();
    public final com.chartboost.sdk.Model.a.d a = new com.chartboost.sdk.Model.a.d(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public void a(com.chartboost.sdk.Model.a aVar) {
            synchronized (this.a) {
                boolean z = aVar.l;
            }
            if (aVar.c == e.LOADING) {
                aVar.c = e.LOADED;
                if (aVar.a == com.chartboost.sdk.Model.a.b.WEB) {
                    aVar.q().g(aVar);
                    aVar.q().n(aVar);
                    return;
                } else if (z) {
                    aVar.q().a(aVar);
                } else {
                    aVar.q().p(aVar);
                }
            }
            if (!z || aVar.c == e.DISPLAYED || aVar.k) {
                aVar.q().g(aVar);
            }
            aVar.q().n(aVar);
        }

        public void b(com.chartboost.sdk.Model.a aVar) {
            g h;
            if (aVar.c == e.DISPLAYED) {
                h = f.q().h();
                if (h != null) {
                    h.b(aVar);
                }
            } else if (aVar.a == com.chartboost.sdk.Model.a.b.WEB && aVar.c == e.LOADED) {
                h = f.q().h();
                if (h != null) {
                    h.d(aVar);
                }
            }
            if (aVar.y()) {
                f.k().c(aVar.q().e(), aVar.e, aVar.p());
            } else {
                f.k().d(aVar.q().e(), aVar.e, aVar.p());
            }
        }

        public void a(com.chartboost.sdk.Model.a aVar, String str, com.chartboost.sdk.Libraries.e.a aVar2) {
            aVar.q().a().a(aVar);
            if (aVar.a() && aVar.c == e.DISPLAYED) {
                g h = f.q().h();
                if (h != null) {
                    h.b(aVar);
                }
            }
            if (com.chartboost.sdk.impl.a.a().a((CharSequence) str)) {
                boolean z = false;
            } else {
                int i = 1;
            }
            if (i != 0) {
                com.chartboost.sdk.Libraries.e.a w = aVar.w();
                ad d = this.a.d();
                d.a("ad_id", w);
                d.a("to", w);
                d.a("cgn", w);
                d.a("creative", w);
                if (aVar.f == c.INTERSTITIAL_VIDEO || aVar.f == c.INTERSTITIAL_REWARD_VIDEO) {
                    h hVar;
                    if (aVar.a == com.chartboost.sdk.Model.a.b.NATIVE && aVar.l() != null) {
                        hVar = (f) aVar.x();
                    } else if (aVar.a != com.chartboost.sdk.Model.a.b.WEB || aVar.l() == null) {
                        hVar = null;
                    } else {
                        bi biVar = (bi) aVar.x();
                    }
                    if (hVar != null) {
                        float k = hVar.k();
                        float j = hVar.j();
                        CBLogging.a(aVar.q().getClass().getSimpleName(), String.format(Locale.US, "TotalDuration: %f PlaybackTime: %f", new Object[]{Float.valueOf(j), Float.valueOf(k)}));
                        d.a("total_time", Float.valueOf(j / 1000.0f));
                        if (k <= 0.0f) {
                            d.a("playback_time", Float.valueOf(j / 1000.0f));
                        } else {
                            d.a("playback_time", Float.valueOf(k / 1000.0f));
                        }
                    }
                }
                if (aVar2 != null) {
                    d.a("cgn", aVar2);
                    d.a("creative", aVar2);
                    d.a("type", aVar2);
                    d.a("more_type", aVar2);
                    Object a = aVar2.a("click_coordinates");
                    if (!a.b()) {
                        d.a("click_coordinates", a);
                    }
                }
                d.a("location", aVar.e);
                if (aVar.d()) {
                    d.a("retarget_reinstall", Boolean.valueOf(aVar.e()));
                }
                aVar.t = d;
                this.a.a(aVar, str, null);
            } else {
                this.a.a().a(aVar, false, str, CBClickError.URI_INVALID, null);
            }
            f.k().b(aVar.q().e(), aVar.e, aVar.p());
        }

        public void a(com.chartboost.sdk.Model.a aVar, CBImpressionError cBImpressionError) {
            e q = aVar.q();
            f.k().a(q.e(), aVar.e, aVar.p(), cBImpressionError);
            q.a(aVar, cBImpressionError);
        }

        public void c(com.chartboost.sdk.Model.a aVar) {
            aVar.r = true;
            if (aVar.d == com.chartboost.sdk.Model.a.a.REWARDED_VIDEO && c.h() != null) {
                c.h().didCompleteRewardedVideo(aVar.e, aVar.g);
            }
            d.b(aVar);
        }

        public void d(com.chartboost.sdk.Model.a aVar) {
            aVar.s = true;
        }
    };
    private final af c;

    public interface a {
        void a();
    }

    public static class b implements com.chartboost.sdk.impl.af.a {
        public void a(com.chartboost.sdk.Model.a aVar, boolean z, String str, CBClickError cBClickError, a aVar2) {
            if (aVar != null) {
                aVar.u = false;
                if (aVar.a()) {
                    aVar.c = e.DISMISSING;
                }
            }
            if (z) {
                if (aVar != null && aVar.t != null) {
                    aVar.t.a(true);
                    aVar.t.t();
                } else if (aVar2 != null) {
                    aVar2.a();
                }
            } else if (c.h() != null) {
                c.h().didFailToRecordClick(str, cBClickError);
            }
        }
    }

    public d(af afVar) {
        this.c = afVar;
    }

    public com.chartboost.sdk.impl.af.a a() {
        return this.c.a();
    }

    public final void a(com.chartboost.sdk.Model.a aVar, String str, a aVar2) {
        this.c.a(aVar, str, f.q().getHostActivity(), aVar2);
    }

    protected boolean b() {
        com.chartboost.sdk.Model.a c = c();
        if (c == null) {
            return false;
        }
        c.w = true;
        this.a.b(c);
        return true;
    }

    private static synchronized void b(com.chartboost.sdk.Model.a aVar) {
        synchronized (d.class) {
            ad adVar = new ad("/api/video-complete");
            adVar.a("location", aVar.e);
            adVar.a("reward", Integer.valueOf(aVar.g));
            adVar.a("currency-name", aVar.h);
            adVar.a("ad_id", aVar.p());
            adVar.a("force_close", Boolean.valueOf(false));
            h hVar = null;
            if (aVar.a == com.chartboost.sdk.Model.a.b.NATIVE && aVar.l() != null) {
                hVar = (f) aVar.x();
            } else if (aVar.a == com.chartboost.sdk.Model.a.b.WEB && aVar.l() != null) {
                bi biVar = (bi) aVar.x();
            }
            if (hVar != null) {
                float k = hVar.k();
                float j = hVar.j();
                CBLogging.a(aVar.q().getClass().getSimpleName(), String.format(Locale.US, "TotalDuration: %f PlaybackTime: %f", new Object[]{Float.valueOf(j), Float.valueOf(k)}));
                adVar.a("total_time", Float.valueOf(j / 1000.0f));
                if (k <= 0.0f) {
                    adVar.a("playback_time", Float.valueOf(j / 1000.0f));
                } else {
                    adVar.a("playback_time", Float.valueOf(k / 1000.0f));
                }
            }
            adVar.a(true);
            adVar.t();
            f.k().b(aVar.q().e(), aVar.p());
        }
    }

    protected com.chartboost.sdk.Model.a c() {
        g h = f.q().h();
        be d = h == null ? null : h.d();
        if (d == null) {
            return null;
        }
        return d.h();
    }

    public ad d() {
        return new ad("/api/click");
    }

    public final boolean a(Activity activity, com.chartboost.sdk.Model.a aVar) {
        if (aVar != null) {
            switch (aVar.c) {
                case LOADING:
                    if (aVar.o) {
                        f.q().a(aVar);
                        break;
                    }
                    break;
                case CACHED:
                case LOADED:
                    f.q().a(aVar);
                    break;
                case DISPLAYED:
                    if (!aVar.h()) {
                        if (c.b() == null || !c.b().doesWrapperUseCustomBackgroundingBehavior() || (activity instanceof CBImpressionActivity)) {
                            g h = f.q().h();
                            if (h != null) {
                                CBLogging.b(b, "Error onActivityStart " + aVar.c.name());
                                h.d(aVar);
                                break;
                            }
                        }
                        return false;
                    }
                    break;
            }
        }
        return true;
    }
}
