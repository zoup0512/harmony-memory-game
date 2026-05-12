package com.google.android.gms.measurement.internal;

import com.cmcm.picks.loader.Ad;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzqz;
import com.mopub.common.Constants;

public final class zzl {
    public static zza<Boolean> aiL = zza.zzo("measurement.service_enabled", true);
    public static zza<Boolean> aiM = zza.zzo("measurement.service_client_enabled", true);
    public static zza<String> aiN = zza.zzl("measurement.log_tag", "FA", "FA-SVC");
    public static zza<Long> aiO = zza.zzg("measurement.ad_id_cache_time", 10000);
    public static zza<Long> aiP = zza.zzg("measurement.monitoring.sample_period_millis", 86400000);
    public static zza<Long> aiQ = zza.zzb("measurement.config.cache_time", 86400000, 3600000);
    public static zza<String> aiR = zza.zzav("measurement.config.url_scheme", Constants.HTTPS);
    public static zza<String> aiS = zza.zzav("measurement.config.url_authority", "app-measurement.com");
    public static zza<Integer> aiT = zza.zzaa("measurement.upload.max_bundles", 100);
    public static zza<Integer> aiU = zza.zzaa("measurement.upload.max_batch_size", 65536);
    public static zza<Integer> aiV = zza.zzaa("measurement.upload.max_bundle_size", 65536);
    public static zza<Integer> aiW = zza.zzaa("measurement.upload.max_events_per_bundle", 1000);
    public static zza<Integer> aiX = zza.zzaa("measurement.upload.max_events_per_day", 100000);
    public static zza<Integer> aiY = zza.zzaa("measurement.upload.max_public_events_per_day", Ad.SHOW_TYPE_HAVE_PIC_BIG_CARD);
    public static zza<Integer> aiZ = zza.zzaa("measurement.upload.max_conversions_per_day", 500);
    public static zza<Integer> aja = zza.zzaa("measurement.store.max_stored_events_per_app", 100000);
    public static zza<String> ajb = zza.zzav("measurement.upload.url", "https://app-measurement.com/a");
    public static zza<Long> ajc = zza.zzg("measurement.upload.backoff_period", 43200000);
    public static zza<Long> ajd = zza.zzg("measurement.upload.window_interval", 3600000);
    public static zza<Long> aje = zza.zzg("measurement.upload.interval", 3600000);
    public static zza<Long> ajf = zza.zzg("measurement.upload.stale_data_deletion_interval", 86400000);
    public static zza<Long> ajg = zza.zzg("measurement.upload.initial_upload_delay_time", 15000);
    public static zza<Long> ajh = zza.zzg("measurement.upload.retry_time", 1800000);
    public static zza<Integer> aji = zza.zzaa("measurement.upload.retry_count", 6);
    public static zza<Long> ajj = zza.zzg("measurement.upload.max_queue_time", 2419200000L);
    public static zza<Integer> ajk = zza.zzaa("measurement.lifetimevalue.max_currency_tracked", 4);
    public static zza<Long> ajl = zza.zzg("measurement.service_client.idle_disconnect_millis", 5000);

    public static final class zza<V> {
        private final V E;
        private final zzqz<V> F;
        private final String zzaxp;

        private zza(String str, zzqz<V> com_google_android_gms_internal_zzqz_V, V v) {
            zzab.zzy(com_google_android_gms_internal_zzqz_V);
            this.F = com_google_android_gms_internal_zzqz_V;
            this.E = v;
            this.zzaxp = str;
        }

        static zza<Integer> zzaa(String str, int i) {
            return zzo(str, i, i);
        }

        static zza<String> zzav(String str, String str2) {
            return zzl(str, str2, str2);
        }

        static zza<Long> zzb(String str, long j, long j2) {
            return new zza(str, zzqz.zza(str, Long.valueOf(j2)), Long.valueOf(j));
        }

        static zza<Boolean> zzb(String str, boolean z, boolean z2) {
            return new zza(str, zzqz.zzm(str, z2), Boolean.valueOf(z));
        }

        static zza<Long> zzg(String str, long j) {
            return zzb(str, j, j);
        }

        static zza<String> zzl(String str, String str2, String str3) {
            return new zza(str, zzqz.zzab(str, str3), str2);
        }

        static zza<Integer> zzo(String str, int i, int i2) {
            return new zza(str, zzqz.zza(str, Integer.valueOf(i2)), Integer.valueOf(i));
        }

        static zza<Boolean> zzo(String str, boolean z) {
            return zzb(str, z, z);
        }

        public V get() {
            return this.E;
        }

        public V get(V v) {
            return v != null ? v : this.E;
        }

        public String getKey() {
            return this.zzaxp;
        }
    }
}
