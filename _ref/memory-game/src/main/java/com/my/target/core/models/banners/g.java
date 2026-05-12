package com.my.target.core.models.banners;

import com.my.target.nativeads.models.ImageData;

/* compiled from: StandardBanner */
public final class g extends a {
    private final ImageData r = new ImageData();
    private String s;
    private String t;
    private String u;
    private ImageData v;
    private int w;
    private String x;
    private String y;
    private float z;

    public final String k() {
        return this.s;
    }

    public final void j(String str) {
        this.s = str;
    }

    public final String l() {
        return this.t;
    }

    public final void k(String str) {
        this.t = str;
    }

    public final String m() {
        return this.u;
    }

    public final void l(String str) {
        this.u = str;
    }

    public final ImageData n() {
        return this.v;
    }

    public final void a(ImageData imageData) {
        this.v = imageData;
    }

    public final int o() {
        return this.w;
    }

    public final String p() {
        return this.y;
    }

    public final float q() {
        return this.z;
    }

    public final void d(int i) {
        this.w = i;
    }

    public final void m(String str) {
        this.x = str;
    }

    public final void n(String str) {
        this.y = str;
    }

    public final void a(float f) {
        this.z = f;
    }

    public final void e(int i) {
        this.r.setWidth(i);
    }

    public final void f(int i) {
        this.r.setHeight(i);
    }

    public final void o(String str) {
        this.r.setUrl(str);
    }

    public g(String str, String str2) {
        super(str, str2);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("StandardBanner{");
        stringBuilder.append("id=").append(this.a);
        stringBuilder.append("title=").append(this.s);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
