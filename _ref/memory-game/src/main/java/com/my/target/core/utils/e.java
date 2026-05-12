package com.my.target.core.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import com.my.target.Tracer;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Comparator;

/* compiled from: DiskMediaCache */
public final class e {
    private static e a;
    private static final FilenameFilter b = new FilenameFilter() {
        public final boolean accept(File file, String str) {
            return str.startsWith("mytrg_");
        }
    };
    private static final FilenameFilter c = new FilenameFilter() {
        public final boolean accept(File file, String str) {
            return str.endsWith(".mp4");
        }
    };
    private File d;

    public static e a(Context context) {
        if (a != null) {
            return a;
        }
        File cacheDir = context.getCacheDir();
        if (!(cacheDir == null || cacheDir.exists())) {
            cacheDir.mkdir();
        }
        File file = new File(cacheDir, "mytargetcache");
        if (!file.exists()) {
            file.mkdir();
        }
        if (!file.isDirectory() || !file.canWrite()) {
            return null;
        }
        e eVar = new e(file);
        a = eVar;
        return eVar;
    }

    private e(File file) {
        this.d = file;
    }

    private synchronized void a() {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            int length;
            if (this.d.lastModified() + 604800000 < currentTimeMillis) {
                for (File file : this.d.listFiles(b)) {
                    if (file.isFile() && file.lastModified() + 604800000 < currentTimeMillis) {
                        Tracer.d("remove expired file: " + file.getPath());
                        file.delete();
                    }
                }
                this.d.setLastModified(currentTimeMillis);
            }
            File[] listFiles = this.d.listFiles(c);
            if (listFiles.length > 10) {
                Arrays.sort(listFiles, new Comparator<File>(this) {
                    final /* synthetic */ e a;

                    {
                        this.a = r1;
                    }

                    public final /* synthetic */ int compare(Object obj, Object obj2) {
                        return Long.valueOf(((File) obj2).lastModified()).compareTo(Long.valueOf(((File) obj).lastModified()));
                    }
                });
                for (length = listFiles.length - 1; length >= 10; length--) {
                    Tracer.d("remove rendurant video: " + listFiles[length].getPath());
                    listFiles[length].delete();
                }
            }
        } catch (Exception e) {
            Tracer.i("DiskMediaCache exception: " + e);
        }
        return;
    }

    public final synchronized File a(InputStream inputStream, String str) {
        File b;
        a();
        b = b(str, ".img");
        Tracer.d("Save image to disk cache: " + b.getPath());
        try {
            a(inputStream, new FileOutputStream(b));
        } catch (Exception e) {
            Tracer.i("DiskMediaCache exception: " + e);
            b = null;
        }
        return b;
    }

    public final synchronized File b(InputStream inputStream, String str) {
        File b;
        a();
        b = b(str, ".mp4");
        Tracer.d("Save video to disk cache: " + b.getPath());
        try {
            a(inputStream, new FileOutputStream(b));
        } catch (Exception e) {
            Tracer.i("DiskMediaCache exception: " + e);
            b = null;
        }
        return b;
    }

    public final synchronized String a(String str, String str2) {
        String absolutePath;
        a();
        File b = b(str, str2);
        if (b.exists()) {
            Tracer.d("Get media from disk cache: " + b.getPath());
            try {
                absolutePath = b.getAbsolutePath();
            } catch (Exception e) {
                Tracer.i("DiskMediaCache exception: " + e);
            }
        }
        absolutePath = null;
        return absolutePath;
    }

    public final synchronized Bitmap a(String str) {
        Bitmap decodeFile;
        a();
        File b = b(str, ".img");
        if (b.exists()) {
            Tracer.d("Get image from disk cache: " + b.getPath());
            try {
                decodeFile = BitmapFactory.decodeFile(b.getAbsolutePath());
            } catch (Object e) {
                System.gc();
                Tracer.i("DiskMediaCache OOME, trying once again");
                try {
                    Options options = new Options();
                    options.inSampleSize = 2;
                    decodeFile = BitmapFactory.decodeFile(b.getAbsolutePath(), options);
                } catch (OutOfMemoryError e2) {
                    Tracer.i("DiskMediaCache OOME, called twice: " + e);
                }
            } catch (Exception e3) {
                Tracer.i("DiskMediaCache exception: " + e3);
            }
        }
        decodeFile = null;
        return decodeFile;
    }

    private static int a(InputStream inputStream, OutputStream outputStream) throws Exception {
        int i = 0;
        byte[] bArr = new byte[8192];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 8192);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 8192);
        while (true) {
            try {
                int read = bufferedInputStream.read(bArr, 0, 8192);
                if (read == -1) {
                    break;
                }
                bufferedOutputStream.write(bArr, 0, read);
                i += read;
            } finally {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    Tracer.d(e.getMessage());
                }
                try {
                    bufferedInputStream.close();
                } catch (IOException e2) {
                    Tracer.d(e2.getMessage());
                }
            }
        }
        bufferedOutputStream.flush();
        try {
            bufferedInputStream.close();
        } catch (IOException e22) {
            Tracer.d(e22.getMessage());
        }
        return i;
    }

    private File b(String str, String str2) {
        return new File(this.d.getAbsolutePath() + File.separator + ("mytrg_" + f.a(str) + str2));
    }
}
