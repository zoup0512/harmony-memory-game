package com.appodeal.ads.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.util.Pair;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;
import com.cube.memorygames.SharingDialog;
import com.mopub.common.Constants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;

public class j extends AsyncTask<a, Void, Bitmap> {
    private final a a;
    private final String b;
    private final boolean c;

    public interface a {
        void a(Bitmap bitmap);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((a[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((Bitmap) obj);
    }

    public j(a aVar, String str, boolean z) {
        this.a = aVar;
        this.b = str.replace(" ", "%20");
        this.c = z;
    }

    protected Bitmap a(a... aVarArr) {
        InputStream a;
        Throwable e;
        Throwable th;
        Bitmap bitmap = null;
        try {
            a = a();
            try {
                bitmap = a(a(a));
                b(a);
            } catch (Exception e2) {
                e = e2;
                try {
                    Appodeal.a(e);
                    b(a);
                    return bitmap;
                } catch (Throwable th2) {
                    th = th2;
                    b(a);
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            a = bitmap;
            Appodeal.a(e);
            b(a);
            return bitmap;
        } catch (Throwable e4) {
            a = bitmap;
            th = e4;
            b(a);
            throw th;
        }
        return bitmap;
    }

    protected void a(Bitmap bitmap) {
        this.a.a(bitmap);
    }

    private InputStream a() {
        URLConnection openConnection;
        try {
            openConnection = new URL(this.b).openConnection();
            a(openConnection);
            openConnection.setConnectTimeout(20000);
            openConnection.setReadTimeout(20000);
            openConnection.connect();
            return openConnection.getInputStream();
        } catch (IOException e) {
            Appodeal.a(e.getMessage());
            Builder buildUpon = Uri.parse(this.b).buildUpon();
            buildUpon.scheme(Constants.HTTP);
            openConnection = new URL(buildUpon.build().toString()).openConnection();
            openConnection.setConnectTimeout(20000);
            openConnection.setReadTimeout(20000);
            openConnection.connect();
            return openConnection.getInputStream();
        }
    }

    private void a(URLConnection uRLConnection) {
        try {
            if (uRLConnection instanceof HttpsURLConnection) {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) uRLConnection;
                httpsURLConnection.setSSLSocketFactory(new o(httpsURLConnection.getSSLSocketFactory()));
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    private byte[] a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(inputStream.available());
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    private Bitmap a(byte[] bArr) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        if (!a(options)) {
            return null;
        }
        int b = b();
        options.inSampleSize = a(options, b, a(b));
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
    }

    private void b(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Throwable e) {
                Appodeal.a(e);
            }
        }
    }

    private boolean a(Options options) {
        if (this.c) {
            if (((float) options.outWidth) / ((float) options.outHeight) < 1.5f) {
                return false;
            }
        }
        return true;
    }

    private int b() {
        try {
            Pair f = an.f(Appodeal.b);
            int min = Math.min(((Integer) f.first).intValue(), ((Integer) f.second).intValue());
            if (min >= 1200) {
                return 1200;
            }
            return min;
        } catch (Exception e) {
            return 1200;
        }
    }

    private int a(int i) {
        int i2;
        if (this.c) {
            i2 = (int) (((float) i) / 1.5f);
        } else {
            i2 = i;
        }
        if (i2 > SharingDialog.DOLLAR1_COINS) {
            return SharingDialog.DOLLAR1_COINS;
        }
        return i2;
    }

    private int a(Options options, int i, int i2) {
        int i3 = options.outWidth;
        int i4 = options.outHeight;
        int i5 = 1;
        while (true) {
            if (i3 / i5 <= i && i4 / i5 <= i2) {
                return i5;
            }
            i5 *= 2;
        }
    }
}
