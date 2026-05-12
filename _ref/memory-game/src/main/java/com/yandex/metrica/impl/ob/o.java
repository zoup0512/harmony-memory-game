package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.p;
import com.yandex.metrica.impl.p.a;
import java.util.LinkedList;
import java.util.List;

public class o extends q<v> {
    private final x a;
    private final u b;
    private final aa c;
    private final ae d;
    private final ag e;
    private final aj f;
    private final ao g;
    private final ah h;
    private final af i;
    private final ai j;
    private final ab k;
    private final ad l;
    private final t m;
    private final s n;
    private final w o;
    private final y p;

    public o(j jVar) {
        this.a = new x(jVar);
        this.b = new u(jVar);
        this.c = new aa(jVar);
        this.d = new ae(jVar);
        this.e = new ag(jVar);
        this.f = new aj(jVar);
        this.g = new ao(jVar);
        this.h = new ah(jVar);
        this.i = new af(jVar);
        this.j = new ai(jVar);
        this.k = new ab(jVar);
        this.l = new ad(jVar);
        this.m = new t(jVar);
        this.n = new s(jVar);
        this.o = new w(jVar);
        this.p = new y(jVar);
    }

    n<v> a(int i) {
        List linkedList = new LinkedList();
        a a = a.a(i);
        if (p.b(a)) {
            linkedList.add(this.j);
        }
        if (p.a(a)) {
            linkedList.add(this.e);
        }
        switch (a) {
            case EVENT_TYPE_ACTIVATION:
                linkedList.add(this.o);
                linkedList.add(this.f);
                break;
            case EVENT_TYPE_START:
                linkedList.add(this.a);
                break;
            case EVENT_TYPE_REGULAR:
                linkedList.add(this.a);
                linkedList.add(this.d);
                break;
            case EVENT_TYPE_EXCEPTION_USER:
            case EVENT_TYPE_REFERRER_DEPRECATED:
            case EVENT_TYPE_STATBOX:
            case EVENT_TYPE_CUSTOM_EVENT:
                linkedList.add(this.d);
                break;
            case EVENT_TYPE_UPDATE_COLLECT_INSTALLED_APPS:
                linkedList.add(this.f);
                linkedList.add(this.a);
                break;
            case EVENT_TYPE_PURGE_BUFFER:
                linkedList.add(this.c);
                break;
            case EVENT_TYPE_NATIVE_CRASH:
                linkedList.add(this.p);
                break;
            case EVENT_TYPE_EXCEPTION_UNHANDLED_DEPRECATED:
            case EVENT_TYPE_EXCEPTION_UNHANDLED:
                linkedList.add(this.c);
                linkedList.add(this.d);
                linkedList.add(this.b);
                linkedList.add(this.g);
                break;
            case EVENT_TYPE_IDENTITY:
                linkedList.add(this.f);
                linkedList.add(this.a);
                break;
            case EVENT_TYPE_SET_USER_INFO:
                linkedList.add(this.h);
                break;
            case EVENT_TYPE_REPORT_USER_INFO:
                linkedList.add(this.i);
                break;
            case EVENT_TYPE_REFERRER_RECEIVED:
                linkedList.add(this.l);
                break;
            case EVENT_TYPE_APP_ENVIRONMENT_UPDATED:
                linkedList.add(this.m);
                break;
            case EVENT_TYPE_APP_ENVIRONMENT_CLEARED:
                linkedList.add(this.n);
                break;
        }
        if (p.c(a)) {
            linkedList.add(this.k);
        }
        return new m(linkedList);
    }
}
