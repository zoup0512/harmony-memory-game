package com.my.target.core.ui.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import com.mopub.volley.DefaultRetryPolicy;
import com.my.target.Tracer;
import com.my.target.nativeads.models.VideoData;
import java.lang.ref.WeakReference;

@TargetApi(14)
public class VideoTextureView extends TextureView implements OnCompletionListener, OnErrorListener, OnPreparedListener {
    private static VideoTextureView a;
    private static WeakReference<com.my.target.core.controllers.a> b;
    private final Runnable c = new Runnable(this) {
        final /* synthetic */ VideoTextureView a;

        {
            this.a = r1;
        }

        public final void run() {
            if (this.a.h != null && this.a.h.isPlaying()) {
                this.a.k = 3;
                this.a.l = null;
                if (this.a.g >= 50) {
                    if (this.a.e != null) {
                        Tracer.d("VideoTextureView: lag common");
                        this.a.b(true);
                    }
                } else if (this.a.j != this.a.h.getCurrentPosition()) {
                    this.a.g = 0;
                    this.a.j = this.a.h.getCurrentPosition();
                    int duration = this.a.h.getDuration();
                    if (this.a.e != null) {
                        this.a.e.a((((float) this.a.j) / 1000.0f), (((float) duration) / 1000.0f));
                    }
                } else {
                    this.a.g = this.a.g + 1;
                }
            } else if (this.a.k == 1) {
                if (this.a.g >= 50) {
                    Tracer.d("VideoTextureView: lag on preparing");
                    this.a.b(true);
                } else {
                    this.a.g = this.a.g + 1;
                }
            }
            this.a.postDelayed(this, 200);
        }
    };
    private VideoData d;
    private a e;
    private boolean f;
    private int g;
    private MediaPlayer h;
    private boolean i;
    private int j;
    private int k;
    private Bitmap l;

    public interface a {
        void a(float f, float f2);

        void a(String str);

        void a(boolean z);

        void e();

        void g();

        void h();
    }

    public static VideoTextureView a(com.my.target.core.controllers.a aVar, Context context) {
        if (a == null) {
            a = new VideoTextureView(context);
            b = new WeakReference(aVar);
            return a;
        }
        if (b != null) {
            com.my.target.core.controllers.a aVar2 = (com.my.target.core.controllers.a) b.get();
            b.clear();
            b = null;
            if (aVar2 != null) {
                aVar2.c();
            }
        }
        if (a.getContext() != context) {
            a.d();
            a = new VideoTextureView(context);
        }
        b = new WeakReference(aVar);
        return a;
    }

    public static void a(com.my.target.core.controllers.a aVar) {
        if (b != null && b.get() == aVar) {
            b.clear();
            b = null;
        }
    }

    public final Bitmap a() {
        return this.l;
    }

    public final int b() {
        return this.k;
    }

    public final boolean c() {
        return this.f;
    }

    public void setVideoListener(a aVar) {
        this.e = aVar;
    }

    public void setWaitingState() {
        this.k = 2;
    }

    public VideoTextureView(Context context) {
        super(context);
    }

    public final void d() {
        Tracer.d("VideoTextureView: call stop from controller state: " + i());
        b(true);
    }

    private String i() {
        switch (this.k) {
            case 1:
                return "preparing";
            case 2:
                return "wait";
            case 3:
                return "playing";
            case 4:
                return "paused";
            case 5:
                return "stopped";
            default:
                return "idle";
        }
    }

    private void b(boolean z) {
        Tracer.d("VideoTextureView: call stop, state: " + i() + " show play " + z);
        if (this.e != null && z) {
            this.e.e();
        }
        this.j = 0;
        j();
        if (this.h != null) {
            this.h.stop();
            this.h.release();
            this.h = null;
        }
        this.k = 5;
    }

    private void j() {
        this.i = false;
        removeCallbacks(this.c);
    }

    public final void a(VideoData videoData, boolean z) {
        Uri parse;
        if (this.e != null) {
            this.e.h();
        }
        if (TextUtils.isEmpty((CharSequence) videoData.getData())) {
            parse = Uri.parse(videoData.getUrl());
        } else {
            parse = Uri.parse("file://" + ((String) videoData.getData()));
        }
        if (!(this.d == null || videoData == this.d)) {
            b(false);
            this.k = 0;
        }
        this.d = videoData;
        if (z) {
            this.k = 2;
        }
        Tracer.d("VideoTextureView: Playing video " + parse.toString() + "state: " + i() + " dims " + getMeasuredHeight() + " " + getMeasuredWidth());
        if (isAvailable()) {
            a(k(), parse);
        }
        setSurfaceTextureListener(new SurfaceTextureListener(this) {
            final /* synthetic */ VideoTextureView b;

            public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                Tracer.d("VideoTextureView: Surface available from callback, playing  force state " + this.b.k + " uri " + parse.toString() + " w= " + i + " h = " + i2);
                switch (this.b.k) {
                    case 1:
                    case 3:
                        if (this.b.f) {
                            this.b.h();
                        } else {
                            this.b.e();
                        }
                        this.b.h.setSurface(new Surface(surfaceTexture));
                        if (this.b.k == 3) {
                            this.b.l();
                            this.b.h.start();
                            return;
                        }
                        return;
                    case 5:
                        return;
                    default:
                        if (VideoTextureView.l(this.b)) {
                            this.b.a(new Surface(surfaceTexture), parse);
                            return;
                        }
                        this.b.f();
                        if (this.b.e != null) {
                            this.b.e.g();
                            return;
                        }
                        return;
                }
            }

            public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            }

            public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                Tracer.d("VideoTextureView: Surface destroyed, state = " + this.b.k);
                if (this.b.h != null) {
                    this.b.h.setSurface(null);
                    switch (this.b.k) {
                        case 1:
                            if (this.b.h != null) {
                                Tracer.d("Release MediaPlayer");
                                this.b.h.release();
                                this.b.h = null;
                            }
                            this.b.k = 2;
                            break;
                        case 2:
                        case 4:
                            break;
                        case 3:
                            this.b.h.pause();
                            this.b.k = 4;
                            break;
                        default:
                            this.b.j();
                            if (this.b.h != null) {
                                Tracer.d("Release MediaPlayer");
                                this.b.h.release();
                                this.b.h = null;
                            }
                            this.b.k = 5;
                            break;
                    }
                }
                return true;
            }

