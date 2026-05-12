package com.appodeal.ads.native_ad;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import com.appodeal.ads.Native;
import com.appodeal.ads.Native.NativeAdType;
import com.appodeal.ads.ab;
import com.appodeal.ads.ac;
import com.appodeal.ads.ae;
import com.appodeal.ads.af;
import com.appodeal.ads.ag;
import com.appodeal.ads.an;
import com.mopub.nativeads.BaseNativeAd;
import com.mopub.nativeads.MoPubAdRenderer;
import com.mopub.nativeads.MoPubNative;
import com.mopub.nativeads.MoPubNative.MoPubNativeNetworkListener;
import com.mopub.nativeads.NativeAd;
import com.mopub.nativeads.NativeErrorCode;
import com.mopub.nativeads.RequestParameters;
import com.mopub.nativeads.RequestParameters.Builder;
import com.mopub.nativeads.StaticNativeAd;
import java.util.ArrayList;

public class k extends af {
    private static ac b;
    private final MoPubAdRenderer c = new MoPubAdRenderer(this) {
        final /* synthetic */ k a;

        {
            this.a = r1;
        }

        @NonNull
        public View createAdView(@NonNull Activity activity, @Nullable ViewGroup viewGroup) {
            return viewGroup;
        }

        public void renderAdView(@NonNull View view, @NonNull BaseNativeAd baseNativeAd) {
        }

        public boolean supports(@NonNull BaseNativeAd baseNativeAd) {
            return true;
        }
    };

    private static class a extends ab {
        private final NativeAd g;
        private StaticNativeAd h;

        public a(NativeAd nativeAd, int i, ac acVar) {
            super(i, acVar);
            this.g = nativeAd;
        }

        boolean n() {
            BaseNativeAd baseNativeAd = this.g.getBaseNativeAd();
            if (!(baseNativeAd instanceof StaticNativeAd)) {
                return false;
            }
            this.h = (StaticNativeAd) baseNativeAd;
            o();
            return true;
        }

        private void o() {
            this.e = this.h.getMainImageUrl();
            this.f = this.h.getIconImageUrl();
        }

        protected void a(View view) {
            this.h.handleClick(view);
        }

        protected void b(View view) {
            this.h.recordImpression(view);
        }

        public String getTitle() {
            return this.h.getTitle();
        }

        public String getAdProvider() {
            return k.b.a();
        }

        public String getDescription() {
            return this.h.getText();
        }

        public float getRating() {
            Double starRating = this.h.getStarRating();
            if (starRating == null || starRating.doubleValue() == 0.0d) {
                return super.getRating();
            }
            return starRating.floatValue();
        }

        public String getCallToAction() {
            String callToAction = this.h.getCallToAction();
            if (callToAction == null || callToAction.isEmpty()) {
                return super.getCallToAction();
            }
            return callToAction;
        }

        public View getProviderView(final Context context) {
            View b = Native.b(context);
            b.setAdjustViewBounds(true);
            b.setScaleType(ScaleType.FIT_CENTER);
            b.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    an.a(context, this.b.h.getPrivacyInformationIconClickThroughUrl());
                }
            });
            return b;
        }
    }

    public static ac getInstance(String str, String[] strArr) {
        if (b == null) {
            af afVar = null;
            if (an.a(strArr)) {
                afVar = new k();
            }
            b = new ac(str, afVar).c();
        }
        return b;
    }

    public void a(Activity activity, final int i, final int i2, final int i3) {
        if (Native.A == NativeAdType.Video) {
            ae.a(i, i2, b);
            return;
        }
        String string = ((ag) Native.l.get(i)).m.getString("mopub_key");
        this.a = new ArrayList(i3);
        MoPubNative moPubNative = new MoPubNative(activity, string, new MoPubNativeNetworkListener(this) {
            final /* synthetic */ k d;

            public void onNativeLoad(NativeAd nativeAd) {
                a aVar = new a(nativeAd, i, k.b);
                if (aVar.n()) {
                    this.d.a.add(aVar);
                    this.d.a(i, i2, k.b, i3);
                    return;
                }
                ae.a(i, i2, k.b);
            }

            public void onNativeFail(NativeErrorCode nativeErrorCode) {
                ae.a(i, i2, k.b);
            }
        });
        moPubNative.registerAdRenderer(this.c);
        moPubNative.makeRequest(a(activity));
    }

    private RequestParameters a(Activity activity) {
        Builder builder = new Builder();
        Location e = an.e(activity);
        if (e != null) {
            builder.location(e);
        }
        return builder.build();
    }
}
