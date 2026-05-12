package com.amazon.device.ads;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.amazon.device.ads.AdActivity.AdActivityAdapter;
import com.amazon.device.ads.AdVideoPlayer.AdVideoPlayerListener;

class VideoActionHandler implements AdActivityAdapter {
    private Activity activity;
    private RelativeLayout layout;
    private AdVideoPlayer player;

    VideoActionHandler() {
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void onCreate() {
        Bundle extras = this.activity.getIntent().getExtras();
        this.layout = new RelativeLayout(this.activity);
        this.layout.setLayoutParams(new LayoutParams(-1, -1));
        this.activity.setContentView(this.layout);
        initPlayer(extras);
        this.player.playVideo();
    }

    private void setPlayerListener(AdVideoPlayer adVideoPlayer) {
        adVideoPlayer.setListener(new AdVideoPlayerListener() {
            public void onError() {
                VideoActionHandler.this.activity.finish();
            }

            public void onComplete() {
                VideoActionHandler.this.activity.finish();
            }
        });
    }

    private void initPlayer(Bundle bundle) {
        this.player = new AdVideoPlayer(this.activity);
        this.player.setPlayData(bundle.getString("url"));
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.player.setLayoutParams(layoutParams);
        this.player.setViewGroup(this.layout);
        setPlayerListener(this.player);
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onStop() {
        this.player.releasePlayer();
        this.player = null;
        this.activity.finish();
    }

    public void onDestroy() {
        this.player.releasePlayer();
        this.player = null;
        this.activity.finish();
    }

    public void preOnCreate() {
        this.activity.requestWindowFeature(1);
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public boolean onBackPressed() {
        return false;
    }

    public void onWindowFocusChanged() {
    }
}
