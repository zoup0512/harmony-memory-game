package com.roughike.bottombar.scrollsweetness;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.design.widget.Snackbar.SnackbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Interpolator;

public class BottomNavigationBehavior<V extends View> extends VerticalScrollingBehavior<V> {
    private static final Interpolator INTERPOLATOR = new LinearOutSlowInInterpolator();
    private boolean hidden = false;
    private boolean isShy = false;
    private boolean isTablet = false;
    private final int mBottomNavHeight;
    private final int mDefaultOffset;
    private boolean mScrollingEnabled;
    private int mSnackbarHeight = -1;
    private ViewPropertyAnimatorCompat mTranslationAnimator;
    private final BottomNavigationWithSnackbar mWithSnackBarImpl;

    private interface BottomNavigationWithSnackbar {
        void updateSnackbar(CoordinatorLayout coordinatorLayout, View view, View view2);
    }

    private class LollipopBottomNavWithSnackBarImpl implements BottomNavigationWithSnackbar {
        private LollipopBottomNavWithSnackBarImpl() {
        }

        public void updateSnackbar(CoordinatorLayout parent, View dependency, View child) {
            if (!BottomNavigationBehavior.this.isTablet && BottomNavigationBehavior.this.isShy && (dependency instanceof SnackbarLayout)) {
                if (BottomNavigationBehavior.this.mSnackbarHeight == -1) {
                    BottomNavigationBehavior.this.mSnackbarHeight = dependency.getHeight();
                }
                if (ViewCompat.getTranslationY(child) == 0.0f) {
                    dependency.setPadding(dependency.getPaddingLeft(), dependency.getPaddingTop(), dependency.getPaddingRight(), (BottomNavigationBehavior.this.mSnackbarHeight + BottomNavigationBehavior.this.mBottomNavHeight) - BottomNavigationBehavior.this.mDefaultOffset);
                }
            }
        }
    }

    private class PreLollipopBottomNavWithSnackBarImpl implements BottomNavigationWithSnackbar {
        private PreLollipopBottomNavWithSnackBarImpl() {
        }

        public void updateSnackbar(CoordinatorLayout parent, View dependency, View child) {
            if (!BottomNavigationBehavior.this.isTablet && BottomNavigationBehavior.this.isShy && (dependency instanceof SnackbarLayout)) {
                if (BottomNavigationBehavior.this.mSnackbarHeight == -1) {
                    BottomNavigationBehavior.this.mSnackbarHeight = dependency.getHeight();
                }
                if (ViewCompat.getTranslationY(child) == 0.0f) {
                    ((MarginLayoutParams) dependency.getLayoutParams()).bottomMargin = (BottomNavigationBehavior.this.mBottomNavHeight + BottomNavigationBehavior.this.mSnackbarHeight) - BottomNavigationBehavior.this.mDefaultOffset;
                    child.bringToFront();
                    child.getParent().requestLayout();
                    if (VERSION.SDK_INT < 19) {
                        ((View) child.getParent()).invalidate();
                    }
                }
            }
        }
    }

    public BottomNavigationBehavior(int bottomNavHeight, int defaultOffset, boolean shy, boolean tablet) {
        this.mWithSnackBarImpl = VERSION.SDK_INT >= 21 ? new LollipopBottomNavWithSnackBarImpl() : new PreLollipopBottomNavWithSnackBarImpl();
        this.mScrollingEnabled = true;
        this.mBottomNavHeight = bottomNavHeight;
        this.mDefaultOffset = defaultOffset;
        this.isShy = shy;
        this.isTablet = tablet;
    }

    public boolean layoutDependsOn(CoordinatorLayout parent, V child, View dependency) {
        this.mWithSnackBarImpl.updateSnackbar(parent, dependency, child);
        return dependency instanceof SnackbarLayout;
    }

    public void onNestedVerticalOverScroll(CoordinatorLayout coordinatorLayout, V v, int direction, int currentOverScroll, int totalOverScroll) {
    }

    public void onDependentViewRemoved(CoordinatorLayout parent, V child, View dependency) {
        updateScrollingForSnackbar(dependency, true);
        super.onDependentViewRemoved(parent, child, dependency);
    }

    private void updateScrollingForSnackbar(View dependency, boolean enabled) {
        if (!this.isTablet && (dependency instanceof SnackbarLayout)) {
            this.mScrollingEnabled = enabled;
        }
    }

    public boolean onDependentViewChanged(CoordinatorLayout parent, V child, View dependency) {
        updateScrollingForSnackbar(dependency, false);
        return super.onDependentViewChanged(parent, child, dependency);
    }

    public void onDirectionNestedPreScroll(CoordinatorLayout coordinatorLayout, V child, View target, int dx, int dy, int[] consumed, int scrollDirection) {
        handleDirection(child, scrollDirection);
    }

    private void handleDirection(V child, int scrollDirection) {
        if (!this.mScrollingEnabled) {
            return;
        }
        if (scrollDirection == -1 && this.hidden) {
            this.hidden = false;
            animateOffset(child, this.mDefaultOffset);
        } else if (scrollDirection == 1 && !this.hidden) {
            this.hidden = true;
            animateOffset(child, this.mBottomNavHeight + this.mDefaultOffset);
        }
    }

    protected boolean onNestedDirectionFling(CoordinatorLayout coordinatorLayout, V child, View target, float velocityX, float velocityY, int scrollDirection) {
        handleDirection(child, scrollDirection);
        return true;
    }

    private void animateOffset(V child, int offset) {
        ensureOrCancelAnimator(child);
        this.mTranslationAnimator.translationY((float) offset).start();
    }

    private void ensureOrCancelAnimator(V child) {
        if (this.mTranslationAnimator == null) {
            this.mTranslationAnimator = ViewCompat.animate(child);
            this.mTranslationAnimator.setDuration(300);
            this.mTranslationAnimator.setInterpolator(INTERPOLATOR);
            return;
        }
        this.mTranslationAnimator.cancel();
    }

    public void setHidden(@NonNull V view, boolean bottomLayoutHidden) {
        if (!bottomLayoutHidden && this.hidden) {
            animateOffset(view, this.mDefaultOffset);
        } else if (bottomLayoutHidden && !this.hidden) {
            animateOffset(view, this.mBottomNavHeight + this.mDefaultOffset);
        }
        this.hidden = bottomLayoutHidden;
    }

    public static <V extends View> BottomNavigationBehavior<V> from(@NonNull V view) {
        LayoutParams params = view.getLayoutParams();
        if (params instanceof CoordinatorLayout.LayoutParams) {
            Behavior behavior = ((CoordinatorLayout.LayoutParams) params).getBehavior();
            if (behavior instanceof BottomNavigationBehavior) {
                return (BottomNavigationBehavior) behavior;
            }
            throw new IllegalArgumentException("The view is not associated with BottomNavigationBehavior");
        }
        throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
    }
}
