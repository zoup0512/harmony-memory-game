package com.chartboost.sdk.impl;

import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Model.a;
import com.chartboost.sdk.c;
import com.chartboost.sdk.e;
import com.chartboost.sdk.impl.w.b;

public class s extends e {
    private static final String e = s.class.getSimpleName();
    protected int d;
    private a f = null;
    private boolean g;
    private boolean h;

    public void a(String str) {
        this.d = 0;
        h();
        super.a(str);
    }

    protected void a(a aVar, com.chartboost.sdk.Libraries.e.a aVar2) {
        if (!this.g && this.h) {
            this.h = false;
            this.d = aVar2.a("cells").p();
        }
        super.a(aVar, aVar2);
    }

    protected a a(String str, boolean z) {
        return new a(this, a.a.MORE_APPS, z, str, false, f());
    }

    protected ad e(a aVar) {
        ad adVar = new ad("/more/get");
        adVar.a(b.HIGH);
        adVar.a(com.chartboost.sdk.Model.b.d);
        return adVar;
    }

    protected ad l(a aVar) {
        ad adVar = new ad("/more/show");
        if (aVar.e != null) {
            adVar.a("location", aVar.e);
        }
        if (aVar.w().c("cells")) {
            adVar.a("cells", aVar.w().a("cells"));
        }
        return adVar;
    }

    protected a e(String str) {
        return this.f;
    }

    protected void f(String str) {
        this.f = null;
    }

    protected void p(a aVar) {
        this.f = aVar;
    }

    protected a b() {
        return new a(this) {
            final /* synthetic */ s a;

            {
                this.a = r1;
            }

            public void a(a aVar) {
                if (c.h() != null) {
                    c.h().didClickMoreApps(aVar.e);
                }
            }

            public void b(a aVar) {
                if (c.h() != null) {
                    c.h().didCloseMoreApps(aVar.e);
                }
            }

            public void c(a aVar) {
                if (c.h() != null) {
                    c.h().didDismissMoreApps(aVar.e);
                }
            }

            public void d(a aVar) {
                if (c.h() != null) {
                    c.h().didCacheMoreApps(aVar.e);
                }
            }

            public void a(a aVar, CBImpressionError cBImpressionError) {
                if (c.h() != null) {
                    c.h().didFailToLoadMoreApps(aVar.e, cBImpressionError);
                }
            }

            public void e(a aVar) {
                this.a.d = 0;
                this.a.h();
                if (c.h() != null) {
                    c.h().didDisplayMoreApps(aVar.e);
                }
            }

            public boolean f(a aVar) {
                if (c.h() != null) {
                    return c.h().shouldDisplayMoreApps(aVar.e);
                }
                return true;
            }

            public boolean g(a aVar) {
                if (c.h() != null) {
                    return c.h().shouldRequestMoreApps(aVar.e);
                }
                return true;
            }

            public boolean h(a aVar) {
                return true;
            }
        };
    }

    protected void h() {
    }

    public String e() {
        return "more-apps";
    }
}
