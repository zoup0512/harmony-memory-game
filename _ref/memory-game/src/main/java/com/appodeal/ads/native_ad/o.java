package com.appodeal.ads.native_ad;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.view.View;
import com.appodeal.ads.Native;
import com.appodeal.ads.Native.NativeAdType;
import com.appodeal.ads.UserSettings.Gender;
import com.appodeal.ads.ab;
import com.appodeal.ads.ac;
import com.appodeal.ads.ae;
import com.appodeal.ads.af;
import com.appodeal.ads.ag;
import com.appodeal.ads.an;
import com.appodeal.ads.utils.r;
import com.startapp.android.publish.Ad;
import com.startapp.android.publish.AdEventListener;
import com.startapp.android.publish.SDKAdPreferences;
import com.startapp.android.publish.StartAppSDK;
import com.startapp.android.publish.nativead.NativeAdDetails;
import com.startapp.android.publish.nativead.NativeAdPreferences;
import com.startapp.android.publish.nativead.StartAppNativeAd;
import java.util.ArrayList;
import java.util.Iterator;

public class o extends af {
    private static ac b;

    private static class a extends ab {
        private final NativeAdDetails g;

        a(NativeAdDetails nativeAdDetails, int i, ac acVar) {
            super(i, acVar, nativeAdDetails.getImageUrl(), nativeAdDetails.getSecondaryImageUrl());
            this.g = nativeAdDetails;
        }

        public String getTitle() {
            return this.g.getTitle();
        }

        public String getAdProvider() {
            return o.b.a();
        }

        public float getRating() {
            if (this.g.getRating() != 0.0f) {
                return this.g.getRating();
            }
            return super.getRating();
        }

        public String getDescription() {
            return this.g.getDescription();
        }

        public String getCallToAction() {
            if (this.g.isApp().booleanValue()) {
                return "Install";
            }
            return "Learn more";
        }

        protected void a(View view) {
            this.g.sendClick(view.getContext());
        }

        protected void b(View view) {
            this.g.sendImpression(view.getContext());
        }
    }

    public static ac getInstance(String str, String[] strArr) {
        if (b == null) {
            af afVar = null;
            if (an.a(strArr)) {
                afVar = new o();
            }
            b = new ac(str, afVar);
        }
        return b;
    }

    public void a(Activity activity, int i, int i2, int i3) {
        if (Native.A == NativeAdType.Video) {
            ae.a(i, i2, b);
            return;
        }
        String string = ((ag) Native.l.get(i)).m.getString("app_id");
        String optString = ((ag) Native.l.get(i)).m.optString("dev_id");
        this.a = new ArrayList(i3);
        if (optString == null || optString.isEmpty()) {
            StartAppSDK.init(activity, string, false);
        } else {
            StartAppSDK.init(activity, optString, string, false);
        }
        NativeAdPreferences nativeAdPreferences = new NativeAdPreferences();
        nativeAdPreferences.setAutoBitmapDownload(false);
        nativeAdPreferences.setAdsNumber(i3);
        nativeAdPreferences.setPrimaryImageSize(4);
        nativeAdPreferences.setSecondaryImageSize(2);
        a(activity, nativeAdPreferences);
        final StartAppNativeAd startAppNativeAd = new StartAppNativeAd(activity);
        final int i4 = i;
        final int i5 = i2;
        final int i6 = i3;
        startAppNativeAd.loadAd(nativeAdPreferences, new AdEventListener(this) {
            final /* synthetic */ o e;

            public void onReceiveAd(Ad ad) {
                ArrayList nativeAds = startAppNativeAd.getNativeAds();
                if (nativeAds == null || nativeAds.size() == 0) {
                    ae.a(i4, i5, o.b);
                    return;
                }
                Iterator it = nativeAds.iterator();
                while (it.hasNext()) {
                    this.e.a.add(new a((NativeAdDetails) it.next(), i4, o.b));
                }
                if (this.e.a.size() == 0) {
                    ae.a(i4, i5, o.b);
                } else {
                    this.e.a(i4, i5, o.b, i6);
                }
            }

            public void onFailedToReceiveAd(Ad ad) {
                ae.a(i4, i5, o.b);
            }
        });
    }

    private void a(Activity activity, NativeAdPreferences nativeAdPreferences) {
        c(activity, nativeAdPreferences);
        d(activity, nativeAdPreferences);
        b(activity, nativeAdPreferences);
    }

    private void b(Activity activity, NativeAdPreferences nativeAdPreferences) {
        Location e = an.e(activity);
        if (e != null) {
            nativeAdPreferences.setLatitude(e.getLatitude());
            nativeAdPreferences.setLongitude(e.getLongitude());
        }
    }

    private void c(Activity activity, NativeAdPreferences nativeAdPreferences) {
        Integer c = r.c(activity);
        if (c != null) {
            nativeAdPreferences.setAge(c);
        }
    }

    private void d(Activity activity, NativeAdPreferences nativeAdPreferences) {
        Gender a = r.a((Context) activity);
        if (a == Gender.FEMALE) {
            nativeAdPreferences.setGender(SDKAdPreferences.Gender.FEMALE);
        } else if (a == Gender.MALE) {
            nativeAdPreferences.setGender(SDKAdPreferences.Gender.MALE);
        }
    }
}
