package com.google.android.gms.analytics;

import com.google.android.gms.analytics.HitBuilders.HitBuilder;
import java.util.Map;

public class HitBuilders$TimingBuilder extends HitBuilder<HitBuilders$TimingBuilder> {
    public HitBuilders$TimingBuilder() {
        set("&t", "timing");
    }

    public HitBuilders$TimingBuilder(String str, String str2, long j) {
        this();
        setVariable(str2);
        setValue(j);
        setCategory(str);
    }

    public /* bridge */ /* synthetic */ Map build() {
        return super.build();
    }

    public HitBuilders$TimingBuilder setCategory(String str) {
        set("&utc", str);
        return this;
    }

    public HitBuilders$TimingBuilder setLabel(String str) {
        set("&utl", str);
        return this;
    }

    public HitBuilders$TimingBuilder setValue(long j) {
        set("&utt", Long.toString(j));
        return this;
    }

    public HitBuilders$TimingBuilder setVariable(String str) {
        set("&utv", str);
        return this;
    }
}
