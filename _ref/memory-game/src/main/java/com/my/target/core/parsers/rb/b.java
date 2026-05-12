package com.my.target.core.parsers.rb;

import android.os.Build.VERSION;
import android.text.TextUtils;
import com.amazon.device.ads.DeviceInfo;
import com.facebook.internal.AnalyticsEvents;
import com.mopub.common.Constants;
import com.mopub.mobileads.VastIconXmlManager;
import com.my.target.ads.MyTargetVideoView;
import com.my.target.core.models.banners.c;
import com.my.target.core.models.banners.d;
import com.my.target.core.models.banners.g;
import com.my.target.core.models.banners.i;
import com.my.target.core.models.e;
import com.my.target.core.models.j;
import com.my.target.core.models.sections.f;
import com.my.target.core.models.sections.h;
import com.my.target.core.parsers.a.a;
import com.my.target.core.utils.l;
import com.my.target.nativeads.banners.NavigationType;
import com.my.target.nativeads.models.ImageData;
import com.my.target.nativeads.models.VideoData;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: RBBannerParser */
public class b {
    public static c a(JSONObject jSONObject, f fVar, ArrayList<String> arrayList, a aVar) {
        aVar.d = "Parsing banner";
        aVar.e = "Banner_id = \"" + jSONObject.optString("bannerID") + "\"";
        aVar.c = b.class.getName();
        String a = a.a(jSONObject, "id", aVar, "", false);
        if (TextUtils.isEmpty(a)) {
            a = a.a(jSONObject, "bannerID", aVar, "", true);
        }
        String a2 = a.a(jSONObject, "type", aVar, "", true);
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        if (TextUtils.isEmpty(a)) {
            return null;
        }
        if (fVar.b(a) != null) {
            return null;
        }
        c a3 = com.my.target.core.factories.a.a(a, a2, fVar.a());
        if (a3 == null) {
            com.my.target.core.parsers.a.a("Mismatch for banner type <" + a2 + ">, banner id <" + a + ">, section type <" + fVar.a() + ">", aVar, "MismatchBannerType");
            return null;
        }
        a = a.a(jSONObject, "bundle_id", aVar, "", false);
        if (!TextUtils.isEmpty(a)) {
            a3.b(a);
            if (arrayList.contains(a)) {
                a3.d();
            }
        }
        c(a3, jSONObject, aVar, fVar);
        if (a3 instanceof g) {
            aVar.d = "Parsing standard banner";
            g gVar = (g) a3;
            gVar.j(a.a(jSONObject, "title", aVar, "", false));
            gVar.k(a.a(jSONObject, "description", aVar, "", false));
            gVar.l(a.a(jSONObject, "disclaimer", aVar, "", false));
            gVar.d(a.a(jSONObject, "votes", aVar, 0));
            gVar.m(a.a(jSONObject, "category", aVar, "", false));
            gVar.n(a.a(jSONObject, "domain", aVar, "", false));
            gVar.o(a.a(jSONObject, "iconLink", aVar, "", false));
            gVar.e(a.a(jSONObject, "iconWidth", aVar, 0));
            gVar.f(a.a(jSONObject, "iconHeight", aVar, 0));
            gVar.a(a.b(jSONObject, "rating", aVar));
            gVar.a(a.a(jSONObject, "imageLink", "imageHeight", "imageWidth", aVar));
            if (a3.f() == 0 && com.my.target.core.enums.a.a.equals(fVar.a())) {
                a3.a(((com.my.target.core.models.sections.g) fVar).j());
            }
            if ("banner".equals(a3.a()) && TextUtils.isEmpty(((g) a3).n().getUrl())) {
                com.my.target.core.parsers.a.a("Banner with type 'banner' has no image", aVar, "No image in banner");
                return null;
            }
        } else if (a3 instanceof com.my.target.core.models.banners.b) {
            aVar.d = "Parsing appwall banner";
            com.my.target.core.models.banners.b bVar = (com.my.target.core.models.banners.b) a3;
            bVar.c(a.b(jSONObject, "hasNotification", aVar, false));
            bVar.g(a.b(jSONObject, "Banner", aVar, false));
            bVar.l(a.a(jSONObject, "bubble_id", aVar, "", false));
            bVar.e(a.b(jSONObject, "RequireCategoryHighlight", aVar, false));
            bVar.b(a.c(jSONObject, "icon_hd", aVar));
            bVar.f(a.b(jSONObject, "ItemHighlight", aVar, false));
            bVar.d(a.b(jSONObject, "Main", aVar, false));
            bVar.d(a.a(jSONObject, "mrgs_id", aVar, 0));
            bVar.e(a.a(jSONObject, "votes", aVar, 0));
            bVar.a(a.b(jSONObject, "rating", aVar));
            bVar.h(a.b(jSONObject, "RequireWifi", aVar, false));
            bVar.j(a.a(jSONObject, "title", aVar, "", false));
            bVar.k(a.a(jSONObject, "description", aVar, "", false));
            bVar.m(a.a(jSONObject, "labelType", aVar, "", false));
            bVar.n(a.a(jSONObject, "status", aVar, "", false));
            bVar.o(a.a(jSONObject, "paidType", aVar, "", false));
            bVar.i(a.b(jSONObject, "subitem", aVar, false));
            bVar.f(a.a(jSONObject, "coins", aVar, 0));
            bVar.c(a.c(jSONObject, "coins_icon_hd", aVar));
            bVar.g(a.b(jSONObject, "coins_icon_bgcolor", aVar, -552418));
            bVar.h(a.b(jSONObject, "coins_icon_textcolor", aVar, -1));
            bVar.h(a.c(jSONObject, "cross_notif_icon_hd", aVar));
            if (com.my.target.core.enums.a.c.equals(fVar.a())) {
                com.my.target.core.models.sections.b bVar2 = (com.my.target.core.models.sections.b) fVar;
                bVar.f(new ImageData(bVar2.i()));
                bVar.e(new ImageData(bVar2.k()));
                bVar.d(new ImageData(bVar2.j()));
                e o = bVar2.o(bVar.getStatus());
                if (o != null) {
                    bVar.a(new ImageData(o.b()));
                }
                if (bVar.isItemHighlight() && bVar2.l() != null) {
                    bVar.g(new ImageData(bVar2.l()));
                }
            }
        } else if (a3 instanceof d) {
            b(a3, jSONObject, aVar, fVar);
        } else if ((a3 instanceof com.my.target.core.models.banners.e) && com.my.target.core.enums.a.d.equals(fVar.a())) {
            aVar.d = "Parsing fs promo banner";
            com.my.target.core.models.banners.e eVar = (com.my.target.core.models.banners.e) a3;
            eVar.j(a.a(jSONObject, "title", aVar, "", false));
            eVar.k(a.a(jSONObject, "description", aVar, "", false));
            eVar.l(a.a(jSONObject, "disclaimer", aVar, "", false));
            eVar.i(a.a(jSONObject, "votes", aVar, 0));
            eVar.m(a.a(jSONObject, "category", aVar, "", false));
            eVar.b(a.b(jSONObject, "rating", aVar));
            eVar.q(a.a(jSONObject, "domain", aVar, "", false));
            eVar.n(a.a(jSONObject, "subcategory", aVar, "", false));
            eVar.o(a.a(jSONObject, "iconLink", aVar, "", false));
            eVar.l(a.a(jSONObject, "iconWidth", aVar, 0));
            eVar.m(a.a(jSONObject, "iconHeight", aVar, 0));
            eVar.p(a.a(jSONObject, "imageLink", aVar, "", false));
            eVar.j(a.a(jSONObject, "imageWidth", aVar, 0));
            eVar.k(a.a(jSONObject, "imageHeight", aVar, 0));
            eVar.d(a.a(jSONObject, "finalLink", aVar, "", false));
            eVar.d(a.b(jSONObject, "footerColor", aVar, -39322));
            eVar.e(a.b(jSONObject, "ctaButtonColor", aVar, -16733198));
            eVar.f(a.b(jSONObject, "ctaButtonTouchColor", aVar, -16746839));
            eVar.g(a.b(jSONObject, "ctaButtonTextColor", aVar, -1));
            com.my.target.core.models.sections.c cVar = (com.my.target.core.models.sections.c) fVar;
            eVar.h(a.a(jSONObject, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, aVar, cVar.o()));
            JSONObject jSONObject2 = jSONObject;
            eVar.a((float) a.a(jSONObject2, "allowCloseDelay", aVar, (double) cVar.i(), false));
            ImageData c = a.c(jSONObject, "close_icon_hd", aVar);
            if (c == null) {
                c = cVar.k();
            }
            eVar.a(c);
            c = a.c(jSONObject, "play_icon_hd", aVar);
            if (c == null) {
                c = cVar.l();
            }
            eVar.b(c);
            c = a.c(jSONObject, "store_icon_hd", aVar);
            if (c == null) {
                c = cVar.n();
            }
            eVar.c(c);
            eVar.c(cVar.u());
            eVar.d(cVar.t());
            r2 = a.a(jSONObject, AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO, aVar, false);
            if (l.b(14) && r2 != null) {
                r1 = com.my.target.core.factories.a.a(a3.getId(), AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO, com.my.target.core.enums.a.g);
                if (r1 instanceof i) {
                    c(r1, r2, aVar, fVar);
                    if (a(r1, r2, aVar, fVar)) {
                        a(r1, r2, aVar);
                        eVar.a((i) r1);
                    }
                }
            }
        } else if (a3 instanceof com.my.target.core.models.banners.f) {
            aVar.d = "Parsing native banner";
            com.my.target.core.models.banners.f fVar2 = (com.my.target.core.models.banners.f) a3;
            fVar2.j(a.a(jSONObject, "title", aVar, "", false));
            fVar2.k(a.a(jSONObject, "description", aVar, "", false));
            fVar2.l(a.a(jSONObject, "disclaimer", aVar, "", false));
            fVar2.i(a.a(jSONObject, "votes", aVar, 0));
            fVar2.m(a.a(jSONObject, "category", aVar, "", false));
            fVar2.b(a.b(jSONObject, "rating", aVar));
            fVar2.q(a.a(jSONObject, "domain", aVar, "", false));
            fVar2.n(a.a(jSONObject, "subcategory", aVar, "", false));
            fVar2.o(a.a(jSONObject, "iconLink", aVar, "", false));
            fVar2.l(a.a(jSONObject, "iconWidth", aVar, 0));
            fVar2.m(a.a(jSONObject, "iconHeight", aVar, 0));
            fVar2.p(a.a(jSONObject, "imageLink", aVar, "", false));
            fVar2.j(a.a(jSONObject, "imageWidth", aVar, 0));
            fVar2.k(a.a(jSONObject, "imageHeight", aVar, 0));
            fVar2.d(a.a(jSONObject, "finalLink", aVar, "", false));
            r2 = a.a(jSONObject, AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO, aVar, false);
            if (l.b(14) && r2 != null) {
                r1 = com.my.target.core.factories.a.a(a3.getId(), AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO, com.my.target.core.enums.a.g);
                if (r1 instanceof i) {
                    c(r1, r2, aVar, fVar);
                    if (a(r1, r2, aVar, fVar)) {
                        a(r1, r2, aVar);
                        fVar2.a((i) r1);
                    }
                }
            }
        } else if ((AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO.equals(a3.a()) || "statistics".equals(a3.a())) && !a(a3, jSONObject, aVar, fVar)) {
            return null;
        }
        a(a3, jSONObject, aVar);
        return a3;
    }

