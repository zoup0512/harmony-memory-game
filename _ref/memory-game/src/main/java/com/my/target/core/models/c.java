package com.my.target.core.models;

import android.content.Context;
import com.my.target.Tracer;
import com.my.target.core.async.a;
import com.my.target.core.models.sections.b;
import com.my.target.core.models.sections.e;
import com.my.target.core.models.sections.f;
import com.my.target.core.utils.i;
import com.my.target.core.utils.j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AdData */
public final class c extends b {
    private static final i<String, String> a = new i();
    private final j b;
    private String c;
    private JSONObject d;
    private String e;
    private final long f;
    private final long g;
    private final ArrayList<f> h = new ArrayList();
    private boolean i;

    public static i<String, String> a() {
        return a;
    }

    public c(long j) {
        this.g = j;
        this.f = System.currentTimeMillis() + j;
        this.b = new j();
    }

    public final String b() {
        return this.c;
    }

    public final void a(String str) {
        this.c = str;
    }

    public final JSONObject c() {
        return this.d;
    }

    public final void a(JSONObject jSONObject) {
        this.d = jSONObject;
    }

    public final boolean d() {
        return System.currentTimeMillis() > this.f;
    }

    public final String e() {
        return this.e;
    }

    public final void b(String str) {
        this.e = str;
    }

    public final boolean a(f fVar) {
        boolean z = false;
        if (this.h.contains(fVar)) {
            return false;
        }
        if (this.h.size() == 0 || fVar.d() == -1) {
            this.h.add(fVar);
        } else {
            Iterator it = this.h.iterator();
            int i = 0;
            while (it.hasNext()) {
                f fVar2 = (f) it.next();
                if (fVar2.d() > fVar.d() || fVar2.d() == -1) {
                    this.h.add(i, fVar);
                    z = true;
                    break;
                }
                i++;
            }
            if (!z) {
                this.h.add(fVar);
            }
        }
        return true;
    }

    public final f c(String str) {
        Iterator it = this.h.iterator();
        while (it.hasNext()) {
            f fVar = (f) it.next();
            if (fVar.e().equals(str)) {
                return fVar;
            }
        }
        return null;
    }

    public final ArrayList<f> f() {
        return new ArrayList(this.h);
    }

    public final boolean g() {
        Iterator it = this.h.iterator();
        while (it.hasNext()) {
            if (((f) it.next()).b() > 0) {
                return true;
            }
        }
        return false;
    }

    public final void a(com.my.target.core.models.banners.c cVar, Context context) {
        this.b.a(cVar, context);
    }

    public static String b(com.my.target.core.models.banners.c cVar, Context context) {
        a.a(cVar.i(), "click", context);
        return cVar.e();
    }

    public final boolean a(com.my.target.core.a aVar, b bVar, com.my.target.core.models.banners.c cVar, Context context) {
        String id = cVar.getId();
        if (!bVar.m(id)) {
            return false;
        }
        boolean n = bVar.n(id);
        try {
            JSONArray jSONArray = this.d.getJSONObject(bVar.e()).getJSONArray("banners");
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                if (jSONObject.getString("bannerID").equals(id)) {
                    jSONObject.put("hasNotification", false);
                    Tracer.d("Changed notification in raw data for banner " + id);
                }
            }
        } catch (JSONException e) {
            Tracer.d("Error updating cache notification for section=" + bVar.e() + " and bannerId=" + id + ", " + e);
        }
        try {
            this.d.put("html_wrapper", this.e);
            String jSONObject2 = this.d.toString();
            this.d.remove("html_wrapper");
            com.my.target.core.factories.b.a(this.g, aVar.d(), jSONObject2, context).b();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return n;
    }

    public static void a(com.my.target.core.models.banners.c cVar, f fVar, Context context) {
        if (fVar.e() != null) {
            Tracer.d("Ad shows. adId: " + cVar.getId() + " in section " + fVar.e());
        } else {
            Tracer.d("Ad shows. adId: " + cVar.getId());
        }
        a.a(cVar.i(), "playbackStarted", context);
    }

    public static void a(Set<g> set, float f, Context context) {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            i iVar = (g) it.next();
            if (iVar.a() <= f) {
                a.a(iVar, context);
                it.remove();
            }
        }
    }

    public static void a(com.my.target.core.models.banners.c cVar, String str, Context context) {
        a.a(cVar.i(), str, context);
    }

    public static void a(f fVar, String str, Context context) {
        a.a(fVar.h(), str, context);
    }

    public static void c(com.my.target.core.models.banners.c cVar, Context context) {
        if (cVar != null) {
            Tracer.d("Ad shows. adId: " + cVar.getId());
            a.a(cVar.i(), "playbackStarted", context);
        }
    }

    public final void h() {
        f c = c("nativeads");
        if (c instanceof e) {
            e eVar = (e) c;
            if (eVar.b() > 0) {
                Iterator it = eVar.g().iterator();
                while (it.hasNext()) {
                    com.my.target.core.models.banners.f fVar = (com.my.target.core.models.banners.f) it.next();
                    a.a(fVar.getId(), fVar.getId());
                }
            }
        }
    }

    public final void i() {
        this.i = true;
    }

    public final boolean j() {
        return this.i;
    }
}
