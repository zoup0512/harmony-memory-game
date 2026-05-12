package com.chartboost.sdk.Libraries;

import android.content.Context;
import android.text.TextUtils;
import com.chartboost.sdk.c;
import com.chartboost.sdk.impl.bj;
import com.mopub.common.AdType;
import com.yalantis.ucrop.util.FileUtils;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class h {
    private static i a;
    private static i b;
    private static File c;
    private static File d;
    private static File e;
    private static com.chartboost.sdk.impl.a f;
    private final boolean g;

    public enum a {
        TemplateMetaData("templates"),
        Videos("videos"),
        Images("images"),
        StyleSheets("css"),
        Javascript("js"),
        Html(AdType.HTML),
        VideoCompletion("videoCompletionEvents"),
        Session(SettingsJsonConstants.SESSION_KEY),
        Track("track"),
        RequestManager("requests");
        
        private final String k;

        private a(String str) {
            this.k = str;
        }

        public String toString() {
            return this.k;
        }
    }

    public static synchronized void a(Context context) {
        synchronized (h.class) {
            f = com.chartboost.sdk.impl.a.a();
            a = new i(context.getCacheDir());
            b = new i(f.b());
            c = new File(a.a, a.RequestManager.toString());
            e = new File(a.a, a.Track.toString());
            d = new File(a.a, a.Session.toString());
        }
    }

    public h(boolean z) {
        this.g = z;
    }

    public static File a() {
        return j().a;
    }

    public synchronized File a(File file, String str, com.chartboost.sdk.Libraries.e.a aVar) {
        File file2;
        file2 = null;
        if (file != null) {
            if (!TextUtils.isEmpty(str)) {
                file2 = new File(file.getPath(), str);
            }
            file2 = a(file, file2, aVar);
        }
        return file2;
    }

    public synchronized File a(File file, File file2, com.chartboost.sdk.Libraries.e.a aVar) {
        File file3;
        if (file == null) {
            file3 = null;
        } else {
            if (file2 == null) {
                file3 = new File(file.getPath(), Long.toString(System.nanoTime()));
            } else {
                file3 = file2;
            }
            try {
                bj.a(file3, aVar.toString().getBytes());
            } catch (Exception e) {
                CBLogging.b("CBFileCache", "IOException attempting to write cache to disk", e);
                com.chartboost.sdk.Tracking.a.a(getClass(), "writeToDisk(File, File, JSONWrapper)", e);
            }
        }
        return file3;
    }

    public synchronized void a(File file, String str, byte[] bArr) {
        if (file != null) {
            File file2 = null;
            if (!TextUtils.isEmpty(str)) {
                file2 = new File(file.getPath(), str);
            }
            a(file, file2, bArr);
        }
    }

    public synchronized void a(File file, File file2, byte[] bArr) {
        if (!(file == null || bArr == null)) {
            if (file2 == null) {
                file2 = new File(file.getPath(), Long.toString(System.nanoTime()));
            }
            try {
                bj.a(file2, bArr);
            } catch (Exception e) {
                CBLogging.b("CBFileCache", "IOException attempting to write cache to disk", e);
                com.chartboost.sdk.Tracking.a.a(getClass(), "writeToDisk(File, File, byte[])", e);
            }
        }
    }

    public synchronized com.chartboost.sdk.Libraries.e.a a(File file, String str) {
        com.chartboost.sdk.Libraries.e.a aVar;
        if (str == null) {
            aVar = com.chartboost.sdk.Libraries.e.a.a;
        } else {
            File file2 = new File(file, str);
            if (file2.exists()) {
                aVar = a(file2);
            } else {
                aVar = com.chartboost.sdk.Libraries.e.a.a;
            }
        }
        return aVar;
    }

    public synchronized com.chartboost.sdk.Libraries.e.a a(File file) {
        String str;
        try {
            str = new String(bj.b(file));
        } catch (Exception e) {
            CBLogging.b("CBFileCache", "Error loading cache from disk", e);
            com.chartboost.sdk.Tracking.a.a(getClass(), "readFromDisk", e);
            str = null;
        }
        return com.chartboost.sdk.Libraries.e.a.k(str);
    }

    public synchronized byte[] b(File file) {
        byte[] bArr = null;
        synchronized (this) {
            if (file != null) {
                try {
                    bArr = bj.b(file);
                } catch (Exception e) {
                    CBLogging.b("CBFileCache", "Error loading cache from disk", e);
                    com.chartboost.sdk.Tracking.a.a(getClass(), "readByteArrayFromDisk", e);
                }
            }
        }
        return bArr;
    }

    public synchronized String[] c(File file) {
        String[] strArr;
        if (file == null) {
            strArr = null;
        } else {
            strArr = file.list();
        }
        return strArr;
    }

    public static synchronized HashMap<String, File> b() {
        HashMap<String, File> hashMap;
        synchronized (h.class) {
            hashMap = new HashMap();
            File file = j().a;
            String[] list = file.list();
            if (list != null && list.length > 0) {
                for (String str : list) {
                    if (!(str.equalsIgnoreCase(a.TemplateMetaData.toString()) || str.equalsIgnoreCase(a.RequestManager.toString()) || str.equalsIgnoreCase(a.Track.toString()) || str.equalsIgnoreCase(a.Session.toString()) || str.equalsIgnoreCase(a.VideoCompletion.toString()) || str.contains(FileUtils.HIDDEN_PREFIX))) {
                        File file2 = new File(file, str);
                        String[] list2 = file2.list();
                        if (list2 != null && list2.length > 0) {
                            for (String str2 : list2) {
                                if (!str2.equals(".nomedia")) {
                                    hashMap.put(str2, new File(file2, str2));
                                }
                            }
                        }
                    }
                }
            }
        }
        return hashMap;
    }

    public static synchronized String[] c() {
        String[] list;
        synchronized (h.class) {
            list = j().g.list();
            if (list == null || list.length == 0) {
                list = null;
            }
        }
        return list;
    }

    public static String a(String str) {
        File file = new File(j().g, str);
        if (file.exists()) {
            return file.getPath();
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void a(java.util.ArrayList<java.lang.String> r6, java.io.File r7, boolean r8) {
        /*
        r2 = 0;
        r3 = com.chartboost.sdk.Libraries.h.class;
        monitor-enter(r3);
        if (r7 == 0) goto L_0x0008;
    L_0x0006:
        if (r6 != 0) goto L_0x000a;
    L_0x0008:
        monitor-exit(r3);
        return;
    L_0x000a:
        if (r8 == 0) goto L_0x0034;
    L_0x000c:
        r1 = d(r7);	 Catch:{ all -> 0x0030 }
        r0 = r1.size();	 Catch:{ all -> 0x0030 }
        if (r0 <= 0) goto L_0x0034;
    L_0x0016:
        r4 = r6.iterator();	 Catch:{ all -> 0x0030 }
    L_0x001a:
        r0 = r4.hasNext();	 Catch:{ all -> 0x0030 }
        if (r0 == 0) goto L_0x0033;
    L_0x0020:
        r0 = r4.next();	 Catch:{ all -> 0x0030 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0030 }
        r5 = r1.contains(r0);	 Catch:{ all -> 0x0030 }
        if (r5 != 0) goto L_0x001a;
    L_0x002c:
        r1.add(r0);	 Catch:{ all -> 0x0030 }
        goto L_0x001a;
    L_0x0030:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
    L_0x0033:
        r6 = r1;
    L_0x0034:
        r4 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x0057 }
        r4.<init>(r7);	 Catch:{ IOException -> 0x0057 }
        r0 = 0;
        r5 = new java.io.ObjectOutputStream;	 Catch:{ Throwable -> 0x0065, all -> 0x0076 }
        r5.<init>(r4);	 Catch:{ Throwable -> 0x0065, all -> 0x0076 }
        r1 = 0;
        r5.writeObject(r6);	 Catch:{ Throwable -> 0x0078, all -> 0x009a }
        if (r5 == 0) goto L_0x004a;
    L_0x0045:
        if (r2 == 0) goto L_0x0072;
    L_0x0047:
        r5.close();	 Catch:{ Throwable -> 0x0060, all -> 0x0076 }
    L_0x004a:
        if (r4 == 0) goto L_0x0008;
    L_0x004c:
        if (r2 == 0) goto L_0x008c;
    L_0x004e:
        r4.close();	 Catch:{ Throwable -> 0x0052 }
        goto L_0x0008;
    L_0x0052:
        r1 = move-exception;
        r0.addSuppressed(r1);	 Catch:{ IOException -> 0x0057 }
        goto L_0x0008;
    L_0x0057:
        r0 = move-exception;
        r1 = com.chartboost.sdk.Libraries.h.class;
        r2 = "serialize";
        com.chartboost.sdk.Tracking.a.a(r1, r2, r0);	 Catch:{ all -> 0x0030 }
        goto L_0x0008;
    L_0x0060:
        r5 = move-exception;
        r1.addSuppressed(r5);	 Catch:{ Throwable -> 0x0065, all -> 0x0076 }
        goto L_0x004a;
    L_0x0065:
        r0 = move-exception;
        throw r0;	 Catch:{ all -> 0x0067 }
    L_0x0067:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x006a:
        if (r4 == 0) goto L_0x0071;
    L_0x006c:
        if (r2 == 0) goto L_0x0096;
    L_0x006e:
        r4.close();	 Catch:{ Throwable -> 0x0091 }
    L_0x0071:
        throw r0;	 Catch:{ IOException -> 0x0057 }
    L_0x0072:
        r5.close();	 Catch:{ Throwable -> 0x0065, all -> 0x0076 }
        goto L_0x004a;
    L_0x0076:
        r0 = move-exception;
        goto L_0x006a;
    L_0x0078:
        r1 = move-exception;
        throw r1;	 Catch:{ all -> 0x007a }
    L_0x007a:
        r0 = move-exception;
    L_0x007b:
        if (r5 == 0) goto L_0x0082;
    L_0x007d:
        if (r1 == 0) goto L_0x0088;
    L_0x007f:
        r5.close();	 Catch:{ Throwable -> 0x0083, all -> 0x0076 }
    L_0x0082:
        throw r0;	 Catch:{ Throwable -> 0x0065, all -> 0x0076 }
    L_0x0083:
        r5 = move-exception;
        r1.addSuppressed(r5);	 Catch:{ Throwable -> 0x0065, all -> 0x0076 }
        goto L_0x0082;
    L_0x0088:
        r5.close();	 Catch:{ Throwable -> 0x0065, all -> 0x0076 }
        goto L_0x0082;
    L_0x008c:
        r4.close();	 Catch:{ IOException -> 0x0057 }
        goto L_0x0008;
    L_0x0091:
        r1 = move-exception;
        r2.addSuppressed(r1);	 Catch:{ IOException -> 0x0057 }
        goto L_0x0071;
    L_0x0096:
        r4.close();	 Catch:{ IOException -> 0x0057 }
        goto L_0x0071;
    L_0x009a:
        r0 = move-exception;
        r1 = r2;
        goto L_0x007b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.Libraries.h.a(java.util.ArrayList, java.io.File, boolean):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized java.util.ArrayList<java.lang.String> d(java.io.File r9) {
        /*
        r3 = 0;
        r4 = com.chartboost.sdk.Libraries.h.class;
        monitor-enter(r4);
        r1 = new java.util.ArrayList;	 Catch:{ all -> 0x0049 }
        r1.<init>();	 Catch:{ all -> 0x0049 }
        if (r9 == 0) goto L_0x0011;
    L_0x000b:
        r0 = r9.exists();	 Catch:{ all -> 0x0049 }
        if (r0 != 0) goto L_0x0014;
    L_0x0011:
        r0 = r1;
    L_0x0012:
        monitor-exit(r4);
        return r0;
    L_0x0014:
        r5 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x0061, ClassNotFoundException -> 0x0098 }
        r5.<init>(r9);	 Catch:{ IOException -> 0x0061, ClassNotFoundException -> 0x0098 }
        r2 = 0;
        r6 = new java.io.ObjectInputStream;	 Catch:{ Throwable -> 0x007a, all -> 0x0081 }
        r6.<init>(r5);	 Catch:{ Throwable -> 0x007a, all -> 0x0081 }
        r7 = 0;
        r0 = r6.readObject();	 Catch:{ Throwable -> 0x006c, all -> 0x009e }
        r0 = (java.util.ArrayList) r0;	 Catch:{ Throwable -> 0x006c, all -> 0x009e }
        if (r6 == 0) goto L_0x002d;
    L_0x0028:
        if (r3 == 0) goto L_0x0063;
    L_0x002a:
        r6.close();	 Catch:{ Throwable -> 0x004c, all -> 0x0067 }
    L_0x002d:
        if (r5 == 0) goto L_0x0012;
    L_0x002f:
        if (r3 == 0) goto L_0x0087;
    L_0x0031:
        r5.close();	 Catch:{ Throwable -> 0x0035 }
        goto L_0x0012;
    L_0x0035:
        r1 = move-exception;
        r2.addSuppressed(r1);	 Catch:{ IOException -> 0x003a, ClassNotFoundException -> 0x008b }
        goto L_0x0012;
    L_0x003a:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
    L_0x003e:
        r8 = r0;
        r0 = r1;
        r1 = r8;
    L_0x0041:
        r2 = com.chartboost.sdk.Libraries.h.class;
        r3 = "deserialize";
        com.chartboost.sdk.Tracking.a.a(r2, r3, r1);	 Catch:{ all -> 0x0049 }
        goto L_0x0012;
    L_0x0049:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
    L_0x004c:
        r1 = move-exception;
        r7.addSuppressed(r1);	 Catch:{ Throwable -> 0x0051, all -> 0x0067 }
        goto L_0x002d;
    L_0x0051:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
    L_0x0055:
        throw r0;	 Catch:{ all -> 0x0056 }
    L_0x0056:
        r2 = move-exception;
        r3 = r0;
        r0 = r2;
    L_0x0059:
        if (r5 == 0) goto L_0x0060;
    L_0x005b:
        if (r3 == 0) goto L_0x009a;
    L_0x005d:
        r5.close();	 Catch:{ Throwable -> 0x0093 }
    L_0x0060:
        throw r0;	 Catch:{ IOException -> 0x0061, ClassNotFoundException -> 0x0098 }
    L_0x0061:
        r0 = move-exception;
        goto L_0x003e;
    L_0x0063:
        r6.close();	 Catch:{ Throwable -> 0x0051, all -> 0x0067 }
        goto L_0x002d;
    L_0x0067:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x0059;
    L_0x006c:
        r0 = move-exception;
        throw r0;	 Catch:{ all -> 0x006e }
    L_0x006e:
        r2 = move-exception;
        r8 = r2;
        r2 = r0;
        r0 = r8;
    L_0x0072:
        if (r6 == 0) goto L_0x0079;
    L_0x0074:
        if (r2 == 0) goto L_0x0083;
    L_0x0076:
        r6.close();	 Catch:{ Throwable -> 0x007c, all -> 0x0081 }
    L_0x0079:
        throw r0;	 Catch:{ Throwable -> 0x007a, all -> 0x0081 }
    L_0x007a:
        r0 = move-exception;
        goto L_0x0055;
    L_0x007c:
        r6 = move-exception;
        r2.addSuppressed(r6);	 Catch:{ Throwable -> 0x007a, all -> 0x0081 }
        goto L_0x0079;
    L_0x0081:
        r0 = move-exception;
        goto L_0x0059;
    L_0x0083:
        r6.close();	 Catch:{ Throwable -> 0x007a, all -> 0x0081 }
        goto L_0x0079;
    L_0x0087:
        r5.close();	 Catch:{ IOException -> 0x003a, ClassNotFoundException -> 0x008b }
        goto L_0x0012;
    L_0x008b:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
    L_0x008f:
        r8 = r0;
        r0 = r1;
        r1 = r8;
        goto L_0x0041;
    L_0x0093:
        r2 = move-exception;
        r3.addSuppressed(r2);	 Catch:{ IOException -> 0x0061, ClassNotFoundException -> 0x0098 }
        goto L_0x0060;
    L_0x0098:
        r0 = move-exception;
        goto L_0x008f;
    L_0x009a:
        r5.close();	 Catch:{ IOException -> 0x0061, ClassNotFoundException -> 0x0098 }
        goto L_0x0060;
    L_0x009e:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0072;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.Libraries.h.d(java.io.File):java.util.ArrayList<java.lang.String>");
    }

    public synchronized void e(File file) {
        if (file != null) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public synchronized void b(File file, String str) {
        if (file != null) {
            if (!TextUtils.isEmpty(str)) {
                e(c(file, str));
            }
        }
    }

    public static boolean d() {
        String c = f.c();
        if (c != null && c.equals("mounted") && !c.a()) {
            return true;
        }
        CBLogging.e("CBFileCache", "External Storage unavailable");
        return false;
    }

    public boolean b(String str) {
        if (h() == null || str == null) {
            return false;
        }
        return new File(h(), str).exists();
    }

    public boolean c(String str) {
        if (e() == null || str == null) {
            return false;
        }
        return new File(e(), str).exists();
    }

    public File c(File file, String str) {
        if (file == null) {
            return null;
        }
        return new File(file.getPath(), str);
    }

    public File e() {
        return i().g;
    }

    public File d(String str) {
        return new File(i().a, str);
    }

    public boolean e(String str) {
        File file = i().a;
        if (file == null) {
            return false;
        }
        File file2 = new File(file, str);
        if (!file2.exists()) {
            file2.mkdir();
        }
        return true;
    }

    public File f() {
        return c;
    }

    public File g() {
        return d;
    }

    public File h() {
        return i().d;
    }

    public i i() {
        if (this.g && d()) {
            return b;
        }
        return a;
    }

    public static i j() {
        if (d()) {
            return b;
        }
        return a;
    }

    public File k() {
        return i().f;
    }

    public File l() {
        return i().a;
    }

    public static long f(File file) {
        long j = 0;
        if (file != null) {
            try {
                if (file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        int i = 0;
                        while (i < listFiles.length) {
                            long f = f(listFiles[i]) + j;
                            i++;
                            j = f;
                        }
                    }
                    return j;
                }
            } catch (Exception e) {
                com.chartboost.sdk.Tracking.a.a(h.class, "getFolderSize", e);
            }
        }
        if (file != null) {
            j = file.length();
        }
        return j;
    }

    public static com.chartboost.sdk.Libraries.e.a m() {
        com.chartboost.sdk.Libraries.e.a a = com.chartboost.sdk.Libraries.e.a.a();
        a.a(".chartboost-external-folder-size", Long.valueOf(f(b.a)));
        a.a(".chartboost-internal-folder-size", Long.valueOf(f(a.a)));
        File file = j().a;
        String[] list = file.list();
        if (list != null && list.length > 0) {
            for (String file2 : list) {
                File file3 = new File(file, file2);
                com.chartboost.sdk.Libraries.e.a a2 = com.chartboost.sdk.Libraries.e.a.a();
                a2.a(file3.getName() + "-size", Long.valueOf(f(file3)));
                String[] list2 = file3.list();
                if (list2 != null) {
                    a2.a("count", Integer.valueOf(list2.length));
                }
                a.a(file3.getName(), a2);
            }
        }
        return a;
    }

    public static boolean a(File file, int i) {
        if (file == null || !file.exists()) {
            return false;
        }
        Calendar instance = Calendar.getInstance();
        instance.add(6, -i);
        Date date = new Date(file.lastModified());
        CBLogging.a("CBFileCache", "### File last modified" + date.toString());
        if (!date.before(instance.getTime())) {
            return false;
        }
        CBLogging.a("CBFileCache", "### File is expired and is past " + i + " days");
        return true;
    }

    public void n() {
        File file = new File(i().a, "asset_log");
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file2 : listFiles) {
                    try {
                        CBLogging.a("CBFileCache", "Copying the template meta data files from asset_log folder to template folder");
                        a(file2, k());
                    } catch (Exception e) {
                        com.chartboost.sdk.Tracking.a.a(getClass(), "copyAssetLogToTemplateDirectory", e);
                    }
                }
            }
        }
    }

    private void a(File file, File file2) throws IOException {
        int i = 0;
        if (!file.isDirectory()) {
            File parentFile = file2.getParentFile();
            if (parentFile == null || parentFile.exists() || parentFile.mkdirs()) {
                InputStream fileInputStream = new FileInputStream(file);
                OutputStream fileOutputStream = new FileOutputStream(file2);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read > 0) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        fileInputStream.close();
                        fileOutputStream.close();
                        return;
                    }
                }
            }
            throw new IOException("Cannot create dir " + parentFile.getAbsolutePath());
        } else if (file2.exists() || file2.mkdirs()) {
            String[] list = file.list();
            int length = list.length;
            while (i < length) {
                String str = list[i];
                a(new File(file, str), new File(file2, str));
                i++;
            }
        } else {
            throw new IOException("Cannot create dir " + file2.getAbsolutePath());
        }
    }

    public static void g(File file) {
        Throwable th;
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            Throwable th2 = null;
            try {
                randomAccessFile.seek(0);
                int read = randomAccessFile.read();
                randomAccessFile.seek(0);
                randomAccessFile.write(read);
                if (randomAccessFile == null) {
                    return;
                }
                if (th2 != null) {
                    try {
                        randomAccessFile.close();
                        return;
                    } catch (Throwable th3) {
                        th2.addSuppressed(th3);
                        return;
                    }
                }
                randomAccessFile.close();
                return;
            } catch (Throwable th22) {
                Throwable th4 = th22;
                th22 = th3;
                th3 = th4;
            }
            if (randomAccessFile != null) {
                if (th22 != null) {
                    try {
                        randomAccessFile.close();
                    } catch (Throwable th5) {
                        th22.addSuppressed(th5);
                    }
                } else {
                    randomAccessFile.close();
                }
            }
            throw th3;
            throw th3;
        } catch (Throwable th32) {
            CBLogging.b("CBFileCache", "File not found when attempting to touch", th32);
        } catch (Throwable th322) {
            CBLogging.b("CBFileCache", "IOException when attempting to touch file", th322);
        }
    }
}
