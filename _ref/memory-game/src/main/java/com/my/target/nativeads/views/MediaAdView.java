package com.my.target.nativeads.views;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.my.target.core.resources.a;
import com.my.target.core.ui.views.CacheImageView;
import com.my.target.core.utils.l;

public class MediaAdView extends RelativeLayout {
    protected static final int COLOR_PLACEHOLDER_GRAY = -1118482;
    private static final String IMAGE_CONTENT_DESCRIPTION = "mvmi";
    private static final String PLAY_BUTTON_CONTENT_DESCRIPTION = "mvpb";
    private static final String PROGRESS_BAR_CONTENT_DESCRIPTION = "mvpr";
    private final CacheImageView imageView;
    private int placeholderHeight;
    private int placeholderWidth;
    private final CacheImageView playButton;
    private final ProgressBar progressBar;

    public MediaAdView(Context context) {
        this(context, null);
    }

    public MediaAdView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MediaAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.imageView = new CacheImageView(context);
        this.playButton = new CacheImageView(context);
        this.progressBar = new ProgressBar(context, null, 16842871);
        initViews(context);
    }

    private void initViews(Context context) {
        setBackgroundColor(COLOR_PLACEHOLDER_GRAY);
        this.imageView.setContentDescription(IMAGE_CONTENT_DESCRIPTION);
        addView(this.imageView, new LayoutParams(-1, -2));
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(13, -1);
        this.progressBar.setContentDescription(PROGRESS_BAR_CONTENT_DESCRIPTION);
        this.progressBar.setVisibility(8);
        this.progressBar.getIndeterminateDrawable().setColorFilter(-16733198, Mode.SRC_ATOP);
        addView(this.progressBar, layoutParams);
        l lVar = new l(context);
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-2, -2);
        layoutParams2.addRule(13, -1);
        this.playButton.setContentDescription(PLAY_BUTTON_CONTENT_DESCRIPTION);
        this.playButton.setImageBitmap(a.b(lVar.a(64)));
        this.playButton.setVisibility(8);
        addView(this.playButton, layoutParams2);
    }

    public ProgressBar getProgressBarView() {
        return this.progressBar;
    }

    public CacheImageView getImageView() {
        return this.imageView;
    }

    public View getPlayButtonView() {
        return this.playButton;
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        int i4 = 0;
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        if (mode == 0) {
            i3 = Integer.MIN_VALUE;
        } else {
            i3 = mode;
        }
        if (mode2 == 0) {
            mode2 = Integer.MIN_VALUE;
        }
        if (this.placeholderHeight == 0 || this.placeholderWidth == 0) {
            super.onMeasure(i, i2);
            return;
        }
        float f = ((float) this.placeholderWidth) / ((float) this.placeholderHeight);
        float f2 = 0.0f;
        if (size2 != 0) {
            f2 = ((float) size) / ((float) size2);
        }
        if (i3 == 1073741824 && r0 == 1073741824) {
            mode2 = size;
            i4 = size2;
        } else if (i3 == Integer.MIN_VALUE && r0 == Integer.MIN_VALUE) {
            if (f < f2) {
                mode2 = Math.round(((float) size2) * f);
                if (size <= 0 || mode2 <= size) {
                    i4 = size2;
                } else {
                    i4 = Math.round(((float) size) / f);
                    mode2 = size;
                }
            } else {
                i4 = Math.round(((float) size) / f);
                if (size2 <= 0 || i4 <= size2) {
                    mode2 = size;
                } else {
                    mode2 = Math.round(((float) size2) * f);
                    i4 = size2;
                }
            }
        } else if (i3 == Integer.MIN_VALUE && r0 == 1073741824) {
            mode2 = Math.round(((float) size2) * f);
            if (size <= 0 || mode2 <= size) {
                i4 = size2;
            } else {
                i4 = Math.round(((float) size) / f);
                mode2 = size;
            }
        } else if (i3 == 1073741824 && r0 == Integer.MIN_VALUE) {
            i4 = Math.round(((float) size) / f);
            if (size2 <= 0 || i4 <= size2) {
                mode2 = size;
            } else {
                mode2 = Math.round(((float) size2) * f);
                i4 = size2;
            }
        } else {
            mode2 = 0;
        }
        super.onMeasure(MeasureSpec.makeMeasureSpec(mode2, 1073741824), MeasureSpec.makeMeasureSpec(i4, 1073741824));
    }

    public void setPlaceHolderDimension(int i, int i2) {
        this.placeholderWidth = i;
        this.placeholderHeight = i2;
    }
}
