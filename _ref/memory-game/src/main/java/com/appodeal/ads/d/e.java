package com.appodeal.ads.d;

import android.util.Pair;
import com.appodeal.ads.Appodeal;
import com.mopub.common.FullAdType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class e implements Serializable {
    private String a;
    private String b;
    private String c;
    private Float d;
    private boolean e = true;
    private boolean f = true;
    private boolean g = false;
    private Boolean h;
    private boolean i;
    private String j;
    private List<Pair<Integer, Integer>> k = new ArrayList();
    private int l;
    private boolean m;

    public e(JSONObject jSONObject) {
        this.a = jSONObject.optString("id");
        this.b = jSONObject.optString("title");
        this.c = jSONObject.optString("url");
        this.m = jSONObject.optBoolean("rtb_watch", true);
        if (jSONObject.has(FullAdType.VAST) || jSONObject.has("vpaid")) {
            this.e = jSONObject.optBoolean(FullAdType.VAST);
            this.f = jSONObject.optBoolean("vpaid");
            this.g = true;
        }
        this.d = jSONObject.has("rate") ? Float.valueOf((float) jSONObject.optDouble("rate")) : null;
        this.h = Boolean.valueOf(jSONObject.optBoolean("video_wo_banners"));
        this.i = jSONObject.optBoolean("video_auto_close", true);
        this.j = jSONObject.optString("type");
        if (jSONObject.has("sizes")) {
            try {
                JSONArray optJSONArray = jSONObject.optJSONArray("sizes");
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    this.k.add(new Pair(Integer.valueOf(jSONObject2.getInt("w")), Integer.valueOf(jSONObject2.getInt("h"))));
                }
            } catch (JSONException e) {
                Appodeal.a(e.getMessage());
            }
        }
        this.l = jSONObject.optInt("icon_size");
    }

    public Float a() {
        if (this.d == null) {
            return Float.valueOf(2.0f);
        }
        return this.d;
    }

    public boolean b() {
        return this.c != null;
    }

    public String c() {
        return this.a;
    }

    public String d() {
        return this.b;
    }

    public String e() {
        return this.c;
    }

    public boolean f() {
        return this.e;
    }

    public boolean g() {
        return this.f;
    }

    public boolean h() {
        return this.g;
    }

    public Boolean i() {
        return this.h;
    }

    public boolean j() {
        return this.i;
    }

    public List<Pair<Integer, Integer>> k() {
        return this.k;
    }

    public boolean l() {
        return this.m;
    }

    public String m() {
        return this.j;
    }

    public int n() {
        return this.l;
    }
}
