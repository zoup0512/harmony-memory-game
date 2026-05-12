package com.yandex.metrica.impl;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.yandex.metrica.impl.ob.bi;
import com.yandex.metrica.impl.ob.cb;
import com.yandex.metrica.impl.ob.cd;

public class f extends ae {
    private final bi a;

    static class a implements a {
        private bi a;

        public a(bi biVar) {
            this.a = biVar;
        }

        public void a(Context context) {
            cb cbVar = new cb(context);
            if (!bg.a(cbVar.c())) {
                if (this.a.a(null) == null || this.a.b(null) == null) {
                    String b = cbVar.b(null);
                    if (a(b, this.a.b(null))) {
                        this.a.h(b);
                    }
                    b = cbVar.a();
                    if (a(b, this.a.a())) {
                        this.a.m(b);
                    }
                    b = cbVar.a(null);
                    if (a(b, this.a.a(null))) {
                        this.a.g(b);
                    }
                    b = cbVar.c(null);
                    if (a(b, this.a.c(null))) {
                        this.a.i(b);
                    }
                    b = cbVar.d(null);
                    if (a(b, this.a.d(null))) {
                        this.a.k(b);
                    }
                    b = cbVar.e(null);
                    if (a(b, this.a.e(null))) {
                        this.a.j(b);
                    }
                    b = cbVar.f(null);
                    if (a(b, this.a.f(null))) {
                        this.a.l(b);
                    }
                    long a = cbVar.a(-1);
                    if (a(a, this.a.a(-1), -1)) {
                        this.a.d(a);
                    }
                    a = cbVar.b(-1);
                    if (a(a, this.a.b(-1), -1)) {
                        this.a.e(a);
                    }
                    this.a.h();
                    cbVar.b().k();
                }
            }
        }

        private static boolean a(long j, long j2, long j3) {
            return j != j3 && j2 == j3;
        }

        private static boolean a(String str, String str2) {
            return !TextUtils.isEmpty(str) && TextUtils.isEmpty(str2);
        }
    }

    public f(bi biVar) {
        this.a = biVar;
    }

    SparseArray<a> a() {
        return new SparseArray<a>(this) {
            final /* synthetic */ f a;

            {
                this.a = r4;
                put(46, new a(this.a.a));
            }
        };
    }

    protected int a(cd cdVar) {
        return (int) this.a.c(-1);
    }

    protected void a(cd cdVar, int i) {
        this.a.f((long) i);
        cdVar.c().k();
    }
}
