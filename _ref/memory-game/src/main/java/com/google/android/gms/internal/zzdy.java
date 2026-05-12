package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzdr.zza;
import java.util.ArrayList;
import java.util.List;

@zzin
public class zzdy extends NativeContentAd {
    private final List<Image> zzbhd = new ArrayList();
    private final zzdx zzbhf;
    private final zzds zzbhg;

    public zzdy(zzdx com_google_android_gms_internal_zzdx) {
        zzds com_google_android_gms_internal_zzds;
        this.zzbhf = com_google_android_gms_internal_zzdx;
        try {
            List<Object> images = this.zzbhf.getImages();
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
            zzdr zzky = this.zzbhf.zzky();
            if (zzky != null) {
                com_google_android_gms_internal_zzds = new zzds(zzky);
                this.zzbhg = com_google_android_gms_internal_zzds;
            }
        } catch (Throwable e2) {
            zzb.zzb("Failed to get icon.", e2);
        }
        com_google_android_gms_internal_zzds = null;
        this.zzbhg = com_google_android_gms_internal_zzds;
    }

    public void destroy() {
        try {
            this.zzbhf.destroy();
        } catch (Throwable e) {
            zzb.zzb("Failed to destroy", e);
        }
    }

    public CharSequence getAdvertiser() {
        try {
            return this.zzbhf.getAdvertiser();
        } catch (Throwable e) {
            zzb.zzb("Failed to get attribution.", e);
            return null;
        }
    }

    public CharSequence getBody() {
        try {
            return this.zzbhf.getBody();
        } catch (Throwable e) {
            zzb.zzb("Failed to get body.", e);
            return null;
        }
    }

    public CharSequence getCallToAction() {
        try {
            return this.zzbhf.getCallToAction();
        } catch (Throwable e) {
            zzb.zzb("Failed to get call to action.", e);
            return null;
        }
    }

    public Bundle getExtras() {
        try {
            return this.zzbhf.getExtras();
        } catch (Throwable e) {
            zzb.zzd("Failed to get extras", e);
            return null;
        }
    }

    public CharSequence getHeadline() {
        try {
            return this.zzbhf.getHeadline();
        } catch (Throwable e) {
            zzb.zzb("Failed to get headline.", e);
            return null;
        }
    }

    public List<Image> getImages() {
        return this.zzbhd;
    }

    public Image getLogo() {
        return this.zzbhg;
    }

    protected /* synthetic */ Object zzdg() {
        return zzkv();
    }

    zzdr zze(Object obj) {
        return obj instanceof IBinder ? zza.zzy((IBinder) obj) : null;
    }

    protected zzd zzkv() {
        try {
            return this.zzbhf.zzkv();
        } catch (Throwable e) {
            zzb.zzb("Failed to retrieve native ad engine.", e);
            return null;
        }
    }
}
