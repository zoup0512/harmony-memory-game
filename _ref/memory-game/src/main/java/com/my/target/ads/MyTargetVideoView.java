package com.my.target.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.my.target.Tracer;
import com.my.target.core.engines.b;
import com.my.target.core.engines.f;
import com.my.target.core.facades.d;
import com.my.target.core.facades.d.a;
import com.my.target.core.facades.g;
import com.my.target.core.factories.c;

public class MyTargetVideoView extends RelativeLayout {
    public static final String COMPLETE_STATUS_ERROR = "error";
    public static final String COMPLETE_STATUS_NO_BANNERS = "no_banners";
    public static final String COMPLETE_STATUS_OK = "ok";
    public static final String COMPLETE_STATUS_TIMEOUT = "timeout";
    public static final int DEFAULT_VIDEO_QUALITY = 360;
    private d ad;
    private a adListener = new a() {
        public void onLoad(d dVar) {
            if (dVar == MyTargetVideoView.this.ad) {
                if (MyTargetVideoView.this.engine == null) {
                    b a = c.a(dVar, MyTargetVideoView.this, MyTargetVideoView.this.getContext());
                    if (a instanceof f) {
                        MyTargetVideoView.this.engine = (f) a;
                    }
                } else {
                    MyTargetVideoView.this.engine.a((g) dVar);
                }
                if (MyTargetVideoView.this.listener != null) {
                    MyTargetVideoView.this.listener.onLoad(MyTargetVideoView.this);
                }
            }
        }

        public void onNoAd(String str, d dVar) {
            if (MyTargetVideoView.this.listener != null) {
                MyTargetVideoView.this.listener.onNoAd(str, MyTargetVideoView.this);
            }
        }
    };
    private f engine;
    private boolean fullscreen;
    private boolean isInitialized;
    private MyTargetVideoViewListener listener;
    private int videoQuality = DEFAULT_VIDEO_QUALITY;

    public static class BannerInfo {
        public final boolean allowClose;
        public final float allowCloseDelay;
        public String ctaText;
        public float duration;
        public final int videoHeight;
        public final int videoWidth;

        public BannerInfo(boolean z, float f, float f2, int i, int i2) {
            this.allowClose = z;
            this.allowCloseDelay = f;
            this.duration = f2;
            this.videoWidth = i;
            this.videoHeight = i2;
        }
    }

    public interface MyTargetVideoViewListener {
        void onComplete(String str, MyTargetVideoView myTargetVideoView, String str2);

        void onCompleteBanner(MyTargetVideoView myTargetVideoView, BannerInfo bannerInfo, String str);

        void onError(String str, MyTargetVideoView myTargetVideoView);

        void onLoad(MyTargetVideoView myTargetVideoView);

        void onNoAd(String str, MyTargetVideoView myTargetVideoView);

        void onResumptionBanner(MyTargetVideoView myTargetVideoView, BannerInfo bannerInfo);

        void onStartBanner(MyTargetVideoView myTargetVideoView, BannerInfo bannerInfo);

        void onSuspenseBanner(MyTargetVideoView myTargetVideoView, BannerInfo bannerInfo);

        void onTimeLeftChange(float f, float f2, MyTargetVideoView myTargetVideoView);
    }

    public MyTargetVideoViewListener getListener() {
        return this.listener;
    }

    public void setListener(MyTargetVideoViewListener myTargetVideoViewListener) {
        this.listener = myTargetVideoViewListener;
    }

    public int getVideoQuality() {
        return this.videoQuality;
    }

    public void setVideoQuality(int i) {
        this.videoQuality = i;
    }

    public MyTargetVideoView(Context context) {
        super(context);
        Tracer.i("MyTargetVideoView created. Version: 4.5.10");
    }

    public MyTargetVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Tracer.i("MyTargetVideoView created. Version: 4.5.10");
    }

    public MyTargetVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Tracer.i("MyTargetVideoView created. Version: 4.5.10");
    }

    @TargetApi(21)
    public MyTargetVideoView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Tracer.i("MyTargetVideoView created. Version: 4.5.10");
    }

    public void init(int i) {
        init(i, null);
    }

    public void init(int i, CustomParams customParams) {
        if (!this.isInitialized) {
            this.ad = new d(i, customParams);
            this.ad.a(getContext());
            this.ad.a(this.adListener);
            this.isInitialized = true;
            Tracer.d("AdView initialized");
        }
    }

    public void load() {
        if (checkInit()) {
            this.ad.load();
        }
    }

    public void skipBanner() {
        if (this.engine != null) {
            this.engine.g();
        }
    }

    public void stop() {
        if (this.engine != null) {
            this.engine.c();
        }
    }

    public void pause() {
        if (this.engine != null) {
            this.engine.a();
        }
    }

    public void resume() {
        if (this.engine != null) {
            this.engine.b();
        }
    }

    public void destroy() {
        if (this.isInitialized) {
            if (this.engine != null) {
                this.engine.f();
                this.engine = null;
            }
            this.isInitialized = false;
            this.ad.a(null);
            this.ad = null;
        }
        this.listener = null;
    }

    private boolean checkInit() {
        if (this.isInitialized) {
            return true;
        }
        Tracer.d("AdView not initialized");
        return false;
    }

    public void closedByUser() {
        if (this.engine != null) {
            this.engine.i();
        }
    }

    public void fullscreen(boolean z) {
        if (this.engine != null) {
            this.engine.a(z);
        }
    }

    private void start(com.my.target.core.enums.b bVar) {
        if (this.engine != null) {
            this.engine.a(bVar);
        }
    }

    public void setTrackingLocationEnabled(boolean z) {
        if (this.isInitialized) {
            this.ad.a(z);
        } else {
            Tracer.d("Unable to set tracking location on MyTargetVideoView, must call init() first");
        }
    }

    public boolean getTrackingLocationEnabled() {
        if (this.isInitialized) {
            return this.ad.a();
        }
        return false;
    }

    public void handleClick() {
        if (this.engine != null) {
            this.engine.h();
        }
    }

    public void startPreroll() {
        start(com.my.target.core.enums.b.PREROLL);
    }

    public void startMidroll() {
        start(com.my.target.core.enums.b.MIDROLL);
    }

    public void startPauseroll() {
        start(com.my.target.core.enums.b.PAUSEROLL);
    }

    public void startPostroll() {
        start(com.my.target.core.enums.b.POSTROLL);
    }
}
