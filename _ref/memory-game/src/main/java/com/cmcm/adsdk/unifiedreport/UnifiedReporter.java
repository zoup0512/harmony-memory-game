package com.cmcm.adsdk.unifiedreport;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.cmcm.adsdk.CMAdManager;
import com.cmcm.adsdk.utils.BackgroundHandler;
import com.cmcm.utils.Commons;
import com.cmcm.utils.f;
import com.cmcm.utils.i;
import java.net.URLEncoder;

public class UnifiedReporter {
    private static final int AC_CLICK = 2;
    private static final int AC_SHOW = 1;
    private static UnifiedReporter sSelf;
    private String mConstantParam = "";
    private Context mContext = null;
    private volatile boolean mIsInit = false;
    private String mReportUrl = "";

    public static UnifiedReporter getInstance() {
        if (sSelf == null) {
            synchronized (UnifiedReporter.class) {
                if (sSelf == null) {
                    sSelf = new UnifiedReporter();
                }
            }
        }
        return sSelf;
    }

    public UnifiedReporter() {
        if (!this.mIsInit) {
            this.mContext = CMAdManager.getContext().getApplicationContext();
            this.mReportUrl = getReportUrl();
            this.mConstantParam = getConstantParam();
            this.mIsInit = true;
        }
    }

    public void reportShow(int posid) {
        reportShow(posid, "");
    }

    public synchronized void reportShow(int posid, String extra) {
        report(posid, extra, 1);
    }

    public void reportClick(int posid) {
        reportClick(posid, "");
    }

    public synchronized void reportClick(int posid, String extra) {
        report(posid, extra, 2);
    }

    private void report(int posid, String extra, int type) {
        if (this.mIsInit) {
            final StringBuffer stringBuffer = new StringBuffer(this.mReportUrl);
            stringBuffer.append("ac=" + type).append("&posid=" + posid).append("&" + getVariabletParam()).append("&" + this.mConstantParam);
            if (!TextUtils.isEmpty(extra)) {
                stringBuffer.append("&").append("extra=").append(extra);
            }
            BackgroundHandler.sBackgroudHandler.post(new Runnable() {
                public void run() {
                    if (i.e(UnifiedReporter.this.mContext)) {
                        f.b(null, stringBuffer.toString(), true);
                    }
                }
            });
        }
    }

    private String getReportUrl() {
        return "http://ud.adkmob.com/r/?";
    }

    private String getConstantParam() {
        String str = Build.MODEL;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append("pid=" + CMAdManager.getMid()).append("&intl=2").append("&aid=" + Commons.getAndroidId()).append("&resolution=" + Commons.getResolution(this.mContext)).append("&brand=" + Build.BRAND).append("&model=" + URLEncoder.encode(str, "utf-8")).append("&vercode=" + Commons.getAppVersionCode(this.mContext)).append("&mcc=" + Commons.getMCC(this.mContext)).append("&cn=" + CMAdManager.getChannelId()).append("&os=" + VERSION.RELEASE);
        } catch (Exception e) {
        }
        return stringBuffer.toString();
    }

    private String getVariabletParam() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("cl=" + Commons.getLocale(this.mContext)).append("&nt=" + getNetType());
        return stringBuffer.toString();
    }

    private int getNetType() {
        if (i.c(this.mContext)) {
            return 1;
        }
        if (i.d(this.mContext)) {
            return 2;
        }
        return 0;
    }
}
