package com.cmcm.picks.vastvideo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.cmcm.utils.Commons;
import com.cmcm.utils.g;
import com.facebook.places.model.PlaceFields;
import com.mopub.volley.DefaultRetryPolicy;

@TargetApi(14)
public class FullScreenVideoActivity extends Activity implements SurfaceTextureListener, OnClickListener, Runnable {
    private static a e;
    boolean a;
    private c b;
    private VastTextureView c;
    private ProgressBar d;
    private VastModel f;
    private boolean g = false;
    private boolean h = false;
    private Handler i;
    private int j;
    private int k;
    private ImageView l;
    private ImageView m;
    private SurfaceTexture n;
    private TelephonyManager o;
    private b p;
    private c q;
    private a r;
    private boolean s = false;

    private class a extends BroadcastReceiver {
        final /* synthetic */ FullScreenVideoActivity a;

        private a(FullScreenVideoActivity fullScreenVideoActivity) {
            this.a = fullScreenVideoActivity;
        }

        public void onReceive(Context context, Intent intent) {
            boolean z = false;
            if (intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION")) {
                if (f.a(context) == 0.0f) {
                    if (!this.a.a) {
                        z = true;
                    }
                } else if (this.a.a) {
                    z = true;
                }
                this.a.a(f.a(context), z);
            }
        }

        private void a() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.media.VOLUME_CHANGED_ACTION");
            this.a.getApplicationContext().registerReceiver(this.a.r, intentFilter);
        }

