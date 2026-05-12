package com.appodeal.ads;

import android.app.Activity;
import com.appodeal.ads.f.c;
import com.appodeal.ads.f.d;

public class i {

    static class a {
        private final Activity a;
        private String b = "banner_320";
        private boolean c = false;
        private com.appodeal.ads.g.b d = com.appodeal.ads.g.b.BOTTOM;
        private boolean e = false;

        a(Activity activity) {
            this.a = activity;
        }

        public void a() {
            g.a(this.a, this.b, this.c, this.d, this.e);
        }

        public a b() {
            this.c = true;
            return this;
        }

        public a a(com.appodeal.ads.g.b bVar) {
            this.d = bVar;
            return this;
        }

        public a c() {
            this.e = true;
            return this;
        }
    }

    static class b {
        private final Activity a;
        private String b = "banner_320";
        private com.appodeal.ads.g.b c = com.appodeal.ads.g.b.BOTTOM;
        private boolean d = false;
        private c e = d.a();

        b(Activity activity) {
            this.a = activity;
        }

        boolean a() {
            return g.a(this.a, this.e, this.b, this.c, this.d);
        }

        public b a(com.appodeal.ads.g.b bVar) {
            this.c = bVar;
            return this;
        }

        public b b() {
            this.d = true;
            return this;
        }

        public b a(c cVar) {
            this.e = cVar;
            return this;
        }
    }
}
