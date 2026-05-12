package com.appodeal.ads.native_ad;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.Nullable;
import android.view.View;
import com.applovin.sdk.AppLovinEventParameters;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.Native;
import com.appodeal.ads.ab;
import com.appodeal.ads.ac;
import com.appodeal.ads.ae;
import com.appodeal.ads.af;
import com.appodeal.ads.ag;
import com.appodeal.ads.an;
import com.appodeal.ads.networks.r;
import com.cmcm.adsdk.Const;
import com.mopub.common.FullAdType;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class l extends af {
    private static ac b;

    private static class a extends ab {
        final String g;
        final String h;
        final String i;
        final String j;
        final String k;
        final ArrayList<String> l;
        final ArrayList<String> m;

        public a(String str, String str2, String str3, String str4, String str5, String str6, ArrayList<String> arrayList, ArrayList<String> arrayList2, String str7, int i, ac acVar) {
            super(i, acVar, str4, str5);
            this.g = str;
            this.h = str2;
            this.i = str3;
            this.j = str6;
            this.l = arrayList;
            this.m = arrayList2;
            this.k = str7;
        }

        protected void a(View view) {
            an.a(view.getContext(), this.j);
        }

        protected void b(View view) {
            if (this.l != null) {
                Iterator it = this.l.iterator();
                while (it.hasNext()) {
                    String str = (String) it.next();
                    if (!(this.k == null || this.k.isEmpty() || !a(view.getContext(), this.k))) {
                        str = str + "&installed=1";
                    }
                    an.b(str);
                }
            }
        }

        public String getTitle() {
            return this.g;
        }

        public String getAdProvider() {
            return l.b.a();
        }

        public String getDescription() {
            return this.h;
        }

        public String getCallToAction() {
            return this.i;
        }

        private boolean a(Context context, String str) {
            try {
                context.getPackageManager().getPackageInfo(str, 4096);
                return true;
            } catch (NameNotFoundException e) {
                return false;
            }
        }

        public String d() {
            return this.j;
        }

        @Nullable
        protected String h() {
            if (!this.m.isEmpty()) {
                Iterator it = this.m.iterator();
                while (it.hasNext()) {
                    String str = (String) it.next();
                    if (!str.isEmpty()) {
                        return str;
                    }
                }
            }
            return null;
        }

        public boolean k() {
            return j() != null;
        }

        public boolean containsVideo() {
            return (h() == null || h().isEmpty()) ? false : true;
        }
    }

    private class b implements com.appodeal.ads.networks.r.a {
        final /* synthetic */ l a;

        private b(l lVar) {
            this.a = lVar;
        }

        public void a(int i, int i2) {
            ae.a(i, i2, l.b);
        }

        public void a(String str, int i, int i2) {
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("ads");
                if (jSONArray.length() == 0) {
                    ae.a(i, i2, l.b);
                }
                for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                    int i4;
                    JSONObject jSONObject = jSONArray.getJSONObject(i3);
                    String str2 = null;
                    JSONObject optJSONObject = jSONObject.optJSONObject("app_details");
                    if (optJSONObject != null) {
                        str2 = optJSONObject.optString(AppLovinEventParameters.IN_APP_PURCHASE_TRANSACTION_IDENTIFIER);
                    }
                    ArrayList arrayList = new ArrayList();
                    JSONArray optJSONArray = jSONObject.optJSONArray("beacons");
                    if (optJSONArray != null) {
                        for (i4 = 0; i4 < optJSONArray.length(); i4++) {
                            JSONObject jSONObject2 = optJSONArray.getJSONObject(i4);
                            if (jSONObject2.optString("type", "").equals("impression")) {
                                arrayList.add(jSONObject2.getString("url"));
                            }
                        }
                    }
                    ArrayList arrayList2 = new ArrayList();
                    optJSONArray = jSONObject.optJSONArray(FullAdType.VAST);
                    if (optJSONArray != null) {
                        for (i4 = 0; i4 < optJSONArray.length(); i4++) {
                            String optString = optJSONArray.getJSONObject(i4).optString(Const.KEY_JUHE);
                            if (optString != null) {
                                arrayList2.add(optString);
                            }
                        }
                    }
                    this.a.a.add(new a(jSONObject.getString("title"), jSONObject.optString("description", ""), jSONObject.getString("cta_text"), jSONObject.getString("banner_url"), jSONObject.getString("icon_url"), jSONObject.getString("click_url"), arrayList, arrayList2, str2, i, l.b));
                }
                this.a.a(i, i2, l.b, this.a.a.size());
            } catch (JSONException e) {
                Appodeal.a(e.toString());
                ae.a(i, i2, l.b);
            }
        }
    }

    public static ac getInstance(String str, String[] strArr) {
        if (b == null) {
            af afVar = null;
            if (an.a(strArr)) {
                afVar = new l();
            }
            b = new ac(str, afVar);
        }
        return b;
    }

    public void a(Activity activity, int i, int i2, int i3) {
        String string = ((ag) Native.l.get(i)).m.getString("app_key");
        this.a = new ArrayList(i3);
        r rVar = new r(activity, new b(), i, i2, string, i3);
    }
}
