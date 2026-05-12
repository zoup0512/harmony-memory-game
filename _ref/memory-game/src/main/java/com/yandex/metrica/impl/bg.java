package com.yandex.metrica.impl;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import com.cube.memorygames.Games;
import com.facebook.appevents.AppEventsConstants;
import com.yalantis.ucrop.util.FileUtils;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.MetricaService;
import com.yandex.metrica.impl.ob.br;
import io.fabric.sdk.android.services.common.IdManager;
import java.io.Closeable;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class bg {

    static class a extends JSONObject {
        public a(String str) throws JSONException {
            super(str);
        }

        public String a(String str) {
            try {
                return super.getString(str);
            } catch (Exception e) {
                return "";
            }
        }

        public Object a(String str, Object obj) {
            try {
                obj = super.get(str);
            } catch (Exception e) {
            }
            return obj;
        }

        public boolean b(String str) {
            try {
                return NULL != super.get(str);
            } catch (Exception e) {
                return false;
            }
        }
    }

    static final class b implements Runnable {
        final Context a;

        public b(Context context) {
            this.a = context;
        }

        public void run() {
            Context context = this.a;
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 516);
                if (packageInfo.services != null) {
                    for (ServiceInfo serviceInfo : packageInfo.services) {
                        if (MetricaService.class.getName().equals(serviceInfo.name) && !serviceInfo.enabled) {
                            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, MetricaService.class), 1, 1);
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public static String a(Context context, String str) {
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(str, 0).versionCode);
        } catch (NameNotFoundException e) {
            return AppEventsConstants.EVENT_PARAM_VALUE_NO;
        }
    }

    public static String b(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (NameNotFoundException e) {
            return IdManager.DEFAULT_VERSION_NAME;
        }
    }

    public static String a(String str) {
        String b;
        StringBuilder append = new StringBuilder().append(str).append("/").append(ax.a()).append(".5816 (");
        if (Build.MODEL.startsWith(Build.MANUFACTURER)) {
            b = be.b(Build.MODEL);
        } else {
            b = be.b(Build.MANUFACTURER) + " " + Build.MODEL;
        }
        return append.append(b).append("; Android ").append(VERSION.RELEASE).append(")").toString();
    }

    public static String a(String str, Throwable th) {
        String b = b(th);
        if (TextUtils.isEmpty(str)) {
            return b;
        }
        return str + ":\n" + b;
    }

    private static String b(Throwable th) {
        String str = "";
        if (th == null) {
            return str;
        }
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        str = stringWriter.toString();
        printWriter.close();
        return str;
    }

    public static boolean a(int i) {
        return VERSION.SDK_INT >= i;
    }

    public static boolean b(int i) {
        return VERSION.SDK_INT > i;
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

    public static void a(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e) {
            }
        }
    }

    public static void a(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    public static void a(Object obj, String str) throws IllegalArgumentException {
        if (obj == null) {
            throw new IllegalArgumentException(String.format(Locale.US, "Invalid %s. %s should not be null.", new Object[]{str, str}));
        }
    }

    public static void a(String str, String str2) throws IllegalArgumentException {
        if (be.a(str)) {
            throw new IllegalArgumentException(String.format(Locale.US, "Invalid %s. %s should not be null/empty.", new Object[]{str2, str2}));
        }
    }

    public static void b(String str) {
        a(str, "API Key");
        try {
            UUID.fromString(str);
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format(Locale.US, "Invalid %s = %s. Please, read official documentation how to obtain one: %s", new Object[]{"API Key", str, "https://tech.yandex.com/metrica-mobile-sdk/doc/mobile-sdk-dg/concepts/android-initialize-docpage/"}));
        }
    }

    public static long a(PackageManager packageManager, String str) {
        long max;
        long j = -1;
        try {
            if (b(8)) {
                PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
                max = Math.max(packageInfo.firstInstallTime, packageInfo.lastUpdateTime);
            } else {
                max = -1;
            }
        } catch (Exception e) {
            max = -1;
        }
        try {
            File file = new File(packageManager.getApplicationInfo(str, 0).sourceDir);
            if (file.exists()) {
                j = file.lastModified();
            }
        } catch (Exception e2) {
        }
        return Math.max(max, j);
    }

    public static String c(Context context, String str) {
        String a = br.a().a(context);
        if (!be.a(a)) {
            Intent a2 = ba.a(context);
            a2.setPackage(str);
            for (ResolveInfo resolveInfo : ba.a(context, a2)) {
                if (ba.a(resolveInfo.serviceInfo) < 29) {
                    try {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("DEVICE_ID", a);
                        if (!be.a(a)) {
                            context.getContentResolver().update(Uri.parse(String.format(Locale.US, "content://%s.MetricaContentProvider/DEVICE_ID", new Object[]{str})), contentValues, null, null);
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        return a;
    }

    public static List<ResolveInfo> a(Context context, String str, String str2) {
        List<ResolveInfo> arrayList = new ArrayList();
        try {
            Intent intent = new Intent(str, null);
            intent.addCategory(str2);
            arrayList = context.getPackageManager().queryIntentActivities(intent, 0);
        } catch (Exception e) {
        }
        return arrayList;
    }

    public static String a(PackageManager packageManager, String str, String str2, String str3) {
        try {
            Bundle bundle = packageManager.getApplicationInfo(str, 128).metaData;
            Object obj = bundle != null ? bundle.get(str2) : null;
            if (obj != null) {
                str3 = obj.toString();
            }
        } catch (Exception e) {
        }
        return str3;
    }

    public static long a(boolean z) {
        try {
            StatFs c = c(z);
            return (((long) c.getBlockSize()) * ((long) c.getBlockCount())) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        } catch (Throwable th) {
            return 0;
        }
    }

    public static long b(boolean z) {
        try {
            StatFs c = c(z);
            return (((long) c.getBlockSize()) * ((long) c.getAvailableBlocks())) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        } catch (Throwable th) {
            return 0;
        }
    }

    public static StatFs c(boolean z) {
        if (z) {
            return new StatFs(Environment.getRootDirectory().getAbsolutePath());
        }
        return new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
    }

    public static void a(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase != null) {
            try {
                sQLiteDatabase.endTransaction();
            } catch (Exception e) {
            }
        }
    }

    public static boolean a(Map map) {
        return map == null || map.size() == 0;
    }

    public static boolean a(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    public static CounterConfiguration a(Bundle bundle) {
        CounterConfiguration counterConfiguration;
        if (bundle != null) {
            try {
                counterConfiguration = (CounterConfiguration) bundle.getParcelable("COUNTER_CFG_OBJ");
            } catch (Throwable th) {
                return null;
            }
        }
        counterConfiguration = null;
        if (counterConfiguration == null) {
            counterConfiguration = new CounterConfiguration();
        }
        counterConfiguration.a(bundle);
        return counterConfiguration;
    }

    public static CounterConfiguration b(Bundle bundle) {
        CounterConfiguration counterConfiguration;
        if (bundle != null) {
            try {
                counterConfiguration = (CounterConfiguration) bundle.getParcelable("COUNTER_MIGRATION_CFG_OBJ");
            } catch (Throwable th) {
                return null;
            }
        }
        counterConfiguration = null;
        return counterConfiguration;
    }

    public static String a(Context context, CounterConfiguration counterConfiguration, String str) {
        return counterConfiguration.B() ? counterConfiguration.j() : a(context.getPackageManager(), str, "metrica:api:key", null);
    }

    public static boolean c(String str) {
        return (be.a(str) || Games.SMART_PROMO_GAME_ID.equals(str)) ? false : true;
    }

    static boolean a(Throwable th) {
        String str = "com.yandex.metrica";
        Object b = b(th);
        return !TextUtils.isEmpty(b) && b.contains("at " + str + FileUtils.HIDDEN_PREFIX);
    }

    public static HashMap<String, String> a(JSONObject jSONObject) {
        if (JSONObject.NULL.equals(jSONObject)) {
            return null;
        }
        HashMap<String, String> hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            String optString = jSONObject.optString(str);
            if (optString != null) {
                hashMap.put(str, optString);
            }
        }
        return hashMap;
    }

    public static HashMap<String, String> d(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return a(new JSONObject(str));
            } catch (JSONException e) {
            }
        }
        return null;
    }

    public static String b(Map map) {
        if (a(map)) {
            return null;
        }
        if (a(19)) {
            return new JSONObject(map).toString();
        }
        return a((Object) map).toString();
    }

    static Object a(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            if (obj.getClass().isArray()) {
                int length = Array.getLength(obj);
                Collection arrayList = new ArrayList(length);
                for (int i = 0; i < length; i++) {
                    arrayList.add(a(Array.get(obj, i)));
                }
                return new JSONArray(arrayList);
            } else if (obj instanceof Collection) {
                Collection<Object> collection = (Collection) obj;
                Collection arrayList2 = new ArrayList(collection.size());
                for (Object a : collection) {
                    arrayList2.add(a(a));
                }
                return new JSONArray(arrayList2);
            } else if (!(obj instanceof Map)) {
                return obj;
            } else {
                Map map = (Map) obj;
                Map linkedHashMap = new LinkedHashMap();
                for (Entry entry : map.entrySet()) {
                    String obj2 = entry.getKey().toString();
                    if (obj2 != null) {
                        linkedHashMap.put(obj2, a(entry.getValue()));
                    }
                }
                return new JSONObject(linkedHashMap);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static Boolean a() {
        return b.a.c();
    }
}
