package com.google.android.gms.measurement.internal;

abstract class zzaa extends zzz {
    private boolean zzcwq;

    zzaa(zzx com_google_android_gms_measurement_internal_zzx) {
        super(com_google_android_gms_measurement_internal_zzx);
        this.ahD.zzb(this);
    }

    public final void initialize() {
        if (this.zzcwq) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzwv();
        this.ahD.zzbug();
        this.zzcwq = true;
    }

    boolean isInitialized() {
        return this.zzcwq;
    }

    boolean zzbul() {
        return false;
    }

    protected abstract void zzwv();

    protected void zzzg() {
        if (!isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }
}
