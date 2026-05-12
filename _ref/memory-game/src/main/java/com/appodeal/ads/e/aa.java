package com.appodeal.ads.e;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.AppodealSettings;
import com.appodeal.ads.ak;
import com.appodeal.ads.am;
import com.appodeal.ads.an;
import com.appodeal.ads.ao.a;
import com.appodeal.ads.ap;
import com.appodeal.ads.aq;
import com.appodeal.ads.ar;
import com.unity3d.ads.UnityAds;

public class aa extends aq {
    public static a b = a.NOT_AVAILABLE;
    private static ap c;
    private static String d = "rewardedVideoZone";
    private static boolean e = false;

    public static ap getInstance(String str, String[] strArr) {
        if (c == null) {
            aq aqVar = null;
            if (an.a(strArr)) {
                aqVar = new aa();
            }
            c = new ap(str, i(), aqVar);
        }
        return c;
    }

    private static String[] i() {
        return new String[]{"com.unity3d.ads.adunit.AdUnitActivity", "com.unity3d.ads.adunit.AdUnitSoftwareActivity"};
    }

    public void a(Activity activity, int i, int i2) {
        final Object abVar = new ab(c, i);
        UnityAds.initialize(activity, ((ar) ak.m.get(i)).l.getString("app_id"), abVar, AppodealSettings.a);
        if (((ar) ak.m.get(i)).l.has("zone_id")) {
            d = ((ar) ak.m.get(i)).l.getString("zone_id");
        }
        if (e) {
            am.b(i, i2, c);
        } else if (UnityAds.isReady(d)) {
            am.a(i, i2, c);
            UnityAds.setListener(abVar);
        } else if (b == a.NOT_AVAILABLE_AFTER_DELAY) {
            am.b(i, i2, c);
        } else {
            final HandlerThread handlerThread = new HandlerThread("UnityAdsThread");
            handlerThread.start();
            final Handler handler = new Handler(handlerThread.getLooper());
            final int i3 = i;
            final int i4 = i2;
            handler.postDelayed(new Runnable(this) {
                int a = 0;
                final /* synthetic */ aa g;

                public void run() {
                    try {
                        if (UnityAds.isReady(aa.d)) {
                            am.a(i3, i4, aa.c);
                            UnityAds.setListener(abVar);
                            handlerThread.quit();
                        } else if (this.a < 30) {
                            handler.postDelayed(this, 1000);
                        } else {
                            aa.b = a.NOT_AVAILABLE_AFTER_DELAY;
                            am.b(i3, i4, aa.c);
                            handlerThread.quit();
                        }
                    } catch (Throwable e) {
                        Appodeal.a(e);
                    }
                    this.a++;
                }
            }, 1000);
        }
    }

    public void a(Activity activity, int i) {
        if (UnityAds.isReady(d)) {
            UnityAds.show(activity, d);
        }
    }

    public boolean d() {
        return e;
    }

    public void b(boolean z) {
        e = z;
    }

    public boolean e() {
        return true;
    }
}
