package com.mopub.mraid;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Drawables;
import com.mopub.mobileads.BaseVideoPlayerActivity;
import com.mopub.mobileads.BaseVideoViewController;
import com.mopub.mobileads.BaseVideoViewController.BaseVideoViewControllerListener;

public class MraidVideoViewController extends BaseVideoViewController {
    private static final float CLOSE_BUTTON_PADDING = 8.0f;
    private static final float CLOSE_BUTTON_SIZE = 50.0f;
    private int mButtonPadding;
    private int mButtonSize;
    private ImageButton mCloseButton;
    private final VideoView mVideoView;

    public MraidVideoViewController(Context context, Bundle bundle, Bundle bundle2, BaseVideoViewControllerListener baseVideoViewControllerListener) {
        super(context, null, baseVideoViewControllerListener);
        this.mVideoView = new VideoView(context);
        this.mVideoView.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                MraidVideoViewController.this.mCloseButton.setVisibility(0);
                MraidVideoViewController.this.videoCompleted(true);
            }
        });
        this.mVideoView.setOnErrorListener(new OnErrorListener() {
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                MraidVideoViewController.this.mCloseButton.setVisibility(0);
                MraidVideoViewController.this.videoError(false);
                return false;
            }
        });
        this.mVideoView.setVideoPath(bundle.getString(BaseVideoPlayerActivity.VIDEO_URL));
    }

    protected void onCreate() {
        super.onCreate();
        this.mButtonSize = Dips.asIntPixels(CLOSE_BUTTON_SIZE, getContext());
        this.mButtonPadding = Dips.asIntPixels(8.0f, getContext());
        createInterstitialCloseButton();
        this.mCloseButton.setVisibility(8);
        this.mVideoView.start();
    }

    protected VideoView getVideoView() {
        return this.mVideoView;
    }

    protected void onDestroy() {
    }

    protected void onPause() {
    }

    protected void onResume() {
    }

    protected void onSaveInstanceState(@NonNull Bundle bundle) {
    }

    protected void onConfigurationChanged(Configuration configuration) {
    }

    protected void onBackPressed() {
    }

    private void createInterstitialCloseButton() {
        this.mCloseButton = new ImageButton(getContext());
        Drawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{-16842919}, Drawables.INTERSTITIAL_CLOSE_BUTTON_NORMAL.createDrawable(getContext()));
        stateListDrawable.addState(new int[]{16842919}, Drawables.INTERSTITIAL_CLOSE_BUTTON_PRESSED.createDrawable(getContext()));
        this.mCloseButton.setImageDrawable(stateListDrawable);
        this.mCloseButton.setBackgroundDrawable(null);
        this.mCloseButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MraidVideoViewController.this.getBaseVideoViewControllerListener().onFinish();
            }
        });
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.mButtonSize, this.mButtonSize);
        layoutParams.addRule(11);
        layoutParams.setMargins(this.mButtonPadding, 0, this.mButtonPadding, 0);
        getLayout().addView(this.mCloseButton, layoutParams);
    }
}
