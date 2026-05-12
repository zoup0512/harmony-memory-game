package com.mopub.mobileads;

import android.location.Location;
import android.support.annotation.Nullable;

public final class MoPubRewardedVideoManager$RequestParameters {
    @Nullable
    public final String mCustomerId;
    @Nullable
    public final String mKeywords;
    @Nullable
    public final Location mLocation;

    public MoPubRewardedVideoManager$RequestParameters(@Nullable String str) {
        this(str, null);
    }

    public MoPubRewardedVideoManager$RequestParameters(@Nullable String str, @Nullable Location location) {
        this(str, location, null);
    }

    public MoPubRewardedVideoManager$RequestParameters(@Nullable String str, @Nullable Location location, @Nullable String str2) {
        this.mKeywords = str;
        this.mLocation = location;
        this.mCustomerId = str2;
    }
}
