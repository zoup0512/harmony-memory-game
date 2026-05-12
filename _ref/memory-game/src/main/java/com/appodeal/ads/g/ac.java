package com.appodeal.ads.g;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.ah;
import com.appodeal.ads.aj;
import com.appodeal.ads.an;
import com.appodeal.ads.ao.a;
import com.appodeal.ads.ao.b;
import com.appodeal.ads.ap;
import com.appodeal.ads.aq;
import com.appodeal.ads.ar;
import com.appodeal.ads.networks.w;
import com.vungle.publisher.EventListener;
import com.vungle.publisher.VunglePub;

public class ac extends aq {
    public static a b = a.NOT_AVAILABLE;
    private static ap c;
    private static boolean d = false;
    private static boolean e = false;

    public static ap getInstance(String str, String[] strArr) {
        if (c == null) {
            aq aqVar = null;
            if (an.a(strArr)) {
                aqVar = new ac();
            }
            c = new ap(str, h(), aqVar).a(18);
        }
        return c;
    }

    private static String[] h() {
        return new String[]{"com.vungle.publisher.FullScreenAdActivity"};
    }

    public void a(Activity activity, int i, int i2) {
        if (VERSION.SDK_INT < 11) {
            aj.b(i, i2, c);
            return;
        }
        w.a(b.NON_REWARDED);
        if (w.a != b.NON_REWARDED) {
            aj.b(i, i2, c);
            return;
        }
        if (!d) {
            VunglePub.getInstance().init(activity, ((ar) ah.m.get(i)).l.getString("app_id"));
            d = true;
        }
        ad adVar = new ad(c, i);
        VunglePub.getInstance().setEventListeners(new EventListener[]{adVar});
        if (e) {
            aj.b(i, i2, c);
        } else if (VunglePub.getInstance().isAdPlayable()) {
            aj.a(i, i2, c);
        } else if (b == a.NOT_AVAILABLE_AFTER_DELAY) {
            aj.b(i, i2, c);
        } else {
            final HandlerThread handlerThread = new HandlerThread("VungleThread");
            handlerThread.start();
            final Handler handler = new Handler(handlerThread.getLooper());
            final int i3 = i;
            final int i4 = i2;
            handler.postDelayed(new Runnable(this) {
                int a = 0;
                final /* synthetic */ ac f;

                public void run() {
                    try {
                        if (VunglePub.getInstance().isAdPlayable()) {
                            aj.a(i3, i4, ac.c);
                            handlerThread.quit();
                        } else if (this.a < 30) {
                            handler.postDelayed(this, 1000);
                        } else {
                            ac.b = a.NOT_AVAILABLE_AFTER_DELAY;
                            aj.b(i3, i4, ac.c);
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
        VunglePub.getInstance().onResume();
        if (VunglePub.getInstance().isAdPlayable()) {
            VunglePub.getInstance().playAd();
        }
    }

    public boolean d() {
        return e;
    }

    public void b(boolean z) {
        e = z;
    }
}
