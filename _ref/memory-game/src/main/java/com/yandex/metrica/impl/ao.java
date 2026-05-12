package com.yandex.metrica.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.cube.memorygames.games.Game1MemoryGridActivity;
import com.yandex.metrica.c.a.f;
import com.yandex.metrica.c.a.g;
import com.yandex.metrica.c.a.h;
import com.yandex.metrica.impl.ob.ay;
import com.yandex.metrica.impl.ob.ba;
import com.yandex.metrica.impl.ob.cx;
import com.yandex.metrica.impl.ob.cy;
import com.yandex.metrica.impl.ob.cz;
import com.yandex.metrica.impl.ob.d;
import com.yandex.metrica.impl.ob.da;
import com.yandex.metrica.impl.ob.j;
import com.yandex.metrica.impl.utils.i;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

class ao extends l {
    com.yandex.metrica.c.a l;
    av m;
    ba n;
    j o;
    List<Long> p;
    int q = 0;
    int r = -1;
    private c s;
    private final com.yandex.metrica.impl.utils.c t = new com.yandex.metrica.impl.utils.c();
    private boolean u;

    static class a {
        a() {
        }

        ao a(j jVar) {
            return new ao(jVar);
        }
    }

    static final class b {
        final g a;
        final com.yandex.metrica.impl.a.a b;
        final boolean c;

        b(g gVar, com.yandex.metrica.impl.a.a aVar, boolean z) {
            this.a = gVar;
            this.b = aVar;
            this.c = z;
        }
    }

    static final class c {
        final List<g> a;
        final List<Long> b;
        final JSONObject c;

        c(List<g> list, List<Long> list2, JSONObject jSONObject) {
            this.a = list;
            this.b = list2;
            this.c = jSONObject;
        }
    }

    public ao(j jVar) {
        this.o = jVar;
        this.n = jVar.i();
        this.m = jVar.h();
        this.q = com.yandex.metrica.impl.ob.b.b(1, ak.a(Long.valueOf(System.currentTimeMillis() / 1000), Long.valueOf(i.a())));
    }

    void q() {
        Builder buildUpon = Uri.parse(this.m.C()).buildUpon();
        buildUpon.path("report");
        buildUpon.appendQueryParameter("deviceid", be.c(this.c.c(), this.m.c()));
        buildUpon.appendQueryParameter("uuid", be.c(this.c.b(), this.m.b()));
        buildUpon.appendQueryParameter("analytics_sdk_version", be.c(this.c.h(), this.m.h()));
        buildUpon.appendQueryParameter("client_analytics_sdk_version", be.c(this.c.i(), this.m.i()));
        buildUpon.appendQueryParameter("app_version_name", be.c(this.c.x(), this.m.x()));
        buildUpon.appendQueryParameter("app_build_number", be.c(this.c.z(), this.m.z()));
        buildUpon.appendQueryParameter("os_version", be.c(this.c.q(), this.m.q()));
        if (this.c.r() > 0) {
            buildUpon.appendQueryParameter("os_api_level", String.valueOf(this.c.r()));
        }
        if (!TextUtils.isEmpty(this.c.k())) {
            buildUpon.appendQueryParameter("analytics_sdk_build_number", this.c.k());
        }
        if (!TextUtils.isEmpty(this.c.l())) {
            buildUpon.appendQueryParameter("analytics_sdk_build_type", this.c.l());
        }
        buildUpon.appendQueryParameter("locale", be.c(this.c.w(), this.m.w()));
        buildUpon.appendQueryParameter("is_rooted", be.c(this.c.E(), this.m.E()));
        buildUpon.appendQueryParameter("app_framework", be.c(this.c.d(), this.m.d()));
        buildUpon.appendQueryParameter(this.m.j() >= 200 ? "api_key_128" : "api_key", t());
        buildUpon.appendQueryParameter("app_id", this.o.l().b());
        buildUpon.appendQueryParameter("app_platform", this.m.m());
        buildUpon.appendQueryParameter("protocol_version", this.m.f());
        buildUpon.appendQueryParameter("model", this.m.p());
        buildUpon.appendQueryParameter("manufacturer", this.m.o());
        buildUpon.appendQueryParameter("screen_width", String.valueOf(this.m.s()));
        buildUpon.appendQueryParameter("screen_height", String.valueOf(this.m.t()));
        buildUpon.appendQueryParameter("screen_dpi", String.valueOf(this.m.u()));
        buildUpon.appendQueryParameter("scalefactor", String.valueOf(this.m.v()));
        buildUpon.appendQueryParameter("device_type", this.m.F());
        buildUpon.appendQueryParameter("android_id", this.m.n());
        Object a = this.m.a(this.o.m());
        if (!TextUtils.isEmpty(a)) {
            buildUpon.appendQueryParameter("adv_id", a);
        }
        a = this.m.y();
        if (!TextUtils.isEmpty(a)) {
            buildUpon.appendQueryParameter("clids_set", a);
        }
        a(buildUpon.build().toString());
    }

