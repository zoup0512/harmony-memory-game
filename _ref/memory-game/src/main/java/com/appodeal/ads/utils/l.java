package com.appodeal.ads.utils;

import android.content.Context;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.util.Pair;
import com.appodeal.ads.Appodeal;
import com.mopub.common.Constants;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import javax.net.ssl.HttpsURLConnection;
import org.nexage.sourcekit.util.DefaultMediaPicker;
import org.nexage.sourcekit.vast.model.VASTModel;
import org.nexage.sourcekit.vast.processor.VASTProcessor;

public class l extends AsyncTask<a, Void, Pair<Uri, VASTModel>> {
    String a = "video/.*(?i)(mp4|3gpp|mp2t|webm|matroska)";
    private a b;
    private String c;
    private File d;

    public interface a {
        void a();

        void a(Uri uri, VASTModel vASTModel);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((a[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((Pair) obj);
    }

    public l(Context context, a aVar, String str) {
        if (context == null || str == null || !c.a(context) || !g.d()) {
            aVar.a();
            return;
        }
        this.b = aVar;
        this.c = str;
        File externalFilesDir = context.getExternalFilesDir(null);
        if (externalFilesDir != null) {
            this.d = new File(externalFilesDir.getPath() + "/native_video/");
            if (!this.d.exists()) {
                this.d.mkdirs();
                return;
            }
            return;
        }
        aVar.a();
    }

    protected Pair<Uri, VASTModel> a(a... aVarArr) {
        InputStream b;
        Throwable th;
        InputStream inputStream;
        Throwable th2;
        InputStream inputStream2 = null;
        try {
            VASTProcessor vASTProcessor = new VASTProcessor(new DefaultMediaPicker(Appodeal.b));
            if (vASTProcessor.process(this.c) == 0) {
                VASTModel model = vASTProcessor.getModel();
                if (model.getPickedMediaFileType().matches(this.a)) {
                    String pickedMediaFileURL = model.getPickedMediaFileURL();
                    b = b(pickedMediaFileURL);
                    try {
                        File file = new File(this.d, a(pickedMediaFileURL));
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = b.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        fileOutputStream.close();
                        if (ThumbnailUtils.createVideoThumbnail(file.getPath(), 1) != null) {
                            Pair<Uri, VASTModel> pair = new Pair(Uri.fromFile(file), model);
                            a(b);
                            return pair;
                        }
                        a(b);
                        return null;
                    } catch (Throwable e) {
                        th = e;
                        inputStream = b;
                        th2 = th;
                        try {
                            Appodeal.a(th2);
                            a(inputStream);
                            return null;
                        } catch (Throwable th3) {
                            th2 = th3;
                            inputStream2 = inputStream;
                            a(inputStream2);
                            throw th2;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        inputStream2 = b;
                        th2 = th;
                        a(inputStream2);
                        throw th2;
                    }
                }
            }
            b = null;
            a(b);
            return null;
        } catch (Exception e2) {
            th2 = e2;
            inputStream = null;
            Appodeal.a(th2);
            a(inputStream);
            return null;
        } catch (Throwable th5) {
            th2 = th5;
            a(inputStream2);
            throw th2;
        }
    }

    protected void a(Pair<Uri, VASTModel> pair) {
        if (pair != null) {
            this.b.a((Uri) pair.first, (VASTModel) pair.second);
        } else {
            this.b.a();
        }
    }

    private InputStream b(String str) {
        URLConnection openConnection;
        try {
            openConnection = new URL(str).openConnection();
            a(openConnection);
            openConnection.setConnectTimeout(20000);
            openConnection.setReadTimeout(20000);
            openConnection.connect();
            return openConnection.getInputStream();
        } catch (IOException e) {
            Appodeal.a(e.getMessage());
            Builder buildUpon = Uri.parse(str).buildUpon();
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
