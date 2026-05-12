package com.flurry.sdk;

import android.content.Context;
import java.io.File;
import java.util.List;
import java.util.Map;

public class hs {
    private static final String b = hs.class.getSimpleName();
    boolean a;
    private final ht c;
    private final File d;
    private String e;

    public hs() {
        this(jy.a().a);
    }

    public hs(Context context) {
        this.c = new ht();
        this.d = context.getFileStreamPath(".flurryinstallreceiver.");
        km.a(3, b, "Referrer file name if it exists:  " + this.d);
    }

    public final synchronized Map<String, List<String>> a() {
        c();
        return ht.a(this.e);
    }

    private void c() {
        if (!this.a) {
            this.a = true;
            km.a(4, b, "Loading referrer info from file: " + this.d.getAbsolutePath());
            String c = lx.c(this.d);
            km.a(b, "Referrer file contents: " + c);
            b(c);
        }
    }

    private void b(String str) {
        if (str != null) {
            this.e = str;
        }
    }

    public final synchronized String b() {
        c();
        return this.e;
    }

    public final synchronized void a(String str) {
        this.a = true;
        b(str);
        lx.a(this.d, this.e);
    }
}
