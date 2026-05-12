package com.github.florent37.viewanimator;

import android.view.View;

class AnimationBuilder$2 implements AnimationListener$Update {
    final /* synthetic */ AnimationBuilder this$0;

    AnimationBuilder$2(AnimationBuilder this$0) {
        this.this$0 = this$0;
    }

    public void update(View view, float value) {
        view.getLayoutParams().height = (int) value;
        view.requestLayout();
    }
}
