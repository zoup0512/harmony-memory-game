package com.mopub.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.event.EventDetails;
import com.mopub.common.util.DateAndTime;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONObject;

public class AdResponse implements Serializable {
    private static final long serialVersionUID = 1;
    @Nullable
    private final Integer mAdTimeoutDelayMillis;
    @Nullable
    private final String mAdType;
    @Nullable
    private final String mAdUnitId;
    @Nullable
    private final String mClickTrackingUrl;
    @Nullable
    private final String mCustomEventClassName;
    @Nullable
    private final String mDspCreativeId;
    @Nullable
    private final EventDetails mEventDetails;
    @Nullable
    private final String mFailoverUrl;
    @Nullable
    private final String mFullAdType;
    @Nullable
    private final Integer mHeight;
    @Nullable
    private final String mImpressionTrackingUrl;
    @Nullable
    private final JSONObject mJsonBody;
    @Nullable
    private final String mNetworkType;
    @Nullable
    private final String mRedirectUrl;
    @Nullable
    private final Integer mRefreshTimeMillis;
    @Nullable
    private final String mRequestId;
    @Nullable
    private final String mResponseBody;
    @Nullable
    private final String mRewardedVideoCompletionUrl;
    @Nullable
    private final String mRewardedVideoCurrencyAmount;
    @Nullable
    private final String mRewardedVideoCurrencyName;
    private final boolean mScrollable;
    @NonNull
    private final Map<String, String> mServerExtras;
    private final long mTimestamp;
    @Nullable
    private final Integer mWidth;

    public static class Builder {
        private Integer adTimeoutDelayMillis;
        private String adType;
        private String adUnitId;
        private String clickTrackingUrl;
        private String customEventClassName;
        private String dspCreativeId;
        private EventDetails eventDetails;
        private String failoverUrl;
        private String fullAdType;
        private Integer height;
        private String impressionTrackingUrl;
        private JSONObject jsonBody;
        private String networkType;
        private String redirectUrl;
        private Integer refreshTimeMillis;
        private String requestId;
        private String responseBody;
        private String rewardedVideoCompletionUrl;
        private String rewardedVideoCurrencyAmount;
        private String rewardedVideoCurrencyName;
        private boolean scrollable = false;
        private Map<String, String> serverExtras = new TreeMap();
        private Integer width;

        public Builder setAdType(@Nullable String str) {
            this.adType = str;
            return this;
        }

        public Builder setAdUnitId(@Nullable String str) {
            this.adUnitId = str;
            return this;
        }

        public Builder setFullAdType(@Nullable String str) {
            this.fullAdType = str;
            return this;
        }

        public Builder setNetworkType(@Nullable String str) {
            this.networkType = str;
            return this;
        }

        public Builder setRewardedVideoCurrencyName(@Nullable String str) {
            this.rewardedVideoCurrencyName = str;
            return this;
        }

        public Builder setRewardedVideoCurrencyAmount(@Nullable String str) {
            this.rewardedVideoCurrencyAmount = str;
            return this;
        }

        public Builder setRewardedVideoCompletionUrl(@Nullable String str) {
            this.rewardedVideoCompletionUrl = str;
            return this;
        }

        public Builder setRedirectUrl(@Nullable String str) {
            this.redirectUrl = str;
            return this;
        }

        public Builder setClickTrackingUrl(@Nullable String str) {
            this.clickTrackingUrl = str;
            return this;
        }

        public Builder setImpressionTrackingUrl(@Nullable String str) {
            this.impressionTrackingUrl = str;
            return this;
        }

        public Builder setFailoverUrl(@Nullable String str) {
            this.failoverUrl = str;
            return this;
        }

        public Builder setRequestId(@Nullable String str) {
            this.requestId = str;
            return this;
        }

        public Builder setDimensions(@Nullable Integer num, @Nullable Integer num2) {
            this.width = num;
            this.height = num2;
            return this;
        }

        public Builder setAdTimeoutDelayMilliseconds(@Nullable Integer num) {
            this.adTimeoutDelayMillis = num;
            return this;
        }

        public Builder setRefreshTimeMilliseconds(@Nullable Integer num) {
            this.refreshTimeMillis = num;
            return this;
        }

        public Builder setScrollable(@Nullable Boolean bool) {
            this.scrollable = bool == null ? this.scrollable : bool.booleanValue();
            return this;
        }

        public Builder setDspCreativeId(@Nullable String str) {
            this.dspCreativeId = str;
            return this;
        }

        public Builder setResponseBody(@Nullable String str) {
            this.responseBody = str;
            return this;
        }

        public Builder setJsonBody(@Nullable JSONObject jSONObject) {
            this.jsonBody = jSONObject;
            return this;
        }

        public Builder setEventDetails(@Nullable EventDetails eventDetails) {
            this.eventDetails = eventDetails;
            return this;
        }

        public Builder setCustomEventClassName(@Nullable String str) {
            this.customEventClassName = str;
            return this;
        }

