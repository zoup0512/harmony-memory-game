package com.appodeal.ads;

import android.app.Activity;
import com.appodeal.ads.f.c;
import com.appodeal.ads.f.d;
import com.mopub.common.AdType;

public class al {

    static class a {
        private final Activity a;
        private String b = AdType.REWARDED_VIDEO;
        private boolean c = false;

        a(Activity activity) {
            this.a = activity;
        }

        public void a() {
            ak.a(this.a, this.b, this.c);
        }

        public a b() {
            this.c = true;
            return this;
        }
    }

    static class b {
        private final Activity a;
        private String b = AdType.REWARDED_VIDEO;
        private c c = d.a();

        b(Activity activity) {
            this.a = activity;
        }

        boolean a() {
            return ak.a(this.a, this.c, this.b);
        }

        public b a(c cVar) {
            this.c = cVar;
            return this;
        }
    }
}
