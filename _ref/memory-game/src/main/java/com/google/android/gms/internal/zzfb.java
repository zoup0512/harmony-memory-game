package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzu;

@zzin
public class zzfb extends zzkc {
    final zzlh zzbgf;
    final zzfd zzbjb;
    private final String zzbjc;

    zzfb(zzlh com_google_android_gms_internal_zzlh, zzfd com_google_android_gms_internal_zzfd, String str) {
        this.zzbgf = com_google_android_gms_internal_zzlh;
        this.zzbjb = com_google_android_gms_internal_zzfd;
        this.zzbjc = str;
        zzu.zzgj().zza(this);
    }

    public void onStop() {
        this.zzbjb.abort();
    }

    public void zzew() {
        try {
            this.zzbjb.zzaz(this.zzbjc);
        } finally {
            zzkh.zzclc.post(new 1(this));
        }
    }
}
