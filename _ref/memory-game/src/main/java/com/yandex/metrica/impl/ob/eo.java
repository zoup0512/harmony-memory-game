package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.ob.en.a;
import com.yandex.metrica.impl.ob.en.b;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class eo<T> implements a, b<T>, Future<T> {
    private boolean a = false;
    private T b;
    private ek c;

    public static <E> eo<E> a() {
        return new eo();
    }

    private eo() {
    }

    public synchronized boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    public T get() throws InterruptedException, ExecutionException {
        try {
            return a(null);
        } catch (TimeoutException e) {
            throw new AssertionError(e);
        }
    }

    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return a(Long.valueOf(TimeUnit.MILLISECONDS.convert(timeout, unit)));
    }

    private synchronized T a(Long l) throws InterruptedException, ExecutionException, TimeoutException {
        T t;
        if (this.c != null) {
            throw new ExecutionException(this.c);
        } else if (this.a) {
            t = this.b;
        } else {
            if (l == null) {
                wait(0);
            } else if (l.longValue() > 0) {
                wait(l.longValue());
            }
            if (this.c != null) {
                throw new ExecutionException(this.c);
            } else if (this.a) {
                t = this.b;
            } else {
                throw new TimeoutException();
            }
        }
        return t;
    }

    public boolean isCancelled() {
        return false;
    }

    public synchronized boolean isDone() {
        boolean z;
        z = this.a || this.c != null || isCancelled();
        return z;
    }

    public synchronized void a(T t) {
        this.a = true;
        this.b = t;
        notifyAll();
    }

    public synchronized void a(ek ekVar) {
        this.c = ekVar;
        notifyAll();
    }
}
