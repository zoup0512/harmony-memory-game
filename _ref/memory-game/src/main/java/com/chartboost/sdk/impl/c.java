package com.chartboost.sdk.impl;

import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Model.a;
import com.chartboost.sdk.Model.a.b;
import com.chartboost.sdk.f;
import com.facebook.appevents.AppEventsConstants;

public class c extends b {
    protected boolean b(a aVar, e.a aVar2) {
        return true;
    }

    protected a a(String str, boolean z) {
        return new a(this, a.a.REWARDED_VIDEO, z, str, false, f());
    }

    protected ad e(a aVar) {
        ad ahVar;
        if (com.chartboost.sdk.c.G().booleanValue()) {
            aVar.a = b.WEB;
            e.a b = f.n().b();
            ahVar = new ah(com.chartboost.sdk.c.z());
            ahVar.a("cache_assets", b, ah.a.AD);
            ahVar.a(w.b.HIGH);
            ahVar.a(com.chartboost.sdk.Model.b.f);
            ahVar.a("location", aVar.e, ah.a.AD);
            if (aVar.l) {
                ahVar.a("cache", Boolean.valueOf(true), ah.a.AD);
                ahVar.b(true);
            } else {
                ahVar.a("cache", Boolean.valueOf(false), ah.a.AD);
            }
        } else {
            aVar.a = b.NATIVE;
            Object h = h();
            ahVar = new ad(com.chartboost.sdk.c.z());
            ahVar.a("local-videos", h);
            ahVar.a(w.b.HIGH);
            ahVar.a("location", aVar.e);
            if (aVar.l) {
                ahVar.a("cache", AppEventsConstants.EVENT_PARAM_VALUE_YES);
                ahVar.b(true);
            }
            ahVar.a(com.chartboost.sdk.Model.b.b);
        }
        return ahVar;
    }

    public ad l(a aVar) {
        ad l = super.l(aVar);
        l.a("/reward/show");
        return l;
    }

    protected void i(a aVar) {
    }

    protected void h(final a aVar) {
        final e.a a = aVar.w().a("ux").a("pre-popup");
        if (a.c() && a.a("title").d() && a.a("text").d() && a.a("confirm").d() && a.a("cancel").d() && d() != null) {
            this.a.post(new Runnable(this) {
                final /* synthetic */ c c;

                public void run() {
                    ba.a aVar = new ba.a();
                    aVar.a(a.e("title")).b(a.e("text")).d(a.e("confirm")).c(a.e("cancel"));
                    aVar.a(this.c.d(), new ba.b(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void a(ba baVar) {
                            this.a.c.a(aVar, CBImpressionError.USER_CANCELLATION);
                        }

                        public void a(ba baVar, int i) {
                            if (i == 1) {
                                super.h(aVar);
                            } else {
                                this.a.c.a(aVar, CBImpressionError.USER_CANCELLATION);
                            }
                        }
                    });
                }
            });
        } else {
            super.h(aVar);
        }
    }

    protected void r(a aVar) {
        final e.a a = aVar.w().a("ux").a("post-popup");
        if (a.c() && a.a("title").d() && a.a("text").d() && a.a("confirm").d() && d() != null && aVar.r) {
            this.a.post(new Runnable(this) {
                final /* synthetic */ c b;

                public void run() {
                    ba.a aVar = new ba.a();
                    aVar.a(a.e("title")).b(a.e("text")).c(a.e("confirm"));
                    aVar.a(this.b.d(), new ba.b(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void a(ba baVar, int i) {
                            CBLogging.c("CBRewardedVideo", "post-popup dismissed");
                        }
                    });
                }
            });
        }
    }

    public a b() {
        return new a(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(a aVar) {
                if (com.chartboost.sdk.c.h() != null) {
                    com.chartboost.sdk.c.h().didClickRewardedVideo(aVar.e);
                }
            }

            public void b(a aVar) {
                if (com.chartboost.sdk.c.h() != null) {
                    com.chartboost.sdk.c.h().didCloseRewardedVideo(aVar.e);
                }
            }

            public void c(a aVar) {
                this.a.r(aVar);
                if (com.chartboost.sdk.c.h() != null) {
                    com.chartboost.sdk.c.h().didDismissRewardedVideo(aVar.e);
                }
            }

            public void d(a aVar) {
                if (com.chartboost.sdk.c.h() != null) {
                    com.chartboost.sdk.c.h().didCacheRewardedVideo(aVar.e);
                }
            }

            public void a(a aVar, CBImpressionError cBImpressionError) {
                if (com.chartboost.sdk.c.h() != null) {
                    com.chartboost.sdk.c.h().didFailToLoadRewardedVideo(aVar.e, cBImpressionError);
                }
            }

            public void e(a aVar) {
                if (com.chartboost.sdk.c.h() != null) {
                    com.chartboost.sdk.c.h().didDisplayRewardedVideo(aVar.e);
                }
            }

            public boolean f(a aVar) {
                if (com.chartboost.sdk.c.h() != null) {
                    return com.chartboost.sdk.c.h().shouldDisplayRewardedVideo(aVar.e);
                }
                return true;
            }

            public boolean g(a aVar) {
                return true;
            }

            public boolean h(a aVar) {
                if (com.chartboost.sdk.c.h() != null) {
                    return com.chartboost.sdk.c.u();
                }
                return true;
            }
        };
    }

    public String e() {
        return String.format("%s-%s", new Object[]{"rewarded-video", com.chartboost.sdk.c.W()});
    }
}
