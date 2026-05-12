package com.google.android.gms.analytics.internal;

public abstract class zzd extends zzc {
    private boolean zzcwq;

    protected zzd(zzf com_google_android_gms_analytics_internal_zzf) {
        super(com_google_android_gms_analytics_internal_zzf);
    }

    public void initialize() {
        zzwv();
        this.zzcwq = true;
    }

    public boolean isInitialized() {
        return this.zzcwq;
    }

    protected abstract void zzwv();

    protected void zzzg() {
        if (!isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }
}
