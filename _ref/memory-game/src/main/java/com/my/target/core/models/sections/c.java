package com.my.target.core.models.sections;

import com.my.target.core.models.banners.a;
import com.my.target.core.models.banners.d;
import com.my.target.core.models.banners.e;
import com.my.target.core.models.j;
import com.my.target.nativeads.models.ImageData;

/* compiled from: FullscreenSection */
public final class c extends a<a> {
    private final j i = new j();
    private ImageData j;
    private ImageData k;
    private ImageData l;
    private boolean m;
    private boolean n;
    private float o;
    private boolean p = true;
    private String q = "Replay";
    private String r = "Close";
    private boolean s = true;
    private boolean t = true;
    private int u;
    private boolean v;

    public final float i() {
        return this.o;
    }

    public final String j() {
        return this.r;
    }

    public final ImageData k() {
        return this.j;
    }

    public final ImageData l() {
        return this.k;
    }

    public final String m() {
        return this.q;
    }

    public final ImageData n() {
        return this.l;
    }

    public final int o() {
        return this.u;
    }

    public final j p() {
        return this.i;
    }

    public final boolean q() {
        return this.s;
    }

    public final boolean r() {
        return this.t;
    }

    public final boolean s() {
        return this.v;
    }

    public final boolean t() {
        return this.n;
    }

    public final boolean u() {
        return this.m;
    }

    public final void a(boolean z) {
        this.v = z;
    }

    public final boolean a(com.my.target.core.models.banners.c cVar) {
        if (cVar instanceof d) {
            if (b(cVar.getId()) == null) {
                this.f.add((d) cVar);
                this.d++;
                return true;
            }
        } else if ((cVar instanceof e) && b(cVar.getId()) == null) {
            this.f.add((e) cVar);
            this.d++;
            return true;
        }
        return false;
    }

    public final boolean v() {
        return this.p;
    }

    public final boolean a(int i, com.my.target.core.models.banners.c cVar) {
        return a(cVar);
    }

    public final void b(boolean z) {
        this.s = z;
    }

    public final void a(float f) {
        this.o = f;
    }

    public final void c(boolean z) {
        this.t = z;
    }

    public final void c(String str) {
        this.r = str;
    }

    public final void a(ImageData imageData) {
        this.j = imageData;
    }

    public final void d(boolean z) {
        this.n = z;
    }

    public final void e(boolean z) {
        this.m = z;
    }

    public final void b(ImageData imageData) {
        this.k = imageData;
    }

    public final void d(String str) {
        this.q = str;
    }

    public final void f(boolean z) {
        this.p = z;
    }

    public final void c(ImageData imageData) {
        this.l = imageData;
    }

    public final void a(int i) {
        this.u = i;
    }

    public c(String str, int i) {
        super(com.my.target.core.enums.a.d, str, i);
    }
}
