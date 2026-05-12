package com.google.android.gms.analytics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresPermission;
import android.text.TextUtils;
import com.google.android.gms.analytics.internal.zzaf;
import com.google.android.gms.analytics.internal.zzao;
import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzvw;

public class CampaignTrackingReceiver extends BroadcastReceiver {
    static Object zzamr = new Object();
    static zzvw zzcrw;
    static Boolean zzcrx;

    public static boolean zzav(Context context) {
        zzab.zzy(context);
        if (zzcrx != null) {
            return zzcrx.booleanValue();
        }
        boolean zzb = zzao.zzb(context, "com.google.android.gms.analytics.CampaignTrackingReceiver", true);
        zzcrx = Boolean.valueOf(zzb);
        return zzb;
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public void onReceive(Context context, Intent intent) {
        zzf zzay = zzf.zzay(context);
        zzaf zzyx = zzay.zzyx();
        if (intent == null) {
            zzyx.zzek("CampaignTrackingReceiver received null intent");
            return;
        }
        String stringExtra = intent.getStringExtra("referrer");
        String action = intent.getAction();
        zzyx.zza("CampaignTrackingReceiver received", action);
        if (!"com.android.vending.INSTALL_REFERRER".equals(action) || TextUtils.isEmpty(stringExtra)) {
            zzyx.zzek("CampaignTrackingReceiver received unexpected intent without referrer extra");
            return;
        }
        boolean zzaw = CampaignTrackingService.zzaw(context);
        if (!zzaw) {
            zzyx.zzek("CampaignTrackingService not registered or disabled. Installation tracking not possible. See http://goo.gl/8Rd3yj for instructions.");
        }
        zzh(context, stringExtra);
        if (zzay.zzyy().zzabc()) {
            zzyx.zzel("Received unexpected installation campaign on package side");
            return;
        }
        Class zzvv = zzvv();
        zzab.zzy(zzvv);
        Intent intent2 = new Intent(context, zzvv);
        intent2.putExtra("referrer", stringExtra);
        synchronized (zzamr) {
            context.startService(intent2);
            if (zzaw) {
                try {
                    if (zzcrw == null) {
                        zzcrw = new zzvw(context, 1, "Analytics campaign WakeLock");
                        zzcrw.setReferenceCounted(false);
                    }
                    zzcrw.acquire(1000);
                } catch (SecurityException e) {
                    zzyx.zzek("CampaignTrackingService service at risk of not starting. For more reliable installation campaign reports, add the WAKE_LOCK permission to your manifest. See http://goo.gl/8Rd3yj for instructions.");
                }
                return;
            }
        }
    }

    protected void zzh(Context context, String str) {
    }

    protected Class<? extends CampaignTrackingService> zzvv() {
        return CampaignTrackingService.class;
    }
}
