package com.yandex.metrica.impl;

import android.content.Context;
import java.io.File;
import java.io.FilenameFilter;
import java.util.concurrent.ExecutorService;

class NativeCrashesHelper {
    private String a;
    private final Context b;
    private boolean c;
    private boolean d;

    private static class a implements Runnable {
        private final at a;
        private final NativeCrashesHelper b;

        a(at atVar, NativeCrashesHelper nativeCrashesHelper) {
            this.b = nativeCrashesHelper;
            this.a = atVar;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r7 = this;
            r0 = r7.b;
            r1 = r0.a;
            r2 = com.yandex.metrica.impl.NativeCrashesHelper.a(r1);
            r3 = r2.length;
            r0 = 0;
        L_0x000c:
            if (r0 >= r3) goto L_0x0055;
        L_0x000e:
            r4 = r2[r0];
            r5 = new java.lang.StringBuilder;
            r5.<init>();
            r5 = r5.append(r1);
            r6 = "/";
            r5 = r5.append(r6);
            r4 = r5.append(r4);
            r4 = r4.toString();
            r5 = com.yandex.metrica.impl.r.a(r4);	 Catch:{ Exception -> 0x0041, all -> 0x004b }
            r5 = com.yandex.metrica.impl.r.b(r5);	 Catch:{ Exception -> 0x0041, all -> 0x004b }
            if (r5 == 0) goto L_0x0036;
        L_0x0031:
            r6 = r7.a;	 Catch:{ Exception -> 0x0041, all -> 0x004b }
            r6.a(r5);	 Catch:{ Exception -> 0x0041, all -> 0x004b }
        L_0x0036:
            r5 = new java.io.File;
            r5.<init>(r4);
            r5.delete();
        L_0x003e:
            r0 = r0 + 1;
            goto L_0x000c;
        L_0x0041:
            r5 = move-exception;
            r5 = new java.io.File;
            r5.<init>(r4);
            r5.delete();
            goto L_0x003e;
        L_0x004b:
            r0 = move-exception;
            r1 = new java.io.File;
            r1.<init>(r4);
            r1.delete();
            throw r0;
        L_0x0055:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.NativeCrashesHelper.a.run():void");
        }
    }

    private static native void cancelSetUpNativeUncaughtExceptionHandler();

    private static native void logsEnabled(boolean z);

    private static native void setUpNativeUncaughtExceptionHandler(String str);

    static /* synthetic */ String[] a(String str) {
        File file = new File(str + "/");
        if (!file.mkdir() && !file.exists()) {
            return new String[0];
        }
        String[] list = file.list(new FilenameFilter() {
            public boolean accept(File dir, String filename) {
                return filename.endsWith(".dmp");
            }
        });
        return list == null ? new String[0] : list;
    }

    NativeCrashesHelper(Context context) {
        this.b = context;
    }

    synchronized void a(boolean z) {
        if (z) {
            try {
                if (!this.d && a()) {
                    b(true);
                    this.a = this.b.getFilesDir().getAbsolutePath() + "/YandexMetricaNativeCrashes";
                }
                this.d = true;
                if (b()) {
                    setUpNativeUncaughtExceptionHandler(this.a);
                    this.c = true;
                }
            } catch (Throwable th) {
                this.c = false;
            }
        } else {
            try {
                if (c()) {
                    cancelSetUpNativeUncaughtExceptionHandler();
                }
            } catch (Throwable th2) {
            }
            this.c = false;
        }
    }

    synchronized void a(at atVar, ExecutorService executorService) {
        if (c()) {
            executorService.execute(new a(atVar, this));
            this.c = false;
        }
    }

    private boolean b() {
        return this.a != null;
    }

    private boolean c() {
        return b() && this.c;
    }

    private static boolean b(boolean z) {
        try {
            logsEnabled(z);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    boolean a() {
        try {
            System.loadLibrary("YandexMetricaNativeModule");
            return true;
        } catch (Throwable th) {
            return false;
        }
    }
}
