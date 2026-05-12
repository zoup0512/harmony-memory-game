package com.google.android.gms.ads;

import com.google.android.gms.internal.zzin;

@zzin
public final class VideoOptions {
    private final boolean zzaio;

    private VideoOptions(Builder builder) {
        this.zzaio = Builder.zza(builder);
    }

    public boolean getStartMuted() {
        return this.zzaio;
    }
}
