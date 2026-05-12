package com.yandex.metrica.impl;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

public class m implements ActivityLifecycleCallbacks {
    private z a;

    public m(z zVar) {
        this.a = zVar;
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
        this.a.a(activity);
    }

    public void onActivityPaused(Activity activity) {
        this.a.b(activity);
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    public void onActivityDestroyed(Activity activity) {
    }
}
