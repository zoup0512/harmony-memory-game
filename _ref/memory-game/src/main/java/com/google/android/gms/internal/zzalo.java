package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.firebase.FirebaseApp;
import java.util.concurrent.atomic.AtomicBoolean;

@TargetApi(14)
public class zzalo implements ActivityLifecycleCallbacks, ComponentCallbacks2 {
    private static final zzalo baJ = new zzalo();
    private final AtomicBoolean baK = new AtomicBoolean();
    private boolean zzcwq;

    private zzalo() {
    }

    public static void zza(Application application) {
        application.registerActivityLifecycleCallbacks(baJ);
        application.registerComponentCallbacks(baJ);
        baJ.zzcwq = true;
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (this.baK.compareAndSet(true, false)) {
            FirebaseApp.zzcl(false);
        }
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
        if (this.baK.compareAndSet(true, false)) {
            FirebaseApp.zzcl(false);
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onLowMemory() {
    }

    public void onTrimMemory(int i) {
        if (i == 20 && this.baK.compareAndSet(false, true)) {
            FirebaseApp.zzcl(true);
        }
    }
}
