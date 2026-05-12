package com.appodeal.ads.f;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.Native;
import com.appodeal.ads.UserSettings;
import com.appodeal.ads.UserSettings.Gender;
import com.appodeal.ads.UserSettings.Occupation;
import com.appodeal.ads.UserSettings.Relation;
import com.appodeal.ads.ah;
import com.appodeal.ads.ak;
import com.appodeal.ads.an;
import com.appodeal.ads.n;
import com.appodeal.ads.utils.d;
import com.appodeal.ads.v;
import com.facebook.places.model.PlaceFields;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public class g {
    private static f a;
    private static Map<String, Object> b;
    private static JSONArray c;
    private static int d = -1;
    private static int e = 0;
    private static double k;
    private static double l;
    private static boolean m;
    private final Context f;
    private final JSONObject g;
    private final Double h;
    private final Double i;
    private final boolean j;

    @NonNull
    public static f a() {
        if (a == null) {
            a = new f(new JSONObject());
        }
        return a;
    }

    public static void a(@NonNull f fVar) {
        a = fVar;
        Appodeal.a(String.format("Matched segment #%s", new Object[]{Long.valueOf(fVar.c())}));
    }

    public g(Context context, JSONObject jSONObject) {
        if (UserSettings.userData != null) {
            this.g = UserSettings.userData.optJSONObject("user_settings");
        } else {
            this.g = null;
        }
        this.f = context;
        this.h = Double.valueOf(jSONObject.optDouble("inapp_sum", 0.0d));
        this.i = Double.valueOf(jSONObject.optDouble("inapp_sum_all_apps", 0.0d));
        boolean z = jSONObject.has("inapp_sum") && this.h.doubleValue() > 0.0d;
        this.j = z;
        k = this.i.doubleValue();
        l = this.i.doubleValue();
        m = this.j;
    }

    g(Context context, double d, double d2, boolean z) {
        if (UserSettings.userData != null) {
            this.g = UserSettings.userData.optJSONObject("user_settings");
        } else {
            this.g = null;
        }
        this.f = context;
        this.h = Double.valueOf(d);
        this.i = Double.valueOf(d2);
        this.j = z;
    }

    public static void a(String str, Object obj) {
        if (b == null) {
            b = new HashMap();
        }
        b.put(str, obj);
        e();
    }

    private static void e() {
        g gVar = new g(Appodeal.b, k, l, m);
        try {
            f a;
            if (c != null) {
                a = gVar.a(c);
            } else {
                a = null;
            }
            if (a != null && a.c() != a.c()) {
                a.a();
                a(a);
                f();
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    private static void f() {
        v.k = true;
        n.l = true;
        ah.j = true;
        Native.i = true;
        com.appodeal.ads.g.k = true;
        ak.j = true;
    }

    public f a(JSONArray jSONArray) {
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                f fVar = new f(jSONArray.getJSONObject(i));
                if (b(fVar)) {
                    return fVar;
                }
                i++;
            } catch (Throwable e) {
                Appodeal.a(e);
            }
        }
        return null;
    }

    private boolean b(f fVar) {
        switch (fVar.a) {
            case ALL:
                return a(fVar.b);
            case ANY:
                return b(fVar.b);
            default:
                return false;
        }
    }

    private boolean a(e[] eVarArr) {
        for (e eVar : eVarArr) {
            if (!a(eVar, a(eVar.a))) {
                return false;
            }
        }
        return true;
    }

    private boolean b(e[] eVarArr) {
        for (e eVar : eVarArr) {
            if (a(eVar, a(eVar.a))) {
                return true;
            }
        }
        return false;
    }

    private Object a(String str) {
        if (str == null) {
            return null;
        }
        try {
            if (str.equals("country")) {
                if (UserSettings.userData != null) {
                    return UserSettings.userData.optString("country_id");
                }
                return null;
            } else if (str.equals("app_version")) {
                return new h(this.f.getPackageManager().getPackageInfo(this.f.getPackageName(), 0).versionName);
            } else {
                if (str.equals(SettingsJsonConstants.APP_KEY)) {
                    return this.f.getSharedPreferences("appodeal", 0).getString("appKey", null);
                }
                if (str.equals("sdk_version")) {
                    return new h("1.15.7");
                }
                if (str.equals("android_version")) {
                    return new h(VERSION.RELEASE);
                }
                if (str.equals("has_app_installed")) {
                    return r();
                }
                if (str.equals("session_count")) {
                    return Integer.valueOf((int) d.a(this.f.getSharedPreferences("appodeal", 0)));
                }
                if (str.equals("average_session_length")) {
                    return q();
                }
                if (str.equals("device_model")) {
                    return String.format("%s %s", new Object[]{Build.MANUFACTURER, Build.MODEL});
                } else if (str.equals("connection_type")) {
                    return j();
                } else {
                    if (str.equals("gender")) {
                        return m();
                    }
                    if (str.equals("age")) {
                        return h();
                    }
                    if (str.equals("occupation")) {
                        return k();
                    }
                    if (str.equals("relation")) {
                        return l();
                    }
                    if (str.equals("interests")) {
                        return g();
                    }
                    if (str.equals("bought_inapps")) {
                        return p();
                    }
                    if (str.equals("inapp_sum")) {
                        return n();
                    }
                    if (str.equals("inapp_sum_all_apps")) {
                        return o();
                    }
                    if (str.equals("last_session_time")) {
                        return i();
                    }
                    if (str.equals("platform")) {
                        return AbstractSpiCall.ANDROID_CLIENT_TYPE;
                    }
                    if (str.equals("device_type")) {
                        return an.n(this.f) ? "tablet" : PlaceFields.PHONE;
                    } else {
                        if (b != null && b.containsKey(str)) {
                            return b.get(str);
                        }
                        if (str.equals("day")) {
                            e = c();
                            return Integer.valueOf(e);
                        } else if (!str.equals("hour")) {
                            return null;
                        } else {
                            d = b();
                            return Integer.valueOf(d);
                        }
                    }
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
            return null;
        }
    }

    private String g() {
        if (this.g == null || !this.g.has("interests")) {
            return Appodeal.getUserSettings(this.f).getInterests();
        }
        return this.g.getString("interests");
    }

    private Integer h() {
        if (this.g == null || !this.g.has("age")) {
            return Appodeal.getUserSettings(this.f).getAge();
        }
        return Integer.valueOf(this.g.getInt("age"));
    }

    private Integer i() {
        long c = d.c();
        if (c == 0) {
            return Integer.valueOf(0);
        }
        return Integer.valueOf((int) ((System.currentTimeMillis() - c) / 60000));
    }

    private Integer j() {
        String str = an.b(this.f).a;
        if (str == null) {
            return Integer.valueOf(0);
        }
        if (str.equals("mobile")) {
            return Integer.valueOf(2);
        }
        if (str.equals("wifi")) {
            return Integer.valueOf(1);
        }
        return Integer.valueOf(0);
    }

    private Integer k() {
        if (this.g != null && this.g.has("occupation")) {
            return Integer.valueOf(this.g.optInt("occupation"));
        }
        Occupation occupation = Appodeal.getUserSettings(this.f).getOccupation();
        if (occupation == null) {
            return Integer.valueOf(0);
        }
        return Integer.valueOf(occupation.getValue());
    }

    private Integer l() {
        if (this.g != null && this.g.has("relation")) {
            return Integer.valueOf(this.g.getInt("relation"));
        }
        Relation relation = Appodeal.getUserSettings(this.f).getRelation();
        if (relation == null) {
            return Integer.valueOf(0);
        }
        return Integer.valueOf(relation.getValue());
    }

    private Integer m() {
        if (this.g != null && this.g.has("gender")) {
            return b(this.g.getString("gender"));
        }
        Gender gender = Appodeal.getUserSettings(this.f).getGender();
        if (gender == null) {
            return Integer.valueOf(0);
        }
        return Integer.valueOf(gender.getValue());
    }

    private Integer b(String str) {
        if (str.equalsIgnoreCase("o")) {
            return Integer.valueOf(0);
        }
        if (str.equalsIgnoreCase("f")) {
            return Integer.valueOf(1);
        }
        if (str.equalsIgnoreCase("m")) {
            return Integer.valueOf(2);
        }
        try {
            return Integer.valueOf(Integer.parseInt(str));
        } catch (Exception e) {
            return null;
        }
    }

    private Double n() {
        return this.h;
    }

    private Double o() {
        return this.i;
    }

    private Boolean p() {
        return Boolean.valueOf(this.j);
    }

    private Double q() {
        SharedPreferences sharedPreferences = this.f.getSharedPreferences("appodeal", 0);
        return Double.valueOf((((double) d.b(sharedPreferences)) / ((double) d.a(sharedPreferences))) / 60.0d);
    }

    private String r() {
        StringBuilder stringBuilder = new StringBuilder();
        List<ApplicationInfo> installedApplications = this.f.getPackageManager().getInstalledApplications(0);
        Pattern compile = Pattern.compile("^?(?:com\\.android|com\\.google|com\\.sec|com\\.samsung|com\\.sonyericsson|com\\.sonymobile|com\\.motorola|com\\.htc).*$");
        for (ApplicationInfo applicationInfo : installedApplications) {
            String str = applicationInfo.packageName;
            if (!(compile.matcher(str).matches() || str.equals(AbstractSpiCall.ANDROID_CLIENT_TYPE))) {
                stringBuilder.append(str).append(",");
            }
        }
        return stringBuilder.toString();
    }

    private boolean a(e eVar, Object obj) {
        if (obj == null) {
            return false;
        }
        if (eVar.d == a.Unknown) {
            eVar.d = e.a(obj);
            eVar.a();
        }
        if (eVar.d == a.Unknown) {
            return false;
        }
        switch (eVar.b) {
            case IN:
                return g(eVar, obj);
            case EQUALS:
                return f(eVar, obj);
            case NOT_EQUALS:
                if (f(eVar, obj)) {
                    return false;
                }
                return true;
            case LESS:
                return d(eVar, obj);
            case MORE:
                return e(eVar, obj);
            case LESS_EQUALS:
                return c(eVar, obj);
            case MORE_EQUALS:
                return b(eVar, obj);
            default:
                return false;
        }
    }

    private boolean b(e eVar, Object obj) {
        return f(eVar, obj) || e(eVar, obj);
    }

    private boolean c(e eVar, Object obj) {
        return f(eVar, obj) || d(eVar, obj);
    }

    private boolean d(e eVar, Object obj) {
        if (eVar.d == a.Double) {
            return ((Double) eVar.c).doubleValue() > ((Double) obj).doubleValue();
        } else if (eVar.d == a.Integer) {
            if (((Integer) eVar.c).intValue() <= ((Integer) obj).intValue()) {
                return false;
            }
            return true;
        } else if (eVar.d != a.Version) {
            return false;
        } else {
            if (((h) eVar.c).compareTo(obj) <= 0) {
                return false;
            }
            return true;
        }
    }

    private boolean e(e eVar, Object obj) {
        if (eVar.d == a.Double) {
            return ((Double) eVar.c).doubleValue() < ((Double) obj).doubleValue();
        } else if (eVar.d == a.Integer) {
            if (((Integer) eVar.c).intValue() >= ((Integer) obj).intValue()) {
                return false;
            }
            return true;
        } else if (eVar.d != a.Version) {
            return false;
        } else {
            if (((h) eVar.c).compareTo(obj) >= 0) {
                return false;
            }
            return true;
        }
    }

    private boolean f(e eVar, Object obj) {
        switch (eVar.d) {
            case Version:
                return ((h) eVar.c).compareTo(obj) == 0;
            case String:
                if (obj == null || !((String) obj).contains((String) eVar.c)) {
                    return false;
                }
                return true;
            case Integer:
            case Double:
            case Boolean:
                return obj != null && obj.equals(eVar.c);
            default:
                return false;
        }
    }

    private boolean g(e eVar, Object obj) {
        switch (eVar.d) {
            case String:
                return ((String) obj).toLowerCase().contains(((String) eVar.c).toLowerCase());
            case StringArray:
                return a((String[]) eVar.c, (String) obj);
            case IntegerArray:
                return a((Integer[]) eVar.c, (Integer) obj);
            default:
                return false;
        }
    }

    private boolean a(Integer[] numArr, Integer num) {
        for (Integer equals : numArr) {
            if (equals.equals(num)) {
                return true;
            }
        }
        return false;
    }

    private boolean a(String[] strArr, String str) {
        for (CharSequence contains : strArr) {
            if (str.contains(contains)) {
                return true;
            }
        }
        return false;
    }

    public boolean b(@NonNull JSONArray jSONArray) {
        if (c == null) {
            c = jSONArray;
            return true;
        } else if (c.toString().equals(jSONArray.toString())) {
            return false;
        } else {
            c = jSONArray;
            return true;
        }
    }

    @VisibleForTesting
    static int b() {
        return Calendar.getInstance().get(11);
    }

    @VisibleForTesting
    static int c() {
        switch (Calendar.getInstance().get(7)) {
            case 1:
                return 7;
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 4;
            case 6:
                return 5;
            case 7:
                return 6;
            default:
                return 0;
        }
    }

    public static void d() {
        if (c == null) {
            return;
        }
        if (c() != e || b() != d) {
            e();
        }
    }
}
