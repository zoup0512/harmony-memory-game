package com.my.target.core.ui.views.fspromo;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.mopub.volley.DefaultRetryPolicy;
import com.my.target.core.models.banners.e;
import com.my.target.core.resources.a;
import com.my.target.core.ui.views.CacheImageView;
import com.my.target.core.ui.views.VideoProgressWheel;
import com.my.target.core.ui.views.VideoTextureView;
import com.my.target.core.ui.views.controls.IconButton;
import com.my.target.core.utils.l;

public class FSPromoDefaultView extends FSPromoView {
    private static final int a = l.b();
    private static final int b = l.b();
    private static final int c = l.b();
    private final CacheImageView d;
    private final FSPromoVerticalView e;
    private final FSPromoMediaView f;
    private final FSPromoPanelView g;
    private final IconButton h;
    private final VideoProgressWheel i;
    private final l j;
    private final boolean k;
    private final IconButton l;

    public void setBanner(e eVar) {
        super.setBanner(eVar);
        this.i.setVisibility(8);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.j.a(28), this.j.a(28));
        layoutParams.addRule(9);
        layoutParams.topMargin = this.j.a(10);
        layoutParams.leftMargin = this.j.a(10);
        this.i.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(11);
        if (eVar.k() != null) {
            this.h.setVisibility(8);
        } else {
            this.l.setVisibility(8);
        }
        this.h.setLayoutParams(layoutParams);
        if (this.h.getParent() == null) {
            addView(this.h);
        }
        if (this.i.getParent() == null) {
            addView(this.i);
        }
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        this.g.a(displayMetrics.widthPixels, displayMetrics.heightPixels);
        this.g.setBanner(eVar);
        this.e.a(displayMetrics.widthPixels, displayMetrics.heightPixels);
        this.e.setBanner(eVar);
        this.f.a();
        this.f.a(eVar);
        if (eVar.l() == null || eVar.l().getData() == null) {
            this.h.setBitmap(a.a(this.j.a(28)), Boolean.valueOf(false));
        } else {
            this.h.setBitmap((Bitmap) eVar.l().getData(), Boolean.valueOf(true));
        }
        int width = eVar.getIcon().getWidth();
        int height = eVar.getIcon().getHeight();
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.bottomMargin = this.j.a(4);
        if (!(width == 0 || height == 0)) {
            int a = (int) ((((float) height) / ((float) width)) * ((float) this.j.a(64)));
            layoutParams2.width = this.j.a(64);
            layoutParams2.height = a;
            if (!(displayMetrics.widthPixels + displayMetrics.heightPixels < 1280)) {
                layoutParams2.bottomMargin = (-a) / 2;
            }
        }
        layoutParams2.addRule(8, a);
        if (l.b(18)) {
            layoutParams2.setMarginStart(this.j.a(20));
        } else {
            layoutParams2.leftMargin = this.j.a(20);
        }
        this.d.setLayoutParams(layoutParams2);
        this.d.setImageBitmap((Bitmap) eVar.getIcon().getData());
        if (eVar.k() != null && eVar.k().q()) {
            this.f.b();
            post(new Runnable(this) {
                final /* synthetic */ FSPromoDefaultView a;

                {
                    this.a = r1;
                }

                public final void run() {
                    this.a.g.a(this.a.l);
                }
            });
        }
    }

    public final boolean a() {
        return this.f.d();
    }

    public final IconButton b() {
        return this.l;
    }

    public final void c() {
        this.g.b(this.l);
        this.f.b();
    }

    public final void d() {
        this.g.b(this.l);
        this.f.e();
    }

    public final void e() {
    }

    public final void a(boolean z) {
        this.i.setVisibility(8);
        this.g.c(this.l);
        this.f.a(z);
    }

    public void setOnCTAClickListener(OnClickListener onClickListener) {
        this.e.setOnCTAClickListener(onClickListener);
        this.g.setOnCTAClickListener(onClickListener);
    }

    public void setOnVideoClickListener(FSPromoView.a aVar) {
        this.f.setOnMediaClickListener(aVar);
    }

    public void setCloseListener(OnClickListener onClickListener) {
        this.h.setOnClickListener(onClickListener);
    }

    public final void f() {
        this.h.setVisibility(0);
    }

    public void setTimeChanged(float f, float f2) {
        this.i.setVisibility(0);
        this.i.setProgress(f / f2);
        this.i.setDigit((int) ((f2 - f) + DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    public final boolean g() {
        return this.f.c();
    }

    public final void h() {
        this.g.c(this.l);
        this.f.f();
    }

    public final void a(int i) {
        this.f.a(i);
    }

    protected final void b(int i) {
        super.b(i);
        LayoutParams layoutParams;
        if (i == 1) {
            this.e.setVisibility(0);
            this.g.setVisibility(8);
            this.d.setVisibility(0);
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(2, b);
            if (l.b(17)) {
                layoutParams.addRule(21, -1);
            } else {
                layoutParams.addRule(11, -1);
            }
            this.l.setLayoutParams(layoutParams);
            this.g.c(this.l);
            return;
        }
        this.e.setVisibility(8);
        this.g.setVisibility(0);
        this.d.setVisibility(8);
        if (this.f.c()) {
            post(new Runnable(this) {
                final /* synthetic */ FSPromoDefaultView a;

                {
                    this.a = r1;
                }

                public final void run() {
                    this.a.g.a(this.a.l);
                }
            });
        }
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(2, c);
        if (l.b(17)) {
            layoutParams.addRule(21, -1);
        } else {
            layoutParams.addRule(11, -1);
        }
        this.l.setLayoutParams(layoutParams);
    }

    public FSPromoDefaultView(Context context) {
        super(context);
        this.k = (getContext().getResources().getConfiguration().screenLayout & 15) >= 3;
        this.j = new l(context);
        this.d = new CacheImageView(context);
        this.d.setContentDescription("fsic");
        this.e = new FSPromoVerticalView(context, this.j, this.k);
        this.e.setId(b);
        this.f = new FSPromoMediaView(context, this.j, this.k);
        this.f.setId(a);
        this.h = new IconButton(context);
        this.h.setContentDescription("fscl");
        this.i = new VideoProgressWheel(context);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(3, a);
        this.g = new FSPromoPanelView(context, this.j);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(12, -1);
        this.g.setLayoutParams(layoutParams2);
        this.g.setId(c);
        this.l = new IconButton(context);
        addView(this.g, 0);
        addView(this.d, 0);
        addView(this.e, 0, layoutParams);
        addView(this.f, 0, new RelativeLayout.LayoutParams(-1, -1));
        addView(this.l);
    }

    public void setVideoListener(VideoTextureView.a aVar) {
        this.f.setVideoListener(aVar);
    }
}
