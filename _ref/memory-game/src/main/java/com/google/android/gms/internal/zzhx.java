package com.google.android.gms.internal;

import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.internal.zzhs.zza;

@zzin
public final class zzhx extends zza {
    private final PlayStorePurchaseListener zzawj;

    public zzhx(PlayStorePurchaseListener playStorePurchaseListener) {
        this.zzawj = playStorePurchaseListener;
    }

    public boolean isValidPurchase(String str) {
        return this.zzawj.isValidPurchase(str);
    }

    public void zza(zzhr com_google_android_gms_internal_zzhr) {
        this.zzawj.onInAppPurchaseFinished(new zzhv(com_google_android_gms_internal_zzhr));
    }
}
