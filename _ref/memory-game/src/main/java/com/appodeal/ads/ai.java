package com.appodeal.ads;

import android.app.Activity;
import com.appodeal.ads.f.c;
import com.appodeal.ads.f.d;
import com.facebook.internal.AnalyticsEvents;

public class ai {

    static class a {
        private final Activity a;
        private String b = AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO;
        private boolean c = false;

        a(Activity activity) {
            this.a = activity;
        }

        public void a() {
            ah.a(this.a, this.b, this.c);
        }

        public a b() {
            this.c = true;
            return this;
        }
    }

    static class b {
        private final Activity a;
        private String b = AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO;
        private c c = d.a();

        b(Activity activity) {
            this.a = activity;
        }

        boolean a() {
            return ah.a(this.a, this.c, this.b);
        }

        public b a(c cVar) {
            this.c = cVar;
            return this;
        }
    }
}
