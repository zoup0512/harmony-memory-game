package com.yandex.metrica.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.SparseArray;
import com.yandex.metrica.impl.ob.bc;
import com.yandex.metrica.impl.ob.bl;
import com.yandex.metrica.impl.ob.bm;
import com.yandex.metrica.impl.ob.br;
import com.yandex.metrica.impl.ob.bx;
import com.yandex.metrica.impl.ob.cc;
import com.yandex.metrica.impl.ob.cd;
import com.yandex.metrica.impl.ob.ce;
import com.yandex.metrica.impl.ob.cf;
import com.yandex.metrica.impl.ob.cg;
import com.yandex.metrica.impl.ob.ci;
import com.yandex.metrica.impl.ob.h;

public class az extends ae {
    private final bl a;

    private static class a implements a {
        private a() {
        }

        public void a(Context context) {
            Object a = new cf(context).a(null);
            if (!TextUtils.isEmpty(a) && TextUtils.isEmpty(br.a().c(context, a))) {
                cf.b(context);
            }
        }
    }

    private static class b implements a {
        private b() {
        }

        public void a(Context context) {
            cc ccVar = new cc(context, context.getPackageName());
            SharedPreferences a = ci.a(context, "_boundentrypreferences");
            String string = a.getString(cc.c.a(), null);
            long j = a.getLong(cc.d.a(), -1);
            if (string != null && j != -1) {
                ccVar.a(new com.yandex.metrica.impl.a.a(string, j)).k();
                a.edit().remove(cc.c.a()).remove(cc.d.a()).apply();
            }
        }
    }

    static class c implements a {
        c() {
        }

        public void a(Context context) {
            bl blVar = new bl(bc.a(context).b());
            cg cgVar = new cg(context);
            if (cgVar.a()) {
                blVar.a(true);
                cgVar.b();
            }
            ce ceVar = new ce(context, context.getPackageName());
            long a = ceVar.a(0);
            if (a != 0) {
                blVar.a(a);
            }
            ceVar.a();
            cc ccVar = new cc(context, h.a(context.getPackageName()).toString());
            com.yandex.metrica.CounterConfiguration.a b = ccVar.b();
            if (b != com.yandex.metrica.CounterConfiguration.a.UNDEFINED) {
                blVar.a(b);
            }
            String b2 = ccVar.b(null);
            if (!TextUtils.isEmpty(b2)) {
                blVar.b(b2);
            }
            ccVar.e().c().k();
            blVar.h();
            bx bxVar = new bx(context);
            bxVar.a();
            bxVar.b();
            br.a().c(context, new bm(bc.a(context).c(), context.getPackageName()).a(""));
        }
    }

    public az(Context context) {
        this.a = new bl(bc.a(context).b());
    }

    SparseArray<a> a() {
        return new SparseArray<a>() {
            {
                put(29, new a());
                put(39, new b());
                put(46, new c());
            }
        };
    }

    protected int a(cd cdVar) {
        int a = cdVar.a();
        if (a == -1) {
            return this.a.a(-1);
        }
        return a;
    }

    protected void a(cd cdVar, int i) {
        this.a.b(i).h();
        cdVar.b().k();
    }
}
