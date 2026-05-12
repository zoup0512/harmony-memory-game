package com.my.target.core.models.banners;

import com.my.target.nativeads.models.ImageData;
import java.util.List;

/* compiled from: FSImageBanner */
public final class d extends a {
    private ImageData r;
    private ImageData s;
    private List<ImageData> t;
    private List<ImageData> u;
    private ImageData v;
    private boolean w = true;

    public final void c(boolean z) {
        this.w = z;
    }

    public d(String str, String str2) {
        super(str, str2);
    }

    public final ImageData k() {
        return this.v;
    }

    public final void a(ImageData imageData) {
        this.v = imageData;
    }

    public final List<ImageData> l() {
        return this.t;
    }

    public final void a(List<ImageData> list) {
        this.t = list;
    }

    public final List<ImageData> m() {
        return this.u;
    }

    public final void b(List<ImageData> list) {
        this.u = list;
    }

    public final ImageData n() {
        return this.r;
    }

    public final void b(ImageData imageData) {
        this.r = imageData;
    }

    public final ImageData o() {
        return this.s;
    }

    public final void c(ImageData imageData) {
        this.s = imageData;
    }
}
