package com.mopub.mobileads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.mopub.common.AdType;
import com.mopub.common.DataKeys;
import com.mopub.common.FullAdType;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Intents;
import com.mopub.mobileads.BaseVideoViewController.BaseVideoViewControllerListener;
import com.mopub.mraid.MraidVideoViewController;

public class MraidVideoPlayerActivity extends BaseVideoPlayerActivity implements BaseVideoViewControllerListener {
    @Nullable
    private BaseVideoViewController mBaseVideoController;
    private long mBroadcastIdentifier;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().addFlags(1024);
        this.mBroadcastIdentifier = getBroadcastIdentifierFromIntent(getIntent());
        try {
            this.mBaseVideoController = createVideoViewController(bundle);
            this.mBaseVideoController.onCreate();
        } catch (IllegalStateException e) {
            EventForwardingBroadcastReceiver.broadcastAction(this, this.mBroadcastIdentifier, EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_FAIL);
            finish();
        }
    }

    protected void onPause() {
        if (this.mBaseVideoController != null) {
            this.mBaseVideoController.onPause();
        }
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
        if (this.mBaseVideoController != null) {
            this.mBaseVideoController.onResume();
        }
    }

    protected void onDestroy() {
        if (this.mBaseVideoController != null) {
            this.mBaseVideoController.onDestroy();
        }
        super.onDestroy();
    }

    protected void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mBaseVideoController != null) {
            this.mBaseVideoController.onSaveInstanceState(bundle);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mBaseVideoController != null) {
            this.mBaseVideoController.onConfigurationChanged(configuration);
        }
    }

    public void onBackPressed() {
        if (this.mBaseVideoController != null && this.mBaseVideoController.backButtonEnabled()) {
            super.onBackPressed();
            this.mBaseVideoController.onBackPressed();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (this.mBaseVideoController != null) {
            this.mBaseVideoController.onActivityResult(i, i2, intent);
        }
    }

    private BaseVideoViewController createVideoViewController(Bundle bundle) {
        String stringExtra = getIntent().getStringExtra(BaseVideoPlayerActivity.VIDEO_CLASS_EXTRAS_KEY);
        if (FullAdType.VAST.equals(stringExtra)) {
            return new VastVideoViewController(this, getIntent().getExtras(), bundle, this.mBroadcastIdentifier, this);
        } else if (AdType.MRAID.equals(stringExtra)) {
            return new MraidVideoViewController(this, getIntent().getExtras(), bundle, this);
        } else {
            throw new IllegalStateException("Unsupported video type: " + stringExtra);
        }
    }

    public void onSetContentView(View view) {
        setContentView(view);
    }

    public void onSetRequestedOrientation(int i) {
        setRequestedOrientation(i);
    }

    public void onFinish() {
        finish();
        overridePendingTransition(0, 0);
    }

    public void onStartActivityForResult(Class<? extends Activity> cls, int i, Bundle bundle) {
        if (cls != null) {
            try {
                startActivityForResult(Intents.getStartActivityIntent(this, cls, bundle), i);
            } catch (ActivityNotFoundException e) {
                MoPubLog.d("Activity " + cls.getName() + " not found. Did you declare it in your AndroidManifest.xml?");
            }
        }
    }

    protected static long getBroadcastIdentifierFromIntent(Intent intent) {
        return intent.getLongExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, -1);
    }

    @Deprecated
    BaseVideoViewController getBaseVideoViewController() {
        return this.mBaseVideoController;
    }

    @Deprecated
    void setBaseVideoViewController(BaseVideoViewController baseVideoViewController) {
        this.mBaseVideoController = baseVideoViewController;
    }
}
