package com.cmcm.picks.mixad;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import java.util.Map;

public class MixSPUtil {
    private static final String TAG = "MixSPUtil";
    private static SharedPreferences sSharePreference;

    public static boolean init(Context context) {
        if (sSharePreference == null) {
            synchronized (MixSPUtil.class) {
                if (sSharePreference == null) {
                    sSharePreference = context.getSharedPreferences("sp_mixbox", 0);
                }
            }
        }
        if (sSharePreference != null) {
            return true;
        }
        return false;
    }

    public static void putLong(String posId, String key, long value) {
        if (sSharePreference != null) {
            Editor edit = sSharePreference.edit();
            edit.putLong(String.valueOf(posId + key), value);
            applyEditor(edit);
        }
    }

    public static long getLong(String posId, String key, long defaultValue) {
        if (sSharePreference == null) {
            return 0;
        }
        return sSharePreference.getLong(String.valueOf(posId + key), defaultValue);
    }

    public static void putString(String posId, String key, String value) {
        if (sSharePreference != null) {
            Editor edit = sSharePreference.edit();
            edit.putString(String.valueOf(posId + key), value);
            applyEditor(edit);
        }
    }

    public static String getString(String posId, String key, String defaultValue) {
        if (sSharePreference == null) {
            return "";
        }
        return sSharePreference.getString(String.valueOf(posId + key), defaultValue);
    }

    public static void putBoolean(String posId, String key, boolean value) {
        if (sSharePreference != null) {
            Editor edit = sSharePreference.edit();
            edit.putBoolean(String.valueOf(posId + key), value);
            applyEditor(edit);
        }
    }

    public static boolean getBoolean(String posId, String key, boolean defaultValue) {
        if (sSharePreference == null) {
            return false;
        }
        return sSharePreference.getBoolean(String.valueOf(posId + key), defaultValue);
    }

    public static Map<String, ?> getAll() {
        if (sSharePreference == null) {
            return null;
        }
        return sSharePreference.getAll();
    }

    public static void remove(String posId, String key) {
        if (sSharePreference != null) {
            Editor edit = sSharePreference.edit();
            edit.remove(String.valueOf(posId + key));
            applyEditor(edit);
        }
    }

    private static void applyEditor(Editor editor) {
        if (VERSION.SDK_INT >= 9) {
            editor.apply();
        } else {
            editor.commit();
        }
    }
}