    private static void a(c cVar, JSONObject jSONObject, a aVar) {
        ArrayList a = e.a(jSONObject, aVar);
        if (!a.isEmpty()) {
            cVar.a(a);
        }
    }

    private static boolean a(c cVar, JSONObject jSONObject, a aVar, f fVar) {
        aVar.d = "Parsing video banner";
        i iVar = (i) cVar;
        j jVar = null;
        if (fVar instanceof h) {
            jVar = ((h) fVar).i();
        } else if (fVar instanceof com.my.target.core.models.sections.c) {
            jVar = ((com.my.target.core.models.sections.c) fVar).p();
            com.my.target.core.models.sections.c cVar2 = (com.my.target.core.models.sections.c) fVar;
            iVar.k(a.a(jSONObject, "closeActionText", aVar, cVar2.j(), false));
            iVar.l(a.a(jSONObject, "replayActionText", aVar, cVar2.m(), false));
            iVar.c(a.b(jSONObject, "allowBackButton", aVar, cVar2.q()));
            iVar.d(a.b(jSONObject, "allowReplay", aVar, cVar2.r()));
            iVar.e(a.b(jSONObject, "automute", aVar, cVar2.s()));
            iVar.i(cVar2.v());
        } else if (fVar instanceof com.my.target.core.models.sections.e) {
            com.my.target.core.models.sections.e eVar = (com.my.target.core.models.sections.e) fVar;
            iVar.k(a.a(jSONObject, "closeActionText", aVar, eVar.k(), false));
            iVar.l(a.a(jSONObject, "replayActionText", aVar, eVar.l(), false));
            iVar.e(a.b(jSONObject, "automute", aVar, eVar.i()));
            iVar.i(eVar.j());
        }
        if (jVar != null) {
            iVar.f(a.b(jSONObject, "allowClose", aVar, jVar.a()));
            double b = (double) jVar.b();
            iVar.b((float) a.a(jSONObject, "allowCloseDelay", aVar, b, false));
        }
        if (!"statistics".equals(cVar.a())) {
            float a = (float) a.a(jSONObject, VastIconXmlManager.DURATION, aVar, 0.0d, true);
            if (a <= 0.0f) {
                return false;
            }
            iVar.a(a);
        }
        iVar.g(a.b(jSONObject, "autoplay", aVar, true));
        iVar.h(a.b(jSONObject, "hasCtaButton", aVar, true));
        ImageData a2 = a.a(jSONObject, "previewLink", "previewHeight", "previewWidth", aVar);
        if (a2 != null) {
            iVar.a(a2);
        }
        if (!"statistics".equals(cVar.a())) {
            String str = "mediafiles";
            aVar.d = "Parsing banner mediafiles";
            List arrayList = new ArrayList();
            JSONArray a3 = a.a(jSONObject, str, aVar);
            if (a3 != null) {
                int length = a3.length();
                if (length == 0) {
                    com.my.target.core.parsers.a.a("Empty mediafiles array", aVar, "Empty mediafiles array");
                } else {
                    for (int i = 0; i < length; i++) {
                        JSONObject a4 = a.a(i, a3, str, aVar);
                        String a5 = a.a(a4, "src", aVar, null, true);
                        if (VERSION.SDK_INT < 12 && a5 != null && a5.startsWith(Constants.HTTPS)) {
                            a5 = 4;
                        }
                        int a6 = a.a(a4, "width", aVar, 0);
                        int a7 = a.a(a4, "height", aVar, 0);
                        int a8 = a.a(a4, "bitrate", aVar, 0);
                        if (a5 == null || a6 <= 0 || a7 <= 0 || a8 <= 0) {
                            com.my.target.core.parsers.a.a("Some of mediafile's params are invalid: src = " + a5 + " width = " + a6 + " height = " + a7 + " bitrate = " + a8, aVar, "Wrong mediafile");
                        } else {
                            VideoData videoData = new VideoData(a5);
                            videoData.setBitrate(a8);
                            videoData.setWidth(a6);
                            videoData.setHeight(a7);
                            arrayList.add(videoData);
                        }
                    }
                }
            }
            if (arrayList.isEmpty()) {
                return false;
            }
            iVar.a(arrayList);
        }
        return true;
    }

