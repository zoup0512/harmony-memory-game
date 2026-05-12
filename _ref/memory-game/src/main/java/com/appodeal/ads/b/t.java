package com.appodeal.ads.b;

import android.webkit.WebView;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;
import com.appodeal.ads.as;
import com.appodeal.ads.o;
import com.appodeal.ads.q;
import com.appodeal.ads.t.a;
import com.appodeal.ads.utils.n;
import com.my.target.ads.MyTargetVideoView;
import org.json.JSONArray;
import org.json.JSONObject;
import org.nexage.sourcekit.mraid.MRAIDInterstitial;
import org.nexage.sourcekit.mraid.MRAIDInterstitialListener;
import org.nexage.sourcekit.mraid.MRAIDNativeFeatureListener;

class t implements MRAIDInterstitialListener, MRAIDNativeFeatureListener {
    private final o a;
    private final int b;
    private final int c;
    private final String d;
    private final long e;

    t(o oVar, int i, int i2) {
        this(oVar, i, i2, null, 0);
    }

    t(o oVar, int i, int i2, String str, long j) {
        this.a = oVar;
        this.b = i;
        this.c = i2;
        this.d = str;
        this.e = j;
    }

    public void mraidInterstitialLoaded(MRAIDInterstitial mRAIDInterstitial) {
        q.a(this.b, this.c, this.a);
    }

    public void mraidInterstitialShow(MRAIDInterstitial mRAIDInterstitial) {
    }

    public void mraidInterstitialHide(MRAIDInterstitial mRAIDInterstitial) {
        q.c(this.b, this.a);
        if (this.a.g().c() != null) {
            this.a.g().c().finish();
            this.a.g().c().overridePendingTransition(0, 0);
        }
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
        if (str.equals("appodeal://")) {
            q.a(this.b, this.a, new a(this) {
                final /* synthetic */ t a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    this.a.a.g().c().a.setVisibility(8);
                    this.a.a.g().c().finish();
                    this.a.a.g().c().overridePendingTransition(0, 0);
                }

                public void a(JSONObject jSONObject, int i, String str) {
                    this.a.a.g().c().b.setVisibility(8);
                    try {
                        if (jSONObject.getString("status").equals(MyTargetVideoView.COMPLETE_STATUS_OK)) {
                            JSONArray jSONArray = new JSONArray();
                            if (jSONObject.has("urls")) {
                                jSONArray = jSONObject.getJSONArray("urls");
                            }
                            if (jSONObject.has("url")) {
                                jSONArray.put(jSONObject.getString("url"));
                            }
                            an.a(this.a.a.g().c(), jSONArray);
                        }
                    } catch (Throwable e) {
                        Appodeal.a(e);
                    }
                    this.a.a.g().c().a.setVisibility(8);
                    this.a.a.g().c().finish();
                    this.a.a.g().c().overridePendingTransition(0, 0);
                }
            });
            as.b(this.a.g().c());
            if (webView != null) {
                webView.setVisibility(8);
                return;
            }
            return;
        }
        q.b(this.b, this.a);
        an.a(Appodeal.b, str);
        if (this.a.g().c() != null) {
            this.a.g().c().finish();
            this.a.g().c().overridePendingTransition(0, 0);
        }
    }

    public void mraidNativeFeatureStorePicture(String str) {
    }

    public void mraidNativeFeatureSendSms(String str) {
    }

    public void mraidInterstitialNoFill(MRAIDInterstitial mRAIDInterstitial) {
        q.b(this.b, this.c, this.a);
    }
}
