package com.chartboost.sdk.Libraries;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.text.TextUtils;
import com.chartboost.sdk.f;
import com.chartboost.sdk.h;
import com.chartboost.sdk.h.b;
import com.chartboost.sdk.impl.ag;
import com.mopub.volley.DefaultRetryPolicy;
import java.io.File;

public class k implements b {
    private a a;
    private final h b;
    private String c;
    private float d = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    private final ag.b e = new ag.b(this) {
        final /* synthetic */ k a;

        {
            this.a = r1;
        }

        public void a(a aVar, Bundle bundle) {
            this.a.a = aVar;
            this.a.b.a(this.a);
        }
    };

    public static class a {
        private int a;
        private final String b;
        private final File c;
        private Bitmap d;
        private final h e;
        private int f = -1;
        private int g = -1;

        public a(String str, File file, h hVar) {
            this.c = file;
            this.b = str;
            this.d = null;
            this.a = 1;
            this.e = hVar;
        }

        public Bitmap a() {
            if (this.d == null) {
                b();
            }
            return this.d;
        }

        public void b() {
            if (this.d == null) {
                CBLogging.a("MemoryBitmap", "Loading image '" + this.b + "' from cache");
                byte[] b = this.e.b(this.c);
                if (b == null) {
                    CBLogging.b("MemoryBitmap", "decode() - bitmap not found");
                    return;
                }
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(b, 0, b.length, options);
                Options options2 = new Options();
                options2.inJustDecodeBounds = false;
                options2.inDither = false;
                options2.inPurgeable = true;
                options2.inInputShareable = true;
                options2.inTempStorage = new byte[32768];
                options2.inSampleSize = 1;
                while (options2.inSampleSize < 32) {
                    try {
                        this.d = BitmapFactory.decodeByteArray(b, 0, b.length, options2);
                        break;
                    } catch (Throwable e) {
                        CBLogging.b("MemoryBitmap", "OutOfMemoryError suppressed - trying larger sample size", e);
                        options2.inSampleSize *= 2;
                    } catch (Exception e2) {
                        CBLogging.b("MemoryBitmap", "Exception raised decoding bitmap", e2);
                        com.chartboost.sdk.Tracking.a.a(getClass(), "decodeByteArray", e2);
                    }
                }
                this.a = options2.inSampleSize;
            }
        }

        public void c() {
            try {
                if (!(this.d == null || this.d.isRecycled())) {
                    this.d.recycle();
                }
            } catch (Exception e) {
                com.chartboost.sdk.Tracking.a.a(getClass(), "recycle", e);
            }
            this.d = null;
        }

        public int d() {
            if (this.d != null) {
                return this.d.getWidth();
            }
            if (this.f >= 0) {
                return this.f;
            }
            f();
            return this.f;
        }

        public int e() {
            if (this.d != null) {
                return this.d.getHeight();
            }
            if (this.g >= 0) {
                return this.g;
            }
            f();
            return this.g;
        }

        private void f() {
            try {
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(this.c.getAbsolutePath(), options);
                this.f = options.outWidth;
                this.g = options.outHeight;
            } catch (Exception e) {
                CBLogging.b("MemoryBitmap", "Error decoding file size", e);
                com.chartboost.sdk.Tracking.a.a(getClass(), "decodeSize", e);
            }
        }
    }

    public k(h hVar) {
        this.b = hVar;
    }

    public int b() {
        return this.a.d() * this.a.a;
    }

    public int c() {
        return this.a.e() * this.a.a;
    }

    public void a(String str) {
        a(this.b.g(), str, new Bundle());
    }

    public void a(com.chartboost.sdk.Libraries.e.a aVar, String str, final Bundle bundle) {
        final com.chartboost.sdk.Libraries.e.a a = aVar.a(str);
        this.c = str;
        if (!a.b()) {
            final Object e = a.e("url");
            this.d = a.a("scale").a((float) DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            if (!TextUtils.isEmpty(e)) {
                this.b.b((b) this);
                CBUtility.c().post(new Runnable(this) {
                    final /* synthetic */ k d;

                    public void run() {
                        String str = "";
                        if (!(a.e("checksum") == null || a.e("checksum").isEmpty())) {
                            str = a.e("checksum");
                        }
                        f.m().a(e, str, this.d.e, null, bundle == null ? new Bundle() : bundle);
                    }
                });
            }
        }
    }

    public boolean a() {
        return e();
    }

    public void d() {
        if (this.a != null) {
            this.a.c();
        }
    }

    public boolean e() {
        return this.a != null;
    }

    public Bitmap f() {
        return this.a != null ? this.a.a() : null;
    }

    public float g() {
        return this.d;
    }

    public int h() {
        return Math.round(((float) b()) / this.d);
    }

    public int i() {
        return Math.round(((float) c()) / this.d);
    }
}
