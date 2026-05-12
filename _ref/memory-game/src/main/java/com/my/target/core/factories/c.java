package com.my.target.core.factories;

import android.content.Context;
import android.view.ViewGroup;
import com.facebook.internal.AnalyticsEvents;
import com.my.target.ads.MyTargetVideoView;
import com.my.target.ads.MyTargetView;
import com.my.target.core.engines.b;
import com.my.target.core.engines.d;
import com.my.target.core.facades.e;
import com.my.target.core.facades.f;
import com.my.target.core.facades.g;
import com.my.target.core.facades.h;
import com.my.target.nativeads.NativeAppwallAd;

/* compiled from: EnginesFactory */
public final class c {
    public static b a(g gVar, ViewGroup viewGroup, Context context) {
        if (gVar instanceof NativeAppwallAd) {
            return new com.my.target.core.engines.c((NativeAppwallAd) gVar, viewGroup, context);
        }
        if ((gVar instanceof h) && (viewGroup instanceof MyTargetView)) {
            if (AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE.equals(((h) gVar).i().a())) {
                return new com.my.target.core.engines.h((MyTargetView) viewGroup, context);
            }
            return new com.my.target.core.engines.g((MyTargetView) viewGroup, context);
        } else if (gVar instanceof e) {
            return new d((e) gVar, viewGroup, context);
        } else {
            if (gVar instanceof f) {
                return new com.my.target.core.engines.e((f) gVar, viewGroup, context);
            }
            if (gVar instanceof com.my.target.core.facades.d) {
                return new com.my.target.core.engines.f((com.my.target.core.facades.d) gVar, (MyTargetVideoView) viewGroup, context);
            }
            return null;
        }
    }
}
