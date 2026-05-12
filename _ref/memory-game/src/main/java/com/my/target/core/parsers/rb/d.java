package com.my.target.core.parsers.rb;

import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.mopub.common.AdType;
import com.my.target.core.models.c;
import com.my.target.core.models.e;
import com.my.target.core.models.h;
import com.my.target.core.models.i;
import com.my.target.core.models.j;
import com.my.target.core.models.sections.b;
import com.my.target.core.models.sections.f;
import com.my.target.core.models.sections.g;
import com.my.target.core.parsers.a.a;
import com.my.target.nativeads.models.ImageData;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: RBSectionParser */
public class d {
    public static f a(String str, JSONObject jSONObject, c cVar, ArrayList<String> arrayList, a aVar, com.my.target.core.models.d dVar) {
        aVar.d = "Parsing section";
        aVar.e = str;
        aVar.c = d.class.getName();
        JSONObject a = a.a(jSONObject, str, aVar, true);
        if (a == null) {
            return null;
        }
        Integer valueOf = Integer.valueOf(a.a(a, "index", aVar, 0));
        f c = cVar.c(str);
        if (c == null) {
            c = com.my.target.core.factories.d.a(str, valueOf.intValue());
        }
        if (c == null) {
            return null;
        }
        JSONObject a2 = a.a(a, "settings", aVar, false);
        if (a2 != null) {
            aVar.d = "Parsing section settings";
            aVar.e = c.e();
            aVar.c = b.class.getName();
            c.a(a.a(a2, "advertisingLabel", aVar, "", false));
            if (com.my.target.core.enums.a.a.equals(c.a())) {
                aVar.d = "Parsing standard section settings";
                g b = a.b(c, c.e(), aVar);
                if (b != null) {
                    b.a(a.a(a2, "refreshTime", aVar, 60));
                    b.a(a.b(a2, "hasAdditionalAds", aVar, true));
                    b.b(a.b(a2, "loopRotation", aVar, true));
                    b.b(a.a(a2, "animationType", aVar, 1));
                    a2 = a.a(a2, "view", aVar, false);
                    if (a2 != null) {
                        aVar.d = "Parsing section view settings";
                        h i = b.i();
                        i.a(a.a(a2, "type", aVar, AdType.HTML, false));
                        i.a(a.b(a2, "backgroundColor", aVar, -1));
                        i.b(a.b(a2, "backgroundTouchColor", aVar, -3806472));
                        i.c(a.b(a2, "titleColor", aVar, -16755546));
                        i.d(a.b(a2, "ageRestrictionsBackgroundColor", aVar, -1));
                        i.e(a.b(a2, "ageRestrictionsTextColor", aVar, -10066330));
                        i.f(a.b(a2, "ageRestrictionsBorderColor", aVar, -5000269));
                        i.g(a.b(a2, "descriptionColor", aVar, -16777216));
                        i.h(a.b(a2, "domainColor", aVar, -10066330));
                        i.i(a.b(a2, "votesColor", aVar, -7829368));
                        i.j(a.b(a2, "disclaimerColor", aVar, -7829368));
                        i.k(a.b(a2, "ctaButtonColor", aVar, -16732432));
                        i.l(a.b(a2, "ctaButtonTouchColor", aVar, -16746839));
                        i.m(a.b(a2, "ctaButtonTextColor", aVar, -1));
                        i.a(a.b(a2, "titleBold", aVar, true));
                        i.b(a.b(a2, "descriptionBold", aVar, false));
                        i.c(a.b(a2, "domainBold", aVar, false));
                        i.d(a.b(a2, "votesBold", aVar, false));
                        i.e(a.b(a2, "disclaimerBold", aVar, false));
                        i.f(a.b(a2, "ctaButtonTextBold", aVar, false));
                    }
                }
            } else if (com.my.target.core.enums.a.g.equals(c.a())) {
                aVar.d = "Parsing video section settings";
                j i2 = a.c(c, c.e(), aVar).i();
                aVar.d = "Parsing video params";
                i2.a(a.b(a2, "allowClose", aVar, false));
                i2.a((float) a.a(a2, "allowCloseDelay", aVar, 0.0d, false));
                i2.a(a.a(a2, "connectionTimeout", aVar, 0));
                i2.b(a.a(a2, "maxBannersShow", aVar, 0));
            } else if (com.my.target.core.enums.a.d.equals(c.a())) {
                aVar.d = "Parsing fullscreen section settings";
                com.my.target.core.models.sections.c d = a.d(c, c.e(), aVar);
                Object a3 = a.a(a2, "close_icon_hd", aVar, "", false);
                if (!TextUtils.isEmpty(a3)) {
                    d.a(new ImageData(a3, 0, 0));
                }
                a3 = a.a(a2, "play_icon_hd", aVar, "", false);
                if (!TextUtils.isEmpty(a3)) {
                    d.b(new ImageData(a3, 0, 0));
                }
                a3 = a.a(a2, "store_icon_hd", aVar, "", false);
                if (!TextUtils.isEmpty(a3)) {
                    d.c(new ImageData(a3, 0, 0));
                }
                d.e(a.b(a2, "extendedClickArea", aVar, false));
                d.d(a.b(a2, "closeOnClick", aVar, true));
                d.a((float) a.a(a2, "allowCloseDelay", aVar, 0));
                d.a(a.a(a2, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, aVar, 0));
                a2 = a.a(a2, AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO, aVar, false);
                if (a2 != null) {
                    d.p().a(a.b(a2, "allowClose", aVar, true));
                    d.p().a((float) a.a(a2, "allowCloseDelay", aVar, 0));
                    d.f(a.b(a2, "showPlayerControls", aVar, true));
                    d.d(a.a(a2, "replayActionText", aVar, "Replay", false));
                    d.c(a.a(a2, "closeActionText", aVar, "Close", false));
                    d.b(a.b(a2, "allowBackButton", aVar, true));
                    d.c(a.b(a2, "allowReplay", aVar, true));
                    d.a(a.b(a2, "automute", aVar, false));
                }
            } else if (com.my.target.core.enums.a.c.equals(c.a())) {
                aVar.d = "Parsing appwall section settings";
                b e = a.e(c, c.e(), aVar);
                e.c(a.a(a2, "title", aVar, "", false));
                e.d(a.a(a2, SettingsJsonConstants.APP_ICON_KEY, aVar, "", false));
                e.e(a.a(a2, "icon_hd", aVar, "", false));
                e.f(a.a(a2, "bubble_icon", aVar, "", false));
                e.g(a.a(a2, "bubble_icon_hd", aVar, "", false));
                e.h(a.a(a2, "label_icon", aVar, "", false));
                e.i(a.a(a2, "label_icon_hd", aVar, "", false));
                e.j(a.a(a2, "goto_app_icon", aVar, "", false));
                e.k(a.a(a2, "goto_app_icon_hd", aVar, "", false));
                e.l(a.a(a2, "item_highlight_icon", aVar, "", false));
                JSONArray a4 = a.a(a2, "icon_status", aVar);
                aVar.d = "Parsing icon statuses";
                int length = a4.length();
                for (int i3 = 0; i3 < length; i3++) {
                    JSONObject a5 = a.a(i3, a4, "icon_status", aVar);
                    e eVar = new e(a.a(a5, Param.VALUE, aVar, "", false));
                    eVar.a(a.a(a5, SettingsJsonConstants.APP_ICON_KEY, aVar, "", false));
                    eVar.b(a.a(a5, "icon_hd", aVar, "", false));
                    e.a(eVar);
                }
            } else if (com.my.target.core.enums.a.e.equals(c.a())) {
                aVar.d = "Parsing native section settings";
                com.my.target.core.models.sections.e a6 = a.a(c, c.e(), aVar);
                a2 = a.a(a2, AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO, aVar, false);
                if (a2 != null) {
                    a6.b(a.b(a2, "showPlayerControls", aVar, true));
                    a6.d(a.a(a2, "replayActionText", aVar, "Replay", false));
                    a6.c(a.a(a2, "closeActionText", aVar, "Close", false));
                    a6.a(a.b(a2, "automute", aVar, false));
                }
            }
        }
        if (com.my.target.core.enums.a.f.equals(str)) {
            aVar.d = "Parsing instream section";
            com.my.target.core.models.sections.d dVar2 = (com.my.target.core.models.sections.d) c;
            JSONObject a7 = a.a(a, "sections", aVar, true);
            if (a7 == null) {
                return null;
            }
            JSONArray names = a7.names();
            int length2 = names.length();
            for (int i4 = 0; i4 < length2; i4++) {
                String b2 = a.b(i4, names, "sections", aVar);
                if (b2 != null) {
                    JSONArray a8 = a.a(a7, b2, aVar);
                    if (a8 != null) {
                        a(dVar2.c(b2), a8, aVar, dVar, (ArrayList) arrayList);
                    }
                }
            }
            return c;
        }
        JSONArray a9 = a.a(a, "banners", aVar);
        if (a9 != null) {
            a(c, a9, aVar, dVar, (ArrayList) arrayList);
        }
        if (c.b() == 0) {
            return null;
        }
        return c;
    }