    private static void b(c cVar, JSONObject jSONObject, a aVar, f fVar) {
        List arrayList;
        int length;
        int i;
        aVar.d = "Parsing fs image banner";
        d dVar = (d) cVar;
        dVar.c(a.b(jSONObject, "allowClose", aVar, false));
        ImageData c = a.c(jSONObject, "close_icon_hd", aVar);
        if (c == null) {
            c = a.c(jSONObject, "close_icon", aVar);
        }
        if (c == null) {
            c = ((com.my.target.core.models.sections.c) fVar).k();
        }
        dVar.a(c);
        JSONArray a = a.a(jSONObject, DeviceInfo.ORIENTATION_PORTRAIT, aVar);
        if (a != null) {
            arrayList = new ArrayList();
            length = a.length();
            for (i = 0; i < length; i++) {
                JSONObject a2 = a.a(i, a, DeviceInfo.ORIENTATION_PORTRAIT, aVar);
                if (a2 != null) {
                    ImageData a3 = a.a(a2, "imageLink", "height", "width", aVar);
                    if (a3 != null) {
                        arrayList.add(a3);
                    }
                }
            }
            dVar.a(arrayList);
        }
        a = a.a(jSONObject, DeviceInfo.ORIENTATION_LANDSCAPE, aVar);
        if (a != null) {
            arrayList = new ArrayList();
            length = a.length();
            for (i = 0; i < length; i++) {
                JSONObject a4 = a.a(i, a, DeviceInfo.ORIENTATION_LANDSCAPE, aVar);
                if (a4 != null) {
                    ImageData a5 = a.a(a4, "imageLink", "height", "width", aVar);
                    if (a5 != null) {
                        arrayList.add(a5);
                    }
                }
            }
            dVar.b(arrayList);
        }
    }

