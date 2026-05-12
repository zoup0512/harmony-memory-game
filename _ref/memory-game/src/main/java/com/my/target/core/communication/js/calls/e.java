package com.my.target.core.communication.js.calls;

import org.json.JSONException;

/* compiled from: JSStartCall */
public final class e extends a {
    public e(String str, int i) {
        super("start");
        try {
            this.a.put("format", str);
            this.a.put("orientation", i);
        } catch (JSONException e) {
        }
    }
}
