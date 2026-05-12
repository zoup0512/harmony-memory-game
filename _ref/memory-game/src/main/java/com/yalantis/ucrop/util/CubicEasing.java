package com.yalantis.ucrop.util;

import com.mopub.volley.DefaultRetryPolicy;

public final class CubicEasing {
    public static float easeOut(float time, float start, float end, float duration) {
        time = (time / duration) - DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        return ((((time * time) * time) + DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) * end) + start;
    }

    public static float easeIn(float time, float start, float end, float duration) {
        time /= duration;
        return (((end * time) * time) * time) + start;
    }

    public static float easeInOut(float time, float start, float end, float duration) {
        time /= duration / 2.0f;
        if (time < DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
            return ((((end / 2.0f) * time) * time) * time) + start;
        }
        time -= 2.0f;
        return ((end / 2.0f) * (((time * time) * time) + 2.0f)) + start;
    }
}
