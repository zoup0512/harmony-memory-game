package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.facebook.share.internal.ShareConstants;
import com.mopub.common.AdType;
import org.json.JSONObject;

@zzin
public class zzhl extends Handler {
    private final zzhk zzbwg;

    public zzhl(Context context) {
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        this(new zzhm(context));
    }

    public zzhl(zzhk com_google_android_gms_internal_zzhk) {
        this.zzbwg = com_google_android_gms_internal_zzhk;
    }

    private void zze(JSONObject jSONObject) {
        try {
            this.zzbwg.zza(jSONObject.getString("request_id"), jSONObject.getString("base_url"), jSONObject.getString(AdType.HTML));
        } catch (Exception e) {
        }
    }

    public void handleMessage(Message message) {
        try {
            Bundle data = message.getData();
            if (data != null) {
                JSONObject jSONObject = new JSONObject(data.getString(ShareConstants.WEB_DIALOG_PARAM_DATA));
                if ("fetch_html".equals(jSONObject.getString("message_name"))) {
                    zze(jSONObject);
                }
            }
        } catch (Exception e) {
        }
    }
}
