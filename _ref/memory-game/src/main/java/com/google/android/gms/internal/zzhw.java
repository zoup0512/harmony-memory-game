package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.purchase.InAppPurchase;

@zzin
public class zzhw implements InAppPurchase {
    private final zzhn zzbxb;

    public zzhw(zzhn com_google_android_gms_internal_zzhn) {
        this.zzbxb = com_google_android_gms_internal_zzhn;
    }

    public String getProductId() {
        try {
            return this.zzbxb.getProductId();
        } catch (Throwable e) {
            zzb.zzd("Could not forward getProductId to InAppPurchase", e);
            return null;
        }
    }

    public void recordPlayBillingResolution(int i) {
        try {
            this.zzbxb.recordPlayBillingResolution(i);
        } catch (Throwable e) {
            zzb.zzd("Could not forward recordPlayBillingResolution to InAppPurchase", e);
        }
    }

    public void recordResolution(int i) {
        try {
            this.zzbxb.recordResolution(i);
        } catch (Throwable e) {
            zzb.zzd("Could not forward recordResolution to InAppPurchase", e);
        }
    }
}
