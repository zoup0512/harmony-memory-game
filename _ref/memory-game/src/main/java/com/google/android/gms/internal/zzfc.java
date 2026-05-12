package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzu;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@zzin
public class zzfc implements Iterable<zzfb> {
    private final List<zzfb> zzbje = new LinkedList();

    private zzfb zzf(zzlh com_google_android_gms_internal_zzlh) {
        Iterator it = zzu.zzgj().iterator();
        while (it.hasNext()) {
            zzfb com_google_android_gms_internal_zzfb = (zzfb) it.next();
            if (com_google_android_gms_internal_zzfb.zzbgf == com_google_android_gms_internal_zzlh) {
                return com_google_android_gms_internal_zzfb;
            }
        }
        return null;
    }

    public Iterator<zzfb> iterator() {
        return this.zzbje.iterator();
    }

    public void zza(zzfb com_google_android_gms_internal_zzfb) {
        this.zzbje.add(com_google_android_gms_internal_zzfb);
    }

    public void zzb(zzfb com_google_android_gms_internal_zzfb) {
        this.zzbje.remove(com_google_android_gms_internal_zzfb);
    }

    public boolean zzd(zzlh com_google_android_gms_internal_zzlh) {
        zzfb zzf = zzf(com_google_android_gms_internal_zzlh);
        if (zzf == null) {
            return false;
        }
        zzf.zzbjb.abort();
        return true;
    }

    public boolean zze(zzlh com_google_android_gms_internal_zzlh) {
        return zzf(com_google_android_gms_internal_zzlh) != null;
    }

    public int zzlk() {
        return this.zzbje.size();
    }
}
