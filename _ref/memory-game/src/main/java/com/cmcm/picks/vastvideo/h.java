package com.cmcm.picks.vastvideo;

import android.content.Context;
import android.text.TextUtils;
import com.cmcm.adsdk.Const;
import com.cmcm.utils.ThreadHelper;
import com.cmcm.utils.g;
import com.yalantis.ucrop.util.FileUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

/* compiled from: VideoDownLoadTask */
public class h implements Runnable {
    private static int b = 100;
    private static int c = 100;
    boolean a;
    private String d;
    private File e;
    private long f;
    private String g;
    private Context h;
    private b i;
    private String j;
    private a k = null;

    /* compiled from: VideoDownLoadTask */
    public interface b {
        void a(String str);

        void b(String str);
    }

    /* compiled from: VideoDownLoadTask */
    class a extends TimerTask {
        final /* synthetic */ h a;
        private FileOutputStream b;

        public a(h hVar, FileOutputStream fileOutputStream) {
            this.a = hVar;
            this.b = fileOutputStream;
        }

        public void run() {
            this.a.d("time out error");
            if (this.b != null) {
                try {
                    this.b.close();
                } catch (Exception e) {
                    if (g.a) {
                        e.printStackTrace();
                    }
                    this.a.d("time out error");
                }
            }
        }
    }

    public h(Context context, String str, b bVar) {
        this.h = context;
        this.i = bVar;
        this.d = str;
    }

    public String a(String str) {
        String file;
        Exception exception;
        try {
            file = new URL(str).getFile();
            try {
                if (!TextUtils.isEmpty(file)) {
                    String replace = file.replace("/", "");
                    try {
                        file = replace.replace(FileUtils.HIDDEN_PREFIX, "");
                    } catch (Exception e) {
                        Exception exception2 = e;
                        file = replace;
                        exception = exception2;
                        e(null);
                        if (g.a) {
                            exception.printStackTrace();
                        }
                        return file;
                    }
                }
            } catch (Exception e2) {
                exception = e2;
                e(null);
                if (g.a) {
                    exception.printStackTrace();
                }
                return file;
            }
        } catch (Exception e3) {
            exception = e3;
            file = null;
            e(null);
            if (g.a) {
                exception.printStackTrace();
            }
            return file;
        }
        return file;
    }

    private File a() {
        try {
            if (f.a() && f.b() > b) {
                File file = new File(this.h.getExternalFilesDir(null).getPath() + "/" + "VastVideo");
                if (file.exists()) {
                    return file;
                }
                file.mkdir();
                return file;
            } else if (f.c() > ((long) c)) {
                return this.h.getDir("VastVideo", 1);
            } else {
                return null;
            }
        } catch (Exception e) {
            if (g.a) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private File b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File a = a();
        if (a == null) {
            return null;
        }
        for (File a2 : a2.listFiles()) {
            if (!a2.isDirectory() && a2.getName().startsWith(str)) {
                return a2;
            }
        }
        return null;
    }

    public void run() {
        if (TextUtils.isEmpty(this.d)) {
            d("url is null");
            return;
        }
        this.j = this.d.substring(this.d.lastIndexOf(FileUtils.HIDDEN_PREFIX) + 1).trim();
        if (TextUtils.isEmpty(this.j)) {
            d("unknown video type");
        } else if ("mp4".equals(this.j) || "3gp".equals(this.j)) {
            this.g = a(this.d);
            File b = b(this.g);
            if (b != null) {
                e(b.getAbsolutePath());
                return;
            }
            c(this.g);
            if (this.e != null) {
                try {
                    InputStream b2 = b();
                    if (b2 == null) {
                        d("get input stream fail");
                        c();
                        return;
                    } else if (b2 != null) {
                        Object a = a(b2);
                        if (TextUtils.isEmpty(a)) {
                            d("write file fail");
                            return;
                        } else {
                            e(a);
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Exception e) {
                    if (g.a) {
                        e.printStackTrace();
                    }
                    d(e.toString());
                    c();
                    return;
                }
            }
            d("create file fail");
        } else {
            g.a(Const.TAG, "vast:video type is not mp4 or 3gp, url =" + this.d);
            d("vast:video type is not mp4 or 3gp");
        }
    }

    private InputStream b() throws Exception {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.d).openConnection();
        int contentLength = httpURLConnection.getContentLength();
        httpURLConnection.setReadTimeout(300000);
        httpURLConnection.setConnectTimeout(300000);
        if (httpURLConnection.getResponseCode() != 200) {
            return null;
        }
        if (contentLength > 104857600) {
            return null;
        }
        this.f = (long) contentLength;
        return new BufferedInputStream(httpURLConnection.getInputStream());
    }

    private void c(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                File a = a();
                if (a == null) {
                    return;
                }
                if ("mp4".equals(this.j)) {
                    this.e = File.createTempFile(str, ".mp4", a);
                } else if ("3gp".equals(this.j)) {
                    this.e = File.createTempFile(str, ".3gp", a);
                }
            }
        } catch (Exception e) {
            if (g.a) {
                e.printStackTrace();
            }
            e("create file fail");
        }
    }

