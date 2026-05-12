package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import com.chartboost.sdk.Libraries.e.b;
import com.chartboost.sdk.Libraries.k;
import com.chartboost.sdk.h;
import com.chartboost.sdk.i;
import com.mopub.mobileads.VastIconXmlManager;
import com.mopub.volley.DefaultRetryPolicy;

public class e extends h {
    protected k l = new k(this);
    protected k m = new k(this);
    protected com.chartboost.sdk.Libraries.e.a n;
    protected String o;
    protected float p = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    private k q = new k(this);
    private k r = new k(this);
    private k s = new k(this);
    private k t = new k(this);

    public class a extends com.chartboost.sdk.h.a {
        protected ay b;
        protected az c;
        protected az d;
        protected ImageView e;
        final /* synthetic */ e f;
        private boolean g = false;

        protected a(final e eVar, Context context) {
            this.f = eVar;
            super(eVar, context);
            setBackgroundColor(0);
            setLayoutParams(new LayoutParams(-1, -1));
            i a = i.a();
            this.b = a.a(context);
            addView(this.b, new LayoutParams(-1, -1));
            this.d = new az(this, context) {
                final /* synthetic */ a b;

                protected void a(MotionEvent motionEvent) {
                    this.b.a(motionEvent.getX(), motionEvent.getY(), (float) this.b.d.getWidth(), (float) this.b.d.getHeight());
                }
            };
            a(this.d);
            this.d.setContentDescription("CBAd");
            this.e = a.h(context);
            this.e.setBackgroundColor(-16777216);
            addView(this.e);
            addView(this.d);
        }

        protected void d() {
            this.c = new az(this, getContext()) {
                final /* synthetic */ a a;

                protected void a(MotionEvent motionEvent) {
                    this.a.e();
                }
            };
            this.c.setContentDescription("CBClose");
            addView(this.c);
        }

        protected void a(float f, float f2, float f3, float f4) {
            e eVar = this.f;
            b[] bVarArr = new b[1];
            bVarArr[0] = com.chartboost.sdk.Libraries.e.a("click_coordinates", com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("x", Float.valueOf(f)), com.chartboost.sdk.Libraries.e.a("y", Float.valueOf(f2)), com.chartboost.sdk.Libraries.e.a("w", Float.valueOf(f3)), com.chartboost.sdk.Libraries.e.a("h", Float.valueOf(f4))));
            eVar.c = com.chartboost.sdk.Libraries.e.a(bVarArr);
            this.f.a(null, this.f.c);
        }

