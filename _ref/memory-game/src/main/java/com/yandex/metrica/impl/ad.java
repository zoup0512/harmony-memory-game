package com.yandex.metrica.impl;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

class ad implements UncaughtExceptionHandler {
    private final CopyOnWriteArrayList<i> a = new CopyOnWriteArrayList();
    private final UncaughtExceptionHandler b;

    public ad(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.b = uncaughtExceptionHandler;
    }

    public void a(i iVar) {
        this.a.add(iVar);
    }

    public void b(i iVar) {
        this.a.remove(iVar);
    }

    public void uncaughtException(Thread thread, Throwable ex) {
        try {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                ((i) it.next()).a(ex);
            }
        } finally {
            if (this.b != null) {
                this.b.uncaughtException(thread, ex);
            }
        }
    }
}
