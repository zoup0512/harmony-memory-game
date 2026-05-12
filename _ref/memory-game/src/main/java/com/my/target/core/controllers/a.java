package com.my.target.core.controllers;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import com.facebook.login.widget.ProfilePictureView;
import com.my.target.Tracer;
import com.my.target.core.models.banners.f;
import com.my.target.core.models.g;
import com.my.target.core.models.i;
import com.my.target.core.ui.b;
import com.my.target.core.ui.views.VideoTextureView;
import com.my.target.nativeads.models.VideoData;
import com.my.target.nativeads.views.MediaAdView;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: NativeAdVideoController */
public final class a implements com.my.target.core.ui.views.VideoTextureView.a {
    private final f a;
    private final VideoData b;
    private MediaAdView c;
    private int d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private OnClickListener i;
    private b j;
    private HashSet<g> k;
    private VideoTextureView l;
    private boolean m;
    private final OnClickListener n = new OnClickListener(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public final void onClick(View view) {
            if (this.a.l == null) {
                return;
            }
            if (this.a.l.c()) {
                this.a.l.e();
                if (this.a.p != null) {
                    this.a.p.g();
                }
                this.a.m = false;
                return;
            }
            this.a.l.h();
            if (this.a.p != null) {
                this.a.p.f();
            }
            this.a.m = true;
        }
    };
    private final OnAudioFocusChangeListener o = new OnAudioFocusChangeListener(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public final void onAudioFocusChange(int i) {
            switch (i) {
                case ProfilePictureView.NORMAL /*-3*/:
                    a.f(this.a);
                    return;
                case -2:
                case -1:
                    a.c(this.a);
                    Tracer.d("Audiofocus loss, pausing");
                    return;
                case 1:
                case 2:
                case 4:
                    if (this.a.g) {
                        Tracer.d("Audiofocus gain, unmuting");
                        this.a.j();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private a p;
    private final OnDismissListener q = new OnDismissListener(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public final void onDismiss(DialogInterface dialogInterface) {
            Tracer.d("Dismiss dialog");
            if (this.a.j != null) {
                this.a.a(this.a.j.getContext());
            }
            this.a.g = false;
            this.a.i();
            if (this.a.l != null) {
                ViewGroup viewGroup = (ViewGroup) this.a.l.getParent();
                if (!(viewGroup == null || viewGroup == this.a.c)) {
                    viewGroup.removeView(this.a.l);
                    this.a.c.addView(this.a.l, 0, new LayoutParams(-1, -1));
                }
                if (this.a.d == 1) {
                    this.a.f();
                    if (this.a.a.k().q()) {
                        this.a.f = true;
                    }
                    this.a.l.setWaitingState();
                } else if (this.a.d == 3) {
                    this.a.f = false;
                    this.a.e();
                    this.a.l.d();
                } else {
                    this.a.f = false;
                }
            }
            if (this.a.p != null) {
                this.a.p.c();
            }
            this.a.j = null;
        }
    };
    private final com.my.target.core.ui.b.a r = new com.my.target.core.ui.b.a(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public final void a() {
            if (this.a.c != null) {
                this.a.d = 1;
                if (this.a.l == null) {
                    this.a.l = VideoTextureView.a(this.a, this.a.c.getContext());
                }
                this.a.b(this.a.c.getContext());
                this.a.l.a(this.a.b, true);
                this.a.h();
                if (this.a.p != null) {
                    this.a.p.e();
                }
            }
        }

        public final void b() {
            a();
            this.a.j.f();
        }

        public final void a(View view) {
            if (this.a.d == 1) {
                a.c(this.a);
            }
            if (this.a.i != null) {
                this.a.i.onClick(view);
            }
        }

        public final void c() {
            a.c(this.a);
            this.a.d = 2;
            if (this.a.p != null) {
                this.a.p.d();
            }
        }
    };

    /* compiled from: NativeAdVideoController */
    public interface a {
        void a();

        void a(float f, HashSet<g> hashSet);

        void b();

        void c();

        void d();

        void e();

        void f();

        void g();
    }

    static /* synthetic */ void p(a aVar) {
        if (aVar.c == null) {
            return;
        }
        if (aVar.b != null || aVar.i == null) {
            aVar.g = true;
            Context context = aVar.c.getContext();
            b bVar = new b(context);
            bVar.a(aVar.a, aVar.b);
            bVar.a(aVar.r);
            bVar.setOnDismissListener(aVar.q);
            if (aVar.l == null) {
                aVar.l = VideoTextureView.a(aVar, context);
            }
            aVar.l.setVideoListener(aVar);
            aVar.b(context);
            if (aVar.d == 1) {
                aVar.d = 4;
                aVar.l.f();
            }
            ViewGroup viewGroup = (ViewGroup) aVar.l.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(aVar.l);
            }
            bVar.a(aVar.l);
            aVar.j = bVar;
            aVar.j.a().setOnClickListener(aVar.n);
            if (aVar.m) {
                aVar.i();
            } else {
                aVar.j();
            }
            aVar.j.show();
            if (aVar.p != null) {
                aVar.p.b();
            }
            if (aVar.l != null) {
                aVar.l.a(aVar.b, true);
                aVar.d = 1;
                return;
            }
            return;
        }
        aVar.i.onClick(aVar.c);
    }

    public final void a(OnClickListener onClickListener) {
        this.i = onClickListener;
    }

    public final <T> void a(a aVar) {
        this.p = aVar;
    }

    public a(f fVar, VideoData videoData) {
        this.a = fVar;
        this.b = videoData;
        this.f = fVar.k().q();
        this.m = fVar.k().m();
        this.k = new HashSet();
        Iterator it = fVar.k().i().iterator();
        while (it.hasNext()) {
            i iVar = (i) it.next();
            if ("playheadReachedValue".equals(iVar.c()) && (iVar instanceof g)) {
                this.k.add((g) iVar);
            }
        }
    }

    public final void a() {
        if (this.f && !this.g) {
            if ((this.d == 0 || this.d == 2 || this.d == 4) && this.c != null) {
                Tracer.d("Handle visible, state = " + this.d + " url = " + this.b.getUrl());
                if (this.l == null) {
                    this.h = false;
                    this.l = VideoTextureView.a(this, this.c.getContext());
                    this.l.setVideoListener(this);
                    this.c.addView(this.l, 0, new LayoutParams(-1, -1));
                }
                i();
                this.l.a(this.b, false);
                this.d = 1;
            }
        }
    }

    private void i() {
        if (this.l != null) {
            this.l.h();
        }
    }

    public final void a(boolean z) {
        if (this.j != null) {
            this.j.a(z);
        }
    }

    public final void a(MediaAdView mediaAdView) {
        b();
        this.c = mediaAdView;
        if (!this.g) {
            if (this.f) {
                h();
            } else {
                e();
            }
        }
        if (this.i != null) {
            mediaAdView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public final void onClick(View view) {
                    a.p(this.a);
                }
            });
        }
    }

    public final void b() {
        Tracer.d("unregister from " + this);
        if (!this.g && this.c != null) {
            d();
            if (this.l != null) {
                c();
            }
            this.c.setOnClickListener(null);
            this.c = null;
        }
    }

    public final void c() {
        Tracer.d("Call release texture view on " + this);
        if (this.l != null) {
            this.l.setVideoListener(null);
            if (this.l.getParent() != null) {
                ((ViewGroup) this.l.getParent()).removeView(this.l);
            }
        }
        e();
        this.l = null;
        VideoTextureView.a(this);
    }

    public final void d() {
        if (!this.g) {
            if (this.d == 1) {
                if (this.f) {
                    Tracer.d("Handle invisible, state = " + this.d + " obj = " + this);
                    this.d = 2;
                    if (this.l != null) {
                        this.l.f();
                        this.d = 4;
                        return;
                    }
                    return;
                }
                e();
                this.d = 3;
                if (this.l != null) {
                    this.l.d();
                }
            } else if (this.d != 4) {
                e();
            }
        }
    }

    public final void e() {
        Context context = null;
        if (this.c != null) {
            if (this.a.getImage() != null) {
                this.c.getImageView().setImageBitmap(this.a.getImage().getBitmap());
            }
            this.c.getImageView().setVisibility(0);
            this.c.getPlayButtonView().setVisibility(0);
            this.c.getProgressBarView().setVisibility(8);
            context = this.c.getContext();
        }
        if (this.g && this.j != null) {
            this.j.b();
            if (context == null) {
                context = this.j.getContext();
            }
        }
        if (context != null) {
            a(context);
        }
    }

    public final void f() {
        this.c.getImageView().setVisibility(4);
        this.c.getProgressBarView().setVisibility(8);
        this.c.getPlayButtonView().setVisibility(8);
        if (this.g && this.j != null) {
            this.j.c();
        }
    }

    private void a(Context context) {
        ((AudioManager) context.getSystemService("audio")).abandonAudioFocus(this.o);
    }

    public final void g() {
        Context context = null;
        if (this.c != null) {
            if (this.l != null && this.l.a() != null) {
                this.c.getImageView().setImageBitmap(this.l.a());
            } else if (this.a.getImage() != null) {
                this.c.getImageView().setImageBitmap(this.a.getImage().getBitmap());
            }
            this.c.getImageView().setVisibility(0);
            this.c.getPlayButtonView().setVisibility(0);
            this.c.getProgressBarView().setVisibility(8);
            context = this.c.getContext();
        }
        if (this.g && this.j != null) {
            this.j.e();
            if (context == null) {
                context = this.j.getContext();
            }
        }
        if (context != null) {
            a(context);
        }
    }

    private void a(float f) {
        if (!this.k.isEmpty() && this.p != null) {
            this.p.a(f, this.k);
        }
    }

    public final void h() {
        this.c.getProgressBarView().setVisibility(0);
        this.c.getPlayButtonView().setVisibility(8);
        if (this.g && this.j != null) {
            this.j.d();
        }
    }

    private void j() {
        if (this.l != null) {
            this.l.e();
        }
    }

    private void b(Context context) {
        ((AudioManager) context.getSystemService("audio")).requestAudioFocus(this.o, 3, 2);
    }

    public final void a(float f, float f2) {
        while (true) {
            f();
            if (!(this.h || this.p == null)) {
                this.p.a();
                this.h = true;
                a(0.0f);
            }
            if (this.e && f != r5) {
                this.e = false;
            }
            if (this.a.k() != null) {
                f2 = this.a.k().n();
                if (this.j != null) {
                    this.j.a(f, f2);
                }
            } else {
                f2 = 0.0f;
            }
            if (f <= f2) {
                break;
            }
            f = f2;
        }
        if (f != 0.0f) {
            a(f);
        }
        if (f == f2) {
            e();
            this.d = 3;
            this.f = false;
            if (this.l != null) {
                this.l.d();
            }
        }
    }

    public final void a(String str) {
        this.d = 3;
        e();
    }

    static /* synthetic */ void c(a aVar) {
        if (aVar.g && aVar.j != null) {
            aVar.d = 2;
            if (aVar.l != null) {
                aVar.l.a(true);
            }
            aVar.g();
        }
    }

    static /* synthetic */ void f(a aVar) {
        if (aVar.l != null) {
            Tracer.d("Audiofocus loss can duck, set volume to 0.3");
            if (!aVar.m) {
                aVar.l.g();
            }
        }
    }
}
