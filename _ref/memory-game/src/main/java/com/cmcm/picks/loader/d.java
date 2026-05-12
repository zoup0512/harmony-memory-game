package com.cmcm.picks.loader;

import android.text.TextUtils;
import com.mopub.mobileads.VastIconXmlManager;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import org.json.JSONObject;

/* compiled from: BuinessDataItem */
public class d {
    private String a;
    private int b;
    private int c;
    private String d;
    private int e;
    private String f;
    private String g;
    private int h;
    private int i;
    private int j;

    public d(String str, int i, String str2, int i2, int i3, int i4) {
        if (!TextUtils.isEmpty(str)) {
            this.a = str.replace("&", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
        }
        this.b = -1;
        this.c = i;
        this.d = str2;
        this.h = i2;
        this.i = i3;
        this.j = i4;
    }

    public void a(String str, String str2) {
        this.f = str;
        this.g = str2;
    }

    public String a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pkg", this.a);
            jSONObject.put("sug", this.b);
            jSONObject.put("res", this.c);
            jSONObject.put("des", TextUtils.isEmpty(this.d) ? "" : this.d);
            if (!(TextUtils.isEmpty(this.f) || TextUtils.isEmpty(this.g))) {
                jSONObject.put("fbpos", this.f);
                jSONObject.put("fbmeta", this.g);
            }
            if (this.e > 0) {
                jSONObject.put("seq", this.e);
            }
            if (this.h > 0) {
                jSONObject.put(VastIconXmlManager.DURATION, this.h);
                jSONObject.put("playtime", this.i);
                jSONObject.put("event", this.j);
            }
            return jSONObject.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
