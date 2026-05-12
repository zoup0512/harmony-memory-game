package com.google.android.gms.analytics;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

@TargetApi(14)
class GoogleAnalytics$zzb implements ActivityLifecycleCallbacks {
    final /* synthetic */ GoogleAnalytics zzcsm;

    GoogleAnalytics$zzb(GoogleAnalytics googleAnalytics) {
        this.zzcsm = googleAnalytics;
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
        this.zzcsm.zzm(activity);
    }

    public void onActivityStopped(Activity activity) {
        this.zzcsm.zzn(activity);
    }
}
