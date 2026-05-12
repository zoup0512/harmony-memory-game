package com.flurry.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import bolts.MeasurementEvent;
import com.amazonaws.services.s3.internal.Constants;
import com.cube.memorygames.games.Game1MemoryGridActivity;
import com.flurry.sdk.lq.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class im implements a {
    private static final String a = im.class.getSimpleName();
    private final kq<hy> b = new kq("proton config request", new iy());
    private final kq<hz> c = new kq("proton config response", new iz());
    private final il d = new il();
    private final kd<String, ic> e = new kd();
    private final List<it> f = new ArrayList();
    private kf<ik> g;
    private kf<List<it>> h;
    private boolean i;
    private String j;
    private boolean k = true;
    private boolean l;
    private long m = 10000;
    private long n;
    private boolean o;
    private hz p;
    private boolean q;
    private final Runnable r = new ma(this) {
        final /* synthetic */ im a;

        {
            this.a = r1;
        }

        public final void a() {
            this.a.e();
        }
    };
    private final kh<jm> s = new kh<jm>(this) {
        final /* synthetic */ im a;

        {
            this.a = r1;
        }

        public final /* bridge */ /* synthetic */ void a(kg kgVar) {
            this.a.e();
        }
    };
    private final kh<jn> t = new kh<jn>(this) {
        final /* synthetic */ im a;

        {
            this.a = r1;
        }

        public final /* bridge */ /* synthetic */ void a(kg kgVar) {
            this.a.e();
        }
    };
    private final kh<jq> u = new kh<jq>(this) {
        final /* synthetic */ im a;

        {
            this.a = r1;
        }

        public final /* bridge */ /* synthetic */ void a(kg kgVar) {
            if (((jq) kgVar).a) {
                this.a.e();
            }
        }
    };

    public im() {
        lq a = lp.a();
        this.i = ((Boolean) a.a("ProtonEnabled")).booleanValue();
        a.a("ProtonEnabled", (a) this);
        km.a(4, a, "initSettings, protonEnabled = " + this.i);
        this.j = (String) a.a("ProtonConfigUrl");
        a.a("ProtonConfigUrl", (a) this);
        km.a(4, a, "initSettings, protonConfigUrl = " + this.j);
        this.k = ((Boolean) a.a("analyticsEnabled")).booleanValue();
        a.a("analyticsEnabled", (a) this);
        km.a(4, a, "initSettings, AnalyticsEnabled = " + this.k);
        ki.a().a("com.flurry.android.sdk.IdProviderFinishedEvent", this.s);
        ki.a().a("com.flurry.android.sdk.IdProviderUpdatedAdvertisingId", this.t);
        ki.a().a("com.flurry.android.sdk.NetworkStateEvent", this.u);
        Context context = jy.a().a;
        this.g = new kf(context.getFileStreamPath(".yflurryprotonconfig." + Long.toString(ly.i(jy.a().d), 16)), ".yflurryprotonconfig.", 1, new lj<ik>(this) {
            final /* synthetic */ im a;

            {
                this.a = r1;
            }

            public final lg<ik> a(int i) {
                return new ik.a();
            }
        });
        this.h = new kf(context.getFileStreamPath(".yflurryprotonreport." + Long.toString(ly.i(jy.a().d), 16)), ".yflurryprotonreport.", 1, new lj<List<it>>(this) {
            final /* synthetic */ im a;

            {
                this.a = r1;
            }

            public final lg<List<it>> a(int i) {
                return new lf(new it.a());
            }
        });
        jy.a().b(new ma(this) {
            final /* synthetic */ im a;

            {
                this.a = r1;
            }

            public final void a() {
                this.a.l();
            }
        });
        jy.a().b(new ma(this) {
            final /* synthetic */ im a;

            {
                this.a = r1;
            }

            public final void a() {
                this.a.m();
            }
        });
    }

    public final void a(String str, Object obj) {
        Object obj2 = -1;
        switch (str.hashCode()) {
            case -1720015653:
                if (str.equals("analyticsEnabled")) {
                    obj2 = 2;
                    break;
                }
                break;
            case 640941243:
                if (str.equals("ProtonEnabled")) {
                    obj2 = null;
                    break;
                }
                break;
            case 1591403975:
                if (str.equals("ProtonConfigUrl")) {
                    obj2 = 1;
                    break;
                }
                break;
        }
        switch (obj2) {
            case null:
                this.i = ((Boolean) obj).booleanValue();
                km.a(4, a, "onSettingUpdate, protonEnabled = " + this.i);
                return;
            case 1:
                this.j = (String) obj;
                km.a(4, a, "onSettingUpdate, protonConfigUrl = " + this.j);
                return;
            case 2:
                this.k = ((Boolean) obj).booleanValue();
                km.a(4, a, "onSettingUpdate, AnalyticsEnabled = " + this.k);
                return;
            default:
                km.a(6, a, "onSettingUpdate internal error!");
                return;
        }
    }

    public final synchronized void a() {
        if (this.i) {
            ly.b();
            jk.a();
            io.a = jk.d();
            this.q = false;
            e();
        }
    }

    private synchronized void e() {
        if (this.i) {
            ly.b();
            if (this.l && jl.a().b()) {
                boolean z;
                final long currentTimeMillis = System.currentTimeMillis();
                if (jl.a().c()) {
                    z = false;
                } else {
                    z = true;
                }
                if (this.p != null) {
                    if (this.o != z) {
                        km.a(3, a, "Limit ad tracking value has changed, purging");
                        this.p = null;
                    } else if (System.currentTimeMillis() < this.n + (this.p.b * 1000)) {
                        km.a(3, a, "Cached Proton config valid, no need to refresh");
                        if (!this.q) {
                            this.q = true;
                            b("flurry.session_start", null);
                        }
                    } else if (System.currentTimeMillis() >= this.n + (this.p.c * 1000)) {
                        km.a(3, a, "Cached Proton config expired, purging");
                        this.p = null;
                        this.e.a();
                    }
                }
                jw.a().a((Object) this);
                km.a(3, a, "Requesting proton config");
                byte[] f = f();
                if (f != null) {
                    String str;
                    mb ksVar = new ks();
                    if (TextUtils.isEmpty(this.j)) {
                        str = "https://proton.flurry.com/sdk/v1/config";
                    } else {
                        str = this.j;
                    }
                    ksVar.g = str;
                    ksVar.u = 5000;
                    ksVar.h = ku.a.kPost;
                    String num = Integer.toString(kq.a(f));
                    ksVar.a("Content-Type", "application/x-flurry;version=2");
                    ksVar.a("Accept", "application/x-flurry;version=2");
                    ksVar.a("FM-Checksum", num);
                    ksVar.c = new lc();
                    ksVar.d = new lc();
                    ksVar.b = f;
                    ksVar.a = new ks.a<byte[], byte[]>(this) {
                        final /* synthetic */ im c;

                        public final /* synthetic */ void a(ks ksVar, Object obj) {
                            hz hzVar = null;
                            final byte[] bArr = (byte[]) obj;
                            int i = ksVar.q;
                            km.a(3, im.a, "Proton config request: HTTP status code is:" + i);
                            if (i == Game1MemoryGridActivity.START_ANIMATION_DURATION || i == 406 || i == Constants.FAILED_PRECONDITION_STATUS_CODE || i == 415) {
                                this.c.m = 10000;
                                return;
                            }
                            if (ksVar.b() && bArr != null) {
                                hz hzVar2;
                                jy.a().b(new ma(this) {
                                    final /* synthetic */ AnonymousClass11 b;

                                    public final void a() {
                                        this.b.c.a(currentTimeMillis, z, bArr);
                                    }
                                });
                                try {
                                    hzVar2 = (hz) this.c.c.b(bArr);
                                } catch (Exception e) {
                                    km.a(5, im.a, "Failed to decode proton config response: " + e);
                                    hzVar2 = null;
                                }
                                if (im.b(hzVar2)) {
                                    hzVar = hzVar2;
                                }
                                if (hzVar != null) {
                                    this.c.m = 10000;
                                    this.c.n = currentTimeMillis;
                                    this.c.o = z;
                                    this.c.p = hzVar;
                                    this.c.g();
                                    if (!this.c.q) {
                                        this.c.q = true;
                                        this.c.b("flurry.session_start", null);
                                    }
                                    this.c.h();
                                }
                            }
                            if (hzVar == null) {
                                long parseLong;
                                long i2 = this.c.m << 1;
                                if (i == 429) {
                                    List a = ksVar.a("Retry-After");
                                    if (!a.isEmpty()) {
                                        String str = (String) a.get(0);
                                        km.a(3, im.a, "Server returned retry time: " + str);
                                        try {
                                            parseLong = Long.parseLong(str) * 1000;
                                        } catch (NumberFormatException e2) {
                                            km.a(3, im.a, "Server returned nonsensical retry time");
                                        }
                                        this.c.m = parseLong;
                                        km.a(3, im.a, "Proton config request failed, backing off: " + this.c.m + "ms");
                                        jy.a().a(this.c.r, this.c.m);
                                    }
                                }
                                parseLong = i2;
                                this.c.m = parseLong;
                                km.a(3, im.a, "Proton config request failed, backing off: " + this.c.m + "ms");
                                jy.a().a(this.c.r, this.c.m);
                            }
                        }
                    };
                    jw.a().a((Object) this, ksVar);
                }
            }
        }
    }

    private synchronized void b(String str, Map<String, String> map) {
        km.a(3, a, "Event triggered: " + str);
        if (!this.k) {
            km.e(a, "Analytics and pulse have been disabled.");
        } else if (this.p == null) {
            km.a(3, a, "Config response is empty. No events to fire.");
        } else {
            ly.b();
            if (!TextUtils.isEmpty(str)) {
                List<ic> a = this.e.a((Object) str);
                if (a == null) {
                    km.a(3, a, "No events to fire. Returning.");
                } else if (a.size() == 0) {
                    km.a(3, a, "No events to fire. Returning.");
                } else {
                    ix ixVar;
                    long currentTimeMillis = System.currentTimeMillis();
                    boolean z = map != null;
                    Object obj = -1;
                    switch (str.hashCode()) {
                        case 645204782:
                            if (str.equals("flurry.session_end")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 1371447545:
                            if (str.equals("flurry.app_install")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 1579613685:
                            if (str.equals("flurry.session_start")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            ixVar = ix.SESSION_START;
                            break;
                        case 1:
                            ixVar = ix.SESSION_END;
                            break;
                        case 2:
                            ixVar = ix.INSTALL;
                            break;
                        default:
                            ixVar = ix.APPLICATION_EVENT;
                            break;
                    }
                    Map hashMap = new HashMap();
                    for (ic icVar : a) {
                        Object obj2 = null;
                        if (icVar instanceof id) {
                            km.a(4, a, "Event contains triggers.");
                            String[] strArr = ((id) icVar).d;
                            if (strArr == null) {
                                km.a(4, a, "Template does not contain trigger values. Firing.");
                                obj2 = 1;
                            } else if (strArr.length == 0) {
                                km.a(4, a, "Template does not contain trigger values. Firing.");
                                obj2 = 1;
                            } else if (map == null) {
                                km.a(4, a, "Publisher has not passed in params list. Not firing.");
                            }
                            String str2 = (String) map.get(((id) icVar).c);
                            if (str2 == null) {
                                km.a(4, a, "Publisher params has no value associated with proton key. Not firing.");
                            } else {
                                Object obj3;
                                int length = strArr.length;
                                int i = 0;
                                while (i < length) {
                                    if (strArr[i].equals(str2)) {
                                        obj3 = 1;
                                        if (obj3 != null) {
                                            km.a(4, a, "Publisher params list does not match proton param values. Not firing.");
                                        } else {
                                            km.a(4, a, "Publisher params match proton values. Firing.");
                                        }
                                    } else {
                                        i++;
                                    }
                                }
                                obj3 = obj2;
                                if (obj3 != null) {
                                    km.a(4, a, "Publisher params match proton values. Firing.");
                                } else {
                                    km.a(4, a, "Publisher params list does not match proton param values. Not firing.");
                                }
                            }
                        }
                        hw hwVar = icVar.b;
                        if (hwVar == null) {
                            km.a(3, a, "Template is empty. Not firing current event.");
                        } else {
                            km.a(3, a, "Creating callback report for partner: " + hwVar.b);
                            Map hashMap2 = new HashMap();
                            hashMap2.put(MeasurementEvent.MEASUREMENT_EVENT_NAME_KEY, str);
                            hashMap2.put("event_time_millis", String.valueOf(currentTimeMillis));
                            String a2 = this.d.a(hwVar.e, hashMap2);
                            String str3 = null;
                            if (hwVar.f != null) {
                                str3 = this.d.a(hwVar.f, hashMap2);
                            }
                            hashMap.put(Long.valueOf(hwVar.a), new ip(hwVar.b, hwVar.a, a2, System.currentTimeMillis() + 259200000, this.p.e.b, hwVar.g, hwVar.d, hwVar.j, hwVar.i, hwVar.h, str3));
                        }
                    }
                    if (hashMap.size() != 0) {
                        jk.a();
                        long d = jk.d();
                        jk.a();
                        it itVar = new it(str, z, d, jk.g(), ixVar, hashMap);
                        if ("flurry.session_end".equals(str)) {
                            km.a(3, a, "Storing Pulse callbacks for event: " + str);
                            this.f.add(itVar);
                        } else {
                            km.a(3, a, "Firing Pulse callbacks for event: " + str);
                            is.c().a(itVar);
                        }
                    }
                }
            }
        }
    }

    private byte[] f() {
        try {
            Object hyVar = new hy();
            hyVar.a = jy.a().d;
            hyVar.b = lv.a(jy.a().a);
            hyVar.c = lv.b(jy.a().a);
            hyVar.d = jz.b();
            hyVar.e = 3;
            ju.a();
            hyVar.f = ju.b();
            hyVar.g = !jl.a().c();
            hyVar.h = new ib();
            hyVar.h.a = new hv();
            hyVar.h.a.a = Build.MODEL;
            hyVar.h.a.b = Build.BRAND;
            hyVar.h.a.c = Build.ID;
            hyVar.h.a.d = Build.DEVICE;
            hyVar.h.a.e = Build.PRODUCT;
            hyVar.h.a.f = VERSION.RELEASE;
            hyVar.i = new ArrayList();
            for (Entry entry : Collections.unmodifiableMap(jl.a().a).entrySet()) {
                ia iaVar = new ia();
                iaVar.a = ((jt) entry.getKey()).c;
                if (((jt) entry.getKey()).d) {
                    iaVar.b = new String((byte[]) entry.getValue());
                } else {
                    iaVar.b = ly.b((byte[]) entry.getValue());
                }
                hyVar.i.add(iaVar);
            }
            Location g = jp.a().g();
            if (g != null) {
                int d = jp.d();
                hyVar.j = new if();
                hyVar.j.a = new ie();
                hyVar.j.a.a = ly.a(g.getLatitude(), d);
                hyVar.j.a.b = ly.a(g.getLongitude(), d);
                hyVar.j.a.c = (float) ly.a((double) g.getAccuracy(), d);
            }
            String str = (String) lp.a().a("UserId");
            if (!str.equals("")) {
                hyVar.k = new ii();
                hyVar.k.a = str;
            }
            return this.b.a(hyVar);
        } catch (Exception e) {
            km.a(5, a, "Proton config request failed with exception: " + e);
            return null;
        }
    }

    private synchronized void a(long j, boolean z, byte[] bArr) {
        if (bArr != null) {
            km.a(4, a, "Saving proton config response");
            ik ikVar = new ik();
            ikVar.a = j;
            ikVar.b = z;
            ikVar.c = bArr;
            this.g.a(ikVar);
        }
    }

    private static boolean b(hz hzVar) {
        if (hzVar == null) {
            return false;
        }
        boolean z;
        hx hxVar = hzVar.e;
        if (!(hxVar == null || hxVar.a == null)) {
            for (int i = 0; i < hxVar.a.size(); i++) {
                hw hwVar = (hw) hxVar.a.get(i);
                if (hwVar != null) {
                    if (!hwVar.b.equals("") && hwVar.a != -1 && !hwVar.e.equals("")) {
                        List<ic> list = hwVar.c;
                        if (list != null) {
                            for (ic icVar : list) {
                                if (!icVar.a.equals("")) {
                                    if ((icVar instanceof id) && ((id) icVar).c.equals("")) {
                                        km.a(3, a, "An event trigger is missing a param name");
                                        z = false;
                                        break;
                                    }
                                } else {
                                    km.a(3, a, "An event is missing a name");
                                    z = false;
                                    break;
                                }
                            }
                        }
                        z = true;
                        if (z) {
                        }
                    }
                    km.a(3, a, "A callback template is missing required values");
                    z = false;
                    break;
                }
            }
        }
        z = true;
        if (z && (hzVar.e == null || hzVar.e.e == null || !hzVar.e.e.equals(""))) {
            return true;
        }
        km.a(3, a, "Config response is missing required values.");
        return false;
    }

    private void g() {
        if (this.p != null) {
            km.a(5, a, "Processing config response");
            is.a(this.p.e.c);
            is.b(this.p.e.d * 1000);
            iu a = iu.a();
            String str = this.p.e.e;
            if (!(str == null || str.endsWith(".do"))) {
                km.a(5, iu.a, "overriding analytics agent report URL without an endpoint, are you sure?");
            }
            a.b = str;
            if (this.i) {
                lp.a().a("analyticsEnabled", (Object) Boolean.valueOf(this.p.f.b));
            }
            this.e.a();
            hx hxVar = this.p.e;
            if (hxVar != null) {
                List<hw> list = hxVar.a;
                if (list != null) {
                    for (hw hwVar : list) {
                        if (hwVar != null) {
                            List<Object> list2 = hwVar.c;
                            if (list2 != null) {
                                for (Object obj : list2) {
                                    if (!(obj == null || TextUtils.isEmpty(obj.a))) {
                                        obj.b = hwVar;
                                        this.e.a(obj.a, obj);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private synchronized void h() {
        if (this.i) {
            ly.b();
            SharedPreferences sharedPreferences = jy.a().a.getSharedPreferences("FLURRY_SHARED_PREFERENCES", 0);
            if (sharedPreferences.getBoolean("com.flurry.android.flurryAppInstall", true)) {
                b("flurry.app_install", null);
                Editor edit = sharedPreferences.edit();
                edit.putBoolean("com.flurry.android.flurryAppInstall", false);
                edit.apply();
            }
        }
    }

    public final synchronized void b() {
        if (this.i) {
            ly.b();
            jk.a();
            b(jk.d());
            i();
        }
    }

    private synchronized void b(long j) {
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            if (j == ((it) it.next()).a) {
                it.remove();
            }
        }
    }

    private synchronized void i() {
        if (this.k) {
            km.a(4, a, "Sending " + this.f.size() + " queued reports.");
            for (it itVar : this.f) {
                km.a(3, a, "Firing Pulse callbacks for event: " + itVar.c);
                is.c().a(itVar);
            }
            j();
        } else {
            km.e(a, "Analytics disabled, not sending pulse reports.");
        }
    }

    private synchronized void j() {
        this.f.clear();
        this.h.b();
    }

    public final synchronized void a(long j) {
        if (this.i) {
            ly.b();
            b(j);
            b("flurry.session_end", null);
            jy.a().b(new ma(this) {
                final /* synthetic */ im a;

                {
                    this.a = r1;
                }

                public final void a() {
                    this.a.k();
                }
            });
        }
    }

    private synchronized void k() {
        km.a(4, a, "Saving queued report data.");
        this.h.a(this.f);
    }

    public final synchronized void c() {
        if (this.i) {
            ly.b();
            i();
        }
    }

    public final synchronized void a(String str, Map<String, String> map) {
        if (this.i) {
            ly.b();
            b(str, (Map) map);
        }
    }

    private synchronized void l() {
        ik ikVar = (ik) this.g.a();
        if (ikVar != null) {
            hz hzVar;
            try {
                hzVar = (hz) this.c.b(ikVar.c);
            } catch (Exception e) {
                km.a(5, a, "Failed to decode saved proton config response: " + e);
                this.g.b();
                hzVar = null;
            }
            if (!b(hzVar)) {
                hzVar = null;
            }
            if (hzVar != null) {
                km.a(4, a, "Loaded saved proton config response");
                this.m = 10000;
                this.n = ikVar.a;
                this.o = ikVar.b;
                this.p = hzVar;
                g();
            }
        }
        this.l = true;
        jy.a().b(new ma(this) {
            final /* synthetic */ im a;

            {
                this.a = r1;
            }

            public final void a() {
                this.a.e();
            }
        });
    }

    private synchronized void m() {
        km.a(4, a, "Loading queued report data.");
        List list = (List) this.h.a();
        if (list != null) {
            this.f.addAll(list);
        }
    }
}
