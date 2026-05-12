package com.google.android.gms.measurement.internal;

import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzab;

class zza {
    private String BK;
    private final zzx ahD;
    private String ahJ;
    private String ahK;
    private String ahL;
    private long ahM;
    private long ahN;
    private long ahO;
    private long ahP;
    private String ahQ;
    private long ahR;
    private long ahS;
    private boolean ahT;
    private long ahU;
    private long ahV;
    private long ahW;
    private long ahX;
    private long ahY;
    private boolean ahZ;
    private long aia;
    private long aib;
    private final String zzcjf;
    private String zzcun;

    @WorkerThread
    zza(zzx com_google_android_gms_measurement_internal_zzx, String str) {
        zzab.zzy(com_google_android_gms_measurement_internal_zzx);
        zzab.zzhr(str);
        this.ahD = com_google_android_gms_measurement_internal_zzx;
        this.zzcjf = str;
        this.ahD.zzwu();
    }

    @WorkerThread
    public void setAppVersion(String str) {
        this.ahD.zzwu();
        this.ahZ = (!zzal.zzbb(this.zzcun, str) ? 1 : 0) | this.ahZ;
        this.zzcun = str;
    }

    @WorkerThread
    public void setMeasurementEnabled(boolean z) {
        this.ahD.zzwu();
        this.ahZ = (this.ahT != z ? 1 : 0) | this.ahZ;
        this.ahT = z;
    }

    @WorkerThread
    public void zzau(long j) {
        this.ahD.zzwu();
        this.ahZ = (this.ahN != j ? 1 : 0) | this.ahZ;
        this.ahN = j;
    }

    @WorkerThread
    public void zzav(long j) {
        this.ahD.zzwu();
        this.ahZ = (this.ahO != j ? 1 : 0) | this.ahZ;
        this.ahO = j;
    }

    @WorkerThread
    public void zzaw(long j) {
        this.ahD.zzwu();
        this.ahZ = (this.ahP != j ? 1 : 0) | this.ahZ;
        this.ahP = j;
    }

    @WorkerThread
    public String zzawo() {
        this.ahD.zzwu();
        return this.BK;
    }

    @WorkerThread
    public void zzax(long j) {
        this.ahD.zzwu();
        this.ahZ = (this.ahR != j ? 1 : 0) | this.ahZ;
        this.ahR = j;
    }

    @WorkerThread
    public void zzay(long j) {
        this.ahD.zzwu();
        this.ahZ = (this.ahS != j ? 1 : 0) | this.ahZ;
        this.ahS = j;
    }

    @WorkerThread
    public void zzaz(long j) {
        int i = 1;
        zzab.zzbo(j >= 0);
        this.ahD.zzwu();
        boolean z = this.ahZ;
        if (this.ahM == j) {
            i = 0;
        }
        this.ahZ = z | i;
        this.ahM = j;
    }

    @WorkerThread
    public void zzba(long j) {
        this.ahD.zzwu();
        this.ahZ = (this.aia != j ? 1 : 0) | this.ahZ;
        this.aia = j;
    }

    @WorkerThread
    public void zzbb(long j) {
        this.ahD.zzwu();
        this.ahZ = (this.aib != j ? 1 : 0) | this.ahZ;
        this.aib = j;
    }

    @WorkerThread
    public void zzbc(long j) {
        this.ahD.zzwu();
        this.ahZ = (this.ahU != j ? 1 : 0) | this.ahZ;
        this.ahU = j;
    }

    @WorkerThread
    public void zzbd(long j) {
        this.ahD.zzwu();
        this.ahZ = (this.ahV != j ? 1 : 0) | this.ahZ;
        this.ahV = j;
    }

    @WorkerThread
    public void zzbe(long j) {
        this.ahD.zzwu();
        this.ahZ = (this.ahW != j ? 1 : 0) | this.ahZ;
        this.ahW = j;
    }

    @WorkerThread
    public void zzbf(long j) {
        this.ahD.zzwu();
        this.ahZ = (this.ahX != j ? 1 : 0) | this.ahZ;
        this.ahX = j;
    }

