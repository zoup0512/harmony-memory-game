package com.google.android.gms.analytics;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzmd;
import java.util.ListIterator;

public class zza extends zzh<zza> {
    private final zzf zzcrn;
    private boolean zzcro;

    public zza(zzf com_google_android_gms_analytics_internal_zzf) {
        super(com_google_android_gms_analytics_internal_zzf.zzyz(), com_google_android_gms_analytics_internal_zzf.zzyw());
        this.zzcrn = com_google_android_gms_analytics_internal_zzf;
    }

    public void enableAdvertisingIdCollection(boolean z) {
        this.zzcro = z;
    }

    protected void zza(zze com_google_android_gms_analytics_zze) {
        zzmd com_google_android_gms_internal_zzmd = (zzmd) com_google_android_gms_analytics_zze.zzb(zzmd.class);
        if (TextUtils.isEmpty(com_google_android_gms_internal_zzmd.zzwb())) {
            com_google_android_gms_internal_zzmd.setClientId(this.zzcrn.zzzn().zzaav());
        }
        if (this.zzcro && TextUtils.isEmpty(com_google_android_gms_internal_zzmd.zzxy())) {
            com.google.android.gms.analytics.internal.zza zzzm = this.zzcrn.zzzm();
            com_google_android_gms_internal_zzmd.zzdx(zzzm.zzyk());
            com_google_android_gms_internal_zzmd.zzao(zzzm.zzxz());
        }
    }

    public void zzdg(String str) {
        zzab.zzhr(str);
        zzdh(str);
        zzwr().add(new zzb(this.zzcrn, str));
    }

    public void zzdh(String str) {
        Uri zzdi = zzb.zzdi(str);
        ListIterator listIterator = zzwr().listIterator();
        while (listIterator.hasNext()) {
            if (zzdi.equals(((zzk) listIterator.next()).zzvu())) {
                listIterator.remove();
            }
        }
    }

    zzf zzvq() {
        return this.zzcrn;
    }

    public zze zzvr() {
        zze zzwf = zzwq().zzwf();
        zzwf.zza(this.zzcrn.zzze().zzaad());
        zzwf.zza(this.zzcrn.zzzf().zzack());
        zzd(zzwf);
        return zzwf;
    }
}
