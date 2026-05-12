package com.my.target.core.communication.js.calls;

import com.facebook.share.internal.ShareConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AbstractJSCall */
public abstract class a implements c {
    protected JSONObject a = new JSONObject();
    private String b;
    private JSONObject c = new JSONObject();

    public final String a() {
        return this.b;
    }

    public final JSONObject b() {
        return this.c;
    }

    public a(String str) {
        this.b = str;
        try {
            this.c.put("method", str);
            this.c.put(ShareConstants.WEB_DIALOG_PARAM_DATA, this.a);
        } catch (JSONException e) {
        }
    }
}