        public Builder setServerExtras(@Nullable Map<String, String> map) {
            if (map == null) {
                this.serverExtras = new TreeMap();
            } else {
                this.serverExtras = new TreeMap(map);
            }
            return this;
        }

        public AdResponse build() {
            return new AdResponse();
        }
    }

    private AdResponse(@NonNull Builder builder) {
        this.mAdType = builder.adType;
        this.mAdUnitId = builder.adUnitId;
        this.mFullAdType = builder.fullAdType;
        this.mNetworkType = builder.networkType;
        this.mRewardedVideoCurrencyName = builder.rewardedVideoCurrencyName;
        this.mRewardedVideoCurrencyAmount = builder.rewardedVideoCurrencyAmount;
        this.mRewardedVideoCompletionUrl = builder.rewardedVideoCompletionUrl;
        this.mRedirectUrl = builder.redirectUrl;
        this.mClickTrackingUrl = builder.clickTrackingUrl;
        this.mImpressionTrackingUrl = builder.impressionTrackingUrl;
        this.mFailoverUrl = builder.failoverUrl;
        this.mRequestId = builder.requestId;
        this.mWidth = builder.width;
        this.mHeight = builder.height;
        this.mAdTimeoutDelayMillis = builder.adTimeoutDelayMillis;
        this.mRefreshTimeMillis = builder.refreshTimeMillis;
        this.mDspCreativeId = builder.dspCreativeId;
        this.mScrollable = builder.scrollable;
        this.mResponseBody = builder.responseBody;
        this.mJsonBody = builder.jsonBody;
        this.mEventDetails = builder.eventDetails;
        this.mCustomEventClassName = builder.customEventClassName;
        this.mServerExtras = builder.serverExtras;
        this.mTimestamp = DateAndTime.now().getTime();
    }

    public boolean hasJson() {
        return this.mJsonBody != null;
    }

    @Nullable
    public JSONObject getJsonBody() {
        return this.mJsonBody;
    }

    @Nullable
    public EventDetails getEventDetails() {
        return this.mEventDetails;
    }

    @Nullable
    public String getStringBody() {
        return this.mResponseBody;
    }

    @Nullable
    public String getAdType() {
        return this.mAdType;
    }

    @Nullable
    public String getFullAdType() {
        return this.mFullAdType;
    }

    @Nullable
    public String getAdUnitId() {
        return this.mAdUnitId;
    }

    @Nullable
    public String getNetworkType() {
        return this.mNetworkType;
    }

    @Nullable
    public String getRewardedVideoCurrencyName() {
        return this.mRewardedVideoCurrencyName;
    }

    @Nullable
    public String getRewardedVideoCurrencyAmount() {
        return this.mRewardedVideoCurrencyAmount;
    }

    @Nullable
    public String getRewardedVideoCompletionUrl() {
        return this.mRewardedVideoCompletionUrl;
    }

    @Nullable
    public String getRedirectUrl() {
        return this.mRedirectUrl;
    }

    @Nullable
    public String getClickTrackingUrl() {
        return this.mClickTrackingUrl;
    }

    @Nullable
    public String getImpressionTrackingUrl() {
        return this.mImpressionTrackingUrl;
    }

    @Nullable
    public String getFailoverUrl() {
        return this.mFailoverUrl;
    }

    @Nullable
    public String getRequestId() {
        return this.mRequestId;
    }

    public boolean isScrollable() {
        return this.mScrollable;
    }

    @Nullable
    public Integer getWidth() {
        return this.mWidth;
    }

    @Nullable
    public Integer getHeight() {
        return this.mHeight;
    }

    @Nullable
    public Integer getAdTimeoutMillis() {
        return this.mAdTimeoutDelayMillis;
    }

    @Nullable
    public Integer getRefreshTimeMillis() {
        return this.mRefreshTimeMillis;
    }

    @Nullable
    public String getDspCreativeId() {
        return this.mDspCreativeId;
    }

    @Nullable
    public String getCustomEventClassName() {
        return this.mCustomEventClassName;
    }

    @NonNull
    public Map<String, String> getServerExtras() {
        return new TreeMap(this.mServerExtras);
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public Builder toBuilder() {
        return new Builder().setAdType(this.mAdType).setNetworkType(this.mNetworkType).setRedirectUrl(this.mRedirectUrl).setClickTrackingUrl(this.mClickTrackingUrl).setImpressionTrackingUrl(this.mImpressionTrackingUrl).setFailoverUrl(this.mFailoverUrl).setDimensions(this.mWidth, this.mHeight).setAdTimeoutDelayMilliseconds(this.mAdTimeoutDelayMillis).setRefreshTimeMilliseconds(this.mRefreshTimeMillis).setDspCreativeId(this.mDspCreativeId).setScrollable(Boolean.valueOf(this.mScrollable)).setResponseBody(this.mResponseBody).setJsonBody(this.mJsonBody).setEventDetails(this.mEventDetails).setCustomEventClassName(this.mCustomEventClassName).setServerExtras(this.mServerExtras);
    }
}
