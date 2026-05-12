package com.appodeal.ads.utils;

import android.content.Context;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import com.appodeal.ads.Appodeal;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

public class k extends AsyncTask<a, Void, Uri> {
    private a a;
    private String b;
    private File c;

    public interface a {
        void a();

        void a(Uri uri);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((a[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((Uri) obj);
    }

    public k(Context context, a aVar, String str) {
        if (context == null || str == null || !c.a(context) || !g.d()) {
            aVar.a();
            return;
        }
        this.a = aVar;
        this.b = str;
        File externalFilesDir = context.getExternalFilesDir(null);
        if (externalFilesDir != null) {
            this.c = new File(externalFilesDir.getPath() + "/native_video/");
            if (!this.c.exists()) {
                this.c.mkdirs();
                return;
            }
            return;
        }
        aVar.a();
    }

    protected Uri a(a... aVarArr) {
        InputStream inputStream;
        Throwable e;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.b).openConnection();
            httpURLConnection.setConnectTimeout(20000);
            httpURLConnection.setReadTimeout(20000);
            inputStream = httpURLConnection.getInputStream();
            try {
                File file = new File(this.c, "temp" + System.currentTimeMillis());
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                long contentLength = (long) httpURLConnection.getContentLength();
                long j = 0;
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                    j += (long) read;
                }
                fileOutputStream.close();
                String a = a(this.b);
                if (contentLength == j) {
                    file.renameTo(new File(this.c, a));
                }
                File file2 = new File(this.c, a);
                if (ThumbnailUtils.createVideoThumbnail(file2.getPath(), 1) != null) {
                    Uri fromFile = Uri.fromFile(file2);
                    a(inputStream);
                    return fromFile;
                }
                a(inputStream);
                return null;
            } catch (Exception e2) {
                e = e2;
                try {
                    Appodeal.a(e);
                    a(inputStream);
                    return null;
                } catch (Throwable th) {
                    e = th;
                    a(inputStream);
                    throw e;
                }
            }
        } catch (Exception e3) {
            e = e3;
            inputStream = null;
            Appodeal.a(e);
            a(inputStream);
            return null;
        } catch (Throwable th2) {
            e = th2;
            inputStream = null;
            a(inputStream);
            throw e;
        }
    }

    protected void a(Uri uri) {
        if (uri != null) {
            this.a.a(uri);
        } else {
            this.a.a();
        }
    }

    private void a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Throwable e) {
                Appodeal.a(e);
            }
        }
    }

    public String a(String str) {
        return new BigInteger(a(str.getBytes())).abs().toString(36);
    }

    private byte[] a(byte[] bArr) {
        byte[] bArr2 = null;
        try {
            MessageDigest instance = MessageDigest.getInstance(CommonUtils.MD5_INSTANCE);
            instance.update(bArr);
            bArr2 = instance.digest();
        } catch (Throwable e) {
            Appodeal.a(e);
        }
        return bArr2;
    }
}
