package com.mopub.common.event;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Preconditions;
import com.mopub.common.util.Json;
import java.util.HashMap;
import java.util.Map;

public class EventDetails {
    private static final String AD_HEIGHT_PX_KEY = "ad_height_px_key";
    private static final String AD_NETWORK_TYPE_KEY = "ad_network_type";
    private static final String AD_TYPE_KEY = "ad_type";
    private static final String AD_UNIT_ID_KEY = "ad_unit_id";
    private static final String AD_WIDTH_PX_KEY = "ad_width_px";
    private static final String DSP_CREATIVE_ID_KEY = "dsp_creative_id";
    private static final String GEO_ACCURACY_KEY = "geo_accuracy_key";
    private static final String GEO_LATITUDE_KEY = "geo_latitude";
    private static final String GEO_LONGITUDE_KEY = "geo_longitude";
    private static final String PERFORMANCE_DURATION_MS_KEY = "performance_duration_ms";
    private static final String REQUEST_ID_KEY = "request_id_key";
    private static final String REQUEST_STATUS_CODE_KEY = "request_status_code";
    private static final String REQUEST_URI_KEY = "request_uri_key";
    @NonNull
    private final Map<String, String> mEventDetailsMap;

    public static class Builder {
        @NonNull
        private final Map<String, String> eventDetailsMap = new HashMap();

        @NonNull
        public Builder adUnitId(@Nullable String str) {
            if (str != null) {
                this.eventDetailsMap.put(EventDetails.AD_UNIT_ID_KEY, str);
            }
            return this;
        }

        @NonNull
        public Builder dspCreativeId(@Nullable String str) {
            if (str != null) {
                this.eventDetailsMap.put(EventDetails.DSP_CREATIVE_ID_KEY, str);
            }
            return this;
        }

        @NonNull
        public Builder adType(@Nullable String str) {
            if (str != null) {
                this.eventDetailsMap.put(EventDetails.AD_TYPE_KEY, str);
            }
            return this;
        }

        @NonNull
        public Builder adNetworkType(@Nullable String str) {
            if (str != null) {
                this.eventDetailsMap.put(EventDetails.AD_NETWORK_TYPE_KEY, str);
            }
            return this;
        }

        @NonNull
        public Builder adWidthPx(@Nullable Integer num) {
            if (num != null) {
                this.eventDetailsMap.put(EventDetails.AD_WIDTH_PX_KEY, String.valueOf(num));
            }
            return this;
        }

        @NonNull
        public Builder adHeightPx(@Nullable Integer num) {
            if (num != null) {
                this.eventDetailsMap.put(EventDetails.AD_HEIGHT_PX_KEY, String.valueOf(num));
            }
            return this;
        }

        @NonNull
        public Builder geoLatitude(@Nullable Double d) {
            if (d != null) {
                this.eventDetailsMap.put(EventDetails.GEO_LATITUDE_KEY, String.valueOf(d));
            }
            return this;
        }

        @NonNull
        public Builder geoLongitude(@Nullable Double d) {
            if (d != null) {
                this.eventDetailsMap.put(EventDetails.GEO_LONGITUDE_KEY, String.valueOf(d));
            }
            return this;
        }

        @NonNull
        public Builder geoAccuracy(@Nullable Float f) {
            if (f != null) {
                this.eventDetailsMap.put(EventDetails.GEO_ACCURACY_KEY, String.valueOf((double) f.floatValue()));
            }
            return this;
        }

        @NonNull
        public Builder performanceDurationMs(@Nullable Long l) {
            if (l != null) {
                this.eventDetailsMap.put(EventDetails.PERFORMANCE_DURATION_MS_KEY, String.valueOf((double) l.longValue()));
            }
            return this;
        }

        @NonNull
        public Builder requestId(@Nullable String str) {
            if (str != null) {
                this.eventDetailsMap.put(EventDetails.REQUEST_ID_KEY, str);
            }
            return this;
        }

        @NonNull
        public Builder requestStatusCode(@Nullable Integer num) {
            if (num != null) {
                this.eventDetailsMap.put(EventDetails.REQUEST_STATUS_CODE_KEY, String.valueOf(num));
            }
            return this;
        }

        @NonNull
        public Builder requestUri(@Nullable String str) {
            if (str != null) {
                this.eventDetailsMap.put(EventDetails.REQUEST_URI_KEY, str);
            }
            return this;
        }

        @NonNull
        public EventDetails build() {
            return new EventDetails(this.eventDetailsMap);
        }
    }

    private EventDetails(@NonNull Map<String, String> map) {
        Preconditions.checkNotNull(map);
        this.mEventDetailsMap = map;
    }

    @Nullable
    public String getAdUnitId() {
        return (String) this.mEventDetailsMap.get(AD_UNIT_ID_KEY);
    }

    @Nullable
    public String getDspCreativeId() {
        return (String) this.mEventDetailsMap.get(DSP_CREATIVE_ID_KEY);
    }

    @Nullable
    public String getAdType() {
        return (String) this.mEventDetailsMap.get(AD_TYPE_KEY);
    }

    @Nullable
    public String getAdNetworkType() {
        return (String) this.mEventDetailsMap.get(AD_NETWORK_TYPE_KEY);
    }

    @Nullable
    public Double getAdWidthPx() {
        return getNullableDoubleValue(this.mEventDetailsMap, AD_WIDTH_PX_KEY);
    }

    @Nullable
    public Double getAdHeightPx() {
        return getNullableDoubleValue(this.mEventDetailsMap, AD_HEIGHT_PX_KEY);
    }

    @Nullable
    public Double getGeoLatitude() {
        return getNullableDoubleValue(this.mEventDetailsMap, GEO_LATITUDE_KEY);
    }

    @Nullable
    public Double getGeoLongitude() {
        return getNullableDoubleValue(this.mEventDetailsMap, GEO_LONGITUDE_KEY);
    }

    @Nullable
    public Double getGeoAccuracy() {
        return getNullableDoubleValue(this.mEventDetailsMap, GEO_ACCURACY_KEY);
    }

    @Nullable
    public Double getPerformanceDurationMs() {
        return getNullableDoubleValue(this.mEventDetailsMap, PERFORMANCE_DURATION_MS_KEY);
    }

    @Nullable
    public String getRequestId() {
        return (String) this.mEventDetailsMap.get(REQUEST_ID_KEY);
    }

    @Nullable
    public Integer getRequestStatusCode() {
        return getNullableIntegerValue(this.mEventDetailsMap, REQUEST_STATUS_CODE_KEY);
    }

    @Nullable
    public String getRequestUri() {
        return (String) this.mEventDetailsMap.get(REQUEST_URI_KEY);
    }

    public String toJsonString() {
        return Json.mapToJsonString(this.mEventDetailsMap);
    }

    public String toString() {
        return toJsonString();
    }

    @Nullable
    private static Double getNullableDoubleValue(@NonNull Map<String, String> map, @NonNull String str) {
        String str2 = (String) map.get(str);
        if (str2 == null) {
            return null;
        }
        try {
            return Double.valueOf(Double.parseDouble(str2));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Nullable
    private static Integer getNullableIntegerValue(@NonNull Map<String, String> map, @NonNull String str) {
        String str2 = (String) map.get(str);
        if (str2 == null) {
            return null;
        }
        try {
            return Integer.valueOf(Integer.parseInt(str2));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
