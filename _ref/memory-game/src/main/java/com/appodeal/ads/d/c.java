package com.appodeal.ads.d;

import android.support.annotation.NonNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class c implements Serializable {
    private JSONObject a;
    private String b;
    private List<j> c;
    private String d;
    private String e;
    private String f;
    private Integer g;
    private String h;
    private List<String> i;
    private JSONObject j;
    private e k;
    private boolean l = false;

    public c(@NonNull String str, String str2, @NonNull e eVar) {
        int i = 0;
        this.a = new JSONObject(str);
        this.k = eVar;
        this.b = this.a.getString("id");
        this.d = this.a.optString("bidid");
        this.e = this.a.optString("cur");
        this.f = this.a.optString("customdata");
        this.g = Integer.valueOf(this.a.optInt("nbr"));
        this.h = this.a.optString("bundle");
        JSONArray optJSONArray = this.a.optJSONArray("cat");
        if (optJSONArray != null) {
            this.i = new ArrayList();
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                this.i.add(optJSONArray.getString(i2));
            }
        }
        this.j = this.a.optJSONObject("ext");
        JSONArray jSONArray = this.a.getJSONArray("seatbid");
        this.c = new ArrayList();
        while (i < jSONArray.length()) {
            this.c.add(new j(jSONArray.getJSONObject(i), str2, eVar, this));
            i++;
        }
    }

    public String a() {
        return this.b;
    }

    public List<j> b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public String d() {
        return this.e;
    }

    public JSONObject e() {
        return this.a;
    }

    public e f() {
        return this.k;
    }

    public void a(boolean z) {
        this.l = z;
    }

    public boolean g() {
        return this.l;
    }
}
