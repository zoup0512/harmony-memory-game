package com.flurry.sdk;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class kf<T> {
    private static final String a = kf.class.getSimpleName();
    private final File b;
    private final lg<T> c;

    public kf(File file, String str, int i, lj<T> ljVar) {
        this.b = file;
        this.c = new le(new li(str, i, ljVar));
    }

    public final T a() {
        Closeable fileInputStream;
        Throwable e;
        Throwable th;
        T t = null;
        if (this.b != null) {
            if (this.b.exists()) {
                Object obj = null;
                try {
                    fileInputStream = new FileInputStream(this.b);
                    try {
                        t = this.c.a(fileInputStream);
                        ly.a(fileInputStream);
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            km.a(3, a, "Error reading data file:" + this.b.getName(), e);
                            obj = 1;
                            ly.a(fileInputStream);
                            if (obj != null) {
                                km.a(3, a, "Deleting data file:" + this.b.getName());
                                this.b.delete();
                            }
                            return t;
                        } catch (Throwable th2) {
                            th = th2;
                            ly.a(fileInputStream);
                            throw th;
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    fileInputStream = t;
                    km.a(3, a, "Error reading data file:" + this.b.getName(), e);
                    obj = 1;
                    ly.a(fileInputStream);
                    if (obj != null) {
                        km.a(3, a, "Deleting data file:" + this.b.getName());
                        this.b.delete();
                    }
                    return t;
                } catch (Throwable e4) {
                    fileInputStream = t;
                    th = e4;
                    ly.a(fileInputStream);
                    throw th;
                }
                if (obj != null) {
                    km.a(3, a, "Deleting data file:" + this.b.getName());
                    this.b.delete();
                }
            } else {
                km.a(5, a, "No data to read for file:" + this.b.getName());
            }
        }
        return t;
    }

    public final void a(T t) {
        Throwable e;
        int i;
        Object obj = null;
        Closeable closeable = null;
        if (t == null) {
            km.a(3, a, "No data to write for file:" + this.b.getName());
            obj = 1;
        } else {
            try {
                if (lx.a(this.b)) {
                    Closeable fileOutputStream = new FileOutputStream(this.b);
                    try {
                        this.c.a(fileOutputStream, t);
                        ly.a(fileOutputStream);
                    } catch (Exception e2) {
                        e = e2;
                        closeable = fileOutputStream;
                        try {
                            km.a(3, a, "Error writing data file:" + this.b.getName(), e);
                            ly.a(closeable);
                            i = 1;
                            if (obj == null) {
                                km.a(3, a, "Deleting data file:" + this.b.getName());
                                this.b.delete();
                            }
                        } catch (Throwable th) {
                            e = th;
                            ly.a(closeable);
                            throw e;
                        }
                    } catch (Throwable th2) {
                        e = th2;
                        closeable = fileOutputStream;
                        ly.a(closeable);
                        throw e;
                    }
                }
                throw new IOException("Cannot create parent directory!");
            } catch (Exception e3) {
                e = e3;
                km.a(3, a, "Error writing data file:" + this.b.getName(), e);
                ly.a(closeable);
                i = 1;
                if (obj == null) {
                    km.a(3, a, "Deleting data file:" + this.b.getName());
                    this.b.delete();
                }
            }
        }
        if (obj == null) {
            km.a(3, a, "Deleting data file:" + this.b.getName());
            this.b.delete();
        }
    }

    public final boolean b() {
        if (this.b == null) {
            return false;
        }
        return this.b.delete();
    }
}
