package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzep;
import com.google.android.gms.internal.zzkd;
import com.google.android.gms.internal.zzlh;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

class zzn$4 implements zzep {
    final /* synthetic */ CountDownLatch zzajw;

    zzn$4(CountDownLatch countDownLatch) {
        this.zzajw = countDownLatch;
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        zzkd.zzcx("Adapter returned an ad, but assets substitution failed");
        this.zzajw.countDown();
        com_google_android_gms_internal_zzlh.destroy();
    }
}
