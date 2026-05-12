package com.amazon.device.ads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import com.amazon.device.ads.ThreadUtils.ExecutionStyle;
import com.amazon.device.ads.ThreadUtils.ExecutionThread;
import com.amazon.device.ads.ThreadUtils.ThreadRunner;

class NativeCloseButton {
    private static final int CLOSE_BUTTON_SIZE_DP = 60;
    private static final int CLOSE_BUTTON_TAP_TARGET_SIZE_DP = 80;
    private static final String CONTENT_DESCRIPTION_NATIVE_CLOSE_BUTTON = "nativeCloseButton";
    private static final String CONTENT_DESCRIPTION_NATIVE_CLOSE_BUTTON_CONTAINER = "nativeCloseButtonContainer";
    private static final String CONTENT_DESCRIPTION_NATIVE_CLOSE_BUTTON_IMAGE = "nativeCloseButtonImage";
    private final AdCloser adCloser;
    private ViewGroup closeButton;
    private ViewGroup closeButtonContainer;
    private ImageView closeButtonImage;
    private boolean hasNativeCloseButton;
    private final ImageViewFactory imageViewFactory;
    private final LayoutFactory layoutFactory;
    private final ThreadRunner threadRunner;
    private final ViewGroup viewGroup;

    public NativeCloseButton(ViewGroup viewGroup, AdCloser adCloser) {
        this(viewGroup, adCloser, ThreadUtils.getThreadRunner(), new LayoutFactory(), new ImageButtonFactory());
    }

    NativeCloseButton(ViewGroup viewGroup, AdCloser adCloser, ThreadRunner threadRunner, LayoutFactory layoutFactory, ImageViewFactory imageViewFactory) {
        this.hasNativeCloseButton = false;
        this.viewGroup = viewGroup;
        this.adCloser = adCloser;
        this.threadRunner = threadRunner;
        this.layoutFactory = layoutFactory;
        this.imageViewFactory = imageViewFactory;
    }

    private Context getContext() {
        return this.viewGroup.getContext();
    }

