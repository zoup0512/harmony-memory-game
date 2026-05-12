package com.yandex.metrica.impl;

import android.text.TextUtils;
import com.yandex.metrica.PreloadInfo;
import com.yandex.metrica.impl.utils.f;
import org.json.JSONException;
import org.json.JSONObject;

public class aj {
    private PreloadInfo a;

    public aj(PreloadInfo preloadInfo) {
        if (preloadInfo == null) {
            return;
        }
        if (TextUtils.isEmpty(preloadInfo.getTrackingId())) {
            f.e().c("Required field \"PreloadInfo.trackingId\" is empty!\nThis preload info will be skipped.", new Object[0]);
        } else {
            this.a = preloadInfo;
        }
    }

    String a() {
        if (this.a == null) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("preloadInfo", b());
        } catch (JSONException e) {
        }
        return jSONObject.toString();
    }

    public JSONObject b() {
        if (this.a == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("trackingId", this.a.getTrackingId());
            if (this.a.getAdditionalParams().isEmpty()) {
                return jSONObject;
            }
            jSONObject.put("additionalParams", new JSONObject(this.a.getAdditionalParams()));
            return jSONObject;
        } catch (JSONException e) {
            return jSONObject;
        }
    }
}
