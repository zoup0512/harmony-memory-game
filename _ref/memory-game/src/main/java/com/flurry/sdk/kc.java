package com.flurry.sdk;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.flurry.sdk.kb.a;
import java.lang.ref.WeakReference;

public class kc {
    private static final String a = kc.class.getSimpleName();
    private static kc b;
    private ActivityLifecycleCallbacks c;

    private kc() {
        if (VERSION.SDK_INT >= 14 && this.c == null) {
            Context context = jy.a().a;
            if (context instanceof Application) {
                this.c = new ActivityLifecycleCallbacks(this) {
                    final /* synthetic */ kc a;

                    {
                        this.a = r1;
                    }

                    public final void onActivityCreated(Activity activity, Bundle bundle) {
                        km.a(3, kc.a, "onActivityCreated for activity:" + activity);
                        AnonymousClass1.a(activity, a.kCreated);
                    }

                    public final void onActivityStarted(Activity activity) {
                        km.a(3, kc.a, "onActivityStarted for activity:" + activity);
                        AnonymousClass1.a(activity, a.kStarted);
                    }

                    public final void onActivityResumed(Activity activity) {
                        km.a(3, kc.a, "onActivityResumed for activity:" + activity);
                        AnonymousClass1.a(activity, a.kResumed);
                    }

                    public final void onActivityPaused(Activity activity) {
                        km.a(3, kc.a, "onActivityPaused for activity:" + activity);
                        AnonymousClass1.a(activity, a.kPaused);
                    }

                    public final void onActivityStopped(Activity activity) {
                        km.a(3, kc.a, "onActivityStopped for activity:" + activity);
                        AnonymousClass1.a(activity, a.kStopped);
                    }

                    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                        km.a(3, kc.a, "onActivitySaveInstanceState for activity:" + activity);
                        AnonymousClass1.a(activity, a.kSaveState);
                    }

                    public final void onActivityDestroyed(Activity activity) {
                        km.a(3, kc.a, "onActivityDestroyed for activity:" + activity);
                        AnonymousClass1.a(activity, a.kDestroyed);
                    }

                    private static void a(Activity activity, a aVar) {
                        kb kbVar = new kb();
                        kbVar.a = new WeakReference(activity);
                        kbVar.b = aVar;
                        kbVar.b();
                    }
                };
                ((Application) context).registerActivityLifecycleCallbacks(this.c);
            }
        }
    }

    public static synchronized kc a() {
        kc kcVar;
        synchronized (kc.class) {
            if (b == null) {
                b = new kc();
            }
            kcVar = b;
        }
        return kcVar;
    }

    public final boolean b() {
        return this.c != null;
    }
}
