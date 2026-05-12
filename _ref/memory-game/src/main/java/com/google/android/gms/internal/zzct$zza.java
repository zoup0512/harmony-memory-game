package com.google.android.gms.internal;

import android.support.annotation.Nullable;

public class zzct$zza {
    final long value;
    final String zzati;
    final int zzatj;

    zzct$zza(long j, String str, int i) {
        this.value = j;
        this.zzati = str;
        this.zzatj = i;
    }

    public boolean equals(@Nullable Object obj) {
        return (obj == null || !(obj instanceof zzct$zza)) ? false : ((zzct$zza) obj).value == this.value && ((zzct$zza) obj).zzatj == this.zzatj;
    }

    public int hashCode() {
        return (int) this.value;
    }
}
