package com.google.android.gms.ads.internal.reward.client;

import android.content.Context;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.internal.client.zzh;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzin;

@zzin
public class zzi implements RewardedVideoAd {
    private final Context mContext;
    private final Object zzail = new Object();
    private final zzb zzchl;
    private RewardedVideoAdListener zzfh;

    public zzi(Context context, zzb com_google_android_gms_ads_internal_reward_client_zzb) {
        this.zzchl = com_google_android_gms_ads_internal_reward_client_zzb;
        this.mContext = context;
    }

    public void destroy() {
        destroy(null);
    }

    public void destroy(Context context) {
        synchronized (this.zzail) {
            if (this.zzchl == null) {
                return;
            }
            try {
                this.zzchl.zzh(zze.zzac(context));
            } catch (Throwable e) {
                zzb.zzd("Could not forward destroy to RewardedVideoAd", e);
            }
        }
    }

    public RewardedVideoAdListener getRewardedVideoAdListener() {
        RewardedVideoAdListener rewardedVideoAdListener;
        synchronized (this.zzail) {
            rewardedVideoAdListener = this.zzfh;
        }
        return rewardedVideoAdListener;
    }

    public String getUserId() {
        zzb.zzcx("RewardedVideoAd.getUserId() is deprecated. Please do not call this method.");
        return null;
    }

    public boolean isLoaded() {
        boolean z = false;
        synchronized (this.zzail) {
            if (this.zzchl == null) {
            } else {
                try {
                    z = this.zzchl.isLoaded();
                } catch (Throwable e) {
                    zzb.zzd("Could not forward isLoaded to RewardedVideoAd", e);
                }
            }
        }
        return z;
    }

    public void loadAd(String str, AdRequest adRequest) {
        synchronized (this.zzail) {
            if (this.zzchl == null) {
                return;
            }
            try {
                this.zzchl.zza(zzh.zzih().zza(this.mContext, adRequest.zzdc(), str));
            } catch (Throwable e) {
                zzb.zzd("Could not forward loadAd to RewardedVideoAd", e);
            }
        }
    }

    public void pause() {
        pause(null);
    }

    public void pause(Context context) {
        synchronized (this.zzail) {
            if (this.zzchl == null) {
                return;
            }
            try {
                this.zzchl.zzf(zze.zzac(context));
            } catch (Throwable e) {
                zzb.zzd("Could not forward pause to RewardedVideoAd", e);
            }
        }
    }

    public void resume() {
        resume(null);
    }

    public void resume(Context context) {
        synchronized (this.zzail) {
            if (this.zzchl == null) {
                return;
            }
            try {
                this.zzchl.zzg(zze.zzac(context));
            } catch (Throwable e) {
                zzb.zzd("Could not forward resume to RewardedVideoAd", e);
            }
        }
    }

    public void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        synchronized (this.zzail) {
            this.zzfh = rewardedVideoAdListener;
            if (this.zzchl != null) {
                try {
                    this.zzchl.zza(new zzg(rewardedVideoAdListener));
                } catch (Throwable e) {
                    zzb.zzd("Could not forward setRewardedVideoAdListener to RewardedVideoAd", e);
                }
            }
        }
    }

    public void setUserId(String str) {
        zzb.zzcx("RewardedVideoAd.setUserId() is deprecated. Please do not call this method.");
    }

    public void show() {
        synchronized (this.zzail) {
            if (this.zzchl == null) {
                return;
            }
            try {
                this.zzchl.show();
            } catch (Throwable e) {
                zzb.zzd("Could not forward show to RewardedVideoAd", e);
            }
        }
    }
}
