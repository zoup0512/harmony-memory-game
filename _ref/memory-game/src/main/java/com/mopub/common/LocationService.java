package com.mopub.common;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.MoPub.LocationAwareness;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import java.math.BigDecimal;

public class LocationService {

    public enum ValidLocationProvider {
        NETWORK("network"),
        GPS("gps");
        
        @NonNull
        final String name;

        private ValidLocationProvider(String str) {
            this.name = str;
        }

        public String toString() {
            return this.name;
        }

        private boolean hasRequiredPermissions(@NonNull Context context) {
            switch (this) {
                case NETWORK:
                    if (DeviceUtils.isPermissionGranted(context, "android.permission.ACCESS_FINE_LOCATION") || DeviceUtils.isPermissionGranted(context, "android.permission.ACCESS_COARSE_LOCATION")) {
                        return true;
                    }
                    return false;
                case GPS:
                    return DeviceUtils.isPermissionGranted(context, "android.permission.ACCESS_FINE_LOCATION");
                default:
                    return false;
            }
        }
    }

    @Nullable
    public static Location getLastKnownLocation(@NonNull Context context, int i, @NonNull LocationAwareness locationAwareness) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(locationAwareness);
        if (locationAwareness == LocationAwareness.DISABLED) {
            return null;
        }
        Location mostRecentValidLocation = getMostRecentValidLocation(getLocationFromProvider(context, ValidLocationProvider.GPS), getLocationFromProvider(context, ValidLocationProvider.NETWORK));
        if (locationAwareness != LocationAwareness.TRUNCATED) {
            return mostRecentValidLocation;
        }
        truncateLocationLatLon(mostRecentValidLocation, i);
        return mostRecentValidLocation;
    }

    @Nullable
    @VisibleForTesting
    static Location getLocationFromProvider(@NonNull Context context, @NonNull ValidLocationProvider validLocationProvider) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(validLocationProvider);
        if (!validLocationProvider.hasRequiredPermissions(context)) {
            return null;
        }
        try {
            return ((LocationManager) context.getSystemService("location")).getLastKnownLocation(validLocationProvider.toString());
        } catch (SecurityException e) {
            MoPubLog.d("Failed to retrieve location from " + validLocationProvider.toString() + " provider: access appears to be disabled.");
            return null;
        } catch (IllegalArgumentException e2) {
            MoPubLog.d("Failed to retrieve location: device has no " + validLocationProvider.toString() + " location provider.");
            return null;
        } catch (NullPointerException e3) {
            MoPubLog.d("Failed to retrieve location: device has no " + validLocationProvider.toString() + " location provider.");
            return null;
        }
    }

    @Nullable
    @VisibleForTesting
    static Location getMostRecentValidLocation(@Nullable Location location, @Nullable Location location2) {
        if (location == null) {
            return location2;
        }
        return (location2 == null || location.getTime() > location2.getTime()) ? location : location2;
    }

    @Nullable
    @VisibleForTesting
    static void truncateLocationLatLon(@Nullable Location location, int i) {
        if (location != null && i >= 0) {
            location.setLatitude(BigDecimal.valueOf(location.getLatitude()).setScale(i, 5).doubleValue());
            location.setLongitude(BigDecimal.valueOf(location.getLongitude()).setScale(i, 5).doubleValue());
        }
    }
}
