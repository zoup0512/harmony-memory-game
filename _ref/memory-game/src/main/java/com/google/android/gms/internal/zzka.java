package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.ads.internal.zzu;

@zzin
public class zzka {
    private final Object zzail;
    private final zzjx zzaob;
    private final String zzcit;
    private int zzckh;
    private int zzcki;

    zzka(zzjx com_google_android_gms_internal_zzjx, String str) {
        this.zzail = new Object();
        this.zzaob = com_google_android_gms_internal_zzjx;
        this.zzcit = str;
    }

    public zzka(String str) {
        this(zzu.zzft(), str);
    }

    public Bundle toBundle() {
        Bundle bundle;
        synchronized (this.zzail) {
            bundle = new Bundle();
            bundle.putInt("pmnli", this.zzckh);
            bundle.putInt("pmnll", this.zzcki);
        }
        return bundle;
    }

    public void zzh(int i, int i2) {
        synchronized (this.zzail) {
            this.zzckh = i;
            this.zzcki = i2;
            this.zzaob.zza(this.zzcit, this);
        }
    }
}
