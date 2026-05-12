package com.google.android.gms.ads;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.zzab;
import com.google.android.gms.ads.internal.client.zzap;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzin;

@zzin
public final class VideoController {
    private final Object zzail = new Object();
    @Nullable
    private zzab zzaim;
    @Nullable
    private VideoLifecycleCallbacks zzain;

    @Nullable
    public VideoLifecycleCallbacks getVideoLifecycleCallbacks() {
        VideoLifecycleCallbacks videoLifecycleCallbacks;
        synchronized (this.zzail) {
            videoLifecycleCallbacks = this.zzain;
        }
        return videoLifecycleCallbacks;
    }

    public boolean hasVideoContent() {
        boolean z;
        synchronized (this.zzail) {
            z = this.zzaim != null;
        }
        return z;
    }

    public void setVideoLifecycleCallbacks(VideoLifecycleCallbacks videoLifecycleCallbacks) {
        com.google.android.gms.common.internal.zzab.zzb(videoLifecycleCallbacks, "VideoLifecycleCallbacks may not be null.");
        synchronized (this.zzail) {
            this.zzain = videoLifecycleCallbacks;
            if (this.zzaim == null) {
                return;
            }
            try {
                this.zzaim.zza(new zzap(videoLifecycleCallbacks));
            } catch (Throwable e) {
                zzb.zzb("Unable to call setVideoLifecycleCallbacks on video controller.", e);
            }
        }
    }

    public void zza(zzab com_google_android_gms_ads_internal_client_zzab) {
        synchronized (this.zzail) {
            this.zzaim = com_google_android_gms_ads_internal_client_zzab;
            if (this.zzain != null) {
                setVideoLifecycleCallbacks(this.zzain);
            }
        }
    }
}
