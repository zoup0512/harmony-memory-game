package com.my.target.core.engines;

import android.content.Context;
import com.my.target.Tracer;
import com.my.target.ads.MyTargetVideoView;
import com.my.target.ads.MyTargetVideoView.BannerInfo;
import com.my.target.core.enums.b;
import com.my.target.core.facades.d;
import com.my.target.core.models.banners.i;
import com.my.target.core.models.g;
import com.my.target.core.models.sections.h;
import com.my.target.core.ui.views.VideoContainer;
import com.my.target.core.ui.views.VideoContainer.a;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: InstreamAdEngine */
public final class f extends a {
    private boolean c;
    private final MyTargetVideoView d;
    private d e;
    private VideoContainer f;
    private ArrayList<i> g;
    private int h;
    private BannerInfo i;
    private HashSet<g> j;
    private i k;
    private h l;
    private String m;
    private int n;
    private int o;
    private boolean p;
    private a q = new a(this) {
        final /* synthetic */ f a;

        {
            this.a = r1;
        }

        public final void a(float f, float f2) {
            while (this.a.d != null && this.a.d.getListener() != null) {
                if (this.a.c) {
                    if (f != f2) {
                        this.a.d.getListener().onResumptionBanner(this.a.d, this.a.i);
                    }
                    this.a.c = false;
                }
                f2 = this.a.i.duration;
                if (f <= f2) {
                    f.a(this.a, f);
                    this.a.d.getListener().onTimeLeftChange(f2 - f, f2, this.a.d);
                    if (f == f2) {
                        this.a.n = this.a.n + 1;
                        this.a.a(false, MyTargetVideoView.COMPLETE_STATUS_OK, false);
                        return;
                    }
                    return;
                }
                f = f2;
            }
        }

        public final void a(float f) {
            Tracer.d("Video file started");
            if (this.a.d != null && this.a.d.getListener() != null) {
                if (!this.a.p) {
                    this.a.e.a(this.a.l, "impression");
                    this.a.p = true;
                }
                this.a.e.a(this.a.k, "playbackStarted");
                if (f < this.a.i.duration) {
                    this.a.i.duration = f;
                }
                this.a.d.getListener().onStartBanner(this.a.d, this.a.i);
            }
        }

        public final void a() {
            this.a.c = false;
            Tracer.d("Video lagging");
            if (!(this.a.d == null || this.a.d.getListener() == null)) {
                this.a.d.getListener().onError("Video ad error: cannot play video", this.a.d);
            }
            this.a.a(false, MyTargetVideoView.COMPLETE_STATUS_TIMEOUT, true);
        }

        public final void a(String str) {
            this.a.c = false;
            this.a.e.a(this.a.l, "error");
            Tracer.d("Video playing error: " + str);
            if (!(this.a.d == null || this.a.d.getListener() == null)) {
                this.a.d.getListener().onError("Video ad error: " + str, this.a.d);
            }
            this.a.a(false, "error", false);
        }

        public final void b() {
            if (!this.a.c) {
                if (!(this.a.d == null || this.a.d.getListener() == null)) {
                    this.a.d.getListener().onSuspenseBanner(this.a.d, this.a.i);
                }
                this.a.c = true;
            }
        }
    };

    public f(d dVar, MyTargetVideoView myTargetVideoView, Context context) {
        super(myTargetVideoView, context);
        this.e = dVar;
        this.d = myTargetVideoView;
        j();
        a((com.my.target.core.facades.g) dVar);
    }

    public final void a(com.my.target.core.facades.g gVar) {
        if (gVar instanceof d) {
            this.e = (d) gVar;
        }
    }

    public final void a() {
        super.a();
        if (this.f != null && this.f.c() && !this.f.d()) {
            this.e.a(this.k, "playbackPaused");
            this.f.a();
        }
    }

    public final void b() {
        super.b();
        if (this.f != null && !this.f.c() && this.f.d()) {
            this.e.a(this.k, "playbackResumed");
            this.f.b();
        }
    }

    public final void c() {
        super.c();
        if (this.f != null && this.f.c()) {
            this.e.a(this.k, "playbackStopped");
            this.f.a(false);
            this.d.removeView(this.f);
        }
    }

    public final void f() {
        c();
        super.f();
    }

