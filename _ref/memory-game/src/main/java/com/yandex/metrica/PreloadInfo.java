package com.yandex.metrica;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PreloadInfo {
    private String a;
    private Map<String, String> b;

    public static class Builder {
        private String a;
        private Map<String, String> b;

        private Builder(String trackingId) {
            this.a = trackingId;
            this.b = new HashMap();
        }

        public Builder setAdditionalParams(String key, String value) {
            if (!(key == null || value == null)) {
                this.b.put(key, value);
            }
            return this;
        }

        public PreloadInfo build() {
            return new PreloadInfo();
        }
    }

    private PreloadInfo(Builder builder) {
        this.a = builder.a;
        this.b = Collections.unmodifiableMap(builder.b);
    }

    public static Builder newBuilder(String trackingId) {
        return new Builder(trackingId);
    }

    public String getTrackingId() {
        return this.a;
    }

    public Map<String, String> getAdditionalParams() {
        return this.b;
    }
}
