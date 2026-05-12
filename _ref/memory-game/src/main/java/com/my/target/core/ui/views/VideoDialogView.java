package com.my.target.core.ui.views;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.MeasureSpec;
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
import com.my.target.core.models.banners.f;
import com.my.target.core.ui.b.a;
import com.my.target.core.ui.views.controls.IconButton;
import com.my.target.core.utils.l;
import com.my.target.nativeads.banners.NavigationType;
import com.my.target.nativeads.factories.NativeViewsFactory;
import com.my.target.nativeads.models.VideoData;
import com.my.target.nativeads.views.MediaAdView;
import com.my.target.nativeads.views.StarsRatingView;

public class VideoDialogView extends RelativeLayout implements OnClickListener {
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
    private final FrameLayout A;
    private final FrameLayout B;
    private final VideoSeekBar C;
    private final TextView D;
    private final TextView E;
    private final RelativeLayout F;
    private final IconButton G;
    private final FramedCacheImageView H;
    private final FramedCacheImageView I;
    private final FramedCacheImageView J;
    private int K;
    private int L;
    private final Runnable M = new Runnable(this) {
        final /* synthetic */ VideoDialogView a;

        {
            this.a = r1;
        }

        public final void run() {
            if (this.a.L == 2 || this.a.L == 0) {
                this.a.c();
            }
        }
    };
    private a N;
    private boolean O;
    private final OnClickListener P = new OnClickListener(this) {
        final /* synthetic */ VideoDialogView a;

        {
            this.a = r1;
        }

        public final void onClick(View view) {
            this.a.removeCallbacks(this.a.M);
            if (this.a.L == 2) {
                this.a.c();
                return;
            }
            if (this.a.L == 0 || this.a.L == 3) {
                this.a.g();
            }
            this.a.postDelayed(this.a.M, 4000);
        }
    };
    private final Button k;
    private final TextView l;
    private final StarsRatingView m;
    private final Button n;
    private final TextView o;
    private final StarsRatingView p;
    private final Button q;
    private final l r;
    private final RelativeLayout s;
    private final LinearLayout t;
    private final LinearLayout u;
    private final TextView v;
    private final FrameLayout w;
    private final MediaAdView x;
    private final TextView y;
    private final TextView z;

    public final IconButton a() {
        return this.G;
    }

    public void setData(f fVar, VideoData videoData) {
        this.k.setText(fVar.getCtaText());
        this.n.setText(fVar.getCtaText());
        this.l.setText(fVar.getTitle());
        this.o.setText(fVar.getTitle());
        if (NavigationType.STORE.equals(fVar.getNavigationType())) {
            this.y.setVisibility(8);
            this.z.setVisibility(8);
            if (fVar.getVotes() == 0 || fVar.getRating() <= 0.0f) {
                this.m.setVisibility(8);
                this.p.setVisibility(8);
            } else {
                this.p.setVisibility(0);
                this.m.setVisibility(0);
                this.m.setRating(fVar.getRating());
                this.p.setRating(fVar.getRating());
            }
        } else {
            this.m.setVisibility(8);
            this.p.setVisibility(8);
            this.y.setVisibility(0);
            this.z.setVisibility(0);
            this.z.setText(fVar.getDomain());
            this.y.setText(fVar.getDomain());
        }
        this.q.setText(fVar.k().s());
        this.v.setText(fVar.k().t());
        this.J.setImageBitmap(com.my.target.core.resources.a.c(getContext()));
        this.O = fVar.k().v();
        this.x.setPlaceHolderDimension(videoData.getWidth(), videoData.getHeight());
        this.x.getImageView().setImageBitmap(fVar.getImage().getBitmap());
    }

    public void setDialogListener(a aVar) {
        this.N = aVar;
    }

    public void setDismissButtonListener(OnClickListener onClickListener) {
        this.q.setOnClickListener(onClickListener);
    }

