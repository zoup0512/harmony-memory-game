package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Build.VERSION;
import com.yandex.metrica.impl.ai;
import org.json.JSONException;
import org.json.JSONObject;

public class bs {
    private final String a;
    private final String b;
    private final String c;
    private final Point d;

    public bs(Context context) {
        String str;
        this.a = Build.MANUFACTURER;
        this.b = Build.MODEL;
        if (VERSION.SDK_INT > 8) {
            str = Build.SERIAL;
        } else {
            str = "";
        }
        this.c = str;
        int i = ai.a(context).y;
        int i2 = ai.a(context).x;
        this.d = new Point(Math.min(i, i2), Math.max(i, i2));
    }

    public bs(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        this.a = jSONObject.getString("manufacturer");
        this.b = jSONObject.getString("model");
        this.c = jSONObject.getString("serial");
        this.d = new Point(jSONObject.getInt("width"), jSONObject.getInt("height"));
    }

    public JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("manufacturer", this.a);
        jSONObject.put("model", this.b);
        jSONObject.put("serial", this.c);
        jSONObject.put("width", this.d.x);
        jSONObject.put("height", this.d.y);
        return jSONObject;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        bs bsVar = (bs) o;
        if (this.a == null ? bsVar.a != null : !this.a.equals(bsVar.a)) {
            return false;
        }
        if (this.b == null ? bsVar.b != null : !this.b.equals(bsVar.b)) {
            return false;
        }
        if (this.c == null ? bsVar.c != null : !this.c.equals(bsVar.c)) {
            return false;
        }
        if (this.d != null) {
            return this.d.equals(bsVar.d);
        }
        if (bsVar.d != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.a != null) {
            hashCode = this.a.hashCode();
        } else {
            hashCode = 0;
        }
        int i2 = hashCode * 31;
        if (this.b != null) {
            hashCode = this.b.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.c != null) {
            hashCode = this.c.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + i2) * 31;
        if (this.d != null) {
            i = this.d.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "DeviceShapshot{mManufacturer='" + this.a + '\'' + ", mModel='" + this.b + '\'' + ", mSerial='" + this.c + '\'' + ", mScreenSize=" + this.d + '}';
    }
}
