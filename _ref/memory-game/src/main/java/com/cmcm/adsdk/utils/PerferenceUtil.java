package com.cmcm.adsdk.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import com.cmcm.utils.g;
import java.util.Map;

public class PerferenceUtil {
    private static final String TAG = "PerferenceUtil";
    private static final String key = "config_cache";
    private static Context mContext;
    private static SharedPreferences sSharePreference;
    private static String spfName;

    public static void init(Context context, String mid) {
        mContext = context;
        spfName = String.format("%s_%s", new Object[]{"cmadsdk", mid});
    }

    public static synchronized String getCacheJsonStr(String defValue) {
        String string;
        synchronized (PerferenceUtil.class) {
            try {
                if (sSharePreference == null) {
                    sSharePreference = mContext.getSharedPreferences(spfName, 0);
                }
                string = sSharePreference.getString(key, defValue);
            } catch (Exception e) {
                g.b(TAG, "get cache json error..." + e.getMessage());
                string = "";
            }
        }
        return string;
    }

    public static synchronized void saveCacheJsonStr(String value) {
        synchronized (PerferenceUtil.class) {
            try {
                if (sSharePreference == null) {
                    sSharePreference = mContext.getSharedPreferences(spfName, 0);
                }
                Editor edit = sSharePreference.edit();
                edit.putString(key, value);
                applyEditor(edit);
            } catch (Exception e) {
                g.b(TAG, "save cache json error..." + e.getMessage());
            }
        }
    }

    public static void applyEditor(Editor editor) {
        if (VERSION.SDK_INT >= 9) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    public static void putString(String key, String value) {
        if (sSharePreference == null) {
            sSharePreference = mContext.getSharedPreferences(spfName, 0);
        }
        Editor edit = sSharePreference.edit();
        edit.putString(key, value);
        applyEditor(edit);
    }

    public static String getString(String key, String defaultValue) {
        if (sSharePreference == null) {
            sSharePreference = mContext.getSharedPreferences(spfName, 0);
        }
        return sSharePreference.getString(key, defaultValue);
    }

    public static void putBoolean(String key, boolean value) {
        if (sSharePreference == null) {
            sSharePreference = mContext.getSharedPreferences(spfName, 0);
        }
        Editor edit = sSharePreference.edit();
        edit.putBoolean(key, value);
        applyEditor(edit);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        if (sSharePreference == null) {
            sSharePreference = mContext.getSharedPreferences(spfName, 0);
        }
        return sSharePreference.getBoolean(key, defaultValue);
    }

    public static void putInt(String key, int value) {
        if (sSharePreference == null) {
            sSharePreference = mContext.getSharedPreferences(spfName, 0);
        }
        Editor edit = sSharePreference.edit();
        edit.putInt(key, value);
        applyEditor(edit);
    }

    public static int getInt(String key, int defaultValue) {
        if (sSharePreference == null) {
            sSharePreference = mContext.getSharedPreferences(spfName, 0);
        }
        return sSharePreference.getInt(key, defaultValue);
    }

    public static void putLong(String key, long value) {
        if (sSharePreference == null) {
            sSharePreference = mContext.getSharedPreferences(spfName, 0);
        }
        Editor edit = sSharePreference.edit();
        edit.putLong(key, value);
        applyEditor(edit);
    }

    public static long getLong(String key, long defaultValue) {
        if (sSharePreference == null) {
            sSharePreference = mContext.getSharedPreferences(spfName, 0);
        }
        return sSharePreference.getLong(key, defaultValue);
    }

    public static Map<String, ?> getAll() {
        if (sSharePreference == null) {
            sSharePreference = mContext.getSharedPreferences(spfName, 0);
        }
        return sSharePreference.getAll();
    }
}
