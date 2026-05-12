package com.my.target.core.async.commands;

import android.content.Context;
import com.my.target.Tracer;
import com.my.target.core.a;
import com.my.target.core.models.c;
import com.my.target.core.parsers.b;

/* compiled from: LoadAppwallAdCommand */
public final class d extends c {
    public d(com.my.target.core.models.d dVar, a aVar, Context context) {
        super(dVar, aVar, context);
    }

    protected final void c() {
        if (this.f.a() > 0) {
            String a;
            Context context = this.b;
            Tracer.d("LoadAppwallAdCommand allowed to check cache record");
            com.my.target.core.utils.d a2 = com.my.target.core.utils.d.a(context);
            if (a2 != null) {
                a = a2.a(Integer.toString(this.f.d()), this.f.a());
            } else {
                a = null;
            }
            if (a != null) {
                Tracer.d("Cache value retrieved successfully");
                this.c = new c(this.f.a());
                ((c) this.c).a(this.e.a());
                b.a(a, (c) this.c, this.f, this.e, this.b);
                ((c) this.c).i();
                return;
            }
        }
        super.c();
    }
}
