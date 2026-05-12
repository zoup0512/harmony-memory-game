package com.my.target.core.models.banners;

import com.my.target.core.models.g;
import com.my.target.nativeads.models.ImageData;
import com.my.target.nativeads.models.VideoData;
import java.util.ArrayList;
import java.util.List;

/* compiled from: VideoBanner */
public class i extends a {
    private boolean A;
    private boolean B;
    private boolean C;
    private boolean D;
    private float r;
    private boolean s;
    private float t;
    private List<VideoData> u = new ArrayList();
    private boolean v = true;
    private boolean w = true;
    private ImageData x;
    private String y;
    private String z;

    public final boolean k() {
        return this.B;
    }

    public final boolean l() {
        return this.C;
    }

    public final void c(boolean z) {
        this.B = z;
    }

    public final void d(boolean z) {
        this.C = z;
    }

    public final void e(boolean z) {
        this.D = z;
    }

    public final boolean m() {
        return this.D;
    }

    public i(String str, String str2) {
        super(str, str2);
    }

    public final void j(String str) {
        this.a = str;
    }

    public final float n() {
        return this.r;
    }

    public final void a(float f) {
        this.r = f;
    }

    public final boolean o() {
        return this.s;
    }

    public final void f(boolean z) {
        this.s = z;
    }

    public final float p() {
        return this.t;
    }

    public final void b(float f) {
        this.t = f;
    }

    public final boolean q() {
        return this.v;
    }

    public final void g(boolean z) {
        this.v = z;
    }

    public final void h(boolean z) {
        this.w = z;
    }

    public final ImageData r() {
        return this.x;
    }

    public final void a(ImageData imageData) {
        this.x = imageData;
    }

    public final String s() {
        return this.y;
    }

    public final void k(String str) {
        this.y = str;
    }

    public final String t() {
        return this.z;
    }

    public final void l(String str) {
        this.z = str;
    }

    public final List<VideoData> u() {
        return this.u;
    }

    public final void a(List<VideoData> list) {
        this.u = list;
    }

    public final boolean v() {
        return this.A;
    }

    public final void i(boolean z) {
        this.A = z;
    }

    public final synchronized boolean a(com.my.target.core.models.i iVar) {
        boolean a;
        if (iVar.c().equals("playheadReachedValue")) {
            iVar = (g) iVar;
            if (iVar.b() != 0.0f) {
                iVar.a((iVar.b() * this.r) / 100.0f);
            }
            a = super.a(iVar);
        } else {
            a = super.a(iVar);
        }
        return a;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("VideoBanner{");
        stringBuilder.append("id=").append(this.a);
        stringBuilder.append(", duration=").append(this.r);
        stringBuilder.append(", allowClose=").append(this.s);
        stringBuilder.append(", allowCloseDelay=").append(this.t);
        stringBuilder.append(", videoDatas=").append(this.u);
        stringBuilder.append(", autoPlay=").append(this.v);
        stringBuilder.append(", hasCtaButton=").append(this.w);
        stringBuilder.append(", preview=").append(this.x);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
