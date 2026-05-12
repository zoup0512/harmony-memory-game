package com.appodeal.ads.d;

import android.support.annotation.NonNull;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.ShareConstants;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a implements Serializable {
    private String a;
    private e b;
    private String c;
    private String d;
    private Double e;
    private String f;
    private String g;
    private String h;
    private String[] i;
    private String j;
    private String k;
    private String l;
    private Integer[] m;
    private String n;
    private Integer o;
    private Integer p;
    private JSONObject q;
    private Double r;
    private j s;

    public a(JSONObject jSONObject, String str, @NonNull e eVar, j jVar) {
        int i = 0;
        this.s = jVar;
        this.a = str;
        this.c = jSONObject.getString("id");
        this.d = jSONObject.getString("impid");
        this.e = Double.valueOf(jSONObject.getDouble(Param.PRICE));
        this.f = jSONObject.optString("adid");
        this.h = jSONObject.optString("adm");
        JSONArray optJSONArray = jSONObject.optJSONArray("adomain");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            this.i = new String[optJSONArray.length()];
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                this.i[i2] = optJSONArray.getString(i2);
            }
        }
        this.g = jSONObject.optString("nurl");
        this.j = jSONObject.optString("iurl");
        this.k = jSONObject.optString("cid");
        this.l = jSONObject.optString("crid");
        JSONArray optJSONArray2 = jSONObject.optJSONArray("attr");
        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
            this.m = new Integer[optJSONArray2.length()];
            while (i < optJSONArray2.length()) {
                this.m[i] = Integer.valueOf(optJSONArray2.getInt(i));
                i++;
            }
        }
        this.n = jSONObject.optString("dealid");
        this.o = Integer.valueOf(jSONObject.optInt("w"));
        this.p = Integer.valueOf(jSONObject.optInt("h"));
        this.q = jSONObject.optJSONObject("ext");
        this.b = eVar;
    }

    public JSONObject a() {
        if (this.q == null) {
            this.q = new JSONObject();
        }
        try {
            this.q.put("mfr_id", this.a);
            this.q.put("bidder_id", this.b.c());
            this.q.put("id", this.c);
            this.q.put(Param.PRICE, this.e);
            this.q.put("actual_price", h());
            this.q.put("adm", this.h);
        } catch (JSONException e) {
        }
        return this.q;
    }

    public Double b() {
        return this.e;
    }

    public String c() {
        return b(this.g);
    }

    public void a(String str) {
        this.h = str;
    }

    public void d() {
        this.h = b(this.h);
    }

    private String b(String str) {
        if (str == null) {
            return str;
        }
        if (str.contains("${AUCTION_ID}")) {
            str = str.replace("${AUCTION_ID}", this.s.c().a());
        }
        if (str.contains("%24%7BAUCTION_ID%7D")) {
            str = str.replace("%24%7BAUCTION_ID%7D", this.s.c().a());
        }
        if (str.contains("${AUCTION_BID_ID}")) {
            str = str.replace("${AUCTION_BID_ID}", this.s.c().c());
        }
        if (str.contains("%24%7BAUCTION_BID_ID%7D")) {
            str = str.replace("%24%7BAUCTION_BID_ID%7D", this.s.c().c());
        }
        if (str.contains("${AUCTION_IMP_ID}")) {
            str = str.replace("${AUCTION_IMP_ID}", this.d);
        }
        if (str.contains("%24%7BAUCTION_IMP_ID%7D")) {
            str = str.replace("%24%7BAUCTION_IMP_ID%7D", this.d);
        }
        if (str.contains("${AUCTION_SEAT_ID}") && this.s.b() != null) {
            str = str.replace("${AUCTION_SEAT_ID}", this.s.b());
        }
        if (str.contains("%24%7BAUCTION_SEAT_ID%7D") && this.s.b() != null) {
            str = str.replace("%24%7BAUCTION_SEAT_ID%7D", this.s.b());
        }
        if (str.contains("${AUCTION_AD_ID}") && this.f != null) {
            str = str.replace("${AUCTION_AD_ID}", this.f);
        }
        if (str.contains("%24%7BAUCTION_AD_ID%7D") && this.f != null) {
            str = str.replace("%24%7BAUCTION_AD_ID%7D", this.f);
        }
        if (str.contains("${AUCTION_PRICE}")) {
            str = str.replace("${AUCTION_PRICE}", String.valueOf(h()));
        }
        if (str.contains("%24%7BAUCTION_PRICE%7D")) {
            str = str.replace("%24%7BAUCTION_PRICE%7D", String.valueOf(h()));
        }
        if (str.contains("${AUCTION_CURRENCY}") && this.s.c().d() != null) {
            str = str.replace("${AUCTION_CURRENCY}", this.s.c().d());
        }
        if (!str.contains("%24%7BAUCTION_CURRENCY%7D") || this.s.c().d() == null) {
            return str;
        }
        return str.replace("%24%7BAUCTION_CURRENCY%7D", this.s.c().d());
    }

    public String e() {
        return this.h;
    }

    public String[] f() {
        return this.i;
    }

    public JSONObject g() {
        return this.q;
    }

    public Double h() {
        if (this.r == null) {
            return this.e;
        }
        return this.r;
    }

    public e i() {
        return this.b;
    }

    public void a(Double d) {
        this.r = d;
    }

    public JSONObject j() {
        if (e() == null || e().isEmpty() || e().equals("")) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject(e()).getJSONObject(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
            JSONObject jSONObject3 = jSONObject2.getJSONObject("link");
            String string = jSONObject3.getString("url");
            JSONArray optJSONArray = jSONObject3.optJSONArray("clicktrackers");
            JSONArray optJSONArray2 = jSONObject2.optJSONArray("imptrackers");
            JSONArray jSONArray = jSONObject2.getJSONArray("assets");
            Object obj = "";
            String str = "";
            Object obj2 = "";
            float f = 0.0f;
            String str2 = "Learn more";
            String str3 = "";
            String str4 = "";
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    switch (optJSONObject.getInt("id")) {
                        case 0:
                            str4 = optJSONObject.getJSONObject("title").getString("text");
                            break;
                        case 1:
                            optJSONObject = optJSONObject.getJSONObject("img");
                            if (optJSONObject == null) {
                                break;
                            }
                            str = optJSONObject.getString("url");
                            break;
                        case 2:
                            optJSONObject = optJSONObject.optJSONObject("img");
                            if (optJSONObject == null) {
                                break;
                            }
                            obj2 = optJSONObject.optString("url");
                            break;
                        case 3:
                            optJSONObject = optJSONObject.optJSONObject("img");
                            if (optJSONObject == null) {
                                break;
                            }
                            obj2 = optJSONObject.optString("url");
                            break;
                        case 4:
                            optJSONObject = optJSONObject.optJSONObject(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO);
                            if (optJSONObject == null) {
                                break;
                            }
                            obj = optJSONObject.optString("vasttag");
                            break;
                        case 6:
                            str3 = optJSONObject.getJSONObject(ShareConstants.WEB_DIALOG_PARAM_DATA).optString(Param.VALUE);
                            break;
                        case 7:
                            f = (float) optJSONObject.getJSONObject(ShareConstants.WEB_DIALOG_PARAM_DATA).optDouble(Param.VALUE, 0.0d);
                            break;
                        case 16:
                            str2 = optJSONObject.getJSONObject(ShareConstants.WEB_DIALOG_PARAM_DATA).getString(Param.VALUE);
                            break;
                        default:
                            break;
                    }
                }
            }
            if (str4.isEmpty() || str3.isEmpty() || str2.isEmpty() || string.isEmpty() || str.isEmpty()) {
                return null;
            }
            jSONObject.put("title", str4);
            jSONObject.put("description", str3);
            jSONObject.put(SettingsJsonConstants.APP_ICON_KEY, str);
            jSONObject.put("image", obj2);
            jSONObject.put("rating", (double) f);
            jSONObject.put("cta", str2);
            jSONObject.put("impTrackers", optJSONArray2);
            jSONObject.put("clickTrackers", optJSONArray);
            jSONObject.put("url", string);
            jSONObject.put("videoTag", obj);
            return jSONObject;
        } catch (Exception e) {
            return null;
        }
    }
}
