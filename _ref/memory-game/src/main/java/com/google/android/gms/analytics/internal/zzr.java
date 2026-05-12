package com.google.android.gms.analytics.internal;

import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zzt;
import java.util.HashSet;
import java.util.Set;

public class zzr {
    private final zzf zzcrn;
    private volatile Boolean zzczb;
    private String zzczc;
    private Set<Integer> zzczd;

    protected zzr(zzf com_google_android_gms_analytics_internal_zzf) {
        zzab.zzy(com_google_android_gms_analytics_internal_zzf);
        this.zzcrn = com_google_android_gms_analytics_internal_zzf;
    }

    public boolean zzabc() {
        return false;
    }

    public boolean zzabd() {
        if (this.zzczb == null) {
            synchronized (this) {
                if (this.zzczb == null) {
                    ApplicationInfo applicationInfo = this.zzcrn.getContext().getApplicationInfo();
                    String zzawa = zzt.zzawa();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        boolean z = str != null && str.equals(zzawa);
                        this.zzczb = Boolean.valueOf(z);
                    }
                    if ((this.zzczb == null || !this.zzczb.booleanValue()) && "com.google.android.gms.analytics".equals(zzawa)) {
                        this.zzczb = Boolean.TRUE;
                    }
                    if (this.zzczb == null) {
                        this.zzczb = Boolean.TRUE;
                        this.zzcrn.zzyx().zzel("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzczb.booleanValue();
    }

    public boolean zzabe() {
        return ((Boolean) zzy.zzczm.get()).booleanValue();
    }

    public int zzabf() {
        return ((Integer) zzy.f.get()).intValue();
    }

    public int zzabg() {
        return ((Integer) zzy.j.get()).intValue();
    }

    public int zzabh() {
        return ((Integer) zzy.k.get()).intValue();
    }

    public int zzabi() {
        return ((Integer) zzy.l.get()).intValue();
    }

    public long zzabj() {
        return ((Long) zzy.zzczu.get()).longValue();
    }

    public long zzabk() {
        return ((Long) zzy.zzczt.get()).longValue();
    }

    public long zzabl() {
        return ((Long) zzy.zzczx.get()).longValue();
    }

    public long zzabm() {
        return ((Long) zzy.zzczy.get()).longValue();
    }

    public int zzabn() {
        return ((Integer) zzy.zzczz.get()).intValue();
    }

    public int zzabo() {
        return ((Integer) zzy.a.get()).intValue();
    }

    public long zzabp() {
        return (long) ((Integer) zzy.n.get()).intValue();
    }

    public String zzabq() {
        return (String) zzy.c.get();
    }

    public String zzabr() {
        return (String) zzy.b.get();
    }

    public String zzabs() {
        return (String) zzy.d.get();
    }

    public String zzabt() {
        return (String) zzy.e.get();
    }

    public zzm zzabu() {
        return zzm.zzeq((String) zzy.g.get());
    }

    public zzo zzabv() {
        return zzo.zzer((String) zzy.h.get());
    }

    public Set<Integer> zzabw() {
        String str = (String) zzy.m.get();
        if (this.zzczd == null || this.zzczc == null || !this.zzczc.equals(str)) {
            String[] split = TextUtils.split(str, ",");
            Set hashSet = new HashSet();
            for (String parseInt : split) {
                try {
                    hashSet.add(Integer.valueOf(Integer.parseInt(parseInt)));
                } catch (NumberFormatException e) {
                }
            }
            this.zzczc = str;
            this.zzczd = hashSet;
        }
        return this.zzczd;
    }

    public long zzabx() {
        return ((Long) zzy.v.get()).longValue();
    }

    public long zzaby() {
        return ((Long) zzy.w.get()).longValue();
    }

    public long zzabz() {
        return ((Long) zzy.B.get()).longValue();
    }

    public int zzaca() {
        return ((Integer) zzy.zzczq.get()).intValue();
    }

    public int zzacb() {
        return ((Integer) zzy.zzczs.get()).intValue();
    }

    public String zzacc() {
        return "google_analytics_v4.db";
    }

    public String zzacd() {
        return "google_analytics2_v4.db";
    }

    public long zzace() {
        return 86400000;
    }

    public int zzacf() {
        return ((Integer) zzy.p.get()).intValue();
    }

    public int zzacg() {
        return ((Integer) zzy.q.get()).intValue();
    }

    public long zzach() {
        return ((Long) zzy.r.get()).longValue();
    }

    public long zzaci() {
        return ((Long) zzy.C.get()).longValue();
    }
}
