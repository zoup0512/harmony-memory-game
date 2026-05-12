package com.cmcm.picks.market;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.cmcm.adsdk.Const;
import com.cmcm.baseapi.ads.INativeAd;
import com.cmcm.picks.PicksLoadingActivity;
import com.cmcm.picks.loader.Ad;
import com.cmcm.picks.webview.PicksBrowser;
import com.cmcm.utils.Commons;
import com.cmcm.utils.ReportFactory;
import com.cmcm.utils.a;
import com.cmcm.utils.g;
import com.cmcm.utils.h;
import com.cmcm.utils.k;
import com.cmcm.utils.k.b;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketUtils {
    private static a mAesUtils;
    static HashMap<String, String> sAdTraceMap = new HashMap();

    public static boolean isGooglePlayUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        if (url.startsWith("https://play.google.com") || url.startsWith("http://play.google.com") || url.startsWith("market:")) {
            return true;
        }
        return false;
    }

    public static boolean openUriByBrowser(Context context, String uri) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(uri));
        List queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65536);
        if (queryIntentActivities == null || queryIntentActivities.size() <= 0) {
            return false;
        }
        Commons.startActivity(context, intent);
        return true;
    }

    private static String getPreloadTraceUrl(String originUrl) {
        return (String) sAdTraceMap.get(originUrl);
    }

    public static void reportExtra(String type, String pkg, String posid, int reportRes, Map<String, String> reportParams, String placementID, INativeAd nativeAd, String rawStr) {
        Ad ad;
        if (Const.KEY_CM.equals(nativeAd.getAdTypeName())) {
            ad = (Ad) nativeAd.getAdObject();
        } else {
            ad = null;
        }
        if (ad == null) {
            ad = com.cmcm.picks.loader.a.a(pkg, reportRes);
        }
        ReportFactory.report(type, ad, posid, null, (Map) reportParams, placementID, encryptRawJson(rawStr), 0);
    }

    private static String encryptRawJson(String rawJson) {
        String str = "";
        if (!TextUtils.isEmpty(rawJson)) {
            try {
                if (mAesUtils == null) {
                    mAesUtils = new a();
                }
                str = URLEncoder.encode(mAesUtils.a(rawJson), "utf-8");
            } catch (Exception e) {
                if (g.a) {
                    e.printStackTrace();
                }
            }
        }
        return str;
    }

    public static void openOrDownloadAdNoDialog(Context context1, String posId, Ad ad, String rf, Map<String, String> extraReportParams) {
        openOrDownloadAdNoReport(context1, posId, ad, rf, extraReportParams);
        ReportFactory.report("click", ad, posId, rf, extraReportParams);
    }

    public static void openOrDownloadAdNoReport(Context context1, String posId, Ad ad, String rf, Map<String, String> map) {
        if (context1 != null) {
            Context hVar = new h(context1);
            if (ad.isDeepLink() && Commons.isHasPackage(hVar, ad.getPkg())) {
                Commons.openAppByDeeplink(hVar, ad.getPkg(), ad.getDeepLink());
            } else if (Commons.isHasPackage(hVar, ad.getPkg())) {
                Commons.openApp(hVar, ad.getPkg());
            } else if (ad.isOpenBrowser()) {
                openUriByBrowser(hVar, ad.getPkgUrl());
            } else if (ad.isOpenInternal()) {
                PicksBrowser.a(hVar, ad.getPkgUrl());
            } else {
                smartGo2GooglePlayEx(hVar, ad.getPkgUrl(), ad, posId);
            }
        }
    }

    private static boolean smartGo2GooglePlayEx(final Context context, final String originUrl, final Ad ad, final String posId) {
        if (TextUtils.isEmpty(originUrl) || context == null) {
            return false;
        }
        if (isGooglePlayUrl(originUrl)) {
            Commons.openGooglePlayByUrl(originUrl, context);
            return false;
        }
        String str = (String) sAdTraceMap.get(originUrl);
        if (TextUtils.isEmpty(str) || !isGooglePlayUrl(str)) {
            k kVar = new k();
            kVar.a(new b() {
                public void a(String str) {
                    MarketUtils.stopLoadingActivity(context);
                    if (!TextUtils.isEmpty(str)) {
                        if (!MarketUtils.isGooglePlayUrl(str)) {
                            str = "market://details?id=" + ad.getPkg();
                            ReportFactory.report(ReportFactory.CLICK_FAILED, ad, posId, "");
                        }
                        Commons.openGooglePlayByUrl(str, context);
                        MarketUtils.sAdTraceMap.put(originUrl, str);
                    }
                }
            });
            startLoadingActivity(context);
            kVar.a(originUrl);
            return true;
        }
        Commons.openGooglePlayByUrl(str, context);
        return true;
    }

    public static void startLoadingActivity(Context context) {
        PicksLoadingActivity.a(context);
    }

    public static boolean stopLoadingActivity(Context context) {
        PicksLoadingActivity.b(context);
        return PicksLoadingActivity.a;
    }
}
