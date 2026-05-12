package com.amazon.device.ads;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.facebook.places.model.PlaceFields;
import com.mopub.volley.DefaultRetryPolicy;
import java.util.Locale;
import java.util.regex.Pattern;
import org.json.JSONObject;

class DeviceInfo {
    private static final String LOGTAG = DeviceInfo.class.getSimpleName();
    public static final String ORIENTATION_LANDSCAPE = "landscape";
    public static final String ORIENTATION_PORTRAIT = "portrait";
    public static final String ORIENTATION_UNKNOWN = "unknown";
    private static final String dt = "android";
    private static final String os = "Android";
    private boolean bad_mac;
    private boolean bad_serial;
    private boolean bad_udid;
    private final AndroidBuildInfo buildInfo;
    private String carrier;
    private String country;
    private final MobileAdsInfoStore infoStore;
    private Size landscapeScreenSize;
    private String language;
    private final MobileAdsLogger logger;
    private boolean macFetched;
    private String make;
    private String model;
    private String osVersion;
    private Size portraitScreenSize;
    private float scalingFactor;
    private String scalingFactorAsString;
    private boolean serialFetched;
    private String sha1_mac;
    private String sha1_serial;
    private String sha1_udid;
    private boolean udidFetched;
    private UserAgentManager userAgentManager;

    public DeviceInfo(Context context, UserAgentManager userAgentManager) {
        this(context, userAgentManager, MobileAdsInfoStore.getInstance(), new MobileAdsLoggerFactory(), new AndroidBuildInfo());
    }

    DeviceInfo(Context context, UserAgentManager userAgentManager, MobileAdsInfoStore mobileAdsInfoStore, MobileAdsLoggerFactory mobileAdsLoggerFactory, AndroidBuildInfo androidBuildInfo) {
        this.make = Build.MANUFACTURER;
        this.model = Build.MODEL;
        this.osVersion = VERSION.RELEASE;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
        this.infoStore = mobileAdsInfoStore;
        this.buildInfo = androidBuildInfo;
        setCountry();
        setCarrier(context);
        setLanguage();
        setScalingFactor(context);
        this.userAgentManager = userAgentManager;
    }

    public void setUserAgentManager(UserAgentManager userAgentManager) {
        this.userAgentManager = userAgentManager;
    }

    private void setMacAddressIfNotFetched() {
        if (!this.macFetched) {
            setMacAddress();
        }
    }

    protected void setMacAddress() {
        WifiInfo connectionInfo;
        Object macAddress;
        WifiManager wifiManager = (WifiManager) this.infoStore.getApplicationContext().getSystemService("wifi");
        if (wifiManager != null) {
            try {
                connectionInfo = wifiManager.getConnectionInfo();
            } catch (SecurityException e) {
                this.logger.d("Unable to get Wifi connection information: %s", e);
                connectionInfo = null;
            } catch (ExceptionInInitializerError e2) {
                this.logger.d("Unable to get Wifi connection information: %s", e2);
            }
            if (connectionInfo != null) {
                this.sha1_mac = null;
            } else {
                macAddress = connectionInfo.getMacAddress();
                if (macAddress != null || macAddress.length() == 0) {
                    this.sha1_mac = null;
                    this.bad_mac = true;
                } else if (Pattern.compile("((([0-9a-fA-F]){1,2}[-:]){5}([0-9a-fA-F]){1,2})").matcher(macAddress).find()) {
                    this.sha1_mac = WebUtils.getURLEncodedString(StringUtils.sha1(macAddress));
                } else {
                    this.sha1_mac = null;
                    this.bad_mac = true;
                }
            }
            this.macFetched = true;
        }
        connectionInfo = null;
        if (connectionInfo != null) {
            macAddress = connectionInfo.getMacAddress();
            if (macAddress != null) {
            }
            this.sha1_mac = null;
            this.bad_mac = true;
        } else {
            this.sha1_mac = null;
        }
        this.macFetched = true;
    }

    private void setSerialIfNotFetched() {
        if (!this.serialFetched) {
            setSerial();
        }
    }

    private void setSerial() {
        String str;
        try {
            str = (String) Build.class.getDeclaredField("SERIAL").get(Build.class);
        } catch (Exception e) {
            str = null;
        }
        if (str == null || str.length() == 0 || str.equalsIgnoreCase("unknown")) {
            this.bad_serial = true;
        } else {
            this.sha1_serial = WebUtils.getURLEncodedString(StringUtils.sha1(str));
        }
        this.serialFetched = true;
    }

    private void setUdidIfNotFetched() {
        if (!this.udidFetched) {
            setUdid();
        }
    }

    private void setUdid() {
        String string = Secure.getString(this.infoStore.getApplicationContext().getContentResolver(), "android_id");
        if (StringUtils.isNullOrEmpty(string) || string.equalsIgnoreCase("9774d56d682e549c")) {
            this.sha1_udid = null;
            this.bad_udid = true;
        } else {
            this.sha1_udid = WebUtils.getURLEncodedString(StringUtils.sha1(string));
        }
        this.udidFetched = true;
    }

