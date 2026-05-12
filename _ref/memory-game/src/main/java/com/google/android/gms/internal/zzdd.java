package com.google.android.gms.internal;

import android.content.Context;
import android.os.Build.VERSION;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.zzu;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.LinkedHashMap;
import java.util.Map;

@zzin
public class zzdd {
    private Context mContext = null;
    private String zzarj = null;
    private boolean zzbdo;
    private String zzbdp;
    private Map<String, String> zzbdq;

    public zzdd(Context context, String str) {
        this.mContext = context;
        this.zzarj = str;
        this.zzbdo = ((Boolean) zzdc.zzaze.get()).booleanValue();
        this.zzbdp = (String) zzdc.zzazf.get();
        this.zzbdq = new LinkedHashMap();
        this.zzbdq.put("s", "gmob_sdk");
        this.zzbdq.put("v", "3");
        this.zzbdq.put("os", VERSION.RELEASE);
        this.zzbdq.put("sdk", VERSION.SDK);
        this.zzbdq.put("device", zzu.zzfq().zztg());
        this.zzbdq.put(SettingsJsonConstants.APP_KEY, context.getApplicationContext() != null ? context.getApplicationContext().getPackageName() : context.getPackageName());
        this.zzbdq.put("is_lite_sdk", zzu.zzfq().zzan(context) ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        zziv zzy = zzu.zzfw().zzy(this.mContext);
        this.zzbdq.put("network_coarse", Integer.toString(zzy.zzcgp));
        this.zzbdq.put("network_fine", Integer.toString(zzy.zzcgq));
    }

    Context getContext() {
        return this.mContext;
    }

    String zzhl() {
        return this.zzarj;
    }

    boolean zzjz() {
        return this.zzbdo;
    }

    String zzka() {
        return this.zzbdp;
    }

    Map<String, String> zzkb() {
        return this.zzbdq;
    }
}
