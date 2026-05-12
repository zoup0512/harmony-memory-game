package com.appodeal.ads.native_ad;

import android.app.Activity;
import android.view.View;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.Native;
import com.appodeal.ads.ab;
import com.appodeal.ads.ac;
import com.appodeal.ads.ae;
import com.appodeal.ads.af;
import com.appodeal.ads.ag;
import com.appodeal.ads.an;
import com.appodeal.ads.networks.e;
import com.mopub.mobileads.BaseVideoPlayerActivity;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class b extends af {
    private static ac b;

    private class a implements com.appodeal.ads.networks.e.a {
        final /* synthetic */ b a;

        private a(b bVar) {
            this.a = bVar;
        }

        public void a(int i, int i2) {
            ae.a(i, i2, b.b);
        }

        public void a(JSONArray jSONArray, int i, int i2, int i3) {
            if (jSONArray != null) {
                try {
                    if (jSONArray.length() > 0) {
                        this.a.a = new ArrayList(i3);
                        for (int i4 = 0; i4 < jSONArray.length(); i4++) {
                            JSONObject jSONObject = jSONArray.getJSONObject(i4);
                            this.a.a.add(new b(jSONObject.getString("title"), jSONObject.optString("description"), jSONObject.getString("cta"), (float) jSONObject.optDouble("star_rating", 0.0d), jSONObject.getString("image_url"), jSONObject.getString("icon_url"), jSONObject.getString("click_url"), jSONObject.getString("simp_url"), jSONObject.optString(BaseVideoPlayerActivity.VIDEO_URL), i, b.b));
                        }
                        this.a.a(i, i2, b.b, i3);
                        return;
                    }
                } catch (Throwable e) {
                    ae.a(i, i2, b.b);
                    Appodeal.a(e);
                    return;
                }
            }
            ae.a(i, i2, b.b);
        }
    }

    private static class b extends ab {
        final String g;
        final String h;
        final String i;
        final float j;
        final String k;
        final String l;
        final String m;

        public b(String str, String str2, String str3, float f, String str4, String str5, String str6, String str7, String str8, int i, ac acVar) {
            super(i, acVar, str4, str5);
            this.g = str;
            this.h = str2;
            this.i = str3;
            this.j = f;
            this.k = str6;
            this.l = str7;
            this.m = str8;
        }

        protected void a(View view) {
            an.a(view.getContext(), this.k);
        }

        protected void b(View view) {
            if (this.l != null) {
                an.b(this.l);
            }
        }

        public String getAdProvider() {
            return b.b.a();
        }

        public String getTitle() {
            return this.g;
        }

        public String getDescription() {
            return this.h;
        }

        public String getCallToAction() {
            return this.i;
        }

        public float getRating() {
            return this.j;
        }

        public String d() {
            return this.k;
        }

        protected String g() {
            return this.m;
        }

        public boolean k() {
            return j() != null;
        }

        public boolean containsVideo() {
            return (g() == null || g().isEmpty()) ? false : true;
        }
    }

    public static ac getInstance(String str, String[] strArr) {
        if (b == null) {
            af afVar = null;
            if (an.a(strArr)) {
                afVar = new b();
            }
            b = new ac(str, afVar);
        }
        return b;
    }

    public void a(Activity activity, int i, int i2, int i3) {
        Activity activity2 = activity;
        e eVar = new e(activity2, new a(), i, i2, ((ag) Native.l.get(i)).m.getString("url"), i3);
    }
}
