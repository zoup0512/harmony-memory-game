package com.cmcm.picks.vastvideo;

import android.content.Context;
import android.text.TextUtils;
import com.cmcm.adsdk.utils.PerferenceUtil;
import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: VastCache */
public class b {
    private static b a;

    private b() {
    }

    public static b a() {
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = new b();
                }
            }
        }
        return a;
    }

    public void a(String str, long j) {
        PerferenceUtil.putLong(str, j);
    }

    public void a(Context context) {
        try {
            Map all = PerferenceUtil.getAll();
            if (all != null && !all.isEmpty()) {
                for (Entry entry : all.entrySet()) {
                    if (entry != null) {
                        String str = (String) entry.getKey();
                        if (!TextUtils.isEmpty(str) && ((str.contains(".mp4") || str.contains(".3gp")) && (System.currentTimeMillis() - ((Long) entry.getValue()).longValue()) - 604800000 > 0)) {
                            a(context, str);
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        File file = new File(str);
        if (file.exists() || file.isDirectory()) {
            return false;
        }
        return true;
    }

    private void a(Context context, String str) {
        if (context != null) {
            File file;
            if (f.a()) {
                file = new File(new String(context.getExternalFilesDir(null).getPath() + "/VastVideo"));
                if (file == null || !file.isDirectory()) {
                    file = null;
                }
            } else {
                file = context.getDir("VastVideo", 1);
            }
            if (file != null) {
                File[] listFiles = file.listFiles();
                if (listFiles != null && listFiles.length != 0) {
                    for (File file2 : listFiles) {
                        if (file2.getAbsolutePath().equalsIgnoreCase(str)) {
                            file2.delete();
                        }
                    }
                }
            }
        }
    }
}
