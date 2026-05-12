package com.appodeal.ads.utils;

import com.appodeal.ads.Native;
import com.appodeal.ads.ah;
import com.appodeal.ads.ak;
import com.appodeal.ads.g;
import com.appodeal.ads.n;
import com.appodeal.ads.t.a;
import org.json.JSONObject;

public class v implements a {
    public void a(int i) {
    }

    public void a(JSONObject jSONObject, int i, String str) {
        if (jSONObject.optBoolean("refresh")) {
            n.g = 0;
            ah.g = 0;
            ak.g = 0;
            g.f = 0;
            com.appodeal.ads.v.f = 0;
            Native.f = 0;
        }
    }
}
