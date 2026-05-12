package com.unity3d.ads2.api;

import com.unity3d.ads2.device.Device;
import com.unity3d.ads2.device.DeviceError;
import com.unity3d.ads2.log.DeviceLog;
import com.unity3d.ads2.properties.ClientProperties;
import com.unity3d.ads2.webview.bridge.WebViewCallback;
import com.unity3d.ads2.webview.bridge.WebViewExposed;
import java.io.File;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;

public class DeviceInfo {
    @WebViewExposed
    public static void getAndroidId(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Device.getAndroidId()});
    }

    @WebViewExposed
    public static void getAdvertisingTrackingId(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Device.getAdvertisingTrackingId()});
    }

    @WebViewExposed
    public static void getLimitAdTrackingFlag(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Boolean.valueOf(Device.isLimitAdTrackingEnabled())});
    }

    @WebViewExposed
    public static void getApiLevel(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Integer.valueOf(Device.getApiLevel())});
    }

    @WebViewExposed
    public static void getOsVersion(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Device.getOsVersion()});
    }

    @WebViewExposed
    public static void getManufacturer(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Device.getManufacturer()});
    }

    @WebViewExposed
    public static void getModel(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Device.getModel()});
    }

    @WebViewExposed
    public static void getScreenLayout(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Integer.valueOf(Device.getScreenLayout())});
    }

    @WebViewExposed
    public static void getScreenDensity(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Integer.valueOf(Device.getScreenDensity())});
    }

    @WebViewExposed
    public static void getScreenWidth(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Integer.valueOf(Device.getScreenWidth())});
    }

    @WebViewExposed
    public static void getScreenHeight(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Integer.valueOf(Device.getScreenHeight())});
    }

    @WebViewExposed
    public static void getTimeZone(Boolean bool, WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{TimeZone.getDefault().getDisplayName(bool.booleanValue(), 0, Locale.US)});
    }

    @WebViewExposed
    public static void getConnectionType(WebViewCallback webViewCallback) {
        String str;
        if (Device.isUsingWifi()) {
            str = "wifi";
        } else if (Device.isActiveNetworkConnected()) {
            str = "cellular";
        } else {
            str = "none";
        }
        webViewCallback.invoke(new Object[]{str});
    }

    @WebViewExposed
    public static void getNetworkType(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Integer.valueOf(Device.getNetworkType())});
    }

    @WebViewExposed
    public static void getNetworkOperator(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Device.getNetworkOperator()});
    }

    @WebViewExposed
    public static void getNetworkOperatorName(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Device.getNetworkOperatorName()});
    }

    @WebViewExposed
    public static void isAppInstalled(String str, WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Boolean.valueOf(Device.isAppInstalled(str))});
    }

    @WebViewExposed
    public static void isRooted(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Boolean.valueOf(Device.isRooted())});
    }

    @WebViewExposed
    public static void getInstalledPackages(boolean z, WebViewCallback webViewCallback) {
        JSONArray jSONArray = new JSONArray(Device.getInstalledPackages(z));
        webViewCallback.invoke(new Object[]{jSONArray});
    }

    @WebViewExposed
    public static void getUniqueEventId(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Device.getUniqueEventId()});
    }

    @WebViewExposed
    public static void getHeadset(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Boolean.valueOf(Device.isWiredHeadsetOn())});
    }

    @WebViewExposed
    public static void getSystemProperty(String str, String str2, WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Device.getSystemProperty(str, str2)});
    }

    @WebViewExposed
    public static void getRingerMode(WebViewCallback webViewCallback) {
        int ringerMode = Device.getRingerMode();
        if (ringerMode > -1) {
            webViewCallback.invoke(new Object[]{Integer.valueOf(ringerMode)});
            return;
        }
        switch (ringerMode) {
            case -2:
                webViewCallback.error(DeviceError.AUDIOMANAGER_NULL, new Object[]{Integer.valueOf(ringerMode)});
                return;
            case -1:
                webViewCallback.error(DeviceError.APPLICATION_CONTEXT_NULL, new Object[]{Integer.valueOf(ringerMode)});
                return;
            default:
                DeviceLog.error("Unhandled ringerMode error: " + ringerMode);
                return;
        }
    }

    @WebViewExposed
    public static void getSystemLanguage(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Locale.getDefault().toString()});
    }

    @WebViewExposed
    public static void getDeviceVolume(Integer num, WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Integer.valueOf(Device.getStreamVolume(num.intValue()))});
    }

    @WebViewExposed
    public static void getScreenBrightness(WebViewCallback webViewCallback) {
        int screenBrightness = Device.getScreenBrightness();
        if (screenBrightness > -1) {
            webViewCallback.invoke(new Object[]{Integer.valueOf(screenBrightness)});
            return;
        }
        switch (screenBrightness) {
            case -1:
                webViewCallback.error(DeviceError.APPLICATION_CONTEXT_NULL, new Object[]{Integer.valueOf(screenBrightness)});
                return;
            default:
                DeviceLog.error("Unhandled screenBrightness error: " + screenBrightness);
                return;
        }
    }

    private static StorageType getStorageTypeFromString(String str) {
        try {
            return StorageType.valueOf(str);
        } catch (Exception e) {
            DeviceLog.exception("Illegal argument: " + str, e);
            return null;
        }
    }

    private static File getFileForStorageType(StorageType storageType) {
        switch (1.$SwitchMap$com$unity3d$ads2$api$DeviceInfo$StorageType[storageType.ordinal()]) {
            case 1:
                return ClientProperties.getApplicationContext().getCacheDir();
            case 2:
                return ClientProperties.getApplicationContext().getExternalCacheDir();
            default:
                DeviceLog.error("Unhandled storagetype: " + storageType);
                return null;
        }
    }

    @WebViewExposed
    public static void getFreeSpace(String str, WebViewCallback webViewCallback) {
        StorageType storageTypeFromString = getStorageTypeFromString(str);
        if (storageTypeFromString == null) {
            webViewCallback.error(DeviceError.INVALID_STORAGETYPE, new Object[]{str});
            return;
        }
        if (Device.getFreeSpace(getFileForStorageType(storageTypeFromString)) > -1) {
            webViewCallback.invoke(new Object[]{Long.valueOf(Device.getFreeSpace(getFileForStorageType(storageTypeFromString)))});
            return;
        }
        webViewCallback.error(DeviceError.COULDNT_GET_STORAGE_LOCATION, new Object[]{Long.valueOf(r0)});
    }

    @WebViewExposed
    public static void getTotalSpace(String str, WebViewCallback webViewCallback) {
        StorageType storageTypeFromString = getStorageTypeFromString(str);
        if (storageTypeFromString == null) {
            webViewCallback.error(DeviceError.INVALID_STORAGETYPE, new Object[]{str});
            return;
        }
        if (Device.getTotalSpace(getFileForStorageType(storageTypeFromString)) > -1) {
            webViewCallback.invoke(new Object[]{Long.valueOf(Device.getTotalSpace(getFileForStorageType(storageTypeFromString)))});
            return;
        }
        webViewCallback.error(DeviceError.COULDNT_GET_STORAGE_LOCATION, new Object[]{Long.valueOf(r0)});
    }

    @WebViewExposed
    public static void getBatteryLevel(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Float.valueOf(Device.getBatteryLevel())});
    }

    @WebViewExposed
    public static void getBatteryStatus(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Integer.valueOf(Device.getBatteryStatus())});
    }

    @WebViewExposed
    public static void getFreeMemory(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Long.valueOf(Device.getFreeMemory())});
    }

    @WebViewExposed
    public static void getTotalMemory(WebViewCallback webViewCallback) {
        webViewCallback.invoke(new Object[]{Long.valueOf(Device.getTotalMemory())});
    }
}
