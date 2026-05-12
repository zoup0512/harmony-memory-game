package com.my.target.core.engines;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ViewFlipper;
import com.mopub.volley.DefaultRetryPolicy;
import com.my.target.Tracer;
import com.my.target.ads.MyTargetView;
import com.my.target.core.models.banners.g;
import com.my.target.core.models.f;
import com.my.target.core.net.b;
import com.my.target.core.ui.views.AdView;
import com.my.target.core.ui.views.StandardNativeView;
import com.my.target.core.ui.views.controls.AdInfoButton;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: StandardAdNativeEngine */
public final class h extends a implements com.my.target.core.ui.views.StandardNativeView.a {
    private static Handler c;
    private final AdView d;
    private final MyTargetView e;
    private final ViewFlipper[] f;
    private final ViewFlipper g;
    private final com.my.target.core.facades.h[] h;
    private final a i = new a();
    private final com.my.target.core.net.b.a j = new com.my.target.core.net.b.a(this) {
        final /* synthetic */ h a;

        {
            this.a = r1;
        }

        public final void onLoad() {
            this.a.i.e();
            if (this.a.e.getListener() != null) {
                this.a.e.getListener().onLoad(this.a.e);
            }
        }
    };
    private final AnimationListener k = new AnimationListener(this) {
        final /* synthetic */ h a;

        {
            this.a = r1;
        }

        public final void onAnimationStart(Animation animation) {
        }

        public final void onAnimationEnd(Animation animation) {
            this.a.z = false;
            this.a.p = System.currentTimeMillis();
            this.a.w = (g) this.a.u.get(this.a.k().getDisplayedChild());
            if (this.a.m() != null) {
                this.a.b(this.a.m().d());
                this.a.m().b(this.a.w.getId());
                this.a.q = (long) (this.a.w.f() * 1000);
                this.a.a(true);
                this.a.i();
            }
        }

        public final void onAnimationRepeat(Animation animation) {
        }
    };
    private final Runnable l = new Runnable(this) {
        final /* synthetic */ h a;

        {
            this.a = r1;
        }

        public final void run() {
            if (this.a.u != null && !this.a.u.isEmpty() && this.a.w != null) {
                if ("banner".equals(this.a.w.a())) {
                    this.a.h();
                } else {
                    this.a.y = true;
                }
            }
        }
    };
    private final com.my.target.core.net.b.a m = new com.my.target.core.net.b.a(this) {
        final /* synthetic */ h a;

        {
            this.a = r1;
        }

        public final void onLoad() {
            this.a.a(this.a.l());
            this.a.x = true;
            if ("banner".equals(this.a.w.a()) && ((long) (this.a.w.f() * 1000)) <= System.currentTimeMillis() - this.a.p) {
                this.a.h();
            }
        }
    };
    private final com.my.target.core.facades.h.a n = new com.my.target.core.facades.h.a(this) {
        final /* synthetic */ h a;

        {
            this.a = r1;
        }

        public final void onLoad(com.my.target.core.facades.h hVar) {
            if (hVar == this.a.n()) {
                this.a.n().a(null);
                h.a(this.a, this.a.n());
            }
        }

        public final void onNoAd(String str, com.my.target.core.facades.h hVar) {
            this.a.o();
        }
    };
    private int o;
    private long p;
    private long q;
    private int r;
    private com.my.target.core.models.h s;
    private AdInfoButton t;
    private ArrayList<g> u;
    private ArrayList<g> v;
    private g w;
    private boolean x;
    private boolean y;
    private boolean z;

    /* compiled from: StandardAdNativeEngine */
    private static class a {
        private boolean a;
        private boolean b;
        private boolean c;

        private a() {
        }

        public final boolean a() {
            return this.b && !this.c;
        }

        public final boolean b() {
            return this.b;
        }

        public final boolean c() {
            return this.b && this.c;
        }

        public final boolean d() {
            return this.a;
        }

        public final void e() {
            this.a = true;
        }

        public final void f() {
            this.c = true;
        }

        public final void g() {
            this.c = false;
        }

        public final void h() {
            this.b = true;
        }

        public final void i() {
            this.b = false;
            this.c = false;
        }

        public final void j() {
            this.b = false;
            this.c = false;
            this.a = false;
        }
    }

