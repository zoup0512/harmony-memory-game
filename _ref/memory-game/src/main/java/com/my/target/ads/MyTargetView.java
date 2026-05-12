package com.my.target.ads;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.my.target.Tracer;
import com.my.target.core.engines.b;
import com.my.target.core.facades.g;
import com.my.target.core.facades.h;
import com.my.target.core.facades.h.a;
import com.my.target.core.factories.c;

public class MyTargetView extends RelativeLayout {
    private h ad;
    private a adListener = new a() {
        public void onLoad(h hVar) {
            if (hVar == MyTargetView.this.ad) {
                if (MyTargetView.this.engine == null) {
                    MyTargetView.this.engine = c.a(hVar, MyTargetView.this, MyTargetView.this.getContext());
                }
                if (MyTargetView.this.engine != null) {
                    MyTargetView.this.engine.a((g) hVar);
                }
            }
        }

        public void onNoAd(String str, h hVar) {
            if (MyTargetView.this.listener != null) {
                MyTargetView.this.listener.onNoAd(str, MyTargetView.this);
            }
        }
    };
    private b engine;
    private boolean isInitialized;
    private MyTargetViewListener listener;
    private boolean trackingEvironmentEnabled = true;

    public interface MyTargetViewListener {
        void onClick(MyTargetView myTargetView);

        void onLoad(MyTargetView myTargetView);

        void onNoAd(String str, MyTargetView myTargetView);
    }

    public MyTargetViewListener getListener() {
        return this.listener;
    }

    public void setListener(MyTargetViewListener myTargetViewListener) {
        this.listener = myTargetViewListener;
    }

    public void setTrackingEnvironmentEnabled(boolean z) {
        this.trackingEvironmentEnabled = z;
        if (this.ad != null) {
            this.ad.setTrackingEnvironmentEnabled(z);
        }
    }

    public MyTargetView(Context context) {
        super(context);
        Tracer.i("AdView created. Version: 4.5.10");
    }

    public MyTargetView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Tracer.i("AdView created. Version: 4.5.10");
    }

    public MyTargetView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Tracer.i("AdView created. Version: 4.5.10");
    }

    public void init(int i) {
        init(i, null, Boolean.valueOf(true));
    }

    public void init(int i, CustomParams customParams) {
        init(i, customParams, Boolean.valueOf(true));
    }

    public void init(int i, CustomParams customParams, Boolean bool) {
        if (!this.isInitialized) {
            this.ad = new h(i, getContext(), customParams, bool);
            this.ad.setTrackingEnvironmentEnabled(this.trackingEvironmentEnabled);
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

    private boolean checkInit() {
        if (this.isInitialized) {
            return true;
        }
        Tracer.d("AdView not initialized");
        return false;
    }

    public void start() {
        if (this.engine != null) {
            this.engine.d();
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
}
