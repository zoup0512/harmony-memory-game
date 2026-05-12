package com.appodeal.ads.d;

import android.support.annotation.NonNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

class j implements Serializable {
    private List<a> a = new ArrayList();
    private String b;
    private Boolean c;
    private JSONObject d;
    private c e;

    public j(JSONObject jSONObject, String str, @NonNull e eVar, c cVar) {
        boolean z;
        int i = 0;
        this.e = cVar;
        this.b = jSONObject.optString("seat");
        if (!jSONObject.has("group") || jSONObject.getInt("group") == 0) {
            z = false;
        } else {
            z = true;
        }
        this.c = Boolean.valueOf(z);
        this.d = jSONObject.optJSONObject("ext");
        JSONArray jSONArray = jSONObject.getJSONArray("bid");
        while (i < jSONArray.length()) {
            this.a.add(new a(jSONArray.getJSONObject(i), str, eVar, this));
            i++;
        }
    }

    public List<a> a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public c c() {
        return this.e;
    }
}
