package com.github.florent37.viewanimator;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.IntRange;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.TextView;
import com.github.florent37.viewanimator.AnimationListener.Start;
import com.github.florent37.viewanimator.AnimationListener.Stop;
import com.github.florent37.viewanimator.AnimationListener.Update;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;
import com.mopub.volley.DefaultRetryPolicy;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.List;

public class AnimationBuilder {
    private final List<Animator> animatorList = new ArrayList();
    private boolean nextValueWillBeDp = false;
    private Interpolator singleInterpolator = null;
    private final ViewAnimator viewAnimator;
    private final View[] views;
    private boolean waitForHeight;

    public AnimationBuilder(ViewAnimator viewAnimator, View... views) {
        this.viewAnimator = viewAnimator;
        this.views = views;
    }

    public AnimationBuilder dp() {
        this.nextValueWillBeDp = true;
        return this;
    }

    protected AnimationBuilder add(Animator animator) {
        this.animatorList.add(animator);
        return this;
    }

    protected float toDp(float px) {
        return px / this.views[0].getContext().getResources().getDisplayMetrics().density;
    }

    protected float toPx(float dp) {
        return this.views[0].getContext().getResources().getDisplayMetrics().density * dp;
    }

    protected float[] getValues(float... values) {
        if (!this.nextValueWillBeDp) {
            return values;
        }
        float[] pxValues = new float[values.length];
        for (int i = 0; i < values.length; i++) {
            pxValues[i] = toPx(values[i]);
        }
        return pxValues;
    }

    public AnimationBuilder property(String propertyName, float... values) {
        for (View view : this.views) {
            this.animatorList.add(ObjectAnimator.ofFloat(view, propertyName, getValues(values)));
        }
        return this;
    }

    public AnimationBuilder translationY(float... y) {
        return property("translationY", y);
    }

    public AnimationBuilder translationX(float... x) {
        return property("translationX", x);
    }

    public AnimationBuilder alpha(float... alpha) {
        return property("alpha", alpha);
    }

    public AnimationBuilder scaleX(float... scaleX) {
        return property("scaleX", scaleX);
    }

    public AnimationBuilder scaleY(float... scaleY) {
        return property("scaleY", scaleY);
    }

    public AnimationBuilder scale(float... scale) {
        scaleX(scale);
        scaleY(scale);
        return this;
    }

    public AnimationBuilder pivotX(float pivotX) {
        for (View view : this.views) {
            ViewCompat.setPivotX(view, pivotX);
        }
        return this;
    }

    public AnimationBuilder pivotY(float pivotY) {
        for (View view : this.views) {
            ViewCompat.setPivotY(view, pivotY);
        }
        return this;
    }

    public AnimationBuilder pivotX(float... pivotX) {
        ObjectAnimator.ofFloat(getView(), "pivotX", getValues(pivotX));
        return this;
    }

    public AnimationBuilder pivotY(float... pivotY) {
        ObjectAnimator.ofFloat(getView(), "pivotY", getValues(pivotY));
        return this;
    }

    public AnimationBuilder rotationX(float... rotationX) {
        return property("rotationX", rotationX);
    }

    public AnimationBuilder rotationY(float... rotationY) {
        return property("rotationY", rotationY);
    }

    public AnimationBuilder rotation(float... rotation) {
        return property("rotation", rotation);
    }

