package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.internal.zzab;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Locale;

class zzt extends zzaa {
    static final Pair<String, Long> ajW = new Pair("", Long.valueOf(0));
    private SharedPreferences ah;
    public final zzc ajX = new zzc("health_monitor", zzbsf().zzaci());
    public final zzb ajY = new zzb(this, "last_upload", 0);
    public final zzb ajZ = new zzb(this, "last_upload_attempt", 0);
    public final zzb aka = new zzb(this, "backoff", 0);
    public final zzb akb = new zzb(this, "last_delete_stale", 0);
    public final zzb akc = new zzb(this, "midnight_offset", 0);
    private String akd;
    private boolean ake;
    private long akf;
    private SecureRandom akg;
    public final zzb akh = new zzb(this, "time_before_start", 10000);
    public final zzb aki = new zzb(this, "session_timeout", 1800000);
    public final zza akj = new zza(this, "start_new_session", true);
    public final zzb akk = new zzb(this, "last_pause_time", 0);
    public final zzb akl = new zzb(this, "time_active", 0);
    public boolean akm;

    public final class zza {
        private final boolean akn;
        private boolean ako;
        final /* synthetic */ zzt akp;
        private boolean rN;
        private final String zzaxp;

        public zza(zzt com_google_android_gms_measurement_internal_zzt, String str, boolean z) {
            this.akp = com_google_android_gms_measurement_internal_zzt;
            zzab.zzhr(str);
            this.zzaxp = str;
            this.akn = z;
        }

        @WorkerThread
        private void zzbtm() {
            if (!this.ako) {
                this.ako = true;
                this.rN = this.akp.ah.getBoolean(this.zzaxp, this.akn);
            }
        }

        @WorkerThread
        public boolean get() {
            zzbtm();
            return this.rN;
        }

        @WorkerThread
        public void set(boolean z) {
            Editor edit = this.akp.ah.edit();
            edit.putBoolean(this.zzaxp, z);
            edit.apply();
            this.rN = z;
        }
    }

    public final class zzb {
        private boolean ako;
        final /* synthetic */ zzt akp;
        private final long akq;
        private final String zzaxp;
        private long zzcve;

        public zzb(zzt com_google_android_gms_measurement_internal_zzt, String str, long j) {
            this.akp = com_google_android_gms_measurement_internal_zzt;
            zzab.zzhr(str);
            this.zzaxp = str;
            this.akq = j;
        }

        @WorkerThread
        private void zzbtm() {
            if (!this.ako) {
                this.ako = true;
                this.zzcve = this.akp.ah.getLong(this.zzaxp, this.akq);
            }
        }

        @WorkerThread
        public long get() {
            zzbtm();
            return this.zzcve;
        }

        @WorkerThread
        public void set(long j) {
            Editor edit = this.akp.ah.edit();
            edit.putLong(this.zzaxp, j);
            edit.apply();
            this.zzcve = j;
        }
    }

    public final class zzc {
        final /* synthetic */ zzt akp;
        final String akr;
        private final String aks;
        private final String akt;
        private final long al;

        private zzc(zzt com_google_android_gms_measurement_internal_zzt, String str, long j) {
            this.akp = com_google_android_gms_measurement_internal_zzt;
            zzab.zzhr(str);
            zzab.zzbo(j > 0);
            this.akr = String.valueOf(str).concat(":start");
            this.aks = String.valueOf(str).concat(":count");
            this.akt = String.valueOf(str).concat(":value");
            this.al = j;
        }

        @WorkerThread
        private void zzadt() {
            this.akp.zzwu();
            long currentTimeMillis = this.akp.zzyw().currentTimeMillis();
            Editor edit = this.akp.ah.edit();
            edit.remove(this.aks);
            edit.remove(this.akt);
            edit.putLong(this.akr, currentTimeMillis);
            edit.apply();
        }

        @WorkerThread
        private long zzadu() {
            this.akp.zzwu();
            long zzadw = zzadw();
            if (zzadw != 0) {
                return Math.abs(zzadw - this.akp.zzyw().currentTimeMillis());
            }
            zzadt();
            return 0;
        }

        @WorkerThread
        private long zzadw() {
            return this.akp.zzbth().getLong(this.akr, 0);
        }

        @WorkerThread
        public Pair<String, Long> zzadv() {
            this.akp.zzwu();
            long zzadu = zzadu();
            if (zzadu < this.al) {
                return null;
            }
            if (zzadu > this.al * 2) {
                zzadt();
                return null;
            }
            String string = this.akp.zzbth().getString(this.akt, null);
            zzadu = this.akp.zzbth().getLong(this.aks, 0);
            zzadt();
            return (string == null || zzadu <= 0) ? zzt.ajW : new Pair(string, Long.valueOf(zzadu));
        }

        @WorkerThread
        public void zzev(String str) {
            zzh(str, 1);
        }

