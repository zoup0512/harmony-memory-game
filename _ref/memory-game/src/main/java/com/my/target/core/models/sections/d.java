package com.my.target.core.models.sections;

import com.my.target.core.enums.b;
import com.my.target.core.models.banners.a;
import com.my.target.core.models.banners.c;
import java.util.ArrayList;
import java.util.List;

/* compiled from: InstreamAdSection */
public final class d extends a<a> {
    private List<h> i = new ArrayList();

    public d(String str, int i) {
        super(com.my.target.core.enums.a.f, str, i);
        for (String hVar : b.a()) {
            this.i.add(new h(hVar));
        }
    }

    public final int b() {
        int i = 0;
        for (h b : this.i) {
            i = b.b() + i;
        }
        return i;
    }

    public final boolean a(c cVar) {
        return false;
    }

    public final boolean a(int i, c cVar) {
        return false;
    }

    public final h c(String str) {
        for (h hVar : this.i) {
            if (str.equals(hVar.e())) {
                return hVar;
            }
        }
        return null;
    }
}
