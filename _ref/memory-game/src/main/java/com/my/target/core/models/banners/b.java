package com.my.target.core.models.banners;

import com.my.target.nativeads.banners.NativeAppwallBanner;
import com.my.target.nativeads.models.ImageData;

/* compiled from: AppwallBanner */
public final class b extends a implements NativeAppwallBanner {
    private int A = -552418;
    private int B = -1;
    private float C;
    private boolean D;
    private boolean E;
    private boolean F;
    private boolean G;
    private boolean H;
    private boolean I;
    private boolean J;
    private ImageData K;
    private ImageData L;
    private ImageData M;
    private ImageData N;
    private ImageData O;
    private ImageData P;
    private ImageData Q;
    private ImageData R;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;
    private int x;
    private int y;
    private int z;

    public final String getTitle() {
        return this.r;
    }

    public final void j(String str) {
        this.r = str;
    }

    public final String getDescription() {
        return this.s;
    }

    public final ImageData getIcon() {
        return this.K;
    }

    public final void k(String str) {
        this.s = str;
    }

    public final String getBubbleId() {
        return this.t;
    }

    public final void l(String str) {
        this.t = str;
    }

    public final String getLabelType() {
        return this.u;
    }

    public final void m(String str) {
        this.u = str;
    }

    public final String getStatus() {
        return this.v;
    }

    public final void n(String str) {
        this.v = str;
    }

    public final String getPaidType() {
        return this.w;
    }

    public final void o(String str) {
        this.w = str;
    }

    public final int getMrgsId() {
        return this.x;
    }

    public final void d(int i) {
        this.x = i;
    }

    public final int getVotes() {
        return this.y;
    }

    public final void e(int i) {
        this.y = i;
    }

    public final float getRating() {
        return this.C;
    }

    public final void a(float f) {
        this.C = f;
    }

    public final boolean isHasNotification() {
        return this.D;
    }

    public final void c(boolean z) {
        this.D = z;
    }

    public final boolean isMain() {
        return this.E;
    }

    public final void d(boolean z) {
        this.E = z;
    }

    public final boolean isRequireCategoryHighlight() {
        return this.F;
    }

    public final void e(boolean z) {
        this.F = z;
    }

    public final boolean isItemHighlight() {
        return this.G;
    }

    public final void f(boolean z) {
        this.G = z;
    }

    public final boolean isBanner() {
        return this.H;
    }

    public final void g(boolean z) {
        this.H = z;
    }

    public final boolean isRequireWifi() {
        return this.I;
    }

    public final void h(boolean z) {
        this.I = z;
    }

    public final boolean isSubItem() {
        return this.J;
    }

    public final void i(boolean z) {
        this.J = z;
    }

    public final int getCoins() {
        return this.z;
    }

    public final void f(int i) {
        this.z = i;
    }

    public final void a(ImageData imageData) {
        this.O = imageData;
    }

    public final void b(ImageData imageData) {
        this.K = imageData;
    }

    public final void c(ImageData imageData) {
        this.L = imageData;
    }

    public final void d(ImageData imageData) {
        this.M = imageData;
    }

    public final void e(ImageData imageData) {
        this.N = imageData;
    }

    public final void f(ImageData imageData) {
        this.P = imageData;
    }

    public final ImageData getCoinsIcon() {
        return this.L;
    }

    public final ImageData getLabelIcon() {
        return this.M;
    }

    public final ImageData getGotoAppIcon() {
        return this.N;
    }

    public final ImageData getStatusIcon() {
        return this.O;
    }

    public final ImageData getBubbleIcon() {
        return this.P;
    }

    public final int getCoinsIconBgColor() {
        return this.A;
    }

    public final void g(int i) {
        this.A = i;
    }

    public final int getCoinsIconTextColor() {
        return this.B;
    }

    public final void h(int i) {
        this.B = i;
    }

    public final ImageData getItemHighlightIcon() {
        return this.Q;
    }

    public final void g(ImageData imageData) {
        this.Q = imageData;
    }

    public final ImageData getCrossNotifIcon() {
        return this.R;
    }

    public final void h(ImageData imageData) {
        this.R = imageData;
    }

    public b(String str, String str2) {
        super(str, str2);
    }

    public final String toString() {
        return "AppwallBanner {title='" + this.r + '\'' + ", description='" + this.s + '\'' + '}';
    }
}
