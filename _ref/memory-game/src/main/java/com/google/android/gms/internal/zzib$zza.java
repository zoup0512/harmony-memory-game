package com.google.android.gms.internal;

protected final class zzib$zza extends Exception {
    private final int zzbyi;

    public zzib$zza(String str, int i) {
        super(str);
        this.zzbyi = i;
    }

    public int getErrorCode() {
        return this.zzbyi;
    }
}
