package com.cmcm.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.cmcm.adsdk.CMAdManager;
import com.cmcm.baseapi.ads.INativeAd;
import com.cmcm.picks.loader.Ad;
import com.facebook.places.model.PlaceFields;
import java.io.File;
import java.security.Key;
import java.util.List;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public final class Commons {
    public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
    private static String MCC = null;
    private static String MNC = null;
    private static final String PKG_NAME_SYSTEMPROPERTIES = "android.os.SystemProperties";
    private static String sAndroidID = "";
    private static Object sAndroidIDLock = new Object();

    public static String getIMEI(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return ((TelephonyManager) context.getSystemService(PlaceFields.PHONE)).getDeviceId();
        } catch (Exception e) {
            return "";
        }
    }

    public static boolean isHasPackage(Context c, String packageName) {
        if (c == null || packageName == null) {
            return false;
        }
        boolean z;
        j a = j.a();
        if (VERSION.SDK_INT > 20) {
            z = true;
        } else {
            z = false;
        }
        List a2 = a.a(z);
        if (a2 != null && a2.size() > 0) {
            return a2.contains(packageName);
        }
        try {
            c.getPackageManager().getPackageInfo(packageName, 256);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isUserApp(ApplicationInfo info) {
        return (info.flags & 1) == 0 && (info.flags & 128) == 0;
    }

    public static final boolean isWebViewProbablyCorrupt(Context context) {
        SQLiteDatabase openOrCreateDatabase;
        Exception e;
        Throwable th;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            if (!new File("/data/data/" + context.getPackageName() + "/databases/" + "webviewCache.db").exists()) {
                openOrCreateDatabase = context.openOrCreateDatabase("webviewCache.db", 0, null);
                if (openOrCreateDatabase != null) {
                    try {
                        openOrCreateDatabase.close();
                        if (sQLiteDatabase == null) {
                            return false;
                        }
                        sQLiteDatabase.close();
                        return false;
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            if (g.a) {
                                e.printStackTrace();
                            }
                            if (openOrCreateDatabase != null) {
                                openOrCreateDatabase.close();
                            }
                            return true;
                        } catch (Throwable th2) {
                            th = th2;
                            sQLiteDatabase = openOrCreateDatabase;
                            if (sQLiteDatabase != null) {
                                sQLiteDatabase.close();
                            }
                            throw th;
                        }
                    }
                }
                if (openOrCreateDatabase != null) {
                    openOrCreateDatabase.close();
                }
                return true;
            } else if (sQLiteDatabase == null) {
                return false;
            } else {
                sQLiteDatabase.close();
                return false;
            }
        } catch (Exception e3) {
            e = e3;
            openOrCreateDatabase = sQLiteDatabase;
            if (g.a) {
                e.printStackTrace();
            }
            if (openOrCreateDatabase != null) {
                openOrCreateDatabase.close();
            }
            return true;
        } catch (Throwable th3) {
            th = th3;
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
            throw th;
        }
    }

    public static boolean startActivity(Context context, Intent intent) {
        try {
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
            }
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void openGooglePlayByUrl(String url, Context context) {
        if (!TextUtils.isEmpty(url)) {
            startActivity(context, new Intent("android.intent.action.VIEW", Uri.parse(url)));
        }
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static void openApp(Context context, String packageName) {
        Intent intent = null;
        try {
            intent = CMAdManager.getContext().getPackageManager().getLaunchIntentForPackage(packageName);
        } catch (Exception e) {
            if (g.a) {
                e.printStackTrace();
            }
        }
        if (intent != null) {
            startActivity(context, intent);
        }
    }

    public static String getResolution(Context context) {
        try {
            return String.format(Locale.US, "%d*%d", new Object[]{Integer.valueOf(getScreenHeight(context)), Integer.valueOf(getScreenWidth(context))});
        } catch (Exception e) {
            return "";
        }
    }

    public static float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static void initMNC_MNC(Context context) {
        if (context != null) {
            String simOperator = ((TelephonyManager) context.getSystemService(PlaceFields.PHONE)).getSimOperator();
            if (!TextUtils.isEmpty(simOperator)) {
                if (simOperator.length() >= 3) {
                    MCC = simOperator.substring(0, 3);
                }
                if (simOperator.length() >= 5) {
                    MNC = simOperator.substring(3, 5);
                }
            }
        }
    }

    public static String getMCC(Context context) {
        if (TextUtils.isEmpty(MCC)) {
            initMNC_MNC(context);
        }
        return MCC;
    }

    public static String getMNC(Context context) {
        if (TextUtils.isEmpty(MNC)) {
            initMNC_MNC(context);
        }
        return MNC;
    }

    public static String getLanguage(Context context) {
        Locale locale = getLocale(context);
        return locale != null ? locale.getLanguage() : null;
    }

    public static String getCountry(Context context) {
        Locale locale = getLocale(context);
        return locale != null ? locale.getCountry() : null;
    }

    public static Locale getLocale(Context context) {
        Locale locale = null;
        if (context == null) {
            return Locale.getDefault();
        }
        Resources resources = context.getResources();
        if (resources == null) {
            return Locale.getDefault();
        }
        Configuration configuration = resources.getConfiguration();
        if (configuration != null) {
            locale = configuration.locale;
        }
        if (locale == null) {
            return Locale.getDefault();
        }
        return locale;
    }

    public static String SP2(String key, String fail) {
        Object systemProperties = getSystemProperties(key);
        if (TextUtils.isEmpty(systemProperties)) {
            systemProperties = Build.MODEL;
        }
        return !TextUtils.isEmpty(systemProperties) ? systemProperties : fail;
    }

    public static boolean isScreenOn(Context context) {
        if (context == null) {
            return false;
        }
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        return powerManager != null ? powerManager.isScreenOn() : false;
    }

    public static String getAndroidId() {
        Context context = CMAdManager.getContext();
        if (TextUtils.isEmpty(sAndroidID)) {
            synchronized (sAndroidIDLock) {
                if (TextUtils.isEmpty(sAndroidID)) {
                    String str = "";
                    try {
                        Object string = System.getString(context.getContentResolver(), "android_id");
                        if (!TextUtils.isEmpty(string)) {
                            sAndroidID = string;
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        return sAndroidID;
    }

    public static int range(int n, int min, int max) {
        if (n <= min) {
            return min;
        }
        if (n >= max) {
            return max;
        }
        return n;
    }

    public static int getResourceId(Context context, String name, String type, String packageName) {
        int i = 0;
        if (context != null) {
            try {
                i = context.getPackageManager().getResourcesForApplication(packageName).getIdentifier(name, type, packageName);
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return i;
    }

    public static boolean openAppByDeeplink(Context context, String pkgName, String link) {
        if (!TextUtils.isEmpty(link)) {
            return startActivity(context, new Intent("android.intent.action.VIEW", Uri.parse(link)));
        }
        openApp(context, pkgName);
        return true;
    }

    public static String getSystemProperties(String key) {
        try {
            return (String) Class.forName(PKG_NAME_SYSTEMPROPERTIES).getMethod("get", new Class[]{String.class}).invoke(null, new Object[]{key});
        } catch (Exception e) {
            return "";
        }
    }

    public static Drawable createDrawable(Context context, String fileName) {
        Drawable bitmapDrawable = new BitmapDrawable(context.getResources(), BitmapFactory.decodeResource(context.getResources(), getResourceId(context, fileName, "drawable", context.getPackageName())));
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        bitmapDrawable.setTargetDensity((int) TypedValue.applyDimension(1, displayMetrics.xdpi, displayMetrics));
        return bitmapDrawable;
    }

    public static boolean isMiui() {
        String systemVariable = getSystemVariable("ro.miui.ui.version.name", "UNKNOWN");
        if (systemVariable.equals("V5") || systemVariable.equalsIgnoreCase("V6") || systemVariable.equalsIgnoreCase("V7")) {
            return true;
        }
        return false;
    }

    public static String getSystemVariable(String key, String defValue) {
        try {
            return (String) Class.forName(PKG_NAME_SYSTEMPROPERTIES).getMethod("get", new Class[]{String.class, String.class}).invoke(null, new Object[]{key, defValue});
        } catch (Exception e) {
            return defValue;
        }
    }

    public static int getAppVersionCode(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            if (!g.a) {
                return i;
            }
            e.printStackTrace();
            return i;
        }
    }

    public static byte[] encrypt(byte[] key, byte[] data) throws Exception {
        Key keyGenerator = keyGenerator(new String(key));
        Cipher instance = Cipher.getInstance(CIPHER_ALGORITHM);
        instance.init(1, keyGenerator);
        return instance.doFinal(data);
    }

    private static Key keyGenerator(String keyStr) throws Exception {
        return SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(hexString2Bytes(keyStr)));
    }

    public static byte[] hexString2Bytes(String hexstr) {
        int i = 0;
        byte[] bArr = new byte[(hexstr.length() / 2)];
        int i2 = 0;
        while (i < bArr.length) {
            int i3 = i2 + 1;
            char charAt = hexstr.charAt(i2);
            i2 = i3 + 1;
            int parse = parse(charAt) << 4;
            bArr[i] = (byte) (parse(hexstr.charAt(i3)) | parse);
            i++;
        }
        return bArr;
    }

    private static int parse(char c) {
        if (c >= 'a') {
            return ((c - 97) + 10) & 15;
        }
        if (c >= 'A') {
            return ((c - 65) + 10) & 15;
        }
        return (c - 48) & 15;
    }

    public static String toHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte b : src) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(toHexString);
        }
        return stringBuilder.toString();
    }

    public static int getAdAppShowType(INativeAd ad) {
        if (ad != null) {
            Object adObject = ad.getAdObject();
            if (adObject instanceof Ad) {
                return ((Ad) adObject).getAppShowType();
            }
        }
        return -1;
    }

    public static int dip2px(Context context, float dpValue) {
        return (int) ((context.getResources().getDisplayMetrics().density * dpValue) + 0.5f);
    }
}
