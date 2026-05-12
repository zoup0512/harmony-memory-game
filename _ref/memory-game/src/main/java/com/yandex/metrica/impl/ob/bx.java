package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class bx {
    private Context a;

    public bx(Context context) {
        this.a = context;
    }

    public void a() {
        SharedPreferences a = ci.a(this.a, "_bidoptpreferences");
        if (a.getAll().size() > 0) {
            Object string = a.getString(cf.c.a(), null);
            cf cfVar = new cf(this.a);
            if (!TextUtils.isEmpty(string) && TextUtils.isEmpty(cfVar.a(null))) {
                cfVar.j(string).k();
                a.edit().remove(cf.c.a()).apply();
            }
            Map all = a.getAll();
            if (all.size() > 0) {
                for (String str : a(all, cf.d.a())) {
                    Object string2 = a.getString(new ch(cf.d.a(), str).b(), null);
                    cf cfVar2 = new cf(this.a, str);
                    if (!TextUtils.isEmpty(string2) && TextUtils.isEmpty(cfVar2.b(null))) {
                        cfVar2.i(string2).k();
                    }
                }
            }
            a.edit().clear().apply();
        }
    }

    private static List<String> a(Map<String, ?> map, String str) {
        List<String> arrayList = new ArrayList();
        for (String str2 : map.keySet()) {
            if (str2.startsWith(str)) {
                arrayList.add(str2.replace(str, ""));
            }
        }
        return arrayList;
    }

    public void b() {
        bd c = bc.a(this.a).c();
        SharedPreferences a = ci.a(this.a, "_startupserviceinfopreferences");
        bm bmVar = new bm(c, null);
        Object string = a.getString(cf.c.a(), null);
        if (!TextUtils.isEmpty(string) && TextUtils.isEmpty(bmVar.a(null))) {
            bmVar.k(string).h();
            a.edit().remove(cf.c.a()).apply();
        }
        bmVar = new bm(c, this.a.getPackageName());
        boolean z = a.getBoolean(cf.e.a(), false);
        if (z) {
            bmVar.b(z).h();
        }
        bmVar = new bm(c, null);
        string = a.getString(cf.f.a(), null);
        if (!TextUtils.isEmpty(string) && TextUtils.isEmpty(bmVar.h(null))) {
            bmVar.i(string).h();
        }
        a(c, this.a.getPackageName());
        for (String a2 : a(a.getAll(), cf.d.a())) {
            a(c, a2);
        }
    }

    private void a(bd bdVar, String str) {
        bm bmVar = new bm(bdVar, str);
        cf cfVar = new cf(this.a, str);
        Object b = cfVar.b(null);
        if (!TextUtils.isEmpty(b)) {
            bmVar.j(b);
        }
        b = cfVar.a();
        if (!TextUtils.isEmpty(b)) {
            bmVar.q(b);
        }
        b = cfVar.d(null);
        if (!TextUtils.isEmpty(b)) {
            bmVar.p(b);
        }
        b = cfVar.f(null);
        if (!TextUtils.isEmpty(b)) {
            bmVar.n(b);
        }
        b = cfVar.g(null);
        if (!TextUtils.isEmpty(b)) {
            bmVar.m(b);
        }
        b = cfVar.c(null);
        if (!TextUtils.isEmpty(b)) {
            bmVar.o(b);
        }
        long a = cfVar.a(-1);
        if (a != -1) {
            bmVar.b(a);
        }
        b = cfVar.e(null);
        if (!TextUtils.isEmpty(b)) {
            bmVar.l(b);
        }
        bmVar.h();
        cfVar.b();
    }
}
