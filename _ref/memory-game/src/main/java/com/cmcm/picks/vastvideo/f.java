package com.cmcm.picks.vastvideo;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Base64;
import com.cmcm.utils.Commons;

/* compiled from: VastUtils */
public class f {
    private static int a = 1048576;

    public static String a(String str) {
        return TextUtils.isEmpty(str) ? "" : new String(Base64.decode(str.getBytes(), 0));
    }

    public static float a(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager != null) {
            return (float) audioManager.getStreamVolume(3);
        }
        return 0.0f;
    }

    public static float b(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager != null) {
            return (float) audioManager.getStreamMaxVolume(3);
        }
        return 0.0f;
    }

    public static void a(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setFlags(268435456);
            intent.setData(Uri.parse(str));
            if (Commons.isHasPackage(context, "com.android.browser")) {
                intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
            }
            try {
                context.startActivity(intent);
            } catch (Exception e) {
            }
        }
    }

    public static boolean a() {
        String externalStorageState = Environment.getExternalStorageState();
        if (externalStorageState == null || !externalStorageState.equals("mounted")) {
            return false;
        }
        return true;
    }

    public static int b() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return (int) ((((double) statFs.getBlockSize()) * ((double) statFs.getAvailableBlocks())) / ((double) a));
    }

    public static long c() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / ((long) a);
    }
}
