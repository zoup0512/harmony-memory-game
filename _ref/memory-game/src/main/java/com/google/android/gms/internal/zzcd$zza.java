package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.ads.internal.formats.zzh;
import java.lang.ref.WeakReference;

public class zzcd$zza implements zzck {
    private WeakReference<zzh> zzarb;

    public zzcd$zza(zzh com_google_android_gms_ads_internal_formats_zzh) {
        this.zzarb = new WeakReference(com_google_android_gms_ads_internal_formats_zzh);
    }

    @Nullable
    public View zzhh() {
        zzh com_google_android_gms_ads_internal_formats_zzh = (zzh) this.zzarb.get();
        return com_google_android_gms_ads_internal_formats_zzh != null ? com_google_android_gms_ads_internal_formats_zzh.zzlc() : null;
    }

    public boolean zzhi() {
        return this.zzarb.get() == null;
    }

    public zzck zzhj() {
        return new zzcd$zzb((zzh) this.zzarb.get());
    }
}
