package com.mopub.common;

import android.graphics.Point;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.mopub.network.Networking;
import com.mopub.network.PlayServicesUrlRewriter;

public abstract class BaseUrlGenerator {
    private static final String HEIGHT_KEY = "h";
    private static final String WIDTH_KEY = "w";
    private boolean mFirstParam;
    private StringBuilder mStringBuilder;

    public abstract String generateUrlString(String str);

    protected void initUrlString(String str, String str2) {
        this.mStringBuilder = new StringBuilder(Networking.getScheme()).append("://").append(str).append(str2);
        this.mFirstParam = true;
    }

    protected String getFinalUrlString() {
        return this.mStringBuilder.toString();
    }

    protected void addParam(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            this.mStringBuilder.append(getParamDelimiter());
            this.mStringBuilder.append(str);
            this.mStringBuilder.append("=");
            this.mStringBuilder.append(Uri.encode(str2));
        }
    }

    private String getParamDelimiter() {
        if (!this.mFirstParam) {
            return "&";
        }
        this.mFirstParam = false;
        return "?";
    }

    protected void setApiVersion(String str) {
        addParam("v", str);
    }

    protected void setAppVersion(String str) {
        addParam("av", str);
    }

    protected void setExternalStoragePermission(boolean z) {
        addParam("android_perms_ext_storage", z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
    }

    protected void setDeviceInfo(String... strArr) {
        StringBuilder stringBuilder = new StringBuilder();
        if (strArr != null && strArr.length >= 1) {
            for (int i = 0; i < strArr.length - 1; i++) {
                stringBuilder.append(strArr[i]).append(",");
            }
            stringBuilder.append(strArr[strArr.length - 1]);
            addParam("dn", stringBuilder.toString());
        }
    }

    protected void setDoNotTrack(boolean z) {
        if (z) {
            addParam("dnt", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        }
    }

    protected void setUdid(String str) {
        addParam("udid", str);
    }

    protected void appendAdvertisingInfoTemplates() {
        addParam("udid", PlayServicesUrlRewriter.UDID_TEMPLATE);
        addParam("dnt", PlayServicesUrlRewriter.DO_NOT_TRACK_TEMPLATE);
    }

    protected void setDeviceDimensions(@NonNull Point point) {
        addParam(WIDTH_KEY, "" + point.x);
        addParam(HEIGHT_KEY, "" + point.y);
    }
}
