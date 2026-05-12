package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzep;
import com.google.android.gms.internal.zzlh;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

class zzn$3 implements zzep {
    final /* synthetic */ CountDownLatch zzajw;

    zzn$3(CountDownLatch countDownLatch) {
        this.zzajw = countDownLatch;
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        this.zzajw.countDown();
        com_google_android_gms_internal_zzlh.getView().setVisibility(0);
    }
}
