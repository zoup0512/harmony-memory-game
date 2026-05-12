package com.google.android.gms.ads.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.ThinAdSizeParcel;
import com.google.android.gms.ads.internal.client.VideoOptionsParcel;
import com.google.android.gms.ads.internal.client.zzf;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.ads.internal.client.zzy;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.ads.internal.reward.client.zzd;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzcg;
import com.google.android.gms.internal.zzcl;
import com.google.android.gms.internal.zzco;
import com.google.android.gms.internal.zzdc;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zzdk;
import com.google.android.gms.internal.zzdo;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.internal.zzho;
import com.google.android.gms.internal.zzhs;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzjd;
import com.google.android.gms.internal.zzju;
import com.google.android.gms.internal.zzjv;
import com.google.android.gms.internal.zzjz;
import com.google.android.gms.internal.zzka;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

@zzin
public abstract class zza extends com.google.android.gms.ads.internal.client.zzu.zza implements com.google.android.gms.ads.internal.client.zza, zzp, com.google.android.gms.ads.internal.request.zza.zza, zzel, com.google.android.gms.internal.zzic.zza, zzjz {
    protected zzdk zzajn;
    protected zzdi zzajo;
    protected zzdi zzajp;
    protected boolean zzajq = false;
    protected final zzr zzajr;
    protected final zzv zzajs;
    @Nullable
    protected transient AdRequestParcel zzajt;
    protected final zzcg zzaju;
    protected final zzd zzajv;

    zza(zzv com_google_android_gms_ads_internal_zzv, @Nullable zzr com_google_android_gms_ads_internal_zzr, zzd com_google_android_gms_ads_internal_zzd) {
        this.zzajs = com_google_android_gms_ads_internal_zzv;
        if (com_google_android_gms_ads_internal_zzr == null) {
            com_google_android_gms_ads_internal_zzr = new zzr(this);
        }
        this.zzajr = com_google_android_gms_ads_internal_zzr;
        this.zzajv = com_google_android_gms_ads_internal_zzd;
        zzu.zzfq().zzad(this.zzajs.zzagf);
        zzu.zzft().zzb(this.zzajs.zzagf, this.zzajs.zzaow);
        this.zzaju = zzu.zzft().zzsu();
        zzdk();
    }

    private AdRequestParcel zza(AdRequestParcel adRequestParcel) {
        return (!zzi.zzcl(this.zzajs.zzagf) || adRequestParcel.zzatu == null) ? adRequestParcel : new zzf(adRequestParcel).zza(null).zzig();
    }

    private TimerTask zza(Timer timer, CountDownLatch countDownLatch) {
        return new 1(this, countDownLatch, timer);
    }

    private void zzdk() {
        if (((Boolean) zzdc.zzbcj.get()).booleanValue()) {
            Timer timer = new Timer();
            timer.schedule(zza(timer, new CountDownLatch(((Integer) zzdc.zzbcl.get()).intValue())), 0, ((Long) zzdc.zzbck.get()).longValue());
        }
    }

    public void destroy() {
        zzab.zzhi("destroy must be called on the main UI thread.");
        this.zzajr.cancel();
        this.zzaju.zzj(this.zzajs.zzapb);
        this.zzajs.destroy();
    }

    public boolean isLoading() {
        return this.zzajq;
    }

    public boolean isReady() {
        zzab.zzhi("isLoaded must be called on the main UI thread.");
        return this.zzajs.zzaoy == null && this.zzajs.zzaoz == null && this.zzajs.zzapb != null;
    }

    public void onAdClicked() {
        if (this.zzajs.zzapb == null) {
            zzb.zzcx("Ad state was null when trying to ping click URLs.");
            return;
        }
        zzb.zzcv("Pinging click URLs.");
        this.zzajs.zzapd.zzrz();
        if (this.zzajs.zzapb.zzbnm != null) {
            zzu.zzfq().zza(this.zzajs.zzagf, this.zzajs.zzaow.zzcs, this.zzajs.zzapb.zzbnm);
        }
        if (this.zzajs.zzape != null) {
            try {
                this.zzajs.zzape.onAdClicked();
            } catch (Throwable e) {
                zzb.zzd("Could not notify onAdClicked event.", e);
            }
        }
    }

    public void onAppEvent(String str, @Nullable String str2) {
        if (this.zzajs.zzapg != null) {
            try {
                this.zzajs.zzapg.onAppEvent(str, str2);
            } catch (Throwable e) {
                zzb.zzd("Could not call the AppEventListener.", e);
            }
        }
    }

