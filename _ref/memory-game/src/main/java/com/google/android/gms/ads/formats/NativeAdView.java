package com.google.android.gms.ads.formats;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzdt;

public abstract class NativeAdView extends FrameLayout {
    private final FrameLayout zzaiz;
    private final zzdt zzaja = zzdh();

    public NativeAdView(Context context) {
        super(context);
        this.zzaiz = zzf(context);
    }

    public NativeAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzaiz = zzf(context);
    }

    public NativeAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzaiz = zzf(context);
    }

    public NativeAdView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.zzaiz = zzf(context);
    }

    private zzdt zzdh() {
        zzab.zzb(this.zzaiz, (Object) "createDelegate must be called after mOverlayFrame has been created");
        return zzm.zzix().zza(this.zzaiz.getContext(), this, this.zzaiz);
    }

    private FrameLayout zzf(Context context) {
        View zzg = zzg(context);
        zzg.setLayoutParams(new LayoutParams(-1, -1));
        addView(zzg);
        return zzg;
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        super.bringChildToFront(this.zzaiz);
    }

    public void bringChildToFront(View view) {
        super.bringChildToFront(view);
        if (this.zzaiz != view) {
            super.bringChildToFront(this.zzaiz);
        }
    }

    public void destroy() {
        try {
            this.zzaja.destroy();
        } catch (Throwable e) {
            zzb.zzb("Unable to destroy native ad view", e);
        }
    }

    public void removeAllViews() {
        super.removeAllViews();
        super.addView(this.zzaiz);
    }

    public void removeView(View view) {
        if (this.zzaiz != view) {
            super.removeView(view);
        }
    }

    public void setNativeAd(NativeAd nativeAd) {
        try {
            this.zzaja.zze((zzd) nativeAd.zzdg());
        } catch (Throwable e) {
            zzb.zzb("Unable to call setNativeAd on delegate", e);
        }
    }

    protected void zza(String str, View view) {
        try {
            this.zzaja.zzc(str, zze.zzac(view));
        } catch (Throwable e) {
            zzb.zzb("Unable to call setAssetView on delegate", e);
        }
    }

    FrameLayout zzg(Context context) {
        return new FrameLayout(context);
    }

    protected View zzq(String str) {
        try {
            zzd zzap = this.zzaja.zzap(str);
            if (zzap != null) {
                return (View) zze.zzad(zzap);
            }
        } catch (Throwable e) {
            zzb.zzb("Unable to call getAssetView on delegate", e);
        }
        return null;
    }
}
