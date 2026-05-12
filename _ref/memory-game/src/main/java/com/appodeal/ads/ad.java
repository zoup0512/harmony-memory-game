package com.appodeal.ads;

import android.app.Activity;
import com.facebook.internal.AnalyticsEvents;

public class ad {

    static class a {
        private final Activity a;
        private String b = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE;

        a(Activity activity) {
            this.a = activity;
        }

        public void a() {
            Native.a(this.a, this.b);
        }
    }
}
