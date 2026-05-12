package com.yandex.metrica.impl.ob;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.json.JSONException;
import org.json.JSONObject;

public class bq {
    private final String a;
    private final bs b;
    private final long c;
    private final boolean d;
    private final long e;

    public bq(JSONObject jSONObject, long j) throws JSONException {
        this.a = jSONObject.getString("device_id");
        if (jSONObject.has("device_snapshot_key")) {
            this.b = new bs(jSONObject.getString("device_snapshot_key"));
        } else {
            this.b = null;
        }
        this.c = jSONObject.optLong("last_elections_time", -1);
        this.d = f();
        this.e = j;
    }

    public bq(String str, bs bsVar, long j) {
        this.a = str;
        this.b = bsVar;
        this.c = j;
        this.d = f();
        this.e = -1;
    }

    public String a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("device_id", this.a);
        if (this.b != null) {
            jSONObject.put("device_snapshot_key", this.b.a());
        }
        jSONObject.put("last_elections_time", this.c);
        return jSONObject.toString();
    }

    public boolean b() {
        if (this.e <= -1) {
            return false;
        }
        Calendar instance = GregorianCalendar.getInstance();
        instance.setTimeInMillis(this.e);
        if (instance.get(1) == 1970) {
            return true;
        }
        return false;
    }

    public String c() {
        return this.a;
    }

    public bs d() {
        return this.b;
    }

    public boolean e() {
        return this.d;
    }

    private boolean f() {
        if (this.c <= -1 || System.currentTimeMillis() - this.c >= 604800000) {
            return false;
        }
        return true;
    }

    public boolean a(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        bq bqVar = (bq) obj;
        if (this.d != bqVar.d) {
            return false;
        }
        if (!this.a.equals(bqVar.a)) {
            return false;
        }
        if (this.b != null) {
            return this.b.equals(bqVar.b);
        }
        if (bqVar.b != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = this.a.hashCode() * 31;
        if (this.b != null) {
            hashCode = this.b.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + hashCode2) * 31;
        if (this.d) {
            i = 1;
        }
        return hashCode + i;
    }

    public String toString() {
        return "Credentials{mFresh=" + this.d + ", mLastElectionsTime=" + this.c + ", mDeviceSnapshot=" + this.b + ", mDeviceID='" + this.a + '\'' + '}';
    }
}
