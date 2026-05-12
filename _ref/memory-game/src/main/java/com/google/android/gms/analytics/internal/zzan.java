package com.google.android.gms.analytics.internal;

import android.app.Activity;
import java.util.HashMap;
import java.util.Map;

public class zzan implements zzp {
    public double as = -1.0d;
    public int at = -1;
    public int au = -1;
    public int av = -1;
    public int aw = -1;
    public Map<String, String> ax = new HashMap();
    public String zzcrs;

    public int getSessionTimeout() {
        return this.at;
    }

    public String getTrackingId() {
        return this.zzcrs;
    }

    public boolean zzaeb() {
        return this.zzcrs != null;
    }

    public boolean zzaec() {
        return this.as >= 0.0d;
    }

    public double zzaed() {
        return this.as;
    }

    public boolean zzaee() {
        return this.at >= 0;
    }

    public boolean zzaef() {
        return this.au != -1;
    }

    public boolean zzaeg() {
        return this.au == 1;
    }

    public boolean zzaeh() {
        return this.av != -1;
    }

    public boolean zzaei() {
        return this.av == 1;
    }

    public boolean zzaej() {
        return this.aw == 1;
    }

    public String zzew(String str) {
        String str2 = (String) this.ax.get(str);
        return str2 != null ? str2 : str;
    }

    public String zzr(Activity activity) {
        return zzew(activity.getClass().getCanonicalName());
    }
}
