package com.appodeal.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.appodeal.ads.Native.NativeAdType;
import com.appodeal.ads.utils.k;
import com.appodeal.ads.utils.l;
import com.mopub.volley.DefaultRetryPolicy;
import java.io.File;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.nexage.sourcekit.vast.model.TRACKING_EVENTS_TYPE;
import org.nexage.sourcekit.vast.model.VASTModel;

public class u extends RelativeLayout implements OnCompletionListener, OnErrorListener, OnPreparedListener, OnVideoSizeChangedListener, Callback, com.appodeal.ads.VideoPlayerActivity.a {
    public static com.appodeal.ads.VideoPlayerActivity.a c;
    private Bitmap A;
    private Bitmap B;
    private a C = a.IMAGE;
    ab a;
    boolean b = false;
    private ImageView d;
    private ProgressBar e;
    private ImageView f;
    private ImageView g;
    private MediaPlayer h;
    private SurfaceView i;
    private SurfaceHolder j;
    private Timer k;
    private boolean l;
    private boolean m;
    private boolean n = true;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private VASTModel v;
    private int w;
    private int x;
    private final String y = "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";
    private final String z = "iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAQAAAAAYLlVAAAAAmJLR0QA/4ePzL8AAAAJcEhZcwAACxIAAAsSAdLdfvwAAAAJdnBBZwAAAEAAAABAAOrz+GAAAAV5SURBVGje7dhdiF1XFQfw3z73zlQTEtNJoo3WxlQ0TakfaaHaVOyDiIpaEDT6EESF+l4sRR8U/Hyx6IPigyLogw/WWKxgKKJIU7AfKCiGxqiRGJI2Jg1JWmzMzD3n78M5c+cjdyZ3ElN9mP+BuZs7d+31P2utvdZei1WsYhWr+B+jrFwkKFL0OumoS3I5W10+AaX9uOLN9C9DOZXGDb5srSjO+KzToyn91xGRkpJ1+X3m8O1IlavPoFNfRb6X5EJmUiepcyJbWwovBYFe5JNJBmmSnM3XcjzJxyL9q0wgOkPvyOk0aTJI8sPIT5J8J1IyQqaVs6R7qhVxKIq+B0xpFDT2qjyOd+ov1tEprhTLUhj/7Vvz35M6M2lSJzmarZE70+TFbIiUETILnis3/+tzOkmTZCbJIxGZynSSOyPVIpmSKq/Lt/LNLnSviEBJlSoPJxkk3d+vR6pM5G9J9pDeIpkq8kCS03lja7+LMVYMpP1l4xPuVncyFQ6C2t9xIxaHYcF+/zblXUulySUJzMZvZtXXXu0rouq2KmqHCjSOY0vmyWZuk8ccxvtFvQICmXuD2S+KL9nSRX+Lgae71bPYNPv7VjY9RaPvjEexy0Yji8UyLkirtq9nUuW9Pj40f6vlhH+FUngBU/oG81TUw7P3S1zrrhEuslwxqjTe6hs2aBRxowlzNTeKU8Owfh6Tivk1+Raf9kffV+x3zivs8pBKYzy0eS37Mh/NvPUgyYORkj75QJLfZk2bCSLSz9NJfpV+Sqo8muTh0edgKRcUcZN3a4bPwhtHhR2uFxUmsNnmQklRFLWn1HbaLhp/wmusU18cBUsRqPB2fUXVPWURwcYtHrRRvSC4KkRP/FTPlB3gADbazPgESmEHy/isMnCHj6i1QdgTDMQaDQ46jp3gn62FVkQAN1i+glRijwmcQ1/Brfb5qkZxxGG8WVU4beDlrikjN1kak5ZHUew0OTR8hbd5n90mVQbde/cxbVo1OhMsR+BStaOgZ4s2K77SJvxBbcqkBv8Y7t9G0MhkvLL7wKUxrUd3N5gZR2A5AuPcshvPauP+pFNYb6B2QYVtZoN4mUq8HIHpSyqPJ013aoqCd+g7aFqDKRwxg54JjTNGuHWpVBwcvYQVGn0/MoP12gPIXhNOqnGd1+KQ4FoTzjs/ygxLEkhb7Ze2UKPvcT/TU1vf2YODvoC+gTfZbuBJwqtwzvMrcUGDJzQyTMSLLVQ5YLfn9AwvD6Gv6qRvF8ccAG/ACc+McsFSBKI45DfzEvFC0TbXHVM02nh/1gnUGkXjZT6l5ylHVYqbcNJZ1fgx0Gb7+7pyHGvcvKAcFWwzYUaw1bBdLRJFfMbd9iIm3Y7D6I13NM27Upf000s/1+TnSeoFpflIJiM98rkk+yLVsBzPPlXkrjRpsmdl5bh9ySIGarUL7nPSXHEquM7a0obrOm096M0zcTs9qPAexbT9aMq4BMqsIWY/e/7i84t82Hdzt9qCY4ajA0p7JStqfXeI3839f0wLtNuUWSq1ynf9Qm9og+jZ3t2Yr8fZuXQ/XFXiNrcpHtLojdIzRi0oc4t7nerivnXGjs7Y2/BnF98egt3WedH+S+tZFsPO8KPDzmhxa3brqNaMvCW/ziOZSDWqe14ZgZbCDzoKc83prjR5IWtHNafd9XbyipvTYXtasil/7dQPUueDqXJvkscubj5n2/Ple+Ox7wNdE1Y85x7TWv9WPqyxS5u2ywgJpZlbXyHmueH+JDPDEc0zST70EoxohkOqXuTHC4ZUR7KlnQBcdQzHdBsWjOm+2FnmMnZcoXMuGlQ2Kifc7zyLDsHVITAksag8l26CvHKscFQ7VJgFw+rBZb7LKlaxilX8P+A/x3xh0YwAAbMAAAAUdEVYdENyZWF0aW9uIFRpbWUAMi8zLzEx5z+uYQAAACV0RVh0ZGF0ZTpjcmVhdGUAMjAxMS0wNy0yMlQxNDozMjoyNy0wNzowMHhe2msAAAAldEVYdGRhdGU6bW9kaWZ5ADIwMTEtMDctMjJUMTQ6MzI6MjctMDc6MDAJA2LXAAAAHHRFWHRTb2Z0d2FyZQBBZG9iZSBGaXJld29ya3MgQ1M1cbXjNgAAAABJRU5ErkJggg==";

