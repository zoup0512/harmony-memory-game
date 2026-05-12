package com.appodeal.ads.native_ad;

import android.app.Activity;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
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
import com.appodeal.ads.networks.j;
import com.appodeal.ads.u;
import com.appodeal.ads.utils.Log.LogLevel;
import com.facebook.share.internal.ShareConstants;
import com.flurry.android.FlurryAgent;
import com.flurry.android.ads.FlurryAdErrorType;
import com.flurry.android.ads.FlurryAdNative;
import com.flurry.android.ads.FlurryAdNativeAsset;
import com.flurry.android.ads.FlurryAdNativeListener;
import java.util.ArrayList;

public class h extends af {
    private static ac b;

    private class a implements FlurryAdNativeListener {
        final /* synthetic */ h a;
        private final Activity b;
        private final String c;
        private final int d;
        private final int e;
        private final int f;

        a(h hVar, Activity activity, String str, int i, int i2, int i3) {
            this.a = hVar;
            this.b = activity;
            this.c = str;
            this.d = i;
            this.e = i2;
            this.f = i3;
        }

        public void onFetched(FlurryAdNative flurryAdNative) {
            String str = null;
            try {
                String value;
                if (flurryAdNative.getAsset("secHqImage") != null) {
                    value = flurryAdNative.getAsset("secHqImage").getValue();
                } else {
                    value = null;
                }
                if (flurryAdNative.getAsset("secImage") != null) {
                    str = flurryAdNative.getAsset("secImage").getValue();
                } else if (flurryAdNative.getAsset("secOrigImg") != null) {
                    str = flurryAdNative.getAsset("secOrigImg").getValue();
                }
                this.a.a.add(new b(this.d, h.b, flurryAdNative, value, str));
                this.a.a(this.d, this.e, h.b, this.f);
            } catch (Exception e) {
                ae.a(this.d, this.e, h.b);
            }
        }

        public void onShowFullscreen(FlurryAdNative flurryAdNative) {
        }

        public void onCloseFullscreen(FlurryAdNative flurryAdNative) {
        }

        public void onAppExit(FlurryAdNative flurryAdNative) {
        }

        public void onClicked(FlurryAdNative flurryAdNative) {
            ae.c(this.d, h.b, new b(this.d, h.b, flurryAdNative, null, null));
        }

        public void onImpressionLogged(FlurryAdNative flurryAdNative) {
        }

        public void onExpanded(FlurryAdNative flurryAdNative) {
        }

        public void onCollapsed(FlurryAdNative flurryAdNative) {
        }

        public void onError(FlurryAdNative flurryAdNative, FlurryAdErrorType flurryAdErrorType, int i) {
            ae.a(this.d, this.e, h.b);
        }
    }

    private static class b extends ab {
        private final FlurryAdNative g;

        public b(int i, ac acVar, FlurryAdNative flurryAdNative, String str, String str2) {
            super(i, acVar, str, str2);
            this.g = flurryAdNative;
        }

        protected void a(View view) {
        }

        protected void b(View view) {
        }

        public String getTitle() {
            return this.g.getAsset("headline").getValue();
        }

        public String getAdProvider() {
            return h.b.a();
        }

        public String getCallToAction() {
            FlurryAdNativeAsset asset = this.g.getAsset("callToAction");
            if (asset != null) {
                String value = asset.getValue();
                if (!(value == null || value.isEmpty())) {
                    return value;
                }
            }
            return super.getCallToAction();
        }

        public float getRating() {
            FlurryAdNativeAsset asset = this.g.getAsset("appRating");
            if (asset != null) {
                String value = asset.getValue();
                if (!(value == null || value.isEmpty())) {
                    try {
                        return Float.valueOf(value).floatValue();
                    } catch (Exception e) {
                        try {
                            if (value.contains("/")) {
                                String[] split = value.split("/");
                                if (split.length == 2) {
                                    return (((float) Integer.valueOf(split[0]).intValue()) / ((float) Integer.valueOf(split[1]).intValue())) * 5.0f;
                                }
                            }
                        } catch (Exception e2) {
                        }
                    }
                }
            }
            return super.getRating();
        }

        public String getDescription() {
            FlurryAdNativeAsset asset = this.g.getAsset("summary");
            FlurryAdNativeAsset asset2 = this.g.getAsset(ShareConstants.FEED_SOURCE_PARAM);
            if (asset == null || asset.getValue() == null) {
                return null;
            }
            if (asset2 == null || asset2.getValue() == null) {
                return this.g.getAsset("summary").getValue();
            }
            return String.format("%s. Sponsored by %s", new Object[]{this.g.getAsset("summary").getValue(), this.g.getAsset(ShareConstants.FEED_SOURCE_PARAM).getValue()});
        }

        public void registerViewForInteraction(View view) {
            super.registerViewForInteraction(view);
            this.g.setTrackingView(view);
        }

        public void unregisterViewForInteraction() {
            super.unregisterViewForInteraction();
            this.g.removeTrackingView();
        }

        public void setAppodealMediaView(AppodealMediaView appodealMediaView) {
            View uVar;
            LayoutParams layoutParams;
            if (Native.A == NativeAdType.NoVideo) {
                uVar = new u(appodealMediaView.getContext());
                layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams.addRule(13, -1);
                appodealMediaView.addView(uVar, layoutParams);
                uVar.setNativeAd(this);
            } else if (!this.g.isVideoAd() || Native.A == NativeAdType.NoVideo) {
                uVar = new u(appodealMediaView.getContext());
                layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams.addRule(13, -1);
                appodealMediaView.addView(uVar, layoutParams);
                uVar.setNativeAd(this);
            } else {
                this.g.getAsset("videoUrl").loadAssetIntoView(appodealMediaView);
            }
        }

        public boolean k() {
            return this.g.isVideoAd();
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
                afVar = new h();
            }
            b = new ac(str, afVar);
        }
        return b;
    }

    public void a(Activity activity, int i, int i2, int i3) {
        if (VERSION.SDK_INT < 10) {
            ae.a(i, i2, b);
            return;
        }
        String string = ((ag) Native.l.get(i)).m.getString("app_key");
        String string2 = ((ag) Native.l.get(i)).m.getString("placement_key");
        FlurryAgent.init(activity, string);
        FlurryAgent.onStartSession(activity);
        if (Appodeal.getLogLevel() == LogLevel.verbose) {
            FlurryAgent.setLogEnabled(true);
        } else {
            FlurryAgent.setLogEnabled(false);
        }
        this.a = new ArrayList(i3);
        FlurryAdNative flurryAdNative = new FlurryAdNative(activity, string2);
        flurryAdNative.setTargeting(j.a(activity));
        flurryAdNative.setListener(new a(this, activity, string2, i, i2, i3));
        flurryAdNative.fetchAd();
    }

    public boolean b() {
        return true;
    }
}