    @WorkerThread
    public void zzbg(long j) {
        this.ahD.zzwu();
        this.ahZ = (this.ahY != j ? 1 : 0) | this.ahZ;
        this.ahY = j;
    }

    @WorkerThread
    public void zzbpr() {
        this.ahD.zzwu();
        this.ahZ = false;
    }

    @WorkerThread
    public String zzbps() {
        this.ahD.zzwu();
        return this.ahJ;
    }

    @WorkerThread
    public String zzbpt() {
        this.ahD.zzwu();
        return this.ahK;
    }

    @WorkerThread
    public String zzbpu() {
        this.ahD.zzwu();
        return this.ahL;
    }

    @WorkerThread
    public long zzbpv() {
        this.ahD.zzwu();
        return this.ahN;
    }

    @WorkerThread
    public long zzbpw() {
        this.ahD.zzwu();
        return this.ahO;
    }

    @WorkerThread
    public long zzbpx() {
        this.ahD.zzwu();
        return this.ahP;
    }

    @WorkerThread
    public String zzbpy() {
        this.ahD.zzwu();
        return this.ahQ;
    }

    @WorkerThread
    public long zzbpz() {
        this.ahD.zzwu();
        return this.ahR;
    }

    @WorkerThread
    public long zzbqa() {
        this.ahD.zzwu();
        return this.ahS;
    }

    @WorkerThread
    public boolean zzbqb() {
        this.ahD.zzwu();
        return this.ahT;
    }

    @WorkerThread
    public long zzbqc() {
        this.ahD.zzwu();
        return this.ahM;
    }

    @WorkerThread
    public long zzbqd() {
        this.ahD.zzwu();
        return this.aia;
    }

    @WorkerThread
    public long zzbqe() {
        this.ahD.zzwu();
        return this.aib;
    }

    @WorkerThread
    public void zzbqf() {
        this.ahD.zzwu();
        long j = this.ahM + 1;
        if (j > 2147483647L) {
            this.ahD.zzbsd().zzbsx().log("Bundle index overflow");
            j = 0;
        }
        this.ahZ = true;
        this.ahM = j;
    }

    @WorkerThread
    public long zzbqg() {
        this.ahD.zzwu();
        return this.ahU;
    }

    @WorkerThread
    public long zzbqh() {
        this.ahD.zzwu();
        return this.ahV;
    }

    @WorkerThread
    public long zzbqi() {
        this.ahD.zzwu();
        return this.ahW;
    }

    @WorkerThread
    public long zzbqj() {
        this.ahD.zzwu();
        return this.ahX;
    }

    @WorkerThread
    public long zzbqk() {
        this.ahD.zzwu();
        return this.ahY;
    }

    @WorkerThread
    public void zzky(String str) {
        this.ahD.zzwu();
        this.ahZ = (!zzal.zzbb(this.BK, str) ? 1 : 0) | this.ahZ;
        this.BK = str;
    }

    @WorkerThread
    public void zzkz(String str) {
        this.ahD.zzwu();
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.ahZ = (!zzal.zzbb(this.ahJ, str) ? 1 : 0) | this.ahZ;
        this.ahJ = str;
    }

    @WorkerThread
    public void zzla(String str) {
        this.ahD.zzwu();
        this.ahZ = (!zzal.zzbb(this.ahK, str) ? 1 : 0) | this.ahZ;
        this.ahK = str;
    }

    @WorkerThread
    public void zzlb(String str) {
        this.ahD.zzwu();
        this.ahZ = (!zzal.zzbb(this.ahL, str) ? 1 : 0) | this.ahZ;
        this.ahL = str;
    }

    @WorkerThread
    public void zzlc(String str) {
        this.ahD.zzwu();
        this.ahZ = (!zzal.zzbb(this.ahQ, str) ? 1 : 0) | this.ahZ;
        this.ahQ = str;
    }

    @WorkerThread
    public String zzsh() {
        this.ahD.zzwu();
        return this.zzcjf;
    }

    @WorkerThread
    public String zzxc() {
        this.ahD.zzwu();
        return this.zzcun;
    }
}
