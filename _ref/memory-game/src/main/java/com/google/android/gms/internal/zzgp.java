package com.google.android.gms.internal;

import android.location.Location;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import java.util.Date;
import java.util.Set;

@zzin
public final class zzgp implements MediationAdRequest {
    private final int zzaud;
    private final boolean zzaup;
    private final int zzbph;
    private final Date zzfp;
    private final Set<String> zzfr;
    private final boolean zzfs;
    private final Location zzft;

    public zzgp(@Nullable Date date, int i, @Nullable Set<String> set, @Nullable Location location, boolean z, int i2, boolean z2) {
        this.zzfp = date;
        this.zzaud = i;
        this.zzfr = set;
        this.zzft = location;
        this.zzfs = z;
        this.zzbph = i2;
        this.zzaup = z2;
    }

    public Date getBirthday() {
        return this.zzfp;
    }

    public int getGender() {
        return this.zzaud;
    }

    public Set<String> getKeywords() {
        return this.zzfr;
    }

    public Location getLocation() {
        return this.zzft;
    }

    public boolean isDesignedForFamilies() {
        return this.zzaup;
    }

    public boolean isTesting() {
        return this.zzfs;
    }

    public int taggedForChildDirectedTreatment() {
        return this.zzbph;
    }
}
