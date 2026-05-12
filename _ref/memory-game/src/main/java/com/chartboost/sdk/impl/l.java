package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.Libraries.k;
import com.chartboost.sdk.i;
import com.chartboost.sdk.impl.f.a;
import com.mopub.volley.DefaultRetryPolicy;
import com.yalantis.ucrop.view.CropImageView;
import java.util.Locale;

@SuppressLint({"ViewConstructor"})
public class l extends RelativeLayout implements OnCompletionListener, OnErrorListener, OnPreparedListener {
    private static final CharSequence a = "00:00";
    private RelativeLayout b;
    private k c;
    private k d;
    private az e;
    private TextView f;
    private h g;
    private av h;
    private final f i;
    private boolean j = false;
    private boolean k = false;
    private final Handler l = CBUtility.c();
    private final Runnable m = new Runnable(this) {
        final /* synthetic */ l a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.d(false);
        }
    };
    private final Runnable n = new Runnable(this) {
        final /* synthetic */ l a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.c != null) {
                this.a.c.setVisibility(8);
            }
            if (this.a.i.K) {
                this.a.g.setVisibility(8);
            }
            this.a.d.setVisibility(8);
            if (this.a.e != null) {
                this.a.e.setEnabled(false);
            }
        }
    };
    private final Runnable o = new Runnable(this) {
        final /* synthetic */ l a;
        private int b = 0;

        {
            this.a = r2;
        }

        public void run() {
            if (this.a.h.a().e()) {
                int d = this.a.h.a().d();
                if (d > 0) {
                    this.a.i.u = d;
                    if (((float) this.a.i.u) / 1000.0f > 0.0f && !this.a.i.u()) {
                        this.a.i.s();
                        this.a.i.a(true);
                    }
                }
                float c = ((float) d) / ((float) this.a.h.a().c());
                if (this.a.i.K) {
                    this.a.g.a(c);
                }
                d /= 1000;
                if (this.b != d) {
                    this.b = d;
                    int i = d / 60;
                    d %= 60;
                    this.a.f.setText(String.format(Locale.US, "%02d:%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(d)}));
                }
            }
            a r = this.a.i.r();
            if (r.g()) {
                View b = r.b(true);
                if (b.getVisibility() == 8) {
                    this.a.i.a(true, b);
                    b.setEnabled(true);
                }
            }
            this.a.l.removeCallbacks(this.a.o);
            this.a.l.postDelayed(this.a.o, 16);
        }
    };

    public l(Context context, f fVar) {
        super(context);
        this.i = fVar;
        a(context);
    }

    private void a(Context context) {
        LayoutParams layoutParams;
        e.a g = this.i.g();
        float f = context.getResources().getDisplayMetrics().density;
        int round = Math.round(f * CropImageView.DEFAULT_MAX_SCALE_MULTIPLIER);
        i a = i.a();
        this.h = a.d(context);
        this.i.r().a(this.h);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(13);
        addView(this.h, layoutParams2);
        this.b = a.c(context);
        if (g.c() && g.a("video-click-button").c()) {
            this.c = a.f(context);
            this.c.setVisibility(8);
            this.e = new az(this, context) {
                final /* synthetic */ l a;

                protected void a(MotionEvent motionEvent) {
                    e.a a = e.a(e.a("paused", Integer.valueOf(1)));
                    a.a("click_coordinates", e.a(e.a("x", Float.valueOf(motionEvent.getX())), e.a("y", Float.valueOf(motionEvent.getY())), e.a("w", Integer.valueOf(this.a.e.getWidth())), e.a("h", Integer.valueOf(this.a.e.getHeight()))));
                    this.a.i.a(null, a);
                }
            };
            this.e.a(ScaleType.FIT_CENTER);
            k kVar = this.i.G;
            Point b = this.i.b("video-click-button");
            LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams3.leftMargin = Math.round(((float) b.x) / kVar.g());
            layoutParams3.topMargin = Math.round(((float) b.y) / kVar.g());
            this.i.a(layoutParams3, kVar, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            this.e.a(kVar);
            this.c.addView(this.e, layoutParams3);
            layoutParams = new RelativeLayout.LayoutParams(-1, Math.round(((float) layoutParams3.height) + (CropImageView.DEFAULT_MAX_SCALE_MULTIPLIER * f)));
            layoutParams.addRule(10);
            this.b.addView(this.c, layoutParams);
        }
        this.d = a.f(context);
        this.d.setVisibility(8);
        layoutParams = new RelativeLayout.LayoutParams(-1, Math.round(32.5f * f));
        layoutParams.addRule(12);
        this.b.addView(this.d, layoutParams);
        this.d.setGravity(16);
        this.d.setPadding(round, round, round, round);
        this.f = a.g(context);
        this.f.setTextColor(-1);
        this.f.setTextSize(2, 11.0f);
        this.f.setText(a);
        this.f.setPadding(0, 0, round, 0);
        this.f.setSingleLine();
        this.f.measure(0, 0);
        int measuredWidth = this.f.getMeasuredWidth();
        this.f.setGravity(17);
        this.d.addView(this.f, new LinearLayout.LayoutParams(measuredWidth, -1));
        this.g = a.e(context);
        this.g.setVisibility(8);
        layoutParams = new LinearLayout.LayoutParams(-1, Math.round(f * CropImageView.DEFAULT_MAX_SCALE_MULTIPLIER));
        layoutParams.setMargins(0, CBUtility.a(1, context), 0, 0);
        this.d.addView(this.g, layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(6, this.h.getId());
        layoutParams.addRule(8, this.h.getId());
        layoutParams.addRule(5, this.h.getId());
        layoutParams.addRule(7, this.h.getId());
        addView(this.b, layoutParams);
        a();
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (this.e != null) {
            this.e.setEnabled(enabled);
        }
        if (enabled) {
            a(false);
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.l.removeCallbacks(this.o);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent e) {
        if (!this.h.a().e() || e.getActionMasked() != 0) {
            return false;
        }
        if (this.i == null) {
            return true;
        }
        d(true);
        return true;
    }

    public void onCompletion(MediaPlayer arg0) {
        this.i.u = this.h.a().c();
        if (this.i.r() != null) {
            this.i.r().f();
        }
    }

    public void onPrepared(MediaPlayer mp) {
        this.i.v = this.h.a().c();
        this.i.r().a(true);
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        this.i.v();
        return false;
    }

    private void d(boolean z) {
        a(!this.j, z);
    }

    protected void a(boolean z, boolean z2) {
        this.l.removeCallbacks(this.m);
        this.l.removeCallbacks(this.n);
        if (this.i.w && this.i.q() && z != this.j) {
            this.j = z;
            Animation alphaAnimation = this.j ? new AlphaAnimation(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) : new AlphaAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f);
            alphaAnimation.setDuration(z2 ? 100 : 200);
            alphaAnimation.setFillAfter(true);
            if (!(this.k || this.c == null)) {
                this.c.setVisibility(0);
                this.c.startAnimation(alphaAnimation);
                if (this.e != null) {
                    this.e.setEnabled(true);
                }
            }
            if (this.i.K) {
                this.g.setVisibility(0);
            }
            this.d.setVisibility(0);
            this.d.startAnimation(alphaAnimation);
            if (this.j) {
                this.l.postDelayed(this.m, 3000);
            } else {
                this.l.postDelayed(this.n, alphaAnimation.getDuration());
            }
        }
    }

    public void a(boolean z) {
        this.l.removeCallbacks(this.m);
        this.l.removeCallbacks(this.n);
        if (z) {
            if (!(this.k || this.c == null)) {
                this.c.setVisibility(0);
            }
            if (this.i.K) {
                this.g.setVisibility(0);
            }
            this.d.setVisibility(0);
            if (this.e != null) {
                this.e.setEnabled(true);
            }
        } else {
            if (this.c != null) {
                this.c.clearAnimation();
                this.c.setVisibility(8);
            }
            this.d.clearAnimation();
            if (this.i.K) {
                this.g.setVisibility(8);
            }
            this.d.setVisibility(8);
            if (this.e != null) {
                this.e.setEnabled(false);
            }
        }
        this.j = z;
    }

    public void b(boolean z) {
        setBackgroundColor(z ? -16777216 : 0);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        if (!z) {
            layoutParams.addRule(6, this.h.getId());
            layoutParams.addRule(8, this.h.getId());
            layoutParams.addRule(5, this.h.getId());
            layoutParams.addRule(7, this.h.getId());
        }
        this.b.setLayoutParams(layoutParams);
        if (this.c != null) {
            this.c.setGravity(8388627);
            this.c.requestLayout();
        }
    }

    public void a() {
        b(CBUtility.a().a());
    }

    public av.a b() {
        return this.h.a();
    }

    public h c() {
        return this.g;
    }

    public void a(int i) {
        if (this.c != null) {
            this.c.setBackgroundColor(i);
        }
        this.d.setBackgroundColor(i);
    }

    public void d() {
        if (this.c != null) {
            this.c.setVisibility(8);
        }
        this.k = true;
        if (this.e != null) {
            this.e.setEnabled(false);
        }
    }

    public void c(boolean z) {
        this.f.setVisibility(z ? 0 : 8);
    }

    public void a(String str) {
        this.h.a().a((OnCompletionListener) this);
        this.h.a().a((OnErrorListener) this);
        this.h.a().a((OnPreparedListener) this);
        this.h.a().a(Uri.parse(str));
    }

    public void e() {
        this.l.postDelayed(new Runnable(this) {
            final /* synthetic */ l a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.h.setVisibility(0);
            }
        }, 500);
        this.h.a().a();
        this.l.removeCallbacks(this.o);
        this.l.postDelayed(this.o, 16);
    }

    public void f() {
        if (this.h.a().e()) {
            this.i.u = this.h.a().d();
            this.h.a().b();
        }
        if (this.i.r().d.getVisibility() == 0) {
            this.i.r().d.postInvalidate();
        }
        this.l.removeCallbacks(this.o);
    }

    public void g() {
        if (this.h.a().e()) {
            this.i.u = this.h.a().d();
        }
        this.h.a().b();
        this.l.removeCallbacks(this.o);
    }

    public void h() {
        this.h.setVisibility(8);
        invalidate();
    }
}
