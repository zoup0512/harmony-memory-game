package com.unity3d.ads.api;

import com.unity3d.ads.log.DeviceLog;
import org.json.JSONArray;

class AdUnit$1 implements Runnable {
    final /* synthetic */ JSONArray val$views;

    AdUnit$1(JSONArray jSONArray) {
        this.val$views = jSONArray;
    }

    public void run() {
        if (AdUnit.getAdUnitActivity() != null) {
            try {
                AdUnit.getAdUnitActivity().setViews(AdUnit.access$000(this.val$views));
            } catch (Exception e) {
                DeviceLog.exception("Corrupted viewlist", e);
            }
        }
    }
}
