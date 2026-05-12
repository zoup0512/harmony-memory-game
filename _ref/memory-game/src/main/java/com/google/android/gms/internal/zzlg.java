package com.google.android.gms.internal;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.google.android.gms.ads.internal.overlay.zzk;
import com.google.android.gms.common.internal.zzab;

@zzin
public class zzlg {
    private final Context mContext;
    private final zzlh zzbgf;
    private zzk zzbwf;
    private final ViewGroup zzcoi;

    public zzlg(Context context, ViewGroup viewGroup, zzlh com_google_android_gms_internal_zzlh) {
        this(context, viewGroup, com_google_android_gms_internal_zzlh, null);
    }

    zzlg(Context context, ViewGroup viewGroup, zzlh com_google_android_gms_internal_zzlh, zzk com_google_android_gms_ads_internal_overlay_zzk) {
        this.mContext = context;
        this.zzcoi = viewGroup;
        this.zzbgf = com_google_android_gms_internal_zzlh;
        this.zzbwf = com_google_android_gms_ads_internal_overlay_zzk;
    }

    public void onDestroy() {
        zzab.zzhi("onDestroy must be called from the UI thread.");
        if (this.zzbwf != null) {
            this.zzbwf.destroy();
            this.zzcoi.removeView(this.zzbwf);
            this.zzbwf = null;
        }
    }

    public void onPause() {
        zzab.zzhi("onPause must be called from the UI thread.");
        if (this.zzbwf != null) {
            this.zzbwf.pause();
        }
    }

    public void zza(int i, int i2, int i3, int i4, int i5, boolean z) {
        if (this.zzbwf == null) {
            zzdg.zza(this.zzbgf.zzus().zzkf(), this.zzbgf.zzur(), "vpr");
            zzdi zzb = zzdg.zzb(this.zzbgf.zzus().zzkf());
            this.zzbwf = new zzk(this.mContext, this.zzbgf, i5, z, this.zzbgf.zzus().zzkf(), zzb);
            this.zzcoi.addView(this.zzbwf, 0, new LayoutParams(-1, -1));
            this.zzbwf.zzd(i, i2, i3, i4);
            this.zzbgf.zzuj().zzak(false);
        }
    }

    public void zze(int i, int i2, int i3, int i4) {
        zzab.zzhi("The underlay may only be modified from the UI thread.");
        if (this.zzbwf != null) {
            this.zzbwf.zzd(i, i2, i3, i4);
        }
    }

    public zzk zzub() {
        zzab.zzhi("getAdVideoUnderlay must be called from the UI thread.");
        return this.zzbwf;
    }
}
