package com.my.target.core.engines;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.facebook.login.widget.ProfilePictureView;
import com.my.target.Tracer;
import com.my.target.core.engines.b.a;
import com.my.target.core.facades.f;
import com.my.target.core.models.banners.i;
import com.my.target.core.models.g;
import com.my.target.core.ui.views.VideoTextureView;
import com.my.target.core.ui.views.fspromo.FSPromoDefaultView;
import com.my.target.core.ui.views.fspromo.FSPromoVideoStyleView;
import com.my.target.core.ui.views.fspromo.FSPromoView;
import com.my.target.core.utils.l;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: FSPromoAdEngine */
public final class e extends a {
    private final f c;
    private FSPromoView d;
    private final Runnable e = new Runnable(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public final void run() {
            if (this.a.d != null) {
                Tracer.d("banner became just closeable");
                this.a.d.f();
            }
        }
    };
    private com.my.target.core.models.banners.e f;
    private a g;
    private final OnClickListener h = new OnClickListener(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public final void onClick(View view) {
            this.a.c.c();
            if (this.a.g != null) {
                boolean z = this.a.f.k() == null && this.a.f.u();
                this.a.g.onClick(z);
            }
        }
    };
    private HashSet<g> i;
    private i j;
    private boolean k;
    private final OnClickListener l = new OnClickListener(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public final void onClick(View view) {
            if (this.a.k) {
                this.a.i();
                this.a.c.a(this.a.j, "volumeOn");
                this.a.k = false;
                return;
            }
            this.a.h();
            this.a.c.a(this.a.j, "volumeOff");
            this.a.k = true;
        }
    };
    private final OnAudioFocusChangeListener m = new OnAudioFocusChangeListener(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public final void onAudioFocusChange(int i) {
            switch (i) {
                case ProfilePictureView.NORMAL /*-3*/:
                    Tracer.d("Audiofocus loss can duck, set volume to 0.3");
                    if (!this.a.k) {
                        this.a.j();
                        return;
                    }
                    return;
                case -2:
                case -1:
                    this.a.a();
                    Tracer.d("Audiofocus loss, pausing");
                    return;
                case 1:
                case 2:
                case 4:
                    Tracer.d("Audiofocus gain, unmuting");
                    if (!this.a.k) {
                        this.a.i();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private final FSPromoView.a n = new FSPromoView.a(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public final void a() {
            ((AudioManager) this.a.b.getSystemService("audio")).requestAudioFocus(this.a.m, 3, 2);
            this.a.d.c();
        }

        public final void b() {
            this.a.c.a(this.a.j, "playbackPaused");
            this.a.d.h();
        }

        public final void c() {
            this.a.c.a(this.a.j, "playbackResumed");
            ((AudioManager) this.a.b.getSystemService("audio")).requestAudioFocus(this.a.m, 3, 2);
            this.a.d.d();
            if (this.a.k) {
                this.a.h();
            } else {
                this.a.i();
            }
        }
    };
    private boolean o;
    private boolean p;
    private final OnClickListener q = new OnClickListener(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public final void onClick(View view) {
            if (this.a.p) {
                if (this.a.d != null) {
                    this.a.a(this.a.b);
                    this.a.d.a(true);
                }
                this.a.c.a(this.a.j, "closedByUser");
            }
            if (this.a.g != null) {
                this.a.g.onCloseClick();
            }
        }
    };
    private float r;
    private float s;
    private long t;
    private boolean u = true;
    private final VideoTextureView.a v = new VideoTextureView.a(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public final void e() {
        }

        public final void a(boolean z) {
            this.a.d.b(z);
        }

        public final void g() {
        }

        public final void h() {
        }

        public final void a(float f, float f2) {
            while (true) {
                this.a.d.setTimeChanged(f, f2);
                if (!this.a.p) {
                    e.a(this.a, this.a.j.i());
                    this.a.c.a(this.a.j, "playbackStarted");
                    e.a(this.a, 0.0f);
                    this.a.p = true;
                }
                if (this.a.o && this.a.r <= f) {
                    this.a.d.f();
                }
                if (f <= this.a.s) {
                    break;
                }
                f = this.a.s;
                f2 = this.a.s;
            }
            if (f != 0.0f) {
                e.a(this.a, f);
            }
            if (f == this.a.s) {
                this.a.u = true;
                e.m(this.a);
                this.a.c.e();
                this.a.d.e();
            }
        }

        public final void a(String str) {
            Tracer.d("Video playing error: " + str);
            e.m(this.a);
            this.a.d.f();
        }
    };

    public final boolean g() {
        return this.u;
    }

    public final void a(a aVar) {
        this.g = aVar;
    }

    public e(f fVar, ViewGroup viewGroup, Context context) {
        super(viewGroup, context);
        this.c = fVar;
        this.f = this.c.d();
        if (this.f != null) {
            FSPromoView fSPromoVideoStyleView;
            com.my.target.core.models.banners.e eVar = this.f;
            Context context2 = this.b;
            if (l.b(14) && eVar.k() != null && eVar.v() == 1) {
                fSPromoVideoStyleView = new FSPromoVideoStyleView(context2);
            } else {
                fSPromoVideoStyleView = new FSPromoDefaultView(context2);
            }
            this.d = fSPromoVideoStyleView;
            this.d.setCloseListener(this.q);
            this.d.setVideoListener(this.v);
            this.d.setBanner(this.f);
            this.a.addView(this.d, new LayoutParams(-1, -1));
            this.j = this.f.k();
            if (this.j != null) {
                int i;
                this.u = this.j.k();
                if (this.j.q()) {
                    this.t = -1;
                }
                this.o = this.j.o();
                this.r = this.j.p();
                if (this.o && this.r == 0.0f) {
                    Tracer.d("banner is allowed to close");
                    this.d.f();
                }
                this.s = this.j.n();
                this.d.setOnVideoClickListener(this.n);
                this.k = this.j.m();
                FSPromoView fSPromoView = this.d;
                if (this.k) {
                    i = 0;
                } else {
                    i = 2;
                }
                fSPromoView.a(i);
            } else if (this.f.s() > 0.0f) {
                Tracer.d("banner will be allowed to close in " + this.f.s() + " seconds");
                a((long) (this.f.s() * 1000.0f));
            } else {
                Tracer.d("banner is allowed to close");
                this.d.f();
            }
            this.c.b();
            this.d.setOnCTAClickListener(this.h);
        }
        if (this.d.b() != null) {
            this.d.b().setOnClickListener(this.l);
        }
    }

    private void a(long j) {
        this.d.removeCallbacks(this.e);
        this.t = System.currentTimeMillis() + j;
        this.d.postDelayed(this.e, j);
    }

    public final void h() {
        this.d.a(0);
    }

    public final void i() {
        this.d.a(2);
    }

    public final void j() {
        this.d.a(1);
    }

    private void a(Context context) {
        ((AudioManager) context.getSystemService("audio")).abandonAudioFocus(this.m);
    }

    public final void a() {
        super.a();
        if (this.d != null) {
            if (this.d.g() && !this.d.a()) {
                this.c.a(this.j, "playbackPaused");
                this.d.h();
            }
            this.d.removeCallbacks(this.e);
        }
    }

    public final void b() {
        super.b();
        if (this.d != null) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.t == -1) {
                return;
            }
            if (currentTimeMillis >= this.t) {
                this.d.f();
            } else {
                a(this.t - currentTimeMillis);
            }
        }
    }

    public final void e() {
        super.e();
        this.c.f();
    }

    static /* synthetic */ void a(e eVar, ArrayList arrayList) {
        if (eVar.i != null) {
            eVar.i.clear();
        } else {
            eVar.i = new HashSet();
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            com.my.target.core.models.i iVar = (com.my.target.core.models.i) it.next();
            if (iVar.c().equals("playheadReachedValue") && (iVar instanceof g)) {
                eVar.i.add((g) iVar);
            }
        }
    }

    static /* synthetic */ void a(e eVar, float f) {
        if (!eVar.i.isEmpty() && eVar.j != null) {
            eVar.c.a(eVar.j, eVar.i, f);
        }
    }

    static /* synthetic */ void m(e eVar) {
        eVar.p = false;
        eVar.d.f();
        boolean z = true;
        if (eVar.j != null) {
            z = eVar.j.l();
        }
        eVar.a(eVar.b);
        eVar.d.a(z);
    }
}
