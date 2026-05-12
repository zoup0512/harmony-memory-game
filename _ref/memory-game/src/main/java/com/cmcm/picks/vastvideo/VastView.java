package com.cmcm.picks.vastvideo;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cmcm.utils.Commons;
import com.cmcm.utils.g;
import com.facebook.places.model.PlaceFields;
import com.mopub.volley.DefaultRetryPolicy;

public class VastView implements OnClickListener, Runnable {
    public static final int STATE_DEFAULT = 5;
    public static final int STATE_PAUSE = 2;
    public static final int STATE_PLAY = 1;
    boolean a = false;
    boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;
    private volatile int h = 0;
    private int i;
    private Context j;
    private VastVideoProgressListener k;
    private VastModel l;
    private f m;
    public int mCurrentState = 5;
    private View n = null;
    private e o = null;
    private SurfaceTexture p;
    private c q;
    private a r;
    private TelephonyManager s;
    private d t;
    private b u;
    private c v;
    private a w;
    private Handler x;
    private boolean y;
    private boolean z = true;

    private class a extends BroadcastReceiver {
        final /* synthetic */ VastView a;

        private a(VastView vastView) {
            this.a = vastView;
        }

        private void a() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
            this.a.j.getApplicationContext().registerReceiver(this.a.w, intentFilter);
        }

