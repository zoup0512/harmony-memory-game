package com.my.target.core.parsers.rb;

import android.graphics.Color;
import android.text.TextUtils;
import com.my.target.core.models.sections.b;
import com.my.target.core.models.sections.c;
import com.my.target.core.models.sections.e;
import com.my.target.core.models.sections.f;
import com.my.target.core.models.sections.g;
import com.my.target.core.models.sections.h;
import com.my.target.nativeads.models.ImageData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: RBAbstractParser */
public final class a {
    static String a(JSONObject jSONObject, String str, com.my.target.core.parsers.a.a aVar, String str2, boolean z) {
        if (jSONObject.has(str)) {
            try {
                str2 = jSONObject.getString(str);
                if (!TextUtils.isEmpty(str2)) {
                    return str2;
                }
                if (!z) {
                    return null;
                }
                com.my.target.core.parsers.a.a("Empty string required field '" + str + "', Operation: " + aVar.d + ", Unit: " + aVar.e, aVar, null, "Required field absence");
                return str2;
            } catch (Exception e) {
                String str3 = "JSON Exception in field " + str + ", Operation: " + aVar.d + ", Unit: " + aVar.e;
                if (!z) {
                    return str2;
                }
                com.my.target.core.parsers.a.a(str3, aVar, e, "JSONException");
                return str2;
            }
        } else if (!z) {
            return str2;
        } else {
            com.my.target.core.parsers.a.a("No required field '" + str + "', Operation: " + aVar.d + ", Unit: " + aVar.e, aVar, null, "Required field absence");
            return str2;
        }
    }

    static JSONObject a(JSONObject jSONObject, String str, com.my.target.core.parsers.a.a aVar, boolean z) {
        JSONObject jSONObject2 = null;
        if (jSONObject.has(str)) {
            try {
                jSONObject2 = jSONObject.getJSONObject(str);
            } catch (Exception e) {
                String str2 = e.getMessage() + "  field: " + str + ", Operation: " + aVar.d + ", Unit: " + aVar.e;
                if (z) {
                    com.my.target.core.parsers.a.a(str2, aVar, e, "JSONException");
                }
            }
        } else if (z) {
            com.my.target.core.parsers.a.a("Didn't found required field '" + str + "', Operation: " + aVar.d + ", Unit: " + aVar.e, aVar, jSONObject2, "Required field absence");
        }
        return jSONObject2;
    }

    static int a(JSONObject jSONObject, String str, com.my.target.core.parsers.a.a aVar, int i) {
        if (jSONObject.has(str)) {
            try {
                i = jSONObject.getInt(str);
            } catch (JSONException e) {
                new StringBuilder("JSON Exception in field '").append(str).append("', Operation: ").append(aVar.d).append(", Unit: ").append(aVar.e);
            }
        }
        return i;
    }

    static double a(JSONObject jSONObject, String str, com.my.target.core.parsers.a.a aVar, double d, boolean z) {
        if (jSONObject.has(str)) {
            try {
                d = jSONObject.getDouble(str);
            } catch (Exception e) {
                String str2 = "JSON Exception in field '" + str + "', Operation: " + aVar.d + ", Unit: " + aVar.e;
                if (z) {
                    com.my.target.core.parsers.a.a(str2, aVar, e, "JSONException");
                }
            }
        } else if (z) {
            com.my.target.core.parsers.a.a("Didn't found required field '" + str + "', Operation: " + aVar.d + ", Unit: " + aVar.e, aVar, null, "Required field absence");
        }
        return d;
    }

    static boolean b(JSONObject jSONObject, String str, com.my.target.core.parsers.a.a aVar, boolean z) {
        if (jSONObject.has(str)) {
            try {
                z = jSONObject.getBoolean(str);
            } catch (JSONException e) {
                new StringBuilder("JSON Exception in field '").append(str).append("', Operation: ").append(aVar.d).append(", Unit: ").append(aVar.e);
            }
        }
        return z;
    }

    static JSONArray a(JSONObject jSONObject, String str, com.my.target.core.parsers.a.a aVar) {
        JSONArray jSONArray = null;
        if (jSONObject.has(str)) {
            try {
                jSONArray = jSONObject.getJSONArray(str);
            } catch (JSONException e) {
                new StringBuilder("JSON Exception in field '").append(str).append("', Operation: ").append(aVar.d).append(", Unit: ").append(aVar.e);
            }
        }
        return jSONArray;
    }

