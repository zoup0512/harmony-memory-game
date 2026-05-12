package com.my.target.core.utils;

import android.content.Context;
import com.my.target.Tracer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/* compiled from: DiskFileCache */
public final class d {
    private static d a;
    private static final FilenameFilter c = new FilenameFilter() {
        public final boolean accept(File file, String str) {
            return str.startsWith("mytrgsc_");
        }
    };
    private File b;

    private d(File file) {
        this.b = file;
    }

    public static d a(Context context) {
        if (a != null) {
            return a;
        }
        File cacheDir = context.getCacheDir();
        if (cacheDir == null) {
            return null;
        }
        boolean z = cacheDir.exists() || cacheDir.mkdir();
        if (z) {
            File file = new File(cacheDir, "mytargetcache");
            if (!file.exists()) {
                z = file.mkdir();
            }
            if (z) {
                return new d(file);
            }
            Tracer.i("Can't create myTarget cache dir");
            return null;
        }
        Tracer.i("Can't create cache dir");
        return null;
    }

    private synchronized void a(long j) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            if (currentTimeMillis - this.b.lastModified() > j) {
                for (File file : this.b.listFiles(c)) {
                    if (file.isFile() && currentTimeMillis - file.lastModified() > j) {
                        Tracer.d("Remove expired cache item: " + file.getPath());
                        file.delete();
                    }
                }
                this.b.setLastModified(currentTimeMillis);
            }
        } catch (Exception e) {
            Tracer.i("FileCache exception: " + e);
        }
    }

    public final synchronized boolean a(String str, String str2, long j) {
        OutputStreamWriter outputStreamWriter;
        Object e;
        Throwable th;
        boolean z = false;
        synchronized (this) {
            a(j);
            if (this.b.isDirectory() && this.b.canWrite()) {
                try {
                    File a = a(str);
                    outputStreamWriter = new OutputStreamWriter(new FileOutputStream(a), Charset.forName("UTF-8").newEncoder());
                    try {
                        outputStreamWriter.write(str2);
                        if (!a.setLastModified(System.currentTimeMillis())) {
                            Tracer.d("NOTE: Cache last modified date is not updated for " + str);
                        }
                        try {
                            outputStreamWriter.close();
                        } catch (IOException e2) {
                        }
                        Tracer.d("DiskFileCache: key: " + str + " bytes: " + str2.length());
                        z = true;
                    } catch (Exception e3) {
                        e = e3;
                        try {
                            Tracer.i("FileCache exception: " + e);
                            if (outputStreamWriter != null) {
                                try {
                                    outputStreamWriter.close();
                                } catch (IOException e4) {
                                }
                            }
                            return z;
                        } catch (Throwable th2) {
                            th = th2;
                            if (outputStreamWriter != null) {
                                try {
                                    outputStreamWriter.close();
                                } catch (IOException e5) {
                                }
                            }
                            throw th;
                        }
                    }
                } catch (Exception e6) {
                    e = e6;
                    outputStreamWriter = null;
                    Tracer.i("FileCache exception: " + e);
                    if (outputStreamWriter != null) {
                        outputStreamWriter.close();
                    }
                    return z;
                } catch (Throwable th3) {
                    th = th3;
                    outputStreamWriter = null;
                    if (outputStreamWriter != null) {
                        outputStreamWriter.close();
                    }
                    throw th;
                }
            } else {
                Tracer.i("Unable to use cache dir");
            }
        }
        return z;
    }

    public final synchronized String a(String str, long j) {
        FileInputStream fileInputStream;
        Object e;
        Throwable th;
        String str2 = null;
        synchronized (this) {
            a(j);
            try {
                File a = a(str);
                if (a.exists()) {
                    Tracer.d("Get file from disk cache: " + a.getPath());
                    fileInputStream = new FileInputStream(a);
                    try {
                        StringBuilder stringBuilder = new StringBuilder();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
                        char[] cArr = new char[1024];
                        while (true) {
                            int read = bufferedReader.read(cArr);
                            if (read == -1) {
                                break;
                            }
                            stringBuilder.append(new String(cArr, 0, read));
                        }
                        if (stringBuilder.length() == 0) {
                            Tracer.d("Error: Cache file is empty");
                        } else {
                            str2 = stringBuilder.toString();
                        }
                        try {
                            fileInputStream.close();
                        } catch (IOException e2) {
                        }
                    } catch (Exception e3) {
                        e = e3;
                        try {
                            Tracer.i("FileCache exception: " + e);
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e4) {
                                }
                            }
                            return str2;
                        } catch (Throwable th2) {
                            th = th2;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e5) {
                                }
                            }
                            throw th;
                        }
                    }
                }
            } catch (Exception e6) {
                e = e6;
                Object obj = str2;
                Tracer.i("FileCache exception: " + e);
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return str2;
            } catch (Throwable th3) {
                fileInputStream = str2;
                th = th3;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        }
        return str2;
    }

    private File a(String str) {
        return new File(this.b.getAbsolutePath() + File.separator + ("mytrgsc_" + f.a(str)));
    }
}
