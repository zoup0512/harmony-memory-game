package com.google.android.gms.analytics;

import com.google.android.gms.analytics.HitBuilders.HitBuilder;
import com.google.android.gms.analytics.internal.zzao;
import java.util.Map;

public class HitBuilders$ExceptionBuilder extends HitBuilder<HitBuilders$ExceptionBuilder> {
    public HitBuilders$ExceptionBuilder() {
        set("&t", "exception");
    }

    public /* bridge */ /* synthetic */ Map build() {
        return super.build();
    }

    public HitBuilders$ExceptionBuilder setDescription(String str) {
        set("&exd", str);
        return this;
    }

    public HitBuilders$ExceptionBuilder setFatal(boolean z) {
        set("&exf", zzao.zzat(z));
        return this;
    }
}
