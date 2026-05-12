package com.my.target.core.async.commands;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.my.target.Tracer;
import com.my.target.core.utils.e;
import com.my.target.nativeads.models.ImageData;
import com.my.target.nativeads.models.VideoData;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* compiled from: LoadMediaCommand */
public final class f extends a<com.my.target.core.models.f> {
    public final /* bridge */ /* synthetic */ String a() {
        return super.a();
    }

    public f(com.my.target.core.models.f fVar, Context context) {
        super(context);
        this.c = fVar;
    }

    protected final void c() {
        Throwable th;
        HttpURLConnection httpURLConnection;
        HttpURLConnection httpURLConnection2 = null;
        if (this.c instanceof ImageData) {
            ImageData imageData = (ImageData) this.c;
            Bitmap bitmap = (Bitmap) imageData.getData();
            e a = e.a(this.b);
            if (bitmap == null) {
                if (a != null) {
                    bitmap = a.a(imageData.getUrl());
                    if (bitmap != null) {
                        imageData.setData(bitmap);
                        return;
                    }
                }
                Tracer.d("Unable to open disk cache and save image " + imageData.getUrl());
                try {
                    Tracer.d("send image request: " + imageData.getUrl());
                    httpURLConnection2 = a(imageData.getUrl());
                    try {
                        httpURLConnection2.setReadTimeout(10000);
                        httpURLConnection2.setConnectTimeout(10000);
                        httpURLConnection2.setInstanceFollowRedirects(true);
                        httpURLConnection2.setRequestProperty("connection", "close");
                        httpURLConnection2.connect();
                        if (httpURLConnection2.getResponseCode() == 200) {
                            InputStream inputStream = httpURLConnection2.getInputStream();
                            if (a != null) {
                                File a2 = a.a(inputStream, imageData.getUrl());
                                if (a2 != null) {
                                    bitmap = BitmapFactory.decodeFile(a2.getAbsolutePath());
                                }
                            } else {
                                InputStream bufferedInputStream = new BufferedInputStream(inputStream, 8192);
                                bitmap = BitmapFactory.decodeStream(bufferedInputStream);
                                bufferedInputStream.close();
                            }
                            if (bitmap != null) {
                                imageData.setData(bitmap);
                                if (imageData.getHeight() == 0) {
                                    imageData.setHeight(bitmap.getHeight());
                                }
                                if (imageData.getWidth() == 0) {
                                    imageData.setWidth(bitmap.getWidth());
                                }
                            }
                        }
                    } catch (IOException e) {
                        Tracer.d(e.getMessage());
                    } catch (Throwable th2) {
                        th = th2;
                        httpURLConnection = httpURLConnection2;
                        Tracer.d("Error: " + th.getMessage());
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                            return;
                        }
                        return;
                    }
                    httpURLConnection2.disconnect();
                    return;
                } catch (Throwable th3) {
                    th = th3;
                    httpURLConnection = httpURLConnection2;
                    Tracer.d("Error: " + th.getMessage());
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                        return;
                    }
                    return;
                }
            }
            Tracer.d("Getting image from memory cache " + imageData.getUrl());
        } else if (this.c instanceof VideoData) {
            VideoData videoData = (VideoData) this.c;
            e a3 = e.a(this.b);
            if (a3 == null) {
                Tracer.d("Unable to open disk cache and save video " + videoData.getUrl());
                return;
            }
            String a4 = a3.a(videoData.getUrl(), ".mp4");
            if (a4 != null) {
                videoData.setData(a4);
                Tracer.d("Getting video from cache " + videoData.getUrl());
                return;
            }
            try {
                Tracer.d("send video request: " + videoData.getUrl());
                httpURLConnection2 = a(videoData.getUrl());
                httpURLConnection2.setReadTimeout(10000);
                httpURLConnection2.setConnectTimeout(10000);
                httpURLConnection2.setInstanceFollowRedirects(true);
                httpURLConnection2.setRequestProperty("connection", "close");
                httpURLConnection2.connect();
                if (httpURLConnection2.getResponseCode() == 200) {
                    File b = a3.b(httpURLConnection2.getInputStream(), videoData.getUrl());
                    if (b != null) {
                        videoData.setData(b.getAbsolutePath());
                    }
                }
                httpURLConnection2.disconnect();
            } catch (Throwable th4) {
                Tracer.d("Error: " + th4.getMessage());
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
            }
        }
    }

    private static HttpURLConnection a(String str) throws Exception {
        return (HttpURLConnection) new URL(str).openConnection();
    }
}
