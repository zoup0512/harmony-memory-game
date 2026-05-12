package com.my.target.core.net;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.ImageView;
import com.my.target.Tracer;
import com.my.target.core.models.f;
import com.my.target.core.ui.views.CacheImageView;
import com.my.target.nativeads.models.ImageData;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

/* compiled from: MediaLoader */
public final class b {
    private final HashMap<com.my.target.core.async.commands.b<f>, c> a;
    private com.my.target.core.async.commands.b.a<f> b;

    /* compiled from: MediaLoader */
    public interface a {
        void onLoad();
    }

    /* compiled from: MediaLoader */
    private static class b {
        private static final b a = new b();
    }

    /* compiled from: MediaLoader */
    private static class c {
        public final a a;
        public final WeakReference<ImageView> b;
        public int c;

        private c(a aVar, ImageView imageView) {
            this.c = 1;
            this.a = aVar;
            if (imageView != null) {
                this.b = new WeakReference(imageView);
            } else {
                this.b = null;
            }
        }
    }

    public static b a() {
        return b.a;
    }

    private b() {
        this.a = new HashMap();
        this.b = new com.my.target.core.async.commands.b.a<f>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void a(com.my.target.core.async.commands.b bVar, Object obj) {
                f fVar = (f) obj;
                c cVar = (c) this.a.a.remove(bVar);
                if (cVar != null) {
                    b.b(cVar, fVar);
                }
            }
        };
    }

    public final void a(List<f> list, Context context, a aVar) {
        if (context == null) {
            Tracer.d("Unable to load media: null context");
            return;
        }
        c cVar = new c(aVar, null);
        cVar.c = list.size();
        for (f a : list) {
            a(a, cVar, context);
        }
    }

    public final void a(ImageData imageData, ImageView imageView) {
        a((f) imageData, new c(null, imageView), imageView.getContext());
    }

    private void a(f fVar, c cVar, Context context) {
        if (fVar.getData() != null || TextUtils.isEmpty(fVar.getUrl())) {
            b(cVar, fVar);
            return;
        }
        com.my.target.core.async.commands.b fVar2 = new com.my.target.core.async.commands.f(fVar, context);
        this.a.put(fVar2, cVar);
        fVar2.a(this.b);
        fVar2.b();
    }

    private static void b(c cVar, f fVar) {
        cVar.c--;
        if (!(!(fVar instanceof ImageData) || cVar.b == null || fVar.getData() == null)) {
            ImageView imageView = (ImageView) cVar.b.get();
            if (imageView != null) {
                if (!Looper.getMainLooper().equals(Looper.myLooper())) {
                    Tracer.d("Unable to set image: not main thread");
                } else if (imageView instanceof CacheImageView) {
                    ((CacheImageView) imageView).setImageBitmap(((ImageData) fVar).getBitmap(), true);
                } else {
                    imageView.setImageBitmap(((ImageData) fVar).getBitmap());
                }
            }
        }
        if (cVar.c == 0 && cVar.a != null) {
            cVar.a.onLoad();
        }
    }
}
