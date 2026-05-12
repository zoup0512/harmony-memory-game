package com.my.target.core.communication.js.calls;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: JSInitCall */
public final class d extends a {
    public d(JSONObject jSONObject) {
        super("init");
        try {
            this.a.put("bannersJSON", jSONObject);
            this.a.put("version", 20);
        } catch (JSONException e) {
        }
    }
}
