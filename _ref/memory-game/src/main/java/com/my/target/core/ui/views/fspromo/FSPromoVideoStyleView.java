package com.my.target.core.ui.views.fspromo;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mopub.mobileads.resource.DrawableConstants;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import com.mopub.volley.DefaultRetryPolicy;
import com.my.target.core.models.banners.e;
import com.my.target.core.ui.views.FramedCacheImageView;
import com.my.target.core.ui.views.VideoProgressWheel;
import com.my.target.core.ui.views.VideoSeekBar;
import com.my.target.core.ui.views.VideoTextureView;
import com.my.target.core.ui.views.controls.IconButton;
import com.my.target.core.ui.views.fspromo.FSPromoView.a;
import com.my.target.core.utils.l;
import com.my.target.nativeads.banners.NavigationType;
import com.my.target.nativeads.views.StarsRatingView;

public class FSPromoVideoStyleView extends FSPromoView {
    private static final int a = l.b();
    private static final int b = l.b();
    private static final int c = l.b();
    private static final int d = l.b();
    private static final int e = l.b();
    private static final int f = l.b();
    private static final int g = l.b();
    private static final int h = l.b();
    private static final int i = l.b();
    private static final int j = l.b();
    private static final int k = l.b();
    private final TextView A;
    private final FrameLayout B;
    private final FrameLayout C;
    private final VideoSeekBar D;
    private final TextView E;
    private final TextView F;
    private final RelativeLayout G;
    private final VideoProgressWheel H;
    private final IconButton I;
    private final FramedCacheImageView J;
    private final FramedCacheImageView K;
    private final FramedCacheImageView L;
    private int M;
    private final Runnable N = new Runnable(this) {
        final /* synthetic */ FSPromoVideoStyleView a;

        {
            this.a = r1;
        }

        public final void run() {
            if (this.a.M == 2 || this.a.M == 0) {
                this.a.l();
            }
        }
    };
    private boolean O;
    private final OnClickListener P = new OnClickListener(this) {
        final /* synthetic */ FSPromoVideoStyleView a;

        {
            this.a = r1;
        }

        public final void onClick(View view) {
            this.a.removeCallbacks(this.a.N);
            if (this.a.M == 2) {
                this.a.l();
                return;
            }
            if (this.a.M == 0 || this.a.M == 3) {
                FSPromoVideoStyleView.d(this.a);
            }
            this.a.postDelayed(this.a.N, 4000);
        }
    };
    private final OnClickListener Q = new OnClickListener(this) {
        final /* synthetic */ FSPromoVideoStyleView a;

        {
            this.a = r1;
        }

        public final void onClick(View view) {
            int id = view.getId();
            if (id == FSPromoVideoStyleView.d) {
                if (this.a.T != null) {
                    this.a.T.a();
                }
                this.a.l();
            } else if (id == FSPromoVideoStyleView.f) {
                if (this.a.T != null) {
                    this.a.T.b();
                }
                this.a.m();
            } else if (id == FSPromoVideoStyleView.e) {
                if (this.a.T != null) {
                    if (this.a.a()) {
                        this.a.T.c();
                    } else {
                        this.a.T.a();
                    }
                }
                this.a.l();
            }
        }
    };
    private float R;
    private boolean S;
    private a T;
    private boolean U;
    private final Button l;
    private final TextView m;
    private final StarsRatingView n;
    private final Button o;
    private final TextView p;
    private final StarsRatingView q;
    private final Button r;
    private final l s;
    private final RelativeLayout t;
    private final LinearLayout u;
    private final LinearLayout v;
    private final TextView w;
    private final FrameLayout x;
    private final FSPromoMediaView y;
    private final TextView z;

