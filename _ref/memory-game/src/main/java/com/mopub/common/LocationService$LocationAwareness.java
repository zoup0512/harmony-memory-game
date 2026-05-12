package com.mopub.common;

import com.mopub.common.MoPub.LocationAwareness;

public enum LocationService$LocationAwareness {
    NORMAL,
    TRUNCATED,
    DISABLED;

    @Deprecated
    public LocationAwareness getNewLocationAwareness() {
        if (this == TRUNCATED) {
            return LocationAwareness.TRUNCATED;
        }
        if (this == DISABLED) {
            return LocationAwareness.DISABLED;
        }
        return LocationAwareness.NORMAL;
    }

    @Deprecated
    public static LocationService$LocationAwareness fromMoPubLocationAwareness(LocationAwareness locationAwareness) {
        if (locationAwareness == LocationAwareness.DISABLED) {
            return DISABLED;
        }
        if (locationAwareness == LocationAwareness.TRUNCATED) {
            return TRUNCATED;
        }
        return NORMAL;
    }
}
