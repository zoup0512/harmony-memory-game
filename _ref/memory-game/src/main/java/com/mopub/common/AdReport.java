package com.mopub.common;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import com.facebook.appevents.AppEventsConstants;
import com.mopub.network.AdResponse;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AdReport implements Serializable {
    private static final String DATE_FORMAT_PATTERN = "M/d/yy hh:mm:ss a z";
    private static final long serialVersionUID = 1;
    private final AdResponse mAdResponse;
    private final String mAdUnitId;
    private final Locale mDeviceLocale;
    private final String mDeviceModel;
    private final String mSdkVersion;
    private final String mUdid;

    public AdReport(@NonNull String str, @NonNull ClientMetadata clientMetadata, @NonNull AdResponse adResponse) {
        this.mAdUnitId = str;
        this.mSdkVersion = clientMetadata.getSdkVersion();
        this.mDeviceModel = clientMetadata.getDeviceModel();
        this.mDeviceLocale = clientMetadata.getDeviceLocale();
        this.mUdid = clientMetadata.getDeviceId();
        this.mAdResponse = adResponse;
    }

    public String toString() {
        String str;
        StringBuilder stringBuilder = new StringBuilder();
        appendKeyValue(stringBuilder, "sdk_version", this.mSdkVersion);
        appendKeyValue(stringBuilder, "creative_id", this.mAdResponse.getDspCreativeId());
        appendKeyValue(stringBuilder, "platform_version", Integer.toString(VERSION.SDK_INT));
        appendKeyValue(stringBuilder, "device_model", this.mDeviceModel);
        appendKeyValue(stringBuilder, "ad_unit_id", this.mAdUnitId);
        String str2 = "device_locale";
        if (this.mDeviceLocale == null) {
            str = null;
        } else {
            str = this.mDeviceLocale.toString();
        }
        appendKeyValue(stringBuilder, str2, str);
        appendKeyValue(stringBuilder, "device_id", this.mUdid);
        appendKeyValue(stringBuilder, "network_type", this.mAdResponse.getNetworkType());
        appendKeyValue(stringBuilder, "platform", AbstractSpiCall.ANDROID_CLIENT_TYPE);
        appendKeyValue(stringBuilder, "timestamp", getFormattedTimeStamp(this.mAdResponse.getTimestamp()));
        appendKeyValue(stringBuilder, "ad_type", this.mAdResponse.getAdType());
        Object width = this.mAdResponse.getWidth();
        Integer height = this.mAdResponse.getHeight();
        String str3 = "ad_size";
        StringBuilder append = new StringBuilder().append("{");
        if (width == null) {
            width = AppEventsConstants.EVENT_PARAM_VALUE_NO;
        }
        append = append.append(width).append(", ");
        if (height == null) {
            width = AppEventsConstants.EVENT_PARAM_VALUE_NO;
        } else {
            Integer num = height;
        }
        appendKeyValue(stringBuilder, str3, append.append(width).append("}").toString());
        return stringBuilder.toString();
    }

    public String getResponseString() {
        return this.mAdResponse.getStringBody();
    }

    public AdResponse getAdResponse() {
        return this.mAdResponse;
    }

    public String getDspCreativeId() {
        return this.mAdResponse.getDspCreativeId();
    }

    private void appendKeyValue(StringBuilder stringBuilder, String str, String str2) {
        stringBuilder.append(str);
        stringBuilder.append(" : ");
        stringBuilder.append(str2);
        stringBuilder.append("\n");
    }

    private String getFormattedTimeStamp(long j) {
        if (j != -1) {
            return new SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.US).format(new Date(j));
        }
        return null;
    }
}
