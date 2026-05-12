package com.google.android.gms.ads.internal.client;

import android.content.Context;
import com.google.android.gms.ads.internal.reward.client.zzi;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzgi;
import com.google.android.gms.internal.zzin;
import com.mopub.volley.DefaultRetryPolicy;

@zzin
public class zzag {
    private static final Object zzamr = new Object();
    private static zzag zzawr;
    private zzz zzaws;
    private RewardedVideoAd zzawt;

    private zzag() {
    }

    public static zzag zzjo() {
        zzag com_google_android_gms_ads_internal_client_zzag;
        synchronized (zzamr) {
            if (zzawr == null) {
                zzawr = new zzag();
            }
            com_google_android_gms_ads_internal_client_zzag = zzawr;
        }
        return com_google_android_gms_ads_internal_client_zzag;
    }

    public RewardedVideoAd getRewardedVideoAdInstance(Context context) {
        RewardedVideoAd rewardedVideoAd;
        synchronized (zzamr) {
            if (this.zzawt != null) {
                rewardedVideoAd = this.zzawt;
            } else {
                this.zzawt = new zzi(context, zzm.zzix().zza(context, new zzgi()));
                rewardedVideoAd = this.zzawt;
            }
        }
        return rewardedVideoAd;
    }

    public void setAppMuted(boolean z) {
        zzab.zza(this.zzaws != null, "MobileAds.initialize() must be called prior to setting the app volume.");
        try {
            this.zzaws.setAppMuted(z);
        } catch (Throwable e) {
            zzb.zzb("Unable to set app mute state.", e);
        }
    }

    public void setAppVolume(float f) {
        boolean z = true;
        boolean z2 = 0.0f <= f && f <= DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        zzab.zzb(z2, "The app volume must be a value between 0 and 1 inclusive.");
        if (this.zzaws == null) {
            z = false;
        }
        zzab.zza(z, "MobileAds.initialize() must be called prior to setting the app volume.");
        try {
            this.zzaws.setAppVolume(f);
        } catch (Throwable e) {
            zzb.zzb("Unable to set app volume.", e);
        }
    }

    public void zza(Context context, String str, zzah com_google_android_gms_ads_internal_client_zzah) {
        synchronized (zzamr) {
            if (this.zzaws != null) {
            } else if (context == null) {
                throw new IllegalArgumentException("Context cannot be null.");
            } else {
                try {
                    this.zzaws = zzm.zzix().zzl(context);
                    this.zzaws.initialize();
                    if (str != null) {
                        this.zzaws.zzu(str);
                    }
                } catch (Throwable e) {
                    zzb.zzd("Fail to initialize or set applicationCode on mobile ads setting manager", e);
                }
            }
        }
    }
}
