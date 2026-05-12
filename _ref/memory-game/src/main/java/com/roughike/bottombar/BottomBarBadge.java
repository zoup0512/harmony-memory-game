package com.roughike.bottombar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.mopub.volley.DefaultRetryPolicy;
import com.yalantis.ucrop.view.CropImageView;

public class BottomBarBadge extends TextView {
    private long animationDuration = 150;
    private boolean autoHideOnSelection = true;
    private boolean autoShowAfterUnSelection = false;
    private int count;
    private boolean isVisible = false;

    public void setCount(int count) {
        this.count = count;
        setText(String.valueOf(count));
    }

    public int getCount() {
        return this.count;
    }

    public void setAutoHideOnSelection(boolean autoHideOnSelection) {
        this.autoHideOnSelection = autoHideOnSelection;
    }

    public boolean getAutoHideOnSelection() {
        return this.autoHideOnSelection;
    }

    public void setAutoShowAfterUnSelection(boolean autoShowAfterUnSelection) {
        this.autoShowAfterUnSelection = autoShowAfterUnSelection;
    }

    public boolean getAutoShowAfterUnSelection() {
        return this.autoShowAfterUnSelection;
    }

    public void setAnimationDuration(long duration) {
        this.animationDuration = duration;
    }

    public void show() {
        this.isVisible = true;
        ViewCompat.animate(this).setDuration(this.animationDuration).scaleX(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT).scaleY(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT).start();
    }

    public void hide() {
        this.isVisible = false;
        ViewCompat.animate(this).setDuration(this.animationDuration).scaleX(0.0f).scaleY(0.0f).start();
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    protected BottomBarBadge(Context context, int position, final View tabToAddTo, int backgroundColor) {
        super(context);
        LayoutParams params = new LayoutParams(-2, -2);
        setLayoutParams(params);
        setGravity(17);
        MiscUtils.setTextAppearance(this, R.style.BB_BottomBarBadge_Text);
        int three = MiscUtils.dpToPixel(context, 3.0f);
        ShapeDrawable backgroundCircle = BadgeCircle.make(three * 3, backgroundColor);
        setPadding(three, three, three, three);
        setBackgroundCompat(backgroundCircle);
        final FrameLayout container = new FrameLayout(context);
        container.setLayoutParams(params);
        ViewGroup parent = (ViewGroup) tabToAddTo.getParent();
        parent.removeView(tabToAddTo);
        container.setTag(tabToAddTo.getTag());
        tabToAddTo.setTag(null);
        container.addView(tabToAddTo);
        container.addView(this);
        parent.addView(container, position);
        container.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                container.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                BottomBarBadge.this.adjustPositionAndSize(tabToAddTo);
            }
        });
    }

    protected void adjustPosition(View tabToAddTo) {
        setX((float) (((double) tabToAddTo.getX()) + (((double) tabToAddTo.getWidth()) / 1.75d)));
    }

    private void adjustPositionAndSize(View tabToAddTo) {
        adjustPosition(tabToAddTo);
        setTranslationY(CropImageView.DEFAULT_MAX_SCALE_MULTIPLIER);
        int size = Math.max(getWidth(), getHeight());
        LayoutParams params = getLayoutParams();
        if (params.width != size || params.height != size) {
            params.width = size;
            params.height = size;
            setLayoutParams(params);
        }
    }

    private void setBackgroundCompat(Drawable background) {
        if (VERSION.SDK_INT >= 16) {
            setBackground(background);
        } else {
            setBackgroundDrawable(background);
        }
    }
}
