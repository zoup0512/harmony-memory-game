package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Model.a;
import com.chartboost.sdk.Model.a.b;
import com.chartboost.sdk.Model.a.c;
import com.chartboost.sdk.Model.a.e;
import com.chartboost.sdk.impl.aw;
import com.chartboost.sdk.impl.be;

public class g {
    private be a = null;
    private a b;
    private int c = -1;

    g() {
    }

    public void a(a aVar) {
        switch (aVar.c) {
            case LOADING:
                if (aVar.o && c.v()) {
                    f(aVar);
                    return;
                }
                return;
            default:
                e(aVar);
                return;
        }
    }

    private void e(a aVar) {
        if (this.a == null || this.a.h() == aVar) {
            Object obj = aVar.c != e.DISPLAYED ? 1 : null;
            aVar.c = e.DISPLAYED;
            Activity f = f.q().f();
            CBImpressionError cBImpressionError = f == null ? CBImpressionError.NO_HOST_ACTIVITY : null;
            if (cBImpressionError == null) {
                cBImpressionError = aVar.k();
            }
            if (cBImpressionError != null) {
                CBLogging.b("CBViewController", "Unable to create the view while trying th display the impression");
                aVar.a(cBImpressionError);
                return;
            }
            if (this.a == null) {
                this.a = new be(f, aVar);
                f.addContentView(this.a, new LayoutParams(-1, -1));
            }
            CBUtility.a(f, aVar.a);
            if (com.chartboost.sdk.impl.a.a().a(11) && this.c == -1 && (aVar.f == c.INTERSTITIAL_VIDEO || aVar.f == c.INTERSTITIAL_REWARD_VIDEO)) {
                this.c = f.getWindow().getDecorView().getSystemUiVisibility();
                CBUtility.a(f);
            }
            this.a.a();
            CBLogging.e("CBViewController", "Displaying the impression");
            aVar.n = this.a;
            if (obj != null) {
                if (aVar.a == b.NATIVE) {
                    this.a.e().a();
                }
                aw.b bVar = aw.b.CBAnimationTypePerspectiveRotate;
                if (aVar.a == b.WEB) {
                    bVar = aw.b.CBAnimationTypeFade;
                }
                if (aVar.d == a.a.MORE_APPS) {
                    bVar = aw.b.CBAnimationTypePerspectiveZoom;
                }
                aw.b a = aw.b.a(aVar.w().f("animation"));
                if (a != null) {
                    bVar = a;
                }
                if (c.j()) {
                    bVar = aw.b.CBAnimationTypeNone;
                }
                aVar.n();
                aw.a(bVar, aVar, new aw.a(this) {
                    final /* synthetic */ g a;

                    {
                        this.a = r1;
                    }

                    public void a(a aVar) {
                        aVar.o();
                    }
                });
                f.n().g();
                f.o().e();
                if (c.h() != null && (aVar.f == c.INTERSTITIAL_VIDEO || aVar.f == c.INTERSTITIAL_REWARD_VIDEO)) {
                    c.h().willDisplayVideo(aVar.e);
                }
                if (aVar.q().a() != null) {
                    aVar.q().a().e(aVar);
                    return;
                }
                return;
            }
            return;
        }
        CBLogging.b("CBViewController", "Impression already visible");
        aVar.a(CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
    }

    public void b(final a aVar) {
        CBLogging.e("CBViewController", "Dismissing impression");
        final Activity f = f.q().f();
        Runnable anonymousClass2 = new Runnable(this) {
            final /* synthetic */ g c;

            public void run() {
                aVar.c = e.DISMISSING;
                aw.b bVar = aw.b.CBAnimationTypePerspectiveRotate;
                if (aVar.a == b.WEB) {
                    bVar = aw.b.CBAnimationTypeFade;
                }
                if (aVar.d == a.a.MORE_APPS) {
                    bVar = aw.b.CBAnimationTypePerspectiveZoom;
                }
                aw.b a = aw.b.a(aVar.w().f("animation"));
                if (a != null) {
                    bVar = a;
                }
                if (c.j()) {
                    bVar = aw.b.CBAnimationTypeNone;
                }
                aw.b(bVar, aVar, new aw.a(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void a(final a aVar) {
                        CBUtility.c().post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public void run() {
                                this.b.a.c.d(aVar);
                            }
                        });
                        aVar.m();
                        CBUtility.b(f, aVar.a);
                        if (VERSION.SDK_INT >= 11 && this.a.c.c != -1) {
                            if (aVar.f == c.INTERSTITIAL_VIDEO || aVar.f == c.INTERSTITIAL_REWARD_VIDEO) {
                                f.getWindow().getDecorView().setSystemUiVisibility(this.a.c.c);
                                this.a.c.c = -1;
                            }
                        }
                    }
                });
            }
        };
        if (aVar.q) {
            aVar.a(anonymousClass2);
        } else {
            anonymousClass2.run();
        }
    }

    private void f(a aVar) {
        Context f = f.q().f();
        if (f == null) {
            CBLogging.d(this, "No host activity to display loading view");
            return;
        }
        if (this.a == null) {
            this.a = new be(f, aVar);
            f.addContentView(this.a, new LayoutParams(-1, -1));
        }
        this.a.b();
        this.b = aVar;
    }

    public void a(a aVar, boolean z) {
        if (aVar == null) {
            return;
        }
        if (aVar == this.b || aVar == f.c().c()) {
            this.b = null;
            CBLogging.e("CBViewController", "Dismissing loading view");
            if (b()) {
                this.a.c();
                if (z && this.a != null && this.a.h() != null) {
                    d(this.a.h());
                }
            }
        }
    }

    public void c(a aVar) {
        CBLogging.e("CBViewController", "Removing impression silently");
        if (b()) {
            a(aVar, false);
        }
        aVar.j();
        try {
            ((ViewGroup) this.a.getParent()).removeView(this.a);
        } catch (Exception e) {
            CBLogging.b("CBViewController", "Exception removing impression silently", e);
            com.chartboost.sdk.Tracking.a.a(getClass(), "removeImpressionSilently", e);
        }
        this.a = null;
    }

    public void d(a aVar) {
        CBLogging.e("CBViewController", "Removing impression");
        aVar.c = e.NONE;
        aVar.i();
        this.a = null;
        f.n().h();
        f.o().f();
        aVar.q().a().c(aVar);
        if (aVar.y()) {
            aVar.q().a().b(aVar);
        }
        a();
    }

    public void a() {
        CBLogging.e("CBViewController", "Attempting to close impression activity");
        Activity f = f.q().f();
        if (f != null && (f instanceof CBImpressionActivity)) {
            CBLogging.e("CBViewController", "Closing impression activity");
            f.q().g();
            f.finish();
        }
    }

    public boolean b() {
        return this.a != null && this.a.g();
    }

    public boolean c() {
        return f.c().c() != null;
    }

    public be d() {
        return this.a;
    }
}
