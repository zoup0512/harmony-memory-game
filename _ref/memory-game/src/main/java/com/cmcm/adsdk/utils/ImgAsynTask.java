package com.cmcm.adsdk.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.ImageView;
import com.cmcm.utils.g;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class ImgAsynTask extends AsyncTask<String, String, Bitmap> {
    private Bitmap mBitMap = null;
    private ImageView mImgView = null;
    private String url;

    public ImgAsynTask(String url, ImageView imageView) {
        this.url = url;
        this.mImgView = imageView;
    }

    protected Bitmap doInBackground(String... params) {
        Bitmap bitmap = null;
        if (!TextUtils.isEmpty(this.url)) {
            try {
                HttpUriRequest httpGet = new HttpGet(this.url);
                HttpParams params2 = httpGet.getParams();
                HttpConnectionParams.setConnectionTimeout(params2, 15000);
                HttpConnectionParams.setSoTimeout(params2, 15000);
                HttpResponse execute = new DefaultHttpClient().execute(httpGet);
                if (execute.getStatusLine().getStatusCode() == 200) {
                    byte[] readInputSream = readInputSream(execute.getEntity().getContent());
                    bitmap = BitmapFactory.decodeByteArray(readInputSream, 0, readInputSream.length);
                }
            } catch (Exception e) {
                if (g.a) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }

    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap != null && this.mImgView != null) {
            this.mImgView.setImageBitmap(bitmap);
        }
    }

    private byte[] readInputSream(InputStream inStream) throws IOException {
        if (inStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                inStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
