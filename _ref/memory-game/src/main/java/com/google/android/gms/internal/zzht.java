package com.google.android.gms.internal;

import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.internal.zzho.zza;

@zzin
public final class zzht extends zza {
    private final InAppPurchaseListener zzawh;

    public zzht(InAppPurchaseListener inAppPurchaseListener) {
        this.zzawh = inAppPurchaseListener;
    }

    public void zza(zzhn com_google_android_gms_internal_zzhn) {
        this.zzawh.onInAppPurchaseRequested(new zzhw(com_google_android_gms_internal_zzhn));
    }
}
