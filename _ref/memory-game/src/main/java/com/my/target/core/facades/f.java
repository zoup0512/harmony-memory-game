package com.my.target.core.facades;

import android.content.Context;
import android.text.TextUtils;
import com.my.target.Tracer;
import com.my.target.ads.MyTargetVideoView;
import com.my.target.core.facades.c.a;
import com.my.target.core.models.banners.e;
import com.my.target.core.models.banners.i;
import com.my.target.core.models.c;
import com.my.target.core.models.g;
import com.my.target.core.net.b;
import com.my.target.core.utils.n;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* compiled from: InterstitialPromoAd */
public final class f implements c {
    private e a;
    private c b;
    private Context c;
    private a d;
    private b.a e = new b.a(this) {
        final /* synthetic */ f a;

        {
            this.a = r1;
        }

        public final void onLoad() {
            if (this.a.d != null) {
                this.a.d.onLoad(this.a);
            }
        }
    };

    public f(e eVar, c cVar, Context context) {
        this.a = eVar;
        this.b = cVar;
        this.c = context;
        Tracer.i("InterstitialPromoAd created. Version: 4.5.10");
    }

    public final boolean a() {
        return true;
    }

    public final void a(a aVar) {
        this.d = aVar;
    }

    public final void b() {
        if (this.b != null) {
            c.c(this.a, this.c);
        }
        if (this.d != null) {
            this.d.onDisplay(this);
        }
    }

    public final void c() {
        if (this.b != null) {
            this.b.a(this.a, this.c);
        }
        if (this.d != null) {
            this.d.onClick(this);
        }
    }

    public final void load() {
        List arrayList = new ArrayList();
        if (this.a.getImage().getUrl() != null) {
            arrayList.add(this.a.getImage());
        }
        if (this.a.getIcon().getUrl() != null) {
            arrayList.add(this.a.getIcon());
        }
        i k = this.a.k();
        if (k != null) {
            if (!(k.r() == null || TextUtils.isEmpty(k.r().getUrl()))) {
                arrayList.add(k.r());
            }
            arrayList.add(n.a(k.u(), MyTargetVideoView.DEFAULT_VIDEO_QUALITY));
        }
        if (!(this.a.l() == null || TextUtils.isEmpty(this.a.l().getUrl()))) {
            arrayList.add(this.a.l());
        }
        if (!(this.a.m() == null || TextUtils.isEmpty(this.a.m().getUrl()))) {
            arrayList.add(this.a.m());
        }
        if (!(this.a.o() == null || TextUtils.isEmpty(this.a.o().getUrl()))) {
            arrayList.add(this.a.o());
        }
        if (!arrayList.isEmpty()) {
            b.a().a(arrayList, this.c, this.e);
        } else if (this.d != null) {
            this.d.onLoad(this);
        }
    }

    public final e d() {
        return this.a;
    }

    public final void a(i iVar, Set<g> set, float f) {
        if (iVar != null) {
            c.a((Set) set, f, this.c);
        }
    }

    public final void a(com.my.target.core.models.banners.c cVar, String str) {
        if (cVar != null) {
            c.a(cVar, str, this.c);
        }
    }

    public final void e() {
        if (this.d != null) {
            this.d.onVideoCompleted(this);
        }
    }

    public final void f() {
        if (this.d != null) {
            this.d.onDismiss(this);
        }
    }
}
