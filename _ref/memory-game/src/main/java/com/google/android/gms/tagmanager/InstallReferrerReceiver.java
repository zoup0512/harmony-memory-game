package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.google.android.gms.analytics.CampaignTrackingService;

public final class InstallReferrerReceiver extends CampaignTrackingReceiver {
    protected void zzh(Context context, String str) {
        zzbe.zzow(str);
        zzbe.zzw(context, str);
    }

    protected Class<? extends CampaignTrackingService> zzvv() {
        return InstallReferrerService.class;
    }
}
