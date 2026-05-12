package com.github.florent37.viewanimator;

import android.graphics.PathMeasure;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;

class AnimationBuilder$4 implements AnimationListener$Update {
    final /* synthetic */ AnimationBuilder this$0;
    final /* synthetic */ PathMeasure val$pathMeasure;

    AnimationBuilder$4(AnimationBuilder this$0, PathMeasure pathMeasure) {
        this.this$0 = this$0;
        this.val$pathMeasure = pathMeasure;
    }

    public void update(View view, float value) {
        float[] currentPosition = new float[2];
        this.val$pathMeasure.getPosTan(value, currentPosition, null);
        float x = currentPosition[0];
        float y = currentPosition[1];
        ViewCompat.setX(view, x);
        ViewCompat.setY(view, y);
        Log.d(null, "path: value=" + value + ", x=" + x + ", y=" + y);
    }
}
