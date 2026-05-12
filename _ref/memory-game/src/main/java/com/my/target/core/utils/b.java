package com.my.target.core.utils;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import com.mopub.volley.DefaultRetryPolicy;

/* compiled from: AnimationUtils */
public final class b {
    public static Animation a() {
        Animation translateAnimation = new TranslateAnimation(2, 0.0f, 2, 0.0f, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 2, 0.0f);
        translateAnimation.setDuration(400);
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        return translateAnimation;
    }

    public static Animation b() {
        Animation translateAnimation = new TranslateAnimation(2, 0.0f, 2, 0.0f, 2, 0.0f, 2, -1.0f);
        translateAnimation.setDuration(400);
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        return translateAnimation;
    }
}
