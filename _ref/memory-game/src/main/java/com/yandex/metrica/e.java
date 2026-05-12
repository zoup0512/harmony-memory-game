package com.yandex.metrica;

import android.location.Location;
import com.yandex.metrica.YandexMetricaConfig.Builder;
import com.yandex.metrica.impl.bg;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class e extends YandexMetricaConfig {
    private final a a;
    private final Map<String, String> b;
    private final String c;
    private final String d;
    private final String e;
    private final Integer f;
    private final Integer g;
    private final Integer h;
    private final Map<String, String> i;
    private final Boolean j;

    public static final class a {
        public String a;
        private Builder b;
        private a c;
        private String d;
        private String e;
        private Integer f;
        private Map<String, String> g;
        private Integer h;
        private Integer i;
        private Map<String, String> j = new HashMap();
        private Boolean k;

        protected a(String str) {
            this.b = YandexMetricaConfig.newConfigBuilder(str);
        }

        public a a(String str) {
            this.b.setAppVersion(str);
            return this;
        }

        public a a(int i) {
            this.b.setSessionTimeout(i);
            return this;
        }

        public a b(String str) {
            this.a = str;
            return this;
        }

        public a a(boolean z) {
            this.b.setReportCrashesEnabled(z);
            return this;
        }

        public a b(boolean z) {
            this.b.setReportNativeCrashesEnabled(z);
            return this;
        }

        public a a() {
            this.b.setLogEnabled();
            return this;
        }

        public a a(Location location) {
            this.b.setLocation(location);
            return this;
        }

        public a c(boolean z) {
            this.b.setTrackLocationEnabled(z);
            return this;
        }

        public a d(boolean z) {
            this.b.setCollectInstalledApps(z);
            return this;
        }

        public a a(String str, String str2) {
            this.b.putErrorEnvironmentValue(str, str2);
            return this;
        }

        public a a(a aVar) {
            this.c = aVar;
            return this;
        }

        public a c(String str) {
            this.d = str;
            return this;
        }

        public a d(String str) {
            bg.a(str, "Custom Host URL");
            this.e = str;
            return this;
        }

        public a b(int i) {
            if (i < 0) {
                throw new IllegalArgumentException(String.format(Locale.US, "Invalid %1$s. %1$s should be positive.", new Object[]{"App Build Number"}));
            }
            this.f = Integer.valueOf(i);
            return this;
        }

        public a a(Map<String, String> map, Boolean bool) {
            this.k = bool;
            this.g = map;
            return this;
        }

        public a c(int i) {
            this.i = Integer.valueOf(i);
            return this;
        }

        public a d(int i) {
            this.h = Integer.valueOf(i);
            return this;
        }

        public a a(PreloadInfo preloadInfo) {
            this.b.setPreloadInfo(preloadInfo);
            return this;
        }

        public a b(String str, String str2) {
            this.j.put(str, str2);
            return this;
        }

        public e b() {
            return new e();
        }
    }

    public e(YandexMetricaConfig yandexMetricaConfig) {
        super(yandexMetricaConfig);
        this.a = null;
        this.b = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.c = null;
        this.i = null;
        this.j = null;
    }

    static e a(YandexMetricaConfig yandexMetricaConfig) {
        if (yandexMetricaConfig instanceof e) {
            return (e) yandexMetricaConfig;
        }
        return new e(yandexMetricaConfig);
    }

    public static a a(String str) {
        return new a(str);
    }

    static a b(YandexMetricaConfig yandexMetricaConfig) {
        Object obj;
        Object obj2 = 1;
        a a = a(yandexMetricaConfig.getApiKey());
        if (yandexMetricaConfig.getAppVersion() != null) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            a.a(yandexMetricaConfig.getAppVersion());
        }
        if (yandexMetricaConfig.getSessionTimeout() != null) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            a.a(yandexMetricaConfig.getSessionTimeout().intValue());
        }
        if (yandexMetricaConfig.isReportCrashEnabled() != null) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            a.a(yandexMetricaConfig.isReportCrashEnabled().booleanValue());
        }
        if (yandexMetricaConfig.isReportNativeCrashEnabled() != null) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            a.b(yandexMetricaConfig.isReportNativeCrashEnabled().booleanValue());
        }
        if (yandexMetricaConfig.getLocation() != null) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            a.a(yandexMetricaConfig.getLocation());
        }
        if (yandexMetricaConfig.isTrackLocationEnabled() != null) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            a.c(yandexMetricaConfig.isTrackLocationEnabled().booleanValue());
        }
        if (yandexMetricaConfig.isCollectInstalledApps() != null) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            a.d(yandexMetricaConfig.isCollectInstalledApps().booleanValue());
        }
        if (yandexMetricaConfig.isLogEnabled() != null) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null && yandexMetricaConfig.isLogEnabled().booleanValue()) {
            a.a();
        }
        if (yandexMetricaConfig.getPreloadInfo() != null) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            a.a(yandexMetricaConfig.getPreloadInfo());
        }
        if (yandexMetricaConfig.getErrorEnvironment() == null) {
            obj2 = null;
        }
        if (obj2 != null) {
            for (Entry entry : yandexMetricaConfig.getErrorEnvironment().entrySet()) {
                a.a((String) entry.getKey(), (String) entry.getValue());
            }
        }
        return a;
    }

    private e(a aVar) {
        super(aVar.b);
        this.d = aVar.d;
        this.f = aVar.f;
        this.e = aVar.e;
        this.a = aVar.c;
        this.b = aVar.g;
        this.h = aVar.i;
        this.g = aVar.h;
        this.c = aVar.a;
        this.i = aVar.j;
        this.j = aVar.k;
    }

    public String a() {
        return this.d;
    }

    public String b() {
        return this.e;
    }

    public Integer c() {
        return this.f;
    }

    public a d() {
        return this.a;
    }

    public Map<String, String> e() {
        return this.b;
    }

    public String f() {
        return this.c;
    }

    public Integer g() {
        return this.h;
    }

    public Integer h() {
        return this.g;
    }

    public Map<String, String> i() {
        return this.i;
    }

    public Boolean j() {
        return this.j;
    }
}
