package com.appodeal.ads;

import android.app.Activity;
import com.appodeal.ads.f.c;
import com.appodeal.ads.f.d;

public class x {

    static class a {
        private final Activity a;
        private String b = "banner_mrec";
        private boolean c = false;
        private boolean d = false;

        a(Activity activity) {
            this.a = activity;
        }

        public void a() {
            v.a(this.a, this.b, this.c, this.d);
        }

        public a b() {
            this.c = true;
            return this;
        }

        public a c() {
            this.d = true;
            return this;
        }
    }

    static class b {
        private final Activity a;
        private String b = "banner_mrec";
        private boolean c = false;
        private c d = d.a();

        b(Activity activity) {
            this.a = activity;
        }

        boolean a() {
            return v.a(this.a, this.b, this.c, this.d);
        }

        public b b() {
            this.c = true;
            return this;
        }

        public b a(c cVar) {
            this.d = cVar;
            return this;
        }
    }
}
