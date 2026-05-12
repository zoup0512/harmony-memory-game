package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import io.fabric.sdk.android.services.common.AbstractSpiCall;

@zzin
public class zzjy {
    private final Object zzail = new Object();
    final String zzcjm;
    long zzckc = -1;
    long zzckd = -1;
    int zzcke = -1;
    int zzckf = 0;
    int zzckg = 0;

    public zzjy(String str) {
        this.zzcjm = str;
    }

    public static boolean zzab(Context context) {
        int identifier = context.getResources().getIdentifier("Theme.Translucent", AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, AbstractSpiCall.ANDROID_CLIENT_TYPE);
        if (identifier == 0) {
            zzb.zzcw("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
        try {
            if (identifier == context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), AdActivity.CLASS_NAME), 0).theme) {
                return true;
            }
            zzb.zzcw("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        } catch (NameNotFoundException e) {
            zzb.zzcx("Fail to fetch AdActivity theme");
            zzb.zzcw("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
    }

    public void zzb(AdRequestParcel adRequestParcel, long j) {
        synchronized (this.zzail) {
            if (this.zzckd == -1) {
                this.zzckd = j;
                this.zzckc = this.zzckd;
            } else {
                this.zzckc = j;
            }
            if (adRequestParcel.extras == null || adRequestParcel.extras.getInt("gw", 2) != 1) {
                this.zzcke++;
                return;
            }
        }
    }

    public Bundle zze(Context context, String str) {
        Bundle bundle;
        synchronized (this.zzail) {
            bundle = new Bundle();
            bundle.putString("session_id", this.zzcjm);
            bundle.putLong("basets", this.zzckd);
            bundle.putLong("currts", this.zzckc);
            bundle.putString("seq_num", str);
            bundle.putInt("preqs", this.zzcke);
            bundle.putInt("pclick", this.zzckf);
            bundle.putInt("pimp", this.zzckg);
            bundle.putBoolean("support_transparent_background", zzab(context));
        }
        return bundle;
    }

    public void zzry() {
        synchronized (this.zzail) {
            this.zzckg++;
        }
    }

    public void zzrz() {
        synchronized (this.zzail) {
            this.zzckf++;
        }
    }

    public long zzsx() {
        return this.zzckd;
    }
}
