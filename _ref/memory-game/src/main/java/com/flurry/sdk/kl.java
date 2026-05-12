package com.flurry.sdk;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;

public class kl<T extends mb> {
    private static final String a = kl.class.getSimpleName();
    private final kd<Object, T> b = new kd();
    private final HashMap<T, Object> c = new HashMap();
    private final HashMap<T, Future<?>> d = new HashMap();
    private final ThreadPoolExecutor e;

    public kl(String str, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue) {
        this.e = new ThreadPoolExecutor(this, timeUnit, blockingQueue) {
            final /* synthetic */ kl a;

            protected final void beforeExecute(Thread thread, Runnable runnable) {
                super.beforeExecute(thread, runnable);
                final mb a = kl.a(runnable);
                if (a != null) {
                    new ma(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public final void a() {
                        }
                    }.run();
                }
            }

            protected final void afterExecute(Runnable runnable, Throwable th) {
                super.afterExecute(runnable, th);
                final mb a = kl.a(runnable);
                if (a != null) {
                    synchronized (this.a.d) {
                        this.a.d.remove(a);
                    }
                    this.a.a(a);
                    new ma(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public final void a() {
                        }
                    }.run();
                }
            }

            protected final <V> RunnableFuture<V> newTaskFor(Runnable runnable, V v) {
                RunnableFuture kkVar = new kk(runnable, v);
                synchronized (this.a.d) {
                    this.a.d.put((mb) runnable, kkVar);
                }
                return kkVar;
            }

            protected final <V> RunnableFuture<V> newTaskFor(Callable<V> callable) {
                throw new UnsupportedOperationException("Callable not supported");
            }
        };
        this.e.setRejectedExecutionHandler(new DiscardPolicy(this) {
            final /* synthetic */ kl a;

            {
                this.a = r1;
            }

            public final void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                super.rejectedExecution(runnable, threadPoolExecutor);
                final mb a = kl.a(runnable);
                if (a != null) {
                    synchronized (this.a.d) {
                        this.a.d.remove(a);
                    }
                    this.a.a(a);
                    new ma(this) {
                        final /* synthetic */ AnonymousClass2 b;

                        public final void a() {
                        }
                    }.run();
                }
            }
        });
        this.e.setThreadFactory(new lr(str));
    }

    private synchronized void a(T t) {
        b(this.c.get(t), t);
    }

    private synchronized void b(Object obj, T t) {
        this.b.b(obj, t);
        this.c.remove(t);
    }

    public final synchronized void a(Object obj, T t) {
        if (!(obj == null || t == null)) {
            c(obj, t);
            this.e.submit(t);
        }
    }

    private synchronized void c(Object obj, T t) {
        this.b.a(obj, (Object) t);
        this.c.put(t, obj);
    }

    public final synchronized void a(Object obj) {
        if (obj != null) {
            Collection<mb> hashSet = new HashSet();
            hashSet.addAll(this.b.a(obj));
            for (mb b : hashSet) {
                b(b);
            }
        }
    }

    private synchronized void b(final T t) {
        if (t != null) {
            Future future;
            synchronized (this.d) {
                future = (Future) this.d.remove(t);
            }
            a((mb) t);
            if (future != null) {
                future.cancel(true);
            }
            new ma(this) {
                final /* synthetic */ kl b;

                public final void a() {
                    t.h();
                }
            }.run();
        }
    }

    public final synchronized long b(Object obj) {
        long j;
        if (obj == null) {
            j = 0;
        } else {
            j = (long) this.b.a(obj).size();
        }
        return j;
    }

    static /* synthetic */ mb a(Runnable runnable) {
        if (runnable instanceof kk) {
            return (mb) ((kk) runnable).a();
        }
        if (runnable instanceof mb) {
            return (mb) runnable;
        }
        km.a(6, a, "Unknown runnable class: " + runnable.getClass().getName());
        return null;
    }
}
