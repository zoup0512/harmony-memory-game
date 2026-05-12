package com.cmcm.adsdk.offerreport;

import android.content.Context;
import android.os.Build;
import com.cmcm.adsdk.CMAdManager;
import com.cmcm.adsdk.unifiedreport.UnifiedNetUtil;
import com.cmcm.adsdk.utils.BackgroundHandler;
import com.cmcm.utils.Commons;
import com.cmcm.utils.g;
import com.cmcm.utils.i;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

public class OfferReport {
    private static final int CLICK_AC = 60;
    private static final int DEFAULT_TIME_OUT = 1000;
    private static final int NET_RETY_TIMES = 3;
    private static final int PLATFORM = 2;
    private static final int PROTOCOL_VERSION = 10;
    private static final int REPORT_AC = 50;
    private static final String offerReportUrl = "https://ms.cmcm.com/c";
    private static OfferReport sSelf;
    private boolean DEG = false;
    private final String TAG = "OfferReport";
    private Context mContext = null;
    private volatile boolean mIsInit = false;

    public static OfferReport getInstance() {
        if (sSelf == null) {
            synchronized (OfferReport.class) {
                if (sSelf == null) {
                    sSelf = new OfferReport();
                }
            }
        }
        return sSelf;
    }

    private OfferReport() {
        if (!this.mIsInit) {
            this.mContext = CMAdManager.getContext().getApplicationContext();
            this.mIsInit = true;
        }
    }

    public void report(String offerStr, int logType, int ac, String posId) {
        final String str = offerStr;
        final int i = logType;
        final int i2 = ac;
        final String str2 = posId;
        BackgroundHandler.sBackgroudHandler.post(new Runnable() {
            public void run() {
                if (i.e(OfferReport.this.mContext)) {
                    UnifiedNetUtil.doPostString(OfferReport.this.getOfferReportUrl(), 1000, 3, OfferReport.this.getConstantParam(str, i, i2, str2));
                }
            }
        });
    }

    private String getOfferReportUrl() {
        return offerReportUrl;
    }

    private String reportOfferShow() {
        return String.valueOf(50);
    }

    private String clickOfferShow() {
        return String.valueOf(60);
    }

    private String getConstantParam(String offerStr, int logType, int ac, String posId) {
        String str = Build.MODEL;
        Object valueOf = String.valueOf(50);
        try {
            URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
        }
        if (ac == 50) {
            valueOf = reportOfferShow();
        } else if (ac == 60) {
            valueOf = clickOfferShow();
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mid", CMAdManager.getMid());
            jSONObject.put("v", 10);
            jSONObject.put("lan", Commons.getLocale(this.mContext));
            jSONObject.put("pos", posId);
            jSONObject.put("pl", 2);
            jSONObject.put("aid", Commons.getAndroidId());
            jSONObject.put("logType", logType);
            jSONObject.put("mcc", Commons.getMCC(this.mContext));
            jSONObject.put("channelid", CMAdManager.getChannelId());
            jSONObject.put("ac", valueOf);
            jSONObject.put("offerInfo", offerStr);
        } catch (JSONException e2) {
            if (g.a) {
                e2.printStackTrace();
            }
        }
        return jSONObject.toString();
    }
}
