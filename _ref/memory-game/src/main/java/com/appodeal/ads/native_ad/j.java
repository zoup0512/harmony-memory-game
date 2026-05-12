package com.appodeal.ads.native_ad;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
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
import com.appodeal.ads.networks.n;
import com.appodeal.ads.u;
import com.my.target.core.facades.b;
import com.my.target.nativeads.NativeAd;
import com.my.target.nativeads.NativeAd.NativeAdListener;
import com.my.target.nativeads.banners.NativePromoBanner;
import com.my.target.nativeads.factories.NativeViewsFactory;
import com.my.target.nativeads.views.ContentStreamAdView;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;

public class j extends af {
    private static ac b;

    private static class a extends ab {
        private final NativeAd g;

        public a(NativeAd nativeAd, int i, ac acVar) {
            super(i, acVar, ((NativePromoBanner) nativeAd.getBanner()).getImage().getUrl(), ((NativePromoBanner) nativeAd.getBanner()).getIcon().getUrl());
            this.g = nativeAd;
        }

        protected void a(View view) {
            this.g.handleClick();
        }

        protected void b(View view) {
            this.g.handleShow();
        }

        public String getTitle() {
            return ((NativePromoBanner) this.g.getBanner()).getTitle();
        }

        public String getCallToAction() {
            return ((NativePromoBanner) this.g.getBanner()).getCtaText();
        }

        public String getAdProvider() {
            return j.b.a();
        }

        public float getRating() {
            if (this.g.getBanner() == null || ((NativePromoBanner) this.g.getBanner()).getRating() == 0.0f) {
                return super.getRating();
            }
            return ((NativePromoBanner) this.g.getBanner()).getRating();
        }

        public String getDescription() {
            return ((NativePromoBanner) this.g.getBanner()).getDescription();
        }

        public void registerViewForInteraction(View view) {
            super.registerViewForInteraction(view);
            this.g.registerView(view);
        }

        public void unregisterViewForInteraction() {
            super.unregisterViewForInteraction();
            this.g.unregisterView();
        }

        @Nullable
        public String getAgeRestrictions() {
            if (this.g != null) {
                return ((NativePromoBanner) this.g.getBanner()).getAgeRestrictions();
            }
            return null;
        }

        public void setAppodealMediaView(AppodealMediaView appodealMediaView) {
            if (Native.A == NativeAdType.NoVideo || !k()) {
                View uVar = new u(appodealMediaView.getContext());
                LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams.addRule(13, -1);
                appodealMediaView.addView(uVar, layoutParams);
                uVar.setNativeAd(this);
                return;
            }
            appodealMediaView.removeAllViews();
            ContentStreamAdView contentStreamView = NativeViewsFactory.getContentStreamView(this.g, Appodeal.b);
            View mediaAdView = contentStreamView.getMediaAdView();
            contentStreamView.removeView(mediaAdView);
            LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams2.addRule(13, -1);
            appodealMediaView.addView(mediaAdView, layoutParams2);
        }

        public boolean k() {
            try {
                Field declaredField = ((NativePromoBanner) this.g.getBanner()).getClass().getDeclaredField("B");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(this.g.getBanner());
                if (obj != null) {
                    Field declaredField2 = obj.getClass().getDeclaredField("u");
                    declaredField2.setAccessible(true);
                    Iterator it = ((ArrayList) declaredField2.get(obj)).iterator();
                    while (it.hasNext()) {
                        obj = it.next();
                        Field declaredField3 = obj.getClass().getSuperclass().getDeclaredField("url");
                        declaredField3.setAccessible(true);
                        String str = (String) declaredField3.get(obj);
                        if (str != null && !str.isEmpty()) {
                            return true;
                        }
                    }
                }
            } catch (Throwable e) {
                Appodeal.a(e);
            }
            return false;
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
                afVar = new j();
            }
            b = new ac(str, afVar);
        }
        return b;
    }

    public void a(Activity activity, final int i, final int i2, final int i3) {
        NativeAd nativeAd = new NativeAd(((ag) Native.l.get(i)).m.getInt("mailru_slot_id"), activity, n.a((Context) activity));
        this.a = new ArrayList(i3);
        nativeAd.setListener(new NativeAdListener(this) {
            final /* synthetic */ j d;

            public /* synthetic */ void onClick(b bVar) {
                b((NativeAd) bVar);
            }

            public /* synthetic */ void onLoad(b bVar) {
                a((NativeAd) bVar);
            }

            public /* synthetic */ void onNoAd(String str, b bVar) {
                a(str, (NativeAd) bVar);
            }

            public void a(NativeAd nativeAd) {
                try {
                    this.d.a.add(new a(nativeAd, i, j.b));
                    this.d.a(i, i2, j.b, i3);
                } catch (Exception e) {
                    ae.a(i, i2, j.b);
                }
            }

            public void a(String str, NativeAd nativeAd) {
                if (this.d.a.size() == 0) {
                    ae.a(i, i2, j.b);
                } else {
                    this.d.a(i, i2, j.b, i3);
                }
            }

            public void b(NativeAd nativeAd) {
                ae.c(i, j.b, new a(nativeAd, i, j.b));
            }
        });
        nativeAd.load();
    }
}