    private static void a(f fVar, JSONArray jSONArray, a aVar, com.my.target.core.models.d dVar, ArrayList<String> arrayList) {
        int i = 0;
        aVar.d = "Parsing banners to section";
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        int length = jSONArray.length();
        for (int i2 = 0; i2 < length; i2++) {
            JSONObject a = a.a(i2, jSONArray, "banners", aVar);
            if (!"additionalData".equals(a.a(a, "type", aVar, "", false))) {
                com.my.target.core.models.banners.c a2 = b.a(a, fVar, (ArrayList) arrayList, aVar);
                if (a2 != null) {
                    arrayList4.add(a2);
                }
            } else if (fVar instanceof com.my.target.core.models.sections.h) {
                com.my.target.core.models.d a3 = a(a, (com.my.target.core.models.sections.h) fVar, i2, aVar, dVar);
                if (a3 != null) {
                    if (a3.k() != -1) {
                        arrayList2.add(a3);
                    } else {
                        arrayList3.add(a3);
                        if (a3.c() || a3.d()) {
                            ((com.my.target.core.models.sections.h) fVar).a(a3);
                        } else {
                            dVar.b(a3);
                        }
                    }
                }
            }
        }
        if (arrayList4.size() <= 0 || !dVar.n()) {
            Iterator it = arrayList4.iterator();
            while (it.hasNext()) {
                fVar.a((com.my.target.core.models.banners.c) it.next());
            }
        } else {
            while (i < arrayList4.size()) {
                fVar.a(i, (com.my.target.core.models.banners.c) arrayList4.get(i));
                i++;
            }
        }
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            com.my.target.core.models.d dVar2 = (com.my.target.core.models.d) it2.next();
            Iterator it3 = arrayList3.iterator();
            while (it3.hasNext()) {
                com.my.target.core.models.d dVar3 = (com.my.target.core.models.d) it3.next();
                if (dVar2.k() == dVar3.e()) {
                    dVar3.a(dVar2);
                    break;
                }
            }
        }
    }

    private static com.my.target.core.models.d a(JSONObject jSONObject, com.my.target.core.models.sections.h hVar, int i, a aVar, com.my.target.core.models.d dVar) {
        if (dVar.i() >= 5) {
            return null;
        }
        aVar.d = "Parsing additional data";
        Object a = a.a(jSONObject, "url", aVar, "", true);
        if (TextUtils.isEmpty(a)) {
            return null;
        }
        com.my.target.core.models.d dVar2 = new com.my.target.core.models.d(a);
        dVar2.a(dVar.e());
        dVar2.f().addAll(dVar.f());
        dVar2.b(dVar.i() + 1);
        dVar2.a(hVar.e());
        if (i == 0 && dVar2.i() == 1) {
            dVar2.o();
        }
        dVar2.a(a.a(jSONObject, "id", aVar, 0));
        dVar2.a(a.b(jSONObject, "doAfter", aVar, false));
        dVar2.c(a.a(jSONObject, "doOnEmptyResponseFromId", aVar, -1));
        dVar2.b(a.b(jSONObject, "isMidrollPoint", aVar, false));
        if (TextUtils.isEmpty(dVar.l())) {
            dVar2.a(hVar.e());
        } else {
            dVar2.a(dVar.l());
        }
        Iterator it = e.a(jSONObject, aVar).iterator();
        while (it.hasNext()) {
            dVar2.a((i) it.next());
        }
        aVar.d = "Parsing service stats";
        ArrayList arrayList = new ArrayList();
        JSONArray a2 = a.a(jSONObject, "serviceStatistics", aVar);
        if (a2 != null) {
            int length = a2.length();
            for (int i2 = 0; i2 < length; i2++) {
                JSONObject a3 = a.a(i2, a2, "statistics", aVar);
                Object a4 = a.a(a3, "type", aVar, "", true);
                Object a5 = a.a(a3, "url", aVar, "", true);
                if (!(TextUtils.isEmpty(a4) || TextUtils.isEmpty(a5))) {
                    arrayList.add(new i(a4, a5));
                }
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            dVar2.c((i) it2.next());
        }
        it2 = dVar.f().iterator();
        while (it2.hasNext()) {
            dVar2.a((i) it2.next());
        }
        it2 = dVar.g().iterator();
        while (it2.hasNext()) {
            dVar2.b((i) it2.next());
        }
        return dVar2;
    }
}
