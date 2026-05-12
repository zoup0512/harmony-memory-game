package com.appodeal.ads.native_ad;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.AppodealMediaView;
import com.appodeal.ads.Native;
import com.appodeal.ads.Native.NativeAdType;
import com.appodeal.ads.ab;
import com.appodeal.ads.ac;
import com.appodeal.ads.ae;
import com.appodeal.ads.af;
import com.appodeal.ads.ag;
import com.appodeal.ads.an;
import com.jirbo.adcolony.AdColonyNativeAdView;
import com.my.target.nativeads.banners.NavigationType;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;

public class a extends af {
    public static com.appodeal.ads.ao.a b = com.appodeal.ads.ao.a.NOT_AVAILABLE;
    public static HashSet<String> c = new HashSet();
    private static ac d;
    private AdColonyNativeAdView e;

    private static class a extends ab {
        private final AdColonyNativeAdView g;

        public a(AdColonyNativeAdView adColonyNativeAdView, int i, ac acVar) {
            super(i, acVar);
            this.g = adColonyNativeAdView;
        }

        protected void a(View view) {
            this.g.performClick();
        }

        protected void b(View view) {
        }

        public Bitmap getIcon() {
            try {
                Drawable drawable = this.g.getAdvertiserImage().getDrawable();
                if (drawable != null && (drawable instanceof BitmapDrawable)) {
                    return ((BitmapDrawable) drawable).getBitmap();
                }
            } catch (Throwable e) {
                Appodeal.a(e);
            }
            return null;
        }

        public Bitmap getImage() {
            try {
                Drawable drawable = this.g.getAdvertiserImage().getDrawable();
                if (drawable != null && (drawable instanceof BitmapDrawable)) {
                    return ((BitmapDrawable) drawable).getBitmap();
                }
            } catch (Throwable e) {
                Appodeal.a(e);
            }
            return null;
        }

        public String getTitle() {
            return this.g.getTitle();
        }

        public String getAdProvider() {
            return a.d.a();
        }

        public String getDescription() {
            if (this.g.getDescription() == null) {
                return null;
            }
            return String.format("%s. Sponsored by %s", new Object[]{this.g.getDescription(), this.g.getAdvertiserName()});
        }

        public String getCallToAction() {
            return this.g.getEngagementLabel();
        }

        public void setAppodealMediaView(AppodealMediaView appodealMediaView) {
            appodealMediaView.removeAllViews();
            ViewParent parent = this.g.getParent();
            if (parent != null && (parent instanceof ViewGroup)) {
                ((ViewGroup) parent).removeView(this.g);
            }
            appodealMediaView.addView(this.g, new LayoutParams(-1, -1));
        }

        public void unregisterViewForInteraction() {
            if (this.b != null) {
                this.b.setOnClickListener(null);
            }
            this.g.destroy();
        }

        public boolean k() {
            try {
                Field declaredField = this.g.getClass().getDeclaredField("f");
                declaredField.setAccessible(true);
                String str = (String) declaredField.get(this.g);
                if (!(str == null || str.isEmpty())) {
                    return true;
                }
            } catch (Throwable e) {
                Appodeal.a(e);
            } catch (Throwable e2) {
                Appodeal.a(e2);
            }
            return false;
        }

        public void l() {
            this.g.destroy();
        }

        public boolean containsVideo() {
            return true;
        }
    }

    public static ac getInstance(String str, String[] strArr) {
        if (d == null) {
            af afVar = null;
            if (an.a(strArr)) {
                afVar = new a();
            }
            d = new ac(str, afVar);
        }
        return d;
    }

    public void a(Activity activity, int i, int i2, int i3) {
        if (VERSION.SDK_INT < 14 || Native.A == NativeAdType.NoVideo) {
            ae.a(i, i2, d);
            return;
        }
        String string = ((ag) Native.l.get(i)).m.getString("zone_id");
        com.appodeal.ads.networks.a.a(activity, ((ag) Native.l.get(i)).m.getString(NavigationType.STORE), ((ag) Native.l.get(i)).m.getString("app_id"), ((ag) Native.l.get(i)).m.optJSONObject("zones"), string);
        Pair f = an.f(activity);
        this.e = new AdColonyNativeAdView(activity, string, (((Integer) f.first).intValue() > ((Integer) f.second).intValue() ? (Integer) f.second : (Integer) f.first).intValue());
        if (this.e.isReady()) {
            a aVar = new a(this.e, i, d);
            if (this.a == null) {
                this.a = new ArrayList();
            }
            this.a.add(aVar);
            a(i, i2, d);
        } else if (b == com.appodeal.ads.ao.a.NOT_AVAILABLE_AFTER_DELAY) {
            ae.a(i, i2, d);
        } else {
            final HandlerThread handlerThread = new HandlerThread("AdcolonyThread");
            handlerThread.start();
            final Handler handler = new Handler(handlerThread.getLooper());
            final int i4 = i;
            final int i5 = i2;
            handler.postDelayed(new Runnable(this) {
                int a = 0;
                final /* synthetic */ a f;

                public void run() {
                    try {
                        if (this.f.e.isReady()) {
                            a aVar = new a(this.f.e, i4, a.d);
                            if (this.f.a == null) {
                                this.f.a = new ArrayList();
                            }
                            this.f.a.add(aVar);
                            this.f.a(i4, i5, a.d);
                            handlerThread.quit();
                        } else if (a.b == com.appodeal.ads.ao.a.NOT_AVAILABLE_AFTER_DELAY) {
                            ae.a(i4, i5, a.d);
                            handlerThread.quit();
                        } else if (this.a < 10) {
                            handler.postDelayed(this, 1000);
                        } else {
                            a.b = com.appodeal.ads.ao.a.NOT_AVAILABLE_AFTER_DELAY;
                            ae.a(i4, i5, a.d);
                            handlerThread.quit();
                        }
                    } catch (Throwable e) {
                        Appodeal.a(e);
                    }
                    this.a++;
                }
            }, 1000);
        }
    }
}
