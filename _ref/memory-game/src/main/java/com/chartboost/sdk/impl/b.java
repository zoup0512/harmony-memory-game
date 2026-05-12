package com.chartboost.sdk.impl;

import android.text.TextUtils;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.h;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Model.a;
import com.chartboost.sdk.c;
import com.chartboost.sdk.e;
import com.chartboost.sdk.f;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.AnalyticsEvents;
import com.mopub.common.AdType;
import org.json.JSONArray;

public class b extends e {
    private static final String d = b.class.getSimpleName();

    protected boolean b(a aVar, com.chartboost.sdk.Libraries.e.a aVar2) {
        com.chartboost.sdk.Libraries.e.a a = aVar2.a("media-type");
        return a != null && a.equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO);
    }

    protected void g(a aVar) {
        super.g(aVar);
    }

    protected void a(a aVar, com.chartboost.sdk.Libraries.e.a aVar2) {
        if (aVar.a != com.chartboost.sdk.Model.a.b.NATIVE) {
            aVar.a(aVar2);
            if (aVar2.c() && aVar2.a("webview").c()) {
                Object e = aVar2.a("webview").e("template");
                if (!TextUtils.isEmpty(e)) {
                    aVar.i = e;
                }
                JSONArray e2 = a.a().e();
                e2.put(aVar2.a("webview"));
                Object d = a.a().d();
                try {
                    d.put("templates", e2);
                    if (aVar.l) {
                        f.n().a.put(aVar, com.chartboost.sdk.b.e.Medium);
                        f.n().a(com.chartboost.sdk.b.e.Medium, com.chartboost.sdk.Libraries.e.a.a(d));
                        return;
                    }
                    f.n().a.put(aVar, com.chartboost.sdk.b.e.High);
                    f.n().a(com.chartboost.sdk.b.e.High, com.chartboost.sdk.Libraries.e.a.a(d));
                    return;
                } catch (Exception e3) {
                    com.chartboost.sdk.Tracking.a.a(getClass(), "loadView put templates array", e3);
                    CBLogging.b(d, "Error while trying to create a template object from the response");
                    f.k().a(e(), aVar.e, "Empty", "Error while trying to parse the response", true);
                    a(aVar, CBImpressionError.INVALID_RESPONSE);
                    return;
                }
            }
            f.k().a(e(), aVar.e, "Empty", "Response is empty", true);
            CBLogging.b(d, "Response got from the server is empty");
            a(aVar, CBImpressionError.INVALID_RESPONSE);
        } else if (!b(aVar, aVar2) || f.o().c(aVar2)) {
            super.a(aVar, aVar2);
        } else {
            CBLogging.b(d, "Video Media unavailable for the cached impression");
            a(aVar, CBImpressionError.VIDEO_UNAVAILABLE);
        }
    }

    public void q(a aVar) {
        com.chartboost.sdk.Libraries.e.a w = aVar.w();
        if (w.c() && w.a("webview").c()) {
            String e = w.a("webview").e("template");
            if (w.j("prefetch_required")) {
                f.n().a();
            }
            if (f.n().c().containsKey(e)) {
                aVar.i = e;
                this.b.put(aVar.e, aVar);
                if (aVar.l) {
                    a().d(aVar);
                    aVar.c = a.e.CACHED;
                    n(aVar);
                    if (aVar.k) {
                        a(aVar.e);
                        return;
                    }
                    return;
                }
                super.a(aVar, aVar.w());
                return;
            }
            CBLogging.b(d, "Cannot able to find the html file for some reason due to some error");
            a(aVar, CBImpressionError.ERROR_LOADING_WEB_VIEW);
        }
    }

    protected a a(String str, boolean z) {
        return new a(this, a.a.INTERSTITIAL, z, str, false, f());
    }

    protected ad e(a aVar) {
        ad ahVar;
        if (c.G().booleanValue()) {
            aVar.a = com.chartboost.sdk.Model.a.b.WEB;
            com.chartboost.sdk.Libraries.e.a b = f.n().b();
            ahVar = new ah(c.y());
            ahVar.a("cache_assets", b, ah.a.AD);
            ahVar.a(com.chartboost.sdk.impl.w.b.HIGH);
            ahVar.a("location", aVar.e, ah.a.AD);
            if (aVar.l) {
                ahVar.a("cache", Boolean.valueOf(true), ah.a.AD);
                ahVar.b(true);
            } else {
                ahVar.a("cache", Boolean.valueOf(false), ah.a.AD);
            }
            ahVar.a(com.chartboost.sdk.Model.b.f);
        } else {
            aVar.a = com.chartboost.sdk.Model.a.b.NATIVE;
            ahVar = new ad(c.y());
            ahVar.a("local-videos", h());
            ahVar.a(com.chartboost.sdk.impl.w.b.HIGH);
            ahVar.a(com.chartboost.sdk.Model.b.f);
            ahVar.a("location", aVar.e);
            if (aVar.l) {
                ahVar.a("cache", AppEventsConstants.EVENT_PARAM_VALUE_YES);
                ahVar.b(true);
            }
        }
        return ahVar;
    }

    protected void i(a aVar) {
        if (aVar.f != a.c.INTERSTITIAL_VIDEO && aVar.a != com.chartboost.sdk.Model.a.b.WEB) {
            super.i(aVar);
        }
    }

    protected a b() {
        return new a(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(a aVar) {
                if (c.h() != null) {
                    c.h().didClickInterstitial(aVar.e);
                }
            }

            public void b(a aVar) {
                if (c.h() != null) {
                    c.h().didCloseInterstitial(aVar.e);
                }
            }

            public void c(a aVar) {
                if (c.h() != null) {
                    c.h().didDismissInterstitial(aVar.e);
                }
            }

            public void d(a aVar) {
                if (c.h() != null) {
                    c.h().didCacheInterstitial(aVar.e);
                }
            }

            public void a(a aVar, CBImpressionError cBImpressionError) {
                if (c.h() != null) {
                    c.h().didFailToLoadInterstitial(aVar.e, cBImpressionError);
                }
            }

            public void e(a aVar) {
                if (c.h() != null) {
                    c.h().didDisplayInterstitial(aVar.e);
                }
            }

            public boolean f(a aVar) {
                if (c.h() != null) {
                    return c.h().shouldDisplayInterstitial(aVar.e);
                }
                return true;
            }

            public boolean g(a aVar) {
                if (c.h() != null) {
                    return c.h().shouldRequestInterstitial(aVar.e);
                }
                return true;
            }

            public boolean h(a aVar) {
                if (c.h() != null) {
                    return c.u();
                }
                return true;
            }
        };
    }

    protected ad l(a aVar) {
        return new ad("/interstitial/show");
    }

    public JSONArray h() {
        JSONArray e = a.a().e();
        String[] c = h.c();
        if (c != null) {
            for (String str : c) {
                if (!str.contains("nomedia")) {
                    e.put(str);
                }
            }
        }
        return e;
    }

    public String e() {
        return String.format("%s-%s", new Object[]{AdType.INTERSTITIAL, c.W()});
    }
}
