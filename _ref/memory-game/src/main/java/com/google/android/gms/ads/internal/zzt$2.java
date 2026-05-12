package com.google.android.gms.ads.internal;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.google.android.gms.internal.zzkd;

class zzt$2 implements OnTouchListener {
    final /* synthetic */ zzt zzano;

    zzt$2(zzt com_google_android_gms_ads_internal_zzt) {
        this.zzano = com_google_android_gms_ads_internal_zzt;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (zzt.zzb(this.zzano) != null) {
            try {
                zzt.zzb(this.zzano).zza(motionEvent);
            } catch (Throwable e) {
                zzkd.zzd("Unable to process ad data", e);
            }
        }
        return false;
    }
}
