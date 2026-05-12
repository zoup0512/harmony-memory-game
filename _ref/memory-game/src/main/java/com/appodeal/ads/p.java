package com.appodeal.ads;

import android.app.Activity;
import com.appodeal.ads.f.c;
import com.appodeal.ads.f.d;

public class p {

    static class a {
        private final Activity a;
        private String b = "banner";
        private boolean c = false;
        private boolean d = false;

        a(Activity activity) {
            this.a = activity;
        }

        public void a() {
            n.a(this.a, this.b, this.c, this.d);
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
        private String b = "banner";
        private c c = d.a();

        b(Activity activity) {
            this.a = activity;
        }

        boolean a() {
            return n.a(this.a, this.c, this.b);
        }

        public b a(c cVar) {
            this.c = cVar;
            return this;
        }
    }
}
