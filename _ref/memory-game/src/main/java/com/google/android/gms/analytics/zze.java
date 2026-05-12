package com.google.android.gms.analytics;

import com.google.android.gms.common.internal.zzab;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class zze {
    private final com.google.android.gms.common.util.zze zzaoc;
    private final zzh zzcsv;
    private boolean zzcsw;
    private long zzcsx;
    private long zzcsy;
    private long zzcsz;
    private long zzcta;
    private long zzctb;
    private boolean zzctc;
    private final Map<Class<? extends zzg>, zzg> zzctd;
    private final List<zzk> zzcte;

    zze(zze com_google_android_gms_analytics_zze) {
        this.zzcsv = com_google_android_gms_analytics_zze.zzcsv;
        this.zzaoc = com_google_android_gms_analytics_zze.zzaoc;
        this.zzcsx = com_google_android_gms_analytics_zze.zzcsx;
        this.zzcsy = com_google_android_gms_analytics_zze.zzcsy;
        this.zzcsz = com_google_android_gms_analytics_zze.zzcsz;
        this.zzcta = com_google_android_gms_analytics_zze.zzcta;
        this.zzctb = com_google_android_gms_analytics_zze.zzctb;
        this.zzcte = new ArrayList(com_google_android_gms_analytics_zze.zzcte);
        this.zzctd = new HashMap(com_google_android_gms_analytics_zze.zzctd.size());
        for (Entry entry : com_google_android_gms_analytics_zze.zzctd.entrySet()) {
            zzg zzc = zzc((Class) entry.getKey());
            ((zzg) entry.getValue()).zzb(zzc);
            this.zzctd.put((Class) entry.getKey(), zzc);
        }
    }

    zze(zzh com_google_android_gms_analytics_zzh, com.google.android.gms.common.util.zze com_google_android_gms_common_util_zze) {
        zzab.zzy(com_google_android_gms_analytics_zzh);
        zzab.zzy(com_google_android_gms_common_util_zze);
        this.zzcsv = com_google_android_gms_analytics_zzh;
        this.zzaoc = com_google_android_gms_common_util_zze;
        this.zzcta = 1800000;
        this.zzctb = 3024000000L;
        this.zzctd = new HashMap();
        this.zzcte = new ArrayList();
    }

    private static <T extends zzg> T zzc(Class<T> cls) {
        try {
            return (zzg) cls.newInstance();
        } catch (Throwable e) {
            throw new IllegalArgumentException("dataType doesn't have default constructor", e);
        } catch (Throwable e2) {
            throw new IllegalArgumentException("dataType default constructor is not accessible", e2);
        }
    }

    public <T extends zzg> T zza(Class<T> cls) {
        return (zzg) this.zzctd.get(cls);
    }

    public void zza(zzg com_google_android_gms_analytics_zzg) {
        zzab.zzy(com_google_android_gms_analytics_zzg);
        Class cls = com_google_android_gms_analytics_zzg.getClass();
        if (cls.getSuperclass() != zzg.class) {
            throw new IllegalArgumentException();
        }
        com_google_android_gms_analytics_zzg.zzb(zzb(cls));
    }

    public <T extends zzg> T zzb(Class<T> cls) {
        zzg com_google_android_gms_analytics_zzg = (zzg) this.zzctd.get(cls);
        if (com_google_android_gms_analytics_zzg != null) {
            return com_google_android_gms_analytics_zzg;
        }
        T zzc = zzc(cls);
        this.zzctd.put(cls, zzc);
        return zzc;
    }

    public void zzn(long j) {
        this.zzcsy = j;
    }

    public zze zzwf() {
        return new zze(this);
    }

    public Collection<zzg> zzwg() {
        return this.zzctd.values();
    }

    public List<zzk> zzwh() {
        return this.zzcte;
    }

    public long zzwi() {
        return this.zzcsx;
    }

    public void zzwj() {
        zzwn().zze(this);
    }

    public boolean zzwk() {
        return this.zzcsw;
    }

    void zzwl() {
        this.zzcsz = this.zzaoc.elapsedRealtime();
        if (this.zzcsy != 0) {
            this.zzcsx = this.zzcsy;
        } else {
            this.zzcsx = this.zzaoc.currentTimeMillis();
        }
        this.zzcsw = true;
    }

    zzh zzwm() {
        return this.zzcsv;
    }

    zzi zzwn() {
        return this.zzcsv.zzwn();
    }

    boolean zzwo() {
        return this.zzctc;
    }

    void zzwp() {
        this.zzctc = true;
    }
}
