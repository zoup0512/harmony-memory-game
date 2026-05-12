package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import org.json.JSONObject;
import org.nexage.sourcekit.mraid.MRAIDNativeFeature;

@zzin
public class zzhd {
    private final boolean zzbqx;
    private final boolean zzbqy;
    private final boolean zzbqz;
    private final boolean zzbra;
    private final boolean zzbrb;

    private zzhd(zza com_google_android_gms_internal_zzhd_zza) {
        this.zzbqx = zza.zza(com_google_android_gms_internal_zzhd_zza);
        this.zzbqy = zza.zzb(com_google_android_gms_internal_zzhd_zza);
        this.zzbqz = zza.zzc(com_google_android_gms_internal_zzhd_zza);
        this.zzbra = zza.zzd(com_google_android_gms_internal_zzhd_zza);
        this.zzbrb = zza.zze(com_google_android_gms_internal_zzhd_zza);
    }

    public JSONObject toJson() {
        try {
            return new JSONObject().put("sms", this.zzbqx).put("tel", this.zzbqy).put(MRAIDNativeFeature.CALENDAR, this.zzbqz).put(MRAIDNativeFeature.STORE_PICTURE, this.zzbra).put(MRAIDNativeFeature.INLINE_VIDEO, this.zzbrb);
        } catch (Throwable e) {
            zzb.zzb("Error occured while obtaining the MRAID capabilities.", e);
            return null;
        }
    }
}
