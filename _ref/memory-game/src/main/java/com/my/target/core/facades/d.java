package com.my.target.core.facades;

import android.content.Context;
import com.my.target.Tracer;
import com.my.target.ads.CustomParams;
import com.my.target.core.models.banners.i;
import com.my.target.core.models.c;
import com.my.target.core.models.g;
import com.my.target.core.models.sections.f;
import com.my.target.core.models.sections.h;
import java.util.Set;

/* compiled from: InstreamAd */
public final class d extends a {
    private final com.my.target.core.a a;
    private a b;
    private com.my.target.core.models.sections.d c;

    /* compiled from: InstreamAd */
    public interface a {
        void onLoad(d dVar);

        void onNoAd(String str, d dVar);
    }

    public d(int i, CustomParams customParams) {
        this.a = new com.my.target.core.a(i, "instreamads");
        this.a.a(customParams);
        this.a.b(false);
    }

    public final void a(Context context) {
        init(this.a, context);
    }

    public final h a(String str) {
        return this.c.c(str);
    }

    public final void a(boolean z) {
        this.a.b(z);
    }

    public final boolean a() {
        return this.a.g();
    }

    protected final void onLoad(c cVar) {
        if (this.b != null) {
            f c = cVar.c("instreamads");
            if (c != null && (c instanceof com.my.target.core.models.sections.d)) {
                this.c = (com.my.target.core.models.sections.d) c;
            }
            if (this.c == null || this.c.b() <= 0) {
                this.b.onNoAd("No ad", this);
            } else {
                this.b.onLoad(this);
            }
        }
    }

    protected final void onLoadError(String str) {
        if (this.b != null) {
            this.b.onNoAd(str, this);
        }
    }

    public final void a(a aVar) {
        this.b = aVar;
    }

    public final void a(i iVar) {
        if (iVar == null) {
            Tracer.d("Something horrible happened");
        } else if (this.adData == null) {
            Tracer.d("AdData is null, click will not be processed.");
        } else {
            try {
                this.adData.a(iVar, this.context);
            } catch (Throwable th) {
                Tracer.d(th.toString());
            }
        }
    }

    public final void a(i iVar, Set<g> set, float f) {
        if (iVar != null) {
            c.a((Set) set, f, this.context);
        }
    }

    public final void a(com.my.target.core.models.banners.c cVar, String str) {
        if (cVar != null) {
            c.a(cVar, str, this.context);
        }
    }

    public final void a(f fVar, String str) {
        if (fVar != null) {
            c.a(fVar, str, this.context);
        }
    }
}
