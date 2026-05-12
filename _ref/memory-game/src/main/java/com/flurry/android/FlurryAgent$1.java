package com.flurry.android;

import com.flurry.sdk.jy;
import com.flurry.sdk.kg;
import com.flurry.sdk.kh;
import com.flurry.sdk.ll;

class FlurryAgent$1 implements kh<ll> {
    FlurryAgent$1() {
    }

    public final /* synthetic */ void a(kg kgVar) {
        final ll llVar = (ll) kgVar;
        jy.a().a(new Runnable(this) {
            final /* synthetic */ FlurryAgent$1 b;

            public final void run() {
                switch (FlurryAgent$2.a[llVar.c - 1]) {
                    case 1:
                        if (FlurryAgent.a() != null) {
                            FlurryAgent.a().onSessionStarted();
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        });
    }
}
