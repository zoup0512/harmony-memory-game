package com.appodeal.ads;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;
import com.appodeal.ads.ao.b;
import com.appodeal.ads.utils.Log.LogLevel;
import com.appodeal.ads.utils.i;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.places.model.PlaceFields;
import com.google.android.gms.common.GoogleApiAvailability;
import com.mopub.common.GpsHelper;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public class an {
    private static long a = 0;
    private static SimpleDateFormat b = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public static class a {
        public final String a;
        public final String b;
        public final boolean c;

        a(String str, String str2, boolean z) {
            this.a = str;
            this.b = str2;
            this.c = z;
        }
    }

    static void a(Activity activity, String str) {
        Editor edit = activity.getSharedPreferences("appodeal", 0).edit();
        edit.putString("appKey", str);
        edit.remove(GpsHelper.ADVERTISING_ID_KEY);
        edit.remove("advertisingTracking");
        edit.apply();
    }

    static void a(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("appodeal", 0);
        String string = sharedPreferences.getString("appodealVersion", null);
        if (string == null || !string.equals(Appodeal.getVersion())) {
            Editor edit = sharedPreferences.edit();
            edit.putString("appodealVersion", Appodeal.getVersion());
            edit.apply();
            a(activity.getDir(new File(i.a).toString(), 0));
            a(activity.getDir(i.b, 0));
        }
    }

    public static void a(File file) {
        if (file.isDirectory()) {
            for (File a : file.listFiles()) {
                a(a);
            }
        }
        file.delete();
    }

    public static boolean a(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        boolean z = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        if (!z) {
            Appodeal.a("No Internet");
        }
        return z;
    }

    public static a b(Context context) {
        String str;
        boolean z;
        String toLowerCase;
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        String str2 = "unknown";
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            String typeName = activeNetworkInfo.getTypeName();
            str2 = activeNetworkInfo.getSubtypeName();
            switch (activeNetworkInfo.getType()) {
                case 0:
                    switch (activeNetworkInfo.getSubtype()) {
                        case 1:
                        case 2:
                        case 4:
                        case 7:
                        case 11:
                            str = str2;
                            str2 = typeName;
                            z = false;
                            break;
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                            str = str2;
                            str2 = typeName;
                            z = true;
                            break;
                        default:
                            str = str2;
                            str2 = typeName;
                            z = false;
                            break;
                    }
                case 1:
                    str = str2;
                    str2 = typeName;
                    z = true;
                    break;
                case 6:
                    str = str2;
                    str2 = typeName;
                    z = true;
                    break;
                case 7:
                    str = str2;
                    str2 = typeName;
                    z = false;
                    break;
                case 9:
                    str = str2;
                    str2 = typeName;
                    z = true;
                    break;
                default:
                    str = str2;
                    str2 = typeName;
                    z = false;
                    break;
            }
        }
        z = false;
        str = null;
        if (str2 != null) {
            if (str2.equals("CELLULAR")) {
                str2 = "MOBILE";
            }
            toLowerCase = str2.toLowerCase(Locale.ENGLISH);
        } else {
            toLowerCase = str2;
        }
        if (str != null) {
            str2 = str.toLowerCase(Locale.ENGLISH);
            if (str2.isEmpty()) {
                str2 = null;
            }
        } else {
            str2 = str;
        }
        return new a(toLowerCase, str2, z);
    }

    static String c(Context context) {
        String networkOperator = ((TelephonyManager) context.getSystemService(PlaceFields.PHONE)).getNetworkOperator();
        if (networkOperator == null || networkOperator.length() < 3) {
            return null;
        }
        return networkOperator.substring(0, 3) + '-' + networkOperator.substring(3);
    }

    static Pair<Integer, Pair<String, String>> d(Context context) {
        int i;
        Object valueOf;
        Object obj = null;
        Location e = e(context);
        if (e != null) {
            i = 1;
            valueOf = String.valueOf(e.getLatitude());
            obj = String.valueOf(e.getLongitude());
        } else {
            i = 0;
            valueOf = null;
        }
        return new Pair(Integer.valueOf(i), new Pair(valueOf, obj));
    }

    public static Location e(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        String bestProvider = locationManager.getBestProvider(new Criteria(), false);
        if (bestProvider != null) {
            try {
                return locationManager.getLastKnownLocation(bestProvider);
            } catch (SecurityException e) {
                Appodeal.a("Failed to retrieve GPS location: access appears to be disabled.");
                return null;
            } catch (IllegalArgumentException e2) {
                Appodeal.a("Failed to retrieve GPS location: device has no GPS provider.");
            }
        }
        return null;
    }

    @SuppressLint({"NewApi"})
    public static Pair<Integer, Integer> f(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (VERSION.SDK_INT < 13) {
            return new Pair(Integer.valueOf(defaultDisplay.getWidth()), Integer.valueOf(defaultDisplay.getHeight()));
        }
        Point point = new Point();
        defaultDisplay.getSize(point);
        return new Pair(Integer.valueOf(point.x), Integer.valueOf(point.y));
    }

    @SuppressLint({"NewApi"})
    public static float g(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (VERSION.SDK_INT < 13) {
            return ((float) defaultDisplay.getWidth()) / displayMetrics.density;
        }
        Point point = new Point();
        defaultDisplay.getSize(point);
        return ((float) point.x) / displayMetrics.density;
    }

    @SuppressLint({"NewApi"})
    public static float h(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (VERSION.SDK_INT < 13) {
            return ((float) defaultDisplay.getHeight()) / displayMetrics.density;
        }
        Point point = new Point();
        defaultDisplay.getSize(point);
        return ((float) point.y) / displayMetrics.density;
    }

    public static float i(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return displayMetrics.density;
    }

    public static int j(Context context) {
        int rotation = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation();
        int i = context.getResources().getConfiguration().orientation;
        if (i == 1) {
            switch (rotation) {
                case 2:
                case 3:
                    return 9;
                default:
                    return 1;
            }
        } else if (i == 2) {
            switch (rotation) {
                case 2:
                case 3:
                    return 8;
                default:
                    return 0;
            }
        } else {
            Appodeal.a("Unknown screen orientation. Defaulting to portrait.");
            return 9;
        }
    }

    public static float k(Context context) {
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver == null) {
            return -1.0f;
        }
        int intExtra = registerReceiver.getIntExtra("level", -1);
        int intExtra2 = registerReceiver.getIntExtra("scale", -1);
        if (intExtra == -1 || intExtra2 == -1) {
            return -1.0f;
        }
        return (((float) intExtra) / ((float) intExtra2)) * 100.0f;
    }

    public static String a(Context context, JSONArray jSONArray) {
        boolean z = false;
        if (jSONArray == null) {
            try {
                return "";
            } catch (Throwable e) {
                Appodeal.a(e);
                return "";
            }
        }
        String str = "";
        for (int i = 0; i < jSONArray.length() && !r1; i++) {
            str = jSONArray.getString(i);
            z = a(context, str);
        }
        return str;
    }

    public static boolean a(Context context, String str) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.setFlags(268435456);
            ComponentName a = a(context, intent);
            if (a != null) {
                intent.setComponent(a);
                context.startActivity(intent);
                return true;
            }
            Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(URLDecoder.decode(str, "UTF-8")));
            intent2.setFlags(268435456);
            ComponentName a2 = a(context, intent2);
            if (a2 != null) {
                intent2.setComponent(a2);
                context.startActivity(intent2);
                return true;
            }
            Appodeal.a(String.format("No activities to handle intent: %s", new Object[]{r2}));
            return false;
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public static ComponentName a(Context context, Intent intent) {
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities.isEmpty()) {
            return null;
        }
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            if (resolveInfo.activityInfo.packageName.equals("com.android.vending")) {
                return new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
            }
        }
        return new ComponentName(((ResolveInfo) queryIntentActivities.get(0)).activityInfo.packageName, ((ResolveInfo) queryIntentActivities.get(0)).activityInfo.name);
    }

    public static String a(String str) {
        if (str == null) {
            return "";
        }
        Matcher matcher = Pattern.compile("_(.)").matcher(str);
        StringBuffer stringBuffer = new StringBuffer(str.length());
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, matcher.group(1).toUpperCase(Locale.ENGLISH));
        }
        matcher.appendTail(stringBuffer);
        stringBuffer.setCharAt(0, Character.toUpperCase(str.charAt(0)));
        return stringBuffer.toString();
    }

    public static String l(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("appodeal", 0);
        if (sharedPreferences.contains("uuid")) {
            return sharedPreferences.getString("uuid", null);
        }
        String uuid = UUID.randomUUID().toString();
        Editor edit = sharedPreferences.edit();
        edit.putString("uuid", uuid);
        edit.apply();
        return uuid;
    }

    public static void a(Activity activity, o oVar, int i) {
        Intent intent = new Intent(activity, InterstitialActivity.class);
        intent.addFlags(268435456);
        intent.addFlags(8388608);
        intent.putExtra("interstitialClass", oVar.a());
        intent.putExtra("requestId", i);
        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Appodeal.a("InterstitialActivity not found - did you declare it in AndroidManifest.xml?");
            q.a(true);
        }
    }

    public static void a(Activity activity, ap apVar, int i) {
        Intent intent = new Intent(activity, VideoActivity.class);
        intent.addFlags(268435456);
        intent.addFlags(8388608);
        intent.putExtra("type", b.NON_REWARDED);
        intent.putExtra("videoClass", apVar.a());
        intent.putExtra("requestId", i);
        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Appodeal.a("VideoActivity not found - did you declare it in AndroidManifest.xml?");
            aj.a(true);
        }
    }

    public static void b(Activity activity, ap apVar, int i) {
        Intent intent = new Intent(activity, VideoActivity.class);
        intent.addFlags(268435456);
        intent.addFlags(8388608);
        intent.putExtra("type", b.REWARDED);
        intent.putExtra("videoClass", apVar.a());
        intent.putExtra("requestId", i);
        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Appodeal.a("VideoActivity not found - did you declare it in AndroidManifest.xml?");
            am.a(true);
        }
    }

    static void m(Context context) {
        Intent intent = new Intent(context, LoaderActivity.class);
        intent.addFlags(268435456);
        intent.addFlags(8388608);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Appodeal.a("LoaderActivity not found - did you declare it in AndroidManifest.xml?");
        }
    }

    public static boolean a(String... strArr) {
        try {
            for (String cls : strArr) {
                Class.forName(cls, false, Appodeal.class.getClassLoader());
            }
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static String a(InputStream inputStream) {
        BufferedReader bufferedReader;
        Throwable e;
        Throwable th;
        String str = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuilder.append(readLine);
                } catch (Exception e2) {
                    e = e2;
                }
            }
            str = stringBuilder.toString();
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Throwable e3) {
                    Appodeal.a(e3);
                }
            }
        } catch (Exception e4) {
            e3 = e4;
            Object obj = str;
            try {
                Appodeal.a(e3);
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable e32) {
                        Appodeal.a(e32);
                    }
                }
                return str;
            } catch (Throwable th2) {
                th = th2;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable e322) {
                        Appodeal.a(e322);
                    }
                }
                throw th;
            }
        } catch (Throwable e3222) {
            bufferedReader = str;
            th = e3222;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
        return str;
    }

    public static Pair<String, Integer> b(InputStream inputStream) {
        BufferedReader bufferedReader;
        Throwable e;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            int i = 0;
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuilder.append(readLine);
                    i += readLine.getBytes().length;
                } catch (Exception e2) {
                    e = e2;
                }
            }
            long currentTimeMillis2 = System.currentTimeMillis();
            if (currentTimeMillis2 == currentTimeMillis) {
                currentTimeMillis2 = 1 + currentTimeMillis;
            }
            Pair<String, Integer> pair = new Pair(stringBuilder.toString(), Integer.valueOf(Math.round((float) (((long) i) / (currentTimeMillis2 - currentTimeMillis)))));
            if (bufferedReader == null) {
                return pair;
            }
            try {
                bufferedReader.close();
                return pair;
            } catch (Throwable e3) {
                Appodeal.a(e3);
                return pair;
            }
        } catch (Exception e4) {
            e = e4;
            bufferedReader = null;
            try {
                Appodeal.a(e);
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable e5) {
                        Appodeal.a(e5);
                    }
                }
                return null;
            } catch (Throwable th) {
                e5 = th;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable e32) {
                        Appodeal.a(e32);
                    }
                }
                throw e5;
            }
        } catch (Throwable th2) {
            e5 = th2;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw e5;
        }
    }

    public static void a(OutputStream outputStream, String str) {
        BufferedOutputStream bufferedOutputStream;
        Throwable e;
        try {
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            try {
                bufferedOutputStream.write(str.getBytes("UTF-8"));
                bufferedOutputStream.flush();
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (Throwable e2) {
                        Appodeal.a(e2);
                    }
                }
            } catch (Exception e3) {
                e2 = e3;
                try {
                    Appodeal.a(e2);
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (Throwable e22) {
                            Appodeal.a(e22);
                        }
                    }
                } catch (Throwable th) {
                    e22 = th;
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (Throwable e4) {
                            Appodeal.a(e4);
                        }
                    }
                    throw e22;
                }
            }
        } catch (Exception e5) {
            e22 = e5;
            bufferedOutputStream = null;
            Appodeal.a(e22);
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
        } catch (Throwable th2) {
            e22 = th2;
            bufferedOutputStream = null;
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            throw e22;
        }
    }

    public static boolean n(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        double d = (double) (((float) displayMetrics.widthPixels) / displayMetrics.xdpi);
        double d2 = (double) (((float) displayMetrics.heightPixels) / displayMetrics.ydpi);
        return Double.valueOf(Math.sqrt((d2 * d2) + (d * d))).doubleValue() >= 6.6d;
    }

    public static boolean b(Context context, String str) {
        boolean z = false;
        InputStream inputStream = null;
        try {
            InputStream open = context.getAssets().open(String.format("dex/%s.dex", new Object[]{str}));
            if (open == null) {
                return true;
            }
            try {
                open.close();
                return true;
            } catch (Throwable e) {
                Appodeal.a(e);
                return true;
            }
        } catch (Throwable e2) {
            Appodeal.a(e2);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable e22) {
                    Appodeal.a(e22);
                }
            }
            return z;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable e3) {
                    Appodeal.a(e3);
                }
            }
        }
    }

    public static void b(final String str) {
        if (!TextUtils.isEmpty(str)) {
            new Thread() {
                public void run() {
                    Throwable e;
                    Throwable th;
                    HttpURLConnection httpURLConnection = null;
                    try {
                        HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
                        try {
                            httpURLConnection2.setConnectTimeout(5000);
                            httpURLConnection2.setInstanceFollowRedirects(true);
                            httpURLConnection2.setRequestProperty("Connection", "close");
                            httpURLConnection2.setRequestMethod(HttpRequest.METHOD_GET);
                            httpURLConnection2.getResponseCode();
                            if (httpURLConnection2 != null) {
                                try {
                                    httpURLConnection2.disconnect();
                                } catch (Throwable e2) {
                                    Appodeal.a(e2);
                                }
                            }
                        } catch (Throwable e3) {
                            th = e3;
                            httpURLConnection = httpURLConnection2;
                            e2 = th;
                            try {
                                Appodeal.a(e2);
                                if (httpURLConnection != null) {
                                    try {
                                        httpURLConnection.disconnect();
                                    } catch (Throwable e22) {
                                        Appodeal.a(e22);
                                    }
                                }
                            } catch (Throwable th2) {
                                e22 = th2;
                                if (httpURLConnection != null) {
                                    try {
                                        httpURLConnection.disconnect();
                                    } catch (Throwable e32) {
                                        Appodeal.a(e32);
                                    }
                                }
                                throw e22;
                            }
                        } catch (Throwable e322) {
                            th = e322;
                            httpURLConnection = httpURLConnection2;
                            e22 = th;
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            throw e22;
                        }
                    } catch (Exception e4) {
                        e22 = e4;
                        Appodeal.a(e22);
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                    }
                }
            }.start();
        }
    }

    public static void b(final Activity activity, final String str) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(activity, str, 1).show();
            }
        });
    }

    static boolean a() {
        try {
            for (String file : new String[]{"/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su"}) {
                if (new File(file).exists()) {
                    return true;
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
        return false;
    }

    @TargetApi(21)
    static String b() {
        if (VERSION.SDK_INT < 21) {
            return Build.CPU_ABI;
        }
        return Build.SUPPORTED_ABIS[0];
    }

    static int o(Context context) {
        FeatureInfo[] systemAvailableFeatures = context.getPackageManager().getSystemAvailableFeatures();
        if (systemAvailableFeatures == null || systemAvailableFeatures.length <= 0) {
            return 1;
        }
        int length = systemAvailableFeatures.length;
        int i = 0;
        while (i < length) {
            FeatureInfo featureInfo = systemAvailableFeatures[i];
            if (featureInfo.name != null) {
                i++;
            } else if (featureInfo.reqGlEsVersion != 0) {
                return (featureInfo.reqGlEsVersion & SupportMenu.CATEGORY_MASK) >> 16;
            } else {
                return 1;
            }
        }
        return 1;
    }

    static long p(Context context) {
        MemoryInfo memoryInfo = new MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return c() - (memoryInfo.availMem / 1048576);
    }

    static long c() {
        Throwable e;
        if (a == 0) {
            RandomAccessFile randomAccessFile;
            try {
                randomAccessFile = new RandomAccessFile("/proc/meminfo", "r");
                try {
                    Matcher matcher = Pattern.compile("(\\d+)").matcher(randomAccessFile.readLine());
                    String str = "";
                    while (matcher.find()) {
                        str = matcher.group(1);
                    }
                    try {
                        a = Long.parseLong(str) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
                    } catch (NumberFormatException e2) {
                        a = 0;
                    }
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                } catch (Exception e4) {
                    e = e4;
                    try {
                        Appodeal.a(e);
                        e.printStackTrace();
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (Exception e32) {
                                e32.printStackTrace();
                            }
                        }
                        return a;
                    } catch (Throwable th) {
                        e = th;
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (Exception e5) {
                                e5.printStackTrace();
                            }
                        }
                        throw e;
                    }
                }
            } catch (Exception e6) {
                e = e6;
                randomAccessFile = null;
                Appodeal.a(e);
                e.printStackTrace();
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                return a;
            } catch (Throwable th2) {
                e = th2;
                randomAccessFile = null;
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                throw e;
            }
        }
        return a;
    }

    @TargetApi(18)
    static long d() {
        if (VERSION.SDK_INT < 18) {
            StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
            return ((((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) - (((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks()))) / 1048576;
        }
        statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
        return ((statFs.getBlockCountLong() * statFs.getBlockSizeLong()) - (statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong())) / 1048576;
    }

    @TargetApi(18)
    static long e() {
        if (VERSION.SDK_INT < 18) {
            StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
            return (((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount())) / 1048576;
        }
        statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
        return (statFs.getBlockSizeLong() * statFs.getBlockCountLong()) / 1048576;
    }

    static String q(Context context) {
        switch (context.getResources().getConfiguration().orientation) {
            case 0:
                return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            case 1:
                return "Portrait";
            case 2:
                return "Landscape";
            default:
                return null;
        }
    }

    static boolean r(Context context) {
        switch (((AudioManager) context.getSystemService("audio")).getRingerMode()) {
            case 0:
                return true;
            case 1:
                return true;
            default:
                return false;
        }
    }

    public static boolean a(Activity activity, View view) {
        try {
            Rect rect = new Rect();
            Object obj = (view.getGlobalVisibleRect(rect) && view.isShown() && view.hasWindowFocus() && !a(view)) ? 1 : null;
            if (obj == null) {
                return false;
            }
            float width = (float) (view.getWidth() * view.getHeight());
            if (width == 0.0f) {
                return false;
            }
            if ((((float) (rect.width() * rect.height())) / width) * 100.0f < 80.0f) {
                return false;
            }
            View view2 = (ViewGroup) view.getRootView();
            Rect rect2 = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect2);
            if (!Rect.intersects(rect, rect2)) {
                return false;
            }
            View view3 = (ViewGroup) view.getParent();
            int i = 0;
            View view4 = view;
            while (view3 != null) {
                View childAt;
                int indexOfChild = view3.indexOfChild(view4) + 1;
                while (indexOfChild < view3.getChildCount()) {
                    int i2;
                    childAt = view3.getChildAt(indexOfChild);
                    if (childAt.getVisibility() == 0) {
                        childAt.getLocationInWindow(new int[2]);
                        rect2 = b(childAt);
                        if (Rect.intersects(rect, rect2)) {
                            if (a(rect, rect2) < 80.0f) {
                                Appodeal.a("Ad View is covered by another view", LogLevel.verbose);
                                return false;
                            }
                            i2 = i + 1;
                            if (i2 >= 3) {
                                Appodeal.a("Ad View is covered by too many views", LogLevel.verbose);
                                return false;
                            }
                            indexOfChild++;
                            i = i2;
                        }
                    }
                    i2 = i;
                    indexOfChild++;
                    i = i2;
                }
                if (view3 != view2) {
                    childAt = (ViewGroup) view3.getParent();
                } else {
                    childAt = null;
                    view3 = view4;
                }
                view4 = view3;
                view3 = childAt;
            }
            return true;
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    private static float a(Rect rect, Rect rect2) {
        int width = rect.width() * rect.height();
        if (width == 0) {
            return 0.0f;
        }
        return (((float) (width - (Math.max(0, Math.min(rect.right, rect2.right) - Math.max(rect.left, rect2.left)) * Math.max(0, Math.min(rect.bottom, rect2.bottom) - Math.max(rect.top, rect2.top))))) / ((float) width)) * 100.0f;
    }

    public static boolean a(View view) {
        return VERSION.SDK_INT >= 11 && view.getAlpha() == 0.0f;
    }

    public static Rect b(View view) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        return new Rect(iArr[0], iArr[1], view.getWidth() + iArr[0], iArr[1] + view.getHeight());
    }

    public static String f() {
        return b.format(Calendar.getInstance().getTime());
    }

    public static Date c(String str) {
        try {
            return b.parse(str);
        } catch (Throwable e) {
            Appodeal.a(e);
            return null;
        }
    }

    public static Object a(Object obj, String str, boolean z, int i) {
        Field declaredField;
        if (z) {
            Class cls = obj.getClass();
            if (i > 0) {
                int i2 = 0;
                while (i2 < i) {
                    i2++;
                    cls = cls.getSuperclass();
                }
            }
            declaredField = cls.getDeclaredField(str);
        } else {
            declaredField = obj.getClass().getDeclaredField(str);
        }
        declaredField.setAccessible(true);
        return declaredField.get(obj);
    }

    public static String d(String str) {
        return "<MediaFile type=\"video/mp4\">" + str + "</MediaFile>";
    }

    public static String c(View view) {
        String str = null;
        try {
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.ARGB_8888);
            view.draw(new Canvas(createBitmap));
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if (createBitmap.compress(CompressFormat.JPEG, 50, byteArrayOutputStream)) {
                str = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
        return str;
    }

    public static String b(Activity activity) {
        try {
            int i;
            if (a("com.google.android.gms.common.GoogleApiAvailability")) {
                i = GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE;
            } else {
                i = activity.getPackageManager().getApplicationInfo(activity.getPackageName(), 128).metaData.getInt("com.google.android.gms.version");
            }
            return String.valueOf(i);
        } catch (Throwable th) {
            Appodeal.a(th);
            return "not-found";
        }
    }

    public static int s(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.google.android.webview", 0);
            if (packageInfo != null) {
                return packageInfo.versionCode;
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    public static String a(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        if ((i & 1) > 0) {
            stringBuilder.append("Interstitial");
        }
        if ((i & 2) > 0) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("Video");
        }
        if ((i & 128) > 0) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("Rewarded video");
        }
        if ((i & 92) > 0) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("Banner");
        }
        if ((i & 256) > 0) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("MREC");
        }
        if ((i & 512) > 0) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("NativeAd");
        }
        return stringBuilder.toString();
    }

    static void a(String str, List<JSONObject> list, List<JSONObject> list2) {
        if (Appodeal.getLogLevel() != LogLevel.none) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(String.format("%s waterfall:\n", new Object[]{a(str)}));
            if (!(list == null || list.isEmpty())) {
                stringBuilder.append("  Precache:\n    ");
                for (JSONObject has : list) {
                    if (has.has("name")) {
                        stringBuilder.append(String.format(Locale.ENGLISH, "%s (%s), eCPM: %.2f; ", new Object[]{a(((JSONObject) r1.next()).optString("name")), a(((JSONObject) r1.next()).optString("status")), Double.valueOf(((JSONObject) r1.next()).optDouble("ecpm", 0.0d))}));
                    } else {
                        stringBuilder.append(String.format(Locale.ENGLISH, "%s, eCPM: %.2f; ", new Object[]{a(((JSONObject) r1.next()).optString("status")), Double.valueOf(((JSONObject) r1.next()).optDouble("ecpm", 0.0d))}));
                    }
                }
                stringBuilder.append("\n");
            }
            if (!(list2 == null || list2.isEmpty())) {
                stringBuilder.append("  Ads:\n    ");
                int i = 0;
                for (JSONObject has2 : list2) {
                    String format;
                    if (has2.has("name")) {
                        format = String.format(Locale.ENGLISH, "%s (%s), eCPM: %.2f; ", new Object[]{a(((JSONObject) r4.next()).optString("name")), a(((JSONObject) r4.next()).optString("status")), Double.valueOf(((JSONObject) r4.next()).optDouble("ecpm", 0.0d))});
                    } else {
                        format = String.format(Locale.ENGLISH, "%s, eCPM: %.2f; ", new Object[]{a(((JSONObject) r4.next()).optString("status")), Double.valueOf(((JSONObject) r4.next()).optDouble("ecpm", 0.0d))});
                    }
                    stringBuilder.append(format);
                    int length = format.length() + i;
                    if (length >= 100) {
                        stringBuilder.append("\n    ");
                        length = 0;
                    }
                    i = length;
                }
            }
            Appodeal.a(stringBuilder.toString());
        }
    }
}
