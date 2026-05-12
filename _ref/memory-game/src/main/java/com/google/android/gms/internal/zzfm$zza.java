package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.zzl;
import com.google.android.gms.ads.internal.zzu;

class zzfm$zza {
    zzl zzbkv;
    @Nullable
    AdRequestParcel zzbkw;
    zzfi zzbkx;
    long zzbky;
    boolean zzbkz;
    boolean zzbla;
    final /* synthetic */ zzfm zzblb;

    zzfm$zza(zzfm com_google_android_gms_internal_zzfm, zzfh com_google_android_gms_internal_zzfh) {
        this.zzblb = com_google_android_gms_internal_zzfm;
        this.zzbkv = com_google_android_gms_internal_zzfh.zzbd(zzfm.zza(com_google_android_gms_internal_zzfm));
        this.zzbkx = new zzfi();
        this.zzbkx.zzc(this.zzbkv);
    }

    zzfm$zza(zzfm com_google_android_gms_internal_zzfm, zzfh com_google_android_gms_internal_zzfh, AdRequestParcel adRequestParcel) {
        this(com_google_android_gms_internal_zzfm, com_google_android_gms_internal_zzfh);
        this.zzbkw = adRequestParcel;
    }

    void zzlv() {
        if (!this.zzbkz) {
            this.zzbla = this.zzbkv.zzb(zzfk.zzj(this.zzbkw != null ? this.zzbkw : zzfm.zzb(this.zzblb)));
            this.zzbkz = true;
            this.zzbky = zzu.zzfu().currentTimeMillis();
        }
    }
}
