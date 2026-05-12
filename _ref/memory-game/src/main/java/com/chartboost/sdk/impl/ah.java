package com.chartboost.sdk.impl;

import com.applovin.sdk.AppLovinEventParameters;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.c;
import com.chartboost.sdk.f;
import com.cmcm.adsdk.Const;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;

public final class ah extends ad {
    private final com.chartboost.sdk.Libraries.e.a b = com.chartboost.sdk.Libraries.e.a.a();
    private final com.chartboost.sdk.Libraries.e.a c = com.chartboost.sdk.Libraries.e.a.a();
    private final com.chartboost.sdk.Libraries.e.a d = com.chartboost.sdk.Libraries.e.a.a();
    private final com.chartboost.sdk.Libraries.e.a e = com.chartboost.sdk.Libraries.e.a.a();

    public enum a {
        AD
    }

    public ah(String str) {
        super(str);
    }

    protected void d() {
        int i = 1;
        as i2 = f.i();
        this.c.a(SettingsJsonConstants.APP_KEY, i2.o);
        this.c.a("bundle", i2.e);
        this.c.a("bundle_id", i2.f);
        this.c.a("custom_id", c.p());
        this.c.a("session_id", "");
        this.c.a("ui", Integer.valueOf(-1));
        this.c.a("test_mode", Boolean.valueOf(false));
        this.a.a(SettingsJsonConstants.APP_KEY, this.c);
        this.d.a("carrier", e.a(e.a("carrier_name", i2.q.e("carrier-name")), e.a("mobile_country_code", i2.q.e("mobile-country-code")), e.a("mobile_network_code", i2.q.e("mobile-network-code")), e.a("iso_country_code", i2.q.e("iso-country-code")), e.a("phone_type", Integer.valueOf(i2.q.f("phone-type")))));
        this.d.a("model", i2.a);
        this.d.a("device_type", i2.p);
        this.d.a("os", i2.b);
        this.d.a("country", i2.c);
        this.d.a("language", i2.d);
        this.d.a("timestamp", i2.m);
        this.d.a("reachability", Integer.valueOf(f.h().a()));
        this.d.a("scale", i2.n);
        com.chartboost.sdk.Libraries.e.a aVar = this.d;
        String str = "is_portrait";
        if (!CBUtility.a().a()) {
            i = 0;
        }
        aVar.a(str, Integer.valueOf(i));
        this.d.a("rooted_device", Boolean.valueOf(i2.r));
        this.d.a("timezone", i2.s);
        this.d.a("mobile_network", i2.t);
        this.d.a("dw", i2.j);
        this.d.a("dh", i2.k);
        this.d.a("dpi", i2.l);
        this.d.a("w", i2.h);
        this.d.a("h", i2.i);
        this.d.a("device_family", "");
        this.d.a("retina", Boolean.valueOf(false));
        this.d.a("identity", com.chartboost.sdk.Libraries.c.b());
        com.chartboost.sdk.Libraries.c.a c = com.chartboost.sdk.Libraries.c.c();
        if (c.b()) {
            this.d.a("tracking", Integer.valueOf(c.a()));
        }
        this.a.a("device", this.d);
        this.b.a("framework", "");
        this.b.a("sdk", i2.g);
        if (c.b() != null) {
            this.b.a("framework_version", c.c());
            this.b.a("wrapper_version", c.d());
        }
        this.b.a("mediation", c.e());
        this.b.a("commit_hash", "2c21bbaaeeb65c0ecc688dee8b3bfeb4fbf1916b");
        CharSequence T = c.T();
        if (!a.a().a(T)) {
            this.b.a("config_variant", T);
        }
        this.a.a("sdk", this.b);
        this.e.a(SettingsJsonConstants.SESSION_KEY, Integer.valueOf(f.p().getInt("cbPrefSessionCount", 0)));
        if (this.e.a("cache").b()) {
            this.e.a("cache", Boolean.valueOf(false));
        }
        if (this.e.a(AppLovinEventParameters.REVENUE_AMOUNT).b()) {
            this.e.a(AppLovinEventParameters.REVENUE_AMOUNT, Integer.valueOf(0));
        }
        if (this.e.a("retry_count").b()) {
            this.e.a("retry_count", Integer.valueOf(0));
        }
        if (this.e.a("location").b()) {
            this.e.a("location", "");
        }
        this.a.a(Const.KEY_JUHE, this.e);
    }

    public void a(String str, Object obj, a aVar) {
        if (this.a == null) {
            this.a = com.chartboost.sdk.Libraries.e.a.a();
        }
        switch (aVar) {
            case AD:
                this.e.a(str, obj);
                this.a.a(Const.KEY_JUHE, this.e);
                return;
            default:
                return;
        }
    }
}
