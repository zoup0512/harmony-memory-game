package com.my.target.core.async.commands;

import android.content.Context;
import com.my.target.Tracer;
import com.my.target.core.utils.d;

/* compiled from: StoreAdDataCommand */
public final class i extends a<Boolean> {
    private int e;
    private long f;
    private String g;

    public final /* bridge */ /* synthetic */ String a() {
        return super.a();
    }

    public i(long j, int i, String str, Context context) {
        super(context);
        this.e = i;
        this.g = str;
        this.f = j;
    }

    protected final void c() {
        d a = d.a(this.b);
        if (a == null) {
            this.c = Boolean.valueOf(false);
            Tracer.d("StoreAdDataCommand can't open file cache");
            return;
        }
        this.c = Boolean.valueOf(a.a(Integer.toString(this.e), this.g, this.f));
        Tracer.d("StoreAdDataCommand complete with status: " + this.c);
    }
}
