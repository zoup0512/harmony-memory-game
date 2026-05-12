package com.appodeal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import com.appodeal.ads.utils.d;
import com.appodeal.ads.v.c;

@TargetApi(14)
class a implements ActivityLifecycleCallbacks, ComponentCallbacks {
    private long a;
    private long b;

    a() {
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
        d.a();
        try {
            this.a = System.currentTimeMillis();
            if (Appodeal.a) {
                ar a;
                Appodeal.a = false;
                if (n.m && n.c) {
                    s a2 = n.a();
                    if (a2 == null || a2.b()) {
                        n.b(activity);
                    }
                }
                if (ah.k && ah.b) {
                    a = ah.a();
                    if (a == null || a.b()) {
                        ah.b(activity);
                    }
                }
                if (ak.k && ak.b) {
                    a = ak.a();
                    if (a == null || a.b()) {
                        ak.b(activity);
                    }
                }
                if (g.l && g.b) {
                    l a3 = g.a();
                    if (a3 == null || a3.b()) {
                        if (g.w == g.d.HIDDEN || g.w == g.d.NEVER_SHOWN) {
                            g.b(activity);
                        } else {
                            new a(activity).b().a(g.r).a();
                        }
                    }
                }
                if (v.l && v.b) {
                    aa a4 = v.a();
                    if (a4 == null || a4.b()) {
                        if (v.s == c.HIDDEN || v.s == c.NEVER_SHOWN) {
                            v.b(activity);
                        } else {
                            new a(activity).b().a();
                        }
                    }
                }
                Appodeal.a("Appodeal resumed");
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public void onActivityPaused(Activity activity) {
        d.b((Context) activity);
        try {
            this.b = System.currentTimeMillis();
            final HandlerThread handlerThread = new HandlerThread("ActivityPausedThread");
            handlerThread.start();
            final long j = this.b;
            new Handler(handlerThread.getLooper()).postDelayed(new Runnable(this) {
                final /* synthetic */ a c;

                public void run() {
                    if (j == this.c.b && this.c.a < this.c.b) {
                        Appodeal.a = true;
                        Appodeal.a("Appodeal paused");
                    }
                    handlerThread.quit();
                }
            }, 1000);
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onConfigurationChanged(Configuration configuration) {
        l a = g.a();
        if (a != null && a.a(configuration) && g.w == g.d.VISIBLE) {
            new a(Appodeal.b).b().a(g.r).a();
        }
    }

    public void onLowMemory() {
    }
}