    public AnimationBuilder backgroundColor(int... colors) {
        for (View view : this.views) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofInt(view, "backgroundColor", colors);
            objectAnimator.setEvaluator(new ArgbEvaluator());
            this.animatorList.add(objectAnimator);
        }
        return this;
    }

    public AnimationBuilder textColor(int... colors) {
        for (View view : this.views) {
            if (view instanceof TextView) {
                ObjectAnimator objectAnimator = ObjectAnimator.ofInt(view, "textColor", colors);
                objectAnimator.setEvaluator(new ArgbEvaluator());
                this.animatorList.add(objectAnimator);
            }
        }
        return this;
    }

    public AnimationBuilder custom(Update update, float... values) {
        for (View view : this.views) {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(getValues(values));
            if (update != null) {
                valueAnimator.addUpdateListener(new 1(this, update, view));
            }
            add(valueAnimator);
        }
        return this;
    }

    public AnimationBuilder height(float... height) {
        return custom(new 2(this), height);
    }

    public AnimationBuilder width(float... width) {
        return custom(new 3(this), width);
    }

    public AnimationBuilder waitForHeight() {
        this.waitForHeight = true;
        return this;
    }

    protected List<Animator> createAnimators() {
        return this.animatorList;
    }

    public AnimationBuilder andAnimate(View... views) {
        return this.viewAnimator.addAnimationBuilder(views);
    }

    public AnimationBuilder thenAnimate(View... views) {
        return this.viewAnimator.thenAnimate(views);
    }

    public AnimationBuilder duration(long duration) {
        this.viewAnimator.duration(duration);
        return this;
    }

    public AnimationBuilder startDelay(long startDelay) {
        this.viewAnimator.startDelay(startDelay);
        return this;
    }

    public AnimationBuilder repeatCount(@IntRange(from = -1) int repeatCount) {
        this.viewAnimator.repeatCount(repeatCount);
        return this;
    }

    public AnimationBuilder repeatMode(int repeatMode) {
        this.viewAnimator.repeatMode(repeatMode);
        return this;
    }

    public AnimationBuilder onStart(Start startListener) {
        this.viewAnimator.onStart(startListener);
        return this;
    }

    public AnimationBuilder onStop(Stop stopListener) {
        this.viewAnimator.onStop(stopListener);
        return this;
    }

    public AnimationBuilder interpolator(Interpolator interpolator) {
        this.viewAnimator.interpolator(interpolator);
        return this;
    }

    public AnimationBuilder singleInterpolator(Interpolator interpolator) {
        this.singleInterpolator = interpolator;
        return this;
    }

    public Interpolator getSingleInterpolator() {
        return this.singleInterpolator;
    }

    public ViewAnimator accelerate() {
        return this.viewAnimator.interpolator(new AccelerateInterpolator());
    }

    public ViewAnimator descelerate() {
        return this.viewAnimator.interpolator(new DecelerateInterpolator());
    }

    public void start() {
        this.viewAnimator.start();
    }

    public View[] getViews() {
        return this.views;
    }

    public View getView() {
        return this.views[0];
    }

    public boolean isWaitForHeight() {
        return this.waitForHeight;
    }

    public AnimationBuilder bounce() {
        return translationY(0.0f, 0.0f, -30.0f, 0.0f, -15.0f, 0.0f, 0.0f);
    }

    public AnimationBuilder bounceIn() {
        alpha(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        scaleX(0.3f, 1.05f, 0.9f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        scaleY(0.3f, 1.05f, 0.9f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        return this;
    }

    public AnimationBuilder bounceOut() {
        scaleY(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.9f, 1.05f, 0.3f);
        scaleX(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.9f, 1.05f, 0.3f);
        alpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f);
        return this;
    }

    public AnimationBuilder fadeIn() {
        return alpha(0.0f, 0.25f, 0.5f, 0.75f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

    public AnimationBuilder fadeOut() {
        return alpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.75f, 0.5f, 0.25f, 0.0f);
    }

    public AnimationBuilder flash() {
        return alpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

    public AnimationBuilder flipHorizontal() {
        return rotationX(90.0f, -15.0f, CtaButton.TEXT_SIZE_SP, 0.0f);
    }

    public AnimationBuilder flipVertical() {
        return rotationY(90.0f, -15.0f, CtaButton.TEXT_SIZE_SP, 0.0f);
    }

    public AnimationBuilder pulse() {
        scaleY(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 1.1f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        scaleX(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 1.1f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        return this;
    }

    public AnimationBuilder rollIn() {
        for (View view : this.views) {
            alpha(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            translationX((float) (-((view.getWidth() - view.getPaddingLeft()) - view.getPaddingRight())), 0.0f);
            rotation(-120.0f, 0.0f);
        }
        return this;
    }

    public AnimationBuilder rollOut() {
        for (View view : this.views) {
            alpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f);
            translationX(0.0f, (float) view.getWidth());
            rotation(0.0f, 120.0f);
        }
        return this;
    }

    public AnimationBuilder rubber() {
        scaleX(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 1.25f, 0.75f, 1.15f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        scaleY(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.75f, 1.25f, 0.85f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        return this;
    }

    public AnimationBuilder shake() {
        translationX(0.0f, 25.0f, -25.0f, 25.0f, -25.0f, CtaButton.TEXT_SIZE_SP, -15.0f, 6.0f, -6.0f, 0.0f);
        interpolator(new CycleInterpolator(5.0f));
        return this;
    }

    public AnimationBuilder standUp() {
        for (View view : this.views) {
            float x = (float) ((((view.getWidth() - view.getPaddingLeft()) - view.getPaddingRight()) / 2) + view.getPaddingLeft());
            float y = (float) (view.getHeight() - view.getPaddingBottom());
            pivotX(x, x, x, x, x);
            pivotY(y, y, y, y, y);
            rotationX(55.0f, -30.0f, CtaButton.TEXT_SIZE_SP, -15.0f, 0.0f);
        }
        return this;
    }

    public AnimationBuilder swing() {
        return rotation(0.0f, CropImageView.DEFAULT_MAX_SCALE_MULTIPLIER, -10.0f, 6.0f, -6.0f, 3.0f, -3.0f, 0.0f);
    }

    public AnimationBuilder tada() {
        scaleX(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.9f, 0.9f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        scaleY(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.9f, 0.9f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, 1.1f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        rotation(0.0f, -3.0f, -3.0f, 3.0f, -3.0f, 3.0f, -3.0f, 3.0f, -3.0f, 0.0f);
        return this;
    }

    public AnimationBuilder wave() {
        for (View view : this.views) {
            float x = (float) ((((view.getWidth() - view.getPaddingLeft()) - view.getPaddingRight()) / 2) + view.getPaddingLeft());
            float y = (float) (view.getHeight() - view.getPaddingBottom());
            rotation(12.0f, -12.0f, 3.0f, -3.0f, 0.0f);
            pivotX(x, x, x, x, x);
            pivotY(y, y, y, y, y);
        }
        return this;
    }

    public AnimationBuilder wobble() {
        for (View view : this.views) {
            float one = (float) (((double) ((float) view.getWidth())) / 100.0d);
            translationX(0.0f * one, -25.0f * one, CloseButton.TEXT_SIZE_SP * one, -15.0f * one, CropImageView.DEFAULT_MAX_SCALE_MULTIPLIER * one, -5.0f * one, 0.0f * one, 0.0f);
            rotation(0.0f, -5.0f, 3.0f, -3.0f, 2.0f, -1.0f, 0.0f);
        }
        return this;
    }

    public AnimationBuilder zoomIn() {
        scaleX(0.45f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        scaleY(0.45f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        alpha(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        return this;
    }

    public AnimationBuilder zoomOut() {
        scaleX(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.3f, 0.0f);
        scaleY(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.3f, 0.0f);
        alpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f, 0.0f);
        return this;
    }

    public AnimationBuilder fall() {
        rotation(1080.0f, 720.0f, 360.0f, 0.0f);
        return this;
    }

    public AnimationBuilder newsPaper() {
        alpha(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        scaleX(0.1f, 0.5f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        scaleY(0.1f, 0.5f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        return this;
    }

    public AnimationBuilder slit() {
        rotationY(90.0f, 88.0f, 88.0f, 45.0f, 0.0f);
        alpha(0.0f, 0.4f, 0.8f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        scaleX(0.0f, 0.5f, 0.9f, 0.9f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        scaleY(0.0f, 0.5f, 0.9f, 0.9f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        return this;
    }

    public AnimationBuilder slideLeft() {
        translationX(-300.0f, 0.0f);
        alpha(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        return this;
    }

    public AnimationBuilder slideRight() {
        translationX(300.0f, 0.0f);
        alpha(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        return this;
    }

    public AnimationBuilder slideTop() {
        translationY(-300.0f, 0.0f);
        alpha(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        return this;
    }

    public AnimationBuilder slideBottom() {
        translationY(300.0f, 0.0f);
        alpha(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        return this;
    }

    public AnimationBuilder path(Path path) {
        if (path == null) {
            return this;
        }
        return custom(new 4(this, new PathMeasure(path, false)), 0.0f, pathMeasure.getLength());
    }

    public AnimationBuilder svgPath(String dAttributeOfPath) {
        return path(SvgPathParser.tryParsePath(dAttributeOfPath));
    }
}
