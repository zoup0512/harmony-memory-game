package com.cmcm.picks.vastvideo;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Handler;
import android.os.Looper;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.cmcm.utils.Commons;
import com.cmcm.utils.g;
import com.mopub.volley.DefaultRetryPolicy;

@TargetApi(14)
/* compiled from: VastSmallView */
public class e extends RelativeLayout implements SurfaceTextureListener, OnClickListener, Runnable {
    private a a;
    private c b;
    private Context c;
    private boolean d = false;
    private boolean e = false;
    private SurfaceTexture f;
    private VastModel g;
    private int h;
    private int i;
    private Handler j;
    private VastTextureView k;
    private ViewGroup l;
    private LinearLayout m;
    private VastView n;
    private boolean o;

    public e(Context context, ViewGroup viewGroup, VastView vastView, a aVar) {
        super(context);
        this.c = context;
        this.l = viewGroup;
        this.n = vastView;
        this.a = aVar;
        c();
    }

    public boolean a() {
        return this.o;
    }

    public void setSmallViewClicked(boolean smallViewClicked) {
        this.o = smallViewClicked;
    }

    private void c() {
        if (this.a != null) {
            this.j = new Handler(Looper.getMainLooper());
            inflate(this.c, Commons.getResourceId(this.c, "cm_vast_small_view", "layout", this.c.getPackageName()), this);
            this.b = new c();
            this.k = (VastTextureView) findViewById(Commons.getResourceId(this.c, "vast_small_ad", "id", this.c.getPackageName()));
            this.m = (LinearLayout) findViewById(Commons.getResourceId(this.c, "vast_small_view_close", "id", this.c.getPackageName()));
            this.m.setOnClickListener(this);
            this.k.setOnClickListener(this);
            this.k.setSurfaceTextureListener(this);
            this.g = this.a.a();
            this.a.d(false);
            setSmallViewClicked(false);
            if (this.a.f()) {
                this.b.setVolume(0.0f, 0.0f);
                return;
            }
            float a = f.a(this.c) / f.b(this.c);
            this.b.setVolume(a, a);
        }
    }

    private void d() {
        if (this.a.d()) {
            a(1, false);
            return;
        }
        this.e = true;
        if (this.f != null && this.d) {
            try {
                this.b.reset();
                this.b.a(this.f);
                this.b.setDataSource(this.g.w());
                this.b.prepare();
                this.b.setWakeMode(this.c, 10);
                this.b.setOnPreparedListener(new OnPreparedListener(this) {
                    final /* synthetic */ e a;

                    {
                        this.a = r1;
                    }

                    public void onPrepared(MediaPlayer mediaPlayer) {
                        this.a.b.seekTo(this.a.a.b());
                        this.a.b.start();
                        this.a.i = this.a.b.getDuration();
                        this.a.h = this.a.b.getCurrentPosition();
                        this.a.j.post(this.a);
                    }
                });
                this.b.setOnCompletionListener(new OnCompletionListener(this) {
                    final /* synthetic */ e a;

                    {
                        this.a = r1;
                    }

                    public void onCompletion(MediaPlayer mediaPlayer) {
                        if (!this.a.a.d()) {
                            this.a.a.a(true, this.a.i, true);
                            this.a.a(1, true);
                        }
                    }
                });
            } catch (Exception e) {
                if (g.a) {
                    e.printStackTrace();
                }
                this.a.l();
            }
        }
    }

    public void run() {
        if (this.b != null && !this.a.d() && getVisibility() != 8) {
            a(this.h);
            if (this.j != null) {
                this.j.postDelayed(this, 100);
            }
            this.h += 100;
        }
    }

    private void a(int i) {
        float b = b(i);
        if (b >= 0.25f && b <= 0.28f) {
            this.a.e(this.i, i);
        } else if (b >= 0.5f && b <= 0.53f) {
            this.a.f(this.i, i);
        } else if (b >= 0.75f && b <= 0.78f) {
            this.a.g(this.i, i);
        }
    }

    private float b(int i) {
        return ((((float) i) * DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) / 1000.0f) / ((((float) this.i) * DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) / 1000.0f);
    }

    public void b() {
        if (this.b != null) {
            this.b.release();
            this.b = null;
        }
    }

    public void onClick(View v) {
        int id = v.getId();
        setSmallViewClicked(true);
        if (id == Commons.getResourceId(this.c, "vast_small_view_close", "id", this.c.getPackageName())) {
            a(1, true);
        } else if (id == Commons.getResourceId(this.c, "vast_small_ad", "id", this.c.getPackageName())) {
            this.a.a(this.c);
            this.a.h(this.h, this.i);
            a(1, true);
        }
    }

    public void a(int i, boolean z) {
        switch (i) {
            case 1:
                a(z);
                setVisibility(8);
                return;
            case 2:
                setVisibility(0);
                d();
                return;
            default:
                return;
        }
    }

    private void a(boolean z) {
        if (this.b != null) {
            this.a.e(false);
            if (this.a.d()) {
                this.b.stop();
            } else {
                if (a()) {
                    this.a.a(this.h, this.i);
                    this.a.d(true);
                }
                this.b.pause();
            }
            if (z) {
                this.a.a(this.h);
            } else {
                this.a.a(this.b.getDuration());
            }
            if (this.l != null) {
                this.l.setVisibility(8);
            }
            if (!z) {
                this.a.a(true, this.i, z);
            }
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.b != null) {
            this.f = surfaceTexture;
            this.d = true;
            if (this.e) {
                d();
                this.a.d(false);
            }
        }
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }
}
