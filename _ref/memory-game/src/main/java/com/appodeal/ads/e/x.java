package com.appodeal.ads.e;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.am;
import com.appodeal.ads.ap;
import com.startapp.android.publish.Ad;
import com.startapp.android.publish.AdDisplayListener;
import com.startapp.android.publish.AdEventListener;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.video.VideoListener;
import java.lang.reflect.Method;

class x implements AdDisplayListener, AdEventListener, VideoListener {
    private final ap a;
    private final int b;
    private final int c;

    x(ap apVar, int i, int i2) {
        this.a = apVar;
        this.b = i;
        this.c = i2;
    }

    public void onReceiveAd(Ad ad) {
        am.a(this.b, this.c, this.a);
    }

    public void onFailedToReceiveAd(Ad ad) {
        am.b(this.b, this.c, this.a);
    }

    public void adDisplayed(Ad ad) {
        this.a.g().a(a(ad));
        am.a(this.b, this.a);
    }

    public void adNotDisplayed(Ad ad) {
    }

    public void adClicked(Ad ad) {
    }

    public void onVideoCompleted() {
        am.b(this.b, this.a);
    }

    public void adHidden(Ad ad) {
        am.d(this.b, this.a);
    }

    private String a(Ad ad) {
        try {
            Method declaredMethod = StartAppAd.class.getDeclaredMethod("getAdHtml", new Class[0]);
            declaredMethod.setAccessible(true);
            return (String) declaredMethod.invoke(ad, new Object[0]);
        } catch (Throwable e) {
            Appodeal.a(e);
            return null;
        }
    }
}
