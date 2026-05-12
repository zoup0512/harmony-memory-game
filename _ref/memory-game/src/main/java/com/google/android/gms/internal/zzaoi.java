package com.google.android.gms.internal;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public final class zzaoi extends zzanh<Time> {
    public static final zzani bfu = new zzani() {
        public <T> zzanh<T> zza(zzamp com_google_android_gms_internal_zzamp, zzaol<T> com_google_android_gms_internal_zzaol_T) {
            return com_google_android_gms_internal_zzaol_T.m() == Time.class ? new zzaoi() : null;
        }
    };
    private final DateFormat bfU = new SimpleDateFormat("hh:mm:ss a");

    public synchronized void zza(zzaoo com_google_android_gms_internal_zzaoo, Time time) throws IOException {
        com_google_android_gms_internal_zzaoo.zzts(time == null ? null : this.bfU.format(time));
    }

    public /* synthetic */ Object zzb(zzaom com_google_android_gms_internal_zzaom) throws IOException {
        return zzn(com_google_android_gms_internal_zzaom);
    }

    public synchronized Time zzn(zzaom com_google_android_gms_internal_zzaom) throws IOException {
        Time time;
        if (com_google_android_gms_internal_zzaom.b() == zzaon.NULL) {
            com_google_android_gms_internal_zzaom.nextNull();
            time = null;
        } else {
            try {
                time = new Time(this.bfU.parse(com_google_android_gms_internal_zzaom.nextString()).getTime());
            } catch (Throwable e) {
                throw new zzane(e);
            }
        }
        return time;
    }
}
