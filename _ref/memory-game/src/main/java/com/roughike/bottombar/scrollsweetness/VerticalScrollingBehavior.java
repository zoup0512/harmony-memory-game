package com.roughike.bottombar.scrollsweetness;

import android.content.Context;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class VerticalScrollingBehavior<V extends View> extends Behavior<V> {
    private int mOverScrollDirection = 0;
    private int mScrollDirection = 0;
    private int mTotalDy = 0;
    private int mTotalDyUnconsumed = 0;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ScrollDirection {
        public static final int SCROLL_DIRECTION_DOWN = -1;
        public static final int SCROLL_DIRECTION_UP = 1;
        public static final int SCROLL_NONE = 0;
    }

    public abstract void onDirectionNestedPreScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr, int i3);

    protected abstract boolean onNestedDirectionFling(CoordinatorLayout coordinatorLayout, V v, View view, float f, float f2, int i);

    public abstract void onNestedVerticalOverScroll(CoordinatorLayout coordinatorLayout, V v, int i, int i2, int i3);

    public VerticalScrollingBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public int getOverScrollDirection() {
        return this.mOverScrollDirection;
    }

    public int getScrollDirection() {
        return this.mScrollDirection;
    }

    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V v, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & 2) != 0;
    }

    public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, V child, View directTargetChild, View target, int nestedScrollAxes) {
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, V child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }

    public void onNestedScroll(CoordinatorLayout coordinatorLayout, V child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if (dyUnconsumed > 0 && this.mTotalDyUnconsumed < 0) {
            this.mTotalDyUnconsumed = 0;
            this.mOverScrollDirection = 1;
        } else if (dyUnconsumed < 0 && this.mTotalDyUnconsumed > 0) {
            this.mTotalDyUnconsumed = 0;
            this.mOverScrollDirection = -1;
        }
        this.mTotalDyUnconsumed += dyUnconsumed;
        onNestedVerticalOverScroll(coordinatorLayout, child, this.mOverScrollDirection, dyConsumed, this.mTotalDyUnconsumed);
    }

    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        if (dy > 0 && this.mTotalDy < 0) {
            this.mTotalDy = 0;
            this.mScrollDirection = 1;
        } else if (dy < 0 && this.mTotalDy > 0) {
            this.mTotalDy = 0;
            this.mScrollDirection = -1;
        }
        this.mTotalDy += dy;
        onDirectionNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, this.mScrollDirection);
    }

    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, V child, View target, float velocityX, float velocityY, boolean consumed) {
        super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
        this.mScrollDirection = velocityY > 0.0f ? 1 : -1;
        return onNestedDirectionFling(coordinatorLayout, child, target, velocityX, velocityY, this.mScrollDirection);
    }

    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, V child, View target, float velocityX, float velocityY) {
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }

    public WindowInsetsCompat onApplyWindowInsets(CoordinatorLayout coordinatorLayout, V child, WindowInsetsCompat insets) {
        return super.onApplyWindowInsets(coordinatorLayout, child, insets);
    }

    public Parcelable onSaveInstanceState(CoordinatorLayout parent, V child) {
        return super.onSaveInstanceState(parent, child);
    }
}
