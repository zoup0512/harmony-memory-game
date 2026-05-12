package com.appodeal.ads.native_ad;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.Native;
import com.appodeal.ads.ab;
import com.appodeal.ads.ac;
import com.appodeal.ads.ae;
import com.appodeal.ads.af;
import com.appodeal.ads.ag;
import com.appodeal.ads.an;
import com.appodeal.ads.networks.l;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.ShareConstants;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class i extends af {
    private static ac b;
    private int c;

    private static class a extends ab {
        private HashMap<String, Object> g;

        public a(HashMap<String, Object> hashMap, int i, ac acVar) {
            super(i, acVar);
            this.g = hashMap;
        }

        protected void a(View view) {
            if (this.g.get("track_click") != null && !this.g.get("track_click").toString().isEmpty()) {
                an.a(view.getContext(), this.g.get("track_click").toString());
            }
        }

        protected void b(View view) {
            if (this.g.get("track_imp") != null) {
                JSONArray jSONArray = (JSONArray) this.g.get("track_imp");
                int i = 0;
                while (i < jSONArray.length()) {
                    try {
                        if (!(jSONArray.getString(i) == null || jSONArray.getString(i).isEmpty())) {
                            an.b(jSONArray.getString(i));
                        }
                    } catch (Throwable e) {
                        Appodeal.a(e);
                    }
                    i++;
                }
            }
        }

        public String getTitle() {
            if (this.g.get("title") != null) {
                return this.g.get("title").toString();
            }
            return null;
        }

        public String getAdProvider() {
            return i.b.a();
        }

        public String getDescription() {
            if (this.g.get("description") != null) {
                return this.g.get("description").toString();
            }
            return null;
        }

        public String getCallToAction() {
            if (this.g.get("cta") != null) {
                return this.g.get("cta").toString();
            }
            return super.getCallToAction();
        }

        public float getRating() {
            Object obj = this.g.get("rating");
            if (obj != null) {
                try {
                    return Float.valueOf(obj.toString()).floatValue();
                } catch (Exception e) {
                }
            }
            return super.getRating();
        }

        public String getIconUrl() {
            if (this.g.get(SettingsJsonConstants.APP_ICON_KEY) != null) {
                return this.g.get(SettingsJsonConstants.APP_ICON_KEY).toString();
            }
            return null;
        }

        public String getMainImageUrl() {
            if (this.g.get("image") != null) {
                return this.g.get("image").toString();
            }
            return null;
        }

        @Nullable
        protected String h() {
            if (this.g.get(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO) != null) {
                return this.g.get(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO).toString();
            }
            return null;
        }

        public boolean containsVideo() {
            return (h() == null || h().isEmpty()) ? false : true;
        }
    }

    private class b implements com.appodeal.ads.networks.l.a {
        final /* synthetic */ i a;

        private b(i iVar) {
            this.a = iVar;
        }

        public void a(int i, int i2) {
            ae.a(i, i2, i.b);
        }

        public void a(String str, int i, int i2) {
            try {
                a aVar = new a(a(str), i, i.b);
                this.a.a = new ArrayList(this.a.c);
                this.a.a.add(aVar);
                this.a.a(i, i2, i.b, this.a.c);
            } catch (Throwable e) {
                Appodeal.a(e);
                ae.a(i, i2, i.b);
            }
        }

        private HashMap<String, Object> a(String str) {
            HashMap<String, Object> hashMap = new HashMap();
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE)) {
                    jSONObject = jSONObject.getJSONObject(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
                    JSONArray jSONArray = jSONObject.getJSONArray("assets");
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        if (jSONObject2.optInt("id", 0) != 0) {
                            switch (jSONObject2.optInt("id")) {
                                case 1:
                                    hashMap.put("title", jSONObject2.optJSONObject("title").optString("text"));
                                    break;
                                case 2:
                                    hashMap.put(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO, jSONObject2.optJSONObject(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO).optString("vasttag"));
                                    break;
                                case 4:
                                    hashMap.put(SettingsJsonConstants.APP_ICON_KEY, jSONObject2.optJSONObject("img").optString("url"));
                                    break;
                                case 6:
                                    hashMap.put("image", jSONObject2.optJSONObject("img").optString("url"));
                                    break;
                                case 12:
                                    hashMap.put("description", jSONObject2.optJSONObject(ShareConstants.WEB_DIALOG_PARAM_DATA).optString(Param.VALUE));
                                    break;
                                case 22:
                                    hashMap.put("cta", jSONObject2.optJSONObject(ShareConstants.WEB_DIALOG_PARAM_DATA).optString(Param.VALUE));
                                    break;
                                case 32:
                                    hashMap.put("rating", jSONObject2.optJSONObject(ShareConstants.WEB_DIALOG_PARAM_DATA).optString(Param.VALUE));
                                    break;
                                default:
                                    continue;
                            }
                        }
                    }
                    if (jSONObject.getJSONObject("link") != null) {
                        hashMap.put("track_click", jSONObject.getJSONObject("link").getString("url"));
                    }
                    if (jSONObject.getJSONArray("imptrackers") != null) {
                        hashMap.put("track_imp", jSONObject.getJSONArray("imptrackers"));
                    }
                }
            } catch (Throwable e) {
                Appodeal.a(e);
            }
            return hashMap;
        }
    }

    public static ac getInstance(String str, String[] strArr) {
        if (b == null) {
            af afVar = null;
            if (an.a(strArr)) {
                afVar = new i();
            }
            b = new ac(str, afVar);
        }
        return b;
    }

    public void a(Activity activity, int i, int i2, int i3) {
        this.c = i3;
        Activity activity2 = activity;
        l lVar = new l(activity2, new b(), i, i2, ((ag) Native.l.get(i)).m.getString("url"), Integer.valueOf(((ag) Native.l.get(i)).m.optInt("speed_limit", 100)));
    }
}
