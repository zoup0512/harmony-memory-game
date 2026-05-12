package com.appodeal.ads.b;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.o;
import com.appodeal.ads.q;
import com.facebook.internal.AnalyticsEvents;
import com.startapp.android.publish.Ad;
import com.startapp.android.publish.AdDisplayListener;
import com.startapp.android.publish.AdEventListener;
import com.startapp.android.publish.StartAppAd;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ag implements AdDisplayListener, AdEventListener {
    private final o a;
    private final int b;
    private final int c;

    ag(o oVar, int i, int i2) {
        this.a = oVar;
        this.b = i;
        this.c = i2;
    }

    public void onReceiveAd(Ad ad) {
        try {
            Method declaredMethod = ((StartAppAd) ad).getClass().getDeclaredMethod("getAdHtml", new Class[0]);
            declaredMethod.setAccessible(true);
            String str = (String) declaredMethod.invoke(ad, new Object[0]);
            this.a.g().a(str);
            Matcher matcher = Pattern.compile("<!-- \\[templateName: (.*?)] -->").matcher(str);
            if (!matcher.find()) {
                q.b(this.b, this.c, this.a);
            } else if (matcher.group(1).contains(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO)) {
                q.b(this.b, this.c, this.a);
            } else {
                q.a(this.b, this.c, this.a);
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public void onFailedToReceiveAd(Ad ad) {
        q.b(this.b, this.c, this.a);
    }

    public void adDisplayed(Ad ad) {
        q.a(this.b, this.a);
    }

    public void adClicked(Ad ad) {
        q.b(this.b, this.a);
    }

    public void adHidden(Ad ad) {
        q.c(this.b, this.a);
    }

    public void adNotDisplayed(Ad ad) {
    }
}
