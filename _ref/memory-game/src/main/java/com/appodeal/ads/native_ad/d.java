package com.appodeal.ads.native_ad;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.view.View;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.Native;
import com.appodeal.ads.UserSettings.Gender;
import com.appodeal.ads.ab;
import com.appodeal.ads.ac;
import com.appodeal.ads.ae;
import com.appodeal.ads.af;
import com.appodeal.ads.ag;
import com.appodeal.ads.an;
import com.appodeal.ads.utils.Log.LogLevel;
import com.appodeal.ads.utils.r;
import com.avocarrot.androidsdk.AdError;
import com.avocarrot.androidsdk.AvocarrotCustom;
import com.avocarrot.androidsdk.AvocarrotCustomListener;
import com.avocarrot.androidsdk.AvocarrotUser;
import com.avocarrot.androidsdk.CustomModel;
import com.avocarrot.androidsdk.ui.AdChoicesView;
import java.util.ArrayList;
import java.util.List;

public class d extends af {
    private static ac b;

    private static class a extends ab {
        private final CustomModel g;
        private final AvocarrotCustom h;
        private AdChoicesView i;

        a(int i, ac acVar, CustomModel customModel, AvocarrotCustom avocarrotCustom) {
            super(i, acVar, customModel.getImageUrl(), customModel.getIconUrl());
            this.g = customModel;
            this.h = avocarrotCustom;
        }

        protected void a(View view) {
            this.h.handleClick(this.g);
        }

        protected void b(View view) {
        }

        public String getTitle() {
            return this.g.getTitle();
        }

        public String getCallToAction() {
            return this.g.getCTAText();
        }

        public String getAdProvider() {
            return d.b.a();
        }

        public float getRating() {
            if (this.g.getRating() == null || this.g.getRating().floatValue() == 0.0f) {
                return super.getRating();
            }
            return this.g.getRating().floatValue();
        }

        public String getDescription() {
            return this.g.getDescription();
        }

        public View getProviderView(Context context) {
            if (this.i == null) {
                this.i = new AdChoicesView(context);
            }
            return this.i;
        }

        public void registerViewForInteraction(View view) {
            super.registerViewForInteraction(view);
            this.h.bindView(this.g, view, (AdChoicesView) getProviderView(view.getContext()));
        }

        @Nullable
        protected String h() {
            return this.g.getVastTag();
        }

        public boolean k() {
            return j() != null;
        }

        public boolean containsVideo() {
            return (h() == null || h().isEmpty()) ? false : true;
        }
    }

    public static ac getInstance(String str, String[] strArr) {
        if (b == null) {
            af afVar = null;
            if (an.a(strArr)) {
                afVar = new d();
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
        this.a = new ArrayList(i3);
        final AvocarrotCustom avocarrotCustom = new AvocarrotCustom(activity, string, string2);
        final int i4 = i;
        final int i5 = i2;
        final int i6 = i3;
        avocarrotCustom.setListener(new AvocarrotCustomListener(this) {
            final /* synthetic */ d e;

            public void onAdLoaded(List<CustomModel> list) {
                super.onAdLoaded(list);
                if (list == null || list.size() == 0) {
                    ae.a(i4, i5, d.b);
                    return;
                }
                for (CustomModel aVar : list) {
                    this.e.a.add(new a(i4, d.b, aVar, avocarrotCustom));
                }
                this.e.a(i4, i5, d.b, i6);
            }

            public void onAdError(AdError adError) {
                super.onAdError(adError);
                ae.a(i4, i5, d.b);
            }
        });
        if (Appodeal.getLogLevel() == LogLevel.verbose) {
            avocarrotCustom.setLogger(Boolean.valueOf(true), "ALL");
        } else {
            avocarrotCustom.setLogger(Boolean.valueOf(false), "INFO");
        }
        a(activity);
        avocarrotCustom.loadAds(i3);
    }

    private void a(Activity activity) {
        b(activity);
        d(activity);
        c(activity);
    }

    private void b(Activity activity) {
        Integer c = r.c(activity);
        if (c != null) {
            AvocarrotUser.setYearOfBirth(c);
        }
    }

    private void c(Activity activity) {
        Gender a = r.a((Context) activity);
        if (a == Gender.FEMALE) {
            AvocarrotUser.setGender(AvocarrotUser.Gender.FEMALE);
        } else if (a == Gender.MALE) {
            AvocarrotUser.setGender(AvocarrotUser.Gender.MALE);
        } else if (a == Gender.OTHER) {
            AvocarrotUser.setGender(AvocarrotUser.Gender.OTHER);
        }
    }

    private void d(Activity activity) {
        Integer b = r.b((Context) activity);
        if (b != null) {
            AvocarrotUser.setAge(b);
        }
    }
}