    com.yandex.metrica.c.a a(c cVar, f[] fVarArr) {
        com.yandex.metrica.c.a aVar = new com.yandex.metrica.c.a();
        a(aVar);
        aVar.b = ak.a(Long.valueOf(System.currentTimeMillis() / 1000), Long.valueOf(i.a()));
        aVar.c = (g[]) cVar.a.toArray(new g[cVar.a.size()]);
        aVar.d = a(cVar.c);
        aVar.e = fVarArr;
        this.q += com.yandex.metrica.impl.ob.b.g(8);
        return aVar;
    }

    void a(final com.yandex.metrica.c.a aVar) {
        cy.a(this.o.m()).a(new da(this) {
            final /* synthetic */ ao b;

            public void a(cz czVar) {
                int i = 0;
                com.yandex.metrica.c.a aVar = aVar;
                Collection c = czVar.c();
                if (!bg.a(c)) {
                    aVar.f = new String[c.size()];
                    for (int i2 = 0; i2 < c.size(); i2++) {
                        String str = (String) c.get(i2);
                        if (!TextUtils.isEmpty(str)) {
                            aVar.f[i2] = str;
                            ao aoVar = this.b;
                            aoVar.q += com.yandex.metrica.impl.ob.b.b(aVar.f[i2]);
                            aoVar = this.b;
                            aoVar.q += com.yandex.metrica.impl.ob.b.g(9);
                        }
                    }
                }
                com.yandex.metrica.c.a aVar2 = aVar;
                Collection a = czVar.a();
                if (!bg.a(a)) {
                    aVar2.g = new h[a.size()];
                    while (i < a.size()) {
                        aVar2.g[i] = ak.a((cx) a.get(i));
                        aoVar = this.b;
                        aoVar.q += com.yandex.metrica.impl.ob.b.b(aVar2.g[i]);
                        aoVar = this.b;
                        aoVar.q += com.yandex.metrica.impl.ob.b.g(10);
                        i++;
                    }
                }
            }
        });
    }

