package com.my.target.core.facades;

import android.content.Context;
import com.my.target.ads.CustomParams;
import com.my.target.core.models.banners.c;
import com.my.target.core.models.banners.g;
import com.my.target.core.models.sections.f;
import java.util.ArrayList;
import org.json.JSONObject;

/* compiled from: StandardAd */
public final class h extends a {
    private final com.my.target.core.a a;
    private boolean b = true;
    private f c;
    private a d;

    /* compiled from: StandardAd */
    public interface a {
        void onLoad(h hVar);

        void onNoAd(String str, h hVar);
    }

    public final void a(a aVar) {
        this.d = aVar;
    }

    public final boolean a() {
        return this.b;
    }

    public h(int i, Context context, CustomParams customParams, Boolean bool) {
        this.a = new com.my.target.core.a(i, "standard_320x50");
        if (customParams != null) {
            this.a.a(customParams);
        }
        this.b = bool.booleanValue();
        init(this.a, context);
    }

    public final String b() {
        if (this.adData != null) {
            return this.adData.e();
        }
        return null;
    }

    public final JSONObject c() {
        if (this.adData != null) {
            return this.adData.c();
        }
        return null;
    }

    public final String d() {
        if (this.c != null) {
            return this.c.c();
        }
        return null;
    }

    public final ArrayList<g> e() {
        if (this.c != null) {
            return this.c.g();
        }
        return null;
    }

    public final String f() {
        if (this.adData != null) {
            return this.adData.b();
        }
        return null;
    }

    public final void a(String str) {
        if (this.c != null && this.adData != null) {
            c b = this.c.b(str);
            if (b != null) {
                this.adData.a(b, this.context);
            }
        }
    }

    public final com.my.target.core.models.sections.g g() {
        if (com.my.target.core.enums.a.a.equals(this.c.a())) {
            return (com.my.target.core.models.sections.g) this.c;
        }
        return null;
    }

    public final void b(String str) {
        c b = this.c.b(str);
        if (b != null) {
            com.my.target.core.models.c.c(b, this.context);
        }
    }

    public final h h() {
        return new h(this.a.d(), this.context, this.a.b(), Boolean.valueOf(this.b));
    }

    public final com.my.target.core.models.h i() {
        if (com.my.target.core.enums.a.a.equals(this.c.a())) {
            return ((com.my.target.core.models.sections.g) this.c).i();
        }
        return null;
    }

    protected final void onLoad(com.my.target.core.models.c cVar) {
        if (this.d != null) {
            if (!cVar.g() || cVar.e() == null) {
                this.d.onNoAd("No ad", this);
                return;
            }
            this.c = cVar.c("standard_320x50");
            if (this.c != null) {
                this.d.onLoad(this);
            } else {
                this.d.onNoAd("No ad", this);
            }
        }
    }

    protected final void onLoadError(String str) {
        if (this.d != null) {
            this.d.onNoAd(str, this);
        }
    }
}
