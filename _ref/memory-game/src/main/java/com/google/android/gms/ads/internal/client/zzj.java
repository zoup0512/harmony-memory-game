package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.internal.client.zzw.zza;
import com.google.android.gms.internal.zzin;

@zzin
public final class zzj extends zza {
    private final AppEventListener zzaux;

    public zzj(AppEventListener appEventListener) {
        this.zzaux = appEventListener;
    }

    public void onAppEvent(String str, String str2) {
        this.zzaux.onAppEvent(str, str2);
    }
}