    static /* synthetic */ void d(FSPromoVideoStyleView fSPromoVideoStyleView) {
        fSPromoVideoStyleView.M = 2;
        fSPromoVideoStyleView.v.setVisibility(8);
        fSPromoVideoStyleView.K.setVisibility(8);
        fSPromoVideoStyleView.J.setVisibility(0);
        fSPromoVideoStyleView.x.setVisibility(8);
        if (fSPromoVideoStyleView.O) {
            fSPromoVideoStyleView.G.setVisibility(0);
        }
    }

    public void setBanner(e eVar) {
        super.setBanner(eVar);
        this.y.a(eVar);
        this.U = eVar.k().l();
        this.R = eVar.k().p();
        this.S = eVar.k().o();
        this.l.setText(eVar.getCtaText());
        this.o.setText(eVar.getCtaText());
        this.m.setText(eVar.getTitle());
        this.p.setText(eVar.getTitle());
        this.H.setMax(eVar.k().p());
        if (NavigationType.STORE.equals(eVar.getNavigationType())) {
            this.z.setVisibility(8);
            this.A.setVisibility(8);
            if (eVar.getVotes() == 0 || eVar.getRating() <= 0.0f) {
                this.n.setVisibility(8);
                this.q.setVisibility(8);
            } else {
                this.q.setVisibility(0);
                this.n.setVisibility(0);
                this.n.setRating(eVar.getRating());
                this.q.setRating(eVar.getRating());
            }
        } else {
            this.n.setVisibility(8);
            this.q.setVisibility(8);
            this.z.setVisibility(0);
            this.A.setVisibility(0);
            this.A.setText(eVar.getDomain());
            this.z.setText(eVar.getDomain());
        }
        this.r.setText(eVar.k().s());
        this.w.setText(eVar.k().t());
        this.L.setImageBitmap(com.my.target.core.resources.a.c(getContext()));
        if (eVar.k().q()) {
            this.y.b();
            l();
        } else {
            m();
        }
        this.O = eVar.k().v();
    }

    private void l() {
        this.M = 0;
        this.v.setVisibility(8);
        this.K.setVisibility(8);
        this.J.setVisibility(8);
        this.x.setVisibility(8);
        this.G.setVisibility(8);
    }

    private void m() {
        this.M = 1;
        this.v.setVisibility(8);
        this.K.setVisibility(0);
        this.J.setVisibility(8);
        this.x.setVisibility(0);
        if (this.O) {
            this.G.setVisibility(0);
        }
    }

    public final void f() {
        this.r.setVisibility(0);
        this.H.setVisibility(8);
    }

    public final boolean g() {
        return this.y.c();
    }

    public final boolean a() {
        return this.y.d();
    }

    public final IconButton b() {
        return this.I;
    }

    public final void c() {
        n();
        this.y.b();
    }

    private void n() {
        this.v.setVisibility(8);
        this.K.setVisibility(8);
        if (this.M != 2) {
            this.J.setVisibility(8);
        }
    }

    public final void e() {
        this.M = 4;
        if (this.U) {
            this.v.setVisibility(0);
        }
        this.K.setVisibility(8);
        this.J.setVisibility(8);
        this.x.setVisibility(0);
        this.G.setVisibility(8);
    }