    public final void a(b bVar) {
        c();
        this.h = 0;
        this.m = bVar.toString();
        this.n = 0;
        this.p = false;
        if (this.e != null) {
            this.l = this.e.a(this.m);
            this.o = this.l.i().d();
            this.g = this.l.g();
            if (!this.g.isEmpty()) {
                a(0);
            } else if (this.d != null && this.d.getListener() != null) {
                this.d.getListener().onComplete(this.m, this.d, MyTargetVideoView.COMPLETE_STATUS_NO_BANNERS);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(int r9) {
        /*
        r8 = this;
        r3 = -1;
    L_0x0001:
        r0 = 0;
        r8.c = r0;
        r0 = r8.g;
        r0 = r0.get(r9);
        r0 = (com.my.target.core.models.banners.i) r0;
        r8.k = r0;
        r0 = "statistics";
        r1 = r8.k;
        r1 = r1.a();
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0052;
    L_0x001c:
        r0 = r8.k;
        r1 = r8.e;
        r2 = "playbackStarted";
        r1.a(r0, r2);
        r0 = r8.h;
        r0 = r0 + 1;
        r8.h = r0;
        r1 = r8.g;
        r1 = r1.size();
        if (r0 >= r1) goto L_0x0036;
    L_0x0033:
        r9 = r8.h;
        goto L_0x0001;
    L_0x0036:
        r0 = r8.d;
        if (r0 == 0) goto L_0x0051;
    L_0x003a:
        r0 = r8.d;
        r0 = r0.getListener();
        if (r0 == 0) goto L_0x0051;
    L_0x0042:
        r0 = r8.d;
        r0 = r0.getListener();
        r1 = r8.m;
        r2 = r8.d;
        r3 = "no_banners";
        r0.onComplete(r1, r2, r3);
    L_0x0051:
        return;
    L_0x0052:
        r0 = r8.k;
        r0 = r0.u();
        r1 = r8.d;
        r1 = r1.getVideoQuality();
        r6 = com.my.target.core.utils.n.a(r0, r1);
        r0 = r8.f;
        if (r0 != 0) goto L_0x0069;
    L_0x0066:
        r8.j();
    L_0x0069:
        r0 = r8.f;
        r0 = r0.getParent();
        if (r0 != 0) goto L_0x0082;
    L_0x0071:
        r0 = new android.widget.RelativeLayout$LayoutParams;
        r0.<init>(r3, r3);
        r1 = 13;
        r0.addRule(r1, r3);
        r1 = r8.a;
        r2 = r8.f;
        r1.addView(r2, r0);
    L_0x0082:
        r0 = r8.l;
        r0 = r0.i();
        r0 = r0.c();
        if (r0 == 0) goto L_0x0093;
    L_0x008e:
        r1 = r8.f;
        r1.setConnectionTimeoutSeconds(r0);
    L_0x0093:
        r0 = r8.k;
        r0 = r0.i();
        r8.a(r0);
        r0 = r8.k;
        r1 = r0.o();
        r0 = r8.k;
        r2 = r0.p();
        r0 = r8.k;
        r3 = r0.n();
        r0 = r8.k;
        r7 = r0.getCtaText();
        r0 = new com.my.target.ads.MyTargetVideoView$BannerInfo;
        r4 = r6.getWidth();
        r5 = r6.getHeight();
        r0.<init>(r1, r2, r3, r4, r5);
        r8.i = r0;
        r0 = r8.i;
        r0.ctaText = r7;
        r0 = r8.f;
        r0.a(r6);
        goto L_0x0051;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.my.target.core.engines.f.a(int):void");
    }

    private void a(ArrayList<com.my.target.core.models.i> arrayList) {
        this.j = new HashSet();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            com.my.target.core.models.i iVar = (com.my.target.core.models.i) it.next();
            if (iVar.c().equals("playheadReachedValue") && (iVar instanceof g)) {
                this.j.add((g) iVar);
            }
        }
    }

    public final void a(b.a aVar) {
    }

    private void j() {
        this.f = new VideoContainer(this.b);
        this.f.setVideoListener(this.q);
    }

    public final void g() {
        this.n++;
        a(true, MyTargetVideoView.COMPLETE_STATUS_OK, false);
    }

    public final void a(boolean z, String str, boolean z2) {
        String str2;
        this.c = false;
        if (z) {
            this.e.a(this.k, "closedByUser");
        }
        this.f.a(z2);
        if (!(this.d == null || this.d.getListener() == null)) {
            this.d.getListener().onCompleteBanner(this.d, this.i, str);
        }
        if (this.g.size() > 1) {
            int i = this.h + 1;
            this.h = i;
            if (i < this.g.size() && (this.o <= 0 || this.n < this.o)) {
                a(this.h);
                return;
            }
        }
        if (this.n > 0) {
            str2 = MyTargetVideoView.COMPLETE_STATUS_OK;
        } else {
            str2 = "error";
        }
        if (this.d != null) {
            if (this.f != null) {
                this.d.removeView(this.f);
                this.f = null;
            }
            if (this.d.getListener() != null) {
                this.d.getListener().onComplete(this.m, this.d, str2);
            }
        }
    }

    public final void h() {
        if (this.d != null && this.d.getListener() != null && this.f != null && this.f.getParent() != null) {
            this.e.a(this.k);
        }
    }

    public final void i() {
        c();
        this.e.a(this.k, "closedByUser");
    }

    public final void a(boolean z) {
        if (z) {
            this.e.a(this.k, "fullscreenOn");
        } else {
            this.e.a(this.k, "fullscreenOff");
        }
    }

    static /* synthetic */ void a(f fVar, float f) {
        if (!fVar.j.isEmpty()) {
            fVar.e.a(fVar.k, fVar.j, f);
        }
    }
}
