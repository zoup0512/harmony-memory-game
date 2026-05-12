package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzdr.zza;
import java.util.ArrayList;
import java.util.List;

@zzin
public class zzdw extends NativeAppInstallAd {
    private final zzdv zzbhc;
    private final List<Image> zzbhd = new ArrayList();
    private final zzds zzbhe;

    public zzdw(zzdv com_google_android_gms_internal_zzdv) {
        zzds com_google_android_gms_internal_zzds;
        this.zzbhc = com_google_android_gms_internal_zzdv;
        try {
            List<Object> images = this.zzbhc.getImages();
            if (images != null) {
                for (Object zze : images) {
                    zzdr zze2 = zze(zze);
                    if (zze2 != null) {
                        this.zzbhd.add(new zzds(zze2));
                    }
                }
            }
        } catch (Throwable e) {
            zzb.zzb("Failed to get image.", e);
        }
        try {
            zzdr zzku = this.zzbhc.zzku();
            if (zzku != null) {
                com_google_android_gms_internal_zzds = new zzds(zzku);
                this.zzbhe = com_google_android_gms_internal_zzds;
            }
        } catch (Throwable e2) {
            zzb.zzb("Failed to get icon.", e2);
        }
        com_google_android_gms_internal_zzds = null;
        this.zzbhe = com_google_android_gms_internal_zzds;
    }

    public void destroy() {
        try {
            this.zzbhc.destroy();
        } catch (Throwable e) {
            zzb.zzb("Failed to destroy", e);
        }
    }

    public CharSequence getBody() {
        try {
            return this.zzbhc.getBody();
        } catch (Throwable e) {
            zzb.zzb("Failed to get body.", e);
            return null;
        }
    }

    public CharSequence getCallToAction() {
        try {
            return this.zzbhc.getCallToAction();
        } catch (Throwable e) {
            zzb.zzb("Failed to get call to action.", e);
            return null;
        }
    }

    public Bundle getExtras() {
        try {
            return this.zzbhc.getExtras();
        } catch (Throwable e) {
            zzb.zzb("Failed to get extras", e);
            return null;
        }
    }

    public CharSequence getHeadline() {
        try {
            return this.zzbhc.getHeadline();
        } catch (Throwable e) {
            zzb.zzb("Failed to get headline.", e);
            return null;
        }
    }

    public Image getIcon() {
        return this.zzbhe;
    }

    public List<Image> getImages() {
        return this.zzbhd;
    }

    public CharSequence getPrice() {
        try {
            return this.zzbhc.getPrice();
        } catch (Throwable e) {
            zzb.zzb("Failed to get price.", e);
            return null;
        }
    }

    public Double getStarRating() {
        Double d = null;
        try {
            double starRating = this.zzbhc.getStarRating();
            if (starRating != -1.0d) {
                d = Double.valueOf(starRating);
            }
        } catch (Throwable e) {
            zzb.zzb("Failed to get star rating.", e);
        }
        return d;
    }

    public CharSequence getStore() {
        try {
            return this.zzbhc.getStore();
        } catch (Throwable e) {
            zzb.zzb("Failed to get store", e);
            return null;
        }
    }

    protected /* synthetic */ Object zzdg() {
        return zzkv();
    }

    zzdr zze(Object obj) {
        return obj instanceof IBinder ? zza.zzy((IBinder) obj) : null;
    }

    protected zzd zzkv() {
        try {
            return this.zzbhc.zzkv();
        } catch (Throwable e) {
            zzb.zzb("Failed to retrieve native ad engine.", e);
            return null;
        }
    }
}