    public void setTimeChanged(float f, float f2) {
        this.E.setText(l.a(f));
        this.F.setText("−" + l.a(f2 - f));
        this.D.setMax((int) (f2 * 1000.0f));
        this.D.setProgress((int) (f * 1000.0f));
        if (this.S && this.R != 0.0f && this.R >= f && this.r.getVisibility() != 0) {
            this.H.setProgress(f / this.R);
            this.H.setDigit((int) ((this.R - f) + DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            this.H.setVisibility(0);
        }
    }

    public void setCloseListener(OnClickListener onClickListener) {
        this.r.setOnClickListener(onClickListener);
    }

    public void setVideoListener(VideoTextureView.a aVar) {
        this.y.setVideoListener(aVar);
    }

    public final void d() {
        n();
        this.y.e();
    }

    public void setOnVideoClickListener(a aVar) {
        this.T = aVar;
    }

    public void setOnCTAClickListener(OnClickListener onClickListener) {
        this.l.setOnClickListener(onClickListener);
        this.o.setOnClickListener(onClickListener);
    }

    public final void a(boolean z) {
        this.y.a(true);
    }

    public final void h() {
        m();
        this.y.f();
    }

    public final void a(int i) {
        this.y.a(i);
    }

    protected final void b(int i) {
        super.b(i);
        LayoutParams layoutParams;
        if (i == 2) {
            this.u.setVisibility(8);
            this.t.setVisibility(0);
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(12, -1);
            layoutParams.bottomMargin = this.s.a(40);
            layoutParams.rightMargin = this.s.a(6);
            if (l.b(17)) {
                layoutParams.addRule(21, -1);
            } else {
                layoutParams.addRule(11, -1);
            }
            this.I.setLayoutParams(layoutParams);
            return;
        }
        this.u.setVisibility(0);
        this.t.setVisibility(8);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10, -1);
        if (l.b(17)) {
            layoutParams.addRule(21, -1);
        } else {
            layoutParams.addRule(11, -1);
        }
        this.I.setLayoutParams(layoutParams);
    }

    public FSPromoVideoStyleView(Context context) {
        super(context);
        this.r = new Button(context);
        this.l = new Button(context);
        this.m = new TextView(context);
        this.n = new StarsRatingView(context);
        this.o = new Button(context);
        this.p = new TextView(context);
        this.q = new StarsRatingView(context);
        this.w = new TextView(context);
        this.x = new FrameLayout(context);
        this.J = new FramedCacheImageView(context);
        this.K = new FramedCacheImageView(context);
        this.L = new FramedCacheImageView(context);
        this.z = new TextView(context);
        this.B = new FrameLayout(context);
        this.C = new FrameLayout(context);
        this.A = new TextView(context);
        this.D = new VideoSeekBar(context);
        this.E = new TextView(context);
        this.F = new TextView(context);
        this.G = new RelativeLayout(context);
        this.y = new FSPromoMediaView(context, new l(context), false);
        this.H = new VideoProgressWheel(context);
        this.I = new IconButton(context);
        this.t = new RelativeLayout(context);
        this.u = new LinearLayout(context);
        this.v = new LinearLayout(context);
        this.s = new l(context);
        setBackgroundColor(-16777216);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13, -1);
        this.y.setLayoutParams(layoutParams);
        this.y.setId(h);
        this.y.setOnClickListener(this.P);
        this.y.setBackgroundColor(-16777216);
        this.y.a();
        this.x.setContentDescription("vdsha");
        layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.x.setBackgroundColor(-1728053248);
        this.x.setVisibility(8);
        this.x.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(this.s.a(16), this.s.a(16), this.s.a(16), this.s.a(16));
        this.r.setLayoutParams(layoutParams);
        this.r.setId(a);
        this.r.setMaxWidth(this.s.a(200));
        this.r.setContentDescription("vddb");
        this.r.setTextColor(-1);
        this.r.setTextSize(2, 16.0f);
        this.r.setTransformationMethod(null);
        this.r.setSingleLine();
        this.r.setEllipsize(TruncateAt.END);
        this.r.setVisibility(8);
        this.m.setContentDescription("vdth");
        this.m.setSingleLine();
        this.m.setEllipsize(TruncateAt.END);
        this.m.setTextSize(2, RadialCountdown.TEXT_SIZE_SP);
        this.m.setTextColor(-1);
        this.p.setContentDescription("vdtv");
        this.p.setSingleLine();
        this.p.setEllipsize(TruncateAt.END);
        this.p.setTextSize(2, RadialCountdown.TEXT_SIZE_SP);
        this.p.setTextColor(-1);
        this.p.setGravity(14);
        l.a(this.l, DrawableConstants.TRANSPARENT_GRAY, -1, -1, this.s.a(1), this.s.a(4));
        l.a(this.o, DrawableConstants.TRANSPARENT_GRAY, -1, -1, this.s.a(1), this.s.a(4));
        l.a(this.r, DrawableConstants.TRANSPARENT_GRAY, -1, -1, this.s.a(1), this.s.a(4));
        this.l.setId(b);
        this.l.setTextColor(-1);
        this.l.setTransformationMethod(null);
        this.l.setSingleLine();
        this.l.setEllipsize(TruncateAt.END);
        this.l.setTextSize(2, 16.0f);
        this.l.setMaxWidth(this.s.a(200));
        this.l.setMinimumWidth(this.s.a(100));
        this.l.setPadding(this.s.a(8), 0, this.s.a(8), 0);
        this.o.setId(c);
        this.o.setTextColor(-1);
        this.o.setTransformationMethod(null);
        this.o.setTextSize(2, 16.0f);
        this.o.setMinimumWidth(this.s.a(100));
        this.o.setPadding(this.s.a(8), 0, this.s.a(8), 0);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(1, a);
        layoutParams.setMargins(this.s.a(16), this.s.a(16), this.s.a(16), this.s.a(16));
        this.t.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(3, h);
        this.u.setGravity(1);
        this.u.setLayoutParams(layoutParams);
        this.u.setOrientation(1);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(0, g);
        layoutParams.addRule(15, -1);
        layoutParams.setMargins(this.s.a(8), 0, this.s.a(8), 0);
        this.m.setLayoutParams(layoutParams);
        this.m.setShadowLayer((float) this.s.a(1), (float) this.s.a(1), (float) this.s.a(1), -16777216);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(0, b);
        layoutParams.addRule(15, -1);
        layoutParams.setMargins(this.s.a(4), this.s.a(3), this.s.a(8), this.s.a(4));
        this.B.setLayoutParams(layoutParams);
        this.B.setId(g);
        this.C.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        layoutParams = new FrameLayout.LayoutParams(this.s.a(73), this.s.a(12));
        this.n.setContentDescription("vdrh");
        this.n.setLayoutParams(layoutParams);
        layoutParams = new FrameLayout.LayoutParams(this.s.a(73), this.s.a(12));
        this.q.setContentDescription("vdrv");
        this.q.setLayoutParams(layoutParams);
        this.z.setContentDescription("vddoh");
        this.z.setTextColor(-3355444);
        this.z.setShadowLayer((float) this.s.a(1), (float) this.s.a(1), (float) this.s.a(1), -16777216);
        this.A.setContentDescription("vddov");
        this.A.setTextColor(-3355444);
        this.A.setShadowLayer((float) this.s.a(1), (float) this.s.a(1), (float) this.s.a(1), -16777216);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(11);
        this.l.setLayoutParams(layoutParams);
        layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 1;
        layoutParams.setMargins(this.s.a(8), this.s.a(8), this.s.a(8), this.s.a(8));
        this.p.setLayoutParams(layoutParams);
        layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 1;
        layoutParams.setMargins(this.s.a(8), this.s.a(16), this.s.a(8), this.s.a(8));
        this.o.setLayoutParams(layoutParams);
        this.v.setId(d);
        this.v.setContentDescription("vdrep");
        this.v.setOnClickListener(this.Q);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13, -1);
        this.v.setLayoutParams(layoutParams);
        this.v.setGravity(17);
        this.v.setVisibility(8);
        this.v.setPadding(this.s.a(8), 0, this.s.a(8), 0);
        layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 16;
        layoutParams.setMargins(this.s.a(8), 0, 0, 0);
        this.w.setLayoutParams(layoutParams);
        this.w.setSingleLine();
        this.w.setMaxWidth(this.s.a(200));
        this.w.setEllipsize(TruncateAt.END);
        this.w.setTypeface(this.w.getTypeface(), 1);
        this.w.setTextColor(-1);
        this.w.setTextSize(2, 16.0f);
        layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 16;
        this.L.setLayoutParams(layoutParams);
        this.L.setPadding(this.s.a(16), this.s.a(16), this.s.a(16), this.s.a(16));
        this.J.setId(f);
        this.J.setContentDescription("vdpab");
        this.J.setOnClickListener(this.Q);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(13, -1);
        this.J.setVisibility(8);
        this.J.setPadding(this.s.a(16), this.s.a(16), this.s.a(16), this.s.a(16));
        this.K.setId(e);
        this.K.setContentDescription("vdplb");
        this.K.setOnClickListener(this.Q);
        LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(13, -1);
        this.K.setVisibility(8);
        this.K.setPadding(this.s.a(16), this.s.a(16), this.s.a(16), this.s.a(16));
        new FrameLayout.LayoutParams(-2, -2).gravity = 17;
        this.K.setImageBitmap(com.my.target.core.resources.a.a(getContext()));
        this.J.setImageBitmap(com.my.target.core.resources.a.b(getContext()));
        l.a(this.J, DrawableConstants.TRANSPARENT_GRAY, -1, -1, this.s.a(1), this.s.a(4));
        l.a(this.K, DrawableConstants.TRANSPARENT_GRAY, -1, -1, this.s.a(1), this.s.a(4));
        l.a(this.L, DrawableConstants.TRANSPARENT_GRAY, -1, -1, this.s.a(1), this.s.a(4));
        this.E.setId(i);
        this.E.setContentDescription("vdela");
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15, -1);
        layoutParams.rightMargin = this.s.a(8);
        this.E.setLayoutParams(layoutParams);
        this.E.setTextSize(2, 12.0f);
        this.E.setIncludeFontPadding(false);
        this.E.setTextColor(-1);
        this.E.setShadowLayer((float) this.s.a(1), (float) this.s.a(1), (float) this.s.a(1), -16777216);
        this.F.setId(j);
        this.F.setContentDescription("vdrem");
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(11, -1);
        layoutParams.addRule(15, -1);
        layoutParams.leftMargin = this.s.a(8);
        this.F.setTextSize(2, 12.0f);
        this.F.setLayoutParams(layoutParams);
        this.F.setTextColor(-1);
        this.F.setIncludeFontPadding(false);
        this.F.setGravity(16);
        this.F.setShadowLayer((float) this.s.a(1), (float) this.s.a(1), (float) this.s.a(1), -16777216);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(15, -1);
        layoutParams.addRule(1, i);
        layoutParams.addRule(0, j);
        this.D.setLayoutParams(layoutParams);
        this.D.setHeight(this.s.a(2));
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(8, h);
        this.G.setId(k);
        this.G.setLayoutParams(layoutParams);
        this.G.setPadding(this.s.a(16), this.s.a(8), this.s.a(16), this.s.a(8));
        layoutParams = new RelativeLayout.LayoutParams(this.s.a(28), this.s.a(28));
        layoutParams.addRule(9);
        layoutParams.topMargin = this.s.a(16);
        layoutParams.leftMargin = this.s.a(16);
        this.H.setLayoutParams(layoutParams);
        this.H.setVisibility(8);
        this.y.addView(this.x);
        this.y.addView(this.I);
        this.G.addView(this.E);
        this.G.addView(this.F);
        this.G.addView(this.D);
        this.G.setVisibility(8);
        addView(this.y);
        addView(this.r);
        addView(this.H);
        addView(this.t);
        addView(this.u);
        addView(this.v);
        addView(this.J, layoutParams2);
        addView(this.K, layoutParams3);
        addView(this.G);
        this.t.addView(this.l);
        this.t.addView(this.B);
        this.B.addView(this.n);
        this.B.addView(this.z);
        this.t.addView(this.m);
        this.u.addView(this.p);
        this.u.addView(this.C);
        this.C.addView(this.q);
        this.C.addView(this.A);
        this.u.addView(this.o);
        this.v.addView(this.L);
        this.v.addView(this.w);
    }
}
