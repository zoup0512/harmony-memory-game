package com.github.florent37.viewanimator;

import android.view.View;

class AnimationBuilder$3 implements AnimationListener$Update {
    final /* synthetic */ AnimationBuilder this$0;

    AnimationBuilder$3(AnimationBuilder this$0) {
        this.this$0 = this$0;
    }

    public void update(View view, float value) {
        view.getLayoutParams().width = (int) value;
        view.requestLayout();
    }
}
