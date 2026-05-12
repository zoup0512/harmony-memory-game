package com.google.android.gms.ads.internal.purchase;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.cube.memorygames.billing.IabHelper;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzjx;

class zzi$1 implements ServiceConnection {
    final /* synthetic */ Context zzala;
    final /* synthetic */ zzi zzbxo;

    zzi$1(zzi com_google_android_gms_ads_internal_purchase_zzi, Context context) {
        this.zzbxo = com_google_android_gms_ads_internal_purchase_zzi;
        this.zzala = context;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        boolean z = false;
        zzb com_google_android_gms_ads_internal_purchase_zzb = new zzb(this.zzala.getApplicationContext(), false);
        com_google_android_gms_ads_internal_purchase_zzb.zzas(iBinder);
        int zzb = com_google_android_gms_ads_internal_purchase_zzb.zzb(3, this.zzala.getPackageName(), IabHelper.ITEM_TYPE_INAPP);
        zzjx zzft = zzu.zzft();
        if (zzb == 0) {
            z = true;
        }
        zzft.zzag(z);
        this.zzala.unbindService(this);
        com_google_android_gms_ads_internal_purchase_zzb.destroy();
    }

    public void onServiceDisconnected(ComponentName componentName) {
    }
}
