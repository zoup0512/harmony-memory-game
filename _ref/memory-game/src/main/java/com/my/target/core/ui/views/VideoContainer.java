package com.my.target.core.ui.views;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.VideoView;
import com.cube.memorygames.billing.IabHelper;
import com.my.target.nativeads.models.VideoData;
import io.branch.referral.BranchError;

public class VideoContainer extends FrameLayout implements OnPreparedListener {
    private long a = 200;
    private VideoView b;
    private a c;
    private int d = 0;
    private boolean e;
    private int f;
    private int g = 10;
    private int h;
    private final Runnable i = new Runnable(this) {
        final /* synthetic */ VideoContainer a;

        {
            this.a = r1;
        }

        public final void run() {
            if (this.a.b != null && this.a.b.isPlaying()) {
                this.a.d = 2;
                if (!this.a.e) {
                    if (this.a.c != null) {
                        this.a.c.a((((float) this.a.b.getDuration()) / 1000.0f));
                    }
                    this.a.e = true;
                    if (this.a.c != null) {
                        this.a.c.a(0.0f, (((float) this.a.b.getDuration()) / 1000.0f));
                    }
                }
                if (((long) this.a.h) >= ((long) (this.a.g * 1000)) / this.a.a) {
                    if (this.a.c != null) {
                        this.a.c.a();
                    }
                } else if (this.a.f != this.a.b.getCurrentPosition()) {
                    this.a.h = 0;
                    this.a.f = this.a.b.getCurrentPosition();
                    int duration = this.a.b.getDuration();
                    if (this.a.c != null) {
                        this.a.c.a((((float) this.a.f) / 1000.0f), (((float) duration) / 1000.0f));
                    }
                } else {
                    if (this.a.c != null) {
                        this.a.c.b();
                    }
                    this.a.h = this.a.h + 1;
                }
            } else if (this.a.d == 1) {
                if (((long) this.a.h) < ((long) (this.a.g * 1000)) / this.a.a) {
                    this.a.h = this.a.h + 1;
                } else if (this.a.c != null) {
                    this.a.c.a();
                }
            }
            this.a.postDelayed(this, this.a.a);
        }
    };
    private final OnCompletionListener j = new OnCompletionListener(this) {
        final /* synthetic */ VideoContainer a;

        {
            this.a = r1;
        }

        public final void onCompletion(MediaPlayer mediaPlayer) {
            this.a.f();
            float a = (((float) mediaPlayer.getDuration()) / 1000.0f);
            if (this.a.c != null) {
                this.a.c.a(a, a);
            }
        }
    };
    private final OnErrorListener k = new OnErrorListener(this) {
        final /* synthetic */ VideoContainer a;

        {
            this.a = r1;
        }

        public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            String str = "";
            if (i == 1) {
                str = "Unknown error: ";
            } else if (i == 100) {
                str = "Server died: ";
            }
            String str2 = "no extra message";
            if (i2 == IabHelper.IABHELPER_SEND_INTENT_FAILED) {
                str2 = "IO Error";
            } else if (i2 == IabHelper.IABHELPER_MISSING_TOKEN) {
                str2 = "Bitstream is not conforming to the related coding standard or file spec.";
            } else if (i2 == IabHelper.IABHELPER_INVALID_CONSUMPTION) {
                str2 = "Bitstream is conforming to the related coding standard or file spec, but the media framework does not support the feature.";
            } else if (i2 == BranchError.ERR_BRANCH_NO_SHARE_OPTION) {
                str2 = "Time out error";
            }
            if (this.a.c != null) {
                this.a.c.a(str + str2);
            }
            return true;
        }
    };

    public static abstract class a {
        public abstract void a();

        public abstract void a(float f);

        public abstract void a(float f, float f2);

        public abstract void a(String str);

        public abstract void b();
    }

    public VideoContainer(Context context) {
        super(context);
        setBackgroundColor(-16777216);
    }

    public VideoContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBackgroundColor(-16777216);
    }

    public VideoContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setBackgroundColor(-16777216);
    }

    public final void a(VideoData videoData) {
        this.e = false;
        Uri parse = Uri.parse(videoData.getUrl());
        a(false);
        this.h = 0;
        this.e = false;
        this.d = 1;
        if (this.b == null) {
            this.b = new VideoView(getContext());
            this.b.setOnPreparedListener(this);
            this.b.setOnErrorListener(this.k);
            this.b.setOnCompletionListener(this.j);
        }
        if (this.b.getParent() == null) {
            addView(this.b, new LayoutParams(-1, -1, 17));
        }
        e();
        try {
            this.b.setVideoURI(parse);
        } catch (Exception e) {
            if (this.c != null) {
                this.c.a(e.getMessage());
            }
        }
    }

    public final void a() {
        this.d = 4;
        f();
        if (this.b != null && this.b.isPlaying()) {
            this.f = this.b.getCurrentPosition();
            this.b.pause();
        }
    }

    public final void b() {
        this.d = 3;
        if (this.b != null) {
            this.b.start();
            this.b.seekTo(this.f);
            e();
        }
    }

    public final void a(boolean z) {
        this.d = 5;
        if (this.b != null) {
            if (VERSION.SDK_INT >= 21 || !z) {
                if (this.b.isPlaying()) {
                    this.b.stopPlayback();
                }
            } else if (this.b.getParent() != null) {
                ((ViewGroup) this.b.getParent()).removeView(this.b);
                this.b.setOnCompletionListener(null);
                this.b.setOnErrorListener(null);
                this.b.setOnPreparedListener(null);
                this.b = null;
            }
        }
        f();
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        if (this.b == null) {
            return;
        }
        if (this.d == 1 || this.d == 3) {
            this.b.requestFocus();
            this.b.start();
        }
    }

    private void e() {
        postDelayed(this.i, this.a);
    }

    private void f() {
        removeCallbacks(this.i);
    }

    public void setVideoListener(a aVar) {
        this.c = aVar;
    }

    public final boolean c() {
        return this.b != null && this.b.isPlaying();
    }

    public final boolean d() {
        return (this.d != 4 || this.b == null || this.b.isPlaying()) ? false : true;
    }

    public void setCheckProgressTime(long j) {
        this.a = j;
    }

    public void setConnectionTimeoutSeconds(int i) {
        this.g = i;
    }
}
