package com.google.android.gms.ads.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzkh;
import java.lang.ref.WeakReference;

@zzin
public class zzr {
    private final zza zzanb;
    @Nullable
    private AdRequestParcel zzanc;
    private boolean zzand;
    private boolean zzane;
    private long zzanf;
    private final Runnable zzw;

    public zzr(zza com_google_android_gms_ads_internal_zza) {
        this(com_google_android_gms_ads_internal_zza, new zza(zzkh.zzclc));
    }

    zzr(zza com_google_android_gms_ads_internal_zza, zza com_google_android_gms_ads_internal_zzr_zza) {
        this.zzand = false;
        this.zzane = false;
        this.zzanf = 0;
        this.zzanb = com_google_android_gms_ads_internal_zzr_zza;
        this.zzw = new 1(this, new WeakReference(com_google_android_gms_ads_internal_zza));
    }

    public void cancel() {
        this.zzand = false;
        this.zzanb.removeCallbacks(this.zzw);
    }

    public void pause() {
        this.zzane = true;
        if (this.zzand) {
            this.zzanb.removeCallbacks(this.zzw);
        }
    }

    public void resume() {
        this.zzane = false;
        if (this.zzand) {
            this.zzand = false;
            zza(this.zzanc, this.zzanf);
        }
    }

    public void zza(AdRequestParcel adRequestParcel, long j) {
        if (this.zzand) {
            zzb.zzcx("An ad refresh is already scheduled.");
            return;
        }
        this.zzanc = adRequestParcel;
        this.zzand = true;
        this.zzanf = j;
        if (!this.zzane) {
            zzb.zzcw("Scheduling ad refresh " + j + " milliseconds from now.");
            this.zzanb.postDelayed(this.zzw, j);
        }
    }

    public boolean zzfc() {
        return this.zzand;
    }

    public void zzg(AdRequestParcel adRequestParcel) {
        zza(adRequestParcel, 60000);
    }
}
