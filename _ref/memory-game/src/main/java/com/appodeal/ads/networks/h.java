package com.appodeal.ads.networks;

import com.appodeal.ads.aj;
import com.appodeal.ads.am;
import com.appodeal.ads.ap;
import com.appodeal.ads.o;
import com.appodeal.ads.q;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.ChartboostDelegate;
import com.chartboost.sdk.Model.CBError.CBImpressionError;

public class h extends ChartboostDelegate {
    private static h a;
    private o b;
    private int c;
    private int d;
    private ap e;
    private int f;
    private int g;
    private ap h;
    private int i;
    private int j;

    public static h a() {
        if (a == null) {
            a = new h();
        }
        return a;
    }

    public h a(o oVar, int i, int i2) {
        this.b = oVar;
        this.c = i;
        this.d = i2;
        return this;
    }

    public h a(ap apVar, int i, int i2) {
        this.e = apVar;
        this.f = i;
        this.g = i2;
        return this;
    }

    public h b(ap apVar, int i, int i2) {
        this.h = apVar;
        this.i = i;
        this.j = i2;
        return this;
    }

    public void didCacheInterstitial(String str) {
        if (Chartboost.hasInterstitial(str)) {
            if (str == null || !str.equals("Video")) {
                q.a(this.c, this.d, this.b);
            } else {
                aj.a(this.i, this.j, this.h);
            }
        } else if (str == null || !str.equals("Video")) {
            q.b(this.c, this.d, this.b);
        } else {
            aj.b(this.i, this.j, this.h);
        }
    }

    public void didFailToLoadInterstitial(String str, CBImpressionError cBImpressionError) {
        if (str == null || !str.equals("Video")) {
            q.b(this.c, this.d, this.b);
        } else {
            aj.b(this.i, this.j, this.h);
        }
    }

    public void didDisplayInterstitial(String str) {
        if (str == null || !str.equals("Video")) {
            q.a(this.c, this.b);
        } else {
            aj.a(this.i, this.h);
        }
    }

    public void didClickInterstitial(String str) {
        if (str == null || !str.equals("Video")) {
            q.b(this.c, this.b);
        } else {
            aj.c(this.i, this.h);
        }
    }

    public void didCloseInterstitial(String str) {
        if (str == null || !str.equals("Video")) {
            q.c(this.c, this.b);
        } else {
            aj.d(this.i, this.h);
        }
    }

    public void didCacheRewardedVideo(String str) {
        if (Chartboost.hasRewardedVideo(str)) {
            am.a(this.f, this.g, this.e);
        } else {
            am.b(this.f, this.g, this.e);
        }
    }

    public void didFailToLoadRewardedVideo(String str, CBImpressionError cBImpressionError) {
        am.b(this.f, this.g, this.e);
    }

    public void didDisplayRewardedVideo(String str) {
        am.a(this.f, this.e);
    }

    public void didCompleteRewardedVideo(String str, int i) {
        am.b(this.f, this.e);
    }

    public void didClickRewardedVideo(String str) {
    }

    public void didCloseRewardedVideo(String str) {
        am.d(this.f, this.e);
    }
}
