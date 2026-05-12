package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.common.internal.zzab;
import java.util.Iterator;
import java.util.LinkedList;

@zzin
class zzfm {
    private final String zzaln;
    private final LinkedList<zza> zzbkr = new LinkedList();
    private AdRequestParcel zzbks;
    private final int zzbkt;
    private boolean zzbku;

    zzfm(AdRequestParcel adRequestParcel, String str, int i) {
        zzab.zzy(adRequestParcel);
        zzab.zzy(str);
        this.zzbks = adRequestParcel;
        this.zzaln = str;
        this.zzbkt = i;
    }

    String getAdUnitId() {
        return this.zzaln;
    }

    int getNetworkType() {
        return this.zzbkt;
    }

    int size() {
        return this.zzbkr.size();
    }

    void zza(zzfh com_google_android_gms_internal_zzfh, AdRequestParcel adRequestParcel) {
        this.zzbkr.add(new zza(this, com_google_android_gms_internal_zzfh, adRequestParcel));
    }

    void zzb(zzfh com_google_android_gms_internal_zzfh) {
        zza com_google_android_gms_internal_zzfm_zza = new zza(this, com_google_android_gms_internal_zzfh);
        this.zzbkr.add(com_google_android_gms_internal_zzfm_zza);
        com_google_android_gms_internal_zzfm_zza.zzlv();
    }

    AdRequestParcel zzlq() {
        return this.zzbks;
    }

    int zzlr() {
        Iterator it = this.zzbkr.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = ((zza) it.next()).zzbkz ? i + 1 : i;
        }
        return i;
    }

    void zzls() {
        Iterator it = this.zzbkr.iterator();
        while (it.hasNext()) {
            ((zza) it.next()).zzlv();
        }
    }

    void zzlt() {
        this.zzbku = true;
    }

    boolean zzlu() {
        return this.zzbku;
    }

    zza zzm(@Nullable AdRequestParcel adRequestParcel) {
        if (adRequestParcel != null) {
            this.zzbks = adRequestParcel;
        }
        return (zza) this.zzbkr.remove();
    }
}
