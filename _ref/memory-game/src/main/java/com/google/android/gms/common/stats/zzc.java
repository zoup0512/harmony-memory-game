package com.google.android.gms.common.stats;

import com.google.android.gms.internal.zzqz;

public final class zzc {
    public static zzqz<Integer> Ar = zzqz.zza("gms:common:stats:max_num_of_events", Integer.valueOf(100));
    public static zzqz<Integer> As = zzqz.zza("gms:common:stats:max_chunk_size", Integer.valueOf(100));

    public static final class zza {
        public static zzqz<Integer> At = zzqz.zza("gms:common:stats:connections:level", Integer.valueOf(zzd.LOG_LEVEL_OFF));
        public static zzqz<String> Au = zzqz.zzab("gms:common:stats:connections:ignored_calling_processes", "");
        public static zzqz<String> Av = zzqz.zzab("gms:common:stats:connections:ignored_calling_services", "");
        public static zzqz<String> Aw = zzqz.zzab("gms:common:stats:connections:ignored_target_processes", "");
        public static zzqz<String> Ax = zzqz.zzab("gms:common:stats:connections:ignored_target_services", "com.google.android.gms.auth.GetToken");
        public static zzqz<Long> Ay = zzqz.zza("gms:common:stats:connections:time_out_duration", Long.valueOf(600000));
    }

    public static final class zzb {
        public static zzqz<Integer> At = zzqz.zza("gms:common:stats:wakeLocks:level", Integer.valueOf(zzd.LOG_LEVEL_OFF));
        public static zzqz<Long> Ay = zzqz.zza("gms:common:stats:wakelocks:time_out_duration", Long.valueOf(600000));
    }
}
