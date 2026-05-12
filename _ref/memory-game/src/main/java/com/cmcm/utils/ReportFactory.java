package com.cmcm.utils;

import android.text.TextUtils;
import com.amazonaws.services.s3.internal.Constants;
import com.cmcm.adsdk.CMAdManager;
import com.cmcm.picks.loader.Ad;
import com.cmcm.picks.loader.d;
import com.cmcm.picks.loader.e;
import com.cmcm.picks.loader.i;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class ReportFactory {
    public static final String CLICK = "click";
    public static final String CLICK_FAILED = "click_failed";
    public static final String DETAIL_CLICK = "detail_click";
    public static final String DOWN_SUCCESS = "down_success";
    public static final String INSTALL_SUCCESS = "install_success";
    public static final String MPA_CLICK = "mpa_click";
    public static final String MPA_SHOW = "mpa_show";
    public static final String VAST_CLICK = "vast_click";
    public static final String VAST_PLAY = "vast_play";
    public static final String VIEW = "view";

    public static void report(String type, Ad ad, String posid, String rf) {
        report(type, ad, posid, rf, null);
    }

    public static void report(String type, Ad ad, String posid, String rf, Map<String, String> extraReportParams) {
        report(type, ad, posid, rf, (Map) extraReportParams, null, null, 0);
    }

    public static void report(String type, final Ad ad, final String posid, String rf, Map<String, String> extraReportParams, String placementId, String rawJson, int ac) {
        if (ad != null && !TextUtils.isEmpty(posid) && !TextUtils.isEmpty(type)) {
            e parsePublicData = parsePublicData(type, rf, posid, ac);
            if (parsePublicData != null) {
                if ("view".equals(type) || "click".equals(type)) {
                    int loadingPage;
                    if ("click".equals(type)) {
                        loadingPage = getLoadingPage(ad);
                    } else {
                        loadingPage = 0;
                    }
                    parsePublicData.a(loadingPage);
                }
                parsePublicData.a((Map) extraReportParams);
                d toBuinessDataItem = toBuinessDataItem(ad);
                toBuinessDataItem.a(placementId, rawJson);
                c cVar = new c();
                cVar.a(toBuinessDataItem, parsePublicData);
                if (cVar != null) {
                    cVar.execute(new Void[0]);
                }
                if ("view".equals(type)) {
                    reportTracking(ad.getThirdImpUrl());
                    ad.setShowed(true);
                    ThreadHelper.post(new Runnable() {
                        public void run() {
                            i.a().a(posid, ad);
                        }
                    });
                } else if ("click".equals(type)) {
                    reportTracking(ad.getClickTrackingUrl());
                }
            }
        }
    }

    public static void reportForMpa(String type, Ad ad, String posid, String rf, Map<String, String> extraReportParams, int ac) {
        report(type, ad, posid, rf, (Map) extraReportParams, null, null, ac);
    }

    private static void report(String type, Ad ad, String posid, String rf, Map<String, String> extraReportParams, Object o, Object o1, int ac) {
        report(type, ad, posid, rf, (Map) extraReportParams, null, null, ac);
    }

    private static d toBuinessDataItem(Ad ad) {
        return toBuinessDataItem(ad, -1, -1, -1);
    }

    private static d toBuinessDataItem(Ad ad, int duration, int playtime, int event) {
        return new d(ad.getPkg(), ad.getResType(), ad.getDes(), duration, playtime, event);
    }

    private static e parsePublicData(String type, String rf, String posid) {
        return parsePublicData(type, rf, posid, 0);
    }

    private static e parsePublicData(String type, String rf, String posid, int ac) {
        if (TextUtils.isEmpty(rf)) {
            rf = null;
        }
        if ("view".equals(type)) {
            return e.a(posid, 50).a(rf);
        }
        if ("click".equals(type)) {
            return e.a(posid, 60).a(rf);
        }
        if (DETAIL_CLICK.equals(type)) {
            return e.a(posid, 61).a(rf);
        }
        if (INSTALL_SUCCESS.equals(type)) {
            return e.a(posid, 38).a(rf);
        }
        if (DOWN_SUCCESS.equals(type)) {
            return e.a(posid, 36).a(rf);
        }
        if (CLICK_FAILED.equals(type)) {
            return e.a(posid, 62).a(rf);
        }
        if (VAST_PLAY.equals(type)) {
            return e.a(posid, 54).a(rf);
        }
        if (VAST_CLICK.equals(type)) {
            return e.a(posid, 64).a(rf);
        }
        if (MPA_SHOW.equals(type)) {
            return e.a(posid, ac).a(rf);
        }
        if (MPA_CLICK.equals(type)) {
            return e.a(posid, ac).a(rf);
        }
        return null;
    }

    private static int getLoadingPage(Ad ad) {
        if (ad == null || !Commons.isHasPackage(CMAdManager.getContext(), ad.getPkg()) || !ad.isDeepLink()) {
            return 0;
        }
        if (TextUtils.isEmpty(ad.getDeepLink())) {
            return 1;
        }
        return 2;
    }

    public static void reportTracking(String url) {
        if (!TextUtils.isEmpty(url) && !Constants.NULL_VERSION_ID.equals(url)) {
            try {
                JSONArray jSONArray = new JSONArray(url);
                if (jSONArray != null && jSONArray.length() > 0) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        final JSONObject jSONObject = jSONArray.getJSONObject(i);
                        if (jSONObject != null) {
                            ThreadHelper.post(new Runnable() {
                                public void run() {
                                    try {
                                        f.b(null, jSONObject.optString("url", ""), true);
                                    } catch (Exception e) {
                                    }
                                }
                            });
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
    }
}
