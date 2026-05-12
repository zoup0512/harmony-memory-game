package com.my.target.core.parsers.rb;

import android.content.Context;
import android.text.TextUtils;
import com.my.target.core.models.d;
import com.my.target.core.models.sections.f;
import com.my.target.core.parsers.a.a;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: RBJSONParser */
public class c {
    public static void a(JSONObject jSONObject, com.my.target.core.models.c cVar, String str, ArrayList<String> arrayList, Context context, d dVar) {
        if (jSONObject.has("html_wrapper")) {
            cVar.b(jSONObject.optString("html_wrapper"));
            jSONObject.remove("html_wrapper");
        }
        cVar.a(jSONObject);
        JSONArray names = jSONObject.names();
        if (names != null) {
            int length = names.length();
            a aVar = new a(context);
            aVar.b = cVar.b();
            aVar.d = "Parsing";
            aVar.c = c.class.getName();
            aVar.e = "root";
            for (int i = 0; i < length; i++) {
                String b = a.b(i, names, "root", aVar);
                if (!(TextUtils.isEmpty(b) || com.my.target.core.enums.a.a(b) == null)) {
                    Object obj;
                    if (str == null || str.equals(b) || ((str.equals("appwall") && "showcaseApps".equals(b)) || "showcaseGames".equals(b) || "showcase".equals(b))) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (obj != null) {
                        f a = d.a(b, jSONObject, cVar, arrayList, aVar, dVar);
                        if (a != null) {
                            cVar.a(a);
                        } else {
                            jSONObject.remove(b);
                            return;
                        }
                    }
                    jSONObject.remove(b);
                    return;
                }
            }
        }
    }
}
