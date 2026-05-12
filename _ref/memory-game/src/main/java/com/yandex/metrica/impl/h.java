package com.yandex.metrica.impl;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.yandex.metrica.impl.ob.j;
import java.util.Locale;
import org.json.JSONArray;

public class h {
    String a;
    String b;
    int c;
    int d;
    int e;
    private a f = new a();
    private String g;
    private String h;
    private String i;
    private Bundle j;
    private int k = 2;
    private String l;

    private static final class a {
        Location a;
        String b;
        Integer c;

        private a() {
        }
    }

    public h(h hVar) {
        if (hVar != null) {
            this.a = hVar.a();
            this.b = hVar.b();
            this.c = hVar.c();
            this.d = hVar.d();
            this.g = hVar.k();
            this.i = hVar.l();
            this.h = hVar.i();
            this.f.a = hVar.e();
            this.f.b = hVar.f();
            this.f.c = hVar.h();
            this.j = hVar.j();
            this.e = hVar.o();
            this.k = hVar.p();
            this.l = hVar.q();
        }
    }

    public h(String str, String str2, int i) {
        this.a = str2;
        this.c = i;
        this.b = str;
    }

    public String a() {
        return this.a;
    }

    public h b(String str) {
        this.a = str;
        return this;
    }

    public String b() {
        return this.b;
    }

    public h c(String str) {
        this.b = str;
        return this;
    }

    public int c() {
        return this.c;
    }

    public h a(int i) {
        this.c = i;
        return this;
    }

    public int d() {
        return this.d;
    }

    public h b(int i) {
        this.d = i;
        return this;
    }

    public Location e() {
        return this.f.a;
    }

    h a(Location location) {
        this.f.a = location;
        return this;
    }

    String f() {
        return this.f.b;
    }

    JSONArray g() {
        try {
            return new JSONArray(this.f.b);
        } catch (Exception e) {
            return new JSONArray();
        }
    }

    h d(String str) {
        this.f.b = str;
        return this;
    }

    Integer h() {
        return this.f.c;
    }

    h a(Integer num) {
        this.f.c = num;
        return this;
    }

    String i() {
        return this.h;
    }

    public Bundle j() {
        return this.j;
    }

    h e(String str) {
        this.h = str;
        return this;
    }

    h a(String str, String str2) {
        if (this.j == null) {
            this.j = new Bundle();
        }
        this.j.putString(str, str2);
        return this;
    }

    public String k() {
        return this.g;
    }

    public h a(String str) {
        this.g = str;
        return this;
    }

    public String l() {
        return this.i;
    }

    public h f(String str) {
        this.i = str;
        return this;
    }

    protected h c(int i) {
        this.e = i;
        return this;
    }

    protected h d(int i) {
        this.k = i;
        return this;
    }

    protected h g(String str) {
        this.l = str;
        return this;
    }

    public boolean m() {
        return this.a == null;
    }

    public boolean n() {
        return com.yandex.metrica.impl.p.a.EVENT_TYPE_UNDEFINED.a() == this.c;
    }

    public int o() {
        return this.e;
    }

    public int p() {
        return this.k;
    }

    public String q() {
        return this.l;
    }

    Bundle a(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString("CounterReport.Event", this.a);
        bundle2.putString("CounterReport.Value", this.b);
        bundle2.putInt("CounterReport.Type", this.c);
        bundle2.putInt("CounterReport.CustomType", this.d);
        bundle2.putString("CounterReport.Wifi", this.f.b);
        bundle2.putByteArray("CounterReport.GeoLocation", y.b(this.f.a));
        bundle2.putInt("CounterReport.TRUNCATED", this.e);
        bundle2.putInt("CounterReport.ConnectionType", this.k);
        bundle2.putString("CounterReport.CellularConnectionType", this.l);
        if (this.f.c != null) {
            bundle2.putInt("CounterReport.CellId", this.f.c.intValue());
        }
        if (this.h != null) {
            bundle2.putString("CounterReport.Environment", this.h);
        }
        if (this.g != null) {
            bundle2.putString("CounterReport.UserInfo", this.g);
        }
        if (this.i != null) {
            bundle2.putString("CounterReport.PackageName", this.i);
        }
        if (this.j != null) {
            bundle2.putBundle("CounterReport.AppEnvironmentDiff", this.j);
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putBundle("CounterReport.Object", bundle2);
        return bundle;
    }

    public static h b(Bundle bundle) {
        int i = 0;
        Bundle bundle2 = bundle.containsKey("CounterReport.Object") ? bundle.getBundle("CounterReport.Object") : new Bundle();
        Object obj = bundle2.get("CounterReport.TRUNCATED");
        if (obj != null) {
            if (obj instanceof Boolean) {
                int i2;
                if (((Boolean) obj).booleanValue()) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                i = i2;
            } else if (obj instanceof Integer) {
                i = ((Integer) obj).intValue();
            }
        }
        h f = new h().a(bundle2.getInt("CounterReport.Type", com.yandex.metrica.impl.p.a.EVENT_TYPE_UNDEFINED.a())).b(bundle2.getInt("CounterReport.CustomType")).a(y.a(bundle2.getByteArray("CounterReport.GeoLocation"))).c(be.b(bundle2.getString("CounterReport.Value"), "")).a(bundle2.getString("CounterReport.UserInfo")).e(bundle2.getString("CounterReport.Environment")).d(bundle2.getString("CounterReport.Wifi")).a((Integer) bundle2.get("CounterReport.CellId")).b(bundle2.getString("CounterReport.Event")).f(bundle2.getString("CounterReport.PackageName"));
        f.j = bundle2.getBundle("CounterReport.AppEnvironmentDiff");
        return f.c(i).d(bundle2.getInt("CounterReport.ConnectionType")).g(bundle2.getString("CounterReport.CellularConnectionType"));
    }

    public static h a(h hVar, com.yandex.metrica.impl.p.a aVar) {
        h hVar2 = new h(hVar);
        hVar2.b(aVar.b());
        hVar2.a(aVar.a());
        return hVar2;
    }

    public static h a(j jVar, h hVar) {
        Context m = jVar.m();
        t a = new t(hVar.b()).a();
        try {
            if (jVar.z()) {
                a.b(m);
            }
            if (jVar.h().G()) {
                a.a(m);
            }
        } catch (Exception e) {
        }
        h hVar2 = new h(hVar);
        hVar2.a(com.yandex.metrica.impl.p.a.EVENT_TYPE_IDENTITY.a()).c(a.d());
        return hVar2;
    }

    public String toString() {
        return String.format(Locale.US, "[event: %s, type: %d, value: %s]", new Object[]{this.a, Integer.valueOf(this.c), this.b});
    }
}
