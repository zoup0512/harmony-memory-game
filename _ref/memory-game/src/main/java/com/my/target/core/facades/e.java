package com.my.target.core.facades;

import android.content.Context;
import com.my.target.Tracer;
import com.my.target.core.facades.c.a;
import com.my.target.core.models.banners.d;
import com.my.target.core.models.c;
import com.my.target.core.net.b;
import com.my.target.nativeads.models.ImageData;
import java.util.ArrayList;
import java.util.List;

/* compiled from: InterstitialImageAd */
public final class e implements c {
    private d a;
    private c b;
    private Context c;
    private a d;
    private b.a e = new b.a(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public final void onLoad() {
            if (this.a.d != null) {
                this.a.d.onLoad(this.a);
            }
        }
    };

    public e(d dVar, c cVar, Context context) {
        this.a = dVar;
        this.b = cVar;
        this.c = context;
        Tracer.i("InterstitialImageAd created. Version: 4.5.10");
    }

    private ImageData a(List<ImageData> list, int i, int i2) {
        if (i2 == 0) {
            Tracer.i("Display height is zero");
            return null;
        }
        float f = ((float) i) / ((float) i2);
        ImageData imageData = null;
        float f2 = 0.0f;
        for (ImageData imageData2 : list) {
            if (imageData2.getWidth() > 0 && imageData2.getHeight() > 0) {
                float width;
                float width2 = ((float) imageData2.getWidth()) / ((float) imageData2.getHeight());
                if (f < width2) {
                    width = (float) imageData2.getWidth();
                    if (width > ((float) i)) {
                        width = (float) i;
                    }
                    float f3 = width / width2;
                    width2 = width;
                    width = f3;
                } else {
                    width = (float) imageData2.getHeight();
                    if (width > ((float) i2)) {
                        width = (float) i2;
                    }
                    width2 *= width;
                }
                width *= width2;
                if (width <= f2) {
                    break;
                }
                imageData = imageData2;
                f2 = width;
            } else {
                com.my.target.core.async.a.a("Image has invalid size: w=" + imageData2.getWidth() + " h=" + imageData2.getHeight() + " in banner with id: " + this.a.getId(), getClass().getName(), 40, "JSONError", imageData2.getUrl(), this.c);
            }
        }
        return imageData;
    }

    public final boolean a() {
        if (this.a == null) {
            return false;
        }
        boolean z;
        boolean z2;
        if (this.a.n() == null || this.a.n().getData() == null) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (this.a.o() == null || this.a.o().getData() == null) {
            z = false;
        } else {
            z = true;
        }
        if (z || r0) {
            return true;
        }
        return false;
    }

    public final void a(a aVar) {
        this.d = aVar;
    }

    public final void load() {
        d dVar = this.a;
        List arrayList = new ArrayList();
        com.my.target.core.providers.d.a().b().b(this.c);
        int a = com.my.target.core.providers.d.a().b().a();
        int b = com.my.target.core.providers.d.a().b().b();
        ImageData a2 = a(dVar.l(), Math.min(a, b), Math.max(a, b));
        if (a2 != null) {
            arrayList.add(a2);
            dVar.b(a2);
        }
        ImageData a3 = a(dVar.m(), Math.max(a, b), Math.min(a, b));
        if (a3 != null) {
            arrayList.add(a3);
            dVar.c(a3);
        }
        if (!((a2 == null && a3 == null) || dVar.k() == null)) {
            arrayList.add(dVar.k());
        }
        if (arrayList.size() > 0) {
            b.a().a(arrayList, this.c, this.e);
        } else if (this.d != null) {
            this.d.onError("No ad", this);
        }
    }

    public final d b() {
        return this.a;
    }

    public final void c() {
        if (this.b != null) {
            c.c(this.a, this.c);
        }
        if (this.d != null) {
            this.d.onDisplay(this);
        }
    }

    public final void d() {
        if (this.b != null) {
            this.b.a(this.a, this.c);
        }
        if (this.d != null) {
            this.d.onClick(this);
        }
    }

    public final void e() {
        if (this.d != null) {
            this.d.onDismiss(this);
        }
    }
}