    private static void c(c cVar, JSONObject jSONObject, a aVar, f fVar) {
        aVar.d = "Parsing common banner";
        cVar.c(a.a(jSONObject, "trackingLink", aVar, "", false));
        cVar.d(a.a(jSONObject, "finalLink", aVar, "", false));
        cVar.a(a.a(jSONObject, MyTargetVideoView.COMPLETE_STATUS_TIMEOUT, aVar, 0));
        cVar.a(a.a(jSONObject, "urlscheme", aVar, "", false));
        cVar.b(a.a(jSONObject, "width", aVar, 0));
        cVar.c(a.a(jSONObject, "height", aVar, 0));
        cVar.e(a.a(jSONObject, "ageRestrictions", aVar, "", false));
        cVar.b(a.a(jSONObject, "bundle_id", aVar, "", false));
        cVar.i(a.a(jSONObject, NavigationType.DEEPLINK, aVar, "", false));
        cVar.a(a.b(jSONObject, "openInBrowser", aVar, false));
        cVar.b(a.b(jSONObject, "usePlayStoreAction", aVar, false));
        String a = a.a(jSONObject, "navigationType", aVar, "", false);
        if (NavigationType.DEEPLINK.equals(a)) {
            a = NavigationType.STORE;
        }
        cVar.f(a);
        cVar.g(a.a(jSONObject, "ctaText", aVar, "", false));
        cVar.h(fVar.f());
    }
}
