package com.appodeal.ads.native_ad;

import android.app.Activity;
import android.view.View;
import com.appodeal.ads.Native;
import com.appodeal.ads.Native.NativeAdType;
import com.appodeal.ads.ab;
import com.appodeal.ads.ac;
import com.appodeal.ads.ae;
import com.appodeal.ads.af;
import com.appodeal.ads.ag;
import com.appodeal.ads.an;
import com.appodeal.ads.networks.i;
import com.cmcm.adsdk.nativead.NativeAdManager;
import com.cmcm.baseapi.ads.INativeAd;
import com.cmcm.baseapi.ads.INativeAd.IAdOnClickListener;
import com.cmcm.baseapi.ads.INativeAdLoaderListener;
import java.util.ArrayList;

public class e extends af {
    private static ac b;

    private static class a extends ab {
        private final INativeAd g;

        a(final int i, final ac acVar, INativeAd iNativeAd) {
            super(i, acVar, iNativeAd.getAdCoverImageUrl(), iNativeAd.getAdIconUrl());
            this.g = iNativeAd;
            this.g.setAdOnClickListener(new IAdOnClickListener(this) {
                final /* synthetic */ a c;

                public void onAdClick(INativeAd iNativeAd) {
                    ae.c(i, acVar, this.c);
                }
            });
        }

        protected void a(View view) {
            this.g.handleClick();
        }

        protected void b(View view) {
        }

        public String getTitle() {
            return this.g.getAdTitle();
        }

        public String getAdProvider() {
            return e.b.a();
        }

        public String getDescription() {
            return this.g.getAdBody();
        }

        public float getRating() {
            if (this.g.getAdStarRating() != 0.0d) {
                return (float) this.g.getAdStarRating();
            }
            return super.getRating();
        }

        public String getCallToAction() {
            return this.g.getAdCallToAction();
        }

        public void registerViewForInteraction(View view) {
            super.registerViewForInteraction(view);
            this.g.registerViewForInteraction(view);
        }

        public void unregisterViewForInteraction() {
            super.unregisterViewForInteraction();
            this.g.unregisterView();
        }
    }

    public static ac getInstance(String str, String[] strArr) {
        if (b == null) {
            af afVar = null;
            if (an.a(strArr)) {
                afVar = new e();
            }
            b = new ac(str, afVar).c();
        }
        return b;
    }

    public void a(Activity activity, int i, int i2, int i3) {
        if (Native.A == NativeAdType.Video) {
            ae.a(i, i2, b);
            return;
        }
        String string = ((ag) Native.l.get(i)).m.getString("appId");
        String string2 = ((ag) Native.l.get(i)).m.getString("posId");
        i.a(activity, string, ((ag) Native.l.get(i)).m.getString("channelId"));
        this.a = new ArrayList(i3);
        final NativeAdManager nativeAdManager = new NativeAdManager(activity, string2);
        final int i4 = i;
        final int i5 = i2;
        final int i6 = i3;
        nativeAdManager.setNativeAdListener(new INativeAdLoaderListener(this) {
            final /* synthetic */ e e;

            public void adLoaded() {
                this.e.a.add(new a(i4, e.b, nativeAdManager.getAd()));
                this.e.a(i4, i5, e.b, i6);
            }

            public void adFailedToLoad(int i) {
                ae.a(i4, i5, e.b);
            }

            public void adClicked(INativeAd iNativeAd) {
            }
        });
        nativeAdManager.loadAd();
    }
}