    static int b(JSONObject jSONObject, String str, com.my.target.core.parsers.a.a aVar, int i) {
        String str2 = "";
        try {
            Object a = a(jSONObject, str, aVar, "", false);
            if (!TextUtils.isEmpty(a)) {
                i = Color.parseColor(a);
            }
        } catch (Exception e) {
            com.my.target.core.parsers.a.a("Unable to parse color: '" + str + "', value: " + str2 + ", Operation: " + aVar.d + ", Unit: " + aVar.e, aVar, e, "Color parse error");
        }
        return i;
    }

    static e a(f fVar, String str, com.my.target.core.parsers.a.a aVar) {
        try {
            return (e) fVar;
        } catch (Exception e) {
            com.my.target.core.parsers.a.a("Unable to cast to native section field '" + str + "', Operation: " + aVar.d + ", Unit: " + aVar.e, aVar, e, "Class cast exception");
            return null;
        }
    }

    static g b(f fVar, String str, com.my.target.core.parsers.a.a aVar) {
        try {
            return (g) fVar;
        } catch (Exception e) {
            com.my.target.core.parsers.a.a("Unable to cast to standard section field '" + str + "', Operation: " + aVar.d + ", Unit: " + aVar.e, aVar, e, "Class cast exception");
            return null;
        }
    }

    static h c(f fVar, String str, com.my.target.core.parsers.a.a aVar) {
        try {
            return (h) fVar;
        } catch (Exception e) {
            com.my.target.core.parsers.a.a("Unable to cast to standard section field '" + str + "', Operation: " + aVar.d + ", Unit: " + aVar.e, aVar, e, "Class cast exception");
            return null;
        }
    }

    static c d(f fVar, String str, com.my.target.core.parsers.a.a aVar) {
        try {
            return (c) fVar;
        } catch (Exception e) {
            com.my.target.core.parsers.a.a("Unable to cast to standard section field '" + str + "', Operation: " + aVar.d + ", Unit: " + aVar.e, aVar, e, "Class cast exception");
            return null;
        }
    }

    static b e(f fVar, String str, com.my.target.core.parsers.a.a aVar) {
        try {
            return (b) fVar;
        } catch (Exception e) {
            com.my.target.core.parsers.a.a("Unable to cast to standard section field '" + str + "', Operation: " + aVar.d + ", Unit: " + aVar.e, aVar, e, "Class cast exception");
            return null;
        }
    }

    static JSONObject a(int i, JSONArray jSONArray, String str, com.my.target.core.parsers.a.a aVar) {
        JSONObject jSONObject = null;
        try {
            jSONObject = jSONArray.getJSONObject(i);
        } catch (JSONException e) {
            com.my.target.core.parsers.a.a("Unable to get JSONObject from JSONArray '" + str + "'", aVar, "JSONException");
        }
        return jSONObject;
    }

    static String b(int i, JSONArray jSONArray, String str, com.my.target.core.parsers.a.a aVar) {
        String str2 = null;
        try {
            str2 = jSONArray.getString(i);
        } catch (JSONException e) {
            com.my.target.core.parsers.a.a("Unable to get String from JSONArray " + str, aVar, "JSONException");
        }
        return str2;
    }

    static float b(JSONObject jSONObject, String str, com.my.target.core.parsers.a.a aVar) {
        float a = (float) a(jSONObject, str, aVar, 0.0d, false);
        if (((double) a) > 5.0d || ((double) a) < 0.0d) {
            com.my.target.core.parsers.a.a("Native Banner parse error: rating '" + a + "' is out of bounds [0, 5]", aVar, "Out of bounds rating");
        }
        return a;
    }

    static ImageData a(JSONObject jSONObject, String str, String str2, String str3, com.my.target.core.parsers.a.a aVar) {
        String a = a(jSONObject, str, aVar, "", false);
        int a2 = a(jSONObject, str2, aVar, 0);
        int a3 = a(jSONObject, str3, aVar, 0);
        if (a != null) {
            return new ImageData(a, a3, a2);
        }
        return null;
    }

    static ImageData c(JSONObject jSONObject, String str, com.my.target.core.parsers.a.a aVar) {
        Object a = a(jSONObject, str, aVar, "", false);
        if (TextUtils.isEmpty(a)) {
            return null;
        }
        return new ImageData(a);
    }
}