    public void pause() {
        zzab.zzhi("pause must be called on the main UI thread.");
    }

    public void resume() {
        zzab.zzhi("resume must be called on the main UI thread.");
    }

    public void setManualImpressionsEnabled(boolean z) {
        throw new UnsupportedOperationException("Attempt to call setManualImpressionsEnabled for an unsupported ad type.");
    }

    public void setUserId(String str) {
        zzb.zzcx("RewardedVideoAd.setUserId() is deprecated. Please do not call this method.");
    }

    public void stopLoading() {
        zzab.zzhi("stopLoading must be called on the main UI thread.");
        this.zzajq = false;
        this.zzajs.zzi(true);
    }

    Bundle zza(@Nullable zzco com_google_android_gms_internal_zzco) {
        if (com_google_android_gms_internal_zzco == null) {
            return null;
        }
        String zzhr;
        String zzhs;
        if (com_google_android_gms_internal_zzco.zzid()) {
            com_google_android_gms_internal_zzco.wakeup();
        }
        zzcl zzib = com_google_android_gms_internal_zzco.zzib();
        if (zzib != null) {
            zzhr = zzib.zzhr();
            zzhs = zzib.zzhs();
            String str = "In AdManager: loadAd, ";
            String valueOf = String.valueOf(zzib.toString());
            zzb.zzcv(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            if (zzhr != null) {
                zzu.zzft().zzcm(zzhr);
            }
        } else {
            zzhs = null;
            zzhr = zzu.zzft().zzsp();
        }
        if (zzhr == null) {
            return null;
        }
        Bundle bundle = new Bundle(1);
        bundle.putString("fingerprint", zzhr);
        if (zzhr.equals(zzhs)) {
            return bundle;
        }
        bundle.putString("v_fp", zzhs);
        return bundle;
    }

    public void zza(AdSizeParcel adSizeParcel) {
        zzab.zzhi("setAdSize must be called on the main UI thread.");
        this.zzajs.zzapa = adSizeParcel;
        if (!(this.zzajs.zzapb == null || this.zzajs.zzapb.zzbtm == null || this.zzajs.zzapw != 0)) {
            this.zzajs.zzapb.zzbtm.zza(adSizeParcel);
        }
        if (this.zzajs.zzaox != null) {
            if (this.zzajs.zzaox.getChildCount() > 1) {
                this.zzajs.zzaox.removeView(this.zzajs.zzaox.getNextView());
            }
            this.zzajs.zzaox.setMinimumWidth(adSizeParcel.widthPixels);
            this.zzajs.zzaox.setMinimumHeight(adSizeParcel.heightPixels);
            this.zzajs.zzaox.requestLayout();
        }
    }

    public void zza(@Nullable VideoOptionsParcel videoOptionsParcel) {
        zzab.zzhi("setVideoOptions must be called on the main UI thread.");
        this.zzajs.zzapp = videoOptionsParcel;
    }

    public void zza(com.google.android.gms.ads.internal.client.zzp com_google_android_gms_ads_internal_client_zzp) {
        zzab.zzhi("setAdListener must be called on the main UI thread.");
        this.zzajs.zzape = com_google_android_gms_ads_internal_client_zzp;
    }

    public void zza(zzq com_google_android_gms_ads_internal_client_zzq) {
        zzab.zzhi("setAdListener must be called on the main UI thread.");
        this.zzajs.zzapf = com_google_android_gms_ads_internal_client_zzq;
    }

    public void zza(zzw com_google_android_gms_ads_internal_client_zzw) {
        zzab.zzhi("setAppEventListener must be called on the main UI thread.");
        this.zzajs.zzapg = com_google_android_gms_ads_internal_client_zzw;
    }

    public void zza(zzy com_google_android_gms_ads_internal_client_zzy) {
        zzab.zzhi("setCorrelationIdProvider must be called on the main UI thread");
        this.zzajs.zzaph = com_google_android_gms_ads_internal_client_zzy;
    }

    public void zza(zzd com_google_android_gms_ads_internal_reward_client_zzd) {
        zzab.zzhi("setRewardedVideoAdListener can only be called from the UI thread.");
        this.zzajs.zzapr = com_google_android_gms_ads_internal_reward_client_zzd;
    }

    protected void zza(@Nullable RewardItemParcel rewardItemParcel) {
        if (this.zzajs.zzapr != null) {
            try {
                String str = "";
                int i = 0;
                if (rewardItemParcel != null) {
                    str = rewardItemParcel.type;
                    i = rewardItemParcel.zzcid;
                }
                this.zzajs.zzapr.zza(new zzjd(str, i));
            } catch (Throwable e) {
                zzb.zzd("Could not call RewardedVideoAdListener.onRewarded().", e);
            }
        }
    }

    public void zza(zzdo com_google_android_gms_internal_zzdo) {
        throw new IllegalStateException("setOnCustomRenderedAdLoadedListener is not supported for current ad type");
    }

    public void zza(zzho com_google_android_gms_internal_zzho) {
        throw new IllegalStateException("setInAppPurchaseListener is not supported for current ad type");
    }

    public void zza(zzhs com_google_android_gms_internal_zzhs, String str) {
        throw new IllegalStateException("setPlayStorePurchaseParams is not supported for current ad type");
    }

    public void zza(com.google.android.gms.internal.zzju.zza com_google_android_gms_internal_zzju_zza) {
        if (!(com_google_android_gms_internal_zzju_zza.zzciq.zzccc == -1 || TextUtils.isEmpty(com_google_android_gms_internal_zzju_zza.zzciq.zzccl))) {
            long zzs = zzs(com_google_android_gms_internal_zzju_zza.zzciq.zzccl);
            if (zzs != -1) {
                zzdi zzc = this.zzajn.zzc(zzs + com_google_android_gms_internal_zzju_zza.zzciq.zzccc);
                this.zzajn.zza(zzc, "stc");
            }
        }
        this.zzajn.zzas(com_google_android_gms_internal_zzju_zza.zzciq.zzccl);
        this.zzajn.zza(this.zzajo, "arf");
        this.zzajp = this.zzajn.zzkg();
        this.zzajn.zzh("gqi", com_google_android_gms_internal_zzju_zza.zzciq.zzccm);
        this.zzajs.zzaoy = null;
        this.zzajs.zzapc = com_google_android_gms_internal_zzju_zza;
        zza(com_google_android_gms_internal_zzju_zza, this.zzajn);
    }

    protected abstract void zza(com.google.android.gms.internal.zzju.zza com_google_android_gms_internal_zzju_zza, zzdk com_google_android_gms_internal_zzdk);

    public void zza(HashSet<zzjv> hashSet) {
        this.zzajs.zza(hashSet);
    }

    protected abstract boolean zza(AdRequestParcel adRequestParcel, zzdk com_google_android_gms_internal_zzdk);

    boolean zza(zzju com_google_android_gms_internal_zzju) {
        return false;
    }

    protected abstract boolean zza(@Nullable zzju com_google_android_gms_internal_zzju, zzju com_google_android_gms_internal_zzju2);

    protected void zzb(View view) {
        com.google.android.gms.ads.internal.zzv.zza com_google_android_gms_ads_internal_zzv_zza = this.zzajs.zzaox;
        if (com_google_android_gms_ads_internal_zzv_zza != null) {
            com_google_android_gms_ads_internal_zzv_zza.addView(view, zzu.zzfs().zztm());
        }
    }

    public void zzb(zzju com_google_android_gms_internal_zzju) {
        this.zzajn.zza(this.zzajp, "awr");
        this.zzajs.zzaoz = null;
        if (!(com_google_android_gms_internal_zzju.errorCode == -2 || com_google_android_gms_internal_zzju.errorCode == 3)) {
            zzu.zzft().zzb(this.zzajs.zzgl());
        }
        if (com_google_android_gms_internal_zzju.errorCode == -1) {
            this.zzajq = false;
            return;
        }
        if (zza(com_google_android_gms_internal_zzju)) {
            zzb.zzcv("Ad refresh scheduled.");
        }
        if (com_google_android_gms_internal_zzju.errorCode != -2) {
            zzh(com_google_android_gms_internal_zzju.errorCode);
            return;
        }
        if (this.zzajs.zzapu == null) {
            this.zzajs.zzapu = new zzka(this.zzajs.zzaou);
        }
        this.zzaju.zzi(this.zzajs.zzapb);
        if (zza(this.zzajs.zzapb, com_google_android_gms_internal_zzju)) {
            this.zzajs.zzapb = com_google_android_gms_internal_zzju;
            this.zzajs.zzgu();
            this.zzajn.zzh("is_mraid", this.zzajs.zzapb.zzho() ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
            this.zzajn.zzh("is_mediation", this.zzajs.zzapb.zzcby ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
            if (!(this.zzajs.zzapb.zzbtm == null || this.zzajs.zzapb.zzbtm.zzuj() == null)) {
                this.zzajn.zzh("is_delay_pl", this.zzajs.zzapb.zzbtm.zzuj().zzuy() ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
            }
            this.zzajn.zza(this.zzajo, "ttc");
            if (zzu.zzft().zzsl() != null) {
                zzu.zzft().zzsl().zza(this.zzajn);
            }
            if (this.zzajs.zzgp()) {
                zzdu();
            }
        }
        if (com_google_android_gms_internal_zzju.zzbnp != null) {
            zzu.zzfq().zza(this.zzajs.zzagf, com_google_android_gms_internal_zzju.zzbnp);
        }
    }

    public boolean zzb(AdRequestParcel adRequestParcel) {
        zzab.zzhi("loadAd must be called on the main UI thread.");
        AdRequestParcel zza = zza(adRequestParcel);
        if (this.zzajs.zzaoy == null && this.zzajs.zzaoz == null) {
            zzb.zzcw("Starting ad request.");
            zzdl();
            this.zzajo = this.zzajn.zzkg();
            if (!zza.zzatp) {
                String valueOf = String.valueOf(zzm.zziw().zzaq(this.zzajs.zzagf));
                zzb.zzcw(new StringBuilder(String.valueOf(valueOf).length() + 71).append("Use AdRequest.Builder.addTestDevice(\"").append(valueOf).append("\") to get test ads on this device.").toString());
            }
            this.zzajq = zza(zza, this.zzajn);
            return this.zzajq;
        }
        if (this.zzajt != null) {
            zzb.zzcx("Aborting last ad request since another ad request is already in progress. The current request object will still be cached for future refreshes.");
        } else {
            zzb.zzcx("Loading already in progress, saving this object for future refreshes.");
        }
        this.zzajt = zza;
        return false;
    }

    protected void zzc(@Nullable zzju com_google_android_gms_internal_zzju) {
        if (com_google_android_gms_internal_zzju == null) {
            zzb.zzcx("Ad state was null when trying to ping impression URLs.");
            return;
        }
        zzb.zzcv("Pinging Impression URLs.");
        this.zzajs.zzapd.zzry();
        if (com_google_android_gms_internal_zzju.zzbnn != null && !com_google_android_gms_internal_zzju.zzcin) {
            zzu.zzfq().zza(this.zzajs.zzagf, this.zzajs.zzaow.zzcs, com_google_android_gms_internal_zzju.zzbnn);
            com_google_android_gms_internal_zzju.zzcin = true;
        }
    }

    protected boolean zzc(AdRequestParcel adRequestParcel) {
        if (this.zzajs.zzaox == null) {
            return false;
        }
        ViewParent parent = this.zzajs.zzaox.getParent();
        if (!(parent instanceof View)) {
            return false;
        }
        View view = (View) parent;
        return zzu.zzfq().zza(view, view.getContext());
    }

    public void zzd(AdRequestParcel adRequestParcel) {
        if (zzc(adRequestParcel)) {
            zzb(adRequestParcel);
            return;
        }
        zzb.zzcw("Ad is not visible. Not refreshing ad.");
        this.zzajr.zzg(adRequestParcel);
    }

    public void zzdl() {
        this.zzajn = new zzdk(((Boolean) zzdc.zzaze.get()).booleanValue(), "load_ad", this.zzajs.zzapa.zzaur);
        this.zzajo = new zzdi(-1, null, null);
        this.zzajp = new zzdi(-1, null, null);
    }

    public com.google.android.gms.dynamic.zzd zzdm() {
        zzab.zzhi("getAdFrame must be called on the main UI thread.");
        return zze.zzac(this.zzajs.zzaox);
    }

    @Nullable
    public AdSizeParcel zzdn() {
        zzab.zzhi("getAdSize must be called on the main UI thread.");
        return this.zzajs.zzapa == null ? null : new ThinAdSizeParcel(this.zzajs.zzapa);
    }

    public void zzdo() {
        zzds();
    }

    public void zzdp() {
        zzab.zzhi("recordManualImpression must be called on the main UI thread.");
        if (this.zzajs.zzapb == null) {
            zzb.zzcx("Ad state was null when trying to ping manual tracking URLs.");
            return;
        }
        zzb.zzcv("Pinging manual tracking URLs.");
        if (this.zzajs.zzapb.zzcca != null && !this.zzajs.zzapb.zzcio) {
            zzu.zzfq().zza(this.zzajs.zzagf, this.zzajs.zzaow.zzcs, this.zzajs.zzapb.zzcca);
            this.zzajs.zzapb.zzcio = true;
        }
    }

    public com.google.android.gms.ads.internal.client.zzab zzdq() {
        return null;
    }

    protected void zzdr() {
        zzb.zzcw("Ad closing.");
        if (this.zzajs.zzapf != null) {
            try {
                this.zzajs.zzapf.onAdClosed();
            } catch (Throwable e) {
                zzb.zzd("Could not call AdListener.onAdClosed().", e);
            }
        }
        if (this.zzajs.zzapr != null) {
            try {
                this.zzajs.zzapr.onRewardedVideoAdClosed();
            } catch (Throwable e2) {
                zzb.zzd("Could not call RewardedVideoAdListener.onRewardedVideoAdClosed().", e2);
            }
        }
    }

    protected void zzds() {
        zzb.zzcw("Ad leaving application.");
        if (this.zzajs.zzapf != null) {
            try {
                this.zzajs.zzapf.onAdLeftApplication();
            } catch (Throwable e) {
                zzb.zzd("Could not call AdListener.onAdLeftApplication().", e);
            }
        }
        if (this.zzajs.zzapr != null) {
            try {
                this.zzajs.zzapr.onRewardedVideoAdLeftApplication();
            } catch (Throwable e2) {
                zzb.zzd("Could not call  RewardedVideoAdListener.onRewardedVideoAdLeftApplication().", e2);
            }
        }
    }

    protected void zzdt() {
        zzb.zzcw("Ad opening.");
        if (this.zzajs.zzapf != null) {
            try {
                this.zzajs.zzapf.onAdOpened();
            } catch (Throwable e) {
                zzb.zzd("Could not call AdListener.onAdOpened().", e);
            }
        }
        if (this.zzajs.zzapr != null) {
            try {
                this.zzajs.zzapr.onRewardedVideoAdOpened();
            } catch (Throwable e2) {
                zzb.zzd("Could not call RewardedVideoAdListener.onRewardedVideoAdOpened().", e2);
            }
        }
    }

    protected void zzdu() {
        zzb.zzcw("Ad finished loading.");
        this.zzajq = false;
        if (this.zzajs.zzapf != null) {
            try {
                this.zzajs.zzapf.onAdLoaded();
            } catch (Throwable e) {
                zzb.zzd("Could not call AdListener.onAdLoaded().", e);
            }
        }
        if (this.zzajs.zzapr != null) {
            try {
                this.zzajs.zzapr.onRewardedVideoAdLoaded();
            } catch (Throwable e2) {
                zzb.zzd("Could not call RewardedVideoAdListener.onRewardedVideoAdLoaded().", e2);
            }
        }
    }

    protected void zzdv() {
        if (this.zzajs.zzapr != null) {
            try {
                this.zzajs.zzapr.onRewardedVideoStarted();
            } catch (Throwable e) {
                zzb.zzd("Could not call RewardedVideoAdListener.onVideoStarted().", e);
            }
        }
    }

    protected void zzh(int i) {
        zzb.zzcx("Failed to load ad: " + i);
        this.zzajq = false;
        if (this.zzajs.zzapf != null) {
            try {
                this.zzajs.zzapf.onAdFailedToLoad(i);
            } catch (Throwable e) {
                zzb.zzd("Could not call AdListener.onAdFailedToLoad().", e);
            }
        }
        if (this.zzajs.zzapr != null) {
            try {
                this.zzajs.zzapr.onRewardedVideoAdFailedToLoad(i);
            } catch (Throwable e2) {
                zzb.zzd("Could not call RewardedVideoAdListener.onRewardedVideoAdFailedToLoad().", e2);
            }
        }
    }

    long zzs(String str) {
        int indexOf = str.indexOf("ufe");
        int indexOf2 = str.indexOf(44, indexOf);
        if (indexOf2 == -1) {
            indexOf2 = str.length();
        }
        try {
            return Long.parseLong(str.substring(indexOf + 4, indexOf2));
        } catch (IndexOutOfBoundsException e) {
            zzb.zzcx("Invalid index for Url fetch time in CSI latency info.");
            return -1;
        } catch (NumberFormatException e2) {
            zzb.zzcx("Cannot find valid format of Url fetch time in CSI latency info.");
            return -1;
        }
    }
}