        private void b() {
            if (this.a.r != null) {
                this.a.getApplicationContext().unregisterReceiver(this.a.r);
                this.a.r = null;
            }
        }
    }

    private class b extends PhoneStateListener {
        final /* synthetic */ FullScreenVideoActivity a;

        private b(FullScreenVideoActivity fullScreenVideoActivity) {
            this.a = fullScreenVideoActivity;
        }

        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state) {
                case 1:
                    if (!FullScreenVideoActivity.e.d()) {
                        if (this.a.b != null) {
                            FullScreenVideoActivity.e.a(this.a.b.getDuration());
                            this.a.b.stop();
                            FullScreenVideoActivity.e.a(true, this.a.k, false);
                        }
                        this.a.finish();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private class c extends BroadcastReceiver {
        final /* synthetic */ FullScreenVideoActivity a;
        private boolean b;

        private c(FullScreenVideoActivity fullScreenVideoActivity) {
            this.a = fullScreenVideoActivity;
        }

        public void a() {
            if (!this.b) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                intentFilter.setPriority(999);
                this.a.getApplicationContext().registerReceiver(this, intentFilter);
                this.b = true;
            }
        }

        public void b() {
            if (this.b) {
                this.a.getApplicationContext().unregisterReceiver(this);
                this.b = false;
            }
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.SCREEN_OFF".equals(intent.getAction()) && !FullScreenVideoActivity.e.d()) {
                FullScreenVideoActivity.e.a(true, this.a.k, false);
                this.a.finish();
            }
        }
    }

    public static void a(a aVar) {
        e = aVar;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Commons.getResourceId(this, "cm_activity_full_screen_video", "layout", getPackageName()));
        if (e == null) {
            finish();
            return;
        }
        this.f = e.a();
        if (this.f == null) {
            finish();
            return;
        }
        b();
        e();
        this.o = (TelephonyManager) getSystemService(PlaceFields.PHONE);
        this.p = new b();
        this.q = new c();
        this.q.a();
        if (this.r == null) {
            this.r = new a();
        }
        this.r.a();
    }

    private void b() {
        this.i = new Handler();
        this.b = new c();
        this.b.setAudioStreamType(3);
        this.c = (VastTextureView) findViewById(Commons.getResourceId(this, "video_full_screen", "id", getPackageName()));
        this.c.setSurfaceTextureListener(this);
        this.d = (ProgressBar) findViewById(Commons.getResourceId(this, "video_full_screen_progress", "id", getPackageName()));
        this.l = (ImageView) findViewById(Commons.getResourceId(this, "vast_full_img_stranch", "id", getPackageName()));
        this.m = (ImageView) findViewById(Commons.getResourceId(this, "vast_full_img_volume", "id", getPackageName()));
        this.l.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.c.setOnClickListener(this);
    }

    protected void onStart() {
        super.onStart();
        if (e.d()) {
            finish();
        }
        e.c(true);
    }

    protected void onResume() {
        super.onResume();
        if (this.r == null) {
            this.r = new a();
        }
        this.r.a();
        this.o.listen(this.p, 32);
        c();
    }

    private void c() {
        this.h = true;
        if (this.n != null && this.g) {
            try {
                this.b.reset();
                this.b.a(this.n);
                this.b.setDataSource(this.f.w());
                this.b.prepare();
                this.b.setWakeMode(this, 10);
                this.b.setOnPreparedListener(new OnPreparedListener(this) {
                    final /* synthetic */ FullScreenVideoActivity a;

                    {
                        this.a = r1;
                    }

                    public void onPrepared(MediaPlayer mediaPlayer) {
                        this.a.b.seekTo(FullScreenVideoActivity.e.b());
                        this.a.b.start();
                        this.a.k = this.a.b.getDuration();
                        this.a.d.setMax(this.a.k);
                        this.a.i.post(this.a);
                    }
                });
                this.b.setOnCompletionListener(new OnCompletionListener(this) {
                    final /* synthetic */ FullScreenVideoActivity a;

                    {
                        this.a = r1;
                    }

                    public void onCompletion(MediaPlayer mediaPlayer) {
                        FullScreenVideoActivity.e.a(true, this.a.k, true);
                        this.a.finish();
                    }
                });
            } catch (Exception e) {
                if (g.a) {
                    e.printStackTrace();
                }
                e.l();
            }
        }
    }

    public void run() {
        if (this.b != null) {
            this.j = this.b.getCurrentPosition();
            this.d.setProgress(this.j);
            a(this.j);
            if (this.i != null) {
                this.i.postDelayed(this, 100);
            }
            this.j += 100;
        }
    }

    protected void onPause() {
        super.onPause();
        if (this.r != null) {
            this.r.b();
        }
        this.o.listen(this.p, 0);
        this.i.removeCallbacks(this);
        e.c(false);
        if (!e.d()) {
            d();
        }
    }

    private void d() {
        if (this.b != null) {
            this.j = this.b.getCurrentPosition();
            e.a(this.j);
            if (this.s) {
                e.g(true);
            } else {
                e.a(this.j, this.k);
                e.g(false);
            }
            this.s = false;
            this.b.pause();
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == Commons.getResourceId(this, "vast_full_img_stranch", "id", getPackageName())) {
            finish();
            this.s = true;
        } else if (id == Commons.getResourceId(this, "vast_full_img_volume", "id", getPackageName())) {
            if (e.f()) {
                a(f.a((Context) this), true);
            } else {
                a(0.0f, true);
            }
            this.s = true;
        } else if (id == Commons.getResourceId(this, "video_full_screen", "id", getPackageName())) {
            e.a((Context) this);
            e.h(this.j, this.k);
            finish();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.b != null) {
            this.b.release();
            this.b = null;
        }
        if (this.r != null) {
            this.r.b();
        }
        if (this.q != null) {
            this.q.b();
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.b != null) {
            this.n = surfaceTexture;
            this.g = true;
            if (this.h) {
                c();
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

    private void a(int i) {
        float b = b(i);
        if (b >= 0.25f && ((double) b) <= 0.4d) {
            e.e(this.k, i);
        } else if (b >= 0.5f && b <= 0.65f) {
            e.f(this.k, i);
        } else if (((double) b) >= 0.75d && b <= 0.78f) {
            e.g(this.k, i);
        }
    }

    private float b(int i) {
        return ((((float) i) * DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) / 1000.0f) / ((((float) this.k) * DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) / 1000.0f);
    }

    private void a(float f, boolean z) {
        if (f == 0.0f) {
            this.a = true;
            this.m.setImageResource(Commons.getResourceId(this, "vast_volume_off", "drawable", getPackageName()));
        } else {
            this.a = false;
            this.m.setImageResource(Commons.getResourceId(this, "vast_volume_on", "drawable", getPackageName()));
        }
        this.j = this.b.getCurrentPosition();
        e.a(this.a, z, this.k, this.j);
        float b = f / f.b(this);
        this.b.setVolume(b, b);
    }

    private void e() {
        if (e.f()) {
            a(0.0f, false);
        } else {
            a(f.a((Context) this), false);
        }
    }

    public void onBackPressed() {
        this.s = true;
        super.onBackPressed();
    }
}
