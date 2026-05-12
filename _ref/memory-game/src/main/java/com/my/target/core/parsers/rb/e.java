package com.my.target.core.parsers.rb;

import android.text.TextUtils;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.my.target.core.models.g;
import com.my.target.core.models.i;
import com.my.target.core.parsers.a.a;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: RBStatsParser */
public final class e {
    static ArrayList<i> a(JSONObject jSONObject, a aVar) {
        aVar.d = "Parsing banner stats";
        ArrayList<i> arrayList = new ArrayList();
        JSONArray a = a.a(jSONObject, "statistics", aVar);
        if (a != null) {
            int length = a.length();
            for (int i = 0; i < length; i++) {
                JSONObject a2 = a.a(i, a, "statistics", aVar);
                String a3 = a.a(a2, "type", aVar, "", true);
                String a4 = a.a(a2, "url", aVar, "", true);
                if (!(TextUtils.isEmpty(a3) || TextUtils.isEmpty(a4))) {
                    if ("playheadReachedValue".equals(a3)) {
                        g gVar = new g(a3, a4);
                        float a5;
                        if (a2.has(Param.VALUE)) {
                            a5 = (float) a.a(a2, Param.VALUE, aVar, -1.0d, true);
                            if (a5 != -1.0f) {
                                gVar.a(a5);
                            }
                        } else if (a2.has("pvalue")) {
                            a5 = (float) a.a(a2, "pvalue", aVar, -1.0d, true);
                            if (a5 != -1.0f) {
                                gVar.b(a5);
                            }
                        }
                        arrayList.add(gVar);
                    } else {
                        arrayList.add(new i(a3, a4));
                    }
                }
            }
        }
        return arrayList;
    }
}
