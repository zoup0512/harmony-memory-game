package com.roughike.bottombar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.MenuRes;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.widget.PopupMenu;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.mopub.volley.DefaultRetryPolicy;

class MiscUtils {
    MiscUtils() {
    }

    protected static int getColor(Context context, int color) {
        TypedValue tv = new TypedValue();
        context.getTheme().resolveAttribute(color, tv, true);
        return tv.data;
    }

    protected static int dpToPixel(Context context, float dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        try {
            return (int) ((((float) metrics.densityDpi) / 160.0f) * dp);
        } catch (NoSuchFieldError e) {
            return (int) TypedValue.applyDimension(1, dp, metrics);
        }
    }

    protected static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) (((float) displayMetrics.widthPixels) / displayMetrics.density);
    }

    protected static BottomBarTab[] inflateMenuFromResource(Activity activity, @MenuRes int menuRes) {
        Menu menu = new PopupMenu(activity, null).getMenu();
        activity.getMenuInflater().inflate(menuRes, menu);
        int menuSize = menu.size();
        BottomBarTab[] tabs = new BottomBarTab[menuSize];
        for (int i = 0; i < menuSize; i++) {
            MenuItem item = menu.getItem(i);
            BottomBarTab tab = new BottomBarTab(item.getIcon(), String.valueOf(item.getTitle()));
            tab.id = item.getItemId();
            tabs[i] = tab;
        }
        return tabs;
    }

    protected static void resizeTab(final View tab, float start, float end) {
        ValueAnimator animator = ValueAnimator.ofFloat(new float[]{start, end});
        animator.setDuration(150);
        animator.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animator) {
                LayoutParams params = tab.getLayoutParams();
                if (params != null) {
                    params.width = Math.round(((Float) animator.getAnimatedValue()).floatValue());
                    tab.setLayoutParams(params);
                }
            }
        });
        animator.start();
    }

    protected static void resizePaddingTop(final View icon, int start, int end, long duration) {
        ValueAnimator paddingAnimator = ValueAnimator.ofInt(new int[]{start, end});
        paddingAnimator.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                icon.setPadding(icon.getPaddingLeft(), ((Integer) animation.getAnimatedValue()).intValue(), icon.getPaddingRight(), icon.getPaddingBottom());
            }
        });
        paddingAnimator.setDuration(duration);
        paddingAnimator.start();
    }

    @TargetApi(21)
    protected static void animateBGColorChange(View clickedView, final View backgroundView, final View bgOverlay, final int newColor) {
        Animator animator;
        int centerX = (int) (ViewCompat.getX(clickedView) + ((float) (clickedView.getMeasuredWidth() / 2)));
        int centerY = clickedView.getMeasuredHeight() / 2;
        int finalRadius = backgroundView.getWidth();
        backgroundView.clearAnimation();
        bgOverlay.clearAnimation();
        if (VERSION.SDK_INT < 21) {
            ViewCompat.setAlpha(bgOverlay, 0.0f);
            animator = ViewCompat.animate(bgOverlay).alpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        } else if (bgOverlay.isAttachedToWindow()) {
            animator = ViewAnimationUtils.createCircularReveal(bgOverlay, centerX, centerY, 0.0f, (float) finalRadius);
        } else {
            return;
        }
        if (animator instanceof ViewPropertyAnimatorCompat) {
            ((ViewPropertyAnimatorCompat) animator).setListener(new ViewPropertyAnimatorListenerAdapter() {
                public void onAnimationEnd(View view) {
                    onCancel();
                }

                public void onAnimationCancel(View view) {
                    onCancel();
                }

                private void onCancel() {
                    backgroundView.setBackgroundColor(newColor);
                    bgOverlay.setVisibility(4);
                    ViewCompat.setAlpha(bgOverlay, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                }
            }).start();
        } else if (animator != null) {
            animator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    onCancel();
                }

                public void onAnimationCancel(Animator animation) {
                    onCancel();
                }

                private void onCancel() {
                    backgroundView.setBackgroundColor(newColor);
                    bgOverlay.setVisibility(4);
                    ViewCompat.setAlpha(bgOverlay, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                }
            });
            animator.start();
        }
        bgOverlay.setBackgroundColor(newColor);
        bgOverlay.setVisibility(0);
    }

    protected static void setTextAppearance(TextView textView, int resId) {
        if (VERSION.SDK_INT >= 23) {
            textView.setTextAppearance(resId);
        } else {
            textView.setTextAppearance(textView.getContext(), resId);
        }
    }

    protected static boolean isNightMode(Context context) {
        return (context.getResources().getConfiguration().uiMode & 48) == 32;
    }
}
