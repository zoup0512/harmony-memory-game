package com.cmcm.picks.loader;

import android.text.TextUtils;
import com.cmcm.adsdk.CMAdManager;
import com.cmcm.utils.Commons;
import com.cmcm.utils.g;
import com.mopub.mobileads.VastIconXmlManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: MarketResponse */
public class h {
    private List<Ad> a = new ArrayList();
    private int b;
    private int c;

    public int a() {
        return this.b;
    }

    public List<Ad> b() {
        return this.a;
    }

    public void a(Ad ad) {
        if (ad != null) {
            this.a.add(ad);
        }
    }

    public static h a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        try {
            h hVar = new h();
            try {
                JSONObject jSONObject = new JSONObject(str2);
                hVar.b = jSONObject.optInt(VastIconXmlManager.OFFSET, 0);
                hVar.c = jSONObject.optInt("code", -1);
                g.d("pei", "code MarketResponse:" + hVar.c);
                JSONArray optJSONArray = jSONObject.optJSONArray("ads");
                if (optJSONArray == null) {
                    return hVar;
                }
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        hVar.a(a.a(str, optJSONObject));
                    }
                }
                return hVar;
            } catch (Exception e) {
                return hVar;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    public void a(List<? extends Ad> list) {
        if (list != null) {
            this.a.addAll(list);
        }
    }

    public boolean c() {
        g.d("pei", "MarketResponse:" + this.c);
        return this.c == 0;
    }

    public void b(List<Ad> list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Ad ad = (Ad) it.next();
            if (!ad.isDeepLink() && Commons.isHasPackage(CMAdManager.getContext(), ad.getPkg())) {
                it.remove();
            } else if (!ad.isMtTypeAvail()) {
                it.remove();
            }
        }
    }
}