        protected void a(int i, int i2) {
            int round;
            int round2;
            if (!this.g) {
                d();
                this.g = true;
            }
            boolean a = this.f.a().a();
            k b = a ? this.f.q : this.f.r;
            k kVar = a ? this.f.l : this.f.m;
            if (!b.e()) {
                if (b == this.f.q) {
                    b = this.f.r;
                } else {
                    b = this.f.q;
                }
            }
            if (!kVar.e()) {
                if (kVar == this.f.l) {
                    kVar = this.f.m;
                } else {
                    kVar = this.f.l;
                }
            }
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
            ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-2, -2);
            this.f.a(layoutParams, b, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            this.f.p = Math.min(Math.min(((float) i) / ((float) layoutParams.width), ((float) i2) / ((float) layoutParams.height)), DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            layoutParams.width = (int) (((float) layoutParams.width) * this.f.p);
            layoutParams.height = (int) (((float) layoutParams.height) * this.f.p);
            Point b2 = this.f.b(a ? "frame-portrait" : "frame-landscape");
            layoutParams.leftMargin = Math.round((((float) (i - layoutParams.width)) / 2.0f) + ((((float) b2.x) / b.g()) * this.f.p));
            layoutParams.topMargin = Math.round(((((float) b2.y) / b.g()) * this.f.p) + (((float) (i2 - layoutParams.height)) / 2.0f));
            this.f.a(layoutParams2, kVar, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            b2 = this.f.b(a ? "close-portrait" : "close-landscape");
            if (b2.x == 0 && b2.y == 0) {
                round = Math.round(((float) (-layoutParams2.width)) / 2.0f) + (layoutParams.leftMargin + layoutParams.width);
                round2 = layoutParams.topMargin + Math.round(((float) (-layoutParams2.height)) / 2.0f);
            } else {
                round = Math.round(((((float) layoutParams.leftMargin) + (((float) layoutParams.width) / 2.0f)) + ((float) b2.x)) - (((float) layoutParams2.width) / 2.0f));
                round2 = Math.round((((float) b2.y) + (((float) layoutParams.topMargin) + (((float) layoutParams.height) / 2.0f))) - (((float) layoutParams2.height) / 2.0f));
            }
            layoutParams2.leftMargin = Math.min(Math.max(0, round), i - layoutParams2.width);
            layoutParams2.topMargin = Math.min(Math.max(0, round2), i2 - layoutParams2.height);
            this.b.setLayoutParams(layoutParams);
            this.c.setLayoutParams(layoutParams2);
            this.b.setScaleType(ScaleType.FIT_CENTER);
            this.b.a(b);
            this.c.a(kVar);
            kVar = a ? this.f.s : this.f.t;
            if (!kVar.e()) {
                if (kVar == this.f.s) {
                    kVar = this.f.t;
                } else {
                    kVar = this.f.s;
                }
            }
            ViewGroup.LayoutParams layoutParams3 = new LayoutParams(-2, -2);
            this.f.a(layoutParams3, kVar, this.f.p);
            Point b3 = this.f.b(a ? "ad-portrait" : "ad-landscape");
            layoutParams3.leftMargin = Math.round((((float) (i - layoutParams3.width)) / 2.0f) + ((((float) b3.x) / kVar.g()) * this.f.p));
            layoutParams3.topMargin = Math.round(((((float) b3.y) / kVar.g()) * this.f.p) + (((float) (i2 - layoutParams3.height)) / 2.0f));
            this.e.setLayoutParams(layoutParams3);
            this.d.setLayoutParams(layoutParams3);
            this.d.a(ScaleType.FIT_CENTER);
            this.d.a(kVar);
        }

        protected void e() {
            this.f.h();
        }

        public void b() {
            super.b();
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
        }
    }

    public e(com.chartboost.sdk.Model.a aVar) {
        super(aVar);
    }

    protected com.chartboost.sdk.h.a b(Context context) {
        return new a(this, context);
    }

    public boolean a(com.chartboost.sdk.Libraries.e.a aVar) {
        if (!super.a(aVar)) {
            return false;
        }
        this.o = aVar.e("ad_id");
        this.n = aVar.a("ux");
        if (this.f.b("frame-portrait") || this.f.b("close-portrait")) {
            this.j = false;
        }
        if (this.f.b("frame-landscape") || this.f.b("close-landscape")) {
            this.k = false;
        }
        this.r.a("frame-landscape");
        this.q.a("frame-portrait");
        this.m.a("close-landscape");
        this.l.a("close-portrait");
        if (this.f.b("ad-portrait")) {
            this.j = false;
        }
        if (this.f.b("ad-landscape")) {
            this.k = false;
        }
        this.t.a("ad-landscape");
        this.s.a("ad-portrait");
        return true;
    }

    protected Point b(String str) {
        com.chartboost.sdk.Libraries.e.a a = this.f.a(str).a(VastIconXmlManager.OFFSET);
        if (a.c()) {
            return new Point(a.f("x"), a.f("y"));
        }
        return new Point(0, 0);
    }

    public void a(ViewGroup.LayoutParams layoutParams, k kVar, float f) {
        layoutParams.width = (int) ((((float) kVar.b()) / kVar.g()) * f);
        layoutParams.height = (int) ((((float) kVar.c()) / kVar.g()) * f);
    }

    public void d() {
        super.d();
        this.r.d();
        this.q.d();
        this.m.d();
        this.l.d();
        this.t.d();
        this.s.d();
        this.r = null;
        this.q = null;
        this.m = null;
        this.l = null;
        this.t = null;
        this.s = null;
    }
}
