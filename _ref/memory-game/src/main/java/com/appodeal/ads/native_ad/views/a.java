package com.appodeal.ads.native_ad.views;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.appodeal.ads.AppodealMediaView;
import com.appodeal.ads.NativeAd;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;

public abstract class a extends FrameLayout {
    RelativeLayout a;
    RelativeLayout b;
    AppodealMediaView c;
    ImageView d;
    TextView e;
    TextView f;
    TextView g;
    TextView h;
    LinearLayout i;
    RatingBar j;
    NativeAd k;
    Context l;
    boolean m = false;
    int n;
    boolean o = false;
    int p = 0;
    int q = 100;
    final int r = 71;
    final int s = 72;
    final int t = 73;
    final int u = 74;
    final int v = 75;
    final int w = 76;
    final int x = 77;
    final int y = 78;

    public a(Context context, NativeAd nativeAd) {
        super(context);
        this.l = context;
        this.k = nativeAd;
        a();
    }

    public a(Context context) {
        super(context);
        this.l = context;
        a();
    }

    public a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l = context;
        a();
    }

    public a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.l = context;
        a();
    }

    @TargetApi(21)
    public a(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.l = context;
        a();
    }

    public void setNativeAd(NativeAd nativeAd) {
        this.k = nativeAd;
        a();
    }

    void a() {
    }

    void b() {
    }

    @SuppressLint({"SetTextI18n"})
    void c() {
        if (this.h == null) {
            return;
        }
        if (this.o) {
            this.h.setText("Sponsored");
            this.h.setBackgroundColor(0);
            this.h.setTextColor(-3355444);
            return;
        }
        this.h.setText(" Ad ");
        this.h.setBackgroundColor(Color.parseColor("#fcb41c"));
        this.h.setTextColor(-1);
    }

    void d() {
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(0);
        gradientDrawable.setStroke(2, this.g.getCurrentTextColor());
        gradientDrawable.setCornerRadius(CloseButton.STROKE_WIDTH);
        if (VERSION.SDK_INT >= 16) {
            this.g.setBackground(gradientDrawable);
        } else {
            this.g.setBackgroundDrawable(gradientDrawable);
        }
    }

    public AppodealMediaView getAppodealMediaView() {
        return this.c;
    }

    public ImageView getIconView() {
        return this.d;
    }

    public TextView getTitleView() {
        return this.e;
    }

    public TextView getDescriptionView() {
        return this.f;
    }

    public TextView getCallToActionView() {
        return this.g;
    }

    public RatingBar getRatingBar() {
        return this.j;
    }

    public void showSponsored(boolean z) {
        this.o = z;
        c();
    }

    public void setCallToActionColor(int i) {
        this.g.setTextColor(i);
        d();
    }

    public void setCallToActionColor(String str) {
        try {
            this.g.setTextColor(Color.parseColor(str));
        } catch (Exception e) {
        }
        d();
    }
}
