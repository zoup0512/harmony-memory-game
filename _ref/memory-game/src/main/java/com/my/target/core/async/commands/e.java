package com.my.target.core.async.commands;

import android.content.Context;
import com.my.target.core.a;
import com.my.target.core.models.c;
import com.my.target.core.models.d;
import com.my.target.core.models.sections.f;
import com.my.target.core.parsers.b;
import java.util.List;

/* compiled from: LoadInstreamAdCommand */
public final class e extends c {
    public e(d dVar, a aVar, Context context) {
        super(dVar, aVar, context);
    }

    protected final void c() {
        super.c();
        if (this.c != null) {
            a(this.e.b(), (c) this.c, new a());
        }
    }

    private void a(List<d> list, c cVar, a aVar) {
        for (d dVar : list) {
            d j;
            do {
                aVar.a(j.a());
                if (!aVar.a) {
                    break;
                }
                int b;
                com.my.target.core.async.a.a(j.h(), "serviceRequested", this.b);
                f c = cVar.c(com.my.target.core.enums.a.f);
                int i = 0;
                if (c != null) {
                    i = c.b();
                }
                b.a(aVar.c, cVar, this.f, j, this.b);
                a(j.b(), cVar, aVar);
                if (c == null) {
                    c = cVar.c(com.my.target.core.enums.a.f);
                }
                if (c != null) {
                    b = c.b();
                } else {
                    b = i;
                }
                if (i != b) {
                    break;
                }
                com.my.target.core.async.a.a(j.h(), "serviceAnswerEmpty", this.b);
                j = j.j();
            } while (j != null);
        }
    }
}
