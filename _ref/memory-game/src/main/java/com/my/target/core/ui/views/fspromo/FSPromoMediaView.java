package com.my.target.core.ui.views.fspromo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import com.my.target.ads.MyTargetVideoView;
import com.my.target.core.models.banners.e;
import com.my.target.core.ui.views.CacheImageView;
import com.my.target.core.ui.views.VideoTextureView;
import com.my.target.core.ui.views.controls.IconButton;
import com.my.target.core.ui.views.fspromo.FSPromoView.a;
import com.my.target.core.utils.l;
import com.my.target.core.utils.n;
import com.my.target.nativeads.models.ImageData;
import com.my.target.nativeads.models.VideoData;

@SuppressLint({"ViewConstructor"})
public class FSPromoMediaView extends RelativeLayout {
    private final CacheImageView a;
    private final l b;
    private final boolean c;
    private final IconButton d;
    private VideoTextureView e;
    private float f;
    private VideoData g;
    private a h;
    private final OnClickListener i = new OnClickListener(this) {
        final /* synthetic */ FSPromoMediaView a;

        {
            this.a = r1;
        }

        public final void onClick(View view) {
            if (this.a.h != null) {
                if (!this.a.c() && !this.a.d()) {
                    this.a.h.a();
                } else if (this.a.d()) {
                    this.a.h.c();
                } else {
                    this.a.h.b();
                }
            }
        }
    };

    public FSPromoMediaView(Context context, l lVar, boolean z) {
        super(context);
        this.b = lVar;
        this.c = z;
        this.a = new CacheImageView(context);
        this.d = new IconButton(context);
        if (l.b(14)) {
            this.e = new VideoTextureView(context);
        }
    }

    final void a(e eVar) {
        float width;
        float height;
        if (!l.b(14) || eVar.k() == null) {
            setOnClickListener(null);
            if (!(this.d == null || this.d.getParent() == null)) {
                ((ViewGroup) this.d.getParent()).removeView(this.d);
            }
            if (eVar.getImage() != null && eVar.getImage().getData() != null) {
                width = (float) eVar.getImage().getWidth();
                height = (float) eVar.getImage().getHeight();
                if (height != 0.0f) {
                    this.f = width / height;
                    requestLayout();
                }
                this.a.setImageBitmap((Bitmap) eVar.getImage().getData());
                this.a.setClickable(false);
                return;
            }
            return;
        }
        this.g = n.a(eVar.k().u(), MyTargetVideoView.DEFAULT_VIDEO_QUALITY);
        width = (float) this.g.getWidth();
        height = (float) this.g.getHeight();
        if (height != 0.0f) {
            this.f = width / height;
            requestLayout();
        }
        ImageData r = eVar.k().r();
        if (r == null || r.getData() == null) {
            r = eVar.getImage();
            if (!(r == null || r.getData() == null)) {
                this.a.setImageBitmap((Bitmap) r.getData());
            }
        } else {
            this.a.setImageBitmap((Bitmap) r.getData());
        }
        if (eVar.v() != 1) {
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13);
            this.d.setLayoutParams(layoutParams);
            r = eVar.m();
            if (r == null || r.getData() == null) {
                int a;
                if (this.c) {
                    a = this.b.a(140);
                } else {
                    a = this.b.a(96);
                }
                this.d.setBitmap(com.my.target.core.resources.a.b(a), Boolean.valueOf(false));
                return;
            }
            this.d.setBitmap((Bitmap) r.getData(), Boolean.valueOf(true));
        }
    }

    public final void a() {
        this.d.setContentDescription("fsmpb");
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        this.a.setScaleType(ScaleType.CENTER_INSIDE);
        this.a.setAdjustViewBounds(true);
        this.a.setLayoutParams(layoutParams);
        if (this.e != null) {
            this.e.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            addView(this.e);
        }
        addView(this.a);
        addView(this.d);
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (this.f == 0.0f) {
            size2 = this.a.getMeasuredHeight();
            size = this.a.getMeasuredWidth();
        } else {
            size2 = Math.min(Math.round(((float) size) / this.f), size2);
        }
        super.onMeasure(MeasureSpec.makeMeasureSpec(size, 1073741824), MeasureSpec.makeMeasureSpec(size2, 1073741824));
    }

    final void a(boolean z) {
        if (this.e != null) {
            this.e.d();
        }
        this.a.setVisibility(0);
        if (z) {
            this.d.setVisibility(0);
            return;
        }
        this.a.setOnClickListener(null);
        this.d.setOnClickListener(null);
        setOnClickListener(null);
    }

    final void b() {
        this.d.setVisibility(8);
        this.a.setVisibility(8);
        if (this.g != null && this.e != null) {
            this.e.a(this.g, true);
        }
    }

    public void setOnMediaClickListener(a aVar) {
        this.h = aVar;
        this.a.setOnClickListener(this.i);
        this.d.setOnClickListener(this.i);
        setOnClickListener(this.i);
    }

    public final boolean c() {
        return this.e != null && this.e.b() == 3;
    }

    public final boolean d() {
        return this.e != null && this.e.b() == 4;
    }

    public final void e() {
        if (this.e != null) {
            this.e.a(this.g, true);
        }
        this.d.setVisibility(8);
    }

    public final void f() {
        if (this.e != null) {
            this.e.a(false);
        }
        this.d.setVisibility(0);
    }

    public void setVideoListener(VideoTextureView.a aVar) {
        if (this.e != null) {
            this.e.setVideoListener(aVar);
        }
    }

    public final void a(int i) {
        if (this.e == null) {
            return;
        }
        if (i == 1) {
            this.e.g();
        } else if (i == 0) {
            this.e.h();
        } else {
            this.e.e();
        }
    }
}
