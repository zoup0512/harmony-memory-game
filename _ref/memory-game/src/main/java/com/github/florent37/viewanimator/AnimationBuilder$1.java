package com.github.florent37.viewanimator;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.View;

class AnimationBuilder$1 implements AnimatorUpdateListener {
    final /* synthetic */ AnimationBuilder this$0;
    final /* synthetic */ AnimationListener$Update val$update;
    final /* synthetic */ View val$view;

    AnimationBuilder$1(AnimationBuilder this$0, AnimationListener$Update animationListener$Update, View view) {
        this.this$0 = this$0;
        this.val$update = animationListener$Update;
        this.val$view = view;
    }

    public void onAnimationUpdate(ValueAnimator animation) {
        this.val$update.update(this.val$view, ((Float) animation.getAnimatedValue()).floatValue());
    }
}
