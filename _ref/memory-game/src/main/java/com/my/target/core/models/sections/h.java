package com.my.target.core.models.sections;

import com.facebook.internal.AnalyticsEvents;
import com.my.target.core.enums.a;
import com.my.target.core.models.banners.c;
import com.my.target.core.models.banners.i;
import com.my.target.core.models.d;
import com.my.target.core.models.j;
import java.util.ArrayList;

/* compiled from: VideoAdSection */
public final class h extends a<i> {
    private final j i = new j();
    private ArrayList<d> j = new ArrayList();
    private ArrayList<d> k = new ArrayList();

    public final j i() {
        return this.i;
    }

    public h(String str) {
        super(a.g, str, 0);
    }

    public final boolean a(c cVar) {
        if ((!AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO.equals(cVar.a()) && !"statistics".equals(cVar.a())) || b(cVar.getId()) != null) {
            return false;
        }
        this.f.add((i) cVar);
        this.d++;
        return true;
    }

    public final boolean a(int i, c cVar) {
        if ((!AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO.equals(cVar.a()) && !"statistics".equals(cVar.a())) || b(cVar.getId()) != null) {
            return false;
        }
        if (i > this.f.size()) {
            i = this.f.size();
        }
        this.f.add(i, (i) cVar);
        this.d++;
        return true;
    }

    public final boolean a(d dVar) {
        if (dVar.c() && !this.j.contains(dVar)) {
            this.j.add(dVar);
            return true;
        } else if (!dVar.d() || this.k.contains(dVar)) {
            return false;
        } else {
            this.k.add(dVar);
            return true;
        }
    }
}