        @WorkerThread
        public void zzh(String str, long j) {
            this.akp.zzwu();
            if (zzadw() == 0) {
                zzadt();
            }
            if (str == null) {
                str = "";
            }
            long j2 = this.akp.ah.getLong(this.aks, 0);
            if (j2 <= 0) {
                Editor edit = this.akp.ah.edit();
                edit.putString(this.akt, str);
                edit.putLong(this.aks, j);
                edit.apply();
                return;
            }
            Object obj = (this.akp.zzbte().nextLong() & Long.MAX_VALUE) < (Long.MAX_VALUE / (j2 + j)) * j ? 1 : null;
            Editor edit2 = this.akp.ah.edit();
            if (obj != null) {
                edit2.putString(this.akt, str);
            }
            edit2.putLong(this.aks, j2 + j);
            edit2.apply();
        }
    }

    zzt(zzx com_google_android_gms_measurement_internal_zzx) {
        super(com_google_android_gms_measurement_internal_zzx);
    }

    @WorkerThread
    private SecureRandom zzbte() {
        zzwu();
        if (this.akg == null) {
            this.akg = new SecureRandom();
        }
        return this.akg;
    }

    @WorkerThread
    private SharedPreferences zzbth() {
        zzwu();
        zzzg();
        return this.ah;
    }

    @WorkerThread
    void setMeasurementEnabled(boolean z) {
        zzwu();
        zzbsd().zzbtc().zzj("Setting measurementEnabled", Boolean.valueOf(z));
        Editor edit = zzbth().edit();
        edit.putBoolean("measurement_enabled", z);
        edit.apply();
    }

    @WorkerThread
    String zzbpu() {
        zzwu();
        return com.google.firebase.iid.zzc.zzcwr().getId();
    }

    @WorkerThread
    String zzbtf() {
        zzbte().nextBytes(new byte[16]);
        return String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, r0)});
    }

    @WorkerThread
    long zzbtg() {
        zzzg();
        zzwu();
        long j = this.akc.get();
        if (j != 0) {
            return j;
        }
        j = (long) (zzbte().nextInt(86400000) + 1);
        this.akc.set(j);
        return j;
    }

    @WorkerThread
    String zzbti() {
        zzwu();
        return zzbth().getString("gmp_app_id", null);
    }

    @WorkerThread
    Boolean zzbtj() {
        zzwu();
        return !zzbth().contains("use_service") ? null : Boolean.valueOf(zzbth().getBoolean("use_service", false));
    }

    @WorkerThread
    void zzbtk() {
        boolean z = true;
        zzwu();
        zzbsd().zzbtc().log("Clearing collection preferences.");
        boolean contains = zzbth().contains("measurement_enabled");
        if (contains) {
            z = zzcc(true);
        }
        Editor edit = zzbth().edit();
        edit.clear();
        edit.apply();
        if (contains) {
            setMeasurementEnabled(z);
        }
    }

    @WorkerThread
    protected String zzbtl() {
        zzwu();
        String string = zzbth().getString("previous_os_version", null);
        String zzbso = zzbrw().zzbso();
        if (!(TextUtils.isEmpty(zzbso) || zzbso.equals(string))) {
            Editor edit = zzbth().edit();
            edit.putString("previous_os_version", zzbso);
            edit.apply();
        }
        return string;
    }

    @WorkerThread
    void zzcb(boolean z) {
        zzwu();
        zzbsd().zzbtc().zzj("Setting useService", Boolean.valueOf(z));
        Editor edit = zzbth().edit();
        edit.putBoolean("use_service", z);
        edit.apply();
    }

    @WorkerThread
    boolean zzcc(boolean z) {
        zzwu();
        return zzbth().getBoolean("measurement_enabled", z);
    }

    @WorkerThread
    @NonNull
    Pair<String, Boolean> zzlx(String str) {
        zzwu();
        long elapsedRealtime = zzyw().elapsedRealtime();
        if (this.akd != null && elapsedRealtime < this.akf) {
            return new Pair(this.akd, Boolean.valueOf(this.ake));
        }
        this.akf = elapsedRealtime + zzbsf().zzlg(str);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(getContext());
            this.akd = advertisingIdInfo.getId();
            if (this.akd == null) {
                this.akd = "";
            }
            this.ake = advertisingIdInfo.isLimitAdTrackingEnabled();
        } catch (Throwable th) {
            zzbsd().zzbtb().zzj("Unable to get advertising id", th);
            this.akd = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair(this.akd, Boolean.valueOf(this.ake));
    }

    String zzly(String str) {
        String str2 = (String) zzlx(str).first;
        if (zzal.zzfa(CommonUtils.MD5_INSTANCE) == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, zzal.zzfa(CommonUtils.MD5_INSTANCE).digest(str2.getBytes()))});
    }

    @WorkerThread
    void zzlz(String str) {
        zzwu();
        Editor edit = zzbth().edit();
        edit.putString("gmp_app_id", str);
        edit.apply();
    }

    protected void zzwv() {
        this.ah = getContext().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.akm = this.ah.getBoolean("has_been_opened", false);
        if (!this.akm) {
            Editor edit = this.ah.edit();
            edit.putBoolean("has_been_opened", true);
            edit.apply();
        }
    }
}
