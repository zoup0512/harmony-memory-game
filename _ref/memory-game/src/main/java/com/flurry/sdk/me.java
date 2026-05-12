package com.flurry.sdk;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public final class me {
    private static me c;
    final UncaughtExceptionHandler a = Thread.getDefaultUncaughtExceptionHandler();
    final Map<UncaughtExceptionHandler, Void> b = new WeakHashMap();

    final class a implements UncaughtExceptionHandler {
        final /* synthetic */ me a;

        private a(me meVar) {
            this.a = meVar;
        }

        public final void uncaughtException(Thread thread, Throwable th) {
            for (UncaughtExceptionHandler uncaughtException : this.a.b()) {
                try {
                    uncaughtException.uncaughtException(thread, th);
                } catch (Throwable th2) {
                }
            }
            me meVar = this.a;
            if (meVar.a != null) {
                try {
                    meVar.a.uncaughtException(thread, th);
                } catch (Throwable th3) {
                }
            }
        }
    }

    private me() {
        Thread.setDefaultUncaughtExceptionHandler(new a());
    }

    public static synchronized me a() {
        me meVar;
        synchronized (me.class) {
            if (c == null) {
                c = new me();
            }
            meVar = c;
        }
        return meVar;
    }

    final Set<UncaughtExceptionHandler> b() {
        Set<UncaughtExceptionHandler> keySet;
        synchronized (this.b) {
            keySet = this.b.keySet();
        }
        return keySet;
    }
}
