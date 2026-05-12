package com.yandex.metrica.impl;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.yandex.metrica.e;
import com.yandex.metrica.impl.ob.cp;
import com.yandex.metrica.impl.utils.f;
import java.util.Map;

class z extends b implements ab {
    z(Context context, e eVar, at atVar) {
        super(context, eVar.getApiKey(), atVar, new ar());
        this.b.a(new aj(eVar.getPreloadInfo()));
    }

    void a(cp cpVar) {
        super.a(cpVar);
    }

    void a(j jVar) {
        super.a(jVar);
    }

    public void reportEvent(String eventName) {
        super.reportEvent(eventName);
    }

    public void reportEvent(String eventName, String jsonValue) {
        super.reportEvent(eventName, jsonValue);
        f.e().a("Event received: %s", eventName);
    }

    public void reportEvent(String eventName, Map<String, Object> attributes) {
        super.reportEvent(eventName, (Map) attributes);
        f.e().a("Event received: %s", eventName);
    }

    public void reportError(String message, Throwable error) {
        super.reportError(message, error);
        f.e().a("Error received: %s", message);
    }

    public void a(Application application) {
        bg.a((Object) application, "Application");
        if (VERSION.SDK_INT >= 14) {
            f.e().a("Enable activity auto tracking", new Object[0]);
            application.registerActivityLifecycleCallbacks(new m(this));
            return;
        }
        f.e().b("Could not enable activity auto tracking. API level should be more than 14 (ICE_CREAM_SANDWICH)", new Object[0]);
    }

    public void a(Activity activity) {
        b(c(activity));
    }

    public void b(Activity activity) {
        c(c(activity));
    }

    String c(Activity activity) {
        if (activity != null) {
            return activity.getClass().getSimpleName();
        }
        return null;
    }

    void a(e eVar, boolean z) {
        this.b.b().a(eVar);
        d(this.b.b().l());
        if (z) {
            b();
        }
        b(eVar.i());
        a(eVar.getErrorEnvironment());
    }

    public void c(boolean z) {
        this.b.b().a(z);
    }

    public void d(boolean z) {
        this.c.a(z, this.b);
    }

    public void a(Location location) {
        this.b.b().a(location);
    }

    public void b(boolean z) {
        this.b.b().c(z);
    }

    public void b(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            f.e().b("Invalid App Environment (key,value) pair: (%s,%s).", str, str2);
            return;
        }
        super.b(str, str2);
    }

    public void a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            f.e().b("Invalid Error Environment (key,value) pair: (%s,%s).", str, str2);
            return;
        }
        super.a(str, str2);
    }

    public void a(boolean z) {
        this.b.b().d(z);
        this.c.a(p.a(), this.b);
    }

    public boolean h() {
        return this.b.b().q();
    }

    public boolean f() {
        return this.b.b().k();
    }
}
