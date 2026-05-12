package com.appodeal.ads.a;

import android.webkit.WebView;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;
import com.appodeal.ads.as;
import com.appodeal.ads.g;
import com.appodeal.ads.h;
import com.appodeal.ads.j;
import com.appodeal.ads.t.a;
import com.appodeal.ads.utils.n;
import com.my.target.ads.MyTargetVideoView;
import org.json.JSONArray;
import org.json.JSONObject;
import org.nexage.sourcekit.mraid.MRAIDNativeFeatureListener;
import org.nexage.sourcekit.mraid.MRAIDView;
import org.nexage.sourcekit.mraid.MRAIDViewListener;

class q implements MRAIDNativeFeatureListener, MRAIDViewListener {
    private final h a;
    private final int b;
    private final int c;
    private final String d;
    private final long e;
    private String f;

    q(h hVar, int i, int i2) {
        this(hVar, i, i2, null, 0);
    }

    q(h hVar, int i, int i2, String str, long j) {
        this.a = hVar;
        this.b = i;
        this.c = i2;
        this.d = str;
        this.e = j;
    }

    public void mraidViewLoaded(MRAIDView mRAIDView) {
        j.a(this.b, this.c, this.a);
    }

    public void mraidViewExpand(MRAIDView mRAIDView) {
    }

    public void mraidViewClose(MRAIDView mRAIDView) {
    }

    public boolean mraidViewResize(MRAIDView mRAIDView, int i, int i2, int i3, int i4) {
        return false;
    }

    public void mraidNativeFeatureCallTel(String str) {
    }

    public void mraidNativeFeatureCreateCalendarEvent(String str) {
    }

    public void mraidNativeFeaturePlayVideo(String str) {
    }

    public void mraidNativeFeatureOpenBrowser(String str, WebView webView) {
        if (!(this.d == null || this.d.isEmpty())) {
            n.a(Appodeal.b, this.d, this.e);
        }
        if (!str.equals("appodeal://")) {
            j.c(this.b, this.a);
            an.a(Appodeal.b, str);
        } else if (this.f == null || this.f.isEmpty() || this.f.equals("")) {
            j.a(this.b, this.a, new a(this) {
                final /* synthetic */ q a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    as.b(g.p);
                }

                public void a(JSONObject jSONObject, int i, String str) {
                    as.b(g.p);
                    try {
                        if (jSONObject.getString("status").equals(MyTargetVideoView.COMPLETE_STATUS_OK)) {
                            JSONArray jSONArray = new JSONArray();
                            if (jSONObject.has("urls")) {
                                jSONArray = jSONObject.getJSONArray("urls");
                            }
                            if (jSONObject.has("url")) {
                                jSONArray.put(jSONObject.getString("url"));
                            }
                            this.a.f = an.a(Appodeal.b, jSONArray);
                        }
                    } catch (Throwable e) {
                        Appodeal.a(e);
                    }
                }
            });
            as.a(g.p);
        } else {
            an.a(Appodeal.b, this.f);
        }
    }

    public void mraidNativeFeatureStorePicture(String str) {
    }

    public void mraidNativeFeatureSendSms(String str) {
    }

    public void mraidViewNoFill(MRAIDView mRAIDView) {
        j.b(this.b, this.c, this.a);
    }
}
