package com.yandex.metrica.impl.ob;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.lang.Thread.State;

public class el {
    private ej a;
    private HandlerThread b;
    private b c;
    private volatile Handler d;

    private static class a implements Runnable {
        private com.yandex.metrica.impl.ob.en.a a;
        private ek b;

        private a(com.yandex.metrica.impl.ob.en.a aVar, ek ekVar) {
            this.a = aVar;
            this.b = ekVar;
        }

        public void run() {
            if (this.a != null) {
                this.a.a(this.b);
            }
        }
    }

    private class b extends Handler {
        final /* synthetic */ el a;

        private b(el elVar, Looper looper) {
            this.a = elVar;
            super(looper);
        }

        public void handleMessage(Message msg) {
            en enVar = (en) msg.obj;
            com.yandex.metrica.impl.ob.en.b e = enVar.e();
            try {
                this.a.d.post(new c(e, enVar.b(this.a.a.a(enVar))));
            } catch (ek e2) {
                this.a.d.post(new a(enVar.f(), e2));
            }
        }

        public <T> void a(en<T> enVar) {
            Message message = new Message();
            message.obj = enVar;
            sendMessage(message);
        }
    }

    private static class c<T> implements Runnable {
        private com.yandex.metrica.impl.ob.en.b<T> a;
        private T b;

        private c(com.yandex.metrica.impl.ob.en.b bVar, T t) {
            this.a = bVar;
            this.b = t;
        }

        public void run() {
            if (this.a != null) {
                this.a.a(this.b);
            }
        }
    }

    public el(ej ejVar) {
        this(ejVar, null);
    }

    public el(ej ejVar, Handler handler) {
        this.a = ejVar;
        this.b = new HandlerThread(el.class.getSimpleName() + '@' + Integer.toHexString(hashCode()));
        this.d = handler;
    }

    public <T> void a(en<T> enVar, com.yandex.metrica.impl.ob.en.b<T> bVar, com.yandex.metrica.impl.ob.en.a aVar) {
        a();
        enVar.a((com.yandex.metrica.impl.ob.en.b) bVar);
        enVar.a(aVar);
        this.c.a(enVar);
    }

    private synchronized void a() {
        if (this.b.getState() == State.NEW) {
            this.b.start();
            Looper looper = this.b.getLooper();
            this.c = new b(looper);
            if (this.d == null) {
                this.d = new Handler(looper);
            }
        }
    }
}