    private static ArrayList<f> a(ArrayList<g> arrayList) {
        ArrayList<f> arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            g gVar = (g) it.next();
            if (!(gVar.n() == null || TextUtils.isEmpty(gVar.n().getUrl()))) {
                arrayList2.add(gVar.n());
            }
        }
        return arrayList2;
    }

    public h(MyTargetView myTargetView, Context context) {
        super(myTargetView, context);
        this.e = myTargetView;
        this.u = new ArrayList();
        this.d = new AdView(context);
        this.g = new ViewFlipper(context);
        this.f = new ViewFlipper[2];
        this.f[0] = new ViewFlipper(context);
        this.f[1] = new ViewFlipper(context);
        this.h = new com.my.target.core.facades.h[2];
        float f = this.b.getResources().getDisplayMetrics().density;
        this.r = (int) (50.0f * f);
        this.g.setLayoutParams(new LayoutParams(-1, -1));
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
        this.d.setMaxWidth((int) (f * 640.0f));
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, this.r);
        layoutParams2.addRule(13);
        this.d.setLayoutParams(layoutParams2);
        this.g.addView(this.f[0]);
        this.g.addView(this.f[1]);
        this.d.addView(this.g, layoutParams);
        this.a.addView(this.d);
    }

    private void h() {
        if (this.x) {
            this.o ^= 1;
            this.g.setDisplayedChild(this.o);
            this.h[this.o ^ 1].a(null);
            this.h[this.o ^ 1] = null;
            a(false);
            this.w = (g) this.u.get(0);
            if (m() != null) {
                m().b(this.w.getId());
                a(m().g());
                this.q = (long) (this.w.f() * 1000);
                i();
            }
            this.y = false;
            this.x = false;
        } else if (k().getDisplayedChild() < k().getChildCount() - 1) {
            j();
            this.z = true;
            this.y = false;
            k().showNext();
        } else if (m() != null && m().g() != null) {
            if (m().g().k() && m() != null && m().a() && n() == null) {
                this.h[this.o ^ 1] = m().h();
                n().a(this.n);
                n().load();
            }
            if (k().getChildCount() > 1 && m().g().l()) {
                this.z = true;
                this.y = false;
                k().setDisplayedChild(0);
            }
        }
    }

    private void i() {
        if (c != null) {
            c.removeCallbacks(this.l);
        }
        if (this.u != null && !this.u.isEmpty() && this.q > 0) {
            if (c == null) {
                c = new Handler();
            }
            c.postDelayed(this.l, this.q);
        }
    }

    public final void d() {
        super.d();
        if (this.i.d()) {
            Tracer.d("Start native banner");
            this.d.setVisibility(0);
            a(k());
            if (!(this.u == null || this.u.isEmpty())) {
                this.w = (g) this.u.get(0);
                if (m() != null) {
                    m().b(this.w.getId());
                    this.p = System.currentTimeMillis();
                    Handler handler = new Handler();
                    c = handler;
                    handler.postDelayed(this.l, (long) (this.w.f() * 1000));
                    a(false);
                }
            }
            this.i.h();
            return;
        }
        Tracer.d("Cannot start native banner");
    }

    public final void a() {
        super.a();
        if (this.i.a()) {
            Tracer.d("Pause native banner");
            if (!(this.z || this.w == null)) {
                this.q = ((long) (this.w.f() * 1000)) - (System.currentTimeMillis() - this.p);
            }
            if (c != null) {
                c.removeCallbacks(this.l);
                c = null;
            }
            j();
            this.i.f();
            return;
        }
        Tracer.d("Cannot pause native banner");
    }

    public final void b() {
        super.b();
        if (this.i.c()) {
            Tracer.d("Resume native banner");
            this.d.setVisibility(0);
            i();
            a(false);
            this.i.g();
            return;
        }
        Tracer.d("Cannot resume native banner");
    }

    public final void c() {
        super.c();
        if (this.i.b()) {
            Tracer.d("Stop native banner");
            this.d.setVisibility(4);
            if (c != null) {
                c.removeCallbacks(this.l);
                c = null;
            }
            j();
            k().removeAllViews();
            l().removeAllViews();
            this.y = false;
            this.x = false;
            o();
            this.i.i();
            return;
        }
        Tracer.d("Cannot stop native banner: Not started");
    }

    public final void f() {
        super.f();
        c();
        Tracer.d("Destroy native banner");
        this.h[0] = null;
        this.h[1] = null;
        this.i.j();
    }

    public final void a(com.my.target.core.engines.b.a aVar) {
    }

    public final void a(com.my.target.core.facades.g gVar) {
        if (gVar instanceof com.my.target.core.facades.h) {
            this.h[this.o] = (com.my.target.core.facades.h) gVar;
            this.s = ((com.my.target.core.facades.h) gVar).i();
            a(((com.my.target.core.facades.h) gVar).g());
            this.v = ((com.my.target.core.facades.h) gVar).e();
            if (!this.v.isEmpty()) {
                List a = a(this.v);
                if (a.isEmpty()) {
                    this.i.e();
                    if (this.e.getListener() != null) {
                        this.e.getListener().onLoad(this.e);
                    }
                } else {
                    b.a().a(a, this.b, this.j);
                }
                if (m() != null) {
                    b(m().d());
                }
                Tracer.d("load native");
                return;
            } else if (this.e.getListener() != null) {
                this.e.getListener().onNoAd("No ad", this.e);
                return;
            } else {
                return;
            }
        }
        Tracer.d("StandardAdEngine: incorrect ad type");
    }

    private void a(com.my.target.core.models.sections.g gVar) {
        int m;
        Animation alphaAnimation;
        Animation animation;
        if (gVar != null) {
            m = gVar.m();
        } else {
            m = 0;
        }
        if (m == 0) {
            alphaAnimation = new AlphaAnimation(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            alphaAnimation.setDuration(10);
            animation = alphaAnimation;
        } else if (m == 2) {
            alphaAnimation = new TranslateAnimation(2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 2, 0.0f, 2, 0.0f, 2, 0.0f);
            alphaAnimation.setDuration(400);
            alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
            animation = alphaAnimation;
        } else {
            animation = com.my.target.core.utils.b.a();
        }
        if (m == 0) {
            alphaAnimation = new AlphaAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f);
            alphaAnimation.setStartOffset(10);
            alphaAnimation.setDuration(10);
        } else if (m == 2) {
            alphaAnimation = new TranslateAnimation(2, 0.0f, 2, -1.0f, 2, 0.0f, 2, 0.0f);
            alphaAnimation.setDuration(400);
            alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        } else {
            alphaAnimation = com.my.target.core.utils.b.b();
        }
        animation.setAnimationListener(this.k);
        this.g.setInAnimation(animation);
        this.g.setOutAnimation(alphaAnimation);
        for (ViewFlipper viewFlipper : this.f) {
            viewFlipper.setInAnimation(animation);
            viewFlipper.setOutAnimation(alphaAnimation);
        }
    }

    public final void a(String str) {
        Tracer.d("Banner clicked " + str);
        if (m() != null) {
            m().a(str);
        }
        if (this.e.getListener() != null) {
            this.e.getListener().onClick(this.e);
        }
    }

    private void b(String str) {
        if (str != null) {
            if (this.t == null) {
                this.t = new AdInfoButton(this.b);
                this.d.addView(this.t, -2, -2);
            }
            this.t.setUrl(str);
        } else if (this.t != null) {
            ViewGroup viewGroup = (ViewGroup) this.t.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.t);
            }
            this.t = null;
        }
    }

    private void a(ViewFlipper viewFlipper) {
        l().removeAllViews();
        Iterator it = this.v.iterator();
        while (it.hasNext()) {
            g gVar = (g) it.next();
            View standardNativeView = new StandardNativeView(this.b, this.r);
            standardNativeView.setViewSettings(this.s, "banner".equals(gVar.a()));
            standardNativeView.setMyTargetClickListener(this);
            standardNativeView.setAfterLastSlideListener(this);
            standardNativeView.setBanner(gVar);
            viewFlipper.addView(standardNativeView);
        }
        this.u = this.v;
    }

    private void j() {
        if (k() != null && k().getCurrentView() != null) {
            ((StandardNativeView) k().getCurrentView()).a();
        }
    }

    private void a(boolean z) {
        if (k() != null && k().getCurrentView() != null) {
            ((StandardNativeView) k().getCurrentView()).a(z);
        }
    }

    private ViewFlipper k() {
        return this.f[this.o];
    }

    private ViewFlipper l() {
        return this.f[this.o ^ 1];
    }

    private com.my.target.core.facades.h m() {
        return this.h[this.o];
    }

    private com.my.target.core.facades.h n() {
        return this.h[this.o ^ 1];
    }

    private void o() {
        if (this.h[this.o ^ 1] != null) {
            this.h[this.o ^ 1].a(null);
            this.h[this.o ^ 1] = null;
        }
    }

    public final void g() {
        if (this.y) {
            h();
        }
    }

    static /* synthetic */ void a(h hVar, com.my.target.core.facades.h hVar2) {
        hVar.v = hVar2.e();
        List a = a(hVar.v);
        if (a.isEmpty()) {
            hVar.a(hVar.l());
            hVar.x = true;
            if ("banner".equals(hVar.w.a()) && ((long) (hVar.w.f() * 1000)) <= System.currentTimeMillis() - hVar.p) {
                hVar.h();
            }
        } else if (a.size() > 0) {
            b.a().a(a, hVar.b, hVar.m);
        }
    }
}
