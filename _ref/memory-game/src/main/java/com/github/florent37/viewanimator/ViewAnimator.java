package com.github.florent37.viewanimator;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.support.annotation.IntRange;
import android.view.View;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.Interpolator;
import com.github.florent37.viewanimator.AnimationListener.Start;
import com.github.florent37.viewanimator.AnimationListener.Stop;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class ViewAnimator {
    private static final long DEFAULT_DURATION = 3000;
    private List<AnimationBuilder> animationList = new ArrayList();
    private AnimatorSet animatorSet;
    private long duration = DEFAULT_DURATION;
    private Interpolator interpolator = null;
    private ViewAnimator next = null;
    private ViewAnimator prev = null;
    private int repeatCount = 0;
    private int repeatMode = 1;
    private long startDelay = 0;
    private Start startListener;
    private Stop stopListener;
    private View waitForThisViewHeight = null;

    @Retention(RetentionPolicy.SOURCE)
    public @interface RepeatMode {
    }

    public static AnimationBuilder animate(View... view) {
        return new ViewAnimator().addAnimationBuilder(view);
    }

    public AnimationBuilder thenAnimate(View... views) {
        ViewAnimator nextViewAnimator = new ViewAnimator();
        this.next = nextViewAnimator;
        nextViewAnimator.prev = this;
        return nextViewAnimator.addAnimationBuilder(views);
    }

    public AnimationBuilder addAnimationBuilder(View... views) {
        AnimationBuilder animationBuilder = new AnimationBuilder(this, views);
        this.animationList.add(animationBuilder);
        return animationBuilder;
    }

    protected AnimatorSet createAnimatorSet() {
        List<Animator> animators = new ArrayList();
        for (AnimationBuilder animationBuilder : this.animationList) {
            List<Animator> animatorList = animationBuilder.createAnimators();
            if (animationBuilder.getSingleInterpolator() != null) {
                for (Animator animator : animatorList) {
                    animator.setInterpolator(animationBuilder.getSingleInterpolator());
                }
            }
            animators.addAll(animatorList);
        }
        for (AnimationBuilder animationBuilder2 : this.animationList) {
            if (animationBuilder2.isWaitForHeight()) {
                this.waitForThisViewHeight = animationBuilder2.getView();
                break;
            }
        }
        for (Animator animator2 : animators) {
            if (animator2 instanceof ValueAnimator) {
                ValueAnimator valueAnimator = (ValueAnimator) animator2;
                valueAnimator.setRepeatCount(this.repeatCount);
                valueAnimator.setRepeatMode(this.repeatMode);
            }
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animators);
        animatorSet.setDuration(this.duration);
        animatorSet.setStartDelay(this.startDelay);
        if (this.interpolator != null) {
            animatorSet.setInterpolator(this.interpolator);
        }
        animatorSet.addListener(new AnimatorListener() {
            public void onAnimationStart(Animator animation) {
                if (ViewAnimator.this.startListener != null) {
                    ViewAnimator.this.startListener.onStart();
                }
            }

            public void onAnimationEnd(Animator animation) {
                if (ViewAnimator.this.stopListener != null) {
                    ViewAnimator.this.stopListener.onStop();
                }
                if (ViewAnimator.this.next != null) {
                    ViewAnimator.this.next.prev = null;
                    ViewAnimator.this.next.start();
                }
            }

            public void onAnimationCancel(Animator animation) {
            }

            public void onAnimationRepeat(Animator animation) {
            }
        });
        return animatorSet;
    }

    public ViewAnimator start() {
        if (this.prev != null) {
            this.prev.start();
        } else {
            this.animatorSet = createAnimatorSet();
            if (this.waitForThisViewHeight != null) {
                this.waitForThisViewHeight.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
                    public boolean onPreDraw() {
                        ViewAnimator.this.animatorSet.start();
                        ViewAnimator.this.waitForThisViewHeight.getViewTreeObserver().removeOnPreDrawListener(this);
                        return false;
                    }
                });
            } else {
                this.animatorSet.start();
            }
        }
        return this;
    }

    public void cancel() {
        if (this.animatorSet != null) {
            this.animatorSet.cancel();
        }
        if (this.next != null) {
            this.next.cancel();
            this.next = null;
        }
    }

    public ViewAnimator duration(long duration) {
        this.duration = duration;
        return this;
    }

    public ViewAnimator startDelay(long startDelay) {
        this.startDelay = startDelay;
        return this;
    }

    public ViewAnimator repeatCount(@IntRange(from = -1) int repeatCount) {
        this.repeatCount = repeatCount;
        return this;
    }

    public ViewAnimator repeatMode(int repeatMode) {
        this.repeatMode = repeatMode;
        return this;
    }

    public ViewAnimator onStart(Start startListener) {
        this.startListener = startListener;
        return this;
    }

    public ViewAnimator onStop(Stop stopListener) {
        this.stopListener = stopListener;
        return this;
    }

    public ViewAnimator interpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
        return this;
    }
}
