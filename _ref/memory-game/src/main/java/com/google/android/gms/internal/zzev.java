package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zze;
import com.google.android.gms.common.util.zzf;
import java.util.Map;
import org.nexage.sourcekit.mraid.MRAIDNativeFeature;

@zzin
public class zzev implements zzep {
    static final Map<String, Integer> zzbiv = zzf.zza("resize", Integer.valueOf(1), "playVideo", Integer.valueOf(2), MRAIDNativeFeature.STORE_PICTURE, Integer.valueOf(3), "createCalendarEvent", Integer.valueOf(4), "setOrientationProperties", Integer.valueOf(5), "closeResizedAd", Integer.valueOf(6));
    private final zze zzbit;
    private final zzha zzbiu;

    public zzev(zze com_google_android_gms_ads_internal_zze, zzha com_google_android_gms_internal_zzha) {
        this.zzbit = com_google_android_gms_ads_internal_zze;
        this.zzbiu = com_google_android_gms_internal_zzha;
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        int intValue = ((Integer) zzbiv.get((String) map.get("a"))).intValue();
        if (intValue == 5 || this.zzbit == null || this.zzbit.zzel()) {
            switch (intValue) {
                case 1:
                    this.zzbiu.execute(map);
                    return;
                case 3:
                    new zzhc(com_google_android_gms_internal_zzlh, map).execute();
                    return;
                case 4:
                    new zzgz(com_google_android_gms_internal_zzlh, map).execute();
                    return;
                case 5:
                    new zzhb(com_google_android_gms_internal_zzlh, map).execute();
                    return;
                case 6:
                    this.zzbiu.zzs(true);
                    return;
                default:
                    zzb.zzcw("Unknown MRAID command called.");
                    return;
            }
        }
        this.zzbit.zzt(null);
    }
}