    private void setLanguage() {
        String language = Locale.getDefault().getLanguage();
        if (language == null || language.length() <= 0) {
            language = null;
        }
        this.language = language;
    }

    private void setCountry() {
        String country = Locale.getDefault().getCountry();
        if (country == null || country.length() <= 0) {
            country = null;
        }
        this.country = country;
    }

    private void setCarrier(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(PlaceFields.PHONE);
        if (telephonyManager != null) {
            String networkOperatorName = telephonyManager.getNetworkOperatorName();
            if (networkOperatorName == null || networkOperatorName.length() <= 0) {
                networkOperatorName = null;
            }
            this.carrier = networkOperatorName;
        }
    }

    private void setScalingFactor(Context context) {
        if (this.make.equals("motorola") && this.model.equals("MB502")) {
            this.scalingFactor = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        } else {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            this.scalingFactor = displayMetrics.scaledDensity;
        }
        this.scalingFactorAsString = Float.toString(this.scalingFactor);
    }

    public String getDeviceType() {
        return "android";
    }

    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public String getOS() {
        return os;
    }

    public String getOSVersion() {
        return this.osVersion;
    }

    public String getMacSha1() {
        setMacAddressIfNotFetched();
        return this.sha1_mac;
    }

    public boolean isMacBad() {
        setMacAddressIfNotFetched();
        return this.bad_mac;
    }

    public String getSerialSha1() {
        setSerialIfNotFetched();
        return this.sha1_serial;
    }

    public boolean isSerialBad() {
        setSerialIfNotFetched();
        return this.bad_serial;
    }

    public String getUdidSha1() {
        setUdidIfNotFetched();
        return this.sha1_udid;
    }

    public boolean isUdidBad() {
        setUdidIfNotFetched();
        return this.bad_udid;
    }

    public String getCarrier() {
        return this.carrier;
    }

    public String getCountry() {
        return this.country;
    }

    public String getLanguage() {
        return this.language;
    }

    public String getScalingFactorAsString() {
        return this.scalingFactorAsString;
    }

    public float getScalingFactorAsFloat() {
        return this.scalingFactor;
    }

    public String getUserAgentString() {
        return this.userAgentManager.getUserAgentString();
    }

    public void setUserAgentString(String str) {
        this.userAgentManager.setUserAgentString(str);
    }

    public void populateUserAgentString(Context context) {
        this.userAgentManager.populateUserAgentString(context);
    }

    public String getOrientation() {
        switch (DisplayUtils.determineCanonicalScreenOrientation(this.infoStore.getApplicationContext(), this.buildInfo)) {
            case 0:
            case 8:
                return ORIENTATION_LANDSCAPE;
            case 1:
            case 9:
                return ORIENTATION_PORTRAIT;
            default:
                return "unknown";
        }
    }

    public Size getScreenSize(String str) {
        if (str.equals(ORIENTATION_PORTRAIT) && this.portraitScreenSize != null) {
            return this.portraitScreenSize;
        }
        if (str.equals(ORIENTATION_LANDSCAPE) && this.landscapeScreenSize != null) {
            return this.landscapeScreenSize;
        }
        WindowManager windowManager = (WindowManager) this.infoStore.getApplicationContext().getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        String str2 = String.valueOf(displayMetrics.widthPixels) + "x" + String.valueOf(displayMetrics.heightPixels);
        if (str.equals(ORIENTATION_PORTRAIT)) {
            this.portraitScreenSize = new Size(str2);
            return this.portraitScreenSize;
        } else if (!str.equals(ORIENTATION_LANDSCAPE)) {
            return new Size(str2);
        } else {
            this.landscapeScreenSize = new Size(str2);
            return this.landscapeScreenSize;
        }
    }

    public JSONObject getDInfoProperty() {
        JSONObject jSONObject = new JSONObject();
        JSONUtils.put(jSONObject, "make", getMake());
        JSONUtils.put(jSONObject, "model", getModel());
        JSONUtils.put(jSONObject, "os", getOS());
        JSONUtils.put(jSONObject, "osVersion", getOSVersion());
        JSONUtils.put(jSONObject, "scalingFactor", getScalingFactorAsString());
        JSONUtils.put(jSONObject, "language", getLanguage());
        JSONUtils.put(jSONObject, "country", getCountry());
        JSONUtils.put(jSONObject, "carrier", getCarrier());
        return jSONObject;
    }

    public String toJsonString() {
        return toJsonObject(getOrientation()).toString();
    }

    JSONObject toJsonObject(String str) {
        JSONObject dInfoProperty = getDInfoProperty();
        JSONUtils.put(dInfoProperty, "orientation", str);
        JSONUtils.put(dInfoProperty, "screenSize", getScreenSize(str).toString());
        JSONUtils.put(dInfoProperty, "connectionType", new ConnectionInfo(this.infoStore).getConnectionType());
        return dInfoProperty;
    }
}