    public boolean b() {
        Closeable gZIPOutputStream;
        Throwable th;
        Closeable closeable = null;
        if (!this.m.I()) {
            return false;
        }
        this.p = null;
        this.u = this.o.A();
        f[] r = r();
        this.s = s();
        if (this.s.a.isEmpty()) {
            return false;
        }
        this.l = a(this.s, r);
        q();
        this.p = this.s.b;
        byte[] a = d.a(this.l);
        Closeable byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            try {
                gZIPOutputStream.write(a, 0, a.length);
                gZIPOutputStream.finish();
                a(byteArrayOutputStream.toByteArray());
                b(HttpRequest.ENCODING_GZIP);
                bg.a(byteArrayOutputStream);
                bg.a(gZIPOutputStream);
            } catch (Exception e) {
                try {
                    a(a);
                    b("identity");
                    bg.a(byteArrayOutputStream);
                    bg.a(gZIPOutputStream);
                    return true;
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    closeable = gZIPOutputStream;
                    th = th3;
                    bg.a(byteArrayOutputStream);
                    bg.a(closeable);
                    throw th;
                }
            }
        } catch (Exception e2) {
            gZIPOutputStream = null;
            a(a);
            b("identity");
            bg.a(byteArrayOutputStream);
            bg.a(gZIPOutputStream);
            return true;
        } catch (Throwable th4) {
            th = th4;
            bg.a(byteArrayOutputStream);
            bg.a(closeable);
            throw th;
        }
        return true;
    }

    f[] r() {
        f[] a = ak.a(this.o.m());
        if (a != null) {
            for (d b : a) {
                this.q = com.yandex.metrica.impl.ob.b.b(b) + this.q;
            }
        }
        return a;
    }

    private static com.yandex.metrica.c.a.c[] a(JSONObject jSONObject) {
        int length = jSONObject.length();
        if (length <= 0) {
            return null;
        }
        com.yandex.metrica.c.a.c[] cVarArr = new com.yandex.metrica.c.a.c[length];
        Iterator keys = jSONObject.keys();
        int i = 0;
        while (keys.hasNext()) {
            String str = (String) keys.next();
            try {
                com.yandex.metrica.c.a.c cVar = new com.yandex.metrica.c.a.c();
                cVar.b = str;
                cVar.c = jSONObject.getString(str);
                cVarArr[i] = cVar;
            } catch (JSONException e) {
            }
            i++;
        }
        return cVarArr;
    }

    public boolean c() {
        int i = 1;
        int i2 = 0;
        this.k = j() == 200;
        int i3;
        if (j() == Game1MemoryGridActivity.START_ANIMATION_DURATION) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        if (!this.k && r0 == 0) {
            i = 0;
        }
        if (i != 0) {
            g[] gVarArr = this.l.c;
            while (i2 < gVarArr.length) {
                g gVar = gVarArr[i2];
                this.n.a(((Long) this.p.get(i2)).longValue(), ak.a(gVar.c.d).a(), gVar.d.length);
                ak.a(gVar);
                i2++;
            }
            this.n.a(this.o.a().c());
        }
        return this.k;
    }

    public boolean d() {
        return true;
    }

    public void e() {
        if (this.k) {
            com.yandex.metrica.impl.utils.f p = this.o.p();
            if (p.b()) {
                for (int i = 0; i < this.s.a.size(); i++) {
                    p.a((g) this.s.a.get(i), "Event sent");
                }
            }
        }
        this.s = null;
    }

    protected c s() {
        Cursor u;
        JSONObject jSONObject;
        Cursor cursor;
        Throwable th;
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        JSONObject jSONObject2 = new JSONObject();
        try {
            u = u();
            JSONObject jSONObject3 = jSONObject2;
            com.yandex.metrica.impl.a.a aVar = null;
            jSONObject = jSONObject3;
            while (u.moveToNext()) {
                try {
                    ContentValues contentValues = new ContentValues();
                    com.yandex.metrica.impl.utils.b.a(u, contentValues);
                    long longValue = contentValues.getAsLong("id").longValue();
                    ay a = ay.a(contentValues.getAsInteger("type"));
                    if (!a(longValue)) {
                        com.yandex.metrica.c.b a2 = ak.a(contentValues);
                        com.yandex.metrica.c.a.g.b a3 = ak.a(this.m.w(), ak.a(a), a2);
                        this.q += com.yandex.metrica.impl.ob.b.c(1, Long.MAX_VALUE);
                        this.q += com.yandex.metrica.impl.ob.b.b(2, (d) a3);
                        if (this.q >= 250880) {
                            break;
                        }
                        b a4 = a(longValue, a3);
                        if (a4 == null) {
                            continue;
                        } else {
                            if (aVar != null) {
                                if (!aVar.equals(a4.b)) {
                                    break;
                                }
                            } else {
                                aVar = a4.b;
                            }
                            arrayList2.add(Long.valueOf(longValue));
                            arrayList.add(a4.a);
                            try {
                                jSONObject = new JSONObject(a4.b.a);
                            } catch (JSONException e) {
                            }
                            if (a4.c) {
                                break;
                            }
                        }
                    }
                } catch (Exception e2) {
                    cursor = u;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            bg.a(u);
        } catch (Exception e3) {
            jSONObject = jSONObject2;
            cursor = null;
            bg.a(cursor);
            return new c(arrayList, arrayList2, jSONObject);
        } catch (Throwable th3) {
            u = null;
            th = th3;
            bg.a(u);
            throw th;
        }
        return new c(arrayList, arrayList2, jSONObject);
    }

    private static int a(com.yandex.metrica.impl.a.a aVar) {
        try {
            com.yandex.metrica.c.a.c[] a = a(new JSONObject(aVar.a));
            if (a == null) {
                return 0;
            }
            int i = 0;
            int i2 = 0;
            while (i < a.length) {
                int b = com.yandex.metrica.impl.ob.b.b(7, a[i]) + i2;
                i++;
                i2 = b;
            }
            return i2;
        } catch (JSONException e) {
            return 0;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected com.yandex.metrica.impl.ao.b a(long r16, com.yandex.metrica.c.a.g.b r18) {
        /*
        r15 = this;
        r7 = new com.yandex.metrica.c$a$g;
        r7.<init>();
        r0 = r16;
        r7.b = r0;
        r0 = r18;
        r7.c = r0;
        r4 = 0;
        r0 = r18;
        r2 = r0.d;
        r5 = com.yandex.metrica.impl.ak.a(r2);
        r2 = 0;
        r3 = 0;
        r0 = r16;
        r5 = r15.a(r0, r5);	 Catch:{ Exception -> 0x017e, all -> 0x0176 }
        r8 = new java.util.ArrayList;	 Catch:{ Exception -> 0x0183, all -> 0x017c }
        r8.<init>();	 Catch:{ Exception -> 0x0183, all -> 0x017c }
    L_0x0023:
        r4 = r5.moveToNext();	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        if (r4 == 0) goto L_0x0191;
    L_0x0029:
        r9 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r9.<init>();	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        com.yandex.metrica.impl.utils.b.a(r5, r9);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r4 = 0;
        r6 = "type";
        r6 = r9.getAsInteger(r6);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r6 = r6.intValue();	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r10 = r15.u;	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r6 = com.yandex.metrica.impl.ak.a.a(r6, r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r10 = "custom_type";
        r10 = r9.getAsInteger(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r6 = r6.b(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r10 = "name";
        r10 = r9.getAsString(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r6 = r6.a(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r10 = "value";
        r10 = r9.getAsString(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r6 = r6.b(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r10 = "time";
        r10 = r9.getAsLong(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r10 = r10.longValue();	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r6 = r6.a(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r10 = "number";
        r10 = r9.getAsInteger(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r10 = r10.intValue();	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r6 = r6.a(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r10 = "cell_info";
        r10 = r9.getAsString(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r6 = r6.e(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r10 = "location_info";
        r10 = r9.getAsString(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r6 = r6.c(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r10 = "wifi_network_info";
        r10 = r9.getAsString(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r6 = r6.d(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r10 = "error_environment";
        r10 = r9.getAsString(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r6 = r6.f(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r10 = "user_info";
        r10 = r9.getAsString(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r6 = r6.g(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r10 = "truncated";
        r10 = r9.getAsInteger(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r10 = r10.intValue();	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r6 = r6.b(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r10 = "connection_type";
        r10 = r9.getAsInteger(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r10 = r10.intValue();	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r6 = r6.c(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r10 = "cellular_connection_type";
        r10 = r9.getAsString(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r6 = r6.h(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r10 = r6.c();	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        if (r10 == 0) goto L_0x018e;
    L_0x00da:
        r4 = r6.e();	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r6 = r4;
    L_0x00df:
        if (r6 == 0) goto L_0x0023;
    L_0x00e1:
        r4 = new com.yandex.metrica.impl.a$a;	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r10 = "app_environment";
        r10 = r9.getAsString(r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r11 = "app_environment_revision";
        r9 = r9.getAsLong(r11);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r12 = r9.longValue();	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r4.<init>(r10, r12);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        if (r2 != 0) goto L_0x014f;
    L_0x00f8:
        r2 = r15.r;	 Catch:{ Exception -> 0x0189, all -> 0x017c }
        if (r2 >= 0) goto L_0x0193;
    L_0x00fc:
        r2 = a(r4);	 Catch:{ Exception -> 0x0189, all -> 0x017c }
        r15.r = r2;	 Catch:{ Exception -> 0x0189, all -> 0x017c }
        r2 = r15.q;	 Catch:{ Exception -> 0x0189, all -> 0x017c }
        r9 = r15.r;	 Catch:{ Exception -> 0x0189, all -> 0x017c }
        r2 = r2 + r9;
        r15.q = r2;	 Catch:{ Exception -> 0x0189, all -> 0x017c }
        r2 = r4;
    L_0x010a:
        r4 = r15.t;	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r9 = r6.f;	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r10 = 245760; // 0x3c000 float:3.44383E-40 double:1.214216E-318;
        r4 = r4.a(r9, r10);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r9 = r6.f;	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r9 = r9.equals(r4);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        if (r9 != 0) goto L_0x012a;
    L_0x011d:
        r6.f = r4;	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r9 = r6.k;	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r10 = r6.f;	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r10 = r10.length;	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r4 = r4.length;	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r4 = r10 - r4;
        r4 = r4 + r9;
        r6.k = r4;	 Catch:{ Exception -> 0x0140, all -> 0x017c }
    L_0x012a:
        r4 = r15.q;	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r9 = 3;
        r9 = com.yandex.metrica.impl.ob.b.b(r9, r6);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r4 = r4 + r9;
        r15.q = r4;	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r4 = r15.q;	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        r9 = 250880; // 0x3d400 float:3.51558E-40 double:1.23951E-318;
        if (r4 >= r9) goto L_0x0191;
    L_0x013b:
        r8.add(r6);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        goto L_0x0023;
    L_0x0140:
        r4 = move-exception;
        r4 = r5;
        r14 = r2;
        r2 = r3;
        r3 = r14;
    L_0x0145:
        com.yandex.metrica.impl.bg.a(r4);
    L_0x0148:
        r4 = new com.yandex.metrica.impl.ao$b;
        r4.<init>(r7, r3, r2);
        r2 = r4;
    L_0x014e:
        return r2;
    L_0x014f:
        r4 = r2.equals(r4);	 Catch:{ Exception -> 0x0140, all -> 0x017c }
        if (r4 != 0) goto L_0x010a;
    L_0x0155:
        r3 = 1;
        r4 = r2;
    L_0x0157:
        r2 = r8.size();	 Catch:{ Exception -> 0x0189, all -> 0x017c }
        if (r2 <= 0) goto L_0x0171;
    L_0x015d:
        r2 = r8.size();	 Catch:{ Exception -> 0x0189, all -> 0x017c }
        r2 = new com.yandex.metrica.c.a.g.a[r2];	 Catch:{ Exception -> 0x0189, all -> 0x017c }
        r2 = r8.toArray(r2);	 Catch:{ Exception -> 0x0189, all -> 0x017c }
        r2 = (com.yandex.metrica.c.a.g.a[]) r2;	 Catch:{ Exception -> 0x0189, all -> 0x017c }
        r7.d = r2;	 Catch:{ Exception -> 0x0189, all -> 0x017c }
        com.yandex.metrica.impl.bg.a(r5);
        r2 = r3;
        r3 = r4;
        goto L_0x0148;
    L_0x0171:
        com.yandex.metrica.impl.bg.a(r5);
        r2 = 0;
        goto L_0x014e;
    L_0x0176:
        r2 = move-exception;
        r5 = r4;
    L_0x0178:
        com.yandex.metrica.impl.bg.a(r5);
        throw r2;
    L_0x017c:
        r2 = move-exception;
        goto L_0x0178;
    L_0x017e:
        r5 = move-exception;
        r14 = r3;
        r3 = r2;
        r2 = r14;
        goto L_0x0145;
    L_0x0183:
        r4 = move-exception;
        r4 = r5;
        r14 = r2;
        r2 = r3;
        r3 = r14;
        goto L_0x0145;
    L_0x0189:
        r2 = move-exception;
        r2 = r3;
        r3 = r4;
        r4 = r5;
        goto L_0x0145;
    L_0x018e:
        r6 = r4;
        goto L_0x00df;
    L_0x0191:
        r4 = r2;
        goto L_0x0157;
    L_0x0193:
        r2 = r4;
        goto L_0x010a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ao.a(long, com.yandex.metrica.c$a$g$b):com.yandex.metrica.impl.ao$b");
    }

    protected String t() {
        return this.m.a();
    }

    protected Cursor u() {
        return this.n.a(this.b);
    }

    protected Cursor a(long j, ay ayVar) {
        return this.n.b(j, ayVar);
    }

    protected boolean a(long j) {
        return -2 == j;
    }

    public static a v() {
        return new a();
    }
}
