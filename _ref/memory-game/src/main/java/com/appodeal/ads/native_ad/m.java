package com.appodeal.ads.native_ad;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import com.appodeal.ads.Native;
import com.appodeal.ads.ab;
import com.appodeal.ads.ac;
import com.appodeal.ads.af;
import com.appodeal.ads.ag;
import com.appodeal.ads.an;
import com.cmcm.adsdk.Const;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public class m extends af {
    private static ac b;

    private static class a extends ab {
        final String g;
        final String h;
        final String i;
        final float j;
        final String k;
        final String l;
        final ArrayList<String> m;
        final ArrayList<String> n;

        public a(String str, String str2, String str3, float f, String str4, String str5, String str6, String str7, ArrayList<String> arrayList, ArrayList<String> arrayList2, int i, ac acVar) {
            super(i, acVar, str4, str5);
            this.g = str;
            this.h = str2;
            this.i = str3;
            this.j = f;
            this.k = str6;
            this.l = str7;
            this.m = arrayList;
            this.n = arrayList2;
        }

        protected void a(View view) {
            an.a(view.getContext(), this.k);
            if (this.m != null) {
                Iterator it = this.m.iterator();
                while (it.hasNext()) {
                    an.b((String) it.next());
                }
            }
        }

        protected void b(View view) {
            if (this.n != null) {
                Iterator it = this.n.iterator();
                while (it.hasNext()) {
                    an.b((String) it.next());
                }
            }
        }

        public String getAdProvider() {
            return m.b.a();
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

        @Nullable
        protected String h() {
            return this.l;
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
                afVar = new m();
            }
            b = new ac(str, afVar);
        }
        return b;
    }

    public void a(Activity activity, int i, int i2, int i3) {
        int i4;
        JSONObject jSONObject = ((ag) Native.l.get(i)).m.getJSONObject(Const.KEY_JUHE);
        this.a = new ArrayList(i3);
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("impTrackers");
        if (optJSONArray != null) {
            for (i4 = 0; i4 < optJSONArray.length(); i4++) {
                arrayList.add(optJSONArray.getString(i4));
            }
        }
        ArrayList arrayList2 = new ArrayList();
        optJSONArray = jSONObject.optJSONArray("clickTrackers");
        if (optJSONArray != null) {
            for (i4 = 0; i4 < optJSONArray.length(); i4++) {
                arrayList2.add(optJSONArray.getString(i4));
            }
        }
        this.a.add(new a(jSONObject.getString("title"), jSONObject.optString("description", ""), jSONObject.getString("cta"), (float) jSONObject.getDouble("rating"), jSONObject.getString("image"), jSONObject.getString(SettingsJsonConstants.APP_ICON_KEY), jSONObject.getString("url"), jSONObject.optString("videoTag"), arrayList2, arrayList, i, b));
        a(i, i2, b, i3);
    }
}
