package com.mopub.mobileads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import com.mopub.common.AdType;
import com.mopub.common.DataKeys;
import com.mopub.common.FullAdType;
import com.mopub.common.logging.MoPubLog;

public class BaseVideoPlayerActivity extends Activity {
    public static final String VIDEO_CLASS_EXTRAS_KEY = "video_view_class_name";
    public static final String VIDEO_URL = "video_url";

    public static void startMraid(Context context, String str) {
        try {
            context.startActivity(createIntentMraid(context, str));
        } catch (ActivityNotFoundException e) {
            MoPubLog.d("Activity MraidVideoPlayerActivity not found. Did you declare it in your AndroidManifest.xml?");
        }
    }

    static Intent createIntentMraid(Context context, String str) {
        Intent intent = new Intent(context, MraidVideoPlayerActivity.class);
        intent.setFlags(268435456);
        intent.putExtra(VIDEO_CLASS_EXTRAS_KEY, AdType.MRAID);
        intent.putExtra(VIDEO_URL, str);
        return intent;
    }

    static void startVast(Context context, VastVideoConfig vastVideoConfig, long j) {
        try {
            context.startActivity(createIntentVast(context, vastVideoConfig, j));
        } catch (ActivityNotFoundException e) {
            MoPubLog.d("Activity MraidVideoPlayerActivity not found. Did you declare it in your AndroidManifest.xml?");
        }
    }

    static Intent createIntentVast(Context context, VastVideoConfig vastVideoConfig, long j) {
        Intent intent = new Intent(context, MraidVideoPlayerActivity.class);
        intent.setFlags(268435456);
        intent.putExtra(VIDEO_CLASS_EXTRAS_KEY, FullAdType.VAST);
        intent.putExtra("vast_video_config", vastVideoConfig);
        intent.putExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, j);
        return intent;
    }

    protected void onDestroy() {
        super.onDestroy();
        AudioManager audioManager = (AudioManager) getSystemService("audio");
        if (audioManager != null) {
            audioManager.abandonAudioFocus(null);
        }
    }
}
