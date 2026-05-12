package com.appodeal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.VideoView;

public class VideoPlayerActivity extends Activity implements OnCompletionListener, OnErrorListener, OnPreparedListener {
    private int a;
    private VideoView b;
    private a c;

    public interface a {
        void a(int i, boolean z);
    }

    public static Intent a(Context context, String str, int i) {
        Intent intent = new Intent(context, VideoPlayerActivity.class);
        intent.putExtra("com.appodeal.ads.fileUri", str);
        intent.putExtra("com.appodeal.ads.seekTo", i);
        return intent;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(128);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("com.appodeal.ads.fileUri");
        this.a = intent.getIntExtra("com.appodeal.ads.seekTo", 0);
        Appodeal.a(String.format("VideoPlayerActivity started, position: %s", new Object[]{Integer.valueOf(this.a)}));
        if (stringExtra != null) {
            this.c = u.c;
            View relativeLayout = new RelativeLayout(this);
            relativeLayout.setLayoutParams(new LayoutParams(-1, -1));
            this.b = new VideoView(this);
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
            layoutParams.addRule(13);
            this.b.setLayoutParams(layoutParams);
            this.b.setOnCompletionListener(this);
            this.b.setOnPreparedListener(this);
            this.b.setVideoPath(stringExtra);
            relativeLayout.addView(this.b);
            View imageView = new ImageView(this);
            int round = Math.round(40.0f * an.i(this));
            ViewGroup.LayoutParams layoutParams2 = new LayoutParams(round, round);
            layoutParams2.addRule(11, -1);
            layoutParams2.addRule(10, -1);
            round = Math.round(16.0f * an.i(this));
            layoutParams2.setMargins(round, round, round, round);
            imageView.setLayoutParams(layoutParams2);
            imageView.setScaleType(ScaleType.FIT_CENTER);
            imageView.setImageResource(17301560);
            imageView.setBackgroundColor(Color.parseColor("#6b000000"));
            imageView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ VideoPlayerActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.b();
                }
            });
            relativeLayout.addView(imageView);
            setContentView(relativeLayout);
        }
    }

    private void a() {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ VideoPlayerActivity a;

            {
                this.a = r1;
            }

            public void run() {
                try {
                    this.a.getWindow().clearFlags(128);
                } catch (Throwable e) {
                    Appodeal.a(e);
                }
            }
        });
        finish();
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        if (this.c != null) {
            this.c.a(0, true);
        }
        a();
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        a();
        return false;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        if (this.b != null && this.b.canSeekForward()) {
            this.b.seekTo(this.a);
            this.b.start();
        }
    }

    public void onBackPressed() {
        b();
    }

    private void b() {
        if (this.c != null) {
            int currentPosition;
            if (this.b.isPlaying()) {
                currentPosition = this.b.getCurrentPosition();
            } else {
                currentPosition = 0;
            }
            this.c.a(currentPosition, false);
        }
        a();
    }
}
