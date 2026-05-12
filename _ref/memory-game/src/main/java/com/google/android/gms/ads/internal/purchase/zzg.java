package com.google.android.gms.ads.internal.purchase;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.stats.zzb;
import com.google.android.gms.internal.zzhr.zza;
import com.google.android.gms.internal.zzin;

@zzin
public final class zzg extends zza implements ServiceConnection {
    private Context mContext;
    private int mResultCode;
    zzb zzbws;
    private String zzbwy;
    private zzf zzbxc;
    private boolean zzbxi = false;
    private Intent zzbxj;

    public zzg(Context context, String str, boolean z, int i, Intent intent, zzf com_google_android_gms_ads_internal_purchase_zzf) {
        this.zzbwy = str;
        this.mResultCode = i;
        this.zzbxj = intent;
        this.zzbxi = z;
        this.mContext = context;
        this.zzbxc = com_google_android_gms_ads_internal_purchase_zzf;
    }

    public void finishPurchase() {
        int zzd = zzu.zzga().zzd(this.zzbxj);
        if (this.mResultCode == -1 && zzd == 0) {
            this.zzbws = new zzb(this.mContext);
            Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            intent.setPackage("com.android.vending");
            zzb.zzaux().zza(this.mContext, intent, this, 1);
        }
    }

    public String getProductId() {
        return this.zzbwy;
    }

    public Intent getPurchaseData() {
        return this.zzbxj;
    }

    public int getResultCode() {
        return this.mResultCode;
    }

    public boolean isVerified() {
        return this.zzbxi;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        com.google.android.gms.ads.internal.util.client.zzb.zzcw("In-app billing service connected.");
        this.zzbws.zzas(iBinder);
        String zzbz = zzu.zzga().zzbz(zzu.zzga().zze(this.zzbxj));
        if (zzbz != null) {
            if (this.zzbws.zzm(this.mContext.getPackageName(), zzbz) == 0) {
                zzh.zzs(this.mContext).zza(this.zzbxc);
            }
            zzb.zzaux().zza(this.mContext, this);
            this.zzbws.destroy();
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        com.google.android.gms.ads.internal.util.client.zzb.zzcw("In-app billing service disconnected.");
        this.zzbws.destroy();
    }
}
