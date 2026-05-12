package com.my.target.core.models.banners;

import com.my.target.nativeads.banners.NativePromoBanner;
import com.my.target.nativeads.models.ImageData;

/* compiled from: NativeAdBanner */
public class f extends a implements NativePromoBanner {
    protected String A;
    private i B;
    protected String r;
    protected String s;
    protected String t;
    protected float u;
    protected int v;
    protected String w;
    protected String x;
    protected ImageData y = new ImageData();
    protected ImageData z = new ImageData();

    public f(String str, String str2) {
        super(str, str2);
    }

    public String getTitle() {
        return this.r;
    }

    public final void j(String str) {
        this.r = str;
    }

    public String getDescription() {
        return this.s;
    }

    public final void k(String str) {
        this.s = str;
    }

    public String getDisclaimer() {
        return this.t;
    }

    public final void l(String str) {
        this.t = str;
    }

    public float getRating() {
        return this.u;
    }

    public final void b(float f) {
        this.u = f;
    }

    public int getVotes() {
        return this.v;
    }

    public final void i(int i) {
        this.v = i;
    }

    public String getCategory() {
        return this.w;
    }

    public final void m(String str) {
        this.w = str;
    }

    public String getSubcategory() {
        return this.x;
    }

    public final void n(String str) {
        this.x = str;
    }

    public final void o(String str) {
        this.y.setUrl(str);
    }

    public final void p(String str) {
        this.z.setUrl(str);
    }

    public final void j(int i) {
        this.z.setWidth(i);
    }

    public final void k(int i) {
        this.z.setHeight(i);
    }

    public String getDomain() {
        return this.A;
    }

    public final void q(String str) {
        this.A = str;
    }

    public final void l(int i) {
        this.y.setWidth(i);
    }

    public final void m(int i) {
        this.y.setHeight(i);
    }

    public ImageData getIcon() {
        return this.y;
    }

    public ImageData getImage() {
        return this.z;
    }

    public final boolean w() {
        return this.u > 0.0f && this.u <= 5.0f && this.v > 0;
    }

    public void a(i iVar) {
        this.B = iVar;
    }

    public i k() {
        return this.B;
    }
}
