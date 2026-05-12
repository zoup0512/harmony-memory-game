package com.flurry.sdk;

import android.app.Activity;
import java.lang.ref.WeakReference;

public final class kb extends kg {
    public WeakReference<Activity> a = new WeakReference(null);
    public a b;

    public enum a {
        kCreated,
        kDestroyed,
        kPaused,
        kResumed,
        kStarted,
        kStopped,
        kSaveState
    }

    public kb() {
        super("com.flurry.android.sdk.ActivityLifecycleEvent");
    }
}
