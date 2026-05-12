package com.appodeal.ads.native_ad;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.Native;
import com.appodeal.ads.NativeAd;
import com.appodeal.ads.ab;
import com.appodeal.ads.ac;
import com.appodeal.ads.ae;
import com.appodeal.ads.af;
import com.appodeal.ads.ag;
import com.appodeal.ads.an;
import com.appodeal.ads.utils.a.b;
import com.appodeal.ads.utils.n;
import com.cmcm.adsdk.Const;
import com.mopub.mobileads.BaseVideoPlayerActivity;
import com.my.target.ads.MyTargetVideoView;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class c extends af {
    private static ac b;

    private static class a extends ab {
        final String g;
        final String h;
        final String i;
        final Double j;
        final String k;
        final String l;
        final int m;
        final ac n;
        private ProgressDialog o;
        private b p;
        private JSONArray q;
        private String r;
        private long s;

        public a(String str, String str2, String str3, Double d, String str4, String str5, String str6, String str7, b bVar, String str8, long j, int i, ac acVar) {
            super(i, acVar, str4, str5);
            this.g = str;
            this.h = str2;
            this.i = str3;
            this.j = d;
            this.k = str6;
            this.l = str7;
            this.m = i;
            this.n = acVar;
            this.p = bVar;
            this.r = str8;
            this.s = j;
        }

        protected void a(View view) {
            if (!this.r.isEmpty()) {
                n.a(Appodeal.b, this.r, this.s);
            }
            if (!this.k.equals("appodeal://")) {
                ae.c(this.m, this.n, this);
                an.a(view.getContext(), this.k);
            } else if (this.q == null || this.q.length() <= 0) {
                this.o = ProgressDialog.show(view.getContext(), "", "Loading...");
                this.o.setProgressStyle(0);
                this.o.setCancelable(false);
                final Runnable anonymousClass1 = new Runnable(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.o.cancel();
                        this.a.o = null;
                    }
                };
                final Handler handler = new Handler();
                handler.postDelayed(anonymousClass1, 5000);
                ae.a(this.m, this.n, (NativeAd) this, new com.appodeal.ads.t.a(this) {
                    final /* synthetic */ a c;

                    public void a(int i) {
                        try {
                            if (this.c.o != null) {
                                handler.removeCallbacks(anonymousClass1);
                                this.c.o.dismiss();
                            }
                        } catch (Throwable e) {
                            Appodeal.a(e);
                        }
                    }

                    public void a(JSONObject jSONObject, int i, String str) {
                        Object obj = null;
                        try {
                            if (this.c.o != null) {
                                handler.removeCallbacks(anonymousClass1);
                                this.c.o.dismiss();
                                obj = 1;
                            }
                            if (jSONObject.getString("status").equals(MyTargetVideoView.COMPLETE_STATUS_OK)) {
                                this.c.q = new JSONArray();
                                if (jSONObject.has("urls")) {
                                    this.c.q = jSONObject.getJSONArray("urls");
                                }
                                if (jSONObject.has("url")) {
                                    this.c.q.put(jSONObject.getString("url"));
                                }
                                if (obj != null) {
                                    an.a(Appodeal.b, this.c.q);
                                }
                            }
                        } catch (Throwable e) {
                            Appodeal.a(e);
                        }
                    }
                });
            } else {
                try {
                    an.a(Appodeal.b, this.q);
                } catch (Throwable e) {
                    Appodeal.a(e);
                }
            }
        }

        protected void b(View view) {
            if (this.p != null) {
                this.p.b(Appodeal.b);
            }
        }

        public String getAdProvider() {
            return c.b.a();
        }

        public String getTitle() {
            return this.g;
        }

        public String getDescription() {
            return this.h;
        }

        public String getCallToAction() {
            if (this.i == null || this.i.isEmpty()) {
                return super.getCallToAction();
            }
            return this.i;
        }

        public float getRating() {
            if (this.j == null || this.j.isNaN() || this.j.doubleValue() == 0.0d) {
                return super.getRating();
            }
            return this.j.floatValue();
        }

        @Nullable
        protected String g() {
            return this.l;
        }

        public boolean k() {
            return j() != null;
        }

        public boolean containsVideo() {
            return (g() == null || g().isEmpty()) ? false : true;
        }

        public String d() {
            return this.k;
        }
    }

    public static ac getInstance(String str, String[] strArr) {
        if (b == null) {
            af afVar = null;
            if (an.a(strArr)) {
                afVar = new c();
            }
            b = new ac(str, afVar);
        }
        return b;
    }

    public void a(Activity activity, int i, int i2, int i3) {
        b bVar;
        JSONObject optJSONObject = ((ag) Native.l.get(i)).m.optJSONObject("freq");
        String optString = ((ag) Native.l.get(i)).m.optString("package");
        Long valueOf = Long.valueOf(((ag) Native.l.get(i)).m.optLong("expiry"));
        if (optJSONObject != null) {
            bVar = new b(activity, optJSONObject);
            if (!bVar.a((Context) activity)) {
                ae.a(i, i2, b);
                return;
            }
        }
        bVar = null;
        JSONObject jSONObject = ((ag) Native.l.get(i)).m.getJSONObject(Const.KEY_JUHE);
        this.a = new ArrayList();
        String optString2 = jSONObject.optString("image");
        if (optString2 == null || optString2.isEmpty()) {
            optString2 = null;
        }
        String optString3 = jSONObject.optString(SettingsJsonConstants.APP_ICON_KEY);
        if (optString3 == null || optString3.isEmpty()) {
            optString3 = null;
        }
        this.a.add(new a(jSONObject.getString("title"), jSONObject.getString("description"), jSONObject.optString("button"), Double.valueOf(jSONObject.optDouble("rating")), optString2, optString3, jSONObject.getString("click_url"), jSONObject.optString(BaseVideoPlayerActivity.VIDEO_URL), bVar, optString, valueOf.longValue(), i, b));
        a(i, i2, b, i3);
    }

    public boolean b() {
        return true;
    }
}
