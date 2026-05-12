package com.appodeal.ads.d;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class g implements Serializable {
    private String a;
    private String b;
    private List<e> c;
    private JSONObject d;
    private boolean e;
    private boolean f;
    private JSONObject g;
    private h h;

    public g(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                this.a = jSONObject.optString("request_id");
                if (this.a == null) {
                    this.a = "";
                }
                JSONArray jSONArray = jSONObject.getJSONArray("bidders");
                this.c = new ArrayList();
                if (jSONArray != null) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        e eVar = new e(jSONArray.getJSONObject(i));
                        if (eVar.b()) {
                            this.c.add(eVar);
                        }
                    }
                }
                if (this.c.isEmpty()) {
                    this.e = true;
                    return;
                }
                return;
            } catch (JSONException e) {
                this.e = true;
                return;
            }
        }
        this.a = "";
        this.c = new ArrayList();
    }

    public g(g gVar) {
        if (gVar != null) {
            this.a = gVar.a();
            this.b = gVar.b();
            this.c = gVar.c();
            this.d = gVar.d();
            this.e = gVar.e();
            this.f = gVar.f();
            this.g = gVar.g();
        }
        this.h = new h();
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public List<e> c() {
        return this.c;
    }

    public JSONObject d() {
        return this.d;
    }

    public void a(JSONObject jSONObject) {
        this.d = jSONObject;
        if (jSONObject != null) {
            this.b = jSONObject.optString("country_id");
        }
    }

    public void a(boolean z) {
        this.e = z;
    }

    public boolean e() {
        return this.e;
    }

    public void b(boolean z) {
        this.f = z;
    }

    public boolean f() {
        return this.f;
    }

    public JSONObject g() {
        return this.g;
    }

    public void b(JSONObject jSONObject) {
        this.g = jSONObject;
    }

    public h h() {
        return this.h;
    }

    public void a(h hVar) {
        this.h = hVar;
    }
}
