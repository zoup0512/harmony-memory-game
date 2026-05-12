package com.google.android.gms.internal;

import android.location.Location;
import android.support.annotation.Nullable;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAdOptions.Builder;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import java.util.Date;
import java.util.List;
import java.util.Set;

@zzin
public final class zzgu implements NativeMediationAdRequest {
    private final NativeAdOptionsParcel zzalk;
    private final List<String> zzall;
    private final int zzaud;
    private final boolean zzaup;
    private final int zzbph;
    private final Date zzfp;
    private final Set<String> zzfr;
    private final boolean zzfs;
    private final Location zzft;

    public zzgu(@Nullable Date date, int i, @Nullable Set<String> set, @Nullable Location location, boolean z, int i2, NativeAdOptionsParcel nativeAdOptionsParcel, List<String> list, boolean z2) {
        this.zzfp = date;
        this.zzaud = i;
        this.zzfr = set;
        this.zzft = location;
        this.zzfs = z;
        this.zzbph = i2;
        this.zzalk = nativeAdOptionsParcel;
        this.zzall = list;
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

    public NativeAdOptions getNativeAdOptions() {
        return this.zzalk == null ? null : new Builder().setReturnUrlsForImageAssets(this.zzalk.zzbgp).setImageOrientation(this.zzalk.zzbgq).setRequestMultipleImages(this.zzalk.zzbgr).build();
    }

    public boolean isAppInstallAdRequested() {
        return this.zzall != null && this.zzall.contains("2");
    }

    public boolean isContentAdRequested() {
        return this.zzall != null && this.zzall.contains(AppEventsConstants.EVENT_PARAM_VALUE_YES);
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
