package com.my.target.core.factories;

import com.facebook.internal.AnalyticsEvents;
import com.my.target.core.models.banners.b;
import com.my.target.core.models.banners.c;
import com.my.target.core.models.banners.d;
import com.my.target.core.models.banners.e;
import com.my.target.core.models.banners.f;
import com.my.target.core.models.banners.g;
import com.my.target.core.models.banners.h;
import com.my.target.core.models.banners.i;

/* compiled from: BannersFactory */
public final class a {
    public static c a(String str, String str2, String str3) {
        if (com.my.target.core.enums.a.e.equals(str3)) {
            if ("promo".equals(str2) || "banner".equals(str2) || "teaser".equals(str2)) {
                return new f(str, str2);
            }
        } else if (com.my.target.core.enums.a.d.equals(str3)) {
            if ("fullscreen".equals(str2) || "banner".equals(str2)) {
                return new d(str, "banner");
            }
            if ("promo".equals(str2)) {
                return new e(str, str2);
            }
        } else if (com.my.target.core.enums.a.a.equals(str3)) {
            if ("banner".equals(str2) || "teaser".equals(str2)) {
                return new g(str, str2);
            }
        } else if (com.my.target.core.enums.a.b.equals(str3) || com.my.target.core.enums.a.c.equals(str3)) {
            if ("showcase".equals(str2) || "teaser".equals(str2)) {
                return new b(str, str2);
            }
        } else if (com.my.target.core.enums.a.g.equals(str3)) {
            if (AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO.equals(str2)) {
                return new i(str, str2);
            }
            if ("statistics".equals(str2)) {
                return new h(str, str2);
            }
        }
        return null;
    }
}