    public enum a {
        IMAGE,
        PLAYING,
        LOADING,
        PAUSED
    }

    public u(Context context) {
        super(context);
    }

    public u(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public u(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setNativeAd(ab abVar) {
        this.a = abVar;
        if (!(Native.A == NativeAdType.NoVideo || ((abVar.g() == null || abVar.g().isEmpty()) && (abVar.h() == null || abVar.h().isEmpty())))) {
            this.s = true;
            if (abVar.i() != null) {
                this.v = abVar.i();
            }
        }
        a();
    }

    void a() {
        if (!this.b) {
            this.b = true;
            this.d = new ImageView(getContext());
            this.d.setLayoutParams(new LayoutParams(-1, -1));
            this.d.setScaleType(ScaleType.FIT_CENTER);
            this.d.setAdjustViewBounds(true);
            addView(this.d);
            if (this.s) {
                int round = Math.round(50.0f * an.i(getContext()));
                this.e = new ProgressBar(getContext(), null, 16842874);
                ViewGroup.LayoutParams layoutParams = new LayoutParams(round, round);
                layoutParams.addRule(13, -1);
                this.e.setLayoutParams(layoutParams);
                this.e.setBackgroundColor(Color.parseColor("#6b000000"));
                addView(this.e);
                this.f = new ImageView(getContext());
                this.f.setImageResource(17301540);
                layoutParams = new LayoutParams(round, round);
                layoutParams.addRule(13, -1);
                this.f.setLayoutParams(layoutParams);
                this.f.setBackgroundColor(Color.parseColor("#6b000000"));
                this.f.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ u a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.r = true;
                        this.a.i();
                    }
                });
                addView(this.f);
                this.i = new SurfaceView(getContext());
                this.i.setLayoutParams(new LayoutParams(-1, -1));
                this.i.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ u a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        Appodeal.a("Video clicked");
                        u.c = this.a;
                        this.a.u = true;
                        int i = 0;
                        if (this.a.h != null && this.a.h.isPlaying()) {
                            i = this.a.h.getCurrentPosition();
                        }
                        this.a.e();
                        this.a.getContext().startActivity(VideoPlayerActivity.a(this.a.getContext(), this.a.a.j().getPath(), i));
                    }
                });
                this.j = this.i.getHolder();
                this.j.addCallback(this);
                addView(this.i);
                n();
                f();
                if (Native.A != NativeAdType.Video || this.a == null || this.a.j() == null || !new File(this.a.j().getPath()).exists()) {
                    this.C = a.LOADING;
                    j();
                    if (this.a.g() != null && !this.a.g().isEmpty()) {
                        a(new k(Appodeal.b, new com.appodeal.ads.utils.k.a(this) {
                            final /* synthetic */ u a;

                            {
                                this.a = r1;
                            }

                            public void a(Uri uri) {
                                this.a.a.a(uri);
                                this.a.g();
                            }

                            public void a() {
                                this.a.C = a.IMAGE;
                                this.a.j();
                                this.a.s = false;
                            }
                        }, this.a.g()));
                    } else if (!(this.a.h() == null || this.a.h().isEmpty())) {
                        a(new l(Appodeal.b, new com.appodeal.ads.utils.l.a(this) {
                            final /* synthetic */ u a;

                            {
                                this.a = r1;
                            }

                            public void a(Uri uri, VASTModel vASTModel) {
                                this.a.v = vASTModel;
                                this.a.a.a(vASTModel);
                                this.a.a.a(uri);
                                this.a.g();
                            }

                            public void a() {
                                this.a.C = a.IMAGE;
                                this.a.j();
                                this.a.s = false;
                            }
                        }, this.a.h()));
                    }
                } else {
                    this.r = Native.B;
                }
            } else {
                this.C = a.IMAGE;
                j();
                this.d.bringToFront();
            }
        }
        if (this.a != null && this.a.getImage() != null) {
            this.d.setImageBitmap(this.a.getImage());
        }
    }

    protected void onWindowVisibilityChanged(int i) {
        if (Native.A != NativeAdType.NoVideo) {
            if (i != 0) {
                e();
            } else if (this.r) {
                i();
            }
        }
        super.onWindowVisibilityChanged(i);
    }

    private void e() {
        if (this.h != null && this.h.isPlaying()) {
            this.h.pause();
        }
        if (this.C != a.LOADING) {
            this.C = a.PAUSED;
            j();
        }
    }

    private void f() {
        this.h = new MediaPlayer();
        this.h.setOnCompletionListener(this);
        this.h.setOnErrorListener(this);
        this.h.setOnPreparedListener(this);
        this.h.setOnVideoSizeChangedListener(this);
        this.h.setAudioStreamType(3);
        o();
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (Native.A != NativeAdType.NoVideo) {
            if (this.h == null) {
                f();
            }
            this.h.setDisplay(this.j);
            g();
        }
    }

    private void g() {
        try {
            if (!this.o && this.a.j() != null && !this.p && !this.t) {
                this.h.setDataSource(getContext(), this.a.j());
                this.h.prepareAsync();
                this.p = true;
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    private void h() {
        if (this.h != null) {
            if (this.h.isPlaying()) {
                this.h.stop();
            }
            this.h.setOnCompletionListener(null);
            this.h.setOnErrorListener(null);
            this.h.setOnPreparedListener(null);
            this.h.setOnVideoSizeChangedListener(null);
            this.h.reset();
            this.h.release();
            this.h = null;
        }
    }

    private void i() {
        if (this.h == null) {
            f();
        }
        if (!this.o) {
            g();
        }
        if (this.h != null && !this.h.isPlaying() && this.o && this.q && q() && !this.t) {
            this.C = a.PLAYING;
            j();
            this.h.start();
            l();
            if (this.k == null) {
                c();
            }
        }
    }

    private void j() {
        switch (this.C) {
            case IMAGE:
                if (this.d != null) {
                    this.d.setVisibility(0);
                    this.d.bringToFront();
                }
                if (this.s) {
                    this.i.setVisibility(4);
                    this.e.setVisibility(4);
                    this.f.setVisibility(4);
                    this.g.setVisibility(4);
                    return;
                }
                return;
            case LOADING:
                if (this.d != null) {
                    this.d.setVisibility(0);
                    this.d.bringToFront();
                }
                if (this.s) {
                    this.e.setVisibility(0);
                    this.e.bringToFront();
                    this.i.setVisibility(4);
                    this.f.setVisibility(4);
                    this.g.setVisibility(4);
                    return;
                }
                return;
            case PLAYING:
                if (this.d != null) {
                    this.d.setVisibility(4);
                }
                if (this.s) {
                    this.i.setVisibility(0);
                    this.i.bringToFront();
                    this.g.setVisibility(0);
                    this.g.bringToFront();
                    p();
                    this.e.setVisibility(4);
                    this.f.setVisibility(4);
                    return;
                }
                return;
            case PAUSED:
                if (this.d != null) {
                    this.d.setVisibility(0);
                    this.d.bringToFront();
                }
                if (this.s) {
                    this.f.setVisibility(0);
                    this.f.bringToFront();
                    this.i.setVisibility(4);
                    this.e.setVisibility(4);
                    this.g.setVisibility(4);
                    return;
                }
                return;
            default:
                return;
        }
    }

    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (mode != 1073741824) {
            size = mode == Integer.MIN_VALUE ? Math.min(size, measuredWidth) : measuredWidth;
        }
        mode = (int) (0.5625f * ((float) size));
        if (mode2 != 1073741824 || size2 >= mode) {
            size2 = mode;
            mode = size;
        } else {
            mode = (int) (1.7777778f * ((float) size2));
        }
        if (Math.abs(size2 - measuredHeight) >= 2 || Math.abs(mode - measuredWidth) >= 2) {
            getLayoutParams().width = mode;
            getLayoutParams().height = size2;
        }
        super.onMeasure(i, i2);
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        k();
    }

    private void k() {
        m();
        d();
        e();
        this.h.seekTo(0);
        this.u = true;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        Appodeal.a("onError");
        this.r = false;
        h();
        this.C = a.IMAGE;
        j();
        d();
        this.t = true;
        this.s = false;
        s();
        return true;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        Appodeal.a("onPrepared");
        this.o = true;
        if (Native.A == NativeAdType.NoVideo) {
            return;
        }
        if (this.r) {
            i();
            return;
        }
        this.C = a.PAUSED;
        j();
    }

    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
    }

    public void b() {
        Appodeal.a("onViewAppearOnScreen");
        this.q = true;
        if (Native.A != NativeAdType.Video) {
            return;
        }
        if (this.r) {
            i();
        } else if (this.C != a.LOADING) {
            this.C = a.PAUSED;
            j();
        }
    }

    private void l() {
        if (!this.l) {
            r();
            this.l = true;
            Appodeal.a("MediaView video started");
        }
    }

    private void m() {
        if (!this.m) {
            a(TRACKING_EVENTS_TYPE.complete);
            this.m = true;
            Appodeal.a("MediaView video finish");
        }
    }

    private void n() {
        this.g = new ImageView(getContext());
        byte[] decode = Base64.decode("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", 0);
        this.A = BitmapFactory.decodeByteArray(decode, 0, decode.length);
        decode = Base64.decode("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", 0);
        this.B = BitmapFactory.decodeByteArray(decode, 0, decode.length);
        int round = Math.round(50.0f * an.i(getContext()));
        ViewGroup.LayoutParams layoutParams = new LayoutParams(round, round);
        layoutParams.addRule(9);
        layoutParams.addRule(10);
        this.g.setLayoutParams(layoutParams);
        p();
        this.g.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ u a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.h != null) {
                    if (this.a.n) {
                        this.a.h.setVolume(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                        this.a.n = false;
                    } else {
                        this.a.h.setVolume(0.0f, 0.0f);
                        this.a.n = true;
                    }
                    this.a.p();
                }
            }
        });
        addView(this.g);
    }

    private void o() {
        if (this.h == null) {
            return;
        }
        if (this.n) {
            this.h.setVolume(0.0f, 0.0f);
        } else {
            this.h.setVolume(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        }
    }

    private void p() {
        if (this.g == null) {
            return;
        }
        if (this.n) {
            this.g.setImageBitmap(this.A);
        } else {
            this.g.setImageBitmap(this.B);
        }
    }

    private boolean q() {
        return getGlobalVisibleRect(new Rect()) && isShown() && hasWindowFocus();
    }

    void c() {
        if (this.s) {
            this.k = new Timer();
            this.k.schedule(new TimerTask(this) {
                final /* synthetic */ u a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.q()) {
                        if (this.a.h != null && this.a.h.isPlaying()) {
                            if (this.a.w == 0) {
                                this.a.w = this.a.h.getDuration();
                            }
                            if (this.a.w != 0 && (this.a.h.getCurrentPosition() * 100) / this.a.w >= this.a.x * 25) {
                                if (this.a.x == 0) {
                                    Appodeal.a(String.format("Video at start: (%s)", new Object[]{Integer.valueOf(r0)}));
                                    this.a.a(TRACKING_EVENTS_TYPE.start);
                                } else if (this.a.x == 1) {
                                    Appodeal.a(String.format("Video at first quartile: (%s)", new Object[]{Integer.valueOf(r0)}));
                                    this.a.a(TRACKING_EVENTS_TYPE.firstQuartile);
                                } else if (this.a.x == 2) {
                                    Appodeal.a(String.format("Video at midpoint: (%s)", new Object[]{Integer.valueOf(r0)}));
                                    this.a.a(TRACKING_EVENTS_TYPE.midpoint);
                                } else if (this.a.x == 3) {
                                    Appodeal.a(String.format("Video at third quartile: (%s)", new Object[]{Integer.valueOf(r0)}));
                                    this.a.a(TRACKING_EVENTS_TYPE.thirdQuartile);
                                }
                                this.a.x = this.a.x + 1;
                            }
                        }
                        Appodeal.b.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass6 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.i();
                            }
                        });
                        return;
                    }
                    Appodeal.b.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass6 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.e();
                            if (!Native.B || this.a.a.u) {
                                this.a.a.d();
                            }
                        }
                    });
                }
            }, 0, (long) 500);
        }
    }

    void d() {
        if (this.k != null) {
            this.k.cancel();
            this.k = null;
        }
    }

    private void a(k kVar) {
        if (VERSION.SDK_INT >= 11) {
            kVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new com.appodeal.ads.utils.k.a[0]);
        } else {
            kVar.execute(new com.appodeal.ads.utils.k.a[0]);
        }
    }

    private void a(l lVar) {
        if (VERSION.SDK_INT >= 11) {
            lVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new com.appodeal.ads.utils.l.a[0]);
        } else {
            lVar.execute(new com.appodeal.ads.utils.l.a[0]);
        }
    }

    private void r() {
        if (this.v != null) {
            a(this.v.getImpressions());
        }
    }

    private void a(TRACKING_EVENTS_TYPE tracking_events_type) {
        if (this.v != null) {
            a((List) this.v.getTrackingUrls().get(tracking_events_type));
        }
    }

    private void a(List<String> list) {
        if (list != null) {
            for (String b : list) {
                an.b(b);
            }
        }
    }

    private void s() {
        if (this.v != null) {
            this.v.sendError(VASTModel.ERROR_CODE_ERROR_SHOWING);
        }
    }

    public void a(int i, boolean z) {
        Appodeal.a(String.format("MediaView videoPlayerActivityClosed, position: %s, finished: %s", new Object[]{Integer.valueOf(i), Boolean.valueOf(z)}));
        if (z) {
            try {
                k();
            } catch (Throwable e) {
                Appodeal.a(e);
            }
        } else if (this.h != null) {
            this.h.seekTo(i);
        }
        c = null;
    }
}
