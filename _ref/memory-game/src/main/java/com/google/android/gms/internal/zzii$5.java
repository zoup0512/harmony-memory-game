package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.formats.zza;
import com.google.android.gms.ads.internal.formats.zzc;
import java.util.List;

class zzii$5 implements zzkx$zza<List<zzc>, zza> {
    final /* synthetic */ zzii zzbzk;
    final /* synthetic */ String zzbzq;
    final /* synthetic */ Integer zzbzr;
    final /* synthetic */ Integer zzbzs;
    final /* synthetic */ int zzbzt;
    final /* synthetic */ int zzbzu;
    final /* synthetic */ int zzbzv;
    final /* synthetic */ int zzbzw;

    zzii$5(zzii com_google_android_gms_internal_zzii, String str, Integer num, Integer num2, int i, int i2, int i3, int i4) {
        this.zzbzk = com_google_android_gms_internal_zzii;
        this.zzbzq = str;
        this.zzbzr = num;
        this.zzbzs = num2;
        this.zzbzt = i;
        this.zzbzu = i2;
        this.zzbzv = i3;
        this.zzbzw = i4;
    }

    public /* synthetic */ Object apply(Object obj) {
        return zzj((List) obj);
    }

    public zza zzj(List<zzc> list) {
        zza com_google_android_gms_ads_internal_formats_zza;
        if (list != null) {
            try {
                if (!list.isEmpty()) {
                    com_google_android_gms_ads_internal_formats_zza = new zza(this.zzbzq, zzii.zzi(list), this.zzbzr, this.zzbzs, this.zzbzt > 0 ? Integer.valueOf(this.zzbzt) : null, this.zzbzu + this.zzbzv, this.zzbzw);
                    return com_google_android_gms_ads_internal_formats_zza;
                }
            } catch (Throwable e) {
                zzkd.zzb("Could not get attribution icon", e);
                return null;
            }
        }
        com_google_android_gms_ads_internal_formats_zza = null;
        return com_google_android_gms_ads_internal_formats_zza;
    }
}
