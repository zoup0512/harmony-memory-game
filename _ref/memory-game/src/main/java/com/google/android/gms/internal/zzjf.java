package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.ads.mediation.AbstractAdViewAdapter;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.reward.client.RewardedVideoAdRequestParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzb;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzju.zza;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

@zzin
public class zzjf extends zzb implements zzji {
    private static final zzgi zzchg = new zzgi();
    private final Map<String, zzjm> zzchh = new HashMap();
    private boolean zzchi;

    public zzjf(Context context, zzd com_google_android_gms_ads_internal_zzd, AdSizeParcel adSizeParcel, zzgj com_google_android_gms_internal_zzgj, VersionInfoParcel versionInfoParcel) {
        super(context, adSizeParcel, null, com_google_android_gms_internal_zzgj, versionInfoParcel, com_google_android_gms_ads_internal_zzd);
    }

    private zza zze(zza com_google_android_gms_internal_zzju_zza) {
        zzkd.v("Creating mediation ad response for non-mediated rewarded ad.");
        try {
            String jSONObject = zziq.zzc(com_google_android_gms_internal_zzju_zza.zzciq).toString();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(AbstractAdViewAdapter.AD_UNIT_ID_PARAMETER, com_google_android_gms_internal_zzju_zza.zzcip.zzaou);
            zzfz com_google_android_gms_internal_zzfz = new zzfz(jSONObject, null, Arrays.asList(new String[]{"com.google.ads.mediation.admob.AdMobAdapter"}), null, null, Collections.emptyList(), Collections.emptyList(), jSONObject2.toString(), null, Collections.emptyList(), Collections.emptyList(), null, null, null, null, null, Collections.emptyList());
            return new zza(com_google_android_gms_internal_zzju_zza.zzcip, com_google_android_gms_internal_zzju_zza.zzciq, new zzga(Arrays.asList(new zzfz[]{com_google_android_gms_internal_zzfz}), -1, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), false, "", -1, 0, 1, null, 0, -1, -1, false), com_google_android_gms_internal_zzju_zza.zzapa, com_google_android_gms_internal_zzju_zza.errorCode, com_google_android_gms_internal_zzju_zza.zzcik, com_google_android_gms_internal_zzju_zza.zzcil, com_google_android_gms_internal_zzju_zza.zzcie);
        } catch (Throwable e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzb("Unable to generate ad state for non-mediated rewarded video.", e);
            return zzf(com_google_android_gms_internal_zzju_zza);
        }
    }

    private zza zzf(zza com_google_android_gms_internal_zzju_zza) {
        return new zza(com_google_android_gms_internal_zzju_zza.zzcip, com_google_android_gms_internal_zzju_zza.zzciq, null, com_google_android_gms_internal_zzju_zza.zzapa, 0, com_google_android_gms_internal_zzju_zza.zzcik, com_google_android_gms_internal_zzju_zza.zzcil, com_google_android_gms_internal_zzju_zza.zzcie);
    }

    public void destroy() {
        zzab.zzhi("destroy must be called on the main UI thread.");
        for (String str : this.zzchh.keySet()) {
            String str2;
            try {
                zzjm com_google_android_gms_internal_zzjm = (zzjm) this.zzchh.get(str2);
                if (!(com_google_android_gms_internal_zzjm == null || com_google_android_gms_internal_zzjm.zzru() == null)) {
                    com_google_android_gms_internal_zzjm.zzru().destroy();
                }
            } catch (RemoteException e) {
                String str3 = "Fail to destroy adapter: ";
                str2 = String.valueOf(str2);
                com.google.android.gms.ads.internal.util.client.zzb.zzcx(str2.length() != 0 ? str3.concat(str2) : new String(str3));
            }
        }
    }

    public boolean isLoaded() {
        zzab.zzhi("isLoaded must be called on the main UI thread.");
        return this.zzajs.zzaoy == null && this.zzajs.zzaoz == null && this.zzajs.zzapb != null && !this.zzchi;
    }

    public void onContextChanged(@NonNull Context context) {
        for (zzjm zzru : this.zzchh.values()) {
            try {
                zzru.zzru().zzj(zze.zzac(context));
            } catch (Throwable e) {
                com.google.android.gms.ads.internal.util.client.zzb.zzb("Unable to call Adapter.onContextChanged.", e);
            }
        }
    }

    public void onRewardedVideoAdClosed() {
        zzdr();
    }

    public void onRewardedVideoAdLeftApplication() {
        zzds();
    }

    public void onRewardedVideoAdOpened() {
        zza(this.zzajs.zzapb, false);
        zzdt();
    }

    public void onRewardedVideoStarted() {
        if (!(this.zzajs.zzapb == null || this.zzajs.zzapb.zzbon == null)) {
            zzu.zzgf().zza(this.zzajs.zzagf, this.zzajs.zzaow.zzcs, this.zzajs.zzapb, this.zzajs.zzaou, false, this.zzajs.zzapb.zzbon.zzbnd);
        }
        zzdv();
    }

    public void pause() {
        zzab.zzhi("pause must be called on the main UI thread.");
        for (String str : this.zzchh.keySet()) {
            String str2;
            try {
                zzjm com_google_android_gms_internal_zzjm = (zzjm) this.zzchh.get(str2);
                if (!(com_google_android_gms_internal_zzjm == null || com_google_android_gms_internal_zzjm.zzru() == null)) {
                    com_google_android_gms_internal_zzjm.zzru().pause();
                }
            } catch (RemoteException e) {
                String str3 = "Fail to pause adapter: ";
                str2 = String.valueOf(str2);
                com.google.android.gms.ads.internal.util.client.zzb.zzcx(str2.length() != 0 ? str3.concat(str2) : new String(str3));
            }
        }
    }

    public void resume() {
        zzab.zzhi("resume must be called on the main UI thread.");
        for (String str : this.zzchh.keySet()) {
            String str2;
            try {
                zzjm com_google_android_gms_internal_zzjm = (zzjm) this.zzchh.get(str2);
                if (!(com_google_android_gms_internal_zzjm == null || com_google_android_gms_internal_zzjm.zzru() == null)) {
                    com_google_android_gms_internal_zzjm.zzru().resume();
                }
            } catch (RemoteException e) {
                String str3 = "Fail to resume adapter: ";
                str2 = String.valueOf(str2);
                com.google.android.gms.ads.internal.util.client.zzb.zzcx(str2.length() != 0 ? str3.concat(str2) : new String(str3));
            }
        }
    }

    public void zza(RewardedVideoAdRequestParcel rewardedVideoAdRequestParcel) {
        zzab.zzhi("loadAd must be called on the main UI thread.");
        if (TextUtils.isEmpty(rewardedVideoAdRequestParcel.zzaou)) {
            com.google.android.gms.ads.internal.util.client.zzb.zzcx("Invalid ad unit id. Aborting.");
            return;
        }
        this.zzchi = false;
        this.zzajs.zzaou = rewardedVideoAdRequestParcel.zzaou;
        super.zzb(rewardedVideoAdRequestParcel.zzcar);
    }

    public void zza(zza com_google_android_gms_internal_zzju_zza, zzdk com_google_android_gms_internal_zzdk) {
        if (com_google_android_gms_internal_zzju_zza.errorCode != -2) {
            zzkh.zzclc.post(new 1(this, com_google_android_gms_internal_zzju_zza));
            return;
        }
        this.zzajs.zzapc = com_google_android_gms_internal_zzju_zza;
        if (com_google_android_gms_internal_zzju_zza.zzcig == null) {
            this.zzajs.zzapc = zze(com_google_android_gms_internal_zzju_zza);
        }
        this.zzajs.zzapw = 0;
        this.zzajs.zzaoz = zzu.zzfp().zza(this.zzajs.zzagf, this.zzajs.zzapc, this);
    }

    protected boolean zza(AdRequestParcel adRequestParcel, zzju com_google_android_gms_internal_zzju, boolean z) {
        return false;
    }

    public boolean zza(zzju com_google_android_gms_internal_zzju, zzju com_google_android_gms_internal_zzju2) {
        return true;
    }

    public void zzc(@Nullable RewardItemParcel rewardItemParcel) {
        if (!(this.zzajs.zzapb == null || this.zzajs.zzapb.zzbon == null)) {
            zzu.zzgf().zza(this.zzajs.zzagf, this.zzajs.zzaow.zzcs, this.zzajs.zzapb, this.zzajs.zzaou, false, this.zzajs.zzapb.zzbon.zzbne);
        }
        if (!(this.zzajs.zzapb == null || this.zzajs.zzapb.zzcig == null || TextUtils.isEmpty(this.zzajs.zzapb.zzcig.zzbnt))) {
            rewardItemParcel = new RewardItemParcel(this.zzajs.zzapb.zzcig.zzbnt, this.zzajs.zzapb.zzcig.zzbnu);
        }
        zza(rewardItemParcel);
    }

    @Nullable
    public zzjm zzcf(String str) {
        Throwable th;
        String str2;
        String valueOf;
        zzjm com_google_android_gms_internal_zzjm = (zzjm) this.zzchh.get(str);
        if (com_google_android_gms_internal_zzjm != null) {
            return com_google_android_gms_internal_zzjm;
        }
        zzjm com_google_android_gms_internal_zzjm2;
        try {
            com_google_android_gms_internal_zzjm2 = new zzjm(("com.google.ads.mediation.admob.AdMobAdapter".equals(str) ? zzchg : this.zzajz).zzbm(str), this);
            try {
                this.zzchh.put(str, com_google_android_gms_internal_zzjm2);
                return com_google_android_gms_internal_zzjm2;
            } catch (Throwable e) {
                th = e;
                str2 = "Fail to instantiate adapter ";
                valueOf = String.valueOf(str);
                com.google.android.gms.ads.internal.util.client.zzb.zzd(valueOf.length() == 0 ? new String(str2) : str2.concat(valueOf), th);
                return com_google_android_gms_internal_zzjm2;
            }
        } catch (Throwable e2) {
            th = e2;
            com_google_android_gms_internal_zzjm2 = com_google_android_gms_internal_zzjm;
            str2 = "Fail to instantiate adapter ";
            valueOf = String.valueOf(str);
            if (valueOf.length() == 0) {
            }
            com.google.android.gms.ads.internal.util.client.zzb.zzd(valueOf.length() == 0 ? new String(str2) : str2.concat(valueOf), th);
            return com_google_android_gms_internal_zzjm2;
        }
    }

    public void zzrq() {
        zzab.zzhi("showAd must be called on the main UI thread.");
        if (isLoaded()) {
            this.zzchi = true;
            zzjm zzcf = zzcf(this.zzajs.zzapb.zzbop);
            if (zzcf != null && zzcf.zzru() != null) {
                try {
                    zzcf.zzru().showVideo();
                    return;
                } catch (Throwable e) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call showVideo.", e);
                    return;
                }
            }
            return;
        }
        com.google.android.gms.ads.internal.util.client.zzb.zzcx("The reward video has not loaded.");
    }

    public void zzrr() {
        onAdClicked();
    }
}
