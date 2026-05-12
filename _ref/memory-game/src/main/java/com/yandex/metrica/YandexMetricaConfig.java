package com.yandex.metrica;

import android.location.Location;
import com.yandex.metrica.impl.bg;
import java.util.HashMap;
import java.util.Map;

public class YandexMetricaConfig {
    private final String a;
    private final String b;
    private final Integer c;
    private final Boolean d;
    private final Boolean e;
    private final Location f;
    private final Boolean g;
    private final Boolean h;
    private final Boolean i;
    private final PreloadInfo j;
    private final Map<String, String> k;

    public static class Builder {
        private final String a;
        private String b;
        private Integer c;
        private Boolean d;
        private Boolean e;
        private Location f;
        private Boolean g;
        private Boolean h;
        private Boolean i;
        private PreloadInfo j;
        private Map<String, String> k = new HashMap();

        protected Builder(String apiKey) {
            bg.b(apiKey);
            this.a = apiKey;
        }

        public Builder setAppVersion(String appVersion) {
            bg.a(appVersion, "App Version");
            this.b = appVersion;
            return this;
        }

        public Builder setSessionTimeout(int sessionTimeout) {
            this.c = Integer.valueOf(sessionTimeout);
            return this;
        }

        public Builder setReportCrashesEnabled(boolean reportCrashesEnabled) {
            this.d = Boolean.valueOf(reportCrashesEnabled);
            return this;
        }

        public Builder setReportNativeCrashesEnabled(boolean reportNativeCrashesEnabled) {
            this.e = Boolean.valueOf(reportNativeCrashesEnabled);
            return this;
        }

        public Builder setLogEnabled() {
            this.i = Boolean.valueOf(true);
            return this;
        }

        public Builder setLocation(Location location) {
            this.f = location;
            return this;
        }

        public Builder setTrackLocationEnabled(boolean trackLocationEnabled) {
            this.g = Boolean.valueOf(trackLocationEnabled);
            return this;
        }

        public Builder setCollectInstalledApps(boolean collectInstalledApps) {
            this.h = Boolean.valueOf(collectInstalledApps);
            return this;
        }

        public Builder setPreloadInfo(PreloadInfo preloadInfo) {
            this.j = preloadInfo;
            return this;
        }

        public Builder putErrorEnvironmentValue(String key, String value) {
            this.k.put(key, value);
            return this;
        }

        public YandexMetricaConfig build() {
            return new YandexMetricaConfig(this);
        }
    }

    public static Builder newConfigBuilder(String apiKey) {
        return new Builder(apiKey);
    }

    protected YandexMetricaConfig(Builder builder) {
        this.a = builder.a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
        this.e = builder.e;
        this.f = builder.f;
        this.g = builder.g;
        this.h = builder.h;
        this.i = builder.i;
        this.j = builder.j;
        this.k = builder.k;
    }

    protected YandexMetricaConfig(YandexMetricaConfig source) {
        this.a = source.a;
        this.b = source.b;
        this.c = source.c;
        this.d = source.d;
        this.e = source.e;
        this.f = source.f;
        this.g = source.g;
        this.h = source.h;
        this.i = source.i;
        this.j = source.j;
        this.k = source.k;
    }

    public String getApiKey() {
        return this.a;
    }

    public String getAppVersion() {
        return this.b;
    }

    public Integer getSessionTimeout() {
        return this.c;
    }

    public Boolean isReportCrashEnabled() {
        return this.d;
    }

    public Boolean isReportNativeCrashEnabled() {
        return this.e;
    }

    public Location getLocation() {
        return this.f;
    }

    public Boolean isTrackLocationEnabled() {
        return this.g;
    }

    public Boolean isLogEnabled() {
        return this.i;
    }

    public Boolean isCollectInstalledApps() {
        return this.h;
    }

    public PreloadInfo getPreloadInfo() {
        return this.j;
    }

    public Map<String, String> getErrorEnvironment() {
        return this.k;
    }
}
