package com.chartboost.sdk.impl;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.a.c;
import com.chartboost.sdk.f;
import com.mopub.volley.DefaultRetryPolicy;

public final class aw {

    public interface a {
        void a(com.chartboost.sdk.Model.a aVar);
    }

    public enum b {
        CBAnimationTypePerspectiveRotate,
        CBAnimationTypeBounce,
        CBAnimationTypePerspectiveZoom,
        CBAnimationTypeSlideFromTop,
        CBAnimationTypeSlideFromBottom,
        CBAnimationTypeFade,
        CBAnimationTypeNone,
        CBAnimationTypeSlideFromLeft,
        CBAnimationTypeSlideFromRight;

        public static b a(int i) {
            if (i != 0 && i > 0 && i <= values().length) {
                return values()[i - 1];
            }
            return null;
        }
    }

    public static void a(b bVar, com.chartboost.sdk.Model.a aVar, a aVar2) {
        b(bVar, aVar, aVar2, true);
    }

    public static void b(b bVar, com.chartboost.sdk.Model.a aVar, a aVar2) {
        c(bVar, aVar, aVar2, false);
    }

    private static void b(b bVar, com.chartboost.sdk.Model.a aVar, a aVar2, boolean z) {
        if (bVar == b.CBAnimationTypeNone) {
            if (aVar2 != null) {
                aVar2.a(aVar);
            }
        } else if (aVar == null || aVar.n == null) {
            CBLogging.a("AnimationManager", "Transition of impression canceled due to lack of container");
        } else {
            final View f = aVar.n.f();
            if (f == null) {
                f.l().d(aVar);
                CBLogging.a("AnimationManager", "Transition of impression canceled due to lack of view");
                return;
            }
            ViewTreeObserver viewTreeObserver = f.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                final b bVar2 = bVar;
                final com.chartboost.sdk.Model.a aVar3 = aVar;
                final a aVar4 = aVar2;
                final boolean z2 = z;
                viewTreeObserver.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                    public void onGlobalLayout() {
                        f.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        aw.c(bVar2, aVar3, aVar4, z2);
                    }
                });
            }
        }
    }

    private static void c(b bVar, com.chartboost.sdk.Model.a aVar, a aVar2, boolean z) {
        Animation animationSet = new AnimationSet(true);
        animationSet.addAnimation(new AlphaAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        if (aVar == null || aVar.n == null) {
            CBLogging.a("AnimationManager", "Transition of impression canceled due to lack of container");
            if (aVar2 != null) {
                aVar2.a(aVar);
                return;
            }
            return;
        }
        View f = aVar.n.f();
        if (f == null) {
            if (aVar2 != null) {
                aVar2.a(aVar);
            }
            CBLogging.a("AnimationManager", "Transition of impression canceled due to lack of view");
            return;
        }
        View view;
        long j;
        Animation alphaAnimation;
        if (aVar.f == c.INTERSTITIAL_REWARD_VIDEO || aVar.f == c.INTERSTITIAL_VIDEO) {
            view = aVar.n;
        } else {
            view = f;
        }
        float width = (float) view.getWidth();
        float height = (float) view.getHeight();
        float f2 = (DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - 0.4f) / 2.0f;
        if (aVar.a == com.chartboost.sdk.Model.a.b.WEB) {
            j = 500;
        } else {
            j = 500;
        }
        float f3;
        float f4;
        Animation translateAnimation;
        switch (bVar) {
            case CBAnimationTypeFade:
                if (z) {
                    alphaAnimation = new AlphaAnimation(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                } else {
                    alphaAnimation = new AlphaAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f);
                }
                alphaAnimation.setDuration(j);
                alphaAnimation.setFillAfter(true);
                Animation animationSet2 = new AnimationSet(true);
                animationSet2.addAnimation(alphaAnimation);
                alphaAnimation = animationSet2;
                break;
            case CBAnimationTypePerspectiveZoom:
                if (z) {
                    alphaAnimation = new bb(-1114636288, 0.0f, width / 2.0f, height / 2.0f, false);
                } else {
                    alphaAnimation = new bb(0.0f, 60.0f, width / 2.0f, height / 2.0f, false);
                }
                alphaAnimation.setDuration(j);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                if (z) {
                    alphaAnimation = new ScaleAnimation(0.4f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.4f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                } else {
                    alphaAnimation = new ScaleAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.4f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.4f);
                }
                alphaAnimation.setDuration(j);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                if (z) {
                    alphaAnimation = new TranslateAnimation(width * f2, 0.0f, (-height) * 0.4f, 0.0f);
                } else {
                    alphaAnimation = new TranslateAnimation(0.0f, width * f2, 0.0f, height);
                }
                alphaAnimation.setDuration(j);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                alphaAnimation = animationSet;
                break;
            case CBAnimationTypePerspectiveRotate:
                if (z) {
                    alphaAnimation = new bb(-1114636288, 0.0f, width / 2.0f, height / 2.0f, true);
                } else {
                    alphaAnimation = new bb(0.0f, 60.0f, width / 2.0f, height / 2.0f, true);
                }
                alphaAnimation.setDuration(j);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                if (z) {
                    alphaAnimation = new ScaleAnimation(0.4f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.4f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                } else {
                    alphaAnimation = new ScaleAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.4f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.4f);
                }
                alphaAnimation.setDuration(j);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                if (z) {
                    alphaAnimation = new TranslateAnimation((-width) * 0.4f, 0.0f, height * f2, 0.0f);
                } else {
                    alphaAnimation = new TranslateAnimation(0.0f, width, 0.0f, height * f2);
                }
                alphaAnimation.setDuration(j);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                alphaAnimation = animationSet;
                break;
            case CBAnimationTypeSlideFromBottom:
                f3 = z ? height : 0.0f;
                if (z) {
                    f4 = 0.0f;
                } else {
                    f4 = height;
                }
                translateAnimation = new TranslateAnimation(0.0f, 0.0f, f3, f4);
                translateAnimation.setDuration(j);
                translateAnimation.setFillAfter(true);
                animationSet.addAnimation(translateAnimation);
                alphaAnimation = animationSet;
                break;
            case CBAnimationTypeSlideFromTop:
                translateAnimation = new TranslateAnimation(0.0f, 0.0f, z ? -height : 0.0f, z ? 0.0f : -height);
                translateAnimation.setDuration(j);
                translateAnimation.setFillAfter(true);
                animationSet.addAnimation(translateAnimation);
                alphaAnimation = animationSet;
                break;
            case CBAnimationTypeSlideFromLeft:
                f3 = z ? width : 0.0f;
                if (z) {
                    f4 = 0.0f;
                } else {
                    f4 = width;
                }
                translateAnimation = new TranslateAnimation(f3, f4, 0.0f, 0.0f);
                translateAnimation.setDuration(j);
                translateAnimation.setFillAfter(true);
                animationSet.addAnimation(translateAnimation);
                alphaAnimation = animationSet;
                break;
            case CBAnimationTypeSlideFromRight:
                translateAnimation = new TranslateAnimation(z ? -width : 0.0f, z ? 0.0f : -width, 0.0f, 0.0f);
                translateAnimation.setDuration(j);
                translateAnimation.setFillAfter(true);
                animationSet.addAnimation(translateAnimation);
                alphaAnimation = animationSet;
                break;
            case CBAnimationTypeBounce:
                if (!z) {
                    alphaAnimation = new ScaleAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f, 1, 0.5f, 1, 0.5f);
                    alphaAnimation.setDuration(j);
                    alphaAnimation.setStartOffset(0);
                    alphaAnimation.setFillAfter(true);
                    animationSet.addAnimation(alphaAnimation);
                    alphaAnimation = animationSet;
                    break;
                }
                alphaAnimation = new ScaleAnimation(0.6f, 1.1f, 0.6f, 1.1f, 1, 0.5f, 1, 0.5f);
                alphaAnimation.setDuration((long) Math.round(((float) j) * 0.6f));
                alphaAnimation.setStartOffset(0);
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                alphaAnimation = new ScaleAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.81818175f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.81818175f, 1, 0.5f, 1, 0.5f);
                alphaAnimation.setDuration((long) Math.round(((float) j) * 0.19999999f));
                alphaAnimation.setStartOffset((long) Math.round(((float) j) * 0.6f));
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                alphaAnimation = new ScaleAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 1.1111112f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 1.1111112f, 1, 0.5f, 1, 0.5f);
                alphaAnimation.setDuration((long) Math.round(((float) j) * 0.099999964f));
                alphaAnimation.setStartOffset((long) Math.round(((float) j) * 0.8f));
                alphaAnimation.setFillAfter(true);
                animationSet.addAnimation(alphaAnimation);
                alphaAnimation = animationSet;
                break;
            default:
                alphaAnimation = animationSet;
                break;
        }
        if (bVar != b.CBAnimationTypeNone) {
            if (aVar2 != null) {
                final a aVar3 = aVar2;
                final com.chartboost.sdk.Model.a aVar4 = aVar;
                CBUtility.c().postDelayed(new Runnable() {
                    public void run() {
                        aVar3.a(aVar4);
                    }
                }, j);
            }
            view.startAnimation(alphaAnimation);
        } else if (aVar2 != null) {
            aVar2.a(aVar);
        }
    }

    public static void a(boolean z, View view) {
        if (com.chartboost.sdk.c.G().booleanValue()) {
            a(z, view, 500);
        } else {
            a(z, view, 500);
        }
    }

    public static void a(boolean z, View view, long j) {
        float f = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        view.clearAnimation();
        if (z) {
            view.setVisibility(0);
        }
        float f2 = z ? 0.0f : DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        if (!z) {
            f = 0.0f;
        }
        Animation alphaAnimation = new AlphaAnimation(f2, f);
        alphaAnimation.setDuration(j);
        alphaAnimation.setFillBefore(true);
        view.startAnimation(alphaAnimation);
    }
}
