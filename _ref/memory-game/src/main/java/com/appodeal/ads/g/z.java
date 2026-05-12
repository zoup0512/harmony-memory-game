package com.appodeal.ads.g;

import android.app.Activity;
import android.content.Context;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.aj;
import com.appodeal.ads.an;
import com.appodeal.ads.ap;
import com.appodeal.ads.t.a;
import com.appodeal.ads.utils.n;
import com.my.target.ads.MyTargetVideoView;
import org.json.JSONArray;
import org.json.JSONObject;
import org.nexage.sourcekit.vast.VASTPlayer.VASTPlayerListener;
import org.nexage.sourcekit.vast.activity.VASTActivity;

class z implements VASTPlayerListener {
    private final ap a;
    private final int b;
    private final int c;
    private final String d;
    private final long e;
    private String f;

    z(ap apVar, int i, int i2) {
        this(apVar, i, i2, null, 0);
    }

    z(ap apVar, int i, int i2, String str, long j) {
        this.a = apVar;
        this.b = i;
        this.c = i2;
        this.d = str;
        this.e = j;
    }

    public void vastReady() {
        aj.a(this.b, this.c, this.a);
    }

    public void vastError(int i) {
        aj.b(this.b, this.c, this.a);
    }

    public void vastShown() {
        aj.a(this.b, this.a);
    }

    public void vastClick(String str, final Activity activity) {
        if (!(this.d == null || this.d.isEmpty())) {
            n.a(Appodeal.b, this.d, this.e);
        }
        if (str == null) {
            return;
        }
        if (!str.equals("appodeal://")) {
            aj.c(this.b, this.a);
            an.a((Context) activity, str);
        } else if (this.f == null || this.f.isEmpty() || this.f.equals("")) {
            if (activity instanceof VASTActivity) {
                ((VASTActivity) activity).showProgressBar();
            }
            aj.a(this.b, this.a, new a(this) {
                final /* synthetic */ z b;

                public void a(int i) {
                    if (((activity != null ? 1 : 0) & (activity instanceof VASTActivity)) != 0) {
                        ((VASTActivity) activity).hideProgressBar();
                        ((VASTActivity) activity).restartVideo();
                    }
                }

                public void a(JSONObject jSONObject, int i, String str) {
                    int i2 = 1;
                    if (((activity != null ? 1 : 0) & (activity instanceof VASTActivity)) != 0) {
                        ((VASTActivity) activity).hideProgressBar();
                    }
                    try {
                        if (jSONObject.getString("status").equals(MyTargetVideoView.COMPLETE_STATUS_OK)) {
                            JSONArray jSONArray = new JSONArray();
                            if (jSONObject.has("urls")) {
                                jSONArray = jSONObject.getJSONArray("urls");
                            }
                            if (jSONObject.has("url")) {
                                jSONArray.put(jSONObject.getString("url"));
                            }
                            this.b.f = an.a(activity, jSONArray);
                            return;
                        }
                        if (activity == null) {
                            i2 = 0;
                        }
                        if (((activity instanceof VASTActivity) & i2) != 0) {
                            ((VASTActivity) activity).restartVideo();
                        }
                    } catch (Throwable e) {
                        Appodeal.a(e);
                    }
                }
            });
        } else {
            an.a((Context) activity, this.f);
        }
    }

    public void vastComplete() {
        aj.b(this.b, this.a);
    }

    public void vastDismiss() {
        aj.d(this.b, this.a);
    }
}