    private void d(final String str) {
        if (!this.a) {
            c();
            if (this.i != null) {
                ThreadHelper.postOnUiThread(new Runnable(this) {
                    final /* synthetic */ h b;

                    public void run() {
                        this.b.i.b(str);
                    }
                });
            }
            this.a = true;
        }
    }

    private void e(final String str) {
        ThreadHelper.postOnUiThread(new Runnable(this) {
            final /* synthetic */ h b;

            public void run() {
                if (this.b.i != null) {
                    this.b.i.a(str);
                }
            }
        });
    }

    private String a(InputStream inputStream) {
        FileOutputStream fileOutputStream;
        Exception e;
        Throwable th;
        if (inputStream == null || !this.e.exists()) {
            return null;
        }
        try {
            fileOutputStream = new FileOutputStream(this.e);
            try {
                String absolutePath;
                a(fileOutputStream);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                if (this.e != null) {
                    absolutePath = this.e.getAbsolutePath();
                } else {
                    absolutePath = null;
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e2) {
                        if (g.a) {
                            e2.printStackTrace();
                        }
                        c();
                        absolutePath = null;
                    }
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                d();
                if (this.e == null || this.e.length() == this.f) {
                    return absolutePath;
                }
                c();
                return null;
            } catch (Exception e3) {
                e2 = e3;
                try {
                    if (g.a) {
                        e2.printStackTrace();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (Exception e22) {
                    if (g.a) {
                        e22.printStackTrace();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e4) {
                            if (g.a) {
                                e4.printStackTrace();
                            }
                            c();
                            d();
                            c();
                            throw th;
                        }
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    d();
                    if (!(this.e == null || this.e.length() == this.f)) {
                        c();
                    }
                    throw th;
                }
                c();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e222) {
                        if (g.a) {
                            e222.printStackTrace();
                        }
                        c();
                        d();
                        return this.e != null ? null : null;
                    }
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                d();
                if (this.e != null) {
                }
            }
        } catch (Exception e5) {
            e222 = e5;
            fileOutputStream = null;
            if (g.a) {
                e222.printStackTrace();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            c();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            d();
            if (this.e != null && this.e.length() != this.f) {
                c();
                return null;
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            d();
            c();
            throw th;
        }
    }

    private void c() {
        if (this.e != null) {
            this.e.delete();
        }
    }

    private void a(FileOutputStream fileOutputStream) {
        g.a(VastAd.TAG, "start down load video time out task");
        this.k = new a(this, fileOutputStream);
        new Timer().schedule(this.k, 300000);
    }

    private void d() {
        try {
            if (this.k != null) {
                this.k.cancel();
            }
        } catch (Exception e) {
            if (g.a) {
                e.printStackTrace();
            }
        }
    }
}
