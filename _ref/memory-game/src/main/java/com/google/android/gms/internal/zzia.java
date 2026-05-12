package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzju.zza;

@zzin
public class zzia extends zzhy {
    private zzhz zzbyg;

    zzia(Context context, zza com_google_android_gms_internal_zzju_zza, zzlh com_google_android_gms_internal_zzlh, zzic.zza com_google_android_gms_internal_zzic_zza) {
        super(context, com_google_android_gms_internal_zzju_zza, com_google_android_gms_internal_zzlh, com_google_android_gms_internal_zzic_zza);
    }

    protected void zzpw() {
        int i;
        int i2;
        AdSizeParcel zzdn = this.zzbgf.zzdn();
        if (zzdn.zzaus) {
            DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
            i = displayMetrics.widthPixels;
            i2 = displayMetrics.heightPixels;
        } else {
            i = zzdn.widthPixels;
            i2 = zzdn.heightPixels;
        }
        this.zzbyg = new zzhz(this, this.zzbgf, i, i2);
        this.zzbgf.zzuj().zza((zzli.zza) this);
        this.zzbyg.zza(this.zzbxs);
    }

    protected int zzpx() {
        if (!this.zzbyg.zzqb()) {
            return !this.zzbyg.zzqc() ? 2 : -2;
        } else {
            zzb.zzcv("Ad-Network indicated no fill with passback URL.");
            return 3;
        }
    }
}
