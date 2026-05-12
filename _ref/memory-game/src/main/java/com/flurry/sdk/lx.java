package com.flurry.sdk;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Environment;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public final class lx {
    private static String a = lx.class.getSimpleName();

    public static File a() {
        File file = null;
        Context context = jy.a().a;
        if ("mounted".equals(Environment.getExternalStorageState()) && (VERSION.SDK_INT >= 19 || context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0)) {
            file = context.getExternalFilesDir(null);
        }
        if (file == null) {
            return context.getFilesDir();
        }
        return file;
    }

    public static File b() {
        Context context = jy.a().a;
        File file = null;
        if ("mounted".equals(Environment.getExternalStorageState()) && (VERSION.SDK_INT >= 19 || context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0)) {
            file = context.getExternalCacheDir();
        }
        if (file == null) {
            return context.getCacheDir();
        }
        return file;
    }

    public static boolean a(File file) {
        if (file == null || file.getAbsoluteFile() == null) {
            return false;
        }
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            return true;
        }
        if (parentFile.mkdirs() || parentFile.isDirectory()) {
            return true;
        }
        km.a(6, a, "Unable to create persistent dir: " + parentFile);
        return false;
    }

    public static boolean b(File file) {
        if (file != null && file.isDirectory()) {
            for (String file2 : file.list()) {
                if (!b(new File(file, file2))) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    @Deprecated
    public static String c(File file) {
        Closeable fileInputStream;
        StringBuilder stringBuilder;
        Throwable th;
        Throwable th2;
        if (file == null || !file.exists()) {
            km.a(4, a, "Persistent file doesn't exist.");
            return null;
        }
        km.a(4, a, "Loading persistent data: " + file.getAbsolutePath());
        try {
            fileInputStream = new FileInputStream(file);
            try {
                stringBuilder = new StringBuilder();
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    stringBuilder.append(new String(bArr, 0, read));
                }
                ly.a(fileInputStream);
            } catch (Throwable th3) {
                th = th3;
                try {
                    km.a(6, a, "Error when loading persistent file", th);
                    ly.a(fileInputStream);
                    stringBuilder = null;
                    if (stringBuilder != null) {
                        return null;
                    }
                    return stringBuilder.toString();
                } catch (Throwable th4) {
                    th2 = th4;
                    ly.a(fileInputStream);
                    throw th2;
                }
            }
        } catch (Throwable th5) {
            fileInputStream = null;
            th2 = th5;
            ly.a(fileInputStream);
            throw th2;
        }
        if (stringBuilder != null) {
            return stringBuilder.toString();
        }
        return null;
    }

    @Deprecated
    public static void a(File file, String str) {
        Closeable fileOutputStream;
        Throwable th;
        if (file == null) {
            km.a(4, a, "No persistent file specified.");
        } else if (str == null) {
            km.a(4, a, "No data specified; deleting persistent file: " + file.getAbsolutePath());
            file.delete();
        } else {
            km.a(4, a, "Writing persistent data: " + file.getAbsolutePath());
            try {
                fileOutputStream = new FileOutputStream(file);
                try {
                    fileOutputStream.write(str.getBytes());
                    ly.a(fileOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        km.a(6, a, "Error writing persistent file", th);
                        ly.a(fileOutputStream);
                    } catch (Throwable th3) {
                        th = th3;
                        ly.a(fileOutputStream);
                        throw th;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                fileOutputStream = null;
                ly.a(fileOutputStream);
                throw th;
            }
        }
    }
}
