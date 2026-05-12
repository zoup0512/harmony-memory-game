package com.mopub.mobileads;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.support.annotation.NonNull;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils$ForceOrientation;
import com.mopub.common.util.Dips;
import com.mopub.mobileads.resource.DrawableConstants.GradientStrip;

public class VastVideoGradientStripWidget extends ImageView {
    @NonNull
    DeviceUtils$ForceOrientation mForceOrientation;
    private boolean mHasCompanionAd;
    private boolean mIsVideoComplete;
    private int mVisibilityForCompanionAd;

    public VastVideoGradientStripWidget(@NonNull Context context, @NonNull Orientation orientation, @NonNull DeviceUtils$ForceOrientation deviceUtils$ForceOrientation, boolean z, int i, int i2, int i3) {
        super(context);
        this.mForceOrientation = deviceUtils$ForceOrientation;
        this.mVisibilityForCompanionAd = i;
        this.mHasCompanionAd = z;
        setImageDrawable(new GradientDrawable(orientation, new int[]{GradientStrip.START_COLOR, GradientStrip.END_COLOR}));
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, Dips.dipsToIntPixels(72.0f, context));
        layoutParams.addRule(i2, i3);
        setLayoutParams(layoutParams);
        updateVisibility();
    }

    void notifyVideoComplete() {
        this.mIsVideoComplete = true;
        updateVisibility();
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateVisibility();
    }

    private void updateVisibility() {
        if (this.mIsVideoComplete) {
            if (this.mHasCompanionAd) {
                setVisibility(this.mVisibilityForCompanionAd);
            } else {
                setVisibility(8);
            }
        } else if (this.mForceOrientation == DeviceUtils$ForceOrientation.FORCE_PORTRAIT) {
            setVisibility(4);
        } else if (this.mForceOrientation == DeviceUtils$ForceOrientation.FORCE_LANDSCAPE) {
            setVisibility(0);
        } else {
            switch (getResources().getConfiguration().orientation) {
                case 0:
                    MoPubLog.d("Screen orientation undefined: do not show gradient strip widget");
                    setVisibility(4);
                    return;
                case 1:
                    setVisibility(4);
                    return;
                case 2:
                    setVisibility(0);
                    return;
                case 3:
                    MoPubLog.d("Screen orientation is deprecated ORIENTATION_SQUARE: do not show gradient strip widget");
                    setVisibility(4);
                    return;
                default:
                    MoPubLog.d("Unrecognized screen orientation: do not show gradient strip widget");
                    setVisibility(4);
                    return;
            }
        }
    }
}
