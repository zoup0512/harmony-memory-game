package com.appodeal.ads.native_ad;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
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
import com.appodeal.ads.u;
import com.facebook.ads.Ad;
import com.facebook.ads.AdChoicesView;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class g extends af {
    private static ac b;

    private static class a extends ab {
        private final NativeAd g;

        public a(NativeAd nativeAd, int i, ac acVar, String str, String str2) {
            super(i, acVar, str, str2);
            this.g = nativeAd;
        }

        protected void a(View view) {
        }

        protected void b(View view) {
        }

        public String getTitle() {
            return this.g.getAdTitle();
        }

        public String getCallToAction() {
            return this.g.getAdCallToAction();
        }

        public String getAdProvider() {
            return g.b.a();
        }

        public float getRating() {
            if (this.g.getAdStarRating() == null || this.g.getAdStarRating().getValue() == 0.0d) {
                return super.getRating();
            }
            return (float) this.g.getAdStarRating().getValue();
        }

        public String getDescription() {
            return this.g.getAdBody();
        }

        public void registerViewForInteraction(View view) {
            super.registerViewForInteraction(view);
            this.g.registerViewForInteraction(view);
        }

        public void unregisterViewForInteraction() {
            super.unregisterViewForInteraction();
            this.g.unregisterView();
        }

        public View getProviderView(Context context) {
            AdChoicesView adChoicesView = new AdChoicesView(context, this.g, true);
            View relativeLayout = new RelativeLayout(context);
            relativeLayout.addView(adChoicesView, new LayoutParams(Math.round(an.i(context) * CloseButton.TEXT_SIZE_SP), Math.round(an.i(context) * CloseButton.TEXT_SIZE_SP)));
            return relativeLayout;
        }

        public void setAppodealMediaView(AppodealMediaView appodealMediaView) {
            if (Native.A != NativeAdType.NoVideo) {
                appodealMediaView.removeAllViews();
                MediaView mediaView = new MediaView(appodealMediaView.getContext());
                ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
                layoutParams.addRule(13, -1);
                appodealMediaView.addView(mediaView, layoutParams);
                mediaView.setNativeAd(this.g);
                mediaView.setAutoplay(Native.B);
                return;
            }
            View uVar = new u(appodealMediaView.getContext());
            layoutParams = new LayoutParams(-1, -1);
            layoutParams.addRule(13, -1);
            appodealMediaView.addView(uVar, layoutParams);
            uVar.setNativeAd(this);
        }

        public boolean k() {
            try {
                Field declaredField = this.g.getClass().getDeclaredField("m");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(this.g);
                Field declaredField2 = obj.getClass().getDeclaredField("A");
                declaredField2.setAccessible(true);
                String str = (String) declaredField2.get(obj);
                return (str == null || str.isEmpty()) ? false : true;
            } catch (Throwable e) {
                Appodeal.a(e);
                return false;
            } catch (Throwable e2) {
                Appodeal.a(e2);
                return false;
            }
        }

        public boolean containsVideo() {
            return k();
        }

        public int m() {
            return this.g.hashCode();
        }
    }

    public static ac getInstance(String str, String[] strArr) {
        if (b == null) {
            af afVar = null;
            if (an.a(strArr)) {
                afVar = new g();
            }
            b = new ac(str, afVar);
        }
        return b;
    }

    public void a(Activity activity, final int i, final int i2, final int i3) {
        if (VERSION.SDK_INT < 11) {
            ae.a(i, i2, b);
            return;
        }
        NativeAd nativeAd = new NativeAd(activity, ((ag) Native.l.get(i)).m.getString("facebook_key"));
        this.a = new ArrayList(i3);
        nativeAd.setAdListener(new AdListener(this) {
            final /* synthetic */ g d;

            public void onError(Ad ad, AdError adError) {
                ae.a(i, i2, g.b);
            }

            public void onAdLoaded(Ad ad) {
                try {
                    this.d.a.add(this.d.a((NativeAd) ad, i, g.b));
                    this.d.a(i, i2, g.b, i3);
                } catch (Exception e) {
                    ae.a(i, i2, g.b);
                }
            }

            public void onAdClicked(Ad ad) {
                ae.c(i, g.b, this.d.a((NativeAd) ad, i, g.b));
            }
        });
        nativeAd.loadAd();
    }

    private a a(NativeAd nativeAd, int i, ac acVar) {
        String str;
        String str2;
        if (nativeAd.getAdIcon() == null || nativeAd.getAdIcon().getUrl() == null || !URLUtil.isValidUrl(nativeAd.getAdIcon().getUrl())) {
            str = null;
        } else {
            str = nativeAd.getAdIcon().getUrl();
        }
        if (nativeAd.getAdCoverImage() == null || nativeAd.getAdCoverImage().getUrl() == null || !URLUtil.isValidUrl(nativeAd.getAdCoverImage().getUrl())) {
            str2 = null;
        } else {
            str2 = nativeAd.getAdCoverImage().getUrl();
        }
        return new a(nativeAd, i, acVar, str2, str);
    }
}
