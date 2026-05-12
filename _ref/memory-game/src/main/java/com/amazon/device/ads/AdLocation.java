package com.amazon.device.ads;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.widget.AutoScrollHelper;
import com.amazon.device.ads.Configuration.ConfigOption;

class AdLocation {
    private static final int ARCMINUTE_PRECISION = 6;
    private static final String LOGTAG = AdLocation.class.getSimpleName();
    private static final float MAX_DISTANCE_IN_KILOMETERS = 3.0f;
    private final Configuration configuration;
    private final Context context;
    private final MobileAdsLogger logger;

    private enum LocationAwareness {
        LOCATION_AWARENESS_NORMAL,
        LOCATION_AWARENESS_TRUNCATED,
        LOCATION_AWARENESS_DISABLED
    }

    public AdLocation(Context context) {
        this(context, Configuration.getInstance());
    }

    AdLocation(Context context, Configuration configuration) {
        this.logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
        this.context = context;
        this.configuration = configuration;
    }

    private static double roundToArcminutes(double d) {
        return ((double) Math.round(d * 60.0d)) / 60.0d;
    }

    private LocationAwareness getLocationAwareness() {
        if (this.configuration.getBoolean(ConfigOption.TRUNCATE_LAT_LON)) {
            return LocationAwareness.LOCATION_AWARENESS_TRUNCATED;
        }
        return LocationAwareness.LOCATION_AWARENESS_NORMAL;
    }

    public Location getLocation() {
        float f = AutoScrollHelper.NO_MAX;
        LocationAwareness locationAwareness = getLocationAwareness();
        if (LocationAwareness.LOCATION_AWARENESS_DISABLED.equals(locationAwareness)) {
            return null;
        }
        Location lastKnownLocation;
        Location lastKnownLocation2;
        LocationManager locationManager = (LocationManager) this.context.getSystemService("location");
        try {
            lastKnownLocation = locationManager.getLastKnownLocation("gps");
        } catch (SecurityException e) {
            this.logger.d("Failed to retrieve GPS location: No permissions to access GPS");
            lastKnownLocation = null;
        } catch (IllegalArgumentException e2) {
            this.logger.d("Failed to retrieve GPS location: No GPS found");
            lastKnownLocation = null;
        }
        try {
            lastKnownLocation2 = locationManager.getLastKnownLocation("network");
        } catch (SecurityException e3) {
            this.logger.d("Failed to retrieve network location: No permissions to access network location");
            lastKnownLocation2 = null;
        } catch (IllegalArgumentException e4) {
            this.logger.d("Failed to retrieve network location: No network provider found");
            lastKnownLocation2 = null;
        }
        if (lastKnownLocation == null && lastKnownLocation2 == null) {
            return null;
        }
        if (lastKnownLocation == null || lastKnownLocation2 == null) {
            if (lastKnownLocation != null) {
                this.logger.d("Setting lat/long using GPS, not network");
            } else {
                this.logger.d("Setting lat/long using network location, not GPS");
                lastKnownLocation = lastKnownLocation2;
            }
        } else if (lastKnownLocation.distanceTo(lastKnownLocation2) / 1000.0f <= MAX_DISTANCE_IN_KILOMETERS) {
            float accuracy = lastKnownLocation.hasAccuracy() ? lastKnownLocation.getAccuracy() : AutoScrollHelper.NO_MAX;
            if (lastKnownLocation2.hasAccuracy()) {
                f = lastKnownLocation2.getAccuracy();
            }
            if (accuracy < f) {
                this.logger.d("Setting lat/long using GPS determined by distance");
            } else {
                this.logger.d("Setting lat/long using network determined by distance");
                lastKnownLocation = lastKnownLocation2;
            }
        } else if (lastKnownLocation.getTime() > lastKnownLocation2.getTime()) {
            this.logger.d("Setting lat/long using GPS");
        } else {
            this.logger.d("Setting lat/long using network");
            lastKnownLocation = lastKnownLocation2;
        }
        if (LocationAwareness.LOCATION_AWARENESS_TRUNCATED.equals(locationAwareness)) {
            lastKnownLocation.setLatitude(((double) Math.round(roundToArcminutes(lastKnownLocation.getLatitude()) * Math.pow(10.0d, 6.0d))) / Math.pow(10.0d, 6.0d));
            lastKnownLocation.setLongitude(((double) Math.round(roundToArcminutes(lastKnownLocation.getLongitude()) * Math.pow(10.0d, 6.0d))) / Math.pow(10.0d, 6.0d));
        }
        return lastKnownLocation;
    }
}
