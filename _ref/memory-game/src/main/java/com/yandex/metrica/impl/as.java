package com.yandex.metrica.impl;

import android.content.Context;
import android.os.Handler;
import com.yandex.metrica.b;
import com.yandex.metrica.e;
import com.yandex.metrica.impl.ob.cp;
import java.util.HashMap;
import java.util.Map;

class as {
    private Context a;
    private at b;
    private j c;
    private Handler d;
    private cp e;
    private Map<String, b> f;

    static class a {
        as a = new as();

        a() {
        }

        a a(Context context) {
            this.a.a = context;
            return this;
        }

        a a(at atVar) {
            this.a.b = atVar;
            return this;
        }

        a a(j jVar) {
            this.a.c = jVar;
            return this;
        }

        a a(Handler handler) {
            this.a.d = handler;
            return this;
        }

        a a(cp cpVar) {
            this.a.e = cpVar;
            return this;
        }

        as a() {
            return this.a;
        }
    }

    private as() {
        this.f = new HashMap();
    }

    z a(e eVar, boolean z) {
        if (this.f.containsKey(eVar.getApiKey())) {
            throw new IllegalArgumentException(String.format("Failed to activate AppMetrica with provided API Key. API Key %s has already been used by another reporter.", new Object[]{eVar.getApiKey()}));
        }
        z zVar = new z(this.a, eVar, this.b);
        a((b) zVar);
        zVar.a(eVar, z);
        zVar.a();
        this.b.a(zVar);
        this.f.put(eVar.getApiKey(), zVar);
        return zVar;
    }

    synchronized b a(String str) {
        b bVar;
        bVar = (b) this.f.get(str);
        if (bVar == null) {
            b aaVar = new aa(this.a, (String) ar.a.get(str), str, this.b);
            a(aaVar);
            aaVar.a();
            this.f.put(str, aaVar);
            bVar = aaVar;
        }
        return bVar;
    }

    private void a(b bVar) {
        bVar.a(new w(this.d, bVar));
        bVar.a(this.c);
        bVar.a(this.e);
    }
}
