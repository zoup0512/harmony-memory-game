package com.mopub.mobileads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils.TruncateAt;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Utils;
import com.mopub.mobileads.resource.CloseButtonDrawable;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import com.mopub.network.Networking;
import com.mopub.volley.toolbox.ImageLoader;

public class VastVideoCloseButtonWidget extends RelativeLayout {
    @NonNull
    private CloseButtonDrawable mCloseButtonDrawable = new CloseButtonDrawable();
    private final int mEdgePadding;
    @NonNull
    private final ImageLoader mImageLoader;
    private final int mImagePadding;
    @NonNull
    private ImageView mImageView;
    private final int mTextRightMargin;
    @NonNull
    private TextView mTextView;
    private final int mWidgetHeight;

    public VastVideoCloseButtonWidget(@NonNull Context context) {
        super(context);
        setId((int) Utils.generateUniqueId());
        this.mEdgePadding = Dips.dipsToIntPixels(16.0f, context);
        this.mImagePadding = Dips.dipsToIntPixels(5.0f, context);
        this.mWidgetHeight = Dips.dipsToIntPixels(46.0f, context);
        this.mTextRightMargin = Dips.dipsToIntPixels(7.0f, context);
        this.mImageLoader = Networking.getImageLoader(context);
        createImageView();
        createTextView();
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, this.mWidgetHeight);
        layoutParams.addRule(11);
        setLayoutParams(layoutParams);
    }

    private void createImageView() {
        this.mImageView = new ImageView(getContext());
        this.mImageView.setId((int) Utils.generateUniqueId());
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.mWidgetHeight, this.mWidgetHeight);
        layoutParams.addRule(11);
        this.mImageView.setImageDrawable(this.mCloseButtonDrawable);
        this.mImageView.setPadding(this.mImagePadding, this.mImagePadding + this.mEdgePadding, this.mImagePadding + this.mEdgePadding, this.mImagePadding);
        addView(this.mImageView, layoutParams);
    }

    private void createTextView() {
        this.mTextView = new TextView(getContext());
        this.mTextView.setSingleLine();
        this.mTextView.setEllipsize(TruncateAt.END);
        this.mTextView.setTextColor(-1);
        this.mTextView.setTextSize(CloseButton.TEXT_SIZE_SP);
        this.mTextView.setTypeface(CloseButton.TEXT_TYPEFACE);
        this.mTextView.setText("");
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15);
        layoutParams.addRule(0, this.mImageView.getId());
        this.mTextView.setPadding(0, this.mEdgePadding, 0, 0);
        layoutParams.setMargins(0, 0, this.mTextRightMargin, 0);
        addView(this.mTextView, layoutParams);
    }

    void updateCloseButtonText(@Nullable String str) {
        if (this.mTextView != null) {
            this.mTextView.setText(str);
        }
    }

    void updateCloseButtonIcon(@NonNull String str) {
        this.mImageLoader.get(str, new 1(this, str));
    }

    void setOnTouchListenerToContent(@Nullable OnTouchListener onTouchListener) {
        this.mImageView.setOnTouchListener(onTouchListener);
        this.mTextView.setOnTouchListener(onTouchListener);
    }

    @Deprecated
    @VisibleForTesting
    ImageView getImageView() {
        return this.mImageView;
    }

    @Deprecated
    @VisibleForTesting
    void setImageView(ImageView imageView) {
        this.mImageView = imageView;
    }

    @Deprecated
    @VisibleForTesting
    TextView getTextView() {
        return this.mTextView;
    }
}