        private void b() {
            if (this.a.w != null) {
                this.a.j.getApplicationContext().unregisterReceiver(this.a.w);
                this.a.w = null;
            }
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.CLOSE_SYSTEM_DIALOGS")) {
                if ("homekey".equals(intent.getStringExtra("reason")) && this.a.r.h() && this.a.o != null) {
                    this.a.o.setSmallViewClicked(true);
                    this.a.o.a(1, true);
                    this.a.r.d(true);
                }
            }
        }
    }

    private class b extends BroadcastReceiver {
        final /* synthetic */ VastView a;

        private b(VastView vastView) {
            this.a = vastView;
        }

        public void onReceive(Context context, Intent intent) {
            this.a.j = context;
            if (intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION")) {
                boolean z = !this.a.b && f.a(context) == 0.0f;
                this.a.a(f.a(context), z);
            }
        }

        private void a() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.media.VOLUME_CHANGED_ACTION");
            this.a.j.getApplicationContext().registerReceiver(this.a.u, intentFilter);
        }

        private void b() {
            if (this.a.u != null) {
                this.a.j.getApplicationContext().unregisterReceiver(this.a.u);
                this.a.u = null;
            }
        }
    }

    private class c extends PhoneStateListener {
        final /* synthetic */ VastView a;

        private c(VastView vastView) {
            this.a = vastView;
        }

        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state) {
                case 1:
                    if (!this.a.r.d()) {
                        if (!this.a.r.h() || this.a.o == null) {
                            this.a.a(false);
                            return;
                        } else {
                            this.a.o.a(1, false);
                            return;
                        }
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private class d extends BroadcastReceiver {
        final /* synthetic */ VastView a;
        private boolean b;

        private d(VastView vastView) {
            this.a = vastView;
        }

        public void a() {
            if (!this.b) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                intentFilter.setPriority(999);
                this.a.j.getApplicationContext().registerReceiver(this, intentFilter);
                this.b = true;
            }
        }

        public void a(Context context) {
            if (this.b) {
                this.a.j.getApplicationContext().unregisterReceiver(this);
                this.b = false;
            }
        }

        public void onReceive(Context context, Intent intent) {
            this.a.j = context;
            if (!"android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                return;
            }
            if (!this.a.r.h() || this.a.o == null) {
                this.a.a(false);
            } else {
                this.a.o.a(1, false);
            }
        }
    }

    @TargetApi(14)
    private class e implements SurfaceTextureListener {
        final /* synthetic */ VastView a;

        private e(VastView vastView) {
            this.a = vastView;
        }

        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            this.a.p = surface;
            this.a.d = true;
            if (this.a.q != null) {
                this.a.q.a(surface);
                if (this.a.e && !this.a.r.e() && !this.a.r.d() && !this.a.r.h()) {
                    this.a.b(this.a.h);
                }
            }
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            this.a.p = null;
            return false;
        }

        public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        }
    }

    private class f {
        public VastTextureView a;
        public ProgressBar b;
        public ImageView c;
        public ImageView d;
        public RelativeLayout e;
        public LinearLayout f;
        public LinearLayout g;
        public TextView h;
        public RelativeLayout i;
        public TextView j;
        final /* synthetic */ VastView k;

        private f(VastView vastView) {
            this.k = vastView;
        }
    }

    public VastView(Context context, VastModel vastModel, VastVideoProgressListener vastVideoProgressListener) {
        this.j = context;
        this.l = vastModel;
        this.k = vastVideoProgressListener;
        a();
    }

    private void a(int i) {
        if (!(this.m == null || this.m.b == null)) {
            this.m.b.setProgress(i);
        }
        float a = a(i, this.i);
        if (a >= 0.25f && ((double) a) <= 0.4d) {
            this.r.e(this.i, i);
        } else if (a >= 0.5f && a <= 0.6f) {
            this.r.f(this.i, i);
        } else if (((double) a) >= 0.75d && a <= 0.78f) {
            this.r.g(this.i, i);
        }
    }

    private float a(int i, int i2) {
        return ((((float) i) * DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) / 1000.0f) / ((((float) i2) * DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) / 1000.0f);
    }

    private void a() {
        if (this.j != null && this.l != null) {
            if (this.r == null) {
                this.r = new a(this.l);
                this.r.b(true);
            }
            if (this.k != null) {
                this.r.a(this.k);
            }
            this.x = new Handler(Looper.getMainLooper());
            b();
            this.s = (TelephonyManager) this.j.getSystemService(PlaceFields.PHONE);
            this.v = new c();
            this.t = new d();
            this.t.a();
            if (this.u == null) {
                this.u = new b();
            }
            this.u.a();
            if (this.w == null) {
                this.w = new a();
            }
            this.w.a();
        } else if (this.k != null) {
            this.k.onVastVideoShowFail("params is null");
            if (this.r != null) {
                this.r.b(false);
            }
        }
    }

    private void b() {
        this.q = new c();
        this.q.setAudioStreamType(3);
        this.q.setVolume(0.0f, 0.0f);
        this.i = this.q.getVideoHeight();
    }

    private void a(boolean z) {
        this.c = true;
        this.r.a(true, this.i, z);
        if (this.m != null) {
            this.m.e.setVisibility(8);
            this.m.i.setVisibility(0);
            this.m.b.setVisibility(8);
            this.m.d.setVisibility(8);
            this.m.c.setVisibility(8);
        }
        if (this.q != null) {
            this.q.stop();
        }
        this.mCurrentState = 5;
    }

    @TargetApi(14)
    private void b(final int i) {
        if (!this.r.c()) {
            return;
        }
        if (this.r.d()) {
            a(false);
            return;
        }
        this.e = true;
        if (this.d && this.p != null) {
            try {
                if (this.m != null && this.mCurrentState != 1) {
                    this.q.reset();
                    this.q.a(this.p);
                    this.q.setDataSource(this.l.w());
                    this.q.prepare();
                    this.q.setWakeMode(this.j, 10);
                    this.q.setOnPreparedListener(new OnPreparedListener(this) {
                        final /* synthetic */ VastView b;

                        public void onPrepared(MediaPlayer mp) {
                            this.b.i = mp.getDuration();
                            this.b.m.b.setMax(this.b.i);
                            if (this.b.c) {
                                mp.seekTo(this.b.i);
                                this.b.m.b.setProgress(this.b.i);
                            } else {
                                mp.seekTo(this.b.h);
                            }
                            if (i < this.b.i) {
                                mp.start();
                                this.b.mCurrentState = 1;
                                if (!this.b.r.e()) {
                                    if (this.b.h != 0 && this.b.h > Callback.DEFAULT_SWIPE_ANIMATION_DURATION && this.b.r.j()) {
                                        this.b.r.b(this.b.h, this.b.i);
                                    }
                                    this.b.r.d(this.b.i, this.b.h);
                                    this.b.l.a(true);
                                }
                                this.b.x.post(this.b);
                            }
                        }
                    });
                    this.q.setOnErrorListener(new OnErrorListener(this) {
                        final /* synthetic */ VastView a;

                        {
                            this.a = r1;
                        }

                        public boolean onError(MediaPlayer mp, int what, int extra) {
                            return false;
                        }
                    });
                    this.a = false;
                    this.q.setOnCompletionListener(new OnCompletionListener(this) {
                        final /* synthetic */ VastView a;

                        {
                            this.a = r1;
                        }

                        public void onCompletion(MediaPlayer mp) {
                            if (!this.a.a) {
                                this.a.h = this.a.i;
                                this.a.a(true);
                                this.a.a = true;
                            }
                        }
                    });
                }
            } catch (Exception e) {
                if (g.a) {
                    e.printStackTrace();
                }
                this.r.l();
            }
        }
    }

    private void b(boolean z) {
        if (this.mCurrentState != 2 && this.mCurrentState != 5) {
            this.q.pause();
            this.h = this.q.getCurrentPosition();
            this.r.a(this.h);
            if (!this.r.e() && z) {
                this.r.a(this.h, this.i);
            }
            this.mCurrentState = 2;
        }
    }

    public View getView() {
        if (!this.r.c()) {
            return null;
        }
        c();
        this.h = this.r.b();
        if (this.r.d()) {
            a(true);
        }
        d();
        this.f = true;
        return this.n;
    }

    @TargetApi(14)
    private void c() {
        if (this.n == null) {
            this.n = ((LayoutInflater) this.j.getSystemService("layout_inflater")).inflate(Commons.getResourceId(this.j, "cm_vast_ad_layout", "layout", this.j.getPackageName()), null);
            SurfaceTextureListener eVar = new e();
            this.m = new f();
            this.m.a = (VastTextureView) this.n.findViewById(Commons.getResourceId(this.j, "vast_ad", "id", this.j.getPackageName()));
            this.m.b = (ProgressBar) this.n.findViewById(Commons.getResourceId(this.j, "vast_progress", "id", this.j.getPackageName()));
            this.m.c = (ImageView) this.n.findViewById(Commons.getResourceId(this.j, "vast_img_volume", "id", this.j.getPackageName()));
            this.m.c.setOnClickListener(this);
            this.m.a.setSurfaceTextureListener(eVar);
            this.m.a.setOnClickListener(this);
            this.m.e = (RelativeLayout) this.n.findViewById(Commons.getResourceId(this.j, "vast_rl", "id", this.j.getPackageName()));
            this.m.d = (ImageView) this.n.findViewById(Commons.getResourceId(this.j, "vast_img_stranch", "id", this.j.getPackageName()));
            this.m.d.setOnClickListener(this);
            this.m.i = (RelativeLayout) this.n.findViewById(Commons.getResourceId(this.j, "cover_top", "id", this.j.getPackageName()));
            this.m.f = (LinearLayout) this.n.findViewById(Commons.getResourceId(this.j, "vast_watch_again", "id", this.j.getPackageName()));
            this.m.f.setOnClickListener(this);
            this.m.g = (LinearLayout) this.n.findViewById(Commons.getResourceId(this.j, "vast_install", "id", this.j.getPackageName()));
            this.m.g.setOnClickListener(this);
            this.m.h = (TextView) this.n.findViewById(Commons.getResourceId(this.j, "vast_detail", "id", this.j.getPackageName()));
            this.m.j = (TextView) this.n.findViewById(Commons.getResourceId(this.j, "vast_detail", "id", this.j.getPackageName()));
        }
    }

    private void d() {
        if (this.r.f()) {
            a(0.0f, false);
        } else {
            a(f.a(this.j), false);
        }
    }

    public void onResume() {
        this.f = true;
        this.s.listen(this.v, 32);
        if (this.u == null) {
            this.u = new b();
        }
        this.u.a();
        if (this.w == null) {
            this.w = new a();
        }
        this.w.a();
        if (this.t != null) {
            this.t.a();
        }
        this.h = this.r.b();
        if (!this.r.c() || !this.z) {
            return;
        }
        if (this.r.d()) {
            a(true);
            return;
        }
        if (this.h != 0 && this.h > Callback.DEFAULT_SWIPE_ANIMATION_DURATION) {
            if (this.r.f()) {
                a(0.0f, false);
            } else {
                a(f.a(this.j), false);
            }
        }
        this.r.f(true);
        b(this.h);
    }

    public void onPause() {
        if (this.r.c()) {
            this.f = false;
            if (this.u != null) {
                this.u.b();
            }
            if (this.w != null) {
                this.w.b();
            }
            this.s.listen(this.v, 0);
            if (this.r.h() && this.o != null) {
                this.o.a(1, true);
                this.r.a(this.h, this.i);
                this.r.d(true);
            }
            b(true);
        }
    }

    public void onDestory() {
        if (this.r.c()) {
            if (this.q != null) {
                this.q.release();
                this.q = null;
            }
            try {
                this.s.listen(null, 0);
            } catch (Exception e) {
            }
            this.v = null;
            if (this.t != null) {
                this.t.a(this.j);
            }
            if (this.u != null) {
                this.u.b();
            }
            if (this.w != null) {
                this.w.b();
            }
            this.j = null;
            if (this.o != null) {
                this.o.b();
            }
            this.r.i();
            this.l = null;
            this.r = null;
        }
    }

    public void showIfCan(ListView lv, IVastVideoBaseAdapter adapter, ViewGroup smallViewGroup) {
        if (lv != null && adapter != null && smallViewGroup != null) {
            int lastVisiblePosition = lv.getLastVisiblePosition();
            for (int firstVisiblePosition = lv.getFirstVisiblePosition(); firstVisiblePosition <= lastVisiblePosition; firstVisiblePosition++) {
                if (adapter.isVastAdShow(firstVisiblePosition)) {
                    this.z = true;
                } else {
                    this.z = false;
                }
            }
            if (!this.g && lv.getVisibility() == 0) {
                this.r.c(this.i, 0);
                this.g = true;
            }
            show(smallViewGroup);
        } else if (this.k != null) {
            this.k.onVastVideoShowFail("showifcan params is null");
        }
    }

    public void show(ViewGroup smallViewGroup) {
        if (!this.r.c()) {
            return;
        }
        if (this.r.d()) {
            a(false);
        } else if (!this.r.e()) {
            this.h = this.r.b();
            this.r.f(false);
            if (this.z) {
                if (this.o != null && this.o.a()) {
                    this.o.setSmallViewClicked(false);
                }
                if (!(!this.r.h() || smallViewGroup == null || this.o == null)) {
                    smallViewGroup.setVisibility(8);
                    this.o.a(1, true);
                }
                if (!this.r.g()) {
                    this.r.f(false);
                } else if (this.r.k()) {
                    this.r.f(false);
                } else {
                    this.r.f(true);
                }
                if (this.y) {
                    this.r.f(true);
                    this.y = false;
                }
                b(this.h);
            } else if (this.o == null || !this.o.a()) {
                if (!(this.r == null || this.r.h() || smallViewGroup == null)) {
                    this.o = new e(this.j, smallViewGroup, this, this.r);
                    smallViewGroup.setVisibility(0);
                    this.o.a(2, true);
                    smallViewGroup.addView(this.o);
                    this.r.e(true);
                    this.mCurrentState = 1;
                }
                if (this.r.h()) {
                    b(false);
                } else {
                    b(true);
                }
            }
        }
    }

    public void run() {
        if (this.mCurrentState == 1 && this.f && this.q != null) {
            this.h = this.q.getCurrentPosition();
            a(this.h);
            if (this.x != null) {
                this.x.postDelayed(this, 100);
            }
            this.h += 100;
            if (!this.c) {
            }
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == Commons.getResourceId(this.j, "vast_img_volume", "id", this.j.getPackageName())) {
            if (!this.q.isPlaying()) {
                return;
            }
            if (this.r.f()) {
                a(f.a(this.j), true);
            } else {
                a(0.0f, true);
            }
        } else if (id == Commons.getResourceId(this.j, "vast_small_title", "id", this.j.getPackageName()) || id == Commons.getResourceId(this.j, "vast_icon", "id", this.j.getPackageName()) || id == Commons.getResourceId(this.j, "vast_install", "id", this.j.getPackageName()) || id == Commons.getResourceId(this.j, "vast_cover_image", "id", this.j.getPackageName()) || id == Commons.getResourceId(this.j, "vast_ad", "id", this.j.getPackageName())) {
            this.y = true;
            this.r.a(this.j);
            this.r.h(this.h, this.i);
        } else if (id == Commons.getResourceId(this.j, "vast_img_stranch", "id", this.j.getPackageName())) {
            if (!this.r.d()) {
                f();
            }
        } else if (id == Commons.getResourceId(this.j, "vast_watch_again", "id", this.j.getPackageName())) {
            this.y = false;
            this.r.a(true);
            e();
        }
    }

    private void e() {
        this.c = false;
        this.r.a(false, this.i, true);
        this.m.e.setVisibility(0);
        this.m.i.setVisibility(8);
        this.m.b.setVisibility(0);
        this.m.d.setVisibility(0);
        this.m.c.setVisibility(0);
        this.h = 0;
        b(0);
    }

    private void f() {
        this.r.a(this.q.getCurrentPosition());
        b(false);
        if (this.j != null) {
            Intent intent = new Intent(this.j, FullScreenVideoActivity.class);
            intent.setFlags(268435456);
            FullScreenVideoActivity.a(this.r);
            this.j.startActivity(intent);
        }
        this.r.k(this.i, this.h);
    }

    private void a(float f, boolean z) {
        if (f == 0.0f) {
            this.b = true;
            if (this.m != null) {
                this.m.c.setImageResource(Commons.getResourceId(this.j, "vast_volume_off", "drawable", this.j.getPackageName()));
            }
        } else {
            this.b = false;
            if (this.m != null) {
                this.m.c.setImageResource(Commons.getResourceId(this.j, "vast_volume_on", "drawable", this.j.getPackageName()));
            }
        }
        this.h = this.q.getCurrentPosition();
        this.r.a(this.b, z, this.i, this.h);
        float b = f / f.b(this.j);
        this.q.setVolume(b, b);
    }
}
