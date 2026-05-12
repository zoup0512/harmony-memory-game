package com.google.android.gms.analytics;

import com.google.android.gms.analytics.HitBuilders.HitBuilder;
import java.util.Map;

public class HitBuilders$SocialBuilder extends HitBuilder<HitBuilders$SocialBuilder> {
    public HitBuilders$SocialBuilder() {
        set("&t", "social");
    }

    public /* bridge */ /* synthetic */ Map build() {
        return super.build();
    }

    public HitBuilders$SocialBuilder setAction(String str) {
        set("&sa", str);
        return this;
    }

    public HitBuilders$SocialBuilder setNetwork(String str) {
        set("&sn", str);
        return this;
    }

    public HitBuilders$SocialBuilder setTarget(String str) {
        set("&st", str);
        return this;
    }
}
