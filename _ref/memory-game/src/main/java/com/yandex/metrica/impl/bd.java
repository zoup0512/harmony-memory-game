package com.yandex.metrica.impl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.yandex.metrica.impl.bc.a.a;
import com.yandex.metrica.impl.ob.bm;
import com.yandex.metrica.impl.ob.br;
import com.yandex.metrica.impl.ob.bv;
import com.yandex.metrica.impl.ob.cn;
import com.yandex.metrica.impl.ob.j;
import com.yandex.metrica.impl.utils.g;
import com.yandex.metrica.impl.utils.h;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.Map;

class bd extends ag {
    private av a;
    private Context b;
    private j c;
    private bm l;
    private boolean m = false;
    private cn n;

    public bd(j jVar) {
        this.c = jVar;
        this.b = jVar.m();
        this.a = jVar.h();
        this.l = jVar.x();
    }

    public boolean b() {
        a(false);
        this.a.c(this.c);
        if (!n()) {
            return false;
        }
        Builder buildUpon = Uri.parse(this.a.D()).buildUpon();
        a(buildUpon);
        a(buildUpon.build().toString());
        return true;
    }

    boolean n() {
        boolean z = !this.a.a(this.l.a(0));
        Object a = h.a(this.c.j().u());
        if (!(z || TextUtils.isEmpty(a))) {
            if (a.equals(this.l.d())) {
                z = (System.currentTimeMillis() - this.l.e()) / 1000 > 86400;
            } else {
                z = true;
            }
        }
        if (z) {
            return z;
        }
        CharSequence d = br.a().d();
        if (TextUtils.isEmpty(d)) {
            if (TextUtils.isEmpty(b(this.a))) {
                return false;
            }
            return true;
        } else if (TextUtils.equals(b(this.a), d)) {
            return false;
        } else {
            return true;
        }
    }

    private static String b(av avVar) {
        CharSequence c = avVar.c();
        String e = avVar.e();
        if (TextUtils.isEmpty(c)) {
            return TextUtils.isEmpty(e) ? "" : e;
        } else {
            return c;
        }
    }

    void a(Builder builder) {
        builder.path("analytics/startup");
        builder.appendQueryParameter("deviceid", b(this.a));
        builder.appendQueryParameter("app_platform", this.a.m());
        builder.appendQueryParameter("protocol_version", this.a.f());
        builder.appendQueryParameter("analytics_sdk_version", this.a.h());
        builder.appendQueryParameter("analytics_sdk_version_name", this.a.g());
        builder.appendQueryParameter("model", this.a.p());
        builder.appendQueryParameter("manufacturer", this.a.o());
        builder.appendQueryParameter("os_version", this.a.q());
        builder.appendQueryParameter("screen_width", String.valueOf(this.a.s()));
        builder.appendQueryParameter("screen_height", String.valueOf(this.a.t()));
        builder.appendQueryParameter("screen_dpi", String.valueOf(this.a.u()));
        builder.appendQueryParameter("scalefactor", String.valueOf(this.a.v()));
        builder.appendQueryParameter("locale", this.a.w());
        builder.appendQueryParameter("device_type", this.a.F());
        builder.appendQueryParameter("query_hosts", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        builder.appendQueryParameter(SettingsJsonConstants.FEATURES_KEY, "easy_collecting");
        builder.appendQueryParameter("browsers", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        Map u = this.c.j().u();
        Object v = this.c.j().v();
        if (TextUtils.isEmpty(v)) {
            v = this.l.a();
        }
        if (!bg.a(u)) {
            builder.appendQueryParameter("distribution_customization", AppEventsConstants.EVENT_PARAM_VALUE_YES);
            a(builder, "clids_set", h.a(u));
            builder.appendQueryParameter("app_id", this.c.l().b());
            if (!TextUtils.isEmpty(v)) {
                builder.appendQueryParameter("install_referrer", v);
            }
        }
        a(builder, "uuid", this.a.b());
    }

    private static void a(Builder builder, String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            builder.appendQueryParameter(str, str2);
        }
    }

    public boolean c() {
        this.k = false;
        if (o()) {
            this.k = true;
        } else if (200 == this.h) {
            Map u = this.c.j().u();
            a a = bc.a(this.i);
            if (a.OK == a.i()) {
                this.l.t(this.a.y());
                this.a.a(a);
                Long a2 = bc.a(k());
                if (a2 != null) {
                    g.a().a(a2.longValue());
                }
                this.a.b(br.a().c(this.b, this.a.c()));
                a(this.a);
                this.c.a(h.a(this.a.y()).equals(u));
                j.a(this.c.k(), this.a, a);
                this.k = true;
            } else {
                this.n = cn.PARSE;
            }
        }
        return this.k;
    }

    public boolean d() {
        return !TextUtils.isEmpty(this.l.h(null)) && bv.a().c();
    }

    public void f() {
        this.n = cn.NETWORK;
    }

    public void e() {
        if (!this.k) {
            if (this.n == null) {
                this.n = cn.UNKNOWN;
            }
            j.a(this.c.k(), this.n);
        }
    }

    synchronized void a(av avVar) {
        if (!o()) {
            this.l.j(avVar.b()).l(avVar.C()).m(avVar.B()).n(avVar.A()).o(avVar.D()).i(avVar.H()).a(avVar.G());
            Object y = avVar.y();
            if (!TextUtils.isEmpty(y)) {
                this.l.p(y);
            }
            this.l.h();
            a(System.currentTimeMillis() / 1000);
            bv.a().a(this.b, this.a.b(), avVar.H());
            if (!be.a(avVar.c())) {
                Intent intent = new Intent("com.yandex.metrica.intent.action.SYNC");
                intent.putExtra("CAUSE", "CAUSE_DEVICE_ID");
                intent.putExtra("SYNC_TO_PKG", this.c.l().b());
                intent.putExtra("SYNC_DATA", avVar.c());
                intent.putExtra("SYNC_DATA_2", avVar.b());
                this.b.sendBroadcast(intent);
            }
        }
    }

    synchronized void a(long j) {
        this.l.b(j).h();
    }

    synchronized void a(boolean z) {
        this.m = z;
    }

    synchronized boolean o() {
        return this.m;
    }

    public boolean m() {
        return true;
    }
}
