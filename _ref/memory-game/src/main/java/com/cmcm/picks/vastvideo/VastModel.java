package com.cmcm.picks.vastvideo;

import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import com.cmcm.picks.loader.Ad;
import com.mopub.volley.DefaultRetryPolicy;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class VastModel implements Serializable {
    private boolean A;
    private Ad B;
    private boolean C;
    private List<String> D;
    private int E;
    private double F;
    private int G;
    private String a;
    private String b;
    private List<String> c;
    private List<String> d;
    private List<String> e;
    private List<String> f;
    private List<String> g;
    private List<String> h;
    private List<String> i;
    private List<String> j;
    private List<String> k;
    private List<String> l;
    private List<String> m;
    private List<String> n;
    private List<String> o;
    private List<String> p;
    private List<String> q;
    private List<String> r;
    private String s;
    private List<String> t;
    private List<a> u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;

    static class a {
        private int a;
        private int b;
        private int c;
        private String d;
        private String e;
        private boolean f;
        private boolean g;
        private String h;
        private String i;

        public int a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }

        public String c() {
            return this.h;
        }

        public void a(String str) {
            this.e = str;
        }

        public void a(int i) {
            this.a = i;
        }

        public void b(int i) {
            this.b = i;
        }

        public void c(int i) {
            this.c = i;
        }

        public void b(String str) {
            this.d = str;
        }

        public void a(boolean z) {
            this.f = z;
        }

        public void b(boolean z) {
            this.g = z;
        }

        public void c(String str) {
            this.h = str;
        }

        public void d(String str) {
            this.i = str;
        }

        public String toString() {
            return "MediaFile{videoWidth=" + this.a + ", videoHeight=" + this.b + ", bitrate=" + this.c + ", id='" + this.d + '\'' + ", delivery='" + this.e + '\'' + ", scalable=" + this.f + ", maintainAspectRatio=" + this.g + ", videoUrl='" + this.h + '\'' + ", videoType='" + this.i + '\'' + '}';
        }
    }

    public String a() {
        return this.b;
    }

    public boolean b() {
        return this.C;
    }

    public void a(boolean z) {
        this.C = z;
    }

    public List<String> c() {
        return this.c;
    }

    public List<String> d() {
        return this.d;
    }

    public List<String> e() {
        return this.e;
    }

    public List<String> f() {
        return this.f;
    }

    public List<String> g() {
        return this.g;
    }

    public List<String> h() {
        return this.h;
    }

    public List<String> i() {
        return this.i;
    }

    public List<String> j() {
        return this.j;
    }

    public List<String> k() {
        return this.k;
    }

    public List<String> l() {
        return this.l;
    }

    public List<String> m() {
        return this.m;
    }

    public List<String> n() {
        return this.n;
    }

    public List<String> o() {
        return this.p;
    }

    public List<String> p() {
        return this.q;
    }

    public List<String> q() {
        return this.r;
    }

    public String r() {
        return this.s;
    }

    public List<String> s() {
        return this.t;
    }

    public List<a> t() {
        return this.u;
    }

    public String u() {
        return this.v;
    }

    public String v() {
        return this.w;
    }

    public String w() {
        return this.y;
    }

    public boolean x() {
        return this.A;
    }

    public Ad getAd() {
        return this.B;
    }

    public List<String> y() {
        return this.D;
    }

    public int z() {
        return this.E;
    }

    public void a(String str) {
        this.a = str;
    }

    public void b(String str) {
        this.b = str;
    }

    public void a(List<String> list) {
        this.c = list;
    }

    public void a(Ad ad) {
        this.B = ad;
    }

    public void b(List<String> list) {
        this.d = list;
    }

    public void c(List<String> list) {
        this.e = list;
    }

    public void d(List<String> list) {
        this.f = list;
    }

    public void e(List<String> list) {
        this.g = list;
    }

    public void f(List<String> list) {
        this.h = list;
    }

    public void g(List<String> list) {
        this.i = list;
    }

    public void h(List<String> list) {
        this.j = list;
    }

    public void i(List<String> list) {
        this.k = list;
    }

    public void j(List<String> list) {
        this.l = list;
    }

    public void k(List<String> list) {
        this.m = list;
    }

    public void l(List<String> list) {
        this.n = list;
    }

    public void m(List<String> list) {
        this.p = list;
    }

    public void n(List<String> list) {
        this.q = list;
    }

    public void o(List<String> list) {
        this.r = list;
    }

    public void c(String str) {
        this.s = str;
    }

    public void p(List<String> list) {
        this.t = list;
    }

    public void q(List<a> list) {
        this.u = list;
    }

    public void d(String str) {
        this.v = str;
    }

    public void e(String str) {
        this.w = str;
    }

    public void f(String str) {
        this.x = str;
    }

    public void g(String str) {
        this.y = str;
    }

    public void b(boolean z) {
        this.A = z;
    }

    public void r(List<String> list) {
        this.D = list;
    }

    public void a(int i) {
        this.E = i;
    }

    public String a(Context context) {
        String str = null;
        List t = t();
        if (!(t == null || t.isEmpty())) {
            b(context);
            double d = Double.POSITIVE_INFINITY;
            Iterator it = t.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                CharSequence c = aVar.c();
                if (TextUtils.isEmpty(c)) {
                    it.remove();
                } else {
                    Integer valueOf = Integer.valueOf(aVar.a());
                    Integer valueOf2 = Integer.valueOf(aVar.b());
                    if (valueOf != null && valueOf.intValue() > 0 && valueOf2 != null && valueOf2.intValue() > 0) {
                        CharSequence charSequence;
                        double d2;
                        double a = a(valueOf.intValue(), valueOf2.intValue());
                        if (a < d) {
                            charSequence = c;
                            d2 = a;
                        } else {
                            Object obj = str;
                            d2 = d;
                        }
                        d = d2;
                        str = charSequence;
                    }
                }
            }
        }
        return str;
    }

    private void b(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        int height = defaultDisplay.getHeight();
        float f = context.getResources().getDisplayMetrics().density;
        if (f <= 0.0f) {
            f = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        }
        int max = Math.max(width, height);
        width = Math.min(width, height);
        this.F = ((double) max) / ((double) width);
        this.G = (int) ((((float) width) / f) * (((float) max) / f));
    }

    private double a(int i, int i2) {
        return (Math.abs(Math.log((((double) i) / ((double) i2)) / this.F)) * 70.0d) + (Math.abs(Math.log(((double) (i * i2)) / ((double) this.G))) * 30.0d);
    }

    public String toString() {
        return "VastModel{id='" + this.a + '\'' + ", adTitle='" + this.b + '\'' + ", errorReportUrl=" + this.c + ", impressionReportUrls=" + this.d + ", createViewReportUrls=" + this.e + ", startReportUrls=" + this.f + ", firstQuartileReportUrls=" + this.g + ", midpointReportUrls=" + this.h + ", thirdQuartileReportUrls=" + this.i + ", completeReportUrls=" + this.j + ", closeReportUrls=" + this.k + ", pauseReportUrls=" + this.l + ", muteReportUrls=" + this.m + ", unmuteReportUrls=" + this.n + ", rewindReportUrls=" + this.o + ", resumeReportUrls=" + this.p + ", fullScreenReportUrls=" + this.q + ", exitFullScreenReportUrls=" + this.r + ", clickThrough='" + this.s + '\'' + ", clickTrackings=" + this.t + ", mediaFile=" + this.u + ", frontImgUrl='" + this.v + '\'' + ", behindImgUrl='" + this.w + '\'' + ", iconUrl='" + this.x + '\'' + ", videoFilePath='" + this.y + '\'' + ", vastSrcUrl='" + this.z + '\'' + ", isWapperType=" + this.A + ", vastAdTagUrl=" + this.D + ", wapperFrequency=" + this.E + '}';
    }
}
