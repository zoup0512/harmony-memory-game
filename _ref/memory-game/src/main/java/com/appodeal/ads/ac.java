package com.appodeal.ads;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import com.appodeal.ads.Native.NativeAdType;
import java.io.File;

public class ac extends c {
    private final String a;
    private final String b;
    private boolean c = true;
    private boolean d = false;
    private int e = 0;
    private final af f;

    public ac(String str, af afVar) {
        this.a = str;
        this.b = str;
        this.f = afVar;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public ac c() {
        this.c = false;
        return this;
    }

    public boolean d() {
        if (!this.c) {
            if (!an.a("org.apache.http.HttpResponse")) {
                return false;
            }
        }
        return true;
    }

    public boolean e() {
        return this.d && VERSION.SDK_INT <= this.e;
    }

    public af f() {
        return this.f;
    }

    public static boolean a(NativeAd nativeAd) {
        if (nativeAd != null) {
            try {
                if (!(nativeAd.getTitle() == null || nativeAd.getDescription() == null)) {
                    if (c(nativeAd) || b(nativeAd)) {
                        return false;
                    }
                    if (Native.A == NativeAdType.Video && !((ab) nativeAd).k()) {
                        return false;
                    }
                    if (Native.A == NativeAdType.Video && Native.x) {
                        Uri j = ((ab) nativeAd).j();
                        if (VERSION.SDK_INT >= 10 && j != null && new File(j.getPath()).exists()) {
                            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                            mediaMetadataRetriever.setDataSource(Appodeal.b, j);
                            if (Long.parseLong(mediaMetadataRetriever.extractMetadata(9)) > ((long) Native.w)) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
            } catch (Throwable e) {
                Appodeal.a(e);
                return false;
            }
        }
        return false;
    }

    private static boolean b(NativeAd nativeAd) {
        if (Native.o && nativeAd.getIcon() == null) {
            return true;
        }
        return false;
    }

    private static boolean c(NativeAd nativeAd) {
        if (Native.n || Native.A != NativeAdType.NoVideo) {
            Bitmap image = nativeAd.getImage();
            if (image == null || !a(image)) {
                return true;
            }
        }
        return false;
    }

    private static boolean a(@NonNull Bitmap bitmap) {
        return ((float) bitmap.getWidth()) / ((float) bitmap.getHeight()) >= 1.5f;
    }
}
