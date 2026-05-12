package com.chartboost.sdk.impl;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.h;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ag {
    private final z a;
    private final h b;
    private final Map<String, com.chartboost.sdk.Libraries.k.a> c = new HashMap();

    public interface b {
        void a(com.chartboost.sdk.Libraries.k.a aVar, Bundle bundle);
    }

    private class a implements Runnable {
        final /* synthetic */ ag a;
        private final String b;
        private final WeakReference<ImageView> c;
        private final b d;
        private String e;
        private final Bundle f;

        public a(ag agVar, ImageView imageView, b bVar, String str, Bundle bundle, String str2) {
            this.a = agVar;
            this.c = new WeakReference(imageView);
            Drawable cVar = new c(this);
            if (imageView != null) {
                imageView.setImageDrawable(cVar);
            }
            this.e = str;
            this.d = bVar;
            this.f = bundle;
            this.b = str2;
        }

        public void run() {
            try {
                if (this.a.b(this.e)) {
                    a();
                    return;
                }
                v anonymousClass1 = new v(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void a(x xVar) {
                        CBLogging.b("CBWebImageCache", "Error downloading the bitmap image from the server");
                        if (!(xVar == null || TextUtils.isEmpty(xVar.a()))) {
                            CBLogging.b("CBWebImageCache", xVar.a());
                        }
                        if (xVar != null && xVar.a != null) {
                            CBLogging.b("CBWebImageCache", "Error status Code: " + xVar.a.b());
                        }
                    }
                };
                CBLogging.a("CBWebImageCache", "downloading image to cache... " + this.b);
                this.a.a.a(new w<String>(this, com.chartboost.sdk.impl.w.a.a, this.b, anonymousClass1) {
                    final /* synthetic */ a a;

                    public y<String> a(ab abVar) {
                        try {
                            byte[] a = abVar.a();
                            if (a.length <= 0) {
                                return y.a(new RuntimeException("Bitmap response data is empty, unable to download the bitmap"));
                            }
                            String b = com.chartboost.sdk.Libraries.b.b(com.chartboost.sdk.Libraries.b.a(a));
                            if (TextUtils.isEmpty(b)) {
                                b = "";
                            }
                            if (!b.equals(this.a.e)) {
                                this.a.e = b;
                                CBLogging.b("CBWebImageCache:ImageDownloader", "Error: checksum did not match while downloading from " + this.a.b);
                            }
                            this.a.a.b.a(this.a.a.b.h(), String.format("%s%s", new Object[]{this.a.e, ".png"}), a);
                            this.a.a.a(this.a.e);
                            return y.b();
                        } catch (Exception e) {
                            com.chartboost.sdk.Tracking.a.a(a.class, "parseServerResponse", e);
                            return y.a(new RuntimeException("Bitmap response data is empty, unable to download the bitmap"));
                        }
                    }

                    public void a(String str) {
                        this.a.a();
                    }

                    public Map<String, String> b() {
                        Map<String, String> hashMap = new HashMap();
                        for (Entry entry : ad.b().entrySet()) {
                            hashMap.put(entry.getKey(), entry.getValue() != null ? entry.getValue().toString() : null);
                        }
                        return hashMap;
                    }
                });
            } catch (Exception e) {
                com.chartboost.sdk.Tracking.a.a(getClass(), "run", e);
            }
        }

        public void a() {
            final com.chartboost.sdk.Libraries.k.a b = b();
            if (b != null) {
                ImageView imageView = (ImageView) this.c.get();
                if (imageView != null && this == ag.b(imageView)) {
                    b.b();
                }
            }
            CBUtility.c().post(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    ImageView imageView = (ImageView) this.b.c.get();
                    if (imageView != null) {
                        a a = ag.b(imageView);
                        if (b != null && this.b == a) {
                            imageView.setImageBitmap(b.a());
                        }
                    }
                    if (this.b.d != null) {
                        this.b.d.a(b, this.b.f);
                    }
                }
            });
        }

        private com.chartboost.sdk.Libraries.k.a b() {
            return (com.chartboost.sdk.Libraries.k.a) this.a.c.get(this.e);
        }
    }

    static class c extends BitmapDrawable {
        private final WeakReference<a> a;

        public c(a aVar) {
            this.a = new WeakReference(aVar);
        }

        public a a() {
            return (a) this.a.get();
        }
    }

    public ag(z zVar, h hVar) {
        this.a = zVar;
        this.b = hVar;
    }

    public void a(String str, String str2, b bVar, ImageView imageView, Bundle bundle) {
        com.chartboost.sdk.Libraries.k.a a = a(str2);
        if (a != null) {
            if (imageView != null) {
                imageView.setImageBitmap(a.a());
            }
            if (bVar != null) {
                bVar.a(a, bundle);
                return;
            }
            return;
        }
        if (str == null && bVar != null) {
            bVar.a(null, bundle);
        }
        u.a().execute(new a(this, imageView, bVar, str2, bundle, str));
    }

    private com.chartboost.sdk.Libraries.k.a a(String str) {
        if (!b(str)) {
            if (this.c.containsKey(str)) {
                this.c.remove(str);
            }
            return null;
        } else if (this.c.containsKey(str)) {
            return (com.chartboost.sdk.Libraries.k.a) this.c.get(str);
        } else {
            com.chartboost.sdk.Libraries.k.a aVar = new com.chartboost.sdk.Libraries.k.a(str, this.b.c(this.b.h(), String.format("%s%s", new Object[]{str, ".png"})), this.b);
            this.c.put(str, aVar);
            return aVar;
        }
    }

    private static a b(ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof c) {
                return ((c) drawable).a();
            }
        }
        return null;
    }

    private boolean b(String str) {
        return this.b.b(String.format("%s%s", new Object[]{str, ".png"}));
    }
}
