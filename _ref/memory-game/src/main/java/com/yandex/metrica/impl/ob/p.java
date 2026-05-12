package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.p.a;
import java.util.LinkedList;

public class p extends q<v> {
    private final ao a;
    private final an b;
    private final am c;
    private final ac d;
    private final ak e;
    private final z f;

    public p(j jVar) {
        this.a = new ao(jVar);
        this.b = new an(jVar);
        this.c = new am(jVar);
        this.d = new ac(jVar);
        this.e = new ak(jVar);
        this.f = new z(jVar);
    }

    n<v> a(int i) {
        LinkedList linkedList = new LinkedList();
        switch (a.a(i)) {
            case EVENT_TYPE_ACTIVITY_START_DEPRECATED:
                linkedList.add(this.e);
                break;
            case EVENT_TYPE_START:
                linkedList.add(this.e);
                linkedList.add(this.d);
                break;
            case EVENT_TYPE_SESSION_START_MANUALLY:
                linkedList.add(this.a);
                linkedList.add(this.b);
                linkedList.add(this.c);
                break;
            case EVENT_TYPE_INIT:
            case EVENT_TYPE_INIT_BACKGROUND:
                linkedList.add(this.d);
                break;
            case EVENT_TYPE_ACTIVITY_END:
                linkedList.add(this.f);
                break;
        }
        return new m(linkedList);
    }
}
