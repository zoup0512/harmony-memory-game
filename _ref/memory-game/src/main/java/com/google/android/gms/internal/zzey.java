package com.google.android.gms.internal;

import android.text.TextUtils;
import com.applovin.sdk.AppLovinEventParameters;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;

@zzin
public class zzey implements zzep {
    private final zza zzbiy;

    public zzey(zza com_google_android_gms_internal_zzey_zza) {
        this.zzbiy = com_google_android_gms_internal_zzey_zza;
    }

    public static void zza(zzlh com_google_android_gms_internal_zzlh, zza com_google_android_gms_internal_zzey_zza) {
        com_google_android_gms_internal_zzlh.zzuj().zza("/reward", new zzey(com_google_android_gms_internal_zzey_zza));
    }

    private void zze(Map<String, String> map) {
        RewardItemParcel rewardItemParcel;
        try {
            int parseInt = Integer.parseInt((String) map.get(AppLovinEventParameters.REVENUE_AMOUNT));
            String str = (String) map.get("type");
            if (!TextUtils.isEmpty(str)) {
                rewardItemParcel = new RewardItemParcel(str, parseInt);
                this.zzbiy.zzb(rewardItemParcel);
            }
        } catch (Throwable e) {
            zzb.zzd("Unable to parse reward amount.", e);
        }
        rewardItemParcel = null;
        this.zzbiy.zzb(rewardItemParcel);
    }

    private void zzf(Map<String, String> map) {
        this.zzbiy.zzev();
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        String str = (String) map.get(NativeProtocol.WEB_DIALOG_ACTION);
        if ("grant".equals(str)) {
            zze(map);
        } else if ("video_start".equals(str)) {
            zzf(map);
        }
    }
}
