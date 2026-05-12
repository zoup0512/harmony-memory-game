package com.yandex.metrica.impl;

import android.content.Context;
import android.util.Base64;
import com.yandex.metrica.YandexMetrica;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class r {
    public static String a(InputStream inputStream) throws IOException {
        Reader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        Writer stringWriter = new StringWriter();
        a(inputStreamReader, stringWriter);
        return stringWriter.toString();
    }

    public static String a(String str) throws IOException {
        Closeable fileInputStream;
        Throwable th;
        try {
            fileInputStream = new FileInputStream(str);
            try {
                String a = a((InputStream) fileInputStream);
                bg.a(fileInputStream);
                return a;
            } catch (Throwable th2) {
                th = th2;
                bg.a(fileInputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            bg.a(fileInputStream);
            throw th;
        }
    }

    public static int a(Reader reader, Writer writer) throws IOException {
        char[] cArr = new char[4096];
        int i = 0;
        while (true) {
            int read = reader.read(cArr, 0, 4096);
            if (-1 == read) {
                return i;
            }
            writer.write(cArr, 0, read);
            i += read;
        }
    }

    public static String b(String str) {
        Closeable byteArrayOutputStream;
        Closeable gZIPOutputStream;
        Throwable th;
        Object obj;
        String str2 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    gZIPOutputStream.write(str.getBytes("UTF-8"));
                    gZIPOutputStream.finish();
                    str2 = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
                    bg.a(gZIPOutputStream);
                    bg.a(byteArrayOutputStream);
                } catch (Exception e) {
                    bg.a(gZIPOutputStream);
                    bg.a(byteArrayOutputStream);
                    return str2;
                } catch (Throwable th2) {
                    th = th2;
                    bg.a(gZIPOutputStream);
                    bg.a(byteArrayOutputStream);
                    throw th;
                }
            } catch (Exception e2) {
                obj = str2;
                bg.a(gZIPOutputStream);
                bg.a(byteArrayOutputStream);
                return str2;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                obj = str2;
                th = th4;
                bg.a(gZIPOutputStream);
                bg.a(byteArrayOutputStream);
                throw th;
            }
        } catch (Exception e3) {
            gZIPOutputStream = str2;
            byteArrayOutputStream = str2;
            bg.a(gZIPOutputStream);
            bg.a(byteArrayOutputStream);
            return str2;
        } catch (Throwable th32) {
            byteArrayOutputStream = str2;
            String str3 = str2;
            th = th32;
            gZIPOutputStream = str3;
            bg.a(gZIPOutputStream);
            bg.a(byteArrayOutputStream);
            throw th;
        }
        return str2;
    }

    public static String c(String str) {
        Closeable byteArrayInputStream;
        Closeable gZIPInputStream;
        Object obj;
        Throwable th;
        String str2 = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(Base64.decode(str, 0));
            try {
                gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            } catch (Exception e) {
                obj = str2;
                bg.a(byteArrayInputStream);
                bg.a(gZIPInputStream);
                return str2;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                obj = str2;
                th = th3;
                bg.a(byteArrayInputStream);
                bg.a(gZIPInputStream);
                throw th;
            }
            try {
                str2 = a((InputStream) gZIPInputStream);
                bg.a(byteArrayInputStream);
                bg.a(gZIPInputStream);
            } catch (Exception e2) {
                bg.a(byteArrayInputStream);
                bg.a(gZIPInputStream);
                return str2;
            } catch (Throwable th4) {
                th = th4;
                bg.a(byteArrayInputStream);
                bg.a(gZIPInputStream);
                throw th;
            }
        } catch (Exception e3) {
            gZIPInputStream = str2;
            byteArrayInputStream = str2;
            bg.a(byteArrayInputStream);
            bg.a(gZIPInputStream);
            return str2;
        } catch (Throwable th22) {
            byteArrayInputStream = str2;
            String str3 = str2;
            th = th22;
            gZIPInputStream = str3;
            bg.a(byteArrayInputStream);
            bg.a(gZIPInputStream);
            throw th;
        }
        return str2;
    }

    public static byte[] b(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        byte[] bArr = new byte[8192];
        Closeable byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (-1 == read) {
                    break;
                } else if (read > 0) {
                    byteArrayOutputStream.write(bArr, 0, read);
                }
            } finally {
                bg.a(byteArrayOutputStream);
            }
        }
        bArr = byteArrayOutputStream.toByteArray();
        return bArr;
    }

    public static String a(Context context, File file) {
        byte[] b = b(context, file);
        try {
            return new String(b, "UTF-8");
        } catch (Throwable e) {
            Throwable th = e;
            String str = new String(b);
            YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportError("read_share_file_with_unsupported_encoding", th);
            return str;
        }
    }

    public static byte[] b(Context context, File file) {
        Closeable randomAccessFile;
        FileLock lock;
        FileLock fileLock;
        Closeable closeable;
        Throwable th;
        Throwable th2;
        byte[] bArr = null;
        try {
            FileChannel channel;
            randomAccessFile = new RandomAccessFile(file, "r");
            try {
                channel = randomAccessFile.getChannel();
                lock = channel.lock(0, Long.MAX_VALUE, true);
            } catch (IOException e) {
                fileLock = bArr;
                closeable = randomAccessFile;
                file.getAbsolutePath();
                a(fileLock);
                bg.a(closeable);
                return bArr;
            } catch (SecurityException e2) {
                lock = bArr;
                file.getAbsolutePath();
                a(lock);
                bg.a(randomAccessFile);
                return bArr;
            } catch (Throwable th3) {
                lock = bArr;
                th2 = th3;
                file.getAbsolutePath();
                a(lock);
                bg.a(randomAccessFile);
                throw th2;
            }
            try {
                ByteBuffer allocate = ByteBuffer.allocate((int) file.length());
                channel.read(allocate);
                allocate.flip();
                bArr = allocate.array();
                file.getAbsolutePath();
                a(lock);
                bg.a(randomAccessFile);
            } catch (IOException e3) {
                fileLock = lock;
                closeable = randomAccessFile;
                file.getAbsolutePath();
                a(fileLock);
                bg.a(closeable);
                return bArr;
            } catch (SecurityException e4) {
                file.getAbsolutePath();
                a(lock);
                bg.a(randomAccessFile);
                return bArr;
            } catch (Throwable th4) {
                th3 = th4;
                YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportError("error_during_file_reading", th3);
                file.getAbsolutePath();
                a(lock);
                bg.a(randomAccessFile);
                return bArr;
            }
        } catch (IOException e5) {
            fileLock = bArr;
            closeable = bArr;
            file.getAbsolutePath();
            a(fileLock);
            bg.a(closeable);
            return bArr;
        } catch (SecurityException e6) {
            lock = bArr;
            randomAccessFile = bArr;
            file.getAbsolutePath();
            a(lock);
            bg.a(randomAccessFile);
            return bArr;
        } catch (Throwable th32) {
            lock = bArr;
            randomAccessFile = bArr;
            th2 = th32;
            file.getAbsolutePath();
            a(lock);
            bg.a(randomAccessFile);
            throw th2;
        }
        return bArr;
    }

    static void a(FileLock fileLock) {
        if (fileLock != null && fileLock.isValid()) {
            try {
                fileLock.release();
            } catch (IOException e) {
            }
        }
    }

    public static void b(Context context, String str, String str2) {
        File file = new File(context.getNoBackupFilesDir(), str);
        try {
            a(str2, new FileOutputStream(file));
            c(context, file);
        } catch (FileNotFoundException e) {
        }
    }

    public static void c(final Context context, final File file) {
        if (file.exists()) {
            file.setReadable(true, false);
            if (bg.a(24)) {
                new File(context.getApplicationInfo().dataDir).setExecutable(true, false);
                return;
            }
            return;
        }
        YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("make_non_existed_world_readable", new HashMap<String, Object>() {
        });
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(java.lang.String r5, java.io.FileOutputStream r6) {
        /*
        r0 = 0;
        r1 = r6.getChannel();	 Catch:{ IOException -> 0x0028, all -> 0x0030 }
        r0 = r1.lock();	 Catch:{ IOException -> 0x0028, all -> 0x0030 }
        r2 = "UTF-8";
        r2 = r5.getBytes(r2);	 Catch:{ IOException -> 0x0028, all -> 0x003b }
        r3 = r2.length;	 Catch:{ IOException -> 0x0028, all -> 0x003b }
        r3 = java.nio.ByteBuffer.allocate(r3);	 Catch:{ IOException -> 0x0028, all -> 0x003b }
        r3.put(r2);	 Catch:{ IOException -> 0x0028, all -> 0x003b }
        r3.flip();	 Catch:{ IOException -> 0x0028, all -> 0x003b }
        r1.write(r3);	 Catch:{ IOException -> 0x0028, all -> 0x003b }
        r2 = 1;
        r1.force(r2);	 Catch:{ IOException -> 0x0028, all -> 0x003b }
        a(r0);
        com.yandex.metrica.impl.bg.a(r6);
    L_0x0027:
        return;
    L_0x0028:
        r1 = move-exception;
        a(r0);
        com.yandex.metrica.impl.bg.a(r6);
        goto L_0x0027;
    L_0x0030:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
    L_0x0034:
        a(r1);
        com.yandex.metrica.impl.bg.a(r6);
        throw r0;
    L_0x003b:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
        goto L_0x0034;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.r.a(java.lang.String, java.io.FileOutputStream):void");
    }

    public static void a(Context context, String str, String str2) {
        try {
            if (bg.a(24)) {
                a(str2, context.openFileOutput(str, 0));
                c(context, context.getFileStreamPath(str));
                return;
            }
            a(str2, context.openFileOutput(str, 1));
        } catch (FileNotFoundException e) {
        }
    }
}
