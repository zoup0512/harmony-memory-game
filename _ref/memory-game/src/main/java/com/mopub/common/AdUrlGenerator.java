package com.mopub.common;

import android.content.Context;
import android.location.Location;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.mopub.common.ClientMetadata.MoPubNetworkType;
import com.mopub.common.util.DateAndTime;

public abstract class AdUrlGenerator extends BaseUrlGenerator {
    private static final String AD_UNIT_ID_KEY = "id";
    private static final String BUNDLE_ID_KEY = "bundle";
    private static final String CARRIER_NAME_KEY = "cn";
    private static final String CARRIER_TYPE_KEY = "ct";
    private static final String COUNTRY_CODE_KEY = "iso";
    private static final String IS_MRAID_KEY = "mr";
    private static final String KEYWORDS_KEY = "q";
    private static final String LAT_LONG_ACCURACY_KEY = "lla";
    private static final String LAT_LONG_FRESHNESS_KEY = "llf";
    private static final String LAT_LONG_FROM_SDK_KEY = "llsdk";
    private static final String LAT_LONG_KEY = "ll";
    private static final String MOBILE_COUNTRY_CODE_KEY = "mcc";
    private static final String MOBILE_NETWORK_CODE_KEY = "mnc";
    private static final String ORIENTATION_KEY = "o";
    private static final String SCREEN_SCALE_KEY = "sc_a";
    private static final String SDK_VERSION_KEY = "nv";
    private static final String TIMEZONE_OFFSET_KEY = "z";
    protected String mAdUnitId;
    protected Context mContext;
    protected String mKeywords;
    protected Location mLocation;

    public AdUrlGenerator(Context context) {
        this.mContext = context;
    }

    public AdUrlGenerator withAdUnitId(String str) {
        this.mAdUnitId = str;
        return this;
    }

    public AdUrlGenerator withKeywords(String str) {
        this.mKeywords = str;
        return this;
    }

    public AdUrlGenerator withLocation(Location location) {
        this.mLocation = location;
        return this;
    }

    protected void setAdUnitId(String str) {
        addParam("id", str);
    }

    protected void setSdkVersion(String str) {
        addParam(SDK_VERSION_KEY, str);
    }

    protected void setKeywords(String str) {
        addParam(KEYWORDS_KEY, str);
    }

    protected void setLocation(@Nullable Location location) {
        Location lastKnownLocation = LocationService.getLastKnownLocation(this.mContext, MoPub.getLocationPrecision(), MoPub.getLocationAwareness());
        if (lastKnownLocation != null && (location == null || lastKnownLocation.getTime() >= location.getTime())) {
            location = lastKnownLocation;
        }
        if (location != null) {
            addParam(LAT_LONG_KEY, location.getLatitude() + "," + location.getLongitude());
            addParam(LAT_LONG_ACCURACY_KEY, String.valueOf((int) location.getAccuracy()));
            addParam(LAT_LONG_FRESHNESS_KEY, String.valueOf(calculateLocationStalenessInMilliseconds(location)));
            if (location == lastKnownLocation) {
                addParam(LAT_LONG_FROM_SDK_KEY, AppEventsConstants.EVENT_PARAM_VALUE_YES);
            }
        }
    }

    protected void setTimezone(String str) {
        addParam(TIMEZONE_OFFSET_KEY, str);
    }

    protected void setOrientation(String str) {
        addParam(ORIENTATION_KEY, str);
    }

    protected void setDensity(float f) {
        addParam(SCREEN_SCALE_KEY, "" + f);
    }

    protected void setMraidFlag(boolean z) {
        if (z) {
            addParam(IS_MRAID_KEY, AppEventsConstants.EVENT_PARAM_VALUE_YES);
        }
    }

    protected void setMccCode(String str) {
        addParam(MOBILE_COUNTRY_CODE_KEY, str == null ? "" : str.substring(0, mncPortionLength(str)));
    }

    protected void setMncCode(String str) {
        addParam(MOBILE_NETWORK_CODE_KEY, str == null ? "" : str.substring(mncPortionLength(str)));
    }

    protected void setIsoCountryCode(String str) {
        addParam(COUNTRY_CODE_KEY, str);
    }

    protected void setCarrierName(String str) {
        addParam(CARRIER_NAME_KEY, str);
    }

    protected void setNetworkType(MoPubNetworkType moPubNetworkType) {
        addParam(CARRIER_TYPE_KEY, moPubNetworkType);
    }

    protected void setBundleId(String str) {
        if (!TextUtils.isEmpty(str)) {
            addParam(BUNDLE_ID_KEY, str);
        }
    }

    protected void addBaseParams(ClientMetadata clientMetadata) {
        setAdUnitId(this.mAdUnitId);
        setSdkVersion(clientMetadata.getSdkVersion());
        setDeviceInfo(new String[]{clientMetadata.getDeviceManufacturer(), clientMetadata.getDeviceModel(), clientMetadata.getDeviceProduct()});
        setBundleId(clientMetadata.getAppPackageName());
        setKeywords(this.mKeywords);
        setLocation(this.mLocation);
        setTimezone(DateAndTime.getTimeZoneOffsetString());
        setOrientation(clientMetadata.getOrientationString());
        setDeviceDimensions(clientMetadata.getDeviceDimensions());
        setDensity(clientMetadata.getDensity());
        String networkOperatorForUrl = clientMetadata.getNetworkOperatorForUrl();
        setMccCode(networkOperatorForUrl);
        setMncCode(networkOperatorForUrl);
        setIsoCountryCode(clientMetadata.getIsoCountryCode());
        setCarrierName(clientMetadata.getNetworkOperatorName());
        setNetworkType(clientMetadata.getActiveNetworkType());
        setAppVersion(clientMetadata.getAppVersion());
        appendAdvertisingInfoTemplates();
    }

    private void addParam(String str, MoPubNetworkType moPubNetworkType) {
        addParam(str, moPubNetworkType.toString());
    }

    private int mncPortionLength(String str) {
        return Math.min(3, str.length());
    }

    private static int calculateLocationStalenessInMilliseconds(Location location) {
        Preconditions.checkNotNull(location);
        return (int) (System.currentTimeMillis() - location.getTime());
    }

    @Deprecated
    public AdUrlGenerator withFacebookSupported(boolean z) {
        return this;
    }
}
