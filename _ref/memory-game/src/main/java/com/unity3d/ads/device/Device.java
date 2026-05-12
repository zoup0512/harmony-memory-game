package com.unity3d.ads.device;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.TelephonyManager;
import com.cmcm.adsdk.nativead.RequestResultLogger.Model;
import com.facebook.places.model.PlaceFields;
import com.unity3d.ads.log.DeviceLog;
import com.unity3d.ads.misc.Utilities;
import com.unity3d.ads.properties.ClientProperties;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Device {

    public enum MemoryInfoType {
        TOTAL_MEMORY,
        FREE_MEMORY
    }

    public static int getApiLevel() {
        return VERSION.SDK_INT;
    }

    public static String getOsVersion() {
        return VERSION.RELEASE;
    }

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String getModel() {
        return Build.MODEL;
    }

    public static int getScreenLayout() {
        if (ClientProperties.getApplicationContext() != null) {
            return ClientProperties.getApplicationContext().getResources().getConfiguration().screenLayout;
        }
        return -1;
    }

    @SuppressLint({"DefaultLocale"})
    public static String getAndroidId() {
        String str = null;
        try {
            str = Secure.getString(ClientProperties.getApplicationContext().getContentResolver(), "android_id");
        } catch (Exception e) {
            DeviceLog.exception("Problems fetching androidId", e);
        }
        return str;
    }

    public static String getAdvertisingTrackingId() {
        return AdvertisingId.getAdvertisingTrackingId();
    }

    public static boolean isLimitAdTrackingEnabled() {
        return AdvertisingId.getLimitedAdTracking();
    }

    public static boolean isUsingWifi() {
        if (ClientProperties.getApplicationContext() == null) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) ClientProperties.getApplicationContext().getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        TelephonyManager telephonyManager = (TelephonyManager) ClientProperties.getApplicationContext().getSystemService(PlaceFields.PHONE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !connectivityManager.getBackgroundDataSetting() || !connectivityManager.getActiveNetworkInfo().isConnected() || telephonyManager == null) {
            return false;
        }
        boolean z = activeNetworkInfo.getType() == 1 && activeNetworkInfo.isConnected();
        return z;
    }

    public static int getNetworkType() {
        if (ClientProperties.getApplicationContext() != null) {
            return ((TelephonyManager) ClientProperties.getApplicationContext().getSystemService(PlaceFields.PHONE)).getNetworkType();
        }
        return -1;
    }

    public static String getNetworkOperator() {
        if (ClientProperties.getApplicationContext() != null) {
            return ((TelephonyManager) ClientProperties.getApplicationContext().getSystemService(PlaceFields.PHONE)).getNetworkOperator();
        }
        return "";
    }

    public static String getNetworkOperatorName() {
        if (ClientProperties.getApplicationContext() != null) {
            return ((TelephonyManager) ClientProperties.getApplicationContext().getSystemService(PlaceFields.PHONE)).getNetworkOperatorName();
        }
        return "";
    }

    public static int getScreenDensity() {
        if (ClientProperties.getApplicationContext() != null) {
            return ClientProperties.getApplicationContext().getResources().getDisplayMetrics().densityDpi;
        }
        return -1;
    }

    public static int getScreenWidth() {
        if (ClientProperties.getApplicationContext() != null) {
            return ClientProperties.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        }
        return -1;
    }

    public static int getScreenHeight() {
        if (ClientProperties.getApplicationContext() != null) {
            return ClientProperties.getApplicationContext().getResources().getDisplayMetrics().heightPixels;
        }
        return -1;
    }

    public static boolean isActiveNetworkConnected() {
        if (ClientProperties.getApplicationContext() != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) ClientProperties.getApplicationContext().getSystemService("connectivity");
            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public static boolean isAppInstalled(String str) {
        if (ClientProperties.getApplicationContext() == null) {
            return false;
        }
        try {
            PackageInfo packageInfo = ClientProperties.getApplicationContext().getPackageManager().getPackageInfo(str, 0);
            if (packageInfo == null || packageInfo.packageName == null || !str.equals(packageInfo.packageName)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            DeviceLog.exception("Couldn't find package: " + str, e);
            return false;
        }
    }

    public static List<Map<String, Object>> getInstalledPackages(boolean z) {
        List<Map<String, Object>> arrayList = new ArrayList();
        if (ClientProperties.getApplicationContext() != null) {
            PackageManager packageManager = ClientProperties.getApplicationContext().getPackageManager();
            for (PackageInfo packageInfo : packageManager.getInstalledPackages(0)) {
                HashMap hashMap = new HashMap();
                if (z) {
                    hashMap.put("name", Utilities.Sha256(packageInfo.packageName));
                } else {
                    hashMap.put("name", packageInfo.packageName);
                }
                if (packageInfo.firstInstallTime > 0) {
                    hashMap.put(Model.KEY_loadtime, Long.valueOf(packageInfo.firstInstallTime));
                }
                String installerPackageName = packageManager.getInstallerPackageName(packageInfo.packageName);
                if (!(installerPackageName == null || installerPackageName.isEmpty())) {
                    hashMap.put("installer", installerPackageName);
                }
                arrayList.add(hashMap);
            }
        }
        return arrayList;
    }

    public static String getUniqueEventId() {
        return UUID.randomUUID().toString();
    }

    public static boolean isWiredHeadsetOn() {
        if (ClientProperties.getApplicationContext() != null) {
            return ((AudioManager) ClientProperties.getApplicationContext().getSystemService("audio")).isWiredHeadsetOn();
        }
        return false;
    }

    public static String getSystemProperty(String str, String str2) {
        if (str2 != null) {
            return System.getProperty(str, str2);
        }
        return System.getProperty(str);
    }

    public static int getRingerMode() {
        if (ClientProperties.getApplicationContext() == null) {
            return -1;
        }
        AudioManager audioManager = (AudioManager) ClientProperties.getApplicationContext().getSystemService("audio");
        if (audioManager != null) {
            return audioManager.getRingerMode();
        }
        return -2;
    }

    public static int getStreamVolume(int i) {
        if (ClientProperties.getApplicationContext() == null) {
            return -1;
        }
        AudioManager audioManager = (AudioManager) ClientProperties.getApplicationContext().getSystemService("audio");
        if (audioManager != null) {
            return audioManager.getStreamVolume(i);
        }
        return -2;
    }

    public static int getScreenBrightness() {
        if (ClientProperties.getApplicationContext() != null) {
            return System.getInt(ClientProperties.getApplicationContext().getContentResolver(), "screen_brightness", -1);
        }
        return -1;
    }

    public static long getFreeSpace(File file) {
        if (file == null || !file.exists()) {
            return -1;
        }
        return (long) Math.round((float) (file.getFreeSpace() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID));
    }

    public static long getTotalSpace(File file) {
        if (file == null || !file.exists()) {
            return -1;
        }
        return (long) Math.round((float) (file.getTotalSpace() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID));
    }

    public static float getBatteryLevel() {
        if (ClientProperties.getApplicationContext() != null) {
            Intent registerReceiver = ClientProperties.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (registerReceiver != null) {
                return ((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra("scale", -1));
            }
        }
        return -1.0f;
    }

    public static int getBatteryStatus() {
        if (ClientProperties.getApplicationContext() == null) {
            return -1;
        }
        Intent registerReceiver = ClientProperties.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver != null) {
            return registerReceiver.getIntExtra("status", -1);
        }
        return -1;
    }

    public static long getTotalMemory() {
        return getMemoryInfo(MemoryInfoType.TOTAL_MEMORY);
    }

    public static long getFreeMemory() {
        return getMemoryInfo(MemoryInfoType.FREE_MEMORY);
    }

    private static long getMemoryInfo(MemoryInfoType memoryInfoType) {
        Exception e;
        Throwable th;
        RandomAccessFile randomAccessFile = null;
        int i = -1;
        switch (memoryInfoType) {
            case TOTAL_MEMORY:
                i = 1;
                break;
            case FREE_MEMORY:
                i = 2;
                break;
        }
        try {
            RandomAccessFile randomAccessFile2 = new RandomAccessFile("/proc/meminfo", "r");
            String str = null;
            int i2 = 0;
            while (i2 < i) {
                try {
                    str = randomAccessFile2.readLine();
                    i2++;
                } catch (IOException e2) {
                    e = e2;
                    randomAccessFile = randomAccessFile2;
                } catch (Throwable th2) {
                    th = th2;
                    randomAccessFile = randomAccessFile2;
                }
            }
            long memoryValueFromString = getMemoryValueFromString(str);
            try {
                randomAccessFile2.close();
                return memoryValueFromString;
            } catch (Exception e3) {
                DeviceLog.exception("Error closing RandomAccessFile", e3);
                return memoryValueFromString;
            }
        } catch (IOException e4) {
            e = e4;
            try {
                DeviceLog.exception("Error while reading memory info: " + memoryInfoType, e);
                try {
                    randomAccessFile.close();
                } catch (Exception e5) {
                    DeviceLog.exception("Error closing RandomAccessFile", e5);
                }
                return -1;
            } catch (Throwable th3) {
                th = th3;
                try {
                    randomAccessFile.close();
                } catch (Exception e6) {
                    DeviceLog.exception("Error closing RandomAccessFile", e6);
                }
                throw th;
            }
        }
    }

    private static long getMemoryValueFromString(String str) {
        if (str == null) {
            return -1;
        }
        Matcher matcher = Pattern.compile("(\\d+)").matcher(str);
        String str2 = "";
        while (matcher.find()) {
            str2 = matcher.group(1);
        }
        return Long.parseLong(str2);
    }

    public static boolean isRooted() {
        try {
            return searchPathForBinary("su");
        } catch (Exception e) {
            DeviceLog.exception("Rooted check failed", e);
            return false;
        }
    }

    private static boolean searchPathForBinary(String str) {
        for (String file : System.getenv("PATH").split(":")) {
            File file2 = new File(file);
            if (file2.exists() && file2.isDirectory()) {
                File[] listFiles = file2.listFiles();
                if (listFiles != null) {
                    for (File name : listFiles) {
                        if (name.getName().equals(str)) {
                            return true;
                        }
                    }
                    continue;
                } else {
                    continue;
                }
            }
        }
        return false;
    }
}
