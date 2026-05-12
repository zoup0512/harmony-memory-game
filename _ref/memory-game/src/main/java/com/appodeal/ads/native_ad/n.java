package com.appodeal.ads.native_ad;

import android.app.Activity;
import android.util.Pair;
import android.view.View;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.Native;
import com.appodeal.ads.Native.NativeAdType;
import com.appodeal.ads.ab;
import com.appodeal.ads.ac;
import com.appodeal.ads.ae;
import com.appodeal.ads.af;
import com.appodeal.ads.ag;
import com.appodeal.ads.an;
import com.appodeal.ads.networks.u;
import com.appodeal.ads.networks.v;
import com.facebook.GraphResponse;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public class n extends af {
    private static ac b;
    private int c;

    private class a implements com.appodeal.ads.networks.v.a {
        final /* synthetic */ n a;

        private a(n nVar) {
            this.a = nVar;
        }

        public void a(int i, int i2) {
            ae.a(i, i2, n.b);
        }

        public void a(Pair<String, String> pair, int i, int i2) {
            try {
                if (u.a == null && pair.second != null) {
                    u.a = (String) pair.second;
                }
                JSONObject jSONObject = new JSONObject((String) pair.first);
                if (jSONObject.getString("status").equalsIgnoreCase(GraphResponse.SUCCESS_KEY)) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("SNAST");
                    String string = jSONObject2.getJSONArray("mainimage").getJSONObject(0).getString("url");
                    String string2 = jSONObject2.getJSONArray("iconimage").getJSONObject(0).getString("url");
                    JSONArray jSONArray = jSONObject.getJSONArray("beacons");
                    ArrayList arrayList = new ArrayList();
                    for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                        arrayList.add(jSONArray.getString(i3));
                    }
                    b bVar = new b(jSONObject2.getString("adtitle"), jSONObject2.getString("adtext"), jSONObject2.getString("ctatext"), (float) jSONObject2.getDouble("starrating"), string, string2, jSONObject2.getString("clickurl"), arrayList, i, n.b);
                    this.a.a = new ArrayList(this.a.c);
                    this.a.a.add(bVar);
                    this.a.a(i, i2, n.b, this.a.c);
                    return;
                }
                ae.a(i, i2, n.b);
            } catch (Throwable e) {
                Appodeal.a(e);
                ae.a(i, i2, n.b);
            }
        }
    }

    private static class b extends ab {
        final String g;
        final String h;
        final String i;
        final float j;
        final String k;
        final ArrayList<String> l;

        public b(String str, String str2, String str3, float f, String str4, String str5, String str6, ArrayList<String> arrayList, int i, ac acVar) {
            super(i, acVar, str4, str5);
            this.g = str;
            this.h = str2;
            this.i = str3;
            this.j = f;
            this.k = str6;
            this.l = arrayList;
        }

        protected void a(View view) {
            an.a(view.getContext(), this.k);
        }

        protected void b(View view) {
            if (this.l != null) {
                Iterator it = this.l.iterator();
                while (it.hasNext()) {
                    an.b((String) it.next());
                }
            }
        }

        public String getAdProvider() {
            return n.b.a();
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
    }

    public static ac getInstance(String str, String[] strArr) {
        if (b == null) {
            af afVar = null;
            if (an.a(strArr)) {
                afVar = new n();
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
        this.c = i3;
        Activity activity2 = activity;
        int i4 = i;
        int i5 = i2;
        v vVar = new v(activity2, new a(), i4, i5, ((ag) Native.l.get(i)).m.getString("url"), u.a);
    }
}
