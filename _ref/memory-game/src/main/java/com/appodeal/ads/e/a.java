package com.appodeal.ads.e;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.ak;
import com.appodeal.ads.am;
import com.appodeal.ads.an;
import com.appodeal.ads.ap;
import com.appodeal.ads.aq;
import com.appodeal.ads.ar;
import com.jirbo.adcolony.AdColonyV4VCAd;
import com.my.target.nativeads.banners.NavigationType;
import java.util.HashSet;

public class a extends aq {
    public static com.appodeal.ads.ao.a b = com.appodeal.ads.ao.a.NOT_AVAILABLE;
    public static HashSet<String> c = new HashSet();
    private static ap d;
    private AdColonyV4VCAd e;

    public static ap getInstance(String str, String[] strArr) {
        if (d == null) {
            aq aqVar = null;
            if (an.a(strArr)) {
                aqVar = new a();
            }
            d = new ap(str, h(), aqVar);
        }
        return d;
    }

    private static String[] h() {
        return new String[]{"com.jirbo.adcolony.AdColonyBrowser", "com.jirbo.adcolony.AdColonyFullscreen", "com.jirbo.adcolony.AdColonyOverlay"};
    }

    public void a(Activity activity, int i, int i2) {
        if (VERSION.SDK_INT < 14) {
            am.b(i, i2, d);
            return;
        }
        String string = ((ar) ak.m.get(i)).l.getString("zone_id");
        com.appodeal.ads.networks.a.a(activity, ((ar) ak.m.get(i)).l.getString(NavigationType.STORE), ((ar) ak.m.get(i)).l.getString("app_id"), ((ar) ak.m.get(i)).l.optJSONObject("zones"), string);
        if (com.appodeal.ads.networks.a.a) {
            am.b(i, i2, d);
            return;
        }
        this.e = new AdColonyV4VCAd(string).withListener(new b(d, i));
        if (this.e.isReady()) {
            am.a(i, i2, d);
        } else if (b == com.appodeal.ads.ao.a.NOT_AVAILABLE_AFTER_DELAY) {
            am.b(i, i2, d);
        } else {
            final HandlerThread handlerThread = new HandlerThread("AdcolonyThread");
            handlerThread.start();
            final Handler handler = new Handler(handlerThread.getLooper());
            final int i3 = i;
            final int i4 = i2;
            handler.postDelayed(new Runnable(this) {
                int a = 0;
                final /* synthetic */ a f;

                public void run() {
                    try {
                        if (this.f.e.isReady()) {
                            am.a(i3, i4, a.d);
                            handlerThread.quit();
                        } else if (this.a < 30) {
                            handler.postDelayed(this, 1000);
                        } else {
                            a.b = com.appodeal.ads.ao.a.NOT_AVAILABLE_AFTER_DELAY;
                            am.b(i3, i4, a.d);
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
        if (this.e.isReady()) {
            this.e.show();
        }
    }

    public boolean d() {
        return com.appodeal.ads.networks.a.a;
    }

    public void b(boolean z) {
        com.appodeal.ads.networks.a.a = z;
    }
}