            public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }
        });
    }

    private void a(Surface surface, Uri uri) {
        Tracer.d("VideoTextureView: call play state: " + i() + " url = " + uri.toString());
        if (surface != null) {
            l();
            switch (this.k) {
                case 1:
                    return;
                case 2:
                    if (this.h != null) {
                        Tracer.d("VideoTextureView: trying to start paused mediaplayer, state: " + i());
                        Tracer.d("VideoTextureView: Resume textureView");
                        if (this.h != null) {
                            l();
                            this.k = 3;
                            if (this.f) {
                                h();
                            } else {
                                e();
                            }
                            this.h.setSurface(surface);
                            this.h.start();
                            this.h.seekTo(this.j);
                            return;
                        }
                        this.k = 0;
                        return;
                    }
                    break;
                case 3:
                    if (this.h != null && this.h.isPlaying()) {
                        this.h.setSurface(surface);
                        return;
                    }
                case 4:
                    if (this.h != null) {
                        Tracer.d("VideoTextureView: trying to RESUMING mediaplayer, state: " + i());
                        this.h.setSurface(surface);
                        if (this.e != null) {
                            this.e.g();
                            return;
                        }
                        return;
                    }
                    break;
            }
            this.k = 1;
            this.g = 0;
            this.h = new MediaPlayer();
            this.h.setOnPreparedListener(this);
            this.h.setOnErrorListener(this);
            this.h.setOnCompletionListener(this);
            this.h.setSurface(surface);
            try {
                this.h.setDataSource(getContext(), uri);
                this.h.prepareAsync();
            } catch (Exception e) {
                if (this.e != null) {
                    this.e.a(e.getMessage());
                }
            }
        }
    }

    private Surface k() {
        if (isAvailable()) {
            return new Surface(getSurfaceTexture());
        }
        return null;
    }

    public final void e() {
        this.f = false;
        if (this.h != null) {
            this.h.setVolume(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        }
        if (this.e != null) {
            this.e.a(false);
        }
    }

    private void l() {
        if (!this.i) {
            this.i = true;
            postDelayed(this.c, 200);
        }
    }

    public final void f() {
        j();
        if (this.h != null && this.h.isPlaying()) {
            Tracer.d("VideoTextureView: Pause textureView until available");
            this.k = 2;
            this.h.pause();
        }
    }

    public final void a(boolean z) {
        j();
        if (this.h != null && this.h.isPlaying()) {
            if (z && this.d != null) {
                this.l = getBitmap(this.d.getWidth(), this.d.getHeight());
            }
            Tracer.d("VideoTextureView: Pause textureView, state: " + i());
            this.k = 4;
            this.h.pause();
        }
    }

    public final void g() {
        if (this.h != null) {
            this.h.setVolume(0.3f, 0.3f);
        }
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        Tracer.d("VideoTextureView: call on prepared, state: " + i());
        if (this.k != 1) {
            return;
        }
        if (isAvailable()) {
            Tracer.d("VideoTextureView: call mediaplayer to start visibility " + getVisibility() + " dims = " + getHeight() + " " + getWidth());
            mediaPlayer.setSurface(k());
            if (this.f) {
                h();
            } else {
                e();
            }
            mediaPlayer.start();
            if (this.j != 0) {
                mediaPlayer.seekTo(this.j);
            }
            this.k = 3;
            return;
        }
        Tracer.d("VideoTextureView: mediaplayer is ready, but surface isn't available");
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        float duration = ((float) mediaPlayer.getDuration()) / 1000.0f;
        if (this.e != null) {
            this.e.a(duration, duration);
        }
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        if (this.e != null) {
            this.e.a("Video error: " + i + "," + i2);
        }
        b(true);
        return true;
    }

    public final void h() {
        this.f = true;
        if (this.h != null) {
            this.h.setVolume(0.0f, 0.0f);
        }
        if (this.e != null) {
            this.e.a(true);
        }
    }

    static /* synthetic */ boolean l(VideoTextureView videoTextureView) {
        Rect rect = new Rect();
        if (videoTextureView.getGlobalVisibleRect(rect)) {
            if (((double) (rect.height() * rect.width())) >= ((double) (videoTextureView.getWidth() * videoTextureView.getHeight())) * 0.6000000238418579d) {
                return true;
            }
        }
        return false;
    }
}
