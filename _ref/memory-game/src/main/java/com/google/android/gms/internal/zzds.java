package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;

@zzin
public class zzds extends Image {
    private final Drawable mDrawable;
    private final Uri mUri;
    private final double zzbff;
    private final zzdr zzbhb;

    public zzds(zzdr com_google_android_gms_internal_zzdr) {
        Drawable drawable;
        double d;
        Uri uri = null;
        this.zzbhb = com_google_android_gms_internal_zzdr;
        try {
            zzd zzkt = this.zzbhb.zzkt();
            if (zzkt != null) {
                drawable = (Drawable) zze.zzad(zzkt);
                this.mDrawable = drawable;
                uri = this.zzbhb.getUri();
                this.mUri = uri;
                d = 1.0d;
                d = this.zzbhb.getScale();
                this.zzbff = d;
            }
        } catch (Throwable e) {
            zzb.zzb("Failed to get drawable.", e);
        }
        Object obj = uri;
        this.mDrawable = drawable;
        try {
            uri = this.zzbhb.getUri();
        } catch (Throwable e2) {
            zzb.zzb("Failed to get uri.", e2);
        }
        this.mUri = uri;
        d = 1.0d;
        try {
            d = this.zzbhb.getScale();
        } catch (Throwable e3) {
            zzb.zzb("Failed to get scale.", e3);
        }
        this.zzbff = d;
    }

    public Drawable getDrawable() {
        return this.mDrawable;
    }

    public double getScale() {
        return this.zzbff;
    }

    public Uri getUri() {
        return this.mUri;
    }
}
