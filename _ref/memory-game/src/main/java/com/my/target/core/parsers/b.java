package com.my.target.core.parsers;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.my.target.Tracer;
import com.my.target.core.a;
import com.my.target.core.models.c;
import com.my.target.core.models.d;
import com.yalantis.ucrop.util.FileUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ResponseParser */
public class b {
    public static void a(String str, c cVar, a aVar, d dVar, Context context) {
        String trim;
        if (str != null) {
            trim = str.trim();
        } else {
            trim = str;
        }
        if (trim == null || trim.equals("")) {
            Tracer.d("data is empty");
            return;
        }
        if (trim.indexOf("<!doctype html>") == 0) {
            int indexOf = trim.indexOf("bannersJSON:");
            if (indexOf >= 0) {
                indexOf += 12;
                int indexOf2 = trim.indexOf("{", indexOf);
                if (indexOf2 >= indexOf) {
                    int indexOf3 = trim.indexOf("};", indexOf2);
                    if (indexOf3 >= indexOf2 + 1) {
                        int indexOf4 = trim.indexOf("</script>", indexOf3);
                        if (indexOf4 >= indexOf3) {
                            trim = "{\"html_wrapper\":\"" + (trim.substring(0, indexOf) + "''};" + trim.substring(indexOf4)).replace("\"", "'") + "\"," + trim.substring(indexOf2 + 1, indexOf3);
                        }
                    }
                }
            }
        }
        if (c.a(trim)) {
            Tracer.d("Parsing XML...");
            Tracer.d("parse VAST");
            a.a aVar2 = new a.a(context);
            aVar2.f = c.class.getSimpleName();
            aVar2.c = b.class.getName();
            aVar2.b = cVar.b();
            aVar2.d = "Parsing VAST";
            aVar2.e = "no unit";
            try {
                c.a(trim, cVar, dVar, aVar2);
                return;
            } catch (c.a e) {
                String message = e.getMessage();
                Tracer.d("parse VAST error. message: " + message);
                if (message.contains("(")) {
                    message = message.substring(0, message.indexOf("("));
                }
                com.my.target.core.async.a.a(message + ", Operation: " + aVar2.d + ", Unit: " + aVar2.e, aVar2.c, "VAST Exception:  Convert to XML error", trim, aVar2.b, aVar2.a);
                return;
            }
        }
        Tracer.d("Converting to JSON...");
        try {
            JSONObject jSONObject = new JSONObject(trim);
            Tracer.d("done");
            if (a(jSONObject)) {
                Tracer.d("parse json");
                com.my.target.core.parsers.rb.c.a(jSONObject, cVar, aVar.c(), a(context), context, dVar);
                if (aVar.e()) {
                    cVar.h();
                }
                Tracer.d("json parsing finished");
                return;
            }
            Tracer.d("invalid json version");
        } catch (Exception e2) {
            Tracer.d("convert to JSON error: " + e2.getMessage());
            com.my.target.core.async.a.a("Convert to JSON error", b.class.getName(), "JSON Exception: Convert to JSON error", trim, cVar.b(), context);
        }
    }

    private static boolean a(JSONObject jSONObject) {
        try {
            String string = jSONObject.getString("version");
            Tracer.d("json version: " + string);
            int indexOf = string.indexOf(FileUtils.HIDDEN_PREFIX);
            if (indexOf <= 0) {
                return false;
            }
            try {
                if (Integer.parseInt(string.substring(0, indexOf), 10) == 2) {
                    return true;
                }
                return false;
            } catch (NumberFormatException e) {
                return false;
            }
        } catch (JSONException e2) {
            return false;
        }
    }

    private static ArrayList<String> a(Context context) {
        List<ApplicationInfo> installedApplications = context.getPackageManager().getInstalledApplications(128);
        ArrayList<String> arrayList = new ArrayList();
        for (ApplicationInfo applicationInfo : installedApplications) {
            if ((applicationInfo.flags & 1) == 0) {
                arrayList.add(applicationInfo.packageName);
            }
        }
        return arrayList;
    }

    private b() {
    }
}