    public void enable(boolean z, RelativePosition relativePosition) {
        this.hasNativeCloseButton = true;
        if (this.closeButton == null || this.closeButtonImage == null || !this.viewGroup.equals(this.closeButton.getParent()) || (!this.closeButton.equals(this.closeButtonImage.getParent()) && z)) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            final int i = (int) ((60.0f * displayMetrics.density) + 0.5f);
            final int i2 = (int) ((80.0f * displayMetrics.density) + 0.5f);
            final boolean z2 = z;
            final RelativePosition relativePosition2 = relativePosition;
            this.threadRunner.executeAsyncTask(new MobileAdsAsyncTask<Void, Void, Void>() {
                protected Void doInBackground(Void... voidArr) {
                    NativeCloseButton.this.createButtonIfNeeded(i2);
                    return null;
                }

                protected void onPostExecute(Void voidR) {
                    NativeCloseButton.this.addCloseButtonToTapTargetIfNeeded(z2, relativePosition2, i, i2);
                }
            }, new Void[0]);
        } else if (!z) {
            hideImage();
        }
    }

    private void createButtonIfNeeded(int i) {
        Object obj = null;
        synchronized (this) {
            if (this.closeButton == null) {
                this.closeButton = this.layoutFactory.createLayout(getContext(), LayoutType.RELATIVE_LAYOUT, CONTENT_DESCRIPTION_NATIVE_CLOSE_BUTTON);
                this.closeButtonImage = this.imageViewFactory.createImageView(getContext(), CONTENT_DESCRIPTION_NATIVE_CLOSE_BUTTON_IMAGE);
                obj = 1;
            }
        }
        if (obj != null) {
            final Drawable createBitmapDrawable = this.imageViewFactory.createBitmapDrawable(getContext().getResources(), Assets.getInstance().getFilePath(Assets.CLOSE_NORMAL));
            final BitmapDrawable createBitmapDrawable2 = this.imageViewFactory.createBitmapDrawable(getContext().getResources(), Assets.getInstance().getFilePath(Assets.CLOSE_PRESSED));
            this.closeButtonImage.setImageDrawable(createBitmapDrawable);
            this.closeButtonImage.setScaleType(ScaleType.FIT_CENTER);
            this.closeButtonImage.setBackgroundDrawable(null);
            OnClickListener anonymousClass2 = new OnClickListener() {
                public void onClick(View view) {
                    NativeCloseButton.this.closeAd();
                }
            };
            this.closeButtonImage.setOnClickListener(anonymousClass2);
            this.closeButton.setOnClickListener(anonymousClass2);
            OnTouchListener anonymousClass3 = new OnTouchListener() {
                @SuppressLint({"ClickableViewAccessibility"})
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    NativeCloseButton.this.animateCloseButton(motionEvent, createBitmapDrawable, createBitmapDrawable2);
                    return false;
                }
            };
            this.closeButton.setOnTouchListener(anonymousClass3);
            this.closeButtonImage.setOnTouchListener(anonymousClass3);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, i);
            layoutParams.addRule(11);
            layoutParams.addRule(10);
            this.closeButtonContainer = this.layoutFactory.createLayout(getContext(), LayoutType.RELATIVE_LAYOUT, CONTENT_DESCRIPTION_NATIVE_CLOSE_BUTTON_CONTAINER);
            this.closeButtonContainer.addView(this.closeButton, layoutParams);
        }
    }

    private void closeAd() {
        this.adCloser.closeAd();
    }

    private void animateCloseButton(MotionEvent motionEvent, BitmapDrawable bitmapDrawable, BitmapDrawable bitmapDrawable2) {
        switch (motionEvent.getAction()) {
            case 0:
                this.closeButtonImage.setImageDrawable(bitmapDrawable2);
                return;
            case 1:
                this.closeButtonImage.setImageDrawable(bitmapDrawable);
                return;
            default:
                return;
        }
    }

    @SuppressLint({"InlinedApi"})
    private void addCloseButtonToTapTargetIfNeeded(boolean z, RelativePosition relativePosition, int i, int i2) {
        LayoutParams layoutParams;
        if (z && !this.closeButton.equals(this.closeButtonImage.getParent())) {
            layoutParams = new RelativeLayout.LayoutParams(i, i);
            layoutParams.addRule(13);
            this.closeButton.addView(this.closeButtonImage, layoutParams);
        } else if (!z && this.closeButton.equals(this.closeButtonImage.getParent())) {
            this.closeButton.removeView(this.closeButtonImage);
        }
        if (!this.viewGroup.equals(this.closeButtonContainer.getParent())) {
            this.viewGroup.addView(this.closeButtonContainer, new FrameLayout.LayoutParams(-1, -1));
        }
        layoutParams = new RelativeLayout.LayoutParams(i2, i2);
        if (relativePosition == null) {
            relativePosition = RelativePosition.TOP_RIGHT;
        }
        switch (relativePosition) {
            case BOTTOM_CENTER:
                layoutParams.addRule(12);
                layoutParams.addRule(14);
                break;
            case BOTTOM_LEFT:
                layoutParams.addRule(12);
                layoutParams.addRule(9);
                break;
            case BOTTOM_RIGHT:
                layoutParams.addRule(12);
                layoutParams.addRule(11);
                break;
            case CENTER:
                layoutParams.addRule(13);
                break;
            case TOP_CENTER:
                layoutParams.addRule(10);
                layoutParams.addRule(14);
                break;
            case TOP_LEFT:
                layoutParams.addRule(10);
                layoutParams.addRule(9);
                break;
            case TOP_RIGHT:
                layoutParams.addRule(10);
                layoutParams.addRule(11);
                break;
            default:
                layoutParams.addRule(10);
                layoutParams.addRule(11);
                break;
        }
        this.closeButton.setLayoutParams(layoutParams);
        this.closeButtonContainer.bringToFront();
    }

    public void remove() {
        this.hasNativeCloseButton = false;
        this.threadRunner.execute(new Runnable() {
            public void run() {
                NativeCloseButton.this.removeNativeCloseButtonOnMainThread();
            }
        }, ExecutionStyle.RUN_ASAP, ExecutionThread.MAIN_THREAD);
    }

    private void removeNativeCloseButtonOnMainThread() {
        this.viewGroup.removeView(this.closeButtonContainer);
    }

    public void showImage(boolean z) {
        if (this.hasNativeCloseButton && this.closeButton != null) {
            if (z) {
                enable(true, null);
            } else {
                hideImage();
            }
        }
    }

    private void hideImage() {
        this.threadRunner.execute(new Runnable() {
            public void run() {
                NativeCloseButton.this.hideImageOnMainThread();
            }
        }, ExecutionStyle.RUN_ASAP, ExecutionThread.MAIN_THREAD);
    }

    private void hideImageOnMainThread() {
        this.closeButton.removeAllViews();
    }
}
