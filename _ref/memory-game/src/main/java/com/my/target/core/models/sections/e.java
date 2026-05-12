package com.my.target.core.models.sections;

import com.my.target.core.enums.a;
import com.my.target.core.models.banners.c;
import com.my.target.core.models.banners.f;

/* compiled from: NativeAdSection */
public final class e extends a<f> {
    private String i = "Close";
    private String j = "Replay";
    private boolean k = true;
    private boolean l = false;

    public final boolean i() {
        return this.l;
    }

    public final void a(boolean z) {
        this.l = z;
    }

    public e(String str, int i) {
        super(a.e, str, i);
    }

    public final boolean a(c cVar) {
        if (!(cVar instanceof f) || b(cVar.getId()) != null) {
            return false;
        }
        this.f.add((f) cVar);
        this.d++;
        return true;
    }

    public final boolean a(int i, c cVar) {
        return a(cVar);
    }

    public final boolean j() {
        return this.k;
    }

    public final void b(boolean z) {
        this.k = z;
    }

    public final String k() {
        return this.i;
    }

    public final void c(String str) {
        this.i = str;
    }

    public final String l() {
        return this.j;
    }

    public final void d(String str) {
        this.j = str;
    }
}
