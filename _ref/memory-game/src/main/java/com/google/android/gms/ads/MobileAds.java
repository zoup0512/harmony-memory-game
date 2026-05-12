package com.google.android.gms.ads;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.internal.client.zzag;
import com.google.android.gms.ads.internal.client.zzah;
import com.google.android.gms.ads.reward.RewardedVideoAd;

public class MobileAds {

    public static final class Settings {
        private final zzah zzaik = new zzah();

        @Deprecated
        public String getTrackingId() {
            return this.zzaik.getTrackingId();
        }

        @Deprecated
        public boolean isGoogleAnalyticsEnabled() {
            return this.zzaik.isGoogleAnalyticsEnabled();
        }

        @Deprecated
        public Settings setGoogleAnalyticsEnabled(boolean z) {
            this.zzaik.zzp(z);
            return this;
        }

        @Deprecated
        public Settings setTrackingId(String str) {
            this.zzaik.zzao(str);
            return this;
        }

        zzah zzde() {
            return this.zzaik;
        }
    }

    private MobileAds() {
    }

    public static RewardedVideoAd getRewardedVideoAdInstance(Context context) {
        return zzag.zzjo().getRewardedVideoAdInstance(context);
    }

    @Deprecated
    public static void initialize(Context context) {
        initialize(context, null, null);
    }

    @RequiresPermission("android.permission.INTERNET")
    public static void initialize(Context context, String str) {
        initialize(context, str, null);
    }

    @RequiresPermission("android.permission.INTERNET")
    @Deprecated
    public static void initialize(Context context, String str, Settings settings) {
        zzag.zzjo().zza(context, str, settings == null ? null : settings.zzde());
    }

    public static void setAppMuted(boolean z) {
        zzag.zzjo().setAppMuted(z);
    }

    public static void setAppVolume(float f) {
        zzag.zzjo().setAppVolume(f);
    }
}