    public VideoDialogView(Context context) {
        super(context);
        this.x = NativeViewsFactory.getMediaAdView(context);
        this.q = new Button(context);
        this.k = new Button(context);
        this.l = new TextView(context);
        this.m = new StarsRatingView(context);
        this.n = new Button(context);
        this.o = new TextView(context);
        this.p = new StarsRatingView(context);
        this.v = new TextView(context);
        this.w = new FrameLayout(context);
        this.H = new FramedCacheImageView(context);
        this.I = new FramedCacheImageView(context);
        this.J = new FramedCacheImageView(context);
        this.y = new TextView(context);
        this.A = new FrameLayout(context);
        this.B = new FrameLayout(context);
        this.z = new TextView(context);
        this.C = new VideoSeekBar(context);
        this.D = new TextView(context);
        this.E = new TextView(context);
        this.F = new RelativeLayout(context);
        this.s = new RelativeLayout(context);
        this.t = new LinearLayout(context);
        this.u = new LinearLayout(context);
        this.r = new l(context);
        this.G = new IconButton(context);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13, -1);
        this.x.setLayoutParams(layoutParams);
        this.x.setId(h);
        this.x.setOnClickListener(this.P);
        this.x.setBackgroundColor(-16777216);
        this.w.setContentDescription("vdsha");
        layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.w.setBackgroundColor(-1157627904);
        this.w.setVisibility(8);
        this.w.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(this.r.a(16), this.r.a(16), this.r.a(16), this.r.a(16));
        this.q.setLayoutParams(layoutParams);
        this.q.setId(a);
        this.q.setContentDescription("vddb");
        this.q.setTextColor(-1);
        this.q.setTextSize(2, 16.0f);
        this.q.setTransformationMethod(null);
        this.l.setContentDescription("vdth");
        this.l.setSingleLine();
        this.l.setEllipsize(TruncateAt.END);
        this.l.setTextSize(2, RadialCountdown.TEXT_SIZE_SP);
        this.l.setTextColor(-1);
        this.o.setContentDescription("vdtv");
        this.o.setSingleLine();
        this.o.setEllipsize(TruncateAt.END);
        this.o.setTextSize(2, RadialCountdown.TEXT_SIZE_SP);
        this.o.setTextColor(-1);
        this.o.setGravity(14);
        l.a(this.k, DrawableConstants.TRANSPARENT_GRAY, -1, -1, this.r.a(1), this.r.a(4));
        l.a(this.n, DrawableConstants.TRANSPARENT_GRAY, -1, -1, this.r.a(1), this.r.a(4));
        l.a(this.q, DrawableConstants.TRANSPARENT_GRAY, -1, -1, this.r.a(1), this.r.a(4));
        this.k.setId(b);
        this.k.setTextColor(-1);
        this.k.setTransformationMethod(null);
        this.k.setTextSize(2, 16.0f);
        this.k.setOnClickListener(this);
        this.n.setId(c);
        this.n.setTextColor(-1);
        this.n.setTransformationMethod(null);
        this.n.setTextSize(2, 16.0f);
        this.n.setOnClickListener(this);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(1, a);
        layoutParams.setMargins(this.r.a(16), this.r.a(16), this.r.a(16), this.r.a(16));
        this.s.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(3, h);
        this.t.setGravity(1);
        this.t.setLayoutParams(layoutParams);
        this.t.setOrientation(1);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(0, g);
        layoutParams.addRule(15, -1);
        layoutParams.setMargins(this.r.a(8), 0, this.r.a(8), 0);
        this.l.setLayoutParams(layoutParams);
        this.l.setShadowLayer((float) this.r.a(1), (float) this.r.a(1), (float) this.r.a(1), -16777216);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(0, b);
        layoutParams.addRule(15, -1);
        layoutParams.setMargins(this.r.a(4), this.r.a(3), this.r.a(8), this.r.a(4));
        this.A.setLayoutParams(layoutParams);
        this.A.setId(g);
        this.B.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        layoutParams = new FrameLayout.LayoutParams(this.r.a(73), this.r.a(12));
        this.m.setContentDescription("vdrh");
        this.m.setLayoutParams(layoutParams);
        layoutParams = new FrameLayout.LayoutParams(this.r.a(73), this.r.a(12));
        this.p.setContentDescription("vdrv");
        this.p.setLayoutParams(layoutParams);
        this.y.setContentDescription("vddoh");
        this.y.setTextColor(-3355444);
        this.y.setShadowLayer((float) this.r.a(1), (float) this.r.a(1), (float) this.r.a(1), -16777216);
        this.z.setContentDescription("vddov");
        this.z.setTextColor(-3355444);
        this.z.setShadowLayer((float) this.r.a(1), (float) this.r.a(1), (float) this.r.a(1), -16777216);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(11);
        this.k.setLayoutParams(layoutParams);
        layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 1;
        layoutParams.setMargins(this.r.a(8), this.r.a(8), this.r.a(8), this.r.a(8));
        this.o.setLayoutParams(layoutParams);
        layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 1;
        layoutParams.setMargins(this.r.a(8), this.r.a(16), this.r.a(8), this.r.a(8));
        this.n.setLayoutParams(layoutParams);
        this.u.setId(d);
        this.u.setContentDescription("vdrep");
        this.u.setOnClickListener(this);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13, -1);
        this.u.setLayoutParams(layoutParams);
        this.u.setGravity(17);
        this.u.setVisibility(8);
        this.u.setPadding(0, 0, this.r.a(8), 0);
        layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 16;
        layoutParams.setMargins(this.r.a(8), 0, 0, 0);
        this.v.setLayoutParams(layoutParams);
        this.v.setTypeface(this.v.getTypeface(), 1);
        this.v.setTextColor(-1);
        this.v.setTextSize(2, 16.0f);
        layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 16;
        this.J.setLayoutParams(layoutParams);
        this.J.setPadding(this.r.a(16), this.r.a(16), this.r.a(16), this.r.a(16));
        this.H.setId(f);
        this.H.setContentDescription("vdpab");
        this.H.setOnClickListener(this);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(13, -1);
        this.H.setVisibility(8);
        this.H.setPadding(this.r.a(16), this.r.a(16), this.r.a(16), this.r.a(16));
        this.I.setId(e);
        this.I.setContentDescription("vdplb");
        this.I.setOnClickListener(this);
        LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(13, -1);
        this.I.setVisibility(8);
        this.I.setPadding(this.r.a(16), this.r.a(16), this.r.a(16), this.r.a(16));
        new FrameLayout.LayoutParams(-2, -2).gravity = 17;
        this.I.setImageBitmap(com.my.target.core.resources.a.a(getContext()));
        this.H.setImageBitmap(com.my.target.core.resources.a.b(getContext()));
        l.a(this.H, DrawableConstants.TRANSPARENT_GRAY, -1, -1, this.r.a(1), this.r.a(4));
        l.a(this.I, DrawableConstants.TRANSPARENT_GRAY, -1, -1, this.r.a(1), this.r.a(4));
        l.a(this.J, DrawableConstants.TRANSPARENT_GRAY, -1, -1, this.r.a(1), this.r.a(4));
        this.D.setId(i);
        this.D.setContentDescription("vdela");
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15, -1);
        layoutParams.rightMargin = this.r.a(8);
        this.D.setLayoutParams(layoutParams);
        this.D.setTextSize(2, 12.0f);
        this.D.setIncludeFontPadding(false);
        this.D.setTextColor(-1);
        this.D.setShadowLayer((float) this.r.a(1), (float) this.r.a(1), (float) this.r.a(1), -16777216);
        this.E.setId(j);
        this.E.setContentDescription("vdrem");
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(11, -1);
        layoutParams.addRule(15, -1);
        layoutParams.leftMargin = this.r.a(8);
        this.E.setTextSize(2, 12.0f);
        this.E.setLayoutParams(layoutParams);
        this.E.setTextColor(-1);
        this.E.setIncludeFontPadding(false);
        this.E.setGravity(16);
        this.E.setShadowLayer((float) this.r.a(1), (float) this.r.a(1), (float) this.r.a(1), -16777216);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(15, -1);
        layoutParams.addRule(1, i);
        layoutParams.addRule(0, j);
        this.C.setLayoutParams(layoutParams);
        this.C.setHeight(this.r.a(2));
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(8, h);
        this.F.setLayoutParams(layoutParams);
        this.F.setPadding(this.r.a(16), this.r.a(8), this.r.a(16), this.r.a(8));
        this.F.addView(this.D);
        this.F.addView(this.E);
        this.F.addView(this.C);
        this.F.setVisibility(8);
        this.x.addView(this.w);
        this.x.addView(this.G);
        addView(this.x);
        addView(this.q);
        addView(this.s);
        addView(this.t);
        addView(this.u);
        addView(this.H, layoutParams2);
        addView(this.I, layoutParams3);
        addView(this.F);
        this.s.addView(this.k);
        this.s.addView(this.A);
        this.A.addView(this.m);
        this.A.addView(this.y);
        this.s.addView(this.l);
        this.t.addView(this.o);
        this.t.addView(this.B);
        this.B.addView(this.p);
        this.B.addView(this.z);
        this.t.addView(this.n);
        this.u.addView(this.J);
        this.u.addView(this.v);
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        if (((float) MeasureSpec.getSize(i)) / ((float) MeasureSpec.getSize(i2)) > DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
            i3 = 2;
        } else {
            i3 = 1;
        }
        if (i3 != this.K) {
            this.K = i3;
            LayoutParams layoutParams;
            if (i3 == 2) {
                this.t.setVisibility(8);
                this.s.setVisibility(0);
                layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(12, -1);
                layoutParams.bottomMargin = this.r.a(40);
                layoutParams.rightMargin = this.r.a(6);
                if (l.b(17)) {
                    layoutParams.addRule(21, -1);
                } else {
                    layoutParams.addRule(11, -1);
                }
                this.G.setLayoutParams(layoutParams);
            } else {
                this.t.setVisibility(0);
                this.s.setVisibility(8);
                layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(10, -1);
                layoutParams.rightMargin = this.r.a(6);
                if (l.b(17)) {
                    layoutParams.addRule(21, -1);
                } else {
                    layoutParams.addRule(11, -1);
                }
                this.G.setLayoutParams(layoutParams);
            }
        }
        super.onMeasure(i, i2);
    }

    public final void a(VideoTextureView videoTextureView) {
        this.x.addView(videoTextureView, 0);
    }

    public final void a(float f, float f2) {
        this.D.setText(l.a(f));
        this.E.setText("−" + l.a(f2 - f));
        this.C.setMax((int) (f2 * 1000.0f));
        this.C.setProgress((int) (f * 1000.0f));
    }

    public final void b() {
        this.L = 4;
        this.x.getImageView().setVisibility(0);
        this.x.getProgressBarView().setVisibility(8);
        this.u.setVisibility(0);
        this.I.setVisibility(8);
        this.H.setVisibility(8);
        this.w.setVisibility(0);
        this.F.setVisibility(8);
    }

    public void onClick(View view) {
        if (this.N != null) {
            int id = view.getId();
            if (id == b || id == c) {
                this.N.a(view);
            } else if (id == d) {
                this.N.b();
            } else if (id == f) {
                this.N.c();
            } else if (id == e) {
                this.N.a();
            }
        }
    }

    public final void c() {
        this.L = 0;
        this.x.getImageView().setVisibility(8);
        this.x.getProgressBarView().setVisibility(8);
        this.u.setVisibility(8);
        this.I.setVisibility(8);
        this.H.setVisibility(8);
        this.w.setVisibility(8);
        this.F.setVisibility(8);
    }

    public final void d() {
        this.L = 3;
        this.x.getImageView().setVisibility(8);
        this.x.getProgressBarView().setVisibility(0);
        this.u.setVisibility(8);
        this.I.setVisibility(8);
        this.H.setVisibility(8);
        this.w.setVisibility(8);
        this.F.setVisibility(8);
    }

    public final void e() {
        this.L = 1;
        this.x.getImageView().setVisibility(8);
        this.x.getProgressBarView().setVisibility(8);
        this.u.setVisibility(8);
        this.I.setVisibility(0);
        this.H.setVisibility(8);
        this.w.setVisibility(0);
        if (this.O) {
            this.F.setVisibility(0);
        }
    }

    public final void f() {
        this.x.getImageView().setVisibility(8);
        this.x.getProgressBarView().setVisibility(8);
        this.u.setVisibility(8);
        this.I.setVisibility(8);
        if (this.L != 2) {
            this.H.setVisibility(8);
        }
    }

    public final void g() {
        this.L = 2;
        this.x.getImageView().setVisibility(8);
        this.x.getProgressBarView().setVisibility(8);
        this.u.setVisibility(8);
        this.I.setVisibility(8);
        this.H.setVisibility(0);
        this.w.setVisibility(8);
        if (this.O) {
            this.F.setVisibility(0);
        }
    }

    public final void h() {
        this.x.getImageView().setVisibility(0);
    }
}
