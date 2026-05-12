package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.zzab;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class zzh {
    private final Map<String, String> zzbeg;
    private final String zzcvi;
    private final long zzcxi;
    private final String zzcxj;
    private final boolean zzcxk;
    private long zzcxl;

    public zzh(long j, String str, String str2, boolean z, long j2, Map<String, String> map) {
        zzab.zzhr(str);
        zzab.zzhr(str2);
        this.zzcxi = j;
        this.zzcvi = str;
        this.zzcxj = str2;
        this.zzcxk = z;
        this.zzcxl = j2;
        if (map != null) {
            this.zzbeg = new HashMap(map);
        } else {
            this.zzbeg = Collections.emptyMap();
        }
    }

    public Map<String, String> zzm() {
        return this.zzbeg;
    }

    public void zzp(long j) {
        this.zzcxl = j;
    }

    public String zzwb() {
        return this.zzcvi;
    }

    public long zzzo() {
        return this.zzcxi;
    }

    public String zzzp() {
        return this.zzcxj;
    }

    public boolean zzzq() {
        return this.zzcxk;
    }

    public long zzzr() {
        return this.zzcxl;
    }
}
