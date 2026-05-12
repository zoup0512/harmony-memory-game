package com.appodeal.ads.native_ad.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.appodeal.ads.AppodealMediaView;
import com.appodeal.ads.NativeAd;
import com.appodeal.ads.an;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import com.yalantis.ucrop.view.CropImageView;

public class NativeAdViewContentStream extends a {
    final int A = 20;
    final int B = 50;
    final int C = 10;
    final int D = 5;
    final int E = 5;
    final int F = 16;
    final int G = 12;
    final int H = 10;
    final int I = 3;
    final int J = 5;
    final int z = 5;

    public NativeAdViewContentStream(Context context, NativeAd nativeAd) {
        super(context, nativeAd);
    }

    public NativeAdViewContentStream(Context context) {
        super(context);
    }

    public NativeAdViewContentStream(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NativeAdViewContentStream(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public NativeAdViewContentStream(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    void a() {
        if (!this.m) {
            int[] iArr = new int[0];
            if (VERSION.SDK_INT >= 11) {
                iArr = new int[]{16843534};
            }
            TypedArray obtainStyledAttributes = this.l.obtainStyledAttributes(iArr);
            Drawable drawable = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            if (VERSION.SDK_INT >= 16) {
                setBackground(drawable);
            } else if (VERSION.SDK_INT >= 11) {
                setBackgroundDrawable(drawable);
            } else {
                setBackgroundDrawable(this.l.getResources().getDrawable(17301602));
            }
            this.a = new RelativeLayout(this.l);
            this.a.setLayoutParams(new LayoutParams(-1, -1));
            int round = Math.round(5.0f * an.i(this.l));
            this.a.setPadding(round, round, round, round);
            this.a.setVisibility(8);
            addView(this.a);
            View relativeLayout = new RelativeLayout(this.l);
            relativeLayout.setLayoutParams(new LayoutParams(-1, -2));
            if (VERSION.SDK_INT >= 17) {
                relativeLayout.setId(View.generateViewId());
            } else {
                relativeLayout.setId(78);
            }
            this.i = new LinearLayout(this.l);
            this.i.setOrientation(0);
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.addRule(11);
            layoutParams.addRule(10);
            this.i.setLayoutParams(layoutParams);
            if (VERSION.SDK_INT >= 17) {
                this.i.setId(View.generateViewId());
            } else {
                this.i.setId(77);
            }
            relativeLayout.addView(this.i);
            this.h = new TextView(this.l);
            this.h.setTextSize(2, CropImageView.DEFAULT_MAX_SCALE_MULTIPLIER);
            layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 16;
            this.h.setLayoutParams(layoutParams);
            c();
            this.i.addView(this.h);
            this.b = new RelativeLayout(this.l);
            this.b.setLayoutParams(new LayoutParams(-2, Math.round(CloseButton.TEXT_SIZE_SP * an.i(this.l))));
            this.i.addView(this.b);
            this.d = new ImageView(this.l);
            this.n = Math.round(50.0f * an.i(this.l));
            layoutParams = new LayoutParams(this.n, this.n);
            layoutParams.setMargins(0, 0, Math.round(CropImageView.DEFAULT_MAX_SCALE_MULTIPLIER * an.i(this.l)), 0);
            layoutParams.addRule(10);
            layoutParams.addRule(9);
            this.d.setLayoutParams(layoutParams);
            this.d.setScaleType(ScaleType.FIT_CENTER);
            if (VERSION.SDK_INT >= 17) {
                this.d.setId(View.generateViewId());
            } else {
                this.d.setId(71);
            }
            relativeLayout.addView(this.d);
            this.e = new TextView(this.l);
            this.e.setTextSize(2, 16.0f);
            layoutParams = new LayoutParams(-2, -2);
            layoutParams.setMargins(0, 0, 0, Math.round(5.0f * an.i(this.l)));
            layoutParams.addRule(1, this.d.getId());
            layoutParams.addRule(0, this.i.getId());
            this.e.setLayoutParams(layoutParams);
            if (VERSION.SDK_INT >= 17) {
                this.e.setId(View.generateViewId());
            } else {
                this.e.setId(72);
            }
            relativeLayout.addView(this.e);
            this.j = new RatingBar(this.l, null, 16842877);
            this.j.setVisibility(8);
            layoutParams = new LayoutParams(-2, -2);
            layoutParams.addRule(1, this.d.getId());
            layoutParams.addRule(3, this.e.getId());
            this.j.setLayoutParams(layoutParams);
            if (VERSION.SDK_INT >= 17) {
                this.j.setId(View.generateViewId());
            } else {
                this.j.setId(74);
            }
            relativeLayout.addView(this.j);
            this.a.addView(relativeLayout);
            this.c = new AppodealMediaView(this.l);
            layoutParams = new LayoutParams(-1, -1);
            int round2 = Math.round(CropImageView.DEFAULT_MAX_SCALE_MULTIPLIER * an.i(this.l));
            layoutParams.setMargins(0, round2, 0, round2);
            layoutParams.addRule(3, relativeLayout.getId());
            this.c.setLayoutParams(layoutParams);
            if (VERSION.SDK_INT >= 17) {
                this.c.setId(View.generateViewId());
            } else {
                this.c.setId(76);
            }
            this.a.addView(this.c);
            this.g = new TextView(this.l);
            ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-2, -2);
            int round3 = Math.round(3.0f * an.i(this.l));
            round2 = Math.round(5.0f * an.i(this.l));
            layoutParams2.setMargins(round3, 0, 3, 3);
            layoutParams2.addRule(11);
            layoutParams2.addRule(3, this.c.getId());
            this.g.setLayoutParams(layoutParams2);
            this.g.setPadding(round2, round2, round2, round2);
            if (VERSION.SDK_INT >= 17) {
                this.g.setId(View.generateViewId());
            } else {
                this.g.setId(75);
            }
            d();
            this.a.addView(this.g);
            this.f = new TextView(this.l);
            this.f.setTextSize(2, 12.0f);
            layoutParams2 = new LayoutParams(-1, -2);
            layoutParams2.setMargins(Math.round(5.0f * an.i(this.l)), 0, 0, 0);
            layoutParams2.addRule(0, this.g.getId());
            layoutParams2.addRule(3, this.c.getId());
            this.f.setLayoutParams(layoutParams2);
            this.f.setMaxLines(2);
            this.f.setMinLines(2);
            if (VERSION.SDK_INT >= 17) {
                this.f.setId(View.generateViewId());
            } else {
                this.f.setId(73);
            }
            this.a.addView(this.f);
            this.m = true;
        }
        b();
    }

    void b() {
        if (this.k != null) {
            this.d.setImageBitmap(this.k.getIcon());
            this.d.measure(getWidth(), getHeight());
            if (this.k.getImage() != null) {
                this.k.setAppodealMediaView(this.c);
            } else {
                this.c.setVisibility(8);
            }
            this.e.setText(this.k.getTitle());
            this.e.measure(getWidth(), getHeight());
            this.f.setText(this.k.getDescription());
            this.f.measure(getWidth(), getHeight());
            if (this.k.getRating() > 0.0f) {
                this.j.setRating(this.k.getRating());
                this.j.setVisibility(0);
            } else {
                this.j.setVisibility(8);
            }
            this.j.measure(getWidth(), getHeight());
            if (this.k.getCallToAction() == null || this.k.getCallToAction().isEmpty() || this.k.getCallToAction().equals("")) {
                this.g.setVisibility(8);
            } else {
                this.g.setText(this.k.getCallToAction());
                this.g.setVisibility(0);
            }
            this.g.measure(getWidth(), getHeight());
            if (!((Math.max(this.d.getMeasuredHeight(), this.e.getMeasuredHeight() + this.j.getMeasuredHeight()) + this.c.getMeasuredHeight()) + Math.max(this.f.getMeasuredHeight(), this.g.getMeasuredHeight()) <= getHeight() || getLayoutParams() == null || getLayoutParams().height == -2 || getLayoutParams().height == -1)) {
                this.c.setVisibility(8);
            }
            View providerView = this.k.getProviderView(this.l);
            if (providerView != null) {
                if (providerView.getParent() != null && (providerView.getParent() instanceof ViewGroup)) {
                    ((ViewGroup) providerView.getParent()).removeView(providerView);
                }
                this.b.removeAllViews();
                this.b.addView(providerView, new ViewGroup.LayoutParams(-2, -2));
            } else if (this.b != null) {
                this.b.setVisibility(8);
            }
            this.k.registerViewForInteraction(this);
            this.a.setVisibility(0);
        }
    }
}
